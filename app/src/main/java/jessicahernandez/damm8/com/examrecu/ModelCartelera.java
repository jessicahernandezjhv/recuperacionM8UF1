package jessicahernandez.damm8.com.examrecu;

import java.util.ArrayList;
import java.util.List;

public class ModelCartelera {
    String titulo;
    String cine;

    public ModelCartelera() {
    }

    public ModelCartelera(String titulo, String cine) {
        this.titulo = titulo;
        this.cine = cine;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCine() {
        return cine;
    }

    public void setCine(String cine) {
        this.cine = cine;
    }
}
