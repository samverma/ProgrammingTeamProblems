//Sam Verma
//Segment 
import java.util.Scanner;
public class segment {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNext())
		{
			float cx = in.nextFloat();
			float cy = in.nextFloat();
			float radius = in.nextFloat();
			float x1 = in.nextFloat();
			float y1 = in.nextFloat();
			float x2 = in.nextFloat();
			float y2 = in.nextFloat();
			float m=0,temp=0,k=0,disc=0,p1=0,p2=0;
			boolean res=false;
			x1 = x1-cx;
			y1 = y1-cy;
			x2 = x2-cx;
			y2 = y2-cy;
			if(Math.abs(x1-x2)<0.001)
			{
				if(y2<y1)
				{
					temp = y1;
					y1=y2;
					y2=temp;
				}
				disc = radius*radius-x1*x1;
				if(disc>=0)
				{
					k = (float) Math.sqrt((double)disc);
					res = ((y1<=k)&&(k<=y2))||((y1<=-k)&&(-k<=y2));
				}
			}
			else
			{
				m = (y1-y2)/(x1-x2);
				k=y1-m*x1;
				disc = m*m*k*k-(1+m*m)*(k*k-radius*radius);
				if(x2<x1)
				{
					temp=x1;
					x1=x2;
					x2=temp;
				}
				if(y2<y1)
				{
					temp=y1;
					y1=y2;
					y2=temp;
				}
				if(disc>=0)
				{
					p1= (-m*k+(float)Math.sqrt(disc))/(1+m*m);
					p2= m*p1+k;
					res = (x1<=p1)&&(p1<=x2)&&(y1<=p2)&&(p2<=y2);
					if(!res)
					{
						p1= (-m*k-(float)Math.sqrt(disc))/(1+m*m);
						p2= m*p1+k;
						res = (x1<=p1)&&(p1<=x2)&&(y1<=p2)&&(p2<=y2);
					}
				}
			}
			if(res)System.out.println("The line segment intersects the circle.");
			else System.out.println("The line segment does not intersect the circle.");
		}	
		in.close();	
	}
}
class pnt{
	public float X;
	public float Y;
	
	public pnt(float x, float y){
		this.X = x;
		this.Y = y;
	}
}

