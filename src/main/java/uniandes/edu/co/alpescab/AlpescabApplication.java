package uniandes.edu.co.alpescab;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.alpescab.modelo.Ciudad;
import uniandes.edu.co.alpescab.repositorio.CiudadRepository;

@SpringBootApplication
public class AlpescabApplication implements CommandLineRunner {

    @Autowired
    private CiudadRepository ciudadRepository;

    public static void main(String[] args) {
        SpringApplication.run(AlpescabApplication.class, args);
    }

    @Override
    public void run(String... arg)
     {
        Collection<Ciudad> ciudades = ciudadRepository.todas();
        System.out.println("Ciudades en la base de datos:");
        for (Ciudad c : ciudades) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
    }
}
