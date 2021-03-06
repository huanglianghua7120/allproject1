package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
import org.ufida.HomaU9CustSRMInterfaceSVISRMInterServiceSV;

import properties.IntfPropertiesUtil;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-21T15:00:47.870+08:00
 * Generated source version: 3.0.15
 * 
 */
@WebServiceClient(name = "SRMInterServiceSVStub", 
                  wsdlLocation = "http://192.168.6.89/U9/services/Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV.svc?wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class SRMInterServiceSVStub extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "SRMInterServiceSVStub");
    public final static QName BasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV = new QName("http://tempuri.org/", "BasicHttpBinding_Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV");
    static {
        URL url = null;
        try {
//            url = new URL("http://192.168.6.89/U9/services/Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV.svc?wsdl");
        	IntfPropertiesUtil properties = new IntfPropertiesUtil();
        	String urlStr = properties.getStringValue("URL");
        	System.out.println(urlStr);
        	url = new URL(urlStr);
            
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SRMInterServiceSVStub.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://192.168.6.89/U9/services/Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV.svc?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SRMInterServiceSVStub(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SRMInterServiceSVStub(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SRMInterServiceSVStub() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SRMInterServiceSVStub(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SRMInterServiceSVStub(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SRMInterServiceSVStub(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns HomaU9CustSRMInterfaceSVISRMInterServiceSV
     */
    @WebEndpoint(name = "BasicHttpBinding_Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV")
    public HomaU9CustSRMInterfaceSVISRMInterServiceSV getBasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV() {
        return super.getPort(BasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV, HomaU9CustSRMInterfaceSVISRMInterServiceSV.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HomaU9CustSRMInterfaceSVISRMInterServiceSV
     */
    @WebEndpoint(name = "BasicHttpBinding_Homa.U9.Cust.SRMInterfaceSV.ISRMInterServiceSV")
    public HomaU9CustSRMInterfaceSVISRMInterServiceSV getBasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV(WebServiceFeature... features) {
        return super.getPort(BasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV, HomaU9CustSRMInterfaceSVISRMInterServiceSV.class, features);
    }

}
