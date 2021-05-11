package com.novo.interfaces;

import java.util.List;
import java.util.Map;

import com.novo.modelo.Cliente;

public interface ClienteInterface {
	//agregando metodos de listar, agregar, editar y eliminar
	public List<Map<String,Object>>listar();
	public int agregar(Cliente c);
	public int editar(Cliente c);
	public int eliminar(int id);
}
