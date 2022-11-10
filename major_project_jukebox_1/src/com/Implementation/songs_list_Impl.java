package com.Implementation;
import data_entities.songs_list;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Custom_Exception.Inappropriate_Search;
public class songs_list_Impl
{
    Connection con;
List<songs_list> list=new ArrayList<>();

public List<songs_list> AllSongInList(Connection con)
{
List<songs_list> list1=new ArrayList<>();

        try {

            String sql = "select * from songs_list";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            songs_list songs1;
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songName = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String album = resultSet.getString(4);
                String genre = resultSet.getString(5);
                Time duration = resultSet.getTime(6);

                songs1 = new songs_list(songId, songName, artistName, album, genre, duration);        //
                list1.add(songs1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list1;
    }
    public void showAllSongs(Connection con)
    {
        try {

            String sql = "select * from songs_list";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\u001B[0m"+"    ===========================================================================================     ");
            System.out.println("    =                            DISPLAYING ALL SONGS                                         =     ");
            System.out.println("    ============================================================================================    ");
            System.out.println();
            System.out.println("ID        SONG NAME               ARTIST                     ALBUM                 GENRE                      TIME");
           System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");


            while (resultSet.next())
            {
                System.out.format("%-5s %-25s %-25s %-25s %-15s %-30s %n", resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getTime(6));
            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<songs_list> filter_by_Song_Name (String song_Name, List<songs_list> songs) throws Inappropriate_Search
    {
        List<songs_list> BySong_Name = new ArrayList<>();         //declaring bysong_name list
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("ID         SONG NAME             ARTIST                       ALBUM                        GENRE              " );
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (songs_list songs1 : songs)
        {
            if (songs1.getSong_Name().equalsIgnoreCase(song_Name))
            {
                BySong_Name.add(songs1);
            }

            BySong_Name.sort((o1, o2) ->
            {
                return o1.getSong_Name().compareToIgnoreCase(o2.getSong_Name());
            });
            try {
                if (song_Name.isEmpty())
                {
                    throw new Inappropriate_Search("NO song is found by this Search" ) ;
                }
            }
            catch (Inappropriate_Search o)
            {
                System.out.println(o.getMessage());
            }

        }

        return BySong_Name;

    }

    public List<songs_list>filter_by_Artist_Name (String artist_Name,List<songs_list> songs) throws Inappropriate_Search
    {
        List<songs_list > Artist_Name = new ArrayList<>();
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("ID         SONG NAME             ARTIST                       ALBUM                        GENRE              " );
       // System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (songs_list songs1 : songs)
        {
            if (songs1.getArtist().equalsIgnoreCase(artist_Name))
            {
                Artist_Name.add(songs1);
            }

            Artist_Name.sort((o1, o2) ->
            {
                return o1.getArtist().compareToIgnoreCase(o2.getArtist());
            });

        }
        try {
            if (Artist_Name.isEmpty())
            {
                throw new Inappropriate_Search("NO song is found by this Search" ) ;
            }
        }
        catch (Inappropriate_Search o)
        {
            System.out.println(o.getMessage());
        }
    return Artist_Name;
    }

    public List<songs_list> filter_by_genre(String genre,List<songs_list> songs) throws Inappropriate_Search
    {
        List<songs_list> ByGenre = new ArrayList<>();
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("ID         SONG NAME             ARTIST                       ALBUM                        GENRE              " );
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (songs_list songs1 : songs)
        {
            if (songs1.getGenre().equalsIgnoreCase(genre))
            {
                ByGenre.add(songs1);
            }
            ByGenre.sort((o1, o2) ->
            {
                return o1.getGenre().compareToIgnoreCase(o2.getGenre());
            });

        }
        try {
            if (ByGenre.isEmpty())
            {
                throw new Inappropriate_Search("NO song is found by this Search" ) ;
            }
        }
        catch (Inappropriate_Search o)
        {
            System.out.println(o.getMessage());
        }

        return ByGenre;
    }

    public List<songs_list> filter_by_Album_Name (String album_Name, List<songs_list> songs) throws Inappropriate_Search
    {
        List<songs_list> By_album_Name = new ArrayList<>();
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("ID         SONG NAME             ARTIST                       ALBUM                             GENRE              " );
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (songs_list songs1 : songs)
        {
            if (songs1.getAlbum_Name().equalsIgnoreCase(album_Name))
            {
                By_album_Name.add(songs1);
            }

            By_album_Name.sort((o1, o2) ->
            {
                return o1.getAlbum_Name().compareToIgnoreCase(o2.getAlbum_Name());
            });
        }

        return By_album_Name;
    }


        }


;