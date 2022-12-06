import ADTPackage.Queue.*;

/**
 * A class of thats tests the traversals of graphs.
 */
public class driver {
    public static void main(String[] args) {
        //                          ~~~TESTING ADJACENCY MATRIX GRAPH~~~
        GraphMatrix<Character> matrixGraph = new GraphMatrix<Character>(9);
        matrixGraph.setLabel(0,'A');
        matrixGraph.setLabel(1,'B');
        matrixGraph.setLabel(2,'C');
        matrixGraph.setLabel(3,'D');
        matrixGraph.setLabel(4,'E');
        matrixGraph.setLabel(5,'F');
        matrixGraph.setLabel(6,'G');
        matrixGraph.setLabel(7,'H');
        matrixGraph.setLabel(8,'I');

        matrixGraph.addEdge(0,1);
        matrixGraph.addEdge(0,3);
        matrixGraph.addEdge(0,4);

        matrixGraph.addEdge(1,4);

        matrixGraph.addEdge(2,1);

        matrixGraph.addEdge(3,6);

        matrixGraph.addEdge(4,7);
        matrixGraph.addEdge(4,5);

        matrixGraph.addEdge(5,2);
        matrixGraph.addEdge(5,7);

        matrixGraph.addEdge(6,7);

        matrixGraph.addEdge(7,8);

        matrixGraph.addEdge(8,5);

        System.out.println("~~~MATRIX GRAPH TRAVERSAL TEST~~~");
        System.out.println("breadth first traversal:");
        LinkedQueue<Integer> resultMatrixGraphBreadthTraversal = matrixGraph.getBreadthFirstTraversal(0);
        printMatrixGraphTraversals(matrixGraph, resultMatrixGraphBreadthTraversal);
        System.out.println("depth first traversal:");
        LinkedQueue<Integer> resultMatrixGraphDepthTraversal = matrixGraph.getDepthFirstTraversal(0);
        printMatrixGraphTraversals(matrixGraph, resultMatrixGraphDepthTraversal);

        //                              ~~~TESTING ADJACENCY LIST GRAPH~~~
        GraphList<Character> listGraph = new GraphList<Character>();
        listGraph.addVertex('A');
        listGraph.addVertex('B');
        listGraph.addVertex('C');
        listGraph.addVertex('D');
        listGraph.addVertex('E');
        listGraph.addVertex('F');
        listGraph.addVertex('G');
        listGraph.addVertex('H');
        listGraph.addVertex('I');

        listGraph.addEdge('A','B');
        listGraph.addEdge('A','D');
        listGraph.addEdge('A','E');

        listGraph.addEdge('B','E');

        listGraph.addEdge('C','B');

        listGraph.addEdge('D','G');

        listGraph.addEdge('E','H');
        listGraph.addEdge('E','F');
    
        listGraph.addEdge('F','C');
        listGraph.addEdge('F','H');
    
        listGraph.addEdge('G','H');
    
        listGraph.addEdge('H','I');
    
        listGraph.addEdge('I','F');

        System.out.println("~~~LIST GRAPH TRAVERSAL TEST~~~");
        System.out.println("breadth first traversal:");
        QueueInterface<Character> resultListGraphBreadthTraversal = listGraph.getBreadthFirstTraversal('A');
        printQueue(resultListGraphBreadthTraversal);
        System.out.println("depth first traversal");
        QueueInterface<Character> resultListGraphDepthTraversal = listGraph.getDepthFirstTraversal('A');
        printQueue(resultListGraphDepthTraversal);

    }
    public static void printMatrixGraphTraversals(GraphMatrix<Character> matrixGraph, LinkedQueue<Integer> resultTraversal) {

        for (int i = 0; i < matrixGraph.getSize(); i++) {
            char label = matrixGraph.getLabel(resultTraversal.dequeue());
            System.out.print(label + " ");
        }
        System.out.println();
    }

    public static void printQueue(QueueInterface<Character> queue){
        while(!queue.isEmpty()){
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
    }

}
