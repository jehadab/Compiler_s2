package Java.AST.arrayandjson;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class value_of_left_side extends array_left_side {
    ArrayList<Token > ID ;

    public void setID(ArrayList<Token> ID) {
        this.ID = ID;
    }

    public ArrayList<Token> getID() {
        return ID;
    }

}
