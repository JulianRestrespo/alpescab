package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Vehiculo;
import uniandes.edu.co.alpescab.repositorio.VehiculoRepository;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/vehiculos")
    public String listar(Model model){
        model.addAttribute("vehiculos", vehiculoRepository.todos());
        return "vehiculos";
    }

    @GetMapping("/vehiculos/new")
    public String form(Model model){
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculoNuevo";
    }

    @PostMapping("/vehiculos/new/save")
    public String guardar(@ModelAttribute Vehiculo v){
        vehiculoRepository.insertar(
            v.getPlacaVehiculo(), v.getTipo(), v.getMarca(), v.getModelo(),
            v.getColor(), v.getCapacidad(),
            v.getCiudadExpedicion() != null ? v.getCiudadExpedicion().getId() : null,
            v.getConductor() != null ? v.getConductor().getId() : null
        );
        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var v = vehiculoRepository.porId(id);
        if(v != null){
            model.addAttribute("vehiculo", v);
            return "vehiculoEditar";
        } else return "redirect:/vehiculos";
    }

    @PostMapping("/vehiculos/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Vehiculo v){
        vehiculoRepository.actualizar(
            id, v.getPlacaVehiculo(), v.getTipo(), v.getMarca(), v.getModelo(),
            v.getColor(), v.getCapacidad(),
            v.getCiudadExpedicion() != null ? v.getCiudadExpedicion().getId() : null
        );
        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        vehiculoRepository.eliminar(id);
        return "redirect:/vehiculos";
    }
}
