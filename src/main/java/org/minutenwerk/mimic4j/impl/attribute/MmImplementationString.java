package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationString is the implementation part of a mimic for {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationString extends MmBaseAttributeImplementation<MmString, MmConfigurationString, MmStringAnnotation, String, String> {

  /**
   * Creates a new MmImplementationString instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationString(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   */
  @Override
  public int getMmCols() {
    assureInitialization();

    return configuration.getCols();
  }

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   */
  @Override
  public int getMmRows() {
    assureInitialization();

    return configuration.getRows();
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewModelValue == null) || viewModelValue.trim().isEmpty());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationString onConstructConfiguration(MmStringAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationString(pAnnotation);
    } else {
      return new MmConfigurationString();
    }
  }

}
