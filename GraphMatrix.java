
import ADTPackage.Queue.*;
/**
 * Adjacency matrix implementation of a graph
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

	public int getSize(){
		return labels.length;
	}
	public void printGraph() {

        for (int k = 0; k < labels.length; k++) {
            
                System.out.print(labels[k] + "      ");

        }
        System.out.println();


        for (int i = 0; i < edges.length; i++) {
            
            for (int j = 0; j < edges[0].length; j++) {
                if (edges[i][j] == false) {
                    System.out.print(edges[i][j] + "  ");
                } else {
                    System.out.print(edges[i][j] + "   ");
                }

                
            }
            System.out.println();
        }

    }

    public LinkedQueue getBreadthFirstTraversal(int origin) {
       
        LinkedQueue traversalOrder = new LinkedQueue();             
        LinkedQueue vertexQueue = new LinkedQueue();                
        int visitedCounter = 0;
        int[] visited = new int[labels.length];

        traversalOrder.enqueue(origin);                            
        vertexQueue.enqueue(origin);

        while (!vertexQueue.isEmpty()) {

            int frontVertex = vertexQueue.dequeue();                
            int[] neighbors = neighbors(frontVertex);               
            int neighborIndex = 0;                            
            while (neighborIndex != neighbors.length) {             

                int nextNeighbor = neighbors[neighborIndex];
                if (isVisited(visited,nextNeighbor) == false) {     
                    visit(visited, nextNeighbor, visitedCounter);   
                    visitedCounter++;                               

                    traversalOrder.enqueue(nextNeighbor);           
                    vertexQueue.enqueue(nextNeighbor);

                } 
                
                neighborIndex++;

            }

        }
        
        return traversalOrder;        
    }



     public LinkedQueue getDepthFirstTraversal(int origin) {

        LinkedQueue traversalOrder = new LinkedQueue();        
        LinkedStack vertexStack = new LinkedStack();            
        
        int visitedCounter = 0;                                 
        int[] visited = new int[labels.length];                 

        traversalOrder.enqueue(origin);
        vertexStack.push(origin);

        while (!vertexStack.isEmpty()) {

            int topVertex = vertexStack.peek();
            int[] neighbors = neighbors(topVertex);             

            
            if (hasAnUnvisited(visited, neighbors)) {          

                
                int nextNeighbor = neighbors[getUnvisited(visited, neighbors)]; 

                visit(visited, nextNeighbor, visitedCounter);   
                visitedCounter++;                               

                traversalOrder.enqueue(nextNeighbor);
                vertexStack.push(nextNeighbor);

            } else {
                vertexStack.pop();
            }

        }

        return traversalOrder;
    }
    

    private void visit(int[] visitedArray, int vertex , int index) {

        visitedArray[index] = vertex;

    }

    private boolean isVisited(int[] visited, int vertex) {

        boolean result = false;

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == vertex) {
                result = true;
                break;
            }
        }

        return result;
        
    }

    private  boolean hasAnUnvisited(int[] visited, int[] neighbor) {
        
        boolean unvisitedExists = false;
        
        for (int i = 0; i < neighbor.length; i++) {
            int neighborChosen = neighbor[i];

            if (!isVisited(visited, neighborChosen)) {
                unvisitedExists = true;
            }


        }
        
        return unvisitedExists;

    }

    private int getUnvisited(int[] visited, int[] neighbor) {

        int result = 0;

        for (int i = 0; i < neighbor.length; i++) {
            int neighborChosen = neighbor[i];

            if (!isVisited(visited, neighborChosen)) {
                result = i;
                break;
            }

        }

        return result;
    }
	

}
