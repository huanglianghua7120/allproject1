
package org.datacontract.schemas._2004._07.ufsoft_ubf;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageBase.FormatState的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="MessageBase.FormatState"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="Formatting"/&gt;
 *     &lt;enumeration value="Formatted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageBase.FormatState", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Exceptions")
@XmlEnum
public enum MessageBaseFormatState {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Formatting")
    FORMATTING("Formatting"),
    @XmlEnumValue("Formatted")
    FORMATTED("Formatted");
    private final String value;

    MessageBaseFormatState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageBaseFormatState fromValue(String v) {
        for (MessageBaseFormatState c: MessageBaseFormatState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
