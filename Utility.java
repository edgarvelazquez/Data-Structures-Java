
public class Utility {
	static boolean[] find(Graph g) {
		boolean[] largest = new boolean[g.size()]; // maximum values
		Graph subGraph = null;
		int newSize = g.size();

		while (newSize > 0 && !g.has(newSize)) { // finds the largest clique in the graph
			newSize--;
		}
		int counter = 0;
		for (int i = 0; i < largest.length; i++) {
			Graph temporary;

			if (subGraph == null) {
				temporary = g.remove(counter);
			} else {
				temporary = subGraph.remove(counter);
			}

			if (!temporary.has(newSize)) {

				largest[i] = true;
				counter++; // moves on to the next vertices
			}

			else {
				subGraph = temporary;
			}
		}

		return largest;
	}

}
