package org.minutenwerk.mimic4j.api.mimic;

import java.util.List;
import java.util.stream.Collectors;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
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
   * Returns the list of all direct declaration children of specified mimic, which are instances of class <code>MmBaseDeclaration</code>, including
   * runtime children.
   *
   * @param         pDeclarationType  The specified mimic.
   *
   * @return        The list of all direct children of specified mimic of type <code>MmBaseDeclaration</code>, including runtime children.
   *
   * @throws        IllegalArgumentException  In case of parameter is not a subclass of MmBaseDeclaration.
   */
  @SuppressWarnings("unchecked")
  public static <T extends MmMimic> List<T> getDirectChildrenOfType(MmMimic pMimic, Class<T> pDeclarationType) {
    if (!pDeclarationType.isInterface() && !(MmBaseDeclaration.class.isAssignableFrom(pDeclarationType))) {
      throw new IllegalArgumentException("Class " + pDeclarationType.getSimpleName() + " is not a subclass of MmBaseDeclaration");
    }
    return (List<T>)(getMmChildren(pMimic).stream() //
        .filter(child -> pDeclarationType.isAssignableFrom(child.getClass())) //
        .collect(Collectors.toList()));
  }

  /**
   * Returns a descendant mimic of specified full name, or <code>null</code> if it doesn't exist. The name is a path of ancestors' names like <code>
   * grandparent.parent.child</code>.
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
  public static MmPageMimic<?> getMmRoot(MmMimic pMimic) {
    final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = MmInternal.getMmImplementation(pMimic);
    return implementationPartOfMimic.getMmRoot();
  }

}
