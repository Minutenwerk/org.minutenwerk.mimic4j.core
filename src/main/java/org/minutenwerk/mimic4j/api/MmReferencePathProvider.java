package org.minutenwerk.mimic4j.api;

import org.springframework.web.util.UriComponents;

/**
 * MmReferencePathProvider provides the path part of an URL.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencePathProvider {

  /**
   * Returns the path part of the URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @return  The path part of the URL.
   */
  public UriComponents getMmReferencePath();

}
