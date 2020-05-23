package com.company;

import com.company.lexer.Lexer;
import com.company.lexer.Tag;
import com.company.lexer.Token;
import com.company.syntax.GrammarInputController;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*String workSpace = "/src/com/company/";
        File directory = new File(".");
        Lexer lexer = new Lexer(directory.getCanonicalPath() + workSpace +"/files/test3.txt",
                directory.getCanonicalPath() + workSpace + "/logs/result.log");
        boolean first = true;
        while (true) {
            Token word = lexer.scan();
            if (lexer.end()) { break; }
            if (first) {
                System.out.println("<" + "单词自身值, "  + "内部编码>");
                first = false;
            }
            System.out.println("<" + word.toString() + ", " + Tag.code(word.tag) + ">");
        }*/
        GrammarInputController grammarInputController = new GrammarInputController();
        String grammar = "program->block\n" +
                "block->{ decls stmts }\n" +
                "decls->decls decl | epsilon\n" +
                "decl->type id ;\n" +
                "type->type [ num ] | basic\n" +
                "stmts->stmts stmt | epsilon\n" +
                "\n" +
                "stmt->loc = bool ;" +
                "| if ( bool ) stmt" +
                "| if ( bool ) stmt else stmt" +
                "| while ( bool ) stmt" +
                "| do stmt while ( bool ) ;" +
                "| break ;" +
                "| block" +
                "Loc->loc [ bool ] | id\n" +
                "bool->bool || join | join\n" +
                "join->join && equality | equality\n" +
                "equality->equality == rel | equality != rel | rel\n" +
                "rel->expr < expr|expr <= expr|expr >= expr|expr > expr|expr\n" +
                "expr->expr + term |expr - term |term\n" +
                "term->term * unary|term / unary|unary\n" +
                "unary->!unary | -unary | factor\n" +
                "factor->( bool ) | loc | num | real | true |false";
        grammarInputController.setGrammarText(grammar);
        grammarInputController.parser();
    }
}
