public class AsyncTimer implements Runnable
{
    String chosenProcess;                       //nazwa wybranego procesu
    public AsyncTimer(String chosenProcess)
    {
        this.chosenProcess = chosenProcess;
    }

    @Override
    public void run() {                                 //metoda uruchamiana podczas wykonywania sie watku
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
