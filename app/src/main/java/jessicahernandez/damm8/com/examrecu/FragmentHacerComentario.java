package jessicahernandez.damm8.com.examrecu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHacerComentario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHacerComentario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHacerComentario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edtRecomendacion, edtComentario;
    Button enviarComentario;

    // FIREBASE DATABASE
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    String databasePath = "comentarios";


    private OnFragmentInteractionListener mListener;

    public FragmentHacerComentario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHacerComentario.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHacerComentario newInstance(String param1, String param2) {
        FragmentHacerComentario fragment = new FragmentHacerComentario();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hacer_comentario, container, false);
        edtRecomendacion = view.findViewById(R.id.edtRecomendacionID);
        edtComentario = view.findViewById(R.id.edtComentariosID);
        enviarComentario = view.findViewById(R.id.btnHacerComentarioID);

        //Firebase Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(databasePath);


        enviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerComentario();
            }
        });

        return view;
        // Inflate the layout for this fragment
    }

    private void hacerComentario(){
        final String textoRecomendacion = edtRecomendacion.getText().toString();
        final String comentario = edtComentario.getText().toString();
        final String nombrepeli = "default";

        ModelComentarios nuevoComentario = new ModelComentarios(nombrepeli, textoRecomendacion, comentario);

        // Creamos una nueva clave para introducir un elemento nuevo en firebase

        String nuevoComentarioID = databaseReference.push().getKey();
        // Creamos un hijo con esta clave e introducimos los datos del objeto HacerReservaModel
        databaseReference.child(nuevoComentarioID).setValue(nuevoComentario);
        Toast.makeText(getActivity(),"Comentario posteado",Toast.LENGTH_SHORT).show();
        clearEditFields();
    }

    public void clearEditFields() {
        SystemClock.sleep(500);
        edtRecomendacion.getText().clear();;
        edtComentario.getText().clear();
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
}
