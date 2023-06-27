package org.jboss.seam.jsf;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContextListener;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ProjectStage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.StateManager;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.el.PropertyResolver;
import javax.faces.el.ReferenceSyntaxException;
import javax.faces.el.ValueBinding;
import javax.faces.el.VariableResolver;
import javax.faces.event.ActionListener;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.faces.validator.Validator;

import org.jboss.seam.Component;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Init;
import org.jboss.seam.el.SeamExpressionFactory;

/**
 * Proxies the JSF Application object, and adds all kinds
 * of tasty extras.
 * 
 * @author Gavin King
 *
 */
@SuppressWarnings("deprecation")
public class SeamApplication extends Application
{  
   
   protected Application application;
      
   public SeamApplication(Application application)
   {
      this.application = application;
   }
   
   public Application getDelegate()
   {
      return application;
   }
   
   @Override
   public ELResolver getELResolver() 
   {
      return application.getELResolver();
   }

   @Override
   public void addComponent(String componentType, String componentClass)
   {
      application.addComponent(componentType, componentClass);
   }

   @Override
   public void addConverter(String converterId, String converterClass)
   {
      application.addConverter(converterId, converterClass);
   }

   @Override
   public void addConverter(Class targetClass, String converterClass)
   {
      application.addConverter(targetClass, converterClass);
   }

   @Override
   public void addValidator(String validatorId, String validatorClass)
   {
      application.addValidator(validatorId, validatorClass);
   }

   @Override
   public UIComponent createComponent(String componentType)
         throws FacesException
   {
      return application.createComponent(componentType);
   }

   @Override
   public UIComponent createComponent(ValueBinding componentBinding,
         FacesContext context, String componentType) throws FacesException
   {
      return application.createComponent(componentBinding, context, componentType);
   }
   
   @Override
   public UIComponent createComponent(FacesContext context, 
         String componentType, String rendererType)
   {
      return application.createComponent(context, componentType, rendererType);
   }
   
   @Override
   public UIComponent createComponent(FacesContext context, Resource resource)
   {
      return application.createComponent(context, resource);
   }

   @Override
   public Converter createConverter(String converterId)
   {
      if ( Contexts.isApplicationContextActive() )
      {
         String name = Init.instance().getConverters().get(converterId);
         if (name!=null)
         {
            return (Converter) Component.getInstance(name);
         }
      }
      return application.createConverter(converterId);
   }

   @Override
   public Converter createConverter(Class targetClass)
   {
      Converter converter = null;
      if ( Contexts.isApplicationContextActive() )
      {
         converter = new ConverterLocator(targetClass).getConverter();
      }
      if (converter == null)
      {
         converter = application.createConverter(targetClass);
      }
      return converter;
   }
   
   private class ConverterLocator 
   {
      
      private Map<Class, String> converters;
      private Class targetClass;
      private Converter converter;
      
      public ConverterLocator(Class targetClass)
      {
         converters = Init.instance().getConvertersByClass();
         this.targetClass = targetClass;
      }
      
      public Converter getConverter()
      {
         if (converter == null)
         {
            locateConverter(targetClass);
         }
         return converter;
      }
      
      private Converter createConverter(Class clazz)
      {
         return (Converter) Component.getInstance(converters.get(clazz));
      }
      
      private void locateConverter(Class clazz)
      {
         if (converters.containsKey(clazz))
         {
            converter = createConverter(clazz);
            return;
         }
         
         for (Class _interface: clazz.getInterfaces())
         {
            if (converters.containsKey(_interface))
            {
               converter = createConverter(_interface);
               return;
            }
            else
            {
               locateConverter(_interface);
               if (converter != null)
               {
                  return;
               }
            }
         }
         
         Class superClass = clazz.getSuperclass();
         if (converters.containsKey(superClass))
         {
            converter = createConverter(superClass);
            return;
         }
         else if (superClass != null)
         {
            locateConverter(superClass);
         }
      }
   }

