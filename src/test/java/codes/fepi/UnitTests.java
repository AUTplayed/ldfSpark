package codes.fepi;

import codes.fepi.ldfspark.LdfSpark;
import org.junit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UnitTests {

	private static final int TEST_PORT = 8080;

	@BeforeClass
	public static void prep() {
		spark.Spark.port(TEST_PORT);
		LdfSpark.start();
	}

	@Test
	public void testPage() {
		String response = request("/pages/index/index.html");
		assert response.startsWith("<h1>This is ldfSpark</h1>");
	}

	@Test
	public void testStatic() {
		String response = request("/");
		assert response.startsWith("<h1>index</h1>");
		response = request("/index");
		assert response.startsWith("<h1>index</h1>");
	}

	@AfterClass
	public static void teardown() throws InterruptedException {
		LdfSpark.stop();
		//wait for shutdown
		Thread.sleep(100);

	}

	private String request(String path) {
		try {
			URL url = new URL("http://localhost:" + TEST_PORT + path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
