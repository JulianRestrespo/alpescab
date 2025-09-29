package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.TransportePasajeros;
import uniandes.edu.co.alpescab.repositorio.TransportePasajerosRepository;

@Controller
public class TransportePasajerosController {

    @Autowired
    private TransportePasajerosRepository transportePasajerosRepository;

    @GetMapping("/transportePasajeros")
    public String listar(Model model){
        model.addAttribute("items", transportePasajerosRepository.todas());
        return "transportePasajeros";
    }

    @GetMapping("/transportePasajeros/new")
    public String form(Model model){
        model.addAttribute("item", new TransportePasajeros());
        return "transportePasajerosNuevo";
    }

    @PostMapping("/transportePasajeros/new/save")
    public String guardar(@ModelAttribute TransportePasajeros tp){
        Long idServicio = tp.getServicio() != null ? tp.getServicio().getId() : null;
        Long idDestino  = tp.getDestino()  != null ? tp.getDestino().getId()  : null;

        transportePasajerosRepository.insertar(
            idServicio, tp.getNivel(), tp.getNumeroPasajeros(), idDestino
        );
        return "redirect:/transportePasajeros";
    }

    @GetMapping("/transportePasajeros/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var tp = transportePasajerosRepository.porId(id);
        if(tp != null){
            model.addAttribute("item", tp);
            return "transportePasajerosEditar";
        } else return "redirect:/transportePasajeros";
    }

    @PostMapping("/transportePasajeros/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute TransportePasajeros tp){
        Long idDestino = tp.getDestino() != null ? tp.getDestino().getId() : null;
        transportePasajerosRepository.actualizar(
            id, tp.getNivel(), tp.getNumeroPasajeros(), idDestino
        );
        return "redirect:/transportePasajeros";
    }

    @GetMapping("/transportePasajeros/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        transportePasajerosRepository.eliminar(id);
        return "redirect:/transportePasajeros";
    }
}
