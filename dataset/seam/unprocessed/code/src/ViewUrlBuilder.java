
package org.jboss.seam.ui.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.jboss.seam.navigation.Page;
import org.jboss.seam.navigation.Pages;

public class ViewUrlBuilder extends UrlBuilder
{

   private Page page;
   private String viewId;

   public ViewUrlBuilder(String viewId, String fragment, boolean urlEncodeParameters)
   {
      super(fragment, FacesContext.getCurrentInstance().getResponseWriter().getCharacterEncoding(), urlEncodeParameters);
      if (viewId == null)
      {
         throw new NullPointerException("viewId must not be null");
      }
      FacesContext facesContext = FacesContext.getCurrentInstance();
      // will use bookmarkable URL later in getEncodeUrl() method which already knows all added Parameters
      //String url = facesContext.getApplication().getViewHandler().getBookmarkableURL(facesContext, viewId, null, false);
      String url = facesContext.getApplication().getViewHandler().getActionURL(facesContext, viewId);
      url = Pages.instance().encodeScheme(viewId, facesContext, url);      
      setUrl(url);
      this.page = Pages.instance().getPage(viewId);
      this.viewId = viewId;
   }
   
   public ViewUrlBuilder(String viewId, String fragment)
   {
      this(viewId, fragment, false);
      
   }

   @Override
   public void addParameter(UIParameter parameter) throws UnsupportedEncodingException
   {
      String name = parameter.getName();
      if (parameter.getValue() != null && !(name.equals(page.getConversationIdParameter().getParameterName())
               && getParameters().containsKey(name)))
      {
        super.addParameter(parameter);
      }
   }

    @Override
    public String getEncodedUrl() 
    {
       FacesContext facesContext = FacesContext.getCurrentInstance();
       return facesContext.getApplication().getViewHandler().getBookmarkableURL(facesContext, this.viewId, getParametersAsMap(getParameters()), false);
    }

    /*
     * Converts Seam parameters Map <String,String> to JSF 2 Map<String,List<String>>
     */
    private Map<String,List<String>> getParametersAsMap(Map <String,String> params)
    {
       Map<String,List<String>> parameters = new HashMap<String, List<String>>();
              
       for(Map.Entry<String, String> entry : params.entrySet()) 
       {
          List<String> list = new ArrayList<String>();
          list.add(entry.getValue());
          parameters.put( ((String) entry.getKey()), list);
       }
       
       return parameters;
    }
}
