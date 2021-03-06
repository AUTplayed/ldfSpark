package codes.fepi.libuser;

import codes.fepi.ldfspark.PageHandler;
import spark.Request;

import java.util.Arrays;

@PageHandler
public class TestHandler {

	public Object index() {
		return new TestEntity("ldfSpark", Arrays.asList("static rendering", "single page application"));
	}

	public Object other(Request req) {
		return req.userAgent();
	}
}
