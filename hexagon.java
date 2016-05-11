//Sam Verma
//Hexagon Perplexagon
import java.util.*;

public class hexagon {
	private static int PIECES = 7;
	private static int SIDES = 6;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();

		for (int l=0; l<cases; l++) {
			//read in input
			int[][] puzz = new int[PIECES][SIDES];
			for (int i=0; i<PIECES; i++)
				for (int j=0; j<SIDES; j++)
					puzz[i][j] = in.nextInt();

			//call solving function
			int[] res = solve(puzz);

			//result
			if (res == null) System.out.println("Case "+(l+1)+": No solution");
			else {
				System.out.print("Case "+(l+1)+":");
				for (int i=0; i<PIECES; i++) System.out.print(" "+res[i]);
				System.out.println();
			}
		}
		in.close();
	}
	public static int[] solve(int[][] puzz) {
		int[] perm = new int[PIECES];
		boolean[] used = new boolean[PIECES];
		return solveRec(puzz, perm, used, 0);
	}
	public static int[] solveRec(int[][] puzz, int[] perm, boolean[] used, int k) {
		//hit k pieces so have an answer or don't
		if (k == PIECES) {
			if (evaluate(puzz, perm))
				return perm;
			else
				return null;
		}
		//try unused pieces
		for (int i=0; i<PIECES; i++) {
			if (!used[i]) {
				used[i] = true;
				perm[k] = i;

				//returns if solution
				int[] temp = solveRec(puzz, perm, used, k+1);
				if (temp != null) return temp;
				
				used[i] = false;
			}
		}
		return null;
	}
	public static boolean evaluate(int[][] puzz, int[] perm) {
		//copy over
		int[][] cur = new int[PIECES][];
		for (int i=0; i<PIECES; i++) cur[i] = puzz[perm[i]];
		
		//fix middle
		rotate(cur[0], 0, 1);

		//fix rest
		for (int i=1; i<PIECES; i++) rotate(cur[i], (i+2)%SIDES, cur[0][i-1]);

		for (int i=1; i<PIECES; i++) {
			//set to compare i
			int next = i+1;
			if (next >= PIECES) next = 1;
			//check matching sides
			if (cur[i][(i+1)%SIDES] != cur[next][(i+4)%SIDES]) return false;
		}
		return true;
	}
	//rotate piece so fits in match
	public static void rotate(int[] piece, int match, int num) {
		while (piece[match] != num)
			shift(piece);
	}
	//shift ccw
	public static void shift(int[] piece) {
		int temp = piece[0];
		for (int i=1; i<SIDES; i++) piece[i-1] = piece[i];
		piece[SIDES-1] = temp;
	}
}
