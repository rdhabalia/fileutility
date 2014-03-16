package com.fileobj.annotation;
/**
 * @author rdhabal
 *
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Record{
	/*
	 * It represents type of record.
	 * i.e
	 * 1. Data
	 * 2. MetaData
	 * 3. Header 
	 */
	String type() default "data"; 
}
