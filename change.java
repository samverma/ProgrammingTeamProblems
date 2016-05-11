//Sam Verma
//Exact Change
import java.util.*;
import java.io.*;
public class change {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numCases = in.nextInt();
		for (int l=0; l<numCases; l++) {
			//read input
			int numCoins = in.nextInt();
			int[] coins = new int[numCoins];
			for (int j=0; j<numCoins; j++)
				coins[j] = in.nextInt();

			//check if too big
			if (!isValid(coins)) System.out.println("Set #"+(l+1)+": Sum is too big");
			System.out.println("Set #"+(l+1)+": "+solve(coins));
			System.out.println();
		}
		in.close();
	}
	//finds smallest positive number that can't be a sum of given coins
	public static int solve(int[] coins) {
		//sort for greedy
		Arrays.sort(coins);
		//can't hit the current target if it's bigger
		int sum = 0, ct = 1, index = 0;
		while (index < coins.length && coins[index] <= ct) {
			sum += coins[index];
			ct = sum+1;
			index++;
		}
		return ct;
	}
	public static boolean isValid(int[] arr) {
		long sum = 0;
		for (int i=0; i<arr.length; i++) sum = sum + arr[i];
		return sum <= 1000000000L;
	}
}
