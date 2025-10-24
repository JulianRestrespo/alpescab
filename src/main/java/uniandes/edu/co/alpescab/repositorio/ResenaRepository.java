package uniandes.edu.co.alpescab.repositorio;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.alpescab.modelo.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

    @Query(value = "SELECT * FROM RESENA", nativeQuery = true)
    Collection<Resena> todas();

    // Busca por la pareja única (ID_VIAJE, ID_AUTOR)
    @Query(value = "SELECT * FROM RESENA WHERE ID_VIAJE = :idViaje AND ID_AUTOR = :idAutor", nativeQuery = true)
    Optional<Resena> porViajeYAutor(@Param("idViaje") Long idViaje, @Param("idAutor") Long idAutor);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO RESENA (ID_RESENA, ID_VIAJE, ID_AUTOR, ID_OBJETIVO, CALIFICACION, COMENTARIO)
        VALUES (SEQ_RESENA.NEXTVAL, :idViaje, :idAutor, :idObjetivo, :calificacion, :comentario)
        """, nativeQuery = true)
    void insertar(@Param("idViaje") Long idViaje,
                  @Param("idAutor") Long idAutor,
                  @Param("idObjetivo") Long idObjetivo,
                  @Param("calificacion") BigDecimal calificacion,
                  @Param("comentario") String comentario);

    @Modifying @Transactional
    @Query(value = """
        UPDATE RESENA
           SET ID_OBJETIVO = :idObjetivo,
               CALIFICACION = :calificacion,
               COMENTARIO   = :comentario
         WHERE ID_VIAJE = :idViaje AND ID_AUTOR = :idAutor
        """, nativeQuery = true)
    void actualizar(@Param("idViaje") Long idViaje,
                    @Param("idAutor") Long idAutor,
                    @Param("idObjetivo") Long idObjetivo,
                    @Param("calificacion") BigDecimal calificacion,
                    @Param("comentario") String comentario);

    @Modifying @Transactional
    @Query(value = "DELETE FROM RESENA WHERE ID_VIAJE = :idViaje AND ID_AUTOR = :idAutor", nativeQuery = true)
    void eliminar(@Param("idViaje") Long idViaje, @Param("idAutor") Long idAutor);
}
