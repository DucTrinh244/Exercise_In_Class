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

    }
