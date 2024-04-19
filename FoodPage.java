import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

class FoodFrame extends JFrame implements ActionListener
{
    JButton JB[] = new JButton[15];
    int totalcosts[] = new int[15];
    JLabel J;
    
    static String foodnames[] = new String[15];
    static int foodprices[] = new int[15];
    {
        foodnames[0] = "Burger";
        foodnames[1] = "Cake";
        foodnames[2] = "Noodles";
        foodnames[3] = "Water";
        foodnames[5] = "Chicken";
        foodnames[6] = "Fries";
        foodnames[7] = "Nachoes";
        foodnames[8] = "Lemonade";
        foodnames[10] = "Pizza";
        foodnames[11] = "Samosa";
        foodnames[12] = "Tacos";
        foodnames[13] = "Soda";

        foodprices[0] = 100;
        foodprices[1] = 70;
        foodprices[2] = 60;
        foodprices[3] = 20;
        foodprices[5] = 120;
        foodprices[6] = 70;
        foodprices[7] = 50;
        foodprices[8] = 15;
        foodprices[10] = 120;
        foodprices[11] = 30;
        foodprices[12] = 50;
        foodprices[13] = 20;
    }

    FoodFrame()
    {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("ChandanSita's Online Portal");
        this.setResizable(false);
        
        ImageIcon FAVICON = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/favicon_io/icondis.png");
        this.setIconImage(FAVICON.getImage());
        this.getContentPane().setBackground(new Color(0xFFAAAA));

        for (int i=0; i<15; i++)
        {
            JB[i] = new JButton();
            this.add(JB[i]);

            totalcosts[i]=0;

            if (i!=4 && i!=14 && i!=9)
            {   
                JB[i].setBounds(100+(i%3)*600, 50+(i%5)*150, 300, 100);
                JB[i].setBorder(BorderFactory.createRaisedBevelBorder());
                JB[i].setFont(new Font("ROMALIKA", Font.ITALIC, 25));
            }
            else
            {
                JB[i].setBounds(100+(i%3)*600, 50+(i%5)*150, 300, 200);
                JB[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                JB[i].setFont(new Font("ROMALIKA", Font.BOLD, 50));
            }

            JB[i].addActionListener(this);
            JB[i].setFocusable(false);
            JB[i].setHorizontalTextPosition(JButton.CENTER);
            JB[i].setVerticalTextPosition(JButton.BOTTOM);
            JB[i].setForeground(Color.DARK_GRAY);
            JB[i].setBackground(Color.LIGHT_GRAY);
        }
        
        ImageIcon I0 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/burgir.png");
        JB[0].setIcon(I0);
        JB[0].setText("BURGER - 100 RS");

        ImageIcon I1 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/cake.png");
        JB[1].setIcon(I1);
        JB[1].setText("CAKE - 70 RS");

        ImageIcon I2 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/nodl.png");
        JB[2].setIcon(I2);
        JB[2].setText("NOODLES - 60 RS");
    
        ImageIcon I3 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/boh woh.png");
        JB[3].setIcon(I3);
        JB[3].setText("WATER - 20 RS");

        ImageIcon I5 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/chicken.png");
        JB[5].setIcon(I5);
        JB[5].setText("CHICKEN - 120 RS");
    
        ImageIcon I6 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/fries.png");
        JB[6].setIcon(I6);
        JB[6].setText("FRIES - 70 RS");
    
        ImageIcon I7 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/nacho.png");
        JB[7].setIcon(I7);
        JB[7].setText("NACHOES - 50 RS");
    
        ImageIcon I8 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/lemonade.png");
        JB[8].setIcon(I8);
        JB[8].setText("LEMONADE - 15 RS");
    
        ImageIcon I10 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/pizzaz.png");
        JB[10].setIcon(I10);
        JB[10].setText("PIZZA - 120 RS");

        ImageIcon I11 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/samosa.png");
        JB[11].setIcon(I11);
        JB[11].setText("SAMOSA - 30 RS");
    
        ImageIcon I12 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/taco.png");
        JB[12].setIcon(I12);
        JB[12].setText("TACO - 50 RS");
    
        ImageIcon I13 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/soda.png");
        JB[13].setIcon(I13);
        JB[13].setText("SODA - 20 RS");

        JB[14].setText("RESET!");
        JB[14].setForeground(Color.black);
        JB[14].setBackground(Color.yellow);

        ImageIcon I9 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/parcel.png");
        JB[9].setIcon(I9);
        JB[9].setText("PARCEL? :/");
        JB[9].setForeground(Color.black);
        JB[9].setBackground(Color.cyan);

        ImageIcon I4 = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/fnla.png");
        JB[4].setIcon(I4);
        JB[4].setForeground(Color.black);
        JB[4].setBackground(new Color(0xD15757));
        JB[4].setText("BILLING");

    }

    int finalsum=0;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //  9 is parcel, 14 is reset and 4 is purchase

        int index = 0;

        for (index=0; index<15; index++)
        {
            if (e.getSource()==JB[index] && index!=4 && index!=9 && index!=14)
            {
                totalcosts[index]++;
                JB[index].setBackground(Color.GREEN);
                JB[index].setText("Items In Cart: " + totalcosts[index] + " Item(s)!");
                JB[index].setFont(new Font("ROMALIKA", Font.ITALIC, 15));

                break;
            }
        }
        
        switch (index)
            {
                case 0:
                    finalsum+=100;
                    break;
                case 1:
                    finalsum+=70;
                    break;
                case 2:
                    finalsum+=60;
                    break;
                case 3:
                    finalsum+=20;
                    break;
                case 5:
                    finalsum+=120;
                    break;
                case 6:
                    finalsum+=70;
                    break;
                case 7:
                    finalsum+=50;
                    break;
                case 8:
                    finalsum+=15;
                    break;
                case 10:
                    finalsum+=120;
                    break;
                case 11:
                    finalsum+=30;
                    break;
                case 12:
                    finalsum+=50;
                    break;
                case 13:
                    finalsum+=20;
                    break;
                
                default:
                    break;
            }

        JB[4].setText("BILL: " + finalsum + " RS");
        
        if (e.getSource()==JB[9])
        {
            totalcosts[9]++;  
            //  also maybe change the sprite to package here! this will be awesome!
            
            if ((totalcosts[9]%2)==1)
            {
                JB[9].setBackground(Color.cyan);
                JB[9].setText("PARCELED! :)");
                JOptionPane.showMessageDialog(null, "Your Order will be parcelled!", "Parcel Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }

            else
            {
                JB[9].setBackground(Color.cyan);
                JB[9].setText("PARCEL? :/");
                JOptionPane.showMessageDialog(null, "Your Order will not be parcelled!", "Parcel Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (e.getSource()==JB[14])
        {
            String choices[] = new String[2];
            choices[0] = "Yes, Reset my Choices!";
            choices[1] = "No, let me keep my food!";
            ImageIcon but_why_tho = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/derp.png");

            int x = JOptionPane.showOptionDialog(null, "Are you sure that you want to reset your choices?", "Reset Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, but_why_tho, choices, 1);

            if (x==0)
            {
                for (int i=0; i<15; i++)
                {
                    if (i==4)
                    {
                        finalsum = 0;
                        JB[i].setText("BILLING");
                    }
                    else if (i==9)
                    {
                        JB[9].setBackground(Color.cyan);
                        JB[9].setText("PARCEL? :/");
                    }
                    else if (i==14)
                        ;
                    else
                    {
                        JB[i].setBackground(Color.LIGHT_GRAY);
                        JB[i].setFont(new Font("ROMALIKA", Font.ITALIC, 25));
                        totalcosts[i]=0;
                        
                        switch (i)
                        {
                            case 0:
                                JB[0].setText("BURGER - 100 RS");
                                break;
                            case 1:
                                JB[1].setText("CAKE - 70 RS");
                                break;
                            case 2:
                                JB[2].setText("NOODLES - 60 RS");
                                break;
                            case 3:
                                JB[3].setText("WATER - 20 RS");
                                break;
                            case 5:
                                JB[5].setText("CHICKEN - 120 RS");;
                                break;
                            case 6:
                                JB[6].setText("FRIES - 70 RS");
                                break;
                            case 7:
                                JB[7].setText("NACHOES - 50 RS");
                                break;
                            case 8:
                                JB[8].setText("LEMONADE - 15 RS");
                                break;
                            case 10:
                                JB[10].setText("PIZZA - 120 RS");
                                break;
                            case 11:
                                JB[11].setText("SAMOSA - 30 RS");
                                break;
                            case 12:
                                JB[12].setText("TACO - 50 RS");
                                break;
                            case 13:
                                JB[13].setText("SODA - 20 RS");
                                break;
            
                            default:
                                break;    
                        }
                    }
                }
            }
        }
        
        if (e.getSource()==JB[4])
        {
            dispose();
            String namefinal = Lgf.user_name;
            
            try
            {
                String filename = namefinal + ".txt";
                // File ff = new File(filename);
                System.out.println(filename);
                PrintWriter bw = new PrintWriter(new FileWriter(namefinal + ".txt"));
                int finalBill=0;
                ArrayList<String> bill = new ArrayList<>();

                bw.println("");
                bw.println("*****************************************************************************");
                bw.println("ChandanSita's");
                bw.println("*****************************************************************************");
                bw.println("\n");
                bw.println((Lgf.user_name).toUpperCase());
                bw.println("\n");

                for (int i=0; i<15; i++)
                {
                    if (i==4 || i==9 || i==14)
                        ;
                    else
                    {
                        String item = "";
                        if (totalcosts[i]!=0)
                        {
                            finalBill += totalcosts[i]*foodprices[i];
                            bw.println(foodnames[i] + "  -  " + totalcosts[i]*foodprices[i] + " RS TOTAL WITH " + (totalcosts[i]) + " QUANTITY");
                            item += foodnames[i];
                            item += ", ";
                            item += totalcosts[i];
                            item += ", ";
                            item += foodprices[i];
                            System.out.println(item);
                            bill.add(item);
                        }
                    }
                }

                bw.println("\n");
                bw.println("TOTAL BILL: " + finalBill + "/- RS");
                bw.println("\n");
                bw.println("*****************************************************************************");
                bw.println("Please Visit Again!");
                bw.println("*****************************************************************************");

                bw.close();
                
                // Showing the data to the bill page at the last !
                BillPage.billed(filename);
                
                // Sending to Server through Socket Programming
                // Now
                new socket(bill, namefinal); // Array list of item and username
            }
            catch (IOException except)
            {
                System.out.println("Error!");
            }
        }
    }
}


public class FoodPage
{
    public static void main(String[] args)
    {
        new FoodFrame();
    }
}