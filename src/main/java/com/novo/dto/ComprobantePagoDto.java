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
	@NotNull
	private double precio;
	@NotNull
	private int cantidad;

	public ComprobantePagoDto(ComprobantePago comprobantePago) {
		this.setId(comprobantePago.getId());
		this.setFecha(comprobantePago.getFecha());
		this.setClienteId(comprobantePago.getCliente().getIdcli());
		this.setProductoId(comprobantePago.getProducto().getIdprod());
		this.setMetodoPagoId(comprobantePago.getMetodoPago().getIdmetpago());
		this.setPrecio(comprobantePago.getPrecio());
		this.setCantidad(comprobantePago.getCantidad());
	}
	
	
	


	public ComprobantePagoDto(Long id, @NotNull String fecha, @NotNull int clienteId, @NotNull int productoId,
			@NotNull int metodoPagoId, @NotNull double precio,@NotNull int cantidad) {
		super();
		this.id = id;
		this.fecha = fecha;
		ClienteId = clienteId;
		ProductoId = productoId;
		MetodoPagoId = metodoPagoId;
		this.precio=precio;
		this.cantidad=cantidad;
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


	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	
	
	
	
}
