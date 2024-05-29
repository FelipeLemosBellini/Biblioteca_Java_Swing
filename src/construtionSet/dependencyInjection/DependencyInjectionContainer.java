package construtionSet.dependencyInjection;

import construtionSet.dependencyInjection.entities.EServiceLifetime;

public class DependencyInjectionContainer implements IDependencyInjectionContainer {
    private final IServiceLocator _serviceLocator;
    
    public DependencyInjectionContainer() {
        _serviceLocator = ServiceLocatorImpl.getInstance();
    }

    @Override
    public void addTransient(Class<?> serviceClass) {
        _serviceLocator.registerService(serviceClass, EServiceLifetime.Transient);
    }

    @Override
    public void addTransient(Class<?> serviceInterface, Class<?> serviceClass) {
        _serviceLocator.registerService(serviceInterface, serviceClass, EServiceLifetime.Transient);
    }

    @Override
    public void addScoped(Class<?> serviceClass) {
        _serviceLocator.registerService(serviceClass, EServiceLifetime.Scoped);
    }

    @Override
    public void addScoped(Class<?> serviceInterface, Class<?> serviceClass) {
        _serviceLocator.registerService(serviceInterface, serviceClass, EServiceLifetime.Scoped);
    }

    @Override
    public void addSingleton(Class<?> serviceClass) {
        _serviceLocator.registerService(serviceClass, EServiceLifetime.Singleton);
    }

    @Override
    public void addSingleton(Class<?> serviceInterface, Class<?> serviceClass) {
        _serviceLocator.registerService(serviceInterface, serviceClass, EServiceLifetime.Singleton);
    }
}
