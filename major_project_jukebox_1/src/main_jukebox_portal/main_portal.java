package main_jukebox_portal;
import sql_connection_jdbc.sql_Connect;
import streaming_music.*;
import com.Implementation.songs_list_Impl;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import data_entities.songs_list;
import com.Implementation.Playlist_Impl;
import Custom_Exception.Incorrect_Email;
import Custom_Exception.Inappropriate_Search;
import Custom_Exception.Incorrect_Password;
import static main_jukebox_portal.menu.*;
public class main_portal
{
    public static void main(String[] args) throws Incorrect_Email,Inappropriate_Search,Incorrect_Password
    {
        Scanner sc = new Scanner(System.in);

        sql_Connect getConnection = new sql_Connect();
        Connection con = getConnection.sql_get_Connection();
        checking_user userCheck = new checking_user();
        song_url url1 = new song_url();
        String path1;

        songs_list_Impl music_obj = new songs_list_Impl();
        Playlist_Impl pl_obj=new Playlist_Impl();

        int b,flag=1,option,g,h;
        System.out.println();
        System.out.println( "\u001B[35m"+  "                                                                            ☺☺☺️    WELCOME TO  JUKEBOX SYSTEM  \uD83D\uDE03 ☺☺☺              ");
        System.out.println("\u001B[34m"+ "Press :-    1)   To Continue ");
        System.out.println("            0)   To Exit "+"\u001B[0m");
        option = sc.nextInt();

        while (option == 1)
        {
            System.out.println("\u001B[35m" + "==========        Welcome to JUKEBOX PORTAL       =============" + "\u001B[33m");
            System.out.println(" \n \n Enter FROM THE FOLLOWING :- \n  1) LOG IN ");
            System.out.println("  2) SIGN UP" + "\u001B[34m");
            int ch = sc.nextInt();
            int k = 1;
            switch (ch)                 //1st switchcase
            {
                case 1:
                {
                    System.out.println(" Enter user Name");
                    String name = sc.next();
                    System.out.println("Enter Your Password  ");
                    String password = sc.next();

                    boolean a = userCheck.Login(con, name, password);
                    if (a == false)
                    {
                        System.out.println("  DO YOU WANT TO RESET YOUR PASSWORD   IF YES \nPRESS 1 TO CONTINUE :-");
                        int chh=sc.nextInt();
                        while(chh==1)
                        {
                            System.out.println( "  Enter your user name");
                            String user_name=sc.next();
                            System.out.println("  Enter your registered emailid ");
                            String email=sc.next();

                            System.out.println("  Enter your New Paswword ");
                            String new_password=sc.next();

                            userCheck.reset_password(user_name,email,new_password,con);
                            System.out.println("        PASSWORD RESET SUCCESSFULLY");
                            break;
                        }
                        option=0;
                        break;
                    }
                    while (k == 1)
                    {
                        int cho = menumethod();
                        switch (cho)                        //inner switch 2nd case
                        {
                            case 1:                                  // START OF CASE 1 TO DISPLAY ALL AVAILABLE SONGS
                            {
                                music_obj.showAllSongs(con);
                                System.out.println();
                                System.out.println();

                                while (flag == 1)
                                {
                                    System.out.println("\n Enter Song Id  which you want to listen  ▶️:-");
                                    path1 = url1.showSongPath(con);               //object of song_url
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) to PLAY ANOTHER SONG  ▶️ " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }

                                System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  Enter 1)   TO GO TO MAIN MENU          " +
                                        "      2) TO EXIT           ");
                                int opp1 = sc.nextInt();
                                if (opp1 == 1)
                                {
                                    k = 1;                                                                                          //2nd while loop variable change
                                    option=0;                                                                                           //1 st while loop variable
                                    continue;
                                }
                                break;
                            }                                           //end of case 1

                            case 2:                           // START OF CASE 3 TO FILTER SONGS BY SONG BY ARTIST NAME
                            {
                          try{
                                System.out.println("Enter Artist Name ");
                                String artist_name = sc.next();
                                List<songs_list> list = music_obj.AllSongInList(con);
                                List<songs_list> song = music_obj.filter_by_Artist_Name(artist_name, list);
                                for (songs_list list1 : song) {
                                    System.out.format("%-10s %-20s %-28s %-30s  %-20s  %n", list1.getSongs_id(),
                                            list1.getSong_Name(), list1.getArtist(), list1.getAlbum_Name(), list1.getGenre());
                                }
                                if (song.isEmpty()) {throw new Inappropriate_Search("--------------------------");

                                }

                            }
                          catch(Inappropriate_Search e){
                                System.out.println(e.getMessage());
                              System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                              int b1= sc.nextInt();
                              if (b1 == 1)
                              {
                                  k = 1;option=0;
                                  continue;
                              }
                                break;
                            }
                                while (flag == 1) {
                                    System.out.println("\n Enter Song Id  which you want to listen  ▶️:-");
                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) to PLAY ANOTHER SONG  ▶️ " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                       break;
                                    }
                                }
                                System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                int b2 = sc.nextInt();
                                if (b2 == 1)
                                {
                                    k = 1;option=0;
                                    continue;
                                }
                                break;
                            }                                       // END OF CASE 2 TO FILTER SONGS BY SONG BY ARTIST NAME

                            case 3:                            // START OF CASE 3 TO FILTER SONGS BY SONG BY ALBUM NAME
                            {
                                try                //try block start
                                {
                                System.out.println("Enter Album Name ");
                                String album_Name = sc.next();
                                List<songs_list> list = music_obj.AllSongInList(con);
                                List<songs_list> song = music_obj.filter_by_Album_Name(album_Name, list);
                                for (songs_list list1 : song) {
                                    System.out.format("%-10s %-20s %-28s %-25s  %-20s %-20s %n", list1.getSongs_id(),
                                            list1.getSong_Name(), list1.getArtist(), list1.getAlbum_Name(), list1.getGenre(), list1.getDuration());
                                }
                                    if (song.isEmpty()) {
                                        throw new Inappropriate_Search("No Search Data Found");
                                    }

                                }        //end of try block
                                catch(Inappropriate_Search e)       //start of catch block
                                {
                                    System.out.println(e.getMessage());
                                    System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  \nEnter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                    g = sc.nextInt();
                                    if (g == 1)
                                    {
                                        k = 1;option=0;
                                        continue;
                                    }
                                    break;
                                }                    //start of catch block

                                while (flag == 1) {
                                    System.out.println("\n Enter Song Id  which you want to listen  ▶️:-");
                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) to PLAY ANOTHER SONG  ▶️ " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }
                                System.out.println(" DO YOU WANT TO GO BACK TO MAIN MENU \n Enter 1)   TO GO TO MAIN MENU    " +
                                        "            2) TO EXIT           ");
                                h= sc.nextInt();
                                if (h == 1)
                                {
                                    k = 1;option=0;
                                    continue;
                                } else if (h==2) {break;}
                                System.out.println(                  "......Sorry Wrong Input  .......   ");
                                break;
                            }                         // END OF CASE 3 TO FILTER SONGS BY SONG BY ALBUM NAME

                            case 4:                             //starting of case 4 to filter songs by Genre
                            {
                                try{
                                System.out.println("Enter Genre ");
                                String genre = sc.next();
                                List<songs_list> list = music_obj.AllSongInList(con);
                                List<songs_list> song = music_obj.filter_by_genre(genre, list);
                                for (songs_list list1 : song) {
                                    System.out.format("%-10s %-20s %-28s %-30s  %-20s %n", list1.getSongs_id(),
                                            list1.getSong_Name(), list1.getArtist(), list1.getAlbum_Name(), list1.getGenre(), list1.getDuration());
                                }
                                if (song.isEmpty()) {
                                    throw new Inappropriate_Search("No Search Data Found");
                                }

                            }
                                catch(Inappropriate_Search e)
                                {
                                System.out.println(e.getMessage());
                                        System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                        int ii = sc.nextInt();
                                        if (ii == 1)
                                        {
                                            k = 1;option=0;
                                            continue;
                                        }
                                        break;
                            }
                                while (flag == 1) {
                                    System.out.println("\n Enter Song Id which you want to play:  ▶️:-");
                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) to PLAY ANOTHER SONG   " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }
                                System.out.println("  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                               int  opp4 = sc.nextInt();
                                if (opp4 == 1) {
                                    k = 1;option=1;
                                    continue;
                                }
                                k = 0;
                                break;
                            }                                      //end of case4 to filter songs by genre
                            case 5:
                            {
                                try{


                                System.out.println("Enter Song Name ");
                                String songName = sc.next();

                                    List<songs_list> list = music_obj.AllSongInList(con);

                                List<songs_list> song = music_obj.filter_by_Song_Name(songName, list);
                                for (songs_list list1 : song) {
                                    System.out.format("%-10s %-20s %-28s %-30s  %-25s %n", list1.getSongs_id(),
                                            list1.getSong_Name(), list1.getArtist(), list1.getAlbum_Name(), list1.getGenre(), list1.getDuration());
                                }
                                if (song.isEmpty()) {
                                    throw new Inappropriate_Search("No Search Data Found");
                                }

                            } catch(Inappropriate_Search e){
                                System.out.println(e.getMessage());
                                    System.out.println("  DO YOU WANT TO GO BACK TO MAIN MENU  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                   int  ff = sc.nextInt();
                                    if (ff == 1)
                                    {
                                        k = 1;option=1;
                                        continue;
                                    }
                                    break;
                            }
                                while (flag == 1) {
                                    System.out.println("\n Enter Song Id which you want to listen: -▶️-");
                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) To LISTEN ANOTHER SONG   " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }

                                System.out.println("  Enter \n1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                              int  bb = sc.nextInt();
                                if( bb == 1) {
                                    k = 1;option=0;
                                    continue;
                                }
                                break;
                            }
                            case 6: {

                                System.out.println("Enter your user Id");
                                int user_id = sc.nextInt();
                                pl_obj.display_playlist(user_id, con);
                                System.out.println("***************************");
                                System.out.println("Enter PlayList Id");
                                int playlist_id=sc.nextInt();
                                System.out.printf("%-5s%-30s%-25s%-25s%-15s %n","ID","SONG NAME","ARTIST","ALBUM","GENRE");
                                System.out.println("=========================================================================================");
                                pl_obj.Playlist_Songs(playlist_id,con);           // object of

                                while (flag == 1) {
                                    System.out.println("\n Enter Song Id which want to listen ▶️:-");
                                path1 = url1.showSongPath(con);
                                Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) To PLAY ANOTHER SONG   " +
                                            "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }

                                System.out.println("  Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                int opp6 = sc.nextInt();
                                if (opp6 == 1) {   k = 1;continue;  }
                              else if(opp6==2){break;}
                              break;

                            }
                            case 7: {
                                method_case7();
                                 flag=1;
                                while (flag == 1)
                                {  System.out.println("\n Enter Id  of song from playlist which you want to listen ▶️:-");

                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) To PLAY ANOTHER SONG  from playlist   " + "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }
                                System.out.println("  \nEnter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                b = sc.nextInt();
                                if (b == 1) {k = 1;option=0;
                                    continue;}
                                k = 0;
                                break;
                            }
                            case 8: {
                                case8();   //method  calling
                                while (flag == 1)
                                {  System.out.println("\n Enter Song Id which you want to listen  ▶️  :-");
                                    path1 = url1.showSongPath(con);
                                    Play_Song.playSong(path1);
                                    System.out.println("  PRESS  \n 1) To PLAY ANOTHER SONG ▶️  " + "         0) TO GO TO MAIN MENU  ");
                                    flag = sc.nextInt();
                                    if(flag==0)
                                    {
                                        break;
                                    }
                                }
                                 System.out.println("  \n Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                               int opp8 = sc.nextInt();
                                if (opp8 == 1) {
                                    k = 1;
                                    continue;
                                }
                                k = 0;
                                break;
                            }

                            case  9:
                            {
                                System.out.println(" Enter your User id to Delete song from your Existing Playlist ");
                                int user_id = sc.nextInt();
                                System.out.println("   \n     .... Your Existing Playlist ....  ");
                                pl_obj.display_playlist(user_id, con);
                                System.out.println("\n Enter playlist id from which you want to Delete the song ");
                                int pl_id = sc.nextInt();
                                System.out.println("   \n     .... Your Songs are ....  ");
                                pl_obj.Playlist_Songs(pl_id,con);
                                System.out.println("Enter  Id of song which you want to  Delete from your  playlist");
                                int song_id = sc.nextInt();
                                pl_obj.delete_songs_from_playlist(song_id,con);

                                System.out.println(" \n    ---    Songs Deleted from  Existing Playlist Succesfully     ----");
                               System.out.println("  \n  YOUR PLAYLIST CONSITS THESE SONGS NOW       ");
                                song_title_heading();
                                pl_obj.Playlist_Songs(pl_id,con);
                                System.out.println("  \n Enter 1)   TO GO TO MAIN MENU                2) TO EXIT           ");
                                int opp9 = sc.nextInt();
                                if (opp9 == 1) {
                                    k = 1;
                                    continue;
                                }
                                k = 0;
                                break;
                            }

                            case 10: {
                                System.out.println("\n******------\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17-----------THANK YOU--TO VISIT JUKEBOX -------..\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17..........");
                                break;
                            }
                            default:
                                System.out.println("  =====   WRONG INPUT  !!  CHOOSE CORRECT OPTION ====   ");
                        }         //exit of inner switch
                        break;
                    }             //end of inner while
                    break;
                }
                // end of 1st case of outer switch

                case 2:
                {
                    System.out.println("Enter Details TO Signup");
                    System.out.println("-----------------------");
                    System.out.println("\nEnter User Name");
                    String name1 = sc.next();

                    System.out.println("   Enter User Password  must any contain special character ");
                    String password1 = sc.next();

                    try {
                        boolean s = (password1.contains("_") || (password1.contains("#"))|| (password1.contains("&"))
                                || (password1.contains("1"))|| (password1.contains("$"))
                                || (password1.contains("2")) || (password1.contains("3")) || (password1.contains("4"))
                                || (password1.contains("5")) || (password1.contains("6")) || (password1.contains("7"))
                                || (password1.contains("8")) || (password1.contains("9")) || (password1.contains("9")));
                        if (s == false) {
                            throw new Incorrect_Password(" WRONG INPUT Password Must Contain  special character [# or _ ] ......tRY AGAIN TO SIGNUP");
                        }
                    } catch (Incorrect_Password e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter Email ");
                    String email = sc.next();
                    try {
                        boolean pass_exp = (email.contains("@") && (email.contains(".")));
                        if (pass_exp == false) {
                            throw new Incorrect_Email("\\u001B[31m Email  Must Contain Special Character [@,.,_] and one Numeric Value");
                        }

                    } catch (Incorrect_Email e) {
                        System.out.println(e.getMessage());

                    }
                    System.out.println("Enter Mobile Number Minimum 10 Digit Required");
                    long phone_Number = sc.nextLong();
                    userCheck.signUp(con, name1, password1, email, phone_Number);
                    System.out.println("PRESS \n 1) To  HOME PAGE  \n 0) To EXIT");
                    b = sc.nextInt();
                    if (b == 1)
                    {
                        option=1;
                        continue;
                    }
                    break;

                }
                default:
                    System.out.println("     ------    !!!  WRONG   INPUT CHOICE     !!!   CHOOSE CORRECT ONE  ......    ");
            }
            option=0;
        }
        if (option==0) {
            System.out.println("---------- \uD83E\uDD17\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17-   THANKS FOR VISITING  -----------------      \uD83E\uDD17\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17-          ---");
            System.out.println("\n                                    Visit us again                                    ");
        }

        else
        System.out.println("          ======================================================================================================================   ");


        }

    }


