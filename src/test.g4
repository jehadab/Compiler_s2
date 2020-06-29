/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * Project      : sqlite-parser; an ANTLR4 grammar for SQLite
 *                https://github.com/bkiers/sqlite-parser
 * Developed by : Bart Kiers, bart@big-o.nl
 */
grammar SQL;

parse
 : ( sql_stmt_list | error| funtion  )* EOF
 ;

error
 : UNEXPECTED_CHAR
   {
     throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);
   }
 ;

sql_stmt_list
 : ';'* sql_stmt ( ';'+ sql_stmt )* ';'*
 ;

sql_stmt
// : ( K_EXPLAIN ( K_QUERY K_PLAN )? )? ( alter_table_stmt
 :                                     ( alter_table_stmt
//                                      | analyze_stmt
//                                      | attach_stmt
//                                      | begin_stmt
//                                      | commit_stmt
//                                      | compound_select_stmt
//                                      | create_index_stmt
                                      | create_table_stmt
//                                      | create_trigger_stmt
//                                      | create_view_stmt
//                                      | create_virtual_table_stmt
                                      | delete_stmt
//                                      | delete_stmt_limited
//                                      | detach_stmt
//                                      | drop_index_stmt
                                      | drop_table_stmt
//                                      | drop_trigger_stmt
//                                      | drop_view_stmt
                                      | factored_select_stmt
                                      | insert_stmt
//                                      | pragma_stmt
//                                      | reindex_stmt
//                                      | release_stmt
//                                      | rollback_stmt
//                                      | savepoint_stmt
//                                      | simple_select_stmt
//                                      | select_stmt
                                      | update_stmt
//                                      | update_stmt_limited
//                                      | vacuum_stmt

                                       )
 ;

alter_table_stmt
// : K_ALTER K_TABLE K_ONLY? ( database_name '.' )? source_table_name
 : K_ALTER K_TABLE  ( database_name '.' )? source_table_name
   ( K_RENAME K_TO new_table_name
   | alter_table_add
   | alter_table_add_constraint
   | K_ADD K_COLUMN? column_def
   )
//   K_ENABLE? (unknown)?
 ;

alter_table_add_constraint
 : K_ADD K_CONSTRAINT any_name table_constraint
 ;

alter_table_add
 : K_ADD table_constraint
 ;

//analyze_stmt
// : K_ANALYZE ( database_name | table_or_index_name | database_name '.' table_or_index_name )?
// ;

//attach_stmt
// : K_ATTACH K_DATABASE? expr K_AS database_name
// ;
//
//begin_stmt
// : K_BEGIN ( K_DEFERRED | K_IMMEDIATE | K_EXCLUSIVE )? ( K_TRANSACTION transaction_name? )?
// ;
//
//commit_stmt
// : ( K_COMMIT | K_END ) ( K_TRANSACTION transaction_name? )?
// ;
//
//compound_select_stmt
// : ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
//   select_core ( ( K_UNION K_ALL? | K_INTERSECT | K_EXCEPT ) select_core )+
//   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
//   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
// ;

//create_index_stmt
// : K_CREATE K_UNIQUE? K_INDEX ( K_IF K_NOT K_EXISTS )?
//   ( database_name '.' )? index_name K_ON table_name '(' indexed_column ( ',' indexed_column )* ')'
//   ( K_WHERE expr )?
// ;

create_table_stmt
// : K_CREATE ( K_TEMP | K_TEMPORARY )? K_TABLE ( K_IF K_NOT K_EXISTS )?
 : K_CREATE  K_TABLE ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? table_name
//   ( '(' column_def ( ',' table_constraint | ',' column_def )* ')' ( K_WITHOUT IDENTIFIER )?
   ( '(' column_def ( ',' table_constraint | ',' column_def )* ')'
   | K_AS select_stmt
//   ) (unknown)?
   )
 ;

//create_trigger_stmt
// : K_CREATE ( K_TEMP | K_TEMPORARY )? K_TRIGGER ( K_IF K_NOT K_EXISTS )?
//   ( database_name '.' )? trigger_name ( K_BEFORE  | K_AFTER | K_INSTEAD K_OF )?
//   ( K_DELETE | K_INSERT | K_UPDATE ( K_OF column_name ( ',' column_name )* )? ) K_ON ( database_name '.' )? table_name
//   ( K_FOR K_EACH K_ROW )? ( K_WHEN expr )?
//   K_BEGIN ( ( update_stmt | insert_stmt | delete_stmt | select_stmt ) ';' )+ K_END
// ;
//
//create_view_stmt
// : K_CREATE ( K_TEMP | K_TEMPORARY )? K_VIEW ( K_IF K_NOT K_EXISTS )?
//   ( database_name '.' )? view_name K_AS select_stmt
// ;
//
//create_virtual_table_stmt
// : K_CREATE K_VIRTUAL K_TABLE ( K_IF K_NOT K_EXISTS )?
//   ( database_name '.' )? table_name
//   K_USING module_name ( '(' module_argument ( ',' module_argument )* ')' )?
// ;

delete_stmt
// : with_clause? K_DELETE K_FROM qualified_table_name
 :  K_DELETE K_FROM qualified_table_name
   ( K_WHERE expr )?
 ;

