package data_entities;
import java.sql.Connection;
import java.sql.Time;

public  class songs_list implements Comparable<data_entities.songs_list>
{
    Connection con;
    private  int Songs_id;
    private  String Song_Name;
    private  String Artist;
    private String album_Name;
    private String Genre;
    private Time duration;
   // private String Path;

    public songs_list(int songs_id, String song_Name, String artist, String album_Name ,String genre, Time duration)
    {
        Songs_id = songs_id;
        Song_Name = song_Name;
        Artist = artist;
        this.album_Name=album_Name;
        Genre = genre;
        this.duration = duration;
        //Path = path;
        this.con=con;
    }


    @Override
    public int compareTo(data_entities.songs_list o)
    {
        return this.Song_Name.compareTo(getSong_Name());
    }

    public int getSongs_id() {
        return Songs_id;
    }

    public void setSongs_id(int songs_id) {
        Songs_id = songs_id;
    }

    public String getSong_Name() {
        return Song_Name;
    }

    public void setSong_Name(String song_Name) {
        Song_Name = song_Name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return Songs_id + "         "+Song_Name + "                    "+ Artist + "                    " +Genre +
                "                     "+duration ;
    }

    public String getAlbum_Name() {
        return album_Name;
    }

    public void setAlbum_Name(String album_Name) {
        this.album_Name = album_Name;
    }
}
