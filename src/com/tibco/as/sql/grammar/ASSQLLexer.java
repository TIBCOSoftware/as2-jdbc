// $ANTLR 3.2 Sep 23, 2009 12:02:23 C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g 2014-10-20 16:47:07

package com.tibco.as.sql.grammar;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ASSQLLexer extends Lexer {
    public static final int UNICAST=103;
    public static final int CAPACITY=67;
    public static final int DVertbar=117;
    public static final int TTL=101;
    public static final int CHAR=23;
    public static final int NOR=59;
    public static final int COUNT=63;
    public static final int NOT=58;
    public static final int EOF=-1;
    public static final int QMark=61;
    public static final int Int=152;
    public static final int Identifier=19;
    public static final int CREATE=4;
    public static final int CParen=8;
    public static final int INSERT=12;
    public static final int IS_NULL=56;
    public static final int Comment=156;
    public static final int PRIMARY_KEY=21;
    public static final int QUERY_TIMEOUT=96;
    public static final int DOUBLE=32;
    public static final int EVICTION_POLICY=74;
    public static final int UPDATE_TRANSPORT=102;
    public static final int PHASE_INTERVAL=94;
    public static final int SELECT=15;
    public static final int INTO=13;
    public static final int READ_WRITE_THROUGH=69;
    public static final int ControlSpace=158;
    public static final int Period=113;
    public static final int D=134;
    public static final int E=126;
    public static final int F=138;
    public static final int LThan=53;
    public static final int G=120;
    public static final int BLOB=33;
    public static final int Underscore=119;
    public static final int A=127;
    public static final int B=135;
    public static final int C=124;
    public static final int L=129;
    public static final int M=121;
    public static final int N=131;
    public static final int DISTRIBUTION_POLICY=71;
    public static final int O=136;
    public static final int ASDate=149;
    public static final int H=137;
    public static final int I=130;
    public static final int NULL=41;
    public static final int J=147;
    public static final int K=143;
    public static final int IdentifierStart=153;
    public static final int U=132;
    public static final int T=122;
    public static final int W=144;
    public static final int V=140;
    public static final int Q=146;
    public static final int FILE_SYNC_INTERVAL=77;
    public static final int P=133;
    public static final int CHAR1=24;
    public static final int DELETE=9;
    public static final int S=128;
    public static final int CACHE_POLICY=68;
    public static final int R=125;
    public static final int Y=142;
    public static final int X=139;
    public static final int Z=148;
    public static final int Float=40;
    public static final int REAL=31;
    public static final int Range=118;
    public static final int LOCK_WAIT=84;
    public static final int ASTime=150;
    public static final int Minus=115;
    public static final int REPLICATION_POLICY=99;
    public static final int NONE=75;
    public static final int OR=112;
    public static final int PERSISTENCE=89;
    public static final int FROM=10;
    public static final int FALSE=43;
    public static final int CONSTRAINT=108;
    public static final int Equals=49;
    public static final int DISTINCT=66;
    public static final int TIMESTAMP=37;
    public static final int PHASE_COUNT=93;
    public static final int WHERE=11;
    public static final int OParen=6;
    public static final int QUERY_LIMIT=95;
    public static final int SingleSpace=157;
    public static final int LIMIT=16;
    public static final int ONLY=110;
    public static final int UPDATE=17;
    public static final int TABLE=5;
    public static final int ROUTED=100;
    public static final int DISTRIBUTED=72;
    public static final int VARCHAR=26;
    public static final int SHARED_NOTHING=91;
    public static final int AND=111;
    public static final int QuotedString=38;
    public static final int MIN_SEEDERS=85;
    public static final int NOT_NULL=20;
    public static final int Space=159;
    public static final int AS=62;
    public static final int TIME=36;
    public static final int INDEX=109;
    public static final int IN=55;
    public static final int TREE=47;
    public static final int ASYNC=88;
    public static final int OFFSET=48;
    public static final int IS=145;
    public static final int LRU=76;
    public static final int ALL=65;
    public static final int NegativeNumber=39;
    public static final int GTEquals=52;
    public static final int READ_THROUGH=70;
    public static final int IdentifierPart=154;
    public static final int GThan=51;
    public static final int VIRTUAL_NODE_COUNT=105;
    public static final int INTEGER=29;
    public static final int Asterisk=64;
    public static final int ASDateTime=44;
    public static final int THREAD=81;
    public static final int NotEquals=50;
    public static final int SYNC=87;
    public static final int PERSISTENCE_POLICY=86;
    public static final int WRITE_TIMEOUT=107;
    public static final int DATETIME=34;
    public static final int VALUES=14;
    public static final int HASH=46;
    public static final int SET=18;
    public static final int PositiveNumber=25;
    public static final int IS_NOT_NULL=57;
    public static final int MODULE=141;
    public static final int Digit=151;
    public static final int TRUE=42;
    public static final int Divide=116;
    public static final int PERSISTENCE_TYPE=92;
    public static final int SPACE_WAIT=106;
    public static final int SColon=45;
    public static final int SHARED_ALL=90;
    public static final int Plus=114;
    public static final int GmtTag=123;
    public static final int DoubleQuotedIdentifier=155;
    public static final int LOCK_SCOPE=80;
    public static final int LONGVARCHAR=27;
    public static final int BIGINT=30;
    public static final int LOCK_TTL=83;
    public static final int NON_DISTRIBUTED=73;
    public static final int FORGET_OLD_VALUE=78;
    public static final int MULTICAST=104;
    public static final int Dot=60;
    public static final int BIT=22;
    public static final int PROCESS=82;
    public static final int DATE=35;
    public static final int Comma=7;
    public static final int HOST_AWARE_REPLICATION=79;
    public static final int REPLICATION_COUNT=98;
    public static final int LTEquals=54;
    public static final int SMALLINT=28;
    public static final int READ_TIMEOUT=97;

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


    // delegates
    // delegators

    public ASSQLLexer() {;} 
    public ASSQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ASSQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g"; }

    // $ANTLR start "Asterisk"
    public final void mAsterisk() throws RecognitionException {
        try {
            int _type = Asterisk;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:562:12: ( '*' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:562:14: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Asterisk"

    // $ANTLR start "Dot"
    public final void mDot() throws RecognitionException {
        try {
            int _type = Dot;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:563:12: ( Period )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:563:14: Period
            {
            mPeriod(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Dot"

    // $ANTLR start "OParen"
    public final void mOParen() throws RecognitionException {
        try {
            int _type = OParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:564:12: ( '(' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:564:14: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OParen"

    // $ANTLR start "CParen"
    public final void mCParen() throws RecognitionException {
        try {
            int _type = CParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:565:12: ( ')' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:565:14: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CParen"

    // $ANTLR start "Comma"
    public final void mComma() throws RecognitionException {
        try {
            int _type = Comma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:566:12: ( ',' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:566:14: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comma"

    // $ANTLR start "SColon"
    public final void mSColon() throws RecognitionException {
        try {
            int _type = SColon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:567:12: ( ';' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:567:14: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SColon"

    // $ANTLR start "QMark"
    public final void mQMark() throws RecognitionException {
        try {
            int _type = QMark;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:568:12: ( '?' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:568:14: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QMark"

    // $ANTLR start "Plus"
    public final void mPlus() throws RecognitionException {
        try {
            int _type = Plus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:569:12: ( '+' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:569:14: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Plus"

    // $ANTLR start "Minus"
    public final void mMinus() throws RecognitionException {
        try {
            int _type = Minus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:570:12: ( '-' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:570:14: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Minus"

    // $ANTLR start "Divide"
    public final void mDivide() throws RecognitionException {
        try {
            int _type = Divide;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:571:12: ( '/' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:571:14: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Divide"

    // $ANTLR start "DVertbar"
    public final void mDVertbar() throws RecognitionException {
        try {
            int _type = DVertbar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:572:12: ( '||' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:572:14: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DVertbar"

    // $ANTLR start "Range"
    public final void mRange() throws RecognitionException {
        try {
            int _type = Range;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:573:12: ( '..' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:573:14: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Range"

    // $ANTLR start "Equals"
    public final void mEquals() throws RecognitionException {
        try {
            int _type = Equals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:574:12: ( '=' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:574:14: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Equals"

    // $ANTLR start "NotEquals"
    public final void mNotEquals() throws RecognitionException {
        try {
            int _type = NotEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:575:12: ( '!=' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:575:14: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NotEquals"

    // $ANTLR start "GThan"
    public final void mGThan() throws RecognitionException {
        try {
            int _type = GThan;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:576:12: ( '>' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:576:14: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GThan"

    // $ANTLR start "GTEquals"
    public final void mGTEquals() throws RecognitionException {
        try {
            int _type = GTEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:577:12: ( '>=' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:577:14: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GTEquals"

    // $ANTLR start "LThan"
    public final void mLThan() throws RecognitionException {
        try {
            int _type = LThan;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:578:12: ( '<' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:578:14: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LThan"

    // $ANTLR start "LTEquals"
    public final void mLTEquals() throws RecognitionException {
        try {
            int _type = LTEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:579:12: ( '<=' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:579:14: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTEquals"

    // $ANTLR start "Underscore"
    public final void mUnderscore() throws RecognitionException {
        try {
            int _type = Underscore;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:580:12: ( '_' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:580:14: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Underscore"

    // $ANTLR start "GmtTag"
    public final void mGmtTag() throws RecognitionException {
        try {
            int _type = GmtTag;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:581:12: ( 'Z' | G M T )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='Z') ) {
                alt1=1;
            }
            else if ( (LA1_0=='G'||LA1_0=='g') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:581:14: 'Z'
                    {
                    match('Z'); 

                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:581:20: G M T
                    {
                    mG(); 
                    mM(); 
                    mT(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GmtTag"

    // $ANTLR start "CREATE"
    public final void mCREATE() throws RecognitionException {
        try {
            int _type = CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:586:8: ( C R E A T E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:586:10: C R E A T E
            {
            mC(); 
            mR(); 
            mE(); 
            mA(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CREATE"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:587:8: ( S E L E C T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:587:10: S E L E C T
            {
            mS(); 
            mE(); 
            mL(); 
            mE(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELECT"

    // $ANTLR start "INSERT"
    public final void mINSERT() throws RecognitionException {
        try {
            int _type = INSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:588:8: ( I N S E R T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:588:10: I N S E R T
            {
            mI(); 
            mN(); 
            mS(); 
            mE(); 
            mR(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSERT"

    // $ANTLR start "UPDATE"
    public final void mUPDATE() throws RecognitionException {
        try {
            int _type = UPDATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:589:8: ( U P D A T E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:589:10: U P D A T E
            {
            mU(); 
            mP(); 
            mD(); 
            mA(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UPDATE"

    // $ANTLR start "DELETE"
    public final void mDELETE() throws RecognitionException {
        try {
            int _type = DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:590:8: ( D E L E T E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:590:10: D E L E T E
            {
            mD(); 
            mE(); 
            mL(); 
            mE(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DELETE"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:593:14: ( A L L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:593:16: A L L
            {
            mA(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:594:14: ( A N D )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:594:16: A N D
            {
            mA(); 
            mN(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:595:14: ( A S )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:595:16: A S
            {
            mA(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "BIGINT"
    public final void mBIGINT() throws RecognitionException {
        try {
            int _type = BIGINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:596:14: ( B I G I N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:596:16: B I G I N T
            {
            mB(); 
            mI(); 
            mG(); 
            mI(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIGINT"

    // $ANTLR start "BIT"
    public final void mBIT() throws RecognitionException {
        try {
            int _type = BIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:597:14: ( B I T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:597:16: B I T
            {
            mB(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIT"

    // $ANTLR start "BLOB"
    public final void mBLOB() throws RecognitionException {
        try {
            int _type = BLOB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:598:14: ( B L O B )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:598:16: B L O B
            {
            mB(); 
            mL(); 
            mO(); 
            mB(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOB"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:599:14: ( C H A R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:599:16: C H A R
            {
            mC(); 
            mH(); 
            mA(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "CHAR1"
    public final void mCHAR1() throws RecognitionException {
        try {
            int _type = CHAR1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:600:14: ( C H A R OParen '1' CParen )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:600:16: C H A R OParen '1' CParen
            {
            mC(); 
            mH(); 
            mA(); 
            mR(); 
            mOParen(); 
            match('1'); 
            mCParen(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR1"

    // $ANTLR start "CONSTRAINT"
    public final void mCONSTRAINT() throws RecognitionException {
        try {
            int _type = CONSTRAINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:601:14: ( C O N S T R A I N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:601:16: C O N S T R A I N T
            {
            mC(); 
            mO(); 
            mN(); 
            mS(); 
            mT(); 
            mR(); 
            mA(); 
            mI(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:602:14: ( C O U N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:602:16: C O U N T
            {
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "DATE"
    public final void mDATE() throws RecognitionException {
        try {
            int _type = DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:603:14: ( D A T E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:603:16: D A T E
            {
            mD(); 
            mA(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DATE"

    // $ANTLR start "DATETIME"
    public final void mDATETIME() throws RecognitionException {
        try {
            int _type = DATETIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:604:14: ( D A T E T I M E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:604:16: D A T E T I M E
            {
            mD(); 
            mA(); 
            mT(); 
            mE(); 
            mT(); 
            mI(); 
            mM(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DATETIME"

    // $ANTLR start "DISTINCT"
    public final void mDISTINCT() throws RecognitionException {
        try {
            int _type = DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:605:14: ( D I S T I N C T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:605:16: D I S T I N C T
            {
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mI(); 
            mN(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DISTINCT"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:606:14: ( D O U B L E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:606:16: D O U B L E
            {
            mD(); 
            mO(); 
            mU(); 
            mB(); 
            mL(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:607:14: ( F A L S E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:607:16: F A L S E
            {
            mF(); 
            mA(); 
            mL(); 
            mS(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:608:14: ( F R O M )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:608:16: F R O M
            {
            mF(); 
            mR(); 
            mO(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:609:14: ( I N )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:609:16: I N
            {
            mI(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "INDEX"
    public final void mINDEX() throws RecognitionException {
        try {
            int _type = INDEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:610:14: ( I N D E X )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:610:16: I N D E X
            {
            mI(); 
            mN(); 
            mD(); 
            mE(); 
            mX(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INDEX"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:611:14: ( I N T E G E R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:611:16: I N T E G E R
            {
            mI(); 
            mN(); 
            mT(); 
            mE(); 
            mG(); 
            mE(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "INTO"
    public final void mINTO() throws RecognitionException {
        try {
            int _type = INTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:612:14: ( I N T O )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:612:16: I N T O
            {
            mI(); 
            mN(); 
            mT(); 
            mO(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTO"

    // $ANTLR start "LONGVARCHAR"
    public final void mLONGVARCHAR() throws RecognitionException {
        try {
            int _type = LONGVARCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:613:14: ( L O N G V A R C H A R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:613:16: L O N G V A R C H A R
            {
            mL(); 
            mO(); 
            mN(); 
            mG(); 
            mV(); 
            mA(); 
            mR(); 
            mC(); 
            mH(); 
            mA(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LONGVARCHAR"

    // $ANTLR start "MODULE"
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:614:14: ( M O D U L E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:614:16: M O D U L E
            {
            mM(); 
            mO(); 
            mD(); 
            mU(); 
            mL(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:615:14: ( N O T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:615:16: N O T
            {
            mN(); 
            mO(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:616:14: ( N U L L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:616:16: N U L L
            {
            mN(); 
            mU(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "NOT_NULL"
    public final void mNOT_NULL() throws RecognitionException {
        try {
            int _type = NOT_NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:617:14: ( NOT ' ' NULL )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:617:16: NOT ' ' NULL
            {
            mNOT(); 
            match(' '); 
            mNULL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT_NULL"

    // $ANTLR start "ONLY"
    public final void mONLY() throws RecognitionException {
        try {
            int _type = ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:618:14: ( O N L Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:618:16: O N L Y
            {
            mO(); 
            mN(); 
            mL(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ONLY"

    // $ANTLR start "PRIMARY_KEY"
    public final void mPRIMARY_KEY() throws RecognitionException {
        try {
            int _type = PRIMARY_KEY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:619:14: ( P R I M A R Y ' ' K E Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:619:16: P R I M A R Y ' ' K E Y
            {
            mP(); 
            mR(); 
            mI(); 
            mM(); 
            mA(); 
            mR(); 
            mY(); 
            match(' '); 
            mK(); 
            mE(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIMARY_KEY"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:620:14: ( R E A L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:620:16: R E A L
            {
            mR(); 
            mE(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "SET"
    public final void mSET() throws RecognitionException {
        try {
            int _type = SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:621:14: ( S E T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:621:16: S E T
            {
            mS(); 
            mE(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SET"

    // $ANTLR start "SMALLINT"
    public final void mSMALLINT() throws RecognitionException {
        try {
            int _type = SMALLINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:622:14: ( S M A L L I N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:622:16: S M A L L I N T
            {
            mS(); 
            mM(); 
            mA(); 
            mL(); 
            mL(); 
            mI(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SMALLINT"

    // $ANTLR start "TABLE"
    public final void mTABLE() throws RecognitionException {
        try {
            int _type = TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:623:14: ( T A B L E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:623:16: T A B L E
            {
            mT(); 
            mA(); 
            mB(); 
            mL(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TABLE"

    // $ANTLR start "TIME"
    public final void mTIME() throws RecognitionException {
        try {
            int _type = TIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:624:14: ( T I M E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:624:16: T I M E
            {
            mT(); 
            mI(); 
            mM(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIME"

    // $ANTLR start "TIMESTAMP"
    public final void mTIMESTAMP() throws RecognitionException {
        try {
            int _type = TIMESTAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:625:14: ( T I M E S T A M P )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:625:16: T I M E S T A M P
            {
            mT(); 
            mI(); 
            mM(); 
            mE(); 
            mS(); 
            mT(); 
            mA(); 
            mM(); 
            mP(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMESTAMP"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:626:14: ( T R U E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:626:16: T R U E
            {
            mT(); 
            mR(); 
            mU(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "VALUES"
    public final void mVALUES() throws RecognitionException {
        try {
            int _type = VALUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:627:14: ( V A L U E S )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:627:16: V A L U E S
            {
            mV(); 
            mA(); 
            mL(); 
            mU(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUES"

    // $ANTLR start "VARCHAR"
    public final void mVARCHAR() throws RecognitionException {
        try {
            int _type = VARCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:628:14: ( V A R C H A R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:628:16: V A R C H A R
            {
            mV(); 
            mA(); 
            mR(); 
            mC(); 
            mH(); 
            mA(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARCHAR"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:629:14: ( W H E R E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:629:16: W H E R E
            {
            mW(); 
            mH(); 
            mE(); 
            mR(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "ASYNC"
    public final void mASYNC() throws RecognitionException {
        try {
            int _type = ASYNC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:632:21: ( A S Y N C )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:632:23: A S Y N C
            {
            mA(); 
            mS(); 
            mY(); 
            mN(); 
            mC(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASYNC"

    // $ANTLR start "CAPACITY"
    public final void mCAPACITY() throws RecognitionException {
        try {
            int _type = CAPACITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:633:21: ( C A P A C I T Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:633:23: C A P A C I T Y
            {
            mC(); 
            mA(); 
            mP(); 
            mA(); 
            mC(); 
            mI(); 
            mT(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CAPACITY"

    // $ANTLR start "CACHE_POLICY"
    public final void mCACHE_POLICY() throws RecognitionException {
        try {
            int _type = CACHE_POLICY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:634:21: ( C A C H E '_' P O L I C Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:634:23: C A C H E '_' P O L I C Y
            {
            mC(); 
            mA(); 
            mC(); 
            mH(); 
            mE(); 
            match('_'); 
            mP(); 
            mO(); 
            mL(); 
            mI(); 
            mC(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CACHE_POLICY"

    // $ANTLR start "DISTRIBUTED"
    public final void mDISTRIBUTED() throws RecognitionException {
        try {
            int _type = DISTRIBUTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:635:21: ( D I S T R I B U T E D )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:635:23: D I S T R I B U T E D
            {
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mR(); 
            mI(); 
            mB(); 
            mU(); 
            mT(); 
            mE(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DISTRIBUTED"

    // $ANTLR start "DISTRIBUTION_POLICY"
    public final void mDISTRIBUTION_POLICY() throws RecognitionException {
        try {
            int _type = DISTRIBUTION_POLICY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:636:21: ( D I S T R I B U T I O N '_' P O L I C Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:636:23: D I S T R I B U T I O N '_' P O L I C Y
            {
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mR(); 
            mI(); 
            mB(); 
            mU(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 
            match('_'); 
            mP(); 
            mO(); 
            mL(); 
            mI(); 
            mC(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DISTRIBUTION_POLICY"

    // $ANTLR start "EVICTION_POLICY"
    public final void mEVICTION_POLICY() throws RecognitionException {
        try {
            int _type = EVICTION_POLICY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:637:21: ( E V I C T I O N '_' P O L I C Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:637:23: E V I C T I O N '_' P O L I C Y
            {
            mE(); 
            mV(); 
            mI(); 
            mC(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 
            match('_'); 
            mP(); 
            mO(); 
            mL(); 
            mI(); 
            mC(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EVICTION_POLICY"

    // $ANTLR start "FILE_SYNC_INTERVAL"
    public final void mFILE_SYNC_INTERVAL() throws RecognitionException {
        try {
            int _type = FILE_SYNC_INTERVAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:638:21: ( F I L E '_' S Y N C '_' I N T E R V A L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:638:23: F I L E '_' S Y N C '_' I N T E R V A L
            {
            mF(); 
            mI(); 
            mL(); 
            mE(); 
            match('_'); 
            mS(); 
            mY(); 
            mN(); 
            mC(); 
            match('_'); 
            mI(); 
            mN(); 
            mT(); 
            mE(); 
            mR(); 
            mV(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILE_SYNC_INTERVAL"

    // $ANTLR start "FORGET_OLD_VALUE"
    public final void mFORGET_OLD_VALUE() throws RecognitionException {
        try {
            int _type = FORGET_OLD_VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:639:21: ( F O R G E T '_' O L D '_' V A L U E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:639:23: F O R G E T '_' O L D '_' V A L U E
            {
            mF(); 
            mO(); 
            mR(); 
            mG(); 
            mE(); 
            mT(); 
            match('_'); 
            mO(); 
            mL(); 
            mD(); 
            match('_'); 
            mV(); 
            mA(); 
            mL(); 
            mU(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FORGET_OLD_VALUE"

    // $ANTLR start "HASH"
    public final void mHASH() throws RecognitionException {
        try {
            int _type = HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:640:21: ( H A S H )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:640:23: H A S H
            {
            mH(); 
            mA(); 
            mS(); 
            mH(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HASH"

    // $ANTLR start "HOST_AWARE_REPLICATION"
    public final void mHOST_AWARE_REPLICATION() throws RecognitionException {
        try {
            int _type = HOST_AWARE_REPLICATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:641:24: ( H O S T '_' A W A R E '_' R E P L I C A T I O N )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:641:26: H O S T '_' A W A R E '_' R E P L I C A T I O N
            {
            mH(); 
            mO(); 
            mS(); 
            mT(); 
            match('_'); 
            mA(); 
            mW(); 
            mA(); 
            mR(); 
            mE(); 
            match('_'); 
            mR(); 
            mE(); 
            mP(); 
            mL(); 
            mI(); 
            mC(); 
            mA(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HOST_AWARE_REPLICATION"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:642:21: ( I S )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:642:23: I S
            {
            mI(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "IS_NULL"
    public final void mIS_NULL() throws RecognitionException {
        try {
            int _type = IS_NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:643:21: ( IS ' ' NULL )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:643:23: IS ' ' NULL
            {
            mIS(); 
            match(' '); 
            mNULL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS_NULL"

    // $ANTLR start "IS_NOT_NULL"
    public final void mIS_NOT_NULL() throws RecognitionException {
        try {
            int _type = IS_NOT_NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:644:21: ( IS ' ' NOT ' ' NULL )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:644:23: IS ' ' NOT ' ' NULL
            {
            mIS(); 
            match(' '); 
            mNOT(); 
            match(' '); 
            mNULL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS_NOT_NULL"

    // $ANTLR start "LIMIT"
    public final void mLIMIT() throws RecognitionException {
        try {
            int _type = LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:645:21: ( L I M I T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:645:23: L I M I T
            {
            mL(); 
            mI(); 
            mM(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LIMIT"

    // $ANTLR start "LOCK_SCOPE"
    public final void mLOCK_SCOPE() throws RecognitionException {
        try {
            int _type = LOCK_SCOPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:646:21: ( L O C K '_' S C O P E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:646:23: L O C K '_' S C O P E
            {
            mL(); 
            mO(); 
            mC(); 
            mK(); 
            match('_'); 
            mS(); 
            mC(); 
            mO(); 
            mP(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOCK_SCOPE"

    // $ANTLR start "LOCK_TTL"
    public final void mLOCK_TTL() throws RecognitionException {
        try {
            int _type = LOCK_TTL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:647:21: ( L O C K '_' T T L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:647:23: L O C K '_' T T L
            {
            mL(); 
            mO(); 
            mC(); 
            mK(); 
            match('_'); 
            mT(); 
            mT(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOCK_TTL"

    // $ANTLR start "LOCK_WAIT"
    public final void mLOCK_WAIT() throws RecognitionException {
        try {
            int _type = LOCK_WAIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:648:21: ( L O C K '_' W A I T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:648:23: L O C K '_' W A I T
            {
            mL(); 
            mO(); 
            mC(); 
            mK(); 
            match('_'); 
            mW(); 
            mA(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOCK_WAIT"

    // $ANTLR start "LRU"
    public final void mLRU() throws RecognitionException {
        try {
            int _type = LRU;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:649:21: ( L R U )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:649:23: L R U
            {
            mL(); 
            mR(); 
            mU(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LRU"

    // $ANTLR start "MIN_SEEDERS"
    public final void mMIN_SEEDERS() throws RecognitionException {
        try {
            int _type = MIN_SEEDERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:650:21: ( M I N '_' S E E D E R S )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:650:23: M I N '_' S E E D E R S
            {
            mM(); 
            mI(); 
            mN(); 
            match('_'); 
            mS(); 
            mE(); 
            mE(); 
            mD(); 
            mE(); 
            mR(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MIN_SEEDERS"

    // $ANTLR start "MULTICAST"
    public final void mMULTICAST() throws RecognitionException {
        try {
            int _type = MULTICAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:651:21: ( M U L T I C A S T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:651:23: M U L T I C A S T
            {
            mM(); 
            mU(); 
            mL(); 
            mT(); 
            mI(); 
            mC(); 
            mA(); 
            mS(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTICAST"

    // $ANTLR start "NON_DISTRIBUTED"
    public final void mNON_DISTRIBUTED() throws RecognitionException {
        try {
            int _type = NON_DISTRIBUTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:652:21: ( N O N '_' D I S T R I B U T E D )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:652:23: N O N '_' D I S T R I B U T E D
            {
            mN(); 
            mO(); 
            mN(); 
            match('_'); 
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mR(); 
            mI(); 
            mB(); 
            mU(); 
            mT(); 
            mE(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NON_DISTRIBUTED"

    // $ANTLR start "NONE"
    public final void mNONE() throws RecognitionException {
        try {
            int _type = NONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:653:21: ( N O N E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:653:23: N O N E
            {
            mN(); 
            mO(); 
            mN(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NONE"

    // $ANTLR start "NOR"
    public final void mNOR() throws RecognitionException {
        try {
            int _type = NOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:654:21: ( N O R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:654:23: N O R
            {
            mN(); 
            mO(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOR"

    // $ANTLR start "OFFSET"
    public final void mOFFSET() throws RecognitionException {
        try {
            int _type = OFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:655:21: ( O F F S E T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:655:23: O F F S E T
            {
            mO(); 
            mF(); 
            mF(); 
            mS(); 
            mE(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OFFSET"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:656:21: ( O R )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:656:23: O R
            {
            mO(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "PERSISTENCE"
    public final void mPERSISTENCE() throws RecognitionException {
        try {
            int _type = PERSISTENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:657:21: ( P E R S I S T E N C E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:657:23: P E R S I S T E N C E
            {
            mP(); 
            mE(); 
            mR(); 
            mS(); 
            mI(); 
            mS(); 
            mT(); 
            mE(); 
            mN(); 
            mC(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERSISTENCE"

    // $ANTLR start "PERSISTENCE_POLICY"
    public final void mPERSISTENCE_POLICY() throws RecognitionException {
        try {
            int _type = PERSISTENCE_POLICY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:658:21: ( P E R S I S T E N C E '_' P O L I C Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:658:23: P E R S I S T E N C E '_' P O L I C Y
            {
            mP(); 
            mE(); 
            mR(); 
            mS(); 
            mI(); 
            mS(); 
            mT(); 
            mE(); 
            mN(); 
            mC(); 
            mE(); 
            match('_'); 
            mP(); 
            mO(); 
            mL(); 
            mI(); 
            mC(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERSISTENCE_POLICY"

    // $ANTLR start "PERSISTENCE_TYPE"
    public final void mPERSISTENCE_TYPE() throws RecognitionException {
        try {
            int _type = PERSISTENCE_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:659:21: ( P E R S I S T E N C E '_' T Y P E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:659:23: P E R S I S T E N C E '_' T Y P E
            {
            mP(); 
            mE(); 
            mR(); 
            mS(); 
            mI(); 
            mS(); 
            mT(); 
            mE(); 
            mN(); 
            mC(); 
            mE(); 
            match('_'); 
            mT(); 
            mY(); 
            mP(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERSISTENCE_TYPE"

    // $ANTLR start "PHASE_COUNT"
    public final void mPHASE_COUNT() throws RecognitionException {
        try {
            int _type = PHASE_COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:660:21: ( P H A S E '_' C O U N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:660:23: P H A S E '_' C O U N T
            {
            mP(); 
            mH(); 
            mA(); 
            mS(); 
            mE(); 
            match('_'); 
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PHASE_COUNT"

    // $ANTLR start "PHASE_INTERVAL"
    public final void mPHASE_INTERVAL() throws RecognitionException {
        try {
            int _type = PHASE_INTERVAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:661:21: ( P H A S E '_' I N T E R V A L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:661:23: P H A S E '_' I N T E R V A L
            {
            mP(); 
            mH(); 
            mA(); 
            mS(); 
            mE(); 
            match('_'); 
            mI(); 
            mN(); 
            mT(); 
            mE(); 
            mR(); 
            mV(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PHASE_INTERVAL"

    // $ANTLR start "PROCESS"
    public final void mPROCESS() throws RecognitionException {
        try {
            int _type = PROCESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:662:21: ( P R O C E S S )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:662:23: P R O C E S S
            {
            mP(); 
            mR(); 
            mO(); 
            mC(); 
            mE(); 
            mS(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROCESS"

    // $ANTLR start "QUERY_LIMIT"
    public final void mQUERY_LIMIT() throws RecognitionException {
        try {
            int _type = QUERY_LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:663:21: ( Q U E R Y '_' L I M I T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:663:23: Q U E R Y '_' L I M I T
            {
            mQ(); 
            mU(); 
            mE(); 
            mR(); 
            mY(); 
            match('_'); 
            mL(); 
            mI(); 
            mM(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUERY_LIMIT"

    // $ANTLR start "QUERY_TIMEOUT"
    public final void mQUERY_TIMEOUT() throws RecognitionException {
        try {
            int _type = QUERY_TIMEOUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:664:21: ( Q U E R Y '_' T I M E O U T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:664:23: Q U E R Y '_' T I M E O U T
            {
            mQ(); 
            mU(); 
            mE(); 
            mR(); 
            mY(); 
            match('_'); 
            mT(); 
            mI(); 
            mM(); 
            mE(); 
            mO(); 
            mU(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUERY_TIMEOUT"

    // $ANTLR start "READ_TIMEOUT"
    public final void mREAD_TIMEOUT() throws RecognitionException {
        try {
            int _type = READ_TIMEOUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:665:21: ( R E A D '_' T I M E O U T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:665:23: R E A D '_' T I M E O U T
            {
            mR(); 
            mE(); 
            mA(); 
            mD(); 
            match('_'); 
            mT(); 
            mI(); 
            mM(); 
            mE(); 
            mO(); 
            mU(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ_TIMEOUT"

    // $ANTLR start "READ_THROUGH"
    public final void mREAD_THROUGH() throws RecognitionException {
        try {
            int _type = READ_THROUGH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:666:21: ( R E A D '_' T H R O U G H )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:666:23: R E A D '_' T H R O U G H
            {
            mR(); 
            mE(); 
            mA(); 
            mD(); 
            match('_'); 
            mT(); 
            mH(); 
            mR(); 
            mO(); 
            mU(); 
            mG(); 
            mH(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ_THROUGH"

    // $ANTLR start "READ_WRITE_THROUGH"
    public final void mREAD_WRITE_THROUGH() throws RecognitionException {
        try {
            int _type = READ_WRITE_THROUGH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:667:21: ( R E A D '_' W R I T E '_' T H R O U G H )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:667:23: R E A D '_' W R I T E '_' T H R O U G H
            {
            mR(); 
            mE(); 
            mA(); 
            mD(); 
            match('_'); 
            mW(); 
            mR(); 
            mI(); 
            mT(); 
            mE(); 
            match('_'); 
            mT(); 
            mH(); 
            mR(); 
            mO(); 
            mU(); 
            mG(); 
            mH(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ_WRITE_THROUGH"

    // $ANTLR start "REPLICATION_COUNT"
    public final void mREPLICATION_COUNT() throws RecognitionException {
        try {
            int _type = REPLICATION_COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:668:21: ( R E P L I C A T I O N '_' C O U N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:668:23: R E P L I C A T I O N '_' C O U N T
            {
            mR(); 
            mE(); 
            mP(); 
            mL(); 
            mI(); 
            mC(); 
            mA(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 
            match('_'); 
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REPLICATION_COUNT"

    // $ANTLR start "REPLICATION_POLICY"
    public final void mREPLICATION_POLICY() throws RecognitionException {
        try {
            int _type = REPLICATION_POLICY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:669:21: ( R E P L I C A T I O N '_' P O L I C Y )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:669:23: R E P L I C A T I O N '_' P O L I C Y
            {
            mR(); 
            mE(); 
            mP(); 
            mL(); 
            mI(); 
            mC(); 
            mA(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 
            match('_'); 
            mP(); 
            mO(); 
            mL(); 
            mI(); 
            mC(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REPLICATION_POLICY"

    // $ANTLR start "ROUTED"
    public final void mROUTED() throws RecognitionException {
        try {
            int _type = ROUTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:670:21: ( R O U T E D )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:670:23: R O U T E D
            {
            mR(); 
            mO(); 
            mU(); 
            mT(); 
            mE(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ROUTED"

    // $ANTLR start "SHARED_ALL"
    public final void mSHARED_ALL() throws RecognitionException {
        try {
            int _type = SHARED_ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:671:21: ( S H A R E D '_' A L L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:671:23: S H A R E D '_' A L L
            {
            mS(); 
            mH(); 
            mA(); 
            mR(); 
            mE(); 
            mD(); 
            match('_'); 
            mA(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHARED_ALL"

    // $ANTLR start "SHARED_NOTHING"
    public final void mSHARED_NOTHING() throws RecognitionException {
        try {
            int _type = SHARED_NOTHING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:672:21: ( S H A R E D '_' N O T H I N G )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:672:23: S H A R E D '_' N O T H I N G
            {
            mS(); 
            mH(); 
            mA(); 
            mR(); 
            mE(); 
            mD(); 
            match('_'); 
            mN(); 
            mO(); 
            mT(); 
            mH(); 
            mI(); 
            mN(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHARED_NOTHING"

    // $ANTLR start "SPACE_WAIT"
    public final void mSPACE_WAIT() throws RecognitionException {
        try {
            int _type = SPACE_WAIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:673:21: ( S P A C E '_' W A I T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:673:23: S P A C E '_' W A I T
            {
            mS(); 
            mP(); 
            mA(); 
            mC(); 
            mE(); 
            match('_'); 
            mW(); 
            mA(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SPACE_WAIT"

    // $ANTLR start "SYNC"
    public final void mSYNC() throws RecognitionException {
        try {
            int _type = SYNC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:674:21: ( S Y N C )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:674:23: S Y N C
            {
            mS(); 
            mY(); 
            mN(); 
            mC(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYNC"

    // $ANTLR start "THREAD"
    public final void mTHREAD() throws RecognitionException {
        try {
            int _type = THREAD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:675:21: ( T H R E A D )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:675:23: T H R E A D
            {
            mT(); 
            mH(); 
            mR(); 
            mE(); 
            mA(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THREAD"

    // $ANTLR start "TREE"
    public final void mTREE() throws RecognitionException {
        try {
            int _type = TREE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:676:21: ( T R E E )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:676:23: T R E E
            {
            mT(); 
            mR(); 
            mE(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TREE"

    // $ANTLR start "TTL"
    public final void mTTL() throws RecognitionException {
        try {
            int _type = TTL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:677:21: ( T T L )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:677:23: T T L
            {
            mT(); 
            mT(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TTL"

    // $ANTLR start "UNICAST"
    public final void mUNICAST() throws RecognitionException {
        try {
            int _type = UNICAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:678:21: ( U N I C A S T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:678:23: U N I C A S T
            {
            mU(); 
            mN(); 
            mI(); 
            mC(); 
            mA(); 
            mS(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNICAST"

    // $ANTLR start "UPDATE_TRANSPORT"
    public final void mUPDATE_TRANSPORT() throws RecognitionException {
        try {
            int _type = UPDATE_TRANSPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:679:21: ( U P D A T E '_' T R A N S P O R T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:679:23: U P D A T E '_' T R A N S P O R T
            {
            mU(); 
            mP(); 
            mD(); 
            mA(); 
            mT(); 
            mE(); 
            match('_'); 
            mT(); 
            mR(); 
            mA(); 
            mN(); 
            mS(); 
            mP(); 
            mO(); 
            mR(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UPDATE_TRANSPORT"

    // $ANTLR start "VIRTUAL_NODE_COUNT"
    public final void mVIRTUAL_NODE_COUNT() throws RecognitionException {
        try {
            int _type = VIRTUAL_NODE_COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:680:21: ( V I R T U A L '_' N O D E '_' C O U N T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:680:23: V I R T U A L '_' N O D E '_' C O U N T
            {
            mV(); 
            mI(); 
            mR(); 
            mT(); 
            mU(); 
            mA(); 
            mL(); 
            match('_'); 
            mN(); 
            mO(); 
            mD(); 
            mE(); 
            match('_'); 
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VIRTUAL_NODE_COUNT"

    // $ANTLR start "WRITE_TIMEOUT"
    public final void mWRITE_TIMEOUT() throws RecognitionException {
        try {
            int _type = WRITE_TIMEOUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:681:21: ( W R I T E '_' T I M E O U T )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:681:23: W R I T E '_' T I M E O U T
            {
            mW(); 
            mR(); 
            mI(); 
            mT(); 
            mE(); 
            match('_'); 
            mT(); 
            mI(); 
            mM(); 
            mE(); 
            mO(); 
            mU(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WRITE_TIMEOUT"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:684:11: ( ( 'a' | 'A' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:684:13: ( 'a' | 'A' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:685:11: ( ( 'b' | 'B' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:685:13: ( 'b' | 'B' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:686:11: ( ( 'c' | 'C' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:686:13: ( 'c' | 'C' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:687:11: ( ( 'd' | 'D' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:687:13: ( 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:688:11: ( ( 'e' | 'E' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:688:13: ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:689:11: ( ( 'f' | 'F' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:689:13: ( 'f' | 'F' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:690:11: ( ( 'g' | 'G' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:690:13: ( 'g' | 'G' )
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:691:11: ( ( 'h' | 'H' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:691:13: ( 'h' | 'H' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:692:11: ( ( 'i' | 'I' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:692:13: ( 'i' | 'I' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:693:11: ( ( 'j' | 'J' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:693:13: ( 'j' | 'J' )
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:694:11: ( ( 'k' | 'K' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:694:13: ( 'k' | 'K' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:695:11: ( ( 'l' | 'L' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:695:13: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:696:11: ( ( 'm' | 'M' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:696:13: ( 'm' | 'M' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:697:11: ( ( 'n' | 'N' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:697:13: ( 'n' | 'N' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:698:11: ( ( 'o' | 'O' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:698:13: ( 'o' | 'O' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:699:11: ( ( 'p' | 'P' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:699:13: ( 'p' | 'P' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:700:11: ( ( 'q' | 'Q' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:700:13: ( 'q' | 'Q' )
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:701:11: ( ( 'r' | 'R' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:701:13: ( 'r' | 'R' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:702:11: ( ( 's' | 'S' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:702:13: ( 's' | 'S' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:703:11: ( ( 't' | 'T' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:703:13: ( 't' | 'T' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:704:11: ( ( 'u' | 'U' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:704:13: ( 'u' | 'U' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:705:11: ( ( 'v' | 'V' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:705:13: ( 'v' | 'V' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:706:11: ( ( 'w' | 'W' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:706:13: ( 'w' | 'W' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:707:11: ( ( 'x' | 'X' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:707:13: ( 'x' | 'X' )
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:708:11: ( ( 'y' | 'Y' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:708:13: ( 'y' | 'Y' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:709:11: ( ( 'z' | 'Z' ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:709:13: ( 'z' | 'Z' )
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Z"

    // $ANTLR start "QuotedString"
    public final void mQuotedString() throws RecognitionException {
        try {
            int _type = QuotedString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            int c;

            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:712:5: ( '\\'' (c=~ ( '\\'' ) | '\\'' '\\'' )* '\\'' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:712:7: '\\'' (c=~ ( '\\'' ) | '\\'' '\\'' )* '\\''
            {
            match('\''); 
             StringBuilder b = new StringBuilder(); 
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:714:7: (c=~ ( '\\'' ) | '\\'' '\\'' )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\'') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='\'') ) {
                        alt2=2;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='&')||(LA2_0>='(' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:714:9: c=~ ( '\\'' )
            	    {
            	    c= input.LA(1);
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}

            	     b.appendCodePoint(c);

            	    }
            	    break;
            	case 2 :
            	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:715:9: '\\'' '\\''
            	    {
            	    match('\''); 
            	    match('\''); 
            	     b.appendCodePoint('\'');

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('\''); 

                    // always enclose the string in double quotes
                    // AS filters always expect the double quotes around strings
                    // we remove the double quotes later where they are not needed
                    setText("\"" + b.toString() + "\"");
                  

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QuotedString"

    // $ANTLR start "ASDateTime"
    public final void mASDateTime() throws RecognitionException {
        try {
            int _type = ASDateTime;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:727:3: ( ( ASDate | ASTime | ASDate 'T' ASTime ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:727:5: ( ASDate | ASTime | ASDate 'T' ASTime )
            {
             StringBuilder d = new StringBuilder(); 
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:728:5: ( ASDate | ASTime | ASDate 'T' ASTime )
            int alt3=3;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:728:7: ASDate
                    {
                    mASDate(); 

                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:728:16: ASTime
                    {
                    mASTime(); 

                    }
                    break;
                case 3 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:728:25: ASDate 'T' ASTime
                    {
                    mASDate(); 
                    match('T'); 
                    mASTime(); 

                    }
                    break;

            }


                  // AS dates are always enclosed in single quotes
                  d.append(getText());
                  setText("\'" + d.toString() + "\'");
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASDateTime"

    // $ANTLR start "ASDate"
    public final void mASDate() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:737:3: ( Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:737:5: Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit
            {
            mDigit(); 
            mDigit(); 
            mDigit(); 
            mDigit(); 
            match('-'); 
            mDigit(); 
            mDigit(); 
            match('-'); 
            mDigit(); 
            mDigit(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "ASDate"

    // $ANTLR start "ASTime"
    public final void mASTime() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:3: ( Digit Digit ':' Digit Digit ':' Digit Digit ( Dot Int )? ( GmtTag )? )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:5: Digit Digit ':' Digit Digit ':' Digit Digit ( Dot Int )? ( GmtTag )?
            {
            mDigit(); 
            mDigit(); 
            match(':'); 
            mDigit(); 
            mDigit(); 
            match(':'); 
            mDigit(); 
            mDigit(); 
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:49: ( Dot Int )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:50: Dot Int
                    {
                    mDot(); 
                    mInt(); 

                    }
                    break;

            }

            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:60: ( GmtTag )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='G'||LA5_0=='Z'||LA5_0=='g') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:741:61: GmtTag
                    {
                    mGmtTag(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "ASTime"

    // $ANTLR start "PositiveNumber"
    public final void mPositiveNumber() throws RecognitionException {
        try {
            int _type = PositiveNumber;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:745:3: ( Int )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:745:5: Int
            {
            mInt(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PositiveNumber"

    // $ANTLR start "NegativeNumber"
    public final void mNegativeNumber() throws RecognitionException {
        try {
            int _type = NegativeNumber;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:749:3: ( '-' Int )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:749:5: '-' Int
            {
            match('-'); 
            mInt(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NegativeNumber"

    // $ANTLR start "Float"
    public final void mFloat() throws RecognitionException {
        try {
            int _type = Float;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:753:3: ( PositiveNumber '.' Digit ( Digit )* ( 'F' | 'f' )? | NegativeNumber '.' Digit ( Digit )* ( 'F' | 'f' )? )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                alt10=1;
            }
            else if ( (LA10_0=='-') ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:753:5: PositiveNumber '.' Digit ( Digit )* ( 'F' | 'f' )?
                    {
                    mPositiveNumber(); 
                    match('.'); 
                    mDigit(); 
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:753:30: ( Digit )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:753:31: Digit
                    	    {
                    	    mDigit(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:753:39: ( 'F' | 'f' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='F'||LA7_0=='f') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:754:5: NegativeNumber '.' Digit ( Digit )* ( 'F' | 'f' )?
                    {
                    mNegativeNumber(); 
                    match('.'); 
                    mDigit(); 
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:754:30: ( Digit )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:754:31: Digit
                    	    {
                    	    mDigit(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:754:39: ( 'F' | 'f' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='F'||LA9_0=='f') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Float"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:758:3: ( IdentifierStart ( IdentifierPart )* | DoubleQuotedIdentifier )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>='A' && LA12_0<='Z')||(LA12_0>='a' && LA12_0<='z')) ) {
                alt12=1;
            }
            else if ( (LA12_0=='\"') ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:758:6: IdentifierStart ( IdentifierPart )*
                    {
                    mIdentifierStart(); 
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:758:22: ( IdentifierPart )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='-'||(LA11_0>='0' && LA11_0<='9')||(LA11_0>='A' && LA11_0<='Z')||LA11_0=='_'||(LA11_0>='a' && LA11_0<='z')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:758:22: IdentifierPart
                    	    {
                    	    mIdentifierPart(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:759:5: DoubleQuotedIdentifier
                    {
                    mDoubleQuotedIdentifier(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "DoubleQuotedIdentifier"
    public final void mDoubleQuotedIdentifier() throws RecognitionException {
        try {
            int c;

            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:763:3: ( '\"' (c=~ ( '\"' ) )* '\"' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:763:5: '\"' (c=~ ( '\"' ) )* '\"'
            {
            match('\"'); 
             StringBuilder b = new StringBuilder(); 
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:765:5: (c=~ ( '\"' ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='!')||(LA13_0>='#' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:765:7: c=~ ( '\"' )
            	    {
            	    c= input.LA(1);
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}

            	     b.appendCodePoint(c); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match('\"'); 
             setText(b.toString()); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DoubleQuotedIdentifier"

    // $ANTLR start "Comment"
    public final void mComment() throws RecognitionException {
        try {
            int _type = Comment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:771:3: ( '//' (~ ( '\\r' | '\\n' ) )* | '/*' ( . )* '*/' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='/') ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1=='/') ) {
                    alt16=1;
                }
                else if ( (LA16_1=='*') ) {
                    alt16=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:771:6: '//' (~ ( '\\r' | '\\n' ) )*
                    {
                    match("//"); 

                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:771:11: (~ ( '\\r' | '\\n' ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:771:11: ~ ( '\\r' | '\\n' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    skip();

                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:772:6: '/*' ( . )* '*/'
                    {
                    match("/*"); 

                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:772:11: ( . )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='*') ) {
                            int LA15_1 = input.LA(2);

                            if ( (LA15_1=='/') ) {
                                alt15=2;
                            }
                            else if ( ((LA15_1>='\u0000' && LA15_1<='.')||(LA15_1>='0' && LA15_1<='\uFFFF')) ) {
                                alt15=1;
                            }


                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<=')')||(LA15_0>='+' && LA15_0<='\uFFFF')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:772:11: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    match("*/"); 

                    skip();

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comment"

    // $ANTLR start "Space"
    public final void mSpace() throws RecognitionException {
        try {
            int _type = Space;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:776:3: ( ( SingleSpace | ControlSpace ) )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:776:6: ( SingleSpace | ControlSpace )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Space"

    // $ANTLR start "Period"
    public final void mPeriod() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:780:3: ( '.' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:780:5: '.'
            {
            match('.'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Period"

    // $ANTLR start "ControlSpace"
    public final void mControlSpace() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:784:3: ( '\\t' | '\\r' | '\\n' | '\\u000C' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "ControlSpace"

    // $ANTLR start "SingleSpace"
    public final void mSingleSpace() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:788:3: ( ' ' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:788:5: ' '
            {
            match(' '); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "SingleSpace"

    // $ANTLR start "IdentifierStart"
    public final void mIdentifierStart() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:792:3: ( 'a' .. 'z' | 'A' .. 'Z' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "IdentifierStart"

    // $ANTLR start "IdentifierPart"
    public final void mIdentifierPart() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:795:3: ( IdentifierStart | '_' | '-' | Digit )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:
            {
            if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "IdentifierPart"

    // $ANTLR start "Int"
    public final void mInt() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:798:3: ( '1' .. '9' ( Digit )* | '0' )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>='1' && LA18_0<='9')) ) {
                alt18=1;
            }
            else if ( (LA18_0=='0') ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:798:6: '1' .. '9' ( Digit )*
                    {
                    matchRange('1','9'); 
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:798:15: ( Digit )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:798:15: Digit
                    	    {
                    	    mDigit(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:799:6: '0'
                    {
                    match('0'); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "Int"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:803:3: ( '0' .. '9' )
            // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:803:6: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    public void mTokens() throws RecognitionException {
        // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:8: ( Asterisk | Dot | OParen | CParen | Comma | SColon | QMark | Plus | Minus | Divide | DVertbar | Range | Equals | NotEquals | GThan | GTEquals | LThan | LTEquals | Underscore | GmtTag | CREATE | SELECT | INSERT | UPDATE | DELETE | ALL | AND | AS | BIGINT | BIT | BLOB | CHAR | CHAR1 | CONSTRAINT | COUNT | DATE | DATETIME | DISTINCT | DOUBLE | FALSE | FROM | IN | INDEX | INTEGER | INTO | LONGVARCHAR | MODULE | NOT | NULL | NOT_NULL | ONLY | PRIMARY_KEY | REAL | SET | SMALLINT | TABLE | TIME | TIMESTAMP | TRUE | VALUES | VARCHAR | WHERE | ASYNC | CAPACITY | CACHE_POLICY | DISTRIBUTED | DISTRIBUTION_POLICY | EVICTION_POLICY | FILE_SYNC_INTERVAL | FORGET_OLD_VALUE | HASH | HOST_AWARE_REPLICATION | IS | IS_NULL | IS_NOT_NULL | LIMIT | LOCK_SCOPE | LOCK_TTL | LOCK_WAIT | LRU | MIN_SEEDERS | MULTICAST | NON_DISTRIBUTED | NONE | NOR | OFFSET | OR | PERSISTENCE | PERSISTENCE_POLICY | PERSISTENCE_TYPE | PHASE_COUNT | PHASE_INTERVAL | PROCESS | QUERY_LIMIT | QUERY_TIMEOUT | READ_TIMEOUT | READ_THROUGH | READ_WRITE_THROUGH | REPLICATION_COUNT | REPLICATION_POLICY | ROUTED | SHARED_ALL | SHARED_NOTHING | SPACE_WAIT | SYNC | THREAD | TREE | TTL | UNICAST | UPDATE_TRANSPORT | VIRTUAL_NODE_COUNT | WRITE_TIMEOUT | QuotedString | ASDateTime | PositiveNumber | NegativeNumber | Float | Identifier | Comment | Space )
        int alt19=120;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:10: Asterisk
                {
                mAsterisk(); 

                }
                break;
            case 2 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:19: Dot
                {
                mDot(); 

                }
                break;
            case 3 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:23: OParen
                {
                mOParen(); 

                }
                break;
            case 4 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:30: CParen
                {
                mCParen(); 

                }
                break;
            case 5 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:37: Comma
                {
                mComma(); 

                }
                break;
            case 6 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:43: SColon
                {
                mSColon(); 

                }
                break;
            case 7 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:50: QMark
                {
                mQMark(); 

                }
                break;
            case 8 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:56: Plus
                {
                mPlus(); 

                }
                break;
            case 9 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:61: Minus
                {
                mMinus(); 

                }
                break;
            case 10 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:67: Divide
                {
                mDivide(); 

                }
                break;
            case 11 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:74: DVertbar
                {
                mDVertbar(); 

                }
                break;
            case 12 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:83: Range
                {
                mRange(); 

                }
                break;
            case 13 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:89: Equals
                {
                mEquals(); 

                }
                break;
            case 14 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:96: NotEquals
                {
                mNotEquals(); 

                }
                break;
            case 15 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:106: GThan
                {
                mGThan(); 

                }
                break;
            case 16 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:112: GTEquals
                {
                mGTEquals(); 

                }
                break;
            case 17 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:121: LThan
                {
                mLThan(); 

                }
                break;
            case 18 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:127: LTEquals
                {
                mLTEquals(); 

                }
                break;
            case 19 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:136: Underscore
                {
                mUnderscore(); 

                }
                break;
            case 20 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:147: GmtTag
                {
                mGmtTag(); 

                }
                break;
            case 21 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:154: CREATE
                {
                mCREATE(); 

                }
                break;
            case 22 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:161: SELECT
                {
                mSELECT(); 

                }
                break;
            case 23 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:168: INSERT
                {
                mINSERT(); 

                }
                break;
            case 24 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:175: UPDATE
                {
                mUPDATE(); 

                }
                break;
            case 25 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:182: DELETE
                {
                mDELETE(); 

                }
                break;
            case 26 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:189: ALL
                {
                mALL(); 

                }
                break;
            case 27 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:193: AND
                {
                mAND(); 

                }
                break;
            case 28 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:197: AS
                {
                mAS(); 

                }
                break;
            case 29 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:200: BIGINT
                {
                mBIGINT(); 

                }
                break;
            case 30 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:207: BIT
                {
                mBIT(); 

                }
                break;
            case 31 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:211: BLOB
                {
                mBLOB(); 

                }
                break;
            case 32 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:216: CHAR
                {
                mCHAR(); 

                }
                break;
            case 33 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:221: CHAR1
                {
                mCHAR1(); 

                }
                break;
            case 34 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:227: CONSTRAINT
                {
                mCONSTRAINT(); 

                }
                break;
            case 35 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:238: COUNT
                {
                mCOUNT(); 

                }
                break;
            case 36 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:244: DATE
                {
                mDATE(); 

                }
                break;
            case 37 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:249: DATETIME
                {
                mDATETIME(); 

                }
                break;
            case 38 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:258: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 39 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:267: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 40 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:274: FALSE
                {
                mFALSE(); 

                }
                break;
            case 41 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:280: FROM
                {
                mFROM(); 

                }
                break;
            case 42 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:285: IN
                {
                mIN(); 

                }
                break;
            case 43 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:288: INDEX
                {
                mINDEX(); 

                }
                break;
            case 44 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:294: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 45 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:302: INTO
                {
                mINTO(); 

                }
                break;
            case 46 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:307: LONGVARCHAR
                {
                mLONGVARCHAR(); 

                }
                break;
            case 47 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:319: MODULE
                {
                mMODULE(); 

                }
                break;
            case 48 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:326: NOT
                {
                mNOT(); 

                }
                break;
            case 49 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:330: NULL
                {
                mNULL(); 

                }
                break;
            case 50 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:335: NOT_NULL
                {
                mNOT_NULL(); 

                }
                break;
            case 51 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:344: ONLY
                {
                mONLY(); 

                }
                break;
            case 52 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:349: PRIMARY_KEY
                {
                mPRIMARY_KEY(); 

                }
                break;
            case 53 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:361: REAL
                {
                mREAL(); 

                }
                break;
            case 54 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:366: SET
                {
                mSET(); 

                }
                break;
            case 55 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:370: SMALLINT
                {
                mSMALLINT(); 

                }
                break;
            case 56 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:379: TABLE
                {
                mTABLE(); 

                }
                break;
            case 57 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:385: TIME
                {
                mTIME(); 

                }
                break;
            case 58 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:390: TIMESTAMP
                {
                mTIMESTAMP(); 

                }
                break;
            case 59 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:400: TRUE
                {
                mTRUE(); 

                }
                break;
            case 60 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:405: VALUES
                {
                mVALUES(); 

                }
                break;
            case 61 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:412: VARCHAR
                {
                mVARCHAR(); 

                }
                break;
            case 62 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:420: WHERE
                {
                mWHERE(); 

                }
                break;
            case 63 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:426: ASYNC
                {
                mASYNC(); 

                }
                break;
            case 64 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:432: CAPACITY
                {
                mCAPACITY(); 

                }
                break;
            case 65 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:441: CACHE_POLICY
                {
                mCACHE_POLICY(); 

                }
                break;
            case 66 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:454: DISTRIBUTED
                {
                mDISTRIBUTED(); 

                }
                break;
            case 67 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:466: DISTRIBUTION_POLICY
                {
                mDISTRIBUTION_POLICY(); 

                }
                break;
            case 68 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:486: EVICTION_POLICY
                {
                mEVICTION_POLICY(); 

                }
                break;
            case 69 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:502: FILE_SYNC_INTERVAL
                {
                mFILE_SYNC_INTERVAL(); 

                }
                break;
            case 70 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:521: FORGET_OLD_VALUE
                {
                mFORGET_OLD_VALUE(); 

                }
                break;
            case 71 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:538: HASH
                {
                mHASH(); 

                }
                break;
            case 72 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:543: HOST_AWARE_REPLICATION
                {
                mHOST_AWARE_REPLICATION(); 

                }
                break;
            case 73 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:566: IS
                {
                mIS(); 

                }
                break;
            case 74 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:569: IS_NULL
                {
                mIS_NULL(); 

                }
                break;
            case 75 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:577: IS_NOT_NULL
                {
                mIS_NOT_NULL(); 

                }
                break;
            case 76 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:589: LIMIT
                {
                mLIMIT(); 

                }
                break;
            case 77 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:595: LOCK_SCOPE
                {
                mLOCK_SCOPE(); 

                }
                break;
            case 78 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:606: LOCK_TTL
                {
                mLOCK_TTL(); 

                }
                break;
            case 79 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:615: LOCK_WAIT
                {
                mLOCK_WAIT(); 

                }
                break;
            case 80 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:625: LRU
                {
                mLRU(); 

                }
                break;
            case 81 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:629: MIN_SEEDERS
                {
                mMIN_SEEDERS(); 

                }
                break;
            case 82 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:641: MULTICAST
                {
                mMULTICAST(); 

                }
                break;
            case 83 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:651: NON_DISTRIBUTED
                {
                mNON_DISTRIBUTED(); 

                }
                break;
            case 84 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:667: NONE
                {
                mNONE(); 

                }
                break;
            case 85 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:672: NOR
                {
                mNOR(); 

                }
                break;
            case 86 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:676: OFFSET
                {
                mOFFSET(); 

                }
                break;
            case 87 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:683: OR
                {
                mOR(); 

                }
                break;
            case 88 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:686: PERSISTENCE
                {
                mPERSISTENCE(); 

                }
                break;
            case 89 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:698: PERSISTENCE_POLICY
                {
                mPERSISTENCE_POLICY(); 

                }
                break;
            case 90 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:717: PERSISTENCE_TYPE
                {
                mPERSISTENCE_TYPE(); 

                }
                break;
            case 91 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:734: PHASE_COUNT
                {
                mPHASE_COUNT(); 

                }
                break;
            case 92 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:746: PHASE_INTERVAL
                {
                mPHASE_INTERVAL(); 

                }
                break;
            case 93 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:761: PROCESS
                {
                mPROCESS(); 

                }
                break;
            case 94 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:769: QUERY_LIMIT
                {
                mQUERY_LIMIT(); 

                }
                break;
            case 95 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:781: QUERY_TIMEOUT
                {
                mQUERY_TIMEOUT(); 

                }
                break;
            case 96 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:795: READ_TIMEOUT
                {
                mREAD_TIMEOUT(); 

                }
                break;
            case 97 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:808: READ_THROUGH
                {
                mREAD_THROUGH(); 

                }
                break;
            case 98 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:821: READ_WRITE_THROUGH
                {
                mREAD_WRITE_THROUGH(); 

                }
                break;
            case 99 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:840: REPLICATION_COUNT
                {
                mREPLICATION_COUNT(); 

                }
                break;
            case 100 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:858: REPLICATION_POLICY
                {
                mREPLICATION_POLICY(); 

                }
                break;
            case 101 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:877: ROUTED
                {
                mROUTED(); 

                }
                break;
            case 102 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:884: SHARED_ALL
                {
                mSHARED_ALL(); 

                }
                break;
            case 103 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:895: SHARED_NOTHING
                {
                mSHARED_NOTHING(); 

                }
                break;
            case 104 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:910: SPACE_WAIT
                {
                mSPACE_WAIT(); 

                }
                break;
            case 105 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:921: SYNC
                {
                mSYNC(); 

                }
                break;
            case 106 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:926: THREAD
                {
                mTHREAD(); 

                }
                break;
            case 107 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:933: TREE
                {
                mTREE(); 

                }
                break;
            case 108 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:938: TTL
                {
                mTTL(); 

                }
                break;
            case 109 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:942: UNICAST
                {
                mUNICAST(); 

                }
                break;
            case 110 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:950: UPDATE_TRANSPORT
                {
                mUPDATE_TRANSPORT(); 

                }
                break;
            case 111 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:967: VIRTUAL_NODE_COUNT
                {
                mVIRTUAL_NODE_COUNT(); 

                }
                break;
            case 112 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:986: WRITE_TIMEOUT
                {
                mWRITE_TIMEOUT(); 

                }
                break;
            case 113 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1000: QuotedString
                {
                mQuotedString(); 

                }
                break;
            case 114 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1013: ASDateTime
                {
                mASDateTime(); 

                }
                break;
            case 115 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1024: PositiveNumber
                {
                mPositiveNumber(); 

                }
                break;
            case 116 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1039: NegativeNumber
                {
                mNegativeNumber(); 

                }
                break;
            case 117 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1054: Float
                {
                mFloat(); 

                }
                break;
            case 118 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1060: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 119 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1071: Comment
                {
                mComment(); 

                }
                break;
            case 120 :
                // C:\\src\\SVN\\as2.1.4\\redist\\examples\\src\\java\\JDBCDriver\\src\\com\\tibco\\as\\sql\\grammar\\ASSQL.g:1:1079: Space
                {
                mSpace(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA3_eotS =
        "\13\uffff\1\14\2\uffff";
    static final String DFA3_eofS =
        "\16\uffff";
    static final String DFA3_minS =
        "\4\60\1\uffff\1\55\2\60\1\55\2\60\1\124\2\uffff";
    static final String DFA3_maxS =
        "\2\71\1\72\1\71\1\uffff\1\55\2\71\1\55\2\71\1\124\2\uffff";
    static final String DFA3_acceptS =
        "\4\uffff\1\2\7\uffff\1\1\1\3";
    static final String DFA3_specialS =
        "\16\uffff}>";
    static final String[] DFA3_transitionS = {
            "\12\1",
            "\12\2",
            "\12\3\1\4",
            "\12\5",
            "",
            "\1\6",
            "\12\7",
            "\12\10",
            "\1\11",
            "\12\12",
            "\12\13",
            "\1\15",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "728:5: ( ASDate | ASTime | ASDate 'T' ASTime )";
        }
    }
    static final String DFA19_eotS =
        "\2\uffff\1\55\6\uffff\1\56\1\62\3\uffff\1\64\1\66\1\uffff\1\67"+
        "\25\52\1\uffff\2\160\5\uffff\2\165\7\uffff\12\52\1\u0083\1\u0087"+
        "\7\52\1\u0090\21\52\1\u00a7\22\52\1\uffff\1\160\2\uffff\1\165\1"+
        "\uffff\1\67\10\52\1\u00c7\3\52\1\uffff\3\52\2\uffff\6\52\1\u00d6"+
        "\1\uffff\1\52\1\u00d8\2\52\1\u00db\4\52\1\u00e0\7\52\1\u00e9\1\u00eb"+
        "\3\52\1\uffff\14\52\1\u00fc\11\52\1\160\4\52\1\u010b\1\52\1\u010e"+
        "\1\52\1\uffff\4\52\1\u0114\2\52\1\uffff\5\52\1\u011f\1\uffff\1\52"+
        "\1\uffff\1\u0122\1\52\1\uffff\1\u0124\3\52\1\uffff\6\52\1\u012e"+
        "\1\52\3\uffff\1\u0130\1\52\1\u0132\4\52\1\u0137\4\52\1\u013c\1\u013d"+
        "\1\u013e\1\52\1\uffff\6\52\1\u0147\2\52\1\160\2\52\1\u014d\1\52"+
        "\2\uffff\1\52\1\uffff\5\52\1\uffff\1\52\1\u0156\2\uffff\6\52\1\uffff"+
        "\1\52\1\u015e\1\uffff\1\52\1\uffff\1\u0160\4\52\1\u0167\3\52\1\uffff"+
        "\1\52\1\uffff\1\52\1\uffff\4\52\1\uffff\4\52\3\uffff\1\52\1\u0177"+
        "\3\52\1\u017b\2\52\1\uffff\2\52\1\160\2\52\1\uffff\1\52\1\u0183"+
        "\1\52\1\u0185\2\52\1\u0188\1\52\1\uffff\1\u018a\1\52\1\u018d\1\u018e"+
        "\3\52\1\uffff\1\u0192\1\uffff\6\52\1\uffff\2\52\1\u019b\1\52\1\u019d"+
        "\7\52\1\u01a7\1\u01a8\1\52\1\uffff\1\52\1\u01ab\1\52\1\uffff\7\52"+
        "\1\uffff\1\52\1\uffff\2\52\1\uffff\1\u01b9\1\uffff\1\52\1\u01bb"+
        "\2\uffff\3\52\1\uffff\10\52\1\uffff\1\52\1\uffff\1\u01c8\10\52\2"+
        "\uffff\1\52\1\u01d2\1\uffff\7\52\1\u01da\4\52\1\u01df\1\uffff\1"+
        "\52\1\uffff\1\52\1\u01e2\1\u01e3\3\52\1\u01e7\5\52\2\uffff\10\52"+
        "\1\uffff\7\52\1\uffff\4\52\1\uffff\2\52\2\uffff\2\52\1\u0205\1\uffff"+
        "\2\52\1\u0208\11\52\1\u0212\7\52\1\u021a\1\u021b\1\u021c\6\52\1"+
        "\uffff\1\u0223\1\52\1\uffff\11\52\1\uffff\7\52\3\uffff\3\52\1\u0238"+
        "\2\52\1\uffff\1\u023b\1\u023c\2\52\1\u023f\1\u0240\11\52\1\u024b"+
        "\1\u024c\3\52\1\uffff\2\52\2\uffff\2\52\2\uffff\2\52\1\u0257\1\u0258"+
        "\6\52\2\uffff\12\52\2\uffff\3\52\1\u026d\2\52\1\u0270\1\u0271\5"+
        "\52\1\u0277\6\52\1\uffff\2\52\2\uffff\4\52\1\u0284\1\uffff\6\52"+
        "\1\u028b\1\52\1\u028d\1\52\1\u028f\1\52\1\uffff\1\52\1\u0292\4\52"+
        "\1\uffff\1\52\1\uffff\1\52\1\uffff\2\52\1\uffff\1\52\1\u029c\4\52"+
        "\1\u02a1\1\u02a2\1\u02a3\1\uffff\1\u02a4\1\u02a5\1\52\1\u02a7\5"+
        "\uffff\1\52\1\uffff\2\52\1\u02ab\1\uffff";
    static final String DFA19_eofS =
        "\u02ac\uffff";
    static final String DFA19_minS =
        "\1\11\1\uffff\1\56\6\uffff\1\60\1\52\3\uffff\2\75\1\uffff\1\55"+
        "\1\115\1\101\1\105\2\116\1\101\1\114\1\111\1\101\2\111\1\117\1\106"+
        "\2\105\2\101\1\110\1\126\1\101\1\125\1\uffff\2\56\5\uffff\2\56\7"+
        "\uffff\1\124\1\103\1\116\1\101\1\105\1\116\1\101\1\114\2\101\1\55"+
        "\1\40\1\104\1\111\1\125\1\114\1\123\1\124\1\104\1\55\1\114\1\117"+
        "\1\107\1\117\1\114\1\122\1\114\1\125\1\103\1\115\1\114\1\116\1\104"+
        "\1\116\1\114\1\106\1\114\1\55\1\111\1\101\1\122\1\101\1\125\1\122"+
        "\1\105\1\115\1\102\2\114\1\122\1\105\2\111\2\123\1\105\1\uffff\1"+
        "\56\2\uffff\1\56\1\uffff\1\55\1\110\1\101\1\116\1\123\1\122\1\101"+
        "\2\103\1\55\1\105\1\122\1\114\1\uffff\3\105\1\uffff\1\116\1\101"+
        "\1\103\1\102\1\105\1\124\1\105\1\55\1\uffff\1\116\1\55\1\102\1\111"+
        "\1\55\1\115\1\123\1\107\1\105\1\55\1\113\1\107\1\111\1\124\1\137"+
        "\1\125\1\105\1\40\1\55\1\114\1\123\1\131\1\uffff\1\103\1\115\2\123"+
        "\1\104\1\114\1\124\4\105\1\114\1\55\1\103\1\125\1\124\1\122\1\124"+
        "\1\103\1\110\1\124\1\122\1\56\1\105\1\103\2\124\1\50\1\124\1\55"+
        "\1\105\1\uffff\1\103\1\105\1\114\1\122\1\55\1\107\1\130\1\117\1"+
        "\124\1\101\1\114\1\124\1\111\1\55\1\uffff\1\103\1\uffff\1\55\1\116"+
        "\1\uffff\1\55\2\105\1\137\1\uffff\1\137\1\126\1\124\1\111\1\123"+
        "\1\114\1\55\1\104\3\uffff\1\55\1\105\1\55\1\105\1\101\1\105\1\111"+
        "\1\55\1\137\1\111\1\105\1\101\3\55\1\105\1\uffff\1\110\1\105\1\125"+
        "\2\105\1\124\1\55\1\137\1\131\1\55\1\137\1\111\1\55\1\122\2\uffff"+
        "\1\105\1\uffff\1\137\1\124\1\104\1\111\1\124\1\uffff\1\105\1\55"+
        "\2\uffff\1\105\1\123\2\105\1\111\1\116\1\uffff\1\111\1\55\1\uffff"+
        "\1\124\1\uffff\1\55\1\124\2\123\1\101\1\55\1\103\2\105\1\uffff\1"+
        "\111\1\uffff\1\124\1\uffff\1\123\1\122\1\137\1\123\1\uffff\1\124"+
        "\1\103\2\104\3\uffff\1\124\1\55\1\101\1\123\1\101\1\55\1\137\1\111"+
        "\1\uffff\1\101\1\137\1\56\1\120\1\124\1\uffff\1\101\1\55\1\127\1"+
        "\55\1\137\1\116\1\55\1\122\1\uffff\1\55\1\124\2\55\1\102\1\103\1"+
        "\115\1\uffff\1\55\1\uffff\1\137\1\131\1\101\1\124\1\103\1\122\1"+
        "\uffff\1\101\1\105\1\55\1\123\1\55\1\123\1\131\1\103\1\124\1\122"+
        "\1\110\1\101\2\55\1\101\1\uffff\1\122\1\55\1\114\1\uffff\1\124\1"+
        "\117\1\127\1\114\1\117\1\131\1\111\1\uffff\1\101\1\uffff\1\101\1"+
        "\124\1\uffff\1\55\1\uffff\1\124\1\55\2\uffff\1\125\1\124\1\105\1"+
        "\uffff\1\117\1\116\1\111\1\114\1\117\1\103\1\123\1\104\1\uffff\1"+
        "\124\1\uffff\1\55\1\40\1\116\1\117\1\105\1\111\1\115\1\122\1\124"+
        "\2\uffff\1\115\1\55\1\uffff\1\137\1\111\1\116\1\101\2\111\1\114"+
        "\1\55\1\116\1\111\1\114\1\117\1\55\1\uffff\1\122\1\uffff\1\124\2"+
        "\55\1\114\1\103\1\124\1\55\1\120\1\110\1\124\1\105\1\122\2\uffff"+
        "\1\124\1\125\1\116\1\124\1\105\1\117\1\111\1\120\1\uffff\1\116\1"+
        "\115\1\137\1\122\2\115\1\111\1\uffff\2\124\1\114\1\124\1\uffff\1"+
        "\101\1\105\2\uffff\1\104\1\137\1\55\1\uffff\1\105\1\101\1\55\1\122"+
        "\1\111\1\105\1\116\1\103\1\105\1\117\1\125\1\117\1\55\1\117\1\105"+
        "\1\120\2\105\1\111\1\103\3\55\1\110\1\116\1\117\1\104\1\137\1\111"+
        "\1\uffff\1\55\1\122\1\uffff\1\123\1\102\1\122\1\124\1\105\1\137"+
        "\1\125\1\107\1\116\1\uffff\1\104\2\117\1\137\1\117\1\124\1\131\3"+
        "\uffff\1\111\1\123\1\116\1\55\1\126\1\116\1\uffff\2\55\1\125\1\126"+
        "\2\55\2\124\1\110\1\137\1\105\1\125\1\114\1\122\1\125\2\55\1\116"+
        "\1\120\1\137\1\uffff\1\101\1\124\2\uffff\1\124\1\101\2\uffff\1\120"+
        "\1\110\2\55\1\103\1\137\1\124\1\111\1\105\1\124\2\uffff\1\107\1"+
        "\117\1\120\1\114\2\105\1\114\1\117\1\131\1\122\2\uffff\2\117\1\103"+
        "\1\55\1\103\1\120\2\55\1\122\1\117\1\125\1\122\1\104\1\55\1\114"+
        "\1\120\1\117\1\125\1\114\1\117\1\uffff\1\131\1\114\2\uffff\1\124"+
        "\1\114\1\105\1\126\1\55\1\uffff\1\111\1\105\1\125\1\116\1\111\1"+
        "\125\1\55\1\111\1\55\1\111\1\55\1\101\1\uffff\1\103\1\55\1\107\1"+
        "\124\1\103\1\116\1\uffff\1\103\1\uffff\1\103\1\uffff\1\114\1\131"+
        "\1\uffff\1\110\1\55\1\131\1\124\1\101\1\131\3\55\1\uffff\2\55\1"+
        "\124\1\55\5\uffff\1\111\1\uffff\1\117\1\116\1\55\1\uffff";
    static final String DFA19_maxS =
        "\1\174\1\uffff\1\56\6\uffff\1\71\1\57\3\uffff\2\75\1\uffff\1\172"+
        "\1\155\1\162\1\171\1\163\1\160\1\157\1\163\1\154\2\162\2\165\2\162"+
        "\1\157\1\164\1\151\1\162\1\166\1\157\1\165\1\uffff\2\71\5\uffff"+
        "\1\71\1\56\7\uffff\1\164\1\160\1\165\1\141\1\145\1\156\1\141\1\164"+
        "\2\141\2\172\1\144\1\151\1\165\1\154\1\163\1\164\1\144\1\172\1\154"+
        "\1\157\1\164\1\157\1\154\1\162\1\154\1\165\1\156\1\155\1\154\1\156"+
        "\1\144\1\164\1\154\1\146\1\154\1\172\1\157\1\141\1\162\1\160\1\165"+
        "\1\162\1\165\1\155\1\142\1\154\2\162\1\145\2\151\2\163\1\145\1\uffff"+
        "\1\72\2\uffff\1\71\1\uffff\1\172\1\150\1\141\1\156\1\163\1\162\1"+
        "\141\2\143\1\172\1\145\1\162\1\154\1\uffff\1\145\1\157\1\145\1\uffff"+
        "\1\156\1\141\1\143\1\142\1\145\1\164\1\145\1\172\1\uffff\1\156\1"+
        "\172\1\142\1\151\1\172\1\155\1\163\1\147\1\145\1\172\1\153\1\147"+
        "\1\151\1\164\1\137\1\165\1\145\2\172\1\154\1\163\1\171\1\uffff\1"+
        "\143\1\155\2\163\2\154\1\164\4\145\1\154\1\172\1\143\1\165\1\164"+
        "\1\162\1\164\1\143\1\150\1\164\1\162\1\71\1\145\1\143\2\164\1\172"+
        "\1\164\1\172\1\145\1\uffff\1\143\1\145\1\154\1\162\1\172\1\147\1"+
        "\170\1\165\1\164\1\141\1\154\1\164\1\162\1\172\1\uffff\1\143\1\uffff"+
        "\1\172\1\156\1\uffff\1\172\2\145\1\137\1\uffff\1\137\1\166\1\164"+
        "\1\151\1\163\1\154\1\172\1\144\3\uffff\1\172\1\145\1\172\1\145\1"+
        "\141\1\145\1\151\1\172\1\137\1\151\1\145\1\141\3\172\1\145\1\uffff"+
        "\1\150\1\145\1\165\2\145\1\164\1\172\1\137\1\171\1\71\1\137\1\151"+
        "\1\172\1\162\2\uffff\1\145\1\uffff\1\137\1\164\1\144\1\151\1\164"+
        "\1\uffff\1\145\1\172\2\uffff\1\145\1\163\2\145\1\151\1\156\1\uffff"+
        "\1\151\1\172\1\uffff\1\164\1\uffff\1\172\1\164\1\163\1\167\1\141"+
        "\1\172\1\143\2\145\1\uffff\1\151\1\uffff\1\164\1\uffff\1\163\1\162"+
        "\1\137\1\163\1\uffff\1\167\1\143\2\144\3\uffff\1\164\1\172\1\141"+
        "\1\163\1\141\1\172\1\137\1\151\1\uffff\1\141\1\137\1\71\1\160\1"+
        "\164\1\uffff\1\141\1\172\1\167\1\172\1\137\1\156\1\172\1\162\1\uffff"+
        "\1\172\1\164\2\172\1\142\1\143\1\155\1\uffff\1\172\1\uffff\1\137"+
        "\1\171\1\141\1\164\1\143\1\162\1\uffff\1\141\1\145\1\172\1\163\1"+
        "\172\1\163\1\171\1\151\1\164\1\162\1\151\1\141\2\172\1\141\1\uffff"+
        "\1\162\1\172\1\154\1\uffff\1\164\1\157\1\167\1\164\1\157\1\171\1"+
        "\151\1\uffff\1\141\1\uffff\1\156\1\164\1\uffff\1\172\1\uffff\1\164"+
        "\1\172\2\uffff\1\165\1\164\1\145\1\uffff\1\157\1\156\1\151\1\154"+
        "\1\157\1\143\1\163\1\144\1\uffff\1\164\1\uffff\1\172\1\40\1\156"+
        "\1\157\1\145\1\151\1\155\1\162\1\164\2\uffff\1\155\1\172\1\uffff"+
        "\1\137\1\151\1\156\1\141\2\151\1\154\1\172\1\156\1\151\1\154\1\157"+
        "\1\172\1\uffff\1\162\1\uffff\1\164\2\172\1\154\1\143\1\164\1\172"+
        "\1\160\1\150\1\164\1\145\1\162\2\uffff\1\164\1\165\1\156\1\164\1"+
        "\145\1\157\1\151\1\160\1\uffff\1\156\1\155\1\137\1\162\2\155\1\151"+
        "\1\uffff\2\164\1\154\1\164\1\uffff\1\141\1\151\2\uffff\1\144\1\137"+
        "\1\172\1\uffff\1\145\1\141\1\172\1\162\1\151\1\145\1\156\1\143\1"+
        "\145\1\157\1\165\1\157\1\172\1\157\1\145\1\160\2\145\1\151\1\143"+
        "\3\172\1\150\1\156\1\157\1\144\1\137\1\151\1\uffff\1\172\1\162\1"+
        "\uffff\1\163\1\142\1\162\1\164\1\145\1\137\1\165\1\147\1\156\1\uffff"+
        "\1\144\2\157\1\137\1\157\1\164\1\171\3\uffff\1\151\1\163\1\156\1"+
        "\172\1\166\1\156\1\uffff\2\172\1\165\1\166\2\172\2\164\1\150\1\137"+
        "\1\145\1\165\1\154\1\162\1\165\2\172\1\156\1\160\1\137\1\uffff\1"+
        "\141\1\164\2\uffff\1\164\1\141\2\uffff\1\164\1\150\2\172\1\160\1"+
        "\137\1\164\1\151\1\145\1\164\2\uffff\1\147\1\157\1\160\1\154\2\145"+
        "\1\154\1\157\1\171\1\162\2\uffff\2\157\1\143\1\172\1\143\1\160\2"+
        "\172\1\162\1\157\1\165\1\162\1\144\1\172\1\154\1\160\1\157\1\165"+
        "\1\154\1\157\1\uffff\1\171\1\154\2\uffff\1\164\1\154\1\145\1\166"+
        "\1\172\1\uffff\1\151\1\145\1\165\1\156\1\151\1\165\1\172\1\151\1"+
        "\172\1\151\1\172\1\141\1\uffff\1\143\1\172\1\147\1\164\1\143\1\156"+
        "\1\uffff\1\143\1\uffff\1\143\1\uffff\1\154\1\171\1\uffff\1\150\1"+
        "\172\1\171\1\164\1\141\1\171\3\172\1\uffff\2\172\1\164\1\172\5\uffff"+
        "\1\151\1\uffff\1\157\1\156\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\2\uffff\1\13\1\15"+
        "\1\16\2\uffff\1\23\26\uffff\1\161\2\uffff\1\166\1\170\1\14\1\2\1"+
        "\11\2\uffff\1\167\1\12\1\20\1\17\1\22\1\21\1\24\70\uffff\1\163\1"+
        "\uffff\1\165\1\162\1\uffff\1\164\15\uffff\1\52\3\uffff\1\111\10"+
        "\uffff\1\34\26\uffff\1\127\37\uffff\1\66\16\uffff\1\33\1\uffff\1"+
        "\32\2\uffff\1\36\4\uffff\1\120\10\uffff\1\60\1\62\1\125\20\uffff"+
        "\1\154\16\uffff\1\40\1\41\1\uffff\1\151\5\uffff\1\55\2\uffff\1\113"+
        "\1\112\6\uffff\1\44\2\uffff\1\37\1\uffff\1\51\11\uffff\1\124\1\uffff"+
        "\1\61\1\uffff\1\63\4\uffff\1\65\4\uffff\1\73\1\153\1\71\10\uffff"+
        "\1\107\5\uffff\1\43\10\uffff\1\53\7\uffff\1\77\1\uffff\1\50\6\uffff"+
        "\1\114\17\uffff\1\70\3\uffff\1\76\7\uffff\1\25\1\uffff\1\26\2\uffff"+
        "\1\27\1\uffff\1\30\2\uffff\1\47\1\31\3\uffff\1\35\10\uffff\1\57"+
        "\1\uffff\1\126\11\uffff\1\145\1\152\2\uffff\1\74\15\uffff\1\54\1"+
        "\uffff\1\155\14\uffff\1\135\1\64\10\uffff\1\75\7\uffff\1\100\4\uffff"+
        "\1\67\2\uffff\1\46\1\45\3\uffff\1\116\35\uffff\1\117\2\uffff\1\122"+
        "\11\uffff\1\72\7\uffff\1\42\1\150\1\146\6\uffff\1\115\24\uffff\1"+
        "\102\2\uffff\1\56\1\121\2\uffff\1\133\1\130\12\uffff\1\136\1\101"+
        "\12\uffff\1\140\1\141\24\uffff\1\160\2\uffff\1\137\1\147\5\uffff"+
        "\1\134\14\uffff\1\123\6\uffff\1\104\1\uffff\1\156\1\uffff\1\106"+
        "\2\uffff\1\132\11\uffff\1\143\4\uffff\1\105\1\131\1\142\1\144\1"+
        "\157\1\uffff\1\103\3\uffff\1\110";
    static final String DFA19_specialS =
        "\u02ac\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\53\1\uffff\2\53\22\uffff\1\53\1\15\1\52\4\uffff\1\47\1\3"+
            "\1\4\1\1\1\10\1\5\1\11\1\2\1\12\1\51\11\50\1\uffff\1\6\1\17"+
            "\1\14\1\16\1\7\1\uffff\1\30\1\31\1\23\1\27\1\44\1\32\1\22\1"+
            "\45\1\25\2\52\1\33\1\34\1\35\1\36\1\37\1\46\1\40\1\24\1\41\1"+
            "\26\1\42\1\43\2\52\1\21\4\uffff\1\20\1\uffff\1\30\1\31\1\23"+
            "\1\27\1\44\1\32\1\22\1\45\1\25\2\52\1\33\1\34\1\35\1\36\1\37"+
            "\1\46\1\40\1\24\1\41\1\26\1\42\1\43\3\52\1\uffff\1\13",
            "",
            "\1\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\60\11\57",
            "\1\61\4\uffff\1\61",
            "",
            "",
            "",
            "\1\63",
            "\1\65",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\70\37\uffff\1\70",
            "\1\71\6\uffff\1\73\6\uffff\1\72\2\uffff\1\74\16\uffff\1\71"+
            "\6\uffff\1\73\6\uffff\1\72\2\uffff\1\74",
            "\1\77\2\uffff\1\100\4\uffff\1\101\2\uffff\1\76\10\uffff\1"+
            "\75\13\uffff\1\77\2\uffff\1\100\4\uffff\1\101\2\uffff\1\76\10"+
            "\uffff\1\75",
            "\1\102\4\uffff\1\103\32\uffff\1\102\4\uffff\1\103",
            "\1\105\1\uffff\1\104\35\uffff\1\105\1\uffff\1\104",
            "\1\111\3\uffff\1\107\3\uffff\1\110\5\uffff\1\106\21\uffff"+
            "\1\111\3\uffff\1\107\3\uffff\1\110\5\uffff\1\106",
            "\1\114\1\uffff\1\112\4\uffff\1\113\30\uffff\1\114\1\uffff"+
            "\1\112\4\uffff\1\113",
            "\1\116\2\uffff\1\115\34\uffff\1\116\2\uffff\1\115",
            "\1\120\7\uffff\1\122\5\uffff\1\121\2\uffff\1\117\16\uffff"+
            "\1\120\7\uffff\1\122\5\uffff\1\121\2\uffff\1\117",
            "\1\125\5\uffff\1\124\2\uffff\1\123\26\uffff\1\125\5\uffff"+
            "\1\124\2\uffff\1\123",
            "\1\127\5\uffff\1\130\5\uffff\1\126\23\uffff\1\127\5\uffff"+
            "\1\130\5\uffff\1\126",
            "\1\131\5\uffff\1\132\31\uffff\1\131\5\uffff\1\132",
            "\1\133\7\uffff\1\134\3\uffff\1\135\23\uffff\1\133\7\uffff"+
            "\1\134\3\uffff\1\135",
            "\1\140\2\uffff\1\137\11\uffff\1\136\22\uffff\1\140\2\uffff"+
            "\1\137\11\uffff\1\136",
            "\1\141\11\uffff\1\142\25\uffff\1\141\11\uffff\1\142",
            "\1\146\6\uffff\1\143\1\145\10\uffff\1\144\1\uffff\1\147\14"+
            "\uffff\1\146\6\uffff\1\143\1\145\10\uffff\1\144\1\uffff\1\147",
            "\1\150\7\uffff\1\151\27\uffff\1\150\7\uffff\1\151",
            "\1\152\11\uffff\1\153\25\uffff\1\152\11\uffff\1\153",
            "\1\154\37\uffff\1\154",
            "\1\155\15\uffff\1\156\21\uffff\1\155\15\uffff\1\156",
            "\1\157\37\uffff\1\157",
            "",
            "\1\162\1\uffff\12\161",
            "\1\162\1\uffff\12\163",
            "",
            "",
            "",
            "",
            "",
            "\1\162\1\uffff\12\164",
            "\1\162",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\166\37\uffff\1\166",
            "\1\167\14\uffff\1\170\22\uffff\1\167\14\uffff\1\170",
            "\1\172\6\uffff\1\171\30\uffff\1\172\6\uffff\1\171",
            "\1\173\37\uffff\1\173",
            "\1\174\37\uffff\1\174",
            "\1\175\37\uffff\1\175",
            "\1\176\37\uffff\1\176",
            "\1\u0080\7\uffff\1\177\27\uffff\1\u0080\7\uffff\1\177",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\52\2\uffff\12\52\7\uffff\3\52\1\u0086\16\52\1\u0084\1\u0085"+
            "\6\52\4\uffff\1\52\1\uffff\3\52\1\u0086\16\52\1\u0084\1\u0085"+
            "\6\52",
            "\1\u0088\14\uffff\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff"+
            "\1\52\1\uffff\32\52",
            "\1\u0089\37\uffff\1\u0089",
            "\1\u008a\37\uffff\1\u008a",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\52\2\uffff\12\52\7\uffff\30\52\1\u0091\1\52\4\uffff\1\52"+
            "\1\uffff\30\52\1\u0091\1\52",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\14\uffff\1\u0095\22\uffff\1\u0094\14\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\12\uffff\1\u009c\24\uffff\1\u009b\12\uffff\1\u009c",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\3\uffff\1\u00a3\1\uffff\1\u00a2\31\uffff\1\u00a1"+
            "\3\uffff\1\u00a3\1\uffff\1\u00a2",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00a9\5\uffff\1\u00a8\31\uffff\1\u00a9\5\uffff\1\u00a8",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\u00ac\16\uffff\1\u00ad\20\uffff\1\u00ac\16\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b1\17\uffff\1\u00b0\17\uffff\1\u00b1\17\uffff\1\u00b0",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b6\5\uffff\1\u00b5\31\uffff\1\u00b6\5\uffff\1\u00b5",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "",
            "\1\162\1\uffff\12\u00be\1\163",
            "",
            "",
            "\1\162\1\uffff\12\164",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c3\37\uffff\1\u00c3",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c6\37\uffff\1\u00c6",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cd\11\uffff\1\u00cc\25\uffff\1\u00cd\11\uffff\1\u00cc",
            "\1\u00ce\37\uffff\1\u00ce",
            "",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00dc\37\uffff\1\u00dc",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00de\37\uffff\1\u00de",
            "\1\u00df\37\uffff\1\u00df",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\31\uffff\1\u00e8\5\uffff\1\u00e7",
            "\1\u00ea\14\uffff\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff"+
            "\1\52\1\uffff\32\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\u00f2\37\uffff\1\u00f2",
            "\1\u00f4\7\uffff\1\u00f3\27\uffff\1\u00f4\7\uffff\1\u00f3",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\u00f9\37\uffff\1\u00f9",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u00fd\37\uffff\1\u00fd",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "\1\u0102\37\uffff\1\u0102",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\162\1\uffff\12\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "\1\u0108\37\uffff\1\u0108",
            "\1\u0109\37\uffff\1\u0109",
            "\1\u010a\37\uffff\1\u010a",
            "\1\u010c\4\uffff\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff"+
            "\1\52\1\uffff\32\52",
            "\1\u010d\37\uffff\1\u010d",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u010f\37\uffff\1\u010f",
            "",
            "\1\u0110\37\uffff\1\u0110",
            "\1\u0111\37\uffff\1\u0111",
            "\1\u0112\37\uffff\1\u0112",
            "\1\u0113\37\uffff\1\u0113",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0116\37\uffff\1\u0116",
            "\1\u0117\5\uffff\1\u0118\31\uffff\1\u0117\5\uffff\1\u0118",
            "\1\u0119\37\uffff\1\u0119",
            "\1\u011a\37\uffff\1\u011a",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011e\10\uffff\1\u011d\26\uffff\1\u011e\10\uffff\1\u011d",
            "\1\52\2\uffff\12\52\7\uffff\23\52\1\u0120\6\52\4\uffff\1\52"+
            "\1\uffff\23\52\1\u0120\6\52",
            "",
            "\1\u0121\37\uffff\1\u0121",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0123\37\uffff\1\u0123",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "\1\u0127",
            "",
            "\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\u012c\37\uffff\1\u012c",
            "\1\u012d\37\uffff\1\u012d",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u012f\37\uffff\1\u012f",
            "",
            "",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0131\37\uffff\1\u0131",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0133\37\uffff\1\u0133",
            "\1\u0134\37\uffff\1\u0134",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0136\37\uffff\1\u0136",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\u013a\37\uffff\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\22\52\1\u013f\7\52\4\uffff\1\52"+
            "\1\uffff\22\52\1\u013f\7\52",
            "\1\u0140\37\uffff\1\u0140",
            "",
            "\1\u0141\37\uffff\1\u0141",
            "\1\u0142\37\uffff\1\u0142",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\u0145\37\uffff\1\u0145",
            "\1\u0146\37\uffff\1\u0146",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0148",
            "\1\u0149\37\uffff\1\u0149",
            "\1\163\1\162\1\uffff\12\u014a",
            "\1\u014b",
            "\1\u014c\37\uffff\1\u014c",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u014e\37\uffff\1\u014e",
            "",
            "",
            "\1\u014f\37\uffff\1\u014f",
            "",
            "\1\u0150",
            "\1\u0151\37\uffff\1\u0151",
            "\1\u0152\37\uffff\1\u0152",
            "\1\u0153\37\uffff\1\u0153",
            "\1\u0154\37\uffff\1\u0154",
            "",
            "\1\u0155\37\uffff\1\u0155",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\u0159\37\uffff\1\u0159",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\u015c\37\uffff\1\u015c",
            "",
            "\1\u015d\37\uffff\1\u015d",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u015f\37\uffff\1\u015f",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0161\37\uffff\1\u0161",
            "\1\u0162\37\uffff\1\u0162",
            "\1\u0165\1\u0164\2\uffff\1\u0163\33\uffff\1\u0165\1\u0164"+
            "\2\uffff\1\u0163",
            "\1\u0166\37\uffff\1\u0166",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0168\37\uffff\1\u0168",
            "\1\u0169\37\uffff\1\u0169",
            "\1\u016a\37\uffff\1\u016a",
            "",
            "\1\u016b\37\uffff\1\u016b",
            "",
            "\1\u016c\37\uffff\1\u016c",
            "",
            "\1\u016d\37\uffff\1\u016d",
            "\1\u016e\37\uffff\1\u016e",
            "\1\u016f",
            "\1\u0170\37\uffff\1\u0170",
            "",
            "\1\u0172\2\uffff\1\u0171\34\uffff\1\u0172\2\uffff\1\u0171",
            "\1\u0173\37\uffff\1\u0173",
            "\1\u0174\37\uffff\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "",
            "",
            "",
            "\1\u0176\37\uffff\1\u0176",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0178\37\uffff\1\u0178",
            "\1\u0179\37\uffff\1\u0179",
            "\1\u017a\37\uffff\1\u017a",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u017c",
            "\1\u017d\37\uffff\1\u017d",
            "",
            "\1\u017e\37\uffff\1\u017e",
            "\1\u017f",
            "\1\162\1\uffff\12\u014a",
            "\1\u0180\37\uffff\1\u0180",
            "\1\u0181\37\uffff\1\u0181",
            "",
            "\1\u0182\37\uffff\1\u0182",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0184\37\uffff\1\u0184",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0186",
            "\1\u0187\37\uffff\1\u0187",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0189\37\uffff\1\u0189",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\u018b\1\uffff"+
            "\32\52",
            "\1\u018c\37\uffff\1\u018c",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u018f\37\uffff\1\u018f",
            "\1\u0190\37\uffff\1\u0190",
            "\1\u0191\37\uffff\1\u0191",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u0193",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "\1\u0196\37\uffff\1\u0196",
            "\1\u0197\37\uffff\1\u0197",
            "\1\u0198\37\uffff\1\u0198",
            "",
            "\1\u0199\37\uffff\1\u0199",
            "\1\u019a\37\uffff\1\u019a",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u019c\37\uffff\1\u019c",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u019e\37\uffff\1\u019e",
            "\1\u019f\37\uffff\1\u019f",
            "\1\u01a1\5\uffff\1\u01a0\31\uffff\1\u01a1\5\uffff\1\u01a0",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\u01a3\37\uffff\1\u01a3",
            "\1\u01a5\1\u01a4\36\uffff\1\u01a5\1\u01a4",
            "\1\u01a6\37\uffff\1\u01a6",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01a9\37\uffff\1\u01a9",
            "",
            "\1\u01aa\37\uffff\1\u01aa",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01ac\37\uffff\1\u01ac",
            "",
            "\1\u01ad\37\uffff\1\u01ad",
            "\1\u01ae\37\uffff\1\u01ae",
            "\1\u01af\37\uffff\1\u01af",
            "\1\u01b1\7\uffff\1\u01b0\27\uffff\1\u01b1\7\uffff\1\u01b0",
            "\1\u01b2\37\uffff\1\u01b2",
            "\1\u01b3\37\uffff\1\u01b3",
            "\1\u01b4\37\uffff\1\u01b4",
            "",
            "\1\u01b5\37\uffff\1\u01b5",
            "",
            "\1\u01b6\14\uffff\1\u01b7\22\uffff\1\u01b6\14\uffff\1\u01b7",
            "\1\u01b8\37\uffff\1\u01b8",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u01ba\37\uffff\1\u01ba",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "",
            "\1\u01bc\37\uffff\1\u01bc",
            "\1\u01bd\37\uffff\1\u01bd",
            "\1\u01be\37\uffff\1\u01be",
            "",
            "\1\u01bf\37\uffff\1\u01bf",
            "\1\u01c0\37\uffff\1\u01c0",
            "\1\u01c1\37\uffff\1\u01c1",
            "\1\u01c2\37\uffff\1\u01c2",
            "\1\u01c3\37\uffff\1\u01c3",
            "\1\u01c4\37\uffff\1\u01c4",
            "\1\u01c5\37\uffff\1\u01c5",
            "\1\u01c6\37\uffff\1\u01c6",
            "",
            "\1\u01c7\37\uffff\1\u01c7",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01c9",
            "\1\u01ca\37\uffff\1\u01ca",
            "\1\u01cb\37\uffff\1\u01cb",
            "\1\u01cc\37\uffff\1\u01cc",
            "\1\u01cd\37\uffff\1\u01cd",
            "\1\u01ce\37\uffff\1\u01ce",
            "\1\u01cf\37\uffff\1\u01cf",
            "\1\u01d0\37\uffff\1\u01d0",
            "",
            "",
            "\1\u01d1\37\uffff\1\u01d1",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u01d3",
            "\1\u01d4\37\uffff\1\u01d4",
            "\1\u01d5\37\uffff\1\u01d5",
            "\1\u01d6\37\uffff\1\u01d6",
            "\1\u01d7\37\uffff\1\u01d7",
            "\1\u01d8\37\uffff\1\u01d8",
            "\1\u01d9\37\uffff\1\u01d9",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01db\37\uffff\1\u01db",
            "\1\u01dc\37\uffff\1\u01dc",
            "\1\u01dd\37\uffff\1\u01dd",
            "\1\u01de\37\uffff\1\u01de",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u01e0\37\uffff\1\u01e0",
            "",
            "\1\u01e1\37\uffff\1\u01e1",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01e4\37\uffff\1\u01e4",
            "\1\u01e5\37\uffff\1\u01e5",
            "\1\u01e6\37\uffff\1\u01e6",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u01e8\37\uffff\1\u01e8",
            "\1\u01e9\37\uffff\1\u01e9",
            "\1\u01ea\37\uffff\1\u01ea",
            "\1\u01eb\37\uffff\1\u01eb",
            "\1\u01ec\37\uffff\1\u01ec",
            "",
            "",
            "\1\u01ed\37\uffff\1\u01ed",
            "\1\u01ee\37\uffff\1\u01ee",
            "\1\u01ef\37\uffff\1\u01ef",
            "\1\u01f0\37\uffff\1\u01f0",
            "\1\u01f1\37\uffff\1\u01f1",
            "\1\u01f2\37\uffff\1\u01f2",
            "\1\u01f3\37\uffff\1\u01f3",
            "\1\u01f4\37\uffff\1\u01f4",
            "",
            "\1\u01f5\37\uffff\1\u01f5",
            "\1\u01f6\37\uffff\1\u01f6",
            "\1\u01f7",
            "\1\u01f8\37\uffff\1\u01f8",
            "\1\u01f9\37\uffff\1\u01f9",
            "\1\u01fa\37\uffff\1\u01fa",
            "\1\u01fb\37\uffff\1\u01fb",
            "",
            "\1\u01fc\37\uffff\1\u01fc",
            "\1\u01fd\37\uffff\1\u01fd",
            "\1\u01fe\37\uffff\1\u01fe",
            "\1\u01ff\37\uffff\1\u01ff",
            "",
            "\1\u0200\37\uffff\1\u0200",
            "\1\u0202\3\uffff\1\u0201\33\uffff\1\u0202\3\uffff\1\u0201",
            "",
            "",
            "\1\u0203\37\uffff\1\u0203",
            "\1\u0204",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u0206\37\uffff\1\u0206",
            "\1\u0207\37\uffff\1\u0207",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0209\37\uffff\1\u0209",
            "\1\u020a\37\uffff\1\u020a",
            "\1\u020b\37\uffff\1\u020b",
            "\1\u020c\37\uffff\1\u020c",
            "\1\u020d\37\uffff\1\u020d",
            "\1\u020e\37\uffff\1\u020e",
            "\1\u020f\37\uffff\1\u020f",
            "\1\u0210\37\uffff\1\u0210",
            "\1\u0211\37\uffff\1\u0211",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0213\37\uffff\1\u0213",
            "\1\u0214\37\uffff\1\u0214",
            "\1\u0215\37\uffff\1\u0215",
            "\1\u0216\37\uffff\1\u0216",
            "\1\u0217\37\uffff\1\u0217",
            "\1\u0218\37\uffff\1\u0218",
            "\1\u0219\37\uffff\1\u0219",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u021d\37\uffff\1\u021d",
            "\1\u021e\37\uffff\1\u021e",
            "\1\u021f\37\uffff\1\u021f",
            "\1\u0220\37\uffff\1\u0220",
            "\1\u0221",
            "\1\u0222\37\uffff\1\u0222",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0224\37\uffff\1\u0224",
            "",
            "\1\u0225\37\uffff\1\u0225",
            "\1\u0226\37\uffff\1\u0226",
            "\1\u0227\37\uffff\1\u0227",
            "\1\u0228\37\uffff\1\u0228",
            "\1\u0229\37\uffff\1\u0229",
            "\1\u022a",
            "\1\u022b\37\uffff\1\u022b",
            "\1\u022c\37\uffff\1\u022c",
            "\1\u022d\37\uffff\1\u022d",
            "",
            "\1\u022e\37\uffff\1\u022e",
            "\1\u022f\37\uffff\1\u022f",
            "\1\u0230\37\uffff\1\u0230",
            "\1\u0231",
            "\1\u0232\37\uffff\1\u0232",
            "\1\u0233\37\uffff\1\u0233",
            "\1\u0234\37\uffff\1\u0234",
            "",
            "",
            "",
            "\1\u0235\37\uffff\1\u0235",
            "\1\u0236\37\uffff\1\u0236",
            "\1\u0237\37\uffff\1\u0237",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0239\37\uffff\1\u0239",
            "\1\u023a\37\uffff\1\u023a",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u023d\37\uffff\1\u023d",
            "\1\u023e\37\uffff\1\u023e",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\u0241\1\uffff"+
            "\32\52",
            "\1\u0242\37\uffff\1\u0242",
            "\1\u0243\37\uffff\1\u0243",
            "\1\u0244\37\uffff\1\u0244",
            "\1\u0245",
            "\1\u0246\37\uffff\1\u0246",
            "\1\u0247\37\uffff\1\u0247",
            "\1\u0248\37\uffff\1\u0248",
            "\1\u0249\37\uffff\1\u0249",
            "\1\u024a\37\uffff\1\u024a",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u024d\37\uffff\1\u024d",
            "\1\u024e\37\uffff\1\u024e",
            "\1\u024f",
            "",
            "\1\u0250\37\uffff\1\u0250",
            "\1\u0251\37\uffff\1\u0251",
            "",
            "",
            "\1\u0252\37\uffff\1\u0252",
            "\1\u0253\37\uffff\1\u0253",
            "",
            "",
            "\1\u0254\3\uffff\1\u0255\33\uffff\1\u0254\3\uffff\1\u0255",
            "\1\u0256\37\uffff\1\u0256",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0259\14\uffff\1\u025a\22\uffff\1\u0259\14\uffff\1\u025a",
            "\1\u025b",
            "\1\u025c\37\uffff\1\u025c",
            "\1\u025d\37\uffff\1\u025d",
            "\1\u025e\37\uffff\1\u025e",
            "\1\u025f\37\uffff\1\u025f",
            "",
            "",
            "\1\u0260\37\uffff\1\u0260",
            "\1\u0261\37\uffff\1\u0261",
            "\1\u0262\37\uffff\1\u0262",
            "\1\u0263\37\uffff\1\u0263",
            "\1\u0264\37\uffff\1\u0264",
            "\1\u0265\37\uffff\1\u0265",
            "\1\u0266\37\uffff\1\u0266",
            "\1\u0267\37\uffff\1\u0267",
            "\1\u0268\37\uffff\1\u0268",
            "\1\u0269\37\uffff\1\u0269",
            "",
            "",
            "\1\u026a\37\uffff\1\u026a",
            "\1\u026b\37\uffff\1\u026b",
            "\1\u026c\37\uffff\1\u026c",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u026e\37\uffff\1\u026e",
            "\1\u026f\37\uffff\1\u026f",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0272\37\uffff\1\u0272",
            "\1\u0273\37\uffff\1\u0273",
            "\1\u0274\37\uffff\1\u0274",
            "\1\u0275\37\uffff\1\u0275",
            "\1\u0276\37\uffff\1\u0276",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0278\37\uffff\1\u0278",
            "\1\u0279\37\uffff\1\u0279",
            "\1\u027a\37\uffff\1\u027a",
            "\1\u027b\37\uffff\1\u027b",
            "\1\u027c\37\uffff\1\u027c",
            "\1\u027d\37\uffff\1\u027d",
            "",
            "\1\u027e\37\uffff\1\u027e",
            "\1\u027f\37\uffff\1\u027f",
            "",
            "",
            "\1\u0280\37\uffff\1\u0280",
            "\1\u0281\37\uffff\1\u0281",
            "\1\u0282\37\uffff\1\u0282",
            "\1\u0283\37\uffff\1\u0283",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\u0285\37\uffff\1\u0285",
            "\1\u0286\37\uffff\1\u0286",
            "\1\u0287\37\uffff\1\u0287",
            "\1\u0288\37\uffff\1\u0288",
            "\1\u0289\37\uffff\1\u0289",
            "\1\u028a\37\uffff\1\u028a",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u028c\37\uffff\1\u028c",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u028e\37\uffff\1\u028e",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0290\37\uffff\1\u0290",
            "",
            "\1\u0291\37\uffff\1\u0291",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u0293\37\uffff\1\u0293",
            "\1\u0294\37\uffff\1\u0294",
            "\1\u0295\37\uffff\1\u0295",
            "\1\u0296\37\uffff\1\u0296",
            "",
            "\1\u0297\37\uffff\1\u0297",
            "",
            "\1\u0298\37\uffff\1\u0298",
            "",
            "\1\u0299\37\uffff\1\u0299",
            "\1\u029a\37\uffff\1\u029a",
            "",
            "\1\u029b\37\uffff\1\u029b",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u029d\37\uffff\1\u029d",
            "\1\u029e\37\uffff\1\u029e",
            "\1\u029f\37\uffff\1\u029f",
            "\1\u02a0\37\uffff\1\u02a0",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "\1\u02a6\37\uffff\1\u02a6",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            "",
            "",
            "",
            "",
            "",
            "\1\u02a8\37\uffff\1\u02a8",
            "",
            "\1\u02a9\37\uffff\1\u02a9",
            "\1\u02aa\37\uffff\1\u02aa",
            "\1\52\2\uffff\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32"+
            "\52",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( Asterisk | Dot | OParen | CParen | Comma | SColon | QMark | Plus | Minus | Divide | DVertbar | Range | Equals | NotEquals | GThan | GTEquals | LThan | LTEquals | Underscore | GmtTag | CREATE | SELECT | INSERT | UPDATE | DELETE | ALL | AND | AS | BIGINT | BIT | BLOB | CHAR | CHAR1 | CONSTRAINT | COUNT | DATE | DATETIME | DISTINCT | DOUBLE | FALSE | FROM | IN | INDEX | INTEGER | INTO | LONGVARCHAR | MODULE | NOT | NULL | NOT_NULL | ONLY | PRIMARY_KEY | REAL | SET | SMALLINT | TABLE | TIME | TIMESTAMP | TRUE | VALUES | VARCHAR | WHERE | ASYNC | CAPACITY | CACHE_POLICY | DISTRIBUTED | DISTRIBUTION_POLICY | EVICTION_POLICY | FILE_SYNC_INTERVAL | FORGET_OLD_VALUE | HASH | HOST_AWARE_REPLICATION | IS | IS_NULL | IS_NOT_NULL | LIMIT | LOCK_SCOPE | LOCK_TTL | LOCK_WAIT | LRU | MIN_SEEDERS | MULTICAST | NON_DISTRIBUTED | NONE | NOR | OFFSET | OR | PERSISTENCE | PERSISTENCE_POLICY | PERSISTENCE_TYPE | PHASE_COUNT | PHASE_INTERVAL | PROCESS | QUERY_LIMIT | QUERY_TIMEOUT | READ_TIMEOUT | READ_THROUGH | READ_WRITE_THROUGH | REPLICATION_COUNT | REPLICATION_POLICY | ROUTED | SHARED_ALL | SHARED_NOTHING | SPACE_WAIT | SYNC | THREAD | TREE | TTL | UNICAST | UPDATE_TRANSPORT | VIRTUAL_NODE_COUNT | WRITE_TIMEOUT | QuotedString | ASDateTime | PositiveNumber | NegativeNumber | Float | Identifier | Comment | Space );";
        }
    }
 

}