package com.company.lexer;

public class Word extends Token {
    public String lexeme = "";
    public Word(String s, int tag) { super(tag); lexeme = s; }
    public String toString() { return lexeme; }
    public static final Word
        and   = new Word("&&", Tag.AND), or = new Word("||", Tag.OR),
        yu    = new Word("&", Tag.YU),  huo = new Word("|", Tag.HUO),
        minus = new Word("-", Tag.MINUS), plus = new Word("+", Tag.PLUS),
        selm  = new Word("--", Tag.SELM), selp = new Word("++", Tag.SELM),
        e     = new Word("=", Tag.E),
        me    = new Word("-=", Tag.ME), pe = new Word("+=", Tag.PE),
        eq    = new Word("==", Tag.EQ),  ne = new Word("!=", Tag.NE),
        l     = new Word("<", Tag.L), g = new Word(">", Tag.G),
        le    = new Word("<=", Tag.LE),  ge = new Word(">=", Tag.GE),
        True  = new Word("true", Tag.TRUE),
        False = new Word("false", Tag.FALSE),
        temp  = new Word("t", Tag.TEMP);
}
