grammar ASSQL;

options {
  language = Java;
}

@header {
package com.tibco.as.sql.grammar;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.HashMap;
import com.tibco.as.sql.*;
import com.tibco.as.space.Tuple;
}

@members {
    // Table information <table_name, table_correlationName>
    private List<Tuple> tableInfo = new ArrayList<Tuple>();
    private void addTableInfo(String tname, String correlationName)
    {
        Tuple tableTuple = Tuple.create();
        tableTuple.put(ASSQLUtils.TABLE_NAME, tname);
        tableTuple.put(ASSQLUtils.TABLE_CORRELATION_NAME, correlationName);
        tableInfo.add(tableTuple);
    }
    
    // Column information <column_name, table_name, alias>
    private List<Tuple> columnInfo = new ArrayList<Tuple>();
    private void addColumnInfo(String cname, String tname, String calias, String fname)
    {
        Tuple columnTuple = Tuple.create();
        columnTuple.put(ASSQLUtils.COLUMN_NAME, cname);
        columnTuple.put(ASSQLUtils.TABLE_NAME, tname);
        columnTuple.put(ASSQLUtils.COLUMN_ALIAS, calias);
        columnTuple.put(ASSQLUtils.COLUMN_FUNCTION, fname);
        columnInfo.add(columnTuple);
    }
    
    // FieldDef information for creating tables/spaces
    private ArrayList<Tuple> columnDefs = new ArrayList<Tuple>();
    private void addColumnDef(String fieldName, String dataType, String notNull, String pkey, String pkeyType)
    {
        Tuple tuple = Tuple.create();
        tuple.put(ASSQLUtils.COLUMN_NAME, fieldName);
        tuple.put(ASSQLUtils.COLUMN_TYPE, dataType);
        if (notNull != null)
            tuple.put(ASSQLUtils.NULL_CONSTRAINT, notNull);
        if (pkey != null)
        {
            tuple.put(ASSQLUtils.PKEY_CONSTRAINT, pkey);
            if (pkeyType != null)
                tuple.put(ASSQLUtils.PKEY_TYPE, pkeyType);
        }
        columnDefs.add(tuple);
    }
    
    // List of columns which are primary keys for creating tables/spaces
    private String pkeyType = "hash";
    private ArrayList<String> pkeyList = new ArrayList<String>();
    
    private ArrayList<ASSQLIndex> indexList = new ArrayList<ASSQLIndex>();

    // error handling
    private ArrayList<String> recognitionErrors = new ArrayList<String>();

    public void displayRecognitionError(String[] tokenNames, RecognitionException e)
    {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        recognitionErrors.add(hdr + " " + msg);
    }
    
    public void throwFirstRecognitionError() throws SQLException
    {
        if (recognitionErrors.size() > 0)
            throw new SQLRecoverableException(recognitionErrors.get(0));
    }
}

@lexer::header {
package com.tibco.as.sql.grammar;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
}

@lexer::members {
    private List<String> recognitionErrors = new ArrayList<String>();

    public void displayRecognitionError(String[] tokenNames, RecognitionException e)
    {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        recognitionErrors.add(hdr + " " + msg);
    }
    
    public void throwFirstRecognitionError() throws SQLException
    {
        if (recognitionErrors.size() > 0)
            throw new SQLSyntaxErrorException(recognitionErrors.get(0));
    }
}

query returns [ASSQLStatement stmnt]
    : createStatement  endStmnt {$stmnt = $createStatement.expr; }
    | deleteStatement  endStmnt {$stmnt = $deleteStatement.expr; }
    | insertStatement  endStmnt {$stmnt = $insertStatement.expr; }
    | selectStatement  endStmnt {$stmnt = $selectStatement.expr; }
    | updateStatement  endStmnt {$stmnt = $updateStatement.expr; }
    ;

