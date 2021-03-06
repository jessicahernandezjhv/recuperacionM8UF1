package jessicahernandez.damm8.com.examrecu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentLogin extends Fragment {

    EditText usuario, password;
    Button login;

    private OnAddEquipoListener mListener;

    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usuario = view.findViewById(R.id.usernameID);
        password = view.findViewById(R.id.passID);
        login = view.findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelLogin nuevoUsuario = new ModelLogin(usuario.getText().toString(),
                        password.getText().toString());

                mListener.writeSQLite(nuevoUsuario);
                usuario.getText().clear();
                password.getText().clear();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddEquipoListener) {
            mListener = (OnAddEquipoListener) context;
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
    public interface OnAddEquipoListener {
        // TODO: Update argument type and name
        void writeSQLite(ModelLogin nuevoUsuario);
        void searchSQLite(String columna, String valor);
    }
}