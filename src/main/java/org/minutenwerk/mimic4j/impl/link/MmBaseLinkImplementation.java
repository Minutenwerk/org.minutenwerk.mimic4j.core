package org.minutenwerk.mimic4j.impl.link;

import java.lang.annotation.Annotation;

import java.net.URI;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmLinkMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmBaseLinkImplementation is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text label of
 * the link. In most cases the two models are the same.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-override, group-i18n, group-helper
 */
public abstract class MmBaseLinkImplementation<CALLBACK extends MmLinkCallback<DATA_MODEL, VIEW_MODEL>,
  DATA_MODEL extends MmReferencableModel, VIEW_MODEL, CONFIGURATION extends MmBaseLinkConfiguration, ANNOTATION extends Annotation>
  extends MmBaseImplementation<MmBaseLinkDeclaration<?, DATA_MODEL, VIEW_MODEL>, CONFIGURATION, ANNOTATION> implements MmLinkMimic<DATA_MODEL, VIEW_MODEL> {

  /** Class internal constant to describe index of generic type DATA_MODEL. */
  private static final int                 GENERIC_PARAMETER_INDEX_DATA_MODEL = 2;

  /** Class internal constant to describe index of generic type VIEW_MODEL. */
  private static final int                 GENERIC_PARAMETER_INDEX_VIEW_MODEL = 3;

  /** Logger of this class. */
  private static final Logger              LOGGER                             = LogManager.getLogger(MmBaseLinkImplementation.class);

  /** Accessor of data model. Data model delivers dynamic parts of URL. */
  protected MmModelAccessor<?, DATA_MODEL> dataModelAccessor;

  /** Accessor of view model. View model delivers view text label of link. */
  protected MmModelAccessor<?, VIEW_MODEL> viewModelAccessor;

  /**
   * Creates a new MmBaseLinkImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmBaseLinkImplementation(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call super.onInitialization()!
   *
   * @throws        IllegalStateException  In case of root accessor or model accessor is not defined.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // initialize parentAccessor
    final MmModelAccessor<?, ?> parentAccessor = onInitializeParentAccessor();

    // initialize modelAccessor
    dataModelAccessor = declaration.callbackMmGetModelAccessor(parentAccessor);
    if (dataModelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetModelAccessor() for " + this);
    }

    // initialize view modelAccessor
    viewModelAccessor = declaration.callbackMmGetViewModelAccessor(parentAccessor);
    if (viewModelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetViewModelAccessor() for " + this);
    }
  }

  /**
   * Evaluates accessor of component of parent container mimic.
   *
   * @return        The parent accessor.
   *
   * @throws        IllegalStateException  In case of there is no definition of a parent accessor.
   *
   * @jalopy.group  group-initialization
   */
  private MmModelAccessor<?, ?> onInitializeParentAccessor() {
    MmBaseContainerImplementation<?, ?, ?, ?> containerAncestor = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
    if (containerAncestor == null) {
      throw new IllegalStateException("no ancestor of type MmContainerMimic for " + this);
    } else {
      MmModelAccessor<?, ?> containerAccessor = containerAncestor.onInitializeGetMmModelAccessor();
      if (containerAccessor == null) {
        throw new IllegalStateException("no definition of parentAccessor for " + this);
      }
      return containerAccessor;
    }
  }

  /**
   * Returns data model value.
   *
   * @return        The data model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public DATA_MODEL getMmModel() {
    ensureInitialization();

    return dataModelAccessor.get();
  }

  /**
   * Returns accessor of data model.
   *
   * @return        The accessor of data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, DATA_MODEL> getMmModelAccessor() {
    ensureInitialization();

    return dataModelAccessor;
  }

  /**
   * Returns type of data model.
   *
   * @return        The type of data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<DATA_MODEL> getMmModelType() {
    ensureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseLinkImplementation.class, GENERIC_PARAMETER_INDEX_DATA_MODEL);
  }

  /**
   * Returns URI of the link.
   *
   * @return        The URI of the link.
   *
   * @throws        IllegalArgumentException  In case of no definition of target reference path.
   *
   * @jalopy.group  group-override
   */
  @Override
  public URI getMmTargetReference() {
    ensureInitialization();

    // retrieve target mimic, may be null
    final MmMimic      targetMimic          = declaration.callbackMmGetTargetReferenceMimic(null);

    // retrieve data model, may be null
    final DATA_MODEL   dataModel            = dataModelAccessor.get();

    // retrieve data model reference parameters, may be null
    final List<String> modelReferenceParams = dataModelAccessor.getMmReferenceParams();

    // if link references another mimic without a specified data model
    if ((targetMimic != null) && (dataModel == null)) {
      return targetMimic.getMmSelfReference();

      // if link references another mimic for a specified referencable data model
    } else if ((targetMimic != null) && (!modelReferenceParams.isEmpty())) {
      return targetMimic.getMmSelfReferenceForModel((MmReferencableModel)dataModel);

      // retrieve target reference path
    } else {

      // if link references an URI for a specified referencable data model
      if ((targetMimic == null) && (!modelReferenceParams.isEmpty())) {
        final URI targetReferencePath = declaration.callbackMmGetTargetReference(configuration.getTargetReferencePath(), dataModel, modelReferenceParams);
        if (targetReferencePath == null) {
          throw new IllegalArgumentException("no definition of target reference path for " + this);
        }
        return targetReferencePath;

      } else {
        final URI targetReferencePath = declaration.callbackMmGetTargetReference(configuration.getTargetReferencePath(), dataModel, Collections.emptyList());
        if (targetReferencePath == null) {
          throw new IllegalArgumentException("no definition of target reference path for " + this);
        }
        return targetReferencePath;
      }
    }
  }

  /**
   * Returns view model value.
   *
   * @return        The view model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public VIEW_MODEL getMmViewModel() {
    ensureInitialization();

    return viewModelAccessor.get();
  }

  /**
   * Returns model accessor of view model.
   *
   * @return        The model accessor of view model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, VIEW_MODEL> getMmViewModelAccessor() {
    ensureInitialization();

    return viewModelAccessor;
  }

  /**
   * Returns type of view model.
   *
   * @return        The type of view model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<VIEW_MODEL> getMmViewModelType() {
    ensureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseLinkImplementation.class, GENERIC_PARAMETER_INDEX_VIEW_MODEL);
  }

  /**
   * Returns view text of the link.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmViewValue() {
    ensureInitialization();

    return getMmShortDescription();
  }

  /**
   * Returns <code>true</code>, if the mimic has a model, which delivers data for this model, and a model instance is currently present.
   *
   * @return        <code>True</code>, if a model instance is currently present.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmModelPresent() {
    ensureInitialization();

    if (dataModelAccessor != null) {
      return dataModelAccessor.isPresent();
    } else {
      LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
      return false;
    }
  }

  /**
   * Returns data model for self reference. The data model delivers parameters of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return        The data model for self reference.
   *
   * @jalopy.group  group-override
   */
  @Override
  protected MmReferencableModel getMmReferencableModel() {
    return getMmModel();
  }

  /**
   * Returns the format pattern for formatting data model value to view model value.
   *
   * @return        The format pattern for formatting data model value to view model value.
   *
   * @throws        IllegalStateException  In case of callbackMmGetFormatPattern() returns an invalid format pattern.
   *
   * @jalopy.group  group-i18n
   */
  @Override
  public String getMmFormatPattern() {
    final String i18nFormatPattern     = getMmThisI18nText(MmMessageType.FORMAT);
    final String callbackFormatPattern = declaration.callbackMmGetViewFormatPattern(i18nFormatPattern);
    if (LOGGER.isDebugEnabled()) {
      if (callbackFormatPattern == null) {
        throw new IllegalStateException("callbackMmGetFormatPattern() must return valid format pattern");
      }
    }
    return callbackFormatPattern;
  }

  /**
   * Returns icon after text.
   *
   * @return  The icon after text.
   */
  @Override
  public String getIconAfter() {
    ensureInitialization();

    return configuration.getIconAfter();
  }

  /**
   * Returns icon before text.
   *
   * @return  The icon before text.
   */
  @Override
  public String getIconBefore() {
    ensureInitialization();

    return configuration.getIconBefore();
  }

}