//delete_stmt_limited
// : with_clause? K_DELETE K_FROM qualified_table_name
//   ( K_WHERE expr )?
//   ( ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
//     K_LIMIT expr ( ( K_OFFSET | ',' ) expr )?
//   )?
// ;
//
//detach_stmt
// : K_DETACH K_DATABASE? database_name
// ;

//drop_index_stmt
// : K_DROP K_INDEX ( K_IF K_EXISTS )? ( database_name '.' )? index_name
// ;

drop_table_stmt
 : K_DROP K_TABLE ( K_IF K_EXISTS )? ( database_name '.' )? table_name
 ;

//drop_trigger_stmt
// : K_DROP K_TRIGGER ( K_IF K_EXISTS )? ( database_name '.' )? trigger_name
// ;

//drop_view_stmt
// : K_DROP K_VIEW ( K_IF K_EXISTS )? ( database_name '.' )? view_name
// ;

factored_select_stmt
 :
//  ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
   select_core
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

insert_stmt
// : with_clause? ( K_INSERT
//                | K_REPLACE
//                | K_INSERT K_OR K_REPLACE
//                | K_INSERT K_OR K_ROLLBACK
//                | K_INSERT K_OR K_ABORT
//                | K_INSERT K_OR K_FAIL
//                | K_INSERT K_OR K_IGNORE ) K_INTO
                :   K_INSERT  K_INTO
   ( database_name '.' )? table_name ( '(' column_name ( ',' column_name )* ')' )?
   ( K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
   | select_stmt
   | K_DEFAULT K_VALUES
   )
 ;

//pragma_stmt
// : K_PRAGMA ( database_name '.' )? pragma_name ( '=' pragma_value
//                                               | '(' pragma_value ')' )?
// ;
//
//reindex_stmt
// : K_REINDEX ( collation_name
//             | ( database_name '.' )? ( table_name | index_name )
//             )?
// ;
//
//release_stmt
// : K_RELEASE K_SAVEPOINT? savepoint_name
// ;
//
//rollback_stmt
// : K_ROLLBACK ( K_TRANSACTION transaction_name? )? ( K_TO K_SAVEPOINT? savepoint_name )?
// ;

//savepoint_stmt
// : K_SAVEPOINT savepoint_name
// ;

//simple_select_stmt
//// : ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
//  : select_core ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
//   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
// ;

select_stmt
// : ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
// :  select_or_values ( compound_operator select_or_values )*
 :  select_or_values
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

select_or_values
 : K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*
   ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )?
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
 | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
 ;

update_stmt
// : with_clause? K_UPDATE ( K_OR K_ROLLBACK
// :  K_UPDATE ( K_OR K_ROLLBACK
//                         | K_OR K_ABORT
//                         | K_OR K_REPLACE
//                         | K_OR K_FAIL
//                         | K_OR K_IGNORE )? qualified_table_name
                         :  K_UPDATE  qualified_table_name
   K_SET column_name '=' expr ( ',' column_name '=' expr )* ( K_WHERE expr )?
 ;

//update_stmt_limited
// : with_clause? K_UPDATE ( K_OR K_ROLLBACK
//                         | K_OR K_ABORT
//                         | K_OR K_REPLACE
//                         | K_OR K_FAIL
//                         | K_OR K_IGNORE )? qualified_table_name
//   K_SET column_name '=' expr ( ',' column_name '=' expr )* ( K_WHERE expr )?
//   ( ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
//     K_LIMIT expr ( ( K_OFFSET | ',' ) expr )?
//   )?
// ;
//
//vacuum_stmt
// : K_VACUUM
// ;

column_def
 : column_name ( column_constraint | type_name )*
 ;

type_name
 : name ( '(' signed_number (any_name)? ')'
         | '(' signed_number (any_name)? ',' signed_number (any_name)? ')' )?
 ;

column_constraint
 : ( K_CONSTRAINT name )?
   ( column_constraint_primary_key
   | column_constraint_foreign_key
   | column_constraint_not_null
   | column_constraint_null
//   | K_UNIQUE conflict_clause
   | K_UNIQUE
   | K_CHECK '(' expr ')'
   | column_default
   | K_COLLATE collation_name
   )
 ;

column_constraint_primary_key
// : K_PRIMARY K_KEY ( K_ASC | K_DESC )? conflict_clause K_AUTOINCREMENT?
 : K_PRIMARY K_KEY ( K_ASC | K_DESC )?  K_AUTOINCREMENT?
 ;

column_constraint_foreign_key
 : foreign_key_clause
 ;

column_constraint_not_null
// : K_NOT K_NULL conflict_clause
 : K_NOT K_NULL
 ;

column_constraint_null
// : K_NULL conflict_clause
 : K_NULL
 ;

column_default
 : K_DEFAULT (column_default_value | '(' expr ')' | K_NEXTVAL '(' expr ')' | any_name )  ( '::' any_name+ )?
 ;

column_default_value
 : ( signed_number | literal_value )
 ;

//conflict_clause
// : ( K_ON K_CONFLICT ( K_ROLLBACK
//                     | K_ABORT
//                     | K_FAIL
//                     | K_IGNORE
//                     | K_REPLACE
//                     )
//   )?
// ;

/*
    SQLite understands the following binary operators, in order from highest to
    lowest precedence:

    ||
    *    /    %
    +    -
    <<   >>   &    |
    <    <=   >    >=
    =    ==   !=   <>   IS   IS NOT   IN   LIKE   GLOB   MATCH   REGEXP
    AND
    OR
*/
expr
 : literal_value
