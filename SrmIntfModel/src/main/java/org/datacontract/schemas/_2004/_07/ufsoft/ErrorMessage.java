
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ErrorMessage complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ErrorMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errDescriptor" type="{http://schemas.datacontract.org/2004/07/UFSoft.UBF}ErrorDescriptor"/&gt;
 *         &lt;element name="errorType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="innerMessages" type="{http://schemas.datacontract.org/2004/07/UFSoft.UBF}ArrayOfErrorMessage" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorMessage", propOrder = {
    "errDescriptor",
    "errorType",
    "innerMessages",
    "message"
})
public class ErrorMessage {

    @XmlElement(required = true, nillable = true)
    protected ErrorDescriptor errDescriptor;
    @XmlElement(required = true, nillable = true)
    protected String errorType;
    @XmlElementRef(name = "innerMessages", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfErrorMessage> innerMessages;
    @XmlElementRef(name = "message", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF", type = JAXBElement.class, required = false)
    protected JAXBElement<String> message;

    /**
     * 获取errDescriptor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ErrorDescriptor }
     *     
     */
    public ErrorDescriptor getErrDescriptor() {
        return errDescriptor;
    }

    /**
     * 设置errDescriptor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorDescriptor }
     *     
     */
    public void setErrDescriptor(ErrorDescriptor value) {
        this.errDescriptor = value;
    }

    /**
     * 获取errorType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * 设置errorType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorType(String value) {
        this.errorType = value;
    }

    /**
     * 获取innerMessages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfErrorMessage }{@code >}
     *     
     */
    public JAXBElement<ArrayOfErrorMessage> getInnerMessages() {
        return innerMessages;
    }

    /**
     * 设置innerMessages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfErrorMessage }{@code >}
     *     
     */
    public void setInnerMessages(JAXBElement<ArrayOfErrorMessage> value) {
        this.innerMessages = value;
    }

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessage(JAXBElement<String> value) {
        this.message = value;
    }

}
