package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.EntregaComida;
import uniandes.edu.co.alpescab.repositorio.EntregaComidaRepository;

@Controller
public class EntregaComidaController {

    @Autowired
    private EntregaComidaRepository entregaComidaRepository;

    @GetMapping("/entregas")
    public String listar(Model model){
        model.addAttribute("entregas", entregaComidaRepository.findAll());
        return "entregas";
    }

    @GetMapping("/entregas/new")
    public String form(Model model){
        model.addAttribute("entrega", new EntregaComida());
        return "entregaNuevo";
    }

    @PostMapping("/entregas/new/save")
    public String guardar(@ModelAttribute EntregaComida e){
        Long idServ = e.getServicio() != null ? e.getServicio().getId() : null;
        Long idPunto = e.getPuntoEntrega() != null ? e.getPuntoEntrega().getId() : null;
        entregaComidaRepository.insertar(idServ, e.getRestaurante(), idPunto);
        return "redirect:/entregas";
    }

    @GetMapping("/entregas/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var e = entregaComidaRepository.porServicio(id);
        if(e != null){
            model.addAttribute("entrega", e);
            return "entregaEditar";
        } else return "redirect:/entregas";
    }

    @PostMapping("/entregas/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute EntregaComida e){
        Long idPunto = e.getPuntoEntrega() != null ? e.getPuntoEntrega().getId() : null;
        entregaComidaRepository.actualizar(id, e.getRestaurante(), idPunto);
        return "redirect:/entregas";
    }

    @GetMapping("/entregas/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        entregaComidaRepository.eliminar(id);
        return "redirect:/entregas";
    }
}
