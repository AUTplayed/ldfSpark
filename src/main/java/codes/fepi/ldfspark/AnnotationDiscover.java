package codes.fepi.ldfspark;

import eu.infomas.annotation.AnnotationDetector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;

public class AnnotationDiscover implements AnnotationDetector.TypeReporter {

	private BiConsumer<Method, Object> consumer;

	public AnnotationDiscover(BiConsumer<Method, Object> consumer) {
		super();
		this.consumer = consumer;
	}

	@Override
	public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
		try {
			Class<?> clazz = Class.forName(className);
			Object pageHandler = clazz.newInstance();
			for (Method method : clazz.getMethods()) {
				if (method.getDeclaringClass() == Object.class && !method.isAccessible()) continue;
				consumer.accept(method, pageHandler);
			}
		} catch (Exception e) {
			System.out.println("something went wrong with the reflection: ");
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends Annotation>[] annotations() {
		return new Class[]{PageHandler.class};
	}

}
