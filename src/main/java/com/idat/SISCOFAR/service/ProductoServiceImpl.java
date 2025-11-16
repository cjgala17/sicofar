package com.idat.SISCOFAR.service;

import com.idat.SISCOFAR.dto.ProductoDTO;
import com.idat.SISCOFAR.dto.StockUpdateRequest;
import com.idat.SISCOFAR.entity.Producto;
import com.idat.SISCOFAR.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoDTO> findAll() {
		return productoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
	}

	@Override
	public ProductoDTO findById(Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
		return convertirADTO(producto);
	}

	@Override
	public ProductoDTO create(ProductoDTO productoDTO) {
		Producto producto = convertirAEntidad(productoDTO);
		producto = productoRepository.save(producto);
		return convertirADTO(producto);
	}

	@Override
	public ProductoDTO update(Long id, ProductoDTO productoDTO) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));

		producto.setNombre(productoDTO.nombre());
		producto.setDescripcion(productoDTO.descripcion());
		producto.setStock(productoDTO.stock());
		producto.setPrecio(productoDTO.precio());

		producto = productoRepository.save(producto);
		return convertirADTO(producto);
	}

	@Override
	public ProductoDTO updateStock(Long id, StockUpdateRequest request) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));

		producto.setStock(request.nuevoStock());
		producto = productoRepository.save(producto);
		return convertirADTO(producto);
	}

	@Override
	public void delete(Long id) {
		if (!productoRepository.existsById(id)) {
			throw new EntityNotFoundException("Producto no encontrado con id: " + id);
		}
		productoRepository.deleteById(id);
	}

	private ProductoDTO convertirADTO(Producto producto) {
		return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getStock(),
				producto.getPrecio());
	}

	private Producto convertirAEntidad(ProductoDTO dto) {
		Producto producto = new Producto();

		producto.setNombre(dto.nombre());
		producto.setDescripcion(dto.descripcion());
		producto.setStock(dto.stock());
		producto.setPrecio(dto.precio());
		return producto;
	}
}