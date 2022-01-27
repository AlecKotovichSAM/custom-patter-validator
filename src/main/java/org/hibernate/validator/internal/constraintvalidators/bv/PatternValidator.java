/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.validator.internal.constraintvalidators.bv;

import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.messageinterpolation.util.InterpolationHelper;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Hardy Ferentschik
 */
@Component
public class PatternValidator implements ConstraintValidator<Pattern, CharSequence>, ApplicationContextAware {

	private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());

	private java.util.regex.Pattern pattern;
	private String escapedRegexp;

	private ApplicationContext applicationContext;

	@Override
	public void initialize(Pattern parameters) {
		Pattern.Flag[] flags = parameters.flags();
		int intFlag = 0;
		for (Pattern.Flag flag : flags) {
			intFlag = intFlag | flag.getValue();
		}

		try {
			String resolvedRegexp = applicationContext.getEnvironment().resolvePlaceholders(parameters.regexp());
			pattern = java.util.regex.Pattern
					.compile(resolvedRegexp, intFlag);
		} catch (PatternSyntaxException e) {
			throw LOG.getInvalidRegularExpressionException(e);
		}

		escapedRegexp = InterpolationHelper.escapeMessageParameter(parameters.regexp());
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null) {
			return true;
		}

		if (constraintValidatorContext instanceof HibernateConstraintValidatorContext) {
			constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter("regexp",
					escapedRegexp);
		}

		Matcher m = pattern.matcher(value);
		return m.matches();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
