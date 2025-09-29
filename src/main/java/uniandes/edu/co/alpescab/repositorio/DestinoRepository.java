package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

    @Query(value = "SELECT * FROM DESTINO", nativeQuery = true)
    Collection<Destino> todos();

    @Query(value = "SELECT * FROM DESTINO WHERE ID_DESTINO = :id", nativeQuery = true)
    Destino porId(Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO DESTINO(ID_DESTINO, NOMBRE, DESCRIPCION, ID_PUNTO)
        VALUES(:id, :nombre, :descripcion, :idPunto)
        """, nativeQuery = true)
    void insertar(Long id, String nombre, String descripcion, Long idPunto);
   
    @Modifying @Transactional
    @Query(value = """
        UPDATE DESTINO SET NOMBRE=:nombre, DESCRIPCION=:descripcion, ID_PUNTO=:idPunto
        WHERE ID_DESTINO=:id
        """, nativeQuery = true)
    void actualizar(Long id, String nombre, String descripcion, Long idPunto);

    @Modifying @Transactional
    @Query(value = "DELETE FROM DESTINO WHERE ID_DESTINO = :id", nativeQuery = true)
    void eliminar(Long id);
}
