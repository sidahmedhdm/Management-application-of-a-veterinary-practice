import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JMedicament extends JFrame{
	private int idm;
	private int i;
	private Container pane = this.getContentPane();
	
	private Component act(){
		Object title[]={"ID","Nom","Date Expération","Prix","Contité"};
		Object[][] tab=new Object[100][5]; i=0;
		
		
		JMenuBar mb=new JMenuBar();
		JMenu mm=new JMenu("Fichier");
		JMenuItem mi=new JMenuItem("Fermer");
		JMenuItem mii=new JMenuItem("Quiter le prog");
        mm.add(mi);mb.add(mm);mm.addSeparator();mm.add(mii);
		this.setJMenuBar(mb);
		JMenu ma=new JMenu("Plus");
		JMenuItem mai=new JMenuItem("Aide");
		ma.add(mai);
		mb.add(ma);
		this.setJMenuBar(mb);
		mai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Jf ("Selectionner un Medicament");
			}});
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
		
		Medicament m=new Medicament() ;
		//m=m.extM(idm);
		ResultSet r=m.res("select * from Medicament");
		try{while(r.next())
		{
			tab[i][0]=r.getInt(1);
			tab[i][1]=r.getString(2);
			tab[i][2]=r.getString(3);
			tab[i][3]=r.getFloat(4);
			tab[i][4]=r.getInt(5);
			
		i++;	
		}}catch(SQLException e){
			e.printStackTrace();
		}

		Component s = null;
		if(i==0){
			 s=new JLabel("Aucun Médicament dans le stock");
			 s.setPreferredSize(new Dimension(200,50));
		}
		else{
			 s=new JTable(tab,title);
			

				 ((JTable) s).setPreferredScrollableViewportSize(new Dimension(500,340));
				
		}
		return s;
	}
	
	public JMedicament(){	
		this.setTitle("Gestion des Médicaments");
		this.setSize(740,550);
		//////////////////////////////////////////////////////////////
		JPanel Paneau=new JPanel(new BorderLayout());
		
		JTextField tre=new JTextField();
		JButton bre=new JButton("Rechercher");
		tre.setPreferredSize(new Dimension(170, 25));
		JPanel recherch=new JPanel();
		recherch.add(new JLabel("ID/NOM: "));
		recherch.add(tre);recherch.add(bre);
		
		//
		JTable tab=null;
		JPanel pta=new JPanel(new GridBagLayout());
		Component t=act();
		if(i!=0)  {tab=(JTable)t;pta.add(new JScrollPane(t)); }
		else pta.add(t);
		//************************************************************************************
		JButton plus=new JButton("Plus");		JButton moin=new JButton("moins");
		JTextField cnt=new JTextField("0");
		cnt.setEditable(false);
		cnt.setPreferredSize(new Dimension(30, 20));
		plus.setPreferredSize(new Dimension(50,30));
		JToolBar tol=new JToolBar();
		tol.add(plus);
		tol.add(moin);
		tol.add(cnt);
		JPanel outi=new JPanel(new FlowLayout(0,15,10));
		
		
		pane.add(Paneau);
		Paneau.setBorder(LineBorder.createBlackLineBorder());
		
		this.setVisible(true);
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = Integer.parseInt(cnt.getText());
				if(i<100) cnt.setText(""+(i+1));
			}
		});
		moin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = Integer.parseInt(cnt.getText());
				if(0<i) cnt.setText(""+(i-1));
			}
		});
	Paneau.add(recherch,BorderLayout.NORTH);
	Paneau.add(pta,BorderLayout.WEST);
	Paneau.add(outi,BorderLayout.EAST);
	
	pta.setBorder(BorderFactory.createLoweredBevelBorder());
	pta.setBackground(new Color(200,200,255));
	

	Medicament M=new Medicament();
	
	JPanel exm=new JPanel(new GridBagLayout());
	GridBagConstraints gcc=new GridBagConstraints();
	gcc.gridx=0;
	gcc.gridy=1;
	
	TitledBorder tbor=new TitledBorder(BorderFactory.createLineBorder(Color.gray),"Exemplaire");
	exm.setBorder(tbor);
	exm.add(tol);
	JButton ex=new JButton("Ajouter Exmp");
	ex.setBackground(Color.gray);
	
	JTable taa=tab;
	ex.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			int a=taa.getSelectedRow();
			
		int cont=Integer.parseInt(cnt.getText());
		int j=Integer.parseInt(taa.getValueAt(a, 4).toString());
		cont=cont+j;
		taa.setValueAt(cont, a, 4);
		int idm = Integer.parseInt(taa.getValueAt(a, 0).toString());
		M.exe("update Medicament set contité="+cont+" where idM="+idm);
		new Jf("Des Examplaire a été Ajouté");
		
		}
	});
	JPanel d1= new JPanel();
	JPanel d2= new JPanel();
	JPanel d3= new JPanel();
	
	TitledBorder a1= new TitledBorder(BorderFactory.createLineBorder(new Color(98,253,113), 7),"Nom");
	TitledBorder a2= new TitledBorder(BorderFactory.createLineBorder(new Color(98,253,113), 7),"prix");
	TitledBorder a3= new TitledBorder(BorderFactory.createLineBorder(new Color(98,253,113), 7),"Date_exp");
	
	exm.add(ex,gcc);
	outi.add(exm);
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	
	t1.setPreferredSize(new Dimension(100, 30));
	t2.setPreferredSize(new Dimension(100, 30));
	t3.setPreferredSize(new Dimension(100, 30));
	d1.setBorder(a1);
	d1.add(t1);
	outi.add(d1);
	d2.setBorder(a2);
	d2.add(t2);
	outi.add(d2);
	d3.setBorder(a3);
	d3.add(t3);
	outi.add(d3);
	outi.add(new JLabel("          "));
	JButton v=new JButton("Nouveau médicament");
	v.setBackground(Color.green);
	v.setPreferredSize(new Dimension(160,35));
	outi.add(v);
	
	GridBagConstraints gtc =new GridBagConstraints();
	gtc.gridy=1;
	JButton v2=new JButton("Supprimer Médicament");
	JButton v3=new JButton("Vendre un Médicament");
	JPanel pv=new JPanel();
	pv.add(v2);
	pv.add(v3);
	
	pta.add(pv,gtc);

	v2.setPreferredSize(new Dimension(200, 36));
	v2.setBackground(new Color(253,98,113));
	v3.setPreferredSize(new Dimension(200, 36));
	v3.setBackground(new Color(98,163,200));
	v.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String nom,date;
			float prix;
			prix=Float.valueOf(t2.getText());
			nom=t1.getText();
			date=t3.getText();
			M.actualiser();
			Medicament m=new Medicament(nom, date, prix, 0);
			m.ajout();
			setVisible(false);
			new JMedicament();
			
			
			
			
			
		}
	});
	v2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			int id,i=taa.getSelectedRow();
			id=Integer.parseInt(taa.getValueAt(i, 0).toString());
			M.sup(id,"Medicament");
			setVisible(false);
			new JMedicament();
		}
	});
	
	bre.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			char ch=tre.getText().charAt(0);
			int y;
			if(ch!='0'&&ch!='1'&&ch!='2'&&ch!='3'&&ch!='4'&&ch!='5'&&ch!='6'&&ch!='7'&&ch!='8'&&ch!='9')
			{
				String nomm=tre.getText();
				y=0;
				while(!nomm.equals(taa.getValueAt(y,1).toString()))
					{y++;if(taa.getValueAt(y, 0)==null) break;}
				
			}
					
			
			else{
			int idm=Integer.parseInt(tre.getText());
			y=0;
			while(Integer.parseInt(taa.getValueAt(y,0).toString())!=idm)
				{y++;if(taa.getValueAt(y, 0)==null) break;}}
			
			if(taa.getValueAt(y, 0)==null) 
				new Jf("Médicament n'existe pas");
			else{taa.setRowSelectionInterval(y,y);
			new Jf("Médicament a été séléctioné");
			}
			
		}
	});
	
