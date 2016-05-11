//Sam Verma
//Puzzle;
import java.util.*;
public class puzzle {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		HashMap<Integer,Integer> steps=new HashMap<Integer,Integer>();
		for(int j=0;j<cases;j++)
		{
			steps.put(123456780, 0);
			bfs(steps);
			int[] puzz = new int[9];
			for(int i=0;i<9;i++)
			{
				puzz[i]=in.nextInt();
			}
			System.out.println(steps.get(pc(puzz)));
		}
		in.close();
	}
	public static int[] np(int n)
	{
		int[] puzz = new int[9];
		for(int x=1;x<=9;x++)
		{
			puzz[9-x]=n%10;
			n = n/10;
		}
		return puzz;
	}
	public static int pc(int[] n)
	{
		int tot = 0;
		for(int i=0;i<9;i++)
		{
			tot=n[i]+tot*10;
		}
		return tot;
	}
	public static ArrayList<Integer>possMoves(int n)
	{
		int[] puzz = np(n);
		int pos = 8;
		while(!(n%10==0))
		{
			pos-=1;
			n = n/10;
		}
		int c = pos%3;
		int r = pos/3;
		ArrayList<Integer> fin = new ArrayList<Integer>();
		if (r < 2) 
		{
			flip(r,c,r+1,c,puzz);
			fin.add(pc(puzz));
			flip(r,c,r+1,c,puzz);
		}
		if(r>0)
		{
			flip(r,c,r-1,c,puzz);
			fin.add(pc(puzz));
			flip(r,c,r-1,c,puzz);
		}
		if(c<2)
		{
			flip(r,c,r,c+1,puzz);
			fin.add(pc(puzz));
			flip(r,c,r,c+1,puzz);
		}
		if(c>0)
		{
			flip(r,c,r,c-1,puzz);
			fin.add(pc(puzz));
			flip(r,c,r,c-1,puzz);
		}
		return fin;
	}
	public static void flip(int r,int c,int r1,int c1,int[] puzz)
	{
		int help = puzz[c+r*3];
		puzz[c+r*3]=puzz[c1+r1*3];
		puzz[c1+r1*3]=help;
	}
	public static void bfs(HashMap<Integer, Integer> steps)
	{
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(123456780);
		while(q.size()!=0)
		{
			int key = q.poll();
			int avail = steps.get(key);
			ArrayList<Integer> move = possMoves(key);
			for(int j=0;j<move.size();j++)
			{
				if(steps.containsKey(move.get(j))==false)
				{
					steps.put(move.get(j),1+avail);
					q.offer(move.get((j)));
				}
			}
		}
	}
}
