package org.minutenwerk.mimic4j.impl.javahelper;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmInstant;
import org.minutenwerk.mimic4j.api.attribute.MmInstantAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTimeAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTimeAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.javahelper.MmHelperPerson.Gender;

public class MmTabHelperPerson extends MmTab<MmHelperPerson> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this);

  @MmLocalDateAnnotation(id = "bd")
  public final MmLocalDate birthday = new MmLocalDate(this);

  @MmInstantAnnotation(id = "in")
  public final MmInstant instant = new MmInstant(this);

  @MmLocalTimeAnnotation(id = "lt")
  public final MmLocalTime localTime = new MmLocalTime(this);

  @MmLocalDateTimeAnnotation(id = "ldt")
  public final MmLocalDateTime localDateTime = new MmLocalDateTime(this);

  @MmZonedDateTimeAnnotation(id = "zdt")
  public final MmZonedDateTime zonedDateTime = new MmZonedDateTime(this);

  @MmEnumAnnotation(id = "gd")
  public final MmEnum<Gender> gender = new MmEnum<Gender>(this);

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this);

  @MmTabAnnotation(id = "add")
  public final MmTabHelperAdresse adresse = new MmTabHelperAdresse(this) ;

  public MmTabHelperPerson(MmBaseDeclaration<?, ?> pParent, MmRootAccessor<MmHelperPerson> pRootAccessor) {
    super(pParent, pRootAccessor);
  }
}
