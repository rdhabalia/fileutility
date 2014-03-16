package com.fileobj.feeder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fileobj.annotation.Column;
import com.fileobj.bean.SimpleRecord;
import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.exception.ExceptionUtil;
import com.fileobj.helper.ObjectConverter;
/**
 * @author rdhabalia
 *
 */
public class DataFeeder {

	
	@SuppressWarnings("unchecked")
	public static <T> List<T> feedRecord(Class<T> to, List<SimpleRecord> from) throws  BaseAppException{
	

		List<T> userRecords = new ArrayList<T>();
		try {
			Class<T> c = to;
        	List<SimpleRecord> rawRecords = from;
            Field[] fieldsUserData = c.getDeclaredFields();

            for(SimpleRecord r: rawRecords){
            	
            	Object o = c.newInstance();
            	for(int i=0; i<fieldsUserData.length; i++){
                	Field field = fieldsUserData[i];
                	if(field.isAnnotationPresent(Column.class)){
                		Column column = field.getAnnotation(Column.class);
                		int id = -1;
                		if(column!=null){
                			id = column.id();
                		}
                		if(id!=-1){
                			if(r.getColumnSet().size()>id){
                				field.setAccessible(true);
                				field.set(o,ObjectConverter.convert(r.getColumnSet().get(id), field.getType()));
                			}
                		}
                	}
                }
            	userRecords.add((T) o);
            }
            
        } catch (IllegalAccessException iae) {
        	ExceptionUtil.logException(iae, Level.ALL);
        	throw new BaseAppException(iae,ErrorCodeEnum.APPLICATION_ERROR_20003);
        } catch (SecurityException e) {
        	ExceptionUtil.logException(e, Level.ALL);
        	throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003);
		} catch (InstantiationException e) {
			ExceptionUtil.logException(e, Level.ALL);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003);
		} 
		
		return userRecords;
	}
	
}
