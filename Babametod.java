package faturaonaylama;

import java.net.URISyntaxException;

public class Babametod {

	public static void main(String[] args) {
		
		//
		try {    // Program şu anda hangi klasörde çalışıyor ? 
			String jarPath = Babametod.class
			          .getProtectionDomain()
			          .getCodeSource()
			          .getLocation()
			          .toURI()
			          .getPath();
			
			Analiste.yerelklasoryolu = jarPath.substring(1,jarPath.lastIndexOf("/"))+"/yenifaturalar";
							
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	babametod();  //Bunu bu şekilde açmamım sebebi SWT widget'in diğer tüm pencereleri kilitlemesi
	              // JFrame veya JDialog olmayan bir sınıftan çağırılmalı. 
	}

   public static void babametod() {
	   Analiste.anametod();    
	   Goruntule.calislan();
   }
}
