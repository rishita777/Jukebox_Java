package main_jukebox_portal;
import java.sql.*;
import java.util.Scanner;

import Custom_Exception.Incorrect_Email;
import Custom_Exception.Incorrect_Password;

public class checking_user
{
    Connection con;
    Scanner sc=new Scanner(System.in);
    int get_id;
    public static int CheckUser(Connection con, String user_Name)
    {
        int found = 0;
        try {
            String sql = "Select * from checking_user where user_Name=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user_Name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                found++;
                break;
            }
        }
        catch (SQLException e) {System.out.println(e);}
        if (found == 1) {
            return 1;
        } else
            return 0;
    }

    public int signUp(Connection con, String user_Name, String password, String email, long phone_Number) throws Incorrect_Password,Incorrect_Email
    {
        {
            int exist = CheckUser(con, user_Name);
            if (exist == 1)
            {
                System.out.println(".............User Already Exist...................... \n.....................");
                return exist;
            }

        }
        try {
            String sql = "insert into checking_user(user_Name,password,email,phone_Number) values(?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            // statement.setInt(1,  user_Id);
            statement.setString(1, user_Name);        //giving value to database
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setLong(4, phone_Number);
            statement.execute();


           System.out.println(" ------------------------------------------------------------------☺---✅--Successfully REGISTERED---✅-☺--------------------------------------");
            System.out.println();

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public boolean Login(Connection conn, String user_Name, String password) {
        Scanner src = new Scanner(System.in);
        try {
            String sql = "select user_Id from checking_user where user_Name=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
           statement.setString(1, user_Name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
               System.out.println("\n   YOUR USER ID IS :=    "+resultSet.getInt(1));
                System.out.println("\n  Welcome user:  \uD83E\uDD17 "+  user_Name.toUpperCase());
            }
            else
            {
                System.out.println("  ......                 ☹☹️ SORRY !!  ☹ ☹️ Your Entered Wrong User Name or  Password  try again ");


                return false;
            }
        } catch (SQLException e) {
            System.out.println("-----------------------" + e.getMessage() + "---------------------------------------------");
        }
        return true;
    }

    public void reset_password(String user_name,String email,String password,Connection con)
    {
        try {
            String sql = "update checking_user set password=? where user_Name=? and email=?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, user_name);
            statement.setString(2,email);
            statement.setString(3, password);

            statement.executeUpdate();
        }

            catch (SQLException e)
  {
            throw new RuntimeException(e);
        }
    }
}





