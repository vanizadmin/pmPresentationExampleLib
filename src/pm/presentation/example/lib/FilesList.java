/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pm.presentation.example.lib;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author user
 */
public class FilesList extends JList {
    private final DefaultListModel listModel = new DefaultListModel();
    /**
     * Creates new form FilesList
     */
    public FilesList() {
        initComponents();
    }
    public final void loadDirContents(Path dir) //final ⇔ not overridable
    {
        listModel.removeAllElements();
        if (dir == null) // root
        {
// add file-systems to list
            Iterable<Path> fileSystems
                    = FileSystems.getDefault().getRootDirectories();
            for (Path aPath : fileSystems) {
                listModel.addElement((Path) aPath);
            }
        } else {
// add dir contents to list
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path rfile : stream) {
                    listModel.addElement((Path) rfile);
                }
            } catch (IOException | DirectoryIteratorException x) {
                /* Η εξαίρεση IOException
                 δεν προέρχεται σε καμ ιά περίπτωση από την επανάληψη. Στο παρόν
                 παράδειγμ α, μ πορεί να προέρθει από τη μ έθοδο newDirectoryStream. */
                System.err.println(x);
            }
        }
    }
    public FilesList(Path dir) {
        loadDirContents(dir);
        setModel(listModel);
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
