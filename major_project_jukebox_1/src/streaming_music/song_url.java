package streaming_music;
import java.sql.*;
import java.util.Scanner;
public class song_url {
    Connection con;
    String path;
    int get_id;
    Scanner sc = new Scanner(System.in);

    public String showSongPath(Connection con) {
        try {

            int choice = sc.nextInt();
            String song_Name = "select * from songs_list where song_Id=?";
            PreparedStatement statement1 = con.prepareStatement(song_Name);
            statement1.setInt(1, choice);
            ResultSet url = statement1.executeQuery();
            while (url.next()) {
                path = url.getString(7);
            }
        } catch (SQLException e) {
            System.out.println("Table not showing");
        }
        return path;
    }

    public int get_user_id(Connection con) {
        try {
            int ch1 = sc.nextInt();
            String user_Id = "select user_Id from checking_user";
            PreparedStatement statement1 = con.prepareStatement(user_Id);

            statement1.setInt(1, ch1);
            ResultSet u_id = statement1.executeQuery();
            while (u_id.next()) {
                get_id = u_id.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Table not showing");
        }
        return  get_id;
    }
}
