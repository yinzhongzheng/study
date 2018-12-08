#IOC
###定位
###加载
###注册
**IOC-->XmlBeanFactory extends DefaultListableBeanFactory**
###BeanFactory
1. 


   `
    public void setConfigLocations(String... locations) {
    		if (locations != null) {
    			Assert.noNullElements(locations, "Config locations must not be null");
    			this.configLocations = new String[locations.length];
    			//解析path--->resolvePath()方法
    			for (int i = 0; i < locations.length; i++) {
    				this.configLocations[i] = resolvePath(locations[i]).trim();
    			}
    		}
    		else {
    			this.configLocations = null;
    		}
    	}
    `
    
2.resolvePath()方法


     @Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			
			//检查所依赖的propertites
			prepareRefresh();
			
            //进行beanFactory的清空操作
			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
              
			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
	
 ###BeanDefinition