// | BIND_PARAMETER
 | ( ( database_name '.' )? table_name '.' )? column_name
 | unary_operator expr
 | expr '||' expr
 | expr ( '*' | '/' | '%' ) expr
 | expr ( '+' | '-' ) expr
 | expr ( '<<' | '>>' | '&' | '|' ) expr
 | expr ( '<' | '<=' | '>' | '>=' ) expr
 | expr ( '=' | '==' | '!=' | '<>' | K_IS | K_IS K_NOT | K_IN | K_LIKE | K_GLOB | K_MATCH | K_REGEXP ) expr
 | expr K_AND expr
 | expr K_OR expr
 | function_name '(' ( K_DISTINCT? expr ( ',' expr )* | '*' )? ')'
 | '(' expr ')'
// | K_CAST '(' expr K_AS type_name ')'
// | expr K_COLLATE collation_name
// | expr K_NOT? ( K_LIKE | K_GLOB | K_REGEXP | K_MATCH ) expr ( K_ESCAPE expr )?
// | expr ( K_ISNULL | K_NOTNULL | K_NOT K_NULL )
// | expr K_IS K_NOT? expr
// | expr K_NOT? K_BETWEEN expr K_AND expr
// | expr K_NOT? K_IN ( '(' ( select_stmt
//                          | expr ( ',' expr )*
//                          )?
//                      ')'
//                    | ( database_name '.' )? table_name )
// | ( ( K_NOT )? K_EXISTS )? '(' select_stmt ')'
// | K_CASE expr? ( K_WHEN expr K_THEN expr )+ ( K_ELSE expr )? K_END
// | raise_function
 ;

foreign_key_clause
 : K_REFERENCES ( database_name '.' )? foreign_table ( '(' fk_target_column_name ( ',' fk_target_column_name )* ')' )?
   ( ( K_ON ( K_DELETE | K_UPDATE ) ( K_SET K_NULL
                                    | K_SET K_DEFAULT
                                    | K_CASCADE
                                    | K_RESTRICT
                                    | K_NO K_ACTION )
     | K_MATCH name
     )
   )*
   ( K_NOT? K_DEFERRABLE ( K_INITIALLY K_DEFERRED | K_INITIALLY K_IMMEDIATE )? K_ENABLE? )?
 ;

fk_target_column_name
 : name
 ;

//raise_function
// : K_RAISE '(' ( K_IGNORE
//               | ( K_ROLLBACK | K_ABORT | K_FAIL ) ',' error_message )
//           ')'
// ;

indexed_column
 : column_name ( K_COLLATE collation_name )? ( K_ASC | K_DESC )?
 ;

table_constraint
 : ( K_CONSTRAINT name )?
   ( table_constraint_primary_key
   | table_constraint_key
   | table_constraint_unique
   | K_CHECK '(' expr ')'
   | table_constraint_foreign_key
   )
 ;

table_constraint_primary_key
// : K_PRIMARY K_KEY '(' indexed_column ( ',' indexed_column )* ')' conflict_clause
 : K_PRIMARY K_KEY '(' indexed_column ( ',' indexed_column )* ')'
 ;

table_constraint_foreign_key
 : K_FOREIGN K_KEY '(' fk_origin_column_name ( ',' fk_origin_column_name )* ')' foreign_key_clause
 ;

table_constraint_unique
// : K_UNIQUE K_KEY? name? '(' indexed_column ( ',' indexed_column )* ')' conflict_clause
 : K_UNIQUE K_KEY? name? '(' indexed_column ( ',' indexed_column )* ')'
 ;

table_constraint_key
// : K_KEY name? '(' indexed_column ( ',' indexed_column )* ')' conflict_clause
 : K_KEY name? '(' indexed_column ( ',' indexed_column )* ')'
 ;

fk_origin_column_name
 : column_name
 ;

//with_clause
// : K_WITH K_RECURSIVE? cte_table_name K_AS '(' select_stmt ')' ( ',' cte_table_name K_AS '(' select_stmt ')' )*
// ;

qualified_table_name
 : ( database_name '.' )? table_name ( K_INDEXED K_BY index_name
                                     | K_NOT K_INDEXED )?
 ;

ordering_term
 : expr  ( K_ASC | K_DESC )?
 ;

pragma_value
 : signed_number
 | name
 | STRING_LITERAL
 ;

common_table_expression
 : table_name ( '(' column_name ( ',' column_name )* ')' )? K_AS '(' select_stmt ')'
 ;

result_column
 : '*'
 | table_name '.' '*'
 | expr ( K_AS? column_alias )?
 ;

table_or_subquery
 : ( database_name '.' )? table_name ( K_AS? table_alias )?
   ( K_INDEXED K_BY index_name
   | K_NOT K_INDEXED )?
 | '(' ( table_or_subquery ( ',' table_or_subquery )*
       | join_clause )
   ')' ( K_AS? table_alias )?
 | '(' select_stmt ')' ( K_AS? table_alias )?
 ;

join_clause
 : table_or_subquery ( join_operator table_or_subquery join_constraint )*
// : table_or_subquery ( join_operator table_or_subquery  )*
 ;

join_operator
 : ','
// | K_NATURAL? ( K_LEFT K_OUTER? | K_INNER | K_CROSS )? K_JOIN
 |  ( K_LEFT K_OUTER? | K_INNER  )? K_JOIN
 ;

