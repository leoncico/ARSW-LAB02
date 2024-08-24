package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Integer> primes = new LinkedList<Integer>();
		ArrayList<PrimeFinderThread> threads = new ArrayList<PrimeFinderThread>();

		PrimeFinderThread pft1=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);
		
		threads.add(pft1);
		threads.add(pft2);
		threads.add(pft3);

		pft1.start();
		pft2.start();
		pft3.start();

		while (true) {
			try {
				synchronized(primes) {
					Thread.sleep(5000);
					
					Scanner scanner = new Scanner(System.in);
					System.out.println("Presiona Enter");
					scanner.nextLine();
					System.out.println("Programa iniciado xd");


					for(PrimeFinderThread thread: threads){
						thread.wait();
						
					}

					
					for(PrimeFinderThread thread: threads){
							thread.notify();						
						}
					}
				}
				
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
}
