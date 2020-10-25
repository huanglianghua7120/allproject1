package saaf.common.fmw.genform.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.model.dao.SaafDynamicFormInfoDAO_HI;
import saaf.common.fmw.genform.model.entities.SaafDynamicFormInfoEntity_HI;
import saaf.common.fmw.genform.model.inter.IGenDynamicForm;
import saaf.common.fmw.genform.model.inter.ISaafDynamicTableInfo;
import saaf.common.fmw.genform.utils.FastDFSUploadUtil;
import saaf.common.fmw.genform.utils.GenFormPropertiesUtil;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("genDynamicFormServices")
@Path("/genDynamicFormServices")
public class GenDynamicFormServices extends CommonAbstractServices {
    private static final Logger log = LoggerFactory.getLogger(GenDynamicFormServices.class);

    //    public static final String STATUS = "status";
    //    public static final String MSG = "msg";
    //    public static final String COUNT = "count";

    public GenDynamicFormServices() {
        super();
    }

    @Autowired
    private IGenDynamicForm genDynamicFormServer;

    @Autowired
    private SaafDynamicFormInfoDAO_HI saafDynamicFormInfoDAO_HI;

    @Autowired
    private ISaafDynamicTableInfo saafDynamicTableInfoServer;

    /**
     * 根据Id查询单条数据
     * @param tableName 表名
     * @param id Id
     * @return
     */
    @POST
    @Path("getDynamicFormData")
    @Produces("application/json")
    public String getDynamicFormData(@FormParam("tableName")
        String tableName, @FormParam("id")
        Integer id) {
        try {
            log.info("tableName:" + tableName + "\tid:" + id);

            //            IGenDynamicForm genDynamicFormServer =
            //                (IGenDynamicForm)SaafToolUtils.context.getBean("genDynamicFormServer");
            JSONObject result = genDynamicFormServer.findById(tableName, id);
            return result.toJSONString();
        } catch (Exception e) {
            log.error("", e);
            JSONObject json = new JSONObject();
            json.put(STATUS, "E");
            json.put(MSG, "操作失败！错误信息为\n" +
                    e.getMessage());
            return json.toString();
        }
    }

    /**
     * 保存数据
     * @param tableName 表名
     * @param params 保存的数据对象，JSON格式
     * @return
     */
    @POST
    @Path("save")
    @Produces("application/json")
    public String save(@FormParam("tableName")
        String tableName, @FormParam("params")
        String params) {
        try {
            log.info("tableName:" + tableName + "\tparams:" + params);

            JSONObject entity = JSONObject.parseObject(params);
            //            IGenDynamicForm genDynamicFormServer =
            //                (IGenDynamicForm)SaafToolUtils.context.getBean("genDynamicFormServer");
            Integer id = genDynamicFormServer.saveOrUpdateExecuteSQL(tableName, entity);

            JSONObject json = new JSONObject();
            json.put(STATUS, 0);
            json.put("id", id);
            json.put(MSG, "保存成功");
            return json.toJSONString();
        } catch (Exception e) {
            log.error("", e);
            JSONObject json = new JSONObject();
            json.put(STATUS, "E");
            json.put(MSG, "操作失败！错误信息为\n" +
                    e.getMessage());
            return json.toString();
        }
    }

    /**
     * 查询分页数据列表
     * @param tableName 表名
     * @param params 查询参数
     * @param pageIndex 第几页
     * @param pageSize 每页显示记录数
     * @return
     */
    @POST
    @Path("findPagination")
    @Produces("application/json")
    public String findPagination(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageSize")
        Integer pageSize) {
        try {
            log.info("params:" + params);
            JSONObject paramsJson = JSONObject.parseObject(params);
            String tableName = paramsJson.getString("tableName");
            if (pageSize == null) {
                pageSize = 10;
            }

            StringBuffer querySql = new StringBuffer();
            querySql.append("select * from " + tableName + " where 1=1 ");

            Map<String, Object> parameterMap = SToolUtils.fastJsonObj2Map(paramsJson);
            parameterMap.remove("tableName");
    		Iterator iterator = null;
    	    if (null != parameterMap){
    	      for (iterator = parameterMap.keySet().iterator(); iterator.hasNext(); ) {
    	        String key = (String)iterator.next();
    	        querySql.append(" and "+key+"=:"+key);
    	      }
    	    }
    	    
			String countQueryString = "select count(auto_id) from " + tableName;
			Pagination<JSONObject> findPagination = genDynamicFormServer
					.findPagination(querySql.toString(), countQueryString,
							parameterMap, pageIndex, pageSize);

            return JSONObject.toJSONString(findPagination);
        } catch (Exception e) {
            log.error("", e);
            JSONObject json = new JSONObject();
            json.put(STATUS, "E");
            json.put(MSG, "操作失败！错误信息为\n" +
                    e.getMessage());
            return json.toString();
        }
    }

