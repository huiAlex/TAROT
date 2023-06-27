package experiment.preprocess.parsecode.util;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JavaToXmlUtil {
    private static JAXBContext jaxbContext;

    //convert xml to Java object
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml,Class<T> c){
        T t = null;
        try {
            jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
        }
        return t;
    }

    //convert Java object to xml
    public static String beanToXml(Object obj){
        StringWriter writer = null;
        try {
            jaxbContext=JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
			/*
			* Marshaller.JAXB_FRAGMENT : Whether to omit the xml header information
			* true : omit
			* false : not omit
			* */
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			/*
			* Marshaller.JAXB_FORMATTED_OUTPUT : Whether to format the xml
			* true : format the xml
			* false : the xml in one line
			* */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			/*
			* Marshaller.JAXB_ENCODING : the encoding of xml
			* */
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
        } catch (JAXBException e) {
        }
        return writer.toString();
    }

}
