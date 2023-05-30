package sistem;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac{
	
	private int brVodenih;
	
	public Hidroelektrana(Baterija b) {
		super("H", Color.BLUE, 1500, b);
		brVodenih=0;
	}

	@Override
	public boolean proizvedi(Baterija b) {
		if(brVodenih==0) return false;
		b.napuni(brVodenih);
		return true;
	}
	
	public void setBrVodenih(int br) {
		brVodenih=br;
	}


}
