package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO_GEN")
    @SequenceGenerator(name = "SEQ_USUARIO_GEN", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "CEDULA", nullable = false, length = 20, unique = true)
    private String cedula;

    @Column(name = "EMAIL", nullable = false, length = 320, unique = true)
    private String email;

    @Column(name = "CELULAR", nullable = false, length = 20)
    private String celular;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ROL", length = 15)
    private String rol; // 'pasajero' | 'conductor' según tu CHECK en DB

    public Usuario() {}

    public Usuario(String cedula, String email, String celular, String nombre, String rol) {
        this.cedula = cedula;
        this.email = email;
        this.celular = celular;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public String getCedula() { return cedula; }
    public String getEmail() { return email; }
    public String getCelular() { return celular; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }

    public void setId(Long id) { this.id = id; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setEmail(String email) { this.email = email; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRol(String rol) { this.rol = rol; }
}
