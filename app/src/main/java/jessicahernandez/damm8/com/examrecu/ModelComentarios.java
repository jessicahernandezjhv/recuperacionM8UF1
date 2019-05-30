package jessicahernandez.damm8.com.examrecu;

public class ModelComentarios {
    String nombrePeli;
    String recomendacion;
    String comentario;

    public ModelComentarios() {
    }

    public ModelComentarios(String nombrePeli, String recomendacion, String comentario) {
        this.nombrePeli = nombrePeli;
        this.recomendacion = recomendacion;
        this.comentario = comentario;
    }

    public String getNombrePeli() {
        return nombrePeli;
    }

    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
