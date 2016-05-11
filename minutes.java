//Sam Verma
//Minutes
import java.util.*;
public class minutes {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int times = in.nextInt();
		for(int i=0;i<times;i++)
		{
			int days = in.nextInt();
			int hours = in.nextInt();
			int min = in.nextInt();
			int tot = (days*(24*60))+(hours*60)+min;
			System.out.println(tot);
		}
		in.close();
	}

}
