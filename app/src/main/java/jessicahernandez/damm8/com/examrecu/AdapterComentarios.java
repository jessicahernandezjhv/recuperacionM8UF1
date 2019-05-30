package jessicahernandez.damm8.com.examrecu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterComentarios extends RecyclerView.Adapter<AdapterComentarios.ComentariosViewHolder> {
    ArrayList<ModelComentarios> listaComentarios;

    public AdapterComentarios(ArrayList<ModelComentarios> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    @NonNull
    @Override
    public AdapterComentarios.ComentariosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comentario_item, null, false);
        return new ComentariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentarios.ComentariosViewHolder comentariosViewHolder, int position) {
        comentariosViewHolder.mostrarRecomendacion.setText("Pelicula recomendada: "+ listaComentarios.get(position).getRecomendacion());
        comentariosViewHolder.mostrarComentarios.setText(listaComentarios.get(position).getComentario().toString());
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }

    public class ComentariosViewHolder extends RecyclerView.ViewHolder {
        TextView mostrarRecomendacion, mostrarComentarios;

        public ComentariosViewHolder(@NonNull View itemView) {
            super(itemView);
            mostrarRecomendacion = itemView.findViewById(R.id.verRecomendacionID);
            mostrarComentarios = itemView.findViewById(R.id.verUnComentarioID);
        }
    }
}
