package org.minutenwerk.mimic4j.impl.link;

import java.lang.annotation.Annotation;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.URI;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmInformationable;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeImplementation;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseLinkImplementation is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers
 * the text label of the link. In most cases the two models are the same.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-override, group-i18n
 */
public abstract class MmBaseLinkImplementation<CALLBACK extends MmLinkCallback<DATA_MODEL, VIEW_MODEL>,
  DATA_MODEL extends MmReferencableModel, VIEW_MODEL, CONFIGURATION extends MmBaseLinkConfiguration, ANNOTATION extends Annotation>
  extends MmBaseImplementation<MmBaseLinkDeclaration<?, DATA_MODEL, VIEW_MODEL>, CONFIGURATION, ANNOTATION>
  implements MmLinkMimic<DATA_MODEL, VIEW_MODEL> {

  /** Class internal constant to control index of generic type DATA_MODEL. */
  private static final int                 GENERIC_PARAMETER_INDEX_DATA_MODEL = 2;

  /** Class internal constant to control index of generic type VIEW_MODEL. */
  private static final int                 GENERIC_PARAMETER_INDEX_VIEW_MODEL = 3;

  /** Logger of this class. */
  private static final Logger              LOGGER                             = LogManager.getLogger(MmBaseLinkImplementation.class);

  /** Model accessor of link mimic parent, may be null. */
  protected MmModelAccessor<?, ?>          parentAccessor;

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
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call
   * super.onInitialization()!
   *
   * @throws        IllegalStateException  In case of root accessor or model accessor is not defined.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // initialize parentAccessor
    parentAccessor    = onInitializeParentAccessor();

    // initialize modelAccessor
    dataModelAccessor = declaration.callbackMmGetAccessor(parentAccessor);
    if (dataModelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + this);
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
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    // retrieve view model
    final VIEW_MODEL viewModel = getMmViewModel();

    if (viewModel instanceof MmInformationable) {
      return getMmDescription((MmInformationable)viewModel, MmMessageType.LONG);
    } else {
      return getMmDescription(viewModel, MmMessageType.LONG);
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
    assureInitialization();

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
    assureInitialization();

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
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_DATA_MODEL);
  }

  /**
   * Returns model accessor of link mimic parent, may be null.
   *
   * @return        The model accessor of link mimic parent, may be null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, ?> getMmParentAccessor() {
    assureInitialization();

    return parentAccessor;
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
    assureInitialization();

    // retrieve target mimic, may be null
    final MmMimic    targetMimic = declaration.callbackMmGetTargetReferenceMimic(null);

    // retrieve data model, may be null
    final DATA_MODEL model       = dataModelAccessor.get();

    // if link references another mimic without a specified data model
    if ((targetMimic != null) && (model == null)) {
      return targetMimic.getMmReference();

      // if link references another mimic for a specified referencable data model
    } else if ((targetMimic != null) && (model != null) && (model instanceof MmReferencableModel)) {
      return targetMimic.getMmReference((MmReferencableModel)model);

      // retrieve target reference path
    } else {
      final UriComponents configTargetReferencePath = configuration.getTargetReferencePath();
      final UriComponents targetReferencePath       = declaration.callbackMmGetTargetReferencePath(configTargetReferencePath);
      if (targetReferencePath == null) {
        throw new IllegalArgumentException("no definition of target reference path for " + this);
      }

      // if link references an URI without a specified data model
      if ((targetMimic == null) && (model == null)) {
        final List<String> targetReferenceParams = declaration.callbackMmGetTargetReferenceValues(Collections.emptyList(), null);
        return targetReferencePath.expand(targetReferenceParams).toUri();

        // if link references an URI for a specified referencable data model
      } else if ((targetMimic == null) && (model != null) && (model instanceof MmReferencableModel)) {
        final List<String> modelReferenceParams  = ((MmReferencableModel)model).getMmReferenceValues();
        final List<String> targetReferenceParams = declaration.callbackMmGetTargetReferenceValues(modelReferenceParams, model);
        return targetReferencePath.expand(targetReferenceParams).toUri();

        // if link references an URI for a specified raw data model
      } else if ((targetMimic == null) && (model != null)) {
        final List<String> targetReferenceParams = declaration.callbackMmGetTargetReferenceValues(Collections.emptyList(), model);
        return targetReferencePath.expand(targetReferenceParams).toUri();

        // in all other cases just use target reference path
      } else {
        return targetReferencePath.toUri();
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
    assureInitialization();

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
    assureInitialization();

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
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_VIEW_MODEL);
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
    assureInitialization();

    // retrieve view model
    final VIEW_MODEL viewModel = getMmViewModel();

    if (viewModel instanceof MmInformationable) {
      return getMmDescription((MmInformationable)viewModel, MmMessageType.SHORT);
    } else {
      return getMmDescription(viewModel, MmMessageType.SHORT);
    }
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
    assureInitialization();

    if (dataModelAccessor != null) {
      return dataModelAccessor.isPresent();
    } else {
      LOGGER.warn("no definition of callbackMmGetAccessor() for {}.{}.", parentPath, name);
      return false;
    }
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
  public String getMmFormatPattern() {
    final String i18nFormatPattern     = getMmI18nText(MmMessageType.FORMAT);
    final String callbackFormatPattern = declaration.callbackMmGetViewFormatPattern(i18nFormatPattern);
    if (LOGGER.isDebugEnabled()) {
      if (callbackFormatPattern == null) {
        throw new IllegalStateException("callbackMmGetFormatPattern() must return valid format pattern");
      }
    }
    return callbackFormatPattern;
  }

  /**
   * Returns the formatted specified view model value, formatted depending on its type, returns otherwise the unformatted view model value.
   *
   * @param         pViewModelValue  The specified view model value.
   * @param         pFormatPattern   The specified format pattern.
   *
   * @return        The formatted view model value, formatted depending on its type.
   *
   * @throws        MmDataModelConverterException  In case of conversion fails.
   *
   * @jalopy.group  group-i18n
   */
  protected Object formatViewModelValue(Object pViewModelValue, String pFormatPattern) {
    if (pViewModelValue == null) {
      return null;

    } else if (pViewModelValue instanceof String) {
      return pViewModelValue;

    } else if ((pViewModelValue instanceof Integer) || (pViewModelValue instanceof Long) || (pViewModelValue.getClass().equals(long.class))
        || (pViewModelValue instanceof Float) || (pViewModelValue.getClass().equals(float.class)) || (pViewModelValue instanceof Double)
        || (pViewModelValue.getClass().equals(double.class))) {
      try {
        final NumberFormat numberFormatter = getMmNumberFormatter(pFormatPattern, false);
        return numberFormatter.format(pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if ((pViewModelValue instanceof BigDecimal) || (pViewModelValue instanceof BigInteger)) {
      try {
        final NumberFormat numberFormatter = getMmNumberFormatter(pFormatPattern, true);
        return numberFormatter.format(pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Duration) {
      try {
        return ((Duration)pViewModelValue).toString();
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Instant) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return dateTimeFormatter.format((Instant)pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalDate) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalDate)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalDateTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalDateTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof ZonedDateTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((ZonedDateTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Enum<?>) {
      final Enum<?> enumValue    = (Enum<?>)pViewModelValue;
      final String  enumTypeName = enumValue.getClass().getSimpleName();
      return root.getMmI18nText(enumTypeName + "." + enumValue.name(), MmMessageType.SHORT);

    } else if ((pViewModelValue instanceof Boolean) || (pViewModelValue.getClass().equals(boolean.class))) {
      if ((Boolean)pViewModelValue) {
        return root.getMmI18nText(getMmId() + ".true", MmMessageType.SHORT);
      } else {
        return root.getMmI18nText(getMmId() + ".false", MmMessageType.SHORT);
      }
    } else {
      return pViewModelValue;
    }
  }

  /**
   * Returns the initialized date formatter of this mimic for specified format pattern..
   *
   * @param         pFormatPattern  The specified format pattern.
   *
   * @return        The initialized date formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected DateTimeFormatter getMmDateTimeFormatter(final String pFormatPattern) {
    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(pFormatPattern);
    return returnDateFormatter;
  }

  /**
   * Returns view text of the link for specified MmInformationable.
   *
   * @param         pInformationable  The specified MmInformationable.
   * @param         pMessageType      The specified pMessageType e.g. SHORT or LONG.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-i18n
   */
  protected String getMmDescription(final MmInformationable pInformationable, final MmMessageType pMessageType) {
    // if view value is MmInformationable, transform into an array of formatted objects
    final Object[] viewValueArray     = ((MmInformationable)pInformationable).getInfo();

    // retrieve array of format patterns
    final String[] formatPatternArray = getMmFormatPattern().split("\\|");

    // apply each format pattern to corresponding view value
    for (int index = 0; index < ((Object[])viewValueArray).length; index++) {
      final Object viewValue = ((Object[])viewValueArray)[index];
      if ((viewValue != null) && !(viewValue instanceof String)) {
        final String formatPattern = formatPatternArray[index];
        viewValueArray[index] = formatViewModelValue(viewValue, formatPattern);
      }
    }

    // viewValueArray keeps an Object[], but as this is an Object as well, java still interprets it to be just one object
    // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
    final String i18nalizedViewValue = getMmI18nText(pMessageType, (Object[])viewValueArray);
    return i18nalizedViewValue;
  }

  /**
   * Returns view text of the link for specified single object.
   *
   * @param         pViewValue    The specified single object.
   * @param         pMessageType  The specified pMessageType e.g. SHORT or LONG.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-i18n
   */
  protected String getMmDescription(final Object pViewValue, final MmMessageType pMessageType) {
    // if view value is a single object

    // return empty String for null value
    if (pViewValue == null) {
      return "";

      // format number, boolean, duration, date, time and enum values
    } else if ((pViewValue instanceof Number) || (pViewValue instanceof Boolean) || (pViewValue instanceof Duration)
        || (pViewValue instanceof Instant) || (pViewValue instanceof LocalTime) || (pViewValue instanceof LocalDate)
        || (pViewValue instanceof LocalDateTime) || (pViewValue instanceof ZonedDateTime) || (pViewValue instanceof Enum<?>)) {

      // retrieve format pattern and format view value
      final String formatPattern           = getMmFormatPattern();
      final String formattedViewModelValue = (String)formatViewModelValue(pViewValue, formatPattern);

      // i18nize view value
      final String i18nalizedViewValue     = getMmI18nText(pMessageType, formattedViewModelValue);
      return i18nalizedViewValue;

      // all other types just i18nize
    } else {
      final String i18nalizedViewValue = getMmI18nText(pMessageType, pViewValue);
      return i18nalizedViewValue;
    }
  }

  /**
   * Returns the initialized number formatter of this mimic for specified format pattern.
   *
   * @param         pFormatPattern    The specified format pattern.
   * @param         pParseBigDecimal  True, if
   *
   * @return        The initialized number formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected NumberFormat getMmNumberFormatter(final String pFormatPattern, boolean pParseBigDecimal) {
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(pParseBigDecimal);
    returnNumberFormatter.applyPattern(pFormatPattern);
    return returnNumberFormatter;
  }

}
