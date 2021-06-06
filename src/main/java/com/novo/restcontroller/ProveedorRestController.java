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

import com.novo.modelo.Proveedor;
import com.novo.service.ProveedorService;

@RestController
public class ProveedorRestController {

	@Autowired
	ProveedorService service;

	@GetMapping("/api/listarpro")
	public List<Map<String, Object>> listarpro(Model model) {
		return service.listarpro();
	}

	@PostMapping("/api/agregarpro")
	public String save(@RequestBody Proveedor pr, Model model) {
		int id = service.agregarpro(pr);
		if (id == 0) {
			return "No se registro";
		}
		return "Registro Agregado";

	}

	@PostMapping("/api/actualizarpro/{id}")
	public String save(@RequestBody Proveedor pr, @PathVariable int id, Model model) {
		pr.setIdprove(id);
		int r = service.editarpro(pr);
		if (r == 0) {
			return "No se pudo actualizar";
		}
		return "Se actualizó";
	}

	@PostMapping("/api/eliminarpro/{id}")
	public String delete(@PathVariable int id, Model model) {
		int r = service.eliminarpro(id);
		if (r == 0) {
			return "registro no eliminado";
		}
		return "registro eliminado";
	}
}
