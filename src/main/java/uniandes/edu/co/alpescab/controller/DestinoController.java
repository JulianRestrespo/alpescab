package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Destino;
import uniandes.edu.co.alpescab.repositorio.DestinoRepository;

@Controller
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping("/destinos")
    public String listar(Model model){
        model.addAttribute("destinos", destinoRepository.todos());
        return "destinos";
    }

    @GetMapping("/destinos/new")
    public String form(Model model){
        model.addAttribute("destino", new Destino());
        return "destinoNuevo";
    }

    @PostMapping("/destinos/new/save")
    public String guardar(@ModelAttribute Destino d){
        Long idPunto = d.getPunto() != null ? d.getPunto().getId() : null;
        destinoRepository.insertar(d.getId(), d.getNombre(), d.getDescripcion(), idPunto);
        return "redirect:/destinos";
    }

    @GetMapping("/destinos/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var d = destinoRepository.porId(id);
        if(d != null){
            model.addAttribute("destino", d);
            return "destinoEditar";
        } else return "redirect:/destinos";
    }

    @PostMapping("/destinos/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Destino d){
        Long idPunto = d.getPunto() != null ? d.getPunto().getId() : null;
        destinoRepository.actualizar(id, d.getNombre(), d.getDescripcion(), idPunto);
        return "redirect:/destinos";
    }

    @GetMapping("/destinos/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        destinoRepository.eliminar(id);
        return "redirect:/destinos";
    }
}
