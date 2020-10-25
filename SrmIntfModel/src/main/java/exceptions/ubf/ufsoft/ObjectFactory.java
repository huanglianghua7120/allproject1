
package exceptions.ubf.ufsoft;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the exceptions.ubf.ufsoft package. 
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

    private final static QName _MessageBase_QNAME = new QName("UFSoft.UBF.Exceptions", "MessageBase");
    private final static QName _ArrayOfMessageBase_QNAME = new QName("UFSoft.UBF.Exceptions", "ArrayOfMessageBase");
    private final static QName _MessageBaseAttributeMetadataID_QNAME = new QName("UFSoft.UBF.Exceptions", "attributeMetadataID");
    private final static QName _MessageBaseAttributeName_QNAME = new QName("UFSoft.UBF.Exceptions", "attributeName");
    private final static QName _MessageBaseEntityFullName_QNAME = new QName("UFSoft.UBF.Exceptions", "entityFullName");
    private final static QName _MessageBaseEntityMetadataID_QNAME = new QName("UFSoft.UBF.Exceptions", "entityMetadataID");
    private final static QName _MessageBaseInnerMessages_QNAME = new QName("UFSoft.UBF.Exceptions", "innerMessages");
    private final static QName _MessageBaseLocalMessage_QNAME = new QName("UFSoft.UBF.Exceptions", "localMessage");
    private final static QName _MessageBaseOrginalEntityFullName_QNAME = new QName("UFSoft.UBF.Exceptions", "orginalEntityFullName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: exceptions.ubf.ufsoft
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageBase }
     * 
     */
    public MessageBase createMessageBase() {
        return new MessageBase();
    }

    /**
     * Create an instance of {@link ArrayOfMessageBase }
     * 
     */
    public ArrayOfMessageBase createArrayOfMessageBase() {
        return new ArrayOfMessageBase();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "MessageBase")
    public JAXBElement<MessageBase> createMessageBase(MessageBase value) {
        return new JAXBElement<MessageBase>(_MessageBase_QNAME, MessageBase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "ArrayOfMessageBase")
    public JAXBElement<ArrayOfMessageBase> createArrayOfMessageBase(ArrayOfMessageBase value) {
        return new JAXBElement<ArrayOfMessageBase>(_ArrayOfMessageBase_QNAME, ArrayOfMessageBase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "attributeMetadataID", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseAttributeMetadataID(String value) {
        return new JAXBElement<String>(_MessageBaseAttributeMetadataID_QNAME, String.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "attributeName", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseAttributeName(String value) {
        return new JAXBElement<String>(_MessageBaseAttributeName_QNAME, String.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "entityFullName", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseEntityFullName(String value) {
        return new JAXBElement<String>(_MessageBaseEntityFullName_QNAME, String.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "entityMetadataID", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseEntityMetadataID(String value) {
        return new JAXBElement<String>(_MessageBaseEntityMetadataID_QNAME, String.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "innerMessages", scope = MessageBase.class)
    public JAXBElement<ArrayOfMessageBase> createMessageBaseInnerMessages(ArrayOfMessageBase value) {
        return new JAXBElement<ArrayOfMessageBase>(_MessageBaseInnerMessages_QNAME, ArrayOfMessageBase.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "localMessage", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseLocalMessage(String value) {
        return new JAXBElement<String>(_MessageBaseLocalMessage_QNAME, String.class, MessageBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "UFSoft.UBF.Exceptions", name = "orginalEntityFullName", scope = MessageBase.class)
    public JAXBElement<String> createMessageBaseOrginalEntityFullName(String value) {
        return new JAXBElement<String>(_MessageBaseOrginalEntityFullName_QNAME, String.class, MessageBase.class, value);
    }

}
