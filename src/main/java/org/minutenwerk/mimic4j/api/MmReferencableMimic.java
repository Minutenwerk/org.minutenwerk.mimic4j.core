package org.minutenwerk.mimic4j.api;

import org.springframework.web.util.UriComponents;

/**
 * MmReferencableMimic is a MmContainerMimic (containing other mimics of type MmEditableMimic) being referencable by an url.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableMimic<MODEL extends MmReferencableModel> extends MmContainerMimic<MODEL> {

  /**
   * Returns the path part of the URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @return  The path part of the URL.
   */
  public UriComponents getMmReferencePath();

}
