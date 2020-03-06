package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    EditText editUsuario;
    EditText editContraseña;
    Button butonIngresar;
    String usuario;
    String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsuario= (EditText)findViewById(R.id.editUsuario);
        editContraseña= (EditText)findViewById(R.id.editContraseña);

    }
    public void click(View view) {
        usuario=editUsuario.getText().toString();
        contraseña=editContraseña.getText().toString();

        if(usuario.isEmpty()){
            editUsuario.setError("Ingrese un usuario valido");
            editUsuario.requestFocus();
            return;
        }
        if(contraseña.isEmpty()){
            editContraseña.setError("Ingrese una contraseña valida");
            editContraseña.requestFocus();
            return;

        }
        Log.d("entre aqui", "onResponse: ");

        Retrofit.Builder retrofit= new Retrofit.Builder()
                    .baseUrl(Server.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
        Retrofit r=retrofit.build();

            final Server s= r.create(Server.class);
            Call<User> call=s.postAuthentication(usuario,contraseña);
        Log.d("aqui tambien", "onResponse: ");
            call.enqueue(new Callback<User>() {
               @Override
               public void onResponse(Call<User> call, Response<User> response) {
                   if(response.isSuccessful()) {
                       Toast toast1 =
                               Toast.makeText(getApplicationContext(),
                                       "funciono", Toast.LENGTH_SHORT);

                       toast1.show();

                   }
               }

               @Override
               public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
               }
           });



        }



}
