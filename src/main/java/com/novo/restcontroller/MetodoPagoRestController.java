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

import com.novo.modelo.MetodoPago;
import com.novo.service.MetodoPagoService;

@RestController
public class MetodoPagoRestController {

	@Autowired
	MetodoPagoService service;
	
	@GetMapping("/api/listarMetodoPago")
	public List<Map<String,Object>>listar(Model model){
		return service.listar();
	}
	
	@PostMapping("/api/agregarMetodoPago")
	public String save(@RequestBody MetodoPago m,Model model) {
		int id=service.agregar(m);
		if(id==0) {
			return "No se registro el metodo pago";
		}
		return "registro eliminado";
	}
	
	@PostMapping("/api/actualizarMetodoPago/{id}")
	public String save(@RequestBody MetodoPago m,@PathVariable int id, Model model) {
		m.setIdmetpago(id);
		int r=service.editar(m);
		if(r==0) {
			return "No se pudo actualizar";
		}
		return "Se actualizo";
	}
	@PostMapping("/api/eliminarMetodoPago/{id}")
	public String delete(@PathVariable int id,Model model) {
		int r=service.eliminar(id);
		if(r==0) {
			return "registro no eliminado";
		}
		return "registro eliminado";
	}
	
}
