package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * A mimic controls the data exchange between data layer and view output. MmMimic is the basic interface of all mimic classes in mimic4j.
 *
 * @author  Olaf Kossak
 */
public interface MmMimic {

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty
   * String if the full name is undefined. The full name is evaluated during initialization phase and derived from the field declaration
   * name in its parent class.
   *
   * @return  The full name of this mimic.
   */
  public String getMmFullName();

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return  The id of this mimic.
   */
  public String getMmId();

  /**
   * Returns a long description. The long description is evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetLongDescription()}.
   *
   * @return  A long description.
   *
   * @see     {@link MmBaseCallback#callbackMmGetLongDescription()}
   * @see     {@link MmBaseConfiguration#longDescription()}
   */
  public String getMmLongDescription();

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and
   * derived from the field declaration name in its parent class.
   *
   * @return  The name of this mimic.
   */
  public String getMmName();

  /**
   * Returns the self reference (aka link) of this object for the current data model, or the static part of the reference if there is no
   * current data model.
   *
   * @return  The self reference (aka link) of this object for the current data model, or the static part of the reference if there is no
   *          current data model.
   */
  public MmReference getMmReference();

  /**
   * Returns the self reference (aka link) of this object for a specified data model.
   *
   * @param   pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return  The self reference (aka link) of this object for a specified data model.
   */
  @Deprecated
  // TODO entfällt, wird aber derzeit noch von getMmReference() aufgerufen
  public MmReference getMmReference(MmReferencableModel pModel);

  /**
   * Returns a short description. The short description is evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetShortDescription()}. If {@link MmCallback#callbackMmGetShortDescription())} returns null, the short
   * description is evaluated from configuration attribute {@link MmBaseConfiguration#shortDescription()}.
   *
   * @return  A short description.
   *
   * @see     {@link MmBaseCallback#callbackMmGetShortDescription()}
   * @see     {@link MmBaseConfiguration#shortDescription()}
   */
  public String getMmShortDescription();

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   *
   * @see     {@link MmBaseCallback#callbackMmGetStyleClasses()}
   * @see     {@link MmBaseConfiguration#styleClasses()}
   */
  public String getMmStyleClasses();

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>true</code>). This mimic is enabled, if its parent is enabled and
   * its callback method {@link MmBaseCallback#callbackMmIsEnabled()} returns <code>true</code>. The callback method <code>
   * callbackMmIsEnabled()</code> can be redefined by the developer. The default implementation of <code>callbackMmIsEnabled()</code>
   * returns the value of the mimic's annotation attribute {@link MmBaseConfiguration#isEnabled()}. If the annotation <code>
   * MmConfiguration</code> or its attribute <code>enabled</code> are not declared, the default implementation of <code>
   * MmCallback.callbackMmIsEnabled()</code> returns the default value of the default configuration <code>
   * MmConfiguration.isEnabled()</code>, which is <code>true</code>.
   *
   * @return  <code>True</code>, if the mimic is enabled.
   *
   * @see     {@link MmBaseCallback#callbackMmIsEnabled()}
   * @see     {@link MmBaseConfiguration#isEnabled()}
   */
  public boolean isMmEnabled();

  /**
   * Returns <code>true</code>, if the mimic is readOnly (default is <code>false</code>). This mimic is readOnly, if its parent is readOnly
   * and its callback method {@link MmBaseCallback#callbackMmIsReadOnly()} returns <code>true</code>. The callback method <code>
   * callbackMmIsReadOnly()</code> can be redefined by the developer. The default implementation of <code>callbackMmIsReadOnly()</code>
   * returns the value of the mimic's annotation attribute {@link MmBaseConfiguration#isReadOnly()}. If the annotation <code>
   * MmConfiguration</code> or its attribute <code>readOnly</code> are not declared, the default implementation of <code>
   * MmCallback.callbackMmIsReadOnly()</code> returns the default value of the default configuration <code>
   * MmConfiguration.isReadOnly()</code>, which is <code>false</code>.
   *
   * @return  <code>True</code>, if the mimic is read only.
   *
   * @see     {@link MmBaseCallback#callbackMmIsReadOnly()}
   * @see     {@link MmBaseConfiguration#isReadOnly()}
   */
  public boolean isMmReadOnly();

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link org.minutenwerk.mimic4j.api.container.MmTableRow}.
   *
   * @return  <code>True</code>, if the mimic has been created at runtime.
   */
  public boolean isMmRuntimeMimic();

  /**
   * Returns <code>true</code>, if the mimic is visible. This mimic is visible, if its parent is visible and its callback method
   * callbackMmIsVisible returns <code>true</code>.
   *
   * @return  <code>True</code>, if the mimic is visible.
   *
   * @see     {@link MmBaseCallback#callbackMmIsVisible()}
   * @see     {@link MmBaseConfiguration#isVisible()}
   */
  public boolean isMmVisible();

}
