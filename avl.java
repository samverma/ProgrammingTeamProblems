//Sam Verma
//AVL tree heist
//avl trees 
import java.util.*;
public class avl {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		for(int i=0;i<cases;i++)
		{
			boolean keep = true;
			Node root = null;
			int inserts = in.nextInt();
			for(int j=0;j<inserts;j++)
			{
				int key = in.nextInt();
				if(keep==false)continue;
				if(j==0) root = new Node(key);
				else
				{
					boolean help= root.insertNode(key);
					keep= (keep&&help);
				}
			}
			if(keep==false) System.out.println("Tree #"+(i+1)+":"+" REMOVE");
			else System.out.println("Tree #"+(i+1)+":"+" KEEP");
		}
	}
}
class Node
{
	int key;
	int bal;
	int height;
	Node par, left, right;
	
	Node(int k){
		key = k;
		height = 0;
		left = null;
		right = null;
	}
	
	public boolean insertNode(int k)
	{
		boolean result = true;
		int hl,hr;
		if(key<k)
		{
			if(right==null)
			{
				right = new Node(k);
				if(left==null)height=1;
			}
			else
			{
				result = (result&&right.insertNode(k));
				if(left==null||left.height<right.height) height=right.height+1;
			}
		}else
		{
			if(left==null)
			{
				left=new Node(k);
				if(right==null)height=1;
			}
			else
			{
				result = (result&&left.insertNode(k));
				if(right==null || right.height<left.height) height=left.height+1;
			}
		}
		if(result==false)return false;
		hr=-1;
		hl=-1;
		if(right!=null)hr=right.height;
		if(left!=null)hl=left.height;
		return (2 > Math.abs(hr-hl));
	}
}



