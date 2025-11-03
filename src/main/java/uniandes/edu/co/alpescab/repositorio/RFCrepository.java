package uniandes.edu.co.alpescab.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.alpescab.modelo.Servicio;

import java.util.List;

public interface RFCrepository extends JpaRepository<Servicio, Long> {

    @Query(value = """
        SELECT 
            s.ID_SERVICIO,
            s.TIPO_SERVICIO,
            s.ESTADO,
            c.NOMBRE           AS CIUDAD_ORIGEN,
            v.HORA_INICIO,
            v.HORA_FIN,
            v.DISTANCIA_KM,
            v.COSTO_TOTAL,
            ucon.NOMBRE        AS CONDUCTOR
        FROM SERVICIO s
        JOIN PUNTO_GEOGRAFICO p ON p.ID_PUNTO = s.ID_ORIGEN
        JOIN CIUDAD c           ON c.ID_CIUDAD = p.ID_CIUDAD
        LEFT JOIN VIAJE v       ON v.ID_SERVICIO = s.ID_SERVICIO
        LEFT JOIN USUARIO ucon  ON ucon.ID_USUARIO = v.ID_CONDUCTOR
        WHERE s.ID_CLIENTE = :idUsuario
        ORDER BY s.ID_SERVICIO DESC
        """, nativeQuery = true)
    List<Object[]> rfc1(@Param("idUsuario") Long idUsuario);
}
