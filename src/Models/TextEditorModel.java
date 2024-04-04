package Models;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class TextEditorModel {

    public void populate(File file, DefaultMutableTreeNode node) {// thực hiện duyệt trong folder
        if(file.isDirectory())
        {
            File[] files= file.listFiles();
            if(files !=null) {
                for(File f:files) {
                    DefaultMutableTreeNode childNode= new DefaultMutableTreeNode(f.getName());
                    node.add(childNode);
                    populate(f, childNode);// đệ quy để duyẹt file
                }
            }
        }
    }
    public void addDirectoriesToNode(DefaultMutableTreeNode parentNode) {
        File[] roots = File.listRoots();
        for (File root : roots) {
            DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(root.getAbsolutePath());
            parentNode.add(rootTreeNode);
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        rootTreeNode.add(new DefaultMutableTreeNode(file.getName()));
                    }
                }
            }
        }
    }
    public String getAddress( DefaultMutableTreeNode node){
        Object[] path = node.getPath();
        StringBuilder address = new StringBuilder();
        for (Object obj : path) {
            address.append(obj.toString());
            if (obj != path[path.length - 1]) {
                address.append("/");
            }
        }
        return address.toString();
    }
    public void addFilesToTree(File directory, DefaultMutableTreeNode parentNode) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(file.getName());
                    parentNode.add(directoryNode);
                    addFilesToTree(file, directoryNode); // Đệ quy để thêm các tệp con
                } else {
                    parentNode.add(new DefaultMutableTreeNode(file.getName())); // Thêm tệp vào cây
                }
            }
        }
    }

    }