createStatement returns [CreateStatement expr]
    : {
          // ActiveSpaces properties for defining a table/space
          java.util.Properties sdprops = new java.util.Properties();
      }
      CREATE TABLE target_table
      OParen table_element (Comma table_element)* CParen
      (c=space_property {sdprops.put($c.key, $c.value);} (Comma d=space_property {sdprops.put($d.key, $d.value);})* )*
      {
          return new CreateStatement($target_table.arg, columnDefs, pkeyType, pkeyList, sdprops, indexList);
      }
    ;

deleteStatement returns [DeleteStatement expr]
    : DELETE FROM target_table
      (WHERE where_condition)?
      {
          return new DeleteStatement($target_table.arg, $where_condition.arg);
      }
    ;

insertStatement returns [InsertStatement expr]
    : {
          List<String> columnNames = new ArrayList<String>();
          List<String> columnValues = new ArrayList<String>();
      }
      INSERT INTO table_name
      OParen c=column_name {columnNames.add($c.text);} ( Comma d=column_name {columnNames.add($d.text);} )* CParen
      VALUES OParen e=column_value {columnValues.add($e.text);} ( Comma f=column_value {columnValues.add($f.text);} )* CParen
      {
          return new InsertStatement($table_name.text, columnNames, columnValues);
      }
    ;

selectStatement returns [SelectStatement expr]
    : SELECT (select_quantifier)?
      select_list
      //INTO select_target_list
      FROM table_reference_list
      (WHERE where_condition)?
      (LIMIT limit_clause)?
      //(group_clause)?
      //(set_clause)?
      //(order_clause)?
      //(update_clause)?
      {
          return new SelectStatement($select_quantifier.text, columnInfo, tableInfo, $where_condition.arg);
      }
    ;

updateStatement returns [UpdateStatement expr]
    : {
          HashMap<String,String> cValues = new HashMap<String,String>();
      }
      UPDATE target_table
      SET a=set_clause { cValues.put($a.cname, $a.cvalue); }
         (Comma b=set_clause { cValues.put($b.cname, $b.cvalue); } )*
      (WHERE where_condition)?
      {
          return new UpdateStatement($target_table.arg, cValues, $where_condition.arg);
      }
    ;

alias
    : Identifier
    ;

column_def
    : {
          String not_null = null;
          String pkey = null;
          String pkey_type = null;
      }
      column_name column_type
      (
        NOT_NULL { not_null = "true"; }
      | PRIMARY_KEY { pkey = "true"; }  (key_type { pkey_type = $key_type.text.toLowerCase(); })?
      )*
      {
          addColumnDef($column_name.text, $column_type.type, not_null, pkey, pkey_type);
      }
    ;

column_name
    : Identifier
    ;

column_type returns [String type]
    : BIT         { $type = "boolean"; }
    | CHAR        { $type = "char"; } 
    | CHAR1       { $type = "char"; }
    | CHAR OParen PositiveNumber CParen { $type = "string"; }
    | VARCHAR     { $type = "string"; }
    | VARCHAR OParen PositiveNumber CParen { $type = "string"; }
    | LONGVARCHAR { $type = "string"; }
    | LONGVARCHAR OParen PositiveNumber CParen { $type = "string"; }
    | SMALLINT    { $type = "short"; }
    | INTEGER     { $type = "integer"; }
    | BIGINT      { $type = "long"; }
    | REAL        { $type = "float"; }
    | DOUBLE      { $type = "double"; }
    | BLOB        { $type = "blob"; }
    | DATETIME    { $type = "datetime"; }
    | DATE        { $type = "datetime"; }
    | TIME        { $type = "datetime"; }
    | TIMESTAMP   { $type = "datetime"; }
    ;

column_value
    :   QuotedString
    |   PositiveNumber
    |   NegativeNumber
    |   Float
    |   NULL
    |   TRUE
    |   FALSE
    |   ASDateTime
    ;

endStmnt
    : SColon?  EOF
    ;

key_type
    : HASH
    | TREE
    ;

limit_clause
    : PositiveNumber OFFSET PositiveNumber
    | (PositiveNumber Comma) PositiveNumber
    ;

