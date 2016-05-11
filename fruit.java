//Sam Verma
//Fruit
import java.util.Arrays;
import java.util.Scanner;
public class fruit {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numstands = in.nextInt();
		int[] numsold;
		for(int i=0;i<numstands;i++){
			int numdays = in.nextInt();
			numsold = new int[numdays];
			int total = 0;
			for(int j=0;j<numdays;j++){
				numsold[j] = in.nextInt();
				total += numsold[j];
			}
			int dailyavg = (int)Math.ceil(total/(double)numdays);
			int dailymax = 0;
			for(int x:numsold){
				if(x>dailymax) dailymax=x;
			}
			boolean works=false;
			int maxleft = 0;
			int stock;
			int dailyleft;
			int order=0;
			for(int k=dailyavg;k<=dailymax;k++){
				maxleft=0;
				stock = 0;
				dailyleft=0;
				for(int x=0;x<numsold.length;x++){
					stock = dailyleft;
					stock+=k;
					if(stock>=numsold[x]){
						dailyleft = stock-numsold[x];
						works = true;
						if(dailyleft>=maxleft) maxleft = dailyleft;
						
					}
					else{
						works=false;
						break;
					}
				}
				if(works==true){
					order=k;
					break;
				}
			}
			System.out.println(order+" "+maxleft); 
		}
	}

}