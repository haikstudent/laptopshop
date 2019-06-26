package nl.hu.ipass.laptopshop.persistence;

public class Grafischkaart {
	private int gkaartId;
	private String naam;
	private int werkgeheugen;
	private Leverancier lv;
	
	
	public Grafischkaart(int gkaart_id, String naam, int werkgeheugen) {
		this.gkaartId = gkaart_id;
		this.naam = naam;
		this.werkgeheugen = werkgeheugen;
	}
	
	
	public int getGkaart_id() {
		return gkaartId;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public int getWerkgeheugen() {
		return werkgeheugen;
	}
	
	public void setGkaart_id(int gkaart_id) {
		this.gkaartId = gkaart_id;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public void setWerkgeheugen(int werkgeheugen) {
		this.werkgeheugen = werkgeheugen;
	}
	
	public Leverancier getLv() {
		return lv;
	}
	
	public void setLv(Leverancier lv) {
		this.lv = lv;
	}
	
}
