package net.webapp.modeles;

public class SocieteDto  {

	private Long id;
	private String nom;

	public SocieteDto() {
		super();
	}

	public SocieteDto(String nom) {
		super();
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
