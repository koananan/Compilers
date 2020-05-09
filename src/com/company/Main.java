package com.company;

import com.company.lexer.Lexer;
import com.company.lexer.Tag;
import com.company.lexer.Token;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String workSpace = "/src/com/company/";
        File directory = new File(".");
        Lexer lexer = new Lexer(directory.getCanonicalPath() + workSpace +"/files/test3.txt",
                directory.getCanonicalPath() + workSpace + "/logs/result3.log");
        boolean first = true;
        while (true) {
            Token word = lexer.scan();
            if (lexer.end()) { break; }
            if (first) {
                System.out.println("<" + "单词自身值, "  + "内部编码>");
                first = false;
            }
            System.out.println("<" + word.toString() + ", " + Tag.code(word.tag) + ">");
        }
    }
}
