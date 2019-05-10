import java.util.LinkedList;
import java.util.Queue;


public class Schedule {
	//private ArrayList<Job> jobs; //it is necessary to put all the jobs in an array, so we can call them with get function
	private LinkedList<Job> jobs;
	//private LinkedList<Integer> jobskahns;

	private boolean cycleTrue;

	private boolean hasChanged=true;
	private int min;

	public Schedule() {
		jobs = new LinkedList<Job>();;
	}

	public Job insert(int time) {
		Job newJob = new Job(time);
		newJob.index = jobs.size();
		jobs.add(newJob);
		if(time>min) { // every time we add a new job calculates if it is bigger than the minimum
			min=time;
		}
		hasChanged=true;
		return newJob; 

	}

	public Job get(int index) {
		return jobs.get(index);
	}

	//Directed Acyclic Graph to calculate the shortest path


	public int finish() {
		if(hasChanged==true) {
          kahns();
		}
		if(cycleTrue==true) {
			return -1;
		}
		
			return min;
		
	}
/*
	public boolean cicleTrue() {
		return cicleTrue;
	}
*/
	/*
	public void printKahns() {
		for(int i=0;i<jobskahns.size();i++) {
			System.out.println("Kahns"+jobskahns.size());
			
		}
		int temp=0;
		while(!topologicalStack.empty()) {
			System.out.println("topological"+topologicalStack.get(temp));
			temp++;
		}
	}
	*/
	
	private void kahns() {

		int inDegree[] = new int[jobs.size()];
		
		for (int i = 0; i < jobs.size(); i++) {
			for (int j = 0; j < jobs.get(i).edges.size(); j++) {
				inDegree[jobs.get(i).index]++;
			}
		}
		Queue<Job> L = new LinkedList<>();
		
		for (int i = 0; i < jobs.size(); i++) {
			if (inDegree[i] == 0)
				L.add(jobs.get(i)); // check
		}
	
		while(!L.isEmpty()) {
	          Job topJob = L.remove();
	          
	          for(Job k: topJob.edges) {
	        	  
	        	  k.start = Math.max(k.start, topJob.start + topJob.time);
	        	  
	        	  if(k.start< topJob.start + topJob.time) {
						min=topJob.start + topJob.time;
					}
	        	  k.inDegree--;
	        	  
	        	  if(k.inDegree==0) {
	        		  
	        		  L.add(k);
	        	  }
	          }
		}
		//check for cycle
		
		/*
		for(int i=0;i<jobs.size();i++) {
			if(jobs.get(i).inDegree!=0) {
				jobs.get(i).start=-1;
				cycleTrue=true;
			}
		}
		*/
		
		
		}
	
        

	class Job {
		private int time;//weight
		private LinkedList<Job> edges;
		private int start;
		private int index;
		private int inDegree;
		

		private Job(int time) {
			this.time = time;
			edges = new LinkedList<>();
			start = 0;
			inDegree=0;
			
		}

		// the jobs required to complete a job
		public void requires(Job j) {
			j.edges.add(this);
			hasChanged=true;
			inDegree++;
			
		}

		// the starting time
		public int start() {
			if(hasChanged==true) {
				kahns();
			}
			return start;
		}
	}

}

