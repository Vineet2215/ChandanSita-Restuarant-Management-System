import java.io.*;
import javax.swing.*;
class BillPage
{
    static JLabel J[] = new JLabel[50];
    static int i=0;
    static void billed(String fname) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fname));
        String str = "";
        String s="hello";

        while (s!=null)
        {
            s=br.readLine();
            str+="\n";
            str+=s;
            J[i] = new JLabel(s);
            J[i].setHorizontalTextPosition(JLabel.CENTER);
            i++;
        }

        System.out.println(str);
        br.close();
        new frame();
    }
}

class frame extends JFrame
{
    static JLabel JN[] = BillPage.J;

    frame()
    {
        this.setVisible(true);
        for (int k=0; k<BillPage.i; k++)
        {
            JN[k].setBounds(0, 100+k*15, 1700, 15);
            JN[k].setHorizontalAlignment(JLabel.CENTER);
            this.add(JN[k]);
        }
        this.setExtendedState(MAXIMIZED_BOTH);
    }
}
