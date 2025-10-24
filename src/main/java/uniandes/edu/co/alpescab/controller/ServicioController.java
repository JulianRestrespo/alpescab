package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoDTO;
import uniandes.edu.co.alpescab.DTO.ServicioDTO;
import uniandes.edu.co.alpescab.DTO.UsuarioDTO;
import uniandes.edu.co.alpescab.modelo.Ciudad;
import uniandes.edu.co.alpescab.modelo.PuntoGeografico;
import uniandes.edu.co.alpescab.modelo.Servicio;
import uniandes.edu.co.alpescab.modelo.Usuario;
import uniandes.edu.co.alpescab.repositorio.ServicioRepository;

import java.util.Collection;
import java.util.List;

@RestController
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public ResponseEntity<List<ServicioDTO>> listar() {
        Collection<Servicio> entidades = servicioRepository.todos();

        List<ServicioDTO> respuesta = entidades.stream()
                .map(this::toServicioDTO)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/servicios/new")
    public ServicioDTO form() {
        return new ServicioDTO(
                null,
                null,
                null,
                null,
                null
        );
    }

    @PostMapping("/servicios/new/save")
    public ResponseEntity<Void> guardar(@RequestBody Servicio s) {
        Long idCliente = (s.getCliente() != null) ? s.getCliente().getId() : null;
        Long idOrigen  = (s.getOrigen()  != null) ? s.getOrigen().getId()  : null;

        servicioRepository.insertar(
                s.getId(),
                s.getTipoServicio(),
                s.getEstado(),
                idCliente,
                idOrigen
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/servicios/{id}/edit")
    public ResponseEntity<ServicioDTO> editarForm(@PathVariable("id") Long id){
        Servicio s = servicioRepository.porId(id);
        if (s != null) {
            return ResponseEntity.ok(toServicioDTO(s));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public ResponseEntity<Void> editarGuardar(@PathVariable("id") Long id, @RequestBody Servicio s){
        Long idCliente = (s.getCliente() != null) ? s.getCliente().getId() : null;
        Long idOrigen  = (s.getOrigen()  != null) ? s.getOrigen().getId()  : null;

        servicioRepository.actualizar(
                id,
                s.getTipoServicio(),
                s.getEstado(),
                idCliente,
                idOrigen
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/servicios/{id}/delete")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id){
        servicioRepository.eliminar(id);
        return ResponseEntity.ok().build();
    }

    private ServicioDTO toServicioDTO(Servicio s) {
        return new ServicioDTO(
                s.getId(),
                s.getTipoServicio(),
                s.getEstado(),
                toUsuarioDTO(s.getCliente()),
                toPuntoGeograficoDTO(s.getOrigen())
        );
    }

    private UsuarioDTO toUsuarioDTO(Usuario u) {
        if (u == null) return null;
        return new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getEmail(),
                u.getRol()
        );
    }

    private PuntoGeograficoDTO toPuntoGeograficoDTO(PuntoGeografico p) {
        if (p == null) return null;
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
