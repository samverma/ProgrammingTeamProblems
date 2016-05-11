//Sam Verma
//Next perm
import java.util.*;
public class perm{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++){
			int c = in.nextInt();
			String num = in.next();
			String[] strnum = new String[num.length()];
			int[] numarray = new int[num.length()];
			for(int i=0;i<num.length();i++){
				strnum[i] = String.valueOf(num.charAt(i));
				numarray[i]=Integer.parseInt(strnum[i]);
			}
			int[] nextperm = nextPerm(numarray);
			if(Arrays.equals(nextperm,numarray)){
				System.out.println(c+" BIGGEST");
			}
			else
			{
				String res = "";
				for(int i:nextperm)
				{
					res+=Integer.toString(i);
				}
				System.out.println(c+" "+res);
			}
		}
		in.close();
	}
	
	public static int[] nextPerm(int[] array) {
		int[] clon = new int[array.length];
		System.arraycopy(array, 0, clon, 0, array.length);
		int i = clon.length - 1;
		while (i > 0 && clon[i - 1] >= clon[i])
			i--;
	  
		if (i <= 0)
			return array;

		int j = clon.length - 1;
		while (clon[j] <= clon[i - 1])
			j--;
		
		int temp = clon[i - 1];
		clon[i - 1] = clon[j];
		clon[j] = temp;
		j = clon.length - 1;
		while (i < j) {
			temp = clon[i];
			clon[i] = clon[j];
			clon[j] = temp;
			i++;
			j--;
		}
		return clon;
	}
}