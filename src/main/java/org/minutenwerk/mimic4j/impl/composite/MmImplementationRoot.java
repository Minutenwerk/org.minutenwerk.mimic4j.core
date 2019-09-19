package org.minutenwerk.mimic4j.impl.composite;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.composite.MmRootAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.provided.MmSessionContext;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeComposite;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * MmImplementationRoot is the specific class for the implementation part of root mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationRoot extends MmBaseCompositeImplementation<MmRoot, MmConfigurationRoot, MmRootAnnotation> {

  /** Constant for default root locale in case of no session context. */
  public static final Locale  NO_SESSION_CONTEXT_LOCALE = Locale.GERMAN;

  /** The logger of this class. */
  private static final Logger LOGGER                    = LogManager.getLogger(MmImplementationRoot.class);

  /** Spring message provider with support for parameters and i18n. */
  protected MessageSource     messageSource;

  /** The user's session context. */
  protected MmSessionContext  sessionContext;

  /** TODOC. */
  protected Locale            locale;

  /**
   * Creates a new MmImplementationRoot instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationRoot(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Creates a new MmImplementationRoot instance.
   *
   * @param  pParent         The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pMessageSource  Spring message provider with support for parameters and i18n.
   */
  public MmImplementationRoot(MmDeclarationMimic pParent, MessageSource pMessageSource) {
    super(pParent);
    messageSource = pMessageSource;
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
    assureInitialization();

    if (messageSource != null) {
      String messageCode = pMessageId;
      if ((pMessageType != null) && (pMessageType != MmMessageType.TEXT)) {
        messageCode = messageCode + "." + pMessageType.getSuffix();
      }
      try {
        return messageSource.getMessage(messageCode, pArguments, getMmLocale());
      } catch (NoSuchMessageException e) {
        LOGGER.warn("no message for >" + messageCode + "< for locale " + getMmLocale());
        return messageCode;
      }

    } else {
      LOGGER.warn("getMmI18nText: {}, {}: no message source", pMessageId, pMessageType);

      // for unit tests this root returns last part of message id
      String returnString = pMessageId;
      if (returnString.contains(".")) {
        returnString = returnString.substring(returnString.lastIndexOf(".") + 1);
      }
      return returnString;
    }
  }

  /**
   * Returns the {@link Locale} of this root.
   *
   * @return  The locale of this root.
   */
  public Locale getMmLocale() {
    assureInitialization();

    if (locale == null) {
      LOGGER.warn("getMmLocale: undefined locale is set to " + NO_SESSION_CONTEXT_LOCALE);
      locale = NO_SESSION_CONTEXT_LOCALE;
    }
    return locale;
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  @Override
  public boolean isMmJsEnabled() {
    assureInitialization();

    return sessionContext.isMmJsEnabled();
  }

  /**
   * Returns true, if this mimic is the root mimic.
   *
   * @return  True, if this mimic is the root mimic.
   */
  @Override
  public final boolean isMmThisTheRoot() {
    return true;
  }

  /**
   * Set specified locale.
   *
   * @param  pLocale  The specified locale.
   */
  public void setMmLocale(Locale pLocale) {
    assureInitialization();

    locale = pLocale;
  }

  /**
   * Sets the {@link MmSessionContext} of this root, which provides information about the user's session.
   *
   * @param  pSessionContext  The session context to be set.
   */
  public void setSessionContext(MmSessionContext pSessionContext) {
    sessionContext = pSessionContext;
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationRoot onConstructConfiguration(MmRootAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationRoot(pAnnotation);
    } else {
      return new MmConfigurationRoot();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeComposite(this);
  }

}
