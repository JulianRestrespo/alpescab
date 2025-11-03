package uniandes.edu.co.alpescab.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.alpescab.modelo.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    @Query(value = "SELECT * FROM VEHICULO", nativeQuery = true)
    Collection<Vehiculo> todos();

    @Query(value = "SELECT * FROM VEHICULO WHERE ID_VEHICULO = :id", nativeQuery = true)
    Vehiculo porId(@Param("id") Long id);

    @Modifying @Transactional
    @Query(value = """
        INSERT INTO VEHICULO (ID_VEHICULO, PLACA_VEHICULO, TIPO, MARCA, MODELO, COLOR,
                              CAPACIDAD, CIUDAD_EXPEDICION, ID_CONDUCTOR)
        VALUES (SEQ_VEHICULO.NEXTVAL, :placa, :tipo, :marca, :modelo, :color,
                :capacidad, :ciudadExpedicion, :idConductor)
        """, nativeQuery = true)
    void insertar(@Param("placa") String placa,
                  @Param("tipo") String tipo,
                  @Param("marca") String marca,
                  @Param("modelo") String modelo,
                  @Param("color") String color,
                  @Param("capacidad") Integer capacidad,
                  @Param("ciudadExpedicion") Long ciudadExpedicion,
                  @Param("idConductor") Long idConductor);

    @Modifying @Transactional
    @Query(value = """
        UPDATE VEHICULO
           SET PLACA_VEHICULO = :placa,
               TIPO            = :tipo,
               MARCA           = :marca,
               MODELO          = :modelo,
               COLOR           = :color,
               CAPACIDAD       = :capacidad,
               CIUDAD_EXPEDICION = :ciudadExpedicion
         WHERE ID_VEHICULO = :id
        """, nativeQuery = true)
    void actualizar(@Param("id") Long id,
                    @Param("placa") String placa,
                    @Param("tipo") String tipo,
                    @Param("marca") String marca,
                    @Param("modelo") String modelo,
                    @Param("color") String color,
                    @Param("capacidad") Integer capacidad,
                    @Param("ciudadExpedicion") Long ciudadExpedicion);

    @Modifying @Transactional
    @Query(value = "DELETE FROM VEHICULO WHERE ID_VEHICULO = :id", nativeQuery = true)
    void eliminar(@Param("id") Long id);
    @Query(value = """
            SELECT *
            FROM VEHICULO
            WHERE PLACA_VEHICULO = :placa
            """, nativeQuery = true)
    Vehiculo porPlaca(@Param("placa") String placa);
}
