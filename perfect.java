//Sam Verma
//Perfection in numbers
import java.util.*;
public class perfect {
	//{6,28,496,8128,33550336,8589869056L,137438691328L};
	public static void main(String[] args){
		List<Long> perfs = new ArrayList<Long>();
		perfs.add((long) 6);
		perfs.add((long) 28);
		perfs.add((long) 496);
		perfs.add((long) 8128);
		perfs.add((long) 33550336);
		perfs.add(8589869056L);
		perfs.add(137438691328L);
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int i=0;i<numcases;i++)
		{
			List<Long> divs = new ArrayList<Long>();
			long pos = in.nextLong();
			if(perfs.contains(pos)){
				System.out.println("perfect");
			}
			else{
				long root=0;
				root = (long)Math.sqrt(pos);
				for(long j=2;j<=root;j++)
				{
					if(pos%j==0)
					{
						if(!divs.contains(j)) divs.add(j);
						if(!divs.contains(pos/j)) divs.add(pos/j); 
					}
				}
				long sum = 1;
				for(Long j:divs) sum+=j;
				if(sum>(pos)) System.out.println("abundant");
		        else System.out.println("defective");	
			}
	    }
		in.close();
	}
}




