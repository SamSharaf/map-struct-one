package com.walmart.sem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

public class FileReaderUtil {
  public static String read(String location) throws IOException {
    return IOUtils.toString(
        new ClassPathResource(location).getInputStream(),
        StandardCharsets.UTF_8
    );
  }
}
