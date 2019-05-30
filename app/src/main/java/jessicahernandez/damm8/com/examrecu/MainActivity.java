package jessicahernandez.damm8.com.examrecu;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements FragmentWelcome.OnFragmentInteractionListener,
        FragmentVerCartelera.OnFragmentInteractionListener, FragmentLogin.OnFragmentInteractionListener,
        FragmentHacerComentario.OnFragmentInteractionListener, FragmentComentList.OnFragmentInteractionListener {
    Toolbar toolbar;
    FragmentWelcome welcomeScreen;
    FragmentVerCartelera verCartelera;
    FragmentLogin loginScreen;
    FragmentHacerComentario hacerComentario;
    FragmentComentList verComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //TOOLBAR
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FRAGMENTS
        welcomeScreen = new FragmentWelcome();
        verCartelera = new FragmentVerCartelera();
        loginScreen = new FragmentLogin();
        hacerComentario = new FragmentHacerComentario();
        verComentarios = new FragmentComentList();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, welcomeScreen).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.verCarteleraID:
                transaction.replace(R.id.contenedorFragments, verCartelera);
                break;
            case R.id.loginID:
                transaction.replace(R.id.contenedorFragments, loginScreen);
                break;
            case R.id.hacerComentarioID:
                transaction.replace(R.id.contenedorFragments, hacerComentario);
                break;
            case R.id.verComentID:
                transaction.replace(R.id.contenedorFragments, verComentarios);
                break;

        }
        transaction.commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
