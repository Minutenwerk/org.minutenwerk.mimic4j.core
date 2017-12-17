package org.minutenwerk.mimic4j.impl;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmBaseDeclaration is the base class of the declaration part of all mimic classes.
 *
 * @author              Olaf Kossak
 * @see                 $HeadURL: $$maven.project.version$
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseDeclaration<DEFINITION extends MmMimic, IMPLEMENTATION extends MmBaseImplementation<?, ?>>
  implements MmDeclarationMimic, MmBaseCallback {

  /** The implementation part of the mimic. */
  protected final IMPLEMENTATION implementation;

  /**
   * Creates a new MmBaseDeclaration instance.
   *
   * @param  pImplementation  The implementation part of the mimic.
   */
  protected MmBaseDeclaration(IMPLEMENTATION pImplementation) {
    this.implementation = pImplementation;
    this.implementation.setCallback(this);
  }

  /**
   * Returns a long description. The long description is evaluated from callbackMm method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> returns null, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>. If <code>MmConfiguration.longDescription</code> returns the constant <code>
   * MmConfiguration.UNDEFINED</code>, the long description is set to value of name of mimic.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   * @param         pArguments         Optional list of message arguments.
   *
   * @return        A long description.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetLongDescription(String pPassThroughValue, Object... pArguments) {
    return pPassThroughValue;
  }

  /**
   * Returns a short description. The short description is evaluated from callbackMm method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> returns null, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>. If <code>MmConfiguration.shortDescription</code> returns the constant <code>
   * MmConfiguration.UNDEFINED</code>, the short description is set to value of name of mimic.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetShortDescription(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A String of space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetStyleClasses(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic shall be enabled. This mimic is enabled, if its parent is enabled and its callbackMm method
   * callbackMmIsEnabled returns <code>true</code>.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be enabled.
   *
   * @jalopy.group  group-callback
   */
  @Override public boolean callbackMmIsEnabled(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic shall be read only. This mimic is read only, if its parent is read only and its callbackMm
   * method callbackMmIsReadOnly returns <code>true</code>.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be read only.
   *
   * @jalopy.group  group-callback
   */
  @Override public boolean callbackMmIsReadOnly(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic shall be visible. This mimic is visible, if its parent is visible and its callbackMm method
   * callbackMmIsVisible returns <code>true</code>.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be visible.
   *
   * @jalopy.group  group-callback
   */
  @Override public boolean callbackMmIsVisible(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty
   * String if the full name is undefined. The full name is evaluated during initialization phase and derived from the field declaration
   * name in its parent class.
   *
   * @return        The full name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmFullName() {
    return this.implementation.getMmFullName();
  }

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return        The id of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmId() {
    return this.implementation.getMmId();
  }

  /**
   * Returns a long description. The long description is evaluated from callbackMm method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> returns null, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmLongDescription() {
    return this.implementation.getMmLongDescription();
  }

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and
   * derived from the field declaration name in its parent class.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmName() {
    return this.implementation.getMmName();
  }

  /**
   * Returns the self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @return        The self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override public final MmReference getMmReference() {
    return this.implementation.getMmReference();
  }

  /**
   * Returns the self reference of this object for a specified data model.
   *
   * @param         pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Override public final MmReference getMmReference(MmReferencableModel pModel) {
    return this.implementation.getMmReference(pModel);
  }

  /**
   * Returns a short description. The short description is evaluated from callbackMm method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> returns null, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmShortDescription() {
    return this.implementation.getMmShortDescription();
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmStyleClasses() {
    return this.implementation.getMmStyleClasses();
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled. This mimic is enabled, if its parent is enabled and its callbackMm method
   * callbackMmIsEnabled returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is enabled.
   *
   * @jalopy.group  group-override
   */
  @Override public final boolean isMmEnabled() {
    return this.implementation.isMmEnabled();
  }

  /**
   * Returns <code>true</code>, if the mimic is read only. This mimic is read only, if its parent is read only and its callbackMm method
   * callbackMmIsReadOnly returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is read only.
   *
   * @jalopy.group  group-override
   */
  @Override public final boolean isMmReadOnly() {
    return this.implementation.isMmReadOnly();
  }

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-override
   */
  @Override public final boolean isMmRuntimeChild() {
    return this.implementation.isMmRuntimeChild();
  }

  /**
   * Returns <code>true</code>, if the mimic is visible. This mimic is visible, if its parent is visible and its callbackMm method
   * callbackMmIsVisible returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is visible.
   *
   * @jalopy.group  group-override
   */
  @Override public final boolean isMmVisible() {
    return this.implementation.isMmVisible();
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
   *
   * @return        Some information about this object for development purposes like debugging and logging.
   *
   * @jalopy.group  group-override
   */
  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.implementation == null) {
      Class<?> clazz = this.getClass();
      while (clazz.getSimpleName().isEmpty()) {
        clazz = clazz.getSuperclass();
      }
      sb.append(clazz.getSimpleName());
      sb.append(" implementation = null");
    } else {
      sb.append(this.implementation.toString());
    }
    return sb.toString();
  }

  /**
   * Returns an array of message arguments for the long description.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  An array of message arguments for the long description.
   */
  public Object[] callbackMmGetLongDescriptionParams(Object... pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the MmJsfBridge of this mimic, which connects it to a JSF view component, is called in facelets tags. See /META-INF/*.xhtml.
   *
   * <pre>
       <... value = "${mm.toJsf.value}" />
   * </pre>
   *
   * @return  The MmJsfBridge of this mimic.
   */
  public final MmJsfBridge<?, ?, ?> getToJsf() {
    return this.implementation.getJsfBridge();
  }

  /**
   * Returns debug information about this mimic and subtree of all its children and runtime children.
   *
   * @return  Debug information about this mimic and subtree of all its children and runtime children.
   */
  public final String toStringSubtree() {
    return MmBaseImplementation.toStringSubtree(this.implementation, "  ");
  }

}