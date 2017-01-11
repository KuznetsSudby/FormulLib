
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Parser {

	public static final int IF_BLOCK = 0;
	public static final int FOR_BLOCK = 1;
	public static final int BEGIN_BLOCK = 2;
	public static final int END_BLOCK = 3;
	public static final int WHILE_BLOCK = 4;
	public static final int NULL_BLOCK = 5;
	public static final int EXPRESSION_BLOCK = 6;
	public static final int WRITE_BLOCK = 7;
	public static final int WRITELN_BLOCK = 8;
	public static final int READLN_BLOCK = 9;
	public static final int ELSE_BLOCK = 10;
	
	public static final int less_BLOCK = 11;	
	public static final int more_BLOCK = 12;	
	public static final int less_equal_BLOCK = 13;	
	public static final int more_equal_BLOCK = 14;	
	public static final int equal_BLOCK = 15;
	public static final int not_equal_BLOCK = 16;	
	public static final int and_BLOCK = 17;
	public static final int or_BLOCK = 18;	

	
	private String line;
	public Map<String,Indentificator> dict = new HashMap<String, Indentificator>();
	private char[] operators = {';',':','+','*','-','/','(',')','='};
	public Parser(){
	}
	
	public void parseString(String line){
		this.line = line;
		System.out.println("===============| Получена строка и словарь");
		PrintTable();
		System.out.println("===============| Начата валидация");
		
		new Validator(line)
		.validateName()
		.printDict()
		.validateBeginEnd()
		.validateBusket()
		.validateFor(dict)
		.validateIf()
		.validateWhile();
		System.out.println("===============| Начато выполнение программы");
		programm(line.replace(" end. ", " end "));
		System.out.println("===============| Конец выполнения программы");
	}

	private void programm(String line2) {
		while (line2.length() > 1){
			int index_block = line2.indexOf(";");
			String block = line2.substring(0, index_block+1);
			line2 = line2.substring(index_block+1);
			int ind_st;
			int ind_nd;
			String next_block;
			String expression;
			String name;
			switch (getTypeOfBlock(block)){
				case IF_BLOCK:
					ind_st = block.indexOf(" ( ");
					ind_nd = block.indexOf(" ) ");
					expression = block.substring(ind_st+2,ind_nd+1);
					next_block = getNextBlock(line2);
					line2 = line2.substring(next_block.length());
					String second_block = getNextBlock(line2);
					if (second_block.startsWith(" else")){
						line2 = line2.substring(second_block.length());
						second_block = getNextBlock(line2);
						line2 = line2.substring(second_block.length());
						if (parseBooleanExpression(expression)){
							programm(next_block);
						}else{
							programm(second_block);
						}
					}else{
						if (parseBooleanExpression(expression)){
							programm(next_block);
						}
					}
					break;
				case FOR_BLOCK:
					ind_st = block.indexOf(" ",3);
					ind_nd = block.indexOf(" ", ind_st+1);
					name = block.substring(ind_st+1, ind_nd);
					ind_st = ind_nd+3;
					ind_nd = block.indexOf(" ",ind_st+1);
					String start_value = block.substring(ind_st+1, ind_nd);
					ind_st = ind_nd+3;
					ind_nd = block.indexOf(" ",ind_st+1);
					String end_value = block.substring(ind_st+1, ind_nd);
					next_block = getNextBlock(line2);
					line2 = line2.substring(next_block.length());
					for(int i=getIntByName(start_value);i<=getIntByName(end_value);i++){
						putInIndentInt(name,i);
						programm(next_block);
					}
					break;
				case BEGIN_BLOCK:
					break;
				case END_BLOCK:
					break;
				case ELSE_BLOCK:
					break;
				case WHILE_BLOCK:
					
					ind_st = block.indexOf(" ( ");
					ind_nd = block.indexOf(" ) ");
					expression = block.substring(ind_st+2,ind_nd+1);
					next_block = getNextBlock(line2);
					line2 = line2.substring(next_block.length());
					while (parseBooleanExpression(expression)){
						programm(next_block);
					}
					break;
				case NULL_BLOCK:
					break;
				case EXPRESSION_BLOCK:
					ind_st = block.indexOf(" := ");
					ind_nd = block.indexOf(" ;");
					putInIndentIndent(block.substring(1,ind_st), getValueByExpression(block.substring(ind_st+2,ind_nd+1)));
					break;
				case WRITE_BLOCK:
					ind_st = block.indexOf(" ( ");
					ind_nd = block.indexOf(" ) ");
					while (block.indexOf(" ) ",ind_nd+1)!=-1)
						ind_nd = block.indexOf(" ) ",ind_nd+1);
					System.out.print(getValueByExpression(block.substring(ind_st+2,ind_nd+1)).value+"");
					break;
				case WRITELN_BLOCK:
					ind_st = block.indexOf(" ( ");
					ind_nd = block.indexOf(" ) ");
					while (block.indexOf(" ) ",ind_nd+1)!=-1)
						ind_nd = block.indexOf(" ) ",ind_nd+1);
					if (ind_st == -1)
						System.out.println();
					else
						System.out.println(getValueByExpression(block.substring(ind_st+2,ind_nd+1)).value+"");
					break;
				case READLN_BLOCK:
					ind_st = block.indexOf(" ( ");
					ind_nd = block.indexOf(" ) ");
					name = block.substring(ind_st+3, ind_nd);
					if (name.contains(" "))
						Error.ErrorReadlnIn(name);
					else{
						Indentificator value = dict.get(name);
						Scanner in = new Scanner(System.in);
						if ((value.type!=Indentificator.INT_CONST)||(value.type!=Indentificator.INT_VALUE))
							putInIndentInt(name, in.nextInt());
						else if ((value.type!=Indentificator.FLOAT_CONST)||(value.type!=Indentificator.FLOAT_VALUE))
								putInIndentFloat(name, in.nextFloat());
						else if ((value.type!=Indentificator.STRING)){
							Indentificator val = dict.get(name);
							value.value=in.next();
							dict.remove(name);
							dict.put(name,value);	
						}else
							Error.ErrorTypeValuePut(name,"ОШИБКА");
					}
					break;
			}
		}
	}

	private void putInIndentIndent(String name, Indentificator ind) {
		Indentificator value = dict.get(name);
		if (value.type != ind.type)
			Error.ErrorTypeValuePut(name,ind.value);
		value.value=ind.value;
		dict.remove(name);
		dict.put(name,value);
	}
	
	private void putInIndentFloat(String name, float nextFloat) {
		Indentificator value = dict.get(name);
		if (value.type != Indentificator.INT_VALUE)
			Error.ErrorTypeValuePut(name,nextFloat+"");
		value.value=nextFloat+"";
		dict.remove(name);
		dict.put(name,value);	
	}

	private Indentificator getValueByExpression(String exp) {
		return new Polish().load(exp).calculation(dict);
	}

	private boolean parseBooleanExpression(String exp) {
		int ind_nd = exp.indexOf(" ",1);
		if (exp.substring(1,ind_nd).contains("&")){
			if (exp.indexOf(" ",ind_nd+1)!=-1){
				int end_token = exp.indexOf(" ",ind_nd+1);
				float a = dict.get(exp.substring(1,ind_nd)).getFloatValue();
				float b = dict.get(exp.substring(end_token+1,exp.indexOf(" ", end_token+1))).getFloatValue();
				switch (getTypeForComprassion(exp.substring(ind_nd+1,end_token))){
					case less_BLOCK:
						return a < b;
					case more_BLOCK:
						return a > b;
					case less_equal_BLOCK:
						return a <= b;
					case more_equal_BLOCK:
						return a >= b;
					case not_equal_BLOCK:
						return a != b;
					case equal_BLOCK:
						return a == b;
				}
			}else{
				return dict.get(exp.substring(1,ind_nd)).getBooleanValue();
			}
		}else if (exp.substring(1,ind_nd).contains("(")){
			int index = getIndexOfEnd(exp,ind_nd);
			String new_exp = exp.substring(ind_nd,index);
			if (exp.indexOf(" ",index+2)== -1)
				return parseBooleanExpression(new_exp);
			else{
				int end_token= exp.indexOf(" ",index+2);
				String end_exp = exp.substring(end_token);
				switch (getTypeForComprassion(exp.substring(index+2,end_token))){
					case and_BLOCK:
						return parseBooleanExpression(new_exp) && parseBooleanExpression(end_exp);
					case or_BLOCK:
						return parseBooleanExpression(new_exp) || parseBooleanExpression(end_exp);
				}
			}
		}else
			Error.ErrorInExp(exp);
		return false;
	}
	
	private int getTypeForComprassion(String substring) {
		if(substring.equals("<"))
			return less_BLOCK;
		if(substring.equals(">"))
			return more_BLOCK;
		if(substring.equals("<="))
			return less_equal_BLOCK;
		if(substring.equals(">="))
			return more_equal_BLOCK;
		if(substring.equals("<>"))
			return not_equal_BLOCK;
		if(substring.equals("=="))
			return equal_BLOCK;
		if(substring.equals("and"))
			return and_BLOCK;
		if(substring.equals("or"))
			return or_BLOCK;
		return 0;
	}

	private int getIndexOfEnd(String exp, int ind_nd) {
		int level=1;
		for (int i=ind_nd; i < exp.length(); i++){
			char ch = exp.charAt(i);
			if (ch=='(')
				level++;
			if (ch==')')
				level--;
			if (level == 0)
				return i;
		}
		return -1;
	}

	private int getIntByName(String name) {
		Indentificator value = dict.get(name);
		if ((value.type!=Indentificator.INT_CONST)&&(value.type!=Indentificator.INT_VALUE))
			Error.ErrorTypeValueGet(name,"int");
		return Integer.parseInt(value.value);
	}

	private void putInIndentInt(String name, int i) {
		Indentificator value = dict.get(name);
		if (value.type != Indentificator.INT_VALUE)
			Error.ErrorTypeValuePut(name,i+"");
		value.value=i+"";
		dict.remove(name);
		dict.put(name,value);
	}

	private String getNextBlock(String line2) {
		int index_end = line2.indexOf(" end ");
		int index_begin = line2.indexOf(" begin ",3);
		int level = 0;
		if (line2.startsWith(" begin ")){
			while (level != 0){
				if (index_begin < index_end){
					if(index_begin!=-1){
						level++;
						index_begin = line2.indexOf(" begin ",index_begin+3);
					}else{
						level--;
						index_end = line2.indexOf(" end ",index_end+3);
					}
				}else{
					level--;
					index_end = line2.indexOf(" end ",index_end+3);
				}
			}
			return line2.substring(0, index_end+6);
		}else{
			return line2.substring(0, line2.indexOf(";")+1);
		}
	}

	private int getTypeOfBlock(String block) {
		if (block.length()<3)
			return NULL_BLOCK;
		
		String str = block.substring(1, block.indexOf(" ",2));
		if (str.equals("begin"))
			return BEGIN_BLOCK;
		if (str.equals("if"))
			return IF_BLOCK;
		if (str.equals("while"))
			return WHILE_BLOCK;
		if (str.equals("for"))
			return FOR_BLOCK;
		if (str.equals("end"))
			return END_BLOCK;
		if (str.equals("write"))
			return WRITE_BLOCK;
		if (str.equals("writeln"))
			return WRITELN_BLOCK;
		if (str.equals("readln"))
			return READLN_BLOCK;
		if (str.equals("else"))
			return ELSE_BLOCK;
		
		if (block.contains(" := "))
			return EXPRESSION_BLOCK;
		Error.ErrorBlock(block);
		return 0;
	}

	public void PrintTable(){
		System.out.println("\n------------------------------------------------------------");
		System.out.println("|===> Таблица");
		System.out.println("------------------------------------------------------------\n");
		for (Map.Entry<String, Indentificator> entry : dict.entrySet()) {
            System.out.println("|=> "+entry.getKey() + "\n" + entry.getValue().toString());
            System.out.println();
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("|===> конец таблицы");
		System.out.println("------------------------------------------------------------\n");
	}
}
