package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Destino;
import uniandes.edu.co.alpescab.repositorio.DestinoRepository;

import java.util.Collection;

@RestController
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping("/destinos")
    public Collection<Destino> listar(){
        return destinoRepository.todos();
    }

    @GetMapping("/destinos/new")
    public Destino form(){
        return new Destino();
    }

    @PostMapping("/destinos/new/save")
    public void guardar(@RequestBody Destino d){
        Long idPunto = d.getPunto() != null ? d.getPunto().getId() : null;
        destinoRepository.insertar(d.getNombre(), d.getDescripcion(), idPunto);
    }

    @GetMapping("/destinos/{id}/edit")
    public ResponseEntity<Destino> editarForm(@PathVariable("id") Long id){
        var d = destinoRepository.porId(id);
        if(d != null){
            return ResponseEntity.ok(d);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/destinos/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody Destino d){
        Long idPunto = d.getPunto() != null ? d.getPunto().getId() : null;
        destinoRepository.actualizar(id, d.getNombre(), d.getDescripcion(), idPunto);
    }

    @GetMapping("/destinos/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        destinoRepository.eliminar(id);
    }
}
