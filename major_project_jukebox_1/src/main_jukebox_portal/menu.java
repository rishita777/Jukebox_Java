package main_jukebox_portal;
import Custom_Exception.Inappropriate_Search;
import com.Implementation.Playlist_Impl;
import com.Implementation.songs_list_Impl;
import data_entities.songs_list;
import sql_connection_jdbc.sql_Connect;
import streaming_music.Play_Song;
import streaming_music.song_url;

import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
public class menu extends main_portal
{

    public static int menumethod()
    {
        Scanner sc=new Scanner(System.in);
String yellow= "\u001B[33m";
        System.out.println( "\nPlease Select from Following");
        System.out.println("\n1) Display All Available Songs \uD83C\uDFB5 ");
        System.out.println("2) Filter by Artist Name  ");
        System.out.println("3) Filter by Album Name ");
        System.out.println("4) Filter by Genre   ");
        System.out.println("5) Filter by Song Name");
        System.out.println("6) Display  Your playlist ");
        System.out.println("7) Create Your own Playlist ");
        System.out.println("8) Add Song to Your Existing Playlist ");
        System.out.println("9) Delete song from Playlist");
        System.out.println("10) Exit "   );

         int option = sc.nextInt();
        return option;
    }

public static void song_title_heading()
{
    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    System.out.println("ID        SONG NAME               ARTIST                     ALBUM                 GENRE                      ");
    System.out.println("------------------------------------------------------------------------------------------------------------------------");
}


public static void method_case7()
{
    Scanner sc = new Scanner(System.in);

    sql_Connect getConnection = new sql_Connect();
    Connection con = getConnection.sql_get_Connection();
    song_url url1 = new song_url();
    String path1;
    songs_list_Impl music_obj = new songs_list_Impl();
    Playlist_Impl pl_obj=new Playlist_Impl();

    System.out.println("Enter your user Id");
    int user_id = sc.nextInt();
    System.out.println(" Create  your Playlist Name ");
    System.out.println("----------------------------\n");
    String play_name = sc.next();
    System.out.println();
    pl_obj.CreatePlayList(play_name, user_id, con);   //calling method
    System.out.println("     ---  Playlist created Sucessfully   ----");
    pl_obj.display_playlist(user_id, con);

    System.out.println("\n Enter playlist id on which you want to add song");
    int pl_id = sc.nextInt();
    music_obj.showAllSongs(con);

    System.out.println("Enter Song Id to add song to your playlist");
    int song_id = sc.nextInt();
    pl_obj.adding_song_to_playlist(pl_id, song_id, user_id, con);
    System.out.println("     --- Songs Added Succesfully to playlist  ----");

    System.out.println("\nPLAYLIST NAME :- " +play_name.toUpperCase()+ "      \n\u001B[33m HAS THESE SONGS U CAN CHOOSE FROM THEM\n");
    song_title_heading();
    pl_obj.Playlist_Songs(pl_id,con);

}

public static void case8()
    {
        Scanner sc = new Scanner(System.in);

        sql_Connect getConnection = new sql_Connect();
        Connection con = getConnection.sql_get_Connection();
        checking_user userCheck = new checking_user();
        song_url url1 = new song_url();
        String path1;
        Playlist_Impl pl_obj=new Playlist_Impl();
        songs_list_Impl music_obj = new songs_list_Impl();


        System.out.println(" Enter your User id to add song to your Existing Playlist ");
        int user_id = sc.nextInt();
        pl_obj.display_playlist(user_id, con);
        System.out.println();
        //music_obj.showAllSongs(con);
        System.out.println("Enter playlist id on which you want to add");
        int pl_id = sc.nextInt();
        music_obj.showAllSongs(con);

        System.out.println("Enter  Id of song which you want to  add  in your  playlist");
        int song_id = sc.nextInt();
        pl_obj.adding_song_to_playlist(pl_id, song_id, user_id, con);
        song_title_heading();
        pl_obj.Playlist_Songs(pl_id,con);              //object of class plylist_impl in package implementaion
        System.out.println("     --- Songs Added to Existing Playlist Succesfully   ----");


       // System.out.println("\nEnter Your User id TOinsert song to playlist ");
    }

public static void calling_objects()
{

}

}
