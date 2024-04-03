package Views;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import Models.TextEditorModel;

public class TextEditorView {
    private  TextEditorModel DefaultTree = new TextEditorModel();
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem Save, Open, Browser;
    private JFrame frame;
    private JTree jTree = new JTree();
    private JTextArea textArea;

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
    public TextEditorView() {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        // Thêm menu bar vào frame
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        Save = new JMenuItem("Save");
        Open = new JMenuItem("Open");
        Browser = new JMenuItem("Browser");
        fileMenu.add(Save);
        fileMenu.add(Open);
        fileMenu.add(Browser);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());

        //-----------------
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("D:");
        File file = new File("D:/");
     //   jTree = new JTree();
        setTreemodeData(node,file);
        panel.add(new JScrollPane(jTree), BorderLayout.WEST);
         textArea = new JTextArea();
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public String GetContentArea() {
       return  this.textArea.getText();
    }
    public void SetContentArea(String textArea) {
        this.textArea.setText(textArea);
    }
    public void ActionButtonSave(ActionListener listener) {
        Save.addActionListener(listener);
    }

    public void ActionButtonOpen(ActionListener listener) {
        Open.addActionListener(listener);
    }

    public void ActionButtonBrowser(ActionListener listener) {
        Browser.addActionListener(listener);
    }
    public void ActionJtree(TreeSelectionListener listener){
        jTree.addTreeSelectionListener(listener);
    }
    public void setTreeData(DefaultMutableTreeNode rootNode,File filename) {
        DefaultTree.populate(filename,rootNode);
        jTree.setModel(new DefaultTreeModel(rootNode));

    }
    public void setTreemodeData(DefaultMutableTreeNode node,File namFile) {
        DefaultTree.populate(namFile, node);
        DefaultTreeModel model = new DefaultTreeModel(node);
        jTree.setModel(model);
    }
}
