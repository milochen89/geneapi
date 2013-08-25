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


public class GetJobIdTest2 {

	
	@Before
	public void setUp() throws Exception {
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test1() {
		Bwamem bwamem = new Bwamem();
		bwamem.setInputpath("56M");
		bwamem.setRefname("131M");
		bwamem.setAlgorithm("mem");
		String jobidactual = GetJobId.getjobid(bwamem);
		Assert.assertNotNull(jobidactual);
	}
	
	@Test
	public void test2() {
		Bwamem bwamem = new Bwamem();
		bwamem.setInputpath("56M");
		bwamem.setRefname("none");
		bwamem.setAlgorithm("mem");
		String jobidactual = GetJobId.getjobid(bwamem);
		Assert.assertNotNull(jobidactual);
	}

}
