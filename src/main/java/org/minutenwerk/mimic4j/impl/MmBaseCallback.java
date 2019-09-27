package org.minutenwerk.mimic4j.impl;

import java.net.URI;

import java.util.List;

import org.springframework.web.util.UriComponents;

/**
 * MmCallback defines a set of override-able methods common to all mimics. Callback methods are part of the declaration API of mimic models.
 * Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmBaseCallback {

  /**
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @param   pPassThroughValue      By default this parameter value will be returned.
   * @param   pPassThroughArguments  Optional list of message arguments.
   *
   * @return  A long description.
   */
  public String callbackMmGetLongDescription(String pPassThroughValue, Object... pPassThroughArguments);

  /**
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @param   pPassThroughValues  By default this parameter value will be returned.
   *
   * @return  An array of message arguments for the long description.
   */
  @Deprecated
  public Object[] callbackMmGetLongDescriptionParams(Object... pPassThroughValues);

  /**
   * Returns the self URL of this mimic.
   *
   * @param   pSelfReferencePath    The path of the self URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   * @param   pSelfReferenceParams  The parameters of the self URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  The self URL of this mimic.
   */
  public URI callbackMmGetSelfReference(UriComponents pSelfReferencePath, List<String> pSelfReferenceParams);

  /**
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A short description.
   */
  public String callbackMmGetShortDescription(String pPassThroughValue);

  /**
   * Returns a String of space delimited <code>CSS</code> style classes. The style classes are evaluated from callbackMm method <code>
   * callbackMmGetStyleClasses</code>.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A String of space delimited <code>CSS</code> style classes.
   */
  public String callbackMmGetStyleClasses(String pPassThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>false</code>). Is controlled by parents state of enabled and
   * callback method {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>
   * enabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be enabled.
   */
  public boolean callbackMmIsEnabled(boolean pPassThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is readOnly (default is <code>false</code>). Is controlled by parents state of readonly and
   * callback method {@link MmBaseCallback#callbackMmIsReadOnly()}. Callback method returns configuration of annotation attribute <code>
   * readonly</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be read only.
   */
  public boolean callbackMmIsReadOnly(boolean pPassThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>false</code>). Is controlled by parents state of visible and
   * callback method {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>
   * visible</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be visible.
   */
  public boolean callbackMmIsVisible(boolean pPassThroughValue);

}
