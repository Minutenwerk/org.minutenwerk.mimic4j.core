package org.minutenwerk.mimic4j.api.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;
import org.minutenwerk.mimic4j.impl.container.MmLeporelloCallback;

/**
 * MmLeporello is a composite mimic to represent a leporello of panels.
 *
 * @param   MODEL      The model containing the values to be set, cannot be null.
 * @param   SUB_MODEL  The sub model containing the values to be set, can be null.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public abstract class MmLeporello<MODEL, SUB_MODEL> extends MmBaseContainerDeclaration<MODEL, MmImplementationLeporello<MODEL, SUB_MODEL>>
  implements MmLeporelloCallback<MODEL, SUB_MODEL> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/container/MmLeporello.java\$
   */
  public enum MmLeporelloJsfTag {

    Leporello;
  }

  /**
   * Creates a new MmLeporelloPanel instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLeporello(MmDeclarationMimic pParent) {
    super(new MmImplementationLeporello<MODEL, SUB_MODEL>(pParent));
  }

  /**
   * Sets values from modelside of mimic into model.
   *
   * @param  pModel  The model to set values into.
   *
   * @since  $maven.project.version$
   */
  @Override public final void callbackMmSetModelFromModelside(MODEL pModel) {
    // do nothing. Because leporellos are read only, this method can never be called, therefore it is made final here
  }

  /**
   * Sets values from model into modelside of mimic.
   *
   * @param  pModel     The model containing the values to be set, cannot be null.
   * @param  pSubModel  The sub model containing the values to be set, can be null.
   *
   * @since  $maven.project.version$
   */
  @Override public void callbackMmSetModelsideFromModel(MODEL pModel, SUB_MODEL pSubModel) {
  }

  /**
   * Sets the values from model to modelside of mimic.
   *
   * @param  pModel     The model containing the values to be set, cannot be null.
   * @param  pSubModel  The sub model containing the values to be set, can be null.
   *
   * @since  $maven.project.version$
   */
  public final void doMmSetModelsideFromModel(MODEL pModel, SUB_MODEL pSubModel) {
    this.implementation.doMmSetModelsideFromModel(pModel, pSubModel);
  }

  /**
   * Returns a list of all leporello panels inside this leporello.
   *
   * @return  A list of all leporello panels inside this leporello.
   *
   * @since   $maven.project.version$
   */
  public List<MmLeporelloPanel<?>> getMmLeporelloPanels() {
    final List<MmLeporelloPanel<?>> panels = new ArrayList<MmLeporelloPanel<?>>();
    for (MmMimic child : MmRelationshipApi.getMmChildren(this)) {
      if (child instanceof MmLeporelloPanel<?>) {
        panels.add((MmLeporelloPanel<?>)child);
      }
    }
    return panels;
  }

}
