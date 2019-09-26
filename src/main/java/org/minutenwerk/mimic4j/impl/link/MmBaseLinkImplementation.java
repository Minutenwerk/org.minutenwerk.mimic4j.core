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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.Date;
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
   * @throws        IllegalStateException  In case of callbackMmGetLongDescription() returns null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    // retrieve model
    final DATA_MODEL model          = getMmModel();

    // retrieve data model value
    final VIEW_MODEL dataModelValue = getMmViewModel();

    String           returnString   = null;
    if (model == null) {
      final String i18nLongDescription = getMmI18nText(MmMessageType.LONG, dataModelValue);
      returnString = declaration.callbackMmGetLongDescription(i18nLongDescription, dataModelValue);
    } else {
      final String i18nLongDescription = getMmI18nText(MmMessageType.LONG, dataModelValue, model);
      returnString = declaration.callbackMmGetLongDescription(i18nLongDescription, dataModelValue, model);
    }
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetLongDescription cannot return null for " + this);
      }
    }
    return returnString;
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
   * @throws        IllegalArgumentException  TODOC
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

    // if view model is MmInformationable
    Object[] viewModelArray = null;
    if (viewModel instanceof MmInformationable) {
      viewModelArray = ((MmInformationable)viewModel).getInfo();
      for (int index = 0; index < ((Object[])viewModelArray).length; index++) {
        viewModelArray[index] = transformObjectForFormattingByMessageSource(((Object[])viewModelArray)[index]);
      }
      
      // if view model is an array of objects
    } else if (viewModel instanceof Object[]){
      // transform object into array of objects
      viewModelArray = new Object[((Object[])viewModel).length];
      for (int index = 0; index < ((Object[])viewModel).length; index++) {
        viewModelArray[index] = transformObjectForFormattingByMessageSource(((Object[])viewModel)[index]);
      }
    }

    // if model is an array
    if (viewModelArray != null) {
      // viewModelArray keeps an Object[], but because it is of type Object, java still interprets it to be just one object
      // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
      final String i18nViewModelValue = getMmI18nText(MmMessageType.SHORT, (Object[])viewModelArray);
      return i18nViewModelValue;

      // if model is a single object
    } else {

      // return empty String for null value
      if (viewModel == null) {
        return "";

        // pass through Strings
      } else if (viewModel instanceof String) {
        return (String)viewModel;

        // i18n single enums
      } else if (viewModel instanceof Enum<?>) {

        // translate enum values to i18n strings before, because MessageFormat shall not do this
        final String i18nViewModelValue = formatViewModelValue(viewModel);
        return i18nViewModelValue;

        // format number, date and time values
//      } else if ((viewModel instanceof Number) || (viewModel instanceof Boolean) || (viewModel instanceof Duration)
//          || (viewModel instanceof Instant) || (viewModel instanceof LocalTime) || (viewModel instanceof LocalDate)
//          || (viewModel instanceof LocalDateTime) || (viewModel instanceof ZonedDateTime)) {
//
//        final String formattedViewModelValue = formatViewModelValue(viewModel);
//        return formattedViewModelValue;

        // all other single objects translate to i18n
      } else {
        final String i18nViewModelValue = getMmI18nText(MmMessageType.SHORT, viewModel);
        return i18nViewModelValue;
      }
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
   * Returns the specified view model value, formatted depending on its type.
   *
   * @param         pViewModelValue  The specified view model value.
   *
   * @return        The formatted view model value, formatted depending on its type.
   *
   * @throws        MmDataModelConverterException  In case of conversion fails.
   * @throws        IllegalArgumentException       In case of conversion fails.
   *
   * @jalopy.group  group-i18n
   */
  protected String formatViewModelValue(Object pViewModelValue) {
    String formattedValue = "";
    if (pViewModelValue != null) {

      if (pViewModelValue instanceof String) {
        formattedValue = (String)pViewModelValue;

      } else if ((pViewModelValue instanceof Integer) || (pViewModelValue instanceof Long) || (pViewModelValue instanceof Float)
          || (pViewModelValue instanceof Double)) {
        try {
          final NumberFormat numberFormatter = getMmNumberFormatter(false);
          formattedValue = numberFormatter.format(pViewModelValue);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if ((pViewModelValue instanceof BigDecimal) || (pViewModelValue instanceof BigInteger)) {
        try {
          final NumberFormat numberFormatter = getMmNumberFormatter(true);
          formattedValue = numberFormatter.format(pViewModelValue);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof Duration) {
        try {
          formattedValue = ((Duration)pViewModelValue).toString();
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof Instant) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = dateTimeFormatter.format((Instant)pViewModelValue);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof LocalTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalTime)pViewModelValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof LocalDate) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalDate)pViewModelValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof LocalDateTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalDateTime)pViewModelValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof ZonedDateTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((ZonedDateTime)pViewModelValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmDataModelConverterException(this,
            "Cannot format " + this + ", view model value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
        }

      } else if (pViewModelValue instanceof Enum<?>) {
        final Enum<?> enumValue    = (Enum<?>)pViewModelValue;
        final String  enumTypeName = enumValue.getClass().getSimpleName();
        formattedValue = root.getMmI18nText(enumTypeName + "." + enumValue.name(), MmMessageType.SHORT);

      } else if (pViewModelValue instanceof Boolean) {
        if ((Boolean)pViewModelValue) {
          formattedValue = root.getMmI18nText(getMmId() + ".true", MmMessageType.SHORT);
        } else {
          formattedValue = root.getMmI18nText(getMmId() + ".false", MmMessageType.SHORT);
        }
      } else {
        throw new IllegalArgumentException("unknown view model value type cannot be formatted " + pViewModelValue);
      }
    }
    return formattedValue;
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return        The initialized date formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected DateTimeFormatter getMmDateTimeFormatter() {
    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(getMmFormatPattern());
    return returnDateFormatter;
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @param         pParseBigDecimal  True, if
   *
   * @return        The initialized number formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected NumberFormat getMmNumberFormatter(boolean pParseBigDecimal) {
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(pParseBigDecimal);
    returnNumberFormatter.applyPattern(getMmFormatPattern());
    return returnNumberFormatter;
  }

  /**
   * Transform specialized types of dates into java.util.date so MessageFormat can format them by rules like "wurde am {1,date,dd.MM.yyy}
   * von".
   *
   * @param   pObject  Enum or specialized types of date.
   *
   * @return  String or java.util.Date.
   */
  protected Object transformObjectForFormattingByMessageSource(final Object pObject) {
    // return empty String for null value
    if (pObject == null) {
      return "";

      // translate enum values to i18n strings before, because MessageFormat shall not do this
    } else if (pObject instanceof Enum<?>) {
      return formatViewModelValue(pObject);

      // transform Instant values to java.util.Date
    } else if (pObject instanceof Instant) {
      return Date.from(((Instant)pObject));

      // transform LocalDate values to java.util.Date
    } else if (pObject instanceof LocalDate) {
      return Date.from(((LocalDate)pObject).atStartOfDay(ZoneId.of("UTC")).toInstant());

      // transform LocalDateTime values to java.util.Date
    } else if (pObject instanceof LocalDateTime) {
      return Date.from(((LocalDateTime)pObject).toInstant(ZoneOffset.UTC));

      // transform ZonedDateTime values to java.util.Date
    } else if (pObject instanceof ZonedDateTime) {
      return Date.from(((ZonedDateTime)pObject).toInstant());

      // all other objects pass through
    } else {
      return pObject;
    }
  }

}
