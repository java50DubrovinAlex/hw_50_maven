package telran.test;

import java.lang.reflect.Method;

import telran.reflect.BeforeEach;
import telran.reflect.Test;

public class TestLibrary {
	public static void launchTest(Object obj)throws Exception {
		boolean isBeforeEach = false;
		Method beForeEach = null;
		Method [] methods = obj.getClass().getDeclaredMethods();
		for(Method method: methods) {
			if(method.isAnnotationPresent(BeforeEach.class)) {
				method.setAccessible(true);
				beForeEach = method;
				isBeforeEach = true;
			}
		}
		for(Method method: methods) {
			if(method.isAnnotationPresent(Test.class) && isBeforeEach) {
				method.setAccessible(true);
				beForeEach.invoke(obj);
				method.invoke(obj);
				
			}
			if(method.isAnnotationPresent(Test.class) && !isBeforeEach) {
				method.setAccessible(true);
				method.invoke(obj);
			}
			
		}
	}
	
}
