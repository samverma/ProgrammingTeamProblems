//Sam Verma
//Profits
import java.util.*;
public class profits {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numdays = in.nextInt();
		while(numdays!=0)
		{	
			int[] profs = new int[numdays];
			for(int i=0;i<numdays;i++)
			{
				profs[i]=in.nextInt();
			}
			int s=profs[0], e=profs[0];
			for (int i=1; i<numdays; i++) 
			{
				e += profs[i];
				if (profs[i] > e) e = profs[i]; 
				if(e>s)s=e;
			}
			System.out.println(s);
			numdays = in.nextInt();
		}
		in.close();
	}
}