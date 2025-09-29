package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Mercancia;

public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {

    @Query(value = "SELECT * FROM MERCANCIA", nativeQuery = true)
    Collection<Mercancia> todas();

    
    @Query(value = "SELECT * FROM MERCANCIA WHERE ID_SERVICIO = :id", nativeQuery = true)
    Mercancia porId(@Param("id") Long id);

    
    @Query(value = "SELECT * FROM MERCANCIA WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    Mercancia porServicio(@Param("idServicio") Long idServicio);

  
    @Modifying @Transactional
    @Query(value = """
        INSERT INTO MERCANCIA(ID_SERVICIO, DESCRIPCION_PRODUCTO, ID_DESTINO)
        VALUES(:idServicio, :descripcion, :idDestino)
        """, nativeQuery = true)
    void insertar(@Param("idServicio") Long idServicio,
                  @Param("descripcion") String descripcion,
                  @Param("idDestino") Long idDestino);

    
    @Modifying @Transactional
    @Query(value = """
        UPDATE MERCANCIA SET DESCRIPCION_PRODUCTO=:descripcion, ID_DESTINO=:idDestino
        WHERE ID_SERVICIO=:idServicio
        """, nativeQuery = true)
    void actualizar(@Param("idServicio") Long idServicio,
                    @Param("descripcion") String descripcion,
                    @Param("idDestino") Long idDestino);

  
    @Modifying @Transactional
    @Query(value = "DELETE FROM MERCANCIA WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminar(@Param("idServicio") Long idServicio);
}
