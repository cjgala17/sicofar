package com.idat.SISCOFAR.controller;

import com.idat.SISCOFAR.dto.ProductoDTO;
import com.idat.SISCOFAR.dto.StockUpdateRequest;
import com.idat.SISCOFAR.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<ProductoDTO>> listarProductos() {
		return ResponseEntity.ok(productoService.findAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ProductoDTO> buscarProductoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(productoService.findById(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
		return new ResponseEntity<>(productoService.create(productoDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id,
			@Valid @RequestBody ProductoDTO productoDTO) {
		return ResponseEntity.ok(productoService.update(id, productoDTO));
	}

	@PatchMapping("/{id}/stock")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<ProductoDTO> actualizarStock(@PathVariable Long id,
			@Valid @RequestBody StockUpdateRequest request) {
		return ResponseEntity.ok(productoService.updateStock(id, request));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
		productoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}