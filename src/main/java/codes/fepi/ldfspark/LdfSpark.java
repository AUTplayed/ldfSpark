package codes.fepi.ldfspark;

import eu.infomas.annotation.AnnotationDetector;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class LdfSpark {

	private static PageLogic pageLogic;
	private static StaticLogic staticLogic;
	private static List<String> pages;

	/**
	 * Start the page setup. Default values: <br/>
	 * templateFolder=templates <br/>
	 * staticFolder=public
	 */
	public static void start() {
		start("templates", "public");
	}

	/**
	 * Start the page setup. Default values: <br/>
	 * staticFolder=public
	 */
	public static void start(String templateFolder) {
		start(templateFolder, "public");
	}

	/**
	 * Start the page setup.
	 */
	public static void start(String templateFolder, String staticFolder) {
		if(pages != null) throw new RuntimeException("This method is only supposed to be called once");
		pages = new LinkedList<>();
		pageLogic = new PageLogic(templateFolder);
		staticLogic = new StaticLogic(staticFolder);
		staticLogic.init();

		try {
			new AnnotationDetector(new AnnotationDiscover(LdfSpark::handleHandlerMethod)).detect();
			staticLogic.route(pages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the spark server and clears all pages
	 */
	public static void stop() {
		pages = null;
		spark.Spark.stop();
	}

	private static void handleHandlerMethod(Method method) {
		pages.add(method.getName());
		pageLogic.page(method.getName(), method);
	}
}