join_constraint
// : ( K_ON expr
//   | K_USING '(' column_name ( ',' column_name )* ')' )?
    : ( K_ON expr)?
 ;

select_core
 : K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*
   ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )?
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
 | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
 ;

//compound_operator
// : K_UNION
// | K_UNION K_ALL
// | K_INTERSECT
// | K_EXCEPT
// ;

cte_table_name
 : table_name ( '(' column_name ( ',' column_name )* ')' )?
 ;

signed_number
 : ( ( '+' | '-' )? NUMERIC_LITERAL | '*' )
 ;

literal_value
 : NUMERIC_LITERAL
 | STRING_LITERAL
 | BLOB_LITERAL
 | K_NULL
 | K_CURRENT_TIME
 | K_CURRENT_DATE
 | K_CURRENT_TIMESTAMP
 ;
 unary_operator
  : '-'
  | '+'
  | '~'
  | K_NOT
  ;
error_message
 : STRING_LITERAL
 ;

module_argument // TODO check what exactly is permitted here
 : expr
 | column_def
 ;

column_alias
 : IDENTIFIER
 | STRING_LITERAL
 ;


keyword
 : K_ABORT
 | K_ACTION
 | K_ADD
 | K_AFTER
 | K_ALL
 | K_ALTER
 | K_ANALYZE
 | K_AND
 | K_AS
 | K_ASC
 | K_ATTACH
 | K_AUTOINCREMENT
 | K_BEFORE
 | K_BEGIN
 | K_BETWEEN
 | K_BY
 | K_CASCADE
 | K_CASE
 | K_CAST
 | K_CHECK
 | K_COLLATE
 | K_COLUMN
 | K_COMMIT
 | K_CONFLICT
 | K_CONSTRAINT
 | K_CREATE
 | K_CROSS
 | K_CURRENT_DATE
 | K_CURRENT_TIME
 | K_CURRENT_TIMESTAMP
 | K_DATABASE
 | K_DEFAULT
 | K_DEFERRABLE
 | K_DEFERRED
 | K_DELETE
 | K_DESC
 | K_DETACH
 | K_DISTINCT
 | K_DROP
 | K_EACH
 | K_ELSE
 | K_END
 | K_ENABLE
 | K_ESCAPE
 | K_EXCEPT
 | K_EXCLUSIVE
 | K_EXISTS
 | K_EXPLAIN
 | K_FAIL
 | K_FOR
 | K_FOREIGN
 | K_FROM
 | K_FULL
 | K_GLOB
 | K_GROUP
 | K_HAVING
 | K_IF
 | K_IGNORE
 | K_IMMEDIATE
 | K_IN
 | K_INDEX
 | K_INDEXED
 | K_INITIALLY
 | K_INNER
 | K_INSERT
 | K_INSTEAD
 | K_INTERSECT
 | K_INTO
 | K_IS
 | K_ISNULL
 | K_JOIN
 | K_KEY
 | K_LEFT
 | K_LIKE
 | K_LIMIT
 | K_MATCH
 | K_NATURAL
 | K_NO
 | K_NOT
 | K_NOTNULL
 | K_NULL
 | K_OF
 | K_OFFSET
 | K_ON
 | K_OR
 | K_ORDER
 | K_OUTER
 | K_PLAN
 | K_PRAGMA
 | K_PRIMARY
 | K_QUERY
 | K_RAISE
 | K_RECURSIVE
 | K_REFERENCES
 | K_REGEXP
 | K_REINDEX
 | K_RELEASE
 | K_RENAME
 | K_REPLACE
 | K_RESTRICT
 | K_RIGHT
 | K_ROLLBACK
 | K_ROW
 | K_SAVEPOINT
 | K_SELECT
 | K_SET
 | K_TABLE
 | K_TEMP
 | K_TEMPORARY
 | K_THEN
 | K_TO
 | K_TRANSACTION
 | K_TRIGGER
 | K_UNION
 | K_UNIQUE
 | K_UPDATE
 | K_USING
 | K_VACUUM
 | K_VALUES
 | K_VIEW
 | K_VIRTUAL
 | K_WHEN
 | K_WHERE
 | K_WITH
 | K_WITHOUT
 | K_NEXTVAL
 ;

// TODO check all names below

//[a-zA-Z_0-9\t \-\[\]\=]+
unknown
 : .*
 ;

name
 : use_random_name
 ;

function_name
 : use_random_name
 ;

database_name
 : use_random_name
 ;

source_table_name
 : use_random_name
 ;

table_name
 : use_random_name
 ;

table_or_index_name
 : use_random_name
 ;

new_table_name
 : use_random_name
 ;

column_name
 : use_random_name
 ;

collation_name
 : use_random_name
 ;

foreign_table
 : use_random_name
 ;

index_name
 : use_random_name
 ;

trigger_name
 : use_random_name
 ;

view_name
 : use_random_name
 ;

module_name
 : use_random_name
 ;

pragma_name
 : use_random_name
 ;

savepoint_name
 : use_random_name
 ;

table_alias
 : use_random_name
 ;

transaction_name
 : use_random_name
 ;

any_name
 : IDENTIFIER
 |RANDOM_NAME
 //| keyword
 | STRING_LITERAL
 //| '(' any_name ')'

 ;