v3.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int i = taa.getSelectedRow();
		if(i!=-1){
		Medicament Medi=M.extM(Integer.parseInt(taa.getValueAt(i, 0).toString()));
		JFrame Ven=new JFrame();
		Container cmed=Ven.getContentPane();
		Ven.setTitle("Vendre "+Medi.getNomm());
		Ven.setSize(350,250);
		Ven.setVisible(true);
		JTextField vco=new JTextField();
		vco.setPreferredSize(new Dimension(150, 25));
		JTextField vcl=new JTextField();
		vcl.setPreferredSize(new Dimension(150, 25));
		
		JButton okok=new JButton("OK");
		JPanel vpan=new JPanel(new FlowLayout(FlowLayout.CENTER,15,30));
		cmed.add(vpan);
		
		
		vpan.add(new JLabel("ID Client :"));
		vpan.add(vcl);
		vpan.add(new JLabel("                  "));
		vpan.add(new JLabel("Contité :"));
		vpan.add(vco);
		vpan.add(new JLabel("                  "));
		
		vpan.add(okok);
		okok.setPreferredSize(new Dimension(100, 35));
		okok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int idc = Integer.parseInt(vcl.getText());
				int con = Integer.parseInt(vco.getText());
				Client Clien=M.extC(idc);
				Medi.vente(Clien, con);
				Ven.setVisible(false);
				setVisible(false);
				new JMedicament();
				
			}
		});
		}
		else new Jf("Séléctionez un Médicament.");
		
		
	}
});

	
	Paneau.add(outi);
	
	
	}
	
}
