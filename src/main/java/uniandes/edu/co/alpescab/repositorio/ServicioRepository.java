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
