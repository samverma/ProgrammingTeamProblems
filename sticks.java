//Sam Verma
//Stick splitting
import java.util.*;
public class sticks{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++){
			int length = in.nextInt();
			int cuts = in.nextInt();
			int[] cutarr = new int[cuts+2];
			cutarr[0]=0;
			cutarr[cutarr.length-1]=length;
			for(int i=1;i<cuts+1;i++){
				cutarr[i]=in.nextInt();	
			}
			int[][] table = new int[cutarr.length][cutarr.length];
			for(int i=0;i<table.length;i++) Arrays.fill(table[i],-1);
			System.out.println(cost(table, cutarr, 0, cutarr.length-1));
		}
	}
	public static int cost(int[][] table, int[] cutarr, int s, int e) {
		int min = Integer.MAX_VALUE;
		if (s >= e-1) return 0;
		if (table[s][e] != -1) return table[s][e];
		for (int part=s+1; part<e; part++){
			int cost = cutarr[e] - cutarr[s] + cost(table, cutarr, s, part) + cost(table, cutarr, part, e);
			if (cost < min) min = cost;
		}
		table[s][e] = min;
		return min;
	}
}