   @Override
   public Validator createValidator(String validatorId) throws FacesException
   {
      if ( Contexts.isApplicationContextActive() )
      {
         String name = Init.instance().getValidators().get(validatorId);
         if (name!=null)
         {
            return (Validator) Component.getInstance(name);
         }
      }
      return application.createValidator(validatorId);
   }

   @Override
   public MethodBinding createMethodBinding(String expression, Class[] params)
         throws ReferenceSyntaxException
   {
      return new UnifiedELMethodBinding(expression, params);

   }

   @Override
   public ValueBinding createValueBinding(String expression)
         throws ReferenceSyntaxException
   {
      return new UnifiedELValueBinding(expression);
   }

   @Override
   public ActionListener getActionListener()
   {
      return application.getActionListener();
   }

   @Override
   public Iterator getComponentTypes()
   {
      return application.getComponentTypes();
   }

   @Override
   public Iterator getConverterIds()
   {
      return application.getConverterIds();
   }

   @Override
   public Iterator getConverterTypes()
   {
      return application.getComponentTypes();
   }

   @Override
   public Locale getDefaultLocale()
   {
      return application.getDefaultLocale();
   }

   @Override
   public String getDefaultRenderKitId()
   {
      return application.getDefaultRenderKitId();
   }

   @Override
   public String getMessageBundle()
   {
      return application.getMessageBundle();
      //obsolete, now handled by faces-config.xml:
      /*String messageBundle = application.getMessageBundle();
      if (messageBundle!=null)
      {
         return messageBundle;
      }
      else
      {
         return "org.jboss.seam.core.SeamResourceBundle";
      }*/
   }

   @Override
   public NavigationHandler getNavigationHandler()
   {
      return application.getNavigationHandler();
   }

   @Override
   public PropertyResolver getPropertyResolver()
   {
      return application.getPropertyResolver();
   }

   @Override
   public StateManager getStateManager()
   {
      return application.getStateManager();
   }

   @Override
   public Iterator getSupportedLocales()
   {
      return application.getSupportedLocales();
   }

   @Override
   public Iterator getValidatorIds()
   {
      return application.getValidatorIds();
   }

   @Override
   public VariableResolver getVariableResolver()
   {
      return application.getVariableResolver();
   }

   @Override
   public ViewHandler getViewHandler()
   {
      return application.getViewHandler();
   }

   @Override
   public void setActionListener(ActionListener listener)
   {
      application.setActionListener(listener);
   }

   @Override
   public void setDefaultLocale(Locale locale)
   {
      application.setDefaultLocale(locale);
   }

   @Override
   public void setDefaultRenderKitId(String renderKitId)
   {
      application.setDefaultRenderKitId(renderKitId);
   }

   @Override
   public void setMessageBundle(String bundle)
   {
      application.setMessageBundle(bundle);
   }

   @Override
   public void setNavigationHandler(NavigationHandler handler)
   {
      application.setNavigationHandler(handler);
   }

   @Override
   public void setPropertyResolver(PropertyResolver resolver)
   {
      application.setPropertyResolver(resolver);
   }

   @Override
   public void setStateManager(StateManager manager)
   {
      application.setStateManager(manager);
   }

   @Override
   public void setSupportedLocales(Collection locales)
   {
      application.setSupportedLocales(locales);
   }

   @Override
   public void setVariableResolver(VariableResolver resolver)
   {
      application.setVariableResolver(resolver);
   }

   @Override
   public void setViewHandler(ViewHandler handler)
   {
      application.setViewHandler(handler);
   }

   @Override
   public void addELContextListener(ELContextListener elcl)
   {
      application.addELContextListener(elcl);
   }

   @Override
   public void addELResolver(ELResolver elr)
   {
      application.addELResolver(elr);
   }

   @Override
   public UIComponent createComponent(ValueExpression ve, FacesContext fc, String id) throws FacesException
   {
      return application.createComponent(ve, fc, id);
   }

   @Override
   public Object evaluateExpressionGet(FacesContext ctx, String expr, Class type) throws ELException
   {
      return getExpressionFactory().createValueExpression( ctx.getELContext(), expr, type).getValue( ctx.getELContext() );
   }

