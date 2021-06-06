package com.novo.modeloDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.novo.interfaces.ProveedorInterface;
import com.novo.modelo.Proveedor;

@Repository
public class ProveedorDao implements ProveedorInterface{

	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<Map<String, Object>> listarpro() {
		List<Map<String,Object>>list=template.queryForList("select * from proveedor");
		return list;
	}

	@Override
	public int agregarpro(Proveedor pr) {
		String sql="insert into proveedor (nomprove, ruc, nomcontacto, direccion, telefono) values(?,?,?,?,?)";	
		return template.update(sql,pr.getNomprove(), pr.getRuc(), pr.getNomcontacto(), pr.getDireccion(), pr.getTelefono());
	}

	@Override
	public int editarpro(Proveedor pr) {
		String sql="update proveedor set nomprove=?,ruc=?,nomcontacto=?,direccion=?, telefono=? where idprove=?";
		return template.update(sql,pr.getNomprove(), pr.getRuc(), pr.getNomcontacto(), pr.getDireccion(), pr.getTelefono(), pr.getIdprove());
	}

	@Override
	public int eliminarpro(int id) {
		String sql="delete from proveedor where idprove=?";
		return template.update(sql,id);
	}

}
