package faturaonaylama;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Goruntule {

static Shell shell;
static Display display ;
static Goruntule window;
static boolean kapat=false,ikincigelis=false,yavruacik=false,degistirdim=false;
static String budosya="",neymis="www.asgard.com.tr";
static Browser browser;
public static void calislan() {
	
    try {
        window = new Goruntule();
        window.open();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void open() {
    display = Display.getDefault();
    createContents();
    shell.open();
    shell.layout();
  
    while (!shell.isDisposed() ) {
        if (!display.readAndDispatch() ) {
        	if (kapat==true) {
        		kapat=false;
        		shell.close();
        	}
        	if (ikincigelis==true) {
        		
        		ikincigelis=false;
        		
        		browser.setUrl(Analiste.secilenyol+Analiste.secilendosya);
        		Damgala.damgala();
        		Analiste.cerceve.dispose();
        		yavruacik=false;
        	}
        	
        	if (degistirdim==true) {
        		
        		browser.setUrl(Analiste.secilenyol+Analiste.secilendosya);
        		degistirdim=false;
         	}
        	
            display.sleep();
        }
   }
}

protected void createContents() {
	
	shell = new Shell();
    shell.addListener(SWT.Close, new Listener()     // Shell kapanmasýn
   {
       public void handleEvent(Event event)
      {
           event.doit =false;    
        }
   });
 
    shell.setSize(1040,720);
    shell.setLocation(1, 5);
    shell.setText("FATURA GORUNTULEME PENCERESÝ ");
     browser= new Browser(shell, SWT.NONE);
	
    browser.setUrl(neymis);
    browser.setBounds(5,5, 1020, 715);
  }
}