    /**
     * 生成HTML文件
     * @param resultHtml HTML字符串
     * @param tableName 表名
     * @param fields 字段数据，JSON格式
     * @return
     */
    @POST
    @Path("genFormHtml")
    @Produces("application/json")
    public String genFormHtml(@FormParam("params")
        String params) {

        try {
            log.info("params:" + params);

            JSONObject queryParams = JSONObject.parseObject(params);
            String id = queryParams.getString("id");
            String resultHtml = queryParams.getString("resultHtml");
            String modelsAttributes = queryParams.getString("modelsAttributes");
            String tableName = queryParams.getString("tableName");
            String formDescription = queryParams.getString("formDescription");
            String fields = queryParams.getString("fields");


            String msg = "表单创建成功";
            SaafDynamicFormInfoEntity_HI dynamicFormInfo = new SaafDynamicFormInfoEntity_HI();
            boolean isupdate = false;
            if (StringUtils.isNotEmpty(id)) {
                //如果有id传入，查询数据
                msg = "表单更新成功";
                dynamicFormInfo = saafDynamicFormInfoDAO_HI.getById(Integer.parseInt(id));
                if (dynamicFormInfo != null) {
                    //有数据，更新
                    isupdate = true;
                }
            }

            //将表名转成拼音，并将空格替换成下进线
            String pinYinTableName = this.getPinYinString(tableName).replaceAll(" ", "_");

            JSONObject tableRetjson = null;
            if (dynamicFormInfo != null && ("auto_" + pinYinTableName).equalsIgnoreCase(dynamicFormInfo.getTableName())) {
                //更新表结构
                tableRetjson = updateTable(pinYinTableName, tableName, fields);
            } else {
                //表不存在，创建表
                tableRetjson = this.createTable(pinYinTableName, tableName, fields); //创建表
            }

            if (tableRetjson.getString(STATUS).equals("E")) {
                //创建表失败，返回结果提示，不往下执行
                return tableRetjson.toJSONString();
            }


            JSONObject json = new JSONObject();
            //将html参数中的中文字段替换成英文字段
            resultHtml = this.replaceFields2En(resultHtml, fields);

            //this.writeHtmlStrToFile(tableName,pinYinTableName,resultHtml);//输出HTML至文件
            JSONObject uploadResult = this.writeHtmlStrToFastDFS(tableName, pinYinTableName, resultHtml); //上传至文件服务器
            if (uploadResult != null && uploadResult.containsKey("staus") && uploadResult.getString("staus").equalsIgnoreCase("S")) {
                //上传成功后处理
                JSONObject ret = uploadResult.getJSONArray("data").getJSONObject(0);

                dynamicFormInfo.setTableName("auto_" + pinYinTableName);
                dynamicFormInfo.setName(tableName);
                dynamicFormInfo.setDescription(formDescription);
                dynamicFormInfo.setModelsAttributes(modelsAttributes);
                this.processUploadSuccess(dynamicFormInfo, ret, isupdate);
            } else {
                System.out.println(uploadResult);
                throw new Exception(uploadResult.getString("msg"));
            }

            json.put(STATUS, "0");
            json.put(MSG, msg);
            return json.toJSONString();

        } catch (Exception e) {
            log.error("", e);
            JSONObject json = new JSONObject();
            json.put(STATUS, "E");
            json.put(MSG, "操作失败！错误信息为\n" +
                    e.getMessage());
            return json.toJSONString();
        }
    }

