//Sam Verma
//Armageddon
//lcm
import java.util.*;
public class collide {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int np = in.nextInt();
		long ss = 1;
		while(np!=0)
		{
			long[] times = new long[np];
			for(int i=0;i<np;i++)
			{
				times[i]=in.nextInt();
			}
			long lcm =0;
			lcm = reclcm(times);
			if(lcm>0&&lcm<=2147483647) 
				System.out.println(ss+": THE WORLD ENDS IN "+lcm+" DAYS");
			else
				System.out.println(ss+": NOT TO WORRY");
			np = in.nextInt();
			ss+=1;
		}
	}
	public static long gcd(long a, long b)
	{
		long temp;
		while(b>0)
		{
			temp=b;
			b=a%b;
			a=temp;
		}
		return a;
	}
	public static long  lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	public static long reclcm(long[] nums)
	{
	    long res = nums[0];
	    for(int i = 1; i < nums.length; i++)
	    {
	    	res = lcm(res, nums[i]);	
	    }
	    return res;
	}
}


