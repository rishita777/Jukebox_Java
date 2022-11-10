package Test_CASES;

import Custom_Exception.Inappropriate_Search;
import com.Implementation.songs_list_Impl;
import sql_connection_jdbc.sql_Connect;
import data_entities.songs_list;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class jukebox_Testcase
{
    sql_Connect getConnection = new sql_Connect();
    Connection con = getConnection.sql_get_Connection();

    songs_list_Impl songs_list1_=new songs_list_Impl();
    List<songs_list> songs=songs_list1_.AllSongInList(con);

    @org.junit.jupiter.api.Test
    void filter_by_Song_Name() throws SQLException,ClassNotFoundException, Inappropriate_Search {
        List<songs_list> byname= songs_list1_.filter_by_Song_Name("Tera-saath-ho-mein", songs);
        assertEquals(1,byname.size());
    }

    @org.junit.jupiter.api.Test
    void filter_by_Artist_Name()throws SQLException,ClassNotFoundException,Inappropriate_Search {

        List<songs_list> artist= songs_list1_.filter_by_Artist_Name("Arijit-Singh",songs);
        assertEquals(3,artist.size());
    }

    @org.junit.jupiter.api.Test
    void filter_by_genre() throws SQLException,ClassNotFoundException,Inappropriate_Search
    {
        List<songs_list> genre= songs_list1_.filter_by_genre("Romantic",songs);
        assertEquals(3,genre.size());

    }

    @org.junit.jupiter.api.Test
    void filter_by_Album_Name() throws SQLException,ClassNotFoundException,Inappropriate_Search {
        List<songs_list> album= songs_list1_.filter_by_Album_Name("Kedarnath",songs);
        assertEquals(1,album.size());
    }

    
}