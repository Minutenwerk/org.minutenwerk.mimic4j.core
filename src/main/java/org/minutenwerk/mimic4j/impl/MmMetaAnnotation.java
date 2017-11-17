package org.minutenwerk.mimic4j.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for all annotations of this framework.
 *
 * @author  okossak
 * @see     $HeadURL: $$maven.project.version$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MmMetaAnnotation {
}
