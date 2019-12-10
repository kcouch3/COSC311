import java.io.*;
import java.util.*;

/* 
 * Karen Couch
 * COSC 311 - Fall 2019
 * Programming Project #2
 * External Merge Sort Code
 */

public class ExternalMS 
{
	public static void main(String[] args)
	{
		//Values for calculating time
		long start = 0, end = 0;
		
		start = System.nanoTime();
		
		mergeSort("data.txt");
		
		end = System.nanoTime();
		
		System.out.println("\n\nTotal time for sorting: " + (end - start) + " nanoseconds");
	} 

	public static void mergeSort(String fname)
	{
		try
		{ 
			BufferedReader br = new BufferedReader(new FileReader(fname));
			FileWriter fw = new FileWriter(fname);
			List<Integer> values = new ArrayList<Integer>();
			Random rand = new Random();
			int size = 250;
			//Add random values into original file
			for(int x = 0; x < size; ++x)
			{
				fw.write(rand.nextInt(100) + "\n");
			}
			
			fw.close();

			String num = null;
			//Add values from original file to arraylist
			while((num = br.readLine()) != null)
			{
				values.add(Integer.parseInt(num));
			}

			br.close();
			
			//Print values from original file
			System.out.println("The original values before sorting:");
			for (Integer i : values)
			{
				System.out.print(i + " ");
			}
			
			Integer[] myArray = values.toArray(new Integer[values.size()]);

			//Call function to sort recursively
			sort(myArray, 0, myArray.length - 1);

			FileWriter fw2 = new FileWriter("sorted.txt");
			BufferedReader br2 = new BufferedReader(new FileReader("sorted.txt"));

			//Add results from sort into new file
			for(int x = size - 1; x >= 0; --x)
			{
				fw2.write(myArray[x] + "\n");
			}
			
			fw2.close();
			
			//Print sorted values from new file
			System.out.println("\n\nThe values after sorting:");
			while((num = br2.readLine()) != null)
			{
				System.out.print(num + " ");
			}

			br2.close();
		}
		catch (Exception e)
		{
			System.out.print(e);
		}

	}
	
	static void merge(Integer[] myArray, int l, int m, int r)
	{
		//Find sizes of subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		//Create temp arrays
		int L[] = new int [n1];
		int R[] = new int [n2];

		//Copy data to temp arrays
		for (int i=0; i<n1; ++i)
		{
			L[i] = myArray[l + i];
		}
		for (int j=0; j<n2; ++j)
		{
			R[j] = myArray[m + 1+ j];
		}
		
		//Merge the temp arrays
		int i = 0, j = 0, k = l;

		while (i < n1 && j < n2)
		{
			if (L[i] > R[j])
			{
				myArray[k] = L[i];
				i++;
			}
			else
			{
				myArray[k] = R[j];
				j++;
			}

			k++;
		}

		//Copy remaining elements of L[] if any
		while (i < n1)
		{
			myArray[k] = L[i];
			i++;
			k++;
		}

		//Copy remaining elements of R[] if any 
		while (j < n2)
		{
			myArray[k] = R[j];
			j++;
			k++;
		}
	}

	static void sort(Integer[] myArray, int l, int r)
	{
		if (l < r)
		{
			//Find middle value
			int m = (l+r)/2;

			//Sort the first and second halves
			sort(myArray, l, m);//recursive call
			sort(myArray , m+1, r);//recursive call

			//Merge both sorted halves
			merge(myArray, l, m, r);
		}
	}
}
