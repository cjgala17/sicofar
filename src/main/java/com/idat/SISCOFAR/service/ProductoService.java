package com.idat.SISCOFAR.service;

import com.idat.SISCOFAR.dto.ProductoDTO;
import com.idat.SISCOFAR.dto.StockUpdateRequest;
import java.util.List;

public interface ProductoService {
	List<ProductoDTO> findAll();

	ProductoDTO findById(Long id);

	ProductoDTO create(ProductoDTO productoDTO);

	ProductoDTO update(Long id, ProductoDTO productoDTO);

	ProductoDTO updateStock(Long id, StockUpdateRequest request);

	void delete(Long id);
}