package org.minutenwerk.mimic4j.api;

import java.util.List;

/**
 * MmReferencableModel is the basic interface of all model classes being referencable by an url.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableModel {

  /**
   * Returns a list of query parameter values of the URL, like "123", "4711" in "person/wohnort/display.html#plz?id1=123&id2=4711".
   *
   * @return  A list of query parameter values of the URL.
   */
  public List<String> getMmReferenceValues();

}
