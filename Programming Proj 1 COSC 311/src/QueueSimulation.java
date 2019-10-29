import java.util.*;

/*
 * Karen Couch
 * Programming Project #1 - pp1008
 * COSC 311 - Fall 2019
 */

public class QueueSimulation 
{
	public static void main(String[] args) 
	{
		int totalTime = 20, inService = 0, completed = 0,
				waitTime = 0;
		
		//For number generator
		Random r = new Random(97);
		
		//Stores wait times for calculating min, max, and average
		int [] times = new int[totalTime];

		//Customers Queue
		Queue<Customer> customers = new LinkedList<>();

		//Servers
		Customer server1 = new Customer(0), server2 = new Customer(0), 
				server3 = new Customer(0), server4 = new Customer(0);
		boolean s1 = false, s2 = false, s3 = false, s4 = false;

		//Each tick is 1 minute
		for(int x = 0; x < totalTime; x++)
		{
			//Add first customers
			if(x == 0)
			{
				customers = addCustomers(customers, r);
			}
				//Check if server is finished
				//Release customer if complete or decrement service time
				if(x != 0)
				{		
					if(server1.getServiceTime() == 0 && s1 == true) 
					{	
						s1 = false;
						inService--;
						completed++;
					}
					else if(s1 == true) 
					{
						server1.setServiceTime(server1.getServiceTime() - 1.0);
						if(server1.getServiceTime() == 0) { s1 = false; inService--; completed++;}
					}
					if(server2.getServiceTime() == 0 && s2 == true) 
					{	
						s2 = false;
						inService--;
						completed++;
					} 
					else if(s2 == true) 
					{
						server2.setServiceTime(server2.getServiceTime() - 1.0);
						if(server2.getServiceTime() == 0) { s2 = false; inService--; completed++;}
					}
					if(server3.getServiceTime() == 0 && s3 == true) 
					{	
						s3 = false;
						inService--;
						completed++;
					} 
					else if(s3 == true) 
					{
						server3.setServiceTime(server3.getServiceTime() - 1);
						if(server3.getServiceTime() == 0) { s3 = false; inService--; completed++;}
					}
					if(server4.getServiceTime() == 0 && s4 == true) 
					{	
						s4 = false;
						inService--;
						completed++;
					} 
					else if(s4 == true) 
					{
						server4.setServiceTime(server4.getServiceTime() - 1);
						if(server4.getServiceTime() == 0) { s4 = false; inService--; completed++;}
					}
				}
				
			//Add customers to the queue
		    customers = addCustomers(customers, r);
		    
			//Idle servers remove customers from queue if they do not have
			//a customer or their current customer's wait time is now 0
			if(s1 == false && !customers.isEmpty()) 
			{
				s1 = true;
				inService++;
				server1 = customers.poll();
			}
			if(s2 == false && !customers.isEmpty()) 
			{
				s2 = true;
				inService++;
				server2 = customers.poll();
			}
			if(s3 == false && !customers.isEmpty()) 
			{
				s3 = true;
				inService++;
				server3 = customers.poll();
			}
			if(s4 == false && !customers.isEmpty()) 
			{
				s4 = true;
				inService++;
				server4 = customers.poll();
			}

			//Increment wait time
			customers = incrementWaitTime(customers);

			//Calculate wait time
			waitTime = calculateWaitTime(customers, server1, server2, server3, server4);
			times[x] = waitTime;

			//Print out info
			printInfo(x, inService, completed, waitTime, customers, times);
		}
	} 

	
	public static int getPoissonRandom(double mean, Random r)
	{
		double l = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		
		do
		{
			p = p * r.nextDouble();
			k++;
		} while(p > l);
		
		return k - 1;
	}
	
	public static Queue<Customer> addCustomers(Queue<Customer> c, Random r)
	{
		double randNum, temp;
		randNum = getPoissonRandom(2, r);
				
		//randNum for how many new customers
		for(int x = 0; x < randNum; x++)
		{
			//temp for new customers max service time
			temp = r.nextInt(3) + 1;
			Customer ct = new Customer(temp);
			c.add(ct);
		}
		
		return c;
	}
	
	public static Queue<Customer> incrementWaitTime(Queue<Customer> c)
	{
		Customer temp = null;
		int size = c.size();
		for(int x = 0; x < size; x++)
		{
			temp = c.poll();
			temp.setWaitTime(temp.getWaitTime() + 1);
			c.add(temp);
		}
		
		return c;
	}
	
	public static int calculateWaitTime(Queue<Customer> c, Customer s1, Customer s2, Customer s3, Customer s4)
	{
		int wt = 0;
		for(Customer item : c)
		{
			wt += item.getWaitTime();
		}
		
		return wt;
	}
	
	public static void printInfo(int tick, int is, int cp, int wt, Queue<Customer> c, int [] time)
	{
		//Print out info after each tick
		System.out.println("Tick #: " + tick);
		System.out.println("	# Customers in service: " + is);
		System.out.println("	# Customers with completed service: " + cp);
		System.out.println("	# Customers in queue: " + c.size());
		System.out.println("	Total wait time " + wt + " minutes");
		System.out.println("Wait time: minimum: " + Arrays.stream(time).min().getAsInt() + " average: " +
				Arrays.stream(time).average().getAsDouble() + " maximum: " + Arrays.stream(time).max().getAsInt());
	}
}
