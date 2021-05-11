package com.novo.modeloDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.novo.interfaces.ClienteInterface;
import com.novo.modelo.Cliente;

@Repository
public class ClienteDao implements ClienteInterface{

	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<Map<String, Object>> listar() {
		//va a mapear la lista de realizando un consulta con query 
		List<Map<String,Object>>list=template.queryForList("select * from cliente");
		return list;
	}

	@Override
	public int agregar(Cliente c) {
		String sql="insert into cliente (nomcli, apecli, numcli) values(?,?,?)";	
		return template.update(sql,c.getNomcli(),c.getApecli(),c.getNumcli());
	}

	@Override
	public int editar(Cliente c) {
		String sql="update cliente set nomcli=?,apecli=?,numcli=? where idcli=?";
		return template.update(sql,c.getNomcli(),c.getApecli(),c.getNumcli(),c.getIdcli());
	}

	@Override
	public int eliminar(int id) {
		String sql="delete from cliente where idcli=?";
		return template.update(sql,id);
	}

}
