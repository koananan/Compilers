package com.company.lexer;

public class Tag {
    public final static int
        AND = 256, OR = 257, YU = 258, HUO = 259, E = 260, L = 261, G = 262,
        EQ = 263, NE = 264, GE = 265, LE = 266, MINUS = 267, PLUS = 268,
        SELM = 269, SELP = 270, ME = 271, PE = 272, BASIC = 273, BREAK = 274,
        CONTINUE = 275, DO = 276, WHILE = 277, IF = 278, ELSE = 279, SWITCH = 280,
        CASE = 281, FALSE = 282, TRUE = 283, ID = 284, INDEX = 285, NUM = 286,
        REAL = 287, TEMP = 288;


    public static String code(int tag) {
        if (tag >= AND && tag <= PE) {
            return "操作符";
        }
        if (tag >= BASIC && tag <= TRUE) {
            return "基本保留字";
        }
        if (tag == ID) {
            return "标识符";
        }
        if (tag >= INDEX && tag <= NUM) {
            return "整数";
        }
        if (tag == REAL) {
            return "浮点数";
        }
        if (tag == '{' || tag == '}' ||
            tag == '(' || tag == ')' ||
            tag == '[' || tag == ']') {
            return "分隔符";
        }
        return "ERROR";
    }
}
