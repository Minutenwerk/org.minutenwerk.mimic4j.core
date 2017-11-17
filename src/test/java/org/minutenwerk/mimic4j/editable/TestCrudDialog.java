package org.minutenwerk.mimic4j.editable;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmDate;
import org.minutenwerk.mimic4j.api.attribute.MmDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.editable.TestModel.Gender;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

@MmTabAnnotation(id = "tab")
public class TestCrudDialog extends MmTab<TestModel> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn", required = true)
  public final MmString nachname = new MmString(this);

  @MmDateAnnotation(id = "bd", formatPattern = "dd.MM.yyyy", required = true)
  public final MmDate birthday = new MmDate(this);

  @MmEnumAnnotation(id = "gd", defaultValue = "MALE")
  public final MmEnum<Gender> gender = new MmEnum<Gender>(this);

  @MmBooleanAnnotation(id = "mb", defaultValue = true)
  public final MmBoolean isMember = new MmBoolean(this);

  public TestCrudDialog(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

  @Override
  public void callbackMmSetModelsideFromModel(TestModel pModel) {
    this.vorname.setMmModelsideValue(pModel.getVorname());
    this.nachname.setMmModelsideValue(pModel.getNachname());
    this.isMember.setMmModelsideValue(pModel.isMember());
    this.birthday.setMmModelsideValue(pModel.getBirthday());
    this.gender.setMmModelsideValue(pModel.getGender());
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
