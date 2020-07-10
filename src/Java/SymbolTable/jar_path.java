//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import java.util.ArrayList;

public class jar_path {
    String name;
    ArrayList<String> JarPath = new ArrayList();

    public jar_path() {
    }

    public ArrayList<String> getJarPath() {
        return this.JarPath;
    }

    public void print() {
        for(int i = 0; i < this.JarPath.size(); ++i) {
            System.out.println("print inside the list " + ((String)this.JarPath.get(i)).toString());
        }

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJarPath(ArrayList<String> jarPath) {
        this.JarPath = jarPath;
    }

    public void add_new_path_name(String s) {
        this.JarPath.add(s);
    }
}
