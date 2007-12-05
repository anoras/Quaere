grammar Quaere;

options
{
	output=AST;
	ASTLabelType=CommonTree;
	language=Java;
}

@header {
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.quaere.expressions.*;
}


@init {
}

quaereExpression returns [Expression value]
	: expression EOF! {$value = $expression.value; }
	;

expression returns [Expression value]
	:	expressionItem {$value = $expressionItem.value; } ('[' expression ']' { $value = new Indexer($expressionItem.value, $expression.value); })? 
	;
	
expressionItem returns [Expression value]
	:	left=conditionalExpression { $value = $left.value; } ( '?' middle=conditionalExpression ':' right=conditionalExpression { $value = new TernaryExpression($left.value, $middle.value, $right.value); })? 
	;

conditionalExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=booleanAndExpression { $value = $left.value; } (
			'||' { type = BinaryExpression.OperatorType.OR; } 
			right=booleanAndExpression { $value = new BinaryExpression(type, $value, $right.value); } 
			)* 
	;
		
booleanAndExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=equalityExpression { $value = $left.value; } (
			'&&' { type = BinaryExpression.OperatorType.AND; } 
			right=equalityExpression { $value = new BinaryExpression(type, $value, $right.value); } 
			)* 
	;

equalityExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=relationalExpression { $value = $left.value; } (
			( '==' { type = BinaryExpression.OperatorType.EQUAL; } 
			| '!=' { type = BinaryExpression.OperatorType.NOT_EQUAL; } ) 
			right=relationalExpression { $value = new BinaryExpression(type, $value, $right.value); } 
			)* 
	;
	
relationalExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=additiveExpression { $value = $left.value; } (
			( '<' { type = BinaryExpression.OperatorType.LESS_THAN; } 
			| '<=' { type = BinaryExpression.OperatorType.LESS_THAN_OR_EQUAL; }  
			| '>' { type = BinaryExpression.OperatorType.GREATER_THAN; } 
			| '>=' { type = BinaryExpression.OperatorType.GREATER_THAN_OR_EQUAL; } ) 
			right=additiveExpression { $value = new BinaryExpression(type, $value, $right.value); } 
			)* 
	;

additiveExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=multiplicativeExpression { $value = $left.value; } (
			( '+' { type = BinaryExpression.OperatorType.PLUS; } 
			| '-' { type = BinaryExpression.OperatorType.MINUS; } ) 
			right=multiplicativeExpression { $value = new BinaryExpression(type, $value, $right.value); } 
			)* 
	;

multiplicativeExpression returns [Expression value]
@init {
BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;
}
	:	left=unaryExpression { $value = $left.value; } (
			( '*' { type = BinaryExpression.OperatorType.MULTIPLY; } 
			| '/' { type = BinaryExpression.OperatorType.DIVIDE; } 
			| '%' { type = BinaryExpression.OperatorType.MODULO; } ) 
			right=unaryExpression { $value = new BinaryExpression(type, $left.value, $right.value); } 
			)* 
	;

	
unaryExpression returns [Expression value]
	:	statement { $value = $statement.value; }
    	|	'!' statement { $value = new UnaryExpression(UnaryExpression.OperatorType.NOT, $statement.value); }
    	|	'-' statement { $value = new UnaryExpression(UnaryExpression.OperatorType.NEGATE, $statement.value); }
   	;

statement returns [Statement value]
@init{
List<Expression> expressions = new ArrayList<Expression>();
}
	:	first=primaryExpression { expressions.add($first.value); } ( '.' follow=primaryExpression { expressions.add($follow.value); } )* 
	{ $value = new Statement(expressions); }
	;
	
		
primaryExpression returns [Expression value]
	:	'(' expression ')' 	{ $value = $expression.value; }
	|	expr=value		{ $value = $expr.value; }
	|	newExpression		{ $value = $newExpression.value; }
	|	identifier {$value = $identifier.value; } (arguments {$value = new MethodCall($identifier.value, ($arguments.value)); })?
	|	methodCall		{ $value = $methodCall.value; }
	| 	queryExpression		{ $value = $queryExpression.value; }
	;

value returns [Constant value]
	: 	INTEGER		{ $value = new Constant(Integer.valueOf($INTEGER.text), Integer.class); }
	|	FLOAT		{ $value = new Constant(Float.valueOf($FLOAT.text), Integer.class); }
	|	STRING		{ $value = new Constant($STRING.text.substring(1, $STRING.text.length()-2), String.class); }
	|	booleanValue		{ $value = new Constant($booleanValue.value, Boolean.class); }
	;

methodCall returns [MethodCall value]
@init {
List<Expression> parameters = new ArrayList<Expression>();
Identifier name = null;
Identifier anonIdentifier = null;
Identifier indexIdentifier = null;
Expression lambdaExpression = null;
}
	:	
	n2=identifier { name = $n2.value; } '('  (anon1=identifier { anonIdentifier = $anon1.value; } (',' indexer1=identifier { indexIdentifier = $indexer1.value; }) ? )?  '=>' lambda1=expression { lambdaExpression = $lambda1.value; } ')'
	{ $value = new MethodCall(name, Arrays.<Expression>asList(), anonIdentifier, indexIdentifier, lambdaExpression ); }
	|	n3=identifier { name = $n3.value; } '(' '(' (anon2=identifier { anonIdentifier = $anon2.value; } (',' indexer2=identifier { indexIdentifier = $indexer2.value; }) ? )? ')' '=>' lambda2=expression { lambdaExpression = $lambda2.value; } ')'
	{ $value = new MethodCall(name, Arrays.<Expression>asList(), anonIdentifier, indexIdentifier, lambdaExpression); }
	;

identifier returns[Identifier value]
	: 	ID { $value = new Identifier($ID.text); }
	;

newExpression returns[Expression value]
@init {
String typ = null;
Identifier id = null;
List<Property> parameters = new ArrayList<Property>();
}
	:	'create' (type { typ = $type.text; id = null; } )? '{' (firstid=identifier '=' { id = $firstid.value; } )? firstexp=expression { parameters.add(new Property(id, $firstexp.value)); } (',' (followid=identifier '=' { id = $followid.value; })? followexp=expression { parameters.add(new Property(id, $followexp.value)); })* '}'
			{$value = new NewExpression(typ, parameters); }
/*	| 	'create' type arguments  
			{$value = new NewExpression($type.text, ($arguments.value)); } */
	;			
	
queryExpression returns [QueryExpression value]
	: 	fromClause queryBody { $value = new QueryExpression($fromClause.value, $queryBody.value); }
	;
	
fromClause returns [FromClause value]
@init {
String t = null;
}
	:	'from' (type { t = $type.text; } )? identifier 'in' expression { $value = new FromClause(t, $identifier.value, $expression.value); }
	;
		
queryBody returns [QueryBody value]
@init {
List<QueryBodyClause> clauses = new ArrayList<QueryBodyClause>();
SelectOrGroupClause selOrGroup = null;
QueryContinuation qc = null;
}
	:	(queryBodyClause { clauses.add($queryBodyClause.value); } )* (selectClause { selOrGroup = $selectClause.value; } | groupClause { selOrGroup = $groupClause.value; }) (queryContinuation { qc = $queryContinuation.value; } )?
	{ $value = new QueryBody(clauses, selOrGroup, qc); }
	;
	
queryBodyClause returns [QueryBodyClause value]
	:	fromClause 	{ $value = $fromClause.value; }
	| 	letClause 	{ $value = $letClause.value; }
	| 	whereClause 	{ $value = $whereClause.value; }
	| 	joinClause 	{ $value = $joinClause.value; }
	| 	orderByClause	{ $value = $orderByClause.value; }
	;

queryContinuation returns[QueryContinuation value]
	: 	'into' identifier queryBody { $value = new QueryContinuation($identifier.value, $queryBody.value); }
	;
		
whereClause returns[WhereClause value]
	:	'where' expression { $value = new WhereClause($expression.value); }
	;
letClause returns[LetClause value]
	:	'let' identifier '=' expression { $value = new LetClause($identifier.value, $expression.value); }
	;
	
joinClause returns[JoinClause value]
@init{
String t = null;
Identifier i = null;
}
	:	'join' (type { t = $type.text; })?  join=identifier 'in' inid=expression 'on' on=expression 'equals' equals=expression ('into' into=identifier { i = $into.value; })?
	{ $value = new JoinClause($join.value, $inid.value, $on.value, $equals.value, i); }
	;
		
orderByClause returns [OrderByClause value]
@init {
List<OrderByCriteria> criterias = new ArrayList<OrderByCriteria>();
}
	:	'orderby' fis=expression fio=('ascending' | 'descending')? { criterias.add(new OrderByCriteria($fis.value, ($fio == null || $fio.text == "ascending")?OrderByCriteria.Direction.ASCENDING:OrderByCriteria.Direction.DESCENDING));}  (',' fos=expression foo=('ascending' | 'descending')? { criterias.add(new OrderByCriteria($fos.value, ($foo == null || $foo.text == "ascending")?OrderByCriteria.Direction.ASCENDING:OrderByCriteria.Direction.DESCENDING));})*
	{$value = new OrderByClause(criterias); }
	;
	
selectClause returns [SelectClause value]
	:	'select' expression
	{ $value = new SelectClause($expression.value); }
	;

groupClause returns [GroupClause value]
	:	'group' identifier 'by' expression { $value = new GroupClause($identifier.value, $expression.value); }
	;
	
type
	:	identifier ( '.' identifier)* 
	;

expressionList returns [List<Expression> value]
@init {
List<Expression> expressions = new ArrayList<Expression>();
}
	:	first=expression {expressions.add($first.value);}  ( ',' follow=expression {expressions.add($follow.value);})* 
	{ $value = expressions; }
	;
	
arguments returns [List<Expression> value]
@init {
$value = new ArrayList<Expression>();
}
	:	'(' ( expressionList {$value = $expressionList.value;} )? ')' 
	;			
ID 
	: 	LETTER (LETTER | DIGIT)*
	;

INTEGER
	:	DIGIT+
	;

FLOAT 
	:	DIGIT* '.' DIGIT+
	;

STRING 
    	:  	'\'' ( EscapeSequence | ~('\\'|'\'') )* '\''
    	;


booleanValue returns [boolean value]
	:	'true'	{ $value = true; }
	|	'false'	{ $value = false; }
	;
			
fragment LETTER
	:	'a'..'z'
	|	'A'..'Z'
	|	'_'
	;

fragment DIGIT
	:	'0'..'9'
	;

fragment
EscapeSequence
    	:   	'\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    	;

fragment
HexDigit 
	: 	('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
UnicodeEscape
    	:   	'\\' 'u' HexDigit HexDigit HexDigit HexDigit
    	;

/* Ignore white spaces */	
WS	:  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
	;
