package com.novo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.interfaces.ProveedorInterface;
import com.novo.modelo.Proveedor;
import com.novo.modeloDao.ProveedorDao;

@Service
public class ProveedorService implements ProveedorInterface{

	@Autowired
	ProveedorDao dao;
	
	@Override
	public List<Map<String, Object>> listarpro() {
		return dao.listarpro();
	}

	@Override
	public int agregarpro(Proveedor pr) {
		return dao.agregarpro(pr);
	}

	@Override
	public int editarpro(Proveedor pr) {
		return dao.editarpro(pr);
	}

	@Override
	public int eliminarpro(int id) {
		return dao.eliminarpro(id);
	}

}
