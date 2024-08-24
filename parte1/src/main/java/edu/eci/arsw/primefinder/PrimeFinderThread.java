package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	boolean flag;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public void run(){
		for (int i=a;i<=b;i++){
			if (isPrime(i)){
				primes.add(i);
				//System.out.println(i);
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	public void sleep(){
		// primes.wait();
		// Thread.sleep(1000);
		// taskQueue.add(i);
		// System.out.println("Produced: " + i);
		// taskQueue.notifyAll();
	}
	
}
