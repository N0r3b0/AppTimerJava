import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AsyncTest implements Runnable
{
    String chosenProcess;
    public AsyncTest(String chosenProcess)
    {
        this.chosenProcess = chosenProcess;
    }

    @Override
    public void run() {
        AppTimer app = new AppTimer(chosenProcess);
        app.timeCounter();
        app.sendToFile();
    }

    public static void main(String[] args) throws InterruptedException {
//        AsyncTest t1 = new AsyncTest();
//        AsyncTest t2 = new AsyncTest();
//        Thread thread1 = new Thread(t1);
//        Thread thread2 = new Thread(t2);
//        thread1.start();
//        i++;
//        thread2.start();
    }
}
