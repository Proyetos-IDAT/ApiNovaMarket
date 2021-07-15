package com.novo.modeloDao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.novo.modelo.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer>{
	List<Producto> findBynomprod(String nomprod);
}
