//Sam Verma
//Lotto
import java.util.*;
public class lotto{
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		long dp[][]=new long[11][2001];
		for(long[] a:dp)Arrays.fill(a,0);
		for(int i=1;i<=2000;i++)
		{
			dp[1][i]=(long)i;
		}
		long x=0;
		for(int i=2;i<=10;i++)
		{
			dp[i-1][0]=0;
			for(int j=1;j<=2000;j++)
			{
				dp[i][j]=0;
				long jdiv = j/2;
				for(int k=1;k<=jdiv;k++)
				{
					x=dp[i-1][k];
					x-=dp[i-1][k-1];
					x *= (long)(j-2*k+1);
					dp[i][j]=dp[i][j]+x;
				}
			}
			
		}
		int n = in.nextInt();
		int m = in.nextInt();
		int c=1;
		while(n!=0 && m!=0)
		{
			
			long res = dp[n][m];
			System.out.println("Case "+c+": n = "+n+", m = "+m+", # lists = "+res);
			c+=1;
			n=in.nextInt();
			m=in.nextInt();
		}
		in.close();
	}
}