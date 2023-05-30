package sistem;

import java.awt.Color;


public abstract class Proizvodjac extends Parcela implements Runnable{
	private int vreme;
	private Baterija bat;
	private Thread thread;
	
	public Proizvodjac(String slovo, Color pozadina, int v, Baterija b) {
		super(slovo, pozadina);
		vreme=v;
		bat=b;
		thread=new Thread(this);
		thread.start();
	}
	
	public int ukVreme() {
		return vreme+(int)Math.random()*300;
	}
	
	public synchronized void finish() {
		if (thread != null) {
			thread.interrupt();
		}

		while (thread != null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				setForeground(Color.WHITE);
				Thread.sleep(ukVreme());
				boolean uspeh;
				synchronized(bat) {
					uspeh=proizvedi(bat);
					if(uspeh) {
						setForeground(Color.RED);
					}
				}
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
		}

		synchronized (this) {
			thread = null;
			notify();
		}
	}
	public abstract boolean proizvedi(Baterija b);
}