// start section instraction and functions defination --------------------------------------------------------------
  funtion:
       (  K_FUNCTION?
       function_header
       function_body)
   ;

   function_header:

        use_random_name
        OPEN_PAR
        (( args (COMMA args)*)(COMMA (create_varible_with_assign|creating_with_assign))*)*
        CLOSE_PAR
        ;

   function_body:

       OPEN_BRACKET
       sub_function_body (return_rule SCOL)?
       //grnral_creating
       CLOSE_BRACKET

   ;

   sub_function_body
    : (instructions*
    |  (OPEN_BRACKET sub_function_body CLOSE_BRACKET))

   ;
   instructions // todo switch in instraction
     :(functional_instruction | nonfunctional_instruction )

   ;
   functional_instruction
       : (do_while| if_else_rule | switch_rule | ( while_rule  | foreach  |for_loop_rule)
       ( (exiting_loops)?SCOL |OPEN_BRACKET  |  (exiting_loops SCOL)? CLOSE_BRACKET | instructions))
   ;
   nonfunctional_instruction
      :( call_function | print_statment | arithmetic_infunction_statment
      | (K_VAR use_random_name ASSIGN)one_line_if_statment_rule | grnral_creating | genral_assign ) SCOL

   ;

   call_function: // if we put first expr no error but when we put prameters it gives us error
         use_random_name//IDENTIFIER
       OPEN_PAR
        ( prameters (','  prameters)*)*
       CLOSE_PAR
   ;

    callback_func:
     function_header
     function_body
     ;

     args
      :  grnral_creating//grnral_creating//create_varible_without_assign
      | callback_func;
      //| create_Array_without_assign
      //| create_json_object_without_assign)
     prameters
     :NUMERIC_LITERAL
     | ONE_CHAR_LETTER
     | call_function
     | IDENTIFIER
     | use_random_name
     | varible_from_object
     | array_base_form_with_index
     | genral_assign
      ;

      return_rule:
      (K_RETURN(return_type)?)
      ;

      exiting_loops:
      (return_rule)|(K_BREAK|K_CONTINUE)
      ;

    return_type: // todo narjess assight
       ONE_CHAR_LETTER
        |IDENTIFIER
        |call_function
        |arithmetic_infunction_statment
        |K_TRUE
        |K_FALSE
        |K_NULL
        |one_line_if_statment_rule
        |varible_name// for calling array
        |boolean_infunction_statment // changing inside it to take arithmatic expression
        |arithmetic_expr
        | array_base_form_with_index
        | varible_from_object
        | assign_varible
        | assign_array
        | assign_json

   ;
    print_statment :
    K_PRINT OPEN_PAR  indisde_the_print  CLOSE_PAR
     ;
     indisde_the_print:
        ((indisde_the_print_part1? IDENTIFIER indisde_the_print_part2?)|(use_random_name | call_function |varible_from_object| array_base_form_with_index))*
     ;

    indisde_the_print_part1:
        ((use_random_name|call_function|varible_from_object| array_base_form_with_index) PLUS)
    ;

    indisde_the_print_part2:
        (PLUS (use_random_name|call_function|varible_from_object| array_base_form_with_index))
    ;


     if_else_rule
        : K_IF OPEN_PAR boolean_infunction_statment CLOSE_PAR (OPEN_BRACKET instructions* (return_rule SCOL) ?  CLOSE_BRACKET| instructions | SCOL |((return_rule)? SCOL) )
       (K_ELSE_IF OPEN_PAR boolean_infunction_statment CLOSE_PAR (OPEN_BRACKET instructions* (return_rule SCOL)?CLOSE_BRACKET) |( instructions | SCOL | (return_rule?  SCOL))  )*
       ((K_ELSE) (OPEN_BRACKET  instructions* (return_rule SCOL)?CLOSE_BRACKET | instructions |  return_rule? SCOL ) )?
     //:K_IF '('boolean_infunction_statment')'
    // |K_IF '('boolean_infunction_statment')' '{'(sub_function_body)'}' K_ELSE '{'(sub_function_body)'}'
    // | K_IF '('boolean_infunction_statment')''{'(sub_function_body)'}' K_ELSE K_IF'('boolean_infunction_statment ')''{'(sub_function_body)'}'
     ;

     while_rule:
        (K_WHILE OPEN_PAR boolean_infunction_statment CLOSE_PAR )
     ;
     do_while:
         K_DO
          ( (exiting_loops)? SCOL
         |
         (OPEN_BRACKET
         instructions*
         (exiting_loops SCOL)?
         CLOSE_BRACKET)
         | SCOL
         ) //SCOL
          while_rule SCOL
      ;

    for_loop_rule:
    K_FOR
     OPEN_PAR ( (create_varible_form|assign_varible | assign_array | create_Array_without_assign | shortcut_statments)   /*arithmetic_infunction_statment*/ )?
       SCOL( boolean_infunction_statment )?
       SCOL ( create_varible_form|assign_varible | assign_array | create_Array_without_assign | shortcut_statments)? CLOSE_PAR
    ;

    foreach:
    K_FOREACH OPEN_PAR
    K_VAR use_random_name '[]'* ':'use_random_name'[]'*
    CLOSE_PAR
    ;
    one_line_if_statment_rule:  // baracket working with t
         (
         ((OPEN_PAR)* boolean_infunction_statment (CLOSE_BRACKET)*
         ('?'OPEN_PAR* (inside_one_line_function )  CLOSE_PAR*) )* (':'(OPEN_PAR* (inside_one_line_function)(CLOSE_PAR)* (CLOSE_BRACKET)*)))*
        ;
        inside_one_line_function :
        (
        | arithmetic_expr
        | boolean_infunction_statment
       // | (OPEN_PAR full_arthmatic_statmint CLOSE_PAR)

        )
        ;

   switch_rule:
       K_SWITCH OPEN_PAR (use_random_name| ONE_CHAR_LETTER|NUMERIC_LITERAL|arithmetic_infunction_statment|arithmetic_expr|call_function|varible_from_object) CLOSE_PAR
       OPEN_BRACKET
      ( case_rule*
       defult?)?
      CLOSE_BRACKET
       ;
       defult:
       K_DEFAULT ':'
       (instructions)*
        ((K_BREAK
           | return_rule)
           SCOL)?
       ;
       case_rule:
       K_CASE (any_name|ONE_CHAR_LETTER|NUMERIC_LITERAL|varible_from_object)':'
       (instructions)*
       ((K_BREAK
       | return_rule)
       SCOL)?
       ;

   //grnral_creating:

    //create_varible_form//with and without assign
    //|create_array_form//with and without assign
    //|create_json_form//with and without assign
   // ;
   creating_with_assign:
   create_json_with_assign
   |create_Array_with_assign
   ;
    grnral_creating:
       creat_without_assign
       | creating_with_assign ;
     creat_without_assign:
     create_varible_without_assign
     | create_Array_without_assign
    | create_json_object_without_assign
     ;
    genral_assign
    : assign_varible // like: a=5
    | assign_array //like: a[2]=3
    | assign_json //like: jsonObject = {}
    ;

    varible_name:
    use_random_name
    ;


    assign_varible:
     (( use_random_name ) (any_arithmetic_oprator)? ASSIGN )+
      (
             arithmetic_expr
           | boolean_infunction_statment
| factored_select_stmt
                  | select_stmt
           //| arithmetic_infunction_statment // todo here merge with infunction

      )
    ;
    create_varible_form:

    create_varible_without_assign|create_varible_with_assign
    ;

    create_varible_without_assign
    :  K_VAR varible_name
    ;

    create_varible_with_assign:
     K_VAR assign_varible
    ;

     array_name:
     use_random_name
     ;

     assign_array:
      (array_base_form_with_index (any_arithmetic_oprator)? ASSIGN)+
       (
                    arithmetic_expr
                  | boolean_infunction_statment
                  | factored_select_stmt
                  | select_stmt

                  //| arithmetic_infunction_statment // todo here merge with infunction

             )
     ;

      create_array_form:
       create_Array_without_assign|create_Array_with_assign
      ;

        create_Array_without_assign:
        K_VAR array_base_form_without_index
        ;

        create_Array_with_assign:
        K_VAR array_base_form_without_index (ASSIGN left_side_array)
        ;

