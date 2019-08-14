package logger;

import java.io.*;
import java.time.LocalDateTime;

public class MyLogger {
    File file = null;

    public MyLogger() {}

    public void logApp(String msg) {
        try {
            if (file == null) {
                file = new File("a:/smssending/log.txt");
            } else {
                FileOutputStream fos = new FileOutputStream("a:/smssending/log.txt", true);
                msg = LocalDateTime.now() + " " + msg + "\n";
                byte[] buffer = msg.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.close();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}