import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Refresher {
	String line;

	public Refresher(String line){
		this.line = line + ";";
	}
	
	public Refresher deleteComments(){
		System.out.println("===============| Удаляем комменты");
		int indexComment = line.indexOf("//",0);
		while (( indexComment >= 0)&&(indexComment <= line.length())){
			int indexEnd = 0;
			while (line.indexOf(";",indexEnd+1)<line.indexOf("//",indexComment))
				indexEnd = line.indexOf(";",indexEnd+1);
			int indexStr = line.indexOf('"', indexEnd);
			if ((indexStr<indexComment)&&(indexStr!=-1)){
				indexComment = line.indexOf("//",indexStr+1);
			}else{
				if (line.indexOf('\n', indexComment) == -1){
					line = line.replace(line.substring(indexEnd+1, line.length()), "");
				}else{
					line = line.replace(line.substring(indexEnd+1, line.indexOf('\n', indexComment)), "");
				}
				indexComment = line.indexOf("//", indexEnd);
			}
		}
		return this;	
	}
	
	public Refresher changeStrings(Map<String,Indentificator> dict) {
		System.out.println("===============| Выделяем строки");
		int indexStr=line.indexOf('"');
		while (indexStr != -1){
			int next = line.indexOf('"', indexStr+1);
			if (next == -1){
				System.out.println("Ошибка. Не веркое количество ковычек");
				return null;
			}else{
				String value = line.substring(indexStr+1, next);
				dict.put("&"+value.replace(" ", "_")+"&", new Indentificator(value, Indentificator.STRING));
				line = line.replace('"'+value+'"', " &"+value.replace(" ", "_")+"& ");
				indexStr=line.indexOf('"');
			}
		}
		return this;
	}
	
	public Refresher deleteSlashN(){
		System.out.println("===============| Удаляем переводы на новую строку");
		line = line.replaceAll("\n", "");
		line = line.replaceAll(";  ", "; ");
		return this;
	}
	
	public Refresher deleteSecondSpace(){
		System.out.println("===============| Удаляем лишние пробелы");
		while (line.contains("  "))
			line = line.replaceAll("  ", " ");
		return this;
	}
	
	public String toString(){
		return line;
	}

	public Refresher addEndsToString() {
		System.out.println("===============| Добавляем разделение на блоки");
		line = line.replace("begin ",  "begin; ");
		line = line.replace("begin;\n", "begin;\n;\n");
		line = line.replace("begin\n", "begin;\n");
		
		line = line.replace("var ",  "var; ");
		line = line.replace("var;\n", "var;\n;\n");
		line = line.replace("var\n", "var;\n");
		
		line = line.replace("do ",  "do; ");
		line = line.replace("do;\n", "do;\n;\n");
		line = line.replace("do\n", "do;\n");
		
		line = line.replace("then ",  "then; ");
		line = line.replace("then;\n", "then;\n;\n");
		line = line.replace("then\n", "then;\n");
		
		line = line.replace("else ",  "else; ");
		line = line.replace("else;\n", "else;\n;\n");
		line = line.replace("else\n", "else;\n");
		return this;
	}
	
	public Refresher addSpace() {
		System.out.println("===============| Добавляем пробелы для знаков");
		line = line.replace("*",  " * ");
		line = line.replace("-",  " - ");
		line = line.replace("+",  " + ");
		line = line.replace("/",  " / ");
		line = line.replace(":=",  " := ");
		line = line.replace("==",  " == ");
		line = line.replace("(",  " ( ");
		line = line.replace(")",  " ) ");
		line = line.replace("<>",  " <> ");
		line = line.replace("<",  " < ");
		line = line.replace(">",  " > ");
		line = line.replace("<  >",  "<>");
		line = line.replace("< >",  "<>");
		line = line.replace("< =",  "<=");
		line = line.replace("> =",  ">=");
		line = line.replace("\n",  "\n ");
		line = line.replace(";",  " ; ");
		line = line.replace(":",  " : ");
		line = line.replace(": =",  ":=");
		line = line.replace(",",  " , ");
		return this;
	}	
	
	public Refresher printLineFromOneLine() {
		System.out.println("===============| Вывод значения с добавление перевода строки");
		System.out.println(line.replace(";", "; \n"));
		return this;
	}
	
	public Refresher printLine() {
		System.out.println("===============| Вывод значения");
		System.out.println(line);
		return this;
	}

	public Refresher addVarAndConst(Map<String, Indentificator> dict) {
		if (line.contains(" var ")){
			System.out.println("===============| Обработка блока var");
			int index = line.indexOf(";", line.indexOf("var"));
			int indexEnd = line.indexOf(" begin", index);
			String var = line.substring(index+1, indexEnd);
			line = line.replace(" var ;"+var, "");
			ParseVar(var, dict);
		}
		
		System.out.println("===============| Поиск констант int");
		Pattern integer = Pattern.compile(" [0-9]+ ");
		boolean make = true;
		while (make){
			Matcher m = integer.matcher(line);
			if (m.find()){
				String integer_value = line.substring(m.start()+1, m.end()-1);
				dict.put("&"+integer_value+"&",new Indentificator(integer_value, Indentificator.INT_CONST));
				line = line.replaceAll(" "+integer_value+" ", " &"+integer_value+"& ");
			}else{
				make = false;
			}
		}

		System.out.println("===============| Поиск констант float");
		Pattern real = Pattern.compile(" [0-9]+.[0-9]+ ");
		make = true;
		while (make){
			Matcher m = real.matcher(line);
			if (m.find()){
				String integer_value = line.substring(m.start()+1, m.end()-1);
				dict.put("&"+integer_value+"&",new Indentificator(integer_value, Indentificator.FLOAT_CONST));
				line = line.replaceAll(" "+integer_value+" ", " &"+integer_value+"& ");
			}else{
				make = false;
			}
		}
		return this;
	}

	private void ParseVar(String var, Map<String, Indentificator> dict) {
		while (var.length()>0){
			int index = var.indexOf(";");
			String block=var.substring(0,index+1);
			ParseBlock(block,dict);
			var = var.replace(block, "");
		}
	}

	private void ParseBlock(String block, Map<String, Indentificator> dict) {
		int indexType = block.indexOf(": ");
		String type = block.substring(indexType+2,block.length()-2);
		block = block.replace(type+" ;", " ");
		int TYPE = getType(type);
		int index = block.indexOf(" ");
		int indexEnd;
		while (index!=-1){
			indexEnd=block.indexOf(" ",index+1);
			String name = block.substring(index+1,indexEnd);
			validateName(name);
			dict.put("&"+name+"&",new Indentificator("0", TYPE));
			line = line.replace(" "+name+" ", " &"+name+"& ");
			index=indexEnd;
			indexEnd=block.indexOf(" ",index+1);
			String token = block.substring(index+1,indexEnd);
			if (!token.equals(","))
				if (token.equals(":")){
					return;
				}else{
					Error.ErrorVar();
					line="";
				}
			index = indexEnd;
		}
	}

	private void validateName(String name) {
		if (name.charAt(0)=='_')
			Error.ErrorName(name);
		if (name.contains("&"))
			Error.ErrorName(name);
	}

	private int getType(String block) {
		if (block.equals("integer"))
			return Indentificator.INT_VALUE;
		if (block.equals("real"))
			return Indentificator.FLOAT_VALUE;
		Error.ErrorType(block);
		line="";
		return 0;
	}
}
