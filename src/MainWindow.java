import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame
{
//    @Override
//    public void run() {
//        AppTimer app = new AppTimer(chosenProcess);
//        app.timeCounter();
//        app.sendToFile();
//    }
    static String chosenProcess;

    static int height = 761;
    static int width = 669;
    ImageIcon icon = new ImageIcon("appTimerIcon.png");
    JButton button = new JButton();
    static Border border = BorderFactory.createLineBorder(Design.backColor);
    ArrayList<String> procList = AppTimer.getProcesses();

    public MainWindow()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(null);
        //this.setResizable(false);

        this.setTitle("App Timer");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Design.backColor);

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

        JPanel processesPanel = new JPanel();
        processesPanel.setBounds(width/2-200,225,400,procList.size()*35);
        processesPanel.setBackground(Design.foreColor);
        processesPanel.setLayout(new GridLayout(procList.size(),procList.size()));   //cols = amount of processes
        addProcButtons(processesPanel ,procList);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rmProcButtons(processesPanel, procList);
                procList = new ArrayList<String>();
                procList = AppTimer.getProcesses();
                processesPanel.setBounds(width/2-200,225,400,procList.size()*35);
                processesPanel.setLayout(new GridLayout(procList.size(),procList.size()));
                addProcButtons(processesPanel, procList);
                validate();

            }
        });


        this.add(title, BorderLayout.NORTH);
        this.add(button);
        this.add(processesPanel);

        this.setVisible(true);
    }

    public static void addProcButtons(JPanel showProcessesPanel, ArrayList<String> procList)
    {
        for (int i = 0; i < procList.size(); i++) {            //pętla do dodawania procesów
            final int index = i;

            JButton[] buttons = new JButton[procList.size()];
            buttons[i] = new JButton();
            buttons[i].setUI(new MyButtonUI());
            buttons[i].setFocusable(false);
            buttons[i].setForeground(Design.fontColor);
            buttons[i].setBackground(Design.foreColor);
            buttons[i].setBorder(border);
            buttons[i].setFont(Design.titleFont);
            buttons[i].setBounds(width/2-150, 150, 300, 50);
            buttons[i].setText(procList.get(i));

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AsyncTest t1 = new AsyncTest(buttons[index].getText());
                    Thread thread = new Thread(t1);
                    thread.start();
                }
            });

            showProcessesPanel.add(buttons[i]);
        }

    }
    public static void rmProcButtons(JPanel showProcessesPanel, ArrayList<String> procList)
    {
        for (int i = 0; i < procList.size(); i++) {            //pętla do usuwanioa procesów
            showProcessesPanel.remove(showProcessesPanel.getComponent(0));
        }

    }


    public static void main(String[] args)
    {
        MainWindow win = new MainWindow();
    }
}
