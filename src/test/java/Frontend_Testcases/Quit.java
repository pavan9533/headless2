package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class Quit extends JohnsonBase{
	
	@Test
	public void quit() {
		application.quit();
	}

}
