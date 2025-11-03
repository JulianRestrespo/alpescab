package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.DTO.CiudadDTO;
import uniandes.edu.co.alpescab.DTO.PuntoGeograficoDTO;
import uniandes.edu.co.alpescab.DTO.ServicioDTO;
import uniandes.edu.co.alpescab.DTO.UsuarioDTO;
import uniandes.edu.co.alpescab.repositorio.DestinoRepository;
import uniandes.edu.co.alpescab.repositorio.DisponibilidadRepository;
import uniandes.edu.co.alpescab.repositorio.EntregaComidaRepository;
import uniandes.edu.co.alpescab.repositorio.MercanciaRepository;
import uniandes.edu.co.alpescab.repositorio.PuntoGeograficoRepository;
import uniandes.edu.co.alpescab.repositorio.ServicioRepository;
import uniandes.edu.co.alpescab.repositorio.TarjetaRepository;
import uniandes.edu.co.alpescab.repositorio.TransportePasajerosRepository;
import uniandes.edu.co.alpescab.repositorio.UsuarioRepository;
import uniandes.edu.co.alpescab.repositorio.VehiculoRepository;
import uniandes.edu.co.alpescab.repositorio.ViajeRepository;

import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.DTO.SolicitarServicioRequestDTO;
import uniandes.edu.co.alpescab.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.util.Collection;

@RestController
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    private DisponibilidadRepository disponibilidadRepository;
    @Autowired
    private TransportePasajerosRepository transportePasajerosRepository;
    @Autowired
    private EntregaComidaRepository entregaComidaRepository;
    @Autowired
    private MercanciaRepository mercanciaRepository;
    @Autowired
    private DestinoRepository destinoRepository;
    @Autowired
    private PuntoGeograficoRepository puntoGeograficoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ViajeRepository viajeRepository;

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

    @PostMapping("/servicios/solicitar")
    @Transactional
    public ResponseEntity<?> solicitar(@RequestBody SolicitarServicioRequestDTO req) {

        // 1. validar cliente
        Usuario cliente = usuarioRepository.porId(req.getIdCliente());
        if (cliente == null) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }

        // 2. validar tarjeta (medio de pago)
        Tarjeta tarjeta = tarjetaRepository.porUsuario(req.getIdCliente());
        if (tarjeta == null) {
            return ResponseEntity.status(409).body("El cliente no tiene medio de pago registrado");
        }

        // 3. buscar disponibilidad por tipo
        String tipo = req.getTipoServicio().toLowerCase();   // "pasajeros" | "comida" | "mercancias"
        Disponibilidad disp = disponibilidadRepository.primeraPorTipo(tipo);
        if (disp == null) {
            return ResponseEntity.status(409).body("No hay conductores disponibles para " + tipo);
        }

        Usuario conductor = disp.getConductor();
        Long idConductor = conductor.getId();
        String placa = disp.getPlacaVehiculo();

        // 4. obtener vehículo por placa
        Vehiculo vehiculo = vehiculoRepository.porPlaca(placa);
        if (vehiculo == null) {
            return ResponseEntity.status(409).body("No se encontró vehículo con placa " + placa + " para el conductor");
        }

        // 5. insertar servicio base en estado 'asignado'
        servicioRepository.insertar(
                null,                      // que use la secuencia
                tipo,
                "asignado",
                req.getIdCliente(),
                req.getIdOrigen()
        );

        // recuperar el servicio recién creado (el último del cliente)
        Servicio servicio = servicioRepository.ultimoPorCliente(req.getIdCliente());
        if (servicio == null) {
            return ResponseEntity.internalServerError().body("No fue posible recuperar el servicio creado");
        }



        // 6. guardar detalle según tipo
        switch (tipo) {
            case "pasajeros" -> {
                if (req.getIdDestino() == null) {
                    return ResponseEntity.badRequest().body("Para servicios de pasajeros debe enviarse idDestino");
                }
                Destino destino = destinoRepository.findById(req.getIdDestino())
                        .orElse(null);
                if (destino == null) {
                    return ResponseEntity.badRequest().body("El destino no existe");
                }

                TransportePasajeros tp = new TransportePasajeros();
                tp.setServicio(servicio);
                tp.setDestino(destino);
                tp.setNivel(req.getNivel() != null ? req.getNivel() : "Estandar");
                tp.setNumeroPasajeros(req.getNumeroPasajeros() != null ? req.getNumeroPasajeros() : 1);

                transportePasajerosRepository.save(tp);
            }
            case "comida" -> {
                if (req.getIdPuntoEntrega() == null) {
                    return ResponseEntity.badRequest().body("Para servicios de comida debe enviarse idPuntoEntrega");
                }
                PuntoGeografico punto = puntoGeograficoRepository.findById(req.getIdPuntoEntrega())
                        .orElse(null);
                if (punto == null) {
                    return ResponseEntity.badRequest().body("El punto de entrega no existe");
                }

                EntregaComida ec = new EntregaComida();
                ec.setServicio(servicio);
                ec.setRestaurante(req.getRestaurante());
                ec.setPuntoEntrega(punto);

                entregaComidaRepository.save(ec);
            }
            case "mercancias" -> {
                Mercancia m = new Mercancia();
                m.setServicio(servicio);
                mercanciaRepository.save(m);
            }
            default -> {
                return ResponseEntity.badRequest().body("Tipo de servicio no soportado: " + tipo);
            }
        }

        // 7. insertar viaje ligado al servicio
        String horaInicio = (req.getHoraInicio() != null) ? req.getHoraInicio() : "08:00";

        viajeRepository.insertar(
                null,                     
                idConductor,
                vehiculo.getId(),
                horaInicio,
                horaInicio,                
                BigDecimal.valueOf(5),           
                BigDecimal.valueOf(20000),         
                servicio.getId(),
                BigDecimal.valueOf(15000),          
                LocalDate.now()
        );

        return ResponseEntity.ok("Servicio creado y viaje iniciado");
    }
}
