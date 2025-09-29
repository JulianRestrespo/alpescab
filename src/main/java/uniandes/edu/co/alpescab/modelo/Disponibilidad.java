package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DISPONIBILIDAD")
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String diaSemana;    
    private String horaInicio;   
    private String horaFin;    
    private String tipoServicio; 
    @ManyToOne
    private Usuario conductor;

     
    private String placaVehiculo;

    public Disponibilidad() {;}

    public Disponibilidad(String diaSemana, String horaInicio, String horaFin,
                          String tipoServicio, Usuario conductor, String placaVehiculo) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tipoServicio = tipoServicio;
        this.conductor = conductor;
        this.placaVehiculo = placaVehiculo;
    }

    public Long getId() { return id; }
    public String getDiaSemana() { return diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public String getTipoServicio() { return tipoServicio; }
    public Usuario getConductor() { return conductor; }
    public String getPlacaVehiculo() { return placaVehiculo; }

    public void setId(Long id) { this.id = id; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setConductor(Usuario conductor) { this.conductor = conductor; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
}
