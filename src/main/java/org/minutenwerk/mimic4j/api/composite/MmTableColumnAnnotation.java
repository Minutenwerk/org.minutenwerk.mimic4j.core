package org.minutenwerk.mimic4j.api.composite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.composite.MmTableColumn.MmTableColumnJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.composite.MmConfigurationTableColumn;

/**
 * MmTableColumnAnnotation annotates declarations of {@link MmTableColumn} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmTableColumnAnnotation {

  public String id() default MmConfigurationTableColumn.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTableColumn.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationTableColumn.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationTableColumn.DEFAULT_IS_ENABLED;

  public MmTableColumnJsfTag jsfTag() default MmTableColumnJsfTag.TableColumn;

  public boolean isRowHeader() default MmConfigurationTableColumn.DEFAULT_IS_ROW_HEADER;

  public String styleClasses() default MmConfigurationTableColumn.DEFAULT_STYLE_CLASSES;

  public String headerClasses() default MmConfigurationTableColumn.DEFAULT_HEADER_CLASSES;

  public String footerClasses() default MmConfigurationTableColumn.DEFAULT_FOOTER_CLASSES;

}
