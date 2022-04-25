import com.fasterxml.jackson.databind.util.BeanUtil;
import com.walmart.sem.controller.CustomUtils;
import com.walmart.sem.controller.StudentBuilder;
import com.walmart.sem.controller.StudentController;
import com.walmart.sem.dto.StudentDto;
import com.walmart.sem.mapper.EntityMapper;
import com.walmart.sem.mapper.StudentMapper;
import com.walmart.sem.model.Student;
import com.walmart.sem.service.StudentService;
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
public class StudentControllerTest {
  private static final String ENDPOINT_URL = "/api/student;@InjectMocks
  private StudentController studentController;
  @Mock
  private StudentService studentService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(studentController)
        //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //.addFilter(CustomFilter::doFilter)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<StudentDto> page = new PageImpl<>(Collections.singletonList(StudentBuilder.getDto()));

    Mockito.when(studentService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(studentService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(studentService);

  }

  @Test
  public void getById() throws Exception {
    Mockito.when(studentService.findById(ArgumentMatchers.anyInteger())).thenReturn(StudentBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    Mockito.verify(studentService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(studentService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(studentService.save(ArgumentMatchers.any(StudentDto.class))).thenReturn(StudentBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(StudentBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(studentService, Mockito.times(1)).save(ArgumentMatchers.any(StudentDto.class));
    Mockito.verifyNoMoreInteractions(studentService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(studentService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(StudentBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(StudentBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(studentService, Mockito.times(1)).update(ArgumentMatchers.any(StudentDto.class), ArgumentMatchers.anyInteger());
    Mockito.verifyNoMoreInteractions(studentService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(studentService).deleteById(ArgumentMatchers.anyInteger());
    mockMvc.perform(
        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(CustomUtils.asJsonString(StudentBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(studentService, Mockito.times(1)).deleteById(Mockito.anyInteger());
    Mockito.verifyNoMoreInteractions(studentService);
  }
}