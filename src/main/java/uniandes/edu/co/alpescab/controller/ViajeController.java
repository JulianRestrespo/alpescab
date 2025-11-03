package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import uniandes.edu.co.alpescab.modelo.*;
import uniandes.edu.co.alpescab.repositorio.ViajeRepository;
import uniandes.edu.co.alpescab.repositorio.DisponibilidadRepository;
import uniandes.edu.co.alpescab.repositorio.ServicioRepository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.alpescab.DTO.ViajeDTO;
import uniandes.edu.co.alpescab.DTO.UsuarioDTO;
import uniandes.edu.co.alpescab.DTO.ServicioDTO;
import uniandes.edu.co.alpescab.DTO.VehiculoDTO;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.DTO.FinalizarViajeDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoDTO;

import java.util.Collection;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

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

    @PutMapping("/viajes/{idViaje}/finalizar")
    @Transactional
    public ResponseEntity<?> finalizar(@PathVariable Long idViaje,
                                    @Valid @RequestBody FinalizarViajeDTO req) {
    // 1) cargar viaje
    var v = viajeRepository.findById(idViaje)
        .orElseThrow(() -> new IllegalArgumentException("Viaje no existe"));
    // 2) validar pertenencia
    if (!v.getIdConductor().equals(req.getIdConductor())) {
        return ResponseEntity.status(403).body("El viaje no pertenece al conductor");
    }
    // 3) cálculos simples
    int costo = (v.getCostoTotal()!=null && v.getCostoTotal().compareTo(BigDecimal.ZERO) > 0) ? v.getCostoTotal().intValue()
                : 3000 + 2000 * req.getDistanciaKm();
    int ganancia = (int)Math.round(costo * 0.7);

    // 4) finalizar viaje
    int updated = viajeRepository.finalizarViaje(
        idViaje, req.getIdConductor(), req.getHoraFin(), req.getDistanciaKm(), costo, ganancia);
    if (updated == 0) return ResponseEntity.ok("Ya estaba finalizado.");

    // 5.1 obtener tipoServicio desde el servicio del viaje
    String[] tipoServicio = {"pasajeros"};
    if (v.getIdServicio() != null) {
        servicioRepository.findById(v.getIdServicio()).ifPresent(s -> {
            if (s.getTipoServicio() != null) {
                tipoServicio[0] = s.getTipoServicio().toLowerCase();
            }
        });
    }

    // 5.2 obtener placa del vehículo del viaje
    String placa = v.getVehiculo().getPlacaVehiculo(); 

    // 5.3 construir día/hora (formato 'lunes'..'domingo' y 'HH:mm')
    LocalDateTime now = LocalDateTime.now();
    String dia = switch (now.getDayOfWeek()) {
        case MONDAY -> "lunes";
        case TUESDAY -> "martes";
        case WEDNESDAY -> "miercoles";
        case THURSDAY -> "jueves";
        case FRIDAY -> "viernes";
        case SATURDAY -> "sabado";
        case SUNDAY -> "domingo";
    };
    String horaInicio = String.format("%02d:%02d", now.getHour(), now.getMinute());
    String horaFinDisp = "23:59";

    // 5.4 insertar disponibilidad
    disponibilidadRepository.insertarDisponibilidad(
        null, 
        dia,
        horaInicio,
        horaFinDisp,
        tipoServicio[0],    // 'pasajeros' | 'comida' | 'mercancias'
        req.getIdConductor(),
        placa
    );

    return ResponseEntity.ok("Viaje finalizado correctamente.");
    }
}
