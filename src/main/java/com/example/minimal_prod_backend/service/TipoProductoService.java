package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.dto.TipoProductoResponse;

import java.util.List;

public interface TipoProductoService {
    List<TipoProductoResponse> getTiposProducto();
    TipoProductoResponse getTipoProductoById(Long id);
    TipoProductoResponse createTipoProducto(TipoProductoInput tipoProductoInput);
    TipoProductoResponse updateTipoProducto(Long id, TipoProductoInput tipoProductoInput);
    void deleteTipoProducto(Long id);
}
