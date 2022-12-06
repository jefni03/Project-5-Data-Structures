
import java.util.Iterator;

import ADTPackage.*;
import ADTPackage.Dictionary.UnsortedLinkedDictionary;
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
	private UnsortedLinkedDictionary<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	public GraphList()
	{
		vertices = new UnsortedLinkedDictionary<>();
		edgeCount = 0;
	} // end default constructor


	public boolean addVertex(T vertexLabel)
	{
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        	return addOutcome == null;
	}


	public boolean addEdge(T begin, T end, double edgeWeight)
	{
	   boolean result = false;
	   VertexInterface<T> beginVertex = vertices.getValue(begin);
	   VertexInterface<T> endVertex = vertices.getValue(end);
	   if ( (beginVertex != null) && (endVertex != null) )
		  result = beginVertex.connect(endVertex, edgeWeight);
	   if (result)
		  edgeCount++;
	   return result;
	} // end addEdge
	
	public boolean addEdge(T begin, T end)
	{
	   return addEdge(begin, end, 0);
	} // end addEdge


	public boolean hasEdge(T begin, T end)
	{
	   boolean found = false;
	   VertexInterface<T> beginVertex = vertices.getValue(begin);
	   VertexInterface<T> endVertex = vertices.getValue(end);
	   if ( (beginVertex != null) && (endVertex != null) )
	   {
		  Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
		  while (!found && neighbors.hasNext())
		  {
			 VertexInterface<T> nextNeighbor = neighbors.next();
			 if (endVertex.equals(nextNeighbor))
				found = true;
		  } // end while
	   } // end if
	   return found;
	} // end hasEdge


	public boolean isEmpty()
	{
	   return vertices.isEmpty();
	} // end isEmpty
	
	public void clear()
	{
	   vertices.clear();
	   edgeCount = 0;
	} // end clear
	
	public int getNumberOfVertices()
	{
	   return vertices.getSize();
	} // end getNumberOfVertices
	
	public int getNumberOfEdges()
	{
	   return edgeCount;
	} // end getNumberOfEdges


	protected void resetVertices()
	{
	   Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
	   while (vertexIterator.hasNext())
	   {
		  VertexInterface<T> nextVertex = VertexIterator.next();
		  nextVertex.unvisit();
		  nextVertex.setCost(0);
		  nextVertex.setPredecessor(null);
	   } // end while
	} // end resetVertices



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
