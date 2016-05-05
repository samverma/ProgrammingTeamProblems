//Sam Verma
//solution to Conference
import java.util.Scanner;

public class conference {
	public static void main(String[] args) {
		int days = 30;
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		
		for(int i=0;i<numcases;i++){
			int numoffers = in.nextInt();
			int[] offarr = new int[days];
			boolean[] isbooked = new boolean[days];
			long earned = 0;
			for(int j=0;j<numoffers;j++){
				int start = in.nextInt();
				int clength = in.nextInt();
				offarr[start]=clength;
			}
			for(int j=0;j<days;j++){
				if(isbooked[j]==false){
					for(int k=j;k<j+offarr[j];k++){
						isbooked[k]=true;
					}
				}
			}
			for(int j=0;j<days;j++){
				if(isbooked[j]){
					earned+=Math.pow(2,29-j);
				}
			}
			System.out.println(earned);
		}
	}

}

