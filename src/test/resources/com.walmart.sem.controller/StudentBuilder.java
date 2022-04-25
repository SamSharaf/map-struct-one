import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class StudentBuilder {
  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static StudentDto getDto() {
    StudentDto dto = new StudentDto();
    dto.setId("1");
    return dto;
  }
}