package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTabAdresse extends MmTab<TestAdresse> {

  @MmStringAnnotation(id = "st")
  public final MmString street = new MmString(this);

  @MmStringAnnotation(id = "ci")
  public final MmString city = new MmString(this);
  
  public MmTabAdresse(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }

  @Override
  public void callbackMmSetModelsideFromModel(TestAdresse pModel) {
    this.street.setMmModelsideValue(pModel.getStreet());
    this.city.setMmModelsideValue(pModel.getCity());
  }

  @Override
  public void callbackMmSetModelFromModelside(TestAdresse pModel) {
    pModel.setStreet(this.street.getMmModelsideValue());
    pModel.setCity(this.city.getMmModelsideValue());
  }

}
