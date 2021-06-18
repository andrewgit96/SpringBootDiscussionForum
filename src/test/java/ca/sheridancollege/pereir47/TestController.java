package ca.sheridancollege.pereir47;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import ca.sheridancollege.pereir47.beans.Messages;
import ca.sheridancollege.pereir47.database.DatabaseAccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc (addFilters = false)
public class TestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DatabaseAccess da;
	

	//tested some positive tests and failure tests 
	//some testing problems due to spring security
	@Test
	public void testRegisterPage()throws Exception {
		this.mockMvc.perform(get("/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("register"));
		}
	
	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
		}
	
	@Test
	public void testWelcome() throws Exception {
		this.mockMvc.perform(get("/welcome"))
		.andExpect(status().isOk())
		.andExpect(view().name("welcome"));
		}
	
	@Test
	public void testHome() throws Exception {
		this.mockMvc
		.perform(get("/").flashAttr("thread", new Thread()))
		.andExpect(status().isBadRequest())
		.andExpect(redirectedUrl("/"));
				
	}
	
	
	@Test
	public void testView() throws Exception{
		this.mockMvc
		.perform(get("/view"))
		.andExpect(status().isBadRequest())
		.andExpect(view().name("view-thread"));
	}
	

	@Test
	public void testInsertMessage() {
		Messages message = new Messages();
		int messageList = da.getAllMessages().size();
		da.insertMessage(message);
		int messageListNew = da.getAllMessages().size();
		assertThat(messageListNew).isEqualTo(messageList + 1);

	}
	
	@Test
	public void testSubmitPost() throws Exception{
		this.mockMvc.perform(get("/"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAddThread() {
		ca.sheridancollege.pereir47.beans.Thread thread = new ca.sheridancollege.pereir47.beans.Thread();
		int originalsize = da.getAllThreads().size();
		da.insertThread(thread);
		int foundsize = da.getAllThreads().size();
		
		assertThat(foundsize).isEqualTo(originalsize);
	}
	
	
	@Test
	public void testAddThreadPage() throws Exception {
	this.mockMvc
	.perform(get("/admin")
	.flashAttr("thread", new Thread()))
	.andExpect(status().isFound())
	.andExpect(redirectedUrl("admin")); //testing problem due to spring security
	}
	
	
	
	
	
		
}
	


