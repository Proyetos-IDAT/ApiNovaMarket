package com.novo.modeloDao;

import org.springframework.data.repository.CrudRepository;

import com.novo.modelo.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer>{
	Producto findBynomprod(String nomprod);
}
