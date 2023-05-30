package sistem;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;


public class Plac extends Panel{
	private int red,kol;
	private EnergetskiSistem prozor;
	private Parcela[][] polja;
	private LinkedList<Integer> proizvodjaci=new LinkedList<>();
	private Parcela kliknuta=null; 
	
	public Plac(int r, int k) {
		super();
		red=r;
		kol=k;
		polja=new Parcela[r][k];
		setLayout(new GridLayout(red,kol,5,5)); 
		for(int i=0; i<red;i++) {
			for(int j=0; j<kol; j++) {
				Parcela pom;
				double odl=Math.random();
				pom=(odl>0.7?(new VodenaPovrs()):(new TravnataPovrs()));
				polja[i][j]=pom;
				add(pom);
			}
		}
		
		//-----------------------------------------------------------ACTION LISTENER
		
		addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(kliknuta!=null) {
                	kliknuta.setFont(new Font("Serif", Font.BOLD, 14));
                }
            	if(!((e.getSource())instanceof Parcela)) return;
                kliknuta=(Parcela)e.getSource();
                kliknuta.setFont(new Font("Serif", Font.BOLD, 20));
            }
        });

		//-------------------------------------------------------------
	}
	
	public void dodajProizvodjaca(Proizvodjac p) {
		if(kliknuta==null) return;
		kliknuta.setFont(new Font("Serif", Font.BOLD, 14));
		int tempI=0,tempJ=0;
		Container parent=null;
		outerloop:
		for(int i=0; i<red;i++) {
			for(int j=0; j<kol; j++) {
				if (polja[i][j]==kliknuta) {
					tempI=i;
					tempJ=j;
					parent = polja[i][j].getParent();
					parent.remove(polja[i][j]);
					polja[i][j]=p;
					break outerloop;
				}
			}
		}
		for(int i=0; i<red;i++) {
			for(int j=0; j<kol; j++) {
				add(polja[i][j]);
				}
			}
		revalidate();
		int temp=tempI*10+tempJ;
		proizvodjaci.add(temp);
		azuriraj();
		return;
	}

	
	private void azuriraj() {
		int x;
		int y;
		Iterator<Integer> iter=proizvodjaci.iterator();
		while (iter.hasNext()) {
			int br=0;
			int pom=iter.next();
			x=pom/10;
			y=pom%10;
			if(!(polja[x][y]instanceof Hidroelektrana)) {
				continue;
			}
			if((x-1)>=0 && (polja[x-1][y])instanceof VodenaPovrs) br++;
			if((x+1)<red && (polja[x+1][y])instanceof VodenaPovrs) br++;
			if((y+1)<kol && (polja[x][y+1])instanceof VodenaPovrs) br++;
			if((y-1)>=0 && (polja[x][y-1])instanceof VodenaPovrs) br++;
			if((y+1)<kol && (x+1)<red && (polja[x+1][y+1])instanceof VodenaPovrs) br++;
			if((y-1)>=0 && (x-1)>=0 && (polja[x-1][y-1])instanceof VodenaPovrs) br++;
			if((y-1)>=0 && (x+1)<red && (polja[x+1][y-1])instanceof VodenaPovrs) br++;
			if((y+1)<kol && (x-1)>=0 && (polja[x-1][y+1])instanceof VodenaPovrs) br++;
			Hidroelektrana h=(Hidroelektrana)polja[x][y];
			h.setBrVodenih(br);
		}
		
	}
	public void zaustaviSve() {
		int x,y;
		Iterator<Integer> iter=proizvodjaci.iterator();
		while (iter.hasNext()) {
			int pom=iter.next();
			x=pom/10;
			y=pom%10;
			Proizvodjac p=(Proizvodjac)polja[x][y];
			p.finish();
		}
	}
	
	

}
