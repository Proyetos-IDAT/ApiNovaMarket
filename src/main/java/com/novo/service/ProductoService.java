package com.novo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.ProductoInterface;
import com.novo.modelo.Producto;
import com.novo.modeloDao.ProductoDao;

@Service
public class ProductoService implements ProductoInterface{

	@Autowired
	ProductoDao dao;

	@Override
	public List<Producto> listarProductos() {
		return (List<Producto>)dao.findAll();
	}

	@Override
	public Producto agregarProducto(Producto prod) {
		return dao.save(prod);
	}

	@Override
	public void eliminarProducto(int id) {
		dao.deleteById(id);
	}

	@Override
	public Producto buscarProducto(Integer id) {
		return dao.findById(id).orElse(null);
	}

}
