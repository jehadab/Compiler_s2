package Java.AST.Table_Constraint;

import Java.AST.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class TableConstraintForeignKey extends Node {
    List<String> fk_column_name = new ArrayList<>();
    ForeignKeyClause foreignKeyClause ;

    public void addItemTofk_column_name (String c){
        fk_column_name.add(c);
    }

    public List<String> getFk_column_name() {
        return fk_column_name;
    }

    public void setFk_column_name(List<String> fk_column_name) {
        this.fk_column_name = fk_column_name;
    }

    public ForeignKeyClause getForeignKeyClause() {
        return foreignKeyClause;
    }

    public void setForeignKeyClause(ForeignKeyClause foreignKeyClause) {
        this.foreignKeyClause = foreignKeyClause;
    }
}
