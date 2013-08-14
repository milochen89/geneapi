

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ucl.genelab.resource.Conf;
import com.ucl.genelab.resource.Init;
import com.ucl.genelab.ssh.SshConfiguration;

public class InitTest {

	@Before
	public void setUp() throws Exception {
		Conf.setIP("127.0.0.1");
		Conf.setPORT(22);
		Conf.setUSERNAME("chenhao");
		Conf.setPASSWORD("246135");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		SshConfiguration conf = new SshConfiguration( Conf.IP,Conf.USERNAME,Conf.PASSWORD,Conf.PORT);
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
