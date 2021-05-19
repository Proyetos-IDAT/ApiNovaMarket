package com.novo.interfaces;

import java.util.List;
import java.util.Map;

import com.novo.modelo.MetodoPago;

public interface MetodoPagoInterface {

	public List<Map<String,Object>>listar();
	public int agregar(MetodoPago m);
	public int editar(MetodoPago m);
	public int eliminar(int id);
}
