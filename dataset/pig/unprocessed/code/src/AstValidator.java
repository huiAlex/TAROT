// $ANTLR 3.4 /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g 2017-12-23 14:37:20

package org.apache.pig.parser;

import org.apache.pig.data.DataType;
import org.apache.pig.impl.util.NumValCarrier;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 * Grammar file for Pig tree parser (visitor for default data type insertion).
 *
 * NOTE: THIS FILE IS BASED ON QueryParser.g, SO IF YOU CHANGE THAT FILE, YOU WILL
 *       PROBABLY NEED TO MAKE CORRESPONDING CHANGES TO THIS FILE AS WELL.
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class AstValidator extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALL", "AMPERSAND", "AND", "ARROBA", "AS", "ASC", "ASSERT", "BAG", "BIGDECIMAL", "BIGDECIMALNUMBER", "BIGINTEGER", "BIGINTEGERNUMBER", "BOOLEAN", "BY", "BYTEARRAY", "CACHE", "CASE", "CHARARRAY", "COGROUP", "COLON", "COMMA", "CROSS", "CUBE", "DATETIME", "DCOLON", "DEFINE", "DENSE", "DESC", "DIGIT", "DISTINCT", "DIV", "DOLLAR", "DOLLARVAR", "DOUBLE", "DOUBLENUMBER", "DOUBLE_PERIOD", "ELSE", "END", "EQUAL", "EXECCOMMAND", "FALSE", "FAT_ARROW", "FILTER", "FLATTEN", "FLOAT", "FLOATINGPOINT", "FLOATNUMBER", "FOREACH", "FULL", "GENERATE", "GROUP", "ID", "IDENTIFIER", "IF", "IMPORT", "IN", "INNER", "INPUT", "INT", "INTEGER", "INTO", "INVOKE", "IS", "JOIN", "LEFT", "LEFT_BRACKET", "LEFT_CURLY", "LEFT_PAREN", "LETTER", "LIMIT", "LOAD", "LONG", "LONGINTEGER", "MAP", "MAPREDUCE", "MINUS", "ML_COMMENT", "MULTILINE_QUOTEDSTRING", "NOT", "NULL", "NUM_OP_EQ", "NUM_OP_GT", "NUM_OP_GTE", "NUM_OP_LT", "NUM_OP_LTE", "NUM_OP_NE", "ONSCHEMA", "OR", "ORDER", "OTHERWISE", "OUTER", "OUTPUT", "PARALLEL", "PARTITION", "PERCENT", "PERIOD", "PLUS", "POUND", "QMARK", "QUOTEDSTRING", "RANK", "REGISTER", "RETURNS", "RIGHT", "RIGHT_BRACKET", "RIGHT_CURLY", "RIGHT_PAREN", "ROLLUP", "SAMPLE", "SEMI_COLON", "SHIP", "SL_COMMENT", "SPECIALCHAR", "SPLIT", "STAR", "STDERROR", "STDIN", "STDOUT", "STORE", "STREAM", "STR_OP_EQ", "STR_OP_GT", "STR_OP_GTE", "STR_OP_LT", "STR_OP_LTE", "STR_OP_MATCHES", "STR_OP_NE", "THEN", "THROUGH", "TRUE", "TUPLE", "UNION", "USING", "VOID", "WHEN", "WS", "ANY", "BAG_TYPE", "BAG_TYPE_CAST", "BAG_VAL", "BIN_EXPR", "BOOL", "BOOL_COND", "CASE_COND", "CASE_EXPR", "CASE_EXPR_LHS", "CASE_EXPR_RHS", "CAST_EXPR", "COL_RANGE", "EXPR_IN_PAREN", "FIELD_DEF", "FIELD_DEF_WITHOUT_IDENTIFIER", "FOREACH_PLAN_COMPLEX", "FOREACH_PLAN_SIMPLE", "FUNC", "FUNC_EVAL", "FUNC_REF", "INVOKER_FUNC_EVAL", "IN_LHS", "IN_RHS", "JOIN_ITEM", "KEY_VAL_PAIR", "MACRO_BODY", "MACRO_DEF", "MACRO_INLINE", "MAP_TYPE", "MAP_VAL", "NEG", "NESTED_CMD", "NESTED_CMD_ASSI", "NESTED_PROJ", "PARAMS", "QUERY", "REALIAS", "RETURN_VAL", "SPLIT_BRANCH", "STATEMENT", "TOBAG", "TOMAP", "TOTUPLE", "TUPLE_TYPE", "TUPLE_TYPE_CAST", "TUPLE_VAL", "EVAL", "MATCHES"
    };

    public static final int EOF=-1;
    public static final int ALL=4;
    public static final int AMPERSAND=5;
    public static final int AND=6;
    public static final int ARROBA=7;
    public static final int AS=8;
    public static final int ASC=9;
    public static final int ASSERT=10;
    public static final int BAG=11;
    public static final int BIGDECIMAL=12;
    public static final int BIGDECIMALNUMBER=13;
    public static final int BIGINTEGER=14;
    public static final int BIGINTEGERNUMBER=15;
    public static final int BOOLEAN=16;
    public static final int BY=17;
    public static final int BYTEARRAY=18;
    public static final int CACHE=19;
    public static final int CASE=20;
    public static final int CHARARRAY=21;
    public static final int COGROUP=22;
    public static final int COLON=23;
    public static final int COMMA=24;
    public static final int CROSS=25;
    public static final int CUBE=26;
    public static final int DATETIME=27;
    public static final int DCOLON=28;
    public static final int DEFINE=29;
    public static final int DENSE=30;
    public static final int DESC=31;
    public static final int DIGIT=32;
    public static final int DISTINCT=33;
    public static final int DIV=34;
    public static final int DOLLAR=35;
    public static final int DOLLARVAR=36;
    public static final int DOUBLE=37;
    public static final int DOUBLENUMBER=38;
    public static final int DOUBLE_PERIOD=39;
    public static final int ELSE=40;
    public static final int END=41;
    public static final int EQUAL=42;
    public static final int EXECCOMMAND=43;
    public static final int FALSE=44;
    public static final int FAT_ARROW=45;
    public static final int FILTER=46;
    public static final int FLATTEN=47;
    public static final int FLOAT=48;
    public static final int FLOATINGPOINT=49;
    public static final int FLOATNUMBER=50;
    public static final int FOREACH=51;
    public static final int FULL=52;
    public static final int GENERATE=53;
    public static final int GROUP=54;
    public static final int ID=55;
    public static final int IDENTIFIER=56;
    public static final int IF=57;
    public static final int IMPORT=58;
    public static final int IN=59;
    public static final int INNER=60;
    public static final int INPUT=61;
    public static final int INT=62;
    public static final int INTEGER=63;
    public static final int INTO=64;
    public static final int INVOKE=65;
    public static final int IS=66;
    public static final int JOIN=67;
    public static final int LEFT=68;
    public static final int LEFT_BRACKET=69;
    public static final int LEFT_CURLY=70;
    public static final int LEFT_PAREN=71;
    public static final int LETTER=72;
    public static final int LIMIT=73;
    public static final int LOAD=74;
    public static final int LONG=75;
    public static final int LONGINTEGER=76;
    public static final int MAP=77;
    public static final int MAPREDUCE=78;
    public static final int MINUS=79;
    public static final int ML_COMMENT=80;
    public static final int MULTILINE_QUOTEDSTRING=81;
    public static final int NOT=82;
    public static final int NULL=83;
    public static final int NUM_OP_EQ=84;
    public static final int NUM_OP_GT=85;
    public static final int NUM_OP_GTE=86;
    public static final int NUM_OP_LT=87;
    public static final int NUM_OP_LTE=88;
    public static final int NUM_OP_NE=89;
    public static final int ONSCHEMA=90;
    public static final int OR=91;
    public static final int ORDER=92;
    public static final int OTHERWISE=93;
    public static final int OUTER=94;
    public static final int OUTPUT=95;
    public static final int PARALLEL=96;
    public static final int PARTITION=97;
    public static final int PERCENT=98;
    public static final int PERIOD=99;
    public static final int PLUS=100;
    public static final int POUND=101;
    public static final int QMARK=102;
    public static final int QUOTEDSTRING=103;
    public static final int RANK=104;
    public static final int REGISTER=105;
    public static final int RETURNS=106;
    public static final int RIGHT=107;
    public static final int RIGHT_BRACKET=108;
    public static final int RIGHT_CURLY=109;
    public static final int RIGHT_PAREN=110;
    public static final int ROLLUP=111;
    public static final int SAMPLE=112;
    public static final int SEMI_COLON=113;
    public static final int SHIP=114;
    public static final int SL_COMMENT=115;
    public static final int SPECIALCHAR=116;
    public static final int SPLIT=117;
    public static final int STAR=118;
    public static final int STDERROR=119;
    public static final int STDIN=120;
    public static final int STDOUT=121;
    public static final int STORE=122;
    public static final int STREAM=123;
    public static final int STR_OP_EQ=124;
    public static final int STR_OP_GT=125;
    public static final int STR_OP_GTE=126;
    public static final int STR_OP_LT=127;
    public static final int STR_OP_LTE=128;
    public static final int STR_OP_MATCHES=129;
    public static final int STR_OP_NE=130;
    public static final int THEN=131;
    public static final int THROUGH=132;
    public static final int TRUE=133;
    public static final int TUPLE=134;
    public static final int UNION=135;
    public static final int USING=136;
    public static final int VOID=137;
    public static final int WHEN=138;
    public static final int WS=139;
    public static final int ANY=140;
    public static final int BAG_TYPE=141;
    public static final int BAG_TYPE_CAST=142;
    public static final int BAG_VAL=143;
    public static final int BIN_EXPR=144;
    public static final int BOOL=145;
    public static final int BOOL_COND=146;
    public static final int CASE_COND=147;
    public static final int CASE_EXPR=148;
    public static final int CASE_EXPR_LHS=149;
    public static final int CASE_EXPR_RHS=150;
    public static final int CAST_EXPR=151;
    public static final int COL_RANGE=152;
    public static final int EXPR_IN_PAREN=153;
    public static final int FIELD_DEF=154;
    public static final int FIELD_DEF_WITHOUT_IDENTIFIER=155;
    public static final int FOREACH_PLAN_COMPLEX=156;
    public static final int FOREACH_PLAN_SIMPLE=157;
    public static final int FUNC=158;
    public static final int FUNC_EVAL=159;
    public static final int FUNC_REF=160;
    public static final int INVOKER_FUNC_EVAL=161;
    public static final int IN_LHS=162;
    public static final int IN_RHS=163;
    public static final int JOIN_ITEM=164;
    public static final int KEY_VAL_PAIR=165;
    public static final int MACRO_BODY=166;
    public static final int MACRO_DEF=167;
    public static final int MACRO_INLINE=168;
    public static final int MAP_TYPE=169;
    public static final int MAP_VAL=170;
    public static final int NEG=171;
    public static final int NESTED_CMD=172;
    public static final int NESTED_CMD_ASSI=173;
    public static final int NESTED_PROJ=174;
    public static final int PARAMS=175;
    public static final int QUERY=176;
    public static final int REALIAS=177;
    public static final int RETURN_VAL=178;
    public static final int SPLIT_BRANCH=179;
    public static final int STATEMENT=180;
    public static final int TOBAG=181;
    public static final int TOMAP=182;
    public static final int TOTUPLE=183;
    public static final int TUPLE_TYPE=184;
    public static final int TUPLE_TYPE_CAST=185;
    public static final int TUPLE_VAL=186;
    public static final int EVAL=187;
    public static final int MATCHES=188;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public AstValidator(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public AstValidator(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return AstValidator.tokenNames; }
    public String getGrammarFileName() { return "/usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g"; }



    private static Log log = LogFactory.getLog( AstValidator.class );

    @Override
    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
    throws RecognitionException {
        throw new MismatchedTokenException( ttype, input );
    }

    @Override
    public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
    throws RecognitionException {
        throw e;
    }

    private void validateSchemaAliasName(Set<String> fieldNames, CommonTree node, String name)
    throws DuplicatedSchemaAliasException {
        if( fieldNames.contains( name ) ) {
            throw new DuplicatedSchemaAliasException( input,
                new SourceLocation( (PigParserNode)node ), name );
        } else {
            fieldNames.add( name );
        }
    }

    private void validateAliasRef(Set<String> aliases, CommonTree node, String alias)
    throws UndefinedAliasException {
        if( !aliases.contains( alias ) ) {
            throw new UndefinedAliasException( input, new SourceLocation( (PigParserNode)node ), alias );
        }
    }

    private void checkDuplication(int count, CommonTree node) throws ParserValidationException {
        if( count > 1 ) {
            throw new ParserValidationException( input, new SourceLocation( (PigParserNode)node ),
                "Duplicated command option" );
        }
    }

    private String lastRel = null;

    private String getLastRel(CommonTree node) throws UndefinedAliasException {
        if (lastRel != null) {
            return lastRel;
        }
        throw new UndefinedAliasException( input, new SourceLocation((PigParserNode)node), "@");
    }

    private Set<String> aliases = new HashSet<String>() {
        @Override
        public boolean add(String e) {
            lastRel = e;
            return super.add(e);
        }
    };



    public static class query_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "query"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:113:1: query : ^( QUERY ( statement )* ) ;
    public final AstValidator.query_return query() throws RecognitionException {
        AstValidator.query_return retval = new AstValidator.query_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUERY1=null;
        AstValidator.statement_return statement2 =null;


        CommonTree QUERY1_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:113:7: ( ^( QUERY ( statement )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:113:9: ^( QUERY ( statement )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            QUERY1=(CommonTree)match(input,QUERY,FOLLOW_QUERY_in_query80); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUERY1_tree = (CommonTree)adaptor.dupNode(QUERY1);


            root_1 = (CommonTree)adaptor.becomeRoot(QUERY1_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:113:18: ( statement )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==ASSERT||LA1_0==REGISTER||LA1_0==SPLIT||LA1_0==REALIAS||LA1_0==STATEMENT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:113:18: statement
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_statement_in_query82);
                	    statement2=statement();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, statement2.getTree());


                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "query"


    public static class statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:116:1: statement : ( general_statement | split_statement | realias_statement | register_statement | assert_statement );
    public final AstValidator.statement_return statement() throws RecognitionException {
        AstValidator.statement_return retval = new AstValidator.statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.general_statement_return general_statement3 =null;

        AstValidator.split_statement_return split_statement4 =null;

        AstValidator.realias_statement_return realias_statement5 =null;

        AstValidator.register_statement_return register_statement6 =null;

        AstValidator.assert_statement_return assert_statement7 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:116:11: ( general_statement | split_statement | realias_statement | register_statement | assert_statement )
            int alt2=5;
            switch ( input.LA(1) ) {
            case STATEMENT:
                {
                alt2=1;
                }
                break;
            case SPLIT:
                {
                alt2=2;
                }
                break;
            case REALIAS:
                {
                alt2=3;
                }
                break;
            case REGISTER:
                {
                alt2=4;
                }
                break;
            case ASSERT:
                {
                alt2=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:116:13: general_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_general_statement_in_statement94);
                    general_statement3=general_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, general_statement3.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:117:13: split_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_split_statement_in_statement108);
                    split_statement4=split_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, split_statement4.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:118:13: realias_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_realias_statement_in_statement122);
                    realias_statement5=realias_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, realias_statement5.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:119:13: register_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_register_statement_in_statement136);
                    register_statement6=register_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, register_statement6.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:120:13: assert_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_assert_statement_in_statement150);
                    assert_statement7=assert_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, assert_statement7.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class split_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "split_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:123:1: split_statement : split_clause ;
    public final AstValidator.split_statement_return split_statement() throws RecognitionException {
        AstValidator.split_statement_return retval = new AstValidator.split_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.split_clause_return split_clause8 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:123:17: ( split_clause )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:123:19: split_clause
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_split_clause_in_split_statement159);
            split_clause8=split_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, split_clause8.getTree());


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "split_statement"


    public static class realias_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "realias_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:126:1: realias_statement : realias_clause ;
    public final AstValidator.realias_statement_return realias_statement() throws RecognitionException {
        AstValidator.realias_statement_return retval = new AstValidator.realias_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.realias_clause_return realias_clause9 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:126:19: ( realias_clause )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:126:21: realias_clause
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_realias_clause_in_realias_statement168);
            realias_clause9=realias_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, realias_clause9.getTree());


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "realias_statement"


    public static class register_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "register_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:129:1: register_statement : ^( REGISTER QUOTEDSTRING ( USING IDENTIFIER AS IDENTIFIER )? ) ;
    public final AstValidator.register_statement_return register_statement() throws RecognitionException {
        AstValidator.register_statement_return retval = new AstValidator.register_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree REGISTER10=null;
        CommonTree QUOTEDSTRING11=null;
        CommonTree USING12=null;
        CommonTree IDENTIFIER13=null;
        CommonTree AS14=null;
        CommonTree IDENTIFIER15=null;

        CommonTree REGISTER10_tree=null;
        CommonTree QUOTEDSTRING11_tree=null;
        CommonTree USING12_tree=null;
        CommonTree IDENTIFIER13_tree=null;
        CommonTree AS14_tree=null;
        CommonTree IDENTIFIER15_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:129:20: ( ^( REGISTER QUOTEDSTRING ( USING IDENTIFIER AS IDENTIFIER )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:129:22: ^( REGISTER QUOTEDSTRING ( USING IDENTIFIER AS IDENTIFIER )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            REGISTER10=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_statement179); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            REGISTER10_tree = (CommonTree)adaptor.dupNode(REGISTER10);


            root_1 = (CommonTree)adaptor.becomeRoot(REGISTER10_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING11=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_register_statement181); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING11_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING11);


            adaptor.addChild(root_1, QUOTEDSTRING11_tree);
            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:129:47: ( USING IDENTIFIER AS IDENTIFIER )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==USING) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:129:48: USING IDENTIFIER AS IDENTIFIER
                    {
                    _last = (CommonTree)input.LT(1);
                    USING12=(CommonTree)match(input,USING,FOLLOW_USING_in_register_statement184); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    USING12_tree = (CommonTree)adaptor.dupNode(USING12);


                    adaptor.addChild(root_1, USING12_tree);
                    }


                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER13=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_register_statement186); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER13_tree = (CommonTree)adaptor.dupNode(IDENTIFIER13);


                    adaptor.addChild(root_1, IDENTIFIER13_tree);
                    }


                    _last = (CommonTree)input.LT(1);
                    AS14=(CommonTree)match(input,AS,FOLLOW_AS_in_register_statement188); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS14_tree = (CommonTree)adaptor.dupNode(AS14);


                    adaptor.addChild(root_1, AS14_tree);
                    }


                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER15=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_register_statement190); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER15_tree = (CommonTree)adaptor.dupNode(IDENTIFIER15);


                    adaptor.addChild(root_1, IDENTIFIER15_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "register_statement"


    public static class assert_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assert_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:132:1: assert_statement : assert_clause ;
    public final AstValidator.assert_statement_return assert_statement() throws RecognitionException {
        AstValidator.assert_statement_return retval = new AstValidator.assert_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.assert_clause_return assert_clause16 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:132:18: ( assert_clause )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:132:20: assert_clause
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_assert_clause_in_assert_statement203);
            assert_clause16=assert_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, assert_clause16.getTree());


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assert_statement"


    public static class general_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "general_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:1: general_statement : ^( STATEMENT ( alias )? op_clause ( parallel_clause )? ) ;
    public final AstValidator.general_statement_return general_statement() throws RecognitionException {
        AstValidator.general_statement_return retval = new AstValidator.general_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STATEMENT17=null;
        AstValidator.alias_return alias18 =null;

        AstValidator.op_clause_return op_clause19 =null;

        AstValidator.parallel_clause_return parallel_clause20 =null;


        CommonTree STATEMENT17_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:19: ( ^( STATEMENT ( alias )? op_clause ( parallel_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:21: ^( STATEMENT ( alias )? op_clause ( parallel_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            STATEMENT17=(CommonTree)match(input,STATEMENT,FOLLOW_STATEMENT_in_general_statement214); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STATEMENT17_tree = (CommonTree)adaptor.dupNode(STATEMENT17);


            root_1 = (CommonTree)adaptor.becomeRoot(STATEMENT17_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:34: ( alias )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENTIFIER) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:36: alias
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_alias_in_general_statement218);
                    alias18=alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, alias18.getTree());


                    if ( state.backtracking==0 ) { aliases.add( (alias18!=null?alias18.name:null) ); }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_op_clause_in_general_statement225);
            op_clause19=op_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, op_clause19.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:87: ( parallel_clause )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==PARALLEL) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:135:87: parallel_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_parallel_clause_in_general_statement227);
                    parallel_clause20=parallel_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, parallel_clause20.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "general_statement"


    public static class realias_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "realias_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:138:1: realias_clause : ^( REALIAS alias IDENTIFIER ) ;
    public final AstValidator.realias_clause_return realias_clause() throws RecognitionException {
        AstValidator.realias_clause_return retval = new AstValidator.realias_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree REALIAS21=null;
        CommonTree IDENTIFIER23=null;
        AstValidator.alias_return alias22 =null;


        CommonTree REALIAS21_tree=null;
        CommonTree IDENTIFIER23_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:138:16: ( ^( REALIAS alias IDENTIFIER ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:138:18: ^( REALIAS alias IDENTIFIER )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            REALIAS21=(CommonTree)match(input,REALIAS,FOLLOW_REALIAS_in_realias_clause240); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            REALIAS21_tree = (CommonTree)adaptor.dupNode(REALIAS21);


            root_1 = (CommonTree)adaptor.becomeRoot(REALIAS21_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_alias_in_realias_clause242);
            alias22=alias();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, alias22.getTree());


            _last = (CommonTree)input.LT(1);
            IDENTIFIER23=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_realias_clause244); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER23_tree = (CommonTree)adaptor.dupNode(IDENTIFIER23);


            adaptor.addChild(root_1, IDENTIFIER23_tree);
            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
                   aliases.add( (alias22!=null?alias22.name:null) );
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "realias_clause"


    public static class parallel_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parallel_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:144:1: parallel_clause : ^( PARALLEL INTEGER ) ;
    public final AstValidator.parallel_clause_return parallel_clause() throws RecognitionException {
        AstValidator.parallel_clause_return retval = new AstValidator.parallel_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PARALLEL24=null;
        CommonTree INTEGER25=null;

        CommonTree PARALLEL24_tree=null;
        CommonTree INTEGER25_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:144:17: ( ^( PARALLEL INTEGER ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:144:19: ^( PARALLEL INTEGER )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PARALLEL24=(CommonTree)match(input,PARALLEL,FOLLOW_PARALLEL_in_parallel_clause261); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PARALLEL24_tree = (CommonTree)adaptor.dupNode(PARALLEL24);


            root_1 = (CommonTree)adaptor.becomeRoot(PARALLEL24_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            INTEGER25=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_parallel_clause263); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            INTEGER25_tree = (CommonTree)adaptor.dupNode(INTEGER25);


            adaptor.addChild(root_1, INTEGER25_tree);
            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parallel_clause"


    public static class alias_return extends TreeRuleReturnScope {
        public String name;
        public CommonTree node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alias"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:147:1: alias returns [String name, CommonTree node] : IDENTIFIER ;
    public final AstValidator.alias_return alias() throws RecognitionException {
        AstValidator.alias_return retval = new AstValidator.alias_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENTIFIER26=null;

        CommonTree IDENTIFIER26_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:148:2: ( IDENTIFIER )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:148:4: IDENTIFIER
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENTIFIER26=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_alias278); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER26_tree = (CommonTree)adaptor.dupNode(IDENTIFIER26);


            adaptor.addChild(root_0, IDENTIFIER26_tree);
            }


            if ( state.backtracking==0 ) {
                   retval.name = (IDENTIFIER26!=null?IDENTIFIER26.getText():null);
                   retval.node = IDENTIFIER26;
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alias"


    public static class previous_rel_return extends TreeRuleReturnScope {
        public String name;
        public CommonTree node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "previous_rel"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:155:1: previous_rel returns [String name, CommonTree node] : ARROBA ;
    public final AstValidator.previous_rel_return previous_rel() throws RecognitionException {
        AstValidator.previous_rel_return retval = new AstValidator.previous_rel_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ARROBA27=null;

        CommonTree ARROBA27_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:156:2: ( ARROBA )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:156:4: ARROBA
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            ARROBA27=(CommonTree)match(input,ARROBA,FOLLOW_ARROBA_in_previous_rel296); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ARROBA27_tree = (CommonTree)adaptor.dupNode(ARROBA27);


            adaptor.addChild(root_0, ARROBA27_tree);
            }


            if ( state.backtracking==0 ) {
                   retval.name = getLastRel(ARROBA27);
                   retval.node = ARROBA27;
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "previous_rel"


    public static class op_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "op_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:163:1: op_clause : ( define_clause | load_clause | group_clause | store_clause | filter_clause | distinct_clause | limit_clause | sample_clause | order_clause | rank_clause | cross_clause | join_clause | union_clause | stream_clause | mr_clause | split_clause | foreach_clause | cube_clause | assert_clause );
    public final AstValidator.op_clause_return op_clause() throws RecognitionException {
        AstValidator.op_clause_return retval = new AstValidator.op_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.define_clause_return define_clause28 =null;

        AstValidator.load_clause_return load_clause29 =null;

        AstValidator.group_clause_return group_clause30 =null;

        AstValidator.store_clause_return store_clause31 =null;

        AstValidator.filter_clause_return filter_clause32 =null;

        AstValidator.distinct_clause_return distinct_clause33 =null;

        AstValidator.limit_clause_return limit_clause34 =null;

        AstValidator.sample_clause_return sample_clause35 =null;

        AstValidator.order_clause_return order_clause36 =null;

        AstValidator.rank_clause_return rank_clause37 =null;

        AstValidator.cross_clause_return cross_clause38 =null;

        AstValidator.join_clause_return join_clause39 =null;

        AstValidator.union_clause_return union_clause40 =null;

        AstValidator.stream_clause_return stream_clause41 =null;

        AstValidator.mr_clause_return mr_clause42 =null;

        AstValidator.split_clause_return split_clause43 =null;

        AstValidator.foreach_clause_return foreach_clause44 =null;

        AstValidator.cube_clause_return cube_clause45 =null;

        AstValidator.assert_clause_return assert_clause46 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:163:11: ( define_clause | load_clause | group_clause | store_clause | filter_clause | distinct_clause | limit_clause | sample_clause | order_clause | rank_clause | cross_clause | join_clause | union_clause | stream_clause | mr_clause | split_clause | foreach_clause | cube_clause | assert_clause )
            int alt6=19;
            switch ( input.LA(1) ) {
            case DEFINE:
                {
                alt6=1;
                }
                break;
            case LOAD:
                {
                alt6=2;
                }
                break;
            case COGROUP:
            case GROUP:
                {
                alt6=3;
                }
                break;
            case STORE:
                {
                alt6=4;
                }
                break;
            case FILTER:
                {
                alt6=5;
                }
                break;
            case DISTINCT:
                {
                alt6=6;
                }
                break;
            case LIMIT:
                {
                alt6=7;
                }
                break;
            case SAMPLE:
                {
                alt6=8;
                }
                break;
            case ORDER:
                {
                alt6=9;
                }
                break;
            case RANK:
                {
                alt6=10;
                }
                break;
            case CROSS:
                {
                alt6=11;
                }
                break;
            case JOIN:
                {
                alt6=12;
                }
                break;
            case UNION:
                {
                alt6=13;
                }
                break;
            case STREAM:
                {
                alt6=14;
                }
                break;
            case MAPREDUCE:
                {
                alt6=15;
                }
                break;
            case SPLIT:
                {
                alt6=16;
                }
                break;
            case FOREACH:
                {
                alt6=17;
                }
                break;
            case CUBE:
                {
                alt6=18;
                }
                break;
            case ASSERT:
                {
                alt6=19;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:163:13: define_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_define_clause_in_op_clause310);
                    define_clause28=define_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, define_clause28.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:164:13: load_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_load_clause_in_op_clause324);
                    load_clause29=load_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, load_clause29.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:165:13: group_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_group_clause_in_op_clause338);
                    group_clause30=group_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, group_clause30.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:166:13: store_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_store_clause_in_op_clause352);
                    store_clause31=store_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, store_clause31.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:167:13: filter_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_filter_clause_in_op_clause366);
                    filter_clause32=filter_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, filter_clause32.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:168:13: distinct_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_distinct_clause_in_op_clause380);
                    distinct_clause33=distinct_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, distinct_clause33.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:169:13: limit_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_limit_clause_in_op_clause394);
                    limit_clause34=limit_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, limit_clause34.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 8 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:170:13: sample_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_sample_clause_in_op_clause408);
                    sample_clause35=sample_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, sample_clause35.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 9 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:171:13: order_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_order_clause_in_op_clause422);
                    order_clause36=order_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, order_clause36.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 10 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:172:13: rank_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rank_clause_in_op_clause436);
                    rank_clause37=rank_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rank_clause37.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 11 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:173:13: cross_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cross_clause_in_op_clause450);
                    cross_clause38=cross_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, cross_clause38.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 12 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:174:13: join_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_join_clause_in_op_clause464);
                    join_clause39=join_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, join_clause39.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 13 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:175:13: union_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_union_clause_in_op_clause478);
                    union_clause40=union_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, union_clause40.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 14 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:176:13: stream_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_stream_clause_in_op_clause492);
                    stream_clause41=stream_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, stream_clause41.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 15 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:177:13: mr_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_mr_clause_in_op_clause506);
                    mr_clause42=mr_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, mr_clause42.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 16 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:178:13: split_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_split_clause_in_op_clause520);
                    split_clause43=split_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, split_clause43.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 17 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:179:13: foreach_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_foreach_clause_in_op_clause534);
                    foreach_clause44=foreach_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, foreach_clause44.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 18 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:180:13: cube_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cube_clause_in_op_clause548);
                    cube_clause45=cube_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, cube_clause45.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 19 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:181:13: assert_clause
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_assert_clause_in_op_clause562);
                    assert_clause46=assert_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, assert_clause46.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "op_clause"


    public static class define_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "define_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:1: define_clause : ^( DEFINE alias ( cmd | func_clause ) ) ;
    public final AstValidator.define_clause_return define_clause() throws RecognitionException {
        AstValidator.define_clause_return retval = new AstValidator.define_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DEFINE47=null;
        AstValidator.alias_return alias48 =null;

        AstValidator.cmd_return cmd49 =null;

        AstValidator.func_clause_return func_clause50 =null;


        CommonTree DEFINE47_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:15: ( ^( DEFINE alias ( cmd | func_clause ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:17: ^( DEFINE alias ( cmd | func_clause ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            DEFINE47=(CommonTree)match(input,DEFINE,FOLLOW_DEFINE_in_define_clause573); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEFINE47_tree = (CommonTree)adaptor.dupNode(DEFINE47);


            root_1 = (CommonTree)adaptor.becomeRoot(DEFINE47_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_alias_in_define_clause575);
            alias48=alias();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, alias48.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:33: ( cmd | func_clause )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==EXECCOMMAND) ) {
                alt7=1;
            }
            else if ( (LA7_0==FUNC||LA7_0==FUNC_REF) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:35: cmd
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cmd_in_define_clause579);
                    cmd49=cmd();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cmd49.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:184:41: func_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_clause_in_define_clause583);
                    func_clause50=func_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_clause50.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "define_clause"


    public static class cmd_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cmd"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:187:1: cmd : ^( EXECCOMMAND ( ship_clause | cache_clause | input_clause | output_clause | error_clause )* ) ;
    public final AstValidator.cmd_return cmd() throws RecognitionException {
        AstValidator.cmd_return retval = new AstValidator.cmd_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree EXECCOMMAND51=null;
        AstValidator.ship_clause_return ship_clause52 =null;

        AstValidator.cache_clause_return cache_clause53 =null;

        AstValidator.input_clause_return input_clause54 =null;

        AstValidator.output_clause_return output_clause55 =null;

        AstValidator.error_clause_return error_clause56 =null;


        CommonTree EXECCOMMAND51_tree=null;


            int ship = 0;
            int cache = 0;
            int in = 0;
            int out = 0;
            int error = 0;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:195:2: ( ^( EXECCOMMAND ( ship_clause | cache_clause | input_clause | output_clause | error_clause )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:195:4: ^( EXECCOMMAND ( ship_clause | cache_clause | input_clause | output_clause | error_clause )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            EXECCOMMAND51=(CommonTree)match(input,EXECCOMMAND,FOLLOW_EXECCOMMAND_in_cmd604); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EXECCOMMAND51_tree = (CommonTree)adaptor.dupNode(EXECCOMMAND51);


            root_1 = (CommonTree)adaptor.becomeRoot(EXECCOMMAND51_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:195:19: ( ship_clause | cache_clause | input_clause | output_clause | error_clause )*
                loop8:
                do {
                    int alt8=6;
                    switch ( input.LA(1) ) {
                    case SHIP:
                        {
                        alt8=1;
                        }
                        break;
                    case CACHE:
                        {
                        alt8=2;
                        }
                        break;
                    case INPUT:
                        {
                        alt8=3;
                        }
                        break;
                    case OUTPUT:
                        {
                        alt8=4;
                        }
                        break;
                    case STDERROR:
                        {
                        alt8=5;
                        }
                        break;

                    }

                    switch (alt8) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:195:21: ship_clause
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_ship_clause_in_cmd608);
                	    ship_clause52=ship_clause();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, ship_clause52.getTree());


                	    if ( state.backtracking==0 ) { checkDuplication( ++ship, (ship_clause52!=null?((CommonTree)ship_clause52.start):null) ); }

                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;
                	case 2 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:196:21: cache_clause
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_cache_clause_in_cmd632);
                	    cache_clause53=cache_clause();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, cache_clause53.getTree());


                	    if ( state.backtracking==0 ) { checkDuplication( ++cache, (cache_clause53!=null?((CommonTree)cache_clause53.start):null) ); }

                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;
                	case 3 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:197:21: input_clause
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_input_clause_in_cmd656);
                	    input_clause54=input_clause();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, input_clause54.getTree());


                	    if ( state.backtracking==0 ) { checkDuplication( ++in, (input_clause54!=null?((CommonTree)input_clause54.start):null) ); }

                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;
                	case 4 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:198:21: output_clause
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_output_clause_in_cmd680);
                	    output_clause55=output_clause();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, output_clause55.getTree());


                	    if ( state.backtracking==0 ) { checkDuplication( ++out, (output_clause55!=null?((CommonTree)output_clause55.start):null) ); }

                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;
                	case 5 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:199:21: error_clause
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_error_clause_in_cmd704);
                	    error_clause56=error_clause();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, error_clause56.getTree());


                	    if ( state.backtracking==0 ) { checkDuplication( ++error, (error_clause56!=null?((CommonTree)error_clause56.start):null) ); }

                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop8;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cmd"


    public static class ship_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ship_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:204:1: ship_clause : ^( SHIP ( path_list )? ) ;
    public final AstValidator.ship_clause_return ship_clause() throws RecognitionException {
        AstValidator.ship_clause_return retval = new AstValidator.ship_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SHIP57=null;
        AstValidator.path_list_return path_list58 =null;


        CommonTree SHIP57_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:204:13: ( ^( SHIP ( path_list )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:204:15: ^( SHIP ( path_list )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SHIP57=(CommonTree)match(input,SHIP,FOLLOW_SHIP_in_ship_clause743); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SHIP57_tree = (CommonTree)adaptor.dupNode(SHIP57);


            root_1 = (CommonTree)adaptor.becomeRoot(SHIP57_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:204:23: ( path_list )?
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==QUOTEDSTRING) ) {
                    alt9=1;
                }
                switch (alt9) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:204:23: path_list
                        {
                        _last = (CommonTree)input.LT(1);
                        pushFollow(FOLLOW_path_list_in_ship_clause745);
                        path_list58=path_list();

                        state._fsp--;
                        if (state.failed) return retval;
                        if ( state.backtracking==0 ) 
                        adaptor.addChild(root_1, path_list58.getTree());


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ship_clause"


    public static class path_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "path_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:207:1: path_list : ( QUOTEDSTRING )+ ;
    public final AstValidator.path_list_return path_list() throws RecognitionException {
        AstValidator.path_list_return retval = new AstValidator.path_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING59=null;

        CommonTree QUOTEDSTRING59_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:207:11: ( ( QUOTEDSTRING )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:207:13: ( QUOTEDSTRING )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:207:13: ( QUOTEDSTRING )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==QUOTEDSTRING) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:207:13: QUOTEDSTRING
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    QUOTEDSTRING59=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_path_list757); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    QUOTEDSTRING59_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING59);


            	    adaptor.addChild(root_0, QUOTEDSTRING59_tree);
            	    }


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path_list"


    public static class cache_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cache_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:210:1: cache_clause : ^( CACHE path_list ) ;
    public final AstValidator.cache_clause_return cache_clause() throws RecognitionException {
        AstValidator.cache_clause_return retval = new AstValidator.cache_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CACHE60=null;
        AstValidator.path_list_return path_list61 =null;


        CommonTree CACHE60_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:210:14: ( ^( CACHE path_list ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:210:16: ^( CACHE path_list )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CACHE60=(CommonTree)match(input,CACHE,FOLLOW_CACHE_in_cache_clause769); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CACHE60_tree = (CommonTree)adaptor.dupNode(CACHE60);


            root_1 = (CommonTree)adaptor.becomeRoot(CACHE60_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_path_list_in_cache_clause771);
            path_list61=path_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, path_list61.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cache_clause"


    public static class input_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "input_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:213:1: input_clause : ^( INPUT ( stream_cmd )+ ) ;
    public final AstValidator.input_clause_return input_clause() throws RecognitionException {
        AstValidator.input_clause_return retval = new AstValidator.input_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree INPUT62=null;
        AstValidator.stream_cmd_return stream_cmd63 =null;


        CommonTree INPUT62_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:213:14: ( ^( INPUT ( stream_cmd )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:213:16: ^( INPUT ( stream_cmd )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            INPUT62=(CommonTree)match(input,INPUT,FOLLOW_INPUT_in_input_clause784); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            INPUT62_tree = (CommonTree)adaptor.dupNode(INPUT62);


            root_1 = (CommonTree)adaptor.becomeRoot(INPUT62_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:213:25: ( stream_cmd )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==QUOTEDSTRING||(LA11_0 >= STDIN && LA11_0 <= STDOUT)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:213:25: stream_cmd
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stream_cmd_in_input_clause786);
            	    stream_cmd63=stream_cmd();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, stream_cmd63.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "input_clause"


    public static class stream_cmd_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stream_cmd"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:216:1: stream_cmd : ( ^( STDIN ( func_clause )? ) | ^( STDOUT ( func_clause )? ) | ^( QUOTEDSTRING ( func_clause )? ) );
    public final AstValidator.stream_cmd_return stream_cmd() throws RecognitionException {
        AstValidator.stream_cmd_return retval = new AstValidator.stream_cmd_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STDIN64=null;
        CommonTree STDOUT66=null;
        CommonTree QUOTEDSTRING68=null;
        AstValidator.func_clause_return func_clause65 =null;

        AstValidator.func_clause_return func_clause67 =null;

        AstValidator.func_clause_return func_clause69 =null;


        CommonTree STDIN64_tree=null;
        CommonTree STDOUT66_tree=null;
        CommonTree QUOTEDSTRING68_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:216:12: ( ^( STDIN ( func_clause )? ) | ^( STDOUT ( func_clause )? ) | ^( QUOTEDSTRING ( func_clause )? ) )
            int alt15=3;
            switch ( input.LA(1) ) {
            case STDIN:
                {
                alt15=1;
                }
                break;
            case STDOUT:
                {
                alt15=2;
                }
                break;
            case QUOTEDSTRING:
                {
                alt15=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:216:14: ^( STDIN ( func_clause )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    STDIN64=(CommonTree)match(input,STDIN,FOLLOW_STDIN_in_stream_cmd800); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STDIN64_tree = (CommonTree)adaptor.dupNode(STDIN64);


                    root_1 = (CommonTree)adaptor.becomeRoot(STDIN64_tree, root_1);
                    }


                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); if (state.failed) return retval;
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:216:23: ( func_clause )?
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==FUNC||LA12_0==FUNC_REF) ) {
                            alt12=1;
                        }
                        switch (alt12) {
                            case 1 :
                                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:216:23: func_clause
                                {
                                _last = (CommonTree)input.LT(1);
                                pushFollow(FOLLOW_func_clause_in_stream_cmd802);
                                func_clause65=func_clause();

                                state._fsp--;
                                if (state.failed) return retval;
                                if ( state.backtracking==0 ) 
                                adaptor.addChild(root_1, func_clause65.getTree());


                                if ( state.backtracking==0 ) {
                                }
                                }
                                break;

                        }


                        match(input, Token.UP, null); if (state.failed) return retval;
                    }
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:217:14: ^( STDOUT ( func_clause )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    STDOUT66=(CommonTree)match(input,STDOUT,FOLLOW_STDOUT_in_stream_cmd822); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STDOUT66_tree = (CommonTree)adaptor.dupNode(STDOUT66);


                    root_1 = (CommonTree)adaptor.becomeRoot(STDOUT66_tree, root_1);
                    }


                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); if (state.failed) return retval;
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:217:24: ( func_clause )?
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==FUNC||LA13_0==FUNC_REF) ) {
                            alt13=1;
                        }
                        switch (alt13) {
                            case 1 :
                                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:217:24: func_clause
                                {
                                _last = (CommonTree)input.LT(1);
                                pushFollow(FOLLOW_func_clause_in_stream_cmd824);
                                func_clause67=func_clause();

                                state._fsp--;
                                if (state.failed) return retval;
                                if ( state.backtracking==0 ) 
                                adaptor.addChild(root_1, func_clause67.getTree());


                                if ( state.backtracking==0 ) {
                                }
                                }
                                break;

                        }


                        match(input, Token.UP, null); if (state.failed) return retval;
                    }
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:218:14: ^( QUOTEDSTRING ( func_clause )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    QUOTEDSTRING68=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_stream_cmd844); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QUOTEDSTRING68_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING68);


                    root_1 = (CommonTree)adaptor.becomeRoot(QUOTEDSTRING68_tree, root_1);
                    }


                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); if (state.failed) return retval;
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:218:30: ( func_clause )?
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==FUNC||LA14_0==FUNC_REF) ) {
                            alt14=1;
                        }
                        switch (alt14) {
                            case 1 :
                                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:218:30: func_clause
                                {
                                _last = (CommonTree)input.LT(1);
                                pushFollow(FOLLOW_func_clause_in_stream_cmd846);
                                func_clause69=func_clause();

                                state._fsp--;
                                if (state.failed) return retval;
                                if ( state.backtracking==0 ) 
                                adaptor.addChild(root_1, func_clause69.getTree());


                                if ( state.backtracking==0 ) {
                                }
                                }
                                break;

                        }


                        match(input, Token.UP, null); if (state.failed) return retval;
                    }
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stream_cmd"


    public static class output_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "output_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:221:1: output_clause : ^( OUTPUT ( stream_cmd )+ ) ;
    public final AstValidator.output_clause_return output_clause() throws RecognitionException {
        AstValidator.output_clause_return retval = new AstValidator.output_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree OUTPUT70=null;
        AstValidator.stream_cmd_return stream_cmd71 =null;


        CommonTree OUTPUT70_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:221:15: ( ^( OUTPUT ( stream_cmd )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:221:17: ^( OUTPUT ( stream_cmd )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            OUTPUT70=(CommonTree)match(input,OUTPUT,FOLLOW_OUTPUT_in_output_clause860); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OUTPUT70_tree = (CommonTree)adaptor.dupNode(OUTPUT70);


            root_1 = (CommonTree)adaptor.becomeRoot(OUTPUT70_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:221:27: ( stream_cmd )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==QUOTEDSTRING||(LA16_0 >= STDIN && LA16_0 <= STDOUT)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:221:27: stream_cmd
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stream_cmd_in_output_clause862);
            	    stream_cmd71=stream_cmd();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, stream_cmd71.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "output_clause"


    public static class error_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "error_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:1: error_clause : ^( STDERROR ( QUOTEDSTRING ( INTEGER )? )? ) ;
    public final AstValidator.error_clause_return error_clause() throws RecognitionException {
        AstValidator.error_clause_return retval = new AstValidator.error_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STDERROR72=null;
        CommonTree QUOTEDSTRING73=null;
        CommonTree INTEGER74=null;

        CommonTree STDERROR72_tree=null;
        CommonTree QUOTEDSTRING73_tree=null;
        CommonTree INTEGER74_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:14: ( ^( STDERROR ( QUOTEDSTRING ( INTEGER )? )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:16: ^( STDERROR ( QUOTEDSTRING ( INTEGER )? )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            STDERROR72=(CommonTree)match(input,STDERROR,FOLLOW_STDERROR_in_error_clause876); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STDERROR72_tree = (CommonTree)adaptor.dupNode(STDERROR72);


            root_1 = (CommonTree)adaptor.becomeRoot(STDERROR72_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:29: ( QUOTEDSTRING ( INTEGER )? )?
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==QUOTEDSTRING) ) {
                    alt18=1;
                }
                switch (alt18) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:31: QUOTEDSTRING ( INTEGER )?
                        {
                        _last = (CommonTree)input.LT(1);
                        QUOTEDSTRING73=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_error_clause881); if (state.failed) return retval;
                        if ( state.backtracking==0 ) {
                        QUOTEDSTRING73_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING73);


                        adaptor.addChild(root_1, QUOTEDSTRING73_tree);
                        }


                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:44: ( INTEGER )?
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==INTEGER) ) {
                            alt17=1;
                        }
                        switch (alt17) {
                            case 1 :
                                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:224:44: INTEGER
                                {
                                _last = (CommonTree)input.LT(1);
                                INTEGER74=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_error_clause883); if (state.failed) return retval;
                                if ( state.backtracking==0 ) {
                                INTEGER74_tree = (CommonTree)adaptor.dupNode(INTEGER74);


                                adaptor.addChild(root_1, INTEGER74_tree);
                                }


                                if ( state.backtracking==0 ) {
                                }
                                }
                                break;

                        }


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "error_clause"


    public static class load_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "load_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:1: load_clause : ^( LOAD filename ( func_clause )? ( as_clause )? ) ;
    public final AstValidator.load_clause_return load_clause() throws RecognitionException {
        AstValidator.load_clause_return retval = new AstValidator.load_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree LOAD75=null;
        AstValidator.filename_return filename76 =null;

        AstValidator.func_clause_return func_clause77 =null;

        AstValidator.as_clause_return as_clause78 =null;


        CommonTree LOAD75_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:13: ( ^( LOAD filename ( func_clause )? ( as_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:15: ^( LOAD filename ( func_clause )? ( as_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            LOAD75=(CommonTree)match(input,LOAD,FOLLOW_LOAD_in_load_clause900); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LOAD75_tree = (CommonTree)adaptor.dupNode(LOAD75);


            root_1 = (CommonTree)adaptor.becomeRoot(LOAD75_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_filename_in_load_clause902);
            filename76=filename();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, filename76.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:32: ( func_clause )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FUNC||LA19_0==FUNC_REF) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:32: func_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_clause_in_load_clause904);
                    func_clause77=func_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_clause77.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:45: ( as_clause )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==AS) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:227:45: as_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_as_clause_in_load_clause907);
                    as_clause78=as_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, as_clause78.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "load_clause"


    public static class filename_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "filename"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:230:1: filename : QUOTEDSTRING ;
    public final AstValidator.filename_return filename() throws RecognitionException {
        AstValidator.filename_return retval = new AstValidator.filename_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING79=null;

        CommonTree QUOTEDSTRING79_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:230:10: ( QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:230:12: QUOTEDSTRING
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING79=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_filename919); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING79_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING79);


            adaptor.addChild(root_0, QUOTEDSTRING79_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "filename"


    public static class as_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "as_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:233:1: as_clause : ^( AS field_def_list ) ;
    public final AstValidator.as_clause_return as_clause() throws RecognitionException {
        AstValidator.as_clause_return retval = new AstValidator.as_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree AS80=null;
        AstValidator.field_def_list_return field_def_list81 =null;


        CommonTree AS80_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:233:10: ( ^( AS field_def_list ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:233:12: ^( AS field_def_list )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            AS80=(CommonTree)match(input,AS,FOLLOW_AS_in_as_clause929); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AS80_tree = (CommonTree)adaptor.dupNode(AS80);


            root_1 = (CommonTree)adaptor.becomeRoot(AS80_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_field_def_list_in_as_clause931);
            field_def_list81=field_def_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, field_def_list81.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "as_clause"


    public static class field_def_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "field_def"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:236:1: field_def[Set<String> fieldNames, NumValCarrier nvc] : ( ^( FIELD_DEF IDENTIFIER ( type )? ) | ^( FIELD_DEF_WITHOUT_IDENTIFIER type ) );
    public final AstValidator.field_def_return field_def(Set<String> fieldNames, NumValCarrier nvc) throws DuplicatedSchemaAliasException, RecognitionException {
        AstValidator.field_def_return retval = new AstValidator.field_def_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FIELD_DEF82=null;
        CommonTree IDENTIFIER83=null;
        CommonTree FIELD_DEF_WITHOUT_IDENTIFIER85=null;
        AstValidator.type_return type84 =null;

        AstValidator.type_return type86 =null;


        CommonTree FIELD_DEF82_tree=null;
        CommonTree IDENTIFIER83_tree=null;
        CommonTree FIELD_DEF_WITHOUT_IDENTIFIER85_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:237:2: ( ^( FIELD_DEF IDENTIFIER ( type )? ) | ^( FIELD_DEF_WITHOUT_IDENTIFIER type ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FIELD_DEF) ) {
                alt22=1;
            }
            else if ( (LA22_0==FIELD_DEF_WITHOUT_IDENTIFIER) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:237:4: ^( FIELD_DEF IDENTIFIER ( type )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FIELD_DEF82=(CommonTree)match(input,FIELD_DEF,FOLLOW_FIELD_DEF_in_field_def950); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FIELD_DEF82_tree = (CommonTree)adaptor.dupNode(FIELD_DEF82);


                    root_1 = (CommonTree)adaptor.becomeRoot(FIELD_DEF82_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER83=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_field_def952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER83_tree = (CommonTree)adaptor.dupNode(IDENTIFIER83);


                    adaptor.addChild(root_1, IDENTIFIER83_tree);
                    }


                    if ( state.backtracking==0 ) { validateSchemaAliasName( fieldNames, IDENTIFIER83, (IDENTIFIER83!=null?IDENTIFIER83.getText():null) ); }

                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:237:102: ( type )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==BIGDECIMAL||LA21_0==BIGINTEGER||LA21_0==BOOLEAN||LA21_0==BYTEARRAY||LA21_0==CHARARRAY||LA21_0==DATETIME||LA21_0==DOUBLE||LA21_0==FLOAT||LA21_0==INT||LA21_0==LONG||LA21_0==BAG_TYPE||LA21_0==MAP_TYPE||LA21_0==TUPLE_TYPE) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:237:102: type
                            {
                            _last = (CommonTree)input.LT(1);
                            pushFollow(FOLLOW_type_in_field_def956);
                            type84=type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) 
                            adaptor.addChild(root_1, type84.getTree());


                            if ( state.backtracking==0 ) {
                            }
                            }
                            break;

                    }


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:238:4: ^( FIELD_DEF_WITHOUT_IDENTIFIER type )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FIELD_DEF_WITHOUT_IDENTIFIER85=(CommonTree)match(input,FIELD_DEF_WITHOUT_IDENTIFIER,FOLLOW_FIELD_DEF_WITHOUT_IDENTIFIER_in_field_def966); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FIELD_DEF_WITHOUT_IDENTIFIER85_tree = (CommonTree)adaptor.dupNode(FIELD_DEF_WITHOUT_IDENTIFIER85);


                    root_1 = (CommonTree)adaptor.becomeRoot(FIELD_DEF_WITHOUT_IDENTIFIER85_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_type_in_field_def968);
                    type86=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, type86.getTree());


                    if ( state.backtracking==0 ) { validateSchemaAliasName ( fieldNames, FIELD_DEF_WITHOUT_IDENTIFIER85, nvc.makeNameFromDataType ( (type86!=null?type86.typev:0) ) ); }

                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "field_def"


    protected static class field_def_list_scope {
        Set<String> fieldNames;
        NumValCarrier nvc;
    }
    protected Stack field_def_list_stack = new Stack();


    public static class field_def_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "field_def_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:241:1: field_def_list : ( field_def[$field_def_list::fieldNames, $field_def_list::nvc] )+ ;
    public final AstValidator.field_def_list_return field_def_list() throws DuplicatedSchemaAliasException, RecognitionException {
        field_def_list_stack.push(new field_def_list_scope());
        AstValidator.field_def_list_return retval = new AstValidator.field_def_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.field_def_return field_def87 =null;




            ((field_def_list_scope)field_def_list_stack.peek()).fieldNames = new HashSet<String>();
            ((field_def_list_scope)field_def_list_stack.peek()).nvc = new NumValCarrier();

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:250:2: ( ( field_def[$field_def_list::fieldNames, $field_def_list::nvc] )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:250:4: ( field_def[$field_def_list::fieldNames, $field_def_list::nvc] )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:250:4: ( field_def[$field_def_list::fieldNames, $field_def_list::nvc] )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0 >= FIELD_DEF && LA23_0 <= FIELD_DEF_WITHOUT_IDENTIFIER)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:250:6: field_def[$field_def_list::fieldNames, $field_def_list::nvc]
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_field_def_in_field_def_list996);
            	    field_def87=field_def(((field_def_list_scope)field_def_list_stack.peek()).fieldNames, ((field_def_list_scope)field_def_list_stack.peek()).nvc);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, field_def87.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
            field_def_list_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "field_def_list"


    public static class type_return extends TreeRuleReturnScope {
        public byte typev;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:253:1: type returns [byte typev] : ( simple_type | tuple_type | bag_type | map_type );
    public final AstValidator.type_return type() throws RecognitionException {
        AstValidator.type_return retval = new AstValidator.type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.simple_type_return simple_type88 =null;

        AstValidator.tuple_type_return tuple_type89 =null;

        AstValidator.bag_type_return bag_type90 =null;

        AstValidator.map_type_return map_type91 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:254:3: ( simple_type | tuple_type | bag_type | map_type )
            int alt24=4;
            switch ( input.LA(1) ) {
            case BIGDECIMAL:
            case BIGINTEGER:
            case BOOLEAN:
            case BYTEARRAY:
            case CHARARRAY:
            case DATETIME:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
                {
                alt24=1;
                }
                break;
            case TUPLE_TYPE:
                {
                alt24=2;
                }
                break;
            case BAG_TYPE:
                {
                alt24=3;
                }
                break;
            case MAP_TYPE:
                {
                alt24=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:254:5: simple_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_simple_type_in_type1015);
                    simple_type88=simple_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, simple_type88.getTree());


                    if ( state.backtracking==0 ) { retval.typev = (simple_type88!=null?simple_type88.typev:0); }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:255:5: tuple_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_tuple_type_in_type1023);
                    tuple_type89=tuple_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, tuple_type89.getTree());


                    if ( state.backtracking==0 ) { retval.typev = DataType.TUPLE; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:256:5: bag_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_bag_type_in_type1031);
                    bag_type90=bag_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, bag_type90.getTree());


                    if ( state.backtracking==0 ) { retval.typev = DataType.BAG; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:257:5: map_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_map_type_in_type1039);
                    map_type91=map_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, map_type91.getTree());


                    if ( state.backtracking==0 ) { retval.typev = DataType.MAP; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "type"


    public static class simple_type_return extends TreeRuleReturnScope {
        public byte typev;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "simple_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:260:1: simple_type returns [byte typev] : ( BOOLEAN | INT | LONG | FLOAT | DOUBLE | BIGINTEGER | BIGDECIMAL | DATETIME | CHARARRAY | BYTEARRAY );
    public final AstValidator.simple_type_return simple_type() throws RecognitionException {
        AstValidator.simple_type_return retval = new AstValidator.simple_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BOOLEAN92=null;
        CommonTree INT93=null;
        CommonTree LONG94=null;
        CommonTree FLOAT95=null;
        CommonTree DOUBLE96=null;
        CommonTree BIGINTEGER97=null;
        CommonTree BIGDECIMAL98=null;
        CommonTree DATETIME99=null;
        CommonTree CHARARRAY100=null;
        CommonTree BYTEARRAY101=null;

        CommonTree BOOLEAN92_tree=null;
        CommonTree INT93_tree=null;
        CommonTree LONG94_tree=null;
        CommonTree FLOAT95_tree=null;
        CommonTree DOUBLE96_tree=null;
        CommonTree BIGINTEGER97_tree=null;
        CommonTree BIGDECIMAL98_tree=null;
        CommonTree DATETIME99_tree=null;
        CommonTree CHARARRAY100_tree=null;
        CommonTree BYTEARRAY101_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:261:3: ( BOOLEAN | INT | LONG | FLOAT | DOUBLE | BIGINTEGER | BIGDECIMAL | DATETIME | CHARARRAY | BYTEARRAY )
            int alt25=10;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt25=1;
                }
                break;
            case INT:
                {
                alt25=2;
                }
                break;
            case LONG:
                {
                alt25=3;
                }
                break;
            case FLOAT:
                {
                alt25=4;
                }
                break;
            case DOUBLE:
                {
                alt25=5;
                }
                break;
            case BIGINTEGER:
                {
                alt25=6;
                }
                break;
            case BIGDECIMAL:
                {
                alt25=7;
                }
                break;
            case DATETIME:
                {
                alt25=8;
                }
                break;
            case CHARARRAY:
                {
                alt25=9;
                }
                break;
            case BYTEARRAY:
                {
                alt25=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:261:5: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BOOLEAN92=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_simple_type1056); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLEAN92_tree = (CommonTree)adaptor.dupNode(BOOLEAN92);


                    adaptor.addChild(root_0, BOOLEAN92_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.BOOLEAN; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:262:5: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    INT93=(CommonTree)match(input,INT,FOLLOW_INT_in_simple_type1064); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT93_tree = (CommonTree)adaptor.dupNode(INT93);


                    adaptor.addChild(root_0, INT93_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.INTEGER; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:263:5: LONG
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    LONG94=(CommonTree)match(input,LONG,FOLLOW_LONG_in_simple_type1072); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONG94_tree = (CommonTree)adaptor.dupNode(LONG94);


                    adaptor.addChild(root_0, LONG94_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.LONG; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:264:5: FLOAT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FLOAT95=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_simple_type1080); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT95_tree = (CommonTree)adaptor.dupNode(FLOAT95);


                    adaptor.addChild(root_0, FLOAT95_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.FLOAT; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:265:5: DOUBLE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DOUBLE96=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_simple_type1088); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLE96_tree = (CommonTree)adaptor.dupNode(DOUBLE96);


                    adaptor.addChild(root_0, DOUBLE96_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.DOUBLE; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:266:5: BIGINTEGER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BIGINTEGER97=(CommonTree)match(input,BIGINTEGER,FOLLOW_BIGINTEGER_in_simple_type1096); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BIGINTEGER97_tree = (CommonTree)adaptor.dupNode(BIGINTEGER97);


                    adaptor.addChild(root_0, BIGINTEGER97_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.BIGINTEGER; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:267:5: BIGDECIMAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BIGDECIMAL98=(CommonTree)match(input,BIGDECIMAL,FOLLOW_BIGDECIMAL_in_simple_type1104); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BIGDECIMAL98_tree = (CommonTree)adaptor.dupNode(BIGDECIMAL98);


                    adaptor.addChild(root_0, BIGDECIMAL98_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.BIGDECIMAL; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 8 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:268:5: DATETIME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DATETIME99=(CommonTree)match(input,DATETIME,FOLLOW_DATETIME_in_simple_type1112); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DATETIME99_tree = (CommonTree)adaptor.dupNode(DATETIME99);


                    adaptor.addChild(root_0, DATETIME99_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.DATETIME; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 9 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:269:5: CHARARRAY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    CHARARRAY100=(CommonTree)match(input,CHARARRAY,FOLLOW_CHARARRAY_in_simple_type1120); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHARARRAY100_tree = (CommonTree)adaptor.dupNode(CHARARRAY100);


                    adaptor.addChild(root_0, CHARARRAY100_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.CHARARRAY; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 10 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:270:5: BYTEARRAY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BYTEARRAY101=(CommonTree)match(input,BYTEARRAY,FOLLOW_BYTEARRAY_in_simple_type1128); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BYTEARRAY101_tree = (CommonTree)adaptor.dupNode(BYTEARRAY101);


                    adaptor.addChild(root_0, BYTEARRAY101_tree);
                    }


                    if ( state.backtracking==0 ) { retval.typev = DataType.BYTEARRAY; }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "simple_type"


    public static class tuple_type_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tuple_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:273:1: tuple_type : ^( TUPLE_TYPE ( field_def_list )? ) ;
    public final AstValidator.tuple_type_return tuple_type() throws RecognitionException {
        AstValidator.tuple_type_return retval = new AstValidator.tuple_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree TUPLE_TYPE102=null;
        AstValidator.field_def_list_return field_def_list103 =null;


        CommonTree TUPLE_TYPE102_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:273:12: ( ^( TUPLE_TYPE ( field_def_list )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:273:14: ^( TUPLE_TYPE ( field_def_list )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            TUPLE_TYPE102=(CommonTree)match(input,TUPLE_TYPE,FOLLOW_TUPLE_TYPE_in_tuple_type1141); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TUPLE_TYPE102_tree = (CommonTree)adaptor.dupNode(TUPLE_TYPE102);


            root_1 = (CommonTree)adaptor.becomeRoot(TUPLE_TYPE102_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:273:28: ( field_def_list )?
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0 >= FIELD_DEF && LA26_0 <= FIELD_DEF_WITHOUT_IDENTIFIER)) ) {
                    alt26=1;
                }
                switch (alt26) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:273:28: field_def_list
                        {
                        _last = (CommonTree)input.LT(1);
                        pushFollow(FOLLOW_field_def_list_in_tuple_type1143);
                        field_def_list103=field_def_list();

                        state._fsp--;
                        if (state.failed) return retval;
                        if ( state.backtracking==0 ) 
                        adaptor.addChild(root_1, field_def_list103.getTree());


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "tuple_type"


    public static class bag_type_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bag_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:1: bag_type : ^( BAG_TYPE ( IDENTIFIER )? ( tuple_type )? ) ;
    public final AstValidator.bag_type_return bag_type() throws RecognitionException {
        AstValidator.bag_type_return retval = new AstValidator.bag_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BAG_TYPE104=null;
        CommonTree IDENTIFIER105=null;
        AstValidator.tuple_type_return tuple_type106 =null;


        CommonTree BAG_TYPE104_tree=null;
        CommonTree IDENTIFIER105_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:10: ( ^( BAG_TYPE ( IDENTIFIER )? ( tuple_type )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:12: ^( BAG_TYPE ( IDENTIFIER )? ( tuple_type )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BAG_TYPE104=(CommonTree)match(input,BAG_TYPE,FOLLOW_BAG_TYPE_in_bag_type1157); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BAG_TYPE104_tree = (CommonTree)adaptor.dupNode(BAG_TYPE104);


            root_1 = (CommonTree)adaptor.becomeRoot(BAG_TYPE104_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:24: ( IDENTIFIER )?
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==IDENTIFIER) ) {
                    alt27=1;
                }
                switch (alt27) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:24: IDENTIFIER
                        {
                        _last = (CommonTree)input.LT(1);
                        IDENTIFIER105=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_bag_type1159); if (state.failed) return retval;
                        if ( state.backtracking==0 ) {
                        IDENTIFIER105_tree = (CommonTree)adaptor.dupNode(IDENTIFIER105);


                        adaptor.addChild(root_1, IDENTIFIER105_tree);
                        }


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:36: ( tuple_type )?
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==TUPLE_TYPE) ) {
                    alt28=1;
                }
                switch (alt28) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:276:36: tuple_type
                        {
                        _last = (CommonTree)input.LT(1);
                        pushFollow(FOLLOW_tuple_type_in_bag_type1162);
                        tuple_type106=tuple_type();

                        state._fsp--;
                        if (state.failed) return retval;
                        if ( state.backtracking==0 ) 
                        adaptor.addChild(root_1, tuple_type106.getTree());


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bag_type"


    public static class map_type_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "map_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:1: map_type : ^( MAP_TYPE ( IDENTIFIER )? ( type )? ) ;
    public final AstValidator.map_type_return map_type() throws RecognitionException {
        AstValidator.map_type_return retval = new AstValidator.map_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree MAP_TYPE107=null;
        CommonTree IDENTIFIER108=null;
        AstValidator.type_return type109 =null;


        CommonTree MAP_TYPE107_tree=null;
        CommonTree IDENTIFIER108_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:10: ( ^( MAP_TYPE ( IDENTIFIER )? ( type )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:12: ^( MAP_TYPE ( IDENTIFIER )? ( type )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            MAP_TYPE107=(CommonTree)match(input,MAP_TYPE,FOLLOW_MAP_TYPE_in_map_type1176); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MAP_TYPE107_tree = (CommonTree)adaptor.dupNode(MAP_TYPE107);


            root_1 = (CommonTree)adaptor.becomeRoot(MAP_TYPE107_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:24: ( IDENTIFIER )?
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==IDENTIFIER) ) {
                    alt29=1;
                }
                switch (alt29) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:24: IDENTIFIER
                        {
                        _last = (CommonTree)input.LT(1);
                        IDENTIFIER108=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_map_type1178); if (state.failed) return retval;
                        if ( state.backtracking==0 ) {
                        IDENTIFIER108_tree = (CommonTree)adaptor.dupNode(IDENTIFIER108);


                        adaptor.addChild(root_1, IDENTIFIER108_tree);
                        }


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:36: ( type )?
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==BIGDECIMAL||LA30_0==BIGINTEGER||LA30_0==BOOLEAN||LA30_0==BYTEARRAY||LA30_0==CHARARRAY||LA30_0==DATETIME||LA30_0==DOUBLE||LA30_0==FLOAT||LA30_0==INT||LA30_0==LONG||LA30_0==BAG_TYPE||LA30_0==MAP_TYPE||LA30_0==TUPLE_TYPE) ) {
                    alt30=1;
                }
                switch (alt30) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:279:36: type
                        {
                        _last = (CommonTree)input.LT(1);
                        pushFollow(FOLLOW_type_in_map_type1181);
                        type109=type();

                        state._fsp--;
                        if (state.failed) return retval;
                        if ( state.backtracking==0 ) 
                        adaptor.addChild(root_1, type109.getTree());


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "map_type"


    public static class func_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "func_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:282:1: func_clause : ( ^( FUNC_REF func_name ) | ^( FUNC func_name ( func_args )? ) );
    public final AstValidator.func_clause_return func_clause() throws RecognitionException {
        AstValidator.func_clause_return retval = new AstValidator.func_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FUNC_REF110=null;
        CommonTree FUNC112=null;
        AstValidator.func_name_return func_name111 =null;

        AstValidator.func_name_return func_name113 =null;

        AstValidator.func_args_return func_args114 =null;


        CommonTree FUNC_REF110_tree=null;
        CommonTree FUNC112_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:282:13: ( ^( FUNC_REF func_name ) | ^( FUNC func_name ( func_args )? ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==FUNC_REF) ) {
                alt32=1;
            }
            else if ( (LA32_0==FUNC) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:282:15: ^( FUNC_REF func_name )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FUNC_REF110=(CommonTree)match(input,FUNC_REF,FOLLOW_FUNC_REF_in_func_clause1195); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FUNC_REF110_tree = (CommonTree)adaptor.dupNode(FUNC_REF110);


                    root_1 = (CommonTree)adaptor.becomeRoot(FUNC_REF110_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_name_in_func_clause1197);
                    func_name111=func_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_name111.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:283:15: ^( FUNC func_name ( func_args )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FUNC112=(CommonTree)match(input,FUNC,FOLLOW_FUNC_in_func_clause1217); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FUNC112_tree = (CommonTree)adaptor.dupNode(FUNC112);


                    root_1 = (CommonTree)adaptor.becomeRoot(FUNC112_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_name_in_func_clause1219);
                    func_name113=func_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_name113.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:283:33: ( func_args )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==MULTILINE_QUOTEDSTRING||LA31_0==QUOTEDSTRING) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:283:33: func_args
                            {
                            _last = (CommonTree)input.LT(1);
                            pushFollow(FOLLOW_func_args_in_func_clause1221);
                            func_args114=func_args();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) 
                            adaptor.addChild(root_1, func_args114.getTree());


                            if ( state.backtracking==0 ) {
                            }
                            }
                            break;

                    }


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "func_clause"


    public static class func_name_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "func_name"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:286:1: func_name : eid ( ( PERIOD | DOLLAR ) eid )* ;
    public final AstValidator.func_name_return func_name() throws RecognitionException {
        AstValidator.func_name_return retval = new AstValidator.func_name_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set116=null;
        AstValidator.eid_return eid115 =null;

        AstValidator.eid_return eid117 =null;


        CommonTree set116_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:286:11: ( eid ( ( PERIOD | DOLLAR ) eid )* )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:286:13: eid ( ( PERIOD | DOLLAR ) eid )*
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_eid_in_func_name1233);
            eid115=eid();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, eid115.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:286:17: ( ( PERIOD | DOLLAR ) eid )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==DOLLAR||LA33_0==PERIOD) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:286:19: ( PERIOD | DOLLAR ) eid
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    set116=(CommonTree)input.LT(1);

            	    if ( input.LA(1)==DOLLAR||input.LA(1)==PERIOD ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) {
            	        set116_tree = (CommonTree)adaptor.dupNode(set116);


            	        adaptor.addChild(root_0, set116_tree);
            	        }

            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_eid_in_func_name1247);
            	    eid117=eid();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, eid117.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "func_name"


    public static class func_args_string_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "func_args_string"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:289:1: func_args_string : ( QUOTEDSTRING | MULTILINE_QUOTEDSTRING );
    public final AstValidator.func_args_string_return func_args_string() throws RecognitionException {
        AstValidator.func_args_string_return retval = new AstValidator.func_args_string_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set118=null;

        CommonTree set118_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:289:18: ( QUOTEDSTRING | MULTILINE_QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set118=(CommonTree)input.LT(1);

            if ( input.LA(1)==MULTILINE_QUOTEDSTRING||input.LA(1)==QUOTEDSTRING ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set118_tree = (CommonTree)adaptor.dupNode(set118);


                adaptor.addChild(root_0, set118_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "func_args_string"


    public static class func_args_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "func_args"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:292:1: func_args : ( func_args_string )+ ;
    public final AstValidator.func_args_return func_args() throws RecognitionException {
        AstValidator.func_args_return retval = new AstValidator.func_args_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.func_args_string_return func_args_string119 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:292:11: ( ( func_args_string )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:292:13: ( func_args_string )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:292:13: ( func_args_string )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==MULTILINE_QUOTEDSTRING||LA34_0==QUOTEDSTRING) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:292:13: func_args_string
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_func_args_string_in_func_args1272);
            	    func_args_string119=func_args_string();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, func_args_string119.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "func_args"


    public static class cube_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:295:1: cube_clause : ^( CUBE cube_item ) ;
    public final AstValidator.cube_clause_return cube_clause() throws RecognitionException {
        AstValidator.cube_clause_return retval = new AstValidator.cube_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CUBE120=null;
        AstValidator.cube_item_return cube_item121 =null;


        CommonTree CUBE120_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:296:2: ( ^( CUBE cube_item ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:296:4: ^( CUBE cube_item )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CUBE120=(CommonTree)match(input,CUBE,FOLLOW_CUBE_in_cube_clause1285); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CUBE120_tree = (CommonTree)adaptor.dupNode(CUBE120);


            root_1 = (CommonTree)adaptor.becomeRoot(CUBE120_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cube_item_in_cube_clause1287);
            cube_item121=cube_item();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cube_item121.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_clause"


    public static class cube_item_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_item"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:299:1: cube_item : rel ( cube_by_clause ) ;
    public final AstValidator.cube_item_return cube_item() throws RecognitionException {
        AstValidator.cube_item_return retval = new AstValidator.cube_item_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.rel_return rel122 =null;

        AstValidator.cube_by_clause_return cube_by_clause123 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:300:2: ( rel ( cube_by_clause ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:300:4: rel ( cube_by_clause )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_cube_item1299);
            rel122=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, rel122.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:300:8: ( cube_by_clause )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:300:10: cube_by_clause
            {
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cube_by_clause_in_cube_item1303);
            cube_by_clause123=cube_by_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, cube_by_clause123.getTree());


            if ( state.backtracking==0 ) {
            }
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_item"


    public static class cube_by_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_by_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:303:1: cube_by_clause : ^( BY cube_or_rollup ) ;
    public final AstValidator.cube_by_clause_return cube_by_clause() throws RecognitionException {
        AstValidator.cube_by_clause_return retval = new AstValidator.cube_by_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BY124=null;
        AstValidator.cube_or_rollup_return cube_or_rollup125 =null;


        CommonTree BY124_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:304:2: ( ^( BY cube_or_rollup ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:304:4: ^( BY cube_or_rollup )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BY124=(CommonTree)match(input,BY,FOLLOW_BY_in_cube_by_clause1317); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BY124_tree = (CommonTree)adaptor.dupNode(BY124);


            root_1 = (CommonTree)adaptor.becomeRoot(BY124_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cube_or_rollup_in_cube_by_clause1319);
            cube_or_rollup125=cube_or_rollup();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cube_or_rollup125.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_by_clause"


    public static class cube_or_rollup_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_or_rollup"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:307:1: cube_or_rollup : ( cube_rollup_list )+ ;
    public final AstValidator.cube_or_rollup_return cube_or_rollup() throws RecognitionException {
        AstValidator.cube_or_rollup_return retval = new AstValidator.cube_or_rollup_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.cube_rollup_list_return cube_rollup_list126 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:308:2: ( ( cube_rollup_list )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:308:4: ( cube_rollup_list )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:308:4: ( cube_rollup_list )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==CUBE||LA35_0==ROLLUP) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:308:4: cube_rollup_list
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_cube_rollup_list_in_cube_or_rollup1331);
            	    cube_rollup_list126=cube_rollup_list();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, cube_rollup_list126.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_or_rollup"


    public static class cube_rollup_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_rollup_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:311:1: cube_rollup_list : ^( ( CUBE | ROLLUP ) cube_by_expr_list ) ;
    public final AstValidator.cube_rollup_list_return cube_rollup_list() throws RecognitionException {
        AstValidator.cube_rollup_list_return retval = new AstValidator.cube_rollup_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set127=null;
        AstValidator.cube_by_expr_list_return cube_by_expr_list128 =null;


        CommonTree set127_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:312:2: ( ^( ( CUBE | ROLLUP ) cube_by_expr_list ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:312:4: ^( ( CUBE | ROLLUP ) cube_by_expr_list )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            set127=(CommonTree)input.LT(1);

            if ( input.LA(1)==CUBE||input.LA(1)==ROLLUP ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set127_tree = (CommonTree)adaptor.dupNode(set127);


                root_1 = (CommonTree)adaptor.becomeRoot(set127_tree, root_1);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cube_by_expr_list_in_cube_rollup_list1354);
            cube_by_expr_list128=cube_by_expr_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cube_by_expr_list128.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_rollup_list"


    public static class cube_by_expr_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_by_expr_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:315:1: cube_by_expr_list : ( cube_by_expr )+ ;
    public final AstValidator.cube_by_expr_list_return cube_by_expr_list() throws RecognitionException {
        AstValidator.cube_by_expr_list_return retval = new AstValidator.cube_by_expr_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.cube_by_expr_return cube_by_expr129 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:316:2: ( ( cube_by_expr )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:316:4: ( cube_by_expr )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:316:4: ( cube_by_expr )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==BIGDECIMALNUMBER||LA36_0==BIGINTEGERNUMBER||LA36_0==CUBE||LA36_0==DIV||LA36_0==DOLLARVAR||LA36_0==DOUBLENUMBER||LA36_0==FALSE||LA36_0==FLOATNUMBER||LA36_0==GROUP||LA36_0==IDENTIFIER||LA36_0==INTEGER||LA36_0==LONGINTEGER||LA36_0==MINUS||LA36_0==NULL||LA36_0==PERCENT||LA36_0==PLUS||LA36_0==QUOTEDSTRING||LA36_0==STAR||LA36_0==TRUE||(LA36_0 >= BAG_VAL && LA36_0 <= BIN_EXPR)||(LA36_0 >= CASE_COND && LA36_0 <= CASE_EXPR)||(LA36_0 >= CAST_EXPR && LA36_0 <= EXPR_IN_PAREN)||LA36_0==FUNC_EVAL||LA36_0==INVOKER_FUNC_EVAL||(LA36_0 >= MAP_VAL && LA36_0 <= NEG)||LA36_0==TUPLE_VAL) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:316:4: cube_by_expr
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_cube_by_expr_in_cube_by_expr_list1366);
            	    cube_by_expr129=cube_by_expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, cube_by_expr129.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt36 >= 1 ) break loop36;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_by_expr_list"


    public static class cube_by_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cube_by_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:319:1: cube_by_expr : ( col_range | expr | STAR );
    public final AstValidator.cube_by_expr_return cube_by_expr() throws RecognitionException {
        AstValidator.cube_by_expr_return retval = new AstValidator.cube_by_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR132=null;
        AstValidator.col_range_return col_range130 =null;

        AstValidator.expr_return expr131 =null;


        CommonTree STAR132_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:320:2: ( col_range | expr | STAR )
            int alt37=3;
            switch ( input.LA(1) ) {
            case COL_RANGE:
                {
                alt37=1;
                }
                break;
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case CUBE:
            case DIV:
            case DOLLARVAR:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case GROUP:
            case IDENTIFIER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
            case NULL:
            case PERCENT:
            case PLUS:
            case QUOTEDSTRING:
            case TRUE:
            case BAG_VAL:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case CAST_EXPR:
            case EXPR_IN_PAREN:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
            case MAP_VAL:
            case NEG:
            case TUPLE_VAL:
                {
                alt37=2;
                }
                break;
            case STAR:
                {
                int LA37_3 = input.LA(2);

                if ( (LA37_3==DOWN) ) {
                    alt37=2;
                }
                else if ( (LA37_3==EOF||LA37_3==UP||LA37_3==BIGDECIMALNUMBER||LA37_3==BIGINTEGERNUMBER||LA37_3==CUBE||LA37_3==DIV||LA37_3==DOLLARVAR||LA37_3==DOUBLENUMBER||LA37_3==FALSE||LA37_3==FLOATNUMBER||LA37_3==GROUP||LA37_3==IDENTIFIER||LA37_3==INTEGER||LA37_3==LONGINTEGER||LA37_3==MINUS||LA37_3==NULL||LA37_3==PERCENT||LA37_3==PLUS||LA37_3==QUOTEDSTRING||LA37_3==STAR||LA37_3==TRUE||(LA37_3 >= BAG_VAL && LA37_3 <= BIN_EXPR)||(LA37_3 >= CASE_COND && LA37_3 <= CASE_EXPR)||(LA37_3 >= CAST_EXPR && LA37_3 <= EXPR_IN_PAREN)||LA37_3==FUNC_EVAL||LA37_3==INVOKER_FUNC_EVAL||(LA37_3 >= MAP_VAL && LA37_3 <= NEG)||LA37_3==TUPLE_VAL) ) {
                    alt37=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:320:4: col_range
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_cube_by_expr1377);
                    col_range130=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range130.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:320:16: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_cube_by_expr1381);
                    expr131=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, expr131.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:320:23: STAR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STAR132=(CommonTree)match(input,STAR,FOLLOW_STAR_in_cube_by_expr1385); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR132_tree = (CommonTree)adaptor.dupNode(STAR132);


                    adaptor.addChild(root_0, STAR132_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cube_by_expr"


    protected static class group_clause_scope {
        int arity;
    }
    protected Stack group_clause_stack = new Stack();


    public static class group_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "group_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:323:1: group_clause : ^( ( GROUP | COGROUP ) ( group_item )+ ( group_type )? ( partition_clause )? ) ;
    public final AstValidator.group_clause_return group_clause() throws RecognitionException {
        group_clause_stack.push(new group_clause_scope());
        AstValidator.group_clause_return retval = new AstValidator.group_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set133=null;
        AstValidator.group_item_return group_item134 =null;

        AstValidator.group_type_return group_type135 =null;

        AstValidator.partition_clause_return partition_clause136 =null;


        CommonTree set133_tree=null;


            ((group_clause_scope)group_clause_stack.peek()).arity = 0;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:2: ( ^( ( GROUP | COGROUP ) ( group_item )+ ( group_type )? ( partition_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:4: ^( ( GROUP | COGROUP ) ( group_item )+ ( group_type )? ( partition_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            set133=(CommonTree)input.LT(1);

            if ( input.LA(1)==COGROUP||input.LA(1)==GROUP ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set133_tree = (CommonTree)adaptor.dupNode(set133);


                root_1 = (CommonTree)adaptor.becomeRoot(set133_tree, root_1);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:27: ( group_item )+
            int cnt38=0;
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==ARROBA||LA38_0==ASSERT||LA38_0==COGROUP||(LA38_0 >= CROSS && LA38_0 <= CUBE)||LA38_0==DEFINE||LA38_0==DISTINCT||LA38_0==FILTER||LA38_0==FOREACH||LA38_0==GROUP||LA38_0==IDENTIFIER||LA38_0==JOIN||(LA38_0 >= LIMIT && LA38_0 <= LOAD)||LA38_0==MAPREDUCE||LA38_0==ORDER||LA38_0==RANK||LA38_0==SAMPLE||LA38_0==SPLIT||(LA38_0 >= STORE && LA38_0 <= STREAM)||LA38_0==UNION) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:27: group_item
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_group_item_in_group_clause1416);
            	    group_item134=group_item();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, group_item134.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt38 >= 1 ) break loop38;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(38, input);
                        throw eee;
                }
                cnt38++;
            } while (true);


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:39: ( group_type )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==QUOTEDSTRING) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:39: group_type
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_group_type_in_group_clause1419);
                    group_type135=group_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, group_type135.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:51: ( partition_clause )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==PARTITION) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:330:51: partition_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_partition_clause_in_group_clause1422);
                    partition_clause136=partition_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, partition_clause136.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
            group_clause_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "group_clause"


    public static class group_type_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "group_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:333:1: group_type : QUOTEDSTRING ;
    public final AstValidator.group_type_return group_type() throws RecognitionException {
        AstValidator.group_type_return retval = new AstValidator.group_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING137=null;

        CommonTree QUOTEDSTRING137_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:333:12: ( QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:333:14: QUOTEDSTRING
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING137=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_group_type1434); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING137_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING137);


            adaptor.addChild(root_0, QUOTEDSTRING137_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "group_type"


    public static class group_item_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "group_item"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:336:1: group_item : rel ( join_group_by_clause | ALL | ANY ) ( INNER | OUTER )? ;
    public final AstValidator.group_item_return group_item() throws RecognitionException {
        AstValidator.group_item_return retval = new AstValidator.group_item_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ALL140=null;
        CommonTree ANY141=null;
        CommonTree set142=null;
        AstValidator.rel_return rel138 =null;

        AstValidator.join_group_by_clause_return join_group_by_clause139 =null;


        CommonTree ALL140_tree=null;
        CommonTree ANY141_tree=null;
        CommonTree set142_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:2: ( rel ( join_group_by_clause | ALL | ANY ) ( INNER | OUTER )? )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:4: rel ( join_group_by_clause | ALL | ANY ) ( INNER | OUTER )?
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_group_item1444);
            rel138=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, rel138.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:8: ( join_group_by_clause | ALL | ANY )
            int alt41=3;
            switch ( input.LA(1) ) {
            case BY:
                {
                alt41=1;
                }
                break;
            case ALL:
                {
                alt41=2;
                }
                break;
            case ANY:
                {
                alt41=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }

            switch (alt41) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:10: join_group_by_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_join_group_by_clause_in_group_item1448);
                    join_group_by_clause139=join_group_by_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, join_group_by_clause139.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:33: ALL
                    {
                    _last = (CommonTree)input.LT(1);
                    ALL140=(CommonTree)match(input,ALL,FOLLOW_ALL_in_group_item1452); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALL140_tree = (CommonTree)adaptor.dupNode(ALL140);


                    adaptor.addChild(root_0, ALL140_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:39: ANY
                    {
                    _last = (CommonTree)input.LT(1);
                    ANY141=(CommonTree)match(input,ANY,FOLLOW_ANY_in_group_item1456); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ANY141_tree = (CommonTree)adaptor.dupNode(ANY141);


                    adaptor.addChild(root_0, ANY141_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:337:45: ( INNER | OUTER )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==INNER||LA42_0==OUTER) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                    {
                    _last = (CommonTree)input.LT(1);
                    set142=(CommonTree)input.LT(1);

                    if ( input.LA(1)==INNER||input.LA(1)==OUTER ) {
                        input.consume();
                        if ( state.backtracking==0 ) {
                        set142_tree = (CommonTree)adaptor.dupNode(set142);


                        adaptor.addChild(root_0, set142_tree);
                        }

                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {
                   if( ((group_clause_scope)group_clause_stack.peek()).arity == 0 ) {
                       // For the first input
                       ((group_clause_scope)group_clause_stack.peek()).arity = (join_group_by_clause139!=null?join_group_by_clause139.exprCount:0);
                   } else if( (join_group_by_clause139!=null?join_group_by_clause139.exprCount:0) != ((group_clause_scope)group_clause_stack.peek()).arity ) {
                       throw new ParserValidationException( input, new SourceLocation( (PigParserNode)((CommonTree)retval.start) ),
                           "The arity of the group by columns do not match." );
                   }
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "group_item"


    public static class rel_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:349:1: rel : ( alias | previous_rel | op_clause ( parallel_clause )? );
    public final AstValidator.rel_return rel() throws RecognitionException {
        AstValidator.rel_return retval = new AstValidator.rel_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.alias_return alias143 =null;

        AstValidator.previous_rel_return previous_rel144 =null;

        AstValidator.op_clause_return op_clause145 =null;

        AstValidator.parallel_clause_return parallel_clause146 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:349:5: ( alias | previous_rel | op_clause ( parallel_clause )? )
            int alt44=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt44=1;
                }
                break;
            case ARROBA:
                {
                alt44=2;
                }
                break;
            case ASSERT:
            case COGROUP:
            case CROSS:
            case CUBE:
            case DEFINE:
            case DISTINCT:
            case FILTER:
            case FOREACH:
            case GROUP:
            case JOIN:
            case LIMIT:
            case LOAD:
            case MAPREDUCE:
            case ORDER:
            case RANK:
            case SAMPLE:
            case SPLIT:
            case STORE:
            case STREAM:
            case UNION:
                {
                alt44=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }

            switch (alt44) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:349:7: alias
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_alias_in_rel1483);
                    alias143=alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, alias143.getTree());


                    if ( state.backtracking==0 ) {  validateAliasRef( aliases, (alias143!=null?alias143.node:null), (alias143!=null?alias143.name:null) ); }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:350:7: previous_rel
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_previous_rel_in_rel1493);
                    previous_rel144=previous_rel();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, previous_rel144.getTree());


                    if ( state.backtracking==0 ) { validateAliasRef( aliases, (previous_rel144!=null?previous_rel144.node:null), (previous_rel144!=null?previous_rel144.name:null) ); }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:351:7: op_clause ( parallel_clause )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_op_clause_in_rel1503);
                    op_clause145=op_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, op_clause145.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:351:17: ( parallel_clause )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==PARALLEL) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:351:17: parallel_clause
                            {
                            _last = (CommonTree)input.LT(1);
                            pushFollow(FOLLOW_parallel_clause_in_rel1505);
                            parallel_clause146=parallel_clause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) 
                            adaptor.addChild(root_0, parallel_clause146.getTree());


                            if ( state.backtracking==0 ) {
                            }
                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel"


    public static class flatten_generated_item_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "flatten_generated_item"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:1: flatten_generated_item : ( flatten_clause | col_range | expr | STAR ) ( field_def_list )? ;
    public final AstValidator.flatten_generated_item_return flatten_generated_item() throws RecognitionException {
        AstValidator.flatten_generated_item_return retval = new AstValidator.flatten_generated_item_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR150=null;
        AstValidator.flatten_clause_return flatten_clause147 =null;

        AstValidator.col_range_return col_range148 =null;

        AstValidator.expr_return expr149 =null;

        AstValidator.field_def_list_return field_def_list151 =null;


        CommonTree STAR150_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:24: ( ( flatten_clause | col_range | expr | STAR ) ( field_def_list )? )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:26: ( flatten_clause | col_range | expr | STAR ) ( field_def_list )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:26: ( flatten_clause | col_range | expr | STAR )
            int alt45=4;
            switch ( input.LA(1) ) {
            case FLATTEN:
                {
                alt45=1;
                }
                break;
            case COL_RANGE:
                {
                alt45=2;
                }
                break;
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case CUBE:
            case DIV:
            case DOLLARVAR:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case GROUP:
            case IDENTIFIER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
            case NULL:
            case PERCENT:
            case PLUS:
            case QUOTEDSTRING:
            case TRUE:
            case BAG_VAL:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case CAST_EXPR:
            case EXPR_IN_PAREN:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
            case MAP_VAL:
            case NEG:
            case TUPLE_VAL:
                {
                alt45=3;
                }
                break;
            case STAR:
                {
                int LA45_4 = input.LA(2);

                if ( (LA45_4==DOWN) ) {
                    alt45=3;
                }
                else if ( (LA45_4==EOF||LA45_4==UP||LA45_4==BIGDECIMALNUMBER||LA45_4==BIGINTEGERNUMBER||LA45_4==CUBE||LA45_4==DIV||LA45_4==DOLLARVAR||LA45_4==DOUBLENUMBER||LA45_4==FALSE||LA45_4==FLATTEN||LA45_4==FLOATNUMBER||LA45_4==GROUP||LA45_4==IDENTIFIER||LA45_4==INTEGER||LA45_4==LONGINTEGER||LA45_4==MINUS||LA45_4==NULL||LA45_4==PERCENT||LA45_4==PLUS||LA45_4==QUOTEDSTRING||LA45_4==STAR||LA45_4==TRUE||(LA45_4 >= BAG_VAL && LA45_4 <= BIN_EXPR)||(LA45_4 >= CASE_COND && LA45_4 <= CASE_EXPR)||(LA45_4 >= CAST_EXPR && LA45_4 <= FIELD_DEF_WITHOUT_IDENTIFIER)||LA45_4==FUNC_EVAL||LA45_4==INVOKER_FUNC_EVAL||(LA45_4 >= MAP_VAL && LA45_4 <= NEG)||LA45_4==TUPLE_VAL) ) {
                    alt45=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 4, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }

            switch (alt45) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:28: flatten_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_flatten_clause_in_flatten_generated_item1517);
                    flatten_clause147=flatten_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, flatten_clause147.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:45: col_range
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_flatten_generated_item1521);
                    col_range148=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range148.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:57: expr
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_flatten_generated_item1525);
                    expr149=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, expr149.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:64: STAR
                    {
                    _last = (CommonTree)input.LT(1);
                    STAR150=(CommonTree)match(input,STAR,FOLLOW_STAR_in_flatten_generated_item1529); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR150_tree = (CommonTree)adaptor.dupNode(STAR150);


                    adaptor.addChild(root_0, STAR150_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:71: ( field_def_list )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( ((LA46_0 >= FIELD_DEF && LA46_0 <= FIELD_DEF_WITHOUT_IDENTIFIER)) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:354:71: field_def_list
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_field_def_list_in_flatten_generated_item1533);
                    field_def_list151=field_def_list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, field_def_list151.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "flatten_generated_item"


    public static class flatten_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "flatten_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:357:1: flatten_clause : ^( FLATTEN expr ) ;
    public final AstValidator.flatten_clause_return flatten_clause() throws RecognitionException {
        AstValidator.flatten_clause_return retval = new AstValidator.flatten_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FLATTEN152=null;
        AstValidator.expr_return expr153 =null;


        CommonTree FLATTEN152_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:357:16: ( ^( FLATTEN expr ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:357:18: ^( FLATTEN expr )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            FLATTEN152=(CommonTree)match(input,FLATTEN,FOLLOW_FLATTEN_in_flatten_clause1545); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FLATTEN152_tree = (CommonTree)adaptor.dupNode(FLATTEN152);


            root_1 = (CommonTree)adaptor.becomeRoot(FLATTEN152_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_expr_in_flatten_clause1547);
            expr153=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, expr153.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "flatten_clause"


    public static class store_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "store_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:360:1: store_clause : ^( STORE rel filename ( func_clause )? ) ;
    public final AstValidator.store_clause_return store_clause() throws RecognitionException {
        AstValidator.store_clause_return retval = new AstValidator.store_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STORE154=null;
        AstValidator.rel_return rel155 =null;

        AstValidator.filename_return filename156 =null;

        AstValidator.func_clause_return func_clause157 =null;


        CommonTree STORE154_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:360:14: ( ^( STORE rel filename ( func_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:360:16: ^( STORE rel filename ( func_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            STORE154=(CommonTree)match(input,STORE,FOLLOW_STORE_in_store_clause1560); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STORE154_tree = (CommonTree)adaptor.dupNode(STORE154);


            root_1 = (CommonTree)adaptor.becomeRoot(STORE154_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_store_clause1562);
            rel155=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel155.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_filename_in_store_clause1564);
            filename156=filename();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, filename156.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:360:38: ( func_clause )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==FUNC||LA47_0==FUNC_REF) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:360:38: func_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_clause_in_store_clause1566);
                    func_clause157=func_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_clause157.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "store_clause"


    public static class assert_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assert_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:363:1: assert_clause : ^( ASSERT rel cond ( comment )? ) ;
    public final AstValidator.assert_clause_return assert_clause() throws RecognitionException {
        AstValidator.assert_clause_return retval = new AstValidator.assert_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ASSERT158=null;
        AstValidator.rel_return rel159 =null;

        AstValidator.cond_return cond160 =null;

        AstValidator.comment_return comment161 =null;


        CommonTree ASSERT158_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:363:15: ( ^( ASSERT rel cond ( comment )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:363:17: ^( ASSERT rel cond ( comment )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            ASSERT158=(CommonTree)match(input,ASSERT,FOLLOW_ASSERT_in_assert_clause1580); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ASSERT158_tree = (CommonTree)adaptor.dupNode(ASSERT158);


            root_1 = (CommonTree)adaptor.becomeRoot(ASSERT158_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_assert_clause1582);
            rel159=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel159.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cond_in_assert_clause1584);
            cond160=cond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cond160.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:363:36: ( comment )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==QUOTEDSTRING) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:363:36: comment
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_comment_in_assert_clause1586);
                    comment161=comment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, comment161.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assert_clause"


    public static class comment_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comment"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:366:1: comment : QUOTEDSTRING ;
    public final AstValidator.comment_return comment() throws RecognitionException {
        AstValidator.comment_return retval = new AstValidator.comment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING162=null;

        CommonTree QUOTEDSTRING162_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:366:9: ( QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:366:11: QUOTEDSTRING
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING162=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_comment1599); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING162_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING162);


            adaptor.addChild(root_0, QUOTEDSTRING162_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comment"


    public static class filter_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "filter_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:369:1: filter_clause : ^( FILTER rel cond ) ;
    public final AstValidator.filter_clause_return filter_clause() throws RecognitionException {
        AstValidator.filter_clause_return retval = new AstValidator.filter_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FILTER163=null;
        AstValidator.rel_return rel164 =null;

        AstValidator.cond_return cond165 =null;


        CommonTree FILTER163_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:369:15: ( ^( FILTER rel cond ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:369:17: ^( FILTER rel cond )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            FILTER163=(CommonTree)match(input,FILTER,FOLLOW_FILTER_in_filter_clause1610); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FILTER163_tree = (CommonTree)adaptor.dupNode(FILTER163);


            root_1 = (CommonTree)adaptor.becomeRoot(FILTER163_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_filter_clause1612);
            rel164=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel164.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cond_in_filter_clause1614);
            cond165=cond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cond165.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "filter_clause"


    public static class cond_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cond"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:372:1: cond : ( ^( OR cond cond ) | ^( AND cond cond ) | ^( NOT cond ) | ^( NULL expr ( NOT )? ) | ^( rel_op expr expr ) | in_eval | func_eval | ^( BOOL_COND expr ) );
    public final AstValidator.cond_return cond() throws RecognitionException {
        AstValidator.cond_return retval = new AstValidator.cond_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree OR166=null;
        CommonTree AND169=null;
        CommonTree NOT172=null;
        CommonTree NULL174=null;
        CommonTree NOT176=null;
        CommonTree BOOL_COND182=null;
        AstValidator.cond_return cond167 =null;

        AstValidator.cond_return cond168 =null;

        AstValidator.cond_return cond170 =null;

        AstValidator.cond_return cond171 =null;

        AstValidator.cond_return cond173 =null;

        AstValidator.expr_return expr175 =null;

        AstValidator.rel_op_return rel_op177 =null;

        AstValidator.expr_return expr178 =null;

        AstValidator.expr_return expr179 =null;

        AstValidator.in_eval_return in_eval180 =null;

        AstValidator.func_eval_return func_eval181 =null;

        AstValidator.expr_return expr183 =null;


        CommonTree OR166_tree=null;
        CommonTree AND169_tree=null;
        CommonTree NOT172_tree=null;
        CommonTree NULL174_tree=null;
        CommonTree NOT176_tree=null;
        CommonTree BOOL_COND182_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:372:6: ( ^( OR cond cond ) | ^( AND cond cond ) | ^( NOT cond ) | ^( NULL expr ( NOT )? ) | ^( rel_op expr expr ) | in_eval | func_eval | ^( BOOL_COND expr ) )
            int alt50=8;
            switch ( input.LA(1) ) {
            case OR:
                {
                alt50=1;
                }
                break;
            case AND:
                {
                alt50=2;
                }
                break;
            case NOT:
                {
                alt50=3;
                }
                break;
            case NULL:
                {
                alt50=4;
                }
                break;
            case NUM_OP_EQ:
            case NUM_OP_GT:
            case NUM_OP_GTE:
            case NUM_OP_LT:
            case NUM_OP_LTE:
            case NUM_OP_NE:
            case STR_OP_EQ:
            case STR_OP_GT:
            case STR_OP_GTE:
            case STR_OP_LT:
            case STR_OP_LTE:
            case STR_OP_MATCHES:
            case STR_OP_NE:
                {
                alt50=5;
                }
                break;
            case IN:
                {
                alt50=6;
                }
                break;
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
                {
                alt50=7;
                }
                break;
            case BOOL_COND:
                {
                alt50=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }

            switch (alt50) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:372:8: ^( OR cond cond )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    OR166=(CommonTree)match(input,OR,FOLLOW_OR_in_cond1627); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OR166_tree = (CommonTree)adaptor.dupNode(OR166);


                    root_1 = (CommonTree)adaptor.becomeRoot(OR166_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cond_in_cond1629);
                    cond167=cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cond167.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cond_in_cond1631);
                    cond168=cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cond168.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:373:8: ^( AND cond cond )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    AND169=(CommonTree)match(input,AND,FOLLOW_AND_in_cond1644); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AND169_tree = (CommonTree)adaptor.dupNode(AND169);


                    root_1 = (CommonTree)adaptor.becomeRoot(AND169_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cond_in_cond1646);
                    cond170=cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cond170.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cond_in_cond1648);
                    cond171=cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cond171.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:374:8: ^( NOT cond )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    NOT172=(CommonTree)match(input,NOT,FOLLOW_NOT_in_cond1661); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT172_tree = (CommonTree)adaptor.dupNode(NOT172);


                    root_1 = (CommonTree)adaptor.becomeRoot(NOT172_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_cond_in_cond1663);
                    cond173=cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, cond173.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:375:8: ^( NULL expr ( NOT )? )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    NULL174=(CommonTree)match(input,NULL,FOLLOW_NULL_in_cond1676); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL174_tree = (CommonTree)adaptor.dupNode(NULL174);


                    root_1 = (CommonTree)adaptor.becomeRoot(NULL174_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_cond1678);
                    expr175=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr175.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:375:21: ( NOT )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==NOT) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:375:21: NOT
                            {
                            _last = (CommonTree)input.LT(1);
                            NOT176=(CommonTree)match(input,NOT,FOLLOW_NOT_in_cond1680); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NOT176_tree = (CommonTree)adaptor.dupNode(NOT176);


                            adaptor.addChild(root_1, NOT176_tree);
                            }


                            if ( state.backtracking==0 ) {
                            }
                            }
                            break;

                    }


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:376:8: ^( rel_op expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_in_cond1694);
                    rel_op177=rel_op();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_1 = (CommonTree)adaptor.becomeRoot(rel_op177.getTree(), root_1);


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_cond1696);
                    expr178=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr178.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_cond1698);
                    expr179=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr179.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:377:8: in_eval
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_in_eval_in_cond1709);
                    in_eval180=in_eval();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, in_eval180.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:378:8: func_eval
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_eval_in_cond1718);
                    func_eval181=func_eval();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, func_eval181.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 8 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:379:8: ^( BOOL_COND expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    BOOL_COND182=(CommonTree)match(input,BOOL_COND,FOLLOW_BOOL_COND_in_cond1729); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL_COND182_tree = (CommonTree)adaptor.dupNode(BOOL_COND182);


                    root_1 = (CommonTree)adaptor.becomeRoot(BOOL_COND182_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_cond1731);
                    expr183=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr183.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cond"


    public static class in_eval_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "in_eval"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:382:1: in_eval : ^( IN ( ^( IN_LHS expr ) ^( IN_RHS expr ) )+ ) ;
    public final AstValidator.in_eval_return in_eval() throws RecognitionException {
        AstValidator.in_eval_return retval = new AstValidator.in_eval_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IN184=null;
        CommonTree IN_LHS185=null;
        CommonTree IN_RHS187=null;
        AstValidator.expr_return expr186 =null;

        AstValidator.expr_return expr188 =null;


        CommonTree IN184_tree=null;
        CommonTree IN_LHS185_tree=null;
        CommonTree IN_RHS187_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:382:8: ( ^( IN ( ^( IN_LHS expr ) ^( IN_RHS expr ) )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:382:10: ^( IN ( ^( IN_LHS expr ) ^( IN_RHS expr ) )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            IN184=(CommonTree)match(input,IN,FOLLOW_IN_in_in_eval1743); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN184_tree = (CommonTree)adaptor.dupNode(IN184);


            root_1 = (CommonTree)adaptor.becomeRoot(IN184_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:382:16: ( ^( IN_LHS expr ) ^( IN_RHS expr ) )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==IN_LHS) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:382:18: ^( IN_LHS expr ) ^( IN_RHS expr )
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    {
            	    CommonTree _save_last_2 = _last;
            	    CommonTree _first_2 = null;
            	    CommonTree root_2 = (CommonTree)adaptor.nil();
            	    _last = (CommonTree)input.LT(1);
            	    IN_LHS185=(CommonTree)match(input,IN_LHS,FOLLOW_IN_LHS_in_in_eval1749); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    IN_LHS185_tree = (CommonTree)adaptor.dupNode(IN_LHS185);


            	    root_2 = (CommonTree)adaptor.becomeRoot(IN_LHS185_tree, root_2);
            	    }


            	    match(input, Token.DOWN, null); if (state.failed) return retval;
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_expr_in_in_eval1751);
            	    expr186=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_2, expr186.getTree());


            	    match(input, Token.UP, null); if (state.failed) return retval;
            	    adaptor.addChild(root_1, root_2);
            	    _last = _save_last_2;
            	    }


            	    _last = (CommonTree)input.LT(1);
            	    {
            	    CommonTree _save_last_2 = _last;
            	    CommonTree _first_2 = null;
            	    CommonTree root_2 = (CommonTree)adaptor.nil();
            	    _last = (CommonTree)input.LT(1);
            	    IN_RHS187=(CommonTree)match(input,IN_RHS,FOLLOW_IN_RHS_in_in_eval1757); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    IN_RHS187_tree = (CommonTree)adaptor.dupNode(IN_RHS187);


            	    root_2 = (CommonTree)adaptor.becomeRoot(IN_RHS187_tree, root_2);
            	    }


            	    match(input, Token.DOWN, null); if (state.failed) return retval;
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_expr_in_in_eval1759);
            	    expr188=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_2, expr188.getTree());


            	    match(input, Token.UP, null); if (state.failed) return retval;
            	    adaptor.addChild(root_1, root_2);
            	    _last = _save_last_2;
            	    }


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt51 >= 1 ) break loop51;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "in_eval"


    public static class func_eval_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "func_eval"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:1: func_eval : ( ^( FUNC_EVAL func_name ( real_arg )* ) | ^( INVOKER_FUNC_EVAL func_name IDENTIFIER ( real_arg )* ) );
    public final AstValidator.func_eval_return func_eval() throws RecognitionException {
        AstValidator.func_eval_return retval = new AstValidator.func_eval_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FUNC_EVAL189=null;
        CommonTree INVOKER_FUNC_EVAL192=null;
        CommonTree IDENTIFIER194=null;
        AstValidator.func_name_return func_name190 =null;

        AstValidator.real_arg_return real_arg191 =null;

        AstValidator.func_name_return func_name193 =null;

        AstValidator.real_arg_return real_arg195 =null;


        CommonTree FUNC_EVAL189_tree=null;
        CommonTree INVOKER_FUNC_EVAL192_tree=null;
        CommonTree IDENTIFIER194_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:10: ( ^( FUNC_EVAL func_name ( real_arg )* ) | ^( INVOKER_FUNC_EVAL func_name IDENTIFIER ( real_arg )* ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==FUNC_EVAL) ) {
                alt54=1;
            }
            else if ( (LA54_0==INVOKER_FUNC_EVAL) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }
            switch (alt54) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:12: ^( FUNC_EVAL func_name ( real_arg )* )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FUNC_EVAL189=(CommonTree)match(input,FUNC_EVAL,FOLLOW_FUNC_EVAL_in_func_eval1776); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FUNC_EVAL189_tree = (CommonTree)adaptor.dupNode(FUNC_EVAL189);


                    root_1 = (CommonTree)adaptor.becomeRoot(FUNC_EVAL189_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_name_in_func_eval1778);
                    func_name190=func_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_name190.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:35: ( real_arg )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==BIGDECIMALNUMBER||LA52_0==BIGINTEGERNUMBER||LA52_0==CUBE||LA52_0==DIV||LA52_0==DOLLARVAR||LA52_0==DOUBLENUMBER||LA52_0==FALSE||LA52_0==FLOATNUMBER||LA52_0==GROUP||LA52_0==IDENTIFIER||LA52_0==INTEGER||LA52_0==LONGINTEGER||LA52_0==MINUS||LA52_0==NULL||LA52_0==PERCENT||LA52_0==PLUS||LA52_0==QUOTEDSTRING||LA52_0==STAR||LA52_0==TRUE||(LA52_0 >= BAG_VAL && LA52_0 <= BIN_EXPR)||(LA52_0 >= CASE_COND && LA52_0 <= CASE_EXPR)||(LA52_0 >= CAST_EXPR && LA52_0 <= EXPR_IN_PAREN)||LA52_0==FUNC_EVAL||LA52_0==INVOKER_FUNC_EVAL||(LA52_0 >= MAP_VAL && LA52_0 <= NEG)||LA52_0==TUPLE_VAL) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:35: real_arg
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    pushFollow(FOLLOW_real_arg_in_func_eval1780);
                    	    real_arg191=real_arg();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) 
                    	    adaptor.addChild(root_1, real_arg191.getTree());


                    	    if ( state.backtracking==0 ) {
                    	    }
                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:49: ^( INVOKER_FUNC_EVAL func_name IDENTIFIER ( real_arg )* )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    INVOKER_FUNC_EVAL192=(CommonTree)match(input,INVOKER_FUNC_EVAL,FOLLOW_INVOKER_FUNC_EVAL_in_func_eval1789); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INVOKER_FUNC_EVAL192_tree = (CommonTree)adaptor.dupNode(INVOKER_FUNC_EVAL192);


                    root_1 = (CommonTree)adaptor.becomeRoot(INVOKER_FUNC_EVAL192_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_name_in_func_eval1791);
                    func_name193=func_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_name193.getTree());


                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER194=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_func_eval1793); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER194_tree = (CommonTree)adaptor.dupNode(IDENTIFIER194);


                    adaptor.addChild(root_1, IDENTIFIER194_tree);
                    }


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:91: ( real_arg )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==BIGDECIMALNUMBER||LA53_0==BIGINTEGERNUMBER||LA53_0==CUBE||LA53_0==DIV||LA53_0==DOLLARVAR||LA53_0==DOUBLENUMBER||LA53_0==FALSE||LA53_0==FLOATNUMBER||LA53_0==GROUP||LA53_0==IDENTIFIER||LA53_0==INTEGER||LA53_0==LONGINTEGER||LA53_0==MINUS||LA53_0==NULL||LA53_0==PERCENT||LA53_0==PLUS||LA53_0==QUOTEDSTRING||LA53_0==STAR||LA53_0==TRUE||(LA53_0 >= BAG_VAL && LA53_0 <= BIN_EXPR)||(LA53_0 >= CASE_COND && LA53_0 <= CASE_EXPR)||(LA53_0 >= CAST_EXPR && LA53_0 <= EXPR_IN_PAREN)||LA53_0==FUNC_EVAL||LA53_0==INVOKER_FUNC_EVAL||(LA53_0 >= MAP_VAL && LA53_0 <= NEG)||LA53_0==TUPLE_VAL) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:385:91: real_arg
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    pushFollow(FOLLOW_real_arg_in_func_eval1795);
                    	    real_arg195=real_arg();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) 
                    	    adaptor.addChild(root_1, real_arg195.getTree());


                    	    if ( state.backtracking==0 ) {
                    	    }
                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "func_eval"


    public static class real_arg_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "real_arg"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:388:1: real_arg : ( expr | STAR | col_range );
    public final AstValidator.real_arg_return real_arg() throws RecognitionException {
        AstValidator.real_arg_return retval = new AstValidator.real_arg_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR197=null;
        AstValidator.expr_return expr196 =null;

        AstValidator.col_range_return col_range198 =null;


        CommonTree STAR197_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:388:10: ( expr | STAR | col_range )
            int alt55=3;
            switch ( input.LA(1) ) {
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case CUBE:
            case DIV:
            case DOLLARVAR:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case GROUP:
            case IDENTIFIER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
            case NULL:
            case PERCENT:
            case PLUS:
            case QUOTEDSTRING:
            case TRUE:
            case BAG_VAL:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case CAST_EXPR:
            case EXPR_IN_PAREN:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
            case MAP_VAL:
            case NEG:
            case TUPLE_VAL:
                {
                alt55=1;
                }
                break;
            case STAR:
                {
                int LA55_2 = input.LA(2);

                if ( (LA55_2==DOWN) ) {
                    alt55=1;
                }
                else if ( (LA55_2==EOF||LA55_2==UP||LA55_2==BIGDECIMALNUMBER||LA55_2==BIGINTEGERNUMBER||LA55_2==CUBE||LA55_2==DIV||LA55_2==DOLLARVAR||LA55_2==DOUBLENUMBER||LA55_2==FALSE||LA55_2==FLOATNUMBER||LA55_2==GROUP||LA55_2==IDENTIFIER||LA55_2==INTEGER||LA55_2==LONGINTEGER||LA55_2==MINUS||LA55_2==NULL||LA55_2==PERCENT||LA55_2==PLUS||LA55_2==QUOTEDSTRING||LA55_2==STAR||LA55_2==TRUE||(LA55_2 >= BAG_VAL && LA55_2 <= BIN_EXPR)||(LA55_2 >= CASE_COND && LA55_2 <= CASE_EXPR)||(LA55_2 >= CAST_EXPR && LA55_2 <= EXPR_IN_PAREN)||LA55_2==FUNC_EVAL||LA55_2==INVOKER_FUNC_EVAL||(LA55_2 >= MAP_VAL && LA55_2 <= NEG)||LA55_2==TUPLE_VAL) ) {
                    alt55=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 2, input);

                    throw nvae;

                }
                }
                break;
            case COL_RANGE:
                {
                alt55=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }

            switch (alt55) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:388:12: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_real_arg1807);
                    expr196=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, expr196.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:388:19: STAR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STAR197=(CommonTree)match(input,STAR,FOLLOW_STAR_in_real_arg1811); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR197_tree = (CommonTree)adaptor.dupNode(STAR197);


                    adaptor.addChild(root_0, STAR197_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:388:26: col_range
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_real_arg1815);
                    col_range198=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range198.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "real_arg"


    public static class expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:391:1: expr : ( ^( PLUS expr expr ) | ^( MINUS expr expr ) | ^( STAR expr expr ) | ^( DIV expr expr ) | ^( PERCENT expr expr ) | ^( CAST_EXPR type expr ) | const_expr | var_expr | ^( NEG expr ) | ^( CAST_EXPR type_cast expr ) | ^( EXPR_IN_PAREN expr ) );
    public final AstValidator.expr_return expr() throws RecognitionException {
        AstValidator.expr_return retval = new AstValidator.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PLUS199=null;
        CommonTree MINUS202=null;
        CommonTree STAR205=null;
        CommonTree DIV208=null;
        CommonTree PERCENT211=null;
        CommonTree CAST_EXPR214=null;
        CommonTree NEG219=null;
        CommonTree CAST_EXPR221=null;
        CommonTree EXPR_IN_PAREN224=null;
        AstValidator.expr_return expr200 =null;

        AstValidator.expr_return expr201 =null;

        AstValidator.expr_return expr203 =null;

        AstValidator.expr_return expr204 =null;

        AstValidator.expr_return expr206 =null;

        AstValidator.expr_return expr207 =null;

        AstValidator.expr_return expr209 =null;

        AstValidator.expr_return expr210 =null;

        AstValidator.expr_return expr212 =null;

        AstValidator.expr_return expr213 =null;

        AstValidator.type_return type215 =null;

        AstValidator.expr_return expr216 =null;

        AstValidator.const_expr_return const_expr217 =null;

        AstValidator.var_expr_return var_expr218 =null;

        AstValidator.expr_return expr220 =null;

        AstValidator.type_cast_return type_cast222 =null;

        AstValidator.expr_return expr223 =null;

        AstValidator.expr_return expr225 =null;


        CommonTree PLUS199_tree=null;
        CommonTree MINUS202_tree=null;
        CommonTree STAR205_tree=null;
        CommonTree DIV208_tree=null;
        CommonTree PERCENT211_tree=null;
        CommonTree CAST_EXPR214_tree=null;
        CommonTree NEG219_tree=null;
        CommonTree CAST_EXPR221_tree=null;
        CommonTree EXPR_IN_PAREN224_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:391:6: ( ^( PLUS expr expr ) | ^( MINUS expr expr ) | ^( STAR expr expr ) | ^( DIV expr expr ) | ^( PERCENT expr expr ) | ^( CAST_EXPR type expr ) | const_expr | var_expr | ^( NEG expr ) | ^( CAST_EXPR type_cast expr ) | ^( EXPR_IN_PAREN expr ) )
            int alt56=11;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt56=1;
                }
                break;
            case MINUS:
                {
                int LA56_2 = input.LA(2);

                if ( (synpred109_AstValidator()) ) {
                    alt56=2;
                }
                else if ( (synpred114_AstValidator()) ) {
                    alt56=7;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 2, input);

                    throw nvae;

                }
                }
                break;
            case STAR:
                {
                alt56=3;
                }
                break;
            case DIV:
                {
                alt56=4;
                }
                break;
            case PERCENT:
                {
                alt56=5;
                }
                break;
            case CAST_EXPR:
                {
                int LA56_6 = input.LA(2);

                if ( (synpred113_AstValidator()) ) {
                    alt56=6;
                }
                else if ( (synpred117_AstValidator()) ) {
                    alt56=10;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 6, input);

                    throw nvae;

                }
                }
                break;
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case INTEGER:
            case LONGINTEGER:
            case NULL:
            case QUOTEDSTRING:
            case TRUE:
            case BAG_VAL:
            case MAP_VAL:
            case TUPLE_VAL:
                {
                alt56=7;
                }
                break;
            case CUBE:
            case DOLLARVAR:
            case GROUP:
            case IDENTIFIER:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
                {
                alt56=8;
                }
                break;
            case NEG:
                {
                alt56=9;
                }
                break;
            case EXPR_IN_PAREN:
                {
                alt56=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }

            switch (alt56) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:391:8: ^( PLUS expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    PLUS199=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expr1826); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS199_tree = (CommonTree)adaptor.dupNode(PLUS199);


                    root_1 = (CommonTree)adaptor.becomeRoot(PLUS199_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1828);
                    expr200=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr200.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1830);
                    expr201=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr201.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:392:8: ^( MINUS expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    MINUS202=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expr1843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS202_tree = (CommonTree)adaptor.dupNode(MINUS202);


                    root_1 = (CommonTree)adaptor.becomeRoot(MINUS202_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1845);
                    expr203=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr203.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1847);
                    expr204=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr204.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:393:8: ^( STAR expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    STAR205=(CommonTree)match(input,STAR,FOLLOW_STAR_in_expr1860); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR205_tree = (CommonTree)adaptor.dupNode(STAR205);


                    root_1 = (CommonTree)adaptor.becomeRoot(STAR205_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1862);
                    expr206=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr206.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1864);
                    expr207=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr207.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:394:8: ^( DIV expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    DIV208=(CommonTree)match(input,DIV,FOLLOW_DIV_in_expr1877); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DIV208_tree = (CommonTree)adaptor.dupNode(DIV208);


                    root_1 = (CommonTree)adaptor.becomeRoot(DIV208_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1879);
                    expr209=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr209.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1881);
                    expr210=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr210.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:395:8: ^( PERCENT expr expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    PERCENT211=(CommonTree)match(input,PERCENT,FOLLOW_PERCENT_in_expr1894); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PERCENT211_tree = (CommonTree)adaptor.dupNode(PERCENT211);


                    root_1 = (CommonTree)adaptor.becomeRoot(PERCENT211_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1896);
                    expr212=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr212.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1898);
                    expr213=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr213.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:396:8: ^( CAST_EXPR type expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    CAST_EXPR214=(CommonTree)match(input,CAST_EXPR,FOLLOW_CAST_EXPR_in_expr1911); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CAST_EXPR214_tree = (CommonTree)adaptor.dupNode(CAST_EXPR214);


                    root_1 = (CommonTree)adaptor.becomeRoot(CAST_EXPR214_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_type_in_expr1913);
                    type215=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, type215.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1915);
                    expr216=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr216.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:397:8: const_expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_const_expr_in_expr1926);
                    const_expr217=const_expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, const_expr217.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 8 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:398:8: var_expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_var_expr_in_expr1935);
                    var_expr218=var_expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, var_expr218.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 9 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:399:8: ^( NEG expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    NEG219=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expr1946); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEG219_tree = (CommonTree)adaptor.dupNode(NEG219);


                    root_1 = (CommonTree)adaptor.becomeRoot(NEG219_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1948);
                    expr220=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr220.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 10 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:400:8: ^( CAST_EXPR type_cast expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    CAST_EXPR221=(CommonTree)match(input,CAST_EXPR,FOLLOW_CAST_EXPR_in_expr1961); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CAST_EXPR221_tree = (CommonTree)adaptor.dupNode(CAST_EXPR221);


                    root_1 = (CommonTree)adaptor.becomeRoot(CAST_EXPR221_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_type_cast_in_expr1963);
                    type_cast222=type_cast();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, type_cast222.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1965);
                    expr223=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr223.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 11 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:401:8: ^( EXPR_IN_PAREN expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    EXPR_IN_PAREN224=(CommonTree)match(input,EXPR_IN_PAREN,FOLLOW_EXPR_IN_PAREN_in_expr1978); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXPR_IN_PAREN224_tree = (CommonTree)adaptor.dupNode(EXPR_IN_PAREN224);


                    root_1 = (CommonTree)adaptor.becomeRoot(EXPR_IN_PAREN224_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_expr1980);
                    expr225=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr225.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class type_cast_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type_cast"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:1: type_cast : ( simple_type | map_type | tuple_type_cast | bag_type_cast );
    public final AstValidator.type_cast_return type_cast() throws RecognitionException {
        AstValidator.type_cast_return retval = new AstValidator.type_cast_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.simple_type_return simple_type226 =null;

        AstValidator.map_type_return map_type227 =null;

        AstValidator.tuple_type_cast_return tuple_type_cast228 =null;

        AstValidator.bag_type_cast_return bag_type_cast229 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:11: ( simple_type | map_type | tuple_type_cast | bag_type_cast )
            int alt57=4;
            switch ( input.LA(1) ) {
            case BIGDECIMAL:
            case BIGINTEGER:
            case BOOLEAN:
            case BYTEARRAY:
            case CHARARRAY:
            case DATETIME:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
                {
                alt57=1;
                }
                break;
            case MAP_TYPE:
                {
                alt57=2;
                }
                break;
            case TUPLE_TYPE_CAST:
                {
                alt57=3;
                }
                break;
            case BAG_TYPE_CAST:
                {
                alt57=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }

            switch (alt57) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:13: simple_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_simple_type_in_type_cast1991);
                    simple_type226=simple_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, simple_type226.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:27: map_type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_map_type_in_type_cast1995);
                    map_type227=map_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, map_type227.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:38: tuple_type_cast
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_tuple_type_cast_in_type_cast1999);
                    tuple_type_cast228=tuple_type_cast();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, tuple_type_cast228.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:404:56: bag_type_cast
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_bag_type_cast_in_type_cast2003);
                    bag_type_cast229=bag_type_cast();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, bag_type_cast229.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "type_cast"


    public static class tuple_type_cast_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tuple_type_cast"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:407:1: tuple_type_cast : ^( TUPLE_TYPE_CAST ( type_cast )* ) ;
    public final AstValidator.tuple_type_cast_return tuple_type_cast() throws RecognitionException {
        AstValidator.tuple_type_cast_return retval = new AstValidator.tuple_type_cast_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree TUPLE_TYPE_CAST230=null;
        AstValidator.type_cast_return type_cast231 =null;


        CommonTree TUPLE_TYPE_CAST230_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:407:17: ( ^( TUPLE_TYPE_CAST ( type_cast )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:407:19: ^( TUPLE_TYPE_CAST ( type_cast )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            TUPLE_TYPE_CAST230=(CommonTree)match(input,TUPLE_TYPE_CAST,FOLLOW_TUPLE_TYPE_CAST_in_tuple_type_cast2014); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TUPLE_TYPE_CAST230_tree = (CommonTree)adaptor.dupNode(TUPLE_TYPE_CAST230);


            root_1 = (CommonTree)adaptor.becomeRoot(TUPLE_TYPE_CAST230_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:407:38: ( type_cast )*
                loop58:
                do {
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==BIGDECIMAL||LA58_0==BIGINTEGER||LA58_0==BOOLEAN||LA58_0==BYTEARRAY||LA58_0==CHARARRAY||LA58_0==DATETIME||LA58_0==DOUBLE||LA58_0==FLOAT||LA58_0==INT||LA58_0==LONG||LA58_0==BAG_TYPE_CAST||LA58_0==MAP_TYPE||LA58_0==TUPLE_TYPE_CAST) ) {
                        alt58=1;
                    }


                    switch (alt58) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:407:38: type_cast
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_type_cast_in_tuple_type_cast2016);
                	    type_cast231=type_cast();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, type_cast231.getTree());


                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop58;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "tuple_type_cast"


    public static class bag_type_cast_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bag_type_cast"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:410:1: bag_type_cast : ^( BAG_TYPE_CAST ( tuple_type_cast )? ) ;
    public final AstValidator.bag_type_cast_return bag_type_cast() throws RecognitionException {
        AstValidator.bag_type_cast_return retval = new AstValidator.bag_type_cast_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BAG_TYPE_CAST232=null;
        AstValidator.tuple_type_cast_return tuple_type_cast233 =null;


        CommonTree BAG_TYPE_CAST232_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:410:15: ( ^( BAG_TYPE_CAST ( tuple_type_cast )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:410:17: ^( BAG_TYPE_CAST ( tuple_type_cast )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BAG_TYPE_CAST232=(CommonTree)match(input,BAG_TYPE_CAST,FOLLOW_BAG_TYPE_CAST_in_bag_type_cast2030); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BAG_TYPE_CAST232_tree = (CommonTree)adaptor.dupNode(BAG_TYPE_CAST232);


            root_1 = (CommonTree)adaptor.becomeRoot(BAG_TYPE_CAST232_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:410:34: ( tuple_type_cast )?
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==TUPLE_TYPE_CAST) ) {
                    alt59=1;
                }
                switch (alt59) {
                    case 1 :
                        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:410:34: tuple_type_cast
                        {
                        _last = (CommonTree)input.LT(1);
                        pushFollow(FOLLOW_tuple_type_cast_in_bag_type_cast2032);
                        tuple_type_cast233=tuple_type_cast();

                        state._fsp--;
                        if (state.failed) return retval;
                        if ( state.backtracking==0 ) 
                        adaptor.addChild(root_1, tuple_type_cast233.getTree());


                        if ( state.backtracking==0 ) {
                        }
                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bag_type_cast"


    public static class var_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "var_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:1: var_expr : projectable_expr ( dot_proj | pound_proj )* ;
    public final AstValidator.var_expr_return var_expr() throws RecognitionException {
        AstValidator.var_expr_return retval = new AstValidator.var_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.projectable_expr_return projectable_expr234 =null;

        AstValidator.dot_proj_return dot_proj235 =null;

        AstValidator.pound_proj_return pound_proj236 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:10: ( projectable_expr ( dot_proj | pound_proj )* )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:12: projectable_expr ( dot_proj | pound_proj )*
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_projectable_expr_in_var_expr2044);
            projectable_expr234=projectable_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, projectable_expr234.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:29: ( dot_proj | pound_proj )*
            loop60:
            do {
                int alt60=3;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==PERIOD) ) {
                    alt60=1;
                }
                else if ( (LA60_0==POUND) ) {
                    alt60=2;
                }


                switch (alt60) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:31: dot_proj
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_dot_proj_in_var_expr2048);
            	    dot_proj235=dot_proj();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, dot_proj235.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;
            	case 2 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:413:42: pound_proj
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_pound_proj_in_var_expr2052);
            	    pound_proj236=pound_proj();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, pound_proj236.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "var_expr"


    public static class projectable_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "projectable_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:1: projectable_expr : ( func_eval | col_ref | bin_expr | case_expr | case_cond );
    public final AstValidator.projectable_expr_return projectable_expr() throws RecognitionException {
        AstValidator.projectable_expr_return retval = new AstValidator.projectable_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.func_eval_return func_eval237 =null;

        AstValidator.col_ref_return col_ref238 =null;

        AstValidator.bin_expr_return bin_expr239 =null;

        AstValidator.case_expr_return case_expr240 =null;

        AstValidator.case_cond_return case_cond241 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:17: ( func_eval | col_ref | bin_expr | case_expr | case_cond )
            int alt61=5;
            switch ( input.LA(1) ) {
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
                {
                alt61=1;
                }
                break;
            case CUBE:
            case DOLLARVAR:
            case GROUP:
            case IDENTIFIER:
                {
                alt61=2;
                }
                break;
            case BIN_EXPR:
                {
                alt61=3;
                }
                break;
            case CASE_EXPR:
                {
                alt61=4;
                }
                break;
            case CASE_COND:
                {
                alt61=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }

            switch (alt61) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:19: func_eval
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_eval_in_projectable_expr2063);
                    func_eval237=func_eval();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, func_eval237.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:31: col_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_projectable_expr2067);
                    col_ref238=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_ref238.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:41: bin_expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_bin_expr_in_projectable_expr2071);
                    bin_expr239=bin_expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, bin_expr239.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:52: case_expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_case_expr_in_projectable_expr2075);
                    case_expr240=case_expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, case_expr240.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:416:64: case_cond
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_case_cond_in_projectable_expr2079);
                    case_cond241=case_cond();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, case_cond241.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "projectable_expr"


    public static class dot_proj_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dot_proj"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:419:1: dot_proj : ^( PERIOD ( col_alias_or_index )+ ) ;
    public final AstValidator.dot_proj_return dot_proj() throws RecognitionException {
        AstValidator.dot_proj_return retval = new AstValidator.dot_proj_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PERIOD242=null;
        AstValidator.col_alias_or_index_return col_alias_or_index243 =null;


        CommonTree PERIOD242_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:419:10: ( ^( PERIOD ( col_alias_or_index )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:419:12: ^( PERIOD ( col_alias_or_index )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PERIOD242=(CommonTree)match(input,PERIOD,FOLLOW_PERIOD_in_dot_proj2090); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PERIOD242_tree = (CommonTree)adaptor.dupNode(PERIOD242);


            root_1 = (CommonTree)adaptor.becomeRoot(PERIOD242_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:419:22: ( col_alias_or_index )+
            int cnt62=0;
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==CUBE||LA62_0==DOLLARVAR||LA62_0==GROUP||LA62_0==IDENTIFIER) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:419:22: col_alias_or_index
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_col_alias_or_index_in_dot_proj2092);
            	    col_alias_or_index243=col_alias_or_index();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, col_alias_or_index243.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt62 >= 1 ) break loop62;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(62, input);
                        throw eee;
                }
                cnt62++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "dot_proj"


    public static class col_alias_or_index_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "col_alias_or_index"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:422:1: col_alias_or_index : ( col_alias | col_index );
    public final AstValidator.col_alias_or_index_return col_alias_or_index() throws RecognitionException {
        AstValidator.col_alias_or_index_return retval = new AstValidator.col_alias_or_index_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.col_alias_return col_alias244 =null;

        AstValidator.col_index_return col_index245 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:422:20: ( col_alias | col_index )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==CUBE||LA63_0==GROUP||LA63_0==IDENTIFIER) ) {
                alt63=1;
            }
            else if ( (LA63_0==DOLLARVAR) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:422:22: col_alias
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_alias_in_col_alias_or_index2104);
                    col_alias244=col_alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_alias244.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:422:34: col_index
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_index_in_col_alias_or_index2108);
                    col_index245=col_index();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_index245.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "col_alias_or_index"


    public static class col_alias_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "col_alias"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:425:1: col_alias : ( GROUP | CUBE | IDENTIFIER );
    public final AstValidator.col_alias_return col_alias() throws RecognitionException {
        AstValidator.col_alias_return retval = new AstValidator.col_alias_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set246=null;

        CommonTree set246_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:425:11: ( GROUP | CUBE | IDENTIFIER )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set246=(CommonTree)input.LT(1);

            if ( input.LA(1)==CUBE||input.LA(1)==GROUP||input.LA(1)==IDENTIFIER ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set246_tree = (CommonTree)adaptor.dupNode(set246);


                adaptor.addChild(root_0, set246_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "col_alias"


    public static class col_index_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "col_index"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:428:1: col_index : DOLLARVAR ;
    public final AstValidator.col_index_return col_index() throws RecognitionException {
        AstValidator.col_index_return retval = new AstValidator.col_index_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DOLLARVAR247=null;

        CommonTree DOLLARVAR247_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:428:11: ( DOLLARVAR )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:428:13: DOLLARVAR
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            DOLLARVAR247=(CommonTree)match(input,DOLLARVAR,FOLLOW_DOLLARVAR_in_col_index2134); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOLLARVAR247_tree = (CommonTree)adaptor.dupNode(DOLLARVAR247);


            adaptor.addChild(root_0, DOLLARVAR247_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "col_index"


    public static class col_range_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "col_range"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:1: col_range : ^( COL_RANGE ( col_ref )? DOUBLE_PERIOD ( col_ref )? ) ;
    public final AstValidator.col_range_return col_range() throws RecognitionException {
        AstValidator.col_range_return retval = new AstValidator.col_range_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree COL_RANGE248=null;
        CommonTree DOUBLE_PERIOD250=null;
        AstValidator.col_ref_return col_ref249 =null;

        AstValidator.col_ref_return col_ref251 =null;


        CommonTree COL_RANGE248_tree=null;
        CommonTree DOUBLE_PERIOD250_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:11: ( ^( COL_RANGE ( col_ref )? DOUBLE_PERIOD ( col_ref )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:14: ^( COL_RANGE ( col_ref )? DOUBLE_PERIOD ( col_ref )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            COL_RANGE248=(CommonTree)match(input,COL_RANGE,FOLLOW_COL_RANGE_in_col_range2145); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COL_RANGE248_tree = (CommonTree)adaptor.dupNode(COL_RANGE248);


            root_1 = (CommonTree)adaptor.becomeRoot(COL_RANGE248_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:26: ( col_ref )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==CUBE||LA64_0==DOLLARVAR||LA64_0==GROUP||LA64_0==IDENTIFIER) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:26: col_ref
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_col_range2147);
                    col_ref249=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, col_ref249.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            DOUBLE_PERIOD250=(CommonTree)match(input,DOUBLE_PERIOD,FOLLOW_DOUBLE_PERIOD_in_col_range2150); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOUBLE_PERIOD250_tree = (CommonTree)adaptor.dupNode(DOUBLE_PERIOD250);


            adaptor.addChild(root_1, DOUBLE_PERIOD250_tree);
            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:49: ( col_ref )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==CUBE||LA65_0==DOLLARVAR||LA65_0==GROUP||LA65_0==IDENTIFIER) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:431:49: col_ref
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_col_range2152);
                    col_ref251=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, col_ref251.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "col_range"


    public static class pound_proj_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pound_proj"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:435:1: pound_proj : ^( POUND ( QUOTEDSTRING | NULL ) ) ;
    public final AstValidator.pound_proj_return pound_proj() throws RecognitionException {
        AstValidator.pound_proj_return retval = new AstValidator.pound_proj_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree POUND252=null;
        CommonTree set253=null;

        CommonTree POUND252_tree=null;
        CommonTree set253_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:435:12: ( ^( POUND ( QUOTEDSTRING | NULL ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:435:14: ^( POUND ( QUOTEDSTRING | NULL ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            POUND252=(CommonTree)match(input,POUND,FOLLOW_POUND_in_pound_proj2166); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POUND252_tree = (CommonTree)adaptor.dupNode(POUND252);


            root_1 = (CommonTree)adaptor.becomeRoot(POUND252_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            set253=(CommonTree)input.LT(1);

            if ( input.LA(1)==NULL||input.LA(1)==QUOTEDSTRING ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set253_tree = (CommonTree)adaptor.dupNode(set253);


                adaptor.addChild(root_1, set253_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pound_proj"


    public static class bin_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bin_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:438:1: bin_expr : ^( BIN_EXPR cond expr expr ) ;
    public final AstValidator.bin_expr_return bin_expr() throws RecognitionException {
        AstValidator.bin_expr_return retval = new AstValidator.bin_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BIN_EXPR254=null;
        AstValidator.cond_return cond255 =null;

        AstValidator.expr_return expr256 =null;

        AstValidator.expr_return expr257 =null;


        CommonTree BIN_EXPR254_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:438:10: ( ^( BIN_EXPR cond expr expr ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:438:12: ^( BIN_EXPR cond expr expr )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BIN_EXPR254=(CommonTree)match(input,BIN_EXPR,FOLLOW_BIN_EXPR_in_bin_expr2189); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BIN_EXPR254_tree = (CommonTree)adaptor.dupNode(BIN_EXPR254);


            root_1 = (CommonTree)adaptor.becomeRoot(BIN_EXPR254_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cond_in_bin_expr2191);
            cond255=cond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cond255.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_expr_in_bin_expr2193);
            expr256=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, expr256.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_expr_in_bin_expr2195);
            expr257=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, expr257.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bin_expr"


    public static class case_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "case_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:1: case_expr : ^( CASE_EXPR ( ^( CASE_EXPR_LHS expr ) ( ^( CASE_EXPR_RHS expr ) )+ )+ ) ;
    public final AstValidator.case_expr_return case_expr() throws RecognitionException {
        AstValidator.case_expr_return retval = new AstValidator.case_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CASE_EXPR258=null;
        CommonTree CASE_EXPR_LHS259=null;
        CommonTree CASE_EXPR_RHS261=null;
        AstValidator.expr_return expr260 =null;

        AstValidator.expr_return expr262 =null;


        CommonTree CASE_EXPR258_tree=null;
        CommonTree CASE_EXPR_LHS259_tree=null;
        CommonTree CASE_EXPR_RHS261_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:10: ( ^( CASE_EXPR ( ^( CASE_EXPR_LHS expr ) ( ^( CASE_EXPR_RHS expr ) )+ )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:12: ^( CASE_EXPR ( ^( CASE_EXPR_LHS expr ) ( ^( CASE_EXPR_RHS expr ) )+ )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CASE_EXPR258=(CommonTree)match(input,CASE_EXPR,FOLLOW_CASE_EXPR_in_case_expr2207); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CASE_EXPR258_tree = (CommonTree)adaptor.dupNode(CASE_EXPR258);


            root_1 = (CommonTree)adaptor.becomeRoot(CASE_EXPR258_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:25: ( ^( CASE_EXPR_LHS expr ) ( ^( CASE_EXPR_RHS expr ) )+ )+
            int cnt67=0;
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==CASE_EXPR_LHS) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:27: ^( CASE_EXPR_LHS expr ) ( ^( CASE_EXPR_RHS expr ) )+
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    {
            	    CommonTree _save_last_2 = _last;
            	    CommonTree _first_2 = null;
            	    CommonTree root_2 = (CommonTree)adaptor.nil();
            	    _last = (CommonTree)input.LT(1);
            	    CASE_EXPR_LHS259=(CommonTree)match(input,CASE_EXPR_LHS,FOLLOW_CASE_EXPR_LHS_in_case_expr2213); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    CASE_EXPR_LHS259_tree = (CommonTree)adaptor.dupNode(CASE_EXPR_LHS259);


            	    root_2 = (CommonTree)adaptor.becomeRoot(CASE_EXPR_LHS259_tree, root_2);
            	    }


            	    match(input, Token.DOWN, null); if (state.failed) return retval;
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_expr_in_case_expr2215);
            	    expr260=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_2, expr260.getTree());


            	    match(input, Token.UP, null); if (state.failed) return retval;
            	    adaptor.addChild(root_1, root_2);
            	    _last = _save_last_2;
            	    }


            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:51: ( ^( CASE_EXPR_RHS expr ) )+
            	    int cnt66=0;
            	    loop66:
            	    do {
            	        int alt66=2;
            	        int LA66_0 = input.LA(1);

            	        if ( (LA66_0==CASE_EXPR_RHS) ) {
            	            alt66=1;
            	        }


            	        switch (alt66) {
            	    	case 1 :
            	    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:441:53: ^( CASE_EXPR_RHS expr )
            	    	    {
            	    	    _last = (CommonTree)input.LT(1);
            	    	    {
            	    	    CommonTree _save_last_2 = _last;
            	    	    CommonTree _first_2 = null;
            	    	    CommonTree root_2 = (CommonTree)adaptor.nil();
            	    	    _last = (CommonTree)input.LT(1);
            	    	    CASE_EXPR_RHS261=(CommonTree)match(input,CASE_EXPR_RHS,FOLLOW_CASE_EXPR_RHS_in_case_expr2223); if (state.failed) return retval;
            	    	    if ( state.backtracking==0 ) {
            	    	    CASE_EXPR_RHS261_tree = (CommonTree)adaptor.dupNode(CASE_EXPR_RHS261);


            	    	    root_2 = (CommonTree)adaptor.becomeRoot(CASE_EXPR_RHS261_tree, root_2);
            	    	    }


            	    	    match(input, Token.DOWN, null); if (state.failed) return retval;
            	    	    _last = (CommonTree)input.LT(1);
            	    	    pushFollow(FOLLOW_expr_in_case_expr2225);
            	    	    expr262=expr();

            	    	    state._fsp--;
            	    	    if (state.failed) return retval;
            	    	    if ( state.backtracking==0 ) 
            	    	    adaptor.addChild(root_2, expr262.getTree());


            	    	    match(input, Token.UP, null); if (state.failed) return retval;
            	    	    adaptor.addChild(root_1, root_2);
            	    	    _last = _save_last_2;
            	    	    }


            	    	    if ( state.backtracking==0 ) {
            	    	    }
            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt66 >= 1 ) break loop66;
            	    	    if (state.backtracking>0) {state.failed=true; return retval;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(66, input);
            	                throw eee;
            	        }
            	        cnt66++;
            	    } while (true);


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt67 >= 1 ) break loop67;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(67, input);
                        throw eee;
                }
                cnt67++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "case_expr"


    public static class case_cond_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "case_cond"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:1: case_cond : ^( CASE_COND ^( WHEN ( cond )+ ) ^( THEN ( expr )+ ) ) ;
    public final AstValidator.case_cond_return case_cond() throws RecognitionException {
        AstValidator.case_cond_return retval = new AstValidator.case_cond_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CASE_COND263=null;
        CommonTree WHEN264=null;
        CommonTree THEN266=null;
        AstValidator.cond_return cond265 =null;

        AstValidator.expr_return expr267 =null;


        CommonTree CASE_COND263_tree=null;
        CommonTree WHEN264_tree=null;
        CommonTree THEN266_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:10: ( ^( CASE_COND ^( WHEN ( cond )+ ) ^( THEN ( expr )+ ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:12: ^( CASE_COND ^( WHEN ( cond )+ ) ^( THEN ( expr )+ ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CASE_COND263=(CommonTree)match(input,CASE_COND,FOLLOW_CASE_COND_in_case_cond2244); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CASE_COND263_tree = (CommonTree)adaptor.dupNode(CASE_COND263);


            root_1 = (CommonTree)adaptor.becomeRoot(CASE_COND263_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            WHEN264=(CommonTree)match(input,WHEN,FOLLOW_WHEN_in_case_cond2248); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHEN264_tree = (CommonTree)adaptor.dupNode(WHEN264);


            root_2 = (CommonTree)adaptor.becomeRoot(WHEN264_tree, root_2);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:33: ( cond )+
            int cnt68=0;
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==AND||LA68_0==IN||(LA68_0 >= NOT && LA68_0 <= NUM_OP_NE)||LA68_0==OR||(LA68_0 >= STR_OP_EQ && LA68_0 <= STR_OP_NE)||LA68_0==BOOL_COND||LA68_0==FUNC_EVAL||LA68_0==INVOKER_FUNC_EVAL) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:33: cond
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_cond_in_case_cond2250);
            	    cond265=cond();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_2, cond265.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt68 >= 1 ) break loop68;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(68, input);
                        throw eee;
                }
                cnt68++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_1, root_2);
            _last = _save_last_2;
            }


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            THEN266=(CommonTree)match(input,THEN,FOLLOW_THEN_in_case_cond2257); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            THEN266_tree = (CommonTree)adaptor.dupNode(THEN266);


            root_2 = (CommonTree)adaptor.becomeRoot(THEN266_tree, root_2);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:49: ( expr )+
            int cnt69=0;
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==BIGDECIMALNUMBER||LA69_0==BIGINTEGERNUMBER||LA69_0==CUBE||LA69_0==DIV||LA69_0==DOLLARVAR||LA69_0==DOUBLENUMBER||LA69_0==FALSE||LA69_0==FLOATNUMBER||LA69_0==GROUP||LA69_0==IDENTIFIER||LA69_0==INTEGER||LA69_0==LONGINTEGER||LA69_0==MINUS||LA69_0==NULL||LA69_0==PERCENT||LA69_0==PLUS||LA69_0==QUOTEDSTRING||LA69_0==STAR||LA69_0==TRUE||(LA69_0 >= BAG_VAL && LA69_0 <= BIN_EXPR)||(LA69_0 >= CASE_COND && LA69_0 <= CASE_EXPR)||LA69_0==CAST_EXPR||LA69_0==EXPR_IN_PAREN||LA69_0==FUNC_EVAL||LA69_0==INVOKER_FUNC_EVAL||(LA69_0 >= MAP_VAL && LA69_0 <= NEG)||LA69_0==TUPLE_VAL) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:444:49: expr
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_expr_in_case_cond2259);
            	    expr267=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_2, expr267.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt69 >= 1 ) break loop69;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(69, input);
                        throw eee;
                }
                cnt69++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_1, root_2);
            _last = _save_last_2;
            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "case_cond"


    public static class limit_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "limit_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:1: limit_clause : ^( LIMIT rel ( INTEGER | LONGINTEGER | expr ) ) ;
    public final AstValidator.limit_clause_return limit_clause() throws RecognitionException {
        AstValidator.limit_clause_return retval = new AstValidator.limit_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree LIMIT268=null;
        CommonTree INTEGER270=null;
        CommonTree LONGINTEGER271=null;
        AstValidator.rel_return rel269 =null;

        AstValidator.expr_return expr272 =null;


        CommonTree LIMIT268_tree=null;
        CommonTree INTEGER270_tree=null;
        CommonTree LONGINTEGER271_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:14: ( ^( LIMIT rel ( INTEGER | LONGINTEGER | expr ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:16: ^( LIMIT rel ( INTEGER | LONGINTEGER | expr ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            LIMIT268=(CommonTree)match(input,LIMIT,FOLLOW_LIMIT_in_limit_clause2275); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LIMIT268_tree = (CommonTree)adaptor.dupNode(LIMIT268);


            root_1 = (CommonTree)adaptor.becomeRoot(LIMIT268_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_limit_clause2277);
            rel269=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel269.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:29: ( INTEGER | LONGINTEGER | expr )
            int alt70=3;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                int LA70_1 = input.LA(2);

                if ( (synpred140_AstValidator()) ) {
                    alt70=1;
                }
                else if ( (true) ) {
                    alt70=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;

                }
                }
                break;
            case LONGINTEGER:
                {
                int LA70_2 = input.LA(2);

                if ( (synpred141_AstValidator()) ) {
                    alt70=2;
                }
                else if ( (true) ) {
                    alt70=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 2, input);

                    throw nvae;

                }
                }
                break;
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case CUBE:
            case DIV:
            case DOLLARVAR:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case GROUP:
            case IDENTIFIER:
            case MINUS:
            case NULL:
            case PERCENT:
            case PLUS:
            case QUOTEDSTRING:
            case STAR:
            case TRUE:
            case BAG_VAL:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case CAST_EXPR:
            case EXPR_IN_PAREN:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
            case MAP_VAL:
            case NEG:
            case TUPLE_VAL:
                {
                alt70=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }

            switch (alt70) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:31: INTEGER
                    {
                    _last = (CommonTree)input.LT(1);
                    INTEGER270=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limit_clause2281); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER270_tree = (CommonTree)adaptor.dupNode(INTEGER270);


                    adaptor.addChild(root_1, INTEGER270_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:41: LONGINTEGER
                    {
                    _last = (CommonTree)input.LT(1);
                    LONGINTEGER271=(CommonTree)match(input,LONGINTEGER,FOLLOW_LONGINTEGER_in_limit_clause2285); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONGINTEGER271_tree = (CommonTree)adaptor.dupNode(LONGINTEGER271);


                    adaptor.addChild(root_1, LONGINTEGER271_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:55: expr
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_limit_clause2289);
                    expr272=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr272.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "limit_clause"


    public static class sample_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sample_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:1: sample_clause : ^( SAMPLE rel ( DOUBLENUMBER | expr ) ) ;
    public final AstValidator.sample_clause_return sample_clause() throws RecognitionException {
        AstValidator.sample_clause_return retval = new AstValidator.sample_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SAMPLE273=null;
        CommonTree DOUBLENUMBER275=null;
        AstValidator.rel_return rel274 =null;

        AstValidator.expr_return expr276 =null;


        CommonTree SAMPLE273_tree=null;
        CommonTree DOUBLENUMBER275_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:15: ( ^( SAMPLE rel ( DOUBLENUMBER | expr ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:17: ^( SAMPLE rel ( DOUBLENUMBER | expr ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SAMPLE273=(CommonTree)match(input,SAMPLE,FOLLOW_SAMPLE_in_sample_clause2304); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SAMPLE273_tree = (CommonTree)adaptor.dupNode(SAMPLE273);


            root_1 = (CommonTree)adaptor.becomeRoot(SAMPLE273_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_sample_clause2306);
            rel274=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel274.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:31: ( DOUBLENUMBER | expr )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==DOUBLENUMBER) ) {
                int LA71_1 = input.LA(2);

                if ( (synpred142_AstValidator()) ) {
                    alt71=1;
                }
                else if ( (true) ) {
                    alt71=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 71, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA71_0==BIGDECIMALNUMBER||LA71_0==BIGINTEGERNUMBER||LA71_0==CUBE||LA71_0==DIV||LA71_0==DOLLARVAR||LA71_0==FALSE||LA71_0==FLOATNUMBER||LA71_0==GROUP||LA71_0==IDENTIFIER||LA71_0==INTEGER||LA71_0==LONGINTEGER||LA71_0==MINUS||LA71_0==NULL||LA71_0==PERCENT||LA71_0==PLUS||LA71_0==QUOTEDSTRING||LA71_0==STAR||LA71_0==TRUE||(LA71_0 >= BAG_VAL && LA71_0 <= BIN_EXPR)||(LA71_0 >= CASE_COND && LA71_0 <= CASE_EXPR)||LA71_0==CAST_EXPR||LA71_0==EXPR_IN_PAREN||LA71_0==FUNC_EVAL||LA71_0==INVOKER_FUNC_EVAL||(LA71_0 >= MAP_VAL && LA71_0 <= NEG)||LA71_0==TUPLE_VAL) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;

            }
            switch (alt71) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:33: DOUBLENUMBER
                    {
                    _last = (CommonTree)input.LT(1);
                    DOUBLENUMBER275=(CommonTree)match(input,DOUBLENUMBER,FOLLOW_DOUBLENUMBER_in_sample_clause2310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLENUMBER275_tree = (CommonTree)adaptor.dupNode(DOUBLENUMBER275);


                    adaptor.addChild(root_1, DOUBLENUMBER275_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:48: expr
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_sample_clause2314);
                    expr276=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr276.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sample_clause"


    public static class rank_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rank_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:453:1: rank_clause : ^( RANK rel ( rank_by_statement )? ) ;
    public final AstValidator.rank_clause_return rank_clause() throws RecognitionException {
        AstValidator.rank_clause_return retval = new AstValidator.rank_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree RANK277=null;
        AstValidator.rel_return rel278 =null;

        AstValidator.rank_by_statement_return rank_by_statement279 =null;


        CommonTree RANK277_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:453:13: ( ^( RANK rel ( rank_by_statement )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:453:15: ^( RANK rel ( rank_by_statement )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            RANK277=(CommonTree)match(input,RANK,FOLLOW_RANK_in_rank_clause2329); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RANK277_tree = (CommonTree)adaptor.dupNode(RANK277);


            root_1 = (CommonTree)adaptor.becomeRoot(RANK277_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_rank_clause2331);
            rel278=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel278.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:453:27: ( rank_by_statement )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==BY) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:453:29: rank_by_statement
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rank_by_statement_in_rank_clause2335);
                    rank_by_statement279=rank_by_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, rank_by_statement279.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rank_clause"


    public static class rank_by_statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rank_by_statement"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:456:1: rank_by_statement : ^( BY rank_by_clause ( DENSE )? ) ;
    public final AstValidator.rank_by_statement_return rank_by_statement() throws RecognitionException {
        AstValidator.rank_by_statement_return retval = new AstValidator.rank_by_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BY280=null;
        CommonTree DENSE282=null;
        AstValidator.rank_by_clause_return rank_by_clause281 =null;


        CommonTree BY280_tree=null;
        CommonTree DENSE282_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:456:19: ( ^( BY rank_by_clause ( DENSE )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:456:21: ^( BY rank_by_clause ( DENSE )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BY280=(CommonTree)match(input,BY,FOLLOW_BY_in_rank_by_statement2351); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BY280_tree = (CommonTree)adaptor.dupNode(BY280);


            root_1 = (CommonTree)adaptor.becomeRoot(BY280_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rank_by_clause_in_rank_by_statement2353);
            rank_by_clause281=rank_by_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rank_by_clause281.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:456:42: ( DENSE )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==DENSE) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:456:44: DENSE
                    {
                    _last = (CommonTree)input.LT(1);
                    DENSE282=(CommonTree)match(input,DENSE,FOLLOW_DENSE_in_rank_by_statement2357); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DENSE282_tree = (CommonTree)adaptor.dupNode(DENSE282);


                    adaptor.addChild(root_1, DENSE282_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rank_by_statement"


    public static class rank_by_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rank_by_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:459:1: rank_by_clause : ( STAR ( ASC | DESC )? | ( rank_col )+ );
    public final AstValidator.rank_by_clause_return rank_by_clause() throws RecognitionException {
        AstValidator.rank_by_clause_return retval = new AstValidator.rank_by_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR283=null;
        CommonTree set284=null;
        AstValidator.rank_col_return rank_col285 =null;


        CommonTree STAR283_tree=null;
        CommonTree set284_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:459:16: ( STAR ( ASC | DESC )? | ( rank_col )+ )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==STAR) ) {
                alt76=1;
            }
            else if ( (LA76_0==CUBE||LA76_0==DOLLARVAR||LA76_0==GROUP||LA76_0==IDENTIFIER||LA76_0==COL_RANGE) ) {
                alt76=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;

            }
            switch (alt76) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:459:18: STAR ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STAR283=(CommonTree)match(input,STAR,FOLLOW_STAR_in_rank_by_clause2371); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR283_tree = (CommonTree)adaptor.dupNode(STAR283);


                    adaptor.addChild(root_0, STAR283_tree);
                    }


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:459:23: ( ASC | DESC )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==ASC||LA74_0==DESC) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set284=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set284_tree = (CommonTree)adaptor.dupNode(set284);


                                adaptor.addChild(root_0, set284_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:460:18: ( rank_col )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:460:18: ( rank_col )+
                    int cnt75=0;
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==CUBE||LA75_0==DOLLARVAR||LA75_0==GROUP||LA75_0==IDENTIFIER||LA75_0==COL_RANGE) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:460:18: rank_col
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    pushFollow(FOLLOW_rank_col_in_rank_by_clause2401);
                    	    rank_col285=rank_col();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) 
                    	    adaptor.addChild(root_0, rank_col285.getTree());


                    	    if ( state.backtracking==0 ) {
                    	    }
                    	    }
                    	    break;

                    	default :
                    	    if ( cnt75 >= 1 ) break loop75;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(75, input);
                                throw eee;
                        }
                        cnt75++;
                    } while (true);


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rank_by_clause"


    public static class rank_col_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rank_col"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:463:1: rank_col : ( col_range ( ASC | DESC )? | col_ref ( ASC | DESC )? );
    public final AstValidator.rank_col_return rank_col() throws RecognitionException {
        AstValidator.rank_col_return retval = new AstValidator.rank_col_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set287=null;
        CommonTree set289=null;
        AstValidator.col_range_return col_range286 =null;

        AstValidator.col_ref_return col_ref288 =null;


        CommonTree set287_tree=null;
        CommonTree set289_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:463:10: ( col_range ( ASC | DESC )? | col_ref ( ASC | DESC )? )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==COL_RANGE) ) {
                alt79=1;
            }
            else if ( (LA79_0==CUBE||LA79_0==DOLLARVAR||LA79_0==GROUP||LA79_0==IDENTIFIER) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;

            }
            switch (alt79) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:463:12: col_range ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_rank_col2411);
                    col_range286=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range286.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:463:22: ( ASC | DESC )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==ASC||LA77_0==DESC) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set287=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set287_tree = (CommonTree)adaptor.dupNode(set287);


                                adaptor.addChild(root_0, set287_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:464:12: col_ref ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_rank_col2433);
                    col_ref288=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_ref288.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:464:20: ( ASC | DESC )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==ASC||LA78_0==DESC) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set289=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set289_tree = (CommonTree)adaptor.dupNode(set289);


                                adaptor.addChild(root_0, set289_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rank_col"


    public static class order_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "order_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:467:1: order_clause : ^( ORDER rel order_by_clause ( func_clause )? ) ;
    public final AstValidator.order_clause_return order_clause() throws RecognitionException {
        AstValidator.order_clause_return retval = new AstValidator.order_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ORDER290=null;
        AstValidator.rel_return rel291 =null;

        AstValidator.order_by_clause_return order_by_clause292 =null;

        AstValidator.func_clause_return func_clause293 =null;


        CommonTree ORDER290_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:467:14: ( ^( ORDER rel order_by_clause ( func_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:467:16: ^( ORDER rel order_by_clause ( func_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            ORDER290=(CommonTree)match(input,ORDER,FOLLOW_ORDER_in_order_clause2455); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORDER290_tree = (CommonTree)adaptor.dupNode(ORDER290);


            root_1 = (CommonTree)adaptor.becomeRoot(ORDER290_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_order_clause2457);
            rel291=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel291.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_order_by_clause_in_order_clause2459);
            order_by_clause292=order_by_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, order_by_clause292.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:467:45: ( func_clause )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==FUNC||LA80_0==FUNC_REF) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:467:45: func_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_clause_in_order_clause2461);
                    func_clause293=func_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_clause293.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "order_clause"


    public static class order_by_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "order_by_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:470:1: order_by_clause : ( STAR ( ASC | DESC )? | ( order_col )+ );
    public final AstValidator.order_by_clause_return order_by_clause() throws RecognitionException {
        AstValidator.order_by_clause_return retval = new AstValidator.order_by_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR294=null;
        CommonTree set295=null;
        AstValidator.order_col_return order_col296 =null;


        CommonTree STAR294_tree=null;
        CommonTree set295_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:470:17: ( STAR ( ASC | DESC )? | ( order_col )+ )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==STAR) ) {
                alt83=1;
            }
            else if ( (LA83_0==CUBE||LA83_0==DOLLARVAR||LA83_0==GROUP||LA83_0==IDENTIFIER||LA83_0==COL_RANGE) ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;

            }
            switch (alt83) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:470:19: STAR ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STAR294=(CommonTree)match(input,STAR,FOLLOW_STAR_in_order_by_clause2473); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR294_tree = (CommonTree)adaptor.dupNode(STAR294);


                    adaptor.addChild(root_0, STAR294_tree);
                    }


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:470:24: ( ASC | DESC )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==ASC||LA81_0==DESC) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set295=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set295_tree = (CommonTree)adaptor.dupNode(set295);


                                adaptor.addChild(root_0, set295_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:471:19: ( order_col )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:471:19: ( order_col )+
                    int cnt82=0;
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==CUBE||LA82_0==DOLLARVAR||LA82_0==GROUP||LA82_0==IDENTIFIER||LA82_0==COL_RANGE) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:471:19: order_col
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    pushFollow(FOLLOW_order_col_in_order_by_clause2504);
                    	    order_col296=order_col();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) 
                    	    adaptor.addChild(root_0, order_col296.getTree());


                    	    if ( state.backtracking==0 ) {
                    	    }
                    	    }
                    	    break;

                    	default :
                    	    if ( cnt82 >= 1 ) break loop82;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(82, input);
                                throw eee;
                        }
                        cnt82++;
                    } while (true);


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "order_by_clause"


    public static class order_col_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "order_col"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:474:1: order_col : ( col_range ( ASC | DESC )? | col_ref ( ASC | DESC )? );
    public final AstValidator.order_col_return order_col() throws RecognitionException {
        AstValidator.order_col_return retval = new AstValidator.order_col_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set298=null;
        CommonTree set300=null;
        AstValidator.col_range_return col_range297 =null;

        AstValidator.col_ref_return col_ref299 =null;


        CommonTree set298_tree=null;
        CommonTree set300_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:474:11: ( col_range ( ASC | DESC )? | col_ref ( ASC | DESC )? )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==COL_RANGE) ) {
                alt86=1;
            }
            else if ( (LA86_0==CUBE||LA86_0==DOLLARVAR||LA86_0==GROUP||LA86_0==IDENTIFIER) ) {
                alt86=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }
            switch (alt86) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:474:13: col_range ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_order_col2514);
                    col_range297=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range297.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:474:23: ( ASC | DESC )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==ASC||LA84_0==DESC) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set298=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set298_tree = (CommonTree)adaptor.dupNode(set298);


                                adaptor.addChild(root_0, set298_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:475:13: col_ref ( ASC | DESC )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_order_col2537);
                    col_ref299=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_ref299.getTree());


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:475:21: ( ASC | DESC )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==ASC||LA85_0==DESC) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
                            {
                            _last = (CommonTree)input.LT(1);
                            set300=(CommonTree)input.LT(1);

                            if ( input.LA(1)==ASC||input.LA(1)==DESC ) {
                                input.consume();
                                if ( state.backtracking==0 ) {
                                set300_tree = (CommonTree)adaptor.dupNode(set300);


                                adaptor.addChild(root_0, set300_tree);
                                }

                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "order_col"


    public static class distinct_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "distinct_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:478:1: distinct_clause : ^( DISTINCT rel ( partition_clause )? ) ;
    public final AstValidator.distinct_clause_return distinct_clause() throws RecognitionException {
        AstValidator.distinct_clause_return retval = new AstValidator.distinct_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DISTINCT301=null;
        AstValidator.rel_return rel302 =null;

        AstValidator.partition_clause_return partition_clause303 =null;


        CommonTree DISTINCT301_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:478:17: ( ^( DISTINCT rel ( partition_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:478:19: ^( DISTINCT rel ( partition_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            DISTINCT301=(CommonTree)match(input,DISTINCT,FOLLOW_DISTINCT_in_distinct_clause2559); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DISTINCT301_tree = (CommonTree)adaptor.dupNode(DISTINCT301);


            root_1 = (CommonTree)adaptor.becomeRoot(DISTINCT301_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_distinct_clause2561);
            rel302=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel302.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:478:35: ( partition_clause )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==PARTITION) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:478:35: partition_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_partition_clause_in_distinct_clause2563);
                    partition_clause303=partition_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, partition_clause303.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "distinct_clause"


    public static class partition_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "partition_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:481:1: partition_clause : ^( PARTITION func_name ) ;
    public final AstValidator.partition_clause_return partition_clause() throws RecognitionException {
        AstValidator.partition_clause_return retval = new AstValidator.partition_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PARTITION304=null;
        AstValidator.func_name_return func_name305 =null;


        CommonTree PARTITION304_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:481:18: ( ^( PARTITION func_name ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:481:20: ^( PARTITION func_name )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PARTITION304=(CommonTree)match(input,PARTITION,FOLLOW_PARTITION_in_partition_clause2577); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PARTITION304_tree = (CommonTree)adaptor.dupNode(PARTITION304);


            root_1 = (CommonTree)adaptor.becomeRoot(PARTITION304_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_func_name_in_partition_clause2579);
            func_name305=func_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, func_name305.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "partition_clause"


    public static class cross_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cross_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:484:1: cross_clause : ^( CROSS rel_list ( partition_clause )? ) ;
    public final AstValidator.cross_clause_return cross_clause() throws RecognitionException {
        AstValidator.cross_clause_return retval = new AstValidator.cross_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CROSS306=null;
        AstValidator.rel_list_return rel_list307 =null;

        AstValidator.partition_clause_return partition_clause308 =null;


        CommonTree CROSS306_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:484:14: ( ^( CROSS rel_list ( partition_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:484:16: ^( CROSS rel_list ( partition_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CROSS306=(CommonTree)match(input,CROSS,FOLLOW_CROSS_in_cross_clause2592); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CROSS306_tree = (CommonTree)adaptor.dupNode(CROSS306);


            root_1 = (CommonTree)adaptor.becomeRoot(CROSS306_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_list_in_cross_clause2594);
            rel_list307=rel_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel_list307.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:484:34: ( partition_clause )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==PARTITION) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:484:34: partition_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_partition_clause_in_cross_clause2596);
                    partition_clause308=partition_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, partition_clause308.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cross_clause"


    public static class rel_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:487:1: rel_list : ( rel )+ ;
    public final AstValidator.rel_list_return rel_list() throws RecognitionException {
        AstValidator.rel_list_return retval = new AstValidator.rel_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.rel_return rel309 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:487:10: ( ( rel )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:487:12: ( rel )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:487:12: ( rel )+
            int cnt89=0;
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==ARROBA||LA89_0==ASSERT||LA89_0==COGROUP||(LA89_0 >= CROSS && LA89_0 <= CUBE)||LA89_0==DEFINE||LA89_0==DISTINCT||LA89_0==FILTER||LA89_0==FOREACH||LA89_0==GROUP||LA89_0==IDENTIFIER||LA89_0==JOIN||(LA89_0 >= LIMIT && LA89_0 <= LOAD)||LA89_0==MAPREDUCE||LA89_0==ORDER||LA89_0==RANK||LA89_0==SAMPLE||LA89_0==SPLIT||(LA89_0 >= STORE && LA89_0 <= STREAM)||LA89_0==UNION) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:487:12: rel
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_rel_in_rel_list2608);
            	    rel309=rel();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, rel309.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt89 >= 1 ) break loop89;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(89, input);
                        throw eee;
                }
                cnt89++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_list"


    protected static class join_clause_scope {
        int arity;
    }
    protected Stack join_clause_stack = new Stack();


    public static class join_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:490:1: join_clause : ^( JOIN join_sub_clause ( join_type )? ( partition_clause )? ) ;
    public final AstValidator.join_clause_return join_clause() throws RecognitionException {
        join_clause_stack.push(new join_clause_scope());
        AstValidator.join_clause_return retval = new AstValidator.join_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree JOIN310=null;
        AstValidator.join_sub_clause_return join_sub_clause311 =null;

        AstValidator.join_type_return join_type312 =null;

        AstValidator.partition_clause_return partition_clause313 =null;


        CommonTree JOIN310_tree=null;


            ((join_clause_scope)join_clause_stack.peek()).arity = 0;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:2: ( ^( JOIN join_sub_clause ( join_type )? ( partition_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:4: ^( JOIN join_sub_clause ( join_type )? ( partition_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            JOIN310=(CommonTree)match(input,JOIN,FOLLOW_JOIN_in_join_clause2630); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            JOIN310_tree = (CommonTree)adaptor.dupNode(JOIN310);


            root_1 = (CommonTree)adaptor.becomeRoot(JOIN310_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_join_sub_clause_in_join_clause2632);
            join_sub_clause311=join_sub_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, join_sub_clause311.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:28: ( join_type )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==QUOTEDSTRING) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:28: join_type
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_join_type_in_join_clause2634);
                    join_type312=join_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, join_type312.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:39: ( partition_clause )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==PARTITION) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:497:39: partition_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_partition_clause_in_join_clause2637);
                    partition_clause313=partition_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, partition_clause313.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
            join_clause_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "join_clause"


    public static class join_type_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_type"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:500:1: join_type : QUOTEDSTRING ;
    public final AstValidator.join_type_return join_type() throws RecognitionException {
        AstValidator.join_type_return retval = new AstValidator.join_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING314=null;

        CommonTree QUOTEDSTRING314_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:500:11: ( QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:500:13: QUOTEDSTRING
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING314=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_join_type2649); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING314_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING314);


            adaptor.addChild(root_0, QUOTEDSTRING314_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "join_type"


    public static class join_sub_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_sub_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:503:1: join_sub_clause : ( join_item ( LEFT | RIGHT | FULL ) ( OUTER )? join_item | ( join_item )+ );
    public final AstValidator.join_sub_clause_return join_sub_clause() throws RecognitionException {
        AstValidator.join_sub_clause_return retval = new AstValidator.join_sub_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set316=null;
        CommonTree OUTER317=null;
        AstValidator.join_item_return join_item315 =null;

        AstValidator.join_item_return join_item318 =null;

        AstValidator.join_item_return join_item319 =null;


        CommonTree set316_tree=null;
        CommonTree OUTER317_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:2: ( join_item ( LEFT | RIGHT | FULL ) ( OUTER )? join_item | ( join_item )+ )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==JOIN_ITEM) ) {
                int LA94_1 = input.LA(2);

                if ( (synpred172_AstValidator()) ) {
                    alt94=1;
                }
                else if ( (true) ) {
                    alt94=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 94, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;

            }
            switch (alt94) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:4: join_item ( LEFT | RIGHT | FULL ) ( OUTER )? join_item
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_join_item_in_join_sub_clause2659);
                    join_item315=join_item();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, join_item315.getTree());


                    _last = (CommonTree)input.LT(1);
                    set316=(CommonTree)input.LT(1);

                    if ( input.LA(1)==FULL||input.LA(1)==LEFT||input.LA(1)==RIGHT ) {
                        input.consume();
                        if ( state.backtracking==0 ) {
                        set316_tree = (CommonTree)adaptor.dupNode(set316);


                        adaptor.addChild(root_0, set316_tree);
                        }

                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:38: ( OUTER )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==OUTER) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:38: OUTER
                            {
                            _last = (CommonTree)input.LT(1);
                            OUTER317=(CommonTree)match(input,OUTER,FOLLOW_OUTER_in_join_sub_clause2675); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            OUTER317_tree = (CommonTree)adaptor.dupNode(OUTER317);


                            adaptor.addChild(root_0, OUTER317_tree);
                            }


                            if ( state.backtracking==0 ) {
                            }
                            }
                            break;

                    }


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_join_item_in_join_sub_clause2678);
                    join_item318=join_item();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, join_item318.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:505:4: ( join_item )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:505:4: ( join_item )+
                    int cnt93=0;
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==JOIN_ITEM) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:505:4: join_item
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    pushFollow(FOLLOW_join_item_in_join_sub_clause2683);
                    	    join_item319=join_item();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) 
                    	    adaptor.addChild(root_0, join_item319.getTree());


                    	    if ( state.backtracking==0 ) {
                    	    }
                    	    }
                    	    break;

                    	default :
                    	    if ( cnt93 >= 1 ) break loop93;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(93, input);
                                throw eee;
                        }
                        cnt93++;
                    } while (true);


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "join_sub_clause"


    public static class join_item_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_item"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:508:1: join_item : ^( JOIN_ITEM rel join_group_by_clause ) ;
    public final AstValidator.join_item_return join_item() throws RecognitionException {
        AstValidator.join_item_return retval = new AstValidator.join_item_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree JOIN_ITEM320=null;
        AstValidator.rel_return rel321 =null;

        AstValidator.join_group_by_clause_return join_group_by_clause322 =null;


        CommonTree JOIN_ITEM320_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:509:2: ( ^( JOIN_ITEM rel join_group_by_clause ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:509:4: ^( JOIN_ITEM rel join_group_by_clause )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            JOIN_ITEM320=(CommonTree)match(input,JOIN_ITEM,FOLLOW_JOIN_ITEM_in_join_item2696); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            JOIN_ITEM320_tree = (CommonTree)adaptor.dupNode(JOIN_ITEM320);


            root_1 = (CommonTree)adaptor.becomeRoot(JOIN_ITEM320_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_join_item2698);
            rel321=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel321.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_join_group_by_clause_in_join_item2700);
            join_group_by_clause322=join_group_by_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, join_group_by_clause322.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
                   if( ((join_clause_scope)join_clause_stack.peek()).arity == 0 ) {
                       // For the first input
                       ((join_clause_scope)join_clause_stack.peek()).arity = (join_group_by_clause322!=null?join_group_by_clause322.exprCount:0);
                   } else if( (join_group_by_clause322!=null?join_group_by_clause322.exprCount:0) != ((join_clause_scope)join_clause_stack.peek()).arity ) {
                       throw new ParserValidationException( input, new SourceLocation( (PigParserNode)((CommonTree)retval.start) ),
                           "The arity of the join columns do not match." );
                   }
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "join_item"


    public static class join_group_by_clause_return extends TreeRuleReturnScope {
        public int exprCount;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_group_by_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:521:1: join_group_by_clause returns [int exprCount] : ^( BY ( join_group_by_expr )+ ) ;
    public final AstValidator.join_group_by_clause_return join_group_by_clause() throws RecognitionException {
        AstValidator.join_group_by_clause_return retval = new AstValidator.join_group_by_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BY323=null;
        AstValidator.join_group_by_expr_return join_group_by_expr324 =null;


        CommonTree BY323_tree=null;


            retval.exprCount = 0;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:525:2: ( ^( BY ( join_group_by_expr )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:525:4: ^( BY ( join_group_by_expr )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BY323=(CommonTree)match(input,BY,FOLLOW_BY_in_join_group_by_clause2727); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BY323_tree = (CommonTree)adaptor.dupNode(BY323);


            root_1 = (CommonTree)adaptor.becomeRoot(BY323_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:525:10: ( join_group_by_expr )+
            int cnt95=0;
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==BIGDECIMALNUMBER||LA95_0==BIGINTEGERNUMBER||LA95_0==CUBE||LA95_0==DIV||LA95_0==DOLLARVAR||LA95_0==DOUBLENUMBER||LA95_0==FALSE||LA95_0==FLOATNUMBER||LA95_0==GROUP||LA95_0==IDENTIFIER||LA95_0==INTEGER||LA95_0==LONGINTEGER||LA95_0==MINUS||LA95_0==NULL||LA95_0==PERCENT||LA95_0==PLUS||LA95_0==QUOTEDSTRING||LA95_0==STAR||LA95_0==TRUE||(LA95_0 >= BAG_VAL && LA95_0 <= BIN_EXPR)||(LA95_0 >= CASE_COND && LA95_0 <= CASE_EXPR)||(LA95_0 >= CAST_EXPR && LA95_0 <= EXPR_IN_PAREN)||LA95_0==FUNC_EVAL||LA95_0==INVOKER_FUNC_EVAL||(LA95_0 >= MAP_VAL && LA95_0 <= NEG)||LA95_0==TUPLE_VAL) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:525:12: join_group_by_expr
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_join_group_by_expr_in_join_group_by_clause2731);
            	    join_group_by_expr324=join_group_by_expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, join_group_by_expr324.getTree());


            	    if ( state.backtracking==0 ) { retval.exprCount++; }

            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt95 >= 1 ) break loop95;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(95, input);
                        throw eee;
                }
                cnt95++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "join_group_by_clause"


    public static class join_group_by_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "join_group_by_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:528:1: join_group_by_expr : ( col_range | expr | STAR );
    public final AstValidator.join_group_by_expr_return join_group_by_expr() throws RecognitionException {
        AstValidator.join_group_by_expr_return retval = new AstValidator.join_group_by_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STAR327=null;
        AstValidator.col_range_return col_range325 =null;

        AstValidator.expr_return expr326 =null;


        CommonTree STAR327_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:528:20: ( col_range | expr | STAR )
            int alt96=3;
            switch ( input.LA(1) ) {
            case COL_RANGE:
                {
                alt96=1;
                }
                break;
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case CUBE:
            case DIV:
            case DOLLARVAR:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case GROUP:
            case IDENTIFIER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
            case NULL:
            case PERCENT:
            case PLUS:
            case QUOTEDSTRING:
            case TRUE:
            case BAG_VAL:
            case BIN_EXPR:
            case CASE_COND:
            case CASE_EXPR:
            case CAST_EXPR:
            case EXPR_IN_PAREN:
            case FUNC_EVAL:
            case INVOKER_FUNC_EVAL:
            case MAP_VAL:
            case NEG:
            case TUPLE_VAL:
                {
                alt96=2;
                }
                break;
            case STAR:
                {
                int LA96_3 = input.LA(2);

                if ( (LA96_3==DOWN) ) {
                    alt96=2;
                }
                else if ( (LA96_3==EOF||LA96_3==UP||LA96_3==BIGDECIMALNUMBER||LA96_3==BIGINTEGERNUMBER||LA96_3==CUBE||LA96_3==DIV||LA96_3==DOLLARVAR||LA96_3==DOUBLENUMBER||LA96_3==FALSE||LA96_3==FLOATNUMBER||LA96_3==GROUP||LA96_3==IDENTIFIER||LA96_3==INTEGER||LA96_3==LONGINTEGER||LA96_3==MINUS||LA96_3==NULL||LA96_3==PERCENT||LA96_3==PLUS||LA96_3==QUOTEDSTRING||LA96_3==STAR||LA96_3==TRUE||(LA96_3 >= BAG_VAL && LA96_3 <= BIN_EXPR)||(LA96_3 >= CASE_COND && LA96_3 <= CASE_EXPR)||(LA96_3 >= CAST_EXPR && LA96_3 <= EXPR_IN_PAREN)||LA96_3==FUNC_EVAL||LA96_3==INVOKER_FUNC_EVAL||(LA96_3 >= MAP_VAL && LA96_3 <= NEG)||LA96_3==TUPLE_VAL) ) {
                    alt96=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;

            }

            switch (alt96) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:528:22: col_range
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_range_in_join_group_by_expr2747);
                    col_range325=col_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_range325.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:528:35: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_join_group_by_expr2752);
                    expr326=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, expr326.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:528:42: STAR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STAR327=(CommonTree)match(input,STAR,FOLLOW_STAR_in_join_group_by_expr2756); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR327_tree = (CommonTree)adaptor.dupNode(STAR327);


                    adaptor.addChild(root_0, STAR327_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "join_group_by_expr"


    public static class union_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "union_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:531:1: union_clause : ^( UNION ( ONSCHEMA )? rel_list ) ;
    public final AstValidator.union_clause_return union_clause() throws RecognitionException {
        AstValidator.union_clause_return retval = new AstValidator.union_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree UNION328=null;
        CommonTree ONSCHEMA329=null;
        AstValidator.rel_list_return rel_list330 =null;


        CommonTree UNION328_tree=null;
        CommonTree ONSCHEMA329_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:531:14: ( ^( UNION ( ONSCHEMA )? rel_list ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:531:16: ^( UNION ( ONSCHEMA )? rel_list )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            UNION328=(CommonTree)match(input,UNION,FOLLOW_UNION_in_union_clause2767); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            UNION328_tree = (CommonTree)adaptor.dupNode(UNION328);


            root_1 = (CommonTree)adaptor.becomeRoot(UNION328_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:531:25: ( ONSCHEMA )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==ONSCHEMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:531:25: ONSCHEMA
                    {
                    _last = (CommonTree)input.LT(1);
                    ONSCHEMA329=(CommonTree)match(input,ONSCHEMA,FOLLOW_ONSCHEMA_in_union_clause2769); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ONSCHEMA329_tree = (CommonTree)adaptor.dupNode(ONSCHEMA329);


                    adaptor.addChild(root_1, ONSCHEMA329_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_list_in_union_clause2772);
            rel_list330=rel_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel_list330.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "union_clause"


    public static class foreach_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "foreach_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:534:1: foreach_clause : ^( FOREACH rel foreach_plan ) ;
    public final AstValidator.foreach_clause_return foreach_clause() throws RecognitionException {
        AstValidator.foreach_clause_return retval = new AstValidator.foreach_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FOREACH331=null;
        AstValidator.rel_return rel332 =null;

        AstValidator.foreach_plan_return foreach_plan333 =null;


        CommonTree FOREACH331_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:534:16: ( ^( FOREACH rel foreach_plan ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:534:18: ^( FOREACH rel foreach_plan )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            FOREACH331=(CommonTree)match(input,FOREACH,FOLLOW_FOREACH_in_foreach_clause2785); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOREACH331_tree = (CommonTree)adaptor.dupNode(FOREACH331);


            root_1 = (CommonTree)adaptor.becomeRoot(FOREACH331_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_foreach_clause2787);
            rel332=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel332.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_foreach_plan_in_foreach_clause2789);
            foreach_plan333=foreach_plan();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, foreach_plan333.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "foreach_clause"


    public static class foreach_plan_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "foreach_plan"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:537:1: foreach_plan : ( ^( FOREACH_PLAN_SIMPLE generate_clause ) | ^( FOREACH_PLAN_COMPLEX nested_blk ) );
    public final AstValidator.foreach_plan_return foreach_plan() throws RecognitionException {
        AstValidator.foreach_plan_return retval = new AstValidator.foreach_plan_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FOREACH_PLAN_SIMPLE334=null;
        CommonTree FOREACH_PLAN_COMPLEX336=null;
        AstValidator.generate_clause_return generate_clause335 =null;

        AstValidator.nested_blk_return nested_blk337 =null;


        CommonTree FOREACH_PLAN_SIMPLE334_tree=null;
        CommonTree FOREACH_PLAN_COMPLEX336_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:537:14: ( ^( FOREACH_PLAN_SIMPLE generate_clause ) | ^( FOREACH_PLAN_COMPLEX nested_blk ) )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==FOREACH_PLAN_SIMPLE) ) {
                alt98=1;
            }
            else if ( (LA98_0==FOREACH_PLAN_COMPLEX) ) {
                alt98=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;

            }
            switch (alt98) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:537:16: ^( FOREACH_PLAN_SIMPLE generate_clause )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FOREACH_PLAN_SIMPLE334=(CommonTree)match(input,FOREACH_PLAN_SIMPLE,FOLLOW_FOREACH_PLAN_SIMPLE_in_foreach_plan2802); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FOREACH_PLAN_SIMPLE334_tree = (CommonTree)adaptor.dupNode(FOREACH_PLAN_SIMPLE334);


                    root_1 = (CommonTree)adaptor.becomeRoot(FOREACH_PLAN_SIMPLE334_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_generate_clause_in_foreach_plan2804);
                    generate_clause335=generate_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, generate_clause335.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:538:16: ^( FOREACH_PLAN_COMPLEX nested_blk )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    FOREACH_PLAN_COMPLEX336=(CommonTree)match(input,FOREACH_PLAN_COMPLEX,FOLLOW_FOREACH_PLAN_COMPLEX_in_foreach_plan2825); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FOREACH_PLAN_COMPLEX336_tree = (CommonTree)adaptor.dupNode(FOREACH_PLAN_COMPLEX336);


                    root_1 = (CommonTree)adaptor.becomeRoot(FOREACH_PLAN_COMPLEX336_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_blk_in_foreach_plan2827);
                    nested_blk337=nested_blk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, nested_blk337.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "foreach_plan"


    protected static class nested_blk_scope {
        Set<String> ids;
    }
    protected Stack nested_blk_stack = new Stack();


    public static class nested_blk_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_blk"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:541:1: nested_blk : ( nested_command )* generate_clause ;
    public final AstValidator.nested_blk_return nested_blk() throws RecognitionException {
        nested_blk_stack.push(new nested_blk_scope());
        AstValidator.nested_blk_return retval = new AstValidator.nested_blk_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.nested_command_return nested_command338 =null;

        AstValidator.generate_clause_return generate_clause339 =null;



         ((nested_blk_scope)nested_blk_stack.peek()).ids = new HashSet<String>(); 
        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:544:2: ( ( nested_command )* generate_clause )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:544:4: ( nested_command )* generate_clause
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:544:4: ( nested_command )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( ((LA99_0 >= NESTED_CMD && LA99_0 <= NESTED_CMD_ASSI)) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:544:4: nested_command
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_nested_command_in_nested_blk2847);
            	    nested_command338=nested_command();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, nested_command338.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_generate_clause_in_nested_blk2850);
            generate_clause339=generate_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, generate_clause339.getTree());


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
            nested_blk_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "nested_blk"


    public static class generate_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "generate_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:547:1: generate_clause : ^( GENERATE ( flatten_generated_item )+ ) ;
    public final AstValidator.generate_clause_return generate_clause() throws RecognitionException {
        AstValidator.generate_clause_return retval = new AstValidator.generate_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree GENERATE340=null;
        AstValidator.flatten_generated_item_return flatten_generated_item341 =null;


        CommonTree GENERATE340_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:547:17: ( ^( GENERATE ( flatten_generated_item )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:547:19: ^( GENERATE ( flatten_generated_item )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            GENERATE340=(CommonTree)match(input,GENERATE,FOLLOW_GENERATE_in_generate_clause2861); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            GENERATE340_tree = (CommonTree)adaptor.dupNode(GENERATE340);


            root_1 = (CommonTree)adaptor.becomeRoot(GENERATE340_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:547:31: ( flatten_generated_item )+
            int cnt100=0;
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==BIGDECIMALNUMBER||LA100_0==BIGINTEGERNUMBER||LA100_0==CUBE||LA100_0==DIV||LA100_0==DOLLARVAR||LA100_0==DOUBLENUMBER||LA100_0==FALSE||LA100_0==FLATTEN||LA100_0==FLOATNUMBER||LA100_0==GROUP||LA100_0==IDENTIFIER||LA100_0==INTEGER||LA100_0==LONGINTEGER||LA100_0==MINUS||LA100_0==NULL||LA100_0==PERCENT||LA100_0==PLUS||LA100_0==QUOTEDSTRING||LA100_0==STAR||LA100_0==TRUE||(LA100_0 >= BAG_VAL && LA100_0 <= BIN_EXPR)||(LA100_0 >= CASE_COND && LA100_0 <= CASE_EXPR)||(LA100_0 >= CAST_EXPR && LA100_0 <= EXPR_IN_PAREN)||LA100_0==FUNC_EVAL||LA100_0==INVOKER_FUNC_EVAL||(LA100_0 >= MAP_VAL && LA100_0 <= NEG)||LA100_0==TUPLE_VAL) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:547:31: flatten_generated_item
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_flatten_generated_item_in_generate_clause2863);
            	    flatten_generated_item341=flatten_generated_item();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, flatten_generated_item341.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt100 >= 1 ) break loop100;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(100, input);
                        throw eee;
                }
                cnt100++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "generate_clause"


    public static class nested_command_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_command"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:550:1: nested_command : ( ^( NESTED_CMD IDENTIFIER nested_op ) | ^( NESTED_CMD_ASSI IDENTIFIER expr ) );
    public final AstValidator.nested_command_return nested_command() throws RecognitionException {
        AstValidator.nested_command_return retval = new AstValidator.nested_command_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree NESTED_CMD342=null;
        CommonTree IDENTIFIER343=null;
        CommonTree NESTED_CMD_ASSI345=null;
        CommonTree IDENTIFIER346=null;
        AstValidator.nested_op_return nested_op344 =null;

        AstValidator.expr_return expr347 =null;


        CommonTree NESTED_CMD342_tree=null;
        CommonTree IDENTIFIER343_tree=null;
        CommonTree NESTED_CMD_ASSI345_tree=null;
        CommonTree IDENTIFIER346_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:551:2: ( ^( NESTED_CMD IDENTIFIER nested_op ) | ^( NESTED_CMD_ASSI IDENTIFIER expr ) )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==NESTED_CMD) ) {
                alt101=1;
            }
            else if ( (LA101_0==NESTED_CMD_ASSI) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;

            }
            switch (alt101) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:551:4: ^( NESTED_CMD IDENTIFIER nested_op )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    NESTED_CMD342=(CommonTree)match(input,NESTED_CMD,FOLLOW_NESTED_CMD_in_nested_command2878); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NESTED_CMD342_tree = (CommonTree)adaptor.dupNode(NESTED_CMD342);


                    root_1 = (CommonTree)adaptor.becomeRoot(NESTED_CMD342_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER343=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_nested_command2880); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER343_tree = (CommonTree)adaptor.dupNode(IDENTIFIER343);


                    adaptor.addChild(root_1, IDENTIFIER343_tree);
                    }


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_op_in_nested_command2882);
                    nested_op344=nested_op();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, nested_op344.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                           ((nested_blk_scope)nested_blk_stack.peek()).ids.add( (IDENTIFIER343!=null?IDENTIFIER343.getText():null) );
                       }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:555:4: ^( NESTED_CMD_ASSI IDENTIFIER expr )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    NESTED_CMD_ASSI345=(CommonTree)match(input,NESTED_CMD_ASSI,FOLLOW_NESTED_CMD_ASSI_in_nested_command2896); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NESTED_CMD_ASSI345_tree = (CommonTree)adaptor.dupNode(NESTED_CMD_ASSI345);


                    root_1 = (CommonTree)adaptor.becomeRoot(NESTED_CMD_ASSI345_tree, root_1);
                    }


                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER346=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_nested_command2898); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER346_tree = (CommonTree)adaptor.dupNode(IDENTIFIER346);


                    adaptor.addChild(root_1, IDENTIFIER346_tree);
                    }


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_nested_command2900);
                    expr347=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr347.getTree());


                    match(input, Token.UP, null); if (state.failed) return retval;
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    if ( state.backtracking==0 ) {
                           ((nested_blk_scope)nested_blk_stack.peek()).ids.add( (IDENTIFIER346!=null?IDENTIFIER346.getText():null) );
                       }

                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_command"


    public static class nested_op_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_op"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:561:1: nested_op : ( nested_proj | nested_filter | nested_sort | nested_distinct | nested_limit | nested_cross | nested_foreach );
    public final AstValidator.nested_op_return nested_op() throws RecognitionException {
        AstValidator.nested_op_return retval = new AstValidator.nested_op_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.nested_proj_return nested_proj348 =null;

        AstValidator.nested_filter_return nested_filter349 =null;

        AstValidator.nested_sort_return nested_sort350 =null;

        AstValidator.nested_distinct_return nested_distinct351 =null;

        AstValidator.nested_limit_return nested_limit352 =null;

        AstValidator.nested_cross_return nested_cross353 =null;

        AstValidator.nested_foreach_return nested_foreach354 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:561:11: ( nested_proj | nested_filter | nested_sort | nested_distinct | nested_limit | nested_cross | nested_foreach )
            int alt102=7;
            switch ( input.LA(1) ) {
            case NESTED_PROJ:
                {
                alt102=1;
                }
                break;
            case FILTER:
                {
                alt102=2;
                }
                break;
            case ORDER:
                {
                alt102=3;
                }
                break;
            case DISTINCT:
                {
                alt102=4;
                }
                break;
            case LIMIT:
                {
                alt102=5;
                }
                break;
            case CROSS:
                {
                alt102=6;
                }
                break;
            case FOREACH:
                {
                alt102=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;

            }

            switch (alt102) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:561:13: nested_proj
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_proj_in_nested_op2916);
                    nested_proj348=nested_proj();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_proj348.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:562:13: nested_filter
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_filter_in_nested_op2930);
                    nested_filter349=nested_filter();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_filter349.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:563:13: nested_sort
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_sort_in_nested_op2944);
                    nested_sort350=nested_sort();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_sort350.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:564:13: nested_distinct
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_distinct_in_nested_op2958);
                    nested_distinct351=nested_distinct();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_distinct351.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:565:13: nested_limit
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_limit_in_nested_op2972);
                    nested_limit352=nested_limit();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_limit352.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:566:13: nested_cross
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_cross_in_nested_op2986);
                    nested_cross353=nested_cross();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_cross353.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:567:13: nested_foreach
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_foreach_in_nested_op3000);
                    nested_foreach354=nested_foreach();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_foreach354.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_op"


    public static class nested_proj_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_proj"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:570:1: nested_proj : ^( NESTED_PROJ col_ref ( col_ref )+ ) ;
    public final AstValidator.nested_proj_return nested_proj() throws RecognitionException {
        AstValidator.nested_proj_return retval = new AstValidator.nested_proj_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree NESTED_PROJ355=null;
        AstValidator.col_ref_return col_ref356 =null;

        AstValidator.col_ref_return col_ref357 =null;


        CommonTree NESTED_PROJ355_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:570:13: ( ^( NESTED_PROJ col_ref ( col_ref )+ ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:570:15: ^( NESTED_PROJ col_ref ( col_ref )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            NESTED_PROJ355=(CommonTree)match(input,NESTED_PROJ,FOLLOW_NESTED_PROJ_in_nested_proj3011); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NESTED_PROJ355_tree = (CommonTree)adaptor.dupNode(NESTED_PROJ355);


            root_1 = (CommonTree)adaptor.becomeRoot(NESTED_PROJ355_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_col_ref_in_nested_proj3013);
            col_ref356=col_ref();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, col_ref356.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:570:38: ( col_ref )+
            int cnt103=0;
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==CUBE||LA103_0==DOLLARVAR||LA103_0==GROUP||LA103_0==IDENTIFIER) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:570:38: col_ref
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_col_ref_in_nested_proj3015);
            	    col_ref357=col_ref();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, col_ref357.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt103 >= 1 ) break loop103;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(103, input);
                        throw eee;
                }
                cnt103++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_proj"


    public static class nested_filter_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_filter"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:573:1: nested_filter : ^( FILTER nested_op_input cond ) ;
    public final AstValidator.nested_filter_return nested_filter() throws RecognitionException {
        AstValidator.nested_filter_return retval = new AstValidator.nested_filter_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FILTER358=null;
        AstValidator.nested_op_input_return nested_op_input359 =null;

        AstValidator.cond_return cond360 =null;


        CommonTree FILTER358_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:574:2: ( ^( FILTER nested_op_input cond ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:574:4: ^( FILTER nested_op_input cond )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            FILTER358=(CommonTree)match(input,FILTER,FOLLOW_FILTER_in_nested_filter3030); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FILTER358_tree = (CommonTree)adaptor.dupNode(FILTER358);


            root_1 = (CommonTree)adaptor.becomeRoot(FILTER358_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_in_nested_filter3032);
            nested_op_input359=nested_op_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input359.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cond_in_nested_filter3034);
            cond360=cond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cond360.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_filter"


    public static class nested_sort_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_sort"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:577:1: nested_sort : ^( ORDER nested_op_input order_by_clause ( func_clause )? ) ;
    public final AstValidator.nested_sort_return nested_sort() throws RecognitionException {
        AstValidator.nested_sort_return retval = new AstValidator.nested_sort_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ORDER361=null;
        AstValidator.nested_op_input_return nested_op_input362 =null;

        AstValidator.order_by_clause_return order_by_clause363 =null;

        AstValidator.func_clause_return func_clause364 =null;


        CommonTree ORDER361_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:577:13: ( ^( ORDER nested_op_input order_by_clause ( func_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:577:15: ^( ORDER nested_op_input order_by_clause ( func_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            ORDER361=(CommonTree)match(input,ORDER,FOLLOW_ORDER_in_nested_sort3047); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORDER361_tree = (CommonTree)adaptor.dupNode(ORDER361);


            root_1 = (CommonTree)adaptor.becomeRoot(ORDER361_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_in_nested_sort3049);
            nested_op_input362=nested_op_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input362.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_order_by_clause_in_nested_sort3052);
            order_by_clause363=order_by_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, order_by_clause363.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:577:57: ( func_clause )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==FUNC||LA104_0==FUNC_REF) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:577:57: func_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_func_clause_in_nested_sort3054);
                    func_clause364=func_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, func_clause364.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_sort"


    public static class nested_distinct_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_distinct"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:580:1: nested_distinct : ^( DISTINCT nested_op_input ) ;
    public final AstValidator.nested_distinct_return nested_distinct() throws RecognitionException {
        AstValidator.nested_distinct_return retval = new AstValidator.nested_distinct_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DISTINCT365=null;
        AstValidator.nested_op_input_return nested_op_input366 =null;


        CommonTree DISTINCT365_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:580:17: ( ^( DISTINCT nested_op_input ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:580:19: ^( DISTINCT nested_op_input )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            DISTINCT365=(CommonTree)match(input,DISTINCT,FOLLOW_DISTINCT_in_nested_distinct3068); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DISTINCT365_tree = (CommonTree)adaptor.dupNode(DISTINCT365);


            root_1 = (CommonTree)adaptor.becomeRoot(DISTINCT365_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_in_nested_distinct3070);
            nested_op_input366=nested_op_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input366.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_distinct"


    public static class nested_limit_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_limit"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:1: nested_limit : ^( LIMIT nested_op_input ( INTEGER | expr ) ) ;
    public final AstValidator.nested_limit_return nested_limit() throws RecognitionException {
        AstValidator.nested_limit_return retval = new AstValidator.nested_limit_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree LIMIT367=null;
        CommonTree INTEGER369=null;
        AstValidator.nested_op_input_return nested_op_input368 =null;

        AstValidator.expr_return expr370 =null;


        CommonTree LIMIT367_tree=null;
        CommonTree INTEGER369_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:14: ( ^( LIMIT nested_op_input ( INTEGER | expr ) ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:16: ^( LIMIT nested_op_input ( INTEGER | expr ) )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            LIMIT367=(CommonTree)match(input,LIMIT,FOLLOW_LIMIT_in_nested_limit3083); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LIMIT367_tree = (CommonTree)adaptor.dupNode(LIMIT367);


            root_1 = (CommonTree)adaptor.becomeRoot(LIMIT367_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_in_nested_limit3085);
            nested_op_input368=nested_op_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input368.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:41: ( INTEGER | expr )
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==INTEGER) ) {
                int LA105_1 = input.LA(2);

                if ( (synpred190_AstValidator()) ) {
                    alt105=1;
                }
                else if ( (true) ) {
                    alt105=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA105_0==BIGDECIMALNUMBER||LA105_0==BIGINTEGERNUMBER||LA105_0==CUBE||LA105_0==DIV||LA105_0==DOLLARVAR||LA105_0==DOUBLENUMBER||LA105_0==FALSE||LA105_0==FLOATNUMBER||LA105_0==GROUP||LA105_0==IDENTIFIER||LA105_0==LONGINTEGER||LA105_0==MINUS||LA105_0==NULL||LA105_0==PERCENT||LA105_0==PLUS||LA105_0==QUOTEDSTRING||LA105_0==STAR||LA105_0==TRUE||(LA105_0 >= BAG_VAL && LA105_0 <= BIN_EXPR)||(LA105_0 >= CASE_COND && LA105_0 <= CASE_EXPR)||LA105_0==CAST_EXPR||LA105_0==EXPR_IN_PAREN||LA105_0==FUNC_EVAL||LA105_0==INVOKER_FUNC_EVAL||(LA105_0 >= MAP_VAL && LA105_0 <= NEG)||LA105_0==TUPLE_VAL) ) {
                alt105=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;

            }
            switch (alt105) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:43: INTEGER
                    {
                    _last = (CommonTree)input.LT(1);
                    INTEGER369=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_nested_limit3089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER369_tree = (CommonTree)adaptor.dupNode(INTEGER369);


                    adaptor.addChild(root_1, INTEGER369_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:53: expr
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expr_in_nested_limit3093);
                    expr370=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, expr370.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_limit"


    public static class nested_cross_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_cross"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:586:1: nested_cross : ^( CROSS nested_op_input_list ) ;
    public final AstValidator.nested_cross_return nested_cross() throws RecognitionException {
        AstValidator.nested_cross_return retval = new AstValidator.nested_cross_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree CROSS371=null;
        AstValidator.nested_op_input_list_return nested_op_input_list372 =null;


        CommonTree CROSS371_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:586:14: ( ^( CROSS nested_op_input_list ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:586:16: ^( CROSS nested_op_input_list )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            CROSS371=(CommonTree)match(input,CROSS,FOLLOW_CROSS_in_nested_cross3108); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CROSS371_tree = (CommonTree)adaptor.dupNode(CROSS371);


            root_1 = (CommonTree)adaptor.becomeRoot(CROSS371_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_list_in_nested_cross3110);
            nested_op_input_list372=nested_op_input_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input_list372.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_cross"


    public static class nested_foreach_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_foreach"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:589:1: nested_foreach : ^( FOREACH nested_op_input generate_clause ) ;
    public final AstValidator.nested_foreach_return nested_foreach() throws RecognitionException {
        AstValidator.nested_foreach_return retval = new AstValidator.nested_foreach_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FOREACH373=null;
        AstValidator.nested_op_input_return nested_op_input374 =null;

        AstValidator.generate_clause_return generate_clause375 =null;


        CommonTree FOREACH373_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:589:16: ( ^( FOREACH nested_op_input generate_clause ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:589:18: ^( FOREACH nested_op_input generate_clause )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            FOREACH373=(CommonTree)match(input,FOREACH,FOLLOW_FOREACH_in_nested_foreach3123); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOREACH373_tree = (CommonTree)adaptor.dupNode(FOREACH373);


            root_1 = (CommonTree)adaptor.becomeRoot(FOREACH373_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nested_op_input_in_nested_foreach3125);
            nested_op_input374=nested_op_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, nested_op_input374.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_generate_clause_in_nested_foreach3127);
            generate_clause375=generate_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, generate_clause375.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_foreach"


    public static class nested_op_input_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_op_input"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:592:1: nested_op_input : ( col_ref | nested_proj );
    public final AstValidator.nested_op_input_return nested_op_input() throws RecognitionException {
        AstValidator.nested_op_input_return retval = new AstValidator.nested_op_input_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.col_ref_return col_ref376 =null;

        AstValidator.nested_proj_return nested_proj377 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:592:17: ( col_ref | nested_proj )
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==CUBE||LA106_0==DOLLARVAR||LA106_0==GROUP||LA106_0==IDENTIFIER) ) {
                alt106=1;
            }
            else if ( (LA106_0==NESTED_PROJ) ) {
                alt106=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 0, input);

                throw nvae;

            }
            switch (alt106) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:592:19: col_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_col_ref_in_nested_op_input3138);
                    col_ref376=col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, col_ref376.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:592:29: nested_proj
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nested_proj_in_nested_op_input3142);
                    nested_proj377=nested_proj();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, nested_proj377.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_op_input"


    public static class nested_op_input_list_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nested_op_input_list"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:595:1: nested_op_input_list : ( nested_op_input )+ ;
    public final AstValidator.nested_op_input_list_return nested_op_input_list() throws RecognitionException {
        AstValidator.nested_op_input_list_return retval = new AstValidator.nested_op_input_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.nested_op_input_return nested_op_input378 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:595:22: ( ( nested_op_input )+ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:595:24: ( nested_op_input )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:595:24: ( nested_op_input )+
            int cnt107=0;
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==CUBE||LA107_0==DOLLARVAR||LA107_0==GROUP||LA107_0==IDENTIFIER||LA107_0==NESTED_PROJ) ) {
                    alt107=1;
                }


                switch (alt107) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:595:24: nested_op_input
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_nested_op_input_in_nested_op_input_list3151);
            	    nested_op_input378=nested_op_input();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_0, nested_op_input378.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt107 >= 1 ) break loop107;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(107, input);
                        throw eee;
                }
                cnt107++;
            } while (true);


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nested_op_input_list"


    public static class stream_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stream_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:598:1: stream_clause : ^( STREAM rel ( EXECCOMMAND | IDENTIFIER ) ( as_clause )? ) ;
    public final AstValidator.stream_clause_return stream_clause() throws RecognitionException {
        AstValidator.stream_clause_return retval = new AstValidator.stream_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STREAM379=null;
        CommonTree set381=null;
        AstValidator.rel_return rel380 =null;

        AstValidator.as_clause_return as_clause382 =null;


        CommonTree STREAM379_tree=null;
        CommonTree set381_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:598:15: ( ^( STREAM rel ( EXECCOMMAND | IDENTIFIER ) ( as_clause )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:598:17: ^( STREAM rel ( EXECCOMMAND | IDENTIFIER ) ( as_clause )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            STREAM379=(CommonTree)match(input,STREAM,FOLLOW_STREAM_in_stream_clause3163); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STREAM379_tree = (CommonTree)adaptor.dupNode(STREAM379);


            root_1 = (CommonTree)adaptor.becomeRoot(STREAM379_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_stream_clause3165);
            rel380=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel380.getTree());


            _last = (CommonTree)input.LT(1);
            set381=(CommonTree)input.LT(1);

            if ( input.LA(1)==EXECCOMMAND||input.LA(1)==IDENTIFIER ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set381_tree = (CommonTree)adaptor.dupNode(set381);


                adaptor.addChild(root_1, set381_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:598:60: ( as_clause )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==AS) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:598:60: as_clause
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_as_clause_in_stream_clause3177);
                    as_clause382=as_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, as_clause382.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stream_clause"


    public static class mr_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mr_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:1: mr_clause : ^( MAPREDUCE QUOTEDSTRING ( path_list )? store_clause load_clause ( EXECCOMMAND )? ) ;
    public final AstValidator.mr_clause_return mr_clause() throws RecognitionException {
        AstValidator.mr_clause_return retval = new AstValidator.mr_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree MAPREDUCE383=null;
        CommonTree QUOTEDSTRING384=null;
        CommonTree EXECCOMMAND388=null;
        AstValidator.path_list_return path_list385 =null;

        AstValidator.store_clause_return store_clause386 =null;

        AstValidator.load_clause_return load_clause387 =null;


        CommonTree MAPREDUCE383_tree=null;
        CommonTree QUOTEDSTRING384_tree=null;
        CommonTree EXECCOMMAND388_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:11: ( ^( MAPREDUCE QUOTEDSTRING ( path_list )? store_clause load_clause ( EXECCOMMAND )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:13: ^( MAPREDUCE QUOTEDSTRING ( path_list )? store_clause load_clause ( EXECCOMMAND )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            MAPREDUCE383=(CommonTree)match(input,MAPREDUCE,FOLLOW_MAPREDUCE_in_mr_clause3191); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MAPREDUCE383_tree = (CommonTree)adaptor.dupNode(MAPREDUCE383);


            root_1 = (CommonTree)adaptor.becomeRoot(MAPREDUCE383_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING384=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_mr_clause3193); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING384_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING384);


            adaptor.addChild(root_1, QUOTEDSTRING384_tree);
            }


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:39: ( path_list )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==QUOTEDSTRING) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:39: path_list
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_path_list_in_mr_clause3195);
                    path_list385=path_list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, path_list385.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_store_clause_in_mr_clause3198);
            store_clause386=store_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, store_clause386.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_load_clause_in_mr_clause3200);
            load_clause387=load_clause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, load_clause387.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:75: ( EXECCOMMAND )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==EXECCOMMAND) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:601:75: EXECCOMMAND
                    {
                    _last = (CommonTree)input.LT(1);
                    EXECCOMMAND388=(CommonTree)match(input,EXECCOMMAND,FOLLOW_EXECCOMMAND_in_mr_clause3202); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXECCOMMAND388_tree = (CommonTree)adaptor.dupNode(EXECCOMMAND388);


                    adaptor.addChild(root_1, EXECCOMMAND388_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mr_clause"


    public static class split_clause_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "split_clause"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:1: split_clause : ^( SPLIT rel ( split_branch )+ ( split_otherwise )? ) ;
    public final AstValidator.split_clause_return split_clause() throws RecognitionException {
        AstValidator.split_clause_return retval = new AstValidator.split_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SPLIT389=null;
        AstValidator.rel_return rel390 =null;

        AstValidator.split_branch_return split_branch391 =null;

        AstValidator.split_otherwise_return split_otherwise392 =null;


        CommonTree SPLIT389_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:14: ( ^( SPLIT rel ( split_branch )+ ( split_otherwise )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:16: ^( SPLIT rel ( split_branch )+ ( split_otherwise )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SPLIT389=(CommonTree)match(input,SPLIT,FOLLOW_SPLIT_in_split_clause3216); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SPLIT389_tree = (CommonTree)adaptor.dupNode(SPLIT389);


            root_1 = (CommonTree)adaptor.becomeRoot(SPLIT389_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_rel_in_split_clause3218);
            rel390=rel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, rel390.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:29: ( split_branch )+
            int cnt111=0;
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==SPLIT_BRANCH) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:29: split_branch
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_split_branch_in_split_clause3220);
            	    split_branch391=split_branch();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) 
            	    adaptor.addChild(root_1, split_branch391.getTree());


            	    if ( state.backtracking==0 ) {
            	    }
            	    }
            	    break;

            	default :
            	    if ( cnt111 >= 1 ) break loop111;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(111, input);
                        throw eee;
                }
                cnt111++;
            } while (true);


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:43: ( split_otherwise )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==OTHERWISE) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:604:43: split_otherwise
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_split_otherwise_in_split_clause3223);
                    split_otherwise392=split_otherwise();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_1, split_otherwise392.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "split_clause"


    public static class split_branch_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "split_branch"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:607:1: split_branch : ^( SPLIT_BRANCH alias cond ) ;
    public final AstValidator.split_branch_return split_branch() throws RecognitionException {
        AstValidator.split_branch_return retval = new AstValidator.split_branch_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SPLIT_BRANCH393=null;
        AstValidator.alias_return alias394 =null;

        AstValidator.cond_return cond395 =null;


        CommonTree SPLIT_BRANCH393_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:608:2: ( ^( SPLIT_BRANCH alias cond ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:608:4: ^( SPLIT_BRANCH alias cond )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SPLIT_BRANCH393=(CommonTree)match(input,SPLIT_BRANCH,FOLLOW_SPLIT_BRANCH_in_split_branch3238); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SPLIT_BRANCH393_tree = (CommonTree)adaptor.dupNode(SPLIT_BRANCH393);


            root_1 = (CommonTree)adaptor.becomeRoot(SPLIT_BRANCH393_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_alias_in_split_branch3240);
            alias394=alias();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, alias394.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_cond_in_split_branch3242);
            cond395=cond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, cond395.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
                   aliases.add( (alias394!=null?alias394.name:null) );
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "split_branch"


    public static class split_otherwise_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "split_otherwise"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:614:1: split_otherwise : ^( OTHERWISE alias ( ALL )? ) ;
    public final AstValidator.split_otherwise_return split_otherwise() throws RecognitionException {
        AstValidator.split_otherwise_return retval = new AstValidator.split_otherwise_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree OTHERWISE396=null;
        CommonTree ALL398=null;
        AstValidator.alias_return alias397 =null;


        CommonTree OTHERWISE396_tree=null;
        CommonTree ALL398_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:614:17: ( ^( OTHERWISE alias ( ALL )? ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:614:19: ^( OTHERWISE alias ( ALL )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            OTHERWISE396=(CommonTree)match(input,OTHERWISE,FOLLOW_OTHERWISE_in_split_otherwise3260); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OTHERWISE396_tree = (CommonTree)adaptor.dupNode(OTHERWISE396);


            root_1 = (CommonTree)adaptor.becomeRoot(OTHERWISE396_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_alias_in_split_otherwise3262);
            alias397=alias();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, alias397.getTree());


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:614:38: ( ALL )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==ALL) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:614:38: ALL
                    {
                    _last = (CommonTree)input.LT(1);
                    ALL398=(CommonTree)match(input,ALL,FOLLOW_ALL_in_split_otherwise3264); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALL398_tree = (CommonTree)adaptor.dupNode(ALL398);


                    adaptor.addChild(root_1, ALL398_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
                   aliases.add( (alias397!=null?alias397.name:null) );
               }

            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "split_otherwise"


    public static class col_ref_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "col_ref"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:620:1: col_ref : ( alias_col_ref | dollar_col_ref );
    public final AstValidator.col_ref_return col_ref() throws RecognitionException {
        AstValidator.col_ref_return retval = new AstValidator.col_ref_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.alias_col_ref_return alias_col_ref399 =null;

        AstValidator.dollar_col_ref_return dollar_col_ref400 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:620:9: ( alias_col_ref | dollar_col_ref )
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==CUBE||LA114_0==GROUP||LA114_0==IDENTIFIER) ) {
                alt114=1;
            }
            else if ( (LA114_0==DOLLARVAR) ) {
                alt114=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;

            }
            switch (alt114) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:620:11: alias_col_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_alias_col_ref_in_col_ref3281);
                    alias_col_ref399=alias_col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, alias_col_ref399.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:620:27: dollar_col_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_dollar_col_ref_in_col_ref3285);
                    dollar_col_ref400=dollar_col_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, dollar_col_ref400.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "col_ref"


    public static class alias_col_ref_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alias_col_ref"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:623:1: alias_col_ref : ( GROUP | CUBE | IDENTIFIER );
    public final AstValidator.alias_col_ref_return alias_col_ref() throws RecognitionException {
        AstValidator.alias_col_ref_return retval = new AstValidator.alias_col_ref_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set401=null;

        CommonTree set401_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:623:15: ( GROUP | CUBE | IDENTIFIER )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set401=(CommonTree)input.LT(1);

            if ( input.LA(1)==CUBE||input.LA(1)==GROUP||input.LA(1)==IDENTIFIER ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set401_tree = (CommonTree)adaptor.dupNode(set401);


                adaptor.addChild(root_0, set401_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alias_col_ref"


    public static class dollar_col_ref_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dollar_col_ref"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:626:1: dollar_col_ref : DOLLARVAR ;
    public final AstValidator.dollar_col_ref_return dollar_col_ref() throws RecognitionException {
        AstValidator.dollar_col_ref_return retval = new AstValidator.dollar_col_ref_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DOLLARVAR402=null;

        CommonTree DOLLARVAR402_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:626:16: ( DOLLARVAR )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:626:18: DOLLARVAR
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            DOLLARVAR402=(CommonTree)match(input,DOLLARVAR,FOLLOW_DOLLARVAR_in_dollar_col_ref3311); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOLLARVAR402_tree = (CommonTree)adaptor.dupNode(DOLLARVAR402);


            adaptor.addChild(root_0, DOLLARVAR402_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "dollar_col_ref"


    public static class const_expr_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "const_expr"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:629:1: const_expr : literal ;
    public final AstValidator.const_expr_return const_expr() throws RecognitionException {
        AstValidator.const_expr_return retval = new AstValidator.const_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.literal_return literal403 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:629:12: ( literal )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:629:14: literal
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_literal_in_const_expr3320);
            literal403=literal();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_0, literal403.getTree());


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "const_expr"


    public static class literal_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literal"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:1: literal : ( scalar | map | bag | tuple );
    public final AstValidator.literal_return literal() throws RecognitionException {
        AstValidator.literal_return retval = new AstValidator.literal_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        AstValidator.scalar_return scalar404 =null;

        AstValidator.map_return map405 =null;

        AstValidator.bag_return bag406 =null;

        AstValidator.tuple_return tuple407 =null;



        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:9: ( scalar | map | bag | tuple )
            int alt115=4;
            switch ( input.LA(1) ) {
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case DOUBLENUMBER:
            case FALSE:
            case FLOATNUMBER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
            case NULL:
            case QUOTEDSTRING:
            case TRUE:
                {
                alt115=1;
                }
                break;
            case MAP_VAL:
                {
                alt115=2;
                }
                break;
            case BAG_VAL:
                {
                alt115=3;
                }
                break;
            case TUPLE_VAL:
                {
                alt115=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;

            }

            switch (alt115) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:11: scalar
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_scalar_in_literal3329);
                    scalar404=scalar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, scalar404.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:20: map
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_map_in_literal3333);
                    map405=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, map405.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:26: bag
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_bag_in_literal3337);
                    bag406=bag();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, bag406.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:632:32: tuple
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_tuple_in_literal3341);
                    tuple407=tuple();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, tuple407.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "literal"


    public static class scalar_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "scalar"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:1: scalar : ( num_scalar | QUOTEDSTRING | NULL | TRUE | FALSE );
    public final AstValidator.scalar_return scalar() throws RecognitionException {
        AstValidator.scalar_return retval = new AstValidator.scalar_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING409=null;
        CommonTree NULL410=null;
        CommonTree TRUE411=null;
        CommonTree FALSE412=null;
        AstValidator.num_scalar_return num_scalar408 =null;


        CommonTree QUOTEDSTRING409_tree=null;
        CommonTree NULL410_tree=null;
        CommonTree TRUE411_tree=null;
        CommonTree FALSE412_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:8: ( num_scalar | QUOTEDSTRING | NULL | TRUE | FALSE )
            int alt116=5;
            switch ( input.LA(1) ) {
            case BIGDECIMALNUMBER:
            case BIGINTEGERNUMBER:
            case DOUBLENUMBER:
            case FLOATNUMBER:
            case INTEGER:
            case LONGINTEGER:
            case MINUS:
                {
                alt116=1;
                }
                break;
            case QUOTEDSTRING:
                {
                alt116=2;
                }
                break;
            case NULL:
                {
                alt116=3;
                }
                break;
            case TRUE:
                {
                alt116=4;
                }
                break;
            case FALSE:
                {
                alt116=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;

            }

            switch (alt116) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:10: num_scalar
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_num_scalar_in_scalar3350);
                    num_scalar408=num_scalar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, num_scalar408.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:23: QUOTEDSTRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    QUOTEDSTRING409=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_scalar3354); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QUOTEDSTRING409_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING409);


                    adaptor.addChild(root_0, QUOTEDSTRING409_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:38: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    NULL410=(CommonTree)match(input,NULL,FOLLOW_NULL_in_scalar3358); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL410_tree = (CommonTree)adaptor.dupNode(NULL410);


                    adaptor.addChild(root_0, NULL410_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:45: TRUE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TRUE411=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_scalar3362); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE411_tree = (CommonTree)adaptor.dupNode(TRUE411);


                    adaptor.addChild(root_0, TRUE411_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:635:52: FALSE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FALSE412=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_scalar3366); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE412_tree = (CommonTree)adaptor.dupNode(FALSE412);


                    adaptor.addChild(root_0, FALSE412_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "scalar"


    public static class num_scalar_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "num_scalar"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:638:1: num_scalar : ( MINUS )? ( INTEGER | LONGINTEGER | FLOATNUMBER | DOUBLENUMBER | BIGINTEGERNUMBER | BIGDECIMALNUMBER ) ;
    public final AstValidator.num_scalar_return num_scalar() throws RecognitionException {
        AstValidator.num_scalar_return retval = new AstValidator.num_scalar_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree MINUS413=null;
        CommonTree set414=null;

        CommonTree MINUS413_tree=null;
        CommonTree set414_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:638:12: ( ( MINUS )? ( INTEGER | LONGINTEGER | FLOATNUMBER | DOUBLENUMBER | BIGINTEGERNUMBER | BIGDECIMALNUMBER ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:638:14: ( MINUS )? ( INTEGER | LONGINTEGER | FLOATNUMBER | DOUBLENUMBER | BIGINTEGERNUMBER | BIGDECIMALNUMBER )
            {
            root_0 = (CommonTree)adaptor.nil();


            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:638:14: ( MINUS )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==MINUS) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:638:14: MINUS
                    {
                    _last = (CommonTree)input.LT(1);
                    MINUS413=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_num_scalar3375); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS413_tree = (CommonTree)adaptor.dupNode(MINUS413);


                    adaptor.addChild(root_0, MINUS413_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            set414=(CommonTree)input.LT(1);

            if ( input.LA(1)==BIGDECIMALNUMBER||input.LA(1)==BIGINTEGERNUMBER||input.LA(1)==DOUBLENUMBER||input.LA(1)==FLOATNUMBER||input.LA(1)==INTEGER||input.LA(1)==LONGINTEGER ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set414_tree = (CommonTree)adaptor.dupNode(set414);


                adaptor.addChild(root_0, set414_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "num_scalar"


    public static class map_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "map"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:641:1: map : ^( MAP_VAL ( keyvalue )* ) ;
    public final AstValidator.map_return map() throws RecognitionException {
        AstValidator.map_return retval = new AstValidator.map_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree MAP_VAL415=null;
        AstValidator.keyvalue_return keyvalue416 =null;


        CommonTree MAP_VAL415_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:641:5: ( ^( MAP_VAL ( keyvalue )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:641:7: ^( MAP_VAL ( keyvalue )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            MAP_VAL415=(CommonTree)match(input,MAP_VAL,FOLLOW_MAP_VAL_in_map3413); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MAP_VAL415_tree = (CommonTree)adaptor.dupNode(MAP_VAL415);


            root_1 = (CommonTree)adaptor.becomeRoot(MAP_VAL415_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:641:18: ( keyvalue )*
                loop118:
                do {
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==KEY_VAL_PAIR) ) {
                        alt118=1;
                    }


                    switch (alt118) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:641:18: keyvalue
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_keyvalue_in_map3415);
                	    keyvalue416=keyvalue();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, keyvalue416.getTree());


                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop118;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "map"


    public static class keyvalue_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "keyvalue"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:644:1: keyvalue : ^( KEY_VAL_PAIR map_key const_expr ) ;
    public final AstValidator.keyvalue_return keyvalue() throws RecognitionException {
        AstValidator.keyvalue_return retval = new AstValidator.keyvalue_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree KEY_VAL_PAIR417=null;
        AstValidator.map_key_return map_key418 =null;

        AstValidator.const_expr_return const_expr419 =null;


        CommonTree KEY_VAL_PAIR417_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:644:10: ( ^( KEY_VAL_PAIR map_key const_expr ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:644:12: ^( KEY_VAL_PAIR map_key const_expr )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            KEY_VAL_PAIR417=(CommonTree)match(input,KEY_VAL_PAIR,FOLLOW_KEY_VAL_PAIR_in_keyvalue3429); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            KEY_VAL_PAIR417_tree = (CommonTree)adaptor.dupNode(KEY_VAL_PAIR417);


            root_1 = (CommonTree)adaptor.becomeRoot(KEY_VAL_PAIR417_tree, root_1);
            }


            match(input, Token.DOWN, null); if (state.failed) return retval;
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_map_key_in_keyvalue3431);
            map_key418=map_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, map_key418.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_const_expr_in_keyvalue3433);
            const_expr419=const_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) 
            adaptor.addChild(root_1, const_expr419.getTree());


            match(input, Token.UP, null); if (state.failed) return retval;
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "keyvalue"


    public static class map_key_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "map_key"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:647:1: map_key : QUOTEDSTRING ;
    public final AstValidator.map_key_return map_key() throws RecognitionException {
        AstValidator.map_key_return retval = new AstValidator.map_key_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree QUOTEDSTRING420=null;

        CommonTree QUOTEDSTRING420_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:647:9: ( QUOTEDSTRING )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:647:11: QUOTEDSTRING
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            QUOTEDSTRING420=(CommonTree)match(input,QUOTEDSTRING,FOLLOW_QUOTEDSTRING_in_map_key3444); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            QUOTEDSTRING420_tree = (CommonTree)adaptor.dupNode(QUOTEDSTRING420);


            adaptor.addChild(root_0, QUOTEDSTRING420_tree);
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "map_key"


    public static class bag_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bag"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:650:1: bag : ^( BAG_VAL ( tuple )* ) ;
    public final AstValidator.bag_return bag() throws RecognitionException {
        AstValidator.bag_return retval = new AstValidator.bag_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BAG_VAL421=null;
        AstValidator.tuple_return tuple422 =null;


        CommonTree BAG_VAL421_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:650:5: ( ^( BAG_VAL ( tuple )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:650:7: ^( BAG_VAL ( tuple )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            BAG_VAL421=(CommonTree)match(input,BAG_VAL,FOLLOW_BAG_VAL_in_bag3455); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BAG_VAL421_tree = (CommonTree)adaptor.dupNode(BAG_VAL421);


            root_1 = (CommonTree)adaptor.becomeRoot(BAG_VAL421_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:650:18: ( tuple )*
                loop119:
                do {
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==TUPLE_VAL) ) {
                        alt119=1;
                    }


                    switch (alt119) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:650:18: tuple
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_tuple_in_bag3457);
                	    tuple422=tuple();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, tuple422.getTree());


                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop119;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bag"


    public static class tuple_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tuple"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:653:1: tuple : ^( TUPLE_VAL ( literal )* ) ;
    public final AstValidator.tuple_return tuple() throws RecognitionException {
        AstValidator.tuple_return retval = new AstValidator.tuple_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree TUPLE_VAL423=null;
        AstValidator.literal_return literal424 =null;


        CommonTree TUPLE_VAL423_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:653:7: ( ^( TUPLE_VAL ( literal )* ) )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:653:9: ^( TUPLE_VAL ( literal )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            TUPLE_VAL423=(CommonTree)match(input,TUPLE_VAL,FOLLOW_TUPLE_VAL_in_tuple3471); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TUPLE_VAL423_tree = (CommonTree)adaptor.dupNode(TUPLE_VAL423);


            root_1 = (CommonTree)adaptor.becomeRoot(TUPLE_VAL423_tree, root_1);
            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:653:22: ( literal )*
                loop120:
                do {
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==BIGDECIMALNUMBER||LA120_0==BIGINTEGERNUMBER||LA120_0==DOUBLENUMBER||LA120_0==FALSE||LA120_0==FLOATNUMBER||LA120_0==INTEGER||LA120_0==LONGINTEGER||LA120_0==MINUS||LA120_0==NULL||LA120_0==QUOTEDSTRING||LA120_0==TRUE||LA120_0==BAG_VAL||LA120_0==MAP_VAL||LA120_0==TUPLE_VAL) ) {
                        alt120=1;
                    }


                    switch (alt120) {
                	case 1 :
                	    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:653:22: literal
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_literal_in_tuple3473);
                	    literal424=literal();

                	    state._fsp--;
                	    if (state.failed) return retval;
                	    if ( state.backtracking==0 ) 
                	    adaptor.addChild(root_1, literal424.getTree());


                	    if ( state.backtracking==0 ) {
                	    }
                	    }
                	    break;

                	default :
                	    break loop120;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            if ( state.backtracking==0 ) {
            }
            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "tuple"


    public static class eid_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "eid"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:657:1: eid : ( rel_str_op | IMPORT | RETURNS | DEFINE | LOAD | FILTER | FOREACH | CUBE | ROLLUP | MATCHES | ORDER | RANK | DISTINCT | COGROUP | JOIN | CROSS | UNION | SPLIT | INTO | IF | ALL | AS | BY | USING | INNER | OUTER | PARALLEL | PARTITION | GROUP | AND | OR | NOT | GENERATE | FLATTEN | EVAL | ASC | DESC | BOOLEAN | INT | LONG | FLOAT | DOUBLE | BIGINTEGER | BIGDECIMAL | DATETIME | CHARARRAY | BYTEARRAY | BAG | TUPLE | MAP | IS | NULL | TRUE | FALSE | STREAM | THROUGH | STORE | MAPREDUCE | SHIP | CACHE | INPUT | OUTPUT | STDERROR | STDIN | STDOUT | LIMIT | SAMPLE | LEFT | RIGHT | FULL | IDENTIFIER | TOBAG | TOMAP | TOTUPLE | ASSERT );
    public final AstValidator.eid_return eid() throws RecognitionException {
        AstValidator.eid_return retval = new AstValidator.eid_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IMPORT426=null;
        CommonTree RETURNS427=null;
        CommonTree DEFINE428=null;
        CommonTree LOAD429=null;
        CommonTree FILTER430=null;
        CommonTree FOREACH431=null;
        CommonTree CUBE432=null;
        CommonTree ROLLUP433=null;
        CommonTree MATCHES434=null;
        CommonTree ORDER435=null;
        CommonTree RANK436=null;
        CommonTree DISTINCT437=null;
        CommonTree COGROUP438=null;
        CommonTree JOIN439=null;
        CommonTree CROSS440=null;
        CommonTree UNION441=null;
        CommonTree SPLIT442=null;
        CommonTree INTO443=null;
        CommonTree IF444=null;
        CommonTree ALL445=null;
        CommonTree AS446=null;
        CommonTree BY447=null;
        CommonTree USING448=null;
        CommonTree INNER449=null;
        CommonTree OUTER450=null;
        CommonTree PARALLEL451=null;
        CommonTree PARTITION452=null;
        CommonTree GROUP453=null;
        CommonTree AND454=null;
        CommonTree OR455=null;
        CommonTree NOT456=null;
        CommonTree GENERATE457=null;
        CommonTree FLATTEN458=null;
        CommonTree EVAL459=null;
        CommonTree ASC460=null;
        CommonTree DESC461=null;
        CommonTree BOOLEAN462=null;
        CommonTree INT463=null;
        CommonTree LONG464=null;
        CommonTree FLOAT465=null;
        CommonTree DOUBLE466=null;
        CommonTree BIGINTEGER467=null;
        CommonTree BIGDECIMAL468=null;
        CommonTree DATETIME469=null;
        CommonTree CHARARRAY470=null;
        CommonTree BYTEARRAY471=null;
        CommonTree BAG472=null;
        CommonTree TUPLE473=null;
        CommonTree MAP474=null;
        CommonTree IS475=null;
        CommonTree NULL476=null;
        CommonTree TRUE477=null;
        CommonTree FALSE478=null;
        CommonTree STREAM479=null;
        CommonTree THROUGH480=null;
        CommonTree STORE481=null;
        CommonTree MAPREDUCE482=null;
        CommonTree SHIP483=null;
        CommonTree CACHE484=null;
        CommonTree INPUT485=null;
        CommonTree OUTPUT486=null;
        CommonTree STDERROR487=null;
        CommonTree STDIN488=null;
        CommonTree STDOUT489=null;
        CommonTree LIMIT490=null;
        CommonTree SAMPLE491=null;
        CommonTree LEFT492=null;
        CommonTree RIGHT493=null;
        CommonTree FULL494=null;
        CommonTree IDENTIFIER495=null;
        CommonTree TOBAG496=null;
        CommonTree TOMAP497=null;
        CommonTree TOTUPLE498=null;
        CommonTree ASSERT499=null;
        AstValidator.rel_str_op_return rel_str_op425 =null;


        CommonTree IMPORT426_tree=null;
        CommonTree RETURNS427_tree=null;
        CommonTree DEFINE428_tree=null;
        CommonTree LOAD429_tree=null;
        CommonTree FILTER430_tree=null;
        CommonTree FOREACH431_tree=null;
        CommonTree CUBE432_tree=null;
        CommonTree ROLLUP433_tree=null;
        CommonTree MATCHES434_tree=null;
        CommonTree ORDER435_tree=null;
        CommonTree RANK436_tree=null;
        CommonTree DISTINCT437_tree=null;
        CommonTree COGROUP438_tree=null;
        CommonTree JOIN439_tree=null;
        CommonTree CROSS440_tree=null;
        CommonTree UNION441_tree=null;
        CommonTree SPLIT442_tree=null;
        CommonTree INTO443_tree=null;
        CommonTree IF444_tree=null;
        CommonTree ALL445_tree=null;
        CommonTree AS446_tree=null;
        CommonTree BY447_tree=null;
        CommonTree USING448_tree=null;
        CommonTree INNER449_tree=null;
        CommonTree OUTER450_tree=null;
        CommonTree PARALLEL451_tree=null;
        CommonTree PARTITION452_tree=null;
        CommonTree GROUP453_tree=null;
        CommonTree AND454_tree=null;
        CommonTree OR455_tree=null;
        CommonTree NOT456_tree=null;
        CommonTree GENERATE457_tree=null;
        CommonTree FLATTEN458_tree=null;
        CommonTree EVAL459_tree=null;
        CommonTree ASC460_tree=null;
        CommonTree DESC461_tree=null;
        CommonTree BOOLEAN462_tree=null;
        CommonTree INT463_tree=null;
        CommonTree LONG464_tree=null;
        CommonTree FLOAT465_tree=null;
        CommonTree DOUBLE466_tree=null;
        CommonTree BIGINTEGER467_tree=null;
        CommonTree BIGDECIMAL468_tree=null;
        CommonTree DATETIME469_tree=null;
        CommonTree CHARARRAY470_tree=null;
        CommonTree BYTEARRAY471_tree=null;
        CommonTree BAG472_tree=null;
        CommonTree TUPLE473_tree=null;
        CommonTree MAP474_tree=null;
        CommonTree IS475_tree=null;
        CommonTree NULL476_tree=null;
        CommonTree TRUE477_tree=null;
        CommonTree FALSE478_tree=null;
        CommonTree STREAM479_tree=null;
        CommonTree THROUGH480_tree=null;
        CommonTree STORE481_tree=null;
        CommonTree MAPREDUCE482_tree=null;
        CommonTree SHIP483_tree=null;
        CommonTree CACHE484_tree=null;
        CommonTree INPUT485_tree=null;
        CommonTree OUTPUT486_tree=null;
        CommonTree STDERROR487_tree=null;
        CommonTree STDIN488_tree=null;
        CommonTree STDOUT489_tree=null;
        CommonTree LIMIT490_tree=null;
        CommonTree SAMPLE491_tree=null;
        CommonTree LEFT492_tree=null;
        CommonTree RIGHT493_tree=null;
        CommonTree FULL494_tree=null;
        CommonTree IDENTIFIER495_tree=null;
        CommonTree TOBAG496_tree=null;
        CommonTree TOMAP497_tree=null;
        CommonTree TOTUPLE498_tree=null;
        CommonTree ASSERT499_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:657:5: ( rel_str_op | IMPORT | RETURNS | DEFINE | LOAD | FILTER | FOREACH | CUBE | ROLLUP | MATCHES | ORDER | RANK | DISTINCT | COGROUP | JOIN | CROSS | UNION | SPLIT | INTO | IF | ALL | AS | BY | USING | INNER | OUTER | PARALLEL | PARTITION | GROUP | AND | OR | NOT | GENERATE | FLATTEN | EVAL | ASC | DESC | BOOLEAN | INT | LONG | FLOAT | DOUBLE | BIGINTEGER | BIGDECIMAL | DATETIME | CHARARRAY | BYTEARRAY | BAG | TUPLE | MAP | IS | NULL | TRUE | FALSE | STREAM | THROUGH | STORE | MAPREDUCE | SHIP | CACHE | INPUT | OUTPUT | STDERROR | STDIN | STDOUT | LIMIT | SAMPLE | LEFT | RIGHT | FULL | IDENTIFIER | TOBAG | TOMAP | TOTUPLE | ASSERT )
            int alt121=75;
            switch ( input.LA(1) ) {
            case STR_OP_EQ:
            case STR_OP_GT:
            case STR_OP_GTE:
            case STR_OP_LT:
            case STR_OP_LTE:
            case STR_OP_MATCHES:
            case STR_OP_NE:
                {
                alt121=1;
                }
                break;
            case IMPORT:
                {
                alt121=2;
                }
                break;
            case RETURNS:
                {
                alt121=3;
                }
                break;
            case DEFINE:
                {
                alt121=4;
                }
                break;
            case LOAD:
                {
                alt121=5;
                }
                break;
            case FILTER:
                {
                alt121=6;
                }
                break;
            case FOREACH:
                {
                alt121=7;
                }
                break;
            case CUBE:
                {
                alt121=8;
                }
                break;
            case ROLLUP:
                {
                alt121=9;
                }
                break;
            case MATCHES:
                {
                alt121=10;
                }
                break;
            case ORDER:
                {
                alt121=11;
                }
                break;
            case RANK:
                {
                alt121=12;
                }
                break;
            case DISTINCT:
                {
                alt121=13;
                }
                break;
            case COGROUP:
                {
                alt121=14;
                }
                break;
            case JOIN:
                {
                alt121=15;
                }
                break;
            case CROSS:
                {
                alt121=16;
                }
                break;
            case UNION:
                {
                alt121=17;
                }
                break;
            case SPLIT:
                {
                alt121=18;
                }
                break;
            case INTO:
                {
                alt121=19;
                }
                break;
            case IF:
                {
                alt121=20;
                }
                break;
            case ALL:
                {
                alt121=21;
                }
                break;
            case AS:
                {
                alt121=22;
                }
                break;
            case BY:
                {
                alt121=23;
                }
                break;
            case USING:
                {
                alt121=24;
                }
                break;
            case INNER:
                {
                alt121=25;
                }
                break;
            case OUTER:
                {
                alt121=26;
                }
                break;
            case PARALLEL:
                {
                alt121=27;
                }
                break;
            case PARTITION:
                {
                alt121=28;
                }
                break;
            case GROUP:
                {
                alt121=29;
                }
                break;
            case AND:
                {
                alt121=30;
                }
                break;
            case OR:
                {
                alt121=31;
                }
                break;
            case NOT:
                {
                alt121=32;
                }
                break;
            case GENERATE:
                {
                alt121=33;
                }
                break;
            case FLATTEN:
                {
                alt121=34;
                }
                break;
            case EVAL:
                {
                alt121=35;
                }
                break;
            case ASC:
                {
                alt121=36;
                }
                break;
            case DESC:
                {
                alt121=37;
                }
                break;
            case BOOLEAN:
                {
                alt121=38;
                }
                break;
            case INT:
                {
                alt121=39;
                }
                break;
            case LONG:
                {
                alt121=40;
                }
                break;
            case FLOAT:
                {
                alt121=41;
                }
                break;
            case DOUBLE:
                {
                alt121=42;
                }
                break;
            case BIGINTEGER:
                {
                alt121=43;
                }
                break;
            case BIGDECIMAL:
                {
                alt121=44;
                }
                break;
            case DATETIME:
                {
                alt121=45;
                }
                break;
            case CHARARRAY:
                {
                alt121=46;
                }
                break;
            case BYTEARRAY:
                {
                alt121=47;
                }
                break;
            case BAG:
                {
                alt121=48;
                }
                break;
            case TUPLE:
                {
                alt121=49;
                }
                break;
            case MAP:
                {
                alt121=50;
                }
                break;
            case IS:
                {
                alt121=51;
                }
                break;
            case NULL:
                {
                alt121=52;
                }
                break;
            case TRUE:
                {
                alt121=53;
                }
                break;
            case FALSE:
                {
                alt121=54;
                }
                break;
            case STREAM:
                {
                alt121=55;
                }
                break;
            case THROUGH:
                {
                alt121=56;
                }
                break;
            case STORE:
                {
                alt121=57;
                }
                break;
            case MAPREDUCE:
                {
                alt121=58;
                }
                break;
            case SHIP:
                {
                alt121=59;
                }
                break;
            case CACHE:
                {
                alt121=60;
                }
                break;
            case INPUT:
                {
                alt121=61;
                }
                break;
            case OUTPUT:
                {
                alt121=62;
                }
                break;
            case STDERROR:
                {
                alt121=63;
                }
                break;
            case STDIN:
                {
                alt121=64;
                }
                break;
            case STDOUT:
                {
                alt121=65;
                }
                break;
            case LIMIT:
                {
                alt121=66;
                }
                break;
            case SAMPLE:
                {
                alt121=67;
                }
                break;
            case LEFT:
                {
                alt121=68;
                }
                break;
            case RIGHT:
                {
                alt121=69;
                }
                break;
            case FULL:
                {
                alt121=70;
                }
                break;
            case IDENTIFIER:
                {
                alt121=71;
                }
                break;
            case TOBAG:
                {
                alt121=72;
                }
                break;
            case TOMAP:
                {
                alt121=73;
                }
                break;
            case TOTUPLE:
                {
                alt121=74;
                }
                break;
            case ASSERT:
                {
                alt121=75;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;

            }

            switch (alt121) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:657:7: rel_str_op
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_str_op_in_eid3486);
                    rel_str_op425=rel_str_op();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_str_op425.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:658:7: IMPORT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    IMPORT426=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_eid3494); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IMPORT426_tree = (CommonTree)adaptor.dupNode(IMPORT426);


                    adaptor.addChild(root_0, IMPORT426_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:659:7: RETURNS
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    RETURNS427=(CommonTree)match(input,RETURNS,FOLLOW_RETURNS_in_eid3502); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RETURNS427_tree = (CommonTree)adaptor.dupNode(RETURNS427);


                    adaptor.addChild(root_0, RETURNS427_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:660:7: DEFINE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DEFINE428=(CommonTree)match(input,DEFINE,FOLLOW_DEFINE_in_eid3510); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEFINE428_tree = (CommonTree)adaptor.dupNode(DEFINE428);


                    adaptor.addChild(root_0, DEFINE428_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:661:7: LOAD
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    LOAD429=(CommonTree)match(input,LOAD,FOLLOW_LOAD_in_eid3518); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOAD429_tree = (CommonTree)adaptor.dupNode(LOAD429);


                    adaptor.addChild(root_0, LOAD429_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:662:7: FILTER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FILTER430=(CommonTree)match(input,FILTER,FOLLOW_FILTER_in_eid3526); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FILTER430_tree = (CommonTree)adaptor.dupNode(FILTER430);


                    adaptor.addChild(root_0, FILTER430_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:663:7: FOREACH
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FOREACH431=(CommonTree)match(input,FOREACH,FOLLOW_FOREACH_in_eid3534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FOREACH431_tree = (CommonTree)adaptor.dupNode(FOREACH431);


                    adaptor.addChild(root_0, FOREACH431_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 8 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:664:7: CUBE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    CUBE432=(CommonTree)match(input,CUBE,FOLLOW_CUBE_in_eid3542); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CUBE432_tree = (CommonTree)adaptor.dupNode(CUBE432);


                    adaptor.addChild(root_0, CUBE432_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 9 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:665:7: ROLLUP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    ROLLUP433=(CommonTree)match(input,ROLLUP,FOLLOW_ROLLUP_in_eid3550); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ROLLUP433_tree = (CommonTree)adaptor.dupNode(ROLLUP433);


                    adaptor.addChild(root_0, ROLLUP433_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 10 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:666:7: MATCHES
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    MATCHES434=(CommonTree)match(input,MATCHES,FOLLOW_MATCHES_in_eid3558); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MATCHES434_tree = (CommonTree)adaptor.dupNode(MATCHES434);


                    adaptor.addChild(root_0, MATCHES434_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 11 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:667:7: ORDER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    ORDER435=(CommonTree)match(input,ORDER,FOLLOW_ORDER_in_eid3566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORDER435_tree = (CommonTree)adaptor.dupNode(ORDER435);


                    adaptor.addChild(root_0, ORDER435_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 12 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:668:7: RANK
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    RANK436=(CommonTree)match(input,RANK,FOLLOW_RANK_in_eid3574); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RANK436_tree = (CommonTree)adaptor.dupNode(RANK436);


                    adaptor.addChild(root_0, RANK436_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 13 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:669:7: DISTINCT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DISTINCT437=(CommonTree)match(input,DISTINCT,FOLLOW_DISTINCT_in_eid3582); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DISTINCT437_tree = (CommonTree)adaptor.dupNode(DISTINCT437);


                    adaptor.addChild(root_0, DISTINCT437_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 14 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:670:7: COGROUP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    COGROUP438=(CommonTree)match(input,COGROUP,FOLLOW_COGROUP_in_eid3590); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COGROUP438_tree = (CommonTree)adaptor.dupNode(COGROUP438);


                    adaptor.addChild(root_0, COGROUP438_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 15 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:671:7: JOIN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    JOIN439=(CommonTree)match(input,JOIN,FOLLOW_JOIN_in_eid3598); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    JOIN439_tree = (CommonTree)adaptor.dupNode(JOIN439);


                    adaptor.addChild(root_0, JOIN439_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 16 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:672:7: CROSS
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    CROSS440=(CommonTree)match(input,CROSS,FOLLOW_CROSS_in_eid3606); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CROSS440_tree = (CommonTree)adaptor.dupNode(CROSS440);


                    adaptor.addChild(root_0, CROSS440_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 17 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:673:7: UNION
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    UNION441=(CommonTree)match(input,UNION,FOLLOW_UNION_in_eid3614); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    UNION441_tree = (CommonTree)adaptor.dupNode(UNION441);


                    adaptor.addChild(root_0, UNION441_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 18 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:674:7: SPLIT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    SPLIT442=(CommonTree)match(input,SPLIT,FOLLOW_SPLIT_in_eid3622); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SPLIT442_tree = (CommonTree)adaptor.dupNode(SPLIT442);


                    adaptor.addChild(root_0, SPLIT442_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 19 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:675:7: INTO
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    INTO443=(CommonTree)match(input,INTO,FOLLOW_INTO_in_eid3630); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTO443_tree = (CommonTree)adaptor.dupNode(INTO443);


                    adaptor.addChild(root_0, INTO443_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 20 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:676:7: IF
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    IF444=(CommonTree)match(input,IF,FOLLOW_IF_in_eid3638); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IF444_tree = (CommonTree)adaptor.dupNode(IF444);


                    adaptor.addChild(root_0, IF444_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 21 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:677:7: ALL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    ALL445=(CommonTree)match(input,ALL,FOLLOW_ALL_in_eid3646); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALL445_tree = (CommonTree)adaptor.dupNode(ALL445);


                    adaptor.addChild(root_0, ALL445_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 22 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:678:7: AS
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    AS446=(CommonTree)match(input,AS,FOLLOW_AS_in_eid3654); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS446_tree = (CommonTree)adaptor.dupNode(AS446);


                    adaptor.addChild(root_0, AS446_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 23 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:679:7: BY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BY447=(CommonTree)match(input,BY,FOLLOW_BY_in_eid3662); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BY447_tree = (CommonTree)adaptor.dupNode(BY447);


                    adaptor.addChild(root_0, BY447_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 24 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:680:7: USING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    USING448=(CommonTree)match(input,USING,FOLLOW_USING_in_eid3670); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    USING448_tree = (CommonTree)adaptor.dupNode(USING448);


                    adaptor.addChild(root_0, USING448_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 25 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:681:7: INNER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    INNER449=(CommonTree)match(input,INNER,FOLLOW_INNER_in_eid3678); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INNER449_tree = (CommonTree)adaptor.dupNode(INNER449);


                    adaptor.addChild(root_0, INNER449_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 26 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:682:7: OUTER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    OUTER450=(CommonTree)match(input,OUTER,FOLLOW_OUTER_in_eid3686); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OUTER450_tree = (CommonTree)adaptor.dupNode(OUTER450);


                    adaptor.addChild(root_0, OUTER450_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 27 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:683:7: PARALLEL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    PARALLEL451=(CommonTree)match(input,PARALLEL,FOLLOW_PARALLEL_in_eid3694); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PARALLEL451_tree = (CommonTree)adaptor.dupNode(PARALLEL451);


                    adaptor.addChild(root_0, PARALLEL451_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 28 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:684:7: PARTITION
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    PARTITION452=(CommonTree)match(input,PARTITION,FOLLOW_PARTITION_in_eid3702); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PARTITION452_tree = (CommonTree)adaptor.dupNode(PARTITION452);


                    adaptor.addChild(root_0, PARTITION452_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 29 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:685:7: GROUP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    GROUP453=(CommonTree)match(input,GROUP,FOLLOW_GROUP_in_eid3710); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GROUP453_tree = (CommonTree)adaptor.dupNode(GROUP453);


                    adaptor.addChild(root_0, GROUP453_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 30 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:686:7: AND
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    AND454=(CommonTree)match(input,AND,FOLLOW_AND_in_eid3718); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AND454_tree = (CommonTree)adaptor.dupNode(AND454);


                    adaptor.addChild(root_0, AND454_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 31 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:687:7: OR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    OR455=(CommonTree)match(input,OR,FOLLOW_OR_in_eid3726); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OR455_tree = (CommonTree)adaptor.dupNode(OR455);


                    adaptor.addChild(root_0, OR455_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 32 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:688:7: NOT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    NOT456=(CommonTree)match(input,NOT,FOLLOW_NOT_in_eid3734); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT456_tree = (CommonTree)adaptor.dupNode(NOT456);


                    adaptor.addChild(root_0, NOT456_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 33 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:689:7: GENERATE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    GENERATE457=(CommonTree)match(input,GENERATE,FOLLOW_GENERATE_in_eid3742); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GENERATE457_tree = (CommonTree)adaptor.dupNode(GENERATE457);


                    adaptor.addChild(root_0, GENERATE457_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 34 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:690:7: FLATTEN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FLATTEN458=(CommonTree)match(input,FLATTEN,FOLLOW_FLATTEN_in_eid3750); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLATTEN458_tree = (CommonTree)adaptor.dupNode(FLATTEN458);


                    adaptor.addChild(root_0, FLATTEN458_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 35 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:691:7: EVAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    EVAL459=(CommonTree)match(input,EVAL,FOLLOW_EVAL_in_eid3758); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EVAL459_tree = (CommonTree)adaptor.dupNode(EVAL459);


                    adaptor.addChild(root_0, EVAL459_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 36 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:692:7: ASC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    ASC460=(CommonTree)match(input,ASC,FOLLOW_ASC_in_eid3766); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASC460_tree = (CommonTree)adaptor.dupNode(ASC460);


                    adaptor.addChild(root_0, ASC460_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 37 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:693:7: DESC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DESC461=(CommonTree)match(input,DESC,FOLLOW_DESC_in_eid3774); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DESC461_tree = (CommonTree)adaptor.dupNode(DESC461);


                    adaptor.addChild(root_0, DESC461_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 38 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:694:7: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BOOLEAN462=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_eid3782); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLEAN462_tree = (CommonTree)adaptor.dupNode(BOOLEAN462);


                    adaptor.addChild(root_0, BOOLEAN462_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 39 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:695:7: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    INT463=(CommonTree)match(input,INT,FOLLOW_INT_in_eid3790); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT463_tree = (CommonTree)adaptor.dupNode(INT463);


                    adaptor.addChild(root_0, INT463_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 40 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:696:7: LONG
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    LONG464=(CommonTree)match(input,LONG,FOLLOW_LONG_in_eid3798); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONG464_tree = (CommonTree)adaptor.dupNode(LONG464);


                    adaptor.addChild(root_0, LONG464_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 41 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:697:7: FLOAT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FLOAT465=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_eid3806); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT465_tree = (CommonTree)adaptor.dupNode(FLOAT465);


                    adaptor.addChild(root_0, FLOAT465_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 42 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:698:7: DOUBLE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DOUBLE466=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_eid3814); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLE466_tree = (CommonTree)adaptor.dupNode(DOUBLE466);


                    adaptor.addChild(root_0, DOUBLE466_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 43 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:699:7: BIGINTEGER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BIGINTEGER467=(CommonTree)match(input,BIGINTEGER,FOLLOW_BIGINTEGER_in_eid3822); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BIGINTEGER467_tree = (CommonTree)adaptor.dupNode(BIGINTEGER467);


                    adaptor.addChild(root_0, BIGINTEGER467_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 44 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:700:7: BIGDECIMAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BIGDECIMAL468=(CommonTree)match(input,BIGDECIMAL,FOLLOW_BIGDECIMAL_in_eid3830); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BIGDECIMAL468_tree = (CommonTree)adaptor.dupNode(BIGDECIMAL468);


                    adaptor.addChild(root_0, BIGDECIMAL468_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 45 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:701:7: DATETIME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    DATETIME469=(CommonTree)match(input,DATETIME,FOLLOW_DATETIME_in_eid3838); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DATETIME469_tree = (CommonTree)adaptor.dupNode(DATETIME469);


                    adaptor.addChild(root_0, DATETIME469_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 46 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:702:7: CHARARRAY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    CHARARRAY470=(CommonTree)match(input,CHARARRAY,FOLLOW_CHARARRAY_in_eid3846); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHARARRAY470_tree = (CommonTree)adaptor.dupNode(CHARARRAY470);


                    adaptor.addChild(root_0, CHARARRAY470_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 47 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:703:7: BYTEARRAY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BYTEARRAY471=(CommonTree)match(input,BYTEARRAY,FOLLOW_BYTEARRAY_in_eid3854); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BYTEARRAY471_tree = (CommonTree)adaptor.dupNode(BYTEARRAY471);


                    adaptor.addChild(root_0, BYTEARRAY471_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 48 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:704:7: BAG
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    BAG472=(CommonTree)match(input,BAG,FOLLOW_BAG_in_eid3862); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BAG472_tree = (CommonTree)adaptor.dupNode(BAG472);


                    adaptor.addChild(root_0, BAG472_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 49 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:705:7: TUPLE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TUPLE473=(CommonTree)match(input,TUPLE,FOLLOW_TUPLE_in_eid3870); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TUPLE473_tree = (CommonTree)adaptor.dupNode(TUPLE473);


                    adaptor.addChild(root_0, TUPLE473_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 50 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:706:7: MAP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    MAP474=(CommonTree)match(input,MAP,FOLLOW_MAP_in_eid3878); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MAP474_tree = (CommonTree)adaptor.dupNode(MAP474);


                    adaptor.addChild(root_0, MAP474_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 51 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:707:7: IS
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    IS475=(CommonTree)match(input,IS,FOLLOW_IS_in_eid3886); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IS475_tree = (CommonTree)adaptor.dupNode(IS475);


                    adaptor.addChild(root_0, IS475_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 52 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:708:7: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    NULL476=(CommonTree)match(input,NULL,FOLLOW_NULL_in_eid3894); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL476_tree = (CommonTree)adaptor.dupNode(NULL476);


                    adaptor.addChild(root_0, NULL476_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 53 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:709:7: TRUE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TRUE477=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_eid3902); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE477_tree = (CommonTree)adaptor.dupNode(TRUE477);


                    adaptor.addChild(root_0, TRUE477_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 54 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:710:7: FALSE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FALSE478=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_eid3910); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE478_tree = (CommonTree)adaptor.dupNode(FALSE478);


                    adaptor.addChild(root_0, FALSE478_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 55 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:711:7: STREAM
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STREAM479=(CommonTree)match(input,STREAM,FOLLOW_STREAM_in_eid3918); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STREAM479_tree = (CommonTree)adaptor.dupNode(STREAM479);


                    adaptor.addChild(root_0, STREAM479_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 56 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:712:7: THROUGH
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    THROUGH480=(CommonTree)match(input,THROUGH,FOLLOW_THROUGH_in_eid3926); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    THROUGH480_tree = (CommonTree)adaptor.dupNode(THROUGH480);


                    adaptor.addChild(root_0, THROUGH480_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 57 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:713:7: STORE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STORE481=(CommonTree)match(input,STORE,FOLLOW_STORE_in_eid3934); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STORE481_tree = (CommonTree)adaptor.dupNode(STORE481);


                    adaptor.addChild(root_0, STORE481_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 58 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:714:7: MAPREDUCE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    MAPREDUCE482=(CommonTree)match(input,MAPREDUCE,FOLLOW_MAPREDUCE_in_eid3942); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MAPREDUCE482_tree = (CommonTree)adaptor.dupNode(MAPREDUCE482);


                    adaptor.addChild(root_0, MAPREDUCE482_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 59 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:715:7: SHIP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    SHIP483=(CommonTree)match(input,SHIP,FOLLOW_SHIP_in_eid3950); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SHIP483_tree = (CommonTree)adaptor.dupNode(SHIP483);


                    adaptor.addChild(root_0, SHIP483_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 60 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:716:7: CACHE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    CACHE484=(CommonTree)match(input,CACHE,FOLLOW_CACHE_in_eid3958); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CACHE484_tree = (CommonTree)adaptor.dupNode(CACHE484);


                    adaptor.addChild(root_0, CACHE484_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 61 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:717:7: INPUT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    INPUT485=(CommonTree)match(input,INPUT,FOLLOW_INPUT_in_eid3966); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INPUT485_tree = (CommonTree)adaptor.dupNode(INPUT485);


                    adaptor.addChild(root_0, INPUT485_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 62 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:718:7: OUTPUT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    OUTPUT486=(CommonTree)match(input,OUTPUT,FOLLOW_OUTPUT_in_eid3974); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OUTPUT486_tree = (CommonTree)adaptor.dupNode(OUTPUT486);


                    adaptor.addChild(root_0, OUTPUT486_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 63 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:719:7: STDERROR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STDERROR487=(CommonTree)match(input,STDERROR,FOLLOW_STDERROR_in_eid3982); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STDERROR487_tree = (CommonTree)adaptor.dupNode(STDERROR487);


                    adaptor.addChild(root_0, STDERROR487_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 64 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:720:7: STDIN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STDIN488=(CommonTree)match(input,STDIN,FOLLOW_STDIN_in_eid3990); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STDIN488_tree = (CommonTree)adaptor.dupNode(STDIN488);


                    adaptor.addChild(root_0, STDIN488_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 65 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:721:7: STDOUT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STDOUT489=(CommonTree)match(input,STDOUT,FOLLOW_STDOUT_in_eid3998); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STDOUT489_tree = (CommonTree)adaptor.dupNode(STDOUT489);


                    adaptor.addChild(root_0, STDOUT489_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 66 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:722:7: LIMIT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    LIMIT490=(CommonTree)match(input,LIMIT,FOLLOW_LIMIT_in_eid4006); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LIMIT490_tree = (CommonTree)adaptor.dupNode(LIMIT490);


                    adaptor.addChild(root_0, LIMIT490_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 67 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:723:7: SAMPLE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    SAMPLE491=(CommonTree)match(input,SAMPLE,FOLLOW_SAMPLE_in_eid4014); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SAMPLE491_tree = (CommonTree)adaptor.dupNode(SAMPLE491);


                    adaptor.addChild(root_0, SAMPLE491_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 68 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:724:7: LEFT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    LEFT492=(CommonTree)match(input,LEFT,FOLLOW_LEFT_in_eid4022); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEFT492_tree = (CommonTree)adaptor.dupNode(LEFT492);


                    adaptor.addChild(root_0, LEFT492_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 69 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:725:7: RIGHT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    RIGHT493=(CommonTree)match(input,RIGHT,FOLLOW_RIGHT_in_eid4030); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHT493_tree = (CommonTree)adaptor.dupNode(RIGHT493);


                    adaptor.addChild(root_0, RIGHT493_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 70 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:726:7: FULL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    FULL494=(CommonTree)match(input,FULL,FOLLOW_FULL_in_eid4038); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FULL494_tree = (CommonTree)adaptor.dupNode(FULL494);


                    adaptor.addChild(root_0, FULL494_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 71 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:727:7: IDENTIFIER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER495=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_eid4046); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER495_tree = (CommonTree)adaptor.dupNode(IDENTIFIER495);


                    adaptor.addChild(root_0, IDENTIFIER495_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 72 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:728:7: TOBAG
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TOBAG496=(CommonTree)match(input,TOBAG,FOLLOW_TOBAG_in_eid4054); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TOBAG496_tree = (CommonTree)adaptor.dupNode(TOBAG496);


                    adaptor.addChild(root_0, TOBAG496_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 73 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:729:7: TOMAP
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TOMAP497=(CommonTree)match(input,TOMAP,FOLLOW_TOMAP_in_eid4062); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TOMAP497_tree = (CommonTree)adaptor.dupNode(TOMAP497);


                    adaptor.addChild(root_0, TOMAP497_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 74 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:730:7: TOTUPLE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    TOTUPLE498=(CommonTree)match(input,TOTUPLE,FOLLOW_TOTUPLE_in_eid4070); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TOTUPLE498_tree = (CommonTree)adaptor.dupNode(TOTUPLE498);


                    adaptor.addChild(root_0, TOTUPLE498_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 75 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:731:7: ASSERT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    ASSERT499=(CommonTree)match(input,ASSERT,FOLLOW_ASSERT_in_eid4078); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSERT499_tree = (CommonTree)adaptor.dupNode(ASSERT499);


                    adaptor.addChild(root_0, ASSERT499_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "eid"


    public static class rel_op_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:735:1: rel_op : ( rel_op_eq | rel_op_ne | rel_op_gt | rel_op_gte | rel_op_lt | rel_op_lte | STR_OP_MATCHES );
    public final AstValidator.rel_op_return rel_op() throws RecognitionException {
        AstValidator.rel_op_return retval = new AstValidator.rel_op_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STR_OP_MATCHES506=null;
        AstValidator.rel_op_eq_return rel_op_eq500 =null;

        AstValidator.rel_op_ne_return rel_op_ne501 =null;

        AstValidator.rel_op_gt_return rel_op_gt502 =null;

        AstValidator.rel_op_gte_return rel_op_gte503 =null;

        AstValidator.rel_op_lt_return rel_op_lt504 =null;

        AstValidator.rel_op_lte_return rel_op_lte505 =null;


        CommonTree STR_OP_MATCHES506_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:735:8: ( rel_op_eq | rel_op_ne | rel_op_gt | rel_op_gte | rel_op_lt | rel_op_lte | STR_OP_MATCHES )
            int alt122=7;
            switch ( input.LA(1) ) {
            case NUM_OP_EQ:
            case STR_OP_EQ:
                {
                alt122=1;
                }
                break;
            case NUM_OP_NE:
            case STR_OP_NE:
                {
                alt122=2;
                }
                break;
            case NUM_OP_GT:
            case STR_OP_GT:
                {
                alt122=3;
                }
                break;
            case NUM_OP_GTE:
            case STR_OP_GTE:
                {
                alt122=4;
                }
                break;
            case NUM_OP_LT:
            case STR_OP_LT:
                {
                alt122=5;
                }
                break;
            case NUM_OP_LTE:
            case STR_OP_LTE:
                {
                alt122=6;
                }
                break;
            case STR_OP_MATCHES:
                {
                alt122=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;

            }

            switch (alt122) {
                case 1 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:735:10: rel_op_eq
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_eq_in_rel_op4088);
                    rel_op_eq500=rel_op_eq();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_eq500.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 2 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:736:10: rel_op_ne
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_ne_in_rel_op4099);
                    rel_op_ne501=rel_op_ne();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_ne501.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 3 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:737:10: rel_op_gt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_gt_in_rel_op4110);
                    rel_op_gt502=rel_op_gt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_gt502.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 4 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:738:10: rel_op_gte
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_gte_in_rel_op4121);
                    rel_op_gte503=rel_op_gte();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_gte503.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 5 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:739:10: rel_op_lt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_lt_in_rel_op4132);
                    rel_op_lt504=rel_op_lt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_lt504.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 6 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:740:10: rel_op_lte
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_rel_op_lte_in_rel_op4143);
                    rel_op_lte505=rel_op_lte();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) 
                    adaptor.addChild(root_0, rel_op_lte505.getTree());


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;
                case 7 :
                    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:741:10: STR_OP_MATCHES
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STR_OP_MATCHES506=(CommonTree)match(input,STR_OP_MATCHES,FOLLOW_STR_OP_MATCHES_in_rel_op4154); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STR_OP_MATCHES506_tree = (CommonTree)adaptor.dupNode(STR_OP_MATCHES506);


                    adaptor.addChild(root_0, STR_OP_MATCHES506_tree);
                    }


                    if ( state.backtracking==0 ) {
                    }
                    }
                    break;

            }
            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op"


    public static class rel_op_eq_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_eq"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:744:1: rel_op_eq : ( STR_OP_EQ | NUM_OP_EQ );
    public final AstValidator.rel_op_eq_return rel_op_eq() throws RecognitionException {
        AstValidator.rel_op_eq_return retval = new AstValidator.rel_op_eq_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set507=null;

        CommonTree set507_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:744:11: ( STR_OP_EQ | NUM_OP_EQ )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set507=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_EQ||input.LA(1)==STR_OP_EQ ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set507_tree = (CommonTree)adaptor.dupNode(set507);


                adaptor.addChild(root_0, set507_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_eq"


    public static class rel_op_ne_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_ne"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:747:1: rel_op_ne : ( STR_OP_NE | NUM_OP_NE );
    public final AstValidator.rel_op_ne_return rel_op_ne() throws RecognitionException {
        AstValidator.rel_op_ne_return retval = new AstValidator.rel_op_ne_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set508=null;

        CommonTree set508_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:747:11: ( STR_OP_NE | NUM_OP_NE )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set508=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_NE||input.LA(1)==STR_OP_NE ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set508_tree = (CommonTree)adaptor.dupNode(set508);


                adaptor.addChild(root_0, set508_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_ne"


    public static class rel_op_gt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_gt"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:750:1: rel_op_gt : ( STR_OP_GT | NUM_OP_GT );
    public final AstValidator.rel_op_gt_return rel_op_gt() throws RecognitionException {
        AstValidator.rel_op_gt_return retval = new AstValidator.rel_op_gt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set509=null;

        CommonTree set509_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:750:11: ( STR_OP_GT | NUM_OP_GT )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set509=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_GT||input.LA(1)==STR_OP_GT ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set509_tree = (CommonTree)adaptor.dupNode(set509);


                adaptor.addChild(root_0, set509_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_gt"


    public static class rel_op_gte_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_gte"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:753:1: rel_op_gte : ( STR_OP_GTE | NUM_OP_GTE );
    public final AstValidator.rel_op_gte_return rel_op_gte() throws RecognitionException {
        AstValidator.rel_op_gte_return retval = new AstValidator.rel_op_gte_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set510=null;

        CommonTree set510_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:753:12: ( STR_OP_GTE | NUM_OP_GTE )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set510=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_GTE||input.LA(1)==STR_OP_GTE ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set510_tree = (CommonTree)adaptor.dupNode(set510);


                adaptor.addChild(root_0, set510_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_gte"


    public static class rel_op_lt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_lt"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:756:1: rel_op_lt : ( STR_OP_LT | NUM_OP_LT );
    public final AstValidator.rel_op_lt_return rel_op_lt() throws RecognitionException {
        AstValidator.rel_op_lt_return retval = new AstValidator.rel_op_lt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set511=null;

        CommonTree set511_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:756:11: ( STR_OP_LT | NUM_OP_LT )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set511=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_LT||input.LA(1)==STR_OP_LT ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set511_tree = (CommonTree)adaptor.dupNode(set511);


                adaptor.addChild(root_0, set511_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_lt"


    public static class rel_op_lte_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_op_lte"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:759:1: rel_op_lte : ( STR_OP_LTE | NUM_OP_LTE );
    public final AstValidator.rel_op_lte_return rel_op_lte() throws RecognitionException {
        AstValidator.rel_op_lte_return retval = new AstValidator.rel_op_lte_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set512=null;

        CommonTree set512_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:759:12: ( STR_OP_LTE | NUM_OP_LTE )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set512=(CommonTree)input.LT(1);

            if ( input.LA(1)==NUM_OP_LTE||input.LA(1)==STR_OP_LTE ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set512_tree = (CommonTree)adaptor.dupNode(set512);


                adaptor.addChild(root_0, set512_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_op_lte"


    public static class rel_str_op_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_str_op"
    // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:762:1: rel_str_op : ( STR_OP_EQ | STR_OP_NE | STR_OP_GT | STR_OP_LT | STR_OP_GTE | STR_OP_LTE | STR_OP_MATCHES );
    public final AstValidator.rel_str_op_return rel_str_op() throws RecognitionException {
        AstValidator.rel_str_op_return retval = new AstValidator.rel_str_op_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set513=null;

        CommonTree set513_tree=null;

        try {
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:762:12: ( STR_OP_EQ | STR_OP_NE | STR_OP_GT | STR_OP_LT | STR_OP_GTE | STR_OP_LTE | STR_OP_MATCHES )
            // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set513=(CommonTree)input.LT(1);

            if ( (input.LA(1) >= STR_OP_EQ && input.LA(1) <= STR_OP_NE) ) {
                input.consume();
                if ( state.backtracking==0 ) {
                set513_tree = (CommonTree)adaptor.dupNode(set513);


                adaptor.addChild(root_0, set513_tree);
                }

                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
            } 

            }

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            }

        }

        catch(RecognitionException re) {
            throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_str_op"

    // $ANTLR start synpred109_AstValidator
    public final void synpred109_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:392:8: ( ^( MINUS expr expr ) )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:392:8: ^( MINUS expr expr )
        {
        match(input,MINUS,FOLLOW_MINUS_in_synpred109_AstValidator1843); if (state.failed) return ;

        match(input, Token.DOWN, null); if (state.failed) return ;
        pushFollow(FOLLOW_expr_in_synpred109_AstValidator1845);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr_in_synpred109_AstValidator1847);
        expr();

        state._fsp--;
        if (state.failed) return ;

        match(input, Token.UP, null); if (state.failed) return ;


        }

    }
    // $ANTLR end synpred109_AstValidator

    // $ANTLR start synpred113_AstValidator
    public final void synpred113_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:396:8: ( ^( CAST_EXPR type expr ) )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:396:8: ^( CAST_EXPR type expr )
        {
        match(input,CAST_EXPR,FOLLOW_CAST_EXPR_in_synpred113_AstValidator1911); if (state.failed) return ;

        match(input, Token.DOWN, null); if (state.failed) return ;
        pushFollow(FOLLOW_type_in_synpred113_AstValidator1913);
        type();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr_in_synpred113_AstValidator1915);
        expr();

        state._fsp--;
        if (state.failed) return ;

        match(input, Token.UP, null); if (state.failed) return ;


        }

    }
    // $ANTLR end synpred113_AstValidator

    // $ANTLR start synpred114_AstValidator
    public final void synpred114_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:397:8: ( const_expr )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:397:8: const_expr
        {
        pushFollow(FOLLOW_const_expr_in_synpred114_AstValidator1926);
        const_expr();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred114_AstValidator

    // $ANTLR start synpred117_AstValidator
    public final void synpred117_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:400:8: ( ^( CAST_EXPR type_cast expr ) )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:400:8: ^( CAST_EXPR type_cast expr )
        {
        match(input,CAST_EXPR,FOLLOW_CAST_EXPR_in_synpred117_AstValidator1961); if (state.failed) return ;

        match(input, Token.DOWN, null); if (state.failed) return ;
        pushFollow(FOLLOW_type_cast_in_synpred117_AstValidator1963);
        type_cast();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr_in_synpred117_AstValidator1965);
        expr();

        state._fsp--;
        if (state.failed) return ;

        match(input, Token.UP, null); if (state.failed) return ;


        }

    }
    // $ANTLR end synpred117_AstValidator

    // $ANTLR start synpred140_AstValidator
    public final void synpred140_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:31: ( INTEGER )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:31: INTEGER
        {
        match(input,INTEGER,FOLLOW_INTEGER_in_synpred140_AstValidator2281); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred140_AstValidator

    // $ANTLR start synpred141_AstValidator
    public final void synpred141_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:41: ( LONGINTEGER )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:447:41: LONGINTEGER
        {
        match(input,LONGINTEGER,FOLLOW_LONGINTEGER_in_synpred141_AstValidator2285); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred141_AstValidator

    // $ANTLR start synpred142_AstValidator
    public final void synpred142_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:33: ( DOUBLENUMBER )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:450:33: DOUBLENUMBER
        {
        match(input,DOUBLENUMBER,FOLLOW_DOUBLENUMBER_in_synpred142_AstValidator2310); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred142_AstValidator

    // $ANTLR start synpred172_AstValidator
    public final void synpred172_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:4: ( join_item ( LEFT | RIGHT | FULL ) ( OUTER )? join_item )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:4: join_item ( LEFT | RIGHT | FULL ) ( OUTER )? join_item
        {
        pushFollow(FOLLOW_join_item_in_synpred172_AstValidator2659);
        join_item();

        state._fsp--;
        if (state.failed) return ;

        if ( input.LA(1)==FULL||input.LA(1)==LEFT||input.LA(1)==RIGHT ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:38: ( OUTER )?
        int alt134=2;
        int LA134_0 = input.LA(1);

        if ( (LA134_0==OUTER) ) {
            alt134=1;
        }
        switch (alt134) {
            case 1 :
                // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:504:38: OUTER
                {
                match(input,OUTER,FOLLOW_OUTER_in_synpred172_AstValidator2675); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_join_item_in_synpred172_AstValidator2678);
        join_item();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred172_AstValidator

    // $ANTLR start synpred190_AstValidator
    public final void synpred190_AstValidator_fragment() throws RecognitionException {
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:43: ( INTEGER )
        // /usr/local/pig-0.17.0/src/org/apache/pig/parser/AstValidator.g:583:43: INTEGER
        {
        match(input,INTEGER,FOLLOW_INTEGER_in_synpred190_AstValidator3089); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred190_AstValidator

    // Delegated rules

    public final boolean synpred140_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred140_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred190_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred190_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred113_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred113_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred109_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred109_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred117_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred117_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred114_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred114_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred172_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred172_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred142_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred142_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred141_AstValidator() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred141_AstValidator_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_QUERY_in_query80 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_query82 = new BitSet(new long[]{0x0000000000000408L,0x0020020000000000L,0x0012000000000000L});
    public static final BitSet FOLLOW_general_statement_in_statement94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_split_statement_in_statement108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_realias_statement_in_statement122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_register_statement_in_statement136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assert_statement_in_statement150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_split_clause_in_split_statement159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_realias_clause_in_realias_statement168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGISTER_in_register_statement179 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_register_statement181 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_USING_in_register_statement184 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_register_statement186 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_AS_in_register_statement188 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_register_statement190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_assert_clause_in_assert_statement203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATEMENT_in_general_statement214 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_general_statement218 = new BitSet(new long[]{0x0048400226400400L,0x0C21010010004608L,0x0000000000000080L});
    public static final BitSet FOLLOW_op_clause_in_general_statement225 = new BitSet(new long[]{0x0000000000000008L,0x0000000100000000L});
    public static final BitSet FOLLOW_parallel_clause_in_general_statement227 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REALIAS_in_realias_clause240 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_realias_clause242 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_realias_clause244 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARALLEL_in_parallel_clause261 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_parallel_clause263 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENTIFIER_in_alias278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARROBA_in_previous_rel296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_clause_in_op_clause310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_load_clause_in_op_clause324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_group_clause_in_op_clause338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_store_clause_in_op_clause352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_filter_clause_in_op_clause366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_distinct_clause_in_op_clause380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_limit_clause_in_op_clause394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sample_clause_in_op_clause408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_order_clause_in_op_clause422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rank_clause_in_op_clause436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cross_clause_in_op_clause450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_clause_in_op_clause464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_union_clause_in_op_clause478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stream_clause_in_op_clause492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mr_clause_in_op_clause506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_split_clause_in_op_clause520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_foreach_clause_in_op_clause534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cube_clause_in_op_clause548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assert_clause_in_op_clause562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFINE_in_define_clause573 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_define_clause575 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_cmd_in_define_clause579 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_func_clause_in_define_clause583 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXECCOMMAND_in_cmd604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ship_clause_in_cmd608 = new BitSet(new long[]{0x2000000000080008L,0x0084000080000000L});
    public static final BitSet FOLLOW_cache_clause_in_cmd632 = new BitSet(new long[]{0x2000000000080008L,0x0084000080000000L});
    public static final BitSet FOLLOW_input_clause_in_cmd656 = new BitSet(new long[]{0x2000000000080008L,0x0084000080000000L});
    public static final BitSet FOLLOW_output_clause_in_cmd680 = new BitSet(new long[]{0x2000000000080008L,0x0084000080000000L});
    public static final BitSet FOLLOW_error_clause_in_cmd704 = new BitSet(new long[]{0x2000000000080008L,0x0084000080000000L});
    public static final BitSet FOLLOW_SHIP_in_ship_clause743 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_list_in_ship_clause745 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_path_list757 = new BitSet(new long[]{0x0000000000000002L,0x0000008000000000L});
    public static final BitSet FOLLOW_CACHE_in_cache_clause769 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_list_in_cache_clause771 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INPUT_in_input_clause784 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stream_cmd_in_input_clause786 = new BitSet(new long[]{0x0000000000000008L,0x0300008000000000L});
    public static final BitSet FOLLOW_STDIN_in_stream_cmd800 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_clause_in_stream_cmd802 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STDOUT_in_stream_cmd822 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_clause_in_stream_cmd824 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_stream_cmd844 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_clause_in_stream_cmd846 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OUTPUT_in_output_clause860 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stream_cmd_in_output_clause862 = new BitSet(new long[]{0x0000000000000008L,0x0300008000000000L});
    public static final BitSet FOLLOW_STDERROR_in_error_clause876 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_error_clause881 = new BitSet(new long[]{0x8000000000000008L});
    public static final BitSet FOLLOW_INTEGER_in_error_clause883 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOAD_in_load_clause900 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_filename_in_load_clause902 = new BitSet(new long[]{0x0000000000000108L,0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_func_clause_in_load_clause904 = new BitSet(new long[]{0x0000000000000108L});
    public static final BitSet FOLLOW_as_clause_in_load_clause907 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_filename919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AS_in_as_clause929 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_field_def_list_in_as_clause931 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FIELD_DEF_in_field_def950 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_field_def952 = new BitSet(new long[]{0x4001002008255008L,0x0000000000000800L,0x0100020000002000L});
    public static final BitSet FOLLOW_type_in_field_def956 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FIELD_DEF_WITHOUT_IDENTIFIER_in_field_def966 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_field_def968 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_field_def_in_field_def_list996 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_simple_type_in_type1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tuple_type_in_type1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bag_type_in_type1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_type_in_type1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_simple_type1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_simple_type1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_simple_type1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_simple_type1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_simple_type1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGINTEGER_in_simple_type1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGDECIMAL_in_simple_type1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATETIME_in_simple_type1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARARRAY_in_simple_type1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTEARRAY_in_simple_type1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TUPLE_TYPE_in_tuple_type1141 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_field_def_list_in_tuple_type1143 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BAG_TYPE_in_bag_type1157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_bag_type1159 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_tuple_type_in_bag_type1162 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_TYPE_in_map_type1176 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_map_type1178 = new BitSet(new long[]{0x4001002008255008L,0x0000000000000800L,0x0100020000002000L});
    public static final BitSet FOLLOW_type_in_map_type1181 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_REF_in_func_clause1195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_name_in_func_clause1197 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_in_func_clause1217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_name_in_func_clause1219 = new BitSet(new long[]{0x0000000000000008L,0x0000008000020000L});
    public static final BitSet FOLLOW_func_args_in_func_clause1221 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_eid_in_func_name1233 = new BitSet(new long[]{0x0000000800000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_func_name1237 = new BitSet(new long[]{0x7779D022AE6F5F50L,0xFFA58D03D80C6E1DL,0x18E00000000001F7L});
    public static final BitSet FOLLOW_eid_in_func_name1247 = new BitSet(new long[]{0x0000000800000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_func_args_string_in_func_args1272 = new BitSet(new long[]{0x0000000000000002L,0x0000008000020000L});
    public static final BitSet FOLLOW_CUBE_in_cube_clause1285 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cube_item_in_cube_clause1287 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rel_in_cube_item1299 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_cube_by_clause_in_cube_item1303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BY_in_cube_by_clause1317 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cube_or_rollup_in_cube_by_clause1319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_cube_rollup_list_in_cube_or_rollup1331 = new BitSet(new long[]{0x0000000004000002L,0x0000800000000000L});
    public static final BitSet FOLLOW_set_in_cube_rollup_list1344 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cube_by_expr_list_in_cube_rollup_list1354 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_cube_by_expr_in_cube_by_expr_list1366 = new BitSet(new long[]{0x814410540400A002L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_col_range_in_cube_by_expr1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_cube_by_expr1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_cube_by_expr1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_group_clause1406 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_group_item_in_group_clause1416 = new BitSet(new long[]{0x0148400226400488L,0x0C21018210004608L,0x0000000000000080L});
    public static final BitSet FOLLOW_group_type_in_group_clause1419 = new BitSet(new long[]{0x0000000000000008L,0x0000000200000000L});
    public static final BitSet FOLLOW_partition_clause_in_group_clause1422 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_group_type1434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_in_group_item1444 = new BitSet(new long[]{0x0000000000020010L,0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_join_group_by_clause_in_group_item1448 = new BitSet(new long[]{0x1000000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_ALL_in_group_item1452 = new BitSet(new long[]{0x1000000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_ANY_in_group_item1456 = new BitSet(new long[]{0x1000000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_alias_in_rel1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_previous_rel_in_rel1493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_op_clause_in_rel1503 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_parallel_clause_in_rel1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flatten_clause_in_flatten_generated_item1517 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_col_range_in_flatten_generated_item1521 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_expr_in_flatten_generated_item1525 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_STAR_in_flatten_generated_item1529 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_field_def_list_in_flatten_generated_item1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLATTEN_in_flatten_clause1545 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_flatten_clause1547 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STORE_in_store_clause1560 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_store_clause1562 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_filename_in_store_clause1564 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_func_clause_in_store_clause1566 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSERT_in_assert_clause1580 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_assert_clause1582 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_assert_clause1584 = new BitSet(new long[]{0x0000000000000008L,0x0000008000000000L});
    public static final BitSet FOLLOW_comment_in_assert_clause1586 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_comment1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTER_in_filter_clause1610 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_filter_clause1612 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_filter_clause1614 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_cond1627 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_cond1629 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_cond1631 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_cond1644 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_cond1646 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_cond1648 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_cond1661 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_cond1663 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_cond1676 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_cond1678 = new BitSet(new long[]{0x0000000000000008L,0x0000000000040000L});
    public static final BitSet FOLLOW_NOT_in_cond1680 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rel_op_in_cond1694 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_cond1696 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_cond1698 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_in_eval_in_cond1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_func_eval_in_cond1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_COND_in_cond1729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_cond1731 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IN_in_in_eval1743 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IN_LHS_in_in_eval1749 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_in_eval1751 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IN_RHS_in_in_eval1757 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_in_eval1759 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_EVAL_in_func_eval1776 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_name_in_func_eval1778 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_real_arg_in_func_eval1780 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_INVOKER_FUNC_EVAL_in_func_eval1789 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_name_in_func_eval1791 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_func_eval1793 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_real_arg_in_func_eval1795 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_expr_in_real_arg1807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_real_arg1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_col_range_in_real_arg1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_expr1826 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1828 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1830 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expr1843 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1845 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1847 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STAR_in_expr1860 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1862 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1864 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_expr1877 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1879 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1881 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PERCENT_in_expr1894 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1896 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1898 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CAST_EXPR_in_expr1911 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_expr1913 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1915 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_const_expr_in_expr1926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_expr_in_expr1935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEG_in_expr1946 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1948 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CAST_EXPR_in_expr1961 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_cast_in_expr1963 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_expr1965 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPR_IN_PAREN_in_expr1978 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1980 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_simple_type_in_type_cast1991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_type_in_type_cast1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tuple_type_cast_in_type_cast1999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bag_type_cast_in_type_cast2003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TUPLE_TYPE_CAST_in_tuple_type_cast2014 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_cast_in_tuple_type_cast2016 = new BitSet(new long[]{0x4001002008255008L,0x0000000000000800L,0x0200020000004000L});
    public static final BitSet FOLLOW_BAG_TYPE_CAST_in_bag_type_cast2030 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tuple_type_cast_in_bag_type_cast2032 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_projectable_expr_in_var_expr2044 = new BitSet(new long[]{0x0000000000000002L,0x0000002800000000L});
    public static final BitSet FOLLOW_dot_proj_in_var_expr2048 = new BitSet(new long[]{0x0000000000000002L,0x0000002800000000L});
    public static final BitSet FOLLOW_pound_proj_in_var_expr2052 = new BitSet(new long[]{0x0000000000000002L,0x0000002800000000L});
    public static final BitSet FOLLOW_func_eval_in_projectable_expr2063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_col_ref_in_projectable_expr2067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bin_expr_in_projectable_expr2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case_expr_in_projectable_expr2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case_cond_in_projectable_expr2079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERIOD_in_dot_proj2090 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_col_alias_or_index_in_dot_proj2092 = new BitSet(new long[]{0x0140001004000008L});
    public static final BitSet FOLLOW_col_alias_in_col_alias_or_index2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_col_index_in_col_alias_or_index2108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLARVAR_in_col_index2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COL_RANGE_in_col_range2145 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_col_ref_in_col_range2147 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_DOUBLE_PERIOD_in_col_range2150 = new BitSet(new long[]{0x0140001004000008L});
    public static final BitSet FOLLOW_col_ref_in_col_range2152 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POUND_in_pound_proj2166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_pound_proj2168 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BIN_EXPR_in_bin_expr2189 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_bin_expr2191 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_bin_expr2193 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_bin_expr2195 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CASE_EXPR_in_case_expr2207 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CASE_EXPR_LHS_in_case_expr2213 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_case_expr2215 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CASE_EXPR_RHS_in_case_expr2223 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_case_expr2225 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CASE_COND_in_case_cond2244 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_WHEN_in_case_cond2248 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_case_cond2250 = new BitSet(new long[]{0x0800000000000048L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_THEN_in_case_cond2257 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_case_cond2259 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_LIMIT_in_limit_clause2275 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_limit_clause2277 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_INTEGER_in_limit_clause2281 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LONGINTEGER_in_limit_clause2285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_in_limit_clause2289 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SAMPLE_in_sample_clause2304 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_sample_clause2306 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_DOUBLENUMBER_in_sample_clause2310 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_in_sample_clause2314 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANK_in_rank_clause2329 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_rank_clause2331 = new BitSet(new long[]{0x0000000000020008L});
    public static final BitSet FOLLOW_rank_by_statement_in_rank_clause2335 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BY_in_rank_by_statement2351 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rank_by_clause_in_rank_by_statement2353 = new BitSet(new long[]{0x0000000040000008L});
    public static final BitSet FOLLOW_DENSE_in_rank_by_statement2357 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STAR_in_rank_by_clause2371 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_rank_col_in_rank_by_clause2401 = new BitSet(new long[]{0x0140001004000002L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_col_range_in_rank_col2411 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_col_ref_in_rank_col2433 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_ORDER_in_order_clause2455 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_order_clause2457 = new BitSet(new long[]{0x0140001004000000L,0x0040000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_order_by_clause_in_order_clause2459 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_func_clause_in_order_clause2461 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STAR_in_order_by_clause2473 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_order_col_in_order_by_clause2504 = new BitSet(new long[]{0x0140001004000002L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_col_range_in_order_col2514 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_col_ref_in_order_col2537 = new BitSet(new long[]{0x0000000080000202L});
    public static final BitSet FOLLOW_DISTINCT_in_distinct_clause2559 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_distinct_clause2561 = new BitSet(new long[]{0x0000000000000008L,0x0000000200000000L});
    public static final BitSet FOLLOW_partition_clause_in_distinct_clause2563 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARTITION_in_partition_clause2577 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_func_name_in_partition_clause2579 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CROSS_in_cross_clause2592 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_list_in_cross_clause2594 = new BitSet(new long[]{0x0000000000000008L,0x0000000200000000L});
    public static final BitSet FOLLOW_partition_clause_in_cross_clause2596 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rel_in_rel_list2608 = new BitSet(new long[]{0x0148400226400482L,0x0C21010010004608L,0x0000000000000080L});
    public static final BitSet FOLLOW_JOIN_in_join_clause2630 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_join_sub_clause_in_join_clause2632 = new BitSet(new long[]{0x0000000000000008L,0x0000008200000000L});
    public static final BitSet FOLLOW_join_type_in_join_clause2634 = new BitSet(new long[]{0x0000000000000008L,0x0000000200000000L});
    public static final BitSet FOLLOW_partition_clause_in_join_clause2637 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_join_type2649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_item_in_join_sub_clause2659 = new BitSet(new long[]{0x0010000000000000L,0x0000080000000010L});
    public static final BitSet FOLLOW_set_in_join_sub_clause2661 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_OUTER_in_join_sub_clause2675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_join_item_in_join_sub_clause2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_item_in_join_sub_clause2683 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_JOIN_ITEM_in_join_item2696 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_join_item2698 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_join_group_by_clause_in_join_item2700 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BY_in_join_group_by_clause2727 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_join_group_by_expr_in_join_group_by_clause2731 = new BitSet(new long[]{0x814410540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_col_range_in_join_group_by_expr2747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_join_group_by_expr2752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_join_group_by_expr2756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_union_clause2767 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ONSCHEMA_in_union_clause2769 = new BitSet(new long[]{0x0148400226400480L,0x0C21010010004608L,0x0000000000000080L});
    public static final BitSet FOLLOW_rel_list_in_union_clause2772 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOREACH_in_foreach_clause2785 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_foreach_clause2787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000030000000L});
    public static final BitSet FOLLOW_foreach_plan_in_foreach_clause2789 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOREACH_PLAN_SIMPLE_in_foreach_plan2802 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_generate_clause_in_foreach_plan2804 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOREACH_PLAN_COMPLEX_in_foreach_plan2825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_blk_in_foreach_plan2827 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_nested_command_in_nested_blk2847 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000000L,0x0000300000000000L});
    public static final BitSet FOLLOW_generate_clause_in_nested_blk2850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERATE_in_generate_clause2861 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_flatten_generated_item_in_generate_clause2863 = new BitSet(new long[]{0x814490540400A008L,0x0040009400089000L,0x04000C0283998020L});
    public static final BitSet FOLLOW_NESTED_CMD_in_nested_command2878 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_nested_command2880 = new BitSet(new long[]{0x0008400202000000L,0x0000000010000200L,0x0000400000000000L});
    public static final BitSet FOLLOW_nested_op_in_nested_command2882 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NESTED_CMD_ASSI_in_nested_command2896 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_nested_command2898 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_nested_command2900 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_nested_proj_in_nested_op2916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_filter_in_nested_op2930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_sort_in_nested_op2944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_distinct_in_nested_op2958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_limit_in_nested_op2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_cross_in_nested_op2986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_foreach_in_nested_op3000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NESTED_PROJ_in_nested_proj3011 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_col_ref_in_nested_proj3013 = new BitSet(new long[]{0x0140001004000000L});
    public static final BitSet FOLLOW_col_ref_in_nested_proj3015 = new BitSet(new long[]{0x0140001004000008L});
    public static final BitSet FOLLOW_FILTER_in_nested_filter3030 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_filter3032 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_nested_filter3034 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ORDER_in_nested_sort3047 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_sort3049 = new BitSet(new long[]{0x0140001004000000L,0x0040000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_order_by_clause_in_nested_sort3052 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_func_clause_in_nested_sort3054 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISTINCT_in_nested_distinct3068 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_distinct3070 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LIMIT_in_nested_limit3083 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_limit3085 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_INTEGER_in_nested_limit3089 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_in_nested_limit3093 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CROSS_in_nested_cross3108 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_list_in_nested_cross3110 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOREACH_in_nested_foreach3123 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_foreach3125 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_generate_clause_in_nested_foreach3127 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_col_ref_in_nested_op_input3138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_proj_in_nested_op_input3142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nested_op_input_in_nested_op_input_list3151 = new BitSet(new long[]{0x0140001004000002L,0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_STREAM_in_stream_clause3163 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_stream_clause3165 = new BitSet(new long[]{0x0100080000000000L});
    public static final BitSet FOLLOW_set_in_stream_clause3167 = new BitSet(new long[]{0x0000000000000108L});
    public static final BitSet FOLLOW_as_clause_in_stream_clause3177 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAPREDUCE_in_mr_clause3191 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_mr_clause3193 = new BitSet(new long[]{0x0000000000000000L,0x0400008000000000L});
    public static final BitSet FOLLOW_path_list_in_mr_clause3195 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_store_clause_in_mr_clause3198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_load_clause_in_mr_clause3200 = new BitSet(new long[]{0x0000080000000008L});
    public static final BitSet FOLLOW_EXECCOMMAND_in_mr_clause3202 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SPLIT_in_split_clause3216 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rel_in_split_clause3218 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_split_branch_in_split_clause3220 = new BitSet(new long[]{0x0000000000000008L,0x0000000020000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_split_otherwise_in_split_clause3223 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SPLIT_BRANCH_in_split_branch3238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_split_branch3240 = new BitSet(new long[]{0x0800000000000040L,0xF00000000BFC0000L,0x0000000280040007L});
    public static final BitSet FOLLOW_cond_in_split_branch3242 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OTHERWISE_in_split_otherwise3260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_split_otherwise3262 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_ALL_in_split_otherwise3264 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_alias_col_ref_in_col_ref3281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dollar_col_ref_in_col_ref3285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLARVAR_in_dollar_col_ref3311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_const_expr3320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_scalar_in_literal3329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_literal3333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bag_in_literal3337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tuple_in_literal3341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_num_scalar_in_scalar3350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_scalar3354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_scalar3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_scalar3362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_scalar3366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_num_scalar3375 = new BitSet(new long[]{0x800400400000A000L,0x0000000000001000L});
    public static final BitSet FOLLOW_set_in_num_scalar3378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAP_VAL_in_map3413 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalue_in_map3415 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEY_VAL_PAIR_in_keyvalue3429 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_key_in_keyvalue3431 = new BitSet(new long[]{0x800410400000A000L,0x0000008000089000L,0x0400040000008020L});
    public static final BitSet FOLLOW_const_expr_in_keyvalue3433 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUOTEDSTRING_in_map_key3444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BAG_VAL_in_bag3455 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tuple_in_bag3457 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_TUPLE_VAL_in_tuple3471 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_literal_in_tuple3473 = new BitSet(new long[]{0x800410400000A008L,0x0000008000089000L,0x0400040000008020L});
    public static final BitSet FOLLOW_rel_str_op_in_eid3486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_eid3494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURNS_in_eid3502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFINE_in_eid3510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOAD_in_eid3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTER_in_eid3526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOREACH_in_eid3534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CUBE_in_eid3542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROLLUP_in_eid3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHES_in_eid3558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_eid3566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANK_in_eid3574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DISTINCT_in_eid3582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COGROUP_in_eid3590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_JOIN_in_eid3598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CROSS_in_eid3606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_eid3614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPLIT_in_eid3622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_eid3630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_eid3638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_eid3646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AS_in_eid3654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BY_in_eid3662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USING_in_eid3670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INNER_in_eid3678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OUTER_in_eid3686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARALLEL_in_eid3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTITION_in_eid3702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_eid3710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_eid3718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_eid3726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_eid3734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERATE_in_eid3742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLATTEN_in_eid3750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVAL_in_eid3758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASC_in_eid3766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESC_in_eid3774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_eid3782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_eid3790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_eid3798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_eid3806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_eid3814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGINTEGER_in_eid3822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGDECIMAL_in_eid3830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATETIME_in_eid3838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARARRAY_in_eid3846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTEARRAY_in_eid3854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BAG_in_eid3862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TUPLE_in_eid3870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAP_in_eid3878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_eid3886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_eid3894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_eid3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_eid3910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STREAM_in_eid3918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROUGH_in_eid3926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STORE_in_eid3934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAPREDUCE_in_eid3942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHIP_in_eid3950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CACHE_in_eid3958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_eid3966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OUTPUT_in_eid3974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STDERROR_in_eid3982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STDIN_in_eid3990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STDOUT_in_eid3998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIMIT_in_eid4006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAMPLE_in_eid4014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_in_eid4022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHT_in_eid4030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FULL_in_eid4038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_eid4046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOBAG_in_eid4054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOMAP_in_eid4062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTUPLE_in_eid4070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_eid4078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_eq_in_rel_op4088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_ne_in_rel_op4099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_gt_in_rel_op4110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_gte_in_rel_op4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_lt_in_rel_op4132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rel_op_lte_in_rel_op4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STR_OP_MATCHES_in_rel_op4154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_synpred109_AstValidator1843 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_synpred109_AstValidator1845 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_synpred109_AstValidator1847 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CAST_EXPR_in_synpred113_AstValidator1911 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_synpred113_AstValidator1913 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_synpred113_AstValidator1915 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_const_expr_in_synpred114_AstValidator1926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_EXPR_in_synpred117_AstValidator1961 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_cast_in_synpred117_AstValidator1963 = new BitSet(new long[]{0x814410540400A000L,0x0040009400089000L,0x04000C0282998020L});
    public static final BitSet FOLLOW_expr_in_synpred117_AstValidator1965 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INTEGER_in_synpred140_AstValidator2281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGINTEGER_in_synpred141_AstValidator2285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLENUMBER_in_synpred142_AstValidator2310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_item_in_synpred172_AstValidator2659 = new BitSet(new long[]{0x0010000000000000L,0x0000080000000010L});
    public static final BitSet FOLLOW_set_in_synpred172_AstValidator2661 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_OUTER_in_synpred172_AstValidator2675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_join_item_in_synpred172_AstValidator2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_synpred190_AstValidator3089 = new BitSet(new long[]{0x0000000000000002L});

}