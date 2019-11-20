import java.sql.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JRDV extends JFrame {

	Container pane = this.getContentPane();
	public JRDV (){
		setTitle("Gestion des Rendez-vous");
		setSize(750, 700);
		setVisible(true);
		int i;
		Object title[]={"IDR","Client","Animal","Date","visité"};
		Object[][] tab=new Object[1000][5]; i=0;
		
		RDV R=new RDV() ;
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
				new Jf ("Selectionner un RDV d'apré le tab");
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
		
		ResultSet r=R.res("select * from RDV");
		try{while(r.next())
		{
			tab[i][0]=r.getInt(1);
			int idc = r.getInt(2);
			int ida=r.getInt(3);
			Client c=R.extC(idc);
			
		    tab[i][1]=c.getNom()+" "+c.getPrenom();
		    Animal a=R.extA(ida);
		    
			tab[i][2]=a.getNom()+"/"+a.getRace();
			tab[i][3]=r.getString(4);
			ResultSet rr = R.res("Select * from Visite where idR="+r.getInt(1));
			String boul="Non visité";
			try{
				if (rr.next())
				boul="Visité";
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			tab[i][4]=boul;
		i++;	
		}}catch(SQLException e){
			e.printStackTrace();
		}

		Component s = null;
		if(i==0){
			 s=new JLabel("Vous n'avez aucun Randez-Vous");
			 s.setPreferredSize(new Dimension(200,50));
		}
		else{
			 s=new JTable(tab,title);
			

				 ((JTable) s).setPreferredScrollableViewportSize(new Dimension(500,340));
				
		}
		
	
		
		JTable tt = ((JTable) s);
		JPanel Paneau=new JPanel(new BorderLayout());
		JPanel Pno=new JPanel();
		JPanel Pce=new JPanel();
		JPanel Pso=new JPanel(new FlowLayout (FlowLayout.CENTER,20,50));
		
		JTextField tr=new JTextField();
		tr.setPreferredSize(new Dimension(300, 25));
		JButton br=new JButton("Recherche");

		Pno.add(new JLabel("ID/DATE: "));
		Pno.add(tr);
		Pno.add(br);
		Paneau.add(Pno,BorderLayout.NORTH);
		Paneau.add(Pce,BorderLayout.CENTER);
		Paneau.add(Pso,BorderLayout.SOUTH);
		
		JButton b1= new JButton("Ajouter RDV");
		JButton b2= new JButton("Annuler RDV");
		JButton b3= new JButton("Visiter");
		
		b1.setPreferredSize(new Dimension(130,40));
		b2.setPreferredSize(new Dimension(130,40));
		b3.setPreferredSize(new Dimension(130,40));
		b1.setBackground(new Color (196,218,178));
		b2.setBackground(new Color(253,98,113));
		b3.setBackground(new Color(141,152,141));
		Pso.add(b1);
		Pso.add(b2);
		Pso.add(b3);
		
		br.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int y=0;
				if (tr.getText().length()>5){
				char ch=tr.getText().charAt(4);
				if(ch=='-')
				{
					String da=tr.getText();
					while(!da.equals(tt.getValueAt(y,3).toString()))
						{y++;if(tt.getValueAt(y, 0)==null) break;}
					
				}
				}
				
				else{
					int id=Integer.parseInt(tr.getText());
					
				while(Integer.parseInt(tt.getValueAt(y, 0).toString())!=id)
					{y++;if(tt.getValueAt(y, 0)==null) break;}}
				if(tt.getValueAt(y, 0)!=null){
				tt.setRowSelectionInterval(y,y);
				new Jf("RDV a été séléctioné");}
				else new Jf("RDV n'existe pas");

				
			}
		});

		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame j =new JFrame();
				Container p =j.getContentPane();
				j.setTitle("Ajout d'un RDV");
				j.setSize(550, 300);
				j.setVisible(true);
				JPanel h=new JPanel(new FlowLayout(FlowLayout.CENTER,40,40));
				JTextField f1= new JTextField();
				JTextField f2= new JTextField();
				
				f1.setPreferredSize(new Dimension(120, 30));
				f2.setPreferredSize(new Dimension(120, 30));

				JPanel u1=new JPanel(new FlowLayout(10,20,30));
				JPanel u2=new JPanel(new FlowLayout(10,20,30));
				u1.setPreferredSize(new Dimension(200, 120));
				u2.setPreferredSize(new Dimension(200, 120));
				TitledBorder b1= new TitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),"Id Animal") ;
				TitledBorder b2= new TitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),"Date RDV") ;
				u1.add(f1);
				u2.add(f2);
				u1.setBorder(b1);
				u2.setBorder(b2);
				JButton n = new JButton("Ok");
				
				h.add(u1);
				h.add(u2);
				h.add(n);
				n.setPreferredSize(new Dimension(150, 35));
				p.add(h);
				n.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String ver=f2.getText(); int cnt=0;
						boolean bdat=true,bid=true;
						if(ver.length()!=10)
						bdat=false;
						else if(!R.isNumber(ver.charAt(0)))
							bdat=false;
						else if(!R.isNumber(ver.charAt(1)))
							bdat=false;
						else if(!R.isNumber(ver.charAt(2)))
								bdat=false;		
						else if(!R.isNumber(ver.charAt(3)))
							bdat=false;
						else if(ver.charAt(4)!='-')
							bdat=false;
						else if(!R.isNumber(ver.charAt(5)))
							bdat=false;
						else if(!R.isNumber(ver.charAt(6)))
							bdat=false;
						else if(ver.charAt(7)!='-')
							bdat=false;
						else if(!R.isNumber(ver.charAt(8)))
							bdat=false;
						else if(!R.isNumber(ver.charAt(9)))
							bdat=false;
						
						int a=Integer.parseInt(f1.getText());
						ResultSet rech=R.Rech(a,"Animal");
						try{
							if(!rech.next())
								bid=false;
						}catch(SQLException e){
							e.printStackTrace();
						}
						
						if(bid&&bdat){
						String b= f2.getText();
						Animal An= R.extA(a);
						R.actualiser();
						RDV V =new RDV (An,b);
						V.ajout();
						int x=0;
						
						while(tt.getValueAt(x, 0)!=null)
							x++;
						
						tt.setValueAt(V.getIdR(), x, 0);
						
						int idc = An.getIdc();
						Client Cl=R.extC(idc);
						
						tt.setValueAt(Cl.getNom()+" "+Cl.getPrenom(), x, 1);
						tt.setValueAt(An.getNom()+"/"+An.getRace(), x, 2);
						tt.setValueAt(b, x, 3);
						ResultSet rrr = R.res("Select * from Visite where idR="+V.getIdR());
						String bou="Non visité";
						try{
							if (rrr.next())
							bou="Visité";
							
						}
						catch(SQLException e){
							e.printStackTrace();
						}
						tt.setValueAt(bou, x, 4);
						
						j.setVisible(false);}
						else if(bid)
							new Jf("Date incorrect");
						else new Jf("Id introuvable");
					}
				});
			}

		
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tt.getSelectedRow();
				if(i!=-1){
				int idr=Integer.parseInt(tt.getValueAt(i, 0).toString());
				R.exe("delete from Visite where idR="+idr );
				R.sup(idr, "RDV");
				tt.setValueAt("", i, 0);
				tt.setValueAt("", i, 1);
				tt.setValueAt("", i, 2);
				tt.setValueAt("", i, 3);
				tt.setValueAt("", i, 4);
			    new Jf ("RDV a été annulé");
				}
				else new Jf("Vous devez séléctioner un RDV");

			}
		});
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tt.getSelectedRow();
				if(i!=-1){
				int id=Integer.parseInt(tt.getValueAt(i, 0).toString());
				RDV RD=R.extR(id);
				R.actualiser();
				Visite Vi=new Visite(RD);
				Vi.ajout();
				tt.setValueAt("Visité", i, 4);
				}
				else new Jf("Vous devez séléctioner un RDV");

				}
		});
		
		pane.add(Paneau);
		
		if(i!=0)  {Pce.add(new JScrollPane(s)); }
		else Pce.add(s);
		
				
		
	}


	
	

}
