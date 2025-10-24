package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Vehiculo;
import uniandes.edu.co.alpescab.repositorio.VehiculoRepository;

import java.util.Collection;

@RestController
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/vehiculos")
    public Collection<Vehiculo> listar(){
        return vehiculoRepository.todos();
    }

    @GetMapping("/vehiculos/new")
    public Vehiculo form(){
        return new Vehiculo();
    }

    @PostMapping("/vehiculos/new/save")
    public void guardar(@RequestBody Vehiculo v){
        vehiculoRepository.insertar(
            v.getPlacaVehiculo(), v.getTipo(), v.getMarca(), v.getModelo(),
            v.getColor(), v.getCapacidad(),
            v.getCiudadExpedicion() != null ? v.getCiudadExpedicion().getId() : null,
            v.getConductor() != null ? v.getConductor().getId() : null
        );
    }

    @GetMapping("/vehiculos/{id}/edit")
    public ResponseEntity<Vehiculo> editarForm(@PathVariable("id") Long id){
        var v = vehiculoRepository.porId(id);
        if(v != null){
            return ResponseEntity.ok(v);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/vehiculos/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody Vehiculo v){
        vehiculoRepository.actualizar(
            id, v.getPlacaVehiculo(), v.getTipo(), v.getMarca(), v.getModelo(),
            v.getColor(), v.getCapacidad(),
            v.getCiudadExpedicion() != null ? v.getCiudadExpedicion().getId() : null
        );
    }

    @GetMapping("/vehiculos/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        vehiculoRepository.eliminar(id);
    }
}
