import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ucl.genelab.metadata.Completion;
import com.ucl.genelab.resource.ReadConfig;
import com.ucl.genelab.ssh.GetJobProcess;

public class GetJobProcessTest {
	
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
	public void test() {
		String jobid = "job_201308051514_0002";
		Completion actualcompletion = new Completion();
		GetJobProcess.getjobprogcess(jobid, actualcompletion);
		Completion expectedcompletion = new Completion();
		expectedcompletion.setMapcompletion(-1.0);
		expectedcompletion.setReducecompletion(0.0);
	    System.out.println(expectedcompletion.toString());
	    System.out.println(actualcompletion.toString());
		Assert.assertEquals(expectedcompletion.getMapcompletion(),actualcompletion.getMapcompletion());
		Assert.assertEquals(expectedcompletion.getReducecompletion(),actualcompletion.getReducecompletion());
	}

}
