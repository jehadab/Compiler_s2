package Java.Base;

import Java.AST.FunctionDeclaration;
import Java.AST.ParametesandArgs.args;
import Java.AST.Parse;
import Java.AST.QueryStmt.*;
import Java.AST.QueryStmt.Alter_Stmt.*;
import Java.AST.QueryStmt.Create_Stmt.*;
import Java.AST.QueryStmt.Create_Type.Create_Type;
import Java.AST.QueryStmt.Create_Type.InsideCreateType;
import Java.AST.QueryStmt.Delete.DeleteStmt;
import Java.AST.QueryStmt.Drop.DropStmt;
import Java.AST.QueryStmt.InsertStmt.InsertStmt;
import Java.AST.QueryStmt.Join.Join_Clause;
import Java.AST.QueryStmt.Join.Join_Constrain;
import Java.AST.QueryStmt.Join.Join_Opreator;
import Java.AST.QueryStmt.SelectStmt.*;
import Java.AST.QueryStmt.UpdateStmt.Asign_Expr_to_column;
import Java.AST.QueryStmt.UpdateStmt.UpdateStmt;
import Java.AST.Table_Constraint.ForeignKeyClause;
import Java.AST.Table_Constraint.IndexedColumn;
import Java.AST.Table_Constraint.TableConstraintPrimaryKey;
import Java.AST.expr.*;
import Java.AST.commn_classes_Sql.name_rule.*;
import Java.AST.function.sub_function_body;
import Java.AST.instruction.Print_rule.Inside_the_print;
import Java.AST.instruction.Print_rule.Print;
import Java.AST.instruction.Returning.returnes_rule;
import Java.AST.instruction.all_the_non_functional_instructions.One_line_if;
import Java.Main;
import Java.SymbolTable.*;
import com.sun.org.apache.xml.internal.utils.StringComparable;
import generated.SQLBaseVisitor;
import generated.SQLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Java.AST.assignmnet.Variable_with_opretor;
import Java.AST.creating.createwihtoutassign;
import Java.AST.creating.creatingvariabelwithoutassing;
import Java.AST.creating.gneralcreating;
import Java.AST.function.function_body;
import Java.AST.function.function_header;
import Java.AST.instruction.Switch_rule.Case;
import Java.AST.instruction.Switch_rule.Deafult;
import Java.AST.instruction.Switch_rule.Switch;
import Java.AST.instruction.loops.*;
import Java.AST.instruction.*;
import Java.AST.instruction.IF_rule.*;
import Java.AST.arrayandjson.*;
import Java.AST.creating.*;
import Java.AST.ParametesandArgs.*;
import org.antlr.v4.runtime.*;
import Java.AST.assignmnet.*;
import Java.AST.instruction.Returning.*;
import org.antlr.v4.runtime.tree.TerminalNode;


import javax.lang.model.type.NullType;
import java.util.*;

public class BaseVisitor extends SQLBaseVisitor {
    Stack<Scope> scopesStack = new Stack<>();

    public Parse visitParse(SQLParser.ParseContext ctx) {
        System.out.println("visitParse");
        Parse p = new Parse();
        Scope globalScope = new Scope();
        globalScope.setId("global_scope");
        scopesStack.push(globalScope);

        if (ctx.create_aggregation_function().size() != 0) {
            for (int i = 0; i < ctx.create_aggregation_function().size(); i++) {
                p.getAg().add(visitCreate_aggregation_function(ctx.create_aggregation_function(i)));
            }
        }


        if (ctx.sql_stmt_list().size() != 0) {
            for (int i = 0; i < ctx.sql_stmt_list().size(); i++) {
                for (int j = 0; j < ctx.sql_stmt_list().get(i).sql_stmt().size(); j++) {
                    p.getSqlStmts().add(visitSql_stmt(ctx.sql_stmt_list().get(i).sql_stmt().get(j)));
                }
            }
        }
        if (ctx.funtion() != null) {

            System.out.println("visiting fucntion ");
            System.out.println(" size of the function " + ctx.funtion().size());
            for (int i = 0; i < ctx.funtion().size(); i++) {
                Scope functionScope = new Scope();
                functionScope.setId(ctx.funtion().get(i).function_header().use_random_name().getText() + "_" + ctx.funtion().get(i).hashCode());
                functionScope.setParent(scopesStack.peek());

                scopesStack.push(functionScope);

                p.getFunctions().add(visitFuntion(ctx.funtion(i)));

                Main.symbolTable.addScope(scopesStack.pop());
            }
        }
        p.setLine(ctx.getStart().getLine()); //get line number
        p.setCol(ctx.getStart().getCharPositionInLine()); // get col number
        Main.symbolTable.addScope(scopesStack.pop());
        Main.showSymboleTable();
        return p;
    }

