package org.minutenwerk.mimic4j.impl.view;

import java.util.List;

import javax.faces.model.SelectItem;

import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract class MmJsfBridge and its descendant classes connect a mimic and a JSF view component. MmJsfBridge implements all methods
 * which are used by any descendant class, but it contains sensible implementations just for the basic cases, which are used by all
 * descendants. All other methods of MmJsfBridge throw an {@link UnsupportedOperationException}. This way the base class MmJsfBridge can be
 * used like an interface.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public abstract class MmJsfBridge<IMPLEMENTATION extends MmBaseImplementation<?, ?>, VIEWSIDE_VALUE, MODEL> {

  /** Logger of this class. */
  private static final Logger    LOGGER         = LoggerFactory.getLogger(MmJsfBridge.class);

  /** Reference to implementation part of connected mimic. */
  protected final IMPLEMENTATION implementation;

  /**
   * Creates a new MmJsfBridge instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridge(IMPLEMENTATION pImplementation) {
    this.implementation = pImplementation;
  }

  /**
   * A static helper method to evaluate conditions in expression language (EL).
   *
   * @param   pCondition    The condition to be evaluated, must return true or false.
   * @param   pTrueReturn   The object to use in case the condition is true.
   * @param   pFalseReturn  The object to use in case the condition is false.
   *
   * @return  The object to use dependent on condition.
   *
   * @since   $maven.project.version$
   */
  public static final Object conditionalValue(boolean pCondition, Object pTrueReturn, Object pFalseReturn) {
    return pCondition ? pTrueReturn : pFalseReturn;
  }

  /**
   * Returns an EL expression of type String, which triggers JSF to execute a Java method, usually invoked by a HTML button.
   *
   * @return  An EL expression of type String.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String action() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag action");
  }

  /**
   * Compares the id of the mimic and the id of XHTML tag, possibly sets mimic id, and returns the evaluated view id. Following cases can
   * occur:
   *
   * <ul>
   *   <li>Success: mimic id and XHTML id both exist and are identical</li>
   *   <li>Error : mimic id and XHTML id both don't exist: mimic id is undefined, view id is generated by JSF</li>
   *   <li>Error : mimic id and XHTML both exist, but are different: mimic id is used for mimic, but XHTML id is used for JSF view</li>
   *   <li>Warning: mimic id exists, but XHTML id is missing: mimic id is used for JSF view id</li>
   *   <li>Warning: XHTML id exists, but mimic id is missing: XHTML id is used for both JSF view and mimic</li>
   * </ul>
   *
   * @param   pExternalId  The declared XHTML id.
   *
   * @return  The evaluated XHTML id to use.
   *
   * @since   $maven.project.version$
   */
  public String evalId(String pExternalId) {
    final boolean externalIdMissing = ((pExternalId == null) || pExternalId.trim().isEmpty());
    final boolean externalIdExists  = !externalIdMissing;
    final String  externalId        = externalIdExists ? pExternalId.trim() : "";

    final String  internalId        = this.implementation.getMmId().trim();
    final boolean internalIdMissing = internalId.isEmpty() || internalId.equals(MmBaseConfiguration.UNDEFINED_ID);
    final boolean internalIdExists  = !internalIdMissing;
    final String  returnID          = internalIdExists ? internalId : (externalIdExists ? externalId : "undefinedIdInMmJsfBridge");

    if (externalIdMissing && internalIdMissing) {
      LOGGER.error("missing id of mimic and of XHTML for: " + this.implementation.toString());

    } else if (externalIdMissing && internalIdExists) {
      LOGGER.warn("missing id of XHTML for: " + this.implementation.toString());

    } else if (externalIdExists && internalIdMissing) {

      // ATTENTION: sets mimic id to value of XHTML id!
      this.implementation.getConfiguration().setId(externalId);
      LOGGER.warn("missing id of mimic, set from XHTML for: " + this.implementation.toString());

    } else if (externalIdExists && internalIdExists) {
      if (!externalId.equals(internalId)) {
        LOGGER.error("different id of mimic <" + internalId + "> and of XHTML <" + externalId + "> for: " + this.implementation.toString());
      }
    }
    return returnID;
  }

  /**
   * Returns value of JSF tag attribute border.
   *
   * @return  The value of JSF tag attribute border.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getBorder() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag border");
  }

  /**
   * Returns the caption text of a table (tag caption in tag h:dataTable).
   *
   * @return  The caption text of a table.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getCaption() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag caption");
  }

  /**
   * Returns the number of columns of a multiline text field (attribute {@code}cols{@code}).
   *
   * @return  The number of columns of a multiline text field.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public int getCols() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag cols");
  }

  /**
   * Returns the CSS style classes of columns, one style class for each column, comma separated.
   *
   * @return  The CSS style classes of columns.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getColumnClasses() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag columnClasses");
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public List<MmTableColumn> getColumnMms() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag columnMms");
  }

  /**
   * Returns CSS selector for data parents of leporello panel, like in (data-toggle="collapse" data-target="#target1,#target2,#target3").
   *
   * @return  The CSS selector for data parents of leporello panel.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getDataParents() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag dataParents");
  }

  /**
   * Returns the CSS style class of this column's footer.
   *
   * @return  The CSS style class of this column's footer.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getFooterClass() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag footerClass");
  }

  /**
   * Returns the CSS style class of this column's header.
   *
   * @return  The CSS style class of this column's header.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getHeaderClass() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag headerClass");
  }

  /**
   * Returns the text of this column's header title (displayed by mouse over).
   *
   * @return  The text of this column's header title.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getHeaderTitle() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag headerTitle");
  }

  /**
   * Returns the text of this column's header.
   *
   * @return  The text of this column's header.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getHeaderValue() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag headerValue");
  }

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return  The id of this mimic.
   *
   * @since   $maven.project.version$
   */
  public String getId() {
    return this.implementation.getMmId();
  }

  /**
   * Returns the name of JSF tag to be used by this mimic. The JSF tag is usually configured by annotation. Each mimic has a jsf tag for
   * enabled state and a JSF tag for disabled state.
   *
   * @return  The name of JSF tag to be used by this mimic.
   *
   * @since   $maven.project.version$
   */
  public String getJsfTag() {
    return this.implementation.getJsfTag();
  }

  /**
   * Returns the label of HTML tag, usually the short description of mimic.
   *
   * @return  The label of HTML tag.
   *
   * @since   $maven.project.version$
   */
  public String getLabel() {
    return this.implementation.getMmShortDescription();
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getLayout() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag layout");
  }

  /**
   * Returns the leporello id, which is used as data parent.
   *
   * @return  The leporello id.
   *
   * @throws  UnsupportedOperationException  TODOC
   *
   * @since   $maven.project.version$
   */
  public String getLeporelloId() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag leporelloId");
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public int getMaxlength() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag maxlength");
  }

  /**
   * Returns a list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @return  A list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public List<MmMessage> getMsgList() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag msgList");
  }

  /**
   * Returns one string containing all error, warning, info and success messages of this mimic.
   *
   * @return  One string containing all error, warning, info and success messages of this mimic.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getMsgListText() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag msgListText");
  }

  /**
   * Returns the highest severity of error message of this mimic, returns empty string in case of no messages.
   *
   * @return  The highest severity of error message of this mimic.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getMsgMaxSeverity() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag msgMaxSeverity");
  }

  /**
   * Returns the title of error message of highest severity of this mimic.
   *
   * @return  The title of error message of highest severity of this mimic.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public String getMsgMaxTitle() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag msgMaxTitle");
  }

  /**
   * Returns a self reference of this mimic, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return  A self reference of this mimic.
   *
   * @since   $maven.project.version$
   */
  public MmReference getReference() {
    return this.implementation.getMmReference();
  }

  /**
   * Returns the number of rows of a multiline text field (attribute {@code}cols{@code}).
   *
   * @return  The number of rows of a multiline text field.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public int getRows() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag rows");
  }

  /**
   * Returns a list of {@link SelectItem}, which are displayed as drop down menu or list box.
   *
   * <ul>
   *   <li>value - Value to be delivered to the model if this item is selected by the user</li>
   *   <li>label - Label to be rendered for this item in the response</li>
   *   <li>description - Description of this item, for use in tools</li>
   *   <li>disabled - Flag indicating that this option is disabled</li>
   *   <li>escape - Flag indicating that the text of this option should be escaped when rendered.</li>
   *   <li>noSelectionOption - Flag indicating that the current option is a "no selection" option</li>
   * </ul>
   *
   * @return  A list of {@link SelectItem},
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public List<SelectItem> getSelectItems() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag selectItems");
  }

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public int getSize() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag size");
  }

  /**
   * Returns the style classes of HTML attribute, as configured and evaluated from mimic. There might be added more style classes from XHTML
   * tag.
   *
   * @return  The style classes of HTML attribute.
   *
   * @since   $maven.project.version$
   */
  public String getStyleClass() {
    return this.implementation.getMmStyleClasses();
  }

  /**
   * Returns the CSS style class for initial opening of leporello panel.
   *
   * @return  The CSS style class for initial opening of leporello panel.
   *
   * @throws  UnsupportedOperationException  Because operation is not supported for this mimic.
   *
   * @since   $maven.project.version$
   */
  public String getStyleInitiallyOpen() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag styleInitiallyOpen");
  }

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator. May be used in combination with
   * getMmTargetRefParams().
   *
   * @return  A reference to some target.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public MmReference getTargetReference() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag targetReference");
  }

  /**
   * Returns the title attribute of HTML tag, usually the long description of mimic.
   *
   * @return  The title attribute of HTML tag.
   *
   * @since   $maven.project.version$
   */
  public String getTitle() {
    return this.implementation.getMmLongDescription();
  }

  /**
   * Returns the value of type VIEWSIDE_VALUE from mimic, to be displayed in HTML tag.
   *
   * @return  The value of type VIEWSIDE_VALUE from mimic, to be displayed in HTML tag.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public VIEWSIDE_VALUE getValue() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag value");
  }

  /**
   * Returns true, if the HTML tag shall be displayed in disabled state.
   *
   * @return  True, if the HTML tag shall be displayed in disabled state.
   *
   * @since   $maven.project.version$
   */
  public boolean isDisabled() {
    return !this.implementation.isMmEnabled() || this.implementation.isMmReadOnly();
  }

  /**
   * Returns true, if the user's browser has disabled Javascript language.
   *
   * @return  True, if the user's browser has disabled Javascript language.
   *
   * @since   $maven.project.version$
   */
  public boolean isJsDisabled() {
    return !this.isJsEnabled();
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   *
   * @since   $maven.project.version$
   */
  public boolean isJsEnabled() {
    return this.implementation.isMmJsEnabled();
  }

  /**
   * Returns true, if the leporello panel body shall be displayed.
   *
   * @return  True, if the leporello panel body shall be displayed.
   *
   * @throws  UnsupportedOperationException  Because operation is not supported for this mimic.
   *
   * @since   $maven.project.version$
   */
  public boolean isPanelBodyVisible() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag isPanelBodyVisible");
  }

  /**
   * Returns true, if the leporello panel heading shall be displayed.
   *
   * @return  True, if the leporello panel heading shall be displayed.
   *
   * @throws  UnsupportedOperationException  Because operation is not supported for this mimic.
   *
   * @since   $maven.project.version$
   */
  public boolean isPanelHeadingVisible() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag isPanelHeadingVisible");
  }

  /**
   * Returns true, if the HTML tag shall be displayed.
   *
   * @return  True, if the HTML tag shall be displayed.
   *
   * @since   $maven.project.version$
   */
  public boolean isRendered() {
    return this.implementation.isMmVisible();
  }

  /**
   * Returns true, if this column is a row header column of the table.
   *
   * @return  True, if this column is a row header column of the table.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public boolean isRowHeader() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag rowHeader");
  }

  /**
   * Returns true, if special characters in displayed text content shall by escaped.
   *
   * @return  True, if special characters in displayed text content shall by escaped.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public boolean isTextEscape() {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag textEscape");
  }

  /**
   * Sets the value of type VIEWSIDE_VALUE into mimic, usually called by input HTML tags.
   *
   * @param   pValue  The value of type VIEWSIDE_VALUE to be set.
   *
   * @throws  UnsupportedOperationException  in case the method is not supported by MmJsfBridge and its concrete subclass.
   *
   * @since   $maven.project.version$
   */
  public void setValue(VIEWSIDE_VALUE pValue) {
    throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not support JSF tag setValue");
  }

}
