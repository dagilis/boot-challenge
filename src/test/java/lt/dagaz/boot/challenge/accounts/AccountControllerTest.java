package lt.dagaz.boot.challenge.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAccountsEndpointRespondWithHTTPOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getAccountsResponseIsCorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"example\",\"currency\":\"EUR\",\"balance\":10.1,\"treasury\":false}]"));
    }
}