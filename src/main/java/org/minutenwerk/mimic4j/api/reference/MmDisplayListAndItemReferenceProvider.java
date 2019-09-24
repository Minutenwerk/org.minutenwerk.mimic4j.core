package org.minutenwerk.mimic4j.api.reference;

/**
 * Returns reference path for HTTP-GET of display list view and display item view.
 */
public interface MmDisplayListAndItemReferenceProvider extends MmDisplayItemReferenceProvider {

  /**
   * Returns reference path for HTTP-GET of display list view.
   *
   * @return  reference path for HTTP-GET of display list view.
   */
  public MmReferencePath.DisplayList provideDisplayListReference();

}
