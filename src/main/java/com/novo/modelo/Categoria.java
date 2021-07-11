package com.novo.modelo;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "categorias")
public class Categoria {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcat;
	
	@Column(name = "nomcat", length = 20)
	private String nomcat;

	public Categoria() {
		super();
		
	}
	
	public Categoria(int idcat, String nomcat) {
		super();
		this.idcat = idcat;
		this.nomcat = nomcat;
	}

	public int getIdcat() {
		return idcat;
	}

	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}

	public String getNomcat() {
		return nomcat;
	}

	public void setNomcat(String nomcat) {
		this.nomcat = nomcat;
	}
	
	
	
}
