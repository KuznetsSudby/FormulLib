import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Polish {

	int[][] type={
			{4,1,6,1,1,5},
			{2,2,6,1,1,2},
			{5,5,5,5,5,5},
			{2,2,6,2,1,2},
			{5,1,6,1,1,3}
	};
	
	String exp;
	Stack<String> result = new Stack<String>();
	Stack<String> operand = new Stack<String>();
	
	public Polish(){
	}
	
	public Polish load(String exp){
		this.exp = exp;
		exp=exp+"|| ";
		operand.add("||");
		int ind_st = exp.indexOf(" ");
		int ind_end = exp.indexOf(" ",ind_st+1);
		String op;
		while (ind_end!=-1){
			op = exp.substring(ind_st+1, ind_end);
			if (valid(op)){
				switch (type[getIndexInType(operand.lastElement(),1)][getIndexInType(op,0)]){
					case 1:
						operand.push(op);
						ind_st = ind_end;
						ind_end = exp.indexOf(" ",ind_st+1);
						break;
					case 2:
						result.push(operand.pop());
						break;
					case 3:
						operand.pop();
						ind_st = ind_end;
						ind_end = exp.indexOf(" ",ind_st+1);
						break;
					case 4:
						ind_end = -1;
						break;
					case 6:
						result.push(op);
						ind_st = ind_end;
						ind_end = exp.indexOf(" ",ind_st+1);
						break;
					case 5:
					default:
						Error.ErrorBlock(this.exp);
				}
			}else{
				Error.ErrorBlock(this.exp);
			}
		}/*
		System.out.println("================================");
		for (int i=0; i<result.size(); i++)
			System.out.print(result.elementAt(i)+" ");
		System.out.println("\n================================");*/
		return this;
	}
	
	private boolean valid(String op) {
		if (op.contains("&"))
			return true;
		if (op.contains("||"))
			return true;
		if (op.equals("+"))
			return true;
		if (op.equals("-"))
			return true;
		if (op.equals("/"))
			return true;
		if (op.equals("*"))
			return true;
		if (op.equals("("))
			return true;
		if (op.equals(")"))
			return true;
		return false;
	}
	
	private int getIndexInType(String op, int time) {
		if (op.contains("&"))
			return 2;
		if (op.equals("+"))
			return 1;
		if (op.equals("-"))
			return 1;
		if (op.equals("/"))
			return 3;
		if (op.equals("*"))
			return 3;
		if (op.equals("("))
			return 4;
		if ((op.equals(")"))&&(time==0))
			return 5;
		if (op.equals("||"))
			return 0;
		Error.ErrorBlock(exp);
		return -1;
	}
	
	public Indentificator calculation(Map<String,Indentificator> dict){
		int i = 0;
		Map<String,Indentificator> dictMap = new HashMap<String,Indentificator>();
		dictMap.putAll(dict);
		operand.clear();
		while (!result.isEmpty())
			operand.push(result.pop());
		Stack<Tree> tree = new Stack<Tree>();
		String op;
		String left;
		String right;
		Indentificator temp = null;
		while (operand.size()!=0){
			op = operand.pop();
			if (op.contains("&")){
				tree.push(new Tree(op));
				result.push(op);
			}else{
				right = result.pop();
				left = result.pop();
				switch (operator(op)){
					case 1:
						temp = dictMap.get(left).add(dictMap.get(right));
						break;
					case 2:
						temp = dictMap.get(left).sub(dictMap.get(right));
						break;
					case 3:
						temp = dictMap.get(left).div(dictMap.get(right));
						break;
					case 4:
						temp = dictMap.get(left).mul(dictMap.get(right));
						break;
				}
				Tree right_t = tree.pop();
				Tree left_t = tree.pop();
				tree.push(new Tree(left_t,right_t,op,"&_"+i+"&"));
				result.push("&_"+i+"&");
				dictMap.put("&_"+i+"&", temp);
				i++;
			}
		}
		if (result.size()>1)
			Error.ErrorBlock(this.exp);
			/*
		System.out.println("\n===============дерево "+exp);
		printTree(tree.pop(),0);
		System.out.println("===============конец дерева\n");*/
		return dictMap.get(result.pop());
	}

	private void printTree(Tree tree, int level){
		String tubs="";
		for (int i=0;i<level;i++)
			tubs+="    ";
		if ((tree.left != null)&&(tree.right != null)){
			System.out.println(tubs+""+tree.name+"->");
			printTree(tree.left, level+1);
			System.out.println(tubs+"     "+tree.sign);
			printTree(tree.right, level+1);
		}else{
			System.out.println(tubs+""+tree.name);
		}
	}
	
	private int operator(String op) {
		if (op.equals("+"))
			return 1;
		if (op.equals("-"))
			return 2;
		if (op.equals("/"))
			return 3;
		if (op.equals("*"))
			return 4;
		return 0;
	}
}
