package org.minutenwerk.mimic4j.api.composite;

import java.util.Locale;

import org.minutenwerk.mimic4j.impl.composite.MmBaseCompositeDeclaration;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationRoot;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.provided.MmMessageSource;
import org.minutenwerk.mimic4j.impl.provided.MmSessionContext;

/**
 * MmRoot is a composite mimic for the root of a subtree of mimics.
 *
 * @author  Olaf Kossak
 */
public class MmRoot extends MmBaseCompositeDeclaration<MmImplementationRoot> {

  /**
   * Creates a new MmRoot instance.
   */
  public MmRoot() {
    super(new MmImplementationRoot(null));
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
    return implementation.getMmI18nText(pMessageId, pMessageType, pArguments);
  }

  /**
   * Returns the {@link Locale} of this root.
   *
   * @return  The locale of this root.
   */
  public Locale getMmLocale() {
    return implementation.getMmLocale();
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  public boolean isMmJsEnabled() {
    return implementation.isMmJsEnabled();
  }

  /**
   * Sets the {@link MmMessageSource} of this root, which provides internationalized messages.
   *
   * @param  pMessageSource  The message source to be set.
   */
  public void setMessageSource(MmMessageSource pMessageSource) {
    implementation.setMessageSource(pMessageSource);
  }

  /**
   * Sets the {@link MmSessionContext} of this root, which provides information about the user's session.
   *
   * @param  pSessionContext  The session context to be set.
   */
  public void setSessionContext(MmSessionContext pSessionContext) {
    implementation.setSessionContext(pSessionContext);
  }

}
