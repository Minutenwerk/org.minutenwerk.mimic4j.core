package org.minutenwerk.mimic4j.api;

/**
 * MmReferencableMimic is a MmContainerMimic (containing other mimics of type MmEditableMimic) being referencable by an url.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableMimic<MODEL extends MmReferencableModel> extends MmContainerMimic<MODEL> {

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The file part of the URL without slashes.
   */
  public String getMmReferenceFile();

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The path part of the URL including trailing slash but without base part.
   */
  public String getMmReferencePath();

}
