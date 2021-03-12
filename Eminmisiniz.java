package faturaonaylama;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
	
public class Eminmisiniz  {
	
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	static JLabel baslik,baslik2,baslik3 ;
	static String cevap="";
		
			public static String sonkarar(int a, int b) {
				
				yavrudugme= new JButton("Tamam");
				yavrudugme2 = new JButton("Ýptal");
				yavru = new JDialog(Analiste.cerceve,"Faturalarý FTP'ye Gönderme Onayý",true); 
			
				yavru.setResizable(false);
					
				baslik = new JLabel("Yerel Klasördeki Onaylýlar Gönderilecek ve Tüm Dosyalar Silinecek." );
				baslik.setBounds(10,3,405,20);
				
				baslik2 = new JLabel("Orjinal Fatura Sayýsý :"+b+"  Onaylanmýþ Fatura Sayýsý :"+a);
				baslik2.setBounds(10,24,405,20);
				
				baslik3 = new JLabel("Tamam'a Bastýktan Sonra Bildirim Gelene Kadar Programý Kapatmayýn." );
				baslik3.setBounds(10,46,405,20);
				
				yavru.setSize(440,150); 
				yavru.setLayout(null);
				yavru.setLocation(800,50);
				yavru.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				yavru.add(yavrudugme);
				yavru.add(yavrudugme2);
				yavru.add(baslik);
				yavru.add(baslik2);
				yavru.add(baslik3);
				
				yavrudugme.setBounds(80,75,80,20);
				yavrudugme.setVisible(true);
				yavrudugme.addActionListener(new ActionListener() {     
		            public void actionPerformed(ActionEvent e) {
		            	cevap="evet";
					    yavru.dispose();
		            }
		        });
				
				yavrudugme2.setBounds(220,75,80,20);
				yavrudugme2.setVisible(true);
				yavrudugme2.addActionListener(new ActionListener() {     
		            public void actionPerformed(ActionEvent f) {
		            	cevap="hayir";
					    yavru.dispose();
		            }
		        });
				yavru.setVisible(true);
				return cevap;
				
			}
}