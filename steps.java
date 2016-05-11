//Sam Verma
//Steps 
import java.util.*;
public class steps {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numlocs = in.nextInt();
		int[][] am = new int[numlocs][numlocs];
		for(int i=0;i<numlocs;i++)
		{
			for(int j=0;j<numlocs;j++)
			{
				am[i][j]= in.nextInt();
			}
		}
		int numwalks = in.nextInt();
		for(int i=0;i<numwalks;i++)
		{
			String walk = in.next();
			char[] arr = walk.toCharArray();
			int dist =0;
			for(int j=1;j<arr.length;j++)
			{
				int loc1 = chartoInt(arr[j-1]);
				int loc2 = chartoInt(arr[j]);
				dist+=am[loc1-1][loc2-1];
				
				
			}
			System.out.println(dist);
		}
		in.close();	
	}
	public static int chartoInt(char a){
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return letters.indexOf(a)+1;
	}
}
