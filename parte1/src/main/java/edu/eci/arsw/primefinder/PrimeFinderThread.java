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
		flag = true;
	}

	@Override
	public void run(){
		for (int i=a;i<=b;i++){
			if(flag){
				if (isPrime(i)){
					primes.add(i);
					
					System.out.println(i);
				}
			}
			else{
				synchronized (primes){
					try {
						primes.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
		synchronized(primes) {
			return primes;
		}
		
	}
	
	public void setFlag(boolean value){
		synchronized(primes){
			flag = value;
			if(value == true){
				primes.notify();
			}
		}
	}
}
