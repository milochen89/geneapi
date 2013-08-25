

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ucl.genelab.resource.Init;
import com.ucl.genelab.resource.ReadConfig;
import com.ucl.genelab.ssh.SshConfiguration;

public class InitTest {

	@Before
	public void setUp() throws Exception {
		ReadConfig.setIP("127.0.0.1");
		ReadConfig.setPORT(22);
		ReadConfig.setUSERNAME("chenhao");
		ReadConfig.setPASSWORD("246135");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		SshConfiguration conf = new SshConfiguration( ReadConfig.IP,ReadConfig.USERNAME,ReadConfig.PASSWORD,ReadConfig.PORT);
		Init init = new Init(conf);
		ArrayList<String> actuallist = new ArrayList();
		actuallist.add("GENE");
		actuallist.add("geneapi");
		actuallist.add("GENEupload");
		Assert.assertEquals(actuallist,init.reflist);
		Assert.assertEquals(actuallist,init.inputlist);
		Assert.assertEquals(actuallist,init.outputlist);
	}

}
