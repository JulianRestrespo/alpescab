package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.modelo.Ciudad;
import uniandes.edu.co.alpescab.repositorio.CiudadRepository;

import java.util.Collection;
import java.util.List;

@RestController
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudades")
    public ResponseEntity<List<CiudadDTO>> listar() {
        Collection<Ciudad> entidades = ciudadRepository.todas();

        List<CiudadDTO> respuesta = entidades.stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/ciudades/new")
    public CiudadDTO form() {
        return new CiudadDTO(null, null);
    }

    @PostMapping("/ciudades/new/save")
    public ResponseEntity<Void> guardar(@RequestBody Ciudad c) {
        ciudadRepository.insertar(c.getNombre());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ciudades/{id}/edit")
    public ResponseEntity<CiudadDTO> editarForm(@PathVariable("id") Long id) {
        Ciudad c = ciudadRepository.porId(id);
        if (c != null) {
            return ResponseEntity.ok(toDTO(c));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ciudades/{id}/edit/save")
    public ResponseEntity<Void> editarGuardar(@PathVariable("id") Long id, @RequestBody Ciudad c) {
        ciudadRepository.actualizar(id, c.getNombre());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ciudades/{id}/delete")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        ciudadRepository.eliminar(id);
        return ResponseEntity.ok().build();
    }

    private CiudadDTO toDTO(Ciudad c) {
        return new CiudadDTO(
                c.getId(),
                c.getNombre()
        );
    }
}
