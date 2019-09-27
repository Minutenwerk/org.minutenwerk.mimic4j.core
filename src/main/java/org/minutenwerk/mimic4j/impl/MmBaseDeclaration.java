package org.minutenwerk.mimic4j.impl;

import java.net.URI;

import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.provided.MmSessionContext;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

import org.springframework.context.MessageSource;
import org.springframework.web.util.UriComponents;

/**
 * MmBaseDeclaration is the base class of the declaration part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseDeclaration<DEFINITION extends MmMimic, IMPLEMENTATION extends MmBaseImplementation<?, ?, ?>>
  implements MmDeclarationMimic, MmBaseCallback {

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
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @param         pPassThroughValue      By default this parameter value will be returned.
   * @param         pPassThroughArguments  Optional list of message arguments.
   *
   * @return        A long description.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetLongDescription(String pPassThroughValue, Object... pPassThroughArguments) {
    return pPassThroughValue;
  }

  /**
   * Returns an array of message arguments for the long description.
   *
   * @param         pPassThroughValues  By default this parameter value will be returned.
   *
   * @return        An array of message arguments for the long description.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public Object[] callbackMmGetLongDescriptionParams(Object... pPassThroughValues) {
    return pPassThroughValues;
  }

  /**
   * Returns the self URL of this mimic.
   *
   * @param         pSelfReferencePath    The path of the self URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   * @param         pSelfReferenceParams  The parameters of the self URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return        The self URL of this mimic.
   *
   * @jalopy.group  group-callback
   */
  public URI callbackMmGetSelfReference(UriComponents pSelfReferencePath, List<String> pSelfReferenceParams) {
    if ((pSelfReferenceParams == null) || pSelfReferenceParams.isEmpty()) {
      return pSelfReferencePath.toUri();
    } else if (pSelfReferenceParams.size() == 1) {
      return pSelfReferencePath.expand(pSelfReferenceParams.get(0)).toUri();
    } else {
      return pSelfReferencePath.expand(pSelfReferenceParams.toArray()).toUri();
    }
  }

  /**
   * Returns a short description. The short description is evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetShortDescription()}. If {@link MmCallback#callbackMmGetShortDescription())} returns null, the short
   * description is evaluated from configuration attribute {@link MmBaseConfiguration#shortDescription()}.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A short description.
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
   * Returns <code>true</code>, if the mimic is enabled (default is <code>false</code>). Is controlled by parents state of enabled and
   * callback method {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>
   * enabled</code> on this mimic. Developer can configure annotation and can override callback method.
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
   * Returns <code>true</code>, if the mimic is readOnly (default is <code>false</code>). Is controlled by parents state of readonly and
   * callback method {@link MmBaseCallback#callbackMmIsReadOnly()}. Callback method returns configuration of annotation attribute <code>
   * readonly</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code>, if the mimic shall be read only.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public boolean callbackMmIsReadOnly(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>false</code>). Is controlled by parents state of visible and
   * callback method {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>
   * visible</code> on this mimic. Developer can configure annotation and can override callback method.
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
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty
   * String if the full name is undefined. The full name is evaluated during initialization phase and derived from the field declaration
   * name in its parent class.
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
   * Returns a long description. The long description is evaluated from callbackMm method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> returns null, the long description is evaluated from configuration attribute <code>
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
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and
   * derived from the field declaration name in its parent class.
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
   * Returns the self reference (aka link) of this object for the current data model, or the fixed part of the reference if there is no
   * current data model.
   *
   * @return        The self reference (aka link) of this object for the current data model, or the fixed part of the reference if there is
   *                no current data model.
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
   * @param         pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference (aka link) of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final URI getMmSelfReferenceForModel(MmReferencableModel pModel) {
    return implementation.getMmSelfReferenceForModel(pModel);
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
  @Override
  public final String getMmShortDescription() {
    return implementation.getMmShortDescription();
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
  @Override
  public final String getMmStyleClasses() {
    return implementation.getMmStyleClasses();
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>false</code>). Is controlled by parents state of enabled and
   * callback method {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>
   * enabled</code> on this mimic. Developer can configure annotation and can override callback method.
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
   * Returns <code>true</code>, if the mimic is readOnly (default is <code>false</code>). Is controlled by parents state of readonly and
   * callback method {@link MmBaseCallback#callbackMmIsReadOnly()}. Callback method returns configuration of annotation attribute <code>
   * readonly</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is read only.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmReadOnly() {
    return implementation.isMmReadOnly();
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
   * Returns <code>true</code>, if the mimic is visible (default is <code>false</code>). Is controlled by parents state of visible and
   * callback method {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>
   * visible</code> on this mimic. Developer can configure annotation and can override callback method.
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
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
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
   * Returns an internationalized version for a specified message id and type.
   *
   * @param   pMessageId    The specified id of the message to be internationalized.
   * @param   pMessageType  The specified type of the message to be internationalized.
   * @param   pArguments    Optional list of message arguments.
   *
   * @return  The internationalized message.
   */
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments) {
    return implementation.getMmI18nText(pMessageId, pMessageType, pArguments);
  }

  /**
   * Returns the {@link Locale} of this root.
   *
   * @return  The locale of this root.
   */
  public Locale getMmLocale() {
    return implementation.getMmLocale();
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
    return implementation.getJsfBridge();
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  public boolean isMmJsEnabled() {
    return implementation.isMmJsEnabled();
  }

  /**
   * Sets specified message source.
   *
   * @param  pMessageSource  The specified message source.
   */
  public void setMmMessageSource(MessageSource pMessageSource) {
    implementation.setMmMessageSource(pMessageSource);
  }

  /**
   * Sets the {@link MmSessionContext} of this root, which provides information about the user's session.
   *
   * @param  pSessionContext  The session context to be set.
   */
  public void setSessionContext(MmSessionContext pSessionContext) {
    implementation.setSessionContext(pSessionContext);
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
