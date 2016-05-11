//Sam Verma
//Alphabet Soup
//network flow 
import java.util.*;
public class alphabet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		for(int l=0;l<numcases;l++)
		{
			String red = in.next();
			String green = in.next();
			int size=0;
			size = green.length()+red.length();
			int[][] am = new int[size+2][size+2];
			for(int[] a:am)
			{
				Arrays.fill(a, 0);
			}
			for(int i=1;i<=red.length();i++)
			{
				am[0][i]=1;
			}
			for(int i=1+red.length();i<=size;i++)
			{
				am[i][size+1]=1;
			}
			for(int i=1;i<=red.length();i++)
			{
				int grncount=0;
				for(int j=1+red.length();j<=size;j++)
				{
					int diff = Math.abs(red.charAt(i-1)-green.charAt(grncount));
					//System.out.println(diff);
					if(diff>=3) am[i][j]=1;
					else am[i][j]=0;
					grncount++;
				}
			}
			//System.out.println(Arrays.deepToString(am));
			networkflow alpha = new networkflow(am,0,size+1);
			int max = alpha.getMaxFlow();
			System.out.println(max);
		}
		
		in.close();
	}
	
}
//edge for nf
class Edge {
	private int capacity;
	private int flow;

	//initialize with no flow 
	public Edge(int cap) {
		capacity = cap;
		flow = 0;
	}

	//what we can push forward 
	public int maxPushForward() {
		return capacity - flow;
	}

	//what we can push backward 
	public int maxPushBackward() {
		return flow;
	}

	//push more flow if cap there, otherwise false 
	public boolean pushForward(int moreflow) {
		if (flow+moreflow > capacity)
			return false;
		flow += moreflow;
		return true;
	}

	//push less flow back if cap there, otherwise back 
	public boolean pushBack(int lessflow) {
		if (flow < lessflow)
			return false;
		flow -= lessflow;
		return true;
	}
}
//direction of edge
class direction {
	//previous visited node is aug path and dir for edge
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
	//positive entries are valid flows 
	public networkflow(int[][] flows, int start, int end) {
		//stores graph as am
		source = start;
		dest = end;
		adjMat = new Edge[flows.length][flows.length];
		for (int i=0; i<flows.length; i++) {
			for (int j=0; j<flows[i].length; j++) {
				//fill in flow.
				if (flows[i][j] > 0)
					adjMat[i][j] = new Edge(flows[i][j]);
				else
					adjMat[i][j] = null;
			}
		}
	}
	public ArrayList<direction> findAugmentingPath() {
		//store prev visited node in bfs 
		direction[] prev = new direction[adjMat.length];
		boolean[] inQueue = new boolean[adjMat.length];
		for (int i=0; i<inQueue.length; i++)
			inQueue[i] = false;

		//no prev node for source 
		prev[source] = new direction(-1, true);

		LinkedList<Integer> bfs_queue = new LinkedList<Integer>();
		bfs_queue.offer(new Integer(source));
		inQueue[source] = true;

		//bfs until queue cleared 
		while (bfs_queue.size() > 0) {

			//add new neighbors of current node .
			Integer next = bfs_queue.poll();
			
			//find neighbors and add to queue, these are the forward edges 
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
		//no aug path 
		if (!inQueue[dest])
			return null;

		ArrayList<direction> path = new ArrayList<direction>();

		direction place = prev[dest];

		direction dummy = new direction(dest, true);
		path.add(dummy);

		//build path backwards 
		while (place.prev != -1) {
			path.add(place);
			place = prev[place.prev];
		}
		//reverse 
		Collections.reverse(path);
		return path;
	}
	//max flow alg 
	public int getMaxFlow() {
		int flow = 0;
		ArrayList<direction> nextpath = findAugmentingPath();
		//loop until no more aug paths 
		while (nextpath != null) {
			//check best flow 
			int this_flow = Integer.MAX_VALUE;
			
			//loop finds min val of all extra caps 
			for (int i=0; i<nextpath.size()-1; i++) {

				if (nextpath.get(i).forward) {
					this_flow = Math.min(this_flow, adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].maxPushForward());
				}
				else {
					this_flow = Math.min(this_flow, adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].maxPushBackward());
				}
			}
			//put flow through 
			for (int i=0; i<nextpath.size()-1; i++) {

				if (nextpath.get(i).forward) {
					adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].pushForward(this_flow);
				}
				else {
					adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].pushBack(this_flow);
				}
			}
			//add flow in and get next path 
			flow += this_flow;
			nextpath = findAugmentingPath();
		}
		return flow;
	}
}
