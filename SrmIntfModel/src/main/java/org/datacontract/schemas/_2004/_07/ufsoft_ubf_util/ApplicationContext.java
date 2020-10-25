
package org.datacontract.schemas._2004._07.ufsoft_ubf_util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfanyTypeanyType;


/**
 * <p>ApplicationContext complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ApplicationContext"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nameValueHas" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfanyTypeanyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationContext", propOrder = {
    "nameValueHas"
})
public class ApplicationContext {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfKeyValueOfanyTypeanyType nameValueHas;

    /**
     * 获取nameValueHas属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyValueOfanyTypeanyType }
     *     
     */
    public ArrayOfKeyValueOfanyTypeanyType getNameValueHas() {
        return nameValueHas;
    }

    /**
     * 设置nameValueHas属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyValueOfanyTypeanyType }
     *     
     */
    public void setNameValueHas(ArrayOfKeyValueOfanyTypeanyType value) {
        this.nameValueHas = value;
    }

}
