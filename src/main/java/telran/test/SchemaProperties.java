package telran.test;

import java.lang.reflect.Field;

import telran.reflect.Id;
import telran.reflect.Index;

public class SchemaProperties {
	public static void displayFieldProperties(Object obj)throws Exception {
		int idCounter = 0;
		Field [] fields = obj.getClass().getDeclaredFields();
		for(Field field: fields) {
			if(field.isAnnotationPresent(Id.class)) {
				field.setAccessible(true);
				if(idCounter > 0) {
					throw new IllegalStateException("Field Id must be one");
				}
				System.out.println("id: " + field.get(obj));
				idCounter++;
			}
			if(field.isAnnotationPresent(Index.class)) {
				field.setAccessible(true);
				System.out.println("index: " + field.get(obj));
			}
		}
		if(idCounter == 0) {
			throw new IllegalStateException("No field Id found");
		}
	}
}
