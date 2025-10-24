package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Disponibilidad;
import uniandes.edu.co.alpescab.repositorio.DisponibilidadRepository;

import java.util.Collection;

@RestController
public class DisponibilidadController {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @GetMapping("/disponibilidades")
    public Collection<Disponibilidad> listar(){
        return disponibilidadRepository.porConductor(null);
    }

    @GetMapping("/disponibilidades/new")
    public Disponibilidad form(){
        return new Disponibilidad();
    }

    @PostMapping("/disponibilidades/new/save")
    public ResponseEntity<?> guardar(@RequestBody Disponibilidad d){

        d.setDiaSemana(d.getDiaSemana().toLowerCase());
        d.setTipoServicio(d.getTipoServicio().toLowerCase());

        Long idConductor = d.getConductor() != null ? d.getConductor().getId() : null;

        int solapes = disponibilidadRepository.conteoSolapes(
            idConductor,
            d.getDiaSemana(),
            d.getHoraInicio(),
            d.getHoraFin()
        );
        if (solapes > 0) {
            return ResponseEntity.status(409)
                .body("Ya existe disponibilidad en ese horario para ese conductor");
        }

        Disponibilidad creada = disponibilidadRepository.save(d);

        return ResponseEntity.ok(creada.getId());
    }

    @GetMapping("/disponibilidades/{id}/edit")
    public ResponseEntity<Disponibilidad> editarForm(@PathVariable("id") Long id){
        var d = disponibilidadRepository.porId(id);
        if(d != null){
            return ResponseEntity.ok(d);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/disponibilidades/{id}/edit/save")
    public ResponseEntity<?> editarGuardar(
            @PathVariable("id") Long id,
            @RequestBody Disponibilidad body) {

        Disponibilidad actual = disponibilidadRepository.porId(id);
        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        String diaSemana    = body.getDiaSemana().toLowerCase();
        String horaInicio   = body.getHoraInicio();
        String horaFin      = body.getHoraFin();
        String tipoServicio = body.getTipoServicio().toLowerCase();
        String placa        = body.getPlacaVehiculo();

        Long idConductor = actual.getConductor() != null ? actual.getConductor().getId() : null;

        int solapes = disponibilidadRepository.conteoSolapesEdit(
            idConductor,
            diaSemana,
            horaInicio,
            horaFin,
            id
        );

        if (solapes > 0) {
            return ResponseEntity.status(409)
                .body("No se puede actualizar: el horario se solapa con otra disponibilidad del mismo conductor");
        }

        disponibilidadRepository.actualizar(
            id,
            diaSemana,
            horaInicio,
            horaFin,
            tipoServicio,
            placa
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/disponibilidades/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        disponibilidadRepository.eliminar(id);
    }
}
