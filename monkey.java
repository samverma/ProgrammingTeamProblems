//Sam Verma
//Monkey vines

import java.util.Scanner;
public class monkey {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String data = in.nextLine();
		data = data.trim();
		int datasets = Integer.parseInt(data);
		for(int i=0;i<datasets;i++)
		{
			String test = in.nextLine();
			test = test.trim();
			int max=0;
			int count=0;
			for(int j=0;j<test.length();j++)
			{
				if(test.charAt(j)=='[')
				{
					count++;
					if(count>max)max=count;
				}
				if(test.charAt(j)==']')
				{
					count--;
				}
			}
			int result = (int)Math.pow(2,max);
			System.out.println((i+1)+" "+result);
		}
		in.close();
	}
}