package Test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DriveExplorer extends JFrame {

    private JTree tree;
    private JButton switchDriveButton;

    public DriveExplorer() {
        setTitle("Drive Explorer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponentsToFrame();
    }

    private void initComponents() {
        // Create JTree for drive D:/
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("D:/");
        File root = new File("D:/");
        createNodes(rootNode, root);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);

        // Create switch drive button
        switchDriveButton = new JButton("Switch to C:/");
        switchDriveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchDriveButtonClicked();
            }
        });
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tree), BorderLayout.CENTER);
        panel.add(switchDriveButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void createNodes(DefaultMutableTreeNode rootNode, File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
                rootNode.add(node);
                if (f.isDirectory()) {
                    createNodes(node, f);
                }
            }
        }
    }

    private void switchDriveButtonClicked() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("C:/");
        File root = new File("C:/");
        createNodes(rootNode, root);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        tree.setModel(treeModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DriveExplorer explorer = new DriveExplorer();
                explorer.setVisible(true);
            }
        });
    }
}
