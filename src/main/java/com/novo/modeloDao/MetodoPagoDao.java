package com.novo.modeloDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.novo.interfaces.MetodoPagoInterface;
import com.novo.modelo.MetodoPago;

@Repository
public class MetodoPagoDao implements MetodoPagoInterface{

	//permite realiazar captura de excepciones o enviar consultas
	// a las base de datos, mapear los resultar, cerrar las conexiones,etc.
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String,Object>>list=template.queryForList("select * from metodopago");
		return list;
	}

	@Override
	public int agregar(MetodoPago m) {
		String sql="insert into metodopago (tipopago) values (?)";
		return template.update(sql,m.getTipopago());
	}

	@Override
	public int editar(MetodoPago m) {
		String sql="update metodopago set tipopago=? where idmetpago=?";
		return template.update(sql,m.getTipopago(),m.getIdmetpago());
	}

	@Override
	public int eliminar(int id) {
		String sql="delete from metodopago where idmetpago=?";
		return template.update(sql,id);
	}
	
}
