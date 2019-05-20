package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTableRowPersonen
    extends MmTableRow<Person> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this);

  @MmLocalDateAnnotation(id = "bd", formatPattern = "yyyy-MM-dd")
  public final MmLocalDate birthday = new MmLocalDate(this);

  @MmEnumAnnotation(id = "gd")
  public final MmEnum<Person.Gender> gender = new MmEnum<Person.Gender>(this);

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this);
  
  public MmTableRowPersonen(MmBaseDeclaration<?,?> pParent, int pRowIndex) {
    super(pParent, pRowIndex);
  };
}
