package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.TransportePasajeros;
import uniandes.edu.co.alpescab.repositorio.TransportePasajerosRepository;

import java.util.Collection;

@RestController
public class TransportePasajerosController {

    @Autowired
    private TransportePasajerosRepository transportePasajerosRepository;

    @GetMapping("/transportePasajeros")
    public Collection<TransportePasajeros> listar(){
        return transportePasajerosRepository.todas();
    }

    @GetMapping("/transportePasajeros/new")
    public TransportePasajeros form(){
        return new TransportePasajeros();
    }

    @PostMapping("/transportePasajeros/new/save")
    public void guardar(@RequestBody TransportePasajeros tp){
        Long idServicio = tp.getServicio() != null ? tp.getServicio().getId() : null;
        Long idDestino  = tp.getDestino()  != null ? tp.getDestino().getId()  : null;

        transportePasajerosRepository.insertar(
            idServicio, tp.getNivel(), tp.getNumeroPasajeros(), idDestino
        );
    }

    @GetMapping("/transportePasajeros/{id}/edit")
    public ResponseEntity<TransportePasajeros> editarForm(@PathVariable("id") Long id){
        var tp = transportePasajerosRepository.porId(id);
        if(tp != null){
            return ResponseEntity.ok(tp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transportePasajeros/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody TransportePasajeros tp){
        Long idDestino = tp.getDestino() != null ? tp.getDestino().getId() : null;
        transportePasajerosRepository.actualizar(
            id, tp.getNivel(), tp.getNumeroPasajeros(), idDestino
        );
    }

    @GetMapping("/transportePasajeros/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        transportePasajerosRepository.eliminar(id);
    }
}
