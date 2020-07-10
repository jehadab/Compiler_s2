package Java.AST.QueryStmt.Join;

import Java.AST.Node;
import Java.AST.commn_classes_Sql.name_rule.TableOrSubQuery;
import Java.Visitor.AstVistor;

import java.util.List;

/**
 * Created by moham on 1/4/2020.
 */
public class Join_Clause extends Node {
private TableOrSubQuery tableOrSubQuery ;
private List<TableOrSubQuery> tableOrSubQueryList;
private List<Join_Opreator>  join_opreatorList ;
private List<Join_Constrain> join_constrain;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if (tableOrSubQuery!=null){
            this.tableOrSubQuery.accept(astVisitor);
        }
        for (int i = 0; i <tableOrSubQueryList.size() ; i++) {
            this.tableOrSubQueryList.get(i).accept(astVisitor);
        }
        for (int i = 0; i <join_opreatorList.size() ; i++) {
            this.join_opreatorList.get(i).accept(astVisitor);
        }
        for (int i = 0; i <join_constrain.size() ; i++) {
            this.join_constrain.get(i).accept(astVisitor);
        }


    }

    public List<Join_Opreator> getJoin_opreatorList() {
        return join_opreatorList;
    }

    public void setJoin_opreatorList(List<Join_Opreator> join_opreatorList) {
        this.join_opreatorList = join_opreatorList;
    }

    public List<Join_Constrain> getJoin_constrain() {
        return join_constrain;
    }

    public void setJoin_constrain(List<Join_Constrain> join_constrain) {
        this.join_constrain = join_constrain;
    }

    public TableOrSubQuery getTableOrSubQuery() {
        return tableOrSubQuery;
    }

    public void setTableOrSubQuery(TableOrSubQuery tableOrSubQuery) {
        this.tableOrSubQuery = tableOrSubQuery;
    }

    public List<TableOrSubQuery> getTableOrSubQueryList() {
        return tableOrSubQueryList;
    }

    public void setTableOrSubQueryList(List<TableOrSubQuery> tableOrSubQueryList) {
        this.tableOrSubQueryList = tableOrSubQueryList;
    }


}
