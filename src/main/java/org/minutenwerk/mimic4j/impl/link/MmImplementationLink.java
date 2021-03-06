package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.link.MmLink;
import org.minutenwerk.mimic4j.api.link.MmLinkAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;

/**
 * MmImplementationLink is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text label of the
 * link. In most cases the two models are the same.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization
 */
public class MmImplementationLink<DATA_MODEL extends MmReferenceableModel, VIEW_MODEL>
  extends MmBaseLinkImplementation<MmLink<DATA_MODEL, VIEW_MODEL>, DATA_MODEL, VIEW_MODEL, MmConfigurationLink, MmLinkAnnotation> {

  /**
   * Creates a new MmImplementationLink instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLink(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param         pAnnotation  The specified annotation, may be null.
   *
   * @return        Configuration of this mimic.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected MmConfigurationLink onConstructConfiguration(MmLinkAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLink(pAnnotation);
    } else {
      return new MmConfigurationLink();
    }
  }

}
