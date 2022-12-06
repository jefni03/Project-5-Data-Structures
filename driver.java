import ADTPackage.Queue.*;
import GraphPackage.*;

/**
 * A class of thats tests the traversals of graphs.
 */

public class driver {
    public static void main(String[] args)
    {
        GraphInterface<Character> Graph1 = new Graph<>();

        //Add vertices to the graph
        Graph1.addVertex('A');
        Graph1.addVertex('B');
        Graph1.addVertex('C');
        Graph1.addVertex('D');
        Graph1.addVertex('E');
        Graph1.addVertex('F');
        Graph1.addVertex('G');
        Graph1.addVertex('H');
        Graph1.addVertex('I');

        //Connect the vertices
        Graph1.addEdge('A', 'B');
        Graph1.addEdge('A', 'E');
        Graph1.addEdge('A', 'D');
        Graph1.addEdge('B', 'E');
        Graph1.addEdge('D', 'G');
        Graph1.addEdge('E', 'F');
        Graph1.addEdge('E', 'H');
        Graph1.addEdge('G', 'H');
        Graph1.addEdge('H', 'I');
        Graph1.addEdge('F', 'H');
        Graph1.addEdge('F', 'C');
        Graph1.addEdge('C', 'B');

        QueueInterface<Character> breadthFirstTravel = Graph1.getBreadthFirstTraversal('A');
        System.out.print("Breadth First Travel: ");
        while(!breadthFirstTravel.isEmpty())
        {
            System.out.print(breadthFirstTravel.dequeue() + ", ");
        }

        System.out.println("");

        QueueInterface<Character> depthFirstTravel = Graph1.getDepthFirstTraversal('A');
        System.out.print("Depth First Travel: ");
        while(!depthFirstTravel.isEmpty())
        {
            System.out.print(depthFirstTravel.dequeue() + ", ");
        }
    }
}