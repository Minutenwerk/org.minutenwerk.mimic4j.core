package org.minutenwerk.mimic4j.impl.composite;

import java.util.HashSet;
import java.util.Set;

import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn.MmTableColumnJsfTag;
import org.minutenwerk.mimic4j.api.composite.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTableColumn contains static configuration information for mimics of type {@link MmTableColumn}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationTableColumn extends MmBaseConfiguration {

  /** Constant for configuration of default value of JSF tag in enabled state. Redundant to {@link MmTableColumnAnnotation.jsfTag()}. */
  public static final MmTableColumnJsfTag DEFAULT_JSF_TAG        = MmTableColumnJsfTag.TableColumn;

  /** Constant for configuration of default value of column is row header column. */
  public static final boolean             DEFAULT_IS_ROW_HEADER  = false;

  /** Constant for configuration of default value of column CSS style classes. */
  public static final String              DEFAULT_STYLE_CLASSES  = "";

  /** Constant for configuration of default value of header CSS style classes. */
  public static final String              DEFAULT_HEADER_CLASSES = "table-header";

  /** Constant for configuration of default value of footer CSS style classes. */
  public static final String              DEFAULT_FOOTER_CLASSES = "table-footer";

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
    this.jsfTag        = DEFAULT_JSF_TAG;
    this.isRowHeader   = DEFAULT_IS_ROW_HEADER;
    this.styleClasses  = DEFAULT_STYLE_CLASSES;
    this.headerClasses = DEFAULT_HEADER_CLASSES;
    this.footerClasses = DEFAULT_FOOTER_CLASSES;
  }

  /**
   * Creates a new MmConfigurationTableColumn instance from annotation.
   *
   * @param  pTableColumnAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTableColumn(MmTableColumnAnnotation pTableColumnAnnotation) {
    super(pTableColumnAnnotation.id(), pTableColumnAnnotation.visible(), pTableColumnAnnotation.readOnly(),
      pTableColumnAnnotation.enabled());
    this.jsfTag        = pTableColumnAnnotation.jsfTag();
    this.isRowHeader   = pTableColumnAnnotation.isRowHeader();
    this.styleClasses  = pTableColumnAnnotation.styleClasses();
    this.headerClasses = pTableColumnAnnotation.headerClasses();
    this.footerClasses = pTableColumnAnnotation.footerClasses();
  }

  /**
   * Adds a CSS style class for footer of this table column.
   *
   * @param  pFooterClass  The specified CSS style class to add.
   *
   * @since  $maven.project.version$
   */
  public void addFooterClass(String pFooterClass) {
    Set<String> tempFooterClasses = new HashSet<String>();
    for (String styleClass : this.footerClasses.split(" ")) {
      tempFooterClasses.add(styleClass);
    }

    tempFooterClasses.add(pFooterClass);

    String newFooterClasses = "";
    for (String styleClass : tempFooterClasses) {
      newFooterClasses = newFooterClasses + " " + styleClass;
    }
    this.footerClasses = newFooterClasses;
  }

  /**
   * Adds a CSS style class for header of this table column.
   *
   * @param  pHeaderClass  The specified CSS style class to add.
   *
   * @since  $maven.project.version$
   */
  public void addHeaderClass(String pHeaderClass) {
    Set<String> tempHeaderClasses = new HashSet<String>();
    for (String styleClass : this.headerClasses.split(" ")) {
      tempHeaderClasses.add(styleClass);
    }

    tempHeaderClasses.add(pHeaderClass);

    String newHeaderClasses = "";
    for (String styleClass : tempHeaderClasses) {
      newHeaderClasses = newHeaderClasses + " " + styleClass;
    }
    this.headerClasses = newHeaderClasses;
  }

  /**
   * Delete all configured footer CSS style classes.
   *
   * @since  $maven.project.version$
   */
  public void clearFooterClasses() {
    this.footerClasses = "";
  }

  /**
   * Delete all configured header CSS style classes.
   *
   * @since  $maven.project.version$
   */
  public void clearHeaderClasses() {
    this.headerClasses = "";
  }

  /**
   * Returns all configured footer CSS style classes.
   *
   * @return  All configured footer CSS style classes.
   *
   * @since   $maven.project.version$
   */
  public String getFooterClasses() {
    return this.footerClasses;
  }

  /**
   * Returns all configured header CSS style classes.
   *
   * @return  All configured header CSS style classes.
   *
   * @since   $maven.project.version$
   */
  public String getHeaderClasses() {
    return this.headerClasses;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   *
   * @since   $maven.project.version$
   */
  public String getStyleClasses() {
    return this.styleClasses;
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   *
   * @since   $maven.project.version$
   */
  public boolean isRowHeader() {
    return this.isRowHeader;
  }

  /**
   * Sets the configuration whether this row is a table row header.
   *
   * @param  pIsRowHeader  The specified configuration whether this row is a table row header.
   *
   * @since  $maven.project.version$
   */
  public void setRowHeader(boolean pIsRowHeader) {
    this.isRowHeader = pIsRowHeader;
  }

}
