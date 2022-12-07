package GraphPackage;
import ADTPackage.Queue.*; // Classes that implement various ADTs
/** 
   An interface of methods that process an existing graph. 

*/
public interface GraphAlgorithmsInterface<T>
{
   /** Performs a breadth-first traversal of this graph.
       @param origin  An object that labels the origin vertex of the traversal.
       @return  A queue of labels of the vertices in the traversal, with
                the label of the origin vertex at the queue's front. */
   public QueueInterface<T> getBreadthFirstTraversal(T origin);

   /** Performs a depth-first traversal of this graph.
       @param origin  An object that labels the origin vertex of the traversal.
       @return  A queue of labels of the vertices in the traversal, with
                the label of the origin vertex at the queue's front. */
   public QueueInterface<T> getDepthFirstTraversal(T origin);
}
<<<<<<< HEAD

=======
>>>>>>> 54a3f6eafee3d7337125f64497179ce4556f8278

