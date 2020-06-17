package org.minutenwerk.mimic4j.api.message;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmPageMimic;
import org.minutenwerk.mimic4j.api.mimic.MmRelationshipApi;

/**
 * MmMessage is the base class for messages from the application to its users. Messages have a severity level and a message text. The message text can be
 * i18ned.
 *
 * @author  Olaf Kossak
 */
public class MmMessage {

  /** Logger of this class. */
  private static final Logger       LOGGER      = LogManager.getLogger(MmMessage.class);

  /** The severity indicates whether it is an information, a warning or an error message. */
  protected final MmMessageSeverity severity;

  /** The mimic which this message relates to. */
  protected final MmMimic           ownerMm;

  /** The id of message text. */
  protected final String            messageId;

  /** The specified message type. */
  protected final MmMessageType     messageType;

  /** An array of arguments to be inserted into message text. */
  protected final Object[]          args;

  /**
   * Creates a new MmMessage instance from MmValidatorException.
   *
   * @param   pValidatorException  The converter exception to create a message from.
   *
   * @throws  IllegalArgumentException  In case of argument pValidatorException is null.
   */
  public MmMessage(MmValidatorException pValidatorException) {
    if (LOGGER.isDebugEnabled()) {
      if (pValidatorException == null) {
        throw new IllegalArgumentException("pException must be defined");
      }
    }
    severity    = MmMessageSeverity.USER_ERROR;
    ownerMm     = pValidatorException.getMimic();
    messageId   = ownerMm.getMmId();
    messageType = MmMessageType.ERROR_VALIDATION;
    args        = pValidatorException.getArgs();
  }

  /**
   * Creates a new MmMessage instance from MmViewModelConverterException.
   *
   * @param   pViewModelConverterException  The converter exception to create a message from.
   *
   * @throws  IllegalArgumentException  In case of argument pViewModelConverterException is null.
   */
  public MmMessage(MmViewModelConverterException pViewModelConverterException) {
    if (LOGGER.isDebugEnabled()) {
      if (pViewModelConverterException == null) {
        throw new IllegalArgumentException("pException must be defined");
      }
    }
    severity    = MmMessageSeverity.USER_ERROR;
    ownerMm     = pViewModelConverterException.getMimic();
    messageId   = ownerMm.getMmId();
    messageType = MmMessageType.ERROR_CONVERSION_VIEW;
    args        = pViewModelConverterException.getArgs();
  }

  /**
   * Creates a new instance of MmMessage.
   *
   * @param   pSeverity     The severity indicates whether it is an information, a warning or an error message.
   * @param   pOwnerMm      The mimic which this message relates to.
   * @param   pMessageId    The message id.
   * @param   pMessageType  The message type.
   * @param   pArgs         An array of arguments to be inserted into message text.
   *
   * @throws  IllegalArgumentException  In case of an argument is null or MmRelationshipApi.getMmRoot(pOwnerMm) isn't defined.
   */
  public MmMessage(MmMessageSeverity pSeverity, MmMimic pOwnerMm, String pMessageId, MmMessageType pMessageType, Object... pArgs) {
    if (LOGGER.isDebugEnabled()) {
      if (pSeverity == null) {
        throw new IllegalArgumentException("pSeverity must be defined");
      }
      if (pMessageId == null) {
        throw new IllegalArgumentException("pMessageId must be defined");
      }
      if (pOwnerMm == null) {
        throw new IllegalArgumentException("pOwnerMm must be defined");
      }
      if (MmRelationshipApi.getMmRoot(pOwnerMm) == null) {
        throw new IllegalArgumentException("MmRelationshipApi.getMmRoot(pOwnerMm) must be defined");
      }
    }
    severity    = pSeverity;
    ownerMm     = pOwnerMm;
    messageId   = pMessageId;
    messageType = pMessageType;
    args        = pArgs;
  }

  /**
   * Returns an array of arguments to be inserted into message text.
   *
   * @return  An array of arguments to be inserted into message text.
   */
  public Object[] getArgs() {
    return args;
  }

  /**
   * Returns the bootstrap severity icon.
   *
   * @return  The bootstrap severity icon.
   */
  public String getIcon() {
    switch (severity) {
      case USER_ERROR:
      case SYSTEM_ERROR: {
        return "fa fa-flash";
      }
      case WARNING: {
        return "fa fa-exclamation";
      }
      case INFO: {
        return "fa fa-info";
      }
      case SUCCESS: {
        return "fa fa-check";
      }
    }
    return "";
  }

  /**
   * Returns the id of message text.
   *
   * @return  The id of message text.
   */
  public String getMessageId() {
    return messageId;
  }

  /**
   * Returns the message type.
   *
   * @return  The message type.
   */
  public MmMessageType getMessageType() {
    return messageType;
  }

  /**
   * Returns the the mimic which this message relates to.
   *
   * @return  The mimic which this message relates to.
   */
  public MmMimic getOwnerMm() {
    return ownerMm;
  }

  /**
   * Returns the severity of this message, which indicates whether it is an information, a warning or an error message.
   *
   * @return  The severity of this message.
   */
  public MmMessageSeverity getSeverity() {
    return severity;
  }

  /**
   * Returns the bootstrap alert style class.
   *
   * @return  The bootstrap alert style class.
   */
  public String getStyleClass() {
    switch (severity) {
      case INFO: {
        return "alert-info";
      }
      case SUCCESS: {
        return "alert-success";
      }
      case WARNING: {
        return "alert-warning";
      }
      case USER_ERROR:
      case SYSTEM_ERROR: {
        return "alert-danger";
      }
    }
    return "";
  }

  /**
   * Returns the message text.
   *
   * @return  The message text.
   */
  public String getText() {
    final MmPageMimic<?> pageMimic     = MmRelationshipApi.getMmRoot(ownerMm);
    String               returnMessage = pageMimic.getMmI18nText(messageId, messageType);
    String               label         = ownerMm.getMmShortDescription();
    if (args == null) {
      returnMessage = MessageFormat.format(returnMessage, label);
    } else {
      Object[] arguments = new Object[args.length + 1];
      arguments[0] = label;
      for (int i = 0; i < args.length; i++) {
        arguments[i + 1] = args[i];
      }
      returnMessage = MessageFormat.format(returnMessage, arguments);
    }
    return returnMessage;
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change at any time.
   *
   * @return  Some information about this object for development purposes like debugging and logging.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(severity.name());
    if (ownerMm != null) {
      sb.append("/ ");
      sb.append(ownerMm.getMmName());
    }
    sb.append("/ ");
    sb.append(messageId);
    if ((args != null) && (args.length > 0)) {
      sb.append("/ [ ");
      for (Object object : args) {
        sb.append(object);
        sb.append(" / ");
      }
      sb.append("]");
    }
    return sb.toString();
  }

}
