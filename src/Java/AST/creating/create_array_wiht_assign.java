package Java.AST.creating;

import Java.AST.arrayandjson.array_left_side;
import Java.AST.arrayandjson.arrayformwithoutindex;

public class create_array_wiht_assign extends createvariablewithassign {
    arrayformwithoutindex index ;
    array_left_side side ;

    public void setSide(array_left_side side) {
        this.side = side;
    }

    public array_left_side getSide() {
        return side;
    }

    public void setIndex(arrayformwithoutindex index) {
        this.index = index;

    }

    public arrayformwithoutindex getIndex() {
        return index;
    }

}

