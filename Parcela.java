package sistem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label { 
	
	public Parcela(String slovo, Color pozadina) {
		super(slovo);
		setBackground(pozadina);
		setAlignment(Label.CENTER);
		setFont(new Font("Serif", Font.BOLD, 14));
		setForeground(Color.WHITE);
		
		//-----------------------------------------------------------------ACTION LISTENER
		addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                        getParent().dispatchEvent(e);
            }
        });
		
		//-----------------------------------------------------------------
	}
	public void promenaBoje(Color nova) {
		setBackground(nova);
		revalidate(); //pazi
	}
	
	
}
