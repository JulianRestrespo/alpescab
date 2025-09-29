package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
    Collection<Usuario> todos();

    @Query(value = "SELECT * FROM USUARIO WHERE ID_USUARIO = :id", nativeQuery = true)
    Usuario porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO USUARIO (ID_USUARIO, CEDULA, EMAIL, CELULAR, NOMBRE, ROL)
        VALUES (SEQ_USUARIO.NEXTVAL, :cedula, :email, :celular, :nombre, :rol)
        """, nativeQuery = true)
    void insertar(@Param("cedula") String cedula,
                  @Param("email") String email,
                  @Param("celular") String celular,
                  @Param("nombre") String nombre,
                  @Param("rol") String rol);

    @Modifying @Transactional
    @Query(value = """
        UPDATE USUARIO SET CEDULA=:cedula, EMAIL=:email, CELULAR=:celular, NOMBRE=:nombre, ROL=:rol
        WHERE ID_USUARIO=:id
        """, nativeQuery = true)
    void actualizar(@Param("id") Long id,
                    @Param("cedula") String cedula,
                    @Param("email") String email,
                    @Param("celular") String celular,
                    @Param("nombre") String nombre,
                    @Param("rol") String rol);

    @Modifying @Transactional
    @Query(value = "DELETE FROM USUARIO WHERE ID_USUARIO = :id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
}