//        array_base_form:
//        (array_base_form_without_index|array_base_form_with_index)
//        ;

         array_base_form_without_index:
         array_name ('[]')+
         ;

         array_base_form_with_index:
        array_name (OPEN_SQER_BAR (NUMERIC_LITERAL| varible_name|arithmetic_infunction_statment|arithmetic_expr )CLOSE_SQER_PAR)+

         ;
        array_identifier_form:
              IDENTIFIER (COMMA IDENTIFIER)*
        ;

        array_boolean_form:
         boolean_expr (COMMA boolean_expr)
        ;

        array_integer_form:
             (arithmetic_expr|arithmetic_infunction_statment) (COMMA (arithmetic_expr|arithmetic_infunction_statment))*
        ;

        array_charecter_form:
              ONE_CHAR_LETTER (COMMA ONE_CHAR_LETTER)*
        ;

        array_objects_form:
               json_statment(COMMA json_statment)*
        ;

        array_objects_form2:
        OPEN_SQER_BAR array_objects_form CLOSE_SQER_PAR
        ;

        array_varible_form:
        (varible_name|varible_from_object)(COMMA(varible_name|varible_from_object))*
        ;

        array_anyType_form:
        (
         array_identifier_form
        |array_integer_form
        |array_charecter_form
        |array_objects_form
        |array_varible_form
        |array_boolean_form
        )
        (COMMA
         (array_identifier_form
         |array_integer_form
         |array_charecter_form
         |array_objects_form
         |array_arrayes_form
         |array_varible_form
         |array_boolean_form
         ))*
        ;

        array_arrayes_form:
        left_side_array (COMMA (left_side_array))*
        ;

        value_left_side:
        (           array_identifier_form
                   |array_integer_form
                   |array_charecter_form
                   |array_objects_form
                   |array_objects_form2
                   |array_arrayes_form
                   |array_boolean_form
                   |varible_from_object
                   |array_anyType_form
                   |array_varible_form

                   )
        ;

         left_side_array:
          (OPEN_BRACKET
           value_left_side?
          CLOSE_BRACKET)
           | factored_select_stmt
           | select_stmt

         ;


   json_name:
   use_random_name
   ;

   assign_json:
   json_name ASSIGN (json_name|json_statment)
   ;

   create_json_form:
   create_json_object_without_assign|create_json_with_assign
   ;

    create_json_object_without_assign:
    K_JSON  json_name
    ;

    create_json_with_assign:
    K_JSON assign_json
    ;
