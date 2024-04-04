package Controllers;
// thực hiện liên kết 2 package từ model và views
import Models.TextEditorModel;
import Views.TextEditorView;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.io.*;

//Lib controllers:
public class TextEditroController {
    private  TextEditorView textEditorView;
    private  TextEditorModel textEditorModel;
    private String AddressFile=null;
    public TextEditroController(TextEditorView textEditorView, TextEditorModel textEditorModel) {
        this.textEditorView = textEditorView;
        this.textEditorModel = textEditorModel;
        // use Lambda Expression
        textEditorView.ActionButtonSave((ActionEvent event)->ButtonSave()); // thục hiện khi được nhấn nút
        textEditorView.ActionButtonOpen((ActionEvent event)->ButtonOpen()); // thục hiện khi được nhấn nút
        textEditorView.ActionButtonBrowser((ActionEvent event)->ButtonBrowser()); // thục hiện khi được nhấn nút
        textEditorView.ActionJtree((TreeSelectionEvent select)->SelectInTree());// Thực hiện khi được nhấn trong jtree
    }
    // -------------------------------thực hiện hành động khi được nhấn nút--------------------------------------------
    private void ButtonSave(){
        DefaultMutableTreeNode selectNode= (DefaultMutableTreeNode) textEditorView.getjTree().getLastSelectedPathComponent();
        if(selectNode!=null){
            AddressFile = textEditorModel.getAddress(selectNode);
            WriteFileToAddress(AddressFile);
        }
        else if(AddressFile==null){
            JOptionPane.showMessageDialog(null,"Please choose File");
        }
        else if(selectNode==null){
            WriteFileToAddress(AddressFile);
        }
        else {// selectNode is correct
            if (AddressFile == textEditorModel.getAddress(selectNode)) {
                AddressFile = textEditorModel.getAddress(selectNode);
                WriteFileToAddress(AddressFile);// check xem file có nằm trong jtree
            }
        }

    }
    private void ButtonOpen(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Open File");
        int ReturnValuePath= jFileChooser.showOpenDialog(null);
        if(ReturnValuePath == JFileChooser.APPROVE_OPTION){// check xem có chọn file không.
            String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
            ReadFileToAddress(filePath);
            AddressFile=filePath;// File được mở sẽ được lưu địa chỉ vào trong address
            // chắc chắn là file có thể nằm bất cứ đou
            textEditorView.getjTree().clearSelection();// Delete Select after open
        }
        // trưường hợp hủy Diglog thì không hiện tb
    }
    private void ButtonBrowser() {// setJtreee from Diaglog File
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            String folderPath = selectedFolder.getAbsolutePath();
//            Open New JFrame
            TextEditorView textEditorView1= new TextEditorView(folderPath);
            TextEditorModel textEditorModel1= new TextEditorModel();
            TextEditroController textEditroController = new TextEditroController(textEditorView1,textEditorModel1);
        } else {
         //   System.out.println("Từ chối mở JFrame mới");
        }
    }
    private void SelectInTree(){
        System.out.println("Dùng hàm này đúng không>>");
        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) textEditorView.getjTree().getLastSelectedPathComponent();
        if (selectNode != null && !selectNode.isLeaf()) {// Nếu nút được chọn không phải là một lá (thư mục)

        } else if (selectNode != null) {// Nếu nút được chọn là một lá (file)
            String address = textEditorModel.getAddress(selectNode);//  thiết lập địa chỉ
            if (address != null) {
                ReadFileToAddress(address);
            }}
    }
    private void ReadFileToAddress(String address){// sử dụng 2 lần
        try {
            BufferedReader reader = new BufferedReader(new FileReader(address));//đọc file từ địa chỉ
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            textEditorView.SetContentArea(content.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void WriteFileToAddress(String address){// trường hợp lấy file bên ngoài jtree vẫn có thể được xử lý
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(address));
            writer.write(textEditorView.GetContentArea());
            writer.close();
            JOptionPane.showMessageDialog(null,"Save File Successful !");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error!! Save!!");
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        TextEditorView TextEditorView= new TextEditorView("D:/");// Default in Drive D:/
        TextEditorModel TextEditorModel = new TextEditorModel();
        TextEditroController textEditroController = new TextEditroController(TextEditorView,TextEditorModel);
    }
}
