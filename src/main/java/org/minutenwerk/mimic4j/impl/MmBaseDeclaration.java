package org.minutenwerk.mimic4j.impl;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseDeclaration is the base class of the declaration part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseDeclaration<DEFINITION extends MmMimic, IMPLEMENTATION extends MmBaseImplementation<?, ?, ?>> implements MmDeclarationMimic,
  MmBaseCallback {

  /** The implementation part of the mimic. */
  protected final IMPLEMENTATION implementation;

  /**
   * Creates a new MmBaseDeclaration instance.
   *
   * @param  pImplementation  The implementation part of the mimic.
   */
  protected MmBaseDeclaration(IMPLEMENTATION pImplementation) {
    implementation = pImplementation;
    implementation.onPostConstruct(this);
  }

  /**
   * Returns optional unformatted arguments for short and long description.
   *
   * @param         pPassThroughValues  Optional message arguments.
   *
   * @return        Optional message arguments.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public Object[] callbackMmGetDescriptionArguments(Object... pPassThroughValues) {
    return pPassThroughValues;
  }

  /**
   * Returns a format pattern for long and short description.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A format pattern.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetFormatPattern(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns an unformatted long description.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        An unformatted long description.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetLongDescription(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the self URL of this mimic.
   *
   * @param         pSelfReferencePath    The path of the self URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   * @param         pDataModel            The data model, which delivers the target reference parameters.
   * @param         pSelfReferenceParams  The parameters of the self URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return        The self URL of this mimic.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public URI callbackMmGetSelfReference(UriComponents pSelfReferencePath, MmReferenceableModel pDataModel, List<String> pSelfReferenceParams) {
    if ((pSelfReferenceParams == null) || pSelfReferenceParams.isEmpty()) {
      if (pDataModel == null) {
        return pSelfReferencePath.toUri();
      } else {
        return pSelfReferencePath.expand(pDataModel).toUri();
      }
    } else if (pSelfReferenceParams.size() == 1) {
      return pSelfReferencePath.expand(pSelfReferenceParams.get(0)).toUri();
    } else {
      return pSelfReferencePath.expand(pSelfReferenceParams.toArray()).toUri();
    }
  }

  /**
   * Returns an unformatted short description.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        An unformatted short description.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetShortDescription(String pPassThroughValue) {
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
  @Override
  public String callbackMmGetStyleClasses(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>true</code>). Is controlled by parents state of enabled and callback method
   * {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>enabled</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be enabled.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public boolean callbackMmIsEnabled(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic is referenceEnabled (default is <code>true</code>). Is controlled by parents state of reference enabled and
   * callback method {@link MmBaseCallback#callbackMmIsReferenceEnabled()}. Callback method returns configuration of annotation attribute <code>
   * referenceEnabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be reference enabled.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public boolean callbackMmIsReferenceEnabled(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>true</code>). Is controlled by parents state of visible and callback method
   * {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>visible</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be visible.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public boolean callbackMmIsVisible(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic has a model, which delivers data for this model, and a model instance is currently present.
   *
   * @return        <code>True</code>, if a model instance is currently present.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public final boolean isMmModelPresent() {
    return implementation.isMmModelPresent();
  }

  /**
   * Returns list of children mimics, might be empty.
   *
   * @return        List of children mimics, might be empty.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final List<MmMimic> getMmChildrenMimics() {
    return implementation.getMmChildrenMimics();
  }

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty String if the full
   * name is undefined. The full name is evaluated during initialization phase and derived from the field declaration name in its parent class.
   *
   * @return        The full name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmFullName() {
    return implementation.getMmFullName();
  }

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return        The id of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmId() {
    return implementation.getMmId();
  }

  /**
   * Returns a long description. The long description is evaluated from callbackMm method <code>callbackMmGetLongDescription</code>. If <code>
   * callbackMmGetLongDescription</code> returns null, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmLongDescription() {
    return implementation.getMmLongDescription();
  }

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and derived from the field
   * declaration name in its parent class.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmName() {
    return implementation.getMmName();
  }

  /**
   * Returns parent mimic, if exists, otherwise null.
   *
   * @return        Parent mimic, if exists, otherwise null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final MmMimic getMmParentMimic() {
    return implementation.getMmParentMimic();
  }

  /**
   * Returns the URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @return        The URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final URI getMmSelfReference() {
    return implementation.getMmSelfReference();
  }

  /**
   * Returns the self reference (aka link) of this object for a specified data model.
   *
   * @param         pDataModel  The specified instance of a data model, which is referenceable by an URL.
   *
   * @return        The self reference (aka link) of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final URI getMmSelfReferenceForModel(MmReferenceableModel pDataModel) {
    return implementation.getMmSelfReferenceForModel(pDataModel);
  }

  /**
   * Returns a short description. The short description is evaluated from callbackMm method <code>callbackMmGetShortDescription</code>. If <code>
   * callbackMmGetShortDescription</code> returns null, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmShortDescription() {
    return implementation.getMmShortDescription();
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style classes are evaluated
   * from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmStyleClasses() {
    return implementation.getMmStyleClasses();
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>true</code>). Is controlled by parents state of enabled and callback method
   * {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>enabled</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is enabled.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmEnabled() {
    return implementation.isMmEnabled();
  }

  /**
   * Returns <code>true</code>, if input of the mimic is disabled and links are disabled.
   *
   * @return        <code>True</code>, if input of the mimic is disabled and links are disabled.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmInputDisabledAndReferenceDisabled() {
    return implementation.isMmInputDisabledAndReferenceDisabled();
  }

  /**
   * Returns <code>true</code>, if input of the mimic is disabled but links can be followed.
   *
   * @return        <code>True</code>, if input of the mimic is disabled but links can be followed.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmInputDisabledButReferenceEnabled() {
    return implementation.isMmInputDisabledButReferenceEnabled();
  }

  /**
   * Returns <code>true</code>, if input of the mimic is enabled and links can be followed.
   *
   * @return        <code>True</code>, if input of the mimic is enabled and links can be followed.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmInputEnabledAndReferenceEnabled() {
    return implementation.isMmInputEnabledAndReferenceEnabled();
  }

  /**
   * Returns <code>true</code>, if input of the mimic is enabled but links are disabled.
   *
   * @return        <code>True</code>, if input of the mimic is enabled but links are disabled.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmInputEnabledButReferenceDisabled() {
    return implementation.isMmInputEnabledButReferenceDisabled();
  }

  /**
   * Returns <code>true</code>, if the mimic is referenceEnabled (default is <code>true</code>). Is controlled by parents state of reference enabled and
   * callback method {@link MmBaseCallback#callbackMmIsReferenceEnabled()}. Callback method returns configuration of annotation attribute <code>
   * referenceEnabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is reference enabled.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmReferenceEnabled() {
    return implementation.isMmReferenceEnabled();
  }

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmRuntimeMimic() {
    return implementation.isMmRuntimeMimic();
  }

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>true</code>). Is controlled by parents state of visible and callback method
   * {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>visible</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is visible.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmVisible() {
    return implementation.isMmVisible();
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change at any time.
   *
   * @return        Some information about this object for development purposes like debugging and logging.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (implementation == null) {
      Class<?> clazz = getClass();
      while (clazz.getSimpleName().isEmpty()) {
        clazz = clazz.getSuperclass();
      }
      sb.append(clazz.getSimpleName());
      sb.append(" implementation = null");
    } else {
      sb.append(implementation.toString());
    }
    return sb.toString();
  }

  /**
   * Returns debug information about this mimic and subtree of all its children and runtime children.
   *
   * @return  Debug information about this mimic and subtree of all its children and runtime children.
   */
  public final String toStringSubtree() {
    return implementation.toStringSubtree("  ");
  }

}