inside_json_statmnet :
use_random_name ':'value_json_statmnet
;

    json_statment:
    OPEN_BRACKET ((inside_json_statmnet)(COMMA inside_json_statmnet )*)? CLOSE_BRACKET
    ;


    value_json_statmnet:
     IDENTIFIER
     |NUMERIC_LITERAL
     | K_NULL
     |varible_name
     | ONE_CHAR_LETTER
     | json_statment
     |varible_from_object
     |call_function
     | array_base_form_with_index //(ASSIGN left_side_array)?
     | array_objects_form2
     | OPEN_SQER_BAR value_left_side CLOSE_SQER_PAR
     |arithmetic_expr
     |full_arthmatic_statmint
     |boolean_expr

    ;

    varible_from_object:
    json_name DOT varible_name
    ;

 arithmetic_infunction_statment
     : full_arthmatic_statmint
     | shortcut_statments
  ;
 full_arthmatic_statmint
     :(     (use_random_name
            | varible_from_object
            | array_base_form_with_index
            ) (any_arithmetic_oprator)? ASSIGN )+ arithmetic_expr

  ;

 arithmetic_expr
    : OPEN_PAR arithmetic_expr CLOSE_PAR
    | (PLUS_PLUS | MINUS_MINUS) use_random_name
    | arithmetic_expr (STAR | DIV) arithmetic_expr
    | arithmetic_expr POWER arithmetic_expr
    | arithmetic_expr MOD arithmetic_expr
    | arithmetic_expr ( PLUS | MINUS) arithmetic_expr
    | use_random_name (MINUS_MINUS | PLUS_PLUS)
    | (IDENTIFIER
    | ONE_CHAR_LETTER
    | call_function
    | varible_name
    | varible_from_object
    | array_base_form_with_index
    | NUMERIC_LITERAL)
 ;

  any_arithmetic_oprator
     : STAR
     | DIV
     | POWER
     | MOD
     | PLUS
     | MINUS
   ;

  boolean_infunction_statment
     : boolean_expr
     | boolean_expr ((PIPE2 | AMP2 ) boolean_expr)*
   ;
 boolean_expr
     : OPEN_PAR (boolean_expr) CLOSE_PAR
     | OPEN_PAR full_arthmatic_statmint CLOSE_PAR
     | arithmetic_expr
     | shortcut_statments
     | boolean_expr (GT | GT_EQ | LT | LT_EQ )  boolean_expr
     | boolean_expr (EQ | NOT_EQ1) boolean_expr
     |( K_TRUE
     | K_FALSE
     | NUMERIC_LITERAL
     | varible_from_object
     | array_base_form_with_index
     | use_random_name
     | IDENTIFIER
     | ONE_CHAR_LETTER)

  ;
 shortcut_statments
      : MINUS_MINUS use_random_name
      | PLUS_PLUS use_random_name
      | use_random_name MINUS_MINUS
      | use_random_name PLUS_PLUS
 ;
     //statmint end section----------------------------------------------------------------------
    use_random_name
    : RANDOM_NAME
    ;

DOUBEL : '"';
SCOL : ';';
DOT : '.';
OPEN_PAR : '(';
CLOSE_PAR : ')';
OPEN_SQER_BAR: '[';
CLOSE_SQER_PAR: ']';
OPEN_BRACKET : '{';
CLOSE_BRACKET : '}';
COMMA : ',';
ASSIGN : '=';
STAR : '*';
POWER :'^';
PLUS : '+';
MINUS : '-';
TILDE : '~';
PIPE2 : '||';
DIV : '/';
MOD : '%';
LT2 : '<<';
GT2 : '>>';
AMP : '&';
AMP2 : '&&';
PIPE : '|';
LT : '<';
LT_EQ : '<=';
GT : '>';
GT_EQ : '>=';
EQ : '==';
NOT_EQ1 : '!=';
NOT_EQ2 : '<>';
PLUS_PLUS: '++' ;
MINUS_MINUS:'--' ;

