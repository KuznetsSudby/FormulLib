import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Validator {
	String[] reserved_name = {";",":=","while","for","if","to","begin","end","end.","do","else",
			"then","-","+","*","/","(",")","==","<>","<",">","<=",">=","writeln","write","readln","or","and"};
	
	String line;
	Set<String> list = new HashSet<String>();
	
	public Validator(String line){
		this.line = line;
	}
	
	public Validator validateName(){
		System.out.println("===============| Проверка зарезервированных значений");
		String str="";
		int index_start = line.indexOf(" ");
		int index_end = line.indexOf(" ", index_start+1);
		while ((index_start != -1) && (index_end != -1)){
			str = line.substring(index_start+1,index_end);
			if (!str.contains("&"))
				if (isGoodName(str)){
					list.add(str);
				}else{
					line = "";
					Error.ErrorName(str);
				}
			index_start = index_end;
			index_end = line.indexOf(" ", index_start+1);	
		}
		return this;
	}
	
	public Validator printDict(){
		System.out.println("\n------------------------------------------------------------");
		System.out.println("|===> Таблица Зарезервированных имен");
		System.out.println("------------------------------------------------------------\n");
		for (String entry : list) {
			System.out.println("|=> "+ entry);
		}
		System.out.println("\n------------------------------------------------------------");
		System.out.println("|===> конец таблицы");
		System.out.println("------------------------------------------------------------\n");
		return this;
	}

	private boolean isGoodName(String str) {
		for (int i =0; i < reserved_name.length; i++)
			if (str.equals(reserved_name[i]))
				return true;
		return false;
	}

	public Validator validateBeginEnd(){
		System.out.println("===============| Проверка BEGIN - END");
		int index_endDot = line.indexOf(" end. ");
		int index_endDot2 = line.indexOf(" end. ", index_endDot+2);
		if ((index_endDot == -1)||(index_endDot2 != -1))
			Error.ErrorEndDot();

		// перед найденной шнягой должна быть ; проверять на нее (кроме самого первого);
		validatePair(" begin ", " end ",1);
		
		validateEndBlockBefore(" begin ", 2);
		validateEndBlockBefore(" end ", 0);
		validateEndBlockBefore(" end. ", 0);
		return this;
	}
	
	private void validateEndBlockBefore(String str, int index){
		int i = line.indexOf(str, index);
		while (i!=-1){
			if (line.charAt(i-1)!=';')
				Error.ErrorEndBefore(str);
			i = line.indexOf(str, i+1);
		}
	}
	
	
	private void validatePair(String st, String nd, int lvl){
		int level = 0;
		int index_begin = line.indexOf(st,0);
		int index_end = line.indexOf(nd,0);
		while ((index_begin != -1)||(index_end!=-1)){
			if (index_begin == -1){
				while (index_end!= -1){
					level--;
					index_end = line.indexOf(nd,index_end+2);
				}
			}
			
			if (index_end == -1){
				while (index_begin!= -1){
					level++;
					index_begin = line.indexOf(st, index_begin+2);
				}
			}
			
			if ((index_end!=-1)&&(index_begin!=-1)){
				if((index_end > index_begin)){
					index_begin = line.indexOf(st, index_begin+2);
					level++;
				}else{
					index_end = line.indexOf(nd,index_end+2);
					level--;
					if (level < lvl)
						Error.ErrorPair(st.toUpperCase()+"-"+nd.toUpperCase());
				}
			}
		}

		if (level > lvl)
			Error.ErrorNum(st.toUpperCase());
		if (level < lvl)
			Error.ErrorNum(nd.toUpperCase());
	}
	
	public Validator validateFor(Map<String, Indentificator> dict){
		System.out.println("===============| Проверка циклов FOR");
		validateEndBlockBefore(" for ", 0);
		int index_for = line.indexOf(" for ");
		int index_for_end;
		int index_start;
		int index_end;
		while (index_for!=-1){
			index_start = line.indexOf(" ",index_for+2);
			index_end = line.indexOf(" ",index_start+1);
			index_for_end = line.indexOf(";",index_for);
			Indentificator ind = dict.get(line.substring(index_start+1,index_end));
			if ((ind == null)||(ind.isConst()))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			if (!line.substring(index_start+1,index_end).equals(":="))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			ind = dict.get(line.substring(index_start+1,index_end));
			if ((ind == null)||(ind.isNotDigit()))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));			
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			if (!line.substring(index_start+1,index_end).equals("to"))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			ind = dict.get(line.substring(index_start+1,index_end));
			if ((ind == null)||(ind.isNotDigit()))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			if (!line.substring(index_start+1,index_end).equals("do"))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_start = index_end;
			index_end = line.indexOf(" ",index_start+1);
			if (!line.substring(index_start+1,index_end).equals(";"))
				Error.ErrorFor(line.substring(index_for+1,index_for_end-1));
			
			index_for = line.indexOf(" for ", index_for + 2);
		}
		return this;
	}
	
	public Validator validateBusket(){
		System.out.println("===============| Проверка ( - )");
		validatePair(" ( "," ) ",0);
		return this;
	}
	
	public Validator validateIf(){
		System.out.println("===============| Проверка IF");
		validateEndBlockBefore(" if ", 0);
		int index = line.indexOf(" if ",0);
		int end;
		while (index!=-1){
			end = line.indexOf(";", index);
			if (end == -1){
				Error.ErrorIf(line.substring(index, line.length()));
			}
			
			String str = line.substring(index, end);
			if (!str.endsWith(" then "))
				Error.ErrorIf(str);
			validateBooleanExpression(str.substring(3,str.length()-5));
			
			int next_index = line.indexOf(";",end+1);
			int else_index = line.indexOf(" else ;",end+1);
			if (else_index != -1)
				if (next_index - 6 == else_index){
				}else{
					if (line.indexOf(" ; begin ;",end)==end){
						if(line.indexOf(" end ;",end)+6 == else_index){
						}else{
							if (line.indexOf(" if ", line.indexOf(" end ;",end)) < else_index){
							}else{
								Error.ErrorIf(str);
							}
						}
					}else{
						if (line.indexOf(" if ", end) < else_index){
						}else{
							Error.ErrorIf(str);
						}
					}
				}
			
			index = line.indexOf(" if ",index+2);
		}
		return this;
	}
	
	public Validator validateWhile(){
		System.out.println("===============| Проверка while");
		validateEndBlockBefore(" while ", 0);
		int index = line.indexOf(" while ",0);
		int end;
		while (index!=-1){
			end = line.indexOf(";", index);
			if (end == -1){
				Error.ErrorWhile(line.substring(index, line.length()));
			}
			String str = line.substring(index, end);
			if (!str.endsWith(" do "))
				Error.ErrorWhile(str);
			validateBooleanExpression(str.substring(6,str.length()-3));
			index = line.indexOf(" if ",index+2);
		}
		return this;
	}
	
	private void validateBooleanExpression(String expr){
		System.out.println("===============| Проверка boolean выражения");
		int level = 0;
		String ex_l = "-";
		if (expr.length()<9)
			Error.ErrorExpression(expr);
		int i_s = expr.indexOf(" ",0);
		int i_d = expr.indexOf(" ",i_s+1);
		while(i_d != -1){
			String ex = expr.substring(i_s+1,i_d);
			
			if (validatePairEx(ex_l, ex))
				Error.ErrorExpression(expr);
			
			if (ex.equals("("))
				level++;
			if (ex.equals(")"))
				level--;
			if (level<0)
				Error.ErrorExpression(expr);
			
			ex_l = ex;
			i_s = i_d;
			i_d = expr.indexOf(" ",i_s+1);
		}
		if (level!=0)
			Error.ErrorExpression(expr);
	}
	
	private boolean validatePairEx(String ex_l, String ex) {
		if ((ex_l.equals("-"))||(ex_l.equals("and"))||(ex_l.equals("or"))){
			if(ex.equals("("))
				return false;
			return true;
		}
		
		if (ex_l.equals("(")){
			if(ex.equals("("))
				return false;
			if(ex.contains("&"))
				return false;
			return true;
		}
		
		if (ex_l.equals(")")){
			if(ex.equals("and"))
				return false;
			if(ex.equals("or"))
				return false;
			if(ex.equals(")"))
				return false;
			return true;
		}
		
		if (ex_l.contains("&")){
			if(ex.equals("<"))
				return false;
			if(ex.equals(">"))
				return false;
			if(ex.equals("=="))
				return false;
			if(ex.equals("<>"))
				return false;
			if(ex.equals(">="))
				return false;
			if(ex.equals("<="))
				return false;
			if(ex.equals(")"))
				return false;
			return true;
		}
		
		if (ex.contains("&")){
			if(ex_l.equals("<"))
				return false;
			if(ex_l.equals(">"))
				return false;
			if(ex_l.equals("=="))
				return false;
			if(ex_l.equals("<>"))
				return false;
			if(ex_l.equals(">="))
				return false;
			if(ex_l.equals("<="))
				return false;
			return true;
		}
		return true;
	}

	public String toString(){
		return line;
	}
}
