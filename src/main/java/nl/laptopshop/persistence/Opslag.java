package nl.laptopshop.persistence;

public class Opslag {
	private int opslagId;
	private String opslagType;
	private int opslagCapiciteit;

	public Opslag(int id, String opslagType, int opslagCapiciteit) {
		this.opslagCapiciteit = opslagCapiciteit;
		this.opslagId = id;
		this.opslagType = opslagType;
	}

	public int getOpslagCapiciteit() {
		return opslagCapiciteit;
	}

	public int getOpslagId() {
		return opslagId;
	}

	public String getOpslagType() {
		return opslagType;
	}

	public void setOpslagCapiciteit(int opslagCapiciteit) {
		this.opslagCapiciteit = opslagCapiciteit;
	}

	public void setOpslagId(int opslagId) {
		this.opslagId = opslagId;
	}

	public void setOpslagType(String opslagType) {
		this.opslagType = opslagType;
	}
}
