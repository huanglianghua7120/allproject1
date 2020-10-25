
package org.datacontract.schemas._2004._07.ufsoft_ubf_util;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.ufsoft_ubf_util package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ApplicationContext_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", "ApplicationContext");
    private final static QName _PlatformContext_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", "PlatformContext");
    private final static QName _ThreadContext_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", "ThreadContext");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.ufsoft_ubf_util
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicationContext }
     * 
     */
    public ApplicationContext createApplicationContext() {
        return new ApplicationContext();
    }

    /**
     * Create an instance of {@link PlatformContext }
     * 
     */
    public PlatformContext createPlatformContext() {
        return new PlatformContext();
    }

    /**
     * Create an instance of {@link ThreadContext }
     * 
     */
    public ThreadContext createThreadContext() {
        return new ThreadContext();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", name = "ApplicationContext")
    public JAXBElement<ApplicationContext> createApplicationContext(ApplicationContext value) {
        return new JAXBElement<ApplicationContext>(_ApplicationContext_QNAME, ApplicationContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlatformContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", name = "PlatformContext")
    public JAXBElement<PlatformContext> createPlatformContext(PlatformContext value) {
        return new JAXBElement<PlatformContext>(_PlatformContext_QNAME, PlatformContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThreadContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Util.Context", name = "ThreadContext")
    public JAXBElement<ThreadContext> createThreadContext(ThreadContext value) {
        return new JAXBElement<ThreadContext>(_ThreadContext_QNAME, ThreadContext.class, null, value);
    }

}
