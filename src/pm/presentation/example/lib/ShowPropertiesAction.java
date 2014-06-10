/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pm.presentation.example.lib;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Action handling for showing file properties.
 */
public class ShowPropertiesAction extends AbstractAction {

    private final FilesMainPanel filesMainPanel;
// Constructor

    public ShowPropertiesAction(FilesMainPanel filesMP) {
        super();
        filesMainPanel = filesMP;
    }
// Show file properties

    @Override
    public void actionPerformed(ActionEvent e) {
   //     createPropertiesDialog(filesMainPanel.getSelectedPath());
    }

    private void createPropertiesDialog(Path entity) {
// Get entity of the file to be deleted
        String fileName;
// Get name of entity
        if (entity == null) {
            return;
        }
        Path tfileName = entity.getFileName();
        if (tfileName == null) {
            fileName = entity.toString();
        } else {
            fileName = tfileName.toString();
        }
// Get Size of entity
        String fileSize = null;
        try {
            fileSize = new Long(Files.size(entity)).toString();
        } catch (IOException ex) {
            Logger.getLogger(ShowPropertiesAction.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
// Get date of last modification of entity

        String fileDate = null;
        try {
            Date date;
            date = new Date(Files.getLastModifiedTime(entity).toMillis());
            SimpleDateFormat formater = new SimpleDateFormat();
            fileDate = formater.format(date);
        } catch (IOException ex) {
            Logger.getLogger(ShowPropertiesAction.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
// Is entity readable and writeable
        String isReadable = (Files.isReadable(entity) == true ? "Y" : "N");
        String isWriteable = (Files.isWritable(entity) == true ? "Y" : "N");
        JPanel propertiesPanel = new JPanel();
        JPanel labels = new JPanel();
        JPanel content = new JPanel();
        propertiesPanel.setLayout(new FlowLayout());
        labels.setLayout(new GridLayout(0, 1));
        content.setLayout(new GridLayout(0, 1));
        propertiesPanel.add(labels);
        propertiesPanel.add(content);
        labels.add(new JLabel("Filename: "));
        content.add(new JLabel(fileName));
        labels.add(new JLabel("Filesize: "));
        content.add(new JLabel(fileSize));
        labels.add(new JLabel("Last Modified: "));
        content.add(new JLabel(fileDate));
        labels.add(new JLabel("Readable: "));
        content.add(new JLabel(isReadable));
        labels.add(new JLabel("Writable: "));
        content.add(new JLabel(isWriteable));
// Show file properties
        JOptionPane.showMessageDialog(new JFrame(),
                propertiesPanel,
                "File/Dir Properties",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
