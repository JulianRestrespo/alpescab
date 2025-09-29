package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Mercancia;
import uniandes.edu.co.alpescab.repositorio.MercanciaRepository;

@Controller
public class MercanciaController {

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @GetMapping("/mercancias")
    public String listar(Model model){
        model.addAttribute("mercancias", mercanciaRepository.todas());
        return "mercancias";
    }

    @GetMapping("/mercancias/new")
    public String form(Model model){
        model.addAttribute("mercancia", new Mercancia());
        return "mercanciaNuevo";
    }

    @PostMapping("/mercancias/new/save")
    public String guardar(@ModelAttribute Mercancia m){
        Long idServ = m.getServicio() != null ? m.getServicio().getId() : null;
        Long idDest = m.getDestino() != null ? m.getDestino().getId() : null;
        mercanciaRepository.insertar(idServ, m.getDescripcionProducto(), idDest);
        return "redirect:/mercancias";
    }

    @GetMapping("/mercancias/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var m = mercanciaRepository.porServicio(id);
        if(m != null){
            model.addAttribute("mercancia", m);
            return "mercanciaEditar";
        } else return "redirect:/mercancias";
    }

    @PostMapping("/mercancias/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Mercancia m){
        Long idDest = m.getDestino() != null ? m.getDestino().getId() : null;
        mercanciaRepository.actualizar(id, m.getDescripcionProducto(), idDest);
        return "redirect:/mercancias";
    }

    @GetMapping("/mercancias/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        mercanciaRepository.eliminar(id);
        return "redirect:/mercancias";
    }
}
