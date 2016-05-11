//Sam Verma
//Walking
import java.util.*;
public class walking {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++)
		{
			int numlocs = in.nextInt();
			int[] locs = new int[numlocs];
			for(int i=0;i<numlocs;i++)
			{
				locs[i] = in.nextInt();
			}
			int[][] am = new int[numlocs][numlocs];
			for(int i=0;i<numlocs;i++)
			{
				Arrays.fill(am[i], -1);
				am[i][i] = 0;
			}
			int numpaths = in.nextInt();
			for(int i=0;i<numpaths;i++)
			{
				String a = in.next();
				String b = in.next();
				int one = Integer.parseInt(a.replaceAll("[\\D]", ""));
				int two = Integer.parseInt(b.replaceAll("[\\D]", ""));
				int ind1 = one-1;
				int ind2 = two-1;
				am[ind1][ind2] = Math.abs(locs[ind1]-locs[ind2]);
				am[ind2][ind1] = Math.abs(locs[ind1]-locs[ind2]);
			}
			for(int k=0;k<numlocs;k++)
			{
				for(int i=0;i<numlocs;i++)
				{
					for(int j=0;j<numlocs;j++)
					{
						if (am[i][k] >= 0 && am[k][j] >= 0) 
						{
							if (am[i][j] >= 0)
							{
								am[i][j] = Math.min(am[i][j], am[i][k] + am[k][j]);
							}
							else
								am[i][j] = am[i][k] + am[k][j];
						}
					}
				}
			}
			
			int tovisit = in.nextInt();
			int start = in.nextInt()-1;
			int tot = 0;
			for(int i=0;i<tovisit-1;i++)
			{
				int next = in.nextInt()-1;
				tot+=am[start][next];
				start = next;
			}
			System.out.println("The least amount of work on trip "+(l+1)+" is: "+tot);
		}
	}

}
