package org.minutenwerk.mimic4j.impl.link;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseLinkDeclaration is the base class for link mimics.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseLinkDeclaration<IMPLEMENTATION extends MmBaseLinkImplementation<?, LINK_MODEL, ?, ?>, LINK_MODEL>
  extends MmBaseDeclaration<MmLinkMimic<LINK_MODEL>, IMPLEMENTATION> implements MmLinkMimic<LINK_MODEL>, MmLinkCallback<LINK_MODEL> {

  /**
   * Creates a new MmBaseLinkDeclaration instance.
   *
   * @param  pImplementation  The reference to the implementation part of the mimic.
   */
  protected MmBaseLinkDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the link's model accessor to corresponding model. The link accessor can be derived from specified parent component accessor.
   *
   * @param         pParentAccessor  The specified parent component accessor.
   *
   * @return        The link's model accessor.
   *
   * @throws        IllegalStateException  In case of model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmModelAccessor<?, LINK_MODEL> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + getClass().getSimpleName());
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's format pattern for displaying viewside value.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetFormatPattern(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A mimic, which is the target reference of this link mimic.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmMimic callbackMmGetTargetMimic(MmMimic pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a string referencing a target, either an URL or an outcome.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A string referencing a target, either an URL or an outcome
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetTargetOutcome(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of URL parameters to be concatenated to target reference. May be used in combination with callbackMmGetTargetOutcome().
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   * @param         pModel             The link's data model providing the list of URL parameters.
   *
   * @return        A list of URL parameters to be concatenated to target reference.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public List<MmNameValue> callbackMmGetTargetReferenceParams(List<MmNameValue> pPassThroughValue, LINK_MODEL pModel) {
    return pPassThroughValue;
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, LINK_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the type of modelside value of the mimic.
   *
   * @return        The type of modelside value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final Class<LINK_MODEL> getMmModelsideType() {
    return implementation.getMmModelsideType();
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return        The modelside value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final LINK_MODEL getMmModelsideValue() {
    return implementation.getMmModelsideValue();
  }

  /**
   * Returns accessor of model of parent container mimic, may be null.
   *
   * @return        The accessor of model of parent container mimic, may be null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, ?> getMmParentAccessor() {
    return implementation.getMmParentAccessor();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome.
   *
   * @return        A reference to some target.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final MmReference getMmTargetReference() {
    return implementation.getMmTargetReference();
  }

  /**
   * Returns the link's viewside value of type String.
   *
   * @return        The link's viewside value of type String.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmViewsideValue() {
    return implementation.getMmViewsideValue();
  }
}
