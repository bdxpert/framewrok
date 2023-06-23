package com.framework.artisan.testing.core;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FWContext implements Cloneable {

	private static List<Object> objectMap = new ArrayList<>();
	private static List<Object> objectMapDI = new ArrayList<>();

	public FWContext() {
		try {
			// find and instantiate all classes annotated with the @TestClass annotation
			Reflections reflections = new Reflections("");
			Set<Class<?>> types = reflections.getTypesAnnotatedWith(TestClass.class);
			for (Class<?> implementationClass : types) {
				objectMap.add((Object) implementationClass.newInstance());
			}
			// find and instantiate all classes annotated with the @Service annotation
			Set<Class<?>> typesDI = reflections.getTypesAnnotatedWith(Service.class);
			for (Class<?> implementationClass : typesDI) {
				objectMapDI.add((Object) implementationClass.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		performDI();
	}

	public void start() {
		try {
			for (Object theTestClass : objectMap) {
				Method[] methods =  theTestClass.getClass().getDeclaredMethods();
				List<Method> bMethods = Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Before.class)).collect(Collectors.toList());


				// find all methods annotated with the @Test annotation
				for (Method method : theTestClass.getClass().getDeclaredMethods()) {
					if (method.isAnnotationPresent(Test.class)) {
						for (Method BeforeMethod : bMethods) {
							BeforeMethod.invoke(theTestClass);
						}
						method.invoke(theTestClass);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void performDI() {
		try {
			for (Object theTestClass : objectMap) {
// find annotated fields
				for (Field field : theTestClass.getClass().getDeclaredFields()) {
					if (field.isAnnotationPresent(Inject.class)) {
// get the type of the field
						Class<?> theFieldType =field.getType();
//get the object instance of this type
						Object instance = getBeanOftype(theFieldType);
//do the injection
						field.setAccessible(true);
						field.set(theTestClass, instance);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Object getBeanOftype(Class interfaceClass) {
		Object service = null;
		try {
			for (Object theTestClass : objectMapDI) {
				Class<?>[] interfaces = theTestClass.getClass().getInterfaces();
				for (Class<?> theInterface : interfaces) {
					if (theInterface.getName().contentEquals(interfaceClass.getName()))
						service = theTestClass;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}

}
