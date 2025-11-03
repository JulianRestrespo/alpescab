package uniandes.edu.co.alpescab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.repositorio.RFCrepository;

import java.util.*;

@RestController
@RequestMapping("/rfc1")
@RequiredArgsConstructor
public class RfcController {

    private final RFCrepository repo;

    private List<Map<String,Object>> toMaps(List<Object[]> rows) {
        String[] cols = {
            "ID_SERVICIO","TIPO_SERVICIO","ESTADO","CIUDAD_ORIGEN",
            "HORA_INICIO","HORA_FIN","DISTANCIA_KM","COSTO_TOTAL","CONDUCTOR"
        };
        List<Map<String,Object>> out = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            Map<String,Object> m = new LinkedHashMap<>();
            for (int i = 0; i < cols.length; i++) m.put(cols[i], r[i]);
            out.add(m);
        }
        return out;
    }

    @GetMapping("/read-committed/{idUsuario}")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Map<String,Object> rfc1RC(@PathVariable Long idUsuario) throws InterruptedException {
        var antes = toMaps(repo.rfc1(idUsuario));
        Thread.sleep(30_000);
        var despues = toMaps(repo.rfc1(idUsuario));
        return Map.of("aislamiento","READ_COMMITTED","antes",antes,"despues",despues);
    }

    @GetMapping("/serializable/{idUsuario}")
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Map<String,Object> rfc1SZ(@PathVariable Long idUsuario) throws InterruptedException {
        var antes = toMaps(repo.rfc1(idUsuario));
        Thread.sleep(30_000);
        var despues = toMaps(repo.rfc1(idUsuario));
        return Map.of("aislamiento","SERIALIZABLE","antes",antes,"despues",despues);
    }
}
