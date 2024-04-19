import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class socket
{
    private Socket socket;
    private PrintWriter out;
 
    public socket(ArrayList<String> bill, String name)
    {
        try
        {
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (UnknownHostException u)
        {
            System.out.println(u);
            return;
        }
        catch (IOException i)
        {
            System.out.println(i);
            return;
        }


        System.out.println(bill);
        System.out.println(name);
        out.println(name);
        out.println(bill.size());
        for (String item : bill)
        {
            out.println(item);
        }
 
        try
        {
            out.close();
            socket.close();
        }
        catch (IOException i)
        {
            System.out.println(i);
        }
    }
}