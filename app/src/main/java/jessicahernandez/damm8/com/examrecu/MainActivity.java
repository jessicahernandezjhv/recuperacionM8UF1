package jessicahernandez.damm8.com.examrecu;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentWelcome.OnFragmentInteractionListener,
        FragmentVerCartelera.OnFragmentInteractionListener, FragmentLogin.OnAddEquipoListener,
        FragmentHacerComentario.OnFragmentInteractionListener, FragmentComentList.OnFragmentInteractionListener {
    Toolbar toolbar;
    FragmentWelcome welcomeScreen;
    FragmentVerCartelera verCartelera;
    FragmentLogin loginScreen;
    FragmentHacerComentario hacerComentario;
    FragmentComentList verComentarios;

    List<ModelLogin> usuariosRegistrados = new ArrayList<>();
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new MyBBDD_Helper(this).getWritableDatabase();

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


    @Override
    public void writeSQLite(ModelLogin nuevoUsuario) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyBBDD_Schema.EntradaBBDD.COLUMNA1, nuevoUsuario.getUsername());
        contentValues.put(MyBBDD_Schema.EntradaBBDD.COLUMNA2, nuevoUsuario.getPassword());

        MyBBDD_Helper dbHelper = new MyBBDD_Helper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        sqLiteDatabase.insert(MyBBDD_Schema.EntradaBBDD.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchSQLite(String columna, String valor) {
        MyBBDD_Helper dbHelper = new MyBBDD_Helper(this);
        usuariosRegistrados = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columnas = { MyBBDD_Schema.EntradaBBDD.COLUMNA1};

        Cursor cursor = db.query(MyBBDD_Schema.EntradaBBDD.TABLE_NAME, columnas, columna + "=?", new String[]{valor}, null, null, null);
        while (cursor.moveToNext() ) {
            usuariosRegistrados.add(new ModelLogin( cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA1)),
                            cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA2))
                    )
            );
        }


        db.close();
    }
}
