package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "USUARIO")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    
    private String cedula;
    private String email;
    private String celular;
    private String nombre;
    private String rol;

    public Usuario() 
    {;}

    public Usuario(String cedula, String email, String celular, String nombre, String rol) {
        this.cedula = cedula;
        this.email = email;
        this.celular = celular;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }
    public String getCedula() {
        return cedula;
    }
    public String getEmail() {
        return email;
    }
    public String getCelular() {
        return celular;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCelular(String celular) {
        this.celular = celular;}
    public void setNombre(String nombre) {
        this.nombre = nombre;}
    public void setRol(String rol) {
        this.rol = rol;
    }

}
    