package com.novo.interfaces;

import java.util.List;

import com.novo.modelo.Producto;

public interface ProductoInterface{
	public List<Producto> listarProductos();
	public Producto agregarProducto(Producto prod);
	public void eliminarProducto(int id);
	public Producto buscarProducto(Integer id);
}
