package hh.swd20.contactdetails;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.contactdetails.web.ContactTypeController;
import hh.swd20.contactdetails.web.PersonController;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContactdetailsApplicationTests {

	@Autowired
	private ContactTypeController contactController;
	
	@Autowired
	private PersonController personController;
	
	@Test
	public void contextLoads1()throws Exception {
		assertThat(contactController).isNotNull();
	}
	@Test
	public void contextLoads2()throws Exception {
		assertThat(personController).isNotNull();
	}

}