relation returns [String arg]
    : a=rterm type=(Equals | NotEquals | GThan | GTEquals | LThan | LTEquals) b=rterm {$arg = $a.text + $type.text + $b.text;}
    | c=relation_null {$arg = $c.arg;}
    | d=relation_not {$arg = $d.arg;}
    | e=relation_in {$arg = $e.arg;}
    | f=relation_like {$arg = $f.arg;}
    ;

relation_in returns [String arg]
    : rterm IN OParen rterm_list CParen { $arg = $rterm.text + " IN (" + $rterm_list.text + ")"; }
    ;

relation_like returns [String arg]
    : a=rterm_identifier LIKE b=QuotedString {$arg = $a.text + " LIKE " + $b.text;}
    | c=rterm_identifier NOT LIKE d=QuotedString {$arg = $c.text + " NOT LIKE " + $d.text;}
    ;

relation_null returns [String arg]
    : a=rterm_identifier IS_NULL {$arg = $a.text + " IS NULL";}
    | b=rterm_identifier IS_NOT_NULL {$arg = $b.text + " IS NOT NULL";}
    ;

relation_not returns [String arg]
    : a=rterm_identifier NOT b=column_value {$arg = $a.text + " NOT " + $b.text;} (NOR c=column_value {$arg += " NOR " + $c.text;})?
    ;
            
rterm_list
    : rterm (Comma rterm)*
    ;

rterm_identifier
    : (table_name Dot)? Identifier
    ;

rterm
    : rterm_identifier
    | QMark
    | column_value
    ;
        
select_column returns [String cname, String tname, String calias]
    : (table_name Dot { $tname = $table_name.text; } )?
      column_name { $cname = $column_name.text; }
      (AS alias { $calias = $alias.text; })?
    ;

select_column_list
    : a=select_column { addColumnInfo($a.cname, $a.tname, $a.calias, null); }
      (Comma b=select_column { addColumnInfo($b.cname, $b.tname, $b.calias, null); })*
    ;

select_list
    : (fn=COUNT OParen)? Asterisk (CParen)? { addColumnInfo($Asterisk.text, null, null, $fn.text); }
    | a=table_name Dot b=Asterisk { addColumnInfo($b.text, $a.text, null, null); }
      (Comma c=table_name Dot d=Asterisk { addColumnInfo($d.text, $c.text, null, null); })*
    | select_column_list
    ;

select_quantifier
    : ALL
    | DISTINCT
    ;

set_clause returns [String cname, String cvalue]
    : c=column_name Equals v=column_value { $cname = $c.text; $cvalue = $v.text; }
    ;

space_property returns [String key, String value]
    : (   space_capacity { $key = $space_capacity.key; $value = $space_capacity.value; }
        | space_cache_policy { $key = $space_cache_policy.key; $value = $space_cache_policy.value; }
        | space_distribution_policy { $key = $space_distribution_policy.key; $value = $space_distribution_policy.value; }
        | space_eviction_policy { $key = $space_eviction_policy.key; $value = $space_eviction_policy.value; }
        | space_file_sync_interval { $key = $space_file_sync_interval.key; $value = $space_file_sync_interval.value; }
        | space_forget_old_value { $key = $space_forget_old_value.key; $value = $space_forget_old_value.value; }
        | space_host_aware_replication { $key = $space_host_aware_replication.key; $value = $space_host_aware_replication.value; }
        | space_lock_scope { $key = $space_lock_scope.key; $value = $space_lock_scope.value; }
        | space_lock_ttl { $key = $space_lock_ttl.key; $value = $space_lock_ttl.value; }
        | space_lock_wait { $key = $space_lock_wait.key; $value = $space_lock_wait.value; }
        | space_min_seeders { $key = $space_min_seeders.key; $value = $space_min_seeders.value; }
        | space_persistence_policy { $key = $space_persistence_policy.key; $value = $space_persistence_policy.value; }
        | space_persistence_type { $key = $space_persistence_type.key; $value = $space_persistence_type.value; }
        | space_phase_count { $key = $space_phase_count.key; $value = $space_phase_count.value; }
        | space_phase_interval { $key = $space_phase_interval.key; $value = $space_phase_interval.value; }
        | space_query_limit { $key = $space_query_limit.key; $value = $space_query_limit.value; }
        | space_query_timeout { $key = $space_query_timeout.key; $value = $space_query_timeout.value; }
        | space_read_timeout { $key = $space_read_timeout.key; $value = $space_read_timeout.value; }
        | space_replication_count { $key = $space_replication_count.key; $value = $space_replication_count.value; }
        | space_replication_policy { $key = $space_replication_policy.key; $value = $space_replication_policy.value; }
        | space_routed { $key = $space_routed.key; $value = $space_routed.value; }
        | space_ttl { $key = $space_ttl.key; $value = $space_ttl.value; }
        | space_update_transport { $key = $space_update_transport.key; $value = $space_update_transport.value; }
        | space_virtual_node_count { $key = $space_virtual_node_count.key; $value = $space_virtual_node_count.value; }
        | space_wait { $key = $space_wait.key; $value = $space_wait.value; }
        | space_write_timeout { $key = $space_write_timeout.key; $value = $space_write_timeout.value; }
      )
    ;

