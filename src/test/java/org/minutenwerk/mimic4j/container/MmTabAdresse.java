package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTabAdresse extends MmTab<Adress> {

  @MmStringAnnotation(id = "st")
  public final MmString street = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmComponentAccessor<?, ?> pParentAccessor) {
      AdressAccessor adress = (AdressAccessor) pParentAccessor;
      return adress.street();
    }
  };

  @MmStringAnnotation(id = "ci")
  public final MmString city = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmComponentAccessor<?, ?> pParentAccessor) {
      AdressAccessor adress = (AdressAccessor) pParentAccessor;
      return adress.city();
    }
  };

  public MmTabAdresse(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }
}
