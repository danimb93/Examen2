package dsa.examen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nombre extends AppCompatActivity {
    private EditText user;
    private Button explore;
    private ProgressBar loading;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        explore=(Button) findViewById(R.id.btnExplore);
        user = (EditText) findViewById(R.id.etUser);
        loading= (ProgressBar) findViewById(R.id.progressBar) ;
        loading.setVisibility(View.GONE);


        explore.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if(user.getText().toString().equals("")){
                    Toast.makeText(Nombre.this, "Escriba un nombre", Toast.LENGTH_SHORT).show();
                }else{
                    loading.setVisibility(View.VISIBLE);
                    loading.setProgress(20);
                    Intent intent = new Intent (getApplicationContext(),Followers.class);
                    intent.putExtra("nombre",user.getText().toString());
                    startActivity(intent);
            }

        }
        });
    }
}