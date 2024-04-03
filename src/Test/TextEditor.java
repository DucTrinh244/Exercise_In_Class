package Test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class TextEditor {
    private static JTree jTree;
    private static JFrame jFrame;

    public void populate(File file, DefaultMutableTreeNode node) {
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(f.getName());
                        node.add(childNode);
                        populate(f, childNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
         jFrame = new JFrame("Text Editor");
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(600, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode("D:"); // Tạo nút gốc với tên D:
        File file = new File("D:/");
        TextEditor textEditor = new TextEditor();
        textEditor.populate(file, node);
        DefaultTreeModel model = new DefaultTreeModel(node);
         jTree = new JTree(model);
        jFrame.add(new JScrollPane(jTree));
    }
}
