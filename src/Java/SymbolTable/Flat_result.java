package Java.SymbolTable;

import java.util.ArrayList;

public class Flat_result  {
    String name ;
ArrayList<Flat_object> Flat = new ArrayList<Flat_object>();

    public ArrayList<Flat_object> getFlat() {
        return Flat;
    }

    public void setFlat(ArrayList<Flat_object> flat) {
        Flat = flat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public  void add_new_one(Flat_object object ){
        this.getFlat().add(object);
    }
}
