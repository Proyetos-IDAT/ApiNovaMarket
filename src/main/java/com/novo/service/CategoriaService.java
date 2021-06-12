package com.novo.service;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.CategoriaInterface;
import com.novo.modelo.Categoria;
import com.novo.modeloDao.CategoriaDao;

@Service
public class CategoriaService implements CategoriaInterface {
	
	@Autowired
	CategoriaDao dao;

	@Override
	public List<Map<String, Object>> listar() {
		return dao.listar();
	}

	@Override
	public int agregar(Categoria c) {
		return dao.agregar(c);
	}
	@Override
	public int editar(Categoria c) {
		return dao.editar(c);
	}
	

	@Override
	public int eliminar(int id) {
		return dao.eliminar(id);
	}

	
	

}
