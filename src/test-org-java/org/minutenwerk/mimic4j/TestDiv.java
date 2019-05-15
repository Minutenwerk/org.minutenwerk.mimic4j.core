package org.minutenwerk.mimic4j;

import org.minutenwerk.mimic4j.api.MmAttributeMimic.MmBooleanLayout;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmModelAccessor;

public class TestDiv extends MmDiv {

  public TestDiv(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

  public final MmDiv tab1 = new MmDiv(this) {

    @MmStringAnnotation(id = "vn")
    public final MmString vorname = new MmString(this) {
      public MmAttributeAccessor<?,String> callbackMmGetAccessor(MmModelAccessor<?,?> pRootAccessor) {
        return null;
      };
    };

    @MmStringAnnotation(id = "nn", required = true)
    public final MmString nachname = new MmString(this);

    @MmLocalDateAnnotation(id = "bd", formatPattern = "dd.MM.yyyy")
    public final MmLocalDate birthday = new MmLocalDate(this);

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
