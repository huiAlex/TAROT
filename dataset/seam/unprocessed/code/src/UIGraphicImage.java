package org.jboss.seam.ui.graphicImage;

import javax.faces.component.html.HtmlGraphicImage;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.Description;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;

/**
 * Outputs an image to the view. You can specify the file as a path, a java.io.File, java.io.InputStream, java.net.URL or byte[]
 * @author mnovotny
 *
 */
@JsfComponent(description=@Description(displayName="org.jboss.seam.ui.graphicImage.GraphicImage",value="Output an image to the view. You can specify the file as a path, a java.io.File, java.io.InputStream, java.net.URL or byte[]"),
family="org.jboss.seam.ui.graphicImage.GraphicImage", type="org.jboss.seam.ui.graphicImage.GraphicImage",generate="org.jboss.seam.ui.component.html.HtmlGraphicImage", 
tag = @Tag(baseClass="org.jboss.seam.ui.util.cdk.UIComponentTagBase", name="graphicImage"), 
renderer = @JsfRenderer(type="org.jboss.seam.ui.graphicImage.GraphicImageRenderer", family="org.jboss.seam.ui.GraphicImageRenderer"),
attributes = {"core-props.xml", "javax.faces.component.UIGraphic.xml", "javax.faces.component.UIOutput.xml" })
public abstract class UIGraphicImage extends HtmlGraphicImage
{

   public static final String FAMILY = "org.jboss.seam.ui.UIGraphicImage";

   @Attribute(description = @Description("File name for the generated URL - allows a stable file name and thus browser caching"))
   public abstract String getFileName();

   @Attribute
   public abstract String getStyle();
   
   @Attribute
   public abstract Object getValue();
   
   @Attribute(description = @Description("True if this image should be cached"))
   public abstract boolean isCache();
   
   @Attribute(description = @Description("Unique string for a cached image"))
   public abstract String getCacheKey();
   
   @Attribute(description = @Description("if property resolves to true, current cached key will be removed"))
   public abstract boolean isInvalidate();
}
