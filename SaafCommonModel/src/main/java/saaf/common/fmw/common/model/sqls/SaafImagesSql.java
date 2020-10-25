package saaf.common.fmw.common.model.sqls;


public class SaafImagesSql {
    public static String QUERY_SQL =
        "  select " + "  si.id id,     " + "  si.storage_address storageAddress,     " + "  si.image_name imageName,     " + "  si.target_id targetId,     " +
        "  si.target_type targetType,     " + "  si.img_type imgType,     " + "  si.img_channel imgChannel,     " + "  si.url url,     " + "  si.width width,     " +
        "  si.height height,     " + "  si.size size,     " + "  si.disabled disabled,     " + "  si.FILE_NAME fileName,     " + "  si.FILE_TYPE fileType,     " +
        "  si.FILE_PATH filePath,     " + "  si.ACCESS_PATH accessPath,     " + "  si.FILE_SIZE fileSize,     " + "  si.UPLOAD_DATE uploadDate,     " +
        "  si.UPLOAD_AUTHOR uploadAuthor,     " + "  si.GROUP_NAME groupName,     " + "  si.REMOTE_FILE_NAME remoteFileName,     " + "  si.FILE_CONTENT fileContent,     " +
        "  date_format(si.CREATION_DATE, '%Y-%m-%d %H:%i:%s') creationDate,     " + "  si.mtime_film_code mtimeFilmCode,     " + "  si.mtime_film_name mtimeFilmName,     " +
        "  si.mtime_film_name_en mtimeFilmNameEn,     " + "  si.mtime_message_type mtimeMessageType     " + "  from  saaf_images si " + "  where 1=1 ";

}

