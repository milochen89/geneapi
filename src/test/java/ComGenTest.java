import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ucl.genelab.service.ComGen;

public class ComGenTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getreflisttest() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.getreflist());
	}
	
	@Test
	public void getinputlisttest() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.getinputlist());
	}
	
	@Test
	public void getgetoutputlisttest() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.getoutputlist());
	}

	@Test
	public void checkoutputlist1() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.checkoutputlist("mem_1_4G_1G_Paired"));
	}
	
	@Test
	public void checkoutputlist2() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.checkoutputlist("abcd"));
	}
	
	@Test
	public void deletecachetest() throws Exception {
		ComGen CG = new ComGen();
		Assert.assertNotNull(CG.deletecache());
	}
	
	
}
