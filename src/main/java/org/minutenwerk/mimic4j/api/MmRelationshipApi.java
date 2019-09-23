package org.minutenwerk.mimic4j.api;

import java.util.List;

import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmInternal;

/**
 * The MmRelationshipApi offers methods to access related mimic of a specified mimic.
 *
 * @author  Olaf Kossak
 */
public class MmRelationshipApi {

  /**
   * Constructor is not for use.
   */
  private MmRelationshipApi() {
  }

  /**
   * Returns a list of all ancestor mimics of this mimic. Top most ancestor will be first, direct parent will be last.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  A list of all ancestor mimics of this mimic.
   */
  public static List<MmMimic> getMmAncestors(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmAncestors();
  }

  /**
   * Returns a direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   *
   * @param   pMimic      The specified mimic.
   * @param   pChildName  The name of the child to search for.
   *
   * @return  A direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   */
  public static MmMimic getMmChildByName(MmMimic pMimic, String pChildName) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmChildByName(pChildName);
  }

  /**
   * Returns a list of all direct child mimics of this mimic.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  A list of all direct child mimics of this mimic.
   */
  public static List<MmMimic> getMmChildren(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmChildren();
  }

  /**
   * Returns a descendant mimic of specified full name, or <code>null</code> if it doesn't exist. The name is a path of ancestors' names
   * like <code>grandparent.parent.child</code>.
   *
   * @param   pMimic     The specified mimic.
   * @param   pFullName  The full name of the mimic to search for.
   *
   * @return  A descendant mimic of specified full name, or <code>null</code> if it doesn't exist.
   */
  public static MmMimic getMmDescendantByFullName(MmMimic pMimic, String pFullName) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmDescendantByFullName(pFullName);
  }

  /**
   * Returns a list of all descendant mimics of this mimic. Each child is followed by own children, before followed by siblings.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  A list of all descendant mimics of this mimic.
   */
  public static List<MmMimic> getMmDescendants(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmDescendants();
  }

  /**
   * Returns the parent mimic of this mimic, may be null in case of <code>MmRoot</code>.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  The parent mimic of this mimic, may be null.
   */
  public static MmMimic getMmParent(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmParent();
  }

  /**
   * Returns the <code>MmRoot</code> of this mimic.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  The <code>MmRoot</code> of this mimic.
   */
  public static MmMimic getMmRoot(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmRoot();
  }

}
