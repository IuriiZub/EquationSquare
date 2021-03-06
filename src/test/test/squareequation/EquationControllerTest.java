package test.squareequation;

import com.squareequation.config.MyConfig;
import com.squareequation.controller.EquationController;
import com.squareequation.service.dto.EquationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test of Main controller
 * Created by Yurii on 5/4/2019.
 *
 * @test of controller
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {MyConfig.class})
@EnableWebMvc

public class EquationControllerTest {

    @InjectMocks
    EquationController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Test of root route
     *
     * @throws Exception
     */
    @Test
    public void getMainPage() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    /**
     * Test of post form
     *
     * @throws Exception
     */

    @Test
    public void calcSubmit() throws Exception {
        mockMvc.perform(post("/results")
                .flashAttr("equationdto", createDTO())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("results"));
    }

    public static EquationDTO createDTO() {
        EquationDTO equationDTO = new EquationDTO();
        equationDTO.setParamA(1.0);
        equationDTO.setParamB(2.0);
        equationDTO.setParamC(1.0);
        return equationDTO;
    }

    /**
     * Test of result route
     *
     * @throws Exception
     */
    @Test
    public void showEnterForm() throws Exception {
        mockMvc.perform(get("/equation")
                .accept(MediaType.TEXT_HTML))
                .andExpect(view().name("equationform"))
                .andExpect(status().isOk());
    }
}