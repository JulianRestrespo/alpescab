package uniandes.edu.co.alpescab.repositorio;

import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.EntregaComida;

public interface EntregaComidaRepository extends JpaRepository<EntregaComida, Long> {
   
    @Query(value = "SELECT * FROM ENTREGA_COMIDA WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    EntregaComida porServicio(Long idServicio);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO ENTREGA_COMIDA(ID_SERVICIO, RESTAURANTE, ID_PUNTO_ENTREGA)
        VALUES(:idServicio, :restaurante, :idPuntoEntrega)
        """, nativeQuery = true)
    void insertar(Long idServicio, String restaurante, Long idPuntoEntrega);

    @Modifying @Transactional
    @Query(value = """
        UPDATE ENTREGA_COMIDA SET RESTAURANTE=:restaurante, ID_PUNTO_ENTREGA=:idPuntoEntrega
        WHERE ID_SERVICIO=:idServicio
        """, nativeQuery = true)
    void actualizar(Long idServicio, String restaurante, Long idPuntoEntrega);

    @Modifying @Transactional
    @Query(value = "DELETE FROM ENTREGA_COMIDA WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminar(Long idServicio);
}