space_capacity returns [String key, String value]
    @init { $key = CreateStatement.CAPACITY; }
    : CAPACITY ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_cache_policy returns [String key, String value]
    @init { $key = CreateStatement.CACHE_POLICY; }
    : CACHE_POLICY READ_WRITE_THROUGH { $value = $READ_WRITE_THROUGH.text.toLowerCase(); }
    | CACHE_POLICY READ_THROUGH { $value = $READ_THROUGH.text.toLowerCase(); }
    ;

     
space_distribution_policy returns [String key, String value]
    @init { $key = CreateStatement.DISTRIBUTION_POLICY; }
    : DISTRIBUTION_POLICY DISTRIBUTED { $value = $DISTRIBUTED.text.toLowerCase(); }
    | DISTRIBUTION_POLICY NON_DISTRIBUTED { $value = $NON_DISTRIBUTED.text.toLowerCase(); }
    ;
    
space_eviction_policy returns [String key, String value]
    @init { $key = CreateStatement.EVICTION_POLICY; }
    : EVICTION_POLICY NONE { $value = $NONE.text.toLowerCase(); }
    | EVICTION_POLICY LRU { $value = $LRU.text.toLowerCase(); }
    ;

space_file_sync_interval returns [String key, String value]
    @init { $key = CreateStatement.FILE_SYNC_INTERVAL; }
    : FILE_SYNC_INTERVAL ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_forget_old_value returns [String key, String value]
    @init { $key = CreateStatement.FORGET_OLD_VALUE; }
    : FORGET_OLD_VALUE TRUE { $value = $TRUE.text.toLowerCase(); }
    | FORGET_OLD_VALUE FALSE { $value = $FALSE.text.toLowerCase(); }
    ;

space_host_aware_replication returns [String key, String value]
    @init { $key = CreateStatement.HOST_AWARE_REPLICATION; }
    : HOST_AWARE_REPLICATION TRUE { $value = $TRUE.text.toLowerCase(); }
    | HOST_AWARE_REPLICATION FALSE { $value = $FALSE.text.toLowerCase(); }
    ;

space_lock_scope returns [String key, String value]
    @init { $key = CreateStatement.LOCK_SCOPE; }
    : LOCK_SCOPE THREAD { $value = $THREAD.text.toLowerCase(); }
    | LOCK_SCOPE PROCESS { $value = $PROCESS.text.toLowerCase(); }
    ;

space_lock_ttl returns [String key, String value]
    @init { $key = CreateStatement.LOCK_TTL; }
    : LOCK_TTL ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_lock_wait returns [String key, String value]
    @init { $key = CreateStatement.LOCK_WAIT; }
    : LOCK_WAIT ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;
 
