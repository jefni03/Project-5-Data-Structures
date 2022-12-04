/**
 * Graph using a 2d array implementation / adjacency matrix
 */
public class GraphMatrix<E>{
	private boolean[][] edges;
	private E[] labels;

	/**
	 * Constructs graph of n vertices
	 * @param n number of vertices
	 */
	public GraphMatrix(int n){
		edges = new boolean[n][n];
		labels = (E[]) new Object[n];
	}

	/**
	 * @param vertex an int representation of vertex
	 * @return label of vertex
	 */
	public E getLabel(int vertex){
		return labels[vertex];
	}

	public boolean isEdge(int source, int target){
		return edges[source][target];
	}

	public void addEdge(int source, int target){
		edges[source][target] = true;
	}

	public int[] neighbors(int vertex){
		int i;
		int count = 0;
		int[] answer;
		for(int i=0; i<labels.length; i++){
			if(edges[vertex][i]){
				count++;
			}
		}
		answer = new int[count];
		count = 0;
		for(int i=0; i<labels.length; i++){
			if(edges[vertex][i]){
				answer[count] = i;
				count++;
			}
		}
		return answer;
	}

	public void removeEdge(int source, int target){
		edges[source][target] = false;
	}

	public void setLabel(int vertex, E newLabel){
		labels[vertex] = newLabel;
	}

	public int size(){
		return labels.length;
	}

	//still need to add breadthfirst and depthfirst transversal methods
}
