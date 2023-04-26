package com.cardona.apiservlet.webapp.services;

import com.cardona.apiservlet.webapp.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
}