# ldfSpark

A java library for bootstrapping a web application with java backend, and ldf+mustache frontend

To get started look at the example in the [test dir](https://github.com/AUTplayed/ldfSpark/tree/master/src/test)

This library is supposed to greatly simplify creating a new application with my [ldf framework](https://github.com/AUTplayed/ldf)

The user only has to implement a Handler Class for handling the page content (annotated with @PageHandler):

	@PageHandler
	public class Handler {

		public static Object index() {
			return "page content";
		}

		public static Object otherPage(Request req) {
			return "other page content";
		}
		
		public static Object login(Request req, Response res) {
			return "login page content";
		}
	}
	
The method names correspond to the page names, if a method for a page doesn't exist, the page will 404
