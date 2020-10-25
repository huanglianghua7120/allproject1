package saaf.common.fmw.intf;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlType  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement 
public class TestBeAn {
@XmlAttribute 
public String cccYyyy;
public String lllXxxx;
public String XllXxxx;
public String getXllXxxx() {
	return XllXxxx;
}
public void setXllXxxx(String xllXxxx) {
	XllXxxx = xllXxxx;
}
public String getCccYyyy() {
	return cccYyyy;
}
public void setCccYyyy(String cccYyyy) {
	this.cccYyyy = cccYyyy;
}
public String getLllXxxx() {
	return lllXxxx;
}
public void setLllXxxx(String lllXxxx) {
	this.lllXxxx = lllXxxx;
}
 
	
}
