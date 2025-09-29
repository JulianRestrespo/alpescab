package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Viaje;
import uniandes.edu.co.alpescab.repositorio.ViajeRepository;

@Controller
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping("/viajes")
    public String listar(Model model){
        model.addAttribute("viajes", viajeRepository.todos());
        return "viajes";
    }

    @GetMapping("/viajes/new")
    public String form(Model model){
        model.addAttribute("viaje", new Viaje());
        return "viajeNuevo";
    }

    @PostMapping("/viajes/new/save")
    public String guardar(@ModelAttribute Viaje v){
        Long idConductor = v.getConductor() != null ? v.getConductor().getId() : null;
        Long idVehiculo  = v.getVehiculo()  != null ? v.getVehiculo().getId()  : null;
        Long idServicio  = v.getServicio()  != null ? v.getServicio().getId()  : null;

        viajeRepository.insertar(
            v.getId(), idConductor, idVehiculo, v.getHoraInicio(), v.getHoraFin(),
            v.getDistanciaKm(), v.getCostoTotal(), idServicio, v.getGananciaConductor(), v.getFecha()
        );
        return "redirect:/viajes";
    }

    @GetMapping("/viajes/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var v = viajeRepository.porId(id);
        if(v != null){
            model.addAttribute("viaje", v);
            return "viajeEditar";
        } else return "redirect:/viajes";
    }

    @PostMapping("/viajes/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Viaje v){
        viajeRepository.actualizar(
            id, v.getHoraInicio(), v.getHoraFin(), v.getDistanciaKm(),
            v.getCostoTotal(), v.getGananciaConductor(), v.getFecha()
        );
        return "redirect:/viajes";
    }

    @GetMapping("/viajes/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        viajeRepository.eliminar(id);
        return "redirect:/viajes";
    }
}
