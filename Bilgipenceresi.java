package faturaonaylama;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Bilgipenceresi implements ActionListener {
	
	static JTextArea area ;
	static JFrame f;
	
	public static void anons(String onons) {
		    JPanel panel=new JPanel();
			f= new JFrame(); 
			f.setResizable(false);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if (Analiste.oldur==true) {
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setUndecorated(true);
			}
			
	        area=new JTextArea();  
	        area.setEditable(false);
	        area.setBounds(10,30, 200,100);  
	        area.setLineWrap(true);
	        area.setWrapStyleWord(true);
	        JScrollPane sp=new JScrollPane(area);
	        sp.setPreferredSize(new Dimension(180,90));
	        
	        JButton dugme=new JButton("Tamam");
	        dugme.addActionListener( new Bilgipenceresi());
	        dugme.setBounds(10, 210, 110, 40);
	        panel.add(dugme);
	        
	        Container contentPane = f.getContentPane();
	  	  
		    contentPane.add(sp, BorderLayout.PAGE_START);
		    contentPane.add(panel, BorderLayout.PAGE_END);
		    
		  area.setText(onons);
		    f.setLocation(450, 300);
	        f.pack();
	        f.setLayout(null);  
	        f.setVisible(true);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Analiste.oldur==true) {
			System.exit(0);
		}
		f.dispose();
	}
}
