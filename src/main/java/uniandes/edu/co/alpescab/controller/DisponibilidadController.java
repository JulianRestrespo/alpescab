package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Disponibilidad;
import uniandes.edu.co.alpescab.repositorio.DisponibilidadRepository;

@Controller
public class DisponibilidadController {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @GetMapping("/disponibilidades")
    public String listar(Model model){
        model.addAttribute("disponibilidades", disponibilidadRepository.porConductor(null));  
        return "disponibilidades";
    }

    @GetMapping("/disponibilidades/new")
    public String form(Model model){
        model.addAttribute("disponibilidad", new Disponibilidad());
        return "disponibilidadNuevo";
    }

    @PostMapping("/disponibilidades/new/save")
    public String guardar(@ModelAttribute Disponibilidad d){
        Long idConductor = d.getConductor() != null ? d.getConductor().getId() : null;

        int solapes = disponibilidadRepository.conteoSolapes(
            idConductor, d.getDiaSemana(), d.getHoraInicio(), d.getHoraFin());
        if (solapes > 0) return "redirect:/disponibilidades"; // o una vista de error

        disponibilidadRepository.insertar(
            d.getId(), d.getDiaSemana(), d.getHoraInicio(), d.getHoraFin(),
            d.getTipoServicio(), idConductor, d.getPlacaVehiculo()
        );
        return "redirect:/disponibilidades";
    }

    @GetMapping("/disponibilidades/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var d = disponibilidadRepository.porId(id);
        if(d != null){
            model.addAttribute("disponibilidad", d);
            return "disponibilidadEditar";
        } else return "redirect:/disponibilidades";
    }

    @PostMapping("/disponibilidades/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Disponibilidad d){
        disponibilidadRepository.actualizar(
            id, d.getDiaSemana(), d.getHoraInicio(), d.getHoraFin(),
            d.getTipoServicio(), d.getPlacaVehiculo()
        );
        return "redirect:/disponibilidades";
    }

    @GetMapping("/disponibilidades/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        disponibilidadRepository.eliminar(id);
        return "redirect:/disponibilidades";
    }
}
