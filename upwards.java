//Sam Verma
//solution to Up-wards
import java.util.Scanner;

public class upwards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int numwords = in.nextInt();
		for(int i=0;i<numwords;i++){
			String next = in.next();
			if(isUpWord(next)) System.out.println("YES");
			else System.out.println("NO");
		}
	}
	
	static boolean isUpWord(String s){
		if(s.length()==1) return true;
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i+1)<=s.charAt(i)) return false;
		}
		return true;
	}

}
