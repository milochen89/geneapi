import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ucl.genelab.metadata.Completion;
import com.ucl.genelab.resource.ReadConfig;
import com.ucl.genelab.ssh.GetJobProcess;

public class GetJobProcessTest2 {
	
	@Before
	public void setUp() throws Exception {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test1() {
		String jobid = "job_201308051514_0002";
		Completion actualcompletion = new Completion();
		GetJobProcess.getjobprogcess(jobid, actualcompletion);
		Completion expectedcompletion = new Completion();
		expectedcompletion.setMapcompletion(-1.0);
		expectedcompletion.setReducecompletion(0.0);
		Assert.assertEquals(expectedcompletion.getMapcompletion(),actualcompletion.getMapcompletion());
		Assert.assertEquals(expectedcompletion.getReducecompletion(),actualcompletion.getReducecompletion());
	}

	@Test
	public void test2() {
		String jobid = "job_201307291516_0863";
		Completion actualcompletion = new Completion();
		GetJobProcess.getjobprogcess(jobid, actualcompletion);
		Completion expectedcompletion = new Completion();
		expectedcompletion.setMapcompletion(1.0);
		expectedcompletion.setReducecompletion(1.0);
		Assert.assertEquals(expectedcompletion.getMapcompletion(),actualcompletion.getMapcompletion());
		Assert.assertEquals(expectedcompletion.getReducecompletion(),actualcompletion.getReducecompletion());
	}
	
	@Test
	public void test3() {
		String jobid = "sdasd";
		Completion actualcompletion = new Completion();
		GetJobProcess.getjobprogcess(jobid, actualcompletion);
		Completion expectedcompletion = new Completion();
		expectedcompletion.setMapcompletion(-1.0);
		expectedcompletion.setReducecompletion(0.0);
		Assert.assertEquals(expectedcompletion.getMapcompletion(),actualcompletion.getMapcompletion());
		Assert.assertEquals(expectedcompletion.getReducecompletion(),actualcompletion.getReducecompletion());
	}
	
	@Test
	public void test4() {
		String jobid = "201307291516_0863";
		Completion actualcompletion = new Completion();
		GetJobProcess.getjobprogcess(jobid, actualcompletion);
		Completion expectedcompletion = new Completion();
		expectedcompletion.setMapcompletion(-1.0);
		expectedcompletion.setReducecompletion(0.0);
		Assert.assertEquals(expectedcompletion.getMapcompletion(),actualcompletion.getMapcompletion());
		Assert.assertEquals(expectedcompletion.getReducecompletion(),actualcompletion.getReducecompletion());
	}
	
}