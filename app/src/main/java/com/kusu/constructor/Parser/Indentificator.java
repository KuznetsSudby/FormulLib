

public class Indentificator {
	public final static int VARIABLE=0;
	public final static int INT_VALUE=1;
	public final static int FLOAT_VALUE=2;
	public final static int INT_CONST=3;
	public final static int FLOAT_CONST=4;
	public final static int STRING=5;
	
	public int type;
	public String value="";
	
	public Indentificator(String value, int type){
		this.value = value;
		this.type = type;
	}
	
	public String toString(){
		return "| значение: "+value+" \n| тип: "+getStringType();
	}
	
	public String getStringType(){
		switch (type){
		case VARIABLE:
			return "Неизвестный тип";
		case INT_VALUE:
			return "Целочисленная переменная";
		case FLOAT_VALUE:
			return "Переменная с плавающей запятой";
		case INT_CONST:
			return "Целочисленная константа";
		case FLOAT_CONST:
			return "Константа с плавающей запятой";
		case STRING:
			return "Строка";
		default:
			return "error";
		}
	}

	public boolean isConst() {
		if (type == INT_VALUE)
			return false;
		if (type == FLOAT_VALUE)
			return false;
		return true;
	}

	public boolean isNotDigit() {
		if (type == VARIABLE)
			return true;
		if (type == STRING)
			return true;
		return false;
	}

	public boolean getBooleanValue() {
		if (value.equals("0"))
			return false;
		else
			return true;
	}

	public float getFloatValue() {
		if ((type == VARIABLE)|| (type == STRING))
			Error.ErrorTypeValueGet(value,"int или float");
		return Float.parseFloat(value);
	}
	
	public int getIntValue() {
		return Integer.parseInt(value);
	}
	
	public Indentificator mul(Indentificator ind){
		if (this.isNotDigit())
			Error.ErrorTypeValueGet(this.value,"int или float");
		if (ind.isNotDigit())
			Error.ErrorTypeValueGet(ind.value,"int или float");
		
		if (isInt(ind.type,this.type))
			return new Indentificator(""+(this.getIntValue()*ind.getIntValue()), INT_VALUE);
		else
			return new Indentificator(""+(this.getFloatValue()*ind.getFloatValue()), FLOAT_VALUE);
	}
	
	public Indentificator add(Indentificator ind){
		if (isString(ind.type,this.type))
			return new Indentificator(this.value+ind.value, STRING);	
		if (this.isNotDigit())
			Error.ErrorTypeValueGet(this.value,"int или float");
		if (ind.isNotDigit())
			Error.ErrorTypeValueGet(ind.value,"int или float");
		if (isInt(ind.type,this.type))
			return new Indentificator(""+(this.getIntValue()+ind.getIntValue()), INT_VALUE);
		else
			return new Indentificator(""+(this.getFloatValue()+ind.getFloatValue()), FLOAT_VALUE);
	}
	
	private boolean isString(int type2, int type3) {
		if (type2 == STRING)
			return true;
		if (type3 == STRING)
			return true;
		return false;
	}

	public Indentificator sub(Indentificator ind){
		if (this.isNotDigit())
			Error.ErrorTypeValueGet(this.value,"int или float");
		if (ind.isNotDigit())
			Error.ErrorTypeValueGet(ind.value,"int или float");
		
		if (isInt(ind.type,this.type))
			return new Indentificator(""+(this.getIntValue()-ind.getIntValue()), INT_VALUE);
		else
			return new Indentificator(""+(this.getFloatValue()-ind.getFloatValue()), FLOAT_VALUE);
	}

	public Indentificator div(Indentificator ind){
		if (this.isNotDigit())
			Error.ErrorTypeValueGet(this.value,"int или float");
		if (ind.isNotDigit())
			Error.ErrorTypeValueGet(ind.value,"int или float");
		
		if (ind.getFloatValue()==0)
			Error.ErrorDivByZero();
		
		if (isInt(ind.type,this.type))
			return new Indentificator(""+(this.getIntValue()/ind.getIntValue()), INT_VALUE);
		else
			return new Indentificator(""+(this.getFloatValue()/ind.getFloatValue()), FLOAT_VALUE);
	}
	
	private boolean isInt(int type2, int type3) {
		if ((type2 != INT_VALUE)&&(type2!=INT_CONST))
			return false;
		if ((type3 != INT_VALUE)&&(type3!=INT_CONST))
			return false;
		return true;
	}
}
