package com.novo.modeloDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.novo.interfaces.CategoriaInterface;
import com.novo.modelo.Categoria;
@Repository
public class CategoriaDao implements CategoriaInterface{
	
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String,Object>>list=template.queryForList("select * from categorias");
		return list;
	}

	@Override
	public int agregar(Categoria c) {
		String sql="insert into categorias (nomcat) values (?)";
		return template.update(sql,c.getNomcat());
		
	}

	@Override
	public int eliminar(int id) {
		String sql="delete from categorias where idcat=?";
		return template.update(sql,id);
	}

	@Override
	public int editar(Categoria c) {
		String sql="update categorias set nomcat=? where idcat=?";
		return template.update(sql,c.getNomcat(),c.getIdcat());
	}
	

}
