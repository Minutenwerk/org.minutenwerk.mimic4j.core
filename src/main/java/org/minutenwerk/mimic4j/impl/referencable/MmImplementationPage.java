package org.minutenwerk.mimic4j.impl.referencable;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.referencable.MmPage;
import org.minutenwerk.mimic4j.api.referencable.MmPageAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgePage;

/**
 * MmImplementationPage is the specific class for the implementation part of tab mimics.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-override
 */
public class MmImplementationPage<MODEL extends MmReferencableModel>
  extends MmBaseReferencableImplementation<MmPage<MODEL>, MODEL, MmConfigurationPage, MmPageAnnotation> {

  /**
   * Creates a new MmImplementationPage instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationPage(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return        The file part of the URL without slashes.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmReferenceFile() {
    assureInitialization();

    final String configurationReferenceFile = configuration.getReferenceFile();
    return declaration.callbackMmGetReferenceFile(configurationReferenceFile);
  }

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return        The path part of the URL including trailing slash but without base part.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmReferencePath() {
    assureInitialization();

    final String configurationReferencePath = configuration.getReferencePath();
    return declaration.callbackMmGetReferencePath(configurationReferencePath);
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgePage<MODEL>(this);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationPage onConstructConfiguration(MmPageAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationPage(pAnnotation);
    } else {
      return new MmConfigurationPage();
    }
  }

}
