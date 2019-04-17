package org.minutenwerk.mimic4j.impl.composite;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.composite.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTableColumn;

/**
 * MmCompositeImplementationTableColumn is the specific class for the implementation part of table column mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationTableColumn extends MmBaseCompositeImplementation<MmTableColumn, MmConfigurationTableColumn> {

  /**
   * Creates a new MmCompositeImplementationTableColumn instance.
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
    this.ensureInitialization();

    return this.configuration.getFooterClasses();
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the header of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the header of this column.
   */
  public String getMmHeaderClasses() {
    this.ensureInitialization();

    return this.configuration.getHeaderClasses();
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
    this.ensureInitialization();

    final String callbackValue      = this.declaration.callbackMmGetStyleClasses("");
    final String configurationValue = this.configuration.getStyleClasses();
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
    this.ensureInitialization();

    return this.configuration.isRowHeader();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeTableColumn(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmTableColumnAnnotation.class);

    MmTableColumnAnnotation annotation = this.findAnnotation(this.declaration, MmTableColumnAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationTableColumn();
    } else {
      this.configuration = new MmConfigurationTableColumn(annotation);
    }
  }

}
