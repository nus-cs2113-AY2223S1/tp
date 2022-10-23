package recipeditor.ui;

import recipeditor.storage.Storage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor extends JFrame implements ActionListener {

    private enum EditorState {
        USING, SAVE, EXIT
    }

    private static Logger logger = Logger.getLogger(Editor.class.getName());

    private EditorState state = EditorState.USING;
    private static JTextArea textArea;
    private static JFrame frame;
    private JButton buttonSave;
    private JButton buttonExit;
    private JMenuBar menu;

    public Editor() {
        frame = new JFrame("Editor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        textArea = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        frame.toFront();

        this.buttonSave = new JButton("Save and Exit");
        this.buttonExit = new JButton("Exit Only");
        this.menu = new JMenuBar();

        frame.add(textArea);
        frame.setJMenuBar(menu);
        frame.setVisible(true);

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
                if (JOptionPane.showConfirmDialog(frame, "Do you want to save?", "Closing", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    state = EditorState.SAVE;
                }
                else {
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
            File temp = new File(Storage.TEMPORARY_PATH);
            try{
                FileWriter writer = new FileWriter(Storage.TEMPORARY_PATH);
                writer.write(textArea.getText());
                writer.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            frame.dispose();

        } else if (e.getSource() == buttonExit) {
            this.state = EditorState.EXIT;
            frame.dispose();
        }
    }

    public boolean enterEditor(String path) {
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
        return (state.equals(EditorState.SAVE))? true : false;
    }

    /** Load file from the path to the editor*/
    private void loadFile(String path){
        File textFile = new File(path);
        Scanner scan = null;
        try{ 
            scan = new Scanner(textFile);
            while(scan.hasNext()){
                String line = scan.nextLine()+"\n";
                textArea.append(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finally{
            scan.close();
        }
    }

    
}

