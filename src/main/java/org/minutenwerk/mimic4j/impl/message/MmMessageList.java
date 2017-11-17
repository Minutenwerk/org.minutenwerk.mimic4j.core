package org.minutenwerk.mimic4j.impl.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * MmMessageList is a list of messages of type {@link MmMessage}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmMessageList {

  /** The list of messages. */
  protected final List<MmMessage>   messages;

  /** The comparator of messages. */
  protected final MessageComparator comparator;

  /**
   * Creates a new MmMessageList instance.
   */
  public MmMessageList() {
    this.messages   = new ArrayList<MmMessage>();
    this.comparator = new MessageComparator();
  }

  /**
   * Adds a message to this list of messages, and returns the message list.
   *
   * @param   mmMessage  The message to be added.
   *
   * @return  The message list after adding the message.
   *
   * @since   $maven.project.version$
   */
  public MmMessageList addMessage(MmMessage mmMessage) {
    this.messages.add(mmMessage);
    return this;
  }

  /**
   * Clears the message list.
   *
   * @return  The empty message list.
   *
   * @since   $maven.project.version$
   */
  public MmMessageList clear() {
    this.messages.clear();
    return this;
  }

  /**
   * Returns the highest severity of error message of this mimic.
   *
   * @return  The highest severity of error message of this mimic.
   *
   * @since   $maven.project.version$
   */
  public MmMessageSeverity getMaximumSeverity() {
    MmMessageSeverity returnSeverity = null;
    for (MmMessage message : this.messages) {
      if ((returnSeverity == null) || (message.getSeverity().ordinal() > returnSeverity.ordinal())) {
        returnSeverity = message.getSeverity();
      }
    }
    return returnSeverity;
  }

  /**
   * Returns the list of all messages of this mimic, sorted by severity.
   *
   * @return  The list of all messages of this mimic, sorted by severity.
   *
   * @since   $maven.project.version$
   */
  public List<MmMessage> getMessages() {
    Collections.sort(this.messages, this.comparator);

    List<MmMessage> returnMessages = Collections.unmodifiableList(this.messages);
    return returnMessages;
  }

  public class MessageComparator implements Comparator<MmMessage> {

    @Override public int compare(MmMessage pMessage1, MmMessage pMessage2) {
      return pMessage1.severity.ordinal() - pMessage2.severity.ordinal();
    }
  }

}
