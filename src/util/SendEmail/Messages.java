// 
// Decompiled by Procyon v0.5.36
// 

package util.SendEmail;

import org.testng.SkipException;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JFrame;
import org.testng.Reporter;

public class Messages
{
    public static void terminate(final String message) {
        Reporter.log("<<FATAL ERROR>> " + message, true);
        final JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
        throw new SkipException("<<FATAL ERROR>>" + message);
    }
    
    public static void warn(final String message) {
        Reporter.log("<<WARNING>> " + message);
        final JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
    }
}
