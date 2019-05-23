package org.minutenwerk.mimic4j.impl;

import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.CONSTRUCTION_COMPLETE;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.INITIALIZED;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.IN_CONSTRUCTION;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.IN_INITIALIZATION;

/**
 * State machine of mimic initialization phase.
 *
 * @author  Olaf Kossak
 */
public class MmInitialState {

  /**
   * Enumeration of possibles states during initialization phase.
   */
  public enum MmState {

    /** Mimic instance constructors are in execution. */
    IN_CONSTRUCTION,

    /** Mimic instance constructors are executed. */
    CONSTRUCTION_COMPLETE,

    /** Mimic initialize method is in execution. */
    IN_NAMING,

    /** Mimic initialize method is in execution. */
    IN_CONFIGURATION,

    /** Mimic initialize method is in execution. */
    IN_INITIALIZATION,

    /** Mimic is initialized. */
    INITIALIZED;

  }

  /** The current state. */
  private MmState state;

  /**
   * Creates a new MmInitialState instance.
   */
  MmInitialState() {
    state = IN_CONSTRUCTION;
  }

  /**
   * Returns true, if state is equal to specified state.
   *
   * @param   pState  The specified state to compare with.
   *
   * @return  True, if state is equal to specified state.
   */
  public boolean is(MmState pState) {
    return state == pState;
  }

  /**
   * Returns true, if state is equal or later in phase than specified state.
   *
   * @param   pState  The specified state to compare with.
   *
   * @return  True, if state is equal or later in phase than specified state.
   */
  public boolean isEqualOrLater(MmState pState) {
    return state.ordinal() >= pState.ordinal();
  }

  /**
   * Returns false, if state is equal to specified state.
   *
   * @param   pState  The specified state to compare with.
   *
   * @return  False, if state is equal to specified state.
   */
  public boolean isNot(MmState pState) {
    return !is(pState);
  }

  /**
   * Returns false, if state is equal or later in phase than specified state.
   *
   * @param   pState  The specified state to compare with.
   *
   * @return  False, if state is equal or later in phase than specified state.
   */
  public boolean isNotEqualOrLater(MmState pState) {
    return !isEqualOrLater(pState);
  }

  /**
   * Returns true, if state is not equal to any of specified states.
   *
   * @param   pState  The specified state to compare with.
   *
   * @return  False, if state is not equal to any of specified states.
   */
  public boolean isNotIn(MmState... pState) {
    for (MmState temp : pState) {
      if (state == temp) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns true, if state is equal to one of specified states.
   *
   * @param   pState  The specified states to compare with.
   *
   * @return  True, if state is equal to one of specified states.
   */
  public boolean isOneOf(MmState... pState) {
    for (MmState temp : pState) {
      if (state == temp) {
        return true;
      }
    }
    return false;
  }

  /**
   * Sets specified next state, if allowed.
   *
   * @param   pNextState  The specified next state.
   *
   * @throws  IllegalStateException  In case of illegal transition.
   */
  public void set(MmState pNextState) {
    if (((state == IN_CONSTRUCTION) && (pNextState == CONSTRUCTION_COMPLETE)) //
        || ((state == CONSTRUCTION_COMPLETE) && (pNextState == IN_INITIALIZATION)) //
        || ((state == IN_INITIALIZATION) && (pNextState == INITIALIZED))) {
      state = pNextState;
    } else {
      throw new IllegalStateException("Illegal transition from " + state + " to " + pNextState);
    }
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public String toString() {
    return state.name();
  }
}
