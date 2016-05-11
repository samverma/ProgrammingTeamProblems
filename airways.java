//Sam Verma
//Airways
//topsort 

import java.util.*;
import java.io.*;

public class airways {
	public static int[] res;
	public static int numedge;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		for (int l=0; l<cases; l++) {
			//read in dests 
			numedge = in.nextInt();
			Edge[] edges = new Edge[numedge];
			//keeps track of degrees
			HashMap<String,Integer> hm = new HashMap<String,Integer>();
			int id = 0;
			int[] degs = new int[2*numedge];

			for (int i=0; i<numedge; i++) {
				//read in edges 
				String one = in.next();
				String two = in.next();
				int num = in.nextInt();
				edges[i] = new Edge(one,two,num);
				if (!hm.containsKey(one))
					hm.put(one, id++);
				if (!hm.containsKey(two))
					hm.put(two, id++);
				//update vertex degs
				degs[hm.get(one)]++;
			}
			System.out.println(id);
			Dest[][] dests = new Dest[id][];
			for (int i=0; i<id; i++)
				dests[i] = new Dest[degs[i]];

			//forn edge list 
			int[] currind = new int[id];
			int[] currdegs = new int[id];
			for (int i=0; i<numedge; i++) {
				int from = hm.get(edges[i].start);
				int to = hm.get(edges[i].end);
				dests[from][currind[from]++] = new Dest(to, edges[i].num);
				currdegs[to]++;
			}

			res = new int[numedge];
			topSort(dests, currdegs);

			// Output result.
			for (int i=0; i<res.length-1; i++)
				System.out.print(res[i]+" ");
			System.out.println(res[res.length-1]);
		}
		in.close();
	}
	public static void topSort(Dest[][] dests, int[] currdegs) {
		//set up edges 
		PriorityQueue<Dest> flightList = new PriorityQueue<Dest>();
		for (int i=0; i<currdegs.length; i++) {
			if (currdegs[i] == 0) {
				for (int j=0; j<dests[i].length; j++)
					flightList.offer(dests[i][j]);
			}
		}
		//loop and place flights
		for (int i=0; i<numedge; i++) {
			Dest place = flightList.poll();
			res[i] = place.num;
			int next = place.dest;
			//update degs based on edge
			currdegs[next]--;
			if (currdegs[next] == 0) {
				for (int j=0; j<dests[next].length; j++)
					flightList.offer(dests[next][j]);
			}
		}
	}
}
class Edge {
	public String start;
	public String end;
	public int num;

	public Edge(String one, String two, int n) {
		start = one;
		end = two;
		num = n;
	}
}
class Dest implements Comparable<Dest> {
	public int dest;
	public int num;

	public Dest(int where, int flight) {
		dest = where;
		num = flight;
	}
	public int compareTo(Dest other) {
		return this.num - other.num;
	}
}