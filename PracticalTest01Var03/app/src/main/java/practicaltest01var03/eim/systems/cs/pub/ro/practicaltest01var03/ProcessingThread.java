package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

class ProcessingThread extends Thread{

    private Context context;
    private String user;
    private String grupa;

    private int stillRunning = 0;
    private Random random = new Random();


    public ProcessingThread(Context context, String user, String grupa) {
        this.context = context;
        this.user = user;
        this.grupa = grupa;

    }

    @Override
    public void run() {
        while(stillRunning == 0) {
            sendMessage(random.nextInt(2));
            sleep();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        switch (messageType) {
            case 0:
                intent.setAction("0");
                intent.putExtra("message", user + " " + grupa);
                break;
            case 1:
                intent.setAction("1");
                intent.putExtra("message", user + " " + grupa);
                break;
        }
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        stillRunning = 1;
    }

}

