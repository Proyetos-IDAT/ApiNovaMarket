package com.novo.interfaces;

import java.util.List;
import java.util.Map;

import com.novo.modelo.Categoria;

public interface CategoriaInterface {
	
	public List<Map<String,Object>>listar();
	public int agregar(Categoria c);
	public int editar(Categoria c);
	public int eliminar(int id);
}
