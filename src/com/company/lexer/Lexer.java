package com.company.lexer;

import com.company.symbols.Type;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
	public static int line = 1;	//对输入行计数
	char peek = ' ';			//读入下一个字符
	private Hashtable words = new Hashtable();
	void reserve(Word t){
		words.put(t.lexeme, t);
	}
	public Lexer(){
		reserve(new Word("if", Tag.IF));
		reserve(new Word("else", Tag.ELSE));
		reserve(new Word("while", Tag.WHILE));
		reserve(new Word("do", Tag.DO));
		reserve(new Word("break", Tag.BREAK));
		reserve(Word.True);
		reserve(Word.False);
		reserve(Type.Int);
		reserve(Type.Char);
		reserve(Type.Bool);
		reserve(Type.Float);
	}
	//词法分析中的检查下一个字符
	public void readch() throws IOException{
		peek = (char) System.in.read();
	}
	public boolean readch(char c) throws IOException{
		readch();
		if (peek != c) {
			return false;
		}
		peek = ' ';
		return true;
	}
	public Token scan() throws IOException{
		for(;; peek = (char)System.in.read()){
			if (peek == ' ' || peek == '\t') continue;
			else if(peek == '\n') {   //windows 换行符是\r\n   linux是\n
				line = line + 1;
				System.in.read();
			}
			else break;
		}
		switch (peek) {
			case '&':
				if (readch('&')) return Word.and;
				else return Word.yu;
			case '|':
				if (readch('|')) return Word.or;
				else return Word.huo;
			case '=':
				if (readch('=')) return Word.eq;
				else return Word.e;
			case '!':
				if (readch('=')) return Word.ne;
				else return new Token('!');
			case '<':
				if (readch('=')) return Word.le;
				else return Word.l;
			case '>':
				if (readch('=')) return Word.ge;
				else return Word.g;
			case '+':
				if (readch('+')) return Word.selp;
				else if (readch('=')) return Word.pe;
				else  return Word.plus;
			case '-':
				if (readch('-')) return Word.selm;
				else if (readch('=')) return Word.me;
				else return Word.minus;
		}
		
		//数字和字符
		if(Character.isDigit(peek)){
			int v = 0;
			do{
				v = 10 * v + Character.digit(peek, 10);
				readch();
			}while(Character.isDigit(peek));
			if (peek != '.') {
				return new Num(v);
			}
			//为小数
			float x = v;
			float d = 10;
			for(;;){
				readch();
				if(!Character.isDigit(peek)) break;
				x = x + Character.digit(peek, 10) / d;
				d = d * 10;
			}
			return new Real(x);
		}
		if (Character.isLetter(peek)) {
			//字符串缓冲区
			StringBuffer b = new StringBuffer();
			do{
				b.append(peek);
				readch();
			}while(Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null) {
				return w;
			}
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}
		
		//任意字符作为词法单元被返回
		Token token = new Token(peek);
		peek = ' ';
		return token;
	}
	
}
