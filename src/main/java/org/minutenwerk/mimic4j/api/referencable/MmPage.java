package org.minutenwerk.mimic4j.api.referencable;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.referencable.MmBaseReferencableDeclaration;
import org.minutenwerk.mimic4j.impl.referencable.MmImplementationPage;

/**
 * MmPage is a container mimic to represent a dialog tab.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public abstract class MmPage<MODEL extends MmReferencableModel> extends MmBaseReferencableDeclaration<MODEL, MmImplementationPage<MODEL>> {

  /**
   * Creates a new MmPage instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmPage(MmDeclarationMimic pParent) {
    super(new MmImplementationPage<MODEL>(pParent));
  }

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The file part of the URL without slashes.
   *
   * @since   $maven.project.version$
   */
  @Override public String callbackMmGetReferenceFile(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The path part of the URL including trailing slash but without base part.
   *
   * @since   $maven.project.version$
   */
  @Override public String callbackMmGetReferencePath(String pPassThroughValue) {
    return pPassThroughValue;
  }

}
