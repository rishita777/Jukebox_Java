package com.Implementation;
import java.sql.*;
import Custom_Exception.*;
public class Playlist_Impl
{
    Connection con;
    public void display_playlist( int user_id,Connection con)
    {
        try
        {
            boolean found = false;
            String sql = "Select * from playlist where user_id =?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,user_id);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\u001B[34m"+" PLAYLIST ID        PLAYLIST NAME "    );
            System.out.println("\u001B[34m"+" ----------------------------------------------");
            while (resultSet.next())
            {
               found=true;
                System.out.format(" %-15s %-25s  %n",resultSet.getInt(1),
                        resultSet.getString(2));
            }
            if(found==false)
                System.out.println("no data found for "+user_id);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

   public void adding_song_to_playlist(int playlist_Id,int song_id,int user_id,Connection con)
   {
        try {

            String sql = "insert into  playlist_display values (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlist_Id);
            statement.setInt(2, song_id);
            statement.setInt(3, user_id);
            statement.executeUpdate();
            System.out.println("     ✅ ✅     Records have been inserted   ✅ ✅     ");
        }
        catch (SQLException e)
        {
            System.out.println("  Sorry you cant add this song to playlist !!  It already Exist  ");
        }
    }

    public void Playlist_Songs(int playList_Id,Connection con)
    {
        try {
            String sql = "select * from songs_list where song_Id in(select song_Id from playlist_display where playlist_Id=?)";
            PreparedStatement statement1 = con.prepareStatement(sql);
            statement1.setInt(1, playList_Id);
            ResultSet Play_song = statement1.executeQuery();
            while (Play_song.next()) {
                System.out.format("%-5s %-25s %-25s %-25s %-10s  %n", Play_song.getInt(1),
                        Play_song.getString(2), Play_song.getString(3), Play_song.getString(4),
                        Play_song.getString(5));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

       public void CreatePlayList(String playlist_Name, int user_Id,Connection con)
       {
        try {

        String sql = "insert into  playList(playlist_Name,user_Id) values  (?,?)";

        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, playlist_Name);
        statement.setInt(2, user_Id);
        statement.executeUpdate();
        System.out.println("  ✅  ☺ Records have been inserted  ☺ ✅");

        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        }
    public void delete_songs_from_playlist(int song_id,Connection con)
    {
        try{
            String sql="delete from playlist_display where song_id =?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,song_id);
            statement.executeUpdate();

            System.out.println("  ✅  ☺   Song is Deleted From PLaylist     ☺ ✅");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        }


