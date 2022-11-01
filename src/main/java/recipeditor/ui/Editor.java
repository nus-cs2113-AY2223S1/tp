package recipeditor.ui;

import recipeditor.storage.Storage;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor extends JFrame implements ActionListener {

    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private static JTextArea textArea;
    private static JFrame frame;
    private final JScrollPane scrollPane;
    private final JButton buttonSave;
    private final JButton buttonExit;
    private final JMenuBar menu;
    private EditorState state = EditorState.USING;

    public Editor() {
        frame = new JFrame("Editor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        textArea = new JTextArea();
        textArea.setSize(new Dimension(400,400));
        textArea.setFont(new Font("Arial", Font.PLAIN,15));
        scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        buttonSave = new JButton("Save and Exit");
        buttonExit = new JButton("Exit Only");
        menu = new JMenuBar();
        frame.add(scrollPane);
        frame.setSize(600,600);
        frame.setJMenuBar(menu);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.toFront();
        frame.setAlwaysOnTop(true);


        frame.add(buttonSave);
        frame.add(buttonExit);
        menu.add(buttonSave);
        menu.add(buttonExit);


        buttonSave.addActionListener(this);
        buttonExit.addActionListener(this);

        /** Action when closing with "x" button */
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to save?", "Closing",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    state = EditorState.SAVE;
                } else {
                    state = EditorState.EXIT;
                }
            }
        });
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSave) {
            this.state = EditorState.SAVE;
            // Save to temporary
            File temp = new File(Storage.TEMPORARY_FILE_PATH);
            try {
                FileWriter writer = new FileWriter(Storage.TEMPORARY_FILE_PATH);
                writer.write(textArea.getText());
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            frame.dispose();

        } else if (e.getSource() == buttonExit) {
            this.state = EditorState.EXIT;
            frame.dispose();
        }
    }

    public boolean enterEditor(String path) throws FileNotFoundException {
        Ui.showMessage("Please edit in the GUI editor!");
        loadFile(path);
        // Wait until the editor is done
        while (this.state == EditorState.USING) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                Ui.showMessage("Break from sleep");
            }
        }
        logger.log(Level.INFO, "Editor State: " + this.state);
        return state.equals(EditorState.SAVE);
    }

    /**
     * Load file from the path to the editor.
     */
    private void loadFile(String path) throws FileNotFoundException {
        File textFile = new File(path);
        Scanner scan = null;
        scan = new Scanner(textFile);
        while (scan.hasNext()) {
            String line = scan.nextLine() + "\n";
            textArea.append(line);
        }
        scan.close();
    }

    private enum EditorState {
        USING, SAVE, EXIT
    }
}


