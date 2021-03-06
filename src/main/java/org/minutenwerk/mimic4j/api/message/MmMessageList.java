package org.minutenwerk.mimic4j.api.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.minutenwerk.mimic4j.api.mimic.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.mimic.MmContainerMimic;

/**
 * MmMessageList is a list of messages of type {@link MmMessage}.
 *
 * @author  Olaf Kossak
 */
public class MmMessageList {

  /** The comparator of messages. */
  protected static final MessageComparator COMPARATOR = new MessageComparator();

  /** The list of messages. */
  protected final List<MmMessage>          messages;

  /**
   * Creates a new MmMessageList instance.
   */
  public MmMessageList() {
    messages = new ArrayList<>();
  }

  /**
   * Adds a message to this list of messages, and returns the message list.
   *
   * @param   mmMessage  The message to be added.
   *
   * @return  The message list after adding the message.
   */
  public MmMessageList addMessage(MmMessage mmMessage) {
    messages.add(mmMessage);
    return this;
  }

  /**
   * Adds a specified message list to this message list, and returns this message list.
   *
   * @param   mmMessageList  The specified message list.
   *
   * @return  The message list after adding the specified message list.
   */
  public MmMessageList addMessages(MmMessageList mmMessageList) {
    messages.addAll(mmMessageList.getMessages());
    return this;
  }

  /**
   * Clears the message list.
   *
   * @return  The empty message list.
   */
  public MmMessageList clear() {
    messages.clear();
    return this;
  }

  /**
   * Returns the highest severity of error message of this mimic.
   *
   * @return  The highest severity of error message of this mimic.
   */
  public MmMessageSeverity getMaximumSeverity() {
    if (messages.isEmpty()) {
      return MmMessageSeverity.INFO;
    } else {
      return messages.stream().map(MmMessage::getSeverity).max(Comparator.comparingInt(MmMessageSeverity::ordinal)).get();
    }
  }

  /**
   * Returns the list of all messages of this mimic, sorted by severity.
   *
   * @return  The list of all messages of this mimic, sorted by severity.
   */
  public List<MmMessage> getMessages() {
    Collections.sort(messages, COMPARATOR);

    List<MmMessage> returnMessages = Collections.unmodifiableList(messages);
    return returnMessages;
  }

  /**
   * Returns true, if message list contains messages.
   *
   * @return  True, if message list contains messages.
   */
  public boolean hasMessages() {
    return !isEmpty();
  }

  /**
   * Returns true, if message list is empty.
   *
   * @return  True, if message list is empty.
   */
  public boolean isEmpty() {
    return messages.isEmpty();
  }

  public static final class MessageComparator implements Comparator<MmMessage> {

    @Override
    public int compare(MmMessage pMessage1, MmMessage pMessage2) {
      int diff = pMessage1.severity.ordinal() - pMessage2.severity.ordinal();
      if (diff != 0) {
        return diff;
      }
      if ((pMessage1.ownerMm instanceof MmContainerMimic<?>) && (pMessage2.ownerMm instanceof MmAttributeMimic<?, ?>)) {
        return -1;
      } else if ((pMessage1.ownerMm instanceof MmAttributeMimic<?, ?>) && (pMessage2.ownerMm instanceof MmContainerMimic<?>)) {
        return 1;
      }
      return pMessage1.ownerMm.getMmId().compareTo(pMessage2.ownerMm.getMmId());
    }
  }
}
