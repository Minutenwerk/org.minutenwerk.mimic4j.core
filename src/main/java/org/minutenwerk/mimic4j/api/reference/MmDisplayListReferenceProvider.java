package org.minutenwerk.mimic4j.api.reference;

/**
 * Returns reference path for HTTP-GET of display list view.
 */
public interface MmDisplayListReferenceProvider extends MmDisplayReferenceProvider {

  /**
   * Returns reference path for HTTP-GET of display list view.
   *
   * @return  reference path for HTTP-GET of display list view.
   */
  @Override
  public MmReferencePath.DisplayList provideDisplayReference();

}
