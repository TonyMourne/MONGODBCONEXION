package org.tony;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/api/lugar")
public class LugarController {

    @Autowired
    private MongoTemplate mongoTemplate;
    // esto de MongoTemplate es una clase importada que hace mas facil la conexion  y pila el template
    // desde properties y lo inyecta.

    @PostMapping
    public int insertar(@RequestBody Map<String, String> Lugar) {
        mongoTemplate.insert(Lugar, "Lugares");
            return 0;
    }

    @DeleteMapping("/{id}")
    public int eliminar(@PathVariable String id) {

        ObjectId objectId = new ObjectId(id);

        Query query = new Query(Criteria.where("_id").is(objectId));

        var result = mongoTemplate.remove(query, "Lugares");

        return result.getDeletedCount() > 0 ? 1 : 0;
    }

    @GetMapping("/{id}")
    public Map<String,Object> getLugarID(@PathVariable String id) {
        // usamos object por si acaso para cualquier tipo aunque no deberia hacer falta
        ObjectId objectId = new ObjectId(id);
        Map<String,Object> lugar = mongoTemplate.findById(objectId, Map.class, "Lugares");
        return lugar;
    }

    @GetMapping("/preview")
    public List<Map<String, String>> getAllLugaresPreview() {
        List<Map> lugares = mongoTemplate.findAll(Map.class, "Lugares");
        List<Map<String,String>> preview = new ArrayList<>();
        for (Map lugar: lugares){
            Map<String,String> lugarresumido = new HashMap<>();
            Object id = lugar.get("_id");
            Object nombre = lugar.get("Nombre");
            lugarresumido.put("id",id !=null ? id.toString() : "");
            lugarresumido.put("nombre",nombre!=null ? nombre.toString() : "Sin nombre");
            lugarresumido.put("tipo","L");
            preview.add(lugarresumido);
        }
        return preview;
    }



    //BETA DE FILTRADO
    @GetMapping("/buscar")
    public List<Map<String,String>> buscarLugar(@RequestParam Map<String,String> filtros) {

        Query query = new Query();

        for (Map.Entry<String,String> entry : filtros.entrySet()) {
            query.addCriteria(
                    Criteria.where(entry.getKey()).regex(entry.getValue(), "i")
            );
        }

        List<Map> lugares = mongoTemplate.find(query, Map.class, "Lugares");

        List<Map<String,String>> preview = new ArrayList<>();

        for (Map lugar : lugares) {
            Map<String,String> resumen = new HashMap<>();

            Object id = lugar.get("_id");
            Object nombre = lugar.get("Nombre");

            resumen.put("id", id != null ? id.toString() : "");
            resumen.put("nombre", nombre != null ? nombre.toString() : "Sin nombre");
            resumen.put("tipo", "L");

            preview.add(resumen);
        }

        return preview;
    }

    @GetMapping("/all")
    public List<Map<String,Object>> obtenerTodos() {

        List<Map> personajes = mongoTemplate.findAll(Map.class, "Lugares");
        List<Map<String,Object>> resultado = new ArrayList<>();

        for (Map personaje : personajes) {

            Map<String,Object> limpio = new HashMap<>(personaje);

            Object id = personaje.get("_id");

            if (id instanceof ObjectId) {
                limpio.put("_id", ((ObjectId) id).toHexString());
            }

            resultado.add(limpio);
        }

        return resultado;
    }



    @PutMapping("/{id}")
    public int actualizarCampo(@PathVariable String id, @RequestBody Map<String, String> data) {

        ObjectId objectId = new ObjectId(id);

        String campo = data.get("campo");
        String valor = data.get("valor");

        Query query = new Query(Criteria.where("_id").is(objectId));

        org.springframework.data.mongodb.core.query.Update update =
                new org.springframework.data.mongodb.core.query.Update().set(campo, valor);

        var result = mongoTemplate.updateFirst(query, update, "Lugares");
        // esto lo meto por si acaso para el debugging si falla.
        return result.getModifiedCount() > 0 ? 1 : 0;
    }

}
