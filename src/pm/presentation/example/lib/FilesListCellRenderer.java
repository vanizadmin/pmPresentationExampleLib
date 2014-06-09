/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.presentation.example.lib;
import java.awt.Color;
import java.awt.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author user
 */
public class FilesListCellRenderer extends DefaultListCellRenderer {
    private final FileSystemView fileSystemView;
    private final JLabel label;
    private final Color textSelectionColor = Color.BLACK;
    private final Color backgroundSelectionColor = Color.CYAN;
    private final Color textNonSelectionColor = Color.BLACK;
    private final Color backgroundNonSelectionColor = Color.WHITE;
    public FilesListCellRenderer() {
        super();
        fileSystemView = FileSystemView.getFileSystemView();
        label = new JLabel();
        label.setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Path entity = (Path) value;
        String text = null;
        if (entity == null || Files.isDirectory(entity)) {
            if (entity != null) {
                Path name = entity.getFileName();
                if (name == null) {
                    text = entity.toString();
                } else {
                    text = name.toString();
                }
            }
        } else {
            text = entity.getFileName().toString();
        }
        if (entity != null) {
            label.setIcon(fileSystemView.getSystemIcon(entity.toFile()));
            label.setToolTipText(entity.toString());
        }
        label.setText(text);
        if (isSelected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }
        return label;
    }
}
