package codes.fepi.ldfspark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.lang.reflect.Method;

import static spark.Spark.get;

class PageLogic {

	private MustacheTemplateEngine mte;
	private Logger LOGGER;

	PageLogic(String templateFolder) {
		mte = new MustacheTemplateEngine(templateFolder);
		LOGGER = LoggerFactory.getLogger("LdfPageLogic");
	}

	void page(Method method, Object handler) {
		String page = method.getName();
		String route = String.format("/pages/%s/%s.html", page, page);
		LOGGER.info("registered page: {}", route);
		get(route, (req, res) -> {
			Object entity = null;
			switch (method.getParameterCount()) {
				case 2:
					entity = method.invoke(handler, req, res); break;
				case 1:
					entity = method.invoke(handler, req); break;
				case 0:
					entity = method.invoke(handler); break;
			}
			return mte.render(new ModelAndView(entity, page + ".html"));
		});
	}
}
