package org.minutenwerk.mimic4j.composite;

import org.junit.Assert;
import org.junit.Test;

public class MmMimicModelTest {

  public final TestMmRoot root = new TestMmRoot();

  @Test
  public void test1() {
    Assert.assertEquals("Fullname must be grandfather.father.child.grandchild", "grandfather.father.child.grandchild", this.root.grandfather.father.child.grandchild.getMmFullName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("Id must be grandfather-father-child-grandchild", "grandfather-father-child-grandchild", this.root.grandfather.father.child.grandchild.getMmId());
  }

}
