package retrofit;

import model.SongArtist;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SA_retrofit {

    String BASE_URL = "http://10.0.2.2:8000";

    @GET("/SQLKkboxSong/")
    Call<ArrayList<SongArtist>> getSongInfo(@Query("button") String button);

}
