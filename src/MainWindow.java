import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame
{
    ArrayList<String> processesList = AppTimer.getProcesses();
    int height = 761;
    int width = 669;
    ImageIcon icon = new ImageIcon("appTimerIcon.png");
    JButton button = new JButton();

    public MainWindow()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(null);
        this.setResizable(false);

        this.setTitle("App Timer");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Design.backColor);

        Border border = BorderFactory.createLineBorder(Design.backColor);


        JLabel title = new JLabel("AppTimer");
        title.setPreferredSize(new Dimension(250,100));
        title.setFont(Design.titleFont);
        title.setForeground(Design.fontColor);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        //title.setBorder(border);
        title.setBounds(width/2-100, 50, 200, 50);

        button = new JButton();
        button.setUI(new MyButtonUI());
        //button.addActionListener(e -> button.setBackground(Color.red));
        button.setFocusable(false);
        button.setForeground(Design.fontColor);
        button.setBackground(Design.foreColor);
        button.setBorder(border);

        button.setFont(Design.titleFont);
        button.setBounds(width/2-150, 150, 300, 50);
        button.setText("Show processes");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        JPanel showProcessesPanel = new JPanel();
        showProcessesPanel.setBounds(width/2-200,225,400,processesList.size()*35);
        showProcessesPanel.setBackground(Design.foreColor);
        showProcessesPanel.setLayout(new GridLayout(processesList.size(),processesList.size()));   //cols = amount of processes


        for (int i = 0; i < processesList.size(); i++) {            //pętla do dodawania procesów
            final int index = i;

            JButton[] buttons = new JButton[processesList.size()];
            buttons[i] = new JButton();
            buttons[i].setUI(new MyButtonUI());
            buttons[i].setFocusable(false);
            buttons[i].setForeground(Design.fontColor);
            buttons[i].setBackground(Design.foreColor);
            buttons[i].setBorder(border);
            buttons[i].setFont(Design.titleFont);
            buttons[i].setBounds(width/2-150, 150, 300, 50);
            buttons[i].setText(processesList.get(i));

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AppTimer timer = new AppTimer(buttons[index].getText());
                    timer.timeCounter();
                    timer.sendToFile();
                }
            });

            showProcessesPanel.add(buttons[i]);
        }

        this.add(title, BorderLayout.NORTH);
        this.add(button);
        this.add(showProcessesPanel);

        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        MainWindow win = new MainWindow();
        System.out.println(AppTimer.getProcesses().size());
        System.out.println(AppTimer.getProcesses().get(2));
    }
}
