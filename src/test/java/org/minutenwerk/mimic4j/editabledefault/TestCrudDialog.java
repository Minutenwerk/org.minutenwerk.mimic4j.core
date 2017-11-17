package org.minutenwerk.mimic4j.editabledefault;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmDate;
import org.minutenwerk.mimic4j.api.attribute.MmDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.editabledefault.TestModel.Gender;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestCrudDialog extends MmTab<TestModel> {

  @MmStringAnnotation(id = "vn", defaultValue = "Martin")
  public final MmString vorname = new MmString(this);

  @MmStringAnnotation(id = "nn", defaultValue = "Fowler")
  public final MmString nachname = new MmString(this);

  @MmDateAnnotation(id = "bd")
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
    throw new IllegalStateException();
  }

  @Override
  public void callbackMmSetModelFromModelside(TestModel pModel) {
    throw new IllegalStateException();
  }

}
