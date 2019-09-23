package org.minutenwerk.mimic4j.impl.container;

import java.util.HashSet;
import java.util.Set;

import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumn.MmTableColumnJsfTag;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTableColumn contains static configuration information for mimics of type {@link MmTableColumn}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTableColumn extends MmBaseConfiguration {

  /** Constant for configuration of default value of JSF tag in enabled state. Redundant to {@link MmTableColumnAnnotation.jsfTag()}. */
  public static final MmTableColumnJsfTag DEFAULT_JSF_TAG        = MmTableColumnJsfTag.TableColumn;

  /** Constant for configuration of default value of column is row header column. */
  public static final boolean             DEFAULT_IS_ROW_HEADER  = false;

  /** Constant for configuration of default value of column CSS style classes. */
  public static final String              DEFAULT_STYLE_CLASSES  = "";

  /** Constant for configuration of default value of header CSS style classes. */
  public static final String              DEFAULT_HEADER_CLASSES = "";

  /** Constant for configuration of default value of footer CSS style classes. */
  public static final String              DEFAULT_FOOTER_CLASSES = "";

  /** The JSF tag of this mimic. */
  protected MmTableColumnJsfTag           jsfTag;

  /** True, if column is row header column. */
  protected boolean                       isRowHeader;

  /** The columns CSS style classes. */
  protected String                        styleClasses;

  /** The header CSS style classes. */
  protected String                        headerClasses;

  /** The footer CSS style classes. */
  protected String                        footerClasses;

  /**
   * Creates a new MmConfigurationTableColumn instance of default values.
   */
  public MmConfigurationTableColumn() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    jsfTag        = DEFAULT_JSF_TAG;
    isRowHeader   = DEFAULT_IS_ROW_HEADER;
    styleClasses  = DEFAULT_STYLE_CLASSES;
    headerClasses = DEFAULT_HEADER_CLASSES;
    footerClasses = DEFAULT_FOOTER_CLASSES;
  }

  /**
   * Creates a new MmConfigurationTableColumn instance from annotation.
   *
   * @param  pTableColumnAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTableColumn(MmTableColumnAnnotation pTableColumnAnnotation) {
    super(pTableColumnAnnotation.id(), pTableColumnAnnotation.visible(), pTableColumnAnnotation.readOnly(),
      pTableColumnAnnotation.enabled());
    jsfTag        = pTableColumnAnnotation.jsfTag();
    isRowHeader   = pTableColumnAnnotation.isRowHeader();
    styleClasses  = pTableColumnAnnotation.styleClasses();
    headerClasses = pTableColumnAnnotation.headerClasses();
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
   * Adds a CSS style class for header of this table column.
   *
   * @param  pHeaderClass  The specified CSS style class to add.
   */
  public void addHeaderClass(String pHeaderClass) {
    Set<String> tempHeaderClasses = new HashSet<String>();
    for (String styleClass : headerClasses.split(" ")) {
      tempHeaderClasses.add(styleClass);
    }

    tempHeaderClasses.add(pHeaderClass);

    String newHeaderClasses = "";
    for (String styleClass : tempHeaderClasses) {
      newHeaderClasses = newHeaderClasses + " " + styleClass;
    }
    headerClasses = newHeaderClasses;
  }

  /**
   * Delete all configured footer CSS style classes.
   */
  public void clearFooterClasses() {
    footerClasses = "";
  }

  /**
   * Delete all configured header CSS style classes.
   */
  public void clearHeaderClasses() {
    headerClasses = "";
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
   * Returns all configured header CSS style classes.
   *
   * @return  All configured header CSS style classes.
   */
  public String getHeaderClasses() {
    return headerClasses;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return jsfTag.name();
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   */
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
