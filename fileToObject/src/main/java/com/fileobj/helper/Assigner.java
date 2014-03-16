package com.fileobj.helper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ExceptionUtil;


/**
 * This class helps setting up values of any field in the class. 
 * @author rdhabalia
 *
 */
public class Assigner {

	private static final Assigner assigner = new Assigner();
	
	public static Assigner getInstance(){
		return assigner;
	}
	
	/**
	 * It can add element in list and set list based on argument.
	 * 
	 * class A{
	 * 
	 * List<B> bs;
	 *  
	 * }
	 * 
	 * 1. addToListField(A a, B b) => bs.add(b)
	 * 2. addToListField(A a, List<B> b) => bs = b;
	 * 
	 * @param obj : Class which contains that field.
	 * @param value : Value of that field.
	 * @return boolean: false: If it doesn't find appropriate object to set the value.
	 * @throws BaseAppException 
	 */
	public boolean addToListField(Object obj, Object value) throws BaseAppException{
		
		boolean isFind= false;
		
		if(value!=null){
			
			try{
				
				for (Field field : obj.getClass().getDeclaredFields()) {
					
					field.setAccessible(true);
					
				    Type type = field.getGenericType();
				    
				    if (type instanceof ParameterizedType) {
				        ParameterizedType elementType = (ParameterizedType)type;
				        Type element = (elementType.getActualTypeArguments()[0]);
				        
				        if(field.getType().equals(List.class)){
				        	
				        	if(value instanceof List){
				        		
				        		List list = (List)value;
				        		if(!list.isEmpty()){
				        			
				        			Class eType = list.get(0).getClass();
							        
							        if(eType.equals(element)){
							        	field.set(obj, value);
							        }
				        			
				        		}
				        		
				        		
				        	}else if(element.equals(value.getClass())){
					        	List l = (List) field.get(obj);
					        	
					        	if(l==null){
					        		l = new ArrayList();
					        	}
					        	
					        	l.add(value);
					        	field.set(obj, l);
					        	
					        	isFind = true;
					        	
					        	break;
					        }
				        }
				        
				        
				    } 
				}
				
			}catch(Exception th){
				ExceptionUtil.logException(th, Level.ALL);
				throw new BaseAppException(th);
			}
			
		}else{
			isFind = true;
		}
		
		
		
		return isFind;
	}
	
	
	/**
	 * It first tries to set value to any non-List field. 
	 * If it it doesn't find then it considers value as List and tries to set list value.
	 * @param obj : Class which contains that field.
	 * @param value : Value of that field.
	 * @return: boolean : false: If it doesn't find appropriate object to set the value.
	 * @throws BaseAppException 
	 */
	public boolean addToField(Object obj, Object value) throws BaseAppException{
		
		if(!addToSingleObjectFiled(obj,value)){
			return addToListField(obj,value);
		}else{
			return true;
		}
		
	}
	
	
	/**
	 * It tries to set the value of non-List field.
	 * @param obj : Class which contains that field.
	 * @param value : Value of that field.
	 * @return: boolean : false: If it doesn't find appropriate object to set the value.
	 * @throws BaseAppException 
	 */
	public boolean addToSingleObjectFiled(Object obj, Object value) throws BaseAppException{
		
		boolean isFind = false;
		
		try{
			
			for (Field field : obj.getClass().getDeclaredFields()) {

				if(field.getType().equals(value.getClass())){
					field.setAccessible(true);
			    	field.set(obj, value);
			    	isFind = true;
			    	break;
			    }
				
			}
			
		}catch(Throwable th){
			ExceptionUtil.logException(th, Level.ALL);
			throw new BaseAppException(th);
		}
	
		return isFind;
		
	}
	
	
}
