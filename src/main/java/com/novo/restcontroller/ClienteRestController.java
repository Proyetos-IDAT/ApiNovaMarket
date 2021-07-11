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

import com.novo.modelo.Cliente;
import com.novo.service.ClienteService;

@RestController
public class ClienteRestController {

	@Autowired 
	ClienteService service;
	
	@GetMapping("/api/listar")
	public List<Map<String, Object>>listar(Model model){
		return service.listar();
	}
	//Para agregar y funcione correctamente en el postman llamarlo sin el id en el body de Json
	@PostMapping("/api/agregar")
	public String save(@RequestBody Cliente c,Model model) {
		int id=service.agregar(c);
		if(id==0) {
			return "No se registro";
		}
		return "registro agregado";
		
	}
	//Para modificar no coloquen el id en el body
	@PostMapping("/api/actualizar/{id}")
	public String save(@RequestBody Cliente c,@PathVariable int id,Model model) {
		c.setIdcli(id);
		int r=service.editar(c);
		if(r==0) {
			return "No se pudo actualizar";
		}
		return "Se actualizó";
	}
	@PostMapping("/api/eliminar/{id}")
	public String delete(@PathVariable int id,Model model) {
		int r=service.eliminar(id);
		if(r==0) {
			return "registro no eliminado";
		}
		return "registro eliminado";
	}
	
}
