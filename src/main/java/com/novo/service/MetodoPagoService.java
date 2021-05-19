package com.novo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.MetodoPagoInterface;
import com.novo.modelo.MetodoPago;
import com.novo.modeloDao.MetodoPagoDao;

@Service
public class MetodoPagoService implements MetodoPagoInterface{
	@Autowired
	MetodoPagoDao dao;

	@Override
	public List<Map<String, Object>> listar() {	
		return dao.listar();
	}

	@Override
	public int agregar(MetodoPago m) {
		return dao.agregar(m);
	}

	@Override
	public int editar(MetodoPago m) {	
		return dao.editar(m);
	}
	
	@Override
	public int eliminar(int id) {
		return dao.eliminar(id);
	}
	
	
}
