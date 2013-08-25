import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.ucl.genelab.metadata.Bwamem;
import com.ucl.genelab.resource.ReadConfig;
import com.ucl.genelab.ssh.GetJobId;


public class GetJobIdTest {

	
	@Before
	public void setUp() throws Exception {
		ReadConfig.setIP("127.0.0.1");
		ReadConfig.setPORT(22);
		ReadConfig.setUSERNAME("chenhao");
		ReadConfig.setPASSWORD("246135");
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
