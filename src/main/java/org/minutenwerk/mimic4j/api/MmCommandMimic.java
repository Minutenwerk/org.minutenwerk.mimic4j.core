package org.minutenwerk.mimic4j.api;

/**
 * MmCommandMimic defines a mimic which controls execution of actions.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts and view text label of link.
 *
 * @author  Olaf Kossak
 */
public interface MmCommandMimic<DATA_MODEL extends MmReferencableModel> extends MmLinkMimic<DATA_MODEL, DATA_MODEL> {

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  public String getMmSubmitParam();

}
