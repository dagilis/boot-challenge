package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.transactions.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static lt.dagaz.boot.challenge.accounts.AccountServiceTestConfig.SAMPLE_ACCOUNT;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    public static final String EXAMPLE_JSON_STRING_PAYOLOAD = "{\"name\":\"example\",\"currency\":\"EUR\",\"balance\":10.1,\"treasury\":false}";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void getAccountsEndpointRespondWithHTTPOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountsResponseIsEmptyByDefault() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAccountsResponseWillContainJson() throws Exception {
        given(accountService.getByName(null))
                .willReturn(Collections.singletonList(SAMPLE_ACCOUNT));

        mvc.perform(MockMvcRequestBuilders.get("/accounts")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + EXAMPLE_JSON_STRING_PAYOLOAD + "]"));
    }

    @Test
    public void getAccountAllowsFiltering() throws Exception {
        given(accountService.getByName(anyString()))
                .willReturn(Collections.singletonList(SAMPLE_ACCOUNT));

        mvc.perform(MockMvcRequestBuilders.get("/accounts?name=example")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + EXAMPLE_JSON_STRING_PAYOLOAD + "]"));
    }

    @Test
    public void postToTheEndpointWillSaveNewValidAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content(EXAMPLE_JSON_STRING_PAYOLOAD)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postWithMissingNameShouldRerunError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"currency\":\"EUR\",\"balance\":10.1,\"treasury\":false}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nonTreasuryAccountWithNegativeBalanceShouldFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"treasury\",\"currency\":\"EUR\",\"balance\":-10.1,\"treasury\":false}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void treasuryAccountWithNegativeBalanceShouldBeCreated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"treasury\",\"currency\":\"EUR\",\"balance\":-10.1,\"treasury\":true}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postWithBlankNameShouldRerunError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"\",\"currency\":\"EUR\",\"balance\":10.1,\"treasury\":false}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postWithMissingCurrencyShouldRerunError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"example\",\"balance\":10.1,\"treasury\":false}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postWithMissingBalanceShouldRerunError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"example\",\"currency\":\"EUR\",\"treasury\":false}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postWithMissingTreasuryShouldRerunError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .content("{\"name\":\"example\",\"currency\":\"EUR\",\"balance\":10.1}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}