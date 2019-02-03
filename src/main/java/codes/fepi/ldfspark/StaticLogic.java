package codes.fepi.ldfspark;

import spark.Request;
import spark.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

class StaticLogic {

	private String staticFolder;
	private byte[] indexPage;

	StaticLogic(String staticFolder) {
		this.staticFolder = staticFolder;
	}

	void init() {
		if (!staticFolder.startsWith("/")) {
			staticFolder = "/" + staticFolder;
		}
		staticFiles.location(staticFolder);
		staticFiles.expireTime(60 * 60);
	}

	void route(List<String> pages) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("public/index.html");
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] data = new byte[8192];
		int nRead;
		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		indexPage = buffer.toByteArray();
		pages.forEach(page -> get(page, this::index));
	}

	private byte[] index(Request req, Response res) {
		return indexPage;
	}
}
