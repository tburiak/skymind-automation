package skymind.automation.resolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestDataResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return true;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        String testMethodName = extensionContext.getTestMethod()
                .map(Method::getName)
                .orElseThrow(() -> new IllegalStateException("Test method not found"));
        try {
            Method method = this.getClass().getDeclaredMethod(testMethodName);
            method.setAccessible(true);
            return method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ParameterResolutionException("Failed to resolve parameter", e);
        }
    }
}
