//Sam Verma
//Magazine
import java.util.*;
import java.text.*;
public class Magazine{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++)
		{
			int numpages = in.nextInt();
			int numnotprinted = in.nextInt();
			ArrayList<Integer> notprinted = new ArrayList<Integer>();
			for(int i=0;i<numnotprinted;i++)
			{
				notprinted.add(in.nextInt());
			}
			int numtorn = in.nextInt();
			
			int numsheets = numpages/2;
			double pbt = numtorn/numsheets;
			double pst = 1-pbt;
			
			double esum = 0;
			DecimalFormat dform = new DecimalFormat("#.####");
			for(int i=1;i<=numpages;i++)
			{
				if(!notprinted.contains(i))
				{
					esum+= (i*pst);
				}
			}
			System.out.println(dform.format(esum));
		}
		in.close();
	}
}