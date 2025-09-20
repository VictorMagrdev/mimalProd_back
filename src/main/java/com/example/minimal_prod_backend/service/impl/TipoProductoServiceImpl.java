package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.entity.TipoProducto;
import com.example.minimal_prod_backend.repository.TipoProductoRepository;
import com.example.minimal_prod_backend.service.TipoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;

    @Override
    public List<TipoProducto> findAll() {
        return tipoProductoRepository.findAll();
    }

    @Override
    public TipoProducto findById(Long id) {
        return tipoProductoRepository.findById(id).orElse(null);
    }

    @Override
    public TipoProducto save(TipoProductoInput input) {
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setCodigo(input.getCodigo());
        tipoProducto.setNombre(input.getNombre());
        tipoProducto.setDescripcion(input.getDescripcion());
        return tipoProductoRepository.save(tipoProducto);
    }

    @Override
    public TipoProducto update(Long id, TipoProductoInput input) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id).orElse(null);
        if (tipoProducto != null) {
            tipoProducto.setCodigo(input.getCodigo());
            tipoProducto.setNombre(input.getNombre());
            tipoProducto.setDescripcion(input.getDescripcion());
            return tipoProductoRepository.save(tipoProducto);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        tipoProductoRepository.deleteById(id);
    }
}
