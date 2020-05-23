package com.company.syntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public abstract class LRParser {

    protected HashMap<String, Integer>[] goToTable;
    protected HashMap<String, Action>[] actionTable;
    protected Grammar grammar;

    public LRParser(Grammar grammar) {
        this.grammar = grammar;
    }

    protected abstract void createGoToTable();

    public boolean accept(ArrayList<String> inputs) {
        inputs.add("$");
        int index = 0;
        Stack<String> stack = new Stack<>();
        stack.add("0");
        System.out.print("\n");
        System.out.printf("|%-6s|%-29s|%-29s|%-28s|%-30s ","", "栈", "符号", "输入", "动作");
        int row = 0;
        String nextInput = "";
        Stack<String> fuhao = new Stack<>();
        Stack<String> stack1 = new Stack<>();
        stack1.push("0");
        while(index < inputs.size()){
            System.out.print("\n");
            String inputS = "";
            for (int i = index; i < inputs.size(); ++i) {
                inputS += inputs.get(i);
            }
            String s = "";
            for (String tem : stack1) {
                s += tem;
            }
            String s1 = "";
            for (String tem : fuhao) {
                s1 += tem;
            }
            System.out.printf("|%-6s|%-30s|%-30s|%-30s|", "("+row+")", s, s1, inputS);
            int state = Integer.valueOf(stack.peek());
            nextInput = inputs.get(index);
            Action action = actionTable[state].get(nextInput);
            if(action == null){
                System.out.printf("%-30s", "出错");
                return false;
            }else if(action.getType() == ActionType.SHIFT){
                System.out.printf("%-30s", "移入");
                stack.push(nextInput);
                stack.push(action.getOperand()+"");
                index++;
                stack1.push(action.getOperand() + "");
                fuhao.push(nextInput);
            }else if(action.getType() == ActionType.REDUCE){
                int ruleIndex = action.getOperand();
                Rule rule = grammar.getRules().get(ruleIndex);
                String right = "";
                for (String temp : rule.getRightSide()) {
                    right += temp;
                }
                System.out.printf("%-30s", "根据" + rule.leftSide + "->" + right +"归约");
                String leftSide = rule.getLeftSide();
                int rightSideLength = rule.getRightSide().length;
                for(int i=0; i <2*rightSideLength ; i++){
                    stack.pop();
                }
                for(int i = 0; i < rightSideLength; ++i) {
                    stack1.pop();
                    fuhao.pop();
                }
                int nextState = Integer.valueOf(stack.peek());
                stack.push(leftSide);
                fuhao.push(leftSide);
                int variableState = goToTable[nextState].get(leftSide);
                stack.push(variableState+"");
                stack1.push(variableState + "");
            }else if(action.getType() == ActionType.ACCEPT){
                System.out.printf("%-30s", "接受");
                return true;
            }
            row++;
        }
        return false;
    }

    public String goToTableStr() {
        String str = "Go TO Table : \n";
        str += "          ";
        for (String variable : grammar.getVariables()) {
            str += String.format("%-6s",variable);
        }
        str += "\n";

        for (int i = 0; i < goToTable.length; i++) {
            for (int j = 0; j < (grammar.getVariables().size()+1)*6+2; j++) {
                str += "-";
            }
            str += "\n";
            str += String.format("|%-6s|",i);
            for (String variable : grammar.getVariables()) {
                str += String.format("%-6s",(goToTable[i].get(variable) == null ? "|" : goToTable[i].get(variable)+"|"));
            }
            str += "\n";
        }
        for (int j = 0; j < (grammar.getVariables().size()+1)*6+2; j++) {
            str += "-";
        }
        return str;
    }

    public String actionTableStr() {
        String str = "Action Table : \n";
        HashSet<String> terminals = new HashSet<>(grammar.getTerminals());
        terminals.add("$");
        str += "                ";
        for (String terminal : terminals) {
            str += String.format("%-10s" , terminal);
        }
        str += "\n";

        for (int i = 0; i < actionTable.length; i++) {
            for (int j = 0; j < (terminals.size()+1)*10+2; j++) {
                str += "-";
            }
            str += "\n";
            str += String.format("|%-10s|",i);
            for (String terminal : terminals) {
                str += String.format("%10s",(actionTable[i].get(terminal) == null ? "|" : actionTable[i].get(terminal) + "|"));
            }
            str += "\n";
        }
        for (int j = 0; j < (terminals.size()+1)*10+2; j++) {
            str += "-";
        }
        return str;
    }

    public Grammar getGrammar() {
        return grammar;
    }
}
