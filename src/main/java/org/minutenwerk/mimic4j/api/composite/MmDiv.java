package org.minutenwerk.mimic4j.api.composite;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.composite.MmBaseCompositeDeclaration;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationDiv;

/**
 * MmDiv is a composite mimic without display representation.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmDiv extends MmBaseCompositeDeclaration<MmImplementationDiv> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/composite/MmDiv.java\$
   */
  public enum MmDivJsfTag {

    Div;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/composite/MmDiv.java\$
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
