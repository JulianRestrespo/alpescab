package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Resena;
import uniandes.edu.co.alpescab.repositorio.ResenaRepository;
import uniandes.edu.co.alpescab.DTO.ResenaDTO;

import java.util.Collection;

@RestController
public class ResenaController {

    @Autowired
    private ResenaRepository resenaRepository;

    @GetMapping("/resenas")
    public Collection<ResenaDTO> listar() {
        return resenaRepository.todas()
            .stream()
            .map(r -> new ResenaDTO(
                r.getId(),
                r.getViaje() != null ? r.getViaje().getId() : null,
                r.getAutor() != null ? r.getAutor().getId() : null,
                r.getAutor() != null ? r.getAutor().getNombre() : null,
                r.getObjetivo() != null ? r.getObjetivo().getId() : null,
                r.getObjetivo() != null ? r.getObjetivo().getNombre() : null,
                r.getCalificacion(),
                r.getComentario()
            ))
            .toList();
    }

    @GetMapping("/resenas/new")
    public Resena form() {
        return new Resena();
    }

    @PostMapping("/resenas/new/save")
    public void guardar(@RequestBody Resena r) {
        Long idViaje    = (r.getViaje()    != null) ? r.getViaje().getId()    : null;
        Long idAutor    = (r.getAutor()    != null) ? r.getAutor().getId()    : null;
        Long idObjetivo = (r.getObjetivo() != null) ? r.getObjetivo().getId() : null;

        resenaRepository.insertar(
            idViaje,
            idAutor,
            idObjetivo,
            r.getCalificacion(),
            r.getComentario()
        );
    }

    @GetMapping("/resenas/{viaje}/{autor}/delete")
    public void eliminar(@PathVariable("viaje") Long idViaje,
                         @PathVariable("autor") Long idAutor) {
        resenaRepository.eliminar(idViaje, idAutor);
    }
}

