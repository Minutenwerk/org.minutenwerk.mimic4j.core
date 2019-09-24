package org.minutenwerk.mimic4j.api.reference;

/**
 * Returns reference path for HTTP-GET of display view.
 */
public interface MmDisplayReferenceProvider {

  /**
   * Returns reference path for HTTP-GET of display view.
   *
   * @return  reference path for HTTP-GET of display view.
   */
  public MmReferencePath.Display provideDisplayReference();

}
