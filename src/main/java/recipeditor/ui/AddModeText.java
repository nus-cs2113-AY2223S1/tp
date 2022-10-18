package recipeditor.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddModeText extends JFrame implements ActionListener {
    private int status = 0;
    private static JTextArea area;
    private static JFrame frame;

    private JButton buttonDone;
    private JButton buttonExit;
    private JMenuBar menu;

    public AddModeText() {
        frame = new JFrame("Editor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger(AddModeText.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        area = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);


        this.buttonDone = new JButton("Done");
        this.buttonExit = new JButton("Exit");
        this.menu = new JMenuBar();
        buttonDone.setPreferredSize(new Dimension(20, 20));
        buttonExit.setPreferredSize(new Dimension(20, 20));

        frame.add(buttonDone);
        frame.add(buttonExit);
        frame.add(area);
        frame.setJMenuBar(menu);
        menu.add(buttonDone);
        menu.add(buttonExit);
        frame.setVisible(true);
        frame.toFront();


        /** Premature closing */
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "closing", "Close?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Ui.showMessage("Pressed X");
                    status = 2;
                }
            }
        });


        buttonDone.addActionListener(this);
        buttonExit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ae = e.getActionCommand();
        if (e.getSource() == buttonDone) {
            this.status = 1;
            System.out.println("Save");
            frame.dispose();
        } else if (e.getSource() == buttonExit) {
            System.out.println("No Save");
            this.status = 2;
            frame.dispose();
        }
    }

    public void enterAddModeText() {
        while (this.status == 0) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                Ui.showMessage("Break from sleep");
            }

        }
        Ui.showMessage(String.valueOf(this.status));
    }


}
