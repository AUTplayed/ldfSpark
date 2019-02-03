package codes.fepi;

import codes.fepi.ldfspark.LdfSpark;

public class ManualTest {
	public static void main(String[] args) {
		spark.Spark.port(8080);
		LdfSpark.start();
	}
}
