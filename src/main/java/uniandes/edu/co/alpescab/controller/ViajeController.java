package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.alpescab.modelo.*;
import uniandes.edu.co.alpescab.repositorio.ViajeRepository;

import uniandes.edu.co.alpescab.DTO.ViajeDTO;
import uniandes.edu.co.alpescab.DTO.UsuarioDTO;
import uniandes.edu.co.alpescab.DTO.ServicioDTO;
import uniandes.edu.co.alpescab.DTO.VehiculoDTO;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoDTO;

import java.util.Collection;
import java.util.List;

@RestController
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping("/viajes")
    public ResponseEntity<List<ViajeDTO>> listar() {
        Collection<Viaje> entidades = viajeRepository.todos();

        List<ViajeDTO> respuesta = entidades.stream()
                .map(this::toViajeDTO)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/viajes/new")
    public Viaje form() {
        return new Viaje();
    }

    @PostMapping("/viajes/new/save")
    public ResponseEntity<Void> guardar(@RequestBody Viaje v) {
        Long idConductor = v.getConductor() != null ? v.getConductor().getId() : null;
        Long idVehiculo  = v.getVehiculo()  != null ? v.getVehiculo().getId()  : null;
        Long idServicio  = v.getServicio()  != null ? v.getServicio().getId()  : null;

        viajeRepository.insertar(
            v.getId(),
            idConductor,
            idVehiculo,
            v.getHoraInicio(),
            v.getHoraFin(),
            v.getDistanciaKm(),
            v.getCostoTotal(),
            idServicio,
            v.getGananciaConductor(),
            v.getFecha()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/viajes/{id}/edit")
    public ResponseEntity<ViajeDTO> editarForm(@PathVariable("id") Long id){
        Viaje v = viajeRepository.porId(id);
        if(v != null){
            return ResponseEntity.ok(toViajeDTO(v));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/viajes/{id}/edit/save")
    public ResponseEntity<Void> editarGuardar(@PathVariable("id") Long id,
                                              @RequestBody Viaje v){

        viajeRepository.actualizar(
            id,
            v.getHoraInicio(),
            v.getHoraFin(),
            v.getDistanciaKm(),
            v.getCostoTotal(),
            v.getGananciaConductor(),
            v.getFecha()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/viajes/{id}/delete")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id){
        viajeRepository.eliminar(id);
        return ResponseEntity.ok().build();
    }

    private ViajeDTO toViajeDTO(Viaje v) {
        return new ViajeDTO(
            v.getId(),
            v.getServicio() != null ? v.getServicio().getEstado() : null, 
            toUsuarioDTO(v.getConductor()),
            toServicioDTO(v.getServicio()),
            toVehiculoDTO(v.getVehiculo()),
            v.getHoraInicio(),
            v.getHoraFin(),
            v.getDistanciaKm(),
            v.getCostoTotal(),
            v.getGananciaConductor(),
            v.getFecha()
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

    private ServicioDTO toServicioDTO(Servicio s) {
        if (s == null) return null;
        return new ServicioDTO(
            s.getId(),
            s.getTipoServicio(),
            s.getEstado(),
            toUsuarioDTO(s.getCliente()),
            toPuntoGeograficoDTO(s.getOrigen())
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

    private VehiculoDTO toVehiculoDTO(Vehiculo v) {
        if (v == null) return null;
        return new VehiculoDTO(
            v.getId(),
            v.getPlacaVehiculo(),
            v.getMarca(),
            v.getModelo(),
            v.getColor(),
            v.getCapacidad(),
            v.getTipo(),
            toUsuarioDTO(v.getConductor()),
            toCiudadDTO(v.getCiudadExpedicion())
        );
    }
}
