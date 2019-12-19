package org.minutenwerk.mimic4j.impl.container;

import java.util.HashSet;
import java.util.Set;

import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTableColumn contains configuration information for mimics of type {@link MmTableColumn}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTableColumn extends MmBaseConfiguration {

  /** Constant for configuration of default value of column is row header column. */
  public static final boolean DEFAULT_IS_ROW_HEADER  = false;

  /** Constant for configuration of default value of column CSS style classes. */
  public static final String  DEFAULT_STYLE_CLASSES  = "";

  /** Constant for configuration of default value of footer CSS style classes. */
  public static final String  DEFAULT_FOOTER_CLASSES = "";

  /** True, if column is row header column. */
  protected boolean           isRowHeader;

  /** The columns CSS style classes. */
  protected String            styleClasses;

  /** The footer CSS style classes. */
  protected String            footerClasses;

  /**
   * Creates a new MmConfigurationTableColumn instance of default values.
   */
  public MmConfigurationTableColumn() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
    isRowHeader   = DEFAULT_IS_ROW_HEADER;
    styleClasses  = DEFAULT_STYLE_CLASSES;
    footerClasses = DEFAULT_FOOTER_CLASSES;
  }

  /**
   * Creates a new MmConfigurationTableColumn instance from annotation.
   *
   * @param  pTableColumnAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTableColumn(MmTableColumnAnnotation pTableColumnAnnotation) {
    super(pTableColumnAnnotation.id(), pTableColumnAnnotation.visible(), pTableColumnAnnotation.readOnly(), pTableColumnAnnotation.enabled(),
      pTableColumnAnnotation.styleClasses());
    isRowHeader   = pTableColumnAnnotation.isRowHeader();
    styleClasses  = pTableColumnAnnotation.styleClasses();
    footerClasses = pTableColumnAnnotation.footerClasses();
  }

  /**
   * Adds a CSS style class for footer of this table column.
   *
   * @param  pFooterClass  The specified CSS style class to add.
   */
  public void addFooterClass(String pFooterClass) {
    Set<String> tempFooterClasses = new HashSet<String>();
    for (String styleClass : footerClasses.split(" ")) {
      tempFooterClasses.add(styleClass);
    }

    tempFooterClasses.add(pFooterClass);

    String newFooterClasses = "";
    for (String styleClass : tempFooterClasses) {
      newFooterClasses = newFooterClasses + " " + styleClass;
    }
    footerClasses = newFooterClasses;
  }

  /**
   * Delete all configured footer CSS style classes.
   */
  public void clearFooterClasses() {
    footerClasses = "";
  }

  /**
   * Returns all configured footer CSS style classes.
   *
   * @return  All configured footer CSS style classes.
   */
  public String getFooterClasses() {
    return footerClasses;
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style classes are evaluated
   * from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   */
  @Override
  public String getStyleClasses() {
    return styleClasses;
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   */
  public boolean isRowHeader() {
    return isRowHeader;
  }

  /**
   * Sets the configuration whether this row is a table row header.
   *
   * @param  pIsRowHeader  The specified configuration whether this row is a table row header.
   */
  public void setRowHeader(boolean pIsRowHeader) {
    isRowHeader = pIsRowHeader;
  }

}
