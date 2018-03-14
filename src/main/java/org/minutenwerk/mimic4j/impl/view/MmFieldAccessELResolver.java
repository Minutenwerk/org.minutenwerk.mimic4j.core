package org.minutenwerk.mimic4j.impl.view;

import java.beans.FeatureDescriptor;

import java.lang.reflect.Field;

import java.util.Iterator;

import javax.el.ArrayELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * The MmFieldAccessELResolver allows EL expressions to access public fields in instances of MmMimic directly. The EL Resolver must be
 * configured in <code>faces-config.xml</code> like
 *
 * <pre>
    <application>
      <!-- configuration of mimic4j -->
      <el-resolver>org.minutenwerk.mimic4j.impl.view.MmFieldAccessELResolver</el-resolver>
 * </pre>
 *
 * @author  Olaf Kossak
 */
public class MmFieldAccessELResolver extends ELResolver {

  /**
   * Creates a new MmFieldAccessELResolver instance.
   */
  public MmFieldAccessELResolver() {
  }

  /**
   * Returns the most general type that this resolver accepts for the <code>property</code> argument, given a <code>base</code> object. One
   * use for this method is to assist tools in auto-completion.
   *
   * <p>This assists tools in auto-completion and also provides a way to express that the resolver accepts a primitive value, such as an
   * integer index into an array. For example, the {@link ArrayELResolver} will accept any <code>int</code> as a <code>property</code>, so
   * the return value would be <code>Integer.class</code>.</p>
   *
   * @param   pContext  The context of this evaluation.
   * @param   pBase     The base object to return the most general property type for, or <code>null</code> to enumerate the set of top-level
   *                    variables that this resolver can evaluate.
   *
   * @return  <code>null</code> if this <code>ELResolver</code> does not know how to handle the given <code>base</code> object; otherwise
   *          <code>Object.class</code> if any type of <code>property</code> is accepted; otherwise the most general <code>property</code>
   *          type accepted for the given <code>base</code>.
   */
  @Override public Class<?> getCommonPropertyType(ELContext pContext, Object pBase) {
    if (pBase instanceof MmMimic) {
      try {
        pContext.setPropertyResolved(true);
        return pBase.getClass();
      } catch (Exception e) {
        pContext.setPropertyResolved(false);
        return null;
      }
    } else {
      pContext.setPropertyResolved(false);
      return null;
    }
  }

