package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTableColumn;

/**
 * MmImplementationTableColumn is the specific class for the implementation part of table column mimics.
 *
 * @author  Olaf Kossak
 */
// TableColumn hat noch keinen Accessor
public class MmImplementationTableColumn<MODEL>
  extends MmBaseContainerImplementation<MmTableColumn<MODEL>, MODEL, MmConfigurationTableColumn, MmTableColumnAnnotation> {

  /**
   * Creates a new MmImplementationTableColumn instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationTableColumn(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the footer of this column.
   */
  public String getMmFooterClasses() {
    assureInitialization();

    return configuration.getFooterClasses();
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the header of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the header of this column.
   */
  public String getMmHeaderClasses() {
    assureInitialization();

    return configuration.getHeaderClasses();
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmStyleClasses() {
    assureInitialization();

    final String callbackValue      = declaration.callbackMmGetStyleClasses("");
    final String configurationValue = configuration.getStyleClasses();
    if (callbackValue.isEmpty()) {
      return configurationValue;
    } else if (configurationValue.isEmpty()) {
      return callbackValue;
    } else {
      return callbackValue + " " + configurationValue;
    }
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   */
  public boolean isMmRowHeader() {
    assureInitialization();

    return configuration.isRowHeader();
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationTableColumn onConstructConfiguration(MmTableColumnAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationTableColumn(pAnnotation);
    } else {
      return new MmConfigurationTableColumn();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeTableColumn(this);
  }

}
