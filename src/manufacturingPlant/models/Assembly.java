package manufacturingPlant.models;

/**
 * Dit is de abstrace klasse Assembly. Hierin kan een naam
 * voor een Assembly opgegeven worden. In het systeem voor Use Case
 * Industries gaat het daarbij om MadeParts, BoughtParts en Products.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public abstract class Assembly {

	//De naam van de Assembly
	private String name;

	/**
	 * De Constructor van Assemby
	 * @param name de name van de Assembly
	 * @require name != ""
	 * @ensure this.name == name
	 */
	public Assembly(String name) {
		this.name = name;
	}
	
	/**
	 * Geeft de naam van deze Assembly terug
	 * @return this.name
	 * @ensure result != ""
	 */
	public String getName() {
		return name;
	}
}
