
package com.microsoft.schemas._2003._10.serialization.arrays;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.schemas._2003._10.serialization.arrays package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ArrayOfKeyValueOfanyTypeanyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfanyTypeanyType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.schemas._2003._10.serialization.arrays
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfKeyValueOfanyTypeanyType }
     * 
     */
    public ArrayOfKeyValueOfanyTypeanyType createArrayOfKeyValueOfanyTypeanyType() {
        return new ArrayOfKeyValueOfanyTypeanyType();
    }

    /**
     * Create an instance of {@link ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType }
     * 
     */
    public ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType createArrayOfKeyValueOfanyTypeanyTypeKeyValueOfanyTypeanyType() {
        return new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfanyTypeanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfKeyValueOfanyTypeanyType")
    public JAXBElement<ArrayOfKeyValueOfanyTypeanyType> createArrayOfKeyValueOfanyTypeanyType(ArrayOfKeyValueOfanyTypeanyType value) {
        return new JAXBElement<ArrayOfKeyValueOfanyTypeanyType>(_ArrayOfKeyValueOfanyTypeanyType_QNAME, ArrayOfKeyValueOfanyTypeanyType.class, null, value);
    }

}