    @Override
    public Object visitError(SQLParser.ErrorContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public List<Statement> visitSql_stmt_list(SQLParser.Sql_stmt_listContext ctx) {

        System.out.println("visitSql_stmt_list");

        List<Statement> sqlStmt = new ArrayList<Statement>();
        for (int i = 0; i < ctx.sql_stmt().size(); i++) {
            sqlStmt.add(visitSql_stmt(ctx.sql_stmt(i)));
        }

        return sqlStmt;

    }

    @Override
    public Statement visitSql_stmt(SQLParser.Sql_stmtContext ctx) {

        System.out.println("visitSql_stmt   ");
        Statement s = new Statement();
        if (ctx.factored_select_stmt() != null) {
            s = visitFactored_select_stmt(ctx.factored_select_stmt());
        }
        else if (ctx.delete_stmt() != null) {
            s = visitDelete_stmt(ctx.delete_stmt());// ... ignor this
        } else if (ctx.drop_table_stmt() != null) {
            s = visitDrop_table_stmt(ctx.drop_table_stmt());//... ignor this
        } else if (ctx.create_table_stmt() != null) {
            s = visitCreate_table_stmt(ctx.create_table_stmt());
        } else if (ctx.alter_table_stmt() != null) {
            s = visitAlter_table_stmt(ctx.alter_table_stmt());// ... ignor this
        } else if (ctx.insert_stmt() != null) {
            s = visitInsert_stmt(ctx.insert_stmt());// ... ignor this
        } else if (ctx.update_stmt() != null) {
            s = visitUpdate_stmt(ctx.update_stmt());// ... ignor this
        }
        else if(ctx.create_type()!=null){
            s = visitCreate_type(ctx.create_type());
        }


        return s;

    }

    @Override
    public SelectFactoredStmt visitFactored_select_stmt(SQLParser.Factored_select_stmtContext ctx) {
        System.out.println("visitFactored_select_stmt");
        SelectFactoredStmt select = new SelectFactoredStmt();
        if (ctx.select_core() != null) {
            select.setSelect_core(visitSelect_core(ctx.select_core()));
            if (ctx.ordering_term() != null) {
                List<Ordering_term> ordering_terms = new ArrayList<>();
                for (int i = 0; i < ctx.ordering_term().size(); i++) {
                    ordering_terms.add(visitOrdering_term(ctx.ordering_term(i)));
                }
                select.setOrdering_terms(ordering_terms);
            }
            if(ctx.limit_expr()!=null)
            {
                select.setLimitExpr(visitExpr(ctx.limit_expr().expr()));
                if (ctx.K_OFFSET() != null) {
                    select.setOffset(true);
                } else if (ctx.expr() != null) {
                    select.setExpr(visitExpr(ctx.expr()));
                }
            }

        }

        select.setName("Select");
        return select;
    }

    @Override
    public DeleteStmt visitDelete_stmt(SQLParser.Delete_stmtContext ctx) {
        System.out.println("visitDelete_Stmt");
        DeleteStmt delete = new DeleteStmt();
        if (ctx.qualified_table_name() != null) {
            delete.setQualifiedTableName(visitQualified_table_name(ctx.qualified_table_name()));
            if (ctx.where_expr() != null) {
                delete.setExpr(visitExpr(ctx.where_expr().expr()));
            }
            delete.setName("Delete");
            return delete;
        } else
            return null;
    }


    @Override
    public DropStmt visitDrop_table_stmt(SQLParser.Drop_table_stmtContext ctx) {
        System.out.println("visitDrop_table_stmt");
        DropStmt drop = new DropStmt();
        if (ctx.K_IF() != null && ctx.K_EXISTS() != null) {
            drop.setExist(true);
        }
        if (ctx.database_name() != null) {
            drop.setDataBaseName(visitDatabase_name(ctx.database_name()));
        }
        if (ctx.table_name() != null) {
            drop.setTableName(visitTable_name(ctx.table_name()));
            drop.setName("Drop");
        } else
            return null;


        return drop;
    }

    @Override
    public CreateTableStmt visitCreate_table_stmt(SQLParser.Create_table_stmtContext ctx) {
        System.out.println("visitCreate_table_stmt");
        Scope currentScope =  scopesStack.peek();
        Table table = new Table();
         CreateTableStmt createTableStmt = new CreateTableStmt();
//        if(ctx.database_name()!=null) {
//            createTableStmt.setDataBaseName(visitDatabase_name(ctx.database_name()));
//        }
        if (ctx.table_name() != null) {
            createTableStmt.setTableName(visitTable_name(ctx.table_name()));
            String name = ctx.table_name().use_random_name().getText();
            table.setTable_name(name);

            if(ctx.column_def()!=null)
            {
                List<ColumnDef> columnDefs = new ArrayList<>();
                for (int i = 0; i < ctx.column_def().size(); i++) {
                    columnDefs.add(visitColumn_def(ctx.column_def(i)));
                }
                createTableStmt.setColumnDefs(columnDefs);

                if(ctx.table_constraint()!=null){
                    List<TableConstraint> tableConstraints= new ArrayList<>();
                    for (int i = 0; i <ctx.table_constraint().size() ; i++) {
                        tableConstraints.add(visitTable_constraint(ctx.table_constraint(i)));
                    }
                    createTableStmt.setTableConstraints(tableConstraints);
                }
                if (ctx.declare_type_table() != null) {
                    createTableStmt.setDeclareTypeTable(visitDeclare_type_table(ctx.declare_type_table()));
                    if (ctx.COMMA() != null && ctx.declare_path_table() != null) {
                        createTableStmt.setDeclarePathTable(visitDeclare_path_table(ctx.declare_path_table()));
//
                    }
                }
            } else if (ctx.select_stmt() != null) {
                createTableStmt.setSelect_stmt(visitSelect_stmt(ctx.select_stmt()));
            }

        }
        currentScope.addTable(table.getTable_name(),table);
        table.setColumnDefListList(createTableStmt.getColumnDefs());
        table.setPath_of_table(createTableStmt.getDeclarePathTable().getPath());
//        System.out.println("the path of the table is : "+table.getPath_of_table());
        table.setExtension_of_table(createTableStmt.getDeclareTypeTable().getType());
//        System.out.println("the extension of the table is : "+table.getExtension_of_table());
        createTableStmt.setName("Create Table");


        return createTableStmt;
    }


    @Override
    public DeclareTypeTable visitDeclare_type_table(SQLParser.Declare_type_tableContext ctx) {
        System.out.println("Visit Declare_type_table");
        DeclareTypeTable declareTypeTable = new DeclareTypeTable();
        if (ctx.K_TYPE() != null && ctx.ASSIGN() != null && ctx.IDENTIFIER() != null) {
            declareTypeTable.setType(ctx.IDENTIFIER().getText());
        }
        return declareTypeTable;
    }


    @Override
    public DeclarePathTable visitDeclare_path_table(SQLParser.Declare_path_tableContext ctx) {
        System.out.println("Visit Declare_path_table");
        DeclarePathTable declarePathTable = new DeclarePathTable();
        if (ctx.K_PATH() != null && ctx.ASSIGN() != null && ctx.IDENTIFIER() != null) {
            declarePathTable.setPath(ctx.IDENTIFIER().getText());
        }
        return declarePathTable;
    }


    @Override
    public ColumnDef visitColumn_def(SQLParser.Column_defContext ctx) {
        System.out.println("visitColumn_def");
        ColumnDef colmndef = new ColumnDef();
        if (ctx.column_name() != null) {
            colmndef.setName(ctx.column_name().use_random_name().RANDOM_NAME().getText());
            if (ctx.column_constraint() != null) {
                for (int i = 0; i < ctx.column_constraint().size(); i++) {
                    colmndef.addItemToListOfColumnConstraint(visitColumn_constraint(ctx.column_constraint(i)));
                }
            }
            if (ctx.type_name() != null) {
              colmndef.setTypeName(visitType_name(ctx.type_name()));
            }
        }
        return colmndef;
    }


    @Override
    public ColumnConstraint visitColumn_constraint(SQLParser.Column_constraintContext ctx) {
        System.out.println("visitCoumn_constraint");
        ColumnConstraint columnConstraint = new ColumnConstraint();
        if (ctx.name() != null) {
            columnConstraint.setConstraintname(ctx.name().use_random_name().RANDOM_NAME().getText());
            System.out.println("the nam constraint is : " + columnConstraint.getConstraintname());
        }

        if (ctx.column_constraint_primary_key() != null) {
            columnConstraint.setConstrainttype(ctx.column_constraint_primary_key().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.column_constraint_foreign_key() != null) {
            columnConstraint.setConstrainttype((ctx.column_constraint_foreign_key().getText()));
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.collation_name() != null) {
            columnConstraint.setConstrainttype(ctx.collation_name().getText());
            System.out.println("the constraint is  : collation " + columnConstraint.getConstrainttype());
        } else if (ctx.column_constraint_null() != null) {
            columnConstraint.setConstrainttype(ctx.column_constraint_null().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.column_constraint_not_null() != null) {
            columnConstraint.setConstrainttype(ctx.column_constraint_not_null().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.column_default() != null) {
            columnConstraint.setConstrainttype(ctx.column_default().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.column_unique() != null) {
            columnConstraint.setConstrainttype(ctx.column_unique().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        } else if (ctx.column_check() != null) {
            columnConstraint.setConstrainttype(ctx.column_check().getText());
            System.out.println("the constraint is  : " + columnConstraint.getConstrainttype());
        }


        return columnConstraint;
    }


    @Override
    public TableConstraint visitTable_constraint(SQLParser.Table_constraintContext ctx) {
        System.out.println("visitTable_constraint");
        TableConstraint tableConstraint = new TableConstraint();
        if (ctx.name() != null) {
            tableConstraint.setCostraintname(ctx.name().use_random_name().RANDOM_NAME().getText());
            System.out.println("the nam constraint is : " + tableConstraint.getCostraintname());
        }
        if (ctx.table_constraint_primary_key() != null) {
            tableConstraint.setConstrainttype(ctx.table_constraint_primary_key().getText());
            System.out.println("the constraint is  : " + tableConstraint.getConstrainttype());
        } else if (ctx.table_constraint_foreign_key() != null) {
            tableConstraint.setConstrainttype((ctx.table_constraint_foreign_key().getText()));
            System.out.println("the constraint is  : " + tableConstraint.getConstrainttype());
        } else if (ctx.table_constraint_unique() != null) {
            tableConstraint.setConstrainttype(ctx.table_constraint_unique().getText());
            System.out.println("the constraint is  : " + tableConstraint.getConstrainttype());
        } else if (ctx.table_constraint_key() != null) {
            tableConstraint.setConstrainttype(ctx.table_constraint_key().getText());
            System.out.println("the constraint is  : " + tableConstraint.getConstrainttype());
        } else if (ctx.table_check() != null) {
            tableConstraint.setConstrainttype(ctx.table_check().getText());
            System.out.println("the constraint is  : " + tableConstraint.getConstrainttype());
        }
        return tableConstraint;
    }


    @Override
    public TypeName visitType_name(SQLParser.Type_nameContext ctx) {
        System.out.println("visitType_name");
        TypeName typeName = new TypeName();
        if (ctx.oneOftype_name() != null) {
            if (ctx.signed_number1() != null) {
                typeName.setLim1(ctx.signed_number1().signed_number().NUMERIC_LITERAL().getText());
                System.out.println("singed_number is : " + typeName.getLim1());
            } else if (ctx.signed_number2() != null) {
                typeName.setLim2(ctx.signed_number2().getText());
                System.out.println("singed_number is : " + "(" + typeName.getLim2() + ")");
            }
            typeName.setName(ctx.oneOftype_name().getText());
        }
        return typeName;
    }


    @Override
    public Create_Type visitCreate_type(SQLParser.Create_typeContext ctx) {
        System.out.println("visit_creat_type");
        Scope currentScope =  scopesStack.peek();
        Type type = new Type();
        Create_Type create_type = new Create_Type();
        if (ctx.use_random_name() != null) {
            create_type.setNameOfType(ctx.use_random_name().getText());
//            todo check for the type name
            type.setName(create_type.getNameOfType());
            if(ctx.inside_create_type()!=null){
                List<InsideCreateType> insideCreateTypes =  new ArrayList<>();
                for(int i=0; i<ctx.inside_create_type().size();i++){
                    insideCreateTypes.add(visitInside_create_type(ctx.inside_create_type(i)));
                    type.addColumnForType(insideCreateTypes.get(i).getNameOfColumnOfType(),insideCreateTypes.get(i).getType());
                }

                create_type.setInsideCreateTypeList(insideCreateTypes);
            }
            create_type.setName("Create_Type");
        }
        currentScope.addType(type.getName(),type);
        type.setScope(currentScope);
        Main.symbolTable.addType(type);
        return create_type;
    }

    @Override
    public InsideCreateType visitInside_create_type(SQLParser.Inside_create_typeContext ctx) {
        System.out.println("visit_inside_create_type");
        InsideCreateType insideCreateType = new InsideCreateType();
        if (ctx.use_random_name() != null) {
            insideCreateType.setNameOfColumnOfType(ctx.use_random_name().getText());
            System.out.println("the column of type is " + insideCreateType.getNameOfColumnOfType());
            if (ctx.oneOftype_name() != null) {

                insideCreateType.setType(ctx.oneOftype_name().getText());
            }
            return insideCreateType;
        }
        return null;
    }


    @Override
    public AlterTableStmt visitAlter_table_stmt(SQLParser.Alter_table_stmtContext ctx) {
        System.out.println("visitAlter_table_stmt");
        AlterTableStmt alterTableStmt = new AlterTableStmt();
        if (ctx.source_table_name() != null) {
            if (ctx.database_name() != null) {
                alterTableStmt.setTablename(ctx.database_name().use_random_name().RANDOM_NAME().getText() + "." +
                        ctx.source_table_name().use_random_name().RANDOM_NAME().getText());
            } else {
                alterTableStmt.setTablename(ctx.source_table_name().use_random_name().RANDOM_NAME().getText());
            }

            if (ctx.renametable() != null) {
                alterTableStmt.setAltertype(visitRenametable(ctx.renametable()));
            } else if (ctx.alter_table_add() != null) {
                alterTableStmt.setAltertype(visitAlter_table_add(ctx.alter_table_add()));

            } else if (ctx.alter_table_add_constraint() != null) {
                alterTableStmt.setAltertype(visitAlter_table_add_constraint(ctx.alter_table_add_constraint()));
            } else if (ctx.addcolumn() != null) {
                alterTableStmt.setAltertype(visitAddcolumn(ctx.addcolumn()));
            }
        }
        alterTableStmt.setName("Alter table");
        return alterTableStmt;
    }


    @Override
    public RenameTable visitRenametable(SQLParser.RenametableContext ctx) {
        System.out.println("visitRenametable");
        RenameTable renameTable = new RenameTable();
        if (ctx.new_table_name() != null) {
            renameTable.setNewname(ctx.new_table_name().use_random_name().RANDOM_NAME().getText());
            System.out.println("the new Name after rename is : " + renameTable.getNewname());
        }
        return renameTable;

    }


    @Override
    public AlterTableAdd visitAlter_table_add(SQLParser.Alter_table_addContext ctx) {
        System.out.println("visitAlter_table_add");
        AlterTableAdd alterTableAdd = new AlterTableAdd();
        if (ctx.table_constraint() != null) {
            alterTableAdd.setTableConstraint(visitTable_constraint(ctx.table_constraint()));
        }
        return alterTableAdd;
    }


    @Override
    public AlterTableAddConstraint visitAlter_table_add_constraint(SQLParser.Alter_table_add_constraintContext ctx) {
        System.out.println("visitAlter_table_add_constraint");
        AlterTableAddConstraint alterTableAddConstraint = new AlterTableAddConstraint();
        if (ctx.use_random_name() != null) {
            alterTableAddConstraint.setTablename(ctx.use_random_name().RANDOM_NAME().getText());
            System.out.println("the table Name that want to alter is : " + alterTableAddConstraint.getTablename());
            if (ctx.table_constraint() != null) {
                alterTableAddConstraint.setTableConstraint(visitTable_constraint(ctx.table_constraint()));
            }
        }
        return alterTableAddConstraint;
    }


    @Override
    public InsertStmt visitInsert_stmt(SQLParser.Insert_stmtContext ctx) {
        System.out.println("visitInsert_stmt");
        InsertStmt insertStmt = new InsertStmt();

        if (ctx.database_name() != null) {
            insertStmt.setDataBaseName(visitDatabase_name(ctx.database_name()));
        }

        if (ctx.table_name() != null) {
            insertStmt.setTableName(visitTable_name(ctx.table_name()));
            if (ctx.column_name() != null) {
                for (int i = 0; i < ctx.column_name().size(); i++) {
                    insertStmt.addItemToListOfColumnName(visitColumn_name(ctx.column_name(i)));
                }
            }

            if (ctx.expr() != null) {
                for (int i = 0; i < ctx.expr().size(); i++) {
                    insertStmt.addItemToListOfExpr(visitExpr(ctx.expr(i)));
                }
            }

            if (ctx.select_stmt() != null) {
                System.out.println("select statment ");
                insertStmt.setSleSelect_stmt(visitSelect_stmt(ctx.select_stmt()));
            }

            if (ctx.K_DEFAULT() != null && ctx.K_VALUES() != null) {
                System.out.println("set default values ");
                insertStmt.setDefalut_value(true);
            }

        }

        return insertStmt;
    }


    @Override
    public UpdateStmt visitUpdate_stmt(SQLParser.Update_stmtContext ctx) {
        System.out.println("visitUpdate_stmt");
        UpdateStmt updateStmt = new UpdateStmt();
        if (ctx.qualified_table_name() != null) {
            updateStmt.setQualifiedTableName(visitQualified_table_name(ctx.qualified_table_name()));
        }

        if (ctx.asign_expr_to_column() != null) {
            for (int i = 0; i < ctx.asign_expr_to_column().size(); i++) {
                updateStmt.addItemToListOfAsign_expr_to_column(visitAsign_expr_to_column(ctx.asign_expr_to_column(i)));
            }
        }

        if (ctx.K_WHERE() != null && ctx.expr() != null) {
            {
                updateStmt.setWhereExpr(visitExpr(ctx.expr()));
            }
        }

        updateStmt.setName("Update");

        return updateStmt;
    }


    @Override
    public Asign_Expr_to_column visitAsign_expr_to_column(SQLParser.Asign_expr_to_columnContext ctx) {
        System.out.println("visitAsign_expr_to_column");
        Asign_Expr_to_column asign_expr_to_column = new Asign_Expr_to_column();
        if (ctx.column_name() != null && ctx.expr() != null) {
            asign_expr_to_column.setColumnName(visitColumn_name(ctx.column_name()));
            asign_expr_to_column.setExpr(visitExpr(ctx.expr()));
        }
        return asign_expr_to_column;
    }

// ---------------------------------------------select statment ----------------------------------------------------

    @Override
    public select_stmt visitSelect_stmt(SQLParser.Select_stmtContext ctx) {
        System.out.println("visitSelect_stmt");
        select_stmt select_stmt = new select_stmt();
        if (ctx.select_or_values() != null) {

            select_stmt.setSelectOrValue(visitSelect_or_values(ctx.select_or_values()));
            if (ctx.ordering_term() != null) {

                for (int i = 0; i < ctx.ordering_term().size(); i++) {
                    select_stmt.addItemToListof_ordering_term(visitOrdering_term(ctx.ordering_term(i)));
                }
            }
            if (ctx.limit_expr() != null) {
                select_stmt.setLimitExpr(visitExpr(ctx.limit_expr().expr()));
                if (ctx.K_OFFSET() != null) {
                    select_stmt.setOffset(true);
                } else if (ctx.expr() != null) {
                    select_stmt.setExpr(visitExpr(ctx.expr()));
                }
            }
            select_stmt.setName("select_stmt");
            return select_stmt;
        } else
            return null;
    }

    @Override
    public Select_Core visitSelect_core(SQLParser.Select_coreContext ctx) {
        System.out.println("visitSelect_core");
        Select_Core select_core = new Select_Core();
//        Scope currentScope = scopesStack.peek();
        if (ctx.result_column() != null) {
            List<Reslult_Cloumn> reslult_cloumnList = new ArrayList<>();
            for (int i = 0; i < ctx.result_column().size(); i++) {
                reslult_cloumnList.add(visitResult_column(ctx.result_column(i)));
//                //                                               tablename.columnname
////                if(select_core.getReslult_cloumnList().get(i).getHelper_value()==select_core.getReslult_cloumnList().get(i).getTable_with_dot_column()){
//                    String table_name =  select_core.getReslult_cloumnList().get(i).getExpr().getTableName().getName();
//                    System.out.println("the table name is for test 44444 :"+ table_name);
//                    String column_name = select_core.getReslult_cloumnList().get(i).getExpr().getColumnName().getName();
//                    System.out.println("the column name is for test 33333 :"+ column_name);
////                }

            }
            select_core.setReslult_cloumnList(reslult_cloumnList);

            if (ctx.table_or_subquery() != null) {
                List<TableOrSubQuery> tableOrSubQueryList = new ArrayList<>();
                for (int i = 0; i < ctx.table_or_subquery().size(); i++) {
                    tableOrSubQueryList.add(visitTable_or_subquery(ctx.table_or_subquery(i)));
                }
                select_core.setTableOrSubQueryList(tableOrSubQueryList);
                if (ctx.where_expr() != null) {
                    select_core.setWhereExpr(visitExpr(ctx.where_expr().expr()));
                }
                if (ctx.expr() != null) {
                    List<Expr> exprs = new ArrayList<>();
                    for (int i = 0; i < ctx.expr().size(); i++) {
                        exprs.add(visitExpr(ctx.expr(i)));
                    }
                    select_core.setExprList_Group(exprs);
                    if (ctx.having_expr() != null) {
                        System.out.println("set having value ");
                        select_core.setHavingExpr(visitExpr(ctx.having_expr().expr()));
                    }
                }

            }
            if (ctx.join_clause() != null) {
                select_core.setJoin_clause(visitJoin_clause(ctx.join_clause()));
                if (ctx.where_expr() != null) {
                    select_core.setWhereExpr(visitExpr(ctx.where_expr().expr()));
                }
                if (ctx.expr() != null) {
                    List<Expr> exprs = new ArrayList<>();
                    for (int i = 0; i < ctx.expr().size(); i++) {
                        exprs.add(visitExpr(ctx.expr(i)));
                    }
                    select_core.setExprList_Group(exprs);
                }
            }
        } else if (ctx.list_of_expr() != null) {
            List<Expr> exprs = new ArrayList<>();
            for (int i = 0; i < ctx.list_of_expr().expr().size(); i++) {
                exprs.add(visitExpr(ctx.list_of_expr().expr(i)));
            }
            select_core.setExprList_Values(exprs);
            if (ctx.list_of_list_of_expr() != null) {
                List<List_Of_Expr> list_of_exprs = new ArrayList<>();
                for (int i = 0; i < ctx.list_of_list_of_expr().list_of_expr().size(); i++) {
                    list_of_exprs.add(visitList_of_expr(ctx.list_of_list_of_expr().list_of_expr(i)));
                }
                select_core.setList_of_exprList(list_of_exprs);
            }
        }

        return select_core;
    }

    @Override
    public List_Of_Expr visitList_of_expr(SQLParser.List_of_exprContext ctx) {
        List_Of_Expr list_of_expr = new List_Of_Expr();
        if (ctx.expr() != null) {
            List<Expr> exprs = new ArrayList<>();
            for (int i = 0; i < ctx.expr().size(); i++) {
                exprs.add(visitExpr(ctx.expr(i)));
            }
            list_of_expr.setExprList(exprs);
        }
        return list_of_expr;
    }

    @Override public SelectOrValue visitSelect_or_values(SQLParser.Select_or_valuesContext ctx)
    {
        int c=1;
        System.out.println("visitSelect_or_values");
        SelectOrValue selectOrValue = new SelectOrValue();
        if (ctx.result_column() != null) {
            for (int i = 0; i < ctx.result_column().size(); i++) {
                selectOrValue.addItemToListOf_reslult_coulmn(visitResult_column(ctx.result_column(i)));
            }
            if (ctx.table_or_subquery() != null) {
                for (int i = 0; i < ctx.table_or_subquery().size(); i++) {
                    selectOrValue.addItemToListOftableOrSubQuery(visitTable_or_subquery(ctx.table_or_subquery(i)));
                }
                if (ctx.join_clause() != null) {
                    selectOrValue.setJoin_clause(visitJoin_clause(ctx.join_clause()));
                }
                if (ctx.where_expr() != null) {
                    selectOrValue.setWhere_expr(visitExpr(ctx.where_expr().expr()));
                }
                if (ctx.K_GROUP() != null && ctx.expr() != null) {
                    c = ctx.expr().size();
                    for (int i = 0; i < c; i++) {
                        selectOrValue.addItemToListOfExpr(visitExpr(ctx.expr(i)));
                    }
                    if (ctx.having_expr() != null) {
                        System.out.println("the having value is ");
                        selectOrValue.setHaving_expr(visitExpr(ctx.having_expr().expr()));

                    }

                } else if (ctx.K_VALUES() != null && ctx.expr() != null) {
                    for (int i = 0; i < ctx.expr().size(); i++) {
                        selectOrValue.addItemToListOfExpr(visitExpr(ctx.expr(i)));
                    }
                }
            }

        }

        return selectOrValue;
    }


    @Override
    public Join_Clause visitJoin_clause(SQLParser.Join_clauseContext ctx) {
        System.out.println("visitJoin_clause");
        Join_Clause join_clause = new Join_Clause();
        if (ctx.table_or_subquery() != null) {
            join_clause.setTableOrSubQuery(visitTable_or_subquery(ctx.table_or_subquery(0)));
            if (ctx.join_operator() != null && ctx.table_or_subquery().size() > 1 && ctx.join_constraint() != null) {

                List<TableOrSubQuery> tableOrSubQueryList = new ArrayList<>();
                for (int i = 1; i < ctx.table_or_subquery().size(); i++) {
                    tableOrSubQueryList.add(visitTable_or_subquery(ctx.table_or_subquery(i)));
                }
                join_clause.setTableOrSubQueryList(tableOrSubQueryList);

                List<Join_Constrain> join_constrains = new ArrayList<>();

                List<Join_Opreator> join_opreators = new ArrayList<>();
                for (int i = 0; i < ctx.join_operator().size(); i++) {
                    join_opreators.add(visitJoin_operator(ctx.join_operator(i)));
                    join_constrains.add(visitJoin_constraint(ctx.join_constraint(i)));
                }
                join_clause.setJoin_opreatorList(join_opreators);

            }


        }
        return join_clause;
    }


    @Override
    public Join_Constrain visitJoin_constraint(SQLParser.Join_constraintContext ctx) {
        Join_Constrain join_constrain = new Join_Constrain();
        System.out.println("visitJoin_constraint");
        if (ctx.expr() != null) {
            join_constrain.setExpr(visitExpr(ctx.expr()));
        }
        return join_constrain;
    }

    @Override
    public Join_Opreator visitJoin_operator(SQLParser.Join_operatorContext ctx) {
        Join_Opreator join_opreator = new Join_Opreator();
        System.out.println("visitJoin_operator");
        return join_opreator;
    }

    @Override
    public TableOrSubQuery visitTable_or_subquery(SQLParser.Table_or_subqueryContext ctx) {
        System.out.println("visitTable_or_subquery");
        TableOrSubQuery tableOrSubQuery = new TableOrSubQuery();
        if (ctx.table_name() != null) {
            if (ctx.database_name() != null) {
                tableOrSubQuery.setDataBaseName(visitDatabase_name(ctx.database_name()));
            }
            tableOrSubQuery.setTableName(visitTable_name(ctx.table_name()));
            if (ctx.table_alias() != null) {
                tableOrSubQuery.setTable_alias(visitTable_alias(ctx.table_alias()));
            }
            if (ctx.index_name() != null) {
                tableOrSubQuery.setIndexName(visitIndex_name(ctx.index_name()));
            }
        } else if (ctx.table_or_subquery() != null) {
            List<TableOrSubQuery> tableOrSubQueryList = new ArrayList<>();
            for (int i = 0; i < ctx.table_or_subquery().size(); i++) {
                tableOrSubQueryList.add(visitTable_or_subquery(ctx.table_or_subquery(i)));
            }
            tableOrSubQuery.setLisTableOrSubQueries(tableOrSubQueryList);
            if (ctx.table_alias() != null) {
                tableOrSubQuery.setTable_alias(visitTable_alias(ctx.table_alias()));
            }
        } else if (ctx.join_clause() != null) {
            tableOrSubQuery.setJoin_clause(visitJoin_clause(ctx.join_clause()));
            if (ctx.table_alias() != null) {
                tableOrSubQuery.setTable_alias(visitTable_alias(ctx.table_alias()));
            }
        } else if (ctx.select_stmt() != null) {
            tableOrSubQuery.setSelect_stmt(visitSelect_stmt(ctx.select_stmt()));
            if (ctx.table_alias() != null) {
                tableOrSubQuery.setTable_alias(visitTable_alias(ctx.table_alias()));
            }
        }

        return tableOrSubQuery;
    }

    @Override
    public Reslult_Cloumn visitResult_column(SQLParser.Result_columnContext ctx) {
        System.out.println("visitResult_column");
        Reslult_Cloumn reslult_cloumn = new Reslult_Cloumn();
        if (ctx.STAR() != null) {
            reslult_cloumn.setStar(true);
           }
        else if(ctx.table_name()!=null && ctx.DOT()!=null && ctx.STAR()!=null){
            reslult_cloumn.setTableName(visitTable_name(ctx.table_name()));
            reslult_cloumn.setStar(true);
               }
        else if(ctx.expr()!=null){
            reslult_cloumn.setExpr(visitExpr(ctx.expr()));
            if (ctx.K_AS() != null && ctx.column_alias() != null) {
                reslult_cloumn.setColumn_alias(visitColumn_alias(ctx.column_alias()));
            }
                }
        return reslult_cloumn ;
    }

    @Override
    public Ordering_term visitOrdering_term(SQLParser.Ordering_termContext ctx) {
        System.out.println("visitOrdering_term");
        Ordering_term ordering_term = new Ordering_term();
        if (ctx.expr() != null) {
            ordering_term.setExpr(visitExpr(ctx.expr()));
        }
        return ordering_term;
    }

    //------------------------------------------------------table constraint ------------------------------------------
    @Override
    public AddColumn visitAddcolumn(SQLParser.AddcolumnContext ctx) {
        System.out.println("visitAddcolumn");
        AddColumn addColumn = new AddColumn();
        if (ctx.column_def() != null) {
            addColumn.setColumnDef(visitColumn_def(ctx.column_def()));
        }
        return addColumn;
    }


    @Override
    public TableConstraintPrimaryKey visitTable_constraint_primary_key(SQLParser.Table_constraint_primary_keyContext ctx) {
        System.out.println("visitTable_constraint_primary_key");
        TableConstraintPrimaryKey tableConstraintPrimaryKey = new TableConstraintPrimaryKey();
        if (ctx.indexed_column() != null) {
            for (int i = 0; i < ctx.indexed_column().size(); i++) {
                tableConstraintPrimaryKey.addItemToListOfIndexedColumn(visitIndexed_column(ctx.indexed_column(i)));
            }
        }
        return tableConstraintPrimaryKey;
    }


    @Override
    public IndexedColumn visitIndexed_column(SQLParser.Indexed_columnContext ctx) {
        System.out.println("visitIndexed_column");
        IndexedColumn indexedColumn = new IndexedColumn();
        if (ctx.column_name() != null) {
            indexedColumn.setColumnname(ctx.collation_name().use_random_name().RANDOM_NAME().getText());
            System.out.println("the column_indexed is : " + indexedColumn.getColumnname());
            if (ctx.collation_name() != null) {
                indexedColumn.setColectionname(ctx.collation_name().use_random_name().RANDOM_NAME().getText());
                System.out.println("the collaction Name is :" + indexedColumn.getColectionname());
            }
            if (ctx.K_ASC() != null) {
                indexedColumn.setASK(true);
            } else if (ctx.K_DESC() != null) {
                indexedColumn.setDESK(true);
            }
        }
        return indexedColumn;
    }

    @Override
    public ForeignKeyClause visitForeign_key_clause(SQLParser.Foreign_key_clauseContext ctx) {
        System.out.println("visitForeign_key_clause");
        ForeignKeyClause foreignKeyClause = new ForeignKeyClause();
        if (ctx.database_name() != null) {
            foreignKeyClause.setDataBaseName(visitDatabase_name(ctx.database_name()));
        }
        if (ctx.foreign_table() != null) {
            foreignKeyClause.setForeignTable(visitForeign_table(ctx.foreign_table()));
            if (ctx.fk_target_column_name() != null) {
                for (int i = 0; i < ctx.fk_target_column_name().size(); i++) {
                    foreignKeyClause.addItemToListOfFkTarget_ColumnName(visitFk_target_column_name(ctx.fk_target_column_name(i)));
                }
            }
            if (ctx.K_UPDATE() != null) {
                foreignKeyClause.setUpdate(true);
                if (ctx.K_SET() != null && ctx.K_NULL() != null) {
                    foreignKeyClause.setSetnull(true);
                } else if (ctx.K_SET() != null && ctx.K_DELETE() != null) {
                    foreignKeyClause.setSetdefault(true);
                } else if (ctx.K_CASCADE() != null) {
                    foreignKeyClause.setCascade(true);
                } else if (ctx.K_MATCH() != null) {
                    foreignKeyClause.setMatch(true);
                    foreignKeyClause.setNameMatch(visitName(ctx.name(0)));
                } else if (ctx.K_RESTRICT() != null) {
                    foreignKeyClause.setRestrict(true);
                } else if (ctx.K_NO() != null && ctx.K_ACTION() != null) {
                    foreignKeyClause.setNoaction(true);
                }
            }
        } else if (ctx.K_DELETE() != null) {
            foreignKeyClause.setDelete(true);
            if (ctx.K_SET() != null && ctx.K_NULL() != null) {
                foreignKeyClause.setSetnull(true);
            } else if (ctx.K_SET() != null && ctx.K_DELETE() != null) {
                foreignKeyClause.setSetdefault(true);
            } else if (ctx.K_CASCADE() != null) {
                foreignKeyClause.setCascade(true);
            } else if (ctx.K_MATCH() != null) {
                foreignKeyClause.setMatch(true);
                foreignKeyClause.setNameMatch(visitName(ctx.name(0)));
            } else if (ctx.K_RESTRICT() != null) {
                foreignKeyClause.setRestrict(true);
            } else if (ctx.K_NO() != null && ctx.K_ACTION() != null) {
                foreignKeyClause.setNoaction(true);
            }
        }


        return null;
    }

    public void DFS(Expr e) {
        Stack<Expr> exprStack = new Stack<>();
        exprStack.push(e);

    }


    //    -----------------------------------------------------expr----------------------------------------------------
    @Override
    public Expr visitExpr(SQLParser.ExprContext ctx) {
        System.out.println("visitExpr");
        Expr expr = new Expr();
        if (ctx.literal_value() != null) {
            expr.setLiteral_value(visitLiteral_value(ctx.literal_value()));
        }
        if (ctx.database_name() != null) {
            expr.setDataBaseName(visitDatabase_name(ctx.database_name()));
        }
        if (ctx.table_name() != null) {
            expr.setTableName(visitTable_name(ctx.table_name()));
        }
        if (ctx.column_name() != null) {
            expr.setColumnName(visitColumn_name(ctx.column_name()));
        }
        if (ctx.unary_operator() != null && ctx.expr() != null) {
            System.out.println("hy");
        }
        if (ctx.commn_expr_opreator() != null) {
            System.out.println("have expr opreator fill left and right");
            expr.setLeft(visitExpr(ctx.expr(0)));
            System.out.println("fill left");
            (expr).setRight(visitExpr(ctx.expr(1)));
            System.out.println("fill right");
        }
        return expr;
    }


    @Override
    public Literal_Value visitLiteral_value(SQLParser.Literal_valueContext ctx) {
        System.out.println("visitLiteral_value");
        Literal_Value literal_value = new Literal_Value();
        if (ctx.BLOB_LITERAL() != null) {
            literal_value.setReturnType(ctx.BLOB_LITERAL().getText());
        } else if (ctx.NUMERIC_LITERAL() != null) {
            literal_value.setReturnType(ctx.NUMERIC_LITERAL().getText());
            System.out.println("th number is : " + literal_value.getReturnType());
        } else if (ctx.K_CURRENT_TIME() != null) {
            literal_value.setReturnType(ctx.K_CURRENT_TIME().getText());
        } else if (ctx.K_CURRENT_DATE() != null) {
            literal_value.setReturnType(ctx.K_CURRENT_DATE().getText());
        } else if (ctx.K_NULL() != null) {
            literal_value.setReturnType(ctx.K_NULL().getText());
        } else if (ctx.K_CURRENT_TIMESTAMP() != null) {
            literal_value.setReturnType(ctx.K_CURRENT_TIMESTAMP().getText());
        } else if (ctx.STRING_LITERAL() != null) {
            literal_value.setReturnType(ctx.STRING_LITERAL().getText());
        }
        return literal_value;
    }

    @Override
    public ComonExprOpreator visitCommn_expr_opreator(SQLParser.Commn_expr_opreatorContext ctx) {
        System.out.println("visitCommn_expr_opreator");
        ComonExprOpreator comonExprOpreator = new ComonExprOpreator();
        comonExprOpreator.setOpreator(ctx.getText());
        System.out.println("the opreator is : " + comonExprOpreator.getOpreator());
        return comonExprOpreator;
    }

    @Override
    public Unary_Operator visitUnary_operator(SQLParser.Unary_operatorContext ctx) {
        System.out.println("visitUnary_operator");
        Unary_Operator unary_operator = new Unary_Operator();
        unary_operator.setOperator(ctx.getText());
        return unary_operator;
    }

//    -----------------------------------------------------join-------------------------------------------------


//    -----------------------------------------------names----------------------------------------

    @Override
    public FkTarget_ColumnName visitFk_target_column_name(SQLParser.Fk_target_column_nameContext ctx) {
        System.out.println("visitFk_target_column_name");
        FkTarget_ColumnName fkTarget_columnName = new FkTarget_ColumnName();
        fkTarget_columnName.setName(ctx.getText());
        return fkTarget_columnName;
    }

    @Override
    public ForeignTable visitForeign_table(SQLParser.Foreign_tableContext ctx) {
        System.out.println("visitForeign_table");
        ForeignTable foreignTable = new ForeignTable();
        foreignTable.setName(ctx.use_random_name().RANDOM_NAME().getText());
        return foreignTable;
    }

    @Override
    public DataBaseName visitDatabase_name(SQLParser.Database_nameContext ctx) {
        System.out.println("visitDatabase_name");
        DataBaseName dataBaseName = new DataBaseName();
        dataBaseName.setName(ctx.use_random_name().RANDOM_NAME().getText());
        return dataBaseName;
    }

    @Override
    public Name visitName(SQLParser.NameContext ctx) {
        System.out.println("visitName");
        Name name = new Name();
        name.setName(ctx.use_random_name().RANDOM_NAME().getText());
        System.out.println("the Name is : " + name.getName());
        return name;
    }

    @Override
    public Fk_Origin_Column_Name visitFk_origin_column_name(SQLParser.Fk_origin_column_nameContext ctx) {
        System.out.println("visitFk_origin_column_name");
        Fk_Origin_Column_Name fk_origin_column_name = new Fk_Origin_Column_Name();
        fk_origin_column_name.setName(ctx.getText());
        System.out.println("the fk_origin name : is " + fk_origin_column_name.getName());
        return fk_origin_column_name;
    }

    @Override public TableName visitTable_name(SQLParser.Table_nameContext ctx)
    {
        Scope currentScope = scopesStack.peek();
        System.out.println("visitTable_name");
        TableName tableName = new TableName();
        tableName.setName(ctx.use_random_name().RANDOM_NAME().getText());
        if(tableName.checkValidTable(currentScope  , tableName.getName())){
            System.out.println("__________________________________________________________________________________");
            System.out.println("your table name is not declare before you can not use the table before declare it");
            System.out.println("__________________________________________________________________________________");
        }
        System.out.println("the table name is : "+tableName.getName());
        return tableName;
    }

    @Override
    public ColumnName visitColumn_name(SQLParser.Column_nameContext ctx) {
        System.out.println("visitColumn_name");
        ColumnName columnName = new ColumnName();
        columnName.setName(ctx.use_random_name().RANDOM_NAME().getText());
        System.out.println("the column name is : " + columnName.getName());
        return columnName;
    }

    @Override
    public FunctionName visitFunction_name(SQLParser.Function_nameContext ctx) {
        System.out.println("visitFunction_name");
        FunctionName functionName = new FunctionName();
        functionName.setName(ctx.use_random_name().RANDOM_NAME().getText());
        System.out.println("the function name is : " + functionName.getName());
        return functionName;
    }

    @Override
    public IndexName visitIndex_name(SQLParser.Index_nameContext ctx) {
        System.out.println("visitIndex_name");
        IndexName indexName = new IndexName();
        indexName.setName(ctx.use_random_name().RANDOM_NAME().getText());
        System.out.println("the index Name name is : " + indexName.getName());
        return indexName;
    }

    @Override
    public QualifiedTableName visitQualified_table_name(SQLParser.Qualified_table_nameContext ctx) {
        System.out.println("visitQualified_table_name");
        QualifiedTableName qualifiedTableName = new QualifiedTableName();
        if (ctx.database_name() != null) {
            qualifiedTableName.setDataBaseName(visitDatabase_name(ctx.database_name()));
        }
        if (ctx.table_name() != null) {
            qualifiedTableName.setTableName(visitTable_name(ctx.table_name()));
        }
        if (ctx.index_name() != null) {
            qualifiedTableName.setIndexName(visitIndex_name(ctx.index_name()));
        }

        return qualifiedTableName;
    }

    @Override
    public Column_alias visitColumn_alias(SQLParser.Column_aliasContext ctx) {
        System.out.println("visitColumn_alias");
        Column_alias column_alias = new Column_alias();
        if (ctx.IDENTIFIER() != null) {
            column_alias.setContant(ctx.IDENTIFIER().getText());
        } else if (ctx.STRING_LITERAL() != null) {
            column_alias.setContant(ctx.STRING_LITERAL().getText());
        } else if (ctx.use_random_name() != null) {
            column_alias.setContant(ctx.use_random_name().RANDOM_NAME().getText());
        }
        return column_alias;
    }

    @Override
    public Table_alias visitTable_alias(SQLParser.Table_aliasContext ctx) {
        System.out.println("visitTable_alias");
        Table_alias table_alias = new Table_alias();
        if (ctx.IDENTIFIER() != null) {
            table_alias.setName(ctx.IDENTIFIER().getText());
            System.out.println("the table alias is : " + table_alias.getName());
        }
        return table_alias;
    }


    @Override
    public Object visitAny_name(SQLParser.Any_nameContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitKeyword(SQLParser.KeywordContext ctx) {
        return visitChildren(ctx);
    }


    public FunctionDeclaration visitFuntion(SQLParser.FuntionContext ctx) {
        System.out.println("function declaration ");
        FunctionDeclaration funcdec = new FunctionDeclaration();

        funcdec.setHeader(visitFunction_header(ctx.function_header()));
        funcdec.setBody(visitFunction_body(ctx.function_body()));

        return funcdec;
        // we should also visite function body also
    }
    // @Override public function_body visitFunction_body(SQLParser.Function_bodyContext ctx) {
    //   System.out.println("function header ");
    // function_body body = new function_body();
    //body.setGeneral(visitGrnral_creating(ctx.));
    //return body ;
    //}

    public function_header visitFunction_header(SQLParser.Function_headerContext ctx) {
        System.out.println("function_header ");
        function_header header = new function_header();
//
//        Scope functionScop = new Scope();
//        String functionName = ctx.use_random_name().getText();
//        functionScop.setId(functionName);
//        Main.symbolTable.addScope(functionScop)
        header.setName(ctx.use_random_name().getText());
        //System.out.println( " the value here "+ctx.args().size());
        Main.symbolTable.add_functions(header);
        //System.out.println("changing size"+Main.symbolTable.getFunctions().size());
        for (int i = 0; i < ctx.args().size(); i++) {
            //header.additemtoarglist();
            //header.setArg((List<args>) visitArgs(ctx.args().get(i)));
            //System.out.pr var e []=select all clum1 , colum2 from b  where a.o=l.u order by hintln(  " what will return "+ctx.args().get(i).grnral_creating().creat_without_assign().create_varible_without_assign().varible_name().use_random_name().RANDOM_NAME().getSymbol().getText());
            //header.additemtoarglist(ctx.args().get(i));
            header.add_item_to_arglist(visitArgs(ctx.args(i)));
            // if(header.additemtoarglist(visitCreating_with_assign(ctx.creating_with_assign(i))));
        }
        for (int j = 0; j < ctx.creating_with_assign().size(); j++) {
            header.add_item_to_with_assign_var_list(visitCreating_with_assign(ctx.creating_with_assign(j)));
        }
        // System.out.println("testing ");
        //  System.out.println("size of array list "+header.getArg().size());
        // header.pri();
        // we should use for loop
        // set list of parameteres

        return header;
    }


    /*  @Override
      public instructions visitNonfunctional_instruction(SQLParser.Nonfunctional_instructionContext ctx) {
          System.out.println("visit nonfunctional instruction");
          non_functional_instructions instruction = new non_functional_instructions();

          if(ctx.use_random_name() != null)
          {
              instruction = visitOne_line_if_statment_rule(ctx.one_line_if_statment_rule());
          }
          return instruction;
      }*/
    @Override
    public args visitArgs(SQLParser.ArgsContext ctx) {
        //List<args> arg = new ArrayList<args>();
        args temp = new args();
        System.out.println("visite args ");
        // arg.add(visitGrnral_creating(ctx.grnral_creating(0)));

        /// arg.add(visitGrnral_creating(ctx.grnral_creating()));

        //if(ctx.creat_without_assign()!=null)
        //{ temp.setCreating(visitGrnral_creating(ctx.grnral_creating()));

        //}

        if (ctx.creat_without_assign() != null) {
            visitCreat_without_assign(ctx.creat_without_assign());
        }


        // return arg;
        return temp;
    }

    //@Override public createarrywithoutassign visitCreate_Array_without_assign(SQLParser.Create_Array_without_assignContext ctx) {
    //  System.out.println("create Array wihtout assign ");
    //createarrywithoutassign arra = new createarrywithoutassign();


    //}
    @Override
    public gneralcreating visitGrnral_creating(SQLParser.Grnral_creatingContext ctx) {
        System.out.println("general creating ");

        gneralcreating general = new gneralcreating();
        if (ctx.creat_without_assign() != null) {

            general.setWihtoutassig(visitCreat_without_assign(ctx.creat_without_assign()));
            general.setInstrucation_name(general.getWihtoutassig().getInstrucation_name());

        }
        if (ctx.creating_with_assign() != null) {
            general.setWithassign(visitCreating_with_assign(ctx.creating_with_assign()));
            general.setInstrucation_name(general.getWithassign().getInstrucation_name());

        }
        //System.out.println("check what is going on "+general.toString());
        return general;
    }

    @Override
    public createvariablewithassign visitCreating_with_assign(SQLParser.Creating_with_assignContext ctx) {
        System.out.println("visite create with assign ");
        createvariablewithassign variable_with_assign = new createvariablewithassign();
        if (ctx.create_varible_with_assign() != null) {
            create_variable_withassign create = new create_variable_withassign();

            Symbol symbol = new Symbol();
            Type types = new Type();
            variable_with_assign.setVar_wiht_assign(visitCreate_varible_with_assign(ctx.create_varible_with_assign()));
            create = (create_variable_withassign) variable_with_assign.getVar_wiht_assign();
//            for (int i = 0; i < create.getVar().getVariable_with_opretor().size(); i++) {
//                symbol.setName(create.getVar().getVariable_with_opretor().get(i).getVariable_name());
//            }
//            if (create.getVar().getExpression().getExpression_list().getIntral_expression_value().getNUMERIC_LITERAL() != null) {
//                types.setName(Type.NUMBER_CONST);
//                symbol.setType(types);
//            }
//            if (create.getVar().getExpression().getExpression_list().getIntral_expression_value().getIdentyfire() != null) {
//                types.setName(Type.STRING_CONST);
//                symbol.setType(types);
//            }
//            if (create.getVar().getExpression().getExpression_list().getIntral_expression_value().getTure_or_False() != null)
//            {
//                types.setName(Type.BOOLEAN_CONST);
//                symbol.setType(types);
//            }
//            if(create.getVar().getExpression().getExpression_list().getIntral_expression_value().getVariable_name()!=null)
//            {
//                // we should get the varaible type search symbole initilaize same scope or scope father
//            }
//
//            System.out.println(" getting variable name "+symbol.getName());
//            System.out.println(" getting variable type "+symbol.getType().getName());

        } else if (ctx.create_json_with_assign() != null) {
            variable_with_assign.setJson_wiht_assign(visitCreate_json_with_assign(ctx.create_json_with_assign()));
            variable_with_assign.setInstrucation_name(variable_with_assign.getJson_wiht_assign().getInstrucation_name());
        } else if (ctx.create_Array_with_assign() != null) {

            variable_with_assign.setArray_with_assign(visitCreate_Array_with_assign(ctx.create_Array_with_assign()));
            variable_with_assign.setInstrucation_name(variable_with_assign.getArray_with_assign().getInstrucation_name());

        }


        return variable_with_assign;
    }

    @Override
    public create_variable_withassign visitCreate_varible_with_assign(SQLParser.Create_varible_with_assignContext ctx) {

        create_variable_withassign variable_with_assign = new create_variable_withassign();
        variable_with_assign.setVar(visitAssign_varible(ctx.assign_varible()));


        Scope currentScope = new Scope();
        currentScope = scopesStack.peek();
        Symbol createdSymbol = new Symbol();
        String name = ctx.assign_varible().use_random_name().get(0).getText();
        createdSymbol.setName(name);
        createdSymbol.setIsParam(false);
        createdSymbol.setScope(currentScope);
        Expression expression = new Expression();
        if (ctx.assign_varible().expression() != null) {
            expression = visitExpression(ctx.assign_varible().expression());
        }
        createdSymbol.setType(addTypeForVariable(expression));
        currentScope.addSymbol(name, createdSymbol);


        return variable_with_assign;
    }

    @Override
    public create_array_wiht_assign visitCreate_Array_with_assign(SQLParser.Create_Array_with_assignContext ctx) {
        create_array_wiht_assign create = new create_array_wiht_assign();
        System.out.println("visite create array with assign ");
        create.setIndex(visitArray_base_form_without_index(ctx.array_base_form_without_index()));
        create.setSide(visitLeft_side_array(ctx.left_side_array()));
        create.setInstrucation_name(create_array_wiht_assign.class.getName());
        return create;


    }

    @Override
    public array_left_side visitLeft_side_array(SQLParser.Left_side_arrayContext ctx) {
        array_left_side leftside = new array_left_side();
        System.out.println("visite array_left_side");
        if (ctx.value_left_side() != null) {
            leftside.setForm(visitValue_left_side(ctx.value_left_side()));
        }

        if (ctx.array_name() != null) {
            leftside.setName(visitUse_random_name(ctx.array_name().use_random_name()));
        }
        if (ctx.select_stmt() != null) {
            leftside.setSelect(visitSelect_stmt(ctx.select_stmt()));
        }
        if (ctx.factored_select_stmt() != null) {
            leftside.setSelect_factored(visitFactored_select_stmt(ctx.factored_select_stmt()));
        }

        return leftside;
    }

    @Override
    public array_value_form visitValue_left_side(SQLParser.Value_left_sideContext ctx) {
        int i = 0;
        array_value_form leftvalue = new array_value_form();
        System.out.println(" value is the array");
        if (ctx.array_identifier_form() != null)
        ///  {leftvalue.setID(visitArray_identifier_form(ctx.array_identifier_form()));}
        {
            leftvalue.setID(visitArray_identifier_form(ctx.array_identifier_form()));

            for (i = 0; i < leftvalue.getID().size(); i++)
                System.out.println(" value in index  " + i + leftvalue.getID().get(i).getText());
        }
        if (ctx.array_charecter_form() != null) {
            leftvalue.setID(visitArray_charecter_form(ctx.array_charecter_form()));
            for (i = 0; i < leftvalue.getID().size(); i++) {
                System.out.println(" value in index " + i + leftvalue.getID().get(i).getText());
            }

        }
        if (ctx.array_objects_form() != null) {
            leftvalue.setStatment(visitArray_objects_form(ctx.array_objects_form()));

        }
        if (ctx.array_objects_form2() != null) {
            leftvalue.setStatment(visitArray_objects_form2(ctx.array_objects_form2()));
        }
        if (ctx.array_arrayes_form() != null) {
            leftvalue.setArrayfor(visitArray_arrayes_form(ctx.array_arrayes_form()));
        }


        return leftvalue;
    }

    @Override
    public ArrayList<jsonstatment> visitArray_objects_form(SQLParser.Array_objects_formContext ctx) {
        ArrayList<jsonstatment> j = new ArrayList<jsonstatment>();
        for (int i = 0; i < ctx.json_statment().size(); i++) {

            j.add(visitJson_statment(ctx.json_statment(i)));
            System.out.println("value of json statment in list " + "in the index " + i + ctx.json_statment(i).getText().toString());
        }
        return j;
    }

    @Override
    public ArrayList<jsonstatment> visitArray_objects_form2(SQLParser.Array_objects_form2Context ctx) {
        System.out.println("visit array object form 2 ");
        ArrayList<jsonstatment> t = new ArrayList<jsonstatment>();
        t = visitArray_objects_form(ctx.array_objects_form());
        return t;
    }

    @Override
    public ArrayList<Token> visitArray_identifier_form(SQLParser.Array_identifier_formContext ctx) {
        ArrayList<Token> t = new ArrayList<Token>();

        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {

            t.add(ctx.IDENTIFIER(i).getSymbol());
        }
        return t;
    }

    @Override
    public array_array_form visitArray_arrayes_form(SQLParser.Array_arrayes_formContext ctx) {
        System.out.println("visite array of array form ");
        array_array_form forms = new array_array_form();
        for (int i = 0; i < ctx.left_side_array().size(); i++) {
            forms.adding(visitLeft_side_array(ctx.left_side_array(i)));
        }
        for (int i = 0; i < ctx.left_side_array().size(); i++) {
            System.out.println(" the value in th index " + i + ctx.left_side_array(i).toString());

        }
        return forms;
    }

    /* @Override public  ArrayList<Token> visitArray_integer_form(SQLParser.Array_integer_formContext ctx) {
         ArrayList<Token > t = new  ArrayList<Token >();

         for(int i=0;i<ctx..size();i++)
         {

             t.add(ctx.IDENTIFIER(i).getSymbol());
         }
         return t;
     }


     }*/ // arithmatic one we need it ......
    @Override
    public ArrayList<Token> visitArray_charecter_form(SQLParser.Array_charecter_formContext ctx) {
        ArrayList<Token> t = new ArrayList<Token>();

        for (int i = 0; i < ctx.ONE_CHAR_LETTER().size(); i++) {

            t.add(ctx.COMMA(i).getSymbol());
        }
        return t;

    }

    @Override
    public creat_json_with_assign visitCreate_json_with_assign(SQLParser.Create_json_with_assignContext ctx) {
        System.out.println("visite create json wiht assign ");
        creat_json_with_assign jsowith = new creat_json_with_assign();
        // if else = {}
        //else visite use randomname
        jsowith.setAssingit(visitAssign_json(ctx.assign_json()));
        jsowith.setInstrucation_name(creat_json_with_assign.class.getName());

        return jsowith;
    }

    @Override
    public jsonassign visitAssign_json(SQLParser.Assign_jsonContext ctx) {
        System.out.println("visite assign json ");
        jsonassign assjsoin = new jsonassign();
        assjsoin.setForm(visitJson_name(ctx.json_name(0)));
        if (ctx.json_statment() != null) {
            assjsoin.setJson(visitJson_statment(ctx.json_statment()));

        }
        System.out.println(" json name " + ctx.json_name(0).getText());
        if (ctx.json_name(0) != null) {
            System.out.println("json value " + ctx.json_name(0).getText());
            assjsoin.setForm(visitJson_name(ctx.json_name(0)));
        }
        return assjsoin;
    }

    @Override
    public jsonstatment visitJson_statment(SQLParser.Json_statmentContext ctx) {
        System.out.println("visit json statment ");
        jsonstatment inside_json = new jsonstatment();
        for (int i = 0; i < ctx.inside_json_statmnet().size(); i++) {
            inside_json.additemtojsonstatment(visitInside_json_statmnet(ctx.inside_json_statmnet().get(i)));
            //System.out.println(" justing testing it "+ctx.inside_json_statmnet().get(i).getText());
        }

        return inside_json;
    }

    @Override
    public inside_json_statmnet visitInside_json_statmnet(SQLParser.Inside_json_statmnetContext ctx) {
        System.out.println("visite inside json statment ");
        inside_json_statmnet inside = new inside_json_statmnet();
        inside.setName(visitUse_random_name(ctx.use_random_name()));
        inside.setValue(visitValue_json_statmnet(ctx.value_json_statmnet()));
        if (inside.getName() != null && inside.getValue() != null) {
            System.out.println("variable name " + inside.getName());
            if (inside.getValue().getStatmnet() != null) {
                System.out.println(" the value will be " + inside.getValue().getStatmnet().getName());
            }
            if (inside.getValue().getTypes() != null)
                System.out.println("the value will be " + inside.getValue().getTypes().getText());

        }
        return inside;
    }

    @Override
    public return_type visitReturn_type(SQLParser.Return_typeContext ctx) {
        System.out.println("visit return type ");
        return_type t = new return_type();
        if (ctx.IDENTIFIER() != null) {

            System.out.println(" visite identifier " + ctx.IDENTIFIER().getSymbol().getText().toString());

            t.setTt(ctx.IDENTIFIER().getSymbol());
        }
        if (ctx.varible_name() != null) {
            System.out.println("visite varaible name ");
            t.setName(visitUse_random_name(ctx.varible_name().use_random_name()));
        }
        if (ctx.ONE_CHAR_LETTER() != null) {
            System.out.println("visite one char character " + ctx.ONE_CHAR_LETTER().getSymbol().getText());
        }
        if (ctx.K_FALSE() != null) {

            System.out.println(" visitefalse  " + ctx.K_FALSE().getSymbol().getText().toString());

            t.setTt(ctx.K_FALSE().getSymbol());
        }
        if (ctx.K_TRUE() != null) {
            t.setTt(ctx.K_TRUE().getSymbol());

            System.out.println(" visite true " + ctx.K_TRUE().getSymbol().getText().toString());

        }
        if (ctx.K_NULL() != null) {

            t.setTt(ctx.K_NULL().getSymbol());

            System.out.println(" viste null " + ctx.K_NULL().getSymbol().getText().toString());
        }


        if (ctx.array_base_form_with_index() != null) {
            System.out.println(" visit array base form ");
            t.setWihindex(visitArray_base_form_with_index(ctx.array_base_form_with_index()));

        }
        if (ctx.call_function() != null) {
            System.out.println(" visit call function ");

            t.setCall(visitCall_function(ctx.call_function()));
        }
        if (ctx.assign_array() != null) {
            System.out.println(" visit assign array");
            t.setA(visitAssign_array(ctx.assign_array()));
        }
        if (ctx.assign_json() != null) {
            System.out.println(" visit assign json ");
            t.setAssign(visitAssign_json(ctx.assign_json()));
        }
        if (ctx.assign_varible() != null) {
            System.out.println(" visit assign variable");
            t.setV(visitAssign_varible(ctx.assign_varible()));
        }
        if (ctx.expression() != null) {
            System.out.println("visit boolean ");
            t.setExpression(visitExpression(ctx.expression()));
        }

        if (ctx.varible_from_object() != null) {
            t.setFrom(visitVarible_from_object(ctx.varible_from_object()));
        }
        if (ctx.varible_name() != null) {
            System.out.println("visit variable name in return type");
            t.setName(visitUse_random_name(ctx.varible_name().use_random_name()));
        }


        return t;
    }

    @Override
    public Expression visitExpression(SQLParser.ExpressionContext ctx) {
        Expression expression = new Expression();
        //cleanExpressionTree(ctx);
        expression.setExpression_list(expression_algorthim(ctx));

        int x = 1, y = 2, z = 3;
        System.out.println("visit expression");

        return expression;
    }

    public Expression_List expression_algorthim(SQLParser.ExpressionContext ctx) {
        Expression_List expression_list = new Expression_List();
        if (ctx.children.size() == 1) {
            expression_list.setIntral_expression_value(visitIntral_expression_value(ctx.intral_expression_value()));
        } else if (ctx.expression().size() == 2) {

            setOprator(ctx, expression_list);
            expression_list.setLeft_expr(expression_algorthim(ctx.expression(0)));
            expression_list.setRight_expr(expression_algorthim(ctx.expression(1)));
        } else if (ctx.PLUS_PLUS() != null || ctx.MINUS_MINUS() != null) {
            expression_list.setShortcut_statments(shortcut_Statments_Expression(ctx));
        } else if (!ctx.OPEN_PAR().isEmpty()) {
            Bracket_Expression bracket_expression = new Bracket_Expression();
            if(!ctx.expression().isEmpty()){
                bracket_expression.setExpression_list(expression_algorthim(ctx.expression(0)));
            } else if (ctx.genral_assign() != null) {
                bracket_expression.setAssign(visitGenral_assign(ctx.genral_assign()));
            }
            expression_list.setBracket_expression(bracket_expression);
        } else if (ctx.expression().size() == 3) {
            One_Line_If_Expression one_line_if_expression = new One_Line_If_Expression();
            one_line_if_expression.setBoolean_condition(expression_algorthim(ctx.expression(0)));
            one_line_if_expression.setFirstelement(expression_algorthim(ctx.expression(1)));
            one_line_if_expression.setSecond_element(expression_algorthim(ctx.expression(2)));
            expression_list.setOne_line_if_expression(one_line_if_expression);
            System.out.println("visit one line if");
        } else if (ctx.unary_operator() != null) {
            Unaray_Operator_Java unaray_operator_java = new Unaray_Operator_Java();
            unaray_operator_java.setOp(ctx.unary_operator().getText());
            unaray_operator_java.setExpression_list(expression_algorthim(ctx.expression(0)));
            expression_list.setUnaray_operator_java(unaray_operator_java);
        }

        return expression_list;
    }

    public Shortcut_Statments shortcut_Statments_Expression(SQLParser.ExpressionContext ctx) {
        System.out.println("just to make me understand ");
        Shortcut_Statments shortcut_statments = new Shortcut_Statments();
        if (ctx.intral_expression_value().varible_name() != null) {
            Variable_Name variable_name = visitVarible_name(ctx.intral_expression_value().varible_name());
            // Error_ofusing_undeclared_variabler(scopesStack.peek(),ctx.);
            shortcut_statments.setShortcut_variable_name(variable_name.getVariable_name());
        }
        if (ctx.PLUS_PLUS() != null) {
            shortcut_statments.setOprator(ctx.PLUS_PLUS().getText());
        } else if (ctx.MINUS_MINUS() != null) {
            shortcut_statments.setOprator(ctx.MINUS_MINUS().getText());
        }

        System.out.println("shortcut stored : " + shortcut_statments.getInstrucation_name());
        System.out.println(shortcut_statments.getOprator());

        return shortcut_statments;


    }

    public void setOprator(SQLParser.ExpressionContext ctx, Expression_List expression_list) {

        if (ctx.PLUS() != null) {
            expression_list.setOp(ctx.PLUS().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.MINUS() != null) {
            expression_list.setOp(ctx.MINUS().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.STAR() != null) {
            expression_list.setOp(ctx.STAR().toString());
            System.out.println("opretor :" + expression_list.getOp());

        } else if (ctx.DIV() != null) {
            expression_list.setOp(ctx.DIV().toString());
            System.out.println("opretor :"+expression_list.getOp());

        }
        else if (ctx.MOD() != null)
        {
            expression_list.setOp(ctx.MOD().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.LT2() != null) {
            expression_list.setOp(ctx.LT2().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.GT2() != null) {
            expression_list.setOp(ctx.GT2().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.GT3() != null) {
            expression_list.setOp(ctx.GT3().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.LT() != null) {
            expression_list.setOp(ctx.LT().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.GT() != null) {
            expression_list.setOp(ctx.GT().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.GT_EQ() != null) {
            expression_list.setOp(ctx.GT_EQ().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.LT_EQ() != null) {
            expression_list.setOp(ctx.LT_EQ().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.EQ() != null) {
            expression_list.setOp(ctx.EQ().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.NOT_EQ1() != null) {
            expression_list.setOp(ctx.NOT_EQ1().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.AMP() != null) {
            expression_list.setOp(ctx.AMP().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.PIPE() != null) {
            expression_list.setOp(ctx.PIPE().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.AMP2() != null) {
            expression_list.setOp(ctx.AMP2().toString());
            System.out.println("opretor :" + expression_list.getOp());
        } else if (ctx.PIPE2() != null) {
            expression_list.setOp(ctx.PIPE2().toString());
            System.out.println("opretor :" + expression_list.getOp());
        }
    }

    @Override
    public Intral_Expression_Value visitIntral_expression_value(SQLParser.Intral_expression_valueContext ctx) {
        Intral_Expression_Value intral_expression_value = new Intral_Expression_Value();

        if (ctx.varible_name() != null) {
            intral_expression_value.setVariable_name(visitVarible_name(ctx.varible_name()));
        } else if (ctx.array_base_form_with_index() != null) {
            intral_expression_value.setArray_Base_Form_With_Index(visitArray_base_form_with_index(ctx.array_base_form_with_index()));
        } else if (ctx.IDENTIFIER() != null) {
            intral_expression_value.setIdentyfire(ctx.getText());
            System.out.println("String stored : " + intral_expression_value.getIdentyfire());

        } else if (ctx.K_FALSE() != null) {
            intral_expression_value.setTure_or_False(ctx.getText());
            System.out.println("false stored : " + intral_expression_value.getTure_or_False());

        } else if (ctx.K_TRUE() != null) {
            intral_expression_value.setTure_or_False(ctx.getText());
            System.out.println("true stored : " + intral_expression_value.getTure_or_False());

        } else if (ctx.NUMERIC_LITERAL() != null) {
            intral_expression_value.setNUMERIC_LITERAL(ctx.getText());
            System.out.println("number stored : " + intral_expression_value.getNUMERIC_LITERAL());

        } else if (ctx.call_function() != null) {
            intral_expression_value.setCall_function(visitCall_function(ctx.call_function()));

        } else if (ctx.ONE_CHAR_LETTER() != null) {
            intral_expression_value.setONE_CHAR_LETTER(ctx.getText().charAt(0));
            System.out.println("Char stored : " + intral_expression_value.getONE_CHAR_LETTER());

        } else if (ctx.varible_from_object() != null) {
            intral_expression_value.setVariable_From_Object(visitVarible_from_object(ctx.varible_from_object()));
        }


        return intral_expression_value;

    }

    @Override
    public Variable_Name visitVarible_name(SQLParser.Varible_nameContext ctx) {

        Variable_Name variable_name = new Variable_Name();
        variable_name.setVariable_name(ctx.getText());
        System.out.println("variable stored : " + variable_name.getVariable_name());

  /*      Scope currentScope = scopesStack.peek();
        Symbol variableSymbol = new Symbol();
        variableSymbol.setName(variable_name.getVariable_name());
        variableSymbol.setIsParam(false);
        variableSymbol.setScope(currentScope);
        //variableSymbol.setType(); //todo type
        currentScope.addSymbol(variable_name.getVariable_name(),variableSymbol);*/


        return variable_name;
    }

    @Override
    public returnes_rule visitReturn_rule(SQLParser.Return_ruleContext ctx) {
        returnes_rule rule = new returnes_rule();
        System.out.println("visit return rule");
        rule.setT(visitReturn_type(ctx.return_type()));
        return rule;

    }

    @Override
    public value_json_statmnet visitValue_json_statmnet(SQLParser.Value_json_statmnetContext ctx) {
        value_json_statmnet value = new value_json_statmnet();
        System.out.println("visite value json statment ");

        if (ctx.IDENTIFIER() != null) {
            value.setTypes(ctx.IDENTIFIER().getSymbol());

            //System.out.println("value of identifir "+ctx.IDENTIFIER().getSymbol().getText());
        } else if (ctx.K_NULL() != null) {

            value.setTypes(ctx.K_NULL().getSymbol());
            //System.out.println("value of null"+ctx.K_NULL().getSymbol().getText());

        } else if (ctx.NUMERIC_LITERAL() != null) {
            value.setTypes(ctx.NUMERIC_LITERAL().getSymbol());
            //  System.out.println("value of numeric letter "+ctx.NUMERIC_LITERAL().getSymbol().getText());
        } else if (ctx.json_statment() != null) {
            value.setStatmnet(visitJson_statment(ctx.json_statment())); // calling it our we should assign to it
            /// System.out.println("value of json statment "+ctx.json_statment().inside_json_statmnet().);
        } else if (ctx.varible_name() != null) {
            value.setName(visitUse_random_name(ctx.varible_name().use_random_name()));
        } else if (ctx.varible_from_object() != null) {
            value.setObject(visitVarible_from_object(ctx.varible_from_object()));

        }
        return value;

    }

    @Override
    public variablefromobject visitVarible_from_object(SQLParser.Varible_from_objectContext ctx) {
        System.out.println("visite variable from object");
        variablefromobject objectes = new variablefromobject();
        if (ctx.json_name() != null && ctx.varible_name() != null) {
            objectes.setForm(visitJson_name(ctx.json_name()));

            for (int i = 0; i < ctx.varible_name().size(); i++) {
                objectes.setName(visitUse_random_name(ctx.varible_name(i).use_random_name()));

                //objectes.setName(visitUse_random_name(ctx.varible_name().use_random_name()));
            }
            //visitUse_random_name(ctx.varible_name().use_random_name()) ;
        }
        return objectes;
    }

    @Override
    public createwihtoutassign visitCreat_without_assign(SQLParser.Creat_without_assignContext ctx) {
        createwihtoutassign wihtoutassign = new createwihtoutassign();
        System.out.println("visit creating without assign ");
        // wihtoutassign =  visitCreate_varible_without_assign(ctx.create_varible_without_assign());
        if (ctx.create_varible_without_assign() != null) {
            wihtoutassign.setVar(visitCreate_varible_without_assign(ctx.create_varible_without_assign()));
            //  System.out.println("woooo"+wihtoutassign.getVar().getInstrucation_name());
            wihtoutassign.setInstrucation_name(wihtoutassign.getVar().getInstrucation_name());
        }
        if (ctx.create_Array_without_assign() != null) {
            wihtoutassign.setArray(visitCreate_Array_without_assign(ctx.create_Array_without_assign()));
            //  createarrywithoutassign s = new createarrywithoutassign();
            // s=visitCreate_Array_without_assign(ctx.create_Array_without_assign());
            //System.out.println("still checking it well "+s.getInstrucation_name());
            //wihtoutassign.setInstrucation_name( visitCreate_Array_without_assign(ctx.create_Array_without_assign()).getInstrucation_name());
            wihtoutassign.setInstrucation_name(wihtoutassign.getArray().getInstrucation_name());
        }
        if (ctx.create_json_object_without_assign() != null) {
            wihtoutassign.setJson(visitCreate_json_object_without_assign(ctx.create_json_object_without_assign()));
            wihtoutassign.setInstrucation_name(wihtoutassign.getJson().getInstrucation_name());
        }
        return wihtoutassign;

    }

    @Override
    public create_json_wihtout_assign visitCreate_json_object_without_assign(SQLParser.Create_json_object_without_assignContext ctx) {
        System.out.println("visite json wihtout assign ");
        create_json_wihtout_assign json_without_assign = new create_json_wihtout_assign();
        json_without_assign.setForm(visitJson_name(ctx.json_name()));
        ////general.setInstrucation_name( gneralcreating.class.getName());
        json_without_assign.setInstrucation_name(create_json_wihtout_assign.class.getName());

        return json_without_assign;
    }

    @Override
    public jsonform visitJson_name(SQLParser.Json_nameContext ctx) {
        System.out.println("json name ");

        jsonform form = new jsonform();
        //visitUse_random_name(ctx.use_random_name());
        form.setName(visitUse_random_name(ctx.use_random_name()));
        return form;
    }

    @Override
    public createarrywithoutassign visitCreate_Array_without_assign(SQLParser.Create_Array_without_assignContext ctx) {
        System.out.println("creaitng array wihtout assing ");
        createarrywithoutassign wihtoutassing = new createarrywithoutassign();
        wihtoutassing.setWihtoutindex(visitArray_base_form_without_index(ctx.array_base_form_without_index()));
        ////general.setInstrucation_name( gneralcreating.class.getName());
        wihtoutassing.setInstrucation_name(createarrywithoutassign.class.getName());
        //ystem.out.println(" we are here checking it "+wihtoutassing.getInstrucation_name() );

        return wihtoutassing;
    }

    @Override
    public arrayformwithoutindex visitArray_base_form_without_index(SQLParser.Array_base_form_without_indexContext ctx) {
        System.out.println("creating array base form ");
        arrayformwithoutindex wihtoutindex = new arrayformwithoutindex();
        wihtoutindex.setName(visitArray_name(ctx.array_name()));
        //System.out.println( " array name "+wihtoutindex.getName());
        return wihtoutindex;
    }

    @Override
    public String visitArray_name(SQLParser.Array_nameContext ctx) {
        String name = "";
        System.out.println("visite array name ");
        name = visitUse_random_name(ctx.use_random_name());
        System.out.println("array name " + name);
        return name;
    }

    @Override
    public creatingvariabelwithoutassing visitCreate_varible_without_assign(SQLParser.Create_varible_without_assignContext ctx) {
        System.out.println("creatingvariabelwithoutassing");
        creatingvariabelwithoutassing creatvaribelwihtout = new creatingvariabelwithoutassing();
        //  Error_ofusing_undeclared_variabler( ,ctx.varible_name().use_random_name().getText());
        creatvaribelwihtout.setN(visitUse_random_name(ctx.varible_name().use_random_name()));
        creatvaribelwihtout.setInstrucation_name(creatingvariabelwithoutassing.class.getName());

        Scope currentScope = scopesStack.peek();
        Symbol variableSymbol = new Symbol();
        Type type = new Type();
        variableSymbol.setIsParam(false);
        String name = ctx.varible_name().use_random_name().getText();
        type.setName(Type.UNDEFINDED);
        variableSymbol.setName(name);
        variableSymbol.setScope(currentScope);
        variableSymbol.setType(type);
        currentScope.addSymbol(name, variableSymbol);

        return creatvaribelwihtout;

    }

    @Override
    public String visitUse_random_name(SQLParser.Use_random_nameContext ctx) {


        String name = "";
        if (ctx.RANDOM_NAME() != null) {
            name = ctx.RANDOM_NAME().getSymbol().getText();
        }


       /* StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stacktrace[3];
        String callingmethodName = stackTraceElement.getMethodName();
        String createVariableMethode = "visitCreate_varible_with_assign";

        if(callingmethodName.equals(createVariableMethode))
        {
            Scope currentScope =  scopesStack.peek();
            Symbol variableSymbol = new Symbol();
            variableSymbol.setIsParam(false);
            variableSymbol.setName(name);
            variableSymbol.setScope(currentScope);
//        variableSymbol.setType(); //todo add type
            currentScope.addSymbol(name , variableSymbol);

        }
*/


        System.out.println("visitUse_random_name: " + name);
        return name;
    }

    @Override
    public function_body visitFunction_body(SQLParser.Function_bodyContext ctx) {
        System.out.println("visit function body");
        function_body function_body = new function_body();
        instructions ins;

//        Scope functionScope = Main.symbolTable.getScopes().get(Main.symbolTable.getScopes().size() - 1);
//        scopesStack.push(functionScope);


        for (int i = 0; i < ctx.children.size(); i++) {
            System.out.println("---------------");
            if (ctx.children.get(i) instanceof SQLParser.InstructionsContext) {

                function_body.addNode(visitInstructions((SQLParser.InstructionsContext) ctx.children.get(i)));

            } else if (ctx.children.get(i) instanceof SQLParser.Sub_function_bodyContext) {
                function_body.addNode(visitSub_function_body((SQLParser.Sub_function_bodyContext) ctx.children.get(i)));
            }
        }


        // System.out.println(((While_Rule)function_body.getInstructions().get(2)).getBoolean_infunction_statment().getBoolean_exprs().get(0).getBoolean_exprs_list().get(0).getTermenal_node());

       /* for(int i =0 ; i<ctx.sub_function_body().instructions().size() ; i++)
        {
          System.out.println(function_body.getInstructions().get(i).getInstrucation_name());
            //System.out.println(((returnes_rule)((do_while)function_body.getInstructions().get(i)).getInstructions().get(i)).getT().getName());
   //System.out.println("return rule test :"+((if_else)function_body.getInstructions().get(i)).getLoop());

        }*/
       /* if(ctx.sub_function_body()!=null)

        {
            function_body.setSub_one(visitSub_function_body(ctx.sub_function_body()));
        }*/
        if (ctx.return_rule() != null) {
            function_body.setR(visitReturn_rule(ctx.return_rule()));
        }

        /*System.out.println("checkng if it  is working well");
        for(int i=0;i<function_body.getSub_one().getInstructions().size();i++)
        {
            System.out.println("in the index "+i+ " the instruction we have "+function_body.getSub_one().getInstructions().get(i).getInstrucation_name());
        }*/
        return function_body;
    }

    /* public sub_function_body visitSub_function_body(SQLParser.Sub_function_bodyContext ctx){
         sub_function_body s = new sub_function_body();
         if(ctx.instructions()!=null)
         {
             for(int i=0;i<ctx.instructions().size();i++) {
                 //System.out.println("the instruction "+ctx.instructions()
                 instructions in = new instructions();
                 in= visitInstructions(ctx.instructions(i));
                 System.out.println("judt checking what is wrong with it "+in.getInstrucation_name());
                 //s.add_instruction_to_list(visitInstructions(ctx.instructions(i)));
             }
         }

        return s;

     }*/
    @Override
    public sub_function_body visitSub_function_body(SQLParser.Sub_function_bodyContext ctx) {
        sub_function_body sub_function_body = new sub_function_body();
        System.out.println("visit Sub function body");

        Scope subFucntionScope = new Scope();
        subFucntionScope.setId("sub_function_body_" + ctx.hashCode());
        subFucntionScope.setParent(scopesStack.peek());
        scopesStack.push(subFucntionScope);

        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.children.get(i) instanceof SQLParser.InstructionsContext) {
                sub_function_body.addNode(visitInstructions((SQLParser.InstructionsContext) ctx.children.get(i)));
            } else if (ctx.children.get(i) instanceof SQLParser.Sub_function_bodyContext) {
                sub_function_body.addNode(visitSub_function_body((SQLParser.Sub_function_bodyContext) ctx.children.get(i)));
            }
            else if (ctx.children.get(i) instanceof SQLParser.Sql_stmt_listContext) {
                sub_function_body.addNode(visitSql_stmt_list((SQLParser.Sql_stmt_listContext)ctx.children.get(i)));
            }
        }
        Main.symbolTable.addScope(scopesStack.pop());

        return sub_function_body;
    }

    @Override
    public instructions visitFunctional_instruction(SQLParser.Functional_instructionContext ctx) {
        functional_instuctions instructions = new functional_instuctions();

        if (ctx.while_rule() != null) {
            Scope whileScope = new Scope();
            whileScope.setParent(scopesStack.peek());
            whileScope.setId(ctx.while_rule().K_WHILE() + "_" + ctx.while_rule().hashCode());

            instructions = visitWhile_rule(ctx.while_rule());
            scopesStack.push(whileScope);

            if (ctx.instructions().size() != 0) {
                for (int i = 0; i < ctx.instructions().size(); i++) {
                    instructions.getInstructions().add(visitInstructions(ctx.instructions(i)));


                }
            }
            if (ctx.exiting_loops() != null) {
                visitExiting_loops(ctx.exiting_loops());
            }
            Main.symbolTable.addScope(scopesStack.pop());

        } else if (ctx.foreach() != null) {

            Scope foreachScope = new Scope();
            foreachScope.setParent(scopesStack.peek());
            foreachScope.setId(ctx.foreach().K_FOREACH().getText() + "_" + ctx.foreach().hashCode());

            instructions = visitForeach(ctx.foreach());
            scopesStack.push(foreachScope);

            for (int i = 0; i < ctx.instructions().size(); i++) {
                instructions.getInstructions().add(visitInstructions(ctx.instructions(i)));

            }
            if (ctx.exiting_loops() != null) {
                visitExiting_loops(ctx.exiting_loops());

            }
            Main.symbolTable.addScope(scopesStack.pop());
        } else if (ctx.for_loop_rule() != null) {

            Scope forScope = new Scope();
            forScope.setId(ctx.for_loop_rule().K_FOR() + "_" + ctx.for_loop_rule().hashCode());
            forScope.setParent(scopesStack.peek());

            instructions = visitFor_loop_rule(ctx.for_loop_rule());
            scopesStack.push(forScope);
            for (int i = 0; i < ctx.instructions().size(); i++) {
                instructions.getInstructions().add(visitInstructions(ctx.instructions(i)));

            }
            if (ctx.exiting_loops() != null) {
                visitExiting_loops(ctx.exiting_loops());
            }
            Main.symbolTable.addScope(scopesStack.pop());

        }


        return instructions;
    }


    @Override
    public exting_oop visitExiting_loops(SQLParser.Exiting_loopsContext ctx) {
        exting_oop c = new exting_oop();
        if (ctx.K_BREAK() != null)
            System.out.println("visite break rule" + ctx.K_BREAK().getSymbol().getText());
        if (ctx.K_CONTINUE() != null) {
            System.out.println("visite continue rule " + ctx.K_CONTINUE().getSymbol().getText());
        }
        if (ctx.return_rule() != null) {
            c.setR(visitReturn_rule(ctx.return_rule()));
        }
        return c;
    }

    @Override
    public instructions visitInstructions(SQLParser.InstructionsContext ctx) {
        System.out.println("visit instruction");
        //System.out.println(ctx.getText());
        //System.out.println(ctx.functional_instruction().instructions(0).functional_instruction());

        instructions instructions = new instructions();

        if (ctx.functional_instruction() != null) {

            if (ctx.functional_instruction().if_else_rule() != null) {

                instructions = visitIf_else_rule(ctx.functional_instruction().if_else_rule());
                // System.out.println("size ))))))))))"+ctx.functional_instruction().instructions().size());

            } else if (ctx.functional_instruction().do_while() != null) {
                instructions = visitDo_while(ctx.functional_instruction().do_while());

            } else if (ctx.functional_instruction().while_rule() != null) {
                instructions = visitFunctional_instruction(ctx.functional_instruction());
            } else if (ctx.functional_instruction().foreach() != null) {
                instructions = visitFunctional_instruction(ctx.functional_instruction());
            } else if (ctx.functional_instruction().switch_rule() != null) {
                instructions = visitSwitch_rule(ctx.functional_instruction().switch_rule());
            } else if (ctx.functional_instruction().for_loop_rule() != null) {
                //instructions = visitFor_loop_rule(ctx.functional_instruction().for_loop_rule());
                instructions = visitFunctional_instruction(ctx.functional_instruction());
            }


        } else if (ctx.nonfunctional_instruction() != null) {
            if (ctx.nonfunctional_instruction().grnral_creating() != null) {
                instructions = visitGrnral_creating(ctx.nonfunctional_instruction().grnral_creating());
                // System.out.println("check what we have in gneral creating "+instructions.getInstrucation_name());
            } else if (ctx.nonfunctional_instruction().genral_assign() != null) {
                instructions = visitGenral_assign(ctx.nonfunctional_instruction().genral_assign());
            }
            else if (ctx.nonfunctional_instruction().call_function() != null)
            {
                instructions = visitCall_function(ctx.nonfunctional_instruction().call_function());
            } else if (ctx.nonfunctional_instruction().print_statment() != null) {
                instructions = visitPrint_statment(ctx.nonfunctional_instruction().print_statment());
            } else if (ctx.nonfunctional_instruction().shortcut_statments() != null) {
                instructions = visitShortcut_statments(ctx.nonfunctional_instruction().shortcut_statments());
            } else if (ctx.nonfunctional_instruction().create_aggregation_function() != null) {
                instructions.setAggregation_function(visitCreate_aggregation_function(ctx.nonfunctional_instruction().create_aggregation_function()));
            }
            else if(ctx.nonfunctional_instruction().create_table_stmt() != null){
                instructions  = visitCreate_table_stmt(ctx.nonfunctional_instruction().create_table_stmt());
            }
            else if(ctx.nonfunctional_instruction().create_type() != null){
                instructions = visitCreate_type(ctx.nonfunctional_instruction().create_type());
            }
            else if(ctx.nonfunctional_instruction().factored_select_stmt()!= null){
                instructions = visitFactored_select_stmt(ctx.nonfunctional_instruction().factored_select_stmt());
            }

            //todo complete it else if (ctx.nonfunctional_instruction().one_line_if_instruction()!=null)


        }
        // for(int i=0;i<ctx.functional_instruction().instructions().size();i++)
        //   System.out.println("^^^^^^^^"+ctx.functional_instruction().instructions(i).toString());

        return instructions;
    }

    @Override
    public instructions visitIf_else_rule(SQLParser.If_else_ruleContext ctx) {
        System.out.println("visit if rule");
        if_else ins = new if_else();
        ins.setInstrucation_name(if_else.class.getName());
        ins.setExpression(visitExpression(ctx.if_rule().expression()));

        Scope parentScope = scopesStack.peek();
        System.out.println(parentScope.getId());

        if (ctx.if_rule() != null) {

            Scope currentScope = new Scope();
            String scopeName = ctx.if_rule().K_IF().getText() + ctx.if_rule().hashCode();
            currentScope.setId(scopeName);
            currentScope.setParent(parentScope);
            scopesStack.push(currentScope);

            for (int i = 0; i < ctx.if_rule().instructions().size(); i++) {
                ins.addinstruction(visitInstructions(ctx.if_rule().instructions(i)));
            }

            // System.out.println("check the value in the returnign in if "+ctx.if_rule().returning_in_if().getText().toString().toString());
            //   if(ctx.if_rule().returning_in_if()!=null) {
            //System.out.println("here we are ");
            //ins.setR(visitReturning_in_if(ctx.if_rule().returning_in_if())
            // ins.setLoop( (exting_oop) visitReturn_rule(ctx.if_rule().return_rule()));
            // exting_oop e ;

            /*if(ctx.if_rule().return_rule()!=null) {
               functional_instuctions  i = new if_else();
i.setLoop((exting_oop) ctx.if_rule().return_rule().K_RETURN());
i.setLoop(visitExiting_loops((SQLParser.Exiting_loopsContext)ctx.if_rule().return_rule().K_RETURN()));
            }*/
            if (ctx.if_rule().return_rule() != null) {
                returnes_rule r = new returnes_rule();
                r = visitReturn_rule(ctx.if_rule().return_rule());
                exting_oop e = new exting_oop();
                e.setR(r);
                ins.setLoop(e);
                // System.out.println("****************the id for object exiting loop "+e.toString());
            }

            Main.symbolTable.addScope(scopesStack.pop());


            if (ctx.else_if_rule() != null) {
                for (int i = 0; i < ctx.else_if_rule().size(); i++) {
                    System.out.println("visit else_if");
                    else_if_rule else_if_rule = new else_if_rule();
                    ins.add_Else_if_rule_in_if(else_if_rule);
                    System.out.println("else if:" + ins.getElse_if());
                    else_if_rule.setExpression(visitExpression(ctx.else_if_rule().get(i).expression()));

                    Scope elseifScop = new Scope();
                    String elseifeName = ctx.else_if_rule().get(i).K_ELSE_IF().getText() + ctx.else_if_rule().get(i).hashCode();
                    elseifScop.setId(elseifeName);
                    elseifScop.setParent(parentScope);
                    scopesStack.push(elseifScop);

                    for (int j = 0; j < ctx.else_if_rule().get(i).instructions().size(); j++) {

                        else_if_rule.addinstruction(visitInstructions(ctx.else_if_rule().get(i).instructions().get(j)));

                    }

                    if (ctx.else_if_rule(i).return_rule() != null) {
                        returnes_rule r = new returnes_rule();
                        r = visitReturn_rule(ctx.else_if_rule(i).return_rule());
                        exting_oop e = new exting_oop();
                        e.setR(r);
                        ins.setLoop(e);
                        //System.out.println("****************the id for object exiting loop "+e.toString());

                    }
                    Main.symbolTable.addScope(scopesStack.pop());
                }

            }
            if (ctx.else_rulse() != null) {
                System.out.println("visit else");
                else_rule else_rule = new else_rule();
                ins.setElse_rule(else_rule);
                System.out.println("else :" + ins.getElse_rule());

                Scope elseScope = new Scope();
                String elseName = ctx.else_rulse().K_ELSE().getText() + ctx.else_rulse().hashCode();
                elseScope.setId(elseName);
                elseScope.setParent(parentScope);
                scopesStack.push(elseScope);

                for (int i = 0; i < ctx.else_rulse().instructions().size(); i++) {
                    else_rule.addinstruction(visitInstructions(ctx.else_rulse().instructions(i)));

                }

            /*if(ctx.else_rulse().returning_in_if()!=null)
                ins.setR(visitReturning_in_if(ctx.else_rulse().returning_in_if()));*/
                if (ctx.else_rulse().return_rule() != null) {
                    returnes_rule r = new returnes_rule();
                    r = visitReturn_rule(ctx.else_rulse().return_rule());
                    exting_oop e = new exting_oop();
                    e.setR(r);
                    ins.setLoop(e);
                }
                Main.symbolTable.addScope(scopesStack.pop());

            }
        }
        // testing it

        return ins;

    }


    @Override
    public instructions visitPrint_statment(SQLParser.Print_statmentContext ctx) {
        System.out.println("visit Prints");
        Print print = new Print();
        print.setInstrucation_name(Print.class.getName());
        if (ctx.indisde_the_print() != null) {
            if (ctx.indisde_the_print() != null) {
                for (int i = 0; i < ctx.indisde_the_print().size(); i++) {

                    if (ctx.indisde_the_print().get(i) != null) {
                        print.getPrints().add(visitIndisde_the_print(ctx.indisde_the_print().get(i)));
                    }
                }
            }

        }
        return print;
    }

    @Override
    public Inside_the_print visitIndisde_the_print(SQLParser.Indisde_the_printContext ctx) {
        System.out.println("visit inside print");
        Inside_the_print inside_the_print = new Inside_the_print();

        if (ctx.expression() != null) {
            inside_the_print.setExpression(visitExpression(ctx.expression()));

        } else if (ctx.varible_from_object() != null) {
            inside_the_print.setVariable_from_object(visitVarible_from_object(ctx.varible_from_object()));
            System.out.println(inside_the_print.getVariable_from_object());
        } else if (ctx.IDENTIFIER() != null) {
            inside_the_print.setIdentifire(ctx.IDENTIFIER().getText());
            System.out.println(inside_the_print.getIdentifire());
        } else if (ctx.array_base_form_with_index() != null) {
            inside_the_print.setArray_base_with_index(visitArray_base_form_with_index(ctx.array_base_form_with_index()));
            System.out.println(inside_the_print.getArray_base_with_index());
        } else if (ctx.use_random_name() != null) {
            inside_the_print.setVariable_name(visitUse_random_name(ctx.use_random_name()));
            System.out.println(inside_the_print.getVariable_name());
        } else if (ctx.call_function() != null) {
            inside_the_print.setCallFunction(visitCall_function(ctx.call_function()));
            System.out.println(inside_the_print.getCallFunction());
        }

        return inside_the_print;
    }

    @Override
    public instructions visitDo_while(SQLParser.Do_whileContext ctx) {
        System.out.println("visit do while");
        do_while ins = new do_while();
        ins.setInstrucation_name(do_while.class.getName());
        ins.setExpression(visitExpression(ctx.while_rule().expression()));

        Scope dowhileScope = new Scope();
        dowhileScope.setId(ctx.K_DO().getText() + "_" + ctx.hashCode());
        dowhileScope.setParent(scopesStack.peek());
        scopesStack.push(dowhileScope);

        for (int i = 0; i < ctx.instructions().size(); i++) {
            ins.addinstruction(visitInstructions(ctx.instructions(i)));
        }
        if (ctx.exiting_loops() != null) {
            ins.setLoop(visitExiting_loops(ctx.exiting_loops()));

        }
        Main.symbolTable.addScope(scopesStack.pop());


        return ins;
    }

    @Override
    public functional_instuctions visitFor_loop_rule(SQLParser.For_loop_ruleContext ctx) {
        System.out.println("visit for rule");
        For_Loop_Rule for_loop_rule = new For_Loop_Rule();
        for_loop_rule.setInstrucation_name(For_Loop_Rule.class.getName());

        if (ctx.expression() != null) {
            for_loop_rule.setExpression(visitExpression(ctx.expression()));
        } else if (ctx.inside_for_loop(0) != null) {
            for_loop_rule.setLeft_inside_for_loop(visitInside_for_loop(ctx.inside_for_loop(0)));
        } else if (ctx.create_varible_with_assign() != null) {
            for_loop_rule.setVar_with_asgn(visitCreate_varible_with_assign(ctx.create_varible_with_assign()));
        } else if (ctx.create_varible_without_assign() != null) {
            for_loop_rule.setVar_without_asgn(visitCreate_varible_without_assign(ctx.create_varible_without_assign()));
        }
        if (ctx.inside_for_loop(1) != null) {
            for_loop_rule.setRight_inside_for_loop(visitInside_for_loop(ctx.inside_for_loop(1)));
        }
        return for_loop_rule;
    }

    @Override
    public Inside_for_loop visitInside_for_loop(SQLParser.Inside_for_loopContext ctx) {
        Inside_for_loop inside_for_loop = new Inside_for_loop();
        if (ctx.expression() != null) {
            inside_for_loop.setExpression(visitExpression(ctx.expression()));
        } else if (ctx.assign_array() != null) {
            inside_for_loop.setAssign_array(visitAssign_array(ctx.assign_array()));
        } else if (ctx.assign_varible() != null) {
            inside_for_loop.setVar(visitAssign_varible(ctx.assign_varible()));

        } else if (ctx.shortcut_statments() != null) {
            inside_for_loop.setShortcut_statments(visitShortcut_statments(ctx.shortcut_statments()));
        } else if (ctx.create_Array_without_assign() != null) {
            inside_for_loop.setCreate_arry_without_assign(visitCreate_Array_without_assign(ctx.create_Array_without_assign()));
        }
        return inside_for_loop;
    }

    @Override
    public functional_instuctions visitWhile_rule(SQLParser.While_ruleContext ctx) {
        System.out.println("visit while");
        While_Rule ins = new While_Rule();
        ins.setInstrucation_name(While_Rule.class.getName());
        ins.setExpression(visitExpression(ctx.expression()));
        return ins;
    }

    @Override
    public functional_instuctions visitForeach(SQLParser.ForeachContext ctx) {
        System.out.println("visit Foreach");
        Foreach ins = new Foreach();
        ins.setInstrucation_name(Foreach.class.getName());
        ins.setLoop_variable(visitUse_random_name(ctx.use_random_name(0)));
        ins.setLoop_variable(visitUse_random_name(ctx.use_random_name(1)));
        ArrayList<Object> d = new ArrayList<Object>();

        return ins;
    }

    @Override
    public CallFunction visitCall_function(SQLParser.Call_functionContext ctx) {
        System.out.println("visit call function");
        CallFunction ins = new CallFunction();
        ins.setInstrucation_name(CallFunction.class.getName());
        Error_UNdeclared_Function(ctx.use_random_name().getText());
        ins.setFunction_name(ctx.use_random_name().getText());
        for (int i = 0; i < ctx.prameters().size(); i++) {
            ins.getParameters().add(visitPrameters(ctx.prameters(i)));
        }
        return ins;
    }

    @Override
    public Parameter visitPrameters(SQLParser.PrametersContext ctx) {
        System.out.println("visist parametar");
        Parameter parameter = new Parameter();

        if (ctx.NUMERIC_LITERAL() != null)
            parameter.setNumber(ctx.NUMERIC_LITERAL().getText());
        else if (ctx.IDENTIFIER() != null) {
            parameter.setIdentifire(ctx.IDENTIFIER().getText());
        } else if (ctx.call_function() != null) {
            parameter.setCallFunction(visitCall_function(ctx.call_function()));
        } else if (ctx.use_random_name() != null) {
            parameter.setVariable_name(visitUse_random_name(ctx.use_random_name()));
        } else if (ctx.array_base_form_with_index() != null) {
            parameter.setArray_base_with_index(visitArray_base_form_with_index(ctx.array_base_form_with_index()));
        } else if (ctx.varible_from_object() != null) {
            parameter.setVariable_from_object(visitVarible_from_object(ctx.varible_from_object()));
        } else if (ctx.ONE_CHAR_LETTER() != null) {
            parameter.setOne_char(ctx.ONE_CHAR_LETTER().getText());
        } else if (ctx.genral_assign() != null) {
            parameter.setGeneral_assign(visitGenral_assign(ctx.genral_assign()));
        }
        return parameter;
    }

    @Override
    public assignment visitGenral_assign(SQLParser.Genral_assignContext ctx) {
        System.out.println("General Assign");
        assignment ins = new assignment();
        ins.setInstrucation_name(assignment.class.getName());

        if (ctx.assign_varible() != null) {
            ins.setVar(visitAssign_varible(ctx.assign_varible()));
        boolean isTypeValid = checkExpressionTypeValid(ins.getVar().getExpression());
            if (isTypeValid) {
                if (compareTwoTypes(getVariableType(ins.getVar().getVariable_with_opretor().get(0).getVariable_name())
                        , getFirstExpritionType(ins.getVar().getExpression()))) {

                    getVariableType(ins.getVar().getVariable_with_opretor().get(0).getVariable_name()).
                            setName(getFirstExpritionType(ins.getVar().getExpression()).getName());
                }}
       } else if (ctx.assign_array() != null)
        {
            ins.setArray(visitAssign_array(ctx.assign_array()));
        } else if (ctx.assign_json() != null) {
            ins.setJson(visitAssign_json(ctx.assign_json()));
        }
        return ins;
    }

    @Override
    public assign_variable visitAssign_varible(SQLParser.Assign_varibleContext ctx) {
        System.out.println("visit variable assign");
        assign_variable var = new assign_variable();


        if (ctx.use_random_name() != null) {
            for (int i = 0; i < ctx.use_random_name().size(); i++) {
                Variable_with_opretor variable_with_opretor = new Variable_with_opretor();
                variable_with_opretor.setVariable_name(visitUse_random_name(ctx.use_random_name().get(i)));
                if (ctx.any_arithmetic_oprator() != null && ctx.any_arithmetic_oprator().size() != 0) {
                    variable_with_opretor.setOperator(ctx.any_arithmetic_oprator().get(i).getText());
                }
                var.getVariable_with_opretor().add(variable_with_opretor);
            }

        }
        if (ctx.expression() != null) {
            Expression expression = visitExpression(ctx.expression());
            var.setExpression(expression);
        }
        if(ctx.factored_select_stmt()!= null){
            var.setFactored(visitFactored_select_stmt(ctx.factored_select_stmt()));
        }
        if(ctx.select_stmt() != null){
            var.setSelect(visitSelect_stmt(ctx.select_stmt()));
        }

        return var;
    }

    @Override
    public assign_Array visitAssign_array(SQLParser.Assign_arrayContext ctx) {
        System.out.println("visit assign_array");
        assign_Array arry = new assign_Array();

        Array_base_form_with_operetor array_base_form_with_operetor = new Array_base_form_with_operetor();
        array_base_fom_with_index array_base_with_index = new array_base_fom_with_index();
        if (ctx.array_base_form_with_index() != null) {
            for (int i = 0; i < ctx.any_arithmetic_oprator().size(); i++) {
                if (ctx.any_arithmetic_oprator() != null) {
                    if (ctx.any_arithmetic_oprator().get(i) != null)
                        array_base_form_with_operetor.setOpretor(ctx.any_arithmetic_oprator().get(i).getText());
                }
            }
            for (int i = 0; i < ctx.array_base_form_with_index().size(); i++) {

                array_base_form_with_operetor.getArray_base_fom().add(visitArray_base_form_with_index(ctx.array_base_form_with_index(i)));
                arry.getArray_base_form_with_operetors().add(array_base_form_with_operetor);
            }
        }
        if (ctx.expression() != null) {
            arry.setExpression(visitExpression(ctx.expression()));
        }
        if (ctx.select_stmt() != null) {
            arry.setSelect(visitSelect_stmt(ctx.select_stmt()));
        }
        if (ctx.factored_select_stmt() != null) {
            arry.setSelect_factored(visitFactored_select_stmt(ctx.factored_select_stmt()));
        }

        return arry;
    }

    public AggregationFunction visitCreate_aggregation_function(SQLParser.Create_aggregation_functionContext ctx) {
        AggregationFunction A = new AggregationFunction();
        System.out.println("visite aggregation function ");
        if (ctx.use_random_name() != null) {
            A.setAggregationFunctionName(visitUse_random_name(ctx.use_random_name(0)));
        }

         //A.setJat_path(ctx.IDENTIFIER().toString());
        A.setClassName(ctx.use_random_name(1).getText());
        A.setMethodName(ctx.use_random_name(2).getText());
        A.setReturnType(ctx.use_random_name(3).getText());// here sgould we viste retur  type
        if (ctx.parames().size() != 0) {
            System.out.println("the size of parametars " + ctx.use_random_name().size());
            for (int i = 0; i < ctx.parames().size(); i++) {
                A.add_parama(visitParames(ctx.parames(i)));
            }
        }


        return A;

    }

    public parametes visitParames(SQLParser.ParamesContext ctx) {
        System.out.println("visite params ");
        parametes p = new parametes();
        if (ctx.K_BOOLEAN() != null) {

            p.setStrng(ctx.K_BOOLEAN().toString());
            System.out.println("visite boolean ");
        }
        if (ctx.K_NUMBER() != null) {

            p.setStrng(ctx.K_NUMBER().toString());
            System.out.println("visite number");
        }
        if (ctx.K_STRING() != null) {

            p.setStrng(ctx.K_STRING().toString());
            System.out.println("visite string");
        }
        if (ctx.column_name() != null) {

            p.setStrng(visitColumn_name(ctx.column_name()).toString());
        }
        if (ctx.table_name() != null) {

            p.setStrng(visitTable_name(ctx.table_name()).toString());
        }


        return p;
    }

    @Override
    public Shortcut_Statments visitShortcut_statments(SQLParser.Shortcut_statmentsContext ctx) {
        Shortcut_Statments shortcut_statments = new Shortcut_Statments();
        Error_ofusing_undeclared_variabler(scopesStack.peek(), ctx.use_random_name().getText());
        if (ctx.use_random_name() != null) {
            shortcut_statments.setShortcut_variable_name(visitUse_random_name(ctx.use_random_name()));

        }

        if (ctx.PLUS_PLUS() != null) {
            shortcut_statments.setOprator(ctx.PLUS_PLUS().getText());
        } else if (ctx.MINUS_MINUS() != null) {
            shortcut_statments.setOprator(ctx.MINUS_MINUS().getText());
        }

        System.out.println("shortcut stored : " + shortcut_statments.getInstrucation_name());
        System.out.println(shortcut_statments.getOprator());


        return shortcut_statments;
    }

    @Override
    public array_base_fom_with_index visitArray_base_form_with_index(SQLParser.Array_base_form_with_indexContext ctx) {

        System.out.println("visit Array base form with index ");
        array_base_fom_with_index array_base_with_index = new array_base_fom_with_index();
        Array_base_form_variables array_base_form_variables = new Array_base_form_variables();
        for (int j = 0; j < ctx.NUMERIC_LITERAL().size(); j++) {
            if (ctx.NUMERIC_LITERAL() != null) {
                array_base_form_variables.setNumber(ctx.NUMERIC_LITERAL().get(j).getText());
            }
            for (int i = 0; i < ctx.varible_name().size(); i++) {
                if (ctx.varible_name() != null) {
                    array_base_form_variables.setVariable_name(visitUse_random_name(ctx.varible_name().get(j).use_random_name()));
                }
            }
            for (int i = 0; i < ctx.expression().size(); i++) {

                if (ctx.expression().get(j) != null) {
                    array_base_form_variables.setExpression(visitExpression(ctx.expression().get(j)));
                }
            }
            array_base_with_index.getArray_base_form_variables().add(array_base_form_variables);
        }
        return array_base_with_index;
    }

    @Override
    public instructions visitSwitch_rule(SQLParser.Switch_ruleContext ctx) {

        System.out.println("visit Switch");
        Switch ins = new Switch();
        ins.setInstrucation_name(Switch.class.getName());

        Scope switchScope = new Scope();

        switchScope.setId(ctx.K_SWITCH().getText() + "_" + ctx.hashCode());
        switchScope.setParent(scopesStack.peek());

        if (ctx.use_random_name() != null) {
            ins.setVariable_name(visitUse_random_name(ctx.use_random_name()));
        } else if (ctx.ONE_CHAR_LETTER() != null) {
            ins.setTermenal_node(ctx.ONE_CHAR_LETTER().getSymbol().getText());
        } else if (ctx.NUMERIC_LITERAL() != null) {
            ins.setTermenal_node(ctx.NUMERIC_LITERAL().getSymbol().getText());
        } else if (ctx.call_function() != null) {
            ins.setTermenal_node(ctx.call_function().getText());
        } else if (ctx.varible_from_object() != null) {
            ins.setTermenal_node(ctx.varible_from_object().getText());
        } else if (ctx.expression() != null) {
            ins.setExpression(visitExpression(ctx.expression()));
        }
        scopesStack.push(switchScope);
        if (ctx.case_rule() != null) {
            for (int i = 0; i < ctx.case_rule().size(); i++) {
                ins.getCases().add(visitCase_rule(ctx.case_rule(i)));
            }


        }
        if (ctx.defult() != null) {
            ins.setDeafult(visitDefult(ctx.defult()));
        }

        Main.symbolTable.addScope(scopesStack.pop());

        return ins;
    }

    @Override
    public Case visitCase_rule(SQLParser.Case_ruleContext ctx) {
        System.out.println("visit Case");
        Case case_ins = new Case();

        if (ctx.expression() != null) {
            case_ins.setExpression(visitExpression(ctx.expression()));
        } else if (ctx.any_name() != null) {
            case_ins.setVariable_name(ctx.any_name().getText());
        } else if (ctx.NUMERIC_LITERAL() != null) {
            case_ins.setTermenal_node(ctx.NUMERIC_LITERAL().getText());
        } else if (ctx.ONE_CHAR_LETTER() != null) {
            case_ins.setTermenal_node(ctx.ONE_CHAR_LETTER().getText());
        } else if (ctx.varible_from_object() != null) {
            case_ins.setTermenal_node(ctx.varible_from_object().getText());
        }

        for (int i = 0; i < ctx.instructions().size(); i++) {
            case_ins.getInstructions().add(visitInstructions(ctx.instructions(i)));
        }
        if (ctx.return_rule() != null) {
            case_ins.setR(visitReturn_rule(ctx.return_rule()));
        }
        if (ctx.K_BREAK() != null) {
            System.out.println("visit the breake rule " + ctx.K_BREAK().getSymbol().getText().toString());
        }
        return case_ins;
    }

    @Override
    public Deafult visitDefult(SQLParser.DefultContext ctx) {
        System.out.println("visit Deafult");
        Deafult deafult = new Deafult();
        if (ctx.instructions() != null) {
            for (int i = 0; i < ctx.instructions().size(); i++) {
                deafult.getInstructions().add(visitInstructions(ctx.instructions(i)));
            }
        }
        if (ctx.return_rule() != null) {
            deafult.setR(visitReturn_rule(ctx.return_rule()));
        }
        if (ctx.K_BREAK() != null) {
            System.out.println("visite the break relue " + ctx.K_BREAK().getSymbol().getText().toString());
        }
        return deafult;
    }

    /*---------------------------- function for symantic check */
    public void if_the_operator_equal_notequal_assignment_is_ture_or_not(Symbol symbol_1, Symbol symbol_2) {
        if (symbol_1.getType().getName() != symbol_2.getType().getName()) {
            System.out.println("error can not assignment two variable with diffrenete types");

        }
    }

    public void if_GreaterThan_SmallerThan(Symbol symbol_1, Symbol symbol_2) {
        if (symbol_1.getType().getName() != symbol_2.getType().getName()) {
            System.out.println("error can not equal or not to diffrente type");
        }
        if ((symbol_1.getType().getName() == Type.BOOLEAN_CONST && symbol_2.getType().getName() == Type.BOOLEAN_CONST) || (symbol_1.getType().getName() == Type.STRING_CONST && symbol_2.getType().getName() == Type.STRING_CONST)) {
            System.out.println("operator < or > or <= or >= can not be applied to " + symbol_1.getType().getName() + "and" + symbol_2.getType().getName());
        }
        // we could use it in loops
    } public void Error_ofusing_undeclared_variabler(Scope scope, String symbole_name) {
        boolean declared = false;
        while (scope.getId() != "global_scope") {
            if (scope.getSymbolMap().get(symbole_name) != null) {
                declared = true;
                break;
            } else {
                scope = scope.getParent();
                declared = false;

            }

        }
       /*if(declared==true )
       {
           System.out.println("variable"+symbole_name +"is declared befor ");
       }*/
        if (declared == false) {
            System.out.println(" Error variable   " + symbole_name + "  is not declared befor ");
        }

    }

    public void Error_UNdeclared_Function(String function_name) {
        boolean isdeclared = false;
        for (int i = 0; i < Main.symbolTable.getFunctions().size(); i++) {
            //System.out.println(" in the i"+i+Main.symbolTable.getFunctions().get(i).getName());
            //System.out.println("from the passing "+function_name);
            if (function_name.equals(Main.symbolTable.getFunctions().get(i).getName()) == true) {
                isdeclared = true;
                break;
            } else isdeclared = false;
        }
        if (isdeclared == false) {
            System.out.println(" Error  the function   " + function_name + "   is not  declared befor ");

        }

    }


   public boolean compareTwoTypes(Type firstType, Type secondType) {
       if (secondType.getName().equals(Type.UNDEFINDED)) {
           System.err.println("trying to assign undefined variable");
           return false;
       } else if (firstType.getName().equals(secondType.getName())) {
           return true;
       } else if (firstType.getName().equals(Type.UNDEFINDED))
           return true;
       else {
           return false;
       }
   }

    public Type addTypeForVariable(Expression expression) {
        Type type = new Type();
        boolean checktypeValidation = checkExpressionTypeValid(expression);
        if (checktypeValidation) {
            type.setName(getFirstExpritionType(expression).getName());
        } else {

        }
        return type;
    }

    public Type getFirstExpritionType(Expression expression) {
        Type type = new Type();
        type.setName("undefined first function");
        ArrayList<Intral_Expression_Value> expression_lists = convertExpretionListToArray(expression);

        if (expression_lists.get(0).getNUMERIC_LITERAL() != null) {
            type.setName(Type.NUMBER_CONST);
        } else if (expression_lists.get(0).getTure_or_False() != null) {
            type.setName(Type.BOOLEAN_CONST);
        } else if (expression_lists.get(0).getIdentyfire() != null) {
            type.setName(Type.STRING_CONST);
        } else if (expression_lists.get(0).getVariable_name() != null) {
            type = getVariableType(expression_lists.get(0).getVariable_name().getVariable_name());
        }
        return type;
    }

    public Type getVariableType(String variableName) {
        Scope currentScope = scopesStack.peek();
        Type variableType = new Type();
        variableType.setName("did not find variable");
        while (currentScope.getParent() != null) {
            if (currentScope.getSymbolMap().containsKey(variableName)) {
//                System.err.println("ggggggggggg"+currentScope.getSymbolMap().containsKey(variableName));
                return currentScope.getSymbolMap().get(variableName).getType();
            } else {
                currentScope = currentScope.getParent();
            }
        }

        return variableType;

    }

    public boolean checkExpressionTypeValid(Expression expression) {
        ArrayList<Intral_Expression_Value> expression_lists = convertExpretionListToArray(expression);
        ArrayList<Type> types = new ArrayList<>();
        for (Intral_Expression_Value intral_expression_value : expression_lists
                ) {

            Type type = new Type();
            if (intral_expression_value.getNUMERIC_LITERAL() != null) {

                type.setName(Type.NUMBER_CONST);
                types.add(type);
            } else if (intral_expression_value.getTure_or_False() != null) {
                type.setName(Type.BOOLEAN_CONST);
                types.add(type);
            } else if (intral_expression_value.getIdentyfire() != null) {
                type.setName(Type.STRING_CONST);
                types.add(type);
            } else if (intral_expression_value.getVariable_name() != null) {
                type.setName(getVariableType(intral_expression_value.getVariable_name().getVariable_name()).getName());
                types.add(type);

            }

        }

        if (types.size() == 1) {
            return true;
        } else {
            for (int i = 0; i < types.size() - 1; i++) {

                if (types.get(i).getName().equals(types.get(i + 1).getName())) {

                } else {
                    // error in expression types
                    System.err.println("Can't applied operation between " + types.get(i).getName() + " And " + types.get(i + 1).getName());
                    return false;
                }


            }
        }
//        for (int i = 0; i < types.size(); i++) {
//
//           System.out.println("types: " + types.get(i).getName());
//        }

        return true;

    }

    private ArrayList<Intral_Expression_Value> convertExpretionListToArray(Expression expression) {
        ArrayList<Intral_Expression_Value> expression_list = new ArrayList<>();

        extractDataFromExpretion(expression.getExpression_list(), expression_list);

        return expression_list;
    }

    private void extractDataFromExpretion(Expression_List expression_list, ArrayList<Intral_Expression_Value> expression_lists) {
        if (expression_list.getIntral_expression_value() != null) {
            expression_lists.add(expression_list.getIntral_expression_value());
        }
        if (expression_list.getRight_expr() != null) {
            extractDataFromExpretion(expression_list.getRight_expr(), expression_lists);
        }
        if (expression_list.getLeft_expr() != null) {
            extractDataFromExpretion(expression_list.getLeft_expr(), expression_lists);
        }
    }

    public void displayExprissionList(ArrayList<Intral_Expression_Value> intral_expression_values) {
        for (Intral_Expression_Value node : intral_expression_values
                ) {
            if (node.getNUMERIC_LITERAL() != null) {
                System.out.println(node.getNUMERIC_LITERAL());
            } else if (node.getTure_or_False() != null) {
                System.out.println(node.getTure_or_False());
            } else if (node.getIdentyfire() != null) {
                System.out.println(node.getIdentyfire());
            } else if (node.getVariable_name() != null) {
                System.out.println(node.getVariable_name().getVariable_name());
            }


        }
    }

    public void dispalaySymbolsInScope(Scope scope) {
        for (Object symbol : scope.getSymbolMap().values().toArray()) {
            System.out.println("-Symbol: " + ((Symbol) symbol).getName());
            System.out.println("-Symbol Scope: " + ((Symbol) symbol).getScope().getId());
            System.out.println("-Symbol type: " + ((Symbol) symbol).getType().getName());


        }
    }

}







