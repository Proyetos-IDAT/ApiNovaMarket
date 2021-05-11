package com.novo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.ClienteInterface;
import com.novo.modelo.Cliente;
import com.novo.modeloDao.ClienteDao;

@Service
public class ClienteService implements ClienteInterface{

	@Autowired
	ClienteDao dao;
	
	@Override
	public List<Map<String, Object>> listar() {
		
		return dao.listar();
	}

	@Override
	public int agregar(Cliente c) {
	
		return dao.agregar(c);
	}

	@Override
	public int editar(Cliente c) {
		
		return dao.editar(c);
	}

	@Override
	public int eliminar(int id) {
		
		return dao.eliminar(id);
	}

}
