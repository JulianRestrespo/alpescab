package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoCreateDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoDTO;
import uniandes.edu.co.alpescab.modelo.Ciudad;
import uniandes.edu.co.alpescab.modelo.PuntoGeografico;
import uniandes.edu.co.alpescab.repositorio.PuntoGeograficoRepository;

import java.util.Collection;
import java.util.List;

@RestController
public class PuntoGeograficoController {

    @Autowired
    private PuntoGeograficoRepository puntoRepository;

    @GetMapping("/puntos")
    public ResponseEntity<List<PuntoGeograficoDTO>> listar() {
        Collection<PuntoGeografico> entidades = puntoRepository.todos();

        List<PuntoGeograficoDTO> respuesta = entidades.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(respuesta);
    }


    @PostMapping("/puntos/new/save")
    public ResponseEntity<Void> guardar(@RequestBody PuntoGeograficoCreateDTO body) {
        if (body.getIdCiudad() == null ||
            body.getLatitud() == null ||
            body.getLongitud() == null) {
            return ResponseEntity.badRequest().build();
        }

        puntoRepository.insertar(
                body.getLatitud(),
                body.getLongitud(),
                body.getIdCiudad()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/puntos/{id}/edit")
    public ResponseEntity<PuntoGeograficoDTO> editarForm(@PathVariable("id") Long id) {
        PuntoGeografico p = puntoRepository.porId(id);
        if (p != null) {
            return ResponseEntity.ok(toDTO(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/puntos/{id}/edit/save")
    public ResponseEntity<Void> editarGuardar(
            @PathVariable("id") Long id,
            @RequestBody PuntoGeograficoCreateDTO body) {

        if (body.getIdCiudad() == null ||
            body.getLatitud() == null ||
            body.getLongitud() == null) {
            return ResponseEntity.badRequest().build();
        }

        puntoRepository.actualizar(
                id,
                body.getLatitud(),
                body.getLongitud(),
                body.getIdCiudad()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/puntos/{id}/delete")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        puntoRepository.eliminar(id);
        return ResponseEntity.ok().build();
    }

    private PuntoGeograficoDTO toDTO(PuntoGeografico p) {
        return new PuntoGeograficoDTO(
                p.getId(),
                p.getLatitud(),
                p.getLongitud(),
                toCiudadDTO(p.getCiudad())
        );
    }

    private CiudadDTO toCiudadDTO(Ciudad c) {
        if (c == null) return null;
        return new CiudadDTO(
                c.getId(),
                c.getNombre()
        );
    }
}
