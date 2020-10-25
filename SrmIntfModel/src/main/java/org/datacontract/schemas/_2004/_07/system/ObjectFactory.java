
package org.datacontract.schemas._2004._07.system;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.system package. 
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

    private final static QName _ExceptionDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "ExceptionDetail");
    private final static QName _Exception_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "Exception");
    private final static QName _ArrayOfException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ArrayOfException");
    private final static QName _ExceptionDetailHelpLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "HelpLink");
    private final static QName _ExceptionDetailInnerException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "InnerException");
    private final static QName _ExceptionDetailMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "Message");
    private final static QName _ExceptionDetailStackTrace_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "StackTrace");
    private final static QName _ExceptionDetailType_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "Type");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.system
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExceptionDetail }
     * 
     */
    public ExceptionDetail createExceptionDetail() {
        return new ExceptionDetail();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ArrayOfException }
     * 
     */
    public ArrayOfException createArrayOfException() {
        return new ArrayOfException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "ExceptionDetail")
    public JAXBElement<ExceptionDetail> createExceptionDetail(ExceptionDetail value) {
        return new JAXBElement<ExceptionDetail>(_ExceptionDetail_QNAME, ExceptionDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ArrayOfException")
    public JAXBElement<ArrayOfException> createArrayOfException(ArrayOfException value) {
        return new JAXBElement<ArrayOfException>(_ArrayOfException_QNAME, ArrayOfException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "HelpLink", scope = ExceptionDetail.class)
    public JAXBElement<String> createExceptionDetailHelpLink(String value) {
        return new JAXBElement<String>(_ExceptionDetailHelpLink_QNAME, String.class, ExceptionDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "InnerException", scope = ExceptionDetail.class)
    public JAXBElement<ExceptionDetail> createExceptionDetailInnerException(ExceptionDetail value) {
        return new JAXBElement<ExceptionDetail>(_ExceptionDetailInnerException_QNAME, ExceptionDetail.class, ExceptionDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "Message", scope = ExceptionDetail.class)
    public JAXBElement<String> createExceptionDetailMessage(String value) {
        return new JAXBElement<String>(_ExceptionDetailMessage_QNAME, String.class, ExceptionDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "StackTrace", scope = ExceptionDetail.class)
    public JAXBElement<String> createExceptionDetailStackTrace(String value) {
        return new JAXBElement<String>(_ExceptionDetailStackTrace_QNAME, String.class, ExceptionDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "Type", scope = ExceptionDetail.class)
    public JAXBElement<String> createExceptionDetailType(String value) {
        return new JAXBElement<String>(_ExceptionDetailType_QNAME, String.class, ExceptionDetail.class, value);
    }

}
