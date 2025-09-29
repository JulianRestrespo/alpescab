package uniandes.edu.co.alpescab.repositorio;

import java.time.LocalDate;
import java.util.Collection;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.alpescab.modelo.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query(value = "SELECT * FROM VIAJE", nativeQuery = true)
    Collection<Viaje> todos();

    @Query(value = "SELECT * FROM VIAJE WHERE ID_VIAJE = :id", nativeQuery = true)
    Viaje porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO VIAJE (
            ID_VIAJE, ID_CONDUCTOR, ID_VEHICULO,
            HORA_INICIO, HORA_FIN, DISTANCIA_KM,
            COSTO_TOTAL, ID_SERVICIO, GANANCIA_CONDUCTOR, FECHA
        )
        VALUES (
            NVL(:id, SEQ_VIAJE.NEXTVAL), :idConductor, :idVehiculo,
            :horaIni, :horaFin, :distanciaKm,
            :costoTotal, :idServicio, :gananciaConductor, :fecha
        )
        """, nativeQuery = true)
    void insertar(
        @Param("id") Long id,
        @Param("idConductor") Long idConductor,
        @Param("idVehiculo") Long idVehiculo,
        @Param("horaIni") String horaIni,
        @Param("horaFin") String horaFin,
        @Param("distanciaKm") BigDecimal distanciaKm,
        @Param("costoTotal") BigDecimal costoTotal,
        @Param("idServicio") Long idServicio,
        @Param("gananciaConductor") BigDecimal gananciaConductor,
        @Param("fecha") LocalDate fecha
    );

    @Modifying @Transactional
    @Query(value = """
        UPDATE VIAJE
           SET HORA_INICIO = :horaIni,
               HORA_FIN = :horaFin,
               DISTANCIA_KM = :distanciaKm,
               COSTO_TOTAL = :costoTotal,
               GANANCIA_CONDUCTOR = :gananciaConductor,
               FECHA = :fecha
         WHERE ID_VIAJE = :id
        """, nativeQuery = true)
    void actualizar(
        @Param("id") Long id,
        @Param("horaIni") String horaIni,
        @Param("horaFin") String horaFin,
        @Param("distanciaKm") BigDecimal distanciaKm,
        @Param("costoTotal") BigDecimal costoTotal,
        @Param("gananciaConductor") BigDecimal gananciaConductor,
        @Param("fecha") LocalDate fecha
    );

    @Modifying @Transactional
    @Query(value = "DELETE FROM VIAJE WHERE ID_VIAJE = :id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
}
