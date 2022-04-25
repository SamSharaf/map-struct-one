import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class SubjectBuilder {
  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static SubjectDto getDto() {
    SubjectDto dto = new SubjectDto();
    dto.setId("1");
    return dto;
  }
}