package faturaonaylama;

public class Babametod {

	public static void main(String[] args) {
	babametod();  //Bunu bu þekilde açmamým sebebi SWT widget'in diðer tüm pencereleri kilitlemesi
	              // JFrame veya JDialog olmayan bir sýnýftan çaðýrýlmalý. 
	}

   public static void babametod() {
	   Analiste.anametod();    
	   Goruntule.calislan();
   }
}