import com.fasterxml.jackson.databind.util.BeanUtil;
import com.walmart.sem.controller.SubjectController;
import com.walmart.sem.dto.SubjectDto;
import com.walmart.sem.mapper.EntityMapper;
import com.walmart.sem.mapper.SubjectMapper;
import com.walmart.sem.model.Subject;
import com.walmart.sem.service.SubjectService;
import java.util.Arrays;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SubjectControllerTest {
  private static final String ENDPOINT_URL = "/api/subject;@InjectMocks
  private SubjectController subjectController;
  @Mock
  private SubjectService subjectService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(subjectController)
        //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //.addFilter(CustomFilter::doFilter)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<SubjectDto> page = new PageImpl<>(Collections.singletonList(SubjectBuilder.getDto()));

    Mockito.when(subjectService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(subjectService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(subjectService);

  }

  @Test
  public void getById() throws Exception {
    Mockito.when(subjectService.findById(ArgumentMatchers.anyInteger())).thenReturn(SubjectBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    Mockito.verify(subjectService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(subjectService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(subjectService.save(ArgumentMatchers.any(SubjectDto.class))).thenReturn(SubjectBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(SubjectBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(subjectService, Mockito.times(1)).save(ArgumentMatchers.any(SubjectDto.class));
    Mockito.verifyNoMoreInteractions(subjectService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(subjectService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(SubjectBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(SubjectBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(subjectService, Mockito.times(1)).update(ArgumentMatchers.any(SubjectDto.class), ArgumentMatchers.anyInteger());
    Mockito.verifyNoMoreInteractions(subjectService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(subjectService).deleteById(ArgumentMatchers.anyInteger());
    mockMvc.perform(
        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(CustomUtils.asJsonString(SubjectBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(subjectService, Mockito.times(1)).deleteById(Mockito.anyInteger());
    Mockito.verifyNoMoreInteractions(subjectService);
  }
}