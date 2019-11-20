import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JAnimal extends JFrame {
	private Container pane=this.getContentPane();
	private int idc;
	int i;
	
	private Component act(Client c){
		Object title[]={"ID","Nom","Type","Sexe"};
		Object[][] tab=new Object[10][4]; i=0;
		
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
		
		ResultSet r=c.res("select * from Animal where idC = "+c.getIdc());
		try{while(r.next())
		{
			tab[i][0]=r.getInt(1);
			tab[i][1]=r.getString(3);
			tab[i][2]=r.getString(4);
			tab[i][3]=r.getString(5);
		i++;	
		}}catch(SQLException e){
			e.printStackTrace();
		}
		
		Component s = null;
		if(i==0){
			 s=new JLabel("Client n'a aucun Animal");
			 s.setPreferredSize(new Dimension(200,50));
		}
		else{
			 s=new JTable(tab,title);
			

				 ((JTable) s).setPreferredScrollableViewportSize(new Dimension(260,80));
				
		}
		System.out.println("i "+i);
		return s;
	}
	public JAnimal(Client c){
		this.setTitle("Animal de Client: "+c.getNom()+" "+c.getPrenom());
		this.setSize(700,420);
		JPanel pl=new JPanel(new FlowLayout(FlowLayout.CENTER,5,50)),pr=new JPanel(),pl1=new JPanel(new GridBagLayout()),pl2=new JPanel();
		pane.setLayout(new GridLayout(1,2));
		pane.add(pl);pane.add(pr);
		pl2.setPreferredSize(new Dimension(250,130));
		// panel left 1
		JLabel nom=new JLabel("Nom: "+c.getNom()+" .");
		JLabel prenom=new JLabel("Prénom: "+c.getPrenom()+" .");
		JLabel tel=new JLabel("N°Téléphone: "+c.getNum_tel()+" .");
		nom.setFont(new Font("Comic sans ms", Font.BOLD, 15));
		prenom.setFont(new Font("Comic sans ms", Font.BOLD, 15));
		tel.setFont(new Font("Comic sans ms", Font.BOLD, 15));
				
		
		GridBagConstraints gc=new GridBagConstraints();
		gc.fill=GridBagConstraints.HORIZONTAL;
		gc.gridwidth=GridBagConstraints.RELATIVE;
		gc.gridx=2;
		gc.gridy=2;
		pl1.add(nom,gc);
		gc.gridx=2;
		gc.gridy=3;
		pl1.add(prenom,gc);
		gc.gridx=2;
		gc.gridy=4;
		pl1.add(tel,gc);
	
		TitledBorder b = BorderFactory.createTitledBorder("");
		b.setTitle("Information de Client...");
		b.setTitleFont(new Font("Arial",Font.BOLD,17));
		b.setBorder(BorderFactory.createBevelBorder(WIDTH));
		b.setTitleColor(new Color(50,0,100));
		pl1.setPreferredSize(new Dimension(250,50));
		
				TitledBorder bb=new TitledBorder("");
		bb.setBorder(b.getBorder());
		bb.setTitle("Listes Des Animaux..");
		pl2.setBorder(bb);
		bb.setTitleFont(b.getTitleFont());
		//pan right
		JPanel pan=new JPanel(new GridLayout(2,1));
		pan.setPreferredSize(new Dimension(300,300));
		pan.add(pl1);pan.add(pl2);
		pr.add(pan);
		pan.setBorder(b);
		pan.setBackground(new Color(240,220,255));
		
		// pan left
		JPanel ps=new JPanel();

		JRadioButton mal=new JRadioButton("Masculin");
		JRadioButton femal=new JRadioButton("Féminin");
				
		ButtonGroup G=new ButtonGroup();
		G.add(mal);G.add(femal);
		ps.add(mal);ps.add(femal);
		TitledBorder tb=new TitledBorder("Sexe");
		tb.setBorder(BorderFactory.createLineBorder(new Color(150,150,100)));
		ps.setBorder(tb);

		JPanel pn=new JPanel();
		TitledBorder bn=new TitledBorder("Nom");
		bn.setBorder(b.getBorder());
		pn.add(new JLabel("Nom:"));
		JTextField tn=new JTextField();
		tn.setPreferredSize(new Dimension(80,20));
		pn.add(tn);
		pn.setBorder(bn);
		
		JPanel pp=new JPanel();
		pp.add(new JLabel("Type:"));
		JTextField tp=new JTextField();
		tp.setPreferredSize(new Dimension(80,20));
		pp.add(tp);
		TitledBorder bp=new TitledBorder("Type");
		bp.setBorder(b.getBorder());
		pp.setBorder(bp);
		
		pl.add(pn);pl.add(pp);pl.add(new JLabel("                    "));pl.add(ps);
		
		Component t=act(c);
		if(i==0) 
		pl2.add(t);
		else pl2.add(new JScrollPane(t));
		//B
		JButton add=new JButton("Ajouter");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				String name=tn.getText(); String type=tp.getText();
				String sexe="";
				if (mal.isSelected()) sexe = "male";
				else if(femal.isSelected()) sexe = "femele";
				c.actualiser();
				Animal A=new Animal(name, type, sexe, c);
				A.ajout();
				new JAnimal(c);
	}});
		
		JButton sup=new JButton("Supprimer");
		sup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (i!=0){
					
				
				JTable Tab=(JTable)t;
				int j=Tab.getSelectedRow();
				int idA=Integer.parseInt((Tab.getModel().getValueAt(j, 0)).toString());
				c.exe("delete from visite where idA="+idA);
				c.exe("delete from RDV where idA="+idA);
				c.sup(idA, "Animal");
				setVisible(false);
				new JAnimal(c);
				}
			}
		});
		pl.add(new JLabel("                    "));
					pl.add(add);
					pl.add(sup);
		setVisible(true);
	}
}
