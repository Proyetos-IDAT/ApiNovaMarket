package com.novo.dto;

import javax.validation.constraints.NotNull;

import com.novo.modelo.ComprobantePago;

public class ComprobantePagoDto {
	private Long id;
	@NotNull
	private String fecha;
	@NotNull
	private int ClienteId;
	@NotNull 
	private int ProductoId;
	@NotNull 
	private int MetodoPagoId;

	public ComprobantePagoDto(ComprobantePago comprobantePago) {
		this.setId(comprobantePago.getId());
		this.setFecha(comprobantePago.getFecha());
		this.setClienteId(comprobantePago.getCliente().getIdcli());
		this.setProductoId(comprobantePago.getProducto().getIdprod());
		this.setMetodoPagoId(comprobantePago.getMetodoPago().getIdmetpago());
	}
	
	
	


	public ComprobantePagoDto(Long id, @NotNull String fecha, @NotNull int clienteId, @NotNull int productoId,
			@NotNull int metodoPagoId) {
		super();
		this.id = id;
		this.fecha = fecha;
		ClienteId = clienteId;
		ProductoId = productoId;
		MetodoPagoId = metodoPagoId;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public int getClienteId() {
		return ClienteId;
	}


	public void setClienteId(int clienteId) {
		ClienteId = clienteId;
	}


	public int getProductoId() {
		return ProductoId;
	}


	public void setProductoId(int productoId) {
		ProductoId = productoId;
	}


	public int getMetodoPagoId() {
		return MetodoPagoId;
	}


	public void setMetodoPagoId(int metodoPagoId) {
		MetodoPagoId = metodoPagoId;
	}


	
	
	
	
}
