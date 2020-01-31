package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLink;

/**
 * MmLink is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text label of the link. In most
 * cases the two models are the same.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param   <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author  Olaf Kossak
 */
public class MmLink<DATA_MODEL extends MmReferencableModel, VIEW_MODEL>
  extends MmBaseLinkDeclaration<MmImplementationLink<DATA_MODEL, VIEW_MODEL>, DATA_MODEL, VIEW_MODEL> {

  /**
   * Creates a new MmLink instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmLink(MmDeclarationMimic pParent) {
    super(new MmImplementationLink<>(pParent));
  }

}
