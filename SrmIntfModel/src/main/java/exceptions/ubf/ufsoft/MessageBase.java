
package exceptions.ubf.ufsoft;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ufsoft_ubf.MessageBaseFormatState;


/**
 * <p>MessageBase complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MessageBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="attributeMetadataID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="attributeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entityFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entityID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="entityMetadataID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="errorLevel" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="formated" type="{http://schemas.datacontract.org/2004/07/UFSoft.UBF.Exceptions}MessageBase.FormatState" minOccurs="0"/&gt;
 *         &lt;element name="innerMessages" type="{UFSoft.UBF.Exceptions}ArrayOfMessageBase" minOccurs="0"/&gt;
 *         &lt;element name="isValidEntityID" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="localMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orginalEntityFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageBase", propOrder = {
    "attributeMetadataID",
    "attributeName",
    "entityFullName",
    "entityID",
    "entityMetadataID",
    "errorLevel",
    "formated",
    "innerMessages",
    "isValidEntityID",
    "localMessage",
    "orginalEntityFullName"
})
public class MessageBase {

    @XmlElementRef(name = "attributeMetadataID", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeMetadataID;
    @XmlElementRef(name = "attributeName", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeName;
    @XmlElementRef(name = "entityFullName", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> entityFullName;
    protected Long entityID;
    @XmlElementRef(name = "entityMetadataID", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> entityMetadataID;
    protected Short errorLevel;
    @XmlSchemaType(name = "string")
    protected MessageBaseFormatState formated;
    @XmlElementRef(name = "innerMessages", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfMessageBase> innerMessages;
    protected Boolean isValidEntityID;
    @XmlElementRef(name = "localMessage", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> localMessage;
    @XmlElementRef(name = "orginalEntityFullName", namespace = "UFSoft.UBF.Exceptions", type = JAXBElement.class, required = false)
    protected JAXBElement<String> orginalEntityFullName;

    /**
     * 获取attributeMetadataID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeMetadataID() {
        return attributeMetadataID;
    }

    /**
     * 设置attributeMetadataID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeMetadataID(JAXBElement<String> value) {
        this.attributeMetadataID = value;
    }

    /**
     * 获取attributeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeName() {
        return attributeName;
    }

    /**
     * 设置attributeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeName(JAXBElement<String> value) {
        this.attributeName = value;
    }

    /**
     * 获取entityFullName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntityFullName() {
        return entityFullName;
    }

    /**
     * 设置entityFullName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntityFullName(JAXBElement<String> value) {
        this.entityFullName = value;
    }

    /**
     * 获取entityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntityID() {
        return entityID;
    }

    /**
     * 设置entityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntityID(Long value) {
        this.entityID = value;
    }

    /**
     * 获取entityMetadataID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntityMetadataID() {
        return entityMetadataID;
    }

    /**
     * 设置entityMetadataID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntityMetadataID(JAXBElement<String> value) {
        this.entityMetadataID = value;
    }

    /**
     * 获取errorLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getErrorLevel() {
        return errorLevel;
    }

    /**
     * 设置errorLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setErrorLevel(Short value) {
        this.errorLevel = value;
    }

    /**
     * 获取formated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MessageBaseFormatState }
     *     
     */
    public MessageBaseFormatState getFormated() {
        return formated;
    }

    /**
     * 设置formated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MessageBaseFormatState }
     *     
     */
    public void setFormated(MessageBaseFormatState value) {
        this.formated = value;
    }

    /**
     * 获取innerMessages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMessageBase> getInnerMessages() {
        return innerMessages;
    }

    /**
     * 设置innerMessages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}
     *     
     */
    public void setInnerMessages(JAXBElement<ArrayOfMessageBase> value) {
        this.innerMessages = value;
    }

    /**
     * 获取isValidEntityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsValidEntityID() {
        return isValidEntityID;
    }

    /**
     * 设置isValidEntityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsValidEntityID(Boolean value) {
        this.isValidEntityID = value;
    }

    /**
     * 获取localMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalMessage() {
        return localMessage;
    }

    /**
     * 设置localMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalMessage(JAXBElement<String> value) {
        this.localMessage = value;
    }

    /**
     * 获取orginalEntityFullName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrginalEntityFullName() {
        return orginalEntityFullName;
    }

    /**
     * 设置orginalEntityFullName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrginalEntityFullName(JAXBElement<String> value) {
        this.orginalEntityFullName = value;
    }

}
