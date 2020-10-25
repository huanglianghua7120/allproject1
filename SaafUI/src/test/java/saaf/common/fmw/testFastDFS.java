package saaf.common.fmw;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.commons.fileupload.FileItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yhg.base.utils.SToolUtils;
import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SaafAreasEntity_HI;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.message.utils.SaafToolUtils;

public class testFastDFS {
	public static final ApplicationContext context = new FileSystemXmlApplicationContext("classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
    private static final String configFilePath = "/fdfs_client.properties";
    private static final int BUFFER_SIZE = 2048;

    private static byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
 		try {
 			
 			File file = new File("D:"+File.separator+"logs"+File.separator+"timg.jpg");

			InputStream	stream = new FileInputStream(file);
	
		
		String  name = "timg.jpg";
		String ext = "jpg";
          
 		 FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
 
 		FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity("ss", InputStreamTOByte(stream), ext);
        ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);

        ISaafBaseResultFile saafBaseResultFileServer =
                (ISaafBaseResultFile)SaafToolUtils.context.getBean("saafBaseResultFileServer");
        
        SaafBaseResultFileEntity_HI row = 

        saafBaseResultFileServer.saveResult(resutlFileEntity,-2)  ;
        
        
        System.out.println(SToolUtils.convertResultJSONObj("S", "success",  1, row));
        
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
