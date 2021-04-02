package aplicacioncliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Respuestas {
    private final String tipo;
    private final List<String> listadoDatos = new ArrayList<>();

    public Respuestas(String tipo,List <String>listadoDatos) {
        this.tipo = tipo;
        this.listadoDatos.addAll(listadoDatos);
        Collections.reverse(this.listadoDatos);
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getListadoDatos() {
        return listadoDatos;
    }
}
