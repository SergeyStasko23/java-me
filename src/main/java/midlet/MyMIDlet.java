package midlet;

import com.cinterion.io.ATCommand;
import com.cinterion.io.ATCommandFailedException;
import com.cinterion.io.InPort;
import com.cinterion.io.OutPort;
import logger.MyLogger;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MyMIDlet extends MIDlet {
    private InPort inPort;
    private OutPort outPort;

    private MyLogger logger;

    public MyMIDlet() {}

    protected void startApp() throws MIDletStateChangeException {
        try {
            logger.logApp("App state: started\n");

            ATCommand atCommand = new ATCommand(false);

            sendSms(atCommand, "+79103601301", "Successfully!!!");

            atCommand.send("AT^SMSO");
        } catch (ATCommandFailedException e) {
            e.printStackTrace();
        }
    }

    public void sendSms(ATCommand atCommand, String phoneNumber, String message) throws ATCommandFailedException {
        String sendToDestination = atCommand.send("AT+CMGS=\"" + phoneNumber + "\"");
        logger.logApp(sendToDestination);
        logger.logApp(phoneNumber);

        String smsForSending = atCommand.send(message + (char)26);
        logger.logApp(smsForSending);
        logger.logApp(message);
    }

    protected void pauseApp() {
        notifyPaused();
        logger.logApp("App state: paused");
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        notifyDestroyed();
        logger.logApp("App state: destroyed");
    }
}