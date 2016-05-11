//Sam Verma
//Spidey dilemma
import java.util.*;

public class spidey {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++)
		{
			int numverts = in.nextInt();
			int numconn = in.nextInt();
			
			int[] conn = new int[numverts];
			boolean sameside=false;
			boolean haveconn=true;
			for(int i=0;i<numverts;i++) conn[i]=i;
			for(int c=0;c<numconn;c++)
			{
				int x = in.nextInt();
				int y = in.nextInt();
				if(x%2==y%2) sameside=true;
				int conn1 = conn[x];
				for(int i=0;i<numverts;i++)
					if(conn[i]==conn1) conn[i]=conn[y];
			}
			for(int i=0;i<numverts;i++)
			{
				if(conn[0]!=conn[i]) haveconn = false;
			}
			
			if(!sameside && haveconn)
				System.out.println("Way to go, Spider-Man!");
			else 
				System.out.println("It's the end of the world!");
			System.out.println();
		}
		in.close();
	}
}