package org.minutenwerk.mimic4j.composite;

import org.minutenwerk.mimic4j.api.composite.MmRoot;

public class TestMmRoot extends MmRoot {

  public final TestMmGrandfather grandfather = new TestMmGrandfather(this);

  public TestMmRoot() {
  }

}