  /**
   * Returns information about the set of variables or properties that can be resolved for the given <code>base</code> object. One use for
   * this method is to assist tools in auto-completion.
   *
   * <p>If the <code>base</code> parameter is <code>null</code>, the resolver must enumerate the list of top-level variables it can
   * resolve.</p>
   *
   * <p>The <code>Iterator</code> returned must contain zero or more instances of {@link java.beans.FeatureDescriptor}, in no guaranteed
   * order. In the case of primitive types such as <code>int</code>, the value <code>null</code> must be returned. This is to prevent the
   * useless iteration through all possible primitive values. A return value of <code>null</code> indicates that this resolver does not
   * handle the given <code>base</code> object or that the results are too complex to represent with this method and the
   * {@link #getCommonPropertyType} method should be used instead.</p>
   *
   * <p>Each <code>FeatureDescriptor</code> will contain information about a single variable or property. In addition to the standard
   * properties, the <code>FeatureDescriptor</code> must have two named attributes (as set by the <code>setValue</code> method):</p>
   *
   * <ul>
   *   <li>{@link #TYPE} - The value of this named attribute must be an instance of <code>java.lang.Class</code> and specify the runtime
   *     type of the variable or property.</li>
   *   <li>{@link #RESOLVABLE_AT_DESIGN_TIME} - The value of this named attribute must be an instance of <code>java.lang.Boolean</code> and
   *     indicates whether it is safe to attempt to resolve this property at design-time. For instance, it may be unsafe to attempt a
   *     resolution at design time if the <code>ELResolver</code> needs access to a resource that is only available at runtime and no
   *     acceptable simulated value can be provided.</li>
   * </ul>
   *
   * <p>The caller should be aware that the <code>Iterator</code> returned might iterate through a very large or even infinitely large set
   * of properties. Care should be taken by the caller to not get stuck in an infinite loop.</p>
   *
   * <p>This is a "best-effort" list. Not all <code>ELResolver</code>s will return completely accurate results, but all must be callable at
   * both design-time and runtime (i.e. whether or not <code>Beans.isDesignTime()</code> returns <code>true</code>), without causing
   * errors.</p>
   *
   * <p>The <code>propertyResolved</code> property of the <code>ELContext</code> is not relevant to this method. The results of all <code>
   * ELResolver</code>s are concatenated in the case of composite resolvers.</p>
   *
   * @param   pContext  The context of this evaluation.
   * @param   pBase     The base object whose set of valid properties is to be enumerated, or <code>null</code> to enumerate the set of
   *                    top-level variables that this resolver can evaluate.
   *
   * @return  An <code>Iterator</code> containing zero or more (possibly infinitely more) <code>FeatureDescriptor</code> objects, or <code>
   *          null</code> if this resolver does not handle the given <code>base</code> object or that the results are too complex to
   *          represent with this method
   *
   * @see     java.beans.FeatureDescriptor
   */
  @Override public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext pContext, Object pBase) {
    return null;
  }

  /**
   * For a given <code>base</code> and <code>property</code>, attempts to identify the most general type that is acceptable for an object to
   * be passed as the <code>value</code> parameter in a future call to the {@link #setValue} method.
   *
   * <p>If this resolver handles the given (base, property) pair, the <code>propertyResolved</code> property of the <code>ELContext</code>
   * object must be set to <code>true</code> by the resolver, before returning. If this property is not <code>true</code> after this method
   * is called, the caller should ignore the return value.</p>
   *
   * <p>This is not always the same as <code>getValue().getClass()</code>. For example, in the case of an {@link ArrayELResolver}, the
   * <code>getType</code> method will return the element type of the array, which might be a superclass of the type of the actual element
   * that is currently in the specified array element.</p>
   *
   * @param   pContext   The context of this evaluation.
   * @param   pBase      The base object whose property value is to be analyzed, or <code>null</code> to analyze a top-level variable.
   * @param   pProperty  The property or variable to return the acceptable type for.
   *
   * @return  If the <code>propertyResolved</code> property of <code>ELContext</code> was set to <code>true</code>, then the most general
   *          acceptable type; otherwise undefined.
   */
  @Override public Class<?> getType(ELContext pContext, Object pBase, Object pProperty) {
    if (pBase instanceof MmMimic) {
      try {
        Field field = pBase.getClass().getField((String)pProperty);
        if (MmMimic.class.isAssignableFrom(field.getType())) {
          pContext.setPropertyResolved(true);
          return field.getType();
        }
      } catch (Exception e) {
      }
    }
    pContext.setPropertyResolved(false);
    return null;
  }

  /**
   * Attempts to resolve the given <code>property</code> object on the given <code>base</code> object.
   *
   * <p>If this resolver handles the given (base, property) pair, the <code>propertyResolved</code> property of the <code>ELContext</code>
   * object must be set to <code>true</code> by the resolver, before returning. If this property is not <code>true</code> after this method
   * is called, the caller should ignore the return value.</p>
   *
   * @param   pContext   The context of this evaluation.
   * @param   pBase      The base object whose property value is to be returned, or <code>null</code> to resolve a top-level variable.
   * @param   pProperty  The property or variable to be resolved.
   *
   * @return  If the <code>propertyResolved</code> property of <code>ELContext</code> was set to <code>true</code>, then the result of the
   *          variable or property resolution; otherwise undefined.
   */
  @Override public Object getValue(ELContext pContext, Object pBase, Object pProperty) {
    if (pBase instanceof MmMimic) {
      try {
        Field field = pBase.getClass().getField((String)pProperty);
        if (MmMimic.class.isAssignableFrom(field.getType())) {
          Object pValue = field.get(pBase);
          pContext.setPropertyResolved(true);
          return pValue;
        }
      } catch (Exception e) {
      }
    }
    pContext.setPropertyResolved(false);
    return null;
  }

  /**
   * For a given <code>base</code> and <code>property</code>, attempts to determine whether a call to {@link #setValue} will always fail.
   *
   * <p>If this resolver handles the given (base, property) pair, the <code>propertyResolved</code> property of the <code>ELContext</code>
   * object must be set to <code>true</code> by the resolver, before returning. If this property is not <code>true</code> after this method
   * is called, the caller should ignore the return value.</p>
   *
   * @param   pContext   The context of this evaluation.
   * @param   pBase      The base object whose property value is to be analyzed, or <code>null</code> to analyze a top-level variable.
   * @param   pProperty  The property or variable to return the read-only status for.
   *
   * @return  If the <code>propertyResolved</code> property of <code>ELContext</code> was set to <code>true</code>, then <code>true</code>
   *          if the property is read-only or <code>false</code> if not; otherwise undefined.
   */
  @Override public boolean isReadOnly(ELContext pContext, Object pBase, Object pProperty) {
    if (pBase instanceof MmMimic) {
      try {
        Field field = pBase.getClass().getField((String)pProperty);
        if (MmMimic.class.isAssignableFrom(field.getType())) {
          pContext.setPropertyResolved(true);
          return true;
        }
      } catch (Exception e) {
      }
    }
    pContext.setPropertyResolved(false);
    return false;
  }

  /**
   * Attempts to set the value of the given <code>property</code> object on the given <code>base</code> object.
   *
   * <p>If this resolver handles the given (base, property) pair, the <code>propertyResolved</code> property of the <code>ELContext</code>
   * object must be set to <code>true</code> by the resolver, before returning. If this property is not <code>true</code> after this method
   * is called, the caller can safely assume no value has been set.</p>
   *
   * @param  pContext   The context of this evaluation.
   * @param  pBase      The base object whose property value is to be set, or <code>null</code> to set a top-level variable.
   * @param  pProperty  The property or variable to be set.
   * @param  pValue     The value to set the property or variable to.
   */
  @Override public void setValue(ELContext pContext, Object pBase, Object pProperty, Object pValue) {
    if (pBase instanceof MmMimic) {
      try {
        Field field = pBase.getClass().getField((String)pProperty);
        if (MmMimic.class.isAssignableFrom(field.getType())) {
          field.set(pBase, pValue);
          pContext.setPropertyResolved(true);
          return;
        }
      } catch (Exception e) {
      }
    }
    pContext.setPropertyResolved(false);
  }
}
