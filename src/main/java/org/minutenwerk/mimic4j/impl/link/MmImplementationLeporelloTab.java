package org.minutenwerk.mimic4j.impl.link;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import java.util.Date;

import org.minutenwerk.mimic4j.api.MmInformationable;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTabAnnotation;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLeporelloTab;

/**
 * MmImplementationLeporelloTab is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model
 * delivers the text label of the link.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization
 */
public class MmImplementationLeporelloTab<DATA_MODEL extends MmReferencableModel, VIEW_MODEL>
  extends MmBaseLinkImplementation<MmLeporelloTab<DATA_MODEL, VIEW_MODEL>,
    DATA_MODEL, VIEW_MODEL, MmConfigurationLeporelloTab, MmLeporelloTabAnnotation> {

  /** The super tab is the logical parent tab in the parent panel. */
  protected final MmLeporelloTab<DATA_MODEL, VIEW_MODEL> superTab;

  /** The parent leporello of this leporello tab. */
  protected MmImplementationLeporello<?, ?>              parentLeporello;

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel) {
    this(pParentPanel, null);
  }

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pSuperTab     The super tab is the logical parent tab in the parent panel.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel, MmLeporelloTab<DATA_MODEL, VIEW_MODEL> pSuperTab) {
    super(pParentPanel);
    superTab = pSuperTab;
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param         pAnnotation  The specified annotation, may be null.
   *
   * @return        Configuration of this mimic.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected MmConfigurationLeporelloTab onConstructConfiguration(MmLeporelloTabAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLeporelloTab(pAnnotation);
    } else {
      return new MmConfigurationLeporelloTab();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeLeporelloTab(this);
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call
   * super.onInitialization()!
   *
   * @jalopy.group  group-initialization
   */
  @Deprecated
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // TODO MmImplementationLeporelloTab move this into constructor and delete initialize method here
    parentLeporello = getMmImplementationAncestorOfType(MmImplementationLeporello.class);
  }

  /**
   * Returns the CSS styleclass if this leporello tab is active.
   *
   * @return  The CSS styleclass if this leporello tab is active.
   */
  public String getMmStyleClassActive() {
    assureInitialization();

    if (isMmActive()) {
      return "active";
    } else {
      return "";
    }
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   */
  @Override
  public String getMmStyleClasses() {
    assureInitialization();

    String styleClasses = super.getMmStyleClasses();
    if (!isMmEnabled()) {
      return "disabled " + styleClasses;
    }
    return styleClasses;
  }

  /**
   * Returns the view tab of this leporello panel tab.
   *
   * @return  The view tab of this leporello panel tab.
   */
  public MmTab<?> getMmViewTab() {
    assureInitialization();

    // TODO MmImplementationLeporelloTab getMmViewTab
    return null;
  }

  /**
   * Returns view text of the link.
   *
   * @return  The view text of the link.
   */
  @Override
  public String getMmViewValue() {
    assureInitialization();

    // retrieve view model
    final VIEW_MODEL viewModel = getMmViewModel();

    // retrieve data model
    final Object     dataModel = (viewModel instanceof MmInformationable) //
      ? ((MmInformationable)viewModel).getInfo() //
      : viewModel;

    // if model is an array of objects
    if (dataModel instanceof Object[]) {

      // translate enum values to i18n strings before, because MessageFormat shall not do this
      for (int index = 0; index < ((Object[])dataModel).length; index++) {
        final Object dataModelObject = ((Object[])dataModel)[index];
        if (dataModelObject instanceof Enum<?>) {
          final String i18nEnumValue = formatDataModelValue(dataModelObject);
          ((Object[])dataModel)[index] = i18nEnumValue;
        }
      }

      // data model keeps an Object[], but because it is of type Object, java still interprets it to be just one object
      // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
      final String i18nViewModelValue = getMmI18nText(MmMessageType.SHORT, (Object[])dataModel);
      return i18nViewModelValue;

      // if model is a single object
    } else {

      // return empty String for null value
      if (dataModel == null) {
        return "";

        // pass through Strings
      } else if (dataModel instanceof String) {
        return (String)dataModel;

        // i18n single enums
      } else if (dataModel instanceof Enum<?>) {

        // translate enum values to i18n strings before, because MessageFormat shall not do this
        final String i18nViewModelValue = formatDataModelValue(dataModel);
        return i18nViewModelValue;

        // format Instant values
      } else if (dataModel instanceof Instant) {

        final Date   dataModelValueAsJavaUtilDate = Date.from(((Instant)dataModel));
        final String formattedViewModelValue      = formatDataModelValue(dataModelValueAsJavaUtilDate);
        return formattedViewModelValue;

        // format LocalDate values
      } else if (dataModel instanceof LocalDate) {

        final Date   dataModelValueAsJavaUtilDate = Date.from(((LocalDate)dataModel).atStartOfDay(ZoneId.of("UTC")).toInstant());
        final String formattedViewModelValue      = formatDataModelValue(dataModelValueAsJavaUtilDate);
        return formattedViewModelValue;

        // format LocalDateTime values
      } else if (dataModel instanceof LocalDateTime) {

        final Date   dataModelValueAsJavaUtilDate = Date.from(((LocalDateTime)dataModel).toInstant(ZoneOffset.UTC));
        final String formattedViewModelValue      = formatDataModelValue(dataModelValueAsJavaUtilDate);
        return formattedViewModelValue;

        // format ZonedDateTime values
      } else if (dataModel instanceof ZonedDateTime) {

        final Date   dataModelValueAsJavaUtilDate = Date.from(((ZonedDateTime)dataModel).toInstant());
        final String formattedViewModelValue      = formatDataModelValue(dataModelValueAsJavaUtilDate);
        return formattedViewModelValue;

        // all other single objects translate to i18n
      } else {
        final String i18nViewModelValue = getMmI18nText(MmMessageType.SHORT, dataModel);
        return i18nViewModelValue;
      }
    }
  }

  /**
   * Returns <code>true</code>, if the tab is active.
   *
   * @return  <code>True</code>, if the tab is active.
   */
  public boolean isMmActive() {
    assureInitialization();

    final MmLeporelloTab<?, ?> thisDeclaration = (MmLeporelloTab<?, ?>)declaration;
    final MmLeporelloTab<?, ?> selectedTab     = parentLeporello.getMmSelectedTab();
    if (selectedTab == null) {
      return false;
    } else if (selectedTab == thisDeclaration) {
      return true;
    } else {
      final MmImplementationLeporelloTab<?, VIEW_MODEL> selectedTabImplementation = MmBaseImplementation.getImplementation(selectedTab);
      MmLeporelloTab<?, VIEW_MODEL>                     superTabOfSelected        = selectedTabImplementation.superTab;
      while (superTabOfSelected != null) {
        if (thisDeclaration == superTabOfSelected) {
          return true;
        }

        final MmImplementationLeporelloTab<?, VIEW_MODEL> superTabOfSelectedImplementation = MmBaseImplementation.getImplementation(
            superTabOfSelected);
        superTabOfSelected = superTabOfSelectedImplementation.superTab;
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code>, if the tab is selected.
   *
   * @return  <code>True</code>, if the tab is selected.
   */
  public boolean isMmSelected() {
    assureInitialization();

    final MmLeporelloTab<?, ?> thisDeclaration = (MmLeporelloTab<?, ?>)declaration;
    final MmLeporelloTab<?, ?> selectedTab     = parentLeporello.getMmSelectedTab();
    return (thisDeclaration == selectedTab);
  }

}
