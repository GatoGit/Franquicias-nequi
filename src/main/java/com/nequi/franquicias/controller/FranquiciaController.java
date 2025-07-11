package com.nequi.franquicias.controller;

import com.nequi.franquicias.model.Franquicia;
import com.nequi.franquicias.model.Producto;
import com.nequi.franquicias.model.Sucursal;
import com.nequi.franquicias.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService service;

    // 0. Listar todas las franquicias
    @GetMapping
    public ResponseEntity<List<Franquicia>> listarFranquicias() {
        return ResponseEntity.ok(service.obtenerTodas());
    }
    // 1. Crear franquicia
    @PostMapping
    public ResponseEntity<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(service.crearFranquicia(franquicia));
    }

    // 2. Agregar sucursal
    @PostMapping("/{idFranquicia}/sucursales")
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable String idFranquicia, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.agregarSucursal(idFranquicia, sucursal));
    }

    // 3. Agregar producto
    @PostMapping("/{idFranquicia}/sucursales/{idSucursal}/productos")
    public ResponseEntity<Producto> agregarProducto(@PathVariable String idFranquicia,
                                                    @PathVariable String idSucursal,
                                                    @RequestBody Producto producto) {
        return ResponseEntity.ok(service.agregarProducto(idFranquicia, idSucursal, producto));
    }

    // 4. Eliminar producto
    @DeleteMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String idFranquicia,
                                                 @PathVariable String idSucursal,
                                                 @PathVariable String idProducto) {
        service.eliminarProducto(idFranquicia, idSucursal, idProducto);
        return ResponseEntity.noContent().build();
    }

    // 5. Modificar stock producto
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/stock")
    public ResponseEntity<Producto> modificarStock(@PathVariable String idFranquicia,
                                                   @PathVariable String idSucursal,
                                                   @PathVariable String idProducto,
                                                   @RequestParam int stock) {
        return ResponseEntity.ok(service.modificarStockProducto(idFranquicia, idSucursal, idProducto, stock));
    }

    // 6. Producto con mayor stock por sucursal
    @GetMapping("/{idFranquicia}/productos-mayor-stock")
    public ResponseEntity<List<Map<String, Object>>> productosMayorStock(@PathVariable String idFranquicia) {
        return ResponseEntity.ok(service.productoMayorStockPorSucursal(idFranquicia));
    }

    // Plus: actualizar nombre franquicia
    @PutMapping("/{idFranquicia}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(@PathVariable String idFranquicia,
                                                                @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreFranquicia(idFranquicia, nombre));
    }

    // Plus: actualizar nombre sucursal
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/nombre")
    public ResponseEntity<Sucursal> actualizarNombreSucursal(@PathVariable String idFranquicia,
                                                             @PathVariable String idSucursal,
                                                             @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreSucursal(idFranquicia, idSucursal, nombre));
    }

    // Plus: actualizar nombre producto
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/nombre")
    public ResponseEntity<Producto> actualizarNombreProducto(@PathVariable String idFranquicia,
                                                            @PathVariable String idSucursal,
                                                            @PathVariable String idProducto,
                                                            @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreProducto(idFranquicia, idSucursal, idProducto, nombre));
    }
}
