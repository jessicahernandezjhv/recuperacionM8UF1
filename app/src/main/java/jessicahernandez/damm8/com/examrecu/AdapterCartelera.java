package jessicahernandez.damm8.com.examrecu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCartelera extends RecyclerView.Adapter<AdapterCartelera.ViewHolderCartelera>{

    ArrayList<ModelCartelera> listaPeliculas;

    public AdapterCartelera(ArrayList<ModelCartelera> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public AdapterCartelera.ViewHolderCartelera onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pelicula_item, null, false);
        return new ViewHolderCartelera(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCartelera.ViewHolderCartelera viewHolderCartelera, int position) {
        viewHolderCartelera.nombrePelicula.setText(listaPeliculas.get(position).getTitulo());
        //viewHolderCartelera.cines.setText(listaPeliculas.get(position).getCines().toString());
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public class ViewHolderCartelera extends RecyclerView.ViewHolder {
        TextView nombrePelicula, cines;

        public ViewHolderCartelera(@NonNull View itemView) {
            super(itemView);
            nombrePelicula = itemView.findViewById(R.id.nombrePeliculaID);
            cines = itemView.findViewById(R.id.cinesID);
        }
    }
}
