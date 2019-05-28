package org.minutenwerk.mimic4j.impl.javahelper;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

@MmTabAnnotation(id = "some")
public class MmTabHelperAdresse extends MmTab<MmHelperAdresse> {

  @MmStringAnnotation(id = "st")
  public final MmString street = new MmString(this);

  @MmStringAnnotation(id = "ci")
  public final MmString city = new MmString(this);

  public MmTabHelperAdresse(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }
}
