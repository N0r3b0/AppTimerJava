import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame
{
    static int height = 761;
    static int width = 669;
    ImageIcon icon = new ImageIcon("appTimerIcon.png");
    JButton button = new JButton();
    static Border border = BorderFactory.createLineBorder(Design.backColor);
    ArrayList<String> procList = AppTimer.getProcesses();       //lista procesów

    public MainWindow()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //zamyka aplikacje po wyjssciu z niej
        this.setSize(width, height);
        this.setLayout(null);                                   //brak layoutu, wszystko ustawione ręcznie
        this.setResizable(false);                               //usuwa opcje zmiany rozdizelczosci

        this.setTitle("App Timer");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Design.backColor);

        JLabel title = new JLabel("AppTimer");
        title.setFont(Design.titleFont);
        title.setForeground(Design.fontColor);
        title.setVerticalAlignment(JLabel.CENTER);              //ustawia wysokosc napisu w lablu na srodek
        title.setHorizontalAlignment(JLabel.CENTER);            //ustawia szerokosc napisu w lablu na srodek
        //title.setBorder(border);                              //dodaje ramke
        title.setBounds(width/2-100, 50, 200, 50);  //width/2 (połowa ekranu) - 100 (szerokość labla na 2), y - wys,

        button = new JButton();                                //przycisk do odswiezania procesow
        button.setUI(new MyButtonUI());                        //zmienia bazowe UI buttona na nowe z klasy MyButtonUI
        //button.setFocusable(false);                          //usuwa focus
        button.setForeground(Design.fontColor);
        button.setBackground(Design.foreColor);
        button.setBorder(border);

        button.setFont(Design.titleFont);
        button.setBounds(width/2-150, 150, 300, 50);    //width/2 (srodek ekranu) - połowa szerokosci buttona
        button.setText("Show processes");

        JPanel processesPanel = new JPanel();                             //panel przechowujacy procesy
        processesPanel.setBounds(width/2-200,225,400,procList.size()*35);  //wys panelu - dlugosc listy procesow * 35
        processesPanel.setBackground(Design.foreColor);
        processesPanel.setLayout(new GridLayout(procList.size(),procList.size()));   //tworzy layout siatki
        addProcButtons(processesPanel ,procList);             //metoda dodająca buttony jakie powinny się znaleźć po uruchomieniu (przed odświezaniem)

        button.addActionListener(new ActionListener() {      //listener dla odświezania listy procesow
            @Override
            public void actionPerformed(ActionEvent e) {
                rmProcButtons(processesPanel, procList);        //usuwa poprzednie buttony i robi miejsce na nowe
                procList = new ArrayList<String>();             //czyści liste
                procList = AppTimer.getProcesses();             //odswieza liste dodajac lub odejmujac procesy
                processesPanel.setBounds(width/2-200,225,400,procList.size()*35); //nowa wys. panelu zgodnie z nowa lista procesow
                processesPanel.setLayout(new GridLayout(procList.size(),procList.size())); //odświezenie layoutu zgodnie z nowa lista
                addProcButtons(processesPanel, procList);       //dodaje buttony zgodnie z uaktualniona lista procesow
                validate();                                     //odswiezenie okna aplikacji
            }
        });


        this.add(title);
        this.add(button);
        this.add(processesPanel);

        this.setVisible(true);
    }

    public static void addProcButtons(JPanel showProcessesPanel, ArrayList<String> procList) //funkcja dodajaca przyciski z nazwami procesow
    {
        for (int i = 0; i < procList.size(); i++) {            //pętla do dodawania procesów
            final int index = i;                               //final int zeby dalo sie do niego wejsc z wewnatrz listnera dla poszczegolnych buttonow

            JButton[] buttons = new JButton[procList.size()];  //tablica buttonow do dodania zgodna z wielkoscia listy
            buttons[i] = new JButton();                        //za kazdym razem trzeba stworzyc nowy button
            buttons[i].setUI(new MyButtonUI());                //ustwianie designu
            buttons[i].setForeground(Design.fontColor);
            buttons[i].setBackground(Design.foreColor);
            buttons[i].setBorder(border);
            buttons[i].setFont(Design.titleFont);
            buttons[i].setBounds(width/2-150, 150, 300, 50);
            buttons[i].setText(procList.get(i));

            buttons[i].addActionListener(new ActionListener() { //listner dla kazdego przycisku procesow
                @Override
                public void actionPerformed(ActionEvent e) {
                    AsyncTimer t1 = new AsyncTimer(buttons[index].getText());   //tworzy obiekt klasy AsyncTimer i przekazuje mu nazwe wybranego procesu
                    Thread thread = new Thread(t1);
                    thread.start();                                             //odpala timer zgodnie z metoda run z klasy AsyncTimer
                }
            });

            showProcessesPanel.add(buttons[i]);                                 //dodaje stworzone przyciski do panelu procesow
        }

    }
    public static void rmProcButtons(JPanel showProcessesPanel, ArrayList<String> procList) //funkcja odejmujaca przyciski z nazwami procesow
    {
        for (int i = 0; i < procList.size(); i++) {            //pętla do usuwanioa procesów
            showProcessesPanel.remove(showProcessesPanel.getComponent(0)); //0 bo procsy po kazdym usunieciu spadaja o jedna pozycje az do 0
        }
    }


    public static void main(String[] args)
    {
        MainWindow win = new MainWindow();
    }
}
