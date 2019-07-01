package nl.laptopshop.persistence;

public class Leverancier {
	private int leverancierId;
	private String naam;

	public Leverancier(int id, String naam) {
		this.leverancierId = id;
		this.naam = naam;
	}

	public int getLeverancierId() {
		return leverancierId;
	}

	public String getNaam() {
		return naam;
	}

	public void setLeverancierId(int leverancierId) {
		this.leverancierId = leverancierId;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

}
