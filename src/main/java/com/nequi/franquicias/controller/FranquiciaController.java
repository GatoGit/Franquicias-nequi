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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/franquicias")
@Tag(name = "Franquicias", description = "Operaciones relacionadas con franquicias de Nequi, sucursales y productos")

public class FranquiciaController {

    @Autowired
    private FranquiciaService service;

    // 0. Listar todas las franquicias
    @Operation(summary = "Obtener toda las franquicias con su detalle")
    @GetMapping
    public ResponseEntity<List<Franquicia>> listarFranquicias() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    
    // 1. Crear franquicia
    @Operation(summary = "Crear una nueva franquicia")
    @PostMapping
    public ResponseEntity<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(service.crearFranquicia(franquicia));
    }

    // 2. Agregar sucursal
    @Operation(summary = "Agregar una sucursal a una franquicia existente")
    @PostMapping("/{idFranquicia}/sucursales")
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable String idFranquicia, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.agregarSucursal(idFranquicia, sucursal));
    }

    // 3. Agregar producto
    @Operation(summary = "Agregar un producto a una sucursal de una franquicia")
    @PostMapping("/{idFranquicia}/sucursales/{idSucursal}/productos")
    public ResponseEntity<Producto> agregarProducto(@PathVariable String idFranquicia,
                                                    @PathVariable String idSucursal,
                                                    @RequestBody Producto producto) {
        return ResponseEntity.ok(service.agregarProducto(idFranquicia, idSucursal, producto));
    }

    // 4. Eliminar producto
    @Operation(summary = "Eliminar un producto de una sucursal de una franquicia")
    @DeleteMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String idFranquicia,
                                                 @PathVariable String idSucursal,
                                                 @PathVariable String idProducto) {
        service.eliminarProducto(idFranquicia, idSucursal, idProducto);
        return ResponseEntity.noContent().build();
    }

    // 5. Modificar stock producto
    @Operation(summary = "Modificar el stock de un producto en una sucursal")
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/stock")
    public ResponseEntity<Producto> modificarStock(@PathVariable String idFranquicia,
                                                   @PathVariable String idSucursal,
                                                   @PathVariable String idProducto,
                                                   @RequestParam int stock) {
        return ResponseEntity.ok(service.modificarStockProducto(idFranquicia, idSucursal, idProducto, stock));
    }

    // 6. Producto con mayor stock por sucursal
    @Operation(summary = "Obtener el producto con mayor stock por sucursal en una franquicia")
    @GetMapping("/{idFranquicia}/productos-mayor-stock")
    public ResponseEntity<List<Map<String, Object>>> productosMayorStock(@PathVariable String idFranquicia) {
        return ResponseEntity.ok(service.productoMayorStockPorSucursal(idFranquicia));
    }

    // Plus: actualizar nombre franquicia
    @Operation(summary = "Actualizar el nombre de una franquicia")
    @PutMapping("/{idFranquicia}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(@PathVariable String idFranquicia,
                                                                @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreFranquicia(idFranquicia, nombre));
    }

    // Plus: actualizar nombre sucursal
    @Operation(summary = "Actualizar el nombre de una sucursal de una franquicia")
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/nombre")
    public ResponseEntity<Sucursal> actualizarNombreSucursal(@PathVariable String idFranquicia,
                                                             @PathVariable String idSucursal,
                                                             @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreSucursal(idFranquicia, idSucursal, nombre));
    }

    // Plus: actualizar nombre producto
    @Operation(summary = "Actualizar el nombre de un producto en una sucursal")
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/nombre")
    public ResponseEntity<Producto> actualizarNombreProducto(@PathVariable String idFranquicia,
                                                            @PathVariable String idSucursal,
                                                            @PathVariable String idProducto,
                                                            @RequestParam String nombre) {
        return ResponseEntity.ok(service.actualizarNombreProducto(idFranquicia, idSucursal, idProducto, nombre));
    }
}
