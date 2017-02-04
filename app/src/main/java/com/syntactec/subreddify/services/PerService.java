package com.syntactec.subreddify.services;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines a "once per service" scope for dependency injection.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
