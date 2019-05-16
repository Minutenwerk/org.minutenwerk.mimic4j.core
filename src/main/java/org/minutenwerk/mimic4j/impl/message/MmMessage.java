package org.minutenwerk.mimic4j.impl.message;

import java.text.MessageFormat;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;

/**
 * MmMessage is the base class for messages from the application to its users. Messages have a severity level and a message text. The
 * message text can be i18ned.
 *
 * @author  Olaf Kossak
 */
public class MmMessage {

  /** The type indicates whether an error can be viewed as technical error or an error in execution of business logic. */
  protected final MmErrorMessageType errorMessageType;

  /** The severity indicates whether it is an information, a warning or an error message. */
  protected final MmMessageSeverity  severity;

  /** The mimic which this message relates to. */
  protected final MmMimic            ownerMm;

  /** The id of message text. */
  protected final String             messageId;

  /** The specified message type. */
  protected final MmMessageType      messageType;

  /** An array of arguments to be inserted into message text. */
  protected final Object[]           args;

  /**
   * Creates a new MmMessage instance from MmValidatorException.
   *
   * @param  pValidatorException  The converter exception to create a message from.
   */
  public MmMessage(MmValidatorException pValidatorException) {
    assert pValidatorException != null : "pException must be defined";
    errorMessageType = MmErrorMessageType.BUSINESS_LOGIC_ERROR;
    severity         = MmMessageSeverity.USER_ERROR;
    ownerMm          = pValidatorException.getMimic();
    messageId        = ownerMm.getMmId();
    messageType      = MmMessageType.ERROR_VALIDATION;
    args             = pValidatorException.getArgs();
  }

  /**
   * Creates a new MmMessage instance from MmViewsideConverterException.
   *
   * @param  pViewsideConverterException  The converter exception to create a message from.
   */
  public MmMessage(MmViewsideConverterException pViewsideConverterException) {
    assert pViewsideConverterException != null : "pException must be defined";
    errorMessageType = MmErrorMessageType.BUSINESS_LOGIC_ERROR;
    severity         = MmMessageSeverity.USER_ERROR;
    ownerMm          = pViewsideConverterException.getMimic();
    messageId        = ownerMm.getMmId();
    messageType      = MmMessageType.ERROR_CONVERSION_VIEW;
    args             = pViewsideConverterException.getArgs();
  }

  /**
   * Creates a new instance of MmMessage.
   *
   * @param  pType         The type indicates whether an error can be viewed as technical error or an error in execution of business logic.
   * @param  pSeverity     The severity indicates whether it is an information, a warning or an error message.
   * @param  pOwnerMm      The mimic which this message relates to.
   * @param  pMessageId    The message id.
   * @param  pMessageType  The message type.
   * @param  pArgs         An array of arguments to be inserted into message text.
   */
  public MmMessage(MmErrorMessageType pType, MmMessageSeverity pSeverity, MmMimic pOwnerMm, String pMessageId, MmMessageType pMessageType,
    Object... pArgs) {
    assert pType != null : "pType must be defined";
    assert pSeverity != null : "pSeverity must be defined";
    assert pMessageId != null : "pMessageId must be defined";
    assert pOwnerMm != null : "pOwnerMm must be defined";
    assert MmRelationshipApi.getMmRoot(pOwnerMm) != null : "MmRelationshipApi.getMmRoot(pOwnerMm) must be defined";
    errorMessageType = pType;
    severity         = pSeverity;
    ownerMm          = pOwnerMm;
    messageId        = pMessageId;
    messageType      = pMessageType;
    args             = pArgs;
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
   * Returns the type of this message, which indicates whether an error can be viewed as technical error or an error in execution of
   * business logic.
   *
   * @return  The type of this message.
   */
  public MmErrorMessageType getErrorMessageType() {
    return errorMessageType;
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
      case USER_ERROR:
      case SYSTEM_ERROR: {
        return "alert-danger";
      }
      case WARNING: {
        return "alert-warning";
      }
      case INFO: {
        return "alert-info";
      }
      case SUCCESS: {
        return "alert-success";
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
    MmRoot mmRoot        = MmRelationshipApi.getMmRoot(ownerMm);
    String returnMessage = mmRoot.getMmI18nText(messageId, messageType);
    String label         = ownerMm.getMmShortDescription();
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
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
   *
   * @return  Some information about this object for development purposes like debugging and logging.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(errorMessageType.name());
    sb.append("/ ");
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
