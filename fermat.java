//Sam Verna
//Fermat
import java.util.*;
public class fermat {
	public static void main(String[] args)  
	{
		int N = 1000000;
		int[] primes = new int[N+1];
		Arrays.fill(primes, 1);
		primes[0]=0;
		primes[1]=0;
		for(int i=2;i*i<=N;i++)
		{
			for(int j=2*i;j<=N;j+=i)
			{
				primes[j]=0;
			}
		}
		int[] sqsums = new int[N+1];
		Arrays.fill(sqsums, 0);
		sqsums[2]=1;
		for(int i=3;i<=N;i+=2)
		{
			if(primes[i]==1 && (i%4==1))
			{
				sqsums[i]=1;
			}
		}
		for(int i=1;i<N+1;i++)
		{
			primes[i]+=primes[i-1];
			sqsums[i]+=sqsums[i-1];
		}
		Scanner in = new Scanner(System.in);
		int low = in.nextInt();
		int up = in.nextInt();
		while(!(low==-1&&up==-1))
		{	int tl=low,tu=up;
			if(tl<=0) tl=1;
			if(tu<=0) tu=1;
			int totprimes = primes[tu]-primes[tl-1];
			int totsqsums = sqsums[tu]-sqsums[tl-1];
			System.out.println(low+" "+up+" "+totprimes+" "+totsqsums);
			low = in.nextInt();
			up = in.nextInt();
		}
		in.close();	
	}
	
}
