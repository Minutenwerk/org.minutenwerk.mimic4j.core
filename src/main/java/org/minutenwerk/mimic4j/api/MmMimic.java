package org.minutenwerk.mimic4j.api;

import java.net.URI;

import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * A mimic controls the data exchange between data layer and view output. MmMimic is the basic interface of all mimic classes in mimic4j.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-id, group-name, group-fullname, group-runtime, group-visible, group-enabled, group-short, group-long, group-style, group-model,
 *                      group-reference
 */
public interface MmMimic {

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot. It is evaluated during initialization phase and cannot change.
   *
   * @return        The id of this mimic.
   *
   * @jalopy.group  group-id
   */
  public String getMmId();

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. It is evaluated during initialization phase and cannot change. The name is
   * derived from the field declaration name in its parent class.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-name
   */
  public String getMmName();

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty String if the full
   * name is undefined. It is evaluated during initialization phase and cannot change. The full name is derived from the field declaration name in its parent
   * class.
   *
   * @return        The full name of this mimic.
   *
   * @jalopy.group  group-fullname
   */
  public String getMmFullName();

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link org.minutenwerk.mimic4j.api.container.MmTableRow}. It is evaluated
   * during initialization phase and cannot change.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-runtime
   */
  public boolean isMmRuntimeMimic();

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>true</code>). Is controlled by parents state of visible and callback method
   * {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>visible</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is visible.
   *
   * @see           {@link MmBaseCallback#callbackMmIsVisible()}
   * @see           {@link MmBaseConfiguration#isVisible()}
   *
   * @jalopy.group  group-visible
   */
  public boolean isMmVisible();

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>true</code>). Is controlled by parents state of enabled and callback method
   * {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>enabled</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is enabled.
   *
   * @see           {@link MmBaseCallback#callbackMmIsEnabled()}
   * @see           {@link MmBaseConfiguration#isEnabled()}
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmEnabled();

  /**
   * Returns <code>true</code>, if input of the mimic is disabled and links are disabled.
   *
   * @return        <code>True</code>, if input of the mimic is disabled and links are disabled.
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmInputDisabledAndReferenceDisabled();

  /**
   * Returns <code>true</code>, if input of the mimic is disabled but links can be followed.
   *
   * @return        <code>True</code>, if input of the mimic is disabled but links can be followed.
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmInputDisabledButReferenceEnabled();

  /**
   * Returns <code>true</code>, if input of the mimic is enabled and links can be followed.
   *
   * @return        <code>True</code>, if input of the mimic is enabled and links can be followed.
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmInputEnabledAndReferenceEnabled();

  /**
   * Returns <code>true</code>, if input of the mimic is enabled but links are disabled.
   *
   * @return        <code>True</code>, if input of the mimic is enabled but links are disabled.
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmInputEnabledButReferenceDisabled();

  /**
   * Returns <code>true</code>, if the mimic is referenceEnabled (default is <code>true</code>). Is controlled by parents state of referenceEnabled and
   * callback method {@link MmBaseCallback#callbackMmIsReferenceEnabled()}. Callback method returns configuration of annotation attribute <code>
   * referenceEnabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is reference enabled.
   *
   * @see           {@link MmBaseCallback#callbackMmIsReferenceEnabled()}
   * @see           {@link MmBaseConfiguration#isReferenceEnabled()}
   *
   * @jalopy.group  group-enabled
   */
  public boolean isMmReferenceEnabled();

  /**
   * Returns a short description. The short description is evaluated from callback method {@link MmBaseCallback#callbackMmGetShortDescription()}. If
   * {@link MmCallback#callbackMmGetShortDescription())} returns null, the short description is evaluated from configuration attribute
   * {@link MmBaseConfiguration#shortDescription()}.
   *
   * @return        A short description.
   *
   * @see           {@link MmBaseCallback#callbackMmGetShortDescription()}
   * @see           {@link MmBaseConfiguration#shortDescription()}
   *
   * @jalopy.group  group-short
   */
  public String getMmShortDescription();

  /**
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If <code>
   * callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @see           {@link MmBaseCallback#callbackMmGetLongDescription()}
   * @see           {@link MmBaseConfiguration#longDescription()}
   *
   * @jalopy.group  group-long
   */
  public String getMmLongDescription();

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style classes are evaluated
   * from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @see           {@link MmBaseCallback#callbackMmGetStyleClasses()}
   * @see           {@link MmBaseConfiguration#styleClasses()}
   *
   * @jalopy.group  group-style
   */
  public String getMmStyleClasses();

  /**
   * Returns <code>true</code>, if the mimic has a model, which delivers data for this model, and a model instance is currently present.
   *
   * @return        <code>True</code>, if a model instance is currently present.
   *
   * @jalopy.group  group-model
   */
  public boolean isMmModelPresent();

  /**
   * Returns the URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @return        The URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @jalopy.group  group-reference
   */
  public URI getMmSelfReference();

  /**
   * Returns the self reference (aka link) of this object for a specified data model.
   *
   * @param         dataModel  The specified instance of a data model, which is referenceable by an URL.
   *
   * @return        The self reference (aka link) of this object for a specified data model.
   *
   * @jalopy.group  group-reference
   */
  public URI getMmSelfReferenceForModel(MmReferenceableModel dataModel);

}
