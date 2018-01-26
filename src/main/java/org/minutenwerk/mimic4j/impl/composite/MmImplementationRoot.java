package org.minutenwerk.mimic4j.impl.composite;

import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.composite.MmRootAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.provided.MmMessageSource;
import org.minutenwerk.mimic4j.impl.provided.MmSessionContext;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeComposite;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * MmImplementationRoot is the specific class for the implementation part of root mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationRoot extends MmBaseCompositeImplementation<MmRoot, MmConfigurationRoot> {

  /** Constant for default root locale in case of no session context. */
  public static final Locale  NO_SESSION_CONTEXT_LOCALE = Locale.GERMAN;

  /** The logger of this class. */
  private static final Logger LOGGER                    = LogManager.getLogger(MmImplementationRoot.class);

  /** The message source. */
  protected MmMessageSource   messageSource;

  /** The user's session context. */
  protected MmSessionContext  sessionContext;

  /**
   * Creates a new MmImplementationRoot instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationRoot(MmDeclarationMimic pParent) {
    super(pParent);
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
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments) {
    this.ensureInitialization();

    String returnString = "";
    if (this.messageSource != null) {
      returnString = this.messageSource.getMmI18nText(this.getMmLocale(), pMessageId, pMessageType, pArguments);
    } else {
      LOGGER.warn("getMmI18nText: " + pMessageId + ", " + pMessageType + ": no message source");

      // for unit tests this root returns last part of message id
      if (pMessageType.equals(MmMessageType.SHORT)) {
        returnString = pMessageId;
        if (returnString.contains(".")) {
          returnString = returnString.substring(returnString.lastIndexOf(".") + 1);
        }
      }
    }
    return returnString;
  }

  /**
   * Returns the {@link Locale} of this root.
   *
   * @return  The locale of this root.
   */
  public Locale getMmLocale() {
    this.ensureInitialization();

    if (this.sessionContext != null) {
      return this.sessionContext.getMmLocale();
    } else {
      LOGGER.warn("getMmLocale: no session context");
      return NO_SESSION_CONTEXT_LOCALE;
    }
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  @Override public boolean isMmJsEnabled() {
    this.ensureInitialization();

    return this.sessionContext.isMmJsEnabled();
  }

  /**
   * Sets the {@link MmMessageSource} of this root, which provides internationalized messages.
   *
   * @param  pMessageSource  The message source to be set.
   */
  public void setMessageSource(MmMessageSource pMessageSource) {
    this.messageSource = pMessageSource;
  }

  /**
   * Sets the {@link MmSessionContext} of this root, which provides information about the user's session.
   *
   * @param  pSessionContext  The session context to be set.
   */
  public void setSessionContext(MmSessionContext pSessionContext) {
    this.sessionContext = pSessionContext;
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeComposite(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmRootAnnotation.class);

    MmRootAnnotation annotation = this.findAnnotation(this.declaration, MmRootAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationRoot();
    } else {
      this.configuration = new MmConfigurationRoot(annotation);
    }
  }

}
