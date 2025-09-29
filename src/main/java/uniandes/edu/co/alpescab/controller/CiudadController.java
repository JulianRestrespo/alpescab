package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Ciudad;
import uniandes.edu.co.alpescab.repositorio.CiudadRepository;

@Controller
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudades")
    public String listar(Model model){
        model.addAttribute("ciudades", ciudadRepository.todas());
        return "ciudades";
    }

    @GetMapping("/ciudades/new")
    public String form(Model model){
        model.addAttribute("ciudad", new Ciudad());
        return "ciudadNuevo";
    }

    @PostMapping("/ciudades/new/save")
    public String guardar(@ModelAttribute Ciudad c){
        ciudadRepository.insertar(c.getNombre()); // <- tu firma
        return "redirect:/ciudades";
    }

    @GetMapping("/ciudades/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var c = ciudadRepository.porId(id);
        if(c != null){
            model.addAttribute("ciudad", c);
            return "ciudadEditar";
        } else {
            return "redirect:/ciudades";
        }
    }

    @PostMapping("/ciudades/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Ciudad c){
        ciudadRepository.actualizar(id, c.getNombre());
        return "redirect:/ciudades";
    }

    @GetMapping("/ciudades/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        ciudadRepository.eliminar(id);
        return "redirect:/ciudades";
    }
}
