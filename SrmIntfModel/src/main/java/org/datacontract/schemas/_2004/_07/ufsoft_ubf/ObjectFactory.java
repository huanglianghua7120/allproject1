
package org.datacontract.schemas._2004._07.ufsoft_ubf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.ufsoft.ExceptionBase;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.ufsoft_ubf package. 
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

    private final static QName _MessageBaseFormatState_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Exceptions", "MessageBase.FormatState");
    private final static QName _BusinessException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", "BusinessException");
    private final static QName _AttrsContainerException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", "AttrsContainerException");
    private final static QName _AttributeInValidException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", "AttributeInValidException");
    private final static QName _EntityNotExistException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", "EntityNotExistException");
    private final static QName _ServiceLostException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", "ServiceLostException");
    private final static QName _ServiceException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", "ServiceException");
    private final static QName _ServiceExceptionDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", "ServiceExceptionDetail");
    private final static QName _ServiceExceptionDetailException_QNAME = new QName("http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.ufsoft_ubf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessException }
     * 
     */
    public BusinessException createBusinessException() {
        return new BusinessException();
    }

    /**
     * Create an instance of {@link AttrsContainerException }
     * 
     */
    public AttrsContainerException createAttrsContainerException() {
        return new AttrsContainerException();
    }

    /**
     * Create an instance of {@link AttributeInValidException }
     * 
     */
    public AttributeInValidException createAttributeInValidException() {
        return new AttributeInValidException();
    }

    /**
     * Create an instance of {@link EntityNotExistException }
     * 
     */
    public EntityNotExistException createEntityNotExistException() {
        return new EntityNotExistException();
    }

    /**
     * Create an instance of {@link ServiceLostException }
     * 
     */
    public ServiceLostException createServiceLostException() {
        return new ServiceLostException();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link ServiceExceptionDetail }
     * 
     */
    public ServiceExceptionDetail createServiceExceptionDetail() {
        return new ServiceExceptionDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageBaseFormatState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Exceptions", name = "MessageBase.FormatState")
    public JAXBElement<MessageBaseFormatState> createMessageBaseFormatState(MessageBaseFormatState value) {
        return new JAXBElement<MessageBaseFormatState>(_MessageBaseFormatState_QNAME, MessageBaseFormatState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", name = "BusinessException")
    public JAXBElement<BusinessException> createBusinessException(BusinessException value) {
        return new JAXBElement<BusinessException>(_BusinessException_QNAME, BusinessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttrsContainerException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", name = "AttrsContainerException")
    public JAXBElement<AttrsContainerException> createAttrsContainerException(AttrsContainerException value) {
        return new JAXBElement<AttrsContainerException>(_AttrsContainerException_QNAME, AttrsContainerException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeInValidException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", name = "AttributeInValidException")
    public JAXBElement<AttributeInValidException> createAttributeInValidException(AttributeInValidException value) {
        return new JAXBElement<AttributeInValidException>(_AttributeInValidException_QNAME, AttributeInValidException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityNotExistException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Business", name = "EntityNotExistException")
    public JAXBElement<EntityNotExistException> createEntityNotExistException(EntityNotExistException value) {
        return new JAXBElement<EntityNotExistException>(_EntityNotExistException_QNAME, EntityNotExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceLostException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", name = "ServiceLostException")
    public JAXBElement<ServiceLostException> createServiceLostException(ServiceLostException value) {
        return new JAXBElement<ServiceLostException>(_ServiceLostException_QNAME, ServiceLostException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceExceptionDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", name = "ServiceExceptionDetail")
    public JAXBElement<ServiceExceptionDetail> createServiceExceptionDetail(ServiceExceptionDetail value) {
        return new JAXBElement<ServiceExceptionDetail>(_ServiceExceptionDetail_QNAME, ServiceExceptionDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", name = "Exception", scope = ServiceExceptionDetail.class)
    public JAXBElement<ExceptionBase> createServiceExceptionDetailException(ExceptionBase value) {
        return new JAXBElement<ExceptionBase>(_ServiceExceptionDetailException_QNAME, ExceptionBase.class, ServiceExceptionDetail.class, value);
    }

}
