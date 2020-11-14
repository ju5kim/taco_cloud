package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest // 컨트롤러가 스프링 mvc에 등록되는데 우리는 모의 메커니즘을 사용해도 충분하므로 
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))   //get형식으로 / 를 수행한다. 
		.andExpect(status().isOk()) // 상태코드가 200이 된다.
		.andExpect(view().name("home")) // 뷰 네임은 home 이다.
		.andExpect(result-> { content().string(containsString("Welcome to...")); }
				); // 콘탠츠에 스트링 타입의 Welcome to가있어야한다.
		
	}

}