   @Override
   public ELContextListener[] getELContextListeners()
   {
      return application.getELContextListeners();
   }

   @Override
   public ExpressionFactory getExpressionFactory()
   {
      //JBoss EL
      return SeamExpressionFactory.INSTANCE;
   }

   @Override
   public ResourceBundle getResourceBundle(FacesContext fc, String name)
   {
      return application.getResourceBundle(fc, name);
   }

   @Override
   public void removeELContextListener(ELContextListener elcl)
   {
      application.removeELContextListener(elcl);
   }

   @Override
   public String toString()
   {
      return application.toString();
   }
   
   @Override
   public void publishEvent(FacesContext context, Class<? extends SystemEvent> systemEventClass, Object source)
   {
      application.publishEvent(context, systemEventClass, source);      
   }

   @Override
   public void publishEvent(FacesContext context, Class<? extends SystemEvent> systemEventClass, Class<?> sourceBaseType, Object source)
   {
      application.publishEvent(context, systemEventClass, sourceBaseType, source);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#createBehavior(java.lang.String)
    */
   @Override
   public Behavior createBehavior(String behaviorId) throws FacesException
   {
      return application.createBehavior(behaviorId);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#getBehaviorIds()
    */
   @Override
   public Iterator<String> getBehaviorIds()
   {
      return application.getBehaviorIds();
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#getResourceHandler()
    */
   @Override
   public ResourceHandler getResourceHandler()
   {
      return application.getResourceHandler();
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#setResourceHandler(javax.faces.application.ResourceHandler)
    */
   @Override
   public void setResourceHandler(ResourceHandler resourceHandler)
   {
      application.setResourceHandler(resourceHandler);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#getProjectStage()
    */
   @Override
   public ProjectStage getProjectStage()
   {
      return application.getProjectStage();
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#addBehavior(java.lang.String, java.lang.String)
    */
   @Override
   public void addBehavior(String behaviorId, String behaviorClass)
   {
      application.addBehavior(behaviorId, behaviorClass);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#createComponent(javax.el.ValueExpression, javax.faces.context.FacesContext, java.lang.String, java.lang.String)
    */
   @Override
   public UIComponent createComponent(ValueExpression componentExpression, FacesContext context, String componentType, String rendererType)
   {
      return application.createComponent(componentExpression, context, componentType, rendererType);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#addDefaultValidatorId(java.lang.String)
    */
   @Override
   public void addDefaultValidatorId(String validatorId)
   {
      application.addDefaultValidatorId(validatorId);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#getDefaultValidatorInfo()
    */
   @Override
   public Map<String, String> getDefaultValidatorInfo()
   {
      return application.getDefaultValidatorInfo();
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#subscribeToEvent(java.lang.Class, java.lang.Class, javax.faces.event.SystemEventListener)
    */
   @Override
   public void subscribeToEvent(Class<? extends SystemEvent> systemEventClass, Class<?> sourceClass, SystemEventListener listener)
   {
      application.subscribeToEvent(systemEventClass, sourceClass, listener);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#subscribeToEvent(java.lang.Class, javax.faces.event.SystemEventListener)
    */
   @Override
   public void subscribeToEvent(Class<? extends SystemEvent> systemEventClass, SystemEventListener listener)
   {
      application.subscribeToEvent(systemEventClass, listener);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#unsubscribeFromEvent(java.lang.Class, java.lang.Class, javax.faces.event.SystemEventListener)
    */
   @Override
   public void unsubscribeFromEvent(Class<? extends SystemEvent> systemEventClass, Class<?> sourceClass, SystemEventListener listener)
   {
      application.unsubscribeFromEvent(systemEventClass, sourceClass, listener);
   }

   /* (non-Javadoc)
    * @see javax.faces.application.Application#unsubscribeFromEvent(java.lang.Class, javax.faces.event.SystemEventListener)
    */
   @Override
   public void unsubscribeFromEvent(Class<? extends SystemEvent> systemEventClass, SystemEventListener listener)
   {
      application.unsubscribeFromEvent(systemEventClass, listener);
   }
}
