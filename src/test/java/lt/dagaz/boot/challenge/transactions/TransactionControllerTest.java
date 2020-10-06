package lt.dagaz.boot.challenge.transactions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lt.dagaz.boot.challenge.transactions.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Currency;

import static java.math.BigDecimal.ONE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    public static final Currency EUR = Currency.getInstance("EUR");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void getAccountsEndpointRespondWithHTTPOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("Vytautas", "Marie", EUR, ONE)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void trasferBetweenDifferentCurrenciesFails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("Vytautas", "Mark", EUR, ONE)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWithMissingToNameWillBeRejected() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("Vytautas", null, EUR, ONE)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWithMissingFromNameWillBeRejected() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("", "Marc", EUR, ONE)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWithMissingCurrencyWillBeRejected() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("Vytautas", "Marc", null, ONE)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWithMissingAmountWillBeRejected() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(new Transaction("Vytautas", "Marc", EUR, null)))
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWithEmptyBodyWillBeRejected() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    private String mapToJson(Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}