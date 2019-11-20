import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jf extends JFrame{

	Container pane=this.getContentPane();
	public Jf(String s){
		this.setLocationRelativeTo(null);
		setVisible(true);
		this.setTitle("Message");
		this.setSize(300, 150);
		pane.add(new JLabel(s));
		JButton b=new JButton("OK");
		pane.add(new JLabel("  "));
		pane.add(b);
		b.setPreferredSize(new Dimension(100, 24));
		pane.setLayout(new FlowLayout(0,50,30));
		if(s.equals("Erreur")) setDefaultCloseOperation(EXIT_ON_CLOSE);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				if(s.equals("Erreur")) {
					System.exit(0);
				}
				
			}
		});
		
	}
}
