package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.TipoProductoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoProductoResponse;

import java.util.List;

public interface TipoProductoService {
    List<TipoProductoResponse> getTiposProducto();

    TipoProductoResponse getTipoProductoById(Long id);

    TipoProductoResponse createTipoProducto(TipoProductoRequest tipoProductoInput);

    TipoProductoResponse updateTipoProducto(Long id, TipoProductoRequest tipoProductoInput);

    void deleteTipoProducto(Long id);
}
