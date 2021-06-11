package com.novo.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idprod;
	
	@Column(name = "nomprod")
	private String nomprod;
	
	@Column(name = "fechavenc")
	@Temporal(TemporalType.DATE)
	private Date fechavenc;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "stock_min")
	private int stock_min;
	
	@Column(name = "stock_act")
	private int stock_act;
	
	@Column(name = "foto")
	private String foto;
	
	//Relaciones entre tablas
	//Muchos productos van a 1 proveedor, categoria
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idprove")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Proveedor proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcat")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	
}
