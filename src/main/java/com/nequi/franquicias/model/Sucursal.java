package com.nequi.franquicias.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sucursal {
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private List<Producto> productos = new ArrayList<>();

    public Sucursal() {}

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}
