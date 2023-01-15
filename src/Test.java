import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test extends JFrame
{
    int processesListSize = 5;
    int height = 768;
    int width = 1024;
    ImageIcon icon = new ImageIcon("appTimerIcon.png");
    JButton button = new JButton();
    public Test()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocation(0,0);
        setLayout(null);

        JLabel label1 = new JLabel("label1");
        label1.setBounds(10,10,100,20);
        add(label1);

        JButton button1 = new JButton("button1");
        button1.setBounds(200,10,100,20);
        add(button1);

        JLabel label2 = new JLabel("label2");
        label2.setBounds(150,40,100,20);
        add(label2);

        JButton button2 = new JButton("button2");
        button2.setBounds(300,40,100,20);
        add(button2);

//        JLabel label3 = new JLabel("label3");
//        //label3.setBounds(350,70,100,20);
//        add(label3);
//
//        JButton button3 = new JButton("button3");
//        //button3.setBounds(400,70,100,20);
//        add(button3);

        //setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = formatter.format(date);
        System.out.println(strDate);
    }
}
