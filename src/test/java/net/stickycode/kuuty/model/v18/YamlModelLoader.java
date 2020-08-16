package net.stickycode.kuuty.model.v18;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class YamlModelLoader {

  Yaml yaml = new Yaml();

  public <T> T load(Object context, String resource, Class<T> type) {
    return load(context.getClass(), resource, type);
  }

  public <T> T load(Class<?> context, String resource, Class<T> type) {
    return yaml.loadAs(resource(context, resource), type);
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
