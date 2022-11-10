package Custom_Exception;

public class Incorrect_Password extends Exception
{

    public Incorrect_Password(String msg)
    {
        super(msg);
    }

}
