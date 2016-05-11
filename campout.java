//Sam Verma
//Campout
//network flow 
import java.util.*;
public class campout {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numgroups = in.nextInt();
		int limit = 80;
		int students = 10;
		int hours = 168;
		int shifts = hours/4;
		int needed = shifts*3;
		int daily = 6;
		int size = shifts+students;
		for(int l=0;l<numgroups;l++)
		{
			int[][] am = new int[size+2][size+2];
			for(int i=1;i<=students;i++)
			{
				am[0][i]=limit/4;
			}
			for(int i=11;i<=size;i++)
			{
				am[i][size+1]=3;
			}
			for(int i=1;i<=students;i++)
			{
				boolean[] available = new boolean[shifts];
				for(int j=0;j<shifts;j++)
				{
					available[j]=true;
				}
				int busytimes = in.nextInt();
				for(int j=0;j<busytimes;j++)
				{
					int day=in.nextInt()-1;
					int start=in.nextInt();
					int end=in.nextInt();
					int s = day*daily+start/4;
					int e = day*daily+(end+3)/4;
					for(int k=s;k<e;k++)
					{
						available[k]=false;
					}
				}
				for(int j=0;j<shifts;j++)
				{
					if(available[j]) am[i][j+students+1]=1;
				}
			}
			networkflow co = new networkflow(am,0,size+1);
			int coveredtime = co.getMaxFlow();
			if(coveredtime==needed)
				System.out.println("Case #"+(l+1)+": YES");
			else
				System.out.println("Case #"+(l+1)+": NO");
			System.out.println();
		}
		in.close();
	}

}

class Edge {
	private int capacity;
	private int flow;

	public Edge(int cap) {
		capacity = cap;
		flow = 0;
	}
	public int maxPushForward() {
		return capacity - flow;
	}
	public int maxPushBackward() {
		return flow;
	}
	public boolean pushForward(int moreflow) {
		if (flow+moreflow > capacity)
			return false;
		flow += moreflow;
		return true;
	}
	public boolean pushBack(int lessflow) {
		if (flow < lessflow)
			return false;

		flow -= lessflow;
		return true;
	}
}
class direction {
	//store prev node visited and dir of edge 
	public int prev;
	public boolean forward;

	public direction(int node, boolean dir) {
		prev = node;
		forward = dir;
	}

	public String toString() {
		if (forward)
			return "" + prev + "->";
		else
			return "" + prev + "<-";
	}
}
class networkflow {
	private Edge[][] adjMat;
	private int source;
	private int dest;
	public networkflow(int[][] flows, int start, int end) {
		//store graph as am 
		source = start;
		dest = end;
		adjMat = new Edge[flows.length][flows.length];
		for (int i=0; i<flows.length; i++) {
			for (int j=0; j<flows[i].length; j++) {
				if (flows[i][j] > 0)
					adjMat[i][j] = new Edge(flows[i][j]);
				else
					adjMat[i][j] = null;
			}
		}
	}
	public ArrayList<direction> findAugmentingPath() {
		//store nodes visited in bfs 
		direction[] prev = new direction[adjMat.length];
		boolean[] inQueue = new boolean[adjMat.length];
		for (int i=0; i<inQueue.length; i++)
			inQueue[i] = false;
		//no previous node for source 
		prev[source] = new direction(-1, true);

		LinkedList<Integer> bfs_queue = new LinkedList<Integer>();
		bfs_queue.offer(new Integer(source));
		inQueue[source] = true;
		//bfs goes until we clear out queue 
		while (bfs_queue.size() > 0) {
			//add neighbors of current code 
			Integer next = bfs_queue.poll();
			for (int i=0; i<adjMat.length; i++)
				if (!inQueue[i] && adjMat[next][i] != null && adjMat[next][i].maxPushForward() > 0) {
					bfs_queue.offer(new Integer(i));
					inQueue[i] = true;
					prev[i] = new direction(next, true);
				}
			//look for back edges
			for (int i=0; i<adjMat.length; i++)
				if (!inQueue[i] && adjMat[i][next] != null && adjMat[i][next].maxPushBackward() > 0) {
					bfs_queue.offer(new Integer(i));
					inQueue[i] = true;
					prev[i] = new direction(next, false);
				}
		}
		//no aug path found 
		if (!inQueue[dest])
			return null;

		ArrayList<direction> path = new ArrayList<direction>();
		direction place = prev[dest];
		direction dummy = new direction(dest, true);
		path.add(dummy);
		while (place.prev != -1) {
			path.add(place);
			place = prev[place.prev];
		}
		Collections.reverse(path);
		return path;
	}

	//max flow
	public int getMaxFlow() {

		int flow = 0;

		ArrayList<direction> nextpath = findAugmentingPath();
		while (nextpath != null) {
			int this_flow = Integer.MAX_VALUE;
			for (int i=0; i<nextpath.size()-1; i++) {

				if (nextpath.get(i).forward) {
					this_flow = Math.min(this_flow, adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].maxPushForward());
				}
				else {
					this_flow = Math.min(this_flow, adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].maxPushBackward());
				}
			}
			for (int i=0; i<nextpath.size()-1; i++) {

				if (nextpath.get(i).forward) {
					adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].pushForward(this_flow);
				}
				else {
					adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].pushBack(this_flow);
				}
			}
			flow += this_flow;
			nextpath = findAugmentingPath();
		}

		return flow;
	}

}