space_min_seeders returns [String key, String value]
    @init { $key = CreateStatement.MIN_SEEDERS; }
    : MIN_SEEDERS PositiveNumber { $value = $PositiveNumber.text; }
    ;
    
space_persistence_policy returns [String key, String value]
    @init { $key = CreateStatement.PERSISTENCE_POLICY; }
    : PERSISTENCE_POLICY SYNC { $value = $SYNC.text.toLowerCase(); }
    | PERSISTENCE_POLICY ASYNC { $value = $ASYNC.text.toLowerCase(); }
    ;
  
space_persistence_type returns [String key, String value]
    @init { $key = CreateStatement.PERSISTENCE_TYPE; }
    : PERSISTENCE a=NONE { $value = $a.text.toLowerCase(); }
    | PERSISTENCE b=(SHARED_ALL) { $value = $b.text.toLowerCase(); }
    | PERSISTENCE c=(SHARED_NOTHING) { $value = $c.text.toLowerCase(); }
    | PERSISTENCE_TYPE d=NONE { $value = $d.text.toLowerCase(); }
    | PERSISTENCE_TYPE e=(SHARED_ALL) { $value = $e.text.toLowerCase(); }
    | PERSISTENCE_TYPE f=(SHARED_NOTHING) { $value = $f.text.toLowerCase(); }
    ;

space_phase_count returns [String key, String value]
    @init { $key = CreateStatement.PHASE_COUNT; }
    : PHASE_COUNT ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_phase_interval returns [String key, String value]
    @init { $key = CreateStatement.PHASE_INTERVAL; }
    : PHASE_INTERVAL PositiveNumber { $value = $PositiveNumber.text; }
    ;

space_query_limit returns [String key, String value]
    @init { $key = CreateStatement.QUERY_LIMIT; }
    : QUERY_LIMIT ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_query_timeout returns [String key, String value]
    @init { $key = CreateStatement.QUERY_TIMEOUT; }
    : QUERY_TIMEOUT ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_read_timeout returns [String key, String value]
    @init { $key = CreateStatement.READ_TIMEOUT; }
    : READ_TIMEOUT PositiveNumber { $value = $PositiveNumber.text; }
    ;

space_replication_count returns [String key, String value]
    @init { $key = CreateStatement.REPLICATION_COUNT; }
    : REPLICATION_COUNT PositiveNumber { $value = $PositiveNumber.text; }
    ;

space_replication_policy returns [String key, String value]
    @init { $key = CreateStatement.REPLICATION_POLICY; }
    : REPLICATION_POLICY SYNC { $value = $SYNC.text.toLowerCase(); }
    | REPLICATION_POLICY ASYNC { $value = $ASYNC.text.toLowerCase(); }
    ;

space_routed returns [String key, String value]
    @init { $key = CreateStatement.ROUTED; }
    : ROUTED TRUE { $value = $TRUE.text.toLowerCase(); }
    | ROUTED FALSE { $value = $FALSE.text.toLowerCase(); }
    ;

space_ttl returns [String key, String value]
    @init { $key = CreateStatement.TTL; }
    : TTL ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_update_transport returns [String key, String value]
    @init { $key = CreateStatement.UPDATE_TRANSPORT; }
    : UPDATE_TRANSPORT UNICAST { $value = $UNICAST.text.toLowerCase(); }
    | UPDATE_TRANSPORT MULTICAST { $value = $MULTICAST.text.toLowerCase(); }
    ;

space_virtual_node_count returns [String key, String value]
    @init { $key = CreateStatement.VIRTUAL_NODE_COUNT; }
    : VIRTUAL_NODE_COUNT ( NegativeNumber { $value = $NegativeNumber.text; } | PositiveNumber { $value = $PositiveNumber.text; } )
    ;

space_wait returns [String key, String value]
    @init { $key = CreateStatement.SPACE_WAIT; }
    : SPACE_WAIT PositiveNumber { $value = $PositiveNumber.text; }
    ;

space_write_timeout returns [String key, String value]
    @init { $key = CreateStatement.WRITE_TIMEOUT; }
    : WRITE_TIMEOUT PositiveNumber { $value = $PositiveNumber.text; }
    ;
    
table_constraint
    : PRIMARY_KEY (key_type {pkeyType = $key_type.text; })? 
      OParen a=column_name { pkeyList.add($a.text); } 
      ( Comma b=column_name { pkeyList.add($b.text); } )* CParen
    ;

table_constraint_def
    : (CONSTRAINT Identifier)? table_constraint
    ;

table_element
    : column_def
    | table_constraint_def
    | table_index { indexList.add($table_index.index); }
    ;

table_index returns [ASSQLIndex index]
    : {
          String type = null;
      }
      INDEX table_index_name (key_type {type = $key_type.text;})? table_index_list
      {
          return new ASSQLIndex($table_index_name.text, type, $table_index_list.indexColumns);
      }
    ;

table_index_list returns [ArrayList<String> indexColumns]
    : {
          $indexColumns = new ArrayList<String>();
      }
      OParen a=column_name { $indexColumns.add($a.text); } (Comma b=column_name { $indexColumns.add($b.text); })* CParen
    ;
 
table_index_name
    : Identifier
    ;

table_name
    : Identifier
    ;
    
table_reference_list
    : // first table name is the actual table name, second table name is a correlation name
      a=table_name (b=table_name)? {addTableInfo($a.text, $b.text);}
      ( Comma c=table_name (d=table_name)? {addTableInfo($c.text, $d.text);})*
    ;

target_table returns [String arg]
    : table_name { $arg = $table_name.text; }
    | ONLY OParen table_name CParen { $arg = $table_name.text; }
    ;

where_condition returns [String arg]
    : a=where_relation       {$arg = $a.arg;}
    | b=where_relation_group {$arg = $b.arg;}
    | c=where_relation AND d=where_relation_group {$arg = $c.arg + " AND " + $d.arg;}
    | e=where_relation OR f=where_relation_group {$arg = $e.arg + " OR " + $f.arg;}
    | g=where_relation_group OR h=where_relation_group {$arg = $g.arg + " OR " + $h.arg;}
    | i=where_relation_group AND j=where_relation_group {$arg = $i.arg + " AND " + $j.arg;}
    | where_relation_set {$arg = $where_relation_set.arg;}
    ;

where_relation returns [String arg]
    : a=relation {$arg = $a.arg;}
    | b=relation {$arg = $b.arg;} (AND c=relation {$arg += " AND " + $c.arg;})+
    | d=relation {$arg = $d.arg;} (OR e=relation {$arg += " OR " + $e.arg;})+
    ;
     
where_relation_group returns [String arg]
    : OParen a=where_relation CParen {$arg = "(" + $a.arg + ")";}
    ;

where_relation_set returns [String arg]
    : OParen c=where_relation AND d=where_relation_group CParen {$arg = "(" + $c.arg + " AND " + $d.arg + ")";}
    | OParen e=where_relation OR f=where_relation_group CParen {$arg = "(" + $e.arg + " OR " + $f.arg + ")";}
    | OParen g=where_relation_group OR h=where_relation_group CParen {$arg = "(" + $g.arg + " OR " + $h.arg + ")";}
    | OParen i=where_relation_group AND j=where_relation_group CParen {$arg = "(" + $i.arg + " AND " + $j.arg + ")";}
    ;

// lexer rules

Asterisk   : '*';
Dot        : Period;
OParen     : '(';
CParen     : ')';
Comma      : ',';
SColon     : ';';
QMark      : '?';
Plus       : '+';
Minus      : '-';
Divide     : '/';
DVertbar   : '||';
Range      : '..';
Equals     : '=';
NotEquals  : '!=';
GThan      : '>';
GTEquals   : '>=';
LThan      : '<';
LTEquals   : '<=';
Underscore : '_';
GmtTag     : 'Z' | G M T;

