
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ExceptionBase.FormatState的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ExceptionBase.FormatState"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="Formatting"/&gt;
 *     &lt;enumeration value="Formatted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExceptionBase.FormatState")
@XmlEnum
public enum ExceptionBaseFormatState {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Formatting")
    FORMATTING("Formatting"),
    @XmlEnumValue("Formatted")
    FORMATTED("Formatted");
    private final String value;

    ExceptionBaseFormatState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExceptionBaseFormatState fromValue(String v) {
        for (ExceptionBaseFormatState c: ExceptionBaseFormatState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
