
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.system.Exception;
import org.datacontract.schemas._2004._07.ufsoft_ubf.BusinessException;
import org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException;
import org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceLostException;


/**
 * <p>ExceptionBase complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ExceptionBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/System}Exception"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionBase")
@XmlSeeAlso({
    BusinessException.class,
    ServiceLostException.class,
    ServiceException.class,
    UnknownException.class
})
public class ExceptionBase
    extends Exception
{


}
