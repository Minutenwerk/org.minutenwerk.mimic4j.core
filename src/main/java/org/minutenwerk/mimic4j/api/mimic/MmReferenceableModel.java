package org.minutenwerk.mimic4j.api.mimic;

import java.util.List;

import org.springframework.web.util.UriComponents;

/**
 * MmReferenceableModel is a model being referenceable by an url.
 *
 * @author  Olaf Kossak
 */
public interface MmReferenceableModel {

  /**
   * Returns a list of URL parameter values for a specified target reference path, like {"123", "4711" } for "city/123/person/4711/display". Usually this is a
   * list of ids starting by id of root dto.
   *
   * @param   pTargetReferencePath  Specified target reference path.
   *
   * @return  A list of URL parameter values for a specified target reference path.
   */
  public List<String> getMmReferenceParams(UriComponents pTargetReferencePath);

}
