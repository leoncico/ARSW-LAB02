package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	Object lock;
	private boolean running;

	public Galgo(Carril carril, String name, RegistroLlegada reg, Object lock) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		this.lock = lock;
		running = true;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {	
			
			if(running){
				Thread.sleep(100);
				carril.setPasoOn(paso++);
				carril.displayPasos(paso);
			}
			else{
				synchronized (lock) {
					lock.wait();
				}
			}

			if (paso == carril.size()) {						
				carril.finish();

				synchronized(lock){
					int ubicacion=regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion+1);
					System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
					if (ubicacion==1){
						regl.setGanador(this.getName());
					}
				}
			}
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

    public void setRunning(boolean value) {
        running = value;
    }

}
