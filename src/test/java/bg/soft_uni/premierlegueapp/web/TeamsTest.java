package bg.soft_uni.premierlegueapp.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class TeamsTest {
    private final static String USERNAME = "random";
    private final static String ROLE = "USER";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void liverpoolTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/liverpool"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("liverpool"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void arsenalTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/arsenal"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("arsenal"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void astonVillaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/astonVilla"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("astonVilla"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void brentfordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/brentford"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("brentford"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void brightonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/brighton"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("brighton"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void chelseaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chelsea"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("chelsea"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void crystalPalaceTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/crystalPalace"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("crystalPalace"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void evertonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/everton"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("everton"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void fulhamTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fulham"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("fulham"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void manCityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manCity"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("manCity"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void manUnitedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manUnited"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("manUnited"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void newCastleUnitedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/newCastleUnited"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("newCastleUnited"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void tottenhamTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tottenham"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("tottenham"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void westHamTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/westHam"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("westHam"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void wolverhamptonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wolverhampton"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("wolverhampton"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void bournemouthTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bournemouth"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("bournemouth"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void leicesterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/leicester"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("leicester"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void ipswichTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ipswich"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("ipswich"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void nottinghamForestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nottinghamForest"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("nottinghamForest"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void southamptonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/southampton"))
                .andExpect(model().attributeExists("team"))
                .andExpect(view().name("southampton"));
    }
}
