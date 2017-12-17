package org.minutenwerk.mimic4j.api.container;

/**
 * MmRowIdentifiable defines the interface of a table row which delivers information about its database record id and version, and table row
 * index.
 *
 * @author  Olaf Kossak
 */
public interface MmRowIdentifiable {

  /**
   * Returns the database id of record.
   *
   * @return  The database id of record.
   */
  public String getId();

  /**
   * Returns the table row index.
   *
   * @return  The table row index.
   */
  public int getRowIndex();

  /**
   * Returns the database version of data record (for optimistic locking).
   *
   * @return  The database version of data record.
   */
  public Long getVersion();

}
