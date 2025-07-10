package com.nequi.franquicias.service;

import com.nequi.franquicias.model.Franquicia;
import com.nequi.franquicias.model.Producto;
import com.nequi.franquicias.model.Sucursal;
import com.nequi.franquicias.repository.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository repo;

    public Franquicia crearFranquicia(Franquicia franquicia) {
        return repo.save(franquicia);
    }

    public Optional<Franquicia> obtenerPorId(String id) {
        return repo.findById(id);
    }

    public List<Franquicia> obtenerTodas() {
        return repo.findAll();
    }

    public Franquicia actualizarNombreFranquicia(String id, String nuevoNombre) {
        Franquicia f = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        f.setNombre(nuevoNombre);
        return repo.save(f);
    }

    public Sucursal agregarSucursal(String idFranquicia, Sucursal sucursal) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        f.getSucursales().add(sucursal);
        repo.save(f);
        return sucursal;
    }

    public Sucursal actualizarNombreSucursal(String idFranquicia, String idSucursal, String nuevoNombre) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        Sucursal sucursal = f.getSucursales().stream()
                .filter(s -> s.getId().equals(idSucursal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada"));
        sucursal.setNombre(nuevoNombre);
        repo.save(f);
        return sucursal;
    }

    public Producto agregarProducto(String idFranquicia, String idSucursal, Producto producto) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        Sucursal sucursal = f.getSucursales().stream()
                .filter(s -> s.getId().equals(idSucursal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada"));
        sucursal.getProductos().add(producto);
        repo.save(f);
        return producto;
    }

    public void eliminarProducto(String idFranquicia, String idSucursal, String idProducto) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        Sucursal sucursal = f.getSucursales().stream()
                .filter(s -> s.getId().equals(idSucursal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada"));
        boolean removed = sucursal.getProductos().removeIf(p -> p.getId().equals(idProducto));
        if (!removed) throw new NoSuchElementException("Producto no encontrado");
        repo.save(f);
    }

    public Producto modificarStockProducto(String idFranquicia, String idSucursal, String idProducto, int nuevoStock) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        Sucursal sucursal = f.getSucursales().stream()
                .filter(s -> s.getId().equals(idSucursal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada"));
        Producto producto = sucursal.getProductos().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        repo.save(f);
        return producto;
    }

    public Producto actualizarNombreProducto(String idFranquicia, String idSucursal, String idProducto, String nuevoNombre) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));
        Sucursal sucursal = f.getSucursales().stream()
                .filter(s -> s.getId().equals(idSucursal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada"));
        Producto producto = sucursal.getProductos().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
        producto.setNombre(nuevoNombre);
        repo.save(f);
        return producto;
    }

    public List<Map<String, Object>> productoMayorStockPorSucursal(String idFranquicia) {
        Franquicia f = repo.findById(idFranquicia).orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada"));

        return f.getSucursales().stream()
                .map(s -> {
                    Optional<Producto> mayorStock = s.getProductos().stream()
                            .max(Comparator.comparingInt(Producto::getStock));
                    if (mayorStock.isPresent()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("sucursal", s.getNombre());
                        result.put("producto", mayorStock.get());
                        return result;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
