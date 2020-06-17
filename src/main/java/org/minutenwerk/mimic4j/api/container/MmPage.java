package org.minutenwerk.mimic4j.api.container;

import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.message.MmMessageType;
import org.minutenwerk.mimic4j.api.mimic.MmPageMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferencePathProvider;
import org.minutenwerk.mimic4j.api.site.MmSpringMimicAdapter;
import org.minutenwerk.mimic4j.api.site.MmTheme;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationPage;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLink;

import org.springframework.web.util.UriComponents;

/**
 * MmPage is a container mimic to represent a HTML page containing editable attributes. A page implements {@link MmReferencePathProvider} to provide an unique
 * self reference path. MmPage is abstract as it does not implement method {@link MmReferencePathProvider.getMmSelfReferencePath()}. This method is
 * implemented by concrete subclasses of MmPage by overriding both the static method getMmStaticSelfReferencePath() and getMmSelfReferencePath() like in:
 *
 * <pre>
     public static String getMmStaticSelfReferencePath() {
       return "/company/customer/{id0}";
     }

     @Override
     public final UriComponents getMmSelfReferencePath() {
       return UriComponentsBuilder.fromUriString(getMmStaticSelfReferencePath()).build();
     }
   </pre>
 *
 * <p>Method {@link MmReferencePathProvider.getMmSelfReferencePath()} is used to evaluate the self reference for a page and all its contained mimics. But a
 * page of type MmPage can also be used in link annotations like in {@link MmLinkAnnotation.targetPage} to provide the target URI of a link mimic. As in this
 * case the annotation does not reference a page instance but a page class, the instance method {@link MmReferencePathProvider.getMmSelfReferencePath()} can
 * not be invoked to evaluate the page URI. The solution is implemented in class {@link MmConfigurationLink}, where the URI of a page class is evaluated by
 * means of invocation by name of the static method getMmStaticSelfReferencePath() on a class.</p>
 *
 * <pre>
     String targetReferencePathOneItem = (String)targetPage.getMethod("getMmStaticSelfReferencePath").invoke(null);
   </pre>
 *
 * <p>In fact the static method of the concrete class will be invoked, but not the static method of base class MmPage. This shows that static methods can be
 * overridden as well.</p>
 *
 * @param   <MODEL>  Type of the model, containing business data.
 *
 * @author  Olaf Kossak
 */
public abstract class MmPage<MODEL> extends MmBaseContainerDeclaration<MmImplementationPage<MODEL>, MODEL> implements MmPageMimic<MODEL>,
  MmReferencePathProvider {

  /**
   * Creates a new MmPage instance.
   *
   * @param  pSpringMimicAdapter  The Spring mimic adapter of this page.
   */
  public MmPage(final MmSpringMimicAdapter<?> pSpringMimicAdapter) {
    super(new MmImplementationPage<MODEL>(pSpringMimicAdapter, null, null));
  }

  /**
   * Creates a new MmPage instance.
   *
   * @param  pSpringMimicAdapter  The Spring mimic adapter of this page.
   * @param  pName                Specified name and id of web page.
   */
  public MmPage(final MmSpringMimicAdapter<?> pSpringMimicAdapter, final String pName) {
    super(new MmImplementationPage<MODEL>(pSpringMimicAdapter, pName, null));
  }

  /**
   * Creates a new MmPage instance.
   *
   * @param  pSpringMimicAdapter  The Spring mimic adapter of this page.
   * @param  pRootAccessor        This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                              accessor.
   */
  public MmPage(final MmSpringMimicAdapter<?> pSpringMimicAdapter, final MmRootAccessor<MODEL> pRootAccessor) {
    super(new MmImplementationPage<MODEL>(pSpringMimicAdapter, null, pRootAccessor));
  }

  /**
   * Creates a new MmPage instance.
   *
   * @param  pSpringMimicAdapter  The Spring mimic adapter of this page.
   * @param  pName                Specified name and id of web page.
   * @param  pRootAccessor        This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                              accessor.
   */
  public MmPage(final MmSpringMimicAdapter<?> pSpringMimicAdapter, final String pName, final MmRootAccessor<MODEL> pRootAccessor) {
    super(new MmImplementationPage<MODEL>(pSpringMimicAdapter, pName, pRootAccessor));
  }

  /**
   * Returns self reference path. Attention: static method!
   *
   * @return  Self reference path.
   */
  public static String getMmStaticReferencePathMany() {
    return null;
  }

  /**
   * Returns self reference path. Attention: static method!
   *
   * @return  Self reference path.
   */
  public static String getMmStaticSelfReferencePath() {
    return null;
  }

  /**
   * Returns a list of direct mimic children of this page.
   *
   * @return  a list of direct mimic children of this page.
   */
  @Override
  public final List<MmMimic> getMmElements() {
    return implementation.getMmChildren();
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
  public final String getMmI18nText(final String pMessageId, final MmMessageType pMessageType, final Object... pArguments) {
    return implementation.getMmI18nText(pMessageId, pMessageType, pArguments);
  }

  /**
   * Returns current session locale.
   *
   * @return  current session locale.
   */
  @Override
  public final Locale getMmLocale() {
    return implementation.getMmLocale();
  }

  /**
   * Returns page title.
   *
   * @return  page title.
   */
  @Override
  public final String getMmPageTitle() {
    return implementation.getMmPageTitle();
  }

  /**
   * Returns the path part of a many items URL like "city/{id0}/person/{id1}/friemds" in "city/123/person/{id1}/friends".
   *
   * @return  The path part of a many items URL.
   */
  @Override
  public UriComponents getMmReferencePathMany() {
    return null;
  }

  /**
   * Returns web site of this page.
   *
   * @return  web site of this page.
   */
  @Override
  public final <USER_DETAILS> MmSpringMimicAdapter<USER_DETAILS> getMmSpringMimicAdapter() {
    return implementation.getMmSpringMimicAdapter();
  }

  /**
   * Returns current session theme.
   *
   * @return  current session theme.
   */
  @Override
  public final MmTheme getMmTheme() {
    return implementation.getMmTheme();
  }

  /**
   * Returns true, if the user's browser has disabled Javascript language.
   *
   * @return  true, if the user's browser has disabled Javascript language.
   */
  @Override
  public final boolean isMmUserAgentJavaScriptDisabled() {
    return implementation.isMmUserAgentJavaScriptDisabled();
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  true, if the user's browser has enabled Javascript language.
   */
  @Override
  public final boolean isMmUserAgentJavaScriptEnabled() {
    return implementation.isMmUserAgentJavaScriptEnabled();
  }

  /**
   * Static class for void target page, used as default reference target in mimic annotations.
   */
  public static final class MmVoidTarget extends MmPage<Void> {

    public MmVoidTarget() {
      super(null);
    }

    @Override
    public final UriComponents getMmSelfReferencePath() {
      return null;
    }
  }
}