    /**
     * 处理文件上传成功后信息
     * @param dynamicFormInfo 表信息
     * @param ret 上传结果
     * @param isupdate 是否更新或新增
     */
    private void processUploadSuccess(SaafDynamicFormInfoEntity_HI dynamicFormInfo, JSONObject ret, boolean isupdate) {
        String url = ret.getString("accessPath");
        String filename = ret.getString("fileName");
        String groupName = ret.getString("groupName");
        String remoteFileName = ret.getString("remoteFileName");


        dynamicFormInfo.setFilename(filename);

        dynamicFormInfo.setGroupname(groupName);
        dynamicFormInfo.setRemotefilename(remoteFileName);
        dynamicFormInfo.setUrl(url);
        dynamicFormInfo.setOperatorUserId(getUserSessionBean().getUserId());
        if (isupdate) {
            saafDynamicFormInfoDAO_HI.update(dynamicFormInfo);
        } else {
            saafDynamicFormInfoDAO_HI.save(dynamicFormInfo);
        }
    }

    /**
     * 将指定字符串转换为拼音
     * @param msg 指定字符串
     * @return 拼音
     */
    private String getPinYinString(String msg) throws BadHanyuPinyinOutputFormatCombination {
        //拼音格式化
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuffer sb = new StringBuffer(); //转换后的拼音字符串
        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
            if (pinyinArr == null || pinyinArr.length == 0) {
                sb.append(String.valueOf(chars[i])); //不是汉字，原样输出
            } else {
                sb.append(pinyinArr[0]); //汉字，返回第一个拼音（多音字的情况）
            }
        }
        return sb.toString();
    }
    
    private JSONObject updateTable(String tableName, String tableComment, String fields) {
        JSONObject retjson = new JSONObject();
        try {

            JSONArray columnNames = saafDynamicTableInfoServer.findColumnNames("auto_" + tableName);

            StringBuffer alterSql = new StringBuffer();
            alterSql.append("ALTER TABLE `auto_" + tableName + "` ");
            JSONArray fieldsArray = JSONObject.parseArray(fields);
            Iterator it = fieldsArray.iterator();
            JSONObject fieldObj = null;
            while (it.hasNext()) {
                fieldObj = (JSONObject)it.next();
                String fieldName = fieldObj.getString("fieldName");
                String fieldType = fieldObj.getString("fieldType");
                int fieldLength = fieldObj.getIntValue("fieldLength");
                String fieldLabel = fieldObj.getString("fieldLabel");

                String columnName = this.getPinYinString(fieldName);
                if (columnNames.contains(this.getPinYinString(fieldName))) {
                    alterSql.append(" CHANGE COLUMN `" + columnName + "` `" + columnName + "`");
                } else {
                    alterSql.append(" ADD COLUMN `" + columnName + "`");
                }
                columnNames.remove(columnName);

                alterSql.append(fieldType + "(" + fieldLength + ") COMMENT '" + fieldLabel + "' ");

                if (fieldObj.containsKey("defaultValue") && (fieldObj.getString("defaultValue") != null || fieldObj.getString("defaultValue").length() > 0)) {
                    //设置默认值
                    alterSql.append(" DEFAULT " + fieldObj.getString("defaultValue"));
                } else {
                    alterSql.append(" DEFAULT NULL");
                }
                alterSql.append(",");
            }
			if(GenFormPropertiesUtil.getBoolean("genform.update.database.deleteOldField")){
				columnNames.remove("auto_id");//主键不能删除
				if(!columnNames.isEmpty()){
					//剩余字段将删除
					Iterator it2 = columnNames.iterator();
					while(it2.hasNext()){
						String fieldName = (String)it2.next();
					    alterSql.append(" DROP COLUMN `"+fieldName+"`,");
					}
				}
			}
			alterSql.append(" COMMENT='"+tableComment+"'");
			log.info("alter sql:" + alterSql.toString());
			
			int successFlag = genDynamicFormServer.createTableEntity("auto_" + tableName, alterSql.toString());
			return optionTableResult(successFlag);
        } catch (Exception e) {
            ////e.printStackTrace();
            log.error("", e);
            retjson.put(STATUS, "E");
            retjson.put(MSG, e.getMessage());
        }
        return retjson;
    }

    /**
     * 创建表
     * @param tableName 表名
     * @param fields 字段（JSONArray格式）
     */
    private JSONObject createTable(String tableName, String tableComment, String fields) {

        JSONObject retjson = new JSONObject();
        try {
            StringBuffer createSql = new StringBuffer();
            createSql.append("CREATE TABLE `auto_" + tableName + "` (`auto_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键' ");

            JSONArray fieldsArray = JSONObject.parseArray(fields);
            Iterator it = fieldsArray.iterator();
            JSONObject fieldObj = null;
            while (it.hasNext()) {
                fieldObj = (JSONObject)it.next();
                String fieldName = fieldObj.getString("fieldName");
                String fieldType = fieldObj.getString("fieldType");
                int fieldLength = fieldObj.getIntValue("fieldLength");
                String fieldLabel = fieldObj.getString("fieldLabel");

                createSql.append(",`" + this.getPinYinString(fieldName) + "` ");
                createSql.append(fieldType + "(" + fieldLength + ") COMMENT '" + fieldLabel + "'");

                if (fieldObj.containsKey("defaultValue") && (fieldObj.getString("defaultValue") != null || fieldObj.getString("defaultValue").length() > 0)) {
                    //设置默认值
                    createSql.append("DEFAULT " + fieldObj.getString("defaultValue"));
                } else {
                    createSql.append("DEFAULT NULL");
                }
            }
            createSql.append(", PRIMARY KEY(`auto_id`))");
            createSql.append(" COMMENT='" + tableComment + "' ");
            createSql.append(" ENGINE=INNODB DEFAULT CHARSET=utf8;");
            log.info("create sql:" + createSql.toString());

            int successFlag = genDynamicFormServer.createTableEntity("auto_" + tableName, createSql.toString());

            return optionTableResult(successFlag);
        } catch (Exception e) {
            log.error("", e);
            retjson.put(STATUS, "E");
            retjson.put(MSG, e.getMessage());
        }
        return retjson;
    }

    private JSONObject optionTableResult(int successFlag) {
        JSONObject retjson = new JSONObject();
        if (successFlag == 1) {
            retjson.put(STATUS, "E");
            retjson.put(MSG, "表单名称对应的数据表已存在，请修改表单名称");
        } else if (successFlag == -1) {
            retjson.put(STATUS, "E");
            retjson.put(MSG, "数据表创建失败，请检查相关字段名称是否符合规范");
        } else {
            retjson.put(STATUS, "S");
            retjson.put(MSG, "创建表成功");
        }
        return retjson;
    }

    /**
     * 将html文件写至文件服务器
     * @param tableName 表名（中文）
     * @param pinYinTableName 表名（拼音）
     * @param resultHtml 创建的表单html
     * @return
     */
    private JSONObject writeHtmlStrToFastDFS(String tableName, String pinYinTableName, String resultHtml) {
        ByteArrayInputStream bais = null;
        try {
            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("tableName", tableName);
            replaceMap.put("pinYinTableName", pinYinTableName);
            //转换为完整的html
            String html = this.replaceContent(resultHtml, replaceMap);

            String filname = "view_" + System.currentTimeMillis() + ".html";
            bais = new ByteArrayInputStream(html.getBytes("utf-8"));

            FastDFSUploadUtil util = new FastDFSUploadUtil();
            return util.uploadFile(filname, bais);

        } catch (IOException e) {
            log.error("", e);
        } finally {
            try {
                if (bais != null)
                    bais.close();
            } catch (IOException ioe) {
                log.error(ioe.getMessage());
            }
        }
        return null;
    }

    /**
     * 将HTML输出到文件
     * @param tableName 表名
     * @param resultHtml HTML文件内容
     * @throws IOException
     */
    private void writeHtmlStrToFile(String tableName, String pinYinTableName, String resultHtml) throws IOException {
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        try {
            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("tableName", tableName);
            replaceMap.put("pinYinTableName", pinYinTableName);
            String html = replaceContent(resultHtml, replaceMap);
            String realPath = request.getSession().getServletContext().getRealPath("/");
            //将resultHtml写至文件
            String path = realPath + "/genform/gen/" + pinYinTableName; //父目录
            if (!new File(path).exists()) {
                new File(path).mkdirs(); //父目录不存在，创建父目录
            }
            String filepath = path + "/view_" + System.currentTimeMillis() + ".html";
            log.info(filepath);
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile(); //文件不存在创建文件
            }
            log.info(file.getAbsolutePath());
            bais = new ByteArrayInputStream(html.getBytes("utf-8"));

            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = bais.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bais != null)
                    bais.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ioe) {
                log.error(ioe.getMessage());
            }
        }
    }

    /**
     * 替换英文字段
     * @param resultHtml html参数
     * @param fields 数据字段
     * @return 替换后的html
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private String replaceFields2En(String resultHtml, String fields) throws BadHanyuPinyinOutputFormatCombination {
        JSONArray fieldArray = JSONArray.parseArray(fields);
        Pattern pattern = null;
        for (int i = 0, len = fieldArray.size(); i < len; i++) {
            JSONObject field = fieldArray.getJSONObject(i);
            String fieldName = field.getString("fieldName");
            pattern = Pattern.compile("\\{\\%data." + fieldName + "\\%\\}");
            Matcher matcher = pattern.matcher(resultHtml);
            if (matcher.find()) {
                resultHtml = matcher.replaceAll("data." + getPinYinString(fieldName));
            }
        }
        return resultHtml;
    }

    /**
     * 替换文本
     * @param resultHtml html参数
     * @param replaceMap 需要坑爹的的map
     * @return 坑爹的后的参数
     */
    private String replaceContent(String resultHtml, Map<String, String> replaceMap) {
        ByteArrayOutputStream baos = null;
        FileInputStream fis = null;
        try {
            //读取表单模板内容
            String realPath = request.getSession().getServletContext().getRealPath("/");
            //将resultHtml写至文件
            String defaultTmplPath = "/genform/templates/gentemplate/view.template.html";
            String path = realPath + GenFormPropertiesUtil.getProperty("genform.view.template", defaultTmplPath);
            if (!new File(path).exists()) {
                //模板不存在
                throw new FileNotFoundException("模板文件不存在，path=" + path);
            }

            baos = new ByteArrayOutputStream();
            fis = new FileInputStream(new File(path));
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            String templateHtml = new String(baos.toByteArray(), "utf-8");
            //替换表单中的用{% %}定义的字段
            //替换表单内容
            Pattern pattern = Pattern.compile("\\{\\%formContent\\%\\}");
            Matcher matcher = pattern.matcher(templateHtml);
            if (matcher.find()) {
                resultHtml = matcher.replaceAll(Matcher.quoteReplacement(resultHtml));
            }
            //替换标题
            pattern = Pattern.compile("\\{\\%title\\%\\}");
            matcher = pattern.matcher(resultHtml);
            if (matcher.find() && replaceMap.containsKey("tableName")) {
                resultHtml = matcher.replaceAll(replaceMap.get("tableName"));
            }
            //替换表名
            pattern = Pattern.compile("\\{\\%tableName\\%\\}");
            matcher = pattern.matcher(resultHtml);
            if (matcher.find() && replaceMap.containsKey("pinYinTableName")) {
                resultHtml = matcher.replaceAll("auto_" + replaceMap.get("pinYinTableName"));
            }
            //替换获取数据及提交数据的URL-contentPath
            pattern = Pattern.compile("\\{\\%serverPath\\%\\}");
            matcher = pattern.matcher(resultHtml);
            if (matcher.find()) {
                String defaultPath = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
                String contextPath = GenFormPropertiesUtil.getProperty("server.context.path", defaultPath);
                resultHtml = matcher.replaceAll(contextPath);
            }
            return resultHtml;
        } catch (IOException e) {
            log.error("", e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return resultHtml;
    }
}
