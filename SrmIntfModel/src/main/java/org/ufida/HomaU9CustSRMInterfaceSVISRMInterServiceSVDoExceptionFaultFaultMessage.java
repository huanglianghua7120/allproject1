
package org.ufida;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-21T15:00:47.830+08:00
 * Generated source version: 3.0.15
 */

@WebFault(name = "Exception", targetNamespace = "http://schemas.datacontract.org/2004/07/System")
public class HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage extends java.lang.Exception {
    
    private org.datacontract.schemas._2004._07.system.Exception exception;

    public HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage() {
        super();
    }
    
    public HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage(String message) {
        super(message);
    }
    
    public HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public HomaU9CustSRMInterfaceSVISRMInterServiceSVDoExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public org.datacontract.schemas._2004._07.system.Exception getFaultInfo() {
        return this.exception;
    }
}
