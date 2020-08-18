package net.stickycode.kuuty.model.v18;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DeploymentTest {

  private JsonModelLoader loader = new JsonModelLoader();

  private YamlModelLoader yaml = new YamlModelLoader();

  @Rule
  public TestName name = new TestName();

  @Test
  @Ignore
  public void deployment() {
    IoK8sApiAppsV1Deployment deployment = load();
    assertThat(deployment).isEqualToComparingFieldByField(new IoK8sApiAppsV1Deployment());
  }

  private IoK8sApiAppsV1Deployment load() {
    IoK8sApiAppsV1Deployment deployment = yaml.load(this, name.getMethodName() + ".yaml", IoK8sApiAppsV1Deployment.class);
    return deployment;
  }

}
