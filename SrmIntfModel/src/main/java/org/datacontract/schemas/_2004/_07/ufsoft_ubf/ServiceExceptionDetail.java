
package org.datacontract.schemas._2004._07.ufsoft_ubf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.system.ExceptionDetail;
import org.datacontract.schemas._2004._07.ufsoft.ExceptionBase;


/**
 * <p>ServiceExceptionDetail complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ServiceExceptionDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/System.ServiceModel}ExceptionDetail"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Exception" type="{http://schemas.datacontract.org/2004/07/UFSoft.UBF}ExceptionBase" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceExceptionDetail", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", propOrder = {
    "exception"
})
public class ServiceExceptionDetail
    extends ExceptionDetail
{

    @XmlElementRef(name = "Exception", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", type = JAXBElement.class, required = false)
    protected JAXBElement<ExceptionBase> exception;

    /**
     * ��ȡexception���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}
     *     
     */
    public JAXBElement<ExceptionBase> getException() {
        return exception;
    }

    /**
     * ����exception���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}
     *     
     */
    public void setException(JAXBElement<ExceptionBase> value) {
        this.exception = value;
    }

}
