
import java.util.Iterator;

import ADTPackage.*;
import ADTPackage.Queue.LinkedQueue;
import ADTPackage.Queue.QueueInterface;
import ADTPackage.Stack.LinkedStack;
import ADTPackage.Stack.StackInterface;
import GraphPackage.*;
/**
   A class that implements the ADT directed graph.
*/
public class GraphList<T> implements GraphInterface<T>
{
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	public GraphList()
	{
		vertices = new LinkedDictionary<>();
		edgeCount = 0;
	} // end default constructor

/* Implementations of the graph operations go here. 
   . . . */
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
} // end DirectedGraph
