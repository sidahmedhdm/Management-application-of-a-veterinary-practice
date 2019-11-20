import java.sql.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
public class JClient extends JFrame  {

	Container pane=this.getContentPane();
	public JClient(){
		Client s=new Client();
		pane.setBackground(Color.white);
		this.setTitle("Gestion des Cliens");
		this.setSize(1100, 430);
		pane.setLayout(new GridLayout(1, 2));
		this.setVisible(true);
		 //Les données du tableau
		JMenuBar mb=new JMenuBar();
		JMenu m=new JMenu("Fichier");
		JMenuItem mi=new JMenuItem("Fermer");
		JMenuItem mii=new JMenuItem("Quiter le prog");
        m.add(mi);mb.add(m);m.addSeparator();m.add(mii);
        JMenu ma=new JMenu("Plus");
		JMenuItem mai=new JMenuItem("Aide");
		ma.add(mai);
		mb.add(ma);
		this.setJMenuBar(mb);
		mai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Jf ("Selectionner un client d'apré le tab");
			}
		});
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

	    //database
		JPanel p=new JPanel(new BorderLayout());
		Object data[][]=new Object[1000][4];
	   ResultSet RC=s.res("select * from Client");int i=0;
	    try{
	    	while(RC.next()){
	    		data[i][0]=Integer.toString(RC.getInt(1));
	    		data[i][1]=RC.getString(2);
	    		data[i][2]=RC.getString(3);
	    		data[i][3]=RC.getString(4);
	    		i++;
	    		
	    	}
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	   
	    //Les titres des colonnes
	    String  title[] = {"id Client", "Nom Client", "Prénom Client","Numéro tel."};

	   JTable tableau = new JTable(data,title);
	    //Nous ajoutons notre tableau à notre contentPane dans un scroll
	    //Sinon les titres des colonnes ne s'afficheront pas !
	   JScrollPane scrol=new JScrollPane(tableau);
	   p.add(scrol,BorderLayout.EAST);
	  
	
	JPanel p2=new JPanel();
	pane.add(p2);
	
	
	JPanel p21=new JPanel(new FlowLayout(0,100,20)); 
	JPanel p22=new JPanel();
	
	p2.add(p21);p2.add(p22);
	
	JTextField nom=new JTextField( );
	JTextField prenom=new JTextField( );
	JTextField tel=new JTextField();
   nom.setPreferredSize( new Dimension( 150, 24 ) );
    prenom.setPreferredSize( new Dimension( 150, 24 ) );
    tel.setPreferredSize( new Dimension( 150, 24 ) );
  
    p21.add(new JLabel("Nom :      ") );p21.add(nom);
	p21.add(new JLabel("Prénom :") );p21.add(prenom);
	p21.add(new JLabel("Num Tél :") ); p21.add(tel);

	JPanel p211=new JPanel(new FlowLayout(0,30,10));
	JButton ok=new JButton("Ajoutez");
	p211.add(ok);
	JButton mod=new JButton("Modifier");
	p211.add(mod);
	JButton sup=new JButton("Supprimer");
	p211.add(sup);
	p21.add(p211);
	p21.setBorder(BorderFactory.createTitledBorder("Client"));
	p211.setBorder(BorderFactory.createTitledBorder("Action"));
	sup.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i= tableau.getSelectedRow();
			if(i!=-1){
			int idc=Integer.parseInt(tableau.getValueAt(i, 0).toString());
			
			s.exe("delete from Visite where idc="+idc);
			s.exe("delete from Vente where idc="+idc);
			s.exe("delete from RDV where idc="+idc);
			s.exe("delete from ANIMAL where idc="+idc);
			s.sup(idc, "Client");
			new Jf("Client qui a ID= "+idc+" a été supprimé");

			//tableau.removeRowSelectionInterval(1,3);
			
			tableau.getModel().setValueAt("", i, 0);
			tableau.getModel().setValueAt("", i, 1);
			tableau.getModel().setValueAt("", i, 2);
			tableau.getModel().setValueAt("", i, 3);
		}
		else new Jf("Vous devez séléctioner un Client");

		}
	});
	
		mod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i= tableau.getSelectedRow();
				if(i!=-1){
				int idc=Integer.parseInt(tableau.getModel().getValueAt(i, 0).toString());
				String nm=nom.getText(),prn=prenom.getText(),tl=tel.getText();
				s.exe("update Client set nomc='"+nm+"' where idC="+idc);
				s.exe("update Client set prenomc='"+prn+"' where idC="+idc);
				s.exe("update Client set num_tlfn='"+tl+"' where idC="+idc);
				tableau.setValueAt(nm, i, 1);
				tableau.setValueAt(prn, i, 2);
				tableau.setValueAt(tl, i, 3);
				new Jf("Client Modifié");}
				else new Jf("Vous devez séléctioner un Client");
			 
				
			}
		});	

	
		tableau.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
			int i = tableau.getSelectedRow();
			nom.setText(tableau.getModel().getValueAt(i, 1).toString());
			prenom.setText(tableau.getModel().getValueAt(i, 2).toString());
			tel.setText(tableau.getModel().getValueAt(i, 3).toString());
					
		}
	});
		ok.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String nm=nom.getText(),prn=prenom.getText(),tl=tel.getText();
			Client cl;
			s.actualiser();
		    cl=new Client(nm,prn,tl);
			cl.ajout();
			int x=0;
			while(tableau.getValueAt(x, 0)!=null)
				x++;
			tableau.setValueAt(cl.getIdc(),x, 0);
			tableau.setValueAt(nm,x, 1);
			tableau.setValueAt(prn,x, 2);
			tableau.setValueAt(tl,x, 3);
			

			new Jf("Client ajouté avec succé sous ID:"+cl.getIdc());
			
			
		}
	});
	
	JButton ba=new JButton("Ajouter un ANIMAL");
	ba.setPreferredSize(new Dimension(150,29));
	ba.setBackground(Color.getHSBColor(25, 25, 50));
	p21.add(new JLabel("   "));
	p21.add(ba);
	ba.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = tableau.getSelectedRow();
			if(i!=-1){
			int id=Integer.parseInt(tableau.getModel().getValueAt(i, 0).toString());
			Client c=s.extC(id);
			new JAnimal(c);
			setVisible(false);}
			
		else new Jf("Séléctionez un Client");}
	});
	
	
	ok.setBackground(Color.LIGHT_GRAY);
	mod.setBackground(Color.GRAY);
	sup.setBackground(new Color(253,98,113));
	
	JButton br=new JButton("Rechercher");
	
	
	JTextField tr=new JTextField();
	
	tr.setPreferredSize(new Dimension(150, 24));
	p22.setLayout(new FlowLayout());
	p22.add(new JLabel("ID:"));
	p22.add(tr);
	p22.add(br);
	
	br.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int y =0;
			char ch=tr.getText().charAt(0);
			
			if(ch!='0'&&ch!='1'&&ch!='2'&&ch!='3'&&ch!='4'&&ch!='5'&&ch!='6'&&ch!='7'&&ch!='8'&&ch!='9')
			{
				String nomm=tr.getText();
				y=0;
				while(!nomm.equals(tableau.getValueAt(y,1).toString())){
					y++;
				if(tableau.getValueAt(y,1)==null) break;}
			}
					
			
			else{
			
			
			int idc=Integer.parseInt(tr.getText());
			
			y=0;
			while(Integer.parseInt(tableau.getValueAt(y, 0).toString())!=idc)
				{y++;
			if(tableau.getValueAt(y, 0)==null) break;}
			}
			if(tableau.getValueAt(y, 0)!=null){
			tableau.setRowSelectionInterval(y,y);
			new Jf("Client a été séléctioné");}
			else new Jf("Client n'existe pas");
		}
	});
	
p22.setPreferredSize(new Dimension(450, 50));
p21.setPreferredSize(new Dimension(500,300));
	//p2.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
	//p21.setBorder(BorderFactory.createLineBorder(Color.red,3));
	//p22.setBorder(BorderFactory.createLineBorder(Color.green,3));
	

	pane.add(p);
			
			
			
			
		
   
	}}

	     
	