// case insensitive keywords

// SQL commands
CREATE : C R E A T E;
SELECT : S E L E C T;
INSERT : I N S E R T;
UPDATE : U P D A T E;
DELETE : D E L E T E;

// SQL keywords
ALL          : A L L;
AND          : A N D;
AS           : A S;
BIGINT       : B I G I N T;
BIT          : B I T;
BLOB         : B L O B;
CHAR         : C H A R;
CHAR1        : C H A R OParen '1' CParen;
CONSTRAINT   : C O N S T R A I N T;
COUNT        : C O U N T;
DATE         : D A T E;
DATETIME     : D A T E T I M E;
DISTINCT     : D I S T I N C T;
DOUBLE       : D O U B L E;
FALSE        : F A L S E;
FROM         : F R O M;
IN           : I N;
INDEX        : I N D E X;
INTEGER      : I N T E G E R;
INTO         : I N T O;
LIKE         : L I K E;
LONGVARCHAR  : L O N G V A R C H A R;
MODULE       : M O D U L E;
NOT          : N O T;
NULL         : N U L L;
NOT_NULL     : NOT ' ' NULL;
ONLY         : O N L Y;
PRIMARY_KEY  : P R I M A R Y ' ' K E Y;
REAL         : R E A L;
SET          : S E T;
SMALLINT     : S M A L L I N T;
TABLE        : T A B L E;
TIME         : T I M E;
TIMESTAMP    : T I M E S T A M P;
TRUE         : T R U E;
VALUES       : V A L U E S;
VARCHAR      : V A R C H A R;
WHERE        : W H E R E;

// ActiveSpaces keywords
ASYNC               : A S Y N C;
CAPACITY            : C A P A C I T Y;
CACHE_POLICY        : C A C H E '_' P O L I C Y;
DISTRIBUTED         : D I S T R I B U T E D;
DISTRIBUTION_POLICY : D I S T R I B U T I O N '_' P O L I C Y;
EVICTION_POLICY     : E V I C T I O N '_' P O L I C Y;
FILE_SYNC_INTERVAL  : F I L E '_' S Y N C '_' I N T E R V A L;
FORGET_OLD_VALUE    : F O R G E T '_' O L D '_' V A L U E;
HASH                : H A S H;
HOST_AWARE_REPLICATION : H O S T '_' A W A R E '_' R E P L I C A T I O N;
IS                  : I S;
IS_NULL             : IS ' ' NULL;
IS_NOT_NULL         : IS ' ' NOT ' ' NULL;
LIMIT               : L I M I T;
LOCK_SCOPE          : L O C K '_' S C O P E;
LOCK_TTL            : L O C K '_' T T L;
LOCK_WAIT           : L O C K '_' W A I T;
LRU                 : L R U;
MIN_SEEDERS         : M I N '_' S E E D E R S;
MULTICAST           : M U L T I C A S T;
NON_DISTRIBUTED     : N O N '_' D I S T R I B U T E D;
NONE                : N O N E;
NOR                 : N O R;
OFFSET              : O F F S E T;
OR                  : O R;
PERSISTENCE         : P E R S I S T E N C E;
PERSISTENCE_POLICY  : P E R S I S T E N C E '_' P O L I C Y;
PERSISTENCE_TYPE    : P E R S I S T E N C E '_' T Y P E;
PHASE_COUNT         : P H A S E '_' C O U N T;
PHASE_INTERVAL      : P H A S E '_' I N T E R V A L;
PROCESS             : P R O C E S S;
QUERY_LIMIT         : Q U E R Y '_' L I M I T;
QUERY_TIMEOUT       : Q U E R Y '_' T I M E O U T;
READ_TIMEOUT        : R E A D '_' T I M E O U T;
READ_THROUGH        : R E A D '_' T H R O U G H;
READ_WRITE_THROUGH  : R E A D '_' W R I T E '_' T H R O U G H;
REPLICATION_COUNT   : R E P L I C A T I O N '_' C O U N T;
REPLICATION_POLICY  : R E P L I C A T I O N '_' P O L I C Y;
ROUTED              : R O U T E D;
SHARED_ALL          : S H A R E D '_' A L L;
SHARED_NOTHING      : S H A R E D '_' N O T H I N G;
SPACE_WAIT          : S P A C E '_' W A I T;
SYNC                : S Y N C;
THREAD              : T H R E A D;
TREE                : T R E E;
TTL                 : T T L;
UNICAST             : U N I C A S T;
UPDATE_TRANSPORT    : U P D A T E '_' T R A N S P O R T;
VIRTUAL_NODE_COUNT  : V I R T U A L '_' N O D E '_' C O U N T;
WRITE_TIMEOUT       : W R I T E '_' T I M E O U T;