// http://www.sqlite.org/lang_keywords.html
K_ABORT : A B O R T;
K_ACTION : A C T I O N;
K_ADD : A D D;
K_AFTER : A F T E R;
K_ALL : A L L;
K_ALTER : A L T E R;
K_ANALYZE : A N A L Y Z E;
K_AND : A N D;
K_AS : A S;
K_ASC : A S C;
K_ATTACH : A T T A C H;
K_AUTOINCREMENT : A U T O I N C R E M E N T;
K_BEFORE : B E F O R E;
K_BEGIN : B E G I N;
K_BETWEEN : B E T W E E N;
K_BY : B Y;
K_BREAK: B R E A K ;
K_CASCADE : C A S C A D E;
K_CASE : C A S E;
K_CAST : C A S T;
K_CHECK : C H E C K;
K_COLLATE : C O L L A T E;
K_COLUMN : C O L U M N;
K_COMMIT : C O M M I T;
K_CONFLICT : C O N F L I C T;
K_CONSTRAINT : C O N S T R A I N T;
K_CREATE : C R E A T E;
K_CROSS : C R O S S;
K_CURRENT_DATE : C U R R E N T '_' D A T E;
K_CURRENT_TIME : C U R R E N T '_' T I M E;
K_CURRENT_TIMESTAMP : C U R R E N T '_' T I M E S T A M P;
K_DATABASE : D A T A B A S E;
K_DEFAULT : D E F A U L T;
K_DEFERRABLE : D E F E R R A B L E;
K_DEFERRED : D E F E R R E D;
K_DELETE : D E L E T E;
K_DESC : D E S C;
K_DO : D O ;
K_DETACH : D E T A C H;
K_DISTINCT : D I S T I N C T;
K_DROP : D R O P;
K_EACH : E A C H;
K_ELSE : E L S E;
K_ELSE_IF : E L S E ' 'I F;
K_END : E N D;
K_ENABLE : E N A B L E;
K_ESCAPE : E S C A P E;
K_EXCEPT : E X C E P T;
K_EXCLUSIVE : E X C L U S I V E;
K_EXISTS : E X I S T S;
K_EXPLAIN : E X P L A I N;
K_FAIL : F A I L;
K_FALSE : F A L S E ;
K_FUNCTION : F U N C T I O N ;
K_FOR : F O R;
K_FOREACH : F O R E A C H ;
K_FOREIGN : F O R E I G N;
K_FROM : F R O M;
K_FULL : F U L L;
K_GLOB : G L O B;
K_GROUP : G R O U P;
K_HAVING : H A V I N G;
K_IF : I F;
K_IGNORE : I G N O R E;
K_IMMEDIATE : I M M E D I A T E;
K_IN : I N;
K_INDEX : I N D E X;
K_INDEXED : I N D E X E D;
K_INITIALLY : I N I T I A L L Y;
K_INNER : I N N E R;
K_INSERT : I N S E R T;
K_INSTEAD : I N S T E A D;
K_INTERSECT : I N T E R S E C T;
K_INTO : I N T O;
K_IS : I S;
K_ISNULL : I S N U L L;
K_JOIN : J O I N;
K_KEY : K E Y;
K_LEFT : L E F T;
K_LIKE : L I K E;
K_LIMIT : L I M I T;
K_MATCH : M A T C H;
K_NATURAL : N A T U R A L;
K_NEXTVAL : N E X T V A L;
K_NO : N O;
K_NOT : N O T;
K_NOTNULL : N O T N U L L;
K_NULL : N U L L;
K_OF : O F;
K_OFFSET : O F F S E T;
K_ON : O N;
K_ONLY : O N L Y;
K_OR : O R;
K_ORDER : O R D E R;
K_OUTER : O U T E R;
K_PLAN : P L A N;
K_PRAGMA : P R A G M A;
K_PRIMARY : P R I M A R Y;
K_PRINT : P R I N T ;
K_QUERY : Q U E R Y;
K_RAISE : R A I S E;
K_RECURSIVE : R E C U R S I V E;
K_REFERENCES : R E F E R E N C E S;
K_REGEXP : R E G E X P;
K_REINDEX : R E I N D E X;
K_RELEASE : R E L E A S E;
K_RETURN : R E T U R N  ;
K_RENAME : R E N A M E;
K_REPLACE : R E P L A C E;
K_RESTRICT : R E S T R I C T;
K_RIGHT : R I G H T;
K_ROLLBACK : R O L L B A C K;
K_ROW : R O W;
K_SAVEPOINT : S A V E P O I N T;
K_SELECT : S E L E C T;
K_SET : S E T;
K_SWITCH: S W I T C H ;
K_TABLE : T A B L E;
K_TEMP : T E M P;
K_TEMPORARY : T E M P O R A R Y;
K_THEN : T H E N;
K_TO : T O;
K_TRANSACTION : T R A N S A C T I O N;
K_TRUE : T R U E ;
K_TRIGGER : T R I G G E R;
K_UNION : U N I O N;
K_UNIQUE : U N I Q U E;
K_UPDATE : U P D A T E;
K_USING : U S I N G;
K_VACUUM : V A C U U M;
K_VALUES : V A L U E S;
K_VAR : V A R ;
K_VIEW : V I E W;
K_VIRTUAL : V I R T U A L;
K_WHEN : W H E N;
K_WHERE : W H E R E;
K_WITH : W I T H;
K_WITHOUT : W I T H O U T;
K_WHILE :  W H I L E ;
K_CONTINUE : C O N T I N U E ;
K_JSON : J S O N ;
 ONE_CHAR_LETTER :
'\'' ( ~'\'' | '\'\'' ) '\''
 ;
 STRING_LITERAL
  : '\'' ( ~'\'' | '\'\'' )* '\''
  ;

IDENTIFIER
 : '"' (~'"' | '""')* '"'
 //| '`' (~'`' | '``')* '`'
 //| '[' ~']'* ']'
 //| RANDOM_NAME // TODO check: needs more chars in set
 ;
RANDOM_NAME :
[a-zA-Z_] [a-zA-Z_0-9]*
;
NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

BIND_PARAMETER
 : '?' DIGIT*
// | [:@$] IDENTIFIER
 ;



BLOB_LITERAL
 : X STRING_LITERAL
 ;

/*SINGLE_LINE_COMMENT
 : '--' ~[\r\n]* -> channel(HIDDEN)
 ;*/

MULTILINE_COMMENT
 : '/*' .*? ( '*/' | EOF ) -> channel(HIDDEN)
 ;

SPACES
 : [ \u000B\t\r\n] -> channel(HIDDEN)
 ;

UNEXPECTED_CHAR
 :  .
 ;


fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
