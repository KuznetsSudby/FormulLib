
public class Error {

	public static void MyExeption(String error) {
		try {
			throw new Exception(error);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void ErrorType(String block) {
		MyExeption("������! �������� ��� ���������� {" + block + "}");
	}

	public static void ErrorVar() {
		MyExeption("������! �������� ��������� ���������� ����� var");
	}

	public static void ErrorName(String str) {
		MyExeption("������! ����������� �������� {" + str + "}");
	}

	public static void ErrorEndDot() {
		MyExeption("������! ��������� ���� 'end.' � ����� ���������");
	}

	public static void ErrorNum(String string) {
		MyExeption("������! ������������ ���������� {" + string + "} � ���������. �� ������� �����.");
	}

	public static void ErrorPair(String string) {
		MyExeption("������! � ���� {" + string + "} �������� ������������������ ");
	}

	public static void ErrorFor(String substring) {
		MyExeption("������! � ����� {" + substring + "}");
	}

	public static void ErrorEndBefore(String str) {
		MyExeption("������! ��������� ';' ����� {" + str + "}");
	}

	public static void ErrorIf(String substring) {
		MyExeption("������! � ��������� if � ������ {" + substring + "}");
	}

	public static void ErrorWhile(String substring) {
		MyExeption("������! � ��������� while � ������ {" + substring + "}");
	}

	public static void ErrorExpression(String expr) {
		MyExeption("������! � ��������� {" + expr + "}");
	}

	public static void ErrorBlock(String block) {
		MyExeption("������! ����������� ���� ���������� {" + block + "}");
	}

	public static void ErrorTypeValuePut(String name, String string) {
		MyExeption("������! ������� ���������� " + name + " ��������� �������� {" + string + "}");
	}

	public static void ErrorTypeValueGet(String name, String string) {
		MyExeption("������! ������� �� ���������� " + name + " ����� �������� {" + string + "}");
	}

	public static void ErrorReadlnIn(String name) {
		MyExeption("������! ������� �� ������ � " + name);
	}

	public static void ErrorInExp(String exp) {
		MyExeption("������! �������������� ����������� � ��������� " + exp);
	}

	public static void ErrorDivByZero() {
		MyExeption("������! ������� �� ����");
	}
}
