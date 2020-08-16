package net.stickycode.kuuty.model.v18;
import java.io.IOException;
import java.io.InputStream;

import org.openapitools.jackson.nullable.JsonNullableModule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonModelLoader {

  private ObjectMapper mapper;

  public JsonModelLoader() {
    mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    mapper.registerModule(new JavaTimeModule());
    JsonNullableModule jnm = new JsonNullableModule();
    mapper.registerModule(jnm);
  }

  public <T> T load(Object context, String resource, Class<T> type) {
    return load(context.getClass(), resource, type);
  }

  public <T> T load(Class<?> context, String resource, Class<T> type) {
    try {
      return mapper
        .readerFor(type)
        .readValue(resource(context, resource));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private InputStream resource(Class<?> context, String resource) {
    return resource("examples/" + context.getSimpleName() + "/" + resource);
  }

  private InputStream resource(String name) {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
    if (inputStream == null)
      throw new RuntimeException("Could not find the resource " + name);

    return inputStream;
  }
}
