package org.quaere.dsl.parser;
// $ANTLR 3.0.1 /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g 2007-09-09 23:12:32

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.quaere.expressions.*;


import org.antlr.runtime.*;


import org.antlr.runtime.tree.*;

@SuppressWarnings({"all"})
public class QuaereParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INTEGER", "FLOAT", "STRING", "ID", "LETTER", "DIGIT", "EscapeSequence", "HexDigit", "UnicodeEscape", "WS", "'['", "']'", "'?'", "':'", "'||'", "'&&'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'.'", "'('", "')'", "','", "'=>'", "'create'", "'{'", "'='", "'}'", "'from'", "'in'", "'into'", "'where'", "'let'", "'join'", "'on'", "'equals'", "'orderby'", "'ascending'", "'descending'", "'select'", "'group'", "'by'", "'true'", "'false'"
    };
    public static final int INTEGER = 4;
    public static final int UnicodeEscape = 12;
    public static final int LETTER = 8;
    public static final int HexDigit = 11;
    public static final int EscapeSequence = 10;
    public static final int WS = 13;
    public static final int EOF = -1;
    public static final int STRING = 6;
    public static final int FLOAT = 5;
    public static final int DIGIT = 9;
    public static final int ID = 7;

    public QuaereParser(TokenStream input) {
        super(input);
    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() {
        return tokenNames;
    }
    public String getGrammarFileName() {
        return "/Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g";
    }


    public static class quaereExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start quaereExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:21:1: quaereExpression returns [Expression name] : sourceExpression EOF ;
    public final quaereExpression_return quaereExpression() throws RecognitionException {
        quaereExpression_return retval = new quaereExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2 = null;
        expression_return expression1 = null;


        CommonTree EOF2_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:22:2: ( sourceExpression EOF )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:22:4: sourceExpression EOF
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_expression_in_quaereExpression51);
                expression1 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression1.getTree());
                EOF2 = (Token) input.LT(1);
                match(input, EOF, FOLLOW_EOF_in_quaereExpression53);
                retval.value = expression1.value;

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end quaereExpression

    public static class expression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start sourceExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:25:1: sourceExpression returns [Expression name] : expressionItem ( '[' sourceExpression ']' )? ;
    @SuppressWarnings({"RedundantCast"})
    public final expression_return expression() throws RecognitionException {
        expression_return retval = new expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal4 = null;
        Token char_literal6 = null;
        expressionItem_return expressionItem3 = null;

        expression_return expression5 = null;


        CommonTree char_literal4_tree = null;
        CommonTree char_literal6_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:2: ( expressionItem ( '[' sourceExpression ']' )? )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:4: expressionItem ( '[' sourceExpression ']' )?
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_expressionItem_in_expression71);
                expressionItem3 = expressionItem();
                _fsp--;

                adaptor.addChild(root_0, expressionItem3.getTree());
                retval.value = expressionItem3.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:54: ( '[' sourceExpression ']' )?
                int alt1 = 2;
                int LA1_0 = input.LA(1);

                if ((LA1_0 == 14)) {
                    alt1 = 1;
                }
                switch (alt1) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:55: '[' sourceExpression ']'
                    {
                        char_literal4 = (Token) input.LT(1);
                        match(input, 14, FOLLOW_14_in_expression76);
                        char_literal4_tree = (CommonTree) adaptor.create(char_literal4);
                        adaptor.addChild(root_0, char_literal4_tree);

                        pushFollow(FOLLOW_expression_in_expression78);
                        expression5 = expression();
                        _fsp--;

                        adaptor.addChild(root_0, expression5.getTree());
                        char_literal6 = (Token) input.LT(1);
                        match(input, 15, FOLLOW_15_in_expression80);
                        char_literal6_tree = (CommonTree) adaptor.create(char_literal6);
                        adaptor.addChild(root_0, char_literal6_tree);

                        retval.value = new Indexer(expressionItem3.value, retval.value);

                    }
                    break;

                }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sourceExpression

    public static class expressionItem_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start expressionItem
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:29:1: expressionItem returns [Expression name] : left= conditionalExpression ( '?' middle= conditionalExpression ':' right= conditionalExpression )? ;
    public final expressionItem_return expressionItem() throws RecognitionException {
        expressionItem_return retval = new expressionItem_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal7 = null;
        Token char_literal8 = null;
        conditionalExpression_return left = null;

        conditionalExpression_return middle = null;

        conditionalExpression_return right = null;


        CommonTree char_literal7_tree = null;
        CommonTree char_literal8_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:2: (left= conditionalExpression ( '?' middle= conditionalExpression ':' right= conditionalExpression )? )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:4: left= conditionalExpression ( '?' middle= conditionalExpression ':' right= conditionalExpression )?
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_conditionalExpression_in_expressionItem103);
                left = conditionalExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:57: ( '?' middle= conditionalExpression ':' right= conditionalExpression )?
                int alt2 = 2;
                int LA2_0 = input.LA(1);

                if ((LA2_0 == 16)) {
                    alt2 = 1;
                }
                switch (alt2) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:59: '?' middle= conditionalExpression ':' right= conditionalExpression
                    {
                        char_literal7 = (Token) input.LT(1);
                        match(input, 16, FOLLOW_16_in_expressionItem109);
                        char_literal7_tree = (CommonTree) adaptor.create(char_literal7);
                        adaptor.addChild(root_0, char_literal7_tree);

                        pushFollow(FOLLOW_conditionalExpression_in_expressionItem113);
                        middle = conditionalExpression();
                        _fsp--;

                        adaptor.addChild(root_0, middle.getTree());
                        char_literal8 = (Token) input.LT(1);
                        match(input, 17, FOLLOW_17_in_expressionItem115);
                        char_literal8_tree = (CommonTree) adaptor.create(char_literal8);
                        adaptor.addChild(root_0, char_literal8_tree);

                        pushFollow(FOLLOW_conditionalExpression_in_expressionItem119);
                        right = conditionalExpression();
                        _fsp--;

                        adaptor.addChild(root_0, right.getTree());
                        retval.value = new TernaryExpression(left.value, middle.value, right.value);

                    }
                    break;

                }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expressionItem

    public static class conditionalExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start conditionalExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:33:1: conditionalExpression returns [Expression name] : left= booleanAndExpression ( '||' right= booleanAndExpression )* ;
    public final conditionalExpression_return conditionalExpression() throws RecognitionException {
        conditionalExpression_return retval = new conditionalExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal9 = null;
        booleanAndExpression_return left = null;

        booleanAndExpression_return right = null;


        CommonTree string_literal9_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:37:2: (left= booleanAndExpression ( '||' right= booleanAndExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:37:4: left= booleanAndExpression ( '||' right= booleanAndExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_booleanAndExpression_in_conditionalExpression146);
                left = booleanAndExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:37:56: ( '||' right= booleanAndExpression )*
                loop3:
                do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == 18)) {
                        alt3 = 1;
                    }


                    switch (alt3) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:38:4: '||' right= booleanAndExpression
                        {
                            string_literal9 = (Token) input.LT(1);
                            match(input, 18, FOLLOW_18_in_conditionalExpression155);
                            string_literal9_tree = (CommonTree) adaptor.create(string_literal9);
                            adaptor.addChild(root_0, string_literal9_tree);

                            type = BinaryExpression.OperatorType.OR;
                            pushFollow(FOLLOW_booleanAndExpression_in_conditionalExpression165);
                            right = booleanAndExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, retval.value, right.value);

                        }
                        break;

                        default:
                            break loop3;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end conditionalExpression

    public static class booleanAndExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start booleanAndExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:43:1: booleanAndExpression returns [Expression name] : left= equalityExpression ( '&&' right= equalityExpression )* ;
    public final booleanAndExpression_return booleanAndExpression() throws RecognitionException {
        booleanAndExpression_return retval = new booleanAndExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal10 = null;
        equalityExpression_return left = null;

        equalityExpression_return right = null;


        CommonTree string_literal10_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:47:2: (left= equalityExpression ( '&&' right= equalityExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:47:4: left= equalityExpression ( '&&' right= equalityExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_equalityExpression_in_booleanAndExpression199);
                left = equalityExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:47:54: ( '&&' right= equalityExpression )*
                loop4:
                do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if ((LA4_0 == 19)) {
                        alt4 = 1;
                    }


                    switch (alt4) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:48:4: '&&' right= equalityExpression
                        {
                            string_literal10 = (Token) input.LT(1);
                            match(input, 19, FOLLOW_19_in_booleanAndExpression208);
                            string_literal10_tree = (CommonTree) adaptor.create(string_literal10);
                            adaptor.addChild(root_0, string_literal10_tree);

                            type = BinaryExpression.OperatorType.AND;
                            pushFollow(FOLLOW_equalityExpression_in_booleanAndExpression218);
                            right = equalityExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, retval.value, right.value);

                        }
                        break;

                        default:
                            break loop4;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end booleanAndExpression

    public static class equalityExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start equalityExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:53:1: equalityExpression returns [Expression name] : left= relationalExpression ( ( '==' | '!=' ) right= relationalExpression )* ;
    public final equalityExpression_return equalityExpression() throws RecognitionException {
        equalityExpression_return retval = new equalityExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal11 = null;
        Token string_literal12 = null;
        relationalExpression_return left = null;

        relationalExpression_return right = null;


        CommonTree string_literal11_tree = null;
        CommonTree string_literal12_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:57:2: (left= relationalExpression ( ( '==' | '!=' ) right= relationalExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:57:4: left= relationalExpression ( ( '==' | '!=' ) right= relationalExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_relationalExpression_in_equalityExpression250);
                left = relationalExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:57:56: ( ( '==' | '!=' ) right= relationalExpression )*
                loop6:
                do {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == 20)) {
                        alt6 = 1;
                    } else if ((LA6_0 == 21)) {
                        alt6 = 1;
                    }


                    switch (alt6) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:58:4: ( '==' | '!=' ) right= relationalExpression
                        {
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:58:4: ( '==' | '!=' )
                            int alt5 = 2;
                            int LA5_0 = input.LA(1);

                            if ((LA5_0 == 20)) {
                                alt5 = 1;
                            } else if ((LA5_0 == 21)) {
                                alt5 = 2;
                            } else {
                                NoViableAltException nvae =
                                        new NoViableAltException("58:4: ( '==' | '!=' )", 5, 0, input);

                                throw nvae;
                            }
                            switch (alt5) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:58:6: '=='
                                {
                                    string_literal11 = (Token) input.LT(1);
                                    match(input, 20, FOLLOW_20_in_equalityExpression261);
                                    string_literal11_tree = (CommonTree) adaptor.create(string_literal11);
                                    adaptor.addChild(root_0, string_literal11_tree);

                                    type = BinaryExpression.OperatorType.EQUAL;

                                }
                                break;
                                case 2:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:59:6: '!='
                                {
                                    string_literal12 = (Token) input.LT(1);
                                    match(input, 21, FOLLOW_21_in_equalityExpression271);
                                    string_literal12_tree = (CommonTree) adaptor.create(string_literal12);
                                    adaptor.addChild(root_0, string_literal12_tree);

                                    type = BinaryExpression.OperatorType.NOT_EQUAL;

                                }
                                break;

                            }

                            pushFollow(FOLLOW_relationalExpression_in_equalityExpression283);
                            right = relationalExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, retval.value, right.value);

                        }
                        break;

                        default:
                            break loop6;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end equalityExpression

    public static class relationalExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start relationalExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:64:1: relationalExpression returns [Expression name] : left= additiveExpression ( ( '<' | '<=' | '>' | '>=' ) right= additiveExpression )* ;
    public final relationalExpression_return relationalExpression() throws RecognitionException {
        relationalExpression_return retval = new relationalExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal13 = null;
        Token string_literal14 = null;
        Token char_literal15 = null;
        Token string_literal16 = null;
        additiveExpression_return left = null;

        additiveExpression_return right = null;


        CommonTree char_literal13_tree = null;
        CommonTree string_literal14_tree = null;
        CommonTree char_literal15_tree = null;
        CommonTree string_literal16_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:68:2: (left= additiveExpression ( ( '<' | '<=' | '>' | '>=' ) right= additiveExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:68:4: left= additiveExpression ( ( '<' | '<=' | '>' | '>=' ) right= additiveExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_additiveExpression_in_relationalExpression316);
                left = additiveExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:68:54: ( ( '<' | '<=' | '>' | '>=' ) right= additiveExpression )*
                loop8:
                do {
                    int alt8 = 2;
                    switch (input.LA(1)) {
                        case 22: {
                            alt8 = 1;
                        }
                        break;
                        case 23: {
                            alt8 = 1;
                        }
                        break;
                        case 24: {
                            alt8 = 1;
                        }
                        break;
                        case 25: {
                            alt8 = 1;
                        }
                        break;

                    }

                    switch (alt8) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:69:4: ( '<' | '<=' | '>' | '>=' ) right= additiveExpression
                        {
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:69:4: ( '<' | '<=' | '>' | '>=' )
                            int alt7 = 4;
                            switch (input.LA(1)) {
                                case 22: {
                                    alt7 = 1;
                                }
                                break;
                                case 23: {
                                    alt7 = 2;
                                }
                                break;
                                case 24: {
                                    alt7 = 3;
                                }
                                break;
                                case 25: {
                                    alt7 = 4;
                                }
                                break;
                                default:
                                    NoViableAltException nvae =
                                            new NoViableAltException("69:4: ( '<' | '<=' | '>' | '>=' )", 7, 0, input);

                                    throw nvae;
                            }

                            switch (alt7) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:69:6: '<'
                                {
                                    char_literal13 = (Token) input.LT(1);
                                    match(input, 22, FOLLOW_22_in_relationalExpression327);
                                    char_literal13_tree = (CommonTree) adaptor.create(char_literal13);
                                    adaptor.addChild(root_0, char_literal13_tree);

                                    type = BinaryExpression.OperatorType.LESS_THAN;

                                }
                                break;
                                case 2:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:70:6: '<='
                                {
                                    string_literal14 = (Token) input.LT(1);
                                    match(input, 23, FOLLOW_23_in_relationalExpression337);
                                    string_literal14_tree = (CommonTree) adaptor.create(string_literal14);
                                    adaptor.addChild(root_0, string_literal14_tree);

                                    type = BinaryExpression.OperatorType.LESS_THAN_OR_EQUAL;

                                }
                                break;
                                case 3:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:71:6: '>'
                                {
                                    char_literal15 = (Token) input.LT(1);
                                    match(input, 24, FOLLOW_24_in_relationalExpression348);
                                    char_literal15_tree = (CommonTree) adaptor.create(char_literal15);
                                    adaptor.addChild(root_0, char_literal15_tree);

                                    type = BinaryExpression.OperatorType.GREATER_THAN;

                                }
                                break;
                                case 4:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:72:6: '>='
                                {
                                    string_literal16 = (Token) input.LT(1);
                                    match(input, 25, FOLLOW_25_in_relationalExpression358);
                                    string_literal16_tree = (CommonTree) adaptor.create(string_literal16);
                                    adaptor.addChild(root_0, string_literal16_tree);

                                    type = BinaryExpression.OperatorType.GREATER_THAN_OR_EQUAL;

                                }
                                break;

                            }

                            pushFollow(FOLLOW_additiveExpression_in_relationalExpression370);
                            right = additiveExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, retval.value, right.value);

                        }
                        break;

                        default:
                            break loop8;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end relationalExpression

    public static class additiveExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start additiveExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:77:1: additiveExpression returns [Expression name] : left= multiplicativeExpression ( ( '+' | '-' ) right= multiplicativeExpression )* ;
    public final additiveExpression_return additiveExpression() throws RecognitionException {
        additiveExpression_return retval = new additiveExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal17 = null;
        Token char_literal18 = null;
        multiplicativeExpression_return left = null;

        multiplicativeExpression_return right = null;


        CommonTree char_literal17_tree = null;
        CommonTree char_literal18_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:81:2: (left= multiplicativeExpression ( ( '+' | '-' ) right= multiplicativeExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:81:4: left= multiplicativeExpression ( ( '+' | '-' ) right= multiplicativeExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression402);
                left = multiplicativeExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:81:60: ( ( '+' | '-' ) right= multiplicativeExpression )*
                loop10:
                do {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);

                    if ((LA10_0 == 26)) {
                        alt10 = 1;
                    } else if ((LA10_0 == 27)) {
                        alt10 = 1;
                    }


                    switch (alt10) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:82:4: ( '+' | '-' ) right= multiplicativeExpression
                        {
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:82:4: ( '+' | '-' )
                            int alt9 = 2;
                            int LA9_0 = input.LA(1);

                            if ((LA9_0 == 26)) {
                                alt9 = 1;
                            } else if ((LA9_0 == 27)) {
                                alt9 = 2;
                            } else {
                                NoViableAltException nvae =
                                        new NoViableAltException("82:4: ( '+' | '-' )", 9, 0, input);

                                throw nvae;
                            }
                            switch (alt9) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:82:6: '+'
                                {
                                    char_literal17 = (Token) input.LT(1);
                                    match(input, 26, FOLLOW_26_in_additiveExpression413);
                                    char_literal17_tree = (CommonTree) adaptor.create(char_literal17);
                                    adaptor.addChild(root_0, char_literal17_tree);

                                    type = BinaryExpression.OperatorType.ADDITION;

                                }
                                break;
                                case 2:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:83:6: '-'
                                {
                                    char_literal18 = (Token) input.LT(1);
                                    match(input, 27, FOLLOW_27_in_additiveExpression423);
                                    char_literal18_tree = (CommonTree) adaptor.create(char_literal18);
                                    adaptor.addChild(root_0, char_literal18_tree);

                                    type = BinaryExpression.OperatorType.SUBTRACTION;

                                }
                                break;

                            }

                            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression435);
                            right = multiplicativeExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, retval.value, right.value);

                        }
                        break;

                        default:
                            break loop10;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end additiveExpression

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start multiplicativeExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:88:1: multiplicativeExpression returns [Expression name] : left= unaryExpression ( ( '*' | '/' | '%' ) right= unaryExpression )* ;
    public final multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        multiplicativeExpression_return retval = new multiplicativeExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal19 = null;
        Token char_literal20 = null;
        Token char_literal21 = null;
        unaryExpression_return left = null;

        unaryExpression_return right = null;


        CommonTree char_literal19_tree = null;
        CommonTree char_literal20_tree = null;
        CommonTree char_literal21_tree = null;


        BinaryExpression.OperatorType type = BinaryExpression.OperatorType.UNKNOWN;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:92:2: (left= unaryExpression ( ( '*' | '/' | '%' ) right= unaryExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:92:4: left= unaryExpression ( ( '*' | '/' | '%' ) right= unaryExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression467);
                left = unaryExpression();
                _fsp--;

                adaptor.addChild(root_0, left.getTree());
                retval.value = left.value;
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:92:51: ( ( '*' | '/' | '%' ) right= unaryExpression )*
                loop12:
                do {
                    int alt12 = 2;
                    switch (input.LA(1)) {
                        case 28: {
                            alt12 = 1;
                        }
                        break;
                        case 29: {
                            alt12 = 1;
                        }
                        break;
                        case 30: {
                            alt12 = 1;
                        }
                        break;

                    }

                    switch (alt12) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:93:4: ( '*' | '/' | '%' ) right= unaryExpression
                        {
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:93:4: ( '*' | '/' | '%' )
                            int alt11 = 3;
                            switch (input.LA(1)) {
                                case 28: {
                                    alt11 = 1;
                                }
                                break;
                                case 29: {
                                    alt11 = 2;
                                }
                                break;
                                case 30: {
                                    alt11 = 3;
                                }
                                break;
                                default:
                                    NoViableAltException nvae =
                                            new NoViableAltException("93:4: ( '*' | '/' | '%' )", 11, 0, input);

                                    throw nvae;
                            }

                            switch (alt11) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:93:6: '*'
                                {
                                    char_literal19 = (Token) input.LT(1);
                                    match(input, 28, FOLLOW_28_in_multiplicativeExpression478);
                                    char_literal19_tree = (CommonTree) adaptor.create(char_literal19);
                                    adaptor.addChild(root_0, char_literal19_tree);

                                    type = BinaryExpression.OperatorType.MULTIPLICATION;

                                }
                                break;
                                case 2:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:94:6: '/'
                                {
                                    char_literal20 = (Token) input.LT(1);
                                    match(input, 29, FOLLOW_29_in_multiplicativeExpression488);
                                    char_literal20_tree = (CommonTree) adaptor.create(char_literal20);
                                    adaptor.addChild(root_0, char_literal20_tree);

                                    type = BinaryExpression.OperatorType.DIVISION;

                                }
                                break;
                                case 3:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:95:6: '%'
                                {
                                    char_literal21 = (Token) input.LT(1);
                                    match(input, 30, FOLLOW_30_in_multiplicativeExpression498);
                                    char_literal21_tree = (CommonTree) adaptor.create(char_literal21);
                                    adaptor.addChild(root_0, char_literal21_tree);

                                    type = BinaryExpression.OperatorType.MODULO;

                                }
                                break;

                            }

                            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression510);
                            right = unaryExpression();
                            _fsp--;

                            adaptor.addChild(root_0, right.getTree());
                            retval.value = new BinaryExpression(type, left.value, right.value);

                        }
                        break;

                        default:
                            break loop12;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end multiplicativeExpression

    public static class unaryExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start unaryExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:101:1: unaryExpression returns [Expression name] : ( statement | '!' statement | '-' statement );
    public final unaryExpression_return unaryExpression() throws RecognitionException {
        unaryExpression_return retval = new unaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal23 = null;
        Token char_literal25 = null;
        statement_return statement22 = null;

        statement_return statement24 = null;

        statement_return statement26 = null;


        CommonTree char_literal23_tree = null;
        CommonTree char_literal25_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:102:2: ( statement | '!' statement | '-' statement )
            int alt13 = 3;
            switch (input.LA(1)) {
                case INTEGER:
                case FLOAT:
                case STRING:
                case ID:
                case 33:
                case 37:
                case 41:
                case 55:
                case 56: {
                    alt13 = 1;
                }
                break;
                case 31: {
                    alt13 = 2;
                }
                break;
                case 27: {
                    alt13 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("101:1: unaryExpression returns [Expression name] : ( statement | '!' statement | '-' statement );", 13, 0, input);

                    throw nvae;
            }

            switch (alt13) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:102:4: statement
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_statement_in_unaryExpression537);
                    statement22 = statement();
                    _fsp--;

                    adaptor.addChild(root_0, statement22.getTree());
                    retval.value = statement22.value;

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:103:8: '!' statement
                {
                    root_0 = (CommonTree) adaptor.nil();

                    char_literal23 = (Token) input.LT(1);
                    match(input, 31, FOLLOW_31_in_unaryExpression548);
                    char_literal23_tree = (CommonTree) adaptor.create(char_literal23);
                    adaptor.addChild(root_0, char_literal23_tree);

                    pushFollow(FOLLOW_statement_in_unaryExpression550);
                    statement24 = statement();
                    _fsp--;

                    adaptor.addChild(root_0, statement24.getTree());
                    retval.value = new UnaryExpression(UnaryExpression.OperatorType.NOT, statement24.value);

                }
                break;
                case 3:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:104:8: '-' statement
                {
                    root_0 = (CommonTree) adaptor.nil();

                    char_literal25 = (Token) input.LT(1);
                    match(input, 27, FOLLOW_27_in_unaryExpression561);
                    char_literal25_tree = (CommonTree) adaptor.create(char_literal25);
                    adaptor.addChild(root_0, char_literal25_tree);

                    pushFollow(FOLLOW_statement_in_unaryExpression563);
                    statement26 = statement();
                    _fsp--;

                    adaptor.addChild(root_0, statement26.getTree());
                    retval.value = new UnaryExpression(UnaryExpression.OperatorType.NEGATE, statement26.value);

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end unaryExpression

    public static class statement_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start statement
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:107:1: statement returns [Statement name] : first= primaryExpression ( '.' follow= primaryExpression )* ;
    public final statement_return statement() throws RecognitionException {
        statement_return retval = new statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal27 = null;
        primaryExpression_return first = null;

        primaryExpression_return follow = null;


        CommonTree char_literal27_tree = null;


        List<Expression> expressions = new ArrayList<Expression>();

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:111:2: (first= primaryExpression ( '.' follow= primaryExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:111:4: first= primaryExpression ( '.' follow= primaryExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_primaryExpression_in_statement589);
                first = primaryExpression();
                _fsp--;

                adaptor.addChild(root_0, first.getTree());
                expressions.add(first.value);
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:111:63: ( '.' follow= primaryExpression )*
                loop14:
                do {
                    int alt14 = 2;
                    int LA14_0 = input.LA(1);

                    if ((LA14_0 == 32)) {
                        alt14 = 1;
                    }


                    switch (alt14) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:111:65: '.' follow= primaryExpression
                        {
                            char_literal27 = (Token) input.LT(1);
                            match(input, 32, FOLLOW_32_in_statement595);
                            char_literal27_tree = (CommonTree) adaptor.create(char_literal27);
                            adaptor.addChild(root_0, char_literal27_tree);

                            pushFollow(FOLLOW_primaryExpression_in_statement599);
                            follow = primaryExpression();
                            _fsp--;

                            adaptor.addChild(root_0, follow.getTree());
                            expressions.add(follow.value);

                        }
                        break;

                        default:
                            break loop14;
                    }
                }
                while (true);

                retval.value = new Statement(expressions);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end statement

    public static class primaryExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start primaryExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:116:1: primaryExpression returns [Expression name] : ( '(' sourceExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );
    public final primaryExpression_return primaryExpression() throws RecognitionException {
        primaryExpression_return retval = new primaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal28 = null;
        Token char_literal30 = null;
        value_return expr = null;

        expression_return expression29 = null;

        newExpression_return newExpression31 = null;

        identifier_return identifier32 = null;

        arguments_return arguments33 = null;

        methodCall_return methodCall34 = null;

        queryExpression_return queryExpression35 = null;


        CommonTree char_literal28_tree = null;
        CommonTree char_literal30_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:117:2: ( '(' sourceExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression )
            int alt16 = 6;
            switch (input.LA(1)) {
                case 33: {
                    alt16 = 1;
                }
                break;
                case INTEGER:
                case FLOAT:
                case STRING:
                case 55:
                case 56: {
                    alt16 = 2;
                }
                break;
                case 37: {
                    alt16 = 3;
                }
                break;
                case ID: {
                    int LA16_4 = input.LA(2);

                    if ((LA16_4 == 33)) {
                        switch (input.LA(3)) {
                            case 33: {
                                switch (input.LA(4)) {
                                    case INTEGER:
                                    case FLOAT:
                                    case STRING:
                                    case 27:
                                    case 31:
                                    case 33:
                                    case 37:
                                    case 41:
                                    case 55:
                                    case 56: {
                                        alt16 = 4;
                                    }
                                    break;
                                    case ID: {
                                        switch (input.LA(5)) {
                                            case 14:
                                            case 16:
                                            case 18:
                                            case 19:
                                            case 20:
                                            case 21:
                                            case 22:
                                            case 23:
                                            case 24:
                                            case 25:
                                            case 26:
                                            case 27:
                                            case 28:
                                            case 29:
                                            case 30:
                                            case 32:
                                            case 33: {
                                                alt16 = 4;
                                            }
                                            break;
                                            case 34: {
                                                int LA16_13 = input.LA(6);

                                                if ((LA16_13 == 36)) {
                                                    alt16 = 5;
                                                } else
                                                if ((LA16_13 == 14 || LA16_13 == 16 || (LA16_13 >= 18 && LA16_13 <= 30) || LA16_13 == 32 || (LA16_13 >= 34 && LA16_13 <= 35))) {
                                                    alt16 = 4;
                                                } else {
                                                    NoViableAltException nvae =
                                                            new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 13, input);

                                                    throw nvae;
                                                }
                                            }
                                            break;
                                            case 35: {
                                                alt16 = 5;
                                            }
                                            break;
                                            default:
                                                NoViableAltException nvae =
                                                        new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 11, input);

                                                throw nvae;
                                        }

                                    }
                                    break;
                                    case 34: {
                                        alt16 = 5;
                                    }
                                    break;
                                    default:
                                        NoViableAltException nvae =
                                                new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 8, input);

                                        throw nvae;
                                }

                            }
                            break;
                            case INTEGER:
                            case FLOAT:
                            case STRING:
                            case 27:
                            case 31:
                            case 34:
                            case 37:
                            case 41:
                            case 55:
                            case 56: {
                                alt16 = 4;
                            }
                            break;
                            case ID: {
                                switch (input.LA(4)) {
                                    case 14:
                                    case 16:
                                    case 18:
                                    case 19:
                                    case 20:
                                    case 21:
                                    case 22:
                                    case 23:
                                    case 24:
                                    case 25:
                                    case 26:
                                    case 27:
                                    case 28:
                                    case 29:
                                    case 30:
                                    case 32:
                                    case 33:
                                    case 34: {
                                        alt16 = 4;
                                    }
                                    break;
                                    case 35: {
                                        int LA16_12 = input.LA(5);

                                        if ((LA16_12 == ID)) {
                                            int LA16_14 = input.LA(6);

                                            if ((LA16_14 == 14 || LA16_14 == 16 || (LA16_14 >= 18 && LA16_14 <= 30) || (LA16_14 >= 32 && LA16_14 <= 35))) {
                                                alt16 = 4;
                                            } else if ((LA16_14 == 36)) {
                                                alt16 = 5;
                                            } else {
                                                NoViableAltException nvae =
                                                        new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 14, input);

                                                throw nvae;
                                            }
                                        } else
                                        if (((LA16_12 >= INTEGER && LA16_12 <= STRING) || LA16_12 == 27 || LA16_12 == 31 || LA16_12 == 33 || LA16_12 == 37 || LA16_12 == 41 || (LA16_12 >= 55 && LA16_12 <= 56))) {
                                            alt16 = 4;
                                        } else {
                                            NoViableAltException nvae =
                                                    new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 12, input);

                                            throw nvae;
                                        }
                                    }
                                    break;
                                    case 36: {
                                        alt16 = 5;
                                    }
                                    break;
                                    default:
                                        NoViableAltException nvae =
                                                new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 9, input);

                                        throw nvae;
                                }

                            }
                            break;
                            case 36: {
                                alt16 = 5;
                            }
                            break;
                            default:
                                NoViableAltException nvae =
                                        new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 6, input);

                                throw nvae;
                        }

                    } else
                    if ((LA16_4 == EOF || (LA16_4 >= 14 && LA16_4 <= 30) || LA16_4 == 32 || (LA16_4 >= 34 && LA16_4 <= 35) || (LA16_4 >= 40 && LA16_4 <= 41) || (LA16_4 >= 43 && LA16_4 <= 53))) {
                        alt16 = 4;
                    } else {
                        NoViableAltException nvae =
                                new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 4, input);

                        throw nvae;
                    }
                }
                break;
                case 41: {
                    alt16 = 6;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("116:1: primaryExpression returns [Expression name] : ( '(' keySelectionExpression ')' | expr= name | newExpression | identifier ( arguments )? | methodCall | queryExpression );", 16, 0, input);

                    throw nvae;
            }

            switch (alt16) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:117:4: '(' sourceExpression ')'
                {
                    root_0 = (CommonTree) adaptor.nil();

                    char_literal28 = (Token) input.LT(1);
                    match(input, 33, FOLLOW_33_in_primaryExpression627);
                    char_literal28_tree = (CommonTree) adaptor.create(char_literal28);
                    adaptor.addChild(root_0, char_literal28_tree);

                    pushFollow(FOLLOW_expression_in_primaryExpression629);
                    expression29 = expression();
                    _fsp--;

                    adaptor.addChild(root_0, expression29.getTree());
                    char_literal30 = (Token) input.LT(1);
                    match(input, 34, FOLLOW_34_in_primaryExpression631);
                    char_literal30_tree = (CommonTree) adaptor.create(char_literal30);
                    adaptor.addChild(root_0, char_literal30_tree);

                    retval.value = expression29.value;

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:118:4: expr= name
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_value_in_primaryExpression641);
                    expr = value();
                    _fsp--;

                    adaptor.addChild(root_0, expr.getTree());
                    retval.value = expr.value;

                }
                break;
                case 3:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:119:4: newExpression
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_newExpression_in_primaryExpression649);
                    newExpression31 = newExpression();
                    _fsp--;

                    adaptor.addChild(root_0, newExpression31.getTree());
                    retval.value = newExpression31.value;

                }
                break;
                case 4:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:120:4: identifier ( arguments )?
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_primaryExpression657);
                    identifier32 = identifier();
                    _fsp--;

                    adaptor.addChild(root_0, identifier32.getTree());
                    retval.value = identifier32.value;
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:120:46: ( arguments )?
                    int alt15 = 2;
                    int LA15_0 = input.LA(1);

                    if ((LA15_0 == 33)) {
                        alt15 = 1;
                    }
                    switch (alt15) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:120:47: arguments
                        {
                            pushFollow(FOLLOW_arguments_in_primaryExpression662);
                            arguments33 = arguments();
                            _fsp--;

                            adaptor.addChild(root_0, arguments33.getTree());
                            retval.value = new MethodCall(identifier32.value, (arguments33.value));

                        }
                        break;

                    }


                }
                break;
                case 5:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:121:4: methodCall
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_methodCall_in_primaryExpression671);
                    methodCall34 = methodCall();
                    _fsp--;

                    adaptor.addChild(root_0, methodCall34.getTree());
                    retval.value = methodCall34.value;

                }
                break;
                case 6:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:122:5: queryExpression
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_queryExpression_in_primaryExpression680);
                    queryExpression35 = queryExpression();
                    _fsp--;

                    adaptor.addChild(root_0, queryExpression35.getTree());
                    retval.value = queryExpression35.value;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end primaryExpression

    public static class value_return extends ParserRuleReturnScope {
        public Constant value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start name
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:125:1: name returns [Constant name] : ( INTEGER | FLOAT | STRING | booleanValue );
    public final value_return value() throws RecognitionException {
        value_return retval = new value_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INTEGER36 = null;
        Token FLOAT37 = null;
        Token STRING38 = null;
        booleanValue_return booleanValue39 = null;


        CommonTree INTEGER36_tree = null;
        CommonTree FLOAT37_tree = null;
        CommonTree STRING38_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:126:2: ( INTEGER | FLOAT | STRING | booleanValue )
            int alt17 = 4;
            switch (input.LA(1)) {
                case INTEGER: {
                    alt17 = 1;
                }
                break;
                case FLOAT: {
                    alt17 = 2;
                }
                break;
                case STRING: {
                    alt17 = 3;
                }
                break;
                case 55:
                case 56: {
                    alt17 = 4;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("125:1: name returns [Constant name] : ( INTEGER | FLOAT | STRING | booleanValue );", 17, 0, input);

                    throw nvae;
            }

            switch (alt17) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:126:5: INTEGER
                {
                    root_0 = (CommonTree) adaptor.nil();

                    INTEGER36 = (Token) input.LT(1);
                    match(input, INTEGER, FOLLOW_INTEGER_in_value699);
                    INTEGER36_tree = (CommonTree) adaptor.create(INTEGER36);
                    adaptor.addChild(root_0, INTEGER36_tree);

                    retval.value = new Constant(Integer.valueOf(INTEGER36.getText()), Integer.class);

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:127:4: FLOAT
                {
                    root_0 = (CommonTree) adaptor.nil();

                    FLOAT37 = (Token) input.LT(1);
                    match(input, FLOAT, FOLLOW_FLOAT_in_value707);
                    FLOAT37_tree = (CommonTree) adaptor.create(FLOAT37);
                    adaptor.addChild(root_0, FLOAT37_tree);

                    retval.value = new Constant(Float.valueOf(FLOAT37.getText()), Integer.class);

                }
                break;
                case 3:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:128:4: STRING
                {
                    root_0 = (CommonTree) adaptor.nil();

                    STRING38 = (Token) input.LT(1);
                    match(input, STRING, FOLLOW_STRING_in_value715);
                    STRING38_tree = (CommonTree) adaptor.create(STRING38);
                    adaptor.addChild(root_0, STRING38_tree);

                    retval.value = new Constant(STRING38.getText().substring(1, STRING38.getText().length() - 2), String.class);

                }
                break;
                case 4:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:129:4: booleanValue
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_booleanValue_in_value723);
                    booleanValue39 = booleanValue();
                    _fsp--;

                    adaptor.addChild(root_0, booleanValue39.getTree());
                    retval.value = new Constant(booleanValue39.value, Boolean.class);

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end name

    public static class methodCall_return extends ParserRuleReturnScope {
        public MethodCall value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start methodCall
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:132:1: methodCall returns [MethodCall name] : (n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= sourceExpression ')' | n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= sourceExpression ')' );
    public final methodCall_return methodCall() throws RecognitionException {
        methodCall_return retval = new methodCall_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal40 = null;
        Token char_literal41 = null;
        Token string_literal42 = null;
        Token char_literal43 = null;
        Token char_literal44 = null;
        Token char_literal45 = null;
        Token char_literal46 = null;
        Token char_literal47 = null;
        Token string_literal48 = null;
        Token char_literal49 = null;
        identifier_return n2 = null;

        identifier_return anon1 = null;

        identifier_return indexer1 = null;

        expression_return lambda1 = null;

        identifier_return n3 = null;

        identifier_return anon2 = null;

        identifier_return indexer2 = null;

        expression_return lambda2 = null;


        CommonTree char_literal40_tree = null;
        CommonTree char_literal41_tree = null;
        CommonTree string_literal42_tree = null;
        CommonTree char_literal43_tree = null;
        CommonTree char_literal44_tree = null;
        CommonTree char_literal45_tree = null;
        CommonTree char_literal46_tree = null;
        CommonTree char_literal47_tree = null;
        CommonTree string_literal48_tree = null;
        CommonTree char_literal49_tree = null;


        List<Expression> parameters = new ArrayList<Expression>();
        Identifier name = null;
        Identifier anonIdentifier = null;
        Identifier indexIdentifier = null;
        Expression lambdaExpression = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:140:2: (n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= sourceExpression ')' | n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= sourceExpression ')' )
            int alt22 = 2;
            int LA22_0 = input.LA(1);

            if ((LA22_0 == ID)) {
                int LA22_1 = input.LA(2);

                if ((LA22_1 == 33)) {
                    int LA22_2 = input.LA(3);

                    if ((LA22_2 == 33)) {
                        alt22 = 2;
                    } else if ((LA22_2 == ID || LA22_2 == 36)) {
                        alt22 = 1;
                    } else {
                        NoViableAltException nvae =
                                new NoViableAltException("132:1: methodCall returns [MethodCall name] : (n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= keySelectionExpression ')' | n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= keySelectionExpression ')' );", 22, 2, input);

                        throw nvae;
                    }
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("132:1: methodCall returns [MethodCall name] : (n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= keySelectionExpression ')' | n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= keySelectionExpression ')' );", 22, 1, input);

                    throw nvae;
                }
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("132:1: methodCall returns [MethodCall name] : (n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= keySelectionExpression ')' | n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= keySelectionExpression ')' );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:141:2: n2= identifier '(' (anon1= identifier ( ',' indexer1= identifier )? )? '=>' lambda1= sourceExpression ')'
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_methodCall750);
                    n2 = identifier();
                    _fsp--;

                    adaptor.addChild(root_0, n2.getTree());
                    name = n2.value;
                    char_literal40 = (Token) input.LT(1);
                    match(input, 33, FOLLOW_33_in_methodCall754);
                    char_literal40_tree = (CommonTree) adaptor.create(char_literal40);
                    adaptor.addChild(root_0, char_literal40_tree);

                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:141:43: (anon1= identifier ( ',' indexer1= identifier )? )?
                    int alt19 = 2;
                    int LA19_0 = input.LA(1);

                    if ((LA19_0 == ID)) {
                        alt19 = 1;
                    }
                    switch (alt19) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:141:44: anon1= identifier ( ',' indexer1= identifier )?
                        {
                            pushFollow(FOLLOW_identifier_in_methodCall760);
                            anon1 = identifier();
                            _fsp--;

                            adaptor.addChild(root_0, anon1.getTree());
                            anonIdentifier = anon1.value;
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:141:96: ( ',' indexer1= identifier )?
                            int alt18 = 2;
                            int LA18_0 = input.LA(1);

                            if ((LA18_0 == 35)) {
                                alt18 = 1;
                            }
                            switch (alt18) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:141:97: ',' indexer1= identifier
                                {
                                    char_literal41 = (Token) input.LT(1);
                                    match(input, 35, FOLLOW_35_in_methodCall765);
                                    char_literal41_tree = (CommonTree) adaptor.create(char_literal41);
                                    adaptor.addChild(root_0, char_literal41_tree);

                                    pushFollow(FOLLOW_identifier_in_methodCall769);
                                    indexer1 = identifier();
                                    _fsp--;

                                    adaptor.addChild(root_0, indexer1.getTree());
                                    indexIdentifier = indexer1.value;

                                }
                                break;

                            }


                        }
                        break;

                    }

                    string_literal42 = (Token) input.LT(1);
                    match(input, 36, FOLLOW_36_in_methodCall780);
                    string_literal42_tree = (CommonTree) adaptor.create(string_literal42);
                    adaptor.addChild(root_0, string_literal42_tree);

                    pushFollow(FOLLOW_expression_in_methodCall784);
                    lambda1 = expression();
                    _fsp--;

                    adaptor.addChild(root_0, lambda1.getTree());
                    lambdaExpression = lambda1.value;
                    char_literal43 = (Token) input.LT(1);
                    match(input, 34, FOLLOW_34_in_methodCall788);
                    char_literal43_tree = (CommonTree) adaptor.create(char_literal43);
                    adaptor.addChild(root_0, char_literal43_tree);

                    retval.value = new MethodCall(name, Arrays.<Expression>asList(), anonIdentifier, indexIdentifier, lambdaExpression);

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:143:4: n3= identifier '(' '(' (anon2= identifier ( ',' indexer2= identifier )? )? ')' '=>' lambda2= sourceExpression ')'
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_methodCall798);
                    n3 = identifier();
                    _fsp--;

                    adaptor.addChild(root_0, n3.getTree());
                    name = n3.value;
                    char_literal44 = (Token) input.LT(1);
                    match(input, 33, FOLLOW_33_in_methodCall802);
                    char_literal44_tree = (CommonTree) adaptor.create(char_literal44);
                    adaptor.addChild(root_0, char_literal44_tree);

                    char_literal45 = (Token) input.LT(1);
                    match(input, 33, FOLLOW_33_in_methodCall804);
                    char_literal45_tree = (CommonTree) adaptor.create(char_literal45);
                    adaptor.addChild(root_0, char_literal45_tree);

                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:143:48: (anon2= identifier ( ',' indexer2= identifier )? )?
                    int alt21 = 2;
                    int LA21_0 = input.LA(1);

                    if ((LA21_0 == ID)) {
                        alt21 = 1;
                    }
                    switch (alt21) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:143:49: anon2= identifier ( ',' indexer2= identifier )?
                        {
                            pushFollow(FOLLOW_identifier_in_methodCall809);
                            anon2 = identifier();
                            _fsp--;

                            adaptor.addChild(root_0, anon2.getTree());
                            anonIdentifier = anon2.value;
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:143:101: ( ',' indexer2= identifier )?
                            int alt20 = 2;
                            int LA20_0 = input.LA(1);

                            if ((LA20_0 == 35)) {
                                alt20 = 1;
                            }
                            switch (alt20) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:143:102: ',' indexer2= identifier
                                {
                                    char_literal46 = (Token) input.LT(1);
                                    match(input, 35, FOLLOW_35_in_methodCall814);
                                    char_literal46_tree = (CommonTree) adaptor.create(char_literal46);
                                    adaptor.addChild(root_0, char_literal46_tree);

                                    pushFollow(FOLLOW_identifier_in_methodCall818);
                                    indexer2 = identifier();
                                    _fsp--;

                                    adaptor.addChild(root_0, indexer2.getTree());
                                    indexIdentifier = indexer2.value;

                                }
                                break;

                            }


                        }
                        break;

                    }

                    char_literal47 = (Token) input.LT(1);
                    match(input, 34, FOLLOW_34_in_methodCall828);
                    char_literal47_tree = (CommonTree) adaptor.create(char_literal47);
                    adaptor.addChild(root_0, char_literal47_tree);

                    string_literal48 = (Token) input.LT(1);
                    match(input, 36, FOLLOW_36_in_methodCall830);
                    string_literal48_tree = (CommonTree) adaptor.create(string_literal48);
                    adaptor.addChild(root_0, string_literal48_tree);

                    pushFollow(FOLLOW_expression_in_methodCall834);
                    lambda2 = expression();
                    _fsp--;

                    adaptor.addChild(root_0, lambda2.getTree());
                    lambdaExpression = lambda2.value;
                    char_literal49 = (Token) input.LT(1);
                    match(input, 34, FOLLOW_34_in_methodCall838);
                    char_literal49_tree = (CommonTree) adaptor.create(char_literal49);
                    adaptor.addChild(root_0, char_literal49_tree);

                    retval.value = new MethodCall(name, Arrays.<Expression>asList(), anonIdentifier, indexIdentifier, lambdaExpression);

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end methodCall

    public static class identifier_return extends ParserRuleReturnScope {
        public Identifier value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start identifier
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:147:1: identifier returns [Identifier name] : ID ;
    public final identifier_return identifier() throws RecognitionException {
        identifier_return retval = new identifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID50 = null;

        CommonTree ID50_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:148:2: ( ID )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:148:5: ID
            {
                root_0 = (CommonTree) adaptor.nil();

                ID50 = (Token) input.LT(1);
                match(input, ID, FOLLOW_ID_in_identifier856);
                ID50_tree = (CommonTree) adaptor.create(ID50);
                adaptor.addChild(root_0, ID50_tree);

                retval.value = new Identifier(ID50.getText());

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end identifier

    public static class newExpression_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start newExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:151:1: newExpression returns [Expression name] : 'create' ( type )? '{' (firstid= identifier '=' )? firstexp= sourceExpression ( ',' (followid= identifier '=' )? followexp= sourceExpression )* '}' ;
    public final newExpression_return newExpression() throws RecognitionException {
        newExpression_return retval = new newExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal51 = null;
        Token char_literal53 = null;
        Token char_literal54 = null;
        Token char_literal55 = null;
        Token char_literal56 = null;
        Token char_literal57 = null;
        identifier_return firstid = null;

        expression_return firstexp = null;

        identifier_return followid = null;

        expression_return followexp = null;

        type_return type52 = null;


        CommonTree string_literal51_tree = null;
        CommonTree char_literal53_tree = null;
        CommonTree char_literal54_tree = null;
        CommonTree char_literal55_tree = null;
        CommonTree char_literal56_tree = null;
        CommonTree char_literal57_tree = null;


        String typ = null;
        Identifier id = null;
        List<Property> parameters = new ArrayList<Property>();

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:2: ( 'create' ( type )? '{' (firstid= identifier '=' )? firstexp= sourceExpression ( ',' (followid= identifier '=' )? followexp= sourceExpression )* '}' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:4: 'create' ( type )? '{' (firstid= identifier '=' )? firstexp= sourceExpression ( ',' (followid= identifier '=' )? followexp= sourceExpression )* '}'
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal51 = (Token) input.LT(1);
                match(input, 37, FOLLOW_37_in_newExpression877);
                string_literal51_tree = (CommonTree) adaptor.create(string_literal51);
                adaptor.addChild(root_0, string_literal51_tree);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:13: ( type )?
                int alt23 = 2;
                int LA23_0 = input.LA(1);

                if ((LA23_0 == ID)) {
                    alt23 = 1;
                }
                switch (alt23) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:14: type
                    {
                        pushFollow(FOLLOW_type_in_newExpression880);
                        type52 = type();
                        _fsp--;

                        adaptor.addChild(root_0, type52.getTree());
                        typ = input.toString(type52.start, type52.stop);
                        id = null;

                    }
                    break;

                }

                char_literal53 = (Token) input.LT(1);
                match(input, 38, FOLLOW_38_in_newExpression887);
                char_literal53_tree = (CommonTree) adaptor.create(char_literal53);
                adaptor.addChild(root_0, char_literal53_tree);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:59: (firstid= identifier '=' )?
                int alt24 = 2;
                int LA24_0 = input.LA(1);

                if ((LA24_0 == ID)) {
                    int LA24_1 = input.LA(2);

                    if ((LA24_1 == 39)) {
                        alt24 = 1;
                    }
                }
                switch (alt24) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:60: firstid= identifier '='
                    {
                        pushFollow(FOLLOW_identifier_in_newExpression892);
                        firstid = identifier();
                        _fsp--;

                        adaptor.addChild(root_0, firstid.getTree());
                        char_literal54 = (Token) input.LT(1);
                        match(input, 39, FOLLOW_39_in_newExpression894);
                        char_literal54_tree = (CommonTree) adaptor.create(char_literal54);
                        adaptor.addChild(root_0, char_literal54_tree);

                        id = firstid.value;

                    }
                    break;

                }

                pushFollow(FOLLOW_expression_in_newExpression903);
                firstexp = expression();
                _fsp--;

                adaptor.addChild(root_0, firstexp.getTree());
                parameters.add(new Property(id, firstexp.value));
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:186: ( ',' (followid= identifier '=' )? followexp= sourceExpression )*
                loop26:
                do {
                    int alt26 = 2;
                    int LA26_0 = input.LA(1);

                    if ((LA26_0 == 35)) {
                        alt26 = 1;
                    }


                    switch (alt26) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:187: ',' (followid= identifier '=' )? followexp= sourceExpression
                        {
                            char_literal55 = (Token) input.LT(1);
                            match(input, 35, FOLLOW_35_in_newExpression908);
                            char_literal55_tree = (CommonTree) adaptor.create(char_literal55);
                            adaptor.addChild(root_0, char_literal55_tree);

                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:191: (followid= identifier '=' )?
                            int alt25 = 2;
                            int LA25_0 = input.LA(1);

                            if ((LA25_0 == ID)) {
                                int LA25_1 = input.LA(2);

                                if ((LA25_1 == 39)) {
                                    alt25 = 1;
                                }
                            }
                            switch (alt25) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:157:192: followid= identifier '='
                                {
                                    pushFollow(FOLLOW_identifier_in_newExpression913);
                                    followid = identifier();
                                    _fsp--;

                                    adaptor.addChild(root_0, followid.getTree());
                                    char_literal56 = (Token) input.LT(1);
                                    match(input, 39, FOLLOW_39_in_newExpression915);
                                    char_literal56_tree = (CommonTree) adaptor.create(char_literal56);
                                    adaptor.addChild(root_0, char_literal56_tree);

                                    id = followid.value;

                                }
                                break;

                            }

                            pushFollow(FOLLOW_expression_in_newExpression923);
                            followexp = expression();
                            _fsp--;

                            adaptor.addChild(root_0, followexp.getTree());
                            parameters.add(new Property(id, followexp.value));

                        }
                        break;

                        default:
                            break loop26;
                    }
                }
                while (true);

                char_literal57 = (Token) input.LT(1);
                match(input, 40, FOLLOW_40_in_newExpression929);
                char_literal57_tree = (CommonTree) adaptor.create(char_literal57);
                adaptor.addChild(root_0, char_literal57_tree);

                try {
                    retval.value = new NewExpression(Class.forName(typ), parameters);
                }
                catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end newExpression

    public static class queryExpression_return extends ParserRuleReturnScope {
        public QueryExpression value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start queryExpression
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:163:1: queryExpression returns [QueryExpression name] : fromClause queryBody ;
    public final queryExpression_return queryExpression() throws RecognitionException {
        queryExpression_return retval = new queryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        fromClause_return fromClause58 = null;

        queryBody_return queryBody59 = null;


        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:164:2: ( fromClause queryBody )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:164:5: fromClause queryBody
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_fromClause_in_queryExpression956);
                fromClause58 = fromClause();
                _fsp--;

                adaptor.addChild(root_0, fromClause58.getTree());
                pushFollow(FOLLOW_queryBody_in_queryExpression958);
                queryBody59 = queryBody();
                _fsp--;

                adaptor.addChild(root_0, queryBody59.getTree());
                retval.value = new QueryExpression(fromClause58.value, queryBody59.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end queryExpression

    public static class fromClause_return extends ParserRuleReturnScope {
        public FromClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start fromClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:167:1: fromClause returns [FromClause name] : 'from' ( type )? identifier 'in' sourceExpression ;
    public final fromClause_return fromClause() throws RecognitionException {
        fromClause_return retval = new fromClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal60 = null;
        Token string_literal63 = null;
        type_return type61 = null;

        identifier_return identifier62 = null;

        expression_return expression64 = null;


        CommonTree string_literal60_tree = null;
        CommonTree string_literal63_tree = null;


        String t = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:171:2: ( 'from' ( type )? identifier 'in' sourceExpression )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:171:4: 'from' ( type )? identifier 'in' sourceExpression
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal60 = (Token) input.LT(1);
                match(input, 41, FOLLOW_41_in_fromClause981);
                string_literal60_tree = (CommonTree) adaptor.create(string_literal60);
                adaptor.addChild(root_0, string_literal60_tree);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:171:11: ( type )?
                int alt27 = 2;
                int LA27_0 = input.LA(1);

                if ((LA27_0 == ID)) {
                    int LA27_1 = input.LA(2);

                    if ((LA27_1 == ID || LA27_1 == 32)) {
                        alt27 = 1;
                    }
                }
                switch (alt27) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:171:12: type
                    {
                        pushFollow(FOLLOW_type_in_fromClause984);
                        type61 = type();
                        _fsp--;

                        adaptor.addChild(root_0, type61.getTree());
                        t = input.toString(type61.start, type61.stop);

                    }
                    break;

                }

                pushFollow(FOLLOW_identifier_in_fromClause991);
                identifier62 = identifier();
                _fsp--;

                adaptor.addChild(root_0, identifier62.getTree());
                string_literal63 = (Token) input.LT(1);
                match(input, 42, FOLLOW_42_in_fromClause993);
                string_literal63_tree = (CommonTree) adaptor.create(string_literal63);
                adaptor.addChild(root_0, string_literal63_tree);

                pushFollow(FOLLOW_expression_in_fromClause995);
                expression64 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression64.getTree());
                retval.value = new FromClause(identifier62.value, expression64.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end fromClause

    public static class queryBody_return extends ParserRuleReturnScope {
        public QueryBody value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start queryBody
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:174:1: queryBody returns [QueryBody name] : ( queryBodyClause )* ( selectClause | groupClause ) ( queryContinuation )? ;
    public final queryBody_return queryBody() throws RecognitionException {
        queryBody_return retval = new queryBody_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        queryBodyClause_return queryBodyClause65 = null;

        selectClause_return selectClause66 = null;

        groupClause_return groupClause67 = null;

        queryContinuation_return queryContinuation68 = null;


        List<QueryBodyClause> clauses = new ArrayList<QueryBodyClause>();
        SelectOrGroupClause selOrGroup = null;
        QueryContinuation qc = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:2: ( ( queryBodyClause )* ( selectClause | groupClause ) ( queryContinuation )? )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:4: ( queryBodyClause )* ( selectClause | groupClause ) ( queryContinuation )?
            {
                root_0 = (CommonTree) adaptor.nil();

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:4: ( queryBodyClause )*
                loop28:
                do {
                    int alt28 = 2;
                    int LA28_0 = input.LA(1);

                    if ((LA28_0 == 41 || (LA28_0 >= 44 && LA28_0 <= 46) || LA28_0 == 49)) {
                        alt28 = 1;
                    }


                    switch (alt28) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:5: queryBodyClause
                        {
                            pushFollow(FOLLOW_queryBodyClause_in_queryBody1020);
                            queryBodyClause65 = queryBodyClause();
                            _fsp--;

                            adaptor.addChild(root_0, queryBodyClause65.getTree());
                            clauses.add(queryBodyClause65.value);

                        }
                        break;

                        default:
                            break loop28;
                    }
                }
                while (true);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:65: ( selectClause | groupClause )
                int alt29 = 2;
                int LA29_0 = input.LA(1);

                if ((LA29_0 == 52)) {
                    alt29 = 1;
                } else if ((LA29_0 == 53)) {
                    alt29 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("180:65: ( selectClause | groupClause )", 29, 0, input);

                    throw nvae;
                }
                switch (alt29) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:66: selectClause
                    {
                        pushFollow(FOLLOW_selectClause_in_queryBody1028);
                        selectClause66 = selectClause();
                        _fsp--;

                        adaptor.addChild(root_0, selectClause66.getTree());
                        selOrGroup = selectClause66.value;

                    }
                    break;
                    case 2:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:119: groupClause
                    {
                        pushFollow(FOLLOW_groupClause_in_queryBody1034);
                        groupClause67 = groupClause();
                        _fsp--;

                        adaptor.addChild(root_0, groupClause67.getTree());
                        selOrGroup = groupClause67.value;

                    }
                    break;

                }

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:169: ( queryContinuation )?
                int alt30 = 2;
                int LA30_0 = input.LA(1);

                if ((LA30_0 == 43)) {
                    alt30 = 1;
                }
                switch (alt30) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:180:170: queryContinuation
                    {
                        pushFollow(FOLLOW_queryContinuation_in_queryBody1040);
                        queryContinuation68 = queryContinuation();
                        _fsp--;

                        adaptor.addChild(root_0, queryContinuation68.getTree());
                        qc = queryContinuation68.value;

                    }
                    break;

                }

                retval.value = new QueryBody(clauses, selOrGroup, qc);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end queryBody

    public static class queryBodyClause_return extends ParserRuleReturnScope {
        public QueryBodyClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start queryBodyClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:184:1: queryBodyClause returns [QueryBodyClause name] : ( fromClause | letClause | whereClause | joinClause | orderByClause );
    public final queryBodyClause_return queryBodyClause() throws RecognitionException {
        queryBodyClause_return retval = new queryBodyClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        fromClause_return fromClause69 = null;

        letClause_return letClause70 = null;

        whereClause_return whereClause71 = null;

        joinClause_return joinClause72 = null;

        orderByClause_return orderByClause73 = null;


        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:185:2: ( fromClause | letClause | whereClause | joinClause | orderByClause )
            int alt31 = 5;
            switch (input.LA(1)) {
                case 41: {
                    alt31 = 1;
                }
                break;
                case 45: {
                    alt31 = 2;
                }
                break;
                case 44: {
                    alt31 = 3;
                }
                break;
                case 46: {
                    alt31 = 4;
                }
                break;
                case 49: {
                    alt31 = 5;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("184:1: queryBodyClause returns [QueryBodyClause name] : ( fromClause | letClause | whereClause | joinClause | orderByClause );", 31, 0, input);

                    throw nvae;
            }

            switch (alt31) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:185:4: fromClause
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_fromClause_in_queryBodyClause1064);
                    fromClause69 = fromClause();
                    _fsp--;

                    adaptor.addChild(root_0, fromClause69.getTree());
                    retval.value = fromClause69.value;

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:186:5: letClause
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_letClause_in_queryBodyClause1073);
                    letClause70 = letClause();
                    _fsp--;

                    adaptor.addChild(root_0, letClause70.getTree());
                    retval.value = letClause70.value;

                }
                break;
                case 3:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:187:5: whereClause
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_whereClause_in_queryBodyClause1082);
                    whereClause71 = whereClause();
                    _fsp--;

                    adaptor.addChild(root_0, whereClause71.getTree());
                    retval.value = whereClause71.value;

                }
                break;
                case 4:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:188:5: joinClause
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_joinClause_in_queryBodyClause1091);
                    joinClause72 = joinClause();
                    _fsp--;

                    adaptor.addChild(root_0, joinClause72.getTree());
                    retval.value = joinClause72.value;

                }
                break;
                case 5:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:189:5: orderByClause
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_orderByClause_in_queryBodyClause1100);
                    orderByClause73 = orderByClause();
                    _fsp--;

                    adaptor.addChild(root_0, orderByClause73.getTree());
                    retval.value = orderByClause73.value;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end queryBodyClause

    public static class queryContinuation_return extends ParserRuleReturnScope {
        public QueryContinuation value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start queryContinuation
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:192:1: queryContinuation returns [QueryContinuation name] : 'into' identifier queryBody ;
    public final queryContinuation_return queryContinuation() throws RecognitionException {
        queryContinuation_return retval = new queryContinuation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal74 = null;
        identifier_return identifier75 = null;

        queryBody_return queryBody76 = null;


        CommonTree string_literal74_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:193:2: ( 'into' identifier queryBody )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:193:5: 'into' identifier queryBody
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal74 = (Token) input.LT(1);
                match(input, 43, FOLLOW_43_in_queryContinuation1117);
                string_literal74_tree = (CommonTree) adaptor.create(string_literal74);
                adaptor.addChild(root_0, string_literal74_tree);

                pushFollow(FOLLOW_identifier_in_queryContinuation1119);
                identifier75 = identifier();
                _fsp--;

                adaptor.addChild(root_0, identifier75.getTree());
                pushFollow(FOLLOW_queryBody_in_queryContinuation1121);
                queryBody76 = queryBody();
                _fsp--;

                adaptor.addChild(root_0, queryBody76.getTree());
                retval.value = new QueryContinuation(identifier75.value, queryBody76.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end queryContinuation

    public static class whereClause_return extends ParserRuleReturnScope {
        public WhereClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start whereClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:196:1: whereClause returns [WhereClause name] : 'where' sourceExpression ;
    public final whereClause_return whereClause() throws RecognitionException {
        whereClause_return retval = new whereClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal77 = null;
        expression_return expression78 = null;


        CommonTree string_literal77_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:197:2: ( 'where' sourceExpression )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:197:4: 'where' sourceExpression
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal77 = (Token) input.LT(1);
                match(input, 44, FOLLOW_44_in_whereClause1139);
                string_literal77_tree = (CommonTree) adaptor.create(string_literal77);
                adaptor.addChild(root_0, string_literal77_tree);

                pushFollow(FOLLOW_expression_in_whereClause1141);
                expression78 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression78.getTree());
                retval.value = new WhereClause(expression78.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end whereClause

    public static class letClause_return extends ParserRuleReturnScope {
        public DeclareClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start letClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:199:1: letClause returns [LetClause name] : 'let' identifier '=' sourceExpression ;
    public final letClause_return letClause() throws RecognitionException {
        letClause_return retval = new letClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal79 = null;
        Token char_literal81 = null;
        identifier_return identifier80 = null;

        expression_return expression82 = null;


        CommonTree string_literal79_tree = null;
        CommonTree char_literal81_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:200:2: ( 'let' identifier '=' sourceExpression )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:200:4: 'let' identifier '=' sourceExpression
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal79 = (Token) input.LT(1);
                match(input, 45, FOLLOW_45_in_letClause1156);
                string_literal79_tree = (CommonTree) adaptor.create(string_literal79);
                adaptor.addChild(root_0, string_literal79_tree);

                pushFollow(FOLLOW_identifier_in_letClause1158);
                identifier80 = identifier();
                _fsp--;

                adaptor.addChild(root_0, identifier80.getTree());
                char_literal81 = (Token) input.LT(1);
                match(input, 39, FOLLOW_39_in_letClause1160);
                char_literal81_tree = (CommonTree) adaptor.create(char_literal81);
                adaptor.addChild(root_0, char_literal81_tree);

                pushFollow(FOLLOW_expression_in_letClause1162);
                expression82 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression82.getTree());
                retval.value = new DeclareClause(identifier80.value, expression82.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end letClause

    public static class joinClause_return extends ParserRuleReturnScope {
        public JoinClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start joinClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:203:1: joinClause returns [JoinClause name] : 'join' ( type )? join= identifier 'in' inid= sourceExpression 'on' on= sourceExpression 'equals' equals= sourceExpression ( 'into' into= identifier )? ;
    public final joinClause_return joinClause() throws RecognitionException {
        joinClause_return retval = new joinClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal83 = null;
        Token string_literal85 = null;
        Token string_literal86 = null;
        Token string_literal87 = null;
        Token string_literal88 = null;
        identifier_return join = null;

        expression_return inid = null;

        expression_return on = null;

        expression_return equals = null;

        identifier_return into = null;

        type_return type84 = null;


        CommonTree string_literal83_tree = null;
        CommonTree string_literal85_tree = null;
        CommonTree string_literal86_tree = null;
        CommonTree string_literal87_tree = null;
        CommonTree string_literal88_tree = null;


        String t = null;
        Identifier i = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:2: ( 'join' ( type )? join= identifier 'in' inid= sourceExpression 'on' on= sourceExpression 'equals' equals= sourceExpression ( 'into' into= identifier )? )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:4: 'join' ( type )? join= identifier 'in' inid= sourceExpression 'on' on= sourceExpression 'equals' equals= sourceExpression ( 'into' into= identifier )?
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal83 = (Token) input.LT(1);
                match(input, 46, FOLLOW_46_in_joinClause1183);
                string_literal83_tree = (CommonTree) adaptor.create(string_literal83);
                adaptor.addChild(root_0, string_literal83_tree);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:11: ( type )?
                int alt32 = 2;
                int LA32_0 = input.LA(1);

                if ((LA32_0 == ID)) {
                    int LA32_1 = input.LA(2);

                    if ((LA32_1 == ID || LA32_1 == 32)) {
                        alt32 = 1;
                    }
                }
                switch (alt32) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:12: type
                    {
                        pushFollow(FOLLOW_type_in_joinClause1186);
                        type84 = type();
                        _fsp--;

                        adaptor.addChild(root_0, type84.getTree());
                        t = input.toString(type84.start, type84.stop);

                    }
                    break;

                }

                pushFollow(FOLLOW_identifier_in_joinClause1195);
                join = identifier();
                _fsp--;

                adaptor.addChild(root_0, join.getTree());
                string_literal85 = (Token) input.LT(1);
                match(input, 42, FOLLOW_42_in_joinClause1197);
                string_literal85_tree = (CommonTree) adaptor.create(string_literal85);
                adaptor.addChild(root_0, string_literal85_tree);

                pushFollow(FOLLOW_expression_in_joinClause1201);
                inid = expression();
                _fsp--;

                adaptor.addChild(root_0, inid.getTree());
                string_literal86 = (Token) input.LT(1);
                match(input, 47, FOLLOW_47_in_joinClause1203);
                string_literal86_tree = (CommonTree) adaptor.create(string_literal86);
                adaptor.addChild(root_0, string_literal86_tree);

                pushFollow(FOLLOW_expression_in_joinClause1207);
                on = expression();
                _fsp--;

                adaptor.addChild(root_0, on.getTree());
                string_literal87 = (Token) input.LT(1);
                match(input, 48, FOLLOW_48_in_joinClause1209);
                string_literal87_tree = (CommonTree) adaptor.create(string_literal87);
                adaptor.addChild(root_0, string_literal87_tree);

                pushFollow(FOLLOW_expression_in_joinClause1213);
                equals = expression();
                _fsp--;

                adaptor.addChild(root_0, equals.getTree());
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:123: ( 'into' into= identifier )?
                int alt33 = 2;
                int LA33_0 = input.LA(1);

                if ((LA33_0 == 43)) {
                    alt33 = 1;
                }
                switch (alt33) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:208:124: 'into' into= identifier
                    {
                        string_literal88 = (Token) input.LT(1);
                        match(input, 43, FOLLOW_43_in_joinClause1216);
                        string_literal88_tree = (CommonTree) adaptor.create(string_literal88);
                        adaptor.addChild(root_0, string_literal88_tree);

                        pushFollow(FOLLOW_identifier_in_joinClause1220);
                        into = identifier();
                        _fsp--;

                        adaptor.addChild(root_0, into.getTree());
                        i = into.value;

                    }
                    break;

                }

                retval.value = new JoinClause(join.value, inid.value, on.value, equals.value, i);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end joinClause

    public static class orderByClause_return extends ParserRuleReturnScope {
        public OrderByClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start orderByClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:212:1: orderByClause returns [OrderByClause name] : 'orderby' fis= sourceExpression (fio= ( 'ascending' | 'descending' ) )? ( ',' fos= sourceExpression (foo= ( 'ascending' | 'descending' ) )? )* ;
    public final orderByClause_return orderByClause() throws RecognitionException {
        orderByClause_return retval = new orderByClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token fio = null;
        Token foo = null;
        Token string_literal89 = null;
        Token char_literal90 = null;
        expression_return fis = null;

        expression_return fos = null;


        CommonTree fio_tree = null;
        CommonTree foo_tree = null;
        CommonTree string_literal89_tree = null;
        CommonTree char_literal90_tree = null;


        List<OrderByCriteria> criterias = new ArrayList<OrderByCriteria>();

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:2: ( 'orderby' fis= sourceExpression (fio= ( 'ascending' | 'descending' ) )? ( ',' fos= sourceExpression (foo= ( 'ascending' | 'descending' ) )? )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:4: 'orderby' fis= sourceExpression (fio= ( 'ascending' | 'descending' ) )? ( ',' fos= sourceExpression (foo= ( 'ascending' | 'descending' ) )? )*
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal89 = (Token) input.LT(1);
                match(input, 49, FOLLOW_49_in_orderByClause1249);
                string_literal89_tree = (CommonTree) adaptor.create(string_literal89);
                adaptor.addChild(root_0, string_literal89_tree);

                pushFollow(FOLLOW_expression_in_orderByClause1253);
                fis = expression();
                _fsp--;

                adaptor.addChild(root_0, fis.getTree());
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:32: (fio= ( 'ascending' | 'descending' ) )?
                int alt34 = 2;
                int LA34_0 = input.LA(1);

                if (((LA34_0 >= 50 && LA34_0 <= 51))) {
                    alt34 = 1;
                }
                switch (alt34) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:32: fio= ( 'ascending' | 'descending' )
                    {
                        fio = (Token) input.LT(1);
                        if ((input.LA(1) >= 50 && input.LA(1) <= 51)) {
                            input.consume();
                            adaptor.addChild(root_0, adaptor.create(fio));
                            errorRecovery = false;
                        } else {
                            MismatchedSetException mse =
                                    new MismatchedSetException(null, input);
                            recoverFromMismatchedSet(input, mse, FOLLOW_set_in_orderByClause1257);
                            throw mse;
                        }


                    }
                    break;

                }

                criterias.add(new OrderByCriteria(fis.value, (fio == null || fio.getText() == "ascending") ? OrderByCriteria.Direction.ASCENDING : OrderByCriteria.Direction.DESCENDING));
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:232: ( ',' fos= sourceExpression (foo= ( 'ascending' | 'descending' ) )? )*
                loop36:
                do {
                    int alt36 = 2;
                    int LA36_0 = input.LA(1);

                    if ((LA36_0 == 35)) {
                        alt36 = 1;
                    }


                    switch (alt36) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:233: ',' fos= sourceExpression (foo= ( 'ascending' | 'descending' ) )?
                        {
                            char_literal90 = (Token) input.LT(1);
                            match(input, 35, FOLLOW_35_in_orderByClause1270);
                            char_literal90_tree = (CommonTree) adaptor.create(char_literal90);
                            adaptor.addChild(root_0, char_literal90_tree);

                            pushFollow(FOLLOW_expression_in_orderByClause1274);
                            fos = expression();
                            _fsp--;

                            adaptor.addChild(root_0, fos.getTree());
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:255: (foo= ( 'ascending' | 'descending' ) )?
                            int alt35 = 2;
                            int LA35_0 = input.LA(1);

                            if (((LA35_0 >= 50 && LA35_0 <= 51))) {
                                alt35 = 1;
                            }
                            switch (alt35) {
                                case 1:
                                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:216:255: foo= ( 'ascending' | 'descending' )
                                {
                                    foo = (Token) input.LT(1);
                                    if ((input.LA(1) >= 50 && input.LA(1) <= 51)) {
                                        input.consume();
                                        adaptor.addChild(root_0, adaptor.create(foo));
                                        errorRecovery = false;
                                    } else {
                                        MismatchedSetException mse =
                                                new MismatchedSetException(null, input);
                                        recoverFromMismatchedSet(input, mse, FOLLOW_set_in_orderByClause1278);
                                        throw mse;
                                    }


                                }
                                break;

                            }

                            criterias.add(new OrderByCriteria(fos.value, (foo == null || foo.getText() == "ascending") ? OrderByCriteria.Direction.ASCENDING : OrderByCriteria.Direction.DESCENDING));

                        }
                        break;

                        default:
                            break loop36;
                    }
                }
                while (true);

                retval.value = new OrderByClause(criterias);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end orderByClause

    public static class selectClause_return extends ParserRuleReturnScope {
        public SelectClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start selectClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:220:1: selectClause returns [SelectClause name] : 'select' sourceExpression ;
    public final selectClause_return selectClause() throws RecognitionException {
        selectClause_return retval = new selectClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal91 = null;
        expression_return expression92 = null;


        CommonTree string_literal91_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:221:2: ( 'select' sourceExpression )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:221:4: 'select' sourceExpression
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal91 = (Token) input.LT(1);
                match(input, 52, FOLLOW_52_in_selectClause1308);
                string_literal91_tree = (CommonTree) adaptor.create(string_literal91);
                adaptor.addChild(root_0, string_literal91_tree);

                pushFollow(FOLLOW_expression_in_selectClause1310);
                expression92 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression92.getTree());
                retval.value = new SelectClause(expression92.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end selectClause

    public static class groupClause_return extends ParserRuleReturnScope {
        public GroupClause value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start groupClause
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:225:1: groupClause returns [GroupClause name] : 'group' identifier 'by' sourceExpression ;
    public final groupClause_return groupClause() throws RecognitionException {
        groupClause_return retval = new groupClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal93 = null;
        Token string_literal95 = null;
        identifier_return identifier94 = null;

        expression_return expression96 = null;


        CommonTree string_literal93_tree = null;
        CommonTree string_literal95_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:226:2: ( 'group' identifier 'by' sourceExpression )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:226:4: 'group' identifier 'by' sourceExpression
            {
                root_0 = (CommonTree) adaptor.nil();

                string_literal93 = (Token) input.LT(1);
                match(input, 53, FOLLOW_53_in_groupClause1328);
                string_literal93_tree = (CommonTree) adaptor.create(string_literal93);
                adaptor.addChild(root_0, string_literal93_tree);

                pushFollow(FOLLOW_identifier_in_groupClause1330);
                identifier94 = identifier();
                _fsp--;

                adaptor.addChild(root_0, identifier94.getTree());
                string_literal95 = (Token) input.LT(1);
                match(input, 54, FOLLOW_54_in_groupClause1332);
                string_literal95_tree = (CommonTree) adaptor.create(string_literal95);
                adaptor.addChild(root_0, string_literal95_tree);

                pushFollow(FOLLOW_expression_in_groupClause1334);
                expression96 = expression();
                _fsp--;

                adaptor.addChild(root_0, expression96.getTree());
                retval.value = new GroupClause(identifier94.value, expression96.value);

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end groupClause

    public static class type_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start type
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:229:1: type : identifier ( '.' identifier )* ;
    public final type_return type() throws RecognitionException {
        type_return retval = new type_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal98 = null;
        identifier_return identifier97 = null;

        identifier_return identifier99 = null;


        CommonTree char_literal98_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:230:2: ( identifier ( '.' identifier )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:230:4: identifier ( '.' identifier )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_identifier_in_type1348);
                identifier97 = identifier();
                _fsp--;

                adaptor.addChild(root_0, identifier97.getTree());
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:230:15: ( '.' identifier )*
                loop37:
                do {
                    int alt37 = 2;
                    int LA37_0 = input.LA(1);

                    if ((LA37_0 == 32)) {
                        alt37 = 1;
                    }


                    switch (alt37) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:230:17: '.' identifier
                        {
                            char_literal98 = (Token) input.LT(1);
                            match(input, 32, FOLLOW_32_in_type1352);
                            char_literal98_tree = (CommonTree) adaptor.create(char_literal98);
                            adaptor.addChild(root_0, char_literal98_tree);

                            pushFollow(FOLLOW_identifier_in_type1354);
                            identifier99 = identifier();
                            _fsp--;

                            adaptor.addChild(root_0, identifier99.getTree());

                        }
                        break;

                        default:
                            break loop37;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end type

    public static class expressionList_return extends ParserRuleReturnScope {
        public List<Expression> value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start expressionList
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:233:1: expressionList returns [List<Expression> name] : first= sourceExpression ( ',' follow= sourceExpression )* ;
    public final expressionList_return expressionList() throws RecognitionException {
        expressionList_return retval = new expressionList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal100 = null;
        expression_return first = null;

        expression_return follow = null;


        CommonTree char_literal100_tree = null;


        List<Expression> expressions = new ArrayList<Expression>();

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:237:2: (first= sourceExpression ( ',' follow= sourceExpression )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:237:4: first= sourceExpression ( ',' follow= sourceExpression )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_expression_in_expressionList1379);
                first = expression();
                _fsp--;

                adaptor.addChild(root_0, first.getTree());
                expressions.add(first.value);
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:237:55: ( ',' follow= sourceExpression )*
                loop38:
                do {
                    int alt38 = 2;
                    int LA38_0 = input.LA(1);

                    if ((LA38_0 == 35)) {
                        alt38 = 1;
                    }


                    switch (alt38) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:237:57: ',' follow= sourceExpression
                        {
                            char_literal100 = (Token) input.LT(1);
                            match(input, 35, FOLLOW_35_in_expressionList1386);
                            char_literal100_tree = (CommonTree) adaptor.create(char_literal100);
                            adaptor.addChild(root_0, char_literal100_tree);

                            pushFollow(FOLLOW_expression_in_expressionList1390);
                            follow = expression();
                            _fsp--;

                            adaptor.addChild(root_0, follow.getTree());
                            expressions.add(follow.value);

                        }
                        break;

                        default:
                            break loop38;
                    }
                }
                while (true);

                retval.value = expressions;

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expressionList

    public static class arguments_return extends ParserRuleReturnScope {
        public List<Expression> value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start arguments
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:241:1: arguments returns [List<Expression> name] : '(' ( expressionList )? ')' ;
    public final arguments_return arguments() throws RecognitionException {
        arguments_return retval = new arguments_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal101 = null;
        Token char_literal103 = null;
        expressionList_return expressionList102 = null;


        CommonTree char_literal101_tree = null;
        CommonTree char_literal103_tree = null;


        retval.value = new ArrayList<Expression>();

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:245:2: ( '(' ( expressionList )? ')' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:245:4: '(' ( expressionList )? ')'
            {
                root_0 = (CommonTree) adaptor.nil();

                char_literal101 = (Token) input.LT(1);
                match(input, 33, FOLLOW_33_in_arguments1419);
                char_literal101_tree = (CommonTree) adaptor.create(char_literal101);
                adaptor.addChild(root_0, char_literal101_tree);

                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:245:8: ( expressionList )?
                int alt39 = 2;
                int LA39_0 = input.LA(1);

                if (((LA39_0 >= INTEGER && LA39_0 <= ID) || LA39_0 == 27 || LA39_0 == 31 || LA39_0 == 33 || LA39_0 == 37 || LA39_0 == 41 || (LA39_0 >= 55 && LA39_0 <= 56))) {
                    alt39 = 1;
                }
                switch (alt39) {
                    case 1:
                        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:245:10: expressionList
                    {
                        pushFollow(FOLLOW_expressionList_in_arguments1423);
                        expressionList102 = expressionList();
                        _fsp--;

                        adaptor.addChild(root_0, expressionList102.getTree());
                        retval.value = expressionList102.value;

                    }
                    break;

                }

                char_literal103 = (Token) input.LT(1);
                match(input, 34, FOLLOW_34_in_arguments1430);
                char_literal103_tree = (CommonTree) adaptor.create(char_literal103);
                adaptor.addChild(root_0, char_literal103_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end arguments

    public static class booleanValue_return extends ParserRuleReturnScope {
        public boolean value;
        CommonTree tree;
        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start booleanValue
    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:264:1: booleanValue returns [boolean name] : ( 'true' | 'false' );
    public final booleanValue_return booleanValue() throws RecognitionException {
        booleanValue_return retval = new booleanValue_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal104 = null;
        Token string_literal105 = null;

        CommonTree string_literal104_tree = null;
        CommonTree string_literal105_tree = null;

        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:265:2: ( 'true' | 'false' )
            int alt40 = 2;
            int LA40_0 = input.LA(1);

            if ((LA40_0 == 55)) {
                alt40 = 1;
            } else if ((LA40_0 == 56)) {
                alt40 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("264:1: booleanValue returns [boolean name] : ( 'true' | 'false' );", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:265:4: 'true'
                {
                    root_0 = (CommonTree) adaptor.nil();

                    string_literal104 = (Token) input.LT(1);
                    match(input, 55, FOLLOW_55_in_booleanValue1541);
                    string_literal104_tree = (CommonTree) adaptor.create(string_literal104);
                    adaptor.addChild(root_0, string_literal104_tree);

                    retval.value = true;

                }
                break;
                case 2:
                    // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:266:4: 'false'
                {
                    root_0 = (CommonTree) adaptor.nil();

                    string_literal105 = (Token) input.LT(1);
                    match(input, 56, FOLLOW_56_in_booleanValue1548);
                    string_literal105_tree = (CommonTree) adaptor.create(string_literal105);
                    adaptor.addChild(root_0, string_literal105_tree);

                    retval.value = false;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end booleanValue


    public static final BitSet FOLLOW_expression_in_quaereExpression51 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_quaereExpression53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionItem_in_expression71 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_expression76 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_expression78 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_expression80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_expressionItem103 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_expressionItem109 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_conditionalExpression_in_expressionItem113 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_expressionItem115 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_conditionalExpression_in_expressionItem119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanAndExpression_in_conditionalExpression146 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_conditionalExpression155 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_booleanAndExpression_in_conditionalExpression165 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_equalityExpression_in_booleanAndExpression199 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_booleanAndExpression208 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_equalityExpression_in_booleanAndExpression218 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression250 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_20_in_equalityExpression261 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_21_in_equalityExpression271 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression283 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression316 = new BitSet(new long[]{0x0000000003C00002L});
    public static final BitSet FOLLOW_22_in_relationalExpression327 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_23_in_relationalExpression337 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_24_in_relationalExpression348 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_25_in_relationalExpression358 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression370 = new BitSet(new long[]{0x0000000003C00002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression402 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_26_in_additiveExpression413 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_27_in_additiveExpression423 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression435 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression467 = new BitSet(new long[]{0x0000000070000002L});
    public static final BitSet FOLLOW_28_in_multiplicativeExpression478 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_29_in_multiplicativeExpression488 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_30_in_multiplicativeExpression498 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression510 = new BitSet(new long[]{0x0000000070000002L});
    public static final BitSet FOLLOW_statement_in_unaryExpression537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_unaryExpression548 = new BitSet(new long[]{0x01800222000000F0L});
    public static final BitSet FOLLOW_statement_in_unaryExpression550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_unaryExpression561 = new BitSet(new long[]{0x01800222000000F0L});
    public static final BitSet FOLLOW_statement_in_unaryExpression563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_statement589 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_statement595 = new BitSet(new long[]{0x01800222000000F0L});
    public static final BitSet FOLLOW_primaryExpression_in_statement599 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_33_in_primaryExpression627 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_primaryExpression629 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_primaryExpression631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_primaryExpression641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_newExpression_in_primaryExpression649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_primaryExpression657 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_arguments_in_primaryExpression662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_primaryExpression671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_queryExpression_in_primaryExpression680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_value699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_value707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_value715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanValue_in_value723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_methodCall750 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_methodCall754 = new BitSet(new long[]{0x0000001000000080L});
    public static final BitSet FOLLOW_identifier_in_methodCall760 = new BitSet(new long[]{0x0000001800000000L});
    public static final BitSet FOLLOW_35_in_methodCall765 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_methodCall769 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_methodCall780 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_methodCall784 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_methodCall788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_methodCall798 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_methodCall802 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_methodCall804 = new BitSet(new long[]{0x0000000400000080L});
    public static final BitSet FOLLOW_identifier_in_methodCall809 = new BitSet(new long[]{0x0000000C00000000L});
    public static final BitSet FOLLOW_35_in_methodCall814 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_methodCall818 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_methodCall828 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_methodCall830 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_methodCall834 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_methodCall838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_identifier856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_newExpression877 = new BitSet(new long[]{0x0000004000000080L});
    public static final BitSet FOLLOW_type_in_newExpression880 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_newExpression887 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_identifier_in_newExpression892 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_newExpression894 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_newExpression903 = new BitSet(new long[]{0x0000010800000000L});
    public static final BitSet FOLLOW_35_in_newExpression908 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_identifier_in_newExpression913 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_newExpression915 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_newExpression923 = new BitSet(new long[]{0x0000010800000000L});
    public static final BitSet FOLLOW_40_in_newExpression929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fromClause_in_queryExpression956 = new BitSet(new long[]{0x0032720000000000L});
    public static final BitSet FOLLOW_queryBody_in_queryExpression958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_fromClause981 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_type_in_fromClause984 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_fromClause991 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_fromClause993 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_fromClause995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_queryBodyClause_in_queryBody1020 = new BitSet(new long[]{0x0032720000000000L});
    public static final BitSet FOLLOW_selectClause_in_queryBody1028 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_groupClause_in_queryBody1034 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_queryContinuation_in_queryBody1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fromClause_in_queryBodyClause1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_letClause_in_queryBodyClause1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whereClause_in_queryBodyClause1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_joinClause_in_queryBodyClause1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderByClause_in_queryBodyClause1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_queryContinuation1117 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_queryContinuation1119 = new BitSet(new long[]{0x0032720000000000L});
    public static final BitSet FOLLOW_queryBody_in_queryContinuation1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_whereClause1139 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_whereClause1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_letClause1156 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_letClause1158 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_letClause1160 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_letClause1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_joinClause1183 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_type_in_joinClause1186 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_joinClause1195 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_joinClause1197 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_joinClause1201 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_joinClause1203 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_joinClause1207 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_joinClause1209 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_joinClause1213 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_joinClause1216 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_joinClause1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_orderByClause1249 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_orderByClause1253 = new BitSet(new long[]{0x000C000800000002L});
    public static final BitSet FOLLOW_set_in_orderByClause1257 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_orderByClause1270 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_orderByClause1274 = new BitSet(new long[]{0x000C000800000002L});
    public static final BitSet FOLLOW_set_in_orderByClause1278 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_52_in_selectClause1308 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_selectClause1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_groupClause1328 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_groupClause1330 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_groupClause1332 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_groupClause1334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_type1348 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_type1352 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_identifier_in_type1354 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1379 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_expressionList1386 = new BitSet(new long[]{0x01800222880000F0L});
    public static final BitSet FOLLOW_expression_in_expressionList1390 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_33_in_arguments1419 = new BitSet(new long[]{0x01800226880000F0L});
    public static final BitSet FOLLOW_expressionList_in_arguments1423 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_arguments1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_booleanValue1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_booleanValue1548 = new BitSet(new long[]{0x0000000000000002L});

}