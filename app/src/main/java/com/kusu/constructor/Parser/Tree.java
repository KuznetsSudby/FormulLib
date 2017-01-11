import java.io.Serializable;


public class Tree {
	public Tree left=null;
	public Tree right=null;
	public String sign=null;
	public String name;
	
	public Tree(String name){
		this.name = name;
	}
	
	public Tree(Tree left, Tree right, String sign, String name){
		this.name = name;
		this.sign = sign;
		this.left = left;
		this.right = right;
	}
	
	public String toString(){
		if ((left!=null)&&(right!=null))
			return "("+left.toString()+""+sign+""+right.toString()+")";	
		else
			return name;
	}
}
