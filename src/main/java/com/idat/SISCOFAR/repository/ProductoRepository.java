package com.idat.SISCOFAR.repository;

import com.idat.SISCOFAR.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}