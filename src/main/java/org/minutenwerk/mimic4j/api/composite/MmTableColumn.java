package org.minutenwerk.mimic4j.api.composite;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.composite.MmBaseCompositeDeclaration;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationTableColumn;

/**
 * MmTableColumn is a composite mimic to represent a table column.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmTableColumn extends MmBaseCompositeDeclaration<MmImplementationTableColumn> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/composite/MmTableColumn.java\$
   */
  public enum MmTableColumnJsfTag {

    TableColumn;
  }

  /**
   * Creates a new MmTableColumn instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmTableColumn(MmDeclarationMimic pParent) {
    super(new MmImplementationTableColumn(pParent));
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @since   $maven.project.version$
   */
  public final String getMmFooterClasses() {
    return this.implementation.getMmFooterClasses();
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the header of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the header of this column.
   *
   * @since   $maven.project.version$
   */
  public final String getMmHeaderClasses() {
    return this.implementation.getMmHeaderClasses();
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   *
   * @since   $maven.project.version$
   */
  public final boolean isMmRowHeader() {
    return this.implementation.isMmRowHeader();
  }

}
