package projlab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

//A p�lyav�laszt�s�rt felel�s JPanel
public class MenuView extends JPanel{
	
	private JList list;
	private JPanel wpanel;
	private JButton start;
	private JButton exit;

	//A p�ly�knak a list�j�t tartalmaz� objektum
	private DefaultListModel model;

	public MenuView(final MainWindow mw) {
		this.setLayout(new BorderLayout());
		
		model = new DefaultListModel();
	    list = new JList(model);
	    JScrollPane pane = new JScrollPane(list);
	    list.setPreferredSize(new Dimension(150, 100));
	    
	    model.addElement("P�lya 1");
	    model.addElement("P�lya 2");
		
	    wpanel = new JPanel(new BorderLayout());
	    start = new JButton("J�t�k ind�t�sa");
	    exit = new JButton("Kil�p�s");
	    start.setPreferredSize(new Dimension(180, 40));
	    exit.setPreferredSize(new Dimension(180, 40));
	    wpanel.add(start, BorderLayout.NORTH);
	    wpanel.add(exit, BorderLayout.SOUTH);
	    
	    //Itt t�rt�nik a p�lya kiv�laszt�sa
	    start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	int i = list.getSelectedIndex();
	        	if(i != -1) {	        		
	        		Game.StartGame(i);
	        		mw.StartGame();
	        	}
	        }
	    });
	    
	    exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);
	        }
	    });
		
		add(pane, BorderLayout.EAST);
		add(wpanel, BorderLayout.WEST);
	}
}
