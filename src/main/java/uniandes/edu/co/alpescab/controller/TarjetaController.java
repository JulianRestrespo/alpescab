package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Tarjeta;
import uniandes.edu.co.alpescab.repositorio.TarjetaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@RestController
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @GetMapping("/tarjetas")
    public Collection<Tarjeta> listar(){
        return tarjetaRepository.todas();
    }

    @GetMapping("/tarjetas/new")
    public Tarjeta form(){
        return new Tarjeta();
    }

    @PostMapping("/tarjetas/new/save")
    public void guardar(@RequestBody Tarjeta t){
        Long idUsuario = t.getUsuario()!=null ? t.getUsuario().getId() : null;
        LocalDate v = t.getVencimiento();
        tarjetaRepository.insertar(
            t.getNumero(),
            t.getNombreTarjeta(),
            (v!=null ? Date.valueOf(v) : null),
            t.getCvv(),
            idUsuario
        );
    }

    @GetMapping("/tarjetas/{id}/edit")
    public ResponseEntity<Tarjeta> editarForm(@PathVariable("id") Long id){
        var t = tarjetaRepository.porId(id);
        if(t != null){
            return ResponseEntity.ok(t);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tarjetas/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody Tarjeta t){
        LocalDate v = t.getVencimiento();
        Long idUsuario = t.getUsuario()!=null ? t.getUsuario().getId() : null;
        tarjetaRepository.actualizar(
            id,
            t.getNumero(),
            t.getNombreTarjeta(),
            (v!=null ? Date.valueOf(v) : null),
            t.getCvv(),
            idUsuario
        );
    }

    @GetMapping("/tarjetas/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        tarjetaRepository.eliminar(id);
    }
}
