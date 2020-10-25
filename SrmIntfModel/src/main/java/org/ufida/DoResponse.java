
package org.ufida;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import exceptions.ubf.ufsoft.ArrayOfMessageBase;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="outMessages" type="{UFSoft.UBF.Exceptions}ArrayOfMessageBase" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "doResult",
    "outMessages"
})
@XmlRootElement(name = "DoResponse")
public class DoResponse {

    @XmlElementRef(name = "DoResult", namespace = "http://www.UFIDA.org", type = JAXBElement.class, required = false)
    protected JAXBElement<String> doResult;
    @XmlElementRef(name = "outMessages", namespace = "http://www.UFIDA.org", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfMessageBase> outMessages;

    /**
     * ��ȡdoResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoResult() {
        return doResult;
    }

    /**
     * ����doResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoResult(JAXBElement<String> value) {
        this.doResult = value;
    }

    /**
     * ��ȡoutMessages���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMessageBase> getOutMessages() {
        return outMessages;
    }

    /**
     * ����outMessages���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMessageBase }{@code >}
     *     
     */
    public void setOutMessages(JAXBElement<ArrayOfMessageBase> value) {
        this.outMessages = value;
    }

}
