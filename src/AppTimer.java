import java.io.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AppTimer implements Runnable
{
    String processName;
    Instant startTime;
    Instant endTime;
    int[] timeArr;

    public AppTimer(String processName)
    {
        this.processName = processName;
    }

    @Override
    public void run()
    {
        while(true)
        {

        }
    }
    public static ArrayList<String> getProcesses()
    {
        ArrayList<String> tempProcList = new ArrayList<>();
        try {
            String line;
            Process p = Runtime.getRuntime().exec("powershell -Command \"(Get-Process | Where-Object {$_.MainWindowTitle}).Name\"");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null)
                tempProcList.add(line);

            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return tempProcList;
    }
    /*
     Method isRunning checks if indicated process is running by using method getProcessess.
     GetProcess returns array of processes.
     If this array contains processName then process is still running
     */
    public boolean isRunning()
    {
        return getProcesses().contains(processName);
    }

    /*
    timeCounter method uses startTime and EndTime variables to count how much time has passed
    since the start and end of the process
    */
    public void timeCounter()
    {
        startTime = Instant.now();
        while (isRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = Instant.now();
        Duration timeSpan = Duration.between(startTime, endTime);
        timeArr = new int[]{ //temp arr with time spent in applicaton
                (int) timeSpan.toHours(), (int) timeSpan.toMinutes(), (int) timeSpan.toSeconds()
        };
    }
    public String timeArrToString()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = formatter.format(date);
        return strDate + "\n" + "Application: " + processName + "\nHours: " + timeArr[0] + "\nMinutes: " + timeArr[1] + "\nSeconds: " + timeArr[2] + "\n";
    }
    public void sendToFile()
    {
        String path = "./AppTimerResults/" + processName + ".txt";
        try {
            FileWriter writer = new FileWriter(path,true);
            writer.append(timeArrToString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void printList(ArrayList<String> arr)
    {
        for(int i = 0; i < arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
    }
    public static String chooseProcess(ArrayList<String> procList)
    {
        System.out.println("Choose program to follow");
        for (int i = 0; i < procList.size(); i++)
            System.out.println(procList.get(i));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public static void main(String[] args) throws InterruptedException {
        String userInput = chooseProcess(getProcesses());
        AppTimer a = new AppTimer(userInput);
        a.timeCounter();
        a.sendToFile();
    }
}
//powershell -Command "(Get-Process | Where-Object {$_.MainWindowTitle} | Select-Object Name, Id)" komenda w cmd drukująca nazwe i id procesu