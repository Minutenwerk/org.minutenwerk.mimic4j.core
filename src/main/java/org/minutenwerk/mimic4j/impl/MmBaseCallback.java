package org.minutenwerk.mimic4j.impl;

/**
 * MmCallback defines a set of override-able methods common to all mimics. Callback methods are part of the declaration API of mimic models.
 * Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmBaseCallback {

  /**
   * Returns a long description. The long description is evaluated from callbackMm method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> returns null, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>. If <code>MmConfiguration.longDescription</code> returns the constant <code>
   * MmConfiguration.UNDEFINED</code>, the long description is set to value of name of mimic.
   *
   * @param   pPassThroughValue      By default this parameter value will be returned.
   * @param   pPassThroughArguments  Optional list of message arguments.
   *
   * @return  A long description.
   */
  public String callbackMmGetLongDescription(String pPassThroughValue, Object... pPassThroughArguments);

  /**
   * Returns an array of message arguments for the long description.
   *
   * @param   pPassThroughValues  By default this parameter value will be returned.
   *
   * @return  An array of message arguments for the long description.
   */
  public Object[] callbackMmGetLongDescriptionParams(Object... pPassThroughValues);

  /**
   * Returns a short description. The short description is evaluated from callbackMm method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> returns null, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>. If <code>MmConfiguration.shortDescription</code> returns the constant <code>
   * MmConfiguration.UNDEFINED</code>, the short description is set to value of name of mimic.
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
   * Returns <code>true</code>, if the mimic shall be enabled. This mimic is enabled, if its parent is enabled and its callbackMm method
   * callbackMmIsEnabled returns <code>true</code>.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be enabled.
   */
  public boolean callbackMmIsEnabled(boolean pPassThroughValue);

  /**
   * Returns <code>true</code>, if the mimic shall be read only. This mimic is read only, if its parent is read only and its callbackMm
   * method callbackMmIsReadOnly returns <code>true</code>.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be read only.
   */
  public boolean callbackMmIsReadOnly(boolean pPassThroughValue);

  /**
   * Returns <code>true</code>, if the mimic shall be visible. This mimic is visible, if its parent is visible and its callbackMm method
   * callbackMmIsVisible returns <code>true</code>.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code>, if the mimic shall be visible.
   */
  public boolean callbackMmIsVisible(boolean pPassThroughValue);

}
