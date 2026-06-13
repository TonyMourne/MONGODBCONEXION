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
@RequestMapping("/api/campania")
public class CampaniaController {

    @Autowired
    private MongoTemplate mongoTemplate;
    // esto de MongoTemplate es una clase importada que hace mas facil la conexion  y pila el template
    // desde properties y lo inyecta.

    @PostMapping
    public int insertar(@RequestBody Campania camp) {
        System.out.println(camp.toString());
        mongoTemplate.insert(camp, "Campania");
        return 0;
    }

    @DeleteMapping("/{id}")
    public int eliminar(@PathVariable String id) {

        ObjectId objectId = new ObjectId(id);

        Query query = new Query(Criteria.where("_id").is(objectId));

        var result = mongoTemplate.remove(query, "Campania");

        return result.getDeletedCount() > 0 ? 1 : 0;
    }


    @GetMapping("/{id}")
    public Map<String,Object> getLugarID(@PathVariable String id) {
        // usamos object por si acaso para cualquier tipo aunque no deberia hacer falta
        ObjectId objectId = new ObjectId(id);
        Map<String,Object> campania = mongoTemplate.findById(objectId, Map.class, "Campania");
        return campania;
    }

    @GetMapping("/preview")
    public List<Map<String, String>> getAllCampaniasPreview() {
        List<Map> campanias = mongoTemplate.findAll(Map.class, "Campania");
        List<Map<String,String>> preview = new ArrayList<>();
        for (Map campania: campanias){
            Map<String,String> campaniaresumido = new HashMap<>();
            Object id = campania.get("_id");
            Object nombre = campania.get("TITULO_CAMPANIA");
            campaniaresumido.put("id",id !=null ? id.toString() : "");
            campaniaresumido.put("nombre",nombre!=null ? nombre.toString() : "Sin nombre");
            campaniaresumido.put("tipo","C");
            preview.add(campaniaresumido);
        }
        return preview;
    }



    //BETA DE FILTRADO
    @GetMapping("/buscar")
    public List<Map<String,String>> buscarCampania(@RequestParam Map<String,String> filtros) {

        Query query = new Query();

        for (Map.Entry<String,String> entry : filtros.entrySet()) {
            query.addCriteria(
                    Criteria.where(entry.getKey()).regex(entry.getValue(), "i")
            );
        }

        List<Map> campanias = mongoTemplate.find(query, Map.class, "Campania");

        List<Map<String,String>> preview = new ArrayList<>();

        for (Map campania : campanias) {
            Map<String,String> resumen = new HashMap<>();

            Object id = campania.get("_id");
            Object nombre = campania.get("TITULO_CAMPANIA");

            resumen.put("id", id != null ? id.toString() : "");
            resumen.put("nombre", nombre != null ? nombre.toString() : "Sin nombre");
            resumen.put("tipo", "C");

            preview.add(resumen);
        }

        return preview;
    }

    @GetMapping("/all")
    public List<Map<String,Object>> obtenerTodos() {

        List<Map> personajes = mongoTemplate.findAll(Map.class, "Campania");
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

        var result = mongoTemplate.updateFirst(query, update, "Campania");

        return result.getModifiedCount() > 0 ? 1 : 0;
    }

}
