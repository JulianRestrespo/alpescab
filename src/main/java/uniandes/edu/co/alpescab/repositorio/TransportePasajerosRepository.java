package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.TransportePasajeros;

public interface TransportePasajerosRepository extends JpaRepository<TransportePasajeros, Long> {


    @Query(value = "SELECT * FROM TRANSPORTE_PASAJEROS", nativeQuery = true)
    Collection<TransportePasajeros> todas();

   
    @Query(value = "SELECT * FROM TRANSPORTE_PASAJEROS WHERE ID_SERVICIO = :id", nativeQuery = true)
    TransportePasajeros porId(@Param("id") Long id);

   
    @Query(value = "SELECT * FROM TRANSPORTE_PASAJEROS WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    TransportePasajeros porServicio(@Param("idServicio") Long idServicio);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO TRANSPORTE_PASAJEROS(ID_SERVICIO, NIVEL, NUMERO_PASAJEROS, ID_DESTINO)
        VALUES(:idServicio, :nivel, :numPasajeros, :idDestino)
        """, nativeQuery = true)
    void insertar(@Param("idServicio") Long idServicio,
                  @Param("nivel") String nivel,
                  @Param("numPasajeros") Integer numPasajeros,
                  @Param("idDestino") Long idDestino);

    @Modifying @Transactional
    @Query(value = """
        UPDATE TRANSPORTE_PASAJEROS
        SET NIVEL=:nivel, NUMERO_PASAJEROS=:numPasajeros, ID_DESTINO=:idDestino
        WHERE ID_SERVICIO=:idServicio
        """, nativeQuery = true)
    void actualizar(@Param("idServicio") Long idServicio,
                    @Param("nivel") String nivel,
                    @Param("numPasajeros") Integer numPasajeros,
                    @Param("idDestino") Long idDestino);


    @Modifying @Transactional
    @Query(value = "DELETE FROM TRANSPORTE_PASAJEROS WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminar(@Param("idServicio") Long idServicio);
}
