package org.example;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSimpleAdapter extends VoorheesExporter{

    @Override
    public String exportar(List<Socio> socios) {
        if (socios.isEmpty()) {
            return "[]";
        }
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        socios.stream().forEach(socio -> {
            try {
                jsonArray.add(parser.parse(this.exportar(socio)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return jsonArray.toJSONString();
    }

    private String exportar(Socio socio) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombre", socio.getNombre());
        jsonObject.put("email", socio.getEmail());
        jsonObject.put("legajo", socio.getLegajo());
        return jsonObject.toJSONString();
    }
}
