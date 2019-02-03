package codes.fepi.libuser;

import java.util.List;

public class TestEntity {
	private String name;
	private List<String> features;

	public TestEntity(String name, List<String> features) {
		this.name = name;
		this.features = features;
	}

	public String getName() {
		return name;
	}

	public List<String> getFeatures() {
		return features;
	}
}
