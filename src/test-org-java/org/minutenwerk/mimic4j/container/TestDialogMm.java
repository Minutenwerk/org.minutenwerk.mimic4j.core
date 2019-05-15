package org.minutenwerk.mimic4j.container;

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
import org.minutenwerk.mimic4j.container.TestModel.Gender;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;

public class TestDialogMm
    extends MmTab<TestModel> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this) {
	public MmAttributeAccessor<TestModel, String> callbackSetAccessor(TestModelAccessor accessor) {
		return accessor.vorname();
	}
  };

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

  @MmEnumAnnotation(id = "gd", defaultValue = "MALE")
  public final MmEnum<Gender> gender = new MmEnum<Gender>(this);

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this);
  
  public final MmTabAdresse adresse = new MmTabAdresse(this);

  public TestDialogMm(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

  @Override
  public void callbackMmSetModelsideFromModel(TestModel pModel) {
    this.vorname.setMmModelsideValue(pModel.getVorname());
    this.nachname.setMmModelsideValue(pModel.getNachname());
    this.isMember.setMmModelsideValue(pModel.isMember());
    this.birthday.setMmModelsideValue(pModel.getBirthday());
    this.instant.setMmModelsideValue(pModel.getInstant());
    this.localTime.setMmModelsideValue(pModel.getLocalTime());
    this.localDateTime.setMmModelsideValue(pModel.getLocalDateTime());
    this.zonedDateTime.setMmModelsideValue(pModel.getZonedDateTime());
    this.gender.setMmModelsideValue(pModel.getGender());
    this.adresse.doMmSetModelsideFromModel(pModel.getAdresse());
  }

  @Override
  public void callbackMmSetModelFromModelside(TestModel pModel) {
    pModel.setVorname(this.vorname.getMmModelsideValue());
    pModel.setNachname(this.nachname.getMmModelsideValue());
    pModel.setMember(this.isMember.getMmModelsideValue());
    pModel.setBirthday(this.birthday.getMmModelsideValue());
    pModel.setInstant(this.instant.getMmModelsideValue());
    pModel.setLocalTime(this.localTime.getMmModelsideValue());
    pModel.setLocalDateTime(this.localDateTime.getMmModelsideValue());
    pModel.setZonedDateTime(this.zonedDateTime.getMmModelsideValue());
    pModel.setGender(this.gender.getMmModelsideValue());
  }

}
