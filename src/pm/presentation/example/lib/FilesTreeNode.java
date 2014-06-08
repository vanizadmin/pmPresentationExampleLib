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
import javax.swing.tree.DefaultMutableTreeNode;


/**
 *
 * @author user
 */
public class FilesTreeNode extends DefaultMutableTreeNode {
    private boolean childrenLoaded = false;
    public FilesTreeNode(Path filePath) {
        super(filePath);
        if (filePath == null || Files.isDirectory(filePath))
        setAllowsChildren(true);
    }
    protected void loadChildren() {
        if (!childrenLoaded) {
            childrenLoaded = true;
            if (userObject == null) {
                Iterable<Path> fileSystems = FileSystems.getDefault().getRootDirectories();
                for (Path fsname : fileSystems) {
                    // enhanced-for
                    this.add(new FilesTreeNode(fsname));
                }
            }
            else {
                try (DirectoryStream<Path> stream =Files.newDirectoryStream((Path) this.userObject)) {
                    for (Path rfile : stream) {
                        this.add(new FilesTreeNode(rfile));
                    }
                } catch (IOException | DirectoryIteratorException x) {
                /* Η εξαίρεση IOException
                δεν προέρχεται σε καμ ιά περίπτωση από την επανάληψη. Στο παρόν
                παράδειγμ α, μ πορεί να προέρθει από τη μ έθοδο newDirectoryStream. */
                    System.err.println(x);
                }
            }
        }
    }
    @Override
    public boolean isLeaf() {
        if (userObject==null) return false;
        return !Files.isDirectory((Path) this.userObject);
    }
    @Override
    public String toString() {
        if (this.userObject == null) return "FileSystems";// root of file-systems        
        Path fname = ((Path)userObject).getFileName();
        if (fname == null) return ((Path)userObject).toString(); // file-system
        return fname.toString();
    }
}
