import java.util.Random;

/* 
 * Karen Couch
 * COSC 311 - Fall 2019
 * Programming Project #2
 * Internal Merge Sort Code
 */

public class InternalMS 
{
	public static void main(String[] args) 
	{
		int size = 250;
		int [] arr = new int[size];
		Random r = new Random();
		long start = 0, end = 0;
		
		//Fill arr with values
		for(int x = 0; x < size; ++x)
		{
			arr[x] = r.nextInt(100);
		}
		  
        System.out.println("Given Array:"); 
        printArray(arr); 
        
		//Value to measure time
		start = System.nanoTime();
		
        sort(arr, 0, arr.length - 1); 
  
        end = System.nanoTime();
        
        System.out.println("\nSorted array: "); 
        printArray(arr); 	
        
        System.out.println("\nTotal time for sorting: " + (end - start) + " nanoseconds");
	}
	
    //Merges two subarrays of arr[]
    public static void merge(int arr[], int l, int m, int r) 
    { 
        //Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        //Create temp arrays
        int temp1[] = new int [n1]; 
        int temp2[] = new int [n2]; 
  
        //Copy data to temp arrays
        for (int i = 0; i < n1; ++i) 
        {
            temp1[i] = arr[l + i]; 
        }
        for (int j = 0; j <n2; ++j)
        {
            temp2[j] = arr[m + 1+ j]; 
        }
  
        /* Merge temp arrays */
  
        //Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        //Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (temp1[i] <= temp2[j]) 
            { 
                arr[k] = temp1[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = temp2[j]; 
                j++; 
            } 
            k++; 
        } 
  
        //Copy remaining elements of temp1[] if any
        while (i < n1) 
        { 
            arr[k] = temp1[i]; 
            i++; 
            k++; 
        } 
  
        //Copy remaining elements of temp2[] if any 
        while (j < n2) 
        { 
            arr[k] = temp2[j]; 
            j++; 
            k++; 
        } 
    } 
  
    //Main function that sorts arr[l..r] using merge() 
    public static void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            //Find the middle point 
            int m = (l + r)/2; 
  
            //Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m + 1, r); 
  
            //Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    //Function to print array
    public static void printArray(int arr[]) 
    { 
        for (int i = 0; i < arr.length; ++i) 
        {
            System.out.print(arr[i] + " "); 
        }
        System.out.println(); 
    } 
}
