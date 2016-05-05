//Sam Verma
//solution to Balloons
import java.util.*;
public class balloons implements Comparable<balloons> {
	//num balloons a team needs and dist to A and B
	public int dA;
	public int dB;
	public int needed;
	//constructor
	public balloons(int k, int da, int db) {
		needed = k;
		dA = da;
		dB = db;
	}
	//to sort relative distance 
	public int compareTo(balloons other) {
		return Math.abs(other.dA-other.dB) - Math.abs(dA-dB);
	}
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		int n = in.nextInt();
		int balA = in.nextInt();
		int balB = in.nextInt();
		while (n != 0 || balA != 0 || balB != 0) {
			balloons[] teams = new balloons[n];
			//get team info
			for (int i=0; i<n; i++) {
				int k = in.nextInt();
				int da = in.nextInt();
				int db = in.nextInt();
				teams[i] = new balloons(k,da,db);
			}
			System.out.println(solve(teams, balA, balB));
			//next case
			n = in.nextInt();
			balA = in.nextInt();
			balB = in.nextInt();
		}
	}
	public static int solve(balloons[] teams, int A, int B) {
		Arrays.sort(teams);
		int dist = 0;
		//go through teams, distributing balloons
		for (int i=0; i<teams.length; i++) {
			int balA = 0;
			//closer to A
			if (teams[i].dA < teams[i].dB) balA = Math.min(A, teams[i].needed);
			//closer to B
			else balA = teams[i].needed - Math.min(B, teams[i].needed);
			//rest from B
			int balB = teams[i].needed - balA;
			//keep track of distance and balloons left
			dist = dist + balA*teams[i].dA + balB*teams[i].dB;
			A -= balA;
			B -= balB;
		}	
		return dist;
	}
}