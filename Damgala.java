package faturaonaylama;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
	
public class Damgala implements ActionListener {
	
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JFrame yavru ;
	static JLabel baslik2,baslik ;
	static JTextField alan;
	static String pozno="";
	static PdfDocument pdfDoc;
	
	private enum evethayir {
		basevet,bashayir;
	}
	
			public static void damgala() {
				yavrudugme= new JButton("ONAYLA");
				yavrudugme2 = new JButton("Ýptal");
				yavru = new JFrame("Onaylama Penceresi "); 
				yavru.setResizable(false);
				yavru.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				alan=new JTextField() ;
				alan.setBounds(30,37,220,20);
				alan.addKeyListener(new KeyAdapter() {    // Max 25 karakter için key listener
				    public void keyTyped(KeyEvent e) { 
				        if (alan.getText().length() >= 25 ) 
				            e.consume(); 
				    }  
				});
			
				baslik2 = new JLabel("    Pozisyon No. Yazýn " );
				baslik2.setBounds(70,8,270,20);
				
				baslik = new JLabel("" );
				baslik.setBounds(15,90,270,20);
				
				yavru.setSize(300,150); 
				yavru.setLayout(null);
				yavru.setLocation(1040,20);
				yavru.add(yavrudugme);
				yavru.add(yavrudugme2);
				yavru.add(baslik2);
				yavru.add(alan);
				yavru.add(baslik);
				
				yavrudugme.setBounds(20,70,100,20);
				yavrudugme.setVisible(true);
				yavrudugme.addActionListener(new Damgala());
				yavrudugme.setActionCommand(evethayir.basevet.name());
				
				yavrudugme2.setBounds(180,70,80,20);
				yavrudugme2.setVisible(true);
				yavrudugme2.addActionListener(new Damgala());
				yavrudugme2.setActionCommand(evethayir.bashayir.name());
				 
				yavru.setLayout(null);  
				yavru.setVisible(true); 
				}
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getActionCommand()==evethayir.basevet.name())	{
					
					pozno=alan.getText().toUpperCase();
					if (pozno.length()<5) {
						alan.setText("");
						return;
					}
					
					try {
						pdfDoc = new PdfDocument(new PdfReader(Analiste.yerelklasoryolu+"/"+Analiste.secilendosya), 
								new PdfWriter(Analiste.yerelklasoryolu+"/+"+Analiste.secilendosya));
					} catch (FileNotFoundException e1) {
						Bilgipenceresi.anons("Dosya bulunamadý");
						e1.printStackTrace();
					} catch (IOException e1) {
						Bilgipenceresi.anons("Dosya Veri Hatasý !");
						e1.printStackTrace();
					}
					
					PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
					canvas.beginText();
					try {
						canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD),10);
					} catch (IOException e1) {
						Bilgipenceresi.anons("Dosya Veri Hatasý !");
						e1.printStackTrace();
					}
					canvas.setFillColorRgb(254,0,0);
					canvas.moveText(20,830);
					canvas.showText(pozno+" **OPER. OK**");
					canvas.endText();
					
				pdfDoc. close();	
				
				Analiste.secilendosya="+"+Analiste.secilendosya	;
				Analiste.secilenyol=Analiste.yerelklasoryolu+"/";
				Goruntule.degistirdim=true;
				yavru.dispose();
				Analiste.anametod();
					
				}
		    
				if (e.getActionCommand()==evethayir.bashayir.name())	{
					
					yavru.dispose();
					Analiste.anametod();
					
				}
	}
}