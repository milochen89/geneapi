import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.ucl.genelab.metadata.Bwamem;
import com.ucl.genelab.resource.Conf;
import com.ucl.genelab.ssh.GetJobId;


public class GetJobIdTest {

	
	@Before
	public void setUp() throws Exception {
		Conf.setIP("127.0.0.1");
		Conf.setPORT(22);
		Conf.setUSERNAME("chenhao");
		Conf.setPASSWORD("246135");
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Bwamem bwamem = new Bwamem();
		bwamem.setInputpath("geneapi");
		bwamem.setRefname("geneapi");
		bwamem.setAlgorithm("geneapi");
		String jobidexpected = "hadoop: command not found";
		String jobidactual = GetJobId.getjobid(bwamem);
		Assert.assertEquals(jobidexpected,jobidactual);
	}

}
