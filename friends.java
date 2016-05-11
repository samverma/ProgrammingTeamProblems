//Sam Verma
//Friends
//Flyod's 
import java.util.Scanner;
public class friends {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int nets = in.nextInt();
		for (int i=0;i<nets;i++)
		{
			friends tcase = new friends(in);
			tcase.sd();
			int yp = tcase.points("You");
			int rivs = in.nextInt();
			System.out.println("Social Network "+(i+1)+":");
			for(int j=0;j<rivs;j++)
			{
				String riv = in.next();
				int rp = tcase.points(riv);
				System.out.print("   "+riv+": Difference of ");
				System.out.println((yp-rp)+" point(s).");	
			}
			System.out.println();
		}
		in.close();
	}
	public String[] members;
	public int[][] dm;
	public int[][] am;
	public int points(String member)
	{
		int ind = get(member);
		if(ind==-1)return 0;
		int points = 0;
		for(int i=0;i<am.length;i++)
		{
			if(am[i][ind]==1)
			{
				points+=1;
			}
		}
		for(int i=0;i<am.length;i++)
		{
			if(dm[i][ind]<51&&dm[i][ind]>0)
			{
				points+=1;
			}
		}
		return points;
	}
	public void sd(){
		for(int i=0;i<dm.length;i++)
		{
			for(int j=0;j<dm.length;j++)
			{
				for(int k=0;k<dm.length;k++)
				{
					if(dm[j][k]>dm[j][i]+dm[i][k])
					{
						dm[j][k]=dm[j][i]+dm[i][k];
					}
				}
			}
		}
	}
	public int get(String member)
	{
		for(int i=0;i<members.length;i++)
		{
			if(members[i].equals(member))
			{
				return i;
			}
		}
		return -1;
	}
	friends(Scanner in)
	{
		int num = in.nextInt();
		members = new String[num];
		for(int i=0;i<num;i++)
		{
			members[i]=in.next();
		}
		int edges = in.nextInt();
		am = new int[num][num];
		dm = new int[num][num];
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<num;j++)
			{
				am[i][j]=51;
			}
		}
		for(int i=0;i<num;i++)
		{
			am[i][i]=0;
		}
		for(int i=0;i<edges;i++)
		{
			String f = in.next();
			String f1 = in.next();
			int ind = get(f);
			int ind1 = get(f1);
			am[ind][ind1]=1;
			am[ind1][ind]=1;
		}
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<num;j++)
			{
				dm[i][j]=am[i][j];
			}
		}
	}
}
