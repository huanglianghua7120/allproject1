
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.ufsoft package. 
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

    private final static QName _ExceptionBase_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ExceptionBase");
    private final static QName _UnknownException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "UnknownException");
    private final static QName _ErrorMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ErrorMessage");
    private final static QName _ErrorDescriptor_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ErrorDescriptor");
    private final static QName _ErrorLevel_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ErrorLevel");
    private final static QName _ArrayOfErrorMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ArrayOfErrorMessage");
    private final static QName _ExceptionBaseFormatState_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "ExceptionBase.FormatState");
    private final static QName _ErrorDescriptorCategory_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "category");
    private final static QName _ErrorDescriptorOwner_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "owner");
    private final static QName _ErrorMessageInnerMessages_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "innerMessages");
    private final static QName _ErrorMessageMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF", "message");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.ufsoft
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExceptionBase }
     * 
     */
    public ExceptionBase createExceptionBase() {
        return new ExceptionBase();
    }

    /**
     * Create an instance of {@link UnknownException }
     * 
     */
    public UnknownException createUnknownException() {
        return new UnknownException();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link ErrorDescriptor }
     * 
     */
    public ErrorDescriptor createErrorDescriptor() {
        return new ErrorDescriptor();
    }

    /**
     * Create an instance of {@link ArrayOfErrorMessage }
     * 
     */
    public ArrayOfErrorMessage createArrayOfErrorMessage() {
        return new ArrayOfErrorMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ExceptionBase")
    public JAXBElement<ExceptionBase> createExceptionBase(ExceptionBase value) {
        return new JAXBElement<ExceptionBase>(_ExceptionBase_QNAME, ExceptionBase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "UnknownException")
    public JAXBElement<UnknownException> createUnknownException(UnknownException value) {
        return new JAXBElement<UnknownException>(_UnknownException_QNAME, UnknownException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ErrorMessage")
    public JAXBElement<ErrorMessage> createErrorMessage(ErrorMessage value) {
        return new JAXBElement<ErrorMessage>(_ErrorMessage_QNAME, ErrorMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ErrorDescriptor")
    public JAXBElement<ErrorDescriptor> createErrorDescriptor(ErrorDescriptor value) {
        return new JAXBElement<ErrorDescriptor>(_ErrorDescriptor_QNAME, ErrorDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorLevel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ErrorLevel")
    public JAXBElement<ErrorLevel> createErrorLevel(ErrorLevel value) {
        return new JAXBElement<ErrorLevel>(_ErrorLevel_QNAME, ErrorLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfErrorMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ArrayOfErrorMessage")
    public JAXBElement<ArrayOfErrorMessage> createArrayOfErrorMessage(ArrayOfErrorMessage value) {
        return new JAXBElement<ArrayOfErrorMessage>(_ArrayOfErrorMessage_QNAME, ArrayOfErrorMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionBaseFormatState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "ExceptionBase.FormatState")
    public JAXBElement<ExceptionBaseFormatState> createExceptionBaseFormatState(ExceptionBaseFormatState value) {
        return new JAXBElement<ExceptionBaseFormatState>(_ExceptionBaseFormatState_QNAME, ExceptionBaseFormatState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "category", scope = ErrorDescriptor.class)
    public JAXBElement<String> createErrorDescriptorCategory(String value) {
        return new JAXBElement<String>(_ErrorDescriptorCategory_QNAME, String.class, ErrorDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "owner", scope = ErrorDescriptor.class)
    public JAXBElement<String> createErrorDescriptorOwner(String value) {
        return new JAXBElement<String>(_ErrorDescriptorOwner_QNAME, String.class, ErrorDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfErrorMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "innerMessages", scope = ErrorMessage.class)
    public JAXBElement<ArrayOfErrorMessage> createErrorMessageInnerMessages(ArrayOfErrorMessage value) {
        return new JAXBElement<ArrayOfErrorMessage>(_ErrorMessageInnerMessages_QNAME, ArrayOfErrorMessage.class, ErrorMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", name = "message", scope = ErrorMessage.class)
    public JAXBElement<String> createErrorMessageMessage(String value) {
        return new JAXBElement<String>(_ErrorMessageMessage_QNAME, String.class, ErrorMessage.class, value);
    }

}
