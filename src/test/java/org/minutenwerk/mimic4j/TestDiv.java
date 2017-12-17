package org.minutenwerk.mimic4j;

import org.minutenwerk.mimic4j.api.MmAttributeMimic.MmBooleanLayout;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmDate;
import org.minutenwerk.mimic4j.api.attribute.MmDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestDiv extends MmDiv {

  public TestDiv(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

  public final MmDiv tab1 = new MmDiv(this) {

    @MmStringAnnotation(id = "vn")
    public final MmString vorname = new MmString(this);

    @MmStringAnnotation(id = "nn", required = true)
    public final MmString nachname = new MmString(this);

    @MmDateAnnotation(id = "bd", formatPattern = "dd.MM.yyyy")
    public final MmDate birthday = new MmDate(this);

    @MmBooleanAnnotation(id = "mb", defaultValue = true, layout = MmBooleanLayout.LINE_DIRECTION)
    public final MmBoolean isMember = new MmBoolean(this);

  };

  public final MmDiv tab2 = new MmDiv(this) {

    @MmStringAnnotation(id = "plz")
    public final MmString plz = new MmString(this);

    @MmStringAnnotation(id = "ort")
    public final MmString ort = new MmString(this);

    @MmBooleanAnnotation(id = "st")
    public final MmBoolean strasse = new MmBoolean(this);

  };

}