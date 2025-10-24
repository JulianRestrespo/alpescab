package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Mercancia;
import uniandes.edu.co.alpescab.repositorio.MercanciaRepository;

import java.util.Collection;

@RestController
public class MercanciaController {

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @GetMapping("/mercancias")
    public Collection<Mercancia> listar(){
        return mercanciaRepository.todas();
    }

    @GetMapping("/mercancias/new")
    public Mercancia form(){
        return new Mercancia();
    }

    @PostMapping("/mercancias/new/save")
    public void guardar(@RequestBody Mercancia m){
        Long idServ = m.getServicio() != null ? m.getServicio().getId() : null;
        Long idDest = m.getDestino() != null ? m.getDestino().getId() : null;
        mercanciaRepository.insertar(idServ, m.getDescripcionProducto(), idDest);
    }

    @GetMapping("/mercancias/{id}/edit")
    public ResponseEntity<Mercancia> editarForm(@PathVariable("id") Long id){
        var m = mercanciaRepository.porServicio(id);
        if(m != null){
            return ResponseEntity.ok(m);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/mercancias/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody Mercancia m){
        Long idDest = m.getDestino() != null ? m.getDestino().getId() : null;
        mercanciaRepository.actualizar(id, m.getDescripcionProducto(), idDest);
    }

    @GetMapping("/mercancias/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        mercanciaRepository.eliminar(id);
    }
}

