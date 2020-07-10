package Java.AST.arrayandjson;

import Java.AST.Node;
import Java.AST.creating.array_array_form;
import generated.SQLParser;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class array_value_form extends Node {
    ArrayList<Token> ID = new  ArrayList<Token>();
  ArrayList<jsonstatment> statment = new ArrayList<jsonstatment>();
 array_array_form arrayfor ;
    public void setID(ArrayList<Token> ID) {
        this.ID = ID;
    }

    public ArrayList<Token> getID() {
        return ID;
    }
     public  void addIden ( Token TT){
        ID.add(TT);
    }

    public void setStatment(ArrayList<jsonstatment> statment) {
        this.statment = statment;
    }

    public ArrayList<jsonstatment> getStatment() {
        return statment;
    }

    public void setArrayfor(array_array_form arrayfor) {
        this.arrayfor = arrayfor;
    }

    public array_array_form getArrayfor() {
        return arrayfor;
    }

}
