package jessicahernandez.damm8.com.examrecu;

import java.util.List;

public class ModelCartelera {
    String titulo;
    List<String> cines;

    public ModelCartelera() {
    }

    public ModelCartelera(String titulo, List<String> cines) {
        this.titulo = titulo;
        this.cines = cines;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getCines() {
        return cines;
    }

    public void setCines(List<String> cines) {
        this.cines = cines;
    }
}
