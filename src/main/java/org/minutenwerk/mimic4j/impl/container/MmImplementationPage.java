package org.minutenwerk.mimic4j.impl.container;

import java.util.Locale;

import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.container.MmPageAnnotation;
import org.minutenwerk.mimic4j.api.message.MmMessageType;
import org.minutenwerk.mimic4j.api.mimic.MmPageMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferencePathProvider;
import org.minutenwerk.mimic4j.api.site.MmSpringMimicAdapter;
import org.minutenwerk.mimic4j.api.site.MmTheme;

import org.springframework.web.util.UriComponents;

/**
 * MmImplementationPage is the specific class for the implementation part of page mimics.
 *
 * @param   <MODEL>  Type of the model, containing business data.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationPage<MODEL> extends MmBaseContainerImplementation<MmPage<MODEL>, MODEL, MmConfigurationPage, MmPageAnnotation>
  implements MmPageMimic<MODEL>, MmReferencePathProvider {

  /** The Spring mimic adapter of this page. */
  private final MmSpringMimicAdapter<?> springMimicAdapter;

  /**
   * Creates a new MmImplementationPage instance.
   *
   * @param  pSpringMimicAdapter  The Spring mimic adapter of this page.
   * @param  pName                Specified name and id of web page.
   * @param  pRootAccessor        This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                              accessor.
   */
  public MmImplementationPage(final MmSpringMimicAdapter<?> pSpringMimicAdapter, final String pName, final MmRootAccessor<MODEL> pRootAccessor) {
    super(NULL_PARENT, pName, pRootAccessor);
    springMimicAdapter = pSpringMimicAdapter;
  }

  /**
   * Returns an internationalized version for a specified message id and type.
   *
   * @param   pMessageId    The specified id of the message to be internationalized.
   * @param   pMessageType  The specified type of the message to be internationalized.
   * @param   pArguments    Optional list of message arguments.
   *
   * @return  The internationalized message.
   */
  @Override
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments) {
    ensureInitialization();

    return springMimicAdapter.getMmI18nText(pMessageId, pMessageType, pArguments);
  }

  /**
   * Returns current session locale.
   *
   * @return  current session locale.
   */
  @Override
  public Locale getMmLocale() {
    ensureInitialization();

    return springMimicAdapter.getMmActiveSession().getMmLocale();
  }

  /**
   * Returns page title.
   *
   * @return  page title.
   */
  @Override
  public String getMmPageTitle() {
    ensureInitialization();

    return getMmShortDescription();
  }

  /**
   * Returns the path part of the URL like "city/{id0}/person/display" in "city/123/person/display".
   *
   * @return  The path part of the URL.
   */
  @Override
  public UriComponents getMmReferencePathMany() {
    return declaration.getMmReferencePathMany();
  }

  /**
   * Returns the path part of the URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @return  The path part of the URL.
   */
  @Override
  public UriComponents getMmSelfReferencePath() {
    return declaration.getMmSelfReferencePath();
  }

  /**
   * Returns Spring mimic adapter of this page.
   *
   * @return  Spring mimic adapter of this page.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <USER_DETAILS> MmSpringMimicAdapter<USER_DETAILS> getMmSpringMimicAdapter() {
    ensureInitialization();

    return (MmSpringMimicAdapter<USER_DETAILS>)springMimicAdapter;
  }

  /**
   * Returns current session theme.
   *
   * @return  current session theme.
   */
  @Override
  public MmTheme getMmTheme() {
    ensureInitialization();

    return springMimicAdapter.getMmActiveSession().getMmTheme();
  }

  /**
   * Returns true, if the user's browser has disabled Javascript language.
   *
   * @return  true, if the user's browser has disabled Javascript language.
   */
  @Override
  public final boolean isMmUserAgentJavaScriptDisabled() {
    return !isMmUserAgentJavaScriptEnabled();
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  true, if the user's browser has enabled Javascript language.
   */
  @Override
  public boolean isMmUserAgentJavaScriptEnabled() {
    ensureInitialization();

    return springMimicAdapter.getMmActiveSession().isMmUserAgentJavaScriptEnabled();
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
