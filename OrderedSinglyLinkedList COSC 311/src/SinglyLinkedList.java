/**
 *  Modified from Drozdek, Data Structures and Algorithms in Java
 */

/*  Name: Karen Couch
 *  COSC 311  FA19
 *  hw0912
 *  URL:  https://github.com/kcouch3/COSC311/blob/master/OrderedSinglyLinkedList%20COSC%20311/src/SinglyLinkedList.java
 */

public class SinglyLinkedList 
{

   /**
	 * The String used to show that the List is empty.
	 */
	
	private static final String EMPTY_STRING = "Empty String";

public class Node 
   {
      int   data;
      Node  next;
      
      public Node () 
      {
         this (0, null);
      }
      
      public Node (int data) 
      {
         this(data, null);
      }
      
      public Node(int data, Node next) 
      {
         this.data = data;
         this.next = next;
      }
   }
   
   Node  head, tail;
   
   public SinglyLinkedList() 
   {
      head = tail = null;
   }
      
   public void insert(int n)
   {
	   Node newNode = new Node(n);
	   
	   if(isEmpty() || head.data >= newNode.data)
	   {
		   newNode.next = head;
		   head = newNode;
	   }
	   else
	   {
		   Node p = new Node();
		   p = head;
		   while(p.next != null && p.next.data < newNode.data)
		   { 	   
			   p = p.next;
		   }
		   newNode.next = p.next;
		   p.next = newNode;
	   }
   }
   
   public void delete(int n) 
   {
      if (!isEmpty() )
      {
         if (head == tail && n == head.data)
         {
            head = tail = null;
         }
         else if (n == head.data)
         {
            head = head.next;
         }
         else 
         {   
            Node p, q;
            for (p= head, q = head.next; q != null && !(q.data == n); p = p.next, q = q.next) ;
            if (q != null) 
            {
               p.next = q.next;
               if (q == tail )
               {
                  tail = p;
               }
            }
         }
      } 
   }
   
   
   public boolean isEmpty() 
   {
      return head == null;
   }
   
   public String toString() 
   {
      String s = "";
      if (head == null) 
      { 
         return EMPTY_STRING;
      }
      for (Node p = head; p != null; p = p.next) 
      {
         s += p.data + " ";
      }
      return s;
   }
     
   public static void main(String[] args) 
   {

	   SinglyLinkedList list = new SinglyLinkedList();
	       
	   System.out.println("Execution begun");
	   System.out.println("initial list: " + list);

	   // testing
	   list.insert(3);
	   list.insert(5);
	   System.out.println(list);
	   
	   list.insert(2);
	   list.insert(2);
	   list.insert(2);
	   list.insert(7);
	   list.insert(6);
	   System.out.println("list after inserts: " + list);
	   
	   list.delete(2);
	   list.delete(5);
	   list.delete(7);
	   System.out.println("list after deletes: " + list);
	       
	   System.out.println("Execution terminated");
   }
}
