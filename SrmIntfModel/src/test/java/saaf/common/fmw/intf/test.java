package saaf.common.fmw.intf;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.datacontract.schemas._2004._07.ufsoft_ubf_util.ThreadContext;
import org.tempuri.SRMInterServiceSVStub;
import org.ufida.HomaU9CustSRMInterfaceSVISRMInterServiceSV;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfanyTypeanyType;

import com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory;

import exceptions.ubf.ufsoft.ArrayOfMessageBase;



public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SRMInterServiceSVStub  create  =  new SRMInterServiceSVStub();
		HomaU9CustSRMInterfaceSVISRMInterServiceSV createsv =create.getBasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV();
//		
		ThreadContext context = createContext();
//		
//		
//		 
//
		javax.xml.ws.Holder<String> doResult = new javax.xml.ws.Holder<String>();
//
		javax.xml.ws.Holder< ArrayOfMessageBase> outMessages = new javax.xml.ws.Holder< ArrayOfMessageBase>();
	 
		try {
			
//			TestBeAn ss = new TestBeAn();
//			ss.setCccYyyy  ("test1");
//			ss.setLllXxxx("s");
//			ss.setXllXxxx("setXllXxxx");
//
// 			String xMLStr = toXml(ss, "12",
//					"1");
// 			
//
//			System.out.println("xMLStr:"+xMLStr);
			
			String   jsonStr  ="{\"Code\":\"010001\",\"org\":\"Homa\",\"ID\":\"1001209190000930\"}";
			
			String   transCode ="D0004";
//			U9Util.toErpInfoIni(getDataCentre(), custInterface, xMLStr, "waiting");
//			System.out.println(jsonStr);
			createsv._do(context, jsonStr, transCode, doResult, outMessages);
			System.out.println(doResult.value);
			System.out.println(outMessages.value);
			
//			U9Util.toErpInfo(getDataCentre(), custInterface, xMLStr, doResult.value);
//			CmsInfo cms = u9Util.converyToJavaBean(doResult.value, CmsInfo.class);
//			return cms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String toXml(Object obj,String transCode,String operateType) {
		String str= "<?xml version=\"1.0\" encoding = \"UTF-8\"?>"
		+"<CMS>"+
		getPub(obj, transCode,operateType)
		+"</CMS>";
		 return str;
	}
	
	public static String getPub(Object obj,String transCode,String operateType) {
		String str="<pub>"+
		"<TransCode>"+transCode+"</TransCode>"+
		"<OperateType>"+operateType+"</OperateType>"+
		"</pub>"+convertToXml(obj);
		 return str;		
	}
	
	/** 
	 * JavaBean转换成xml 
	 * 默认编码UTF-8 
	 * @param obj 
	 * @param writer 
	 * @return  
	 */  
	public static String convertToXml(Object obj) {  
	    return convertToXml(obj, "UTF-8");  
	}
	
	/** 
	 * JavaBean转换成xml 
	 * @param obj 
	 * @param encoding  
	 * @return  
	 */  
	public static String convertToXml(Object obj, String encoding) {  
	    String result = null;  
	    try {  
	        JAXBContext context = JAXBContext.newInstance(obj.getClass());  
	        Marshaller marshaller = context.createMarshaller();  
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
	          

	        StringWriter writer = new StringWriter();  
	        marshaller.marshal(obj, writer);  
	        result = writer.toString();  
	        result= result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
//	        result=result.replace("<"+toLowerCaseFirstOne(obj.getClass().getSimpleName())+">", "<init>");
//	        result=result.replace("</"+toLowerCaseFirstOne(obj.getClass().getSimpleName())+">", "</init>");
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  

	    return result;  
	}
	/** 
	 * xml转换成JavaBean 
	 * @param xml 
	 * @param c 
	 * @return 
	 */  
	@SuppressWarnings("unchecked")  
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		if(xml==null||xml.length()==0){
			return null;
		}
	    T t = null;  
	    try {  
	        JAXBContext context = JAXBContext.newInstance(c);  
	        Unmarshaller unmarshaller = context.createUnmarshaller();  
	        t = (T) unmarshaller.unmarshal(new StringReader(xml));  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  

	    return t;  
	}
	
	private static ThreadContext createContext() { 

		
		String CultureName = "zh-CN";//语言
		
		Long UserID = Long.parseLong("1001208100001578");
        Long OrgID = Long.parseLong("1001208100000014"); 
	    String EnterpriseID = "01";//  
	    String UserCode = "admin";// 当前用户编码
	
		//Long OrgID = 1001208100000014L;//当前组织ID
		  //Long UserID = 1001208100001578L;//当前用户ID
//		,
		  
		  //String EnterpriseID = "001";//
		  //String UserCode = "admin";//当前用户编码
		  String DefaultCultureName = "zh-CN";
		
		  org.datacontract.schemas._2004._07.ufsoft_ubf_util.ObjectFactory contextfactory = new  org.datacontract.schemas._2004._07.ufsoft_ubf_util.ObjectFactory();
        
		ThreadContext thContext = contextfactory.createThreadContext();
        
		 ObjectFactory arrayfactory =
        		new   ObjectFactory();
        
        ArrayOfKeyValueOfanyTypeanyType ns = arrayfactory.createArrayOfKeyValueOfanyTypeanyType();
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k1 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
        k1.setKey("OrgID");
        k1.setValue(OrgID);
        ns.getKeyValueOfanyTypeanyType().add(k1);
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k2 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
  
         k2.setKey("UserID");
        k2.setValue(UserID);
        ns.getKeyValueOfanyTypeanyType().add(k2);
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k3 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();

         k3.setKey("CultureName");
        k3.setValue(CultureName);
        ns.getKeyValueOfanyTypeanyType().add(k3);


        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k4 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
        k4.setKey("EnterpriseID");
        k4.setValue(EnterpriseID);
        ns.getKeyValueOfanyTypeanyType().add(k4);
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k5 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
        k5.setKey("DefaultCultureName");
        k5.setValue(DefaultCultureName);
        ns.getKeyValueOfanyTypeanyType().add(k5);
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k6 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
        k6.setKey("UserCode");
        k6.setValue(UserCode);
        ns.getKeyValueOfanyTypeanyType().add(k6);
        
        ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k7 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
        k7.setKey("UserName");
        k7.setValue("SRM");
        ns.getKeyValueOfanyTypeanyType().add(k7);
         
        
        thContext.setNameValueHas(ns);
        
        return thContext;
	
	}

	
	
}
