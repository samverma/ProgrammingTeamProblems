//Sam Verma
//Birdman
//2d geometry
import java.io.*;
import java.util.*;
public class birdman 
{
	static int numints = 8;

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for (int l=0; l<numcases; l++)
		{
			int[] coords = new int[numints];
			for (int i=0; i<numints; i++)coords[i] = in.nextInt();
			line line1 = new line(new point(coords[0], coords[1]), new point(coords[2], coords[3]));
			line line2 = new line(new point(coords[4], coords[5]), new point(coords[6], coords[7]));
			if (line2.checkinter(line1)) System.out.println("Move to the left or right!");
			else System.out.println("Good picture, Birdman!");
		}
		in.close();
	}
}
class line 
{
	
	public point s;
	public point e;
	public point dir;
	double eps = 1e-9;
	public line(point b, point f) 
	{
		s = b;
		e = f;
		dir = s.vect(e);
	}
	public boolean checkinter(line pt) 
	{
		double den = det(dir.x, -pt.dir.x, dir.y, -pt.dir.y);
		if (den == 0) 
		{
			point vect = s.vect(pt.s);
			if (vect.mag(dir) != 0) return false;
			double root1 = root(pt.s);
			double root2 = root(pt.e);
			return !((root1 > 1 && root2 > 1) || (root1 < 0 && root2 < 0));
		}
		double num1 = det(pt.s.x-s.x, -pt.dir.x, pt.s.y-s.y, -pt.dir.y);
		double num2 = det(dir.x, pt.s.x-s.x, dir.y, pt.s.y-s.y);
		return -eps <= num1/den && num1/den <= 1+eps && -eps <= num2/den && num2/den <= 1+eps;
	}
	public static double det(double a, double b, double c, double d) 
	{
		return a*d - b*c;
	}
	public double root(point dest) 
	{
		if (dir.x != 0) return 1.0*(dest.x - s.x)/dir.x;
		else return 1.0*(dest.y - s.y)/dir.y;
	}
}
class point 
{
	public int x;
	public int y;
	public point(int one, int two) 
	{
		x = one;
		y = two;
	}
	public point vect(point pt) 
	{
		return new point(pt.x-x, pt.y-y);
	}
	public double mag(point pt) 
	{
		return x*pt.y - pt.x*y;
	}
}