// Case-insensitive alpha characters
fragment A: ('a'|'A');
fragment B: ('b'|'B');
fragment C: ('c'|'C');
fragment D: ('d'|'D');
fragment E: ('e'|'E');
fragment F: ('f'|'F');
fragment G: ('g'|'G');
fragment H: ('h'|'H');
fragment I: ('i'|'I');
fragment J: ('j'|'J');
fragment K: ('k'|'K');
fragment L: ('l'|'L');
fragment M: ('m'|'M');
fragment N: ('n'|'N');
fragment O: ('o'|'O');
fragment P: ('p'|'P');
fragment Q: ('q'|'Q');
fragment R: ('r'|'R');
fragment S: ('s'|'S');
fragment T: ('t'|'T');
fragment U: ('u'|'U');
fragment V: ('v'|'V');
fragment W: ('w'|'W');
fragment X: ('x'|'X');
fragment Y: ('y'|'Y');
fragment Z: ('z'|'Z');

QuotedString
    : '\''
      { StringBuilder b = new StringBuilder(); }
      ( c=~('\'') { b.appendCodePoint(c);}
      | '\'' '\'' { b.appendCodePoint('\'');}
      )*
      '\''
      {
        // always enclose the string in double quotes
        // AS filters always expect the double quotes around strings
        // we remove the double quotes later where they are not needed
        setText("\"" + b.toString() + "\"");
      }
    ;

ASDateTime
  : { StringBuilder d = new StringBuilder(); }
    ( ASDate | ASTime | ASDate 'T' ASTime )
    {
      // AS dates are always enclosed in single quotes
      d.append(getText());
      setText("\'" + d.toString() + "\'");
    }
  ;

fragment ASDate
  : Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit
  ;

fragment ASTime
  : Digit Digit ':' Digit Digit ':' Digit Digit (Dot Int)? (GmtTag)?
  ;

PositiveNumber
  : Int
  ;

NegativeNumber
  : '-' Int
  ;

Float
  : PositiveNumber '.' Digit (Digit)* ('F' | 'f')?
  | NegativeNumber '.' Digit (Digit)* ('F' | 'f')?
  ;

Identifier
  :  IdentifierStart IdentifierPart*
  | DoubleQuotedIdentifier
  ;

fragment DoubleQuotedIdentifier
  : '"'
    { StringBuilder b = new StringBuilder(); }
    ( c=~('"') { b.appendCodePoint(c); } )*
    '"'
    { setText(b.toString()); }
  ;

Comment
  :  '//' ~('\r' | '\n')* {skip();}
  |  '/*' .* '*/'         {skip();}
  ;

Space
  :  (SingleSpace | ControlSpace) {skip();}
  ;

fragment Period
  : '.'
  ;

fragment ControlSpace
  : '\t' | '\r' | '\n' | '\u000C'
  ;

fragment SingleSpace
  : ' '
  ;

fragment IdentifierStart
  : 'a'..'z' | 'A'..'Z';

fragment IdentifierPart
  : IdentifierStart | '_' | '-' | Digit;

fragment Int
  :  '1'..'9' Digit*
  |  '0'
  ;

fragment Digit
  :  '0'..'9'
  ;




