package educama;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * This test is the integration test for the spring boot implementation.
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EducamaApplication.class)
@WebAppConfiguration
@IntegrationTest
public class EducamaApplicationTests {

	/**
	 * this test actually tests that the Spring Context is created successfully, i.e. the application starts successfully.
	 */
	@Test
	public void contextLoads() {
	}

}
