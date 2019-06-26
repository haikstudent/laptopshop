package nl.hu.ipass.laptopshop.persistence;

public class Product {
	private int id;
	private String resolutie;
	private int werkgeheugen;
	private String model;
	private double prijs;
	private int gkaartId;
	private int processorId;
	private int opslagId;
	private int leverancierId;
	
	private String gkaartNaam;
	private String processorNaam;
	private String opslagNaam;
	private String leverancierNaam;
	
	
	public Product(int id, String resolutie, int werkgeheugen, String model, double prijs, int kaart, int processor, int opslag, int leverancier) {
		this.id = id;
		this.resolutie = resolutie;
		this.werkgeheugen = werkgeheugen;
		this.model = model;
		this.prijs = prijs;
		this.gkaartId = kaart;
		this.processorId = processor;
		this.opslagId = opslag;
		this.leverancierId = leverancier;
	}
	
	public Product(int id, String resolutie, int werkgeheugen, String model, double prijs, String kaartNaam, String processorNaam, String opslagNaam, String leverancierNaam) {
		this.id = id;
		this.resolutie = resolutie;
		this.werkgeheugen = werkgeheugen;
		this.model = model;
		this.prijs = prijs;
		this.gkaartNaam = kaartNaam;
		this.processorNaam = processorNaam;
		this.opslagNaam = opslagNaam;
		this.leverancierNaam = leverancierNaam;
	}
	
	
	
	public String getGkaartNaam() {
		return gkaartNaam;
	}
	
	public String getLeverancierNaam() {
		return leverancierNaam;
	}
	
	public String getOpslagNaam() {
		return opslagNaam;
	}
	
	public String getProcessorNaam() {
		return processorNaam;
	}
	
	public void setGkaartNaam(String gkaartNaam) {
		this.gkaartNaam = gkaartNaam;
	}
	
	public void setLeverancierNaam(String leverancierNaam) {
		this.leverancierNaam = leverancierNaam;
	}
	
	public void setOpslagNaam(String opslagNaam) {
		this.opslagNaam = opslagNaam;
	}
	
	public void setProcessorNaam(String processorNaam) {
		this.processorNaam = processorNaam;
	}
	
	
	public int getGkaartId() {
		return gkaartId;
	}
	
	public int getLeverancierId() {
		return leverancierId;
	}
	
	public int getOpslagId() {
		return opslagId;
	}
	
	public int getProcessorId() {
		return processorId;
	}
	
	public void setGkaartId(int gkaartId) {
		this.gkaartId = gkaartId;
	}
	
	public void setLeverancierId(int leverancierId) {
		this.leverancierId = leverancierId;
	}
	
	public void setOpslagId(int opslagId) {
		this.opslagId = opslagId;
	}
	
	public void setProcessorId(int processorId) {
		this.processorId = processorId;
	}
	
	public int getId() {
		return id;
	}
	
	public String getResolutie() {
		return resolutie;
	}
	
	public int getWerkgeheugen() {
		return werkgeheugen;
	}
	
	public String getModel() {
		return model;
	}
	
	public double getPrijs() {
		return prijs;
	}
	
	public void setResolutie(String resolutie) {
		this.resolutie = resolutie;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setWerkgeheugen(int werkgeheugen) {
		this.werkgeheugen = werkgeheugen;
	}
	
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
}
