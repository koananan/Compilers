package com.company;

import com.company.lexer.Lexer;
import com.company.lexer.Tag;
import com.company.lexer.Token;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        boolean first = true;
        while (true) {
            Token word = lexer.scan();
            if (first) {
                System.out.println("<" + "单词自身值, "  + "内部编码>");
                first = false;
            }
            System.out.println("<" + word.toString() + ", " + Tag.code(word.tag) + ">");
        }
    }

}
