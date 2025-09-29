package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query(value = "SELECT * FROM CIUDAD", nativeQuery = true)
    Collection<Ciudad> todas();

    @Query(value = "SELECT * FROM CIUDAD WHERE ID_CIUDAD = :id", nativeQuery = true)
    Ciudad porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = "INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE) VALUES (SEQ_CIUDAD.NEXTVAL, :nombre)", nativeQuery = true)
    void insertar(@Param("nombre") String nombre);

    @Modifying @Transactional
    @Query(value = "UPDATE CIUDAD SET NOMBRE = :nombre WHERE ID_CIUDAD = :id", nativeQuery = true)
    void actualizar(@Param("id") Long id, @Param("nombre") String nombre);

    @Modifying @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE ID_CIUDAD = :id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
}
