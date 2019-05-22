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
}
