package com.novo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.ProductoInterface;
import com.novo.modelo.Producto;
import com.novo.modeloDao.ProductoDao;

@Service
public class ProductoService implements ProductoInterface{

	@Autowired
	ProductoDao dao;

	

	public Optional<Producto>readProducto(int ProductoId){
		return dao.findById(ProductoId);
	}
	
	@Override
	public List<Producto> listarProductos() {
		return (List<Producto>)dao.findAll();
	}

	@Override
	public Producto agregarProducto(Producto prod) {
		return dao.save(prod);
	}
	
	/*public Optional<Category> readCategory(Long categoryId) {
		return categoryrepository.findById(categoryId);
	}*/

	@Override
	public void eliminarProducto(int id) {
		dao.deleteById(id);
	}

	@Override
	public Producto buscarProducto(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Producto> findBynomprod(String nomprod) {
		return dao.findBynomprod(nomprod);
	}


}
