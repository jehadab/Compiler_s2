package Java.AST.creating;

import Java.AST.arrayandjson.array_left_side;

import java.util.ArrayList;

public class array_array_form {
    ArrayList<array_left_side> side = new ArrayList<array_left_side>();

    public void setSide(ArrayList<array_left_side> side) {
        this.side = side;
    }

    public ArrayList<array_left_side> getSide() {
        return side;
    }
    public void adding ( array_left_side left ){
        side.add(left);
    }



}
