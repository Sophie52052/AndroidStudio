package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongArtist {
    @Expose
    @SerializedName("songname") //JSON key name
    private String song;

    @Expose
    @SerializedName("artist") //JSON key name
    private String artist;


    public SongArtist(String song, String artist){
        this.song = song;
        this.artist = artist;

    }

    public String getsong() {
        return this.song;
    }

    public String getartist() {
        return this.artist;
    }


}
