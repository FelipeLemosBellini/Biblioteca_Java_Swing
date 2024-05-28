package dependencyInjection;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ServiceLocatorImpl {
    private static ServiceLocatorImpl instance;
    private Map<String, Class<?>> services = new HashMap<>();

    public static ServiceLocatorImpl getInstance() {
        if (instance == null) {
            instance = new ServiceLocatorImpl();
        }
        return instance;
    }

    public void registerService(Class<?> serviceClass){
        services.put(serviceClass.getSimpleName(), serviceClass);
    }

    public void registerService(Class<?> serviceInterface, Class<?> serviceClass) {
        if (!serviceInterface.isInterface()) {
            throw new RuntimeException("Service interface expected.");
        }

        if (!serviceInterface.isAssignableFrom(serviceClass)) {
            throw new RuntimeException("Service class must implement the specified interface.");
        }

        services.put(serviceInterface.getSimpleName(), serviceClass);
    }

    public Object getService(Class<?> serviceClass) {
        return getService(serviceClass.getSimpleName());
    }

    public Object getService(String key) {
        Class<?> serviceClass = services.get(key);
        if (serviceClass != null) {
            
            try {
                Constructor<?>[] constructors = serviceClass.getConstructors();
                
                for (Constructor<?> constructor : constructors) {
                    
                    if (constructor.getParameterCount() == 0) {
                        return constructor.newInstance();
                        
                    } else {
                        
                        Object[] parameters = new Object[constructor.getParameterCount()];
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        
                        for (int i = 0; i < parameterTypes.length; i++) {
                            String parameterKey = parameterTypes[i].getSimpleName();
                            Object dependency = getService(parameterKey);
                            if (dependency == null) {
                                throw new RuntimeException("No registered service for " + parameterKey);
                            }
                            parameters[i] = dependency;
                        }
                        
                        return constructor.newInstance(parameters);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
