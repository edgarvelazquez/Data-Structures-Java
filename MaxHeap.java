import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<Student>(collection);
      
      for(int i=0; i<students.size();i++) {
    	  students.get(i).setIndex(i);
      }
      
      for(int i = size()/2 - 1; i >= 0; i--)
      {
         maxHeapify(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.get(size()-1).setIndex(0);
      
      students.set(0,students.get(size()-1));
      students.remove(size()-1);
      
      maxHeapify(0);
      return value;
   }
    
   public int size()
   {
      return students.size();
   }
   
   public void insert(Student elt)
   {
      //Please write me.  I should add the given student into the heap,
	  //following the insert algorithm from the videos.
	   students.add(elt);
	   int position= students.size()-1;
	   students.get(position).setIndex(position);
	   moveUp(position);
	   
	   
   }
   
   private void moveUp(int position)
   {
	   //since the student was added at the end, the while loop will use the "bubble" method shown in the video, to move it up, if
	   //it is greater than its parent
	  //position>1
	   while(position>0 && students.get(position).compareTo(students.get(parent(position)))>0) {
		   swap(position, parent(position));
		   position=parent(position);
	   }
   }
   
   public void addGrade(Student elt, double gradePointsPerUnit, int units)
   {
	   
	   //now that the position has been found, we can change the arrayValue and swap it with the new object(student)
	   elt.addGrade(gradePointsPerUnit, units);
	   moveUp(elt.getIndex());
	   maxHeapify(elt.getIndex());
   }
   
   private int parent(int index)
   {
      return (index - 1)/2;
   }
   
   private int left(int index)
   {
      return 2 * index + 1;
   }
   
   private int right(int index)
   {
      return 2 * index + 2;
   }
   
   private void swap(int from, int to)
   {
      Student val = students.get(from);
      
      students.set(from,  students.get(to));
      students.get(from).setIndex(from);
      students.set(to,  val);
      students.get(to).setIndex(to);
      
   }
   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
      //   students.get(index).setIndex(index);
         maxHeapify(largest);
      }  
   }   
   
}