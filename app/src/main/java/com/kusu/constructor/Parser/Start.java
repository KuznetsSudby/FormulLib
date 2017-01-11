import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Start {

	public static String path = "input.txt";
	public static Parser parser = new Parser();
	
	public static void main(String[] args) {
		try (Scanner s = new Scanner(new File(path))) {
			StringBuilder prog = new StringBuilder(" ");
			while (s.hasNext())
				prog.append(s.nextLine() + '\n');
			
			parser.parseString(
					new Refresher(prog.toString().toLowerCase())
					.addEndsToString()
					.deleteComments()
					.changeStrings(parser.dict)
					.addSpace()
					.printLine()
					.deleteSlashN()
					.deleteSecondSpace()
					.addVarAndConst(parser.dict)
					.printLineFromOneLine()
					.toString()
					);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
