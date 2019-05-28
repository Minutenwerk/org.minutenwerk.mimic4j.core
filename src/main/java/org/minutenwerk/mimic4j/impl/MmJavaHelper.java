package org.minutenwerk.mimic4j.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Static helper methods on Java Generics and Annotations.
 *
 * @author  Olaf Kossak
 */
public class MmJavaHelper {

  /**
   * Not for use.
   */
  private MmJavaHelper() {
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
   * Provides all fields within the given class and its super-classes, which are public, static, final and instances of
   * {@link MmBaseDeclaration}.
   *
   * <p>The fields of the super classes appear as first list items.</p>
   *
   * @param   pClassToAnalyze  The class to analyze.
   *
   * @return  All found fields.
   */
  public static List<Field> findPublicStaticFinalBaseDeclarationFields(Class<?> pClassToAnalyze) {
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
    return allFields.stream() //
    .filter(field -> ((field.getModifiers() & Modifier.PUBLIC) != 0)) //
    .filter(field -> ((field.getModifiers() & Modifier.STATIC) == 0)) //
    .filter(field -> ((field.getModifiers() & Modifier.FINAL) != 0)) //
    .filter(field -> MmBaseDeclaration.class.isAssignableFrom(field.getType())) //
    .peek(field -> field.setAccessible(true)) //
    .collect(Collectors.toList());
  }

  /**
   * Searches for a specified annotation on a field and within its inheritance tree.
   *
   * @param   pField            The specified field to analyze.
   * @param   pAnnotationClass  The specified annotation class.
   *
   * @return  The found annotation or <code>null</code>.
   * @throws IllegalArgumentException  In case of more than one annotation of specified type on specified field.
   */
  public static <ANNOTATION extends Annotation> ANNOTATION findAnnotation(Field pField, Class<ANNOTATION> pAnnotationClass) {
    ANNOTATION[] returnAnnotation = pField.getDeclaredAnnotationsByType(pAnnotationClass);
    if (returnAnnotation.length == 1) {
      return returnAnnotation[0];
    } else if (returnAnnotation.length > 1) {
      throw new IllegalArgumentException("more than one annotation of type " + pAnnotationClass + " on field " + pField);
    } else {
      return null;
    }
  }

  /**
   * TODOC.
   *
   * @param   pParent  TODOC
   * @param   pField   TODOC
   *
   * @return  TODOC
   */
  public static ChildAndNameAndGeneric getChildByParentAndField(final MmBaseDeclaration<?, ?> pParent, final Field pField) {
	    try {
	      MmBaseDeclaration<?, ?> child                              = (MmBaseDeclaration<?, ?>)pField.get(pParent);
	      Type                    typeOfFirstGenericParameterOfField = null;
	      if (pField.getGenericType() instanceof ParameterizedType) {
	        ParameterizedType parameterizedType = (ParameterizedType)pField.getGenericType();
	        Type[]            typeArray         = parameterizedType.getActualTypeArguments();
	        if (typeArray.length >= 1) {
	          typeOfFirstGenericParameterOfField = typeArray[0];
	        }
	      }
	      return new ChildAndNameAndGeneric(child, pField.getName(), typeOfFirstGenericParameterOfField);
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

  public static Optional<Type> getFirstGenericType(final Field pField) {
	Type genericSuperClass = pField.getType().getGenericSuperclass();
    if (genericSuperClass instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType)genericSuperClass;
      Type[]            typeArray         = parameterizedType.getActualTypeArguments();
      if (typeArray.length >= 1) {
        return Optional.of(typeArray[0]);
      }
    }
    return Optional.empty();
  }

  /**
   * TODOC.
   *
   * @param   pParent  TODOC
   * @param   pFields  TODOC
   *
   * @return  TODOC
   */
  public static List<ChildAndNameAndGeneric> getChildrenByParentAndFields(final MmBaseDeclaration<?, ?> pParent,
    final List<Field> pFields) {
    List<ChildAndNameAndGeneric> childrenByParentAndFields = new ArrayList<>(pFields.size());
    for (Field field : pFields) {
      ChildAndNameAndGeneric child = getChildByParentAndField(pParent, field);
      if (child != null) {
        childrenByParentAndFields.add(child);
      }
    }
    return childrenByParentAndFields;
  }

  public static class ChildAndNameAndGeneric {
    private final MmBaseDeclaration<?, ?> child;
    private final String                  nameOfChild;
    private final Type                    typeOfFirstGenericParameter;

    public ChildAndNameAndGeneric(MmBaseDeclaration<?, ?> pChild, String pNameOfChild, Type pTypeOfFirstGenericParameter) {
      child                       = pChild;
      nameOfChild                 = pNameOfChild;
      typeOfFirstGenericParameter = pTypeOfFirstGenericParameter;
    }

    public MmBaseDeclaration<?, ?> getChild() {
      return child;
    }

    public String getNameOfChild() {
      return nameOfChild;
    }

    public Type getTypeOfFirstGenericParameter() {
      return typeOfFirstGenericParameter;
    }

  }

}
