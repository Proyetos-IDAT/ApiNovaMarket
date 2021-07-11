package com.novo.restcontroller;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novo.modelo.Categoria;
import com.novo.service.CategoriaService;

@RestController
public class CategoriaRestController {
	
	@Autowired 
	CategoriaService service;
	
	@GetMapping("/api/listarcat")
	public List<Map<String, Object>>listar(Model model){
		return service.listar();
	}
	
	
	
	@PostMapping("/api/eliminarcat/{id}")
	public String delete(@PathVariable int id,Model model) {
		int r=service.eliminar(id);
		if(r==0) {
			return "registro no eliminado";
		}
		return "registro eliminado";
	}
	@PostMapping("/api/actualizarcat/{id}")
	public String save(@RequestBody Categoria c, @PathVariable int id, Model model) {
		c.setIdcat(id);
		int r=service.editar(c);
		if(r==0) {
			return "No se actualizo";
		}
		return "se actualizo";
	}
	@PostMapping("/api/agregarcat")
	public String save(@RequestBody Categoria c,Model model) {
		int id=service.agregar(c);
		if(id==0) {
			return "No se registro la categoria";
		}
		return "registro agregado";
	}

}
