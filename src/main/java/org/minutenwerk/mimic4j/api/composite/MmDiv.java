package org.minutenwerk.mimic4j.api.composite;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.composite.MmBaseCompositeDeclaration;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationDiv;

/**
 * MmDiv is a composite mimic without display representation.
 *
 * @author  Olaf Kossak
 */
public class MmDiv extends MmBaseCompositeDeclaration<MmImplementationDiv> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   */
  public enum MmDivJsfTag {

    Div;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   */
  public enum MmRootJsfTag {

    Root;
  }

  /**
   * Creates a new MmDiv instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmDiv(MmDeclarationMimic pParent) {
    super(new MmImplementationDiv(pParent));
  }

}
