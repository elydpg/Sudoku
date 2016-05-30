import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Dlg extends JDialog {
    public Dlg(JFrame frame, String str) {
        super(frame, str);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {}
        });
    }
  }