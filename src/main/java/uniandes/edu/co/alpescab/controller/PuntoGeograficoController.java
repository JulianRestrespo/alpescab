package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.PuntoGeografico;
import uniandes.edu.co.alpescab.repositorio.PuntoGeograficoRepository;

@Controller
public class PuntoGeograficoController {

    @Autowired
    private PuntoGeograficoRepository puntoRepository;

    @GetMapping("/puntos")
    public String listar(Model model){
        model.addAttribute("puntos", puntoRepository.todos());
        return "puntos";
    }

    @GetMapping("/puntos/new")
    public String form(Model model){
        model.addAttribute("punto", new PuntoGeografico());
        return "puntoNuevo";
    }

    @PostMapping("/puntos/new/save")
    public String guardar(@ModelAttribute PuntoGeografico p){
        puntoRepository.insertar(
            p.getLatitud(), p.getLongitud(),
            p.getCiudad() != null ? p.getCiudad().getId() : null
        );
        return "redirect:/puntos";
    }

    @GetMapping("/puntos/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var p = puntoRepository.porId(id);
        if(p != null){
            model.addAttribute("punto", p);
            return "puntoEditar";
        } else return "redirect:/puntos";
    }

    @PostMapping("/puntos/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute PuntoGeografico p){
        puntoRepository.actualizar(
            id, p.getLatitud(), p.getLongitud(),
            p.getCiudad() != null ? p.getCiudad().getId() : null
        );
        return "redirect:/puntos";
    }

    @GetMapping("/puntos/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        puntoRepository.eliminar(id);
        return "redirect:/puntos";
    }
}
