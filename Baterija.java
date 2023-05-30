package sistem;

public class Baterija {
	private int tren,kap;
	
	public Baterija(int max) {
		kap=max;
		tren=max;
	}
	public void napuni(int bonus) {
		tren+=(((tren+bonus)>kap)?(kap-tren):bonus);
	}
	public void iscuri() {
		tren=0;
	}
	public boolean puna() {
		return (kap==tren);
	}
}
