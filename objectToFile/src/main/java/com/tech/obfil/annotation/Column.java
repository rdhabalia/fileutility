package com.tech.obfil.annotation;
/**
 * @author rdhabalia
 *@Class: This class will be used by reflection to retrieve Record values 
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	int id() default -1;
}
