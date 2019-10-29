/*
 * Karen Couch
 * Programming Project #1 - pp1008
 * COSC 311 - Fall 2019
 */

public class Customer 
{
	private double serviceTime;
	private int waitTime;
	
	public Customer(double s)
	{
		serviceTime = s;
		waitTime = 0;
	}	
	public double getServiceTime() 
	{
		return serviceTime;
	}
	public void setServiceTime(double serviceTime) 
	{
		this.serviceTime = serviceTime;
	}
	public int getWaitTime() 
	{
		return waitTime;
	}
	public void setWaitTime(int waitTime) 
	{
		this.waitTime = waitTime;
	}
}


