import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Fenetre extends JFrame {

	private Container pane=this.getContentPane();
	public Fenetre(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(840, 500);
		this.setLocationRelativeTo(null);	  
		    this.setTitle("Gestion du cabinet Vetirinaire");
		    JPanel pan=new JPanel();
		    JPanel pm=new JPanel();
		    pm.setLayout(new FlowLayout(0, 5, 0));
		    
		    JMenuBar mb=new JMenuBar();
			JMenu m=new JMenu("Fichier");
			JMenu ma=new JMenu("Plus");
			JMenuItem mai=new JMenuItem("Aide");
			ma.add(mai);
			JMenuItem mi=new JMenuItem("Fermer");
			JMenuItem mii=new JMenuItem("Quiter le prog");
	        m.add(mi);mb.add(m);m.addSeparator();m.add(mii);
			this.setJMenuBar(mb);mb.add(ma);
			mai.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new Jf("Veuillez selectionez une gestion");
				}
			});
			
		    
		    pan.add(pm);
		    JButton br=new JButton("Gestion des Rendez-vous");
		    JButton bc=new JButton("Gestion des Clients");
		    JButton bm=new JButton("Gestion des Médicaments");
		    JButton bs=new JButton("Gestion de la caisse");
		    br.setPreferredSize(new Dimension(200,80));
		    bc.setPreferredSize(new Dimension(200,80));
		    bm.setPreferredSize(new Dimension(200,80));
		    bs.setPreferredSize(new Dimension(200,80));
		    br.setBackground(Color.LIGHT_GRAY);
		    bc.setBackground(new Color(98,253,113));
		    bm.setBackground(new Color(230,98,113));
		  
		    br.setFont(new Font("comic sans ms", Font.BOLD, 13));
		    br.setForeground(java.awt.Color.blue);
		   // br.setBackground(Color.LIGHT_GRAY);
		    
		    bc.setFont(new Font("comic sans ms", Font.BOLD, 13));
		    bc.setForeground(java.awt.Color.blue);

		    bs.setFont(new Font("comic sans ms", Font.BOLD, 13));
		    bs.setForeground(java.awt.Color.blue);

		    bm.setFont(new Font("comic sans ms", Font.BOLD, 13));
		    bm.setForeground(java.awt.Color.blue);



		    pm.add(bc);
		    pm.add(br);
		    pm.add(bm);
		    pm.add(bs);
		     bc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JClient J=new JClient();
			}
		});
		     bm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new JMedicament();
				}
			});
		     br.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new JRDV();
				}
			});
		     bs.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new JCaisse();				}
			});
		     JPanel pc=new JPanel();
		     pan.add(pc);
	
		     pc.setPreferredSize(new Dimension(820, 360));
		     pc.add(new JLabel(new ImageIcon("img/im1.jpg")));
		     pane.add(pan);
		     
		 	mi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
			mii.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
		
	
     
		     
		
		
		
		this.setVisible(true);
	}
	
}
