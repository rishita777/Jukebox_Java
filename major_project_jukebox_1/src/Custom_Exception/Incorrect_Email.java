package Custom_Exception;

public class Incorrect_Email extends Exception
{
    public Incorrect_Email(String msg)
    {
        super(msg);
    }
}
