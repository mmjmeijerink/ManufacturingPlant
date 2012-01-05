package manufacturingPlant.models;

public abstract class Assembly {

	private String name;

	public Assembly(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
