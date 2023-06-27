package org.jboss.seam.annotations.intercept;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Synonym for javax.interceptors.AroundTimeout.
 * 
 * @author tremes
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface AroundTimeout {}