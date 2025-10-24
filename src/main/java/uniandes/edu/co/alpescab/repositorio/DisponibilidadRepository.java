package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Disponibilidad;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {

    @Query(value = "SELECT * FROM DISPONIBILIDAD WHERE ID_DISPONIBILIDAD = :id", nativeQuery = true)
    Disponibilidad porId(Long id);

    @Query(value = "SELECT * FROM DISPONIBILIDAD WHERE ID_CONDUCTOR = :idConductor", nativeQuery = true)
    Collection<Disponibilidad> porConductor(Long idConductor);

    @Modifying @Transactional
    @Query(value = """
        UPDATE DISPONIBILIDAD
        SET DIA_SEMANA=:diaSemana, HORA_INICIO=:horaIni, HORA_FIN=:horaFin, TIPO_SERVICIO=:tipoServicio,
            PLACA_VEHICULO=:placaVehiculo
        WHERE ID_DISPONIBILIDAD=:id
        """, nativeQuery = true)
    void actualizar(Long id, String diaSemana, String horaIni, String horaFin, String tipoServicio, String placaVehiculo);

    @Modifying @Transactional
    @Query(value = "DELETE FROM DISPONIBILIDAD WHERE ID_DISPONIBILIDAD = :id", nativeQuery = true)
    void eliminar(Long id);

   
    @Query(value = """
        SELECT COUNT(*) FROM DISPONIBILIDAD
        WHERE ID_CONDUCTOR = :idConductor
          AND DIA_SEMANA = :diaSemana
          AND ((:horaIni BETWEEN HORA_INICIO AND HORA_FIN)
            OR (:horaFin BETWEEN HORA_INICIO AND HORA_FIN)
            OR (HORA_INICIO < :horaIni AND HORA_FIN > :horaFin))
        """, nativeQuery = true)
    int conteoSolapes(Long idConductor, String diaSemana, String horaIni, String horaFin);

    @Query(value = """
        SELECT COUNT(*) 
        FROM DISPONIBILIDAD d
        WHERE d.ID_CONDUCTOR = :idConductor
        AND d.DIA_SEMANA = :diaSemana
        AND d.ID_DISPONIBILIDAD <> :idActual
        AND (
                (d.HORA_INICIO < :horaFin AND d.HORA_FIN > :horaInicio)
            )
    """, nativeQuery = true)
    int conteoSolapesEdit(
        @Param("idConductor") Long idConductor,
        @Param("diaSemana") String diaSemana,
        @Param("horaInicio") String horaInicio,
        @Param("horaFin") String horaFin,
        @Param("idActual") Long idActual
    );

}
