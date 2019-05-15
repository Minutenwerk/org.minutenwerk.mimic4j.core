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
import org.minutenwerk.mimic4j.table.TestModel.Gender;

public class TestTableRowMm
    extends MmTableRow<TestModel> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this);

  @MmLocalDateAnnotation(id = "bd", formatPattern = "yyyy-MM-dd")
  public final MmLocalDate birthday = new MmLocalDate(this);

  @MmEnumAnnotation(id = "gd", defaultValue = "MALE")
  public final MmEnum<Gender> gender = new MmEnum<Gender>(this);

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this);
  
  public TestTableRowMm(MmBaseDeclaration<?,?> pParent, int pRowIndex) {
    super(pParent, pRowIndex);
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
  }

}