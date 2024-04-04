package Views;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
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
    private JTree jTree ;
    private JTextArea textArea;
    public JTree getjTree() {
        return jTree;
    }
    public TextEditorView(String path) {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        // Thêm menu bar vào frame
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        Save = new JMenuItem("Save");
        Open = new JMenuItem("Open");
        Browser = new JMenuItem("Browser Folder");
        fileMenu.add(Save);
        fileMenu.add(Open);
        fileMenu.add(Browser);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        //--------------------------------------------------------------------
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(path);

        // Lấy tất cả các tệp trong ổ C và thêm chúng vào cây// ->>  Duyệt All Folder in this Pc ->> Big Data
//        File[] roots = File.listRoots();
//        for (File root : roots) {
//            DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(root.getAbsolutePath());
//            rootNode.add(rootTreeNode);
//            DefaultTree.addFilesToTree(root, rootTreeNode);
//        }
        File file= new File(path);
        DefaultTree.addFilesToTree(file,rootNode);
        jTree = new JTree(rootNode);

        textArea = new JTextArea();

        // Tạo JSplitPane với JTree ở bên trái và JTextArea ở bên phải
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(jTree), new JScrollPane(textArea));

        // Thiết lập độ co dãn tùy ý cho JSplitPane
        splitPane.setResizeWeight(0.2);
        panel.add(splitPane, BorderLayout.CENTER);
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
}
