package org.minutenwerk.mimic4j.impl;

import java.io.StringWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationRoot;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmBaseImplementation is the abstract base class for the implementation part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-construction, group-naming, group-initialization, group-override, group-helper
 */
public abstract class MmBaseImplementation<DECLARATION extends MmBaseDeclaration<?, ?>, CONFIGURATION extends MmBaseConfiguration>
  implements MmMimic {

  /** Constant for index of generic type of configuration. */
  public static final int     GENERIC_PARAMETER_INDEX_CONFIGURATION = 2;

  /** End of line characters of operating system in use. */
  public static final String  NL                                    = System.getProperty("line.separator");

  /** Logger of this class. */
  private static final Logger LOGGER                                = LogManager.getLogger(MmBaseImplementation.class);

  /**
   * Enumeration of states during initialization phase.
   *
   * @author  Olaf Kossak
   */
  protected enum MmInitialState {

    /** Mimic instance constructors are in execution. */
    IN_CONSTRUCTION,

    /** Mimic instance constructors are executed. */
    CONSTRUCTION_COMPLETE,

    /** Mimic initialize method is in execution. */
    IN_INITIALIZATION,

    /** Mimic is initialized. */
    INITIALIZED
  }

  /** The state of initialization during initialization phase. */
  protected MmInitialState                         initialState                  = MmInitialState.IN_CONSTRUCTION;

  /** The name of this mimic is evaluated during initialization phase of its parent mimic. */
  protected String                                 name;

  /** True, if this mimic is instantiated at runtime, false if it is declared at compile time. */
  protected boolean                                isRuntimeChild;

  /** The type of first optional generic parameter of this mimic is evaluated during initialization phase of its parent mimic. */
  protected Type                                   typeOfFirstGenericParameter;

  /** The parentPath of this mimic is evaluated during initialization phase of its parent mimic. */
  protected String                                 parentPath;

  /** The configuration of fixed values. */
  protected CONFIGURATION                          configuration;

  /** The <code>MmRoot</code> is a root element of a subtree. */
  protected final MmImplementationRoot             root;

  /** The declaration part of this implementation is the declaration. */
  protected DECLARATION                            declaration;

  /** This or an ancestor mimic, which delivers a reference path, file and params. May be null. */
  protected MmReferencableMimic<?>                 referencableAncestor;

  /** The parent mimic is another instance of this class. */
  protected final MmBaseImplementation<?, ?>       implementationParent;

  /** The parent mimic of the declaration is itself of type <code>MmBaseDeclaration</code>. */
  protected final MmBaseDeclaration<?, ?>          declarationParent;

  /** All direct children are instances of this class <code>MmBaseImplementation</code>. */
  protected final List<MmBaseImplementation<?, ?>> implementationChildren;

  /** All direct children of the declaration are of type <code>MmBaseDeclaration</code>. */
  protected final List<MmMimic>                    declarationChildren;

  /** All runtime children are instances of this class <code>MmBaseImplementation</code>. */
  protected final List<MmBaseImplementation<?, ?>> runtimeImplementationChildren;

  /** All runtime declaration children are instances of this class <code>MmBaseDeclaration</code>. */
  protected final List<MmMimic>                    runtimeDeclarationChildren;

  /** Tthe MmJsfBridge of this mimic, which connects it to a JSF view component. */
  protected final MmJsfBridge<?, ?, ?>             mmJsfBridge;

  /**
   * Creates a new MmCompositeImplementation instance.
   *
   * @param   pDeclarationParent  The declaration part of the parent mimic.
   *
   * @throws  IllegalStateException  in case of parameter pDeclarationParent does not have an implementation part, or root ancestor of
   *                                 subtree is not of type {@link MmImplementationRoot}.
   */
  public MmBaseImplementation(MmDeclarationMimic pDeclarationParent) {
    assert this.initialState == MmInitialState.IN_CONSTRUCTION : "Initial state must be IN_CONSTRUCTION";

    this.name       = "";
    this.parentPath = "";

    if (pDeclarationParent == null) {
      // if this is a MmRoot, there is no parent

      // set reference to declaration part of parent mimic to null
      this.declarationParent    = null;

      // set reference to implementation part of parent mimic to null
      this.implementationParent = null;

      // set reference to root to null
      this.root                 = null;

    } else {

      if (!(pDeclarationParent instanceof MmBaseDeclaration<?, ?>)) {
        throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent
          + " must be instanceof MmBaseDeclaration<?,?>");
      }

      final MmBaseDeclaration<?, ?> declarationParent = (MmBaseDeclaration<?, ?>)pDeclarationParent;

      // set reference to declaration part of parent mimic
      this.declarationParent = declarationParent;

      // set reference to implementation part of parent
      if (declarationParent.implementation == null) {
        throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent + " must have an implementation");
      }
      this.implementationParent = declarationParent.implementation;

      // evaluate reference to root
      // evaluate root ancestor
      MmBaseImplementation<?, ?> temp = this.implementationParent;
      while (temp.implementationParent != null) {
        temp = temp.implementationParent;
      }

      // if declaration part of root ancestor is of type MmRoot
      if (MmImplementationRoot.class.isAssignableFrom(temp.getClass())) {

        // set reference to implementation part of MmRoot
        this.root = (MmImplementationRoot)temp;
      } else {
        throw new IllegalStateException("root ancestor of subtree must be of type MmImplementationRoot");
      }
    }

    // create lists for child mimics
    this.implementationChildren        = new ArrayList<>();
    this.declarationChildren           = new ArrayList<>();
    this.runtimeImplementationChildren = new ArrayList<>();
    this.runtimeDeclarationChildren    = new ArrayList<>();

    // create bridge for jsf tags
    this.mmJsfBridge                   = this.createMmJsfBridge();

    assert this.declaration == null : "Instance variable declaration must be still undefined";
    assert this.name.isEmpty() : "Instance variable name must be still empty";
    assert this.parentPath.isEmpty() : "Instance variable parentPath must be still empty";
  }

  /**
   * Logs debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param         pMm           The specific mimic to log.
   * @param         pIndentation  Level of indentation in log.
   *
   * @jalopy.group  group-initialization
   */
  protected static void logSubtree(MmBaseImplementation<?, ?> pMm, String pIndentation) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(pIndentation + pMm.toString());
      for (MmBaseImplementation<?, ?> child : pMm.implementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
      for (MmBaseImplementation<?, ?> child : pMm.runtimeImplementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
    }
  }

  /**
   * Returns the implementation part of the specified declaration.
   *
   * @param         pDeclaration  The specified declaration.
   *
   * @return        The implementation part of the specified declaration.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  protected static <T extends MmBaseImplementation<?, ?>> T getImplementation(MmBaseDeclaration<?, ?> pDeclaration) {
    return (T)pDeclaration.implementation;
  }

  /**
   * Reads the Java-Generics parameter having the given index position (starting at ONE) from the given {@link Class}.
   *
   * @param   pClassToAnalyze  The class to analyze.
   * @param   pGenericRawType  The class containing the generic.
   * @param   pParameterIndex  The parameter position index. Starts with One for the first parameter.
   *
   * @return  The found Java-Generics parameter. <code>null</code> if none was found.
   */
  @SuppressWarnings("unchecked")
  public static <T extends Type> T findGenericsParameterType(Class<?> pClassToAnalyze, Class<?> pGenericRawType, int pParameterIndex) {
    final int  parameterIndex    = pParameterIndex - 1;
    final Type genericSuperclass = pClassToAnalyze.getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      final ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
      final Type              rawType           = parameterizedType.getRawType();
      if (rawType.equals(pGenericRawType)) {
        Type[] typeArray = parameterizedType.getActualTypeArguments();
        if (typeArray.length > parameterIndex) {
          return (T)typeArray[parameterIndex];
        } else {
          return null;
        }
      } else {
        Class<?> superClass = pClassToAnalyze.getSuperclass();
        if (superClass != null) {
          return (T)findGenericsParameterType(superClass, pGenericRawType, parameterIndex);
        } else {
          return null;
        }
      }
    } else {
      return null;
    }
  }

  /**
   * Returns debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param   pMm           The specific mimic to log.
   * @param   pIndentation  Level of indentation in log.
   *
   * @return  Debug information about a specified mimic and subtree of all its children and runtime children.
   */
  public static String toStringSubtree(MmBaseImplementation<?, ?> pMm, String pIndentation) {
    StringWriter writer = new StringWriter();
    if (pMm != null) {
      writer.append(pIndentation + pMm.toString());
      writer.append(NL);
      for (MmBaseImplementation<?, ?> child : pMm.implementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
      for (MmBaseImplementation<?, ?> child : pMm.runtimeImplementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
    }
    return writer.toString();
  }

  /**
   * Returns the list of all direct children of specified mimic, which are instances of class <code>MmBaseImplementation</code>.
   *
   * @param   pMmBaseImplementation  The specified mimic.
   *
   * @return  The list of all direct children of specified mimic of type <code>MmBaseImplementation</code>.
   */
  protected static List<MmBaseImplementation<?, ?>> getImplementationChildrenOf(MmBaseImplementation<?, ?> pMmBaseImplementation) {
    return pMmBaseImplementation.implementationChildren;
  }

  /**
   * Set declaration part of this mimic.
   *
   * @param         pDeclaration  The declaration part to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.IN_CONSTRUCTION}, or reference to declaration
   *                                          part is NOT null.
   * @throws        IllegalArgumentException  in case of parameter pDeclaration is null.
   *
   * @jalopy.group  group-construction
   */
  @SuppressWarnings("unchecked")
  protected void setCallback(MmBaseDeclaration<?, ?> pDeclaration) {
    if (this.initialState != MmInitialState.IN_CONSTRUCTION) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION");
    }
    if (this.declaration != null) {
      throw new IllegalStateException("Reference to declaration part must be null");
    }
    if (pDeclaration == null) {
      throw new IllegalArgumentException("Parameter pDeclaration cannot be null");
    }

    this.declaration  = (DECLARATION)pDeclaration;
    this.initialState = MmInitialState.CONSTRUCTION_COMPLETE;

    // if there is a parent mimic, add this mimic as child of parent mimic
    if (this.implementationParent != null) {
      this.implementationParent.addChild(this.declaration, null, null);
    }

    assert this.parentPath.isEmpty() : "Instance variable parentPath must be still empty";
  }

  /**
   * Sets the name of this mimic. A mimic gets its name from its parent mimic, because its name is the field name in its parent class
   * declaration. This method is called on a mimic during execution of mimic parent's initialize method.
   *
   * @param         pName  The name to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE},
   *                                          {@link MmInitialState.IN_INITIALIZATION} or {@link MmInitialState.INITIALIZED}.
   * @throws        IllegalArgumentException  in case of parameter pName is null.
   *
   * @jalopy.group  group-naming
   */
  protected void setName(String pName) {
    if ((this.initialState != MmInitialState.IN_CONSTRUCTION) && (this.initialState != MmInitialState.CONSTRUCTION_COMPLETE)
        && (this.initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION or CONSTRUCTION_COMPLETE or INITIALIZED, but is "
        + this.initialState);
    }
    assert ((this.implementationParent.initialState == MmInitialState.IN_INITIALIZATION)
        || (this.implementationParent.initialState == MmInitialState.INITIALIZED)) : "Initial state of parent must be IN_INITIALIZATION or INITIALIZED";
    if (pName == null) {
      throw new IllegalArgumentException("Parameter pName cannot be null");
    }
    assert this.name.isEmpty() : "Instance variable name " + this.name + " cannot be changed to " + pName;
    assert this.parentPath.isEmpty() : "Instance variable parentPath " + this.parentPath + " cannot be set twice";

    this.name = pName.trim();

    if (this.implementationParent.name.isEmpty()) {
      this.parentPath = "";
    } else if (this.implementationParent.parentPath.isEmpty()) {
      this.parentPath = this.implementationParent.name;
    } else {
      this.parentPath = this.implementationParent.parentPath + "." + this.implementationParent.name;
    }
  }

  /**
   * Sets the generic type of this mimic. This method is called on a mimic during execution of mimic parent's initialize method.
   *
   * @param         pTypeOfFirstGenericParameter  The type to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE} or
   *                                          {@link MmInitialState.INITIALIZED}.
   * @throws        IllegalArgumentException  in case of parameter pTypeOfFirstGenericParameter is null.
   *
   * @jalopy.group  group-naming
   */
  protected void setTypeOfFirstGenericParameter(Type pTypeOfFirstGenericParameter) {
    if ((this.initialState != MmInitialState.CONSTRUCTION_COMPLETE) && (this.initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or INITIALIZED, but was " + this.initialState);
    }
    assert this.implementationParent.initialState == MmInitialState.IN_INITIALIZATION : "Initial state of parent must be IN_INITIALIZATION";
    if (pTypeOfFirstGenericParameter == null) {
      throw new IllegalArgumentException("Parameter pTypeOfFirstGenericParameter cannot be null");
    }
    assert this.typeOfFirstGenericParameter == null : "Instance variable genericType " + this.typeOfFirstGenericParameter
                                                      + " cannot be changed to " + pTypeOfFirstGenericParameter;

    this.typeOfFirstGenericParameter = pTypeOfFirstGenericParameter;
  }

  /**
   * Add child of type <code>MmBaseDeclaration</code> to list of declarationChildren. Name implementation part of child and add to list of
   * children.
   *
   * @param         pChild                        The child to add (cannot be null).
   * @param         pNameOfChild                  The name of the child (may be null).
   * @param         pTypeOfFirstGenericParameter  The type of first generic parameter of the child.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE},
   *                                          {@link MmInitialState.IN_INITIALIZATION} or {@link MmInitialState.INITIALIZED}, or parameter
   *                                          pChild does not have an implementation part.
   * @throws        IllegalArgumentException  in case of parameter pChild is null.
   *
   * @jalopy.group  group-initialization
   */
  protected void addChild(MmBaseDeclaration<?, ?> pChild, String pNameOfChild, Type pTypeOfFirstGenericParameter) {
    if ((this.initialState != MmInitialState.CONSTRUCTION_COMPLETE) && (this.initialState != MmInitialState.IN_INITIALIZATION)
        && (this.initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or IN_INITIALIZATION or INITIALIZED");
    }
    if (pChild == null) {
      throw new IllegalArgumentException("Parameter pChild cannot be null");
    }
    if (pChild.implementation == null) {
      throw new IllegalStateException("Parameter pChild " + pChild + " must have an implementation");
    }

    MmBaseImplementation<?, ?> childImplementation = pChild.implementation;

    // name implementation part of child
    if (pNameOfChild != null) {
      childImplementation.setName(pNameOfChild);
    }

    // generic type implementation part of child
    if (pTypeOfFirstGenericParameter != null) {
      childImplementation.setTypeOfFirstGenericParameter(pTypeOfFirstGenericParameter);
    }

    // if parent mimic is not initialized yet, add child to compile time children
    if (this.initialState != MmInitialState.INITIALIZED) {

      // if child not in list yet, add child to list of
      // declarationChildren and list of implementationChildren
      if (!this.declarationChildren.contains(pChild)) {
        this.declarationChildren.add(pChild);
        this.implementationChildren.add(childImplementation);
      }

      // if parent mimic is initialized, add child to runtime time children
    } else {

      // if child not in list yet, add child to list of
      // runtime declarationChildren and list of runtime implementationChildren
      if (!this.runtimeDeclarationChildren.contains(pChild)) {

        // mark child as being a runtime child
        childImplementation.isRuntimeChild = true;
        this.runtimeDeclarationChildren.add(pChild);
        this.runtimeImplementationChildren.add(childImplementation);
      }
    }
  }

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param         pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param         pAnnotationClass  The runtime class of the annotation.
   *
   * @throws        IllegalArgumentException  in case othe annotation is not allowed for this type of mimic.
   *
   * @jalopy.group  group-initialization
   */
  protected <ANNOTATION extends Annotation> void checkForIllegalAnnotationsOtherThan(MmBaseDeclaration<?, ?> pDeclaration,
    Class<ANNOTATION> pAnnotationClass) {
    // if implementation part has a name
    // search for all annotations annotated by MmMetaAnnotation in field declaration of parent mimic
    if ((pDeclaration.implementation.name != null) && (pDeclaration.implementation.declarationParent != null)) {
      Class<?> declarationParentClass = pDeclaration.implementation.declarationParent.getClass();
      try {
        Field declaredFieldOfParent = declarationParentClass.getField(pDeclaration.implementation.name);
        for (Annotation annotation : declaredFieldOfParent.getAnnotations()) {
          Class<?>   annotationClass = annotation.annotationType();
          Annotation metaAnnotation  = annotation.annotationType().getAnnotation(MmMetaAnnotation.class);
          if (metaAnnotation != null) {
            if (!annotationClass.equals(pAnnotationClass)) {
              throw new IllegalArgumentException("Annotation " + annotation.annotationType().getSimpleName() + " is not allowed for "
                + pDeclaration.getClass().getSimpleName() + ", use " + pAnnotationClass.getSimpleName() + ".");
            }
          }
        }
      } catch (NoSuchFieldException e) {
        // attribute may use getter method
      }
    }

    // search for all annotations annotated by MmMetaAnnotation in class tree of pDeclaration
    Class<?> declarationClass = pDeclaration.getClass();

    do {
      for (Annotation annotation : declarationClass.getAnnotations()) {
        if (annotation.annotationType().getAnnotation(MmMetaAnnotation.class) != null) {
          Class<?> annotationClass = annotation.annotationType();
          if (!annotationClass.equals(pAnnotationClass)) {
            throw new IllegalArgumentException("Annotation " + annotation.annotationType().getSimpleName() + " is not allowed for "
              + pDeclaration.getClass().getSimpleName() + ", use " + pAnnotationClass.getSimpleName() + ".");
          }
        }
      }
      declarationClass = declarationClass.getSuperclass();
    } while ((declarationClass != null) && !declarationClass.equals(Object.class));
  }

  /**
   * Ensures that mimic will be initialized, if it is not initialized yet.
   *
   * @jalopy.group  group-initialization
   */
  protected void ensureInitialization() {
    if (this.initialState != MmInitialState.INITIALIZED) {
      if (this.root == null) {
        this.initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(this, "");
        }
      } else if (this.root.initialState == MmInitialState.INITIALIZED) {
        this.initialize();
      } else {
        this.root.initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(this.root, "");
        }
      }
    }
  }

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param         pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param         pAnnotationClass  The runtime class of the annotation.
   *
   * @return        The found annotation or <code>null</code>.
   *
   * @jalopy.group  group-initialization
   */
  protected <ANNOTATION extends Annotation> ANNOTATION findAnnotation(MmBaseDeclaration<?, ?> pDeclaration,
    Class<ANNOTATION> pAnnotationClass) {
    ANNOTATION returnAnnotation = null;

    // if implementation part has a name
    // search for annotation in field declaration of parent mimic
    if ((pDeclaration.implementation.name != null) && (pDeclaration.implementation.declarationParent != null)) {
      Class<?> declarationParentClass = pDeclaration.implementation.declarationParent.getClass();
      try {
        Field declaredFieldOfParent = declarationParentClass.getField(pDeclaration.implementation.name);
        returnAnnotation = declaredFieldOfParent.getAnnotation(pAnnotationClass);
      } catch (NoSuchFieldException e) {
        // attribute may use getter method
      }
    }

    // search for annotation in class tree of pDeclaration
    if (returnAnnotation == null) {
      Class<?> declarationClass = pDeclaration.getClass();

      while (returnAnnotation == null) {
        returnAnnotation = declarationClass.getAnnotation(pAnnotationClass);
        if (returnAnnotation == null) {
          declarationClass = declarationClass.getSuperclass();
          if ((declarationClass == null) || declarationClass.equals(Object.class)) {
            break;
          }
        }
      }
    }
    return returnAnnotation;
  }

  /**
   * Provides all fields (inclusive private ones) within the given class and its super-classes.
   *
   * <p>The fields of the super classes appear as first list items.</p>
   *
   * @param         pClassToAnalyze  The class to analyze.
   *
   * @return        All found fields.
   *
   * @jalopy.group  group-initialization
   */
  protected List<Field> findFields(Class<?> pClassToAnalyze) {
    List<Field>    allFields = new ArrayList<>();
    List<Class<?>> classes   = new ArrayList<>();
    Class<?>       c         = pClassToAnalyze;

    while (c != null) {
      classes.add(c);
      c = c.getSuperclass();
    }

    for (int i = classes.size() - 1; i >= 0; --i) {
      allFields.addAll(Arrays.asList(classes.get(i).getDeclaredFields()));
    }
    return allFields;
  }

  /**
   * Returns an ancestor of this mimic of specified type, if exists, otherwise null.
   *
   * @param         pType  The specified type.
   *
   * @return        An ancestor of this mimic of specified type, if exists, otherwise null.
   *
   * @jalopy.group  group-initialization
   */
  @SuppressWarnings("unchecked")
  protected <T> T getImplementationAncestorOfType(Class<T> pType) {
    if (this.implementationParent != null) {
      if (pType.isAssignableFrom(this.implementationParent.getClass())) {
        return (T)this.implementationParent;
      } else {
        return this.implementationParent.getImplementationAncestorOfType(pType);
      }
    } else {
      return null;
    }
  }

  /**
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   *
   * @throws        IllegalStateException  in case of initial state is not {@link MmInitialState.CONSTRUCTION_COMPLETE} or reference to
   *                                       declaration part of mimic is not defined.
   *
   * @jalopy.group  group-initialization
   */
  protected void initialize() {
    if (this.initialState != MmInitialState.CONSTRUCTION_COMPLETE) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE");
    }
    if (this.declaration == null) {
      throw new IllegalStateException("Reference to declaration part must be defined");
    }

    // set state to IN_INITIALIZATION
    this.initialState         = MmInitialState.IN_INITIALIZATION;

    // evaluate ancestor for reference path, file and params
    this.referencableAncestor = this.getImplementationAncestorOfType(MmReferencableMimic.class);

    // evaluate children
    for (Field field : this.findFields(this.declaration.getClass())) {

      // add all public not static fields of type MmBaseDeclaration as children
      this.addFieldOfTypeMmToChildren(field);
    }

    // evaluate configuration
    this.initializeConfiguration();

    // if id is not defined yet, set id to full name
    if (this.configuration.getId().equals(MmBaseConfiguration.UNDEFINED_ID) && !this.name.isEmpty()) {
      String newId = this.name;
      if (!this.parentPath.isEmpty()) {
        newId = this.parentPath.replaceAll("\\.", "-") + "-" + this.name;
      }
      this.configuration.setId(newId);
    }

    // set state to INITIALIZED
    this.initialState = MmInitialState.INITIALIZED;

    // initialize children
    for (MmBaseImplementation<?, ?> implementationChild : this.implementationChildren) {
      implementationChild.initialize();
    }
  }

  /**
   * Initialize this mimic after constructor phase.
   *
   * @jalopy.group  group-initialization
   */
  protected abstract void initializeConfiguration();

  /**
   * Add field to children, if field is public and not static and of type MmBaseDeclaration.
   *
   * @param         pField  The specific field to analyze.
   *
   * @jalopy.group  group-initialization
   */
  private void addFieldOfTypeMmToChildren(Field pField) {
    // if field is public but not static
    if (((pField.getModifiers() & Modifier.PUBLIC) != 0) && ((pField.getModifiers() & Modifier.STATIC) == 0)) {

      // if field is descendant of MmBaseDeclaration
      if (MmBaseDeclaration.class.isAssignableFrom(pField.getType())) {

        // if field is not final log warning
        if ((pField.getModifiers() & Modifier.FINAL) == 0) {
          LOGGER.warn("Field: " + pField.getDeclaringClass() + "." + pField.getName() + " is not declared final!");
        }
        if (!pField.isAccessible()) {
          pField.setAccessible(true);
        }

        try {
          MmBaseDeclaration<?, ?> child                       = (MmBaseDeclaration<?, ?>)pField.get(this.declaration);
          Type                    typeOfFirstGenericParameter = null;
          if (pField.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)pField.getGenericType();
            Type[]            typeArray         = parameterizedType.getActualTypeArguments();
            if (typeArray.length >= 1) {
              typeOfFirstGenericParameter = typeArray[0];
            }
          }
          this.addChild(child, pField.getName(), typeOfFirstGenericParameter);

          // check parent child relation
          if (child.implementation.implementationParent != this) {
            throw new IllegalStateException("this mimic must be parent of child to add");
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Returns a list of all ancestor mimics of this mimic. Top most ancestor will be first, direct parent will be last.
   *
   * @return        A list of all ancestor mimics of this mimic.
   *
   * @jalopy.group  group-override
   */
  public List<MmMimic> getMmAncestors() {
    this.ensureInitialization();

    List<MmMimic> ancestors = new ArrayList<>();
    if ((this.implementationParent != null) && (this.implementationParent.declaration != null)) {
      ancestors.addAll(this.implementationParent.getMmAncestors());
      ancestors.add(this.implementationParent.declaration);
    }
    return ancestors;
  }

  /**
   * Returns a direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   *
   * @param         pChildName  The name of the child to search for.
   *
   * @return        A direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   *
   * @jalopy.group  group-override
   */
  public final MmMimic getMmChildByName(String pChildName) {
    this.ensureInitialization();

    for (MmMimic child : this.getMmChildren()) {
      if (child.getMmName().equals(pChildName)) {
        return child;
      }
    }
    return null;
  }

  /**
   * Returns a list of all direct child mimics of this mimic.
   *
   * @return        A list of all direct child mimics of this mimic.
   *
   * @jalopy.group  group-override
   */
  public List<MmMimic> getMmChildren() {
    this.ensureInitialization();

    final int     size       = this.declarationChildren.size() + this.runtimeDeclarationChildren.size();
    List<MmMimic> returnList = new ArrayList<>(size);
    returnList.addAll(this.declarationChildren);
    returnList.addAll(this.runtimeDeclarationChildren);
    return returnList;
  }

  /**
   * Returns a descendant mimic of specified full name, or <code>null</code> if it doesn't exist. The name is a path of ancestors' names
   * like <code>grandparent.parent.child</code>.
   *
   * @param         pFullName  The full name of the mimic to search for.
   *
   * @return        A descendant mimic of specified full name, or <code>null</code> if it doesn't exist.
   *
   * @jalopy.group  group-override
   */
  public MmMimic getMmDescendantByFullName(String pFullName) {
    this.ensureInitialization();

    final String fullName = this.getMmFullName();
    if (fullName.equals(pFullName)) {
      return this;
    }

    for (MmMimic child : this.getMmChildren()) {
      MmMimic found = MmRelationshipApi.getMmDescendantByFullName(child, pFullName);
      if (found != null) {
        return found;
      }
    }
    return null;
  }

  /**
   * Returns a list of all descendant mimics of this mimic. Each child is followed by own children, before followed by siblings.
   *
   * @return        A list of all descendant mimics of this mimic.
   *
   * @jalopy.group  group-override
   */
  public List<MmMimic> getMmDescendants() {
    this.ensureInitialization();

    final List<MmMimic> descendants = new ArrayList<>();
    for (MmMimic child : this.getMmChildren()) {
      descendants.add(child);
      descendants.addAll(MmRelationshipApi.getMmDescendants(child));
    }
    return descendants;
  }

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty
   * String if the full name is undefined. The full name is evaluated during initialization phase and derived from the field declaration
   * name in its parent class.
   *
   * @return        The full name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public String getMmFullName() {
    this.ensureInitialization();

    if (this.parentPath.isEmpty()) {
      return this.name;
    } else {
      return this.parentPath + "." + this.name;
    }
  }

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return        The id of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public String getMmId() {
    this.ensureInitialization();

    return this.getConfiguration().getId();
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
  @Override public String getMmLongDescription() {
    this.ensureInitialization();

    final Object   initialParams         = null;
    final Object[] longDescriptionParams = this.declaration.callbackMmGetLongDescriptionParams(initialParams);
    final String   i18nLongDescription   = this.getMmI18nText(MmMessageType.LONG, longDescriptionParams);
    final String   returnString          = this.declaration.callbackMmGetLongDescription(i18nLongDescription, longDescriptionParams);
    assert returnString != null : "callbackMmGetLongDescription cannot return null";
    return returnString;
  }

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and
   * derived from the field declaration name in its parent class.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override public String getMmName() {
    this.ensureInitialization();

    return this.name;
  }

  /**
   * Returns the parent mimic of this mimic, may be null in case of <code>MmRoot</code>.
   *
   * @return        The parent mimic of this mimic, may be null.
   *
   * @jalopy.group  group-override
   */
  public MmMimic getMmParent() {
    this.ensureInitialization();

    return this.implementationParent.declaration;
  }

  /**
   * Returns the self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @return        The self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override public MmReference getMmReference() {
    this.ensureInitialization();

    // if no referencable ancestor is available, no reference can be returned
    if (this.referencableAncestor == null) {
      return null;

      // create an own reference from anchestor reference and own anchor
    } else {
      final MmReference ancestorReference = this.referencableAncestor.getMmReference();
      if (ancestorReference.isMmJsfOutcome()) {
        return ancestorReference;
      } else {
        final String      anchor          = "#" + this.getMmId();
        final MmReference returnReference = new MmReferenceImplementation(ancestorReference, anchor);
        return returnReference;
      }
    }
  }

  /**
   * Returns the self reference of this object for a specified data model.
   *
   * @param         pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Override public MmReference getMmReference(MmReferencableModel pModel) {
    this.ensureInitialization();

    // if no referencable ancestor is available, no reference can be returned
    if (this.referencableAncestor == null) {
      return null;

      // create an own reference from anchestor reference for specified model, and own anchor
    } else {
      final MmReference ancestorReference = this.referencableAncestor.getMmReference(pModel);
      if (ancestorReference.isMmJsfOutcome()) {
        return ancestorReference;
      } else {
        final String      anchor          = "#" + this.getMmId();
        final MmReference returnReference = new MmReferenceImplementation(ancestorReference, anchor);
        return returnReference;
      }
    }
  }

  /**
   * Returns the <code>MmRoot</code> of this mimic. The <code>MmRoot</code> is a root element of a subtree.
   *
   * @return        The <code>MmRoot</code> of this mimic.
   *
   * @jalopy.group  group-override
   */
  public MmRoot getMmRoot() {
    this.ensureInitialization();

    return (MmRoot)this.root.declaration;
  }

  /**
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-override
   */
  @Override public String getMmShortDescription() {
    this.ensureInitialization();

    final String i18nShortDescription = this.getMmI18nText(MmMessageType.SHORT);
    final String returnString         = this.declaration.callbackMmGetShortDescription(i18nShortDescription);
    assert returnString != null : "callbackMmGetShortDescription cannot return null";
    return returnString;
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override public String getMmStyleClasses() {
    this.ensureInitialization();

    final String returnString = this.declaration.callbackMmGetStyleClasses("");
    if (returnString == null) {
      return "";
    } else {
      return returnString;
    }
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled. This mimic is enabled, if its parent is enabled and its declaration method
   * callbackMmIsEnabled returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is enabled.
   *
   * @jalopy.group  group-override
   */
  @Override public boolean isMmEnabled() {
    this.ensureInitialization();

    if (this.implementationParent == null) {
      return this.declaration.callbackMmIsEnabled(this.configuration.isEnabled());
    } else {
      return this.implementationParent.isMmEnabled() && this.declaration.callbackMmIsEnabled(this.configuration.isEnabled());
    }
  }

  /**
   * Returns <code>true</code>, if the mimic is read only. This mimic is read only, if its parent is read only or its declaration method
   * callbackMmIsReadOnly returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is read only.
   *
   * @jalopy.group  group-override
   */
  @Override public boolean isMmReadOnly() {
    this.ensureInitialization();

    if (this.implementationParent == null) {
      return this.declaration.callbackMmIsReadOnly(this.configuration.isReadOnly());
    } else {
      return this.implementationParent.isMmReadOnly() || this.declaration.callbackMmIsReadOnly(this.configuration.isReadOnly());
    }
  }

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-override
   */
  @Override public boolean isMmRuntimeChild() {
    this.ensureInitialization();

    return this.isRuntimeChild;
  }

  /**
   * Returns <code>true</code>, if the mimic is visible. This mimic is visible, if its parent is visible and its declaration method
   * callbackMmIsVisible returns <code>true</code>.
   *
   * @return        <code>True</code>, if the mimic is visible.
   *
   * @jalopy.group  group-override
   */
  @Override public boolean isMmVisible() {
    this.ensureInitialization();

    if (this.implementationParent == null) {
      return this.declaration.callbackMmIsVisible(this.configuration.isVisible());
    } else {
      return this.implementationParent.isMmVisible() && this.declaration.callbackMmIsVisible(this.configuration.isVisible());
    }
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
   *
   * @return        Some information about this object for development purposes like debugging and logging.
   *
   * @jalopy.group  group-override
   */
  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(");

    Class<?> clazz = this.declaration.getClass();
    while (clazz.getSimpleName().isEmpty()) {
      clazz = clazz.getSuperclass();
    }
    sb.append(clazz.getSimpleName());
    if (!this.parentPath.isEmpty()) {
      sb.append(" path=");
      sb.append(this.parentPath);
      sb.append(".");
      sb.append(this.name);
    } else if (!this.name.isEmpty()) {
      sb.append(" name=");
      sb.append(this.name);
    }
    if (this.getConfiguration() != null) {
      sb.append(" id=");
      sb.append(this.configuration.id);
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * Clears the list of runtime children of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public void clearRuntimeChildrenList() {
    this.runtimeDeclarationChildren.clear();
    this.runtimeImplementationChildren.clear();
  }

  /**
   * Returns the configuration of this mimic.
   *
   * @return        The configuration of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public CONFIGURATION getConfiguration() {
    return this.configuration;
  }

  /**
   * Returns the declaration part of this mimic.
   *
   * @return        The declaration part of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public DECLARATION getDeclaration() {
    return this.declaration;
  }

  /**
   * Returns an internationalized version of the message of this mimic for a specified message type.
   *
   * @param         pMessageType  The specified message type.
   * @param         pArguments    Optional list of message arguments.
   *
   * @return        The internationalized version of a specified message.
   *
   * @jalopy.group  group-helper
   */
  public String getMmI18nText(MmMessageType pMessageType, Object... pArguments) {
    return this.root.getMmI18nText(this.getMmId(), pMessageType, pArguments);
  }

  /**
   * Returns the Java type of configuration class of this mimic.
   *
   * @return        The Java type of configuration class of this mimic.
   *
   * @jalopy.group  group-helper
   */
  protected Class<?> getConfigurationType() {
    return findGenericsParameterType(this.getClass(), MmBaseImplementation.class, GENERIC_PARAMETER_INDEX_CONFIGURATION);
  }

  /**
   * Returns the MmJsfBridge of this mimic, which connects it to a JSF view component..
   *
   * @return  The MmJsfBridge of this mimic.
   */
  public final MmJsfBridge<?, ?, ?> getJsfBridge() {
    return this.mmJsfBridge;
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @return  The current JSF tag of this mimic.
   */
  public String getJsfTag() {
    if ((this.isMmEnabled() && !this.isMmReadOnly()) || this.configuration.getJsfTagDisabled().equals("SameAsEnabled")) {
      return this.configuration.getJsfTagEnabled();
    } else {
      return this.configuration.getJsfTagDisabled();
    }
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  public boolean isMmJsEnabled() {
    return this.root.isMmJsEnabled();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  protected abstract MmJsfBridge<?, ?, ?> createMmJsfBridge();

}
