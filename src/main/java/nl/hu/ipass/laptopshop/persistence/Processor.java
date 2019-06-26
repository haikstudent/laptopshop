package nl.hu.ipass.laptopshop.persistence;

public class Processor {
	private int processorId;
	private String naam;
	private int klokFrequentie;
	private int aantalCores;
	private Leverancier lv;

	public Processor(int id, String naam, int klokFreq, int aantalCores) {
		this.processorId = id;
		this.naam = naam;
		this.klokFrequentie = klokFreq;
		this.aantalCores = aantalCores;
	}

	public int getAantalCores() {
		return aantalCores;
	}

	public int getKlokFrequentie() {
		return klokFrequentie;
	}

	public String getNaam() {
		return naam;
	}

	public int getProcessorId() {
		return processorId;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAantalCores(int aantalCores) {
		this.aantalCores = aantalCores;
	}

	public void setKlokFrequentie(int klokFrequentie) {
		this.klokFrequentie = klokFrequentie;
	}

	public void setProcessorId(int processorId) {
		this.processorId = processorId;
	}

	public Leverancier getLv() {
		return lv;
	}
	
	public void setLv(Leverancier lv) {
		this.lv = lv;
	}
}
