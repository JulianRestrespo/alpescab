package uniandes.edu.co.alpescab.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.alpescab.modelo.Tarjeta;  

import java.util.Collection;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

    @Query(value = "SELECT * FROM TARJETA", nativeQuery = true)
    Collection<Tarjeta> todas();

    @Query(value = "SELECT * FROM TARJETA WHERE ID_TARJETA = :id", nativeQuery = true)
    Tarjeta porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO TARJETA (ID_TARJETA, NUMERO, NOMBRE_TARJETA, VENCIMIENTO, CVV, ID_USUARIO)
        VALUES (SEQ_TARJETA.NEXTVAL, :numero, :nombre, :vencimiento, :cvv, :idUsuario)
        """, nativeQuery = true)
    void insertar(@Param("numero") String numero,
                  @Param("nombre") String nombre,
                  @Param("vencimiento") java.sql.Date vencimiento,
                  @Param("cvv") String cvv,
                  @Param("idUsuario") Long idUsuario);

    @Modifying @Transactional
    @Query(value = """
        UPDATE TARJETA SET NUMERO=:numero, NOMBRE_TARJETA=:nombre, VENCIMIENTO=:vencimiento, CVV=:cvv, ID_USUARIO=:idUsuario
        WHERE ID_TARJETA=:id
        """, nativeQuery = true)
    void actualizar(@Param("id") Long id,
                    @Param("numero") String numero,
                    @Param("nombre") String nombre,
                    @Param("vencimiento") java.sql.Date vencimiento,
                    @Param("cvv") String cvv,
                    @Param("idUsuario") Long idUsuario);

    @Modifying @Transactional
    @Query(value = "DELETE FROM TARJETA WHERE ID_TARJETA=:id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
}
