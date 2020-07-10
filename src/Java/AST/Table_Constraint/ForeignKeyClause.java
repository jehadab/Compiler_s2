package Java.AST.Table_Constraint;

import Java.AST.Node;
import Java.AST.commn_classes_Sql.name_rule.DataBaseName;
import Java.AST.commn_classes_Sql.name_rule.FkTarget_ColumnName;
import Java.AST.commn_classes_Sql.name_rule.ForeignTable;
import Java.AST.commn_classes_Sql.name_rule.Name;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class ForeignKeyClause  extends Node {
    private Name nameMatch ;

    public Name getNameMatch() {
        return nameMatch;
    }

    public void setNameMatch(Name nameMatch) {
        this.nameMatch = nameMatch;
    }

    private DataBaseName dataBaseName ;
    private List<FkTarget_ColumnName> ListOfFkTarget_ColumnName = new ArrayList<>();
    private ForeignTable foreignTable;
    private boolean update=false,delete=false,setnull=false,setdefault=false,
            cascade=false,restrict=false,noaction=false,match=false;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isSetnull() {
        return setnull;
    }

    public void setSetnull(boolean setnull) {
        this.setnull = setnull;
    }

    public boolean isSetdefault() {
        return setdefault;
    }

    public void setSetdefault(boolean setdefault) {
        this.setdefault = setdefault;
    }

    public boolean isCascade() {
        return cascade;
    }

    public void setCascade(boolean cascade) {
        this.cascade = cascade;
    }

    public boolean isRestrict() {
        return restrict;
    }

    public void setRestrict(boolean restrict) {
        this.restrict = restrict;
    }

    public boolean isNoaction() {
        return noaction;
    }

    public void setNoaction(boolean noaction) {
        this.noaction = noaction;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    public void addItemToListOfFkTarget_ColumnName(FkTarget_ColumnName c){
        ListOfFkTarget_ColumnName.add(c);
    }

    public DataBaseName getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(DataBaseName dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public List<FkTarget_ColumnName> getListOfFkTarget_ColumnName() {
        return ListOfFkTarget_ColumnName;
    }

    public void setListOfFkTarget_ColumnName(List<FkTarget_ColumnName> listOfFkTarget_ColumnName) {
        ListOfFkTarget_ColumnName = listOfFkTarget_ColumnName;
    }

    public ForeignTable getForeignTable() {
        return foreignTable;
    }

    public void setForeignTable(ForeignTable foreignTable) {
        this.foreignTable = foreignTable;
    }
}
