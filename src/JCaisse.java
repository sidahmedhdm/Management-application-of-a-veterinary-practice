import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class JCaisse extends JFrame {

	Container pane = getContentPane();
	public JCaisse(){
		setTitle("Gestion de la Caisse");
		setSize(500	,350 );
		setVisible(true);
		Visite V=new Visite();
		
		JMenuBar mb=new JMenuBar();
		JMenu m=new JMenu("Fichier");
		JMenuItem mi=new JMenuItem("Fermer");
		JMenuItem mii=new JMenuItem("Quiter le prog");
        m.add(mi);mb.add(m);m.addSeparator();m.add(mii);
		this.setJMenuBar(mb);
		mii.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		 mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});

				
				
				
				
		float fm=0;
		float fv=0;
		float total;
		
		ResultSet r=V.res("select sum(prix) from Vente");
		try{
			if(r.next())
				fm=r.getFloat(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		ResultSet rv=V.res("select sum(prix) from Visite");
		try{
			if(rv.next())
				fv=rv.getFloat(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		JPanel Pan=new JPanel(new GridLayout(2, 1));
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JPanel r1=new JPanel();
		JPanel r2=new JPanel();
		JPanel r3=new JPanel();
		JTextField t1=new JTextField();
		JTextField t2=new JTextField();
		JTextField t3=new JTextField();
		t1.setPreferredSize(new Dimension(150, 25));
		t2.setPreferredSize(new Dimension(150, 25));
		t3.setPreferredSize(new Dimension(200, 30));

		TitledBorder b1=new TitledBorder(BorderFactory.createEtchedBorder());
		TitledBorder b2=new TitledBorder(BorderFactory.createEtchedBorder());
		TitledBorder b3=new TitledBorder(BorderFactory.createEtchedBorder());
		b1.setTitle("Recette De Vente");
		b2.setTitle("Recette De Visite");
		b3.setTitle("Recette Total");

		r1.setBorder(b1);
		r2.setBorder(b2);
		r3.setBorder(b3);
		r1.add(t1);
		r2.add(t2);
		r3.add(t3);
		p1.add(r1);
		p1.add(r2);
		p1.add(r3);
		Pan.add(p1);
		pane.add(Pan);
		
		float rve=0,rvi=0;
		ResultSet rA=V.res("select * from caisse");
		try{
			if(rA.next())
			{
				rve=rA.getFloat(1);
				rvi=rA.getFloat(2);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		fm=fm-rve;
		
		fv=fv-rvi;

		total=fm+fv;
		

		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		JPanel p2=new JPanel();
		p2.add(new JLabel("Retirer d'argent"));
		JRadioButton radve=new JRadioButton("Depuis Vente");
		JRadioButton radvi=new JRadioButton("Depuis Visite");
		ButtonGroup bg=new ButtonGroup();
		bg.add(radvi);
		bg.add(radve);
		JPanel rad=new JPanel(new GridBagLayout());
		rad.add(radve);
		GridBagConstraints g=new GridBagConstraints();
		g.gridy=2;
		rad.add(radvi, g);
		JTextField ret=new JTextField();
		ret.setPreferredSize(new Dimension(180, 30));
		p2.add(ret);
		
		p2.add(rad);
		Pan.add(p2);
		JButton kaf=new JButton("Retirer");
		t1.setText(fm+" da" );
		t2.setText(fv+" da");
		t3.setText(total+" da");
		float o=rve;float on=rvi;
		kaf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				float ktib=Float.valueOf(ret.getText());
				if(radve.isSelected())
					V.exe("update caisse set ret_ven="+(ktib+o));
				else V.exe("update caisse set ret_vis="+(ktib+on));
				setVisible(false);
				new JCaisse();
				
			}
		});
		p2.add(new JLabel("              "));
		
		kaf.setPreferredSize(new Dimension(150, 30));
		p2.add(kaf);
		//ResultSet rm=
	}
	
}
