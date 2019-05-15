package org.minutenwerk.mimic4j.composite;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestMmGrandchild extends MmDiv {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  public TestMmGrandchild(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

}
