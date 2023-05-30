package sistem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class EnergetskiSistem extends Frame {
	protected Plac plac; 
	private Baterija bat;
	private Panel gPanel= new Panel();
	
public EnergetskiSistem(int red, int kol, int kap) {
	setBounds(700, 200, 500, 500);
	setResizable(false);
	plac=new Plac(red,kol);
	bat=new Baterija(kap);
	setTitle("2");
	
	populateWindow();
	setVisible(true);
}

private void populateWindow() {
	Button b=new Button("Dodaj");
	gPanel.add(b);
	add(gPanel, BorderLayout.NORTH);
	
	add(plac, BorderLayout.CENTER);
	//---------------------------------------------------ACTION LISTENER
	b.addActionListener((ae) -> {
		plac.dodajProizvodjaca(new Hidroelektrana(bat));
		});
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			plac.zaustaviSve();
			dispose();
		}
	});
	//----------------------------------------------------
}
public static void main(String[] args) {
	new EnergetskiSistem(5,5,5);
}

}
