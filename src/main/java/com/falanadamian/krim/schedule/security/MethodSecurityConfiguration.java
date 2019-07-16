package com.falanadamian.krim.schedule.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

import java.security.Permission;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    private DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }

    public class DefaultMethodSecurityExpressionHandler extends org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler {

        @Override
        public StandardEvaluationContext createEvaluationContextInternal(final Authentication authentication, final MethodInvocation methodInvocation) {
            StandardEvaluationContext standardEvaluationContext = super.createEvaluationContextInternal(authentication, methodInvocation);
            ((StandardTypeLocator) standardEvaluationContext.getTypeLocator()).registerImport(Permission.class.getPackage().getName());
            return standardEvaluationContext;
        }
    }
}
