package org.minutenwerk.mimic4j.api.reference;

import java.util.List;

/**
 * MmReferencableModel is a model being referencable by an url.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableModel {

  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  public List<String> getMmReferenceValues();

}
