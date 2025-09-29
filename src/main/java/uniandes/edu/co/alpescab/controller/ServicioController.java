package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Servicio;
import uniandes.edu.co.alpescab.repositorio.ServicioRepository;

@Controller
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String listar(Model model){
        model.addAttribute("servicios", servicioRepository.todos());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String form(Model model){
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";
    }

    @PostMapping("/servicios/new/save")
    public String guardar(@ModelAttribute Servicio s){
        Long idCliente = (s.getCliente() != null) ? s.getCliente().getId() : null;
        Long idOrigen  = (s.getOrigen()  != null) ? s.getOrigen().getId()  : null;

        servicioRepository.insertar(
            s.getId(),
            s.getTipoServicio(),
            s.getEstado(),
            idCliente,
            idOrigen
        );
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var s = servicioRepository.porId(id);
        if(s != null){
            model.addAttribute("servicio", s);
            return "servicioEditar";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Servicio s){
        Long idCliente = (s.getCliente() != null) ? s.getCliente().getId() : null;
        Long idOrigen  = (s.getOrigen()  != null) ? s.getOrigen().getId()  : null;

        servicioRepository.actualizar(
            id,
            s.getTipoServicio(),
            s.getEstado(),
            idCliente,
            idOrigen
        );
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        servicioRepository.eliminar(id);
        return "redirect:/servicios";
    }
}
