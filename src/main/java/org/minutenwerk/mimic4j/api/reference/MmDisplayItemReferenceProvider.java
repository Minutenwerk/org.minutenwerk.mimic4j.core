package org.minutenwerk.mimic4j.api.reference;

/**
 * Returns reference path for HTTP-GET of display item view.
 */
public interface MmDisplayItemReferenceProvider extends MmDisplayReferenceProvider {

  /**
   * Returns reference path for HTTP-GET of display item view.
   *
   * @return  reference path for HTTP-GET of display item view.
   */
  @Override
  public MmReferencePath.DisplayItem provideDisplayReference();

}
