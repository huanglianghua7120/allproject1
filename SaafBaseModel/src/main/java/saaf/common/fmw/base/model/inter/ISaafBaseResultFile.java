package saaf.common.fmw.base.model.inter;

import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafBaseResultFile.java
 * Description：附件
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafBaseResultFile {

	/**
	 * 附件上传的方法
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	public SaafBaseResultFileEntity_HI saveResult(ResutlFileEntity resutlFileEntity,Integer varUserId)  throws Exception;

	/**
	 * 附件下载方法
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	public String download (HttpServletRequest request, HttpServletResponse response);

	/**
	 * 附件删除方法
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	public String deleteFile(Integer fileId);
	/**
	 * 获取附件
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	public byte[] findFileToByte(Integer fileId);
}



 