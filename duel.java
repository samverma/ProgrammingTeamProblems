//Sam Verma
//Duel
//graph
import java.util.*;
public class duel {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numessays = in.nextInt();
		int numrel = in.nextInt();
		while(numrel>0&&numessays>0)
		{
			duel prob = new duel(in, numessays, numrel);
			LinkedList<Integer> vol = new LinkedList<Integer>();
			for (int i = 0; i < numessays; i++) 
			{
				if (essay[i].indeg == 0)
					vol.add(i);
			}
			boolean mult = false;
			int ordered = 0;
			while (!vol.isEmpty()) {
				if (vol.size() > 1)
					mult = true;
				int v = vol.getFirst();
				vol.removeFirst();
				ordered++;
				for (int i: essay[v].outdeg) {
					(essay[i].indeg)--;
					if (essay[i].indeg == 0)
						vol.add(i);
				}
			}
			if (ordered < numessays)
	                   System.out.println ("0");
			else if (mult)
	                   System.out.println ("2");
			else
	                   System.out.println ("1");
			numessays = in.nextInt();
			numrel = in.nextInt();
		}
	}
	
	static Essay[] essay;
	public duel(Scanner in, int numessays, int numrel)
	{
		essay = new Essay[numessays];
		for (int i = 0; i < numessays; i++)
			essay[i] = new Essay();
            for(int i = 0; i < numrel; i++) 
	        {
				int d = in.nextInt()-1;
				int u = in.nextInt()-1;
				if (d < 0) break;
				(essay[u].indeg)++;
				essay[d].outdeg.add(u);
            }
    
    }
	
	public class Essay {
		public int indeg;
		LinkedList<Integer> outdeg;
		
		public Essay()
		{
			indeg = 0;
			outdeg = new LinkedList<Integer>();
		}
	}
}
