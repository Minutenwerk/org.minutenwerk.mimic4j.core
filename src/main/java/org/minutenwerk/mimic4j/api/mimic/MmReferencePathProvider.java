package org.minutenwerk.mimic4j.api.mimic;

import org.springframework.web.util.UriComponents;

/**
 * MmReferencePathProvider provides the path parts of an URL to display one item or many items.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencePathProvider {

  /**
   * Returns the path part of a many items URL like "city/{id0}/person/{id1}/friemds" in "city/123/person/{id1}/friends".
   *
   * @return  The path part of a many items URL.
   */
  public UriComponents getMmReferencePathMany();

  /**
   * Returns the path part of a self reference URL like "city/{id0}/person/{id1}" in "city/123/person/4711".
   *
   * @return  The path part of self reference URL.
   */
  public UriComponents getMmSelfReferencePath();

}
