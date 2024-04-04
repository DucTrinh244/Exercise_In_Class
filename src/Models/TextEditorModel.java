package Models;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextEditorModel {
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
//    public void addFilesToTree(File directory, DefaultMutableTreeNode parentNode) {// sử dụng mảng
//        File[] files = directory.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(file.getName());
//                    parentNode.add(directoryNode);
//                    addFilesToTree(file, directoryNode); // Đệ quy để thêm các tệp con
//                } else {
//                    parentNode.add(new DefaultMutableTreeNode(file.getName())); // Thêm tệp vào cây
//                }
//            }
//        }
//    }
public static void addFilesToTree(File directory, DefaultMutableTreeNode parentNode) {// sử dụng stream để duyệt
    File[] files = directory.listFiles();
    if (files != null) {
        Arrays.stream(files)
              .collect(Collectors.toList())
              .forEach(file -> {
                    if (file.isDirectory()) {
                        DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(file.getName());
                        parentNode.add(directoryNode);
                        addFilesToTree(file, directoryNode); // Đệ quy để thêm các tệp con
                    } else {
                        parentNode.add(new DefaultMutableTreeNode(file.getName())); // Thêm tệp vào cây
                    }
                });
    }
}
    }
