package dsa.examen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Followers extends AppCompatActivity {
    private ProgressBar loading;
    ListView listView;
    public String usuario;
    List<String> namesFollowers = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        loading= (ProgressBar) findViewById(R.id.progressBar) ;
        loading.setVisibility(View.GONE);

        listView = (ListView) findViewById(R.id.list);
        usuario = getIntent().getExtras().getString("nombre");
        loading.setVisibility(View.VISIBLE);
        loading.setProgress(5);

        //**********************RETROFIT*******************
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());


        final Retrofit retrofit = builder.client(httpClient.build()).build();
        GitHubClient listaFollowers = retrofit.create(GitHubClient.class);
        Call<List<Contributor>> call = listaFollowers.getList(usuario);


        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {


                if (response.isSuccessful()) {
                    loading.setVisibility(View.GONE);

                    List<Contributor> followers = response.body();
                    for (Contributor c : followers) {
                        System.out.println(c.getLogin());
                        namesFollowers.add(c.getLogin());

                    }

                    ArrayAdapter adapter = new ArrayAdapter(Followers.this, android.R.layout.simple_list_item_1, namesFollowers);
                    listView.setAdapter(adapter);

                }

                else{

                    Toast.makeText(Followers.this, "El nombre de usuario esta mal", Toast.LENGTH_SHORT).show();

                }

            }

            @Override

            public void onFailure(Call<List<Contributor>> call, Throwable t) {

                Toast.makeText(Followers.this,  t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}