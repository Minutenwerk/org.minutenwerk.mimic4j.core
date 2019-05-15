package org.minutenwerk.mimic4j.composite;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestMmGrandfather extends MmDiv {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  public final TestMmFather father = new TestMmFather(this);

  public TestMmGrandfather(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

}
