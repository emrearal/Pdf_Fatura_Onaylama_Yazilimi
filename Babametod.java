package faturaonaylama;

public class Babametod {

	public static void main(String[] args) {
	babametod();  //Bunu bu �ekilde a�mam�m sebebi SWT widget'in di�er t�m pencereleri kilitlemesi
	              // JFrame veya JDialog olmayan bir s�n�ftan �a��r�lmal�. 
	}

   public static void babametod() {
	   Analiste.anametod();    
	   Goruntule.calislan();
   }
}