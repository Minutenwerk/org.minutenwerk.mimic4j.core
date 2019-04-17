package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationEnum;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmJsfBridgeAttributeEnum connects an enum attribute mimic and a JSF attribute view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeAttributeEnum<ENUM_TYPE extends Enum<ENUM_TYPE>> extends MmJsfBridgeAttribute<String> {

  /**
   * Creates a new MmJsfBridgeAttributeEnum instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeAttributeEnum(MmImplementationEnum<?> pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the value of type VIEWSIDE_VALUE from mimic, to be displayed in HTML tag.
   *
   * @return  The value of type VIEWSIDE_VALUE from mimic, to be displayed in HTML tag.
   */
  @Override
  public String getValue() {
    @SuppressWarnings("unchecked")
    MmImplementationEnum<ENUM_TYPE> implementationEnum = ((MmImplementationEnum<ENUM_TYPE>)this.implementation);
    if (implementationEnum.isMmReadOnly() || !implementationEnum.isMmEnabled()) {

      // if mimic is readOnly or disabled, show localized value in textfield
      Class<ENUM_TYPE> enumType       = implementationEnum.getMmEnumType();
      String           enumTypeName   = enumType.getSimpleName();
      MmRoot           root           = implementationEnum.getMmRoot();
      ENUM_TYPE        modelsideValue = implementationEnum.getMmModelsideValue();
      String           enumLabel      = (modelsideValue != null)
        ? root.getMmI18nText(enumTypeName + "." + modelsideValue.name(), MmMessageType.SHORT) : "";
      return enumLabel;
    } else {

      // show modelside value in select options
      return super.getValue();
    }
  }

}
