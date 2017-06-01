package dsa.examen2;

import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dani on 31/05/2017.
 */

public interface GitHubClient {

    @GET("/users/{nombre}/followers")
    Call<List<Contributor>> getList(@Path("nombre") String nombre);
}





