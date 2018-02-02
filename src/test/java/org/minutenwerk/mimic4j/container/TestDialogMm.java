package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTimeAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.container.TestModel.Gender;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestDialogMm
    extends MmTab<TestModel> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this);

  @MmLocalDateTimeAnnotation(id = "bd")
  public final MmLocalDateTime birthday = new MmLocalDateTime(this);

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
    this.gender.setMmModelsideValue(pModel.getGender());
    this.adresse.doMmSetModelsideFromModel(pModel.getAdresse());
  }

  @Override
  public void callbackMmSetModelFromModelside(TestModel pModel) {
    pModel.setVorname(this.vorname.getMmModelsideValue());
    pModel.setNachname(this.nachname.getMmModelsideValue());
    pModel.setMember(this.isMember.getMmModelsideValue());
    pModel.setBirthday(this.birthday.getMmModelsideValue());
    pModel.setGender(this.gender.getMmModelsideValue());
  }

}
