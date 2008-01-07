package org.quaere.dsl.parser;
// $ANTLR 3.0.1 /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g 2007-09-09 22:57:25

import org.antlr.runtime.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all"})
public class QueryLexer extends Lexer {
    public static final int T14 = 14;
    public static final int T29 = 29;
    public static final int HexDigit = 11;
    public static final int T36 = 36;
    public static final int FLOAT = 5;
    public static final int T35 = 35;
    public static final int T45 = 45;
    public static final int T20 = 20;
    public static final int T34 = 34;
    public static final int INTEGER = 4;
    public static final int T25 = 25;
    public static final int T18 = 18;
    public static final int T37 = 37;
    public static final int EscapeSequence = 10;
    public static final int T26 = 26;
    public static final int T32 = 32;
    public static final int T17 = 17;
    public static final int T51 = 51;
    public static final int T46 = 46;
    public static final int T16 = 16;
    public static final int DIGIT = 9;
    public static final int T38 = 38;
    public static final int T41 = 41;
    public static final int T24 = 24;
    public static final int T19 = 19;
    public static final int T39 = 39;
    public static final int ID = 7;
    public static final int T21 = 21;
    public static final int T44 = 44;
    public static final int T55 = 55;
    public static final int LETTER = 8;
    public static final int T33 = 33;
    public static final int T22 = 22;
    public static final int T50 = 50;
    public static final int WS = 13;
    public static final int STRING = 6;
    public static final int T43 = 43;
    public static final int T23 = 23;
    public static final int T28 = 28;
    public static final int T42 = 42;
    public static final int T40 = 40;
    public static final int T56 = 56;
    public static final int UnicodeEscape = 12;
    public static final int T48 = 48;
    public static final int T15 = 15;
    public static final int T54 = 54;
    public static final int EOF = -1;
    public static final int T47 = 47;
    public static final int Tokens = 57;
    public static final int T53 = 53;
    public static final int T31 = 31;
    public static final int T49 = 49;
    public static final int T27 = 27;
    public static final int T52 = 52;
    public static final int T30 = 30;
    public QueryLexer() {
        ;
    }
    public QueryLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() {
        return "/Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g";
    }

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:7:5: ( '[' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:7:7: '['
            {
                match('[');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:8:5: ( ']' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:8:7: ']'
            {
                match(']');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:9:5: ( '?' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:9:7: '?'
            {
                match('?');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:10:5: ( ':' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:10:7: ':'
            {
                match(':');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:11:5: ( '||' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:11:7: '||'
            {
                match("||");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:12:5: ( '&&' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:12:7: '&&'
            {
                match("&&");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:13:5: ( '==' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:13:7: '=='
            {
                match("==");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:14:5: ( '!=' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:14:7: '!='
            {
                match("!=");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:15:5: ( '<' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:15:7: '<'
            {
                match('<');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:16:5: ( '<=' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:16:7: '<='
            {
                match("<=");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:17:5: ( '>' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:17:7: '>'
            {
                match('>');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:18:5: ( '>=' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:18:7: '>='
            {
                match(">=");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:19:5: ( '+' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:19:7: '+'
            {
                match('+');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:20:5: ( '-' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:20:7: '-'
            {
                match('-');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:21:5: ( '*' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:21:7: '*'
            {
                match('*');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:22:5: ( '/' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:22:7: '/'
            {
                match('/');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:23:5: ( '%' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:23:7: '%'
            {
                match('%');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:24:5: ( '!' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:24:7: '!'
            {
                match('!');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:25:5: ( '.' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:25:7: '.'
            {
                match('.');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:5: ( '(' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:26:7: '('
            {
                match('(');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:27:5: ( ')' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:27:7: ')'
            {
                match(')');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:28:5: ( ',' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:28:7: ','
            {
                match(',');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:29:5: ( '=>' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:29:7: '=>'
            {
                match("=>");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:5: ( 'new' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:30:7: 'new'
            {
                match("new");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:31:5: ( '{' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:31:7: '{'
            {
                match('{');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:32:5: ( '=' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:32:7: '='
            {
                match('=');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:33:5: ( '}' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:33:7: '}'
            {
                match('}');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:34:5: ( 'from' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:34:7: 'from'
            {
                match("from");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:35:5: ( 'in' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:35:7: 'in'
            {
                match("in");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:36:5: ( 'into' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:36:7: 'into'
            {
                match("into");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:37:5: ( 'where' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:37:7: 'where'
            {
                match("where");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:38:5: ( 'let' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:38:7: 'let'
            {
                match("let");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:39:5: ( 'join' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:39:7: 'join'
            {
                match("join");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:40:5: ( 'on' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:40:7: 'on'
            {
                match("on");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:41:5: ( 'equals' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:41:7: 'equals'
            {
                match("equals");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:42:5: ( 'orderby' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:42:7: 'orderby'
            {
                match("orderby");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:43:5: ( 'ascending' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:43:7: 'ascending'
            {
                match("ascending");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:44:5: ( 'descending' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:44:7: 'descending'
            {
                match("descending");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:45:5: ( 'select' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:45:7: 'select'
            {
                match("select");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:46:5: ( 'group' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:46:7: 'group'
            {
                match("group");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:47:5: ( 'by' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:47:7: 'by'
            {
                match("by");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:48:5: ( 'true' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:48:7: 'true'
            {
                match("true");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start T56
    public final void mT56() throws RecognitionException {
        try {
            int _type = T56;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:49:5: ( 'false' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:49:7: 'false'
            {
                match("false");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T56

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:247:2: ( LETTER ( LETTER | DIGIT )* )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:247:5: LETTER ( LETTER | DIGIT )*
            {
                mLETTER();
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:247:12: ( LETTER | DIGIT )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if (((LA1_0 >= '0' && LA1_0 <= '9') || (LA1_0 >= 'A' && LA1_0 <= 'Z') || LA1_0 == '_' || (LA1_0 >= 'a' && LA1_0 <= 'z'))) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();

                            } else {
                                MismatchedSetException mse =
                                        new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop1;
                    }
                } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start INTEGER
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:251:2: ( ( DIGIT )+ )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:251:4: ( DIGIT )+
            {
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:251:4: ( DIGIT )+
                int cnt2 = 0;
                loop2:
                do {
                    int alt2 = 2;
                    int LA2_0 = input.LA(1);

                    if (((LA2_0 >= '0' && LA2_0 <= '9'))) {
                        alt2 = 1;
                    }


                    switch (alt2) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:251:4: DIGIT
                        {
                            mDIGIT();

                        }
                        break;

                        default:
                            if (cnt2 >= 1) break loop2;
                            EarlyExitException eee =
                                    new EarlyExitException(2, input);
                            throw eee;
                    }
                    cnt2++;
                } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGER

    // $ANTLR start FLOAT
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:2: ( ( DIGIT )* '.' ( DIGIT )+ )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:4: ( DIGIT )* '.' ( DIGIT )+
            {
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:4: ( DIGIT )*
                loop3:
                do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if (((LA3_0 >= '0' && LA3_0 <= '9'))) {
                        alt3 = 1;
                    }


                    switch (alt3) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:4: DIGIT
                        {
                            mDIGIT();

                        }
                        break;

                        default:
                            break loop3;
                    }
                } while (true);

                match('.');
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:15: ( DIGIT )+
                int cnt4 = 0;
                loop4:
                do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if (((LA4_0 >= '0' && LA4_0 <= '9'))) {
                        alt4 = 1;
                    }


                    switch (alt4) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:255:15: DIGIT
                        {
                            mDIGIT();

                        }
                        break;

                        default:
                            if (cnt4 >= 1) break loop4;
                            EarlyExitException eee =
                                    new EarlyExitException(4, input);
                            throw eee;
                    }
                    cnt4++;
                } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FLOAT

    // $ANTLR start STRING
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:259:6: ( '\\'' ( EscapeSequence | ~ ( '\\\\' | '\\'' ) )* '\\'' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:259:10: '\\'' ( EscapeSequence | ~ ( '\\\\' | '\\'' ) )* '\\''
            {
                match('\'');
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:259:15: ( EscapeSequence | ~ ( '\\\\' | '\\'' ) )*
                loop5:
                do {
                    int alt5 = 3;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == '\\')) {
                        alt5 = 1;
                    } else
                    if (((LA5_0 >= '\u0000' && LA5_0 <= '&') || (LA5_0 >= '(' && LA5_0 <= '[') || (LA5_0 >= ']' && LA5_0 <= '\uFFFE'))) {
                        alt5 = 2;
                    }


                    switch (alt5) {
                        case 1:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:259:17: EscapeSequence
                        {
                            mEscapeSequence();

                        }
                        break;
                        case 2:
                            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:259:34: ~ ( '\\\\' | '\\'' )
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '&') || (input.LA(1) >= '(' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\uFFFE')) {
                                input.consume();

                            } else {
                                MismatchedSetException mse =
                                        new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop5;
                    }
                } while (true);

                match('\'');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING

    // $ANTLR start LETTER
    public final void mLETTER() throws RecognitionException {
        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:269:2: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:
            {
                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();

                } else {
                    MismatchedSetException mse =
                            new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        }
        finally {
        }
    }
    // $ANTLR end LETTER

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:275:2: ( '0' .. '9' )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:275:4: '0' .. '9'
            {
                matchRange('0', '9');

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start EscapeSequence
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:280:6: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:280:11: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
            {
                match('\\');
                if (input.LA(1) == '\"' || input.LA(1) == '\'' || input.LA(1) == '\\' || input.LA(1) == 'b' || input.LA(1) == 'f' || input.LA(1) == 'n' || input.LA(1) == 'r' || input.LA(1) == 't') {
                    input.consume();

                } else {
                    MismatchedSetException mse =
                            new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        }
        finally {
        }
    }
    // $ANTLR end EscapeSequence

    // $ANTLR start HexDigit
    public final void mHexDigit() throws RecognitionException {
        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:285:2: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:285:5: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
                if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                    input.consume();

                } else {
                    MismatchedSetException mse =
                            new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        }
        finally {
        }
    }
    // $ANTLR end HexDigit

    // $ANTLR start UnicodeEscape
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:289:6: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:289:11: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
                match('\\');
                match('u');
                mHexDigit();
                mHexDigit();
                mHexDigit();
                mHexDigit();

            }

        }
        finally {
        }
    }
    // $ANTLR end UnicodeEscape

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:293:4: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:293:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
                if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
                    input.consume();

                } else {
                    MismatchedSetException mse =
                            new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

                channel = HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    public void mTokens() throws RecognitionException {
        // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:8: ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | ID | INTEGER | FLOAT | STRING | WS )
        int alt6 = 48;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:10: T14
            {
                mT14();

            }
            break;
            case 2:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:14: T15
            {
                mT15();

            }
            break;
            case 3:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:18: T16
            {
                mT16();

            }
            break;
            case 4:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:22: T17
            {
                mT17();

            }
            break;
            case 5:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:26: T18
            {
                mT18();

            }
            break;
            case 6:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:30: T19
            {
                mT19();

            }
            break;
            case 7:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:34: T20
            {
                mT20();

            }
            break;
            case 8:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:38: T21
            {
                mT21();

            }
            break;
            case 9:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:42: T22
            {
                mT22();

            }
            break;
            case 10:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:46: T23
            {
                mT23();

            }
            break;
            case 11:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:50: T24
            {
                mT24();

            }
            break;
            case 12:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:54: T25
            {
                mT25();

            }
            break;
            case 13:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:58: T26
            {
                mT26();

            }
            break;
            case 14:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:62: T27
            {
                mT27();

            }
            break;
            case 15:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:66: T28
            {
                mT28();

            }
            break;
            case 16:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:70: T29
            {
                mT29();

            }
            break;
            case 17:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:74: T30
            {
                mT30();

            }
            break;
            case 18:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:78: T31
            {
                mT31();

            }
            break;
            case 19:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:82: T32
            {
                mT32();

            }
            break;
            case 20:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:86: T33
            {
                mT33();

            }
            break;
            case 21:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:90: T34
            {
                mT34();

            }
            break;
            case 22:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:94: T35
            {
                mT35();

            }
            break;
            case 23:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:98: T36
            {
                mT36();

            }
            break;
            case 24:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:102: T37
            {
                mT37();

            }
            break;
            case 25:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:106: T38
            {
                mT38();

            }
            break;
            case 26:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:110: T39
            {
                mT39();

            }
            break;
            case 27:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:114: T40
            {
                mT40();

            }
            break;
            case 28:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:118: T41
            {
                mT41();

            }
            break;
            case 29:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:122: T42
            {
                mT42();

            }
            break;
            case 30:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:126: T43
            {
                mT43();

            }
            break;
            case 31:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:130: T44
            {
                mT44();

            }
            break;
            case 32:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:134: T45
            {
                mT45();

            }
            break;
            case 33:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:138: T46
            {
                mT46();

            }
            break;
            case 34:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:142: T47
            {
                mT47();

            }
            break;
            case 35:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:146: T48
            {
                mT48();

            }
            break;
            case 36:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:150: T49
            {
                mT49();

            }
            break;
            case 37:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:154: T50
            {
                mT50();

            }
            break;
            case 38:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:158: T51
            {
                mT51();

            }
            break;
            case 39:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:162: T52
            {
                mT52();

            }
            break;
            case 40:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:166: T53
            {
                mT53();

            }
            break;
            case 41:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:170: T54
            {
                mT54();

            }
            break;
            case 42:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:174: T55
            {
                mT55();

            }
            break;
            case 43:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:178: T56
            {
                mT56();

            }
            break;
            case 44:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:182: ID
            {
                mID();

            }
            break;
            case 45:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:185: INTEGER
            {
                mINTEGER();

            }
            break;
            case 46:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:193: FLOAT
            {
                mFLOAT();

            }
            break;
            case 47:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:199: STRING
            {
                mSTRING();

            }
            break;
            case 48:
                // /Users/anders/IdeaProjects/q2/src/main/resources/Quaere.g:1:206: WS
            {
                mWS();

            }
            break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
            "\7\uffff\1\52\1\54\1\56\1\60\5\uffff\1\62\3\uffff\1\44\2\uffff\15" +
                    "\44\1\uffff\1\103\15\uffff\3\44\1\110\3\44\1\114\6\44\1\123\1\44" +
                    "\1\uffff\1\125\3\44\1\uffff\1\44\1\132\1\44\1\uffff\6\44\1\uffff" +
                    "\1\44\1\uffff\1\44\1\144\1\145\1\44\1\uffff\1\147\6\44\1\156\1\157" +
                    "\2\uffff\1\160\1\uffff\5\44\1\166\3\uffff\1\44\1\170\2\44\1\173" +
                    "\1\uffff\1\174\1\uffff\2\44\2\uffff\2\44\1\u0081\1\44\1\uffff\1" +
                    "\u0083\1\uffff";
    static final String DFA6_eofS =
            "\u0084\uffff";
    static final String DFA6_minS =
            "\1\11\6\uffff\4\75\5\uffff\1\60\3\uffff\1\145\2\uffff\1\141\1\156" +
                    "\1\150\1\145\1\157\1\156\1\161\1\163\2\145\1\162\1\171\1\162\1\uffff" +
                    "\1\56\15\uffff\1\167\1\154\1\157\1\60\1\145\1\164\1\151\1\60\1\144" +
                    "\1\165\1\143\1\163\1\154\1\157\1\60\1\165\1\uffff\1\60\1\163\1\155" +
                    "\1\157\1\uffff\1\162\1\60\1\156\1\uffff\1\145\1\141\1\145\1\143" +
                    "\1\145\1\165\1\uffff\1\145\1\uffff\1\145\2\60\1\145\1\uffff\1\60" +
                    "\1\162\1\154\1\156\1\145\1\143\1\160\2\60\2\uffff\1\60\1\uffff\1" +
                    "\142\1\163\1\144\1\156\1\164\1\60\3\uffff\1\171\1\60\1\151\1\144" +
                    "\1\60\1\uffff\1\60\1\uffff\1\156\1\151\2\uffff\1\147\1\156\1\60" +
                    "\1\147\1\uffff\1\60\1\uffff";
    static final String DFA6_maxS =
            "\1\175\6\uffff\1\76\3\75\5\uffff\1\71\3\uffff\1\145\2\uffff\1\162" +
                    "\1\156\1\150\1\145\1\157\1\162\1\161\1\163\2\145\1\162\1\171\1\162" +
                    "\1\uffff\1\71\15\uffff\1\167\1\154\1\157\1\172\1\145\1\164\1\151" +
                    "\1\172\1\144\1\165\1\143\1\163\1\154\1\157\1\172\1\165\1\uffff\1" +
                    "\172\1\163\1\155\1\157\1\uffff\1\162\1\172\1\156\1\uffff\1\145\1" +
                    "\141\1\145\1\143\1\145\1\165\1\uffff\1\145\1\uffff\1\145\2\172\1" +
                    "\145\1\uffff\1\172\1\162\1\154\1\156\1\145\1\143\1\160\2\172\2\uffff" +
                    "\1\172\1\uffff\1\142\1\163\1\144\1\156\1\164\1\172\3\uffff\1\171" +
                    "\1\172\1\151\1\144\1\172\1\uffff\1\172\1\uffff\1\156\1\151\2\uffff" +
                    "\1\147\1\156\1\172\1\147\1\uffff\1\172\1\uffff";
    static final String DFA6_acceptS =
            "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\4\uffff\1\15\1\16\1\17\1\20\1\21" +
                    "\1\uffff\1\24\1\25\1\26\1\uffff\1\31\1\33\15\uffff\1\54\1\uffff" +
                    "\1\57\1\60\1\7\1\27\1\32\1\10\1\22\1\12\1\11\1\14\1\13\1\56\1\23" +
                    "\20\uffff\1\55\4\uffff\1\35\3\uffff\1\42\6\uffff\1\51\1\uffff\1" +
                    "\30\4\uffff\1\40\11\uffff\1\34\1\36\1\uffff\1\41\6\uffff\1\52\1" +
                    "\53\1\37\5\uffff\1\50\1\uffff\1\43\2\uffff\1\47\1\44\4\uffff\1\45" +
                    "\1\uffff\1\46";
    static final String DFA6_specialS =
            "\u0084\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\47\1\uffff\2\47\22\uffff\1\47\1\10\3\uffff\1\17\1\6\1\46" +
                    "\1\21\1\22\1\15\1\13\1\23\1\14\1\20\1\16\12\45\1\4\1\uffff\1" +
                    "\11\1\7\1\12\1\3\1\uffff\32\44\1\1\1\uffff\1\2\1\uffff\1\44" +
                    "\1\uffff\1\36\1\42\1\44\1\37\1\35\1\27\1\41\1\44\1\30\1\33\1" +
                    "\44\1\32\1\44\1\24\1\34\3\44\1\40\1\43\2\44\1\31\3\44\1\25\1" +
                    "\5\1\26",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\50\1\51",
            "\1\53",
            "\1\55",
            "\1\57",
            "",
            "",
            "",
            "",
            "",
            "\12\61",
            "",
            "",
            "",
            "\1\63",
            "",
            "",
            "\1\64\20\uffff\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72\3\uffff\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "",
            "\1\61\1\uffff\12\45",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\23\44\1\107\6\44",
            "\1\111",
            "\1\112",
            "\1\113",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\124",
            "",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\131",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\133",
            "",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "",
            "\1\142",
            "",
            "\1\143",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\146",
            "",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "",
            "",
            "\1\167",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\171",
            "\1\172",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "\1\175",
            "\1\176",
            "",
            "",
            "\1\177",
            "\1\u0080",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u0082",
            "",
            "\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | ID | INTEGER | FLOAT | STRING | WS );";
        }
    }


}