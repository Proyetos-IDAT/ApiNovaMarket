package com.novo.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novo.common.ApiResponse;
import com.novo.dto.ComprobantePagoDto;
import com.novo.modelo.Cliente;
import com.novo.modelo.MetodoPago;
import com.novo.modelo.Producto;
import com.novo.service.ClienteService;
import com.novo.service.ComprobantePagoService;
import com.novo.service.MetodoPagoService;
import com.novo.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ComprobantePagoRestController {

	@Autowired
	ComprobantePagoService comprobantePagoService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	ProductoService productoService;
	@Autowired
	MetodoPagoService metodoPagoService;
	
	@GetMapping("/listarcompro")
	public ResponseEntity<List<ComprobantePagoDto>>getComprobante(){
		List<ComprobantePagoDto>body=comprobantePagoService.listComprobante();
		return new ResponseEntity<List<ComprobantePagoDto>>(body, HttpStatus.OK);
	}
	@PostMapping("/agregarcompro")
	public ResponseEntity<ApiResponse>addComprobante(@RequestBody ComprobantePagoDto comprobantePagoDto){
		
		Optional<Producto>optionalProducto=productoService.readProducto(comprobantePagoDto.getProductoId());
		if(!optionalProducto.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Producto invalido"), HttpStatus.CONFLICT);
		}
		
		Optional<Cliente>optionalCliente=clienteService.readCliente(comprobantePagoDto.getClienteId());
		if(!optionalCliente.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Cliente invalido invalido"), HttpStatus.CONFLICT);
		}
		Optional<MetodoPago>optionalMetodoPago=metodoPagoService.readMetodoPago(comprobantePagoDto.getMetodoPagoId());
		if(!optionalMetodoPago.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Metodo invalido invalido"), HttpStatus.CONFLICT);
		}
		
		
		Producto producto=optionalProducto.get();
		Cliente cliente=optionalCliente.get();
		MetodoPago metodoPago=optionalMetodoPago.get();
		comprobantePagoService.addComprobantePago(comprobantePagoDto, cliente, producto, metodoPago);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Se ha creado el comprobante"), HttpStatus.CREATED);
	}
}
