package dsa.examen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    ListView listView;
    public String Usuario;
    public int idRepo, idFollow;
    List<String> namesFollowers = new ArrayList<String>();
    String tag="Followers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        listView = (ListView) findViewById(R.id.list);


        Usuario = getIntent().getExtras().getString("emailLogged");
        idRepo = getIntent().getExtras().getInt("idLogged");
        idFollow = getIntent().getExtras().getInt("followersUsuario");


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://api.github.com/")                //poner esta para atacar a la api nuestra 10.0.2.2
                .addConverterFactory(GsonConverterFactory.create());
//
        final Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        GitHubClient listaFollowers = retrofit.create(GitHubClient.class);
        Call<List<Contributor>> call = listaFollowers.getList(Usuario);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> followers = response.body();

                for (Contributor c : followers){
                    System.out.println(c.getLogin());
                    namesFollowers.add(c.getLogin());
                }

                ArrayAdapter adapter=new ArrayAdapter(Followers.this,android.R.layout.simple_list_item_1, namesFollowers);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }
}

