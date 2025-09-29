package uniandes.edu.co.alpescab.repositorio;

import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.PuntoGeografico;

public interface PuntoGeograficoRepository extends JpaRepository<PuntoGeografico, Long> {

    @Query(value = "SELECT * FROM PUNTO_GEOGRAFICO", nativeQuery = true)
    Collection<PuntoGeografico> todos();

    @Query(value = "SELECT * FROM PUNTO_GEOGRAFICO WHERE ID_PUNTO = :id", nativeQuery = true)
    PuntoGeografico porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO PUNTO_GEOGRAFICO (ID_PUNTO, LATITUD, LONGITUD, ID_CIUDAD)
        VALUES (SEQ_PUNTO.NEXTVAL, :latitud, :longitud, :idCiudad)
        """, nativeQuery = true)
    void insertar(@Param("latitud") BigDecimal latitud,
                  @Param("longitud") BigDecimal longitud,
                  @Param("idCiudad") Long idCiudad);

    @Modifying @Transactional
    @Query(value = """
        UPDATE PUNTO_GEOGRAFICO
           SET LATITUD = :latitud,
               LONGITUD = :longitud,
               ID_CIUDAD = :idCiudad
         WHERE ID_PUNTO = :id
        """, nativeQuery = true)
    void actualizar(@Param("id") Long id,
                    @Param("latitud") BigDecimal latitud,
                    @Param("longitud") BigDecimal longitud,
                    @Param("idCiudad") Long idCiudad);

    @Modifying @Transactional
    @Query(value = "DELETE FROM PUNTO_GEOGRAFICO WHERE ID_PUNTO = :id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
}
