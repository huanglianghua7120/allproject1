
package saaf.common.fmw.base.ws.ekp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>kmReviewParamterForm complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="kmReviewParamterForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attachmentForms" type="{http://webservice.review.km.kmss.landray.com/}attachmentForm" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attachmentValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docCreator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdKeyword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdTemplateId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kmReviewParamterForm", propOrder = {
    "attachmentForms",
    "attachmentValues",
    "docContent",
    "docCreator",
    "docProperty",
    "docStatus",
    "docSubject",
    "fdId",
    "fdKeyword",
    "fdSource",
    "fdTemplateId",
    "flowParam",
    "formValues"
})
public class KmReviewParamterForm {

    @XmlElement(nillable = true)
    protected List<AttachmentForm> attachmentForms;
    protected String attachmentValues;
    protected String docContent;
    protected String docCreator;
    protected String docProperty;
    protected String docStatus;
    protected String docSubject;
    protected String fdId;
    protected String fdKeyword;
    protected String fdSource;
    protected String fdTemplateId;
    protected String flowParam;
    protected String formValues;

    /**
     * Gets the value of the attachmentForms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentForms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentForms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttachmentForm }
     * 
     * 
     */
    public List<AttachmentForm> getAttachmentForms() {
        if (attachmentForms == null) {
            attachmentForms = new ArrayList<AttachmentForm>();
        }
        return this.attachmentForms;
    }

    /**
     * 获取attachmentValues属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentValues() {
        return attachmentValues;
    }

    /**
     * 设置attachmentValues属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentValues(String value) {
        this.attachmentValues = value;
    }

    /**
     * 获取docContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocContent() {
        return docContent;
    }

    /**
     * 设置docContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocContent(String value) {
        this.docContent = value;
    }

    /**
     * 获取docCreator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocCreator() {
        return docCreator;
    }

    /**
     * 设置docCreator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocCreator(String value) {
        this.docCreator = value;
    }

    /**
     * 获取docProperty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocProperty() {
        return docProperty;
    }

    /**
     * 设置docProperty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocProperty(String value) {
        this.docProperty = value;
    }

    /**
     * 获取docStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocStatus() {
        return docStatus;
    }

    /**
     * 设置docStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocStatus(String value) {
        this.docStatus = value;
    }

    /**
     * 获取docSubject属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocSubject() {
        return docSubject;
    }

    /**
     * 设置docSubject属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocSubject(String value) {
        this.docSubject = value;
    }

    /**
     * 获取fdId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdId() {
        return fdId;
    }

    /**
     * 设置fdId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdId(String value) {
        this.fdId = value;
    }

    /**
     * 获取fdKeyword属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdKeyword() {
        return fdKeyword;
    }

    /**
     * 设置fdKeyword属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdKeyword(String value) {
        this.fdKeyword = value;
    }

    /**
     * 获取fdSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdSource() {
        return fdSource;
    }

    /**
     * 设置fdSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdSource(String value) {
        this.fdSource = value;
    }

    /**
     * 获取fdTemplateId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdTemplateId() {
        return fdTemplateId;
    }

    /**
     * 设置fdTemplateId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdTemplateId(String value) {
        this.fdTemplateId = value;
    }

    /**
     * 获取flowParam属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowParam() {
        return flowParam;
    }

    /**
     * 设置flowParam属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowParam(String value) {
        this.flowParam = value;
    }

    /**
     * 获取formValues属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormValues() {
        return formValues;
    }

    /**
     * 设置formValues属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormValues(String value) {
        this.formValues = value;
    }

}
