package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value = "SELECT * FROM SERVICIO", nativeQuery = true)
    Collection<Servicio> todos();

    @Query(value = "SELECT * FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    Servicio porId(Long id);

    @Query(value = "SELECT * FROM SERVICIO WHERE ID_CLIENTE = :idCliente ORDER BY ID_SERVICIO DESC", nativeQuery = true)
    Collection<Servicio> porCliente(Long idCliente);

     
    @Query(value = """
        SELECT
          s.ID_SERVICIO    AS idServicio,
          s.TIPO_SERVICIO  AS tipoServicio,
          s.ESTADO         AS estado,
          c.NOMBRE         AS ciudadOrigen,
          v.HORA_INICIO    AS horaInicio,
          v.HORA_FIN       AS horaFin,
          v.DISTANCIA_KM   AS distanciaKm,
          v.COSTO_TOTAL    AS costoTotal,
          ucon.NOMBRE      AS conductor
        FROM SERVICIO s
        JOIN PUNTO_GEOGRAFICO p ON p.ID_PUNTO = s.ID_ORIGEN
        JOIN CIUDAD c           ON c.ID_CIUDAD = p.ID_CIUDAD
        LEFT JOIN VIAJE v       ON v.ID_SERVICIO = s.ID_SERVICIO
        LEFT JOIN USUARIO ucon  ON ucon.ID_USUARIO = v.ID_CONDUCTOR
        WHERE s.ID_CLIENTE = :idUsuario
        ORDER BY s.ID_SERVICIO DESC
        """, nativeQuery = true)
    Collection<ServicioHistoricoView> historicoPorUsuario(Long idUsuario);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO SERVICIO(ID_SERVICIO, TIPO_SERVICIO, ESTADO, ID_CLIENTE, ID_ORIGEN)
        VALUES(:id, :tipoServicio, :estado, :idCliente, :idOrigen)
        """, nativeQuery = true)
    void insertar(Long id, String tipoServicio, String estado, Long idCliente, Long idOrigen);
    
    @Modifying @Transactional
    @Query(value = """
        UPDATE SERVICIO SET TIPO_SERVICIO=:tipoServicio, ESTADO=:estado, ID_CLIENTE=:idCliente, ID_ORIGEN=:idOrigen
        WHERE ID_SERVICIO=:id
        """, nativeQuery = true)
    void actualizar(Long id, String tipoServicio, String estado, Long idCliente, Long idOrigen);

    @Modifying @Transactional
    @Query(value = "DELETE FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    void eliminar(Long id);
}
