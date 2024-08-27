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

		boolean finish = false;

		pft1.start();
		pft2.start();
		pft3.start();

		while(!finish){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int result = 0;
			for(PrimeFinderThread thread : threads){
				result += thread.getPrimes().size();
				thread.setFlag(false);
			}
			System.out.println("Primos encontrados: " + result);

			Scanner scanner = new Scanner(System.in);
			System.out.println("Presiona Enter");
			scanner.nextLine();
			finish = true;
			synchronized(primes){
				for(PrimeFinderThread thread : threads){
					if(thread.isAlive()){
						finish = false;
					}
				}
			}

			for(PrimeFinderThread thread : threads){
				thread.setFlag(true);
			}

			if(finish){
				System.out.println("Busqueda finalizada");
			}
			else{
				System.out.println("Continuando busqueda ...");
			}
		}

		int finalResult = 0;
		for(PrimeFinderThread thread : threads){
			finalResult += thread.getPrimes().size();
		}
		System.out.println("Resultado final: " + finalResult);
	}
}
