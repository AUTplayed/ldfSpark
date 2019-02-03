package codes.fepi.ldfspark;

import codes.fepi.ldfspark.PageHandler;
import eu.infomas.annotation.AnnotationDetector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class AnnotationDiscover implements AnnotationDetector.TypeReporter {

	private Consumer<Method> consumer;

	public AnnotationDiscover(Consumer<Method> consumer) {
		super();
		this.consumer = consumer;
	}

	@Override
	public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getMethods()) {
				if (method.getDeclaringClass() == Object.class && !method.isAccessible()) continue;
				consumer.accept(method);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("something went horribly wrong with the library: " + e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends Annotation>[] annotations() {
		return new Class[]{PageHandler.class};
	}

}
