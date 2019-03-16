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
	
The method names correspond to the page names, if a method for a page doesn't exist, the page will 404.
The page names also correspond to the template names.

For example:
- Template file called **login.html** in template folder
- Method in PageHandler **public static Object login()**

If you include ldf in the index.html in the public folder [like this](https://github.com/AUTplayed/ldfSpark/blob/master/src/test/resources/public/index.html#L8)
and disable hashurls then calling ***host*/login** will result in login.html being responded with the content of the login() method.

In the resources directory you will have to have 2 folders, 
- one for static resources (default "public")
- one for the mustache-flavored template pages (default "templates") 

To start the server/logic you have to call 
	LdfSpark.start();
or
	LdfSpark.start("templateFolder");
or
	LdfSpark.start("templateFolder", "publicFolder");
	
also you have to configure your spark config beforehand (like the port used) like this:

	spark.Spark.port(8080);
	LdfSpark.start();

