package org.minutenwerk.mimic4j.impl;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;

import org.springframework.web.util.UriComponents;

/**
 * MmCallback defines a set of override-able methods common to all mimics. Callback methods are part of the declaration API of mimic models. Callback methods
 * have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmBaseCallback {

  /**
   * Returns optional unformatted arguments for short and long description.
   *
   * @param   passThroughValues  Optional message arguments.
   *
   * @return  Optional message arguments.
   */
  public Object[] callbackMmGetDescriptionArguments(Object... passThroughValues);

  /**
   * Returns a format pattern for long and short description.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  A format pattern.
   */
  public String callbackMmGetFormatPattern(String passThroughValue);

  /**
   * Returns an unformatted long description.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  An unformatted long description.
   */
  public String callbackMmGetLongDescription(String passThroughValue);

  /**
   * /** Returns the self URL of this mimic.
   *
   * @param   selfReferencePath    The path of the self URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   * @param   dataModel            The data model, which delivers the target reference parameters.
   * @param   selfReferenceParams  The parameters of the self URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  The self URL of this mimic.
   */
  public URI callbackMmGetSelfReference(UriComponents selfReferencePath, MmReferenceableModel dataModel, List<String> selfReferenceParams);

  /**
   * Returns an unformatted short description.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  An unformatted short description.
   */
  public String callbackMmGetShortDescription(String passThroughValue);

  /**
   * Returns a String of space delimited <code>CSS</code> style classes. The style classes are evaluated from callbackMm method <code>
   * callbackMmGetStyleClasses</code>.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  A String of space delimited <code>CSS</code> style classes.
   */
  public String callbackMmGetStyleClasses(String passThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>true</code>). Is controlled by parents state of enabled and callback method
   * {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>enabled</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be enabled.
   */
  public boolean callbackMmIsEnabled(boolean passThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is referenceEnabled (default is <code>true</code>). Is controlled by parents state of reference enabled and
   * callback method {@link MmBaseCallback#callbackMmIsReferenceEnabled()}. Callback method returns configuration of annotation attribute <code>
   * referenceEnabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be reference enabled.
   */
  public boolean callbackMmIsReferenceEnabled(boolean passThroughValue);

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>true</code>). Is controlled by parents state of visible and callback method
   * {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>visible</code> on this mimic. Developer
   * can configure annotation and can override callback method.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be visible.
   */
  public boolean callbackMmIsVisible(boolean passThroughValue);

}
