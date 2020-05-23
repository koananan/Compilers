    package com.company.syntax;

import com.company.lr1.LR1Parser;

import java.util.ArrayList;

public class GrammarInputController {

    private static LR1Parser lr1Parser;
    private String grammarText = "";

    public void parser() {
            String grammarText = "E->E + T\n" +
                    "E->T\n" +
                    "T->T * F\n" +
                    "T-> F\n" +
                    "F->( E )\n" +
                    "F->id\n";
            Grammar grammar = new Grammar(grammarText);
            lr1Parser = new LR1Parser(grammar);
            lr1Parser.parseLALR1();
            System.out.print(lr1Parser.actionTableStr());
            boolean canBeParse = lr1Parser.parseLALR1();
            ArrayList<String> inputs = new ArrayList<>();
            inputs.add("id");
            inputs.add("*");
            inputs.add("id");
            inputs.add("+");
            inputs.add("id");
            if (canBeParse) {
                lr1Parser.accept(inputs);
            }
    }

    public String getGrammarText() {
        return grammarText;
    }

    public void setGrammarText(String grammarText) {
        this.grammarText = grammarText;
    }
}
