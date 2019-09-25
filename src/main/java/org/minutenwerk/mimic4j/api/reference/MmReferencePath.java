package org.minutenwerk.mimic4j.api.reference;

import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_ADD;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_CREATE;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_DELETE;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_EDIT;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_REMOVE;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_REPLACE;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_POST_PARAM_SAVE;

import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * MmReferencePath is an immutable class to deliver the fixed parts of an URL.
 */
public abstract class MmReferencePath {

  /** URI Path. */
  protected final String        path;

  /** Request method to be used to call this reference. */
  protected final RequestMethod requestMethod;

  /** Name of query parameter in case of POST method. */
  protected final String        postParam;

  /**
   * Creates a new MmReferencePath instance.
   *
   * @param  pPath           URI Path.
   * @param  pRequestMethod  Request method to be used to call this reference.
   * @param  pPostParam      Name of query parameter in case of POST method.
   */
  public MmReferencePath(String pPath, RequestMethod pRequestMethod, String pPostParam) {
    path          = pPath;
    requestMethod = pRequestMethod;
    postParam     = pPostParam;
  }

  /**
   * Returns URI Path.
   *
   * @return  URI Path.
   */
  public final String getPath() {
    return path;
  }

  /**
   * Returns name of query parameter in case of POST method.
   *
   * @return  name of query parameter in case of POST method.
   */
  public final String getPostParam() {
    return postParam;
  }

  /**
   * Returns request method to be used to call this reference.
   *
   * @return  request method to be used to call this reference.
   */
  public final RequestMethod getRequestMethod() {
    return requestMethod;
  }

  /**
   * True, if this reference path is the default.
   *
   * @return  true, if this reference path is the default.
   */
  public final boolean isDefault() {
    return this instanceof DefaultItem;
  }

  /**
   * True, if this reference path must be called by {@link RequestMethod.GET}.
   *
   * @return  true, if this reference path must be called by {@link RequestMethod.GET}.
   */
  public final boolean isGetMethod() {
    return requestMethod == GET;
  }

  /**
   * True, if this reference path must be called by {@link RequestMethod.POST}.
   *
   * @return  true, if this reference path must be called by {@link RequestMethod.POST}.
   */
  public final boolean isPostMethod() {
    return requestMethod == POST;
  }

  public static final class AddItem extends MmReferencePath {

    public AddItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_ADD);
    }
  }

  public static final class CreateItem extends MmReferencePath {

    public CreateItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_CREATE);
    }
  }

  public static final class DefaultItem extends MmReferencePath {

    public DefaultItem() {
      super("/", GET, null);
    }
  }

  public static final class DeleteItem extends MmReferencePath {

    public DeleteItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_DELETE);
    }
  }

  public abstract static class Display extends MmReferencePath {

    public Display(String pPath) {
      super(pPath, GET, null);
    }
  }

  public static final class DisplayItem extends Display {

    public DisplayItem(String pPath) {
      super(pPath);
    }
  }

  public static final class DisplayList extends Display {

    public DisplayList(String pPath) {
      super(pPath);
    }
  }

  public static final class EditItem extends MmReferencePath {

    public EditItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_EDIT);
    }
  }

  public static final class RemoveItem extends MmReferencePath {

    public RemoveItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_REMOVE);
    }
  }

  public static final class ReplaceItem extends MmReferencePath {

    public ReplaceItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_REPLACE);
    }
  }

  public static final class SaveItem extends MmReferencePath {

    public SaveItem(String pPath) {
      super(pPath, POST, MIMIC4J_DIALECT_POST_PARAM_SAVE);
    }
  }
}
