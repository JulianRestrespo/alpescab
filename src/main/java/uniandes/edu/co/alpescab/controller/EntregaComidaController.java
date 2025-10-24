package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.EntregaComida;
import uniandes.edu.co.alpescab.repositorio.EntregaComidaRepository;

@RestController
public class EntregaComidaController {

    @Autowired
    private EntregaComidaRepository entregaComidaRepository;

    @GetMapping("/entregas")
    public Iterable<EntregaComida> listar(){
        return entregaComidaRepository.findAll();
    }

    @GetMapping("/entregas/new")
    public EntregaComida form(){
        return new EntregaComida();
    }

    @PostMapping("/entregas/new/save")
    public void guardar(@RequestBody EntregaComida e){
        Long idServ = e.getServicio() != null ? e.getServicio().getId() : null;
        Long idPunto = e.getPuntoEntrega() != null ? e.getPuntoEntrega().getId() : null;
        entregaComidaRepository.insertar(idServ, e.getRestaurante(), idPunto);
    }

    @GetMapping("/entregas/{id}/edit")
    public ResponseEntity<EntregaComida> editarForm(@PathVariable("id") Long id){
        var e = entregaComidaRepository.porServicio(id);
        if(e != null){
            return ResponseEntity.ok(e);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/entregas/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody EntregaComida e){
        Long idPunto = e.getPuntoEntrega() != null ? e.getPuntoEntrega().getId() : null;
        entregaComidaRepository.actualizar(id, e.getRestaurante(), idPunto);
    }

    @GetMapping("/entregas/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        entregaComidaRepository.eliminar(id);
    }
}
