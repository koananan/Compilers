package com.company.inter;

import com.company.lexer.Word;
import com.company.symbols.Type;

public class Id extends Expr {
	public int offset;
	public Id(Word id, Type p, int b) {
		super(id,p);
		offset = b;
	
	}
}
