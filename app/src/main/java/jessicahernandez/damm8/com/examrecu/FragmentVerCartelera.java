package jessicahernandez.damm8.com.examrecu;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentVerCartelera.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentVerCartelera#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVerCartelera extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<ModelCartelera> listaPeliculas;
    ProgressBar spinner;
    RecyclerView recyclerCartelera;

    // Variables para la recyclerview
    AdapterCartelera adapter;

    private OnFragmentInteractionListener mListener;

    public FragmentVerCartelera() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVerCartelera.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVerCartelera newInstance(String param1, String param2) {
        FragmentVerCartelera fragment = new FragmentVerCartelera();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_cartelera, container, false);
        listaPeliculas = new ArrayList<>();
        recyclerCartelera = (RecyclerView) view.findViewById(R.id.recyclerPeliculasID);
        spinner = (ProgressBar) view.findViewById(R.id.progressBar2);
        spinner.setVisibility(View.VISIBLE);

        HiloAPI hilo = new HiloAPI();
        hilo.execute("https://jdarestaurant.firebaseio.com/peliculas.json");

        recyclerCartelera.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter= new AdapterCartelera(listaPeliculas);
        recyclerCartelera.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class HiloAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            URL url;
            connection = null;
            String result;
            result = "";

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();
                while (data != -1) {
                    result += (char) data;
                    data = inputStream.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Una vez tenemos todos los datos, los retornamos
            Log.i("RESULT", result);
            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("salas");

                    for(int i=0; i<jsonArray.length(); i++) {
                        ModelCartelera cartelera = new ModelCartelera();

                        JSONObject jsonitem = jsonArray.getJSONObject(i);
                        cartelera.setTitulo(jsonitem.getString("titulo"));
                        cartelera.setCine(jsonitem.getString("cine"));

                        listaPeliculas.add(cartelera);
                        Log.i("Lista", cartelera.getTitulo());
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }

            spinner.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }
}
