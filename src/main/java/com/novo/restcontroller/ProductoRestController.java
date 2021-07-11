package com.novo.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.novo.modelo.Producto;
import com.novo.service.ProductoService;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

	@Autowired
	ProductoService service;

	@GetMapping("/all")
	public ResponseEntity<?> listarProductos() {
		List<Producto> listaproductos = service.listarProductos();
		return new ResponseEntity<>(listaproductos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProducto(@PathVariable Integer id) {

		Producto p = null;
		Map<String, Object> response = new HashMap<>();

		try {
			p = service.buscarProducto(id);
			if (p == null) {
				response.put("mensaje", "El producto con id: " + id.toString() + " no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> registrarProducto(@RequestBody Producto p) {
		Producto pNuevo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			pNuevo = service.agregarProducto(p);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro a la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto fue creado con éxito");
		response.put("producto", pNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Agregar foto
	@PostMapping("/foto")
	public ResponseEntity<?> subirFoto(@RequestParam("foto") MultipartFile foto, @RequestParam("id") Integer id) {

		Map<String, Object> response = new HashMap<>();
		Producto p = service.buscarProducto(id);

		if (!foto.isEmpty()) {
			//UUID -> para generar codigos unicos y evitar que los nombres de las fotos se repitan
			String nombreFoto = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename().replace(" ", "");
			Path rutaFoto = Paths.get("fotos\\productos").resolve(nombreFoto).toAbsolutePath();

			try {
				Files.copy(foto.getInputStream(), rutaFoto);
			} catch (Exception e) {
				response.put("mensaje", "Error al subir la imagen");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = p.getFoto();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("fotos\\productos").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			p.setFoto(nombreFoto);
			service.agregarProducto(p);

			response.put("producto", p);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreFoto);

		} else {
			response.put("mensaje", "El campo foto no puede estar vacío");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Mostar foto
	@GetMapping("/foto/{id}")
	public ResponseEntity<?> obtenerFoto(@PathVariable Integer id) throws IOException {

		Producto p = null;
		String foto = null;
		Map<String, Object> response = new HashMap<>();

		try {

			p = service.buscarProducto(id);

			if (p == null) {
				response.put("mensaje", "El producto con id: " + id.toString() + " no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				foto = p.getFoto();
				if (foto == null) {
					response.put("mensaje", "El producto que seleccionó no cuenta con foto");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				} else {
					File img = new File("fotos/productos/" + foto);
					return ResponseEntity.ok()
							.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
							.body(Files.readAllBytes(img.toPath()));
				}
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta del foto.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto p, @PathVariable Integer id) {

		Producto pActual = service.buscarProducto(id);
		Producto pActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (pActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto con el id " + id.toString()
					+ " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			try {
				
				pActual.setNomprod(p.getNomprod());
				pActual.setFechavenc(p.getFechavenc());
				pActual.setPrecio(p.getPrecio());
				pActual.setStock_min(p.getStock_min());
				pActual.setStock_act(p.getStock_act());
				pActual.setCategoria(p.getCategoria());
				pActual.setProveedor(p.getProveedor());
				
				pActualizado = service.agregarProducto(pActual);
				
				response.put("mensaje", "El producto fue actualizado con éxito");
				response.put("producto", pActualizado);

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el registro a la base de datos.");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
		Producto p = null;
		Map<String, Object> response = new HashMap<>();

		try {
			p = service.buscarProducto(id);
			if (p == null) {
				response.put("mensaje", "El producto con id: " + id.toString() + " no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				
				//Proceso para eliminar la foto una vez eliminado el producto
				String nombreFotoAnterior = p.getFoto();
				
				if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
					Path rutaFotoAnterior = Paths.get("fotos\\productos").resolve(nombreFotoAnterior).toAbsolutePath();
					File archivoFotoAnterior = rutaFotoAnterior.toFile();
					if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
						archivoFotoAnterior.delete();
					}
				}
				
				service.eliminarProducto(id);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación del producto.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto eliminado correctamente");
		response.put("producto eliminado", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
