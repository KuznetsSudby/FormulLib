package com.kusu.constructor.Utils;

import com.kusu.constructor.Prototype.PaintableBlock;

/**
 * Created by mikhail.chvarkou on 1/4/2017.
 */

public class Utils {
	public static int convertOperationSymbols(String s){
		switch (s){
			case "+":
				return PaintableBlock.SYMBOL_PLUS;
			case "-":
				return PaintableBlock.SYMBOL_MINUS;
			case "*":
				return PaintableBlock.SYMBOL_MULTIPLY;
		}
		return -1;
	}
}
