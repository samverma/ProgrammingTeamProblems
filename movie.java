//Sam Verma
//Movie
import java.util.*;
public class movie {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int i=0;i<numcases;i++){
			int numtix = in.nextInt();
			int numslices = in.nextInt();
			int total = 0;
			if(numtix>=10){
				total = numslices+(numtix*10);
			}
			else{
				total = (numslices*2)+(numtix*10);
			}
			System.out.println(total);
		}
		in.close();
	}
}