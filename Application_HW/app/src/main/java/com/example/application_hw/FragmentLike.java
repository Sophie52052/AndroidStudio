package com.example.application_hw;


import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.SongArtist;
import retrofit.SA_retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLike extends Fragment {

    private String button; //bundle
    private SA_retrofit sa; //create
    private ListView listView;
    private SimpleAdapter simpleAdapter;



    public FragmentLike() {
        // Required empty public constructor
    }

    //接BUNDLE
    public static FragmentLike newInstance(Bundle args){
        FragmentLike fragment = new FragmentLike();
        fragment.setArguments(args);
        return fragment;
    }

    //接fragmentMain的bundle
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            button = getArguments().getString("button");
            Log.d("FragmentLike", button);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 把return改成view
        View view=inflater.inflate(R.layout.fragment_fragment_like, container, false);
        getSA_data(view);
        return view;
    }

    private void getSA_data(View view){
        //Log.d("FragmentLike", "create request: " );
        final View framentView = view;

        //build
        Retrofit r = new Retrofit.Builder()
                .baseUrl(SA_retrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sa=r.create(SA_retrofit.class);
        //Log.d("sa",sa.toString());

        Call<ArrayList<SongArtist>> call =sa.getSongInfo(button);
        //Log.d("url",call.request().url().toString());

        //http問題
        //solve:https://stackoverflow.com/questions/41650965/cleartext-communication-not-supported-on-retrofit
        //django ALLOW host *
       call.enqueue(
                new Callback<ArrayList<SongArtist>>(){
                    @Override
                    public void onResponse(Call<ArrayList<SongArtist>> call, Response<ArrayList<SongArtist>> response) {
                        if (response.body() != null){
                            try {
                                setSong(framentView, response.body());
                                Log.d("response","success");
                            } catch (ParseException e) {
                                //e.printStackTrace();
                                //有接10.0.2.2
                                Log.d("response","somethimg_wrong");
                            }
                        }
                    }

                    @Override
                    //完全死亡沒收到10.0.2.2:8000
                    public void onFailure(Call<ArrayList<SongArtist>> call, Throwable t) {
                        new AlertDialog.Builder(getContext()).setMessage("休息哦").show();
                        Log.d("SecondFragment", String.valueOf(t));
                    }
                }
        );
    }




    private void setSong(View view, ArrayList<SongArtist> song_artist) throws ParseException{
        //在vieew底下

        listView = (ListView) view.findViewById(R.id.listView);

        //吃後來的arraylist name


        List<Map<String, String>> items = new ArrayList<Map<String,String>>();
        for (int i = 0; i < song_artist.size(); i++) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("song", song_artist.get(i).getsong());
            item.put("artist", song_artist.get(i).getartist());
            items.add(item);
        }
        //https://stackoverflow.com/questions/36552215/java-constructor-simpleadapter-in-class-simpleadapter-cannot-be-applied-to-given?rq=1
        //simpleadapter this-getActivity()
        simpleAdapter = new SimpleAdapter(getActivity(),
                items, R.layout.list, new String[]{"song", "artist"},
                new int[]{R.id.song, R.id.artist});
        listView.setAdapter(simpleAdapter);
    }
}






