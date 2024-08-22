package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		boolean flag = false;
		List<Integer> primes = new LinkedList<Integer>();

		PrimeFinderThread pft1=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);
		

		Scanner scanner = new Scanner(System.in);
		System.out.println("Presiona Enter");
		scanner.nextLine();
		flag = true;
		System.out.println("Programa iniciado xd");

		while (flag) {
			pft1.start();
			pft2.start();
			pft3.start();
			
			try{
				primes.wait(5);
				flag = false;
			}
			catch(Exception e){
				System.out.println("esperando xd");
			}
		}
		
	}
}
