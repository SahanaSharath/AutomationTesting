package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;

public class TestDemo2 extends BaseTest {

	@Test
	public void testB() {
		Reporter.log("testB",true);
	}
}
