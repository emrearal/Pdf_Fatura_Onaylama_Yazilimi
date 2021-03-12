package faturaonaylama;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Analiste implements ActionListener {

	static JFrame cerceve;
	static JPanel panel1,panel2;
	static JButton dugme1,dugme2,dugme3;
	static JTable tablo;
	static String[][] pdflistesi,pdflistesibenzersiz;
	static String secilendosya,secilenyol;
	static String yerelklasoryolu="C:/Users/emrea/Desktop/yenifaturalar";
	static String ftpyolu="/sanal/faturaonayklasoru/1-operasyononayladi/";
	static String host = "88.188.198.444";
    static String user = "user";
    static String pass = "pass";
    
	static String[] artilisonrasi;
	static JScrollPane sp;
	static boolean kirmizikart=false,ftphata=false;;
	static int c=0,b=0,p=0;
	private static final int BUFFER_SIZE = 4096;
	
	private enum actions {
	    incele,gonder,kapat ;
	  }
	
	public static void anametod() {
		
		c=0; b=0; p=0;secilendosya=""; secilenyol="";
		
		if (Goruntule.yavruacik==true) {
        	Damgala.yavru.dispose();
        	Goruntule.yavruacik=false;
        	
        }
		
	   cerceve = new JFrame("ASGARD FATURA ONAY");
	   cerceve.setLocation(1040,5);
	   cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   panel1 = new JPanel();
	
	   dugme1 =new JButton("FATURA �NCELE");
	   dugme1.addActionListener(new Analiste());
	   dugme1.setActionCommand(actions.incele.name());
	   
	   dugme2 =new JButton("FTP'YE G�NDER");
	   dugme2.addActionListener(new Analiste());
	   dugme2.setActionCommand(actions.gonder.name());
	   
	   dugme3 =new JButton("KAPAT");
	   dugme3.addActionListener(new Analiste());
	   dugme3.setActionCommand(actions.kapat.name());
	   
	   panel1.add(dugme1);
	   panel1.add(dugme2);
	  // panel1.add(dugme3);   �imdilik bu d��meye gerek yok. 
	
	   // klas�rdeki dosyalar� listeleme ve say�s�n� alma
	   	String[] dosyalistesi;
	   	
        File file = new File(yerelklasoryolu);
        dosyalistesi = file.list();
        
     // birinci d�ng� : toplam ka� tane var ?
       for (String siradaki : dosyalistesi) {     

    	   String dosyasoyadi= siradaki.substring(siradaki.length()-4);
    	   String dosyailkkarakter= siradaki.substring(0,1);
    	   if (dosyailkkarakter.equals("+")) {
    		
    		b++;
    	   }
    	   if (dosyasoyadi.equals(".pdf")  && !dosyailkkarakter.equals("+")) {
    		   c++; 
    		}
       }
       pdflistesi = new String[c][1];
       if (b<c) {
    	   pdflistesibenzersiz = new String[c-b][1];   
       } else {
    	   pdflistesibenzersiz = new String[0][1]; 
       }
       
       artilisonrasi =new String [b];
       c=0;
       b=0;
  
       // ikinci d�ng� pdf olanlar� ayr� listeye koy
       for (String siradaki : dosyalistesi) {     
    	   
    	   String dosyasoyadi= siradaki.substring(siradaki.length()-4);
    	   String dosyailkkarakter= siradaki.substring(0,1);
    	   if (dosyailkkarakter.equals("+")) {
       		artilisonrasi [b]= siradaki.substring(1);
       		b++;
       	   }
    	   
    	   if (dosyasoyadi.equals(".pdf")  && !dosyailkkarakter.equals("+")) {
    		  pdflistesi[c][0]=siradaki;
    	  
    		  c++;
    	   }
    	   }
          
  // i�lem g�renin ve art�lanan�n tekrar listelenmemesi i�in s�zme i�lemi 
       for (int z=0; z<c ;z++) {
       
    	   for (int i=0 ; i<b ;i++ ) {
    		  
    		   if (pdflistesi[z][0].equals(artilisonrasi[i])) {
    			   kirmizikart=true;
    			}
    	  }
    	   if (kirmizikart==false) {
    		  
    		   pdflistesibenzersiz[p][0]=pdflistesi[z][0];
    		   p++;
    		   
    	   }
    	   kirmizikart=false;
        }    
        p=0;
           
    // Tablo olu�turma 
        String[] sutun = { "OPERASYON MOD�L�" };
        tablo= new JTable(pdflistesibenzersiz,sutun);  
		sp=new JScrollPane(tablo); 
		sp.setPreferredSize(new Dimension(300,645));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment(JLabel.CENTER);
   	
        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);     //tabloyu ortala
   
		TableColumnModel columnModel = tablo.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(380);
	
		Container contentPane = cerceve.getContentPane();
		  
	    contentPane.add(sp, BorderLayout.PAGE_START);
	    contentPane.add(panel1, BorderLayout.PAGE_END);
	   
	    cerceve.pack();
      
	    cerceve.setVisible(true);
       
	    ListSelectionModel cellSelectionModel = tablo.getSelectionModel();               // listeden se�ileni dinleme k�sm�
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
           
        	  if(!e.getValueIsAdjusting())  {    						// bu IF iki defa yazmamas� i�in konuldu. 
      
        		  int selectedRow     = tablo.getSelectedRow();
          		secilendosya = (String) tablo.getValueAt(selectedRow,0 );
          	    secilenyol=yerelklasoryolu+"/";
        	  }
          }
        
     });
 	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()==actions.incele.name()) {
		
		    if (!secilendosya.equals("")) {
	           if (Goruntule.yavruacik==true) {
	            	Damgala.yavru.dispose();
	        	  Goruntule.yavruacik=false;
	             }
			Goruntule.ikincigelis=true;
			}
     	}
		if (e.getActionCommand()==actions.gonder.name()) {
			
			String[] dosyalistesi;
			
			int artilisayisi=0;
			int artisizsayisi=0;
					   	
	        File file = new File(yerelklasoryolu);
	        dosyalistesi = file.list();
	        
	     // birinci d�ng� : toplam ka� tane var ?
	       for (String siradaki : dosyalistesi) {     

	    	   String dosyasoyadi= siradaki.substring(siradaki.length()-4);
	    	   String dosyailkkarakter= siradaki.substring(0,1);
	    	   if (dosyailkkarakter.equals("+") && dosyasoyadi.equals(".pdf")) {
	    		    artilisayisi++;
	            }
	    	   
	    	   if (!dosyailkkarakter.equals("+") && dosyasoyadi.equals(".pdf")) {
	    		    artisizsayisi++;
	           }
	    	}
	       
	       if ((pdflistesibenzersiz.length)!=0) {
	    	  
	    	   Bilgipenceresi.anons("Onays�z Faturalar Var. Tamam� Onaylanmadan G�nderim Yap�lamaz.");
	    	   return;
	       }
	    	
	      if (Eminmisiniz.sonkarar(artilisayisi, artisizsayisi).equals("hayir")) {
	    	  return;
	      }
		    //Goruntule.kapat=true; 
		    
		    txtvarmi();
		    
		    Goruntule.degistirdim=true;
		    secilendosya="asgard.txt";
		    secilenyol=yerelklasoryolu+"/";
		    			
		    upload();  // Onayl�lar� FTP sunucusuna g�nder
		     
		    if (ftphata==true) {
		    	Bilgipenceresi.anons("FTP Aktar�m Hatas� !!");
		    	ftphata=false;
		    	cerceve.dispose();
				 Analiste.anametod();
		    	
		    	return;
		    }else {
		    	Bilgipenceresi.anons("FTP'ye Aktarma Ba�ar�l�.");
		    }
		    
		    for(int i = 0 ; i<c ; i++) {   // Orjinalleri sil. 
		    	  File silbeni = new File(yerelklasoryolu+"/"+pdflistesi[i][0]); 
			  	  silbeni.delete();
			  	  if (silbeni.exists()==true) {
			  		  Bilgipenceresi.anons("Dosya Silinemedi");
			  	  }
			    }
		     
		    for(int i = 0 ; i<b ; i++) {   //  Onayl�lar� yerelden sil
		    	  File silbeni = new File(yerelklasoryolu+"/"+"+"+artilisonrasi[i]);
			  	  silbeni.delete();
			  	if (silbeni.exists()==true) {
			  		  Bilgipenceresi.anons("Dosya Silinemedi");
			  	  }
			    }  
		
		    cerceve.dispose();
		    Analiste.anametod();
	      return;
       }
		if (e.getActionCommand()==actions.kapat.name()) {
			
			 System.exit(0);
			}
}
	
	public static void upload() {
		
		String dosya="";
		for (int i=0; i<b;i++) {
			
	    dosya="+"+artilisonrasi[i];
       
	    String filePath = yerelklasoryolu+"/"+dosya;
        String uploadPath = ftpyolu+dosya;
       
        String ftpUrl = "ftp://"+user+":"+pass+"@"+host+":21"+uploadPath+";type=i";
        
        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);
 
        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);
 
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            inputStream.close();
            outputStream.close();
 
            System.out.println("Dosya G�nderildi");
        } catch (IOException ex) { 
        	ex.printStackTrace();
        	ftphata=true;
        
        }
           
		}  
    }
	
	   public static void txtvarmi() {   //asgard.txt dosyasi mevcut mu ?
		
		try {   
			FileReader input = new FileReader(yerelklasoryolu+"/asgard.txt");
			LineNumberReader count = new LineNumberReader(input);
			try {
				count.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
		} catch (FileNotFoundException e1) {
			txtolustur();
			e1.printStackTrace();
		}
		return;
	}
		public static void txtolustur() {   // mevcut de�il ise burada olu�tur
	

    	try {          // TXT DOSYASINI  (VER� TABANI ) OLU�TUR
    	      File myObj = new File(yerelklasoryolu+"/asgard.txt");
	          myObj.createNewFile();
	        } catch (IOException e2) {
	        	e2.printStackTrace();
	        }
	
    	try {   // bos veri taban�na 1 numaral� ilk kayd� ekle. 
	    	  FileWriter myWriter = new FileWriter(yerelklasoryolu+"/asgard.txt",true);
	          PrintWriter p = new PrintWriter(new BufferedWriter(myWriter));
	    
	          p.println("Lutfen Bekleyin.");
	           p.close();
      
	        } catch (IOException i) {
	          i.printStackTrace();
	

	     return	;
}

}
		
}

