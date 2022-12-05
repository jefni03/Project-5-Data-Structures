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
	public QueueInterface <T> getBreadthFirstTraversal(T origin)
	{
		resetVertices();
		QueueInterface<T> traversalOrder = new LinkedQueue<T>();
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();
		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexQueue.enqueue(originVertex);

		while(!vertexQueue.isEmpty())
		{
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited())
				{
					nextNeighbor.visit();
					traversalOrder.enqueue(nextNeighbor.getLabel());
					vertexQueue.enqueue(nextNeighbor);
				}
			}
		}
		return traversalOrder;
	}
	public QueueInterface <T> getDepthFirstTraversal (T origin)
	{
		resetVertices();
		QueueInterface<T> traversalOrder = new LinkedQueue<T>();
		StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexStack.push(originVertex);
		while (!vertexStack.isEmpty())
		{
			VertexInterface<T> topVertex = vertexStack.peek();
			VertexInterface<T> nextNeighbor =topVertex.getUnvisitedNeighbor();
			if (nextNeighbor != null)
			{
				nextNeighbor.visit();
				traversalOrder.enqueue(nextNeighbor.getLabel());
				vertexStack.push(nextNeighbor);
			}
			else
				vertexStack.pop();
		}
		return traversalOrder;
	}
}
