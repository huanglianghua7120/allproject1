
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ErrorLevel的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorLevel"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Debug"/&gt;
 *     &lt;enumeration value="Info"/&gt;
 *     &lt;enumeration value="Warn"/&gt;
 *     &lt;enumeration value="Error"/&gt;
 *     &lt;enumeration value="Fatal"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ErrorLevel")
@XmlEnum
public enum ErrorLevel {

    @XmlEnumValue("Debug")
    DEBUG("Debug"),
    @XmlEnumValue("Info")
    INFO("Info"),
    @XmlEnumValue("Warn")
    WARN("Warn"),
    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Fatal")
    FATAL("Fatal");
    private final String value;

    ErrorLevel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorLevel fromValue(String v) {
        for (ErrorLevel c: ErrorLevel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
