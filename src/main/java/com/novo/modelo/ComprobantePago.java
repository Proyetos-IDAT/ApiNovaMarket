package com.novo.modelo;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novo.dto.ComprobantePagoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprobantepago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobantePago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fecha;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cliente_id",nullable = false)
	Cliente cliente;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "producto_id",nullable = false)
	Producto producto;
	
	@JsonIgnore
	@ManyToOne(fetch =FetchType.LAZY, optional = false )
	@JoinColumn(name = "metodopago_id",nullable = false)
	MetodoPago metodoPago;
	
	public ComprobantePago(ComprobantePagoDto comprobantePagoDto,Cliente cliente,Producto producto,MetodoPago metodoPago) {
		this.fecha=comprobantePagoDto.getFecha();
		this.cliente=cliente;
		this.producto=producto;
		this.metodoPago=metodoPago;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
