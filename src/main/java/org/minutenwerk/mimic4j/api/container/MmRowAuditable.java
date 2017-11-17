package org.minutenwerk.mimic4j.api.container;

/**
 * MmRowAuditable defines the interface of a table row which delivers information about date and user of creation and last update of data.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmRowAuditable extends MmRowIdentifiable {

  /**
   * Returns the name of the user who created the data.
   *
   * @return  The name of the user who created the data.
   *
   * @since   $maven.project.version$
   */
  public String getCreatedBy();

  /**
   * Returns the formatted date and time, when the data has been created.
   *
   * @return  The formatted date and time, when the data has been created.
   *
   * @since   $maven.project.version$
   */
  public String getCreatedDate();

  /**
   * Returns the name of the user who made the last change of data.
   *
   * @return  The name of the user who made the last change of data.
   *
   * @since   $maven.project.version$
   */
  public String getLastUpdated();

  /**
   * Returns the formatted date and time of the last change of data.
   *
   * @return  The formatted date and time of the last change of data.
   *
   * @since   $maven.project.version$
   */
  public String getLastUpdatedBy();

}
