package saaf.common.fmw.intf.model.inter.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.intf.model.entities.readonly.SrmAutoProcessEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IAutoProcessPo;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;

@Component("autoProcessPoServer")
public class AutoProcessPoServer implements IAutoProcessPo {
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoProcessPoServer.class);

	@Autowired
	private BaseViewObject<SrmAutoProcessEntity_HI_RO> srmAutoProcessDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPoHeadersEntity_HI> SrmPoHeadersDAO_HI;   
	@Autowired
    private ViewObject<SrmPoLinesEntity_HI> SrmPoLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPoNoticeEntity_HI> noticeDAO_HI;

    //审批过期
	public String updateAutoPoOverdue() throws Exception {
		StringBuffer sql =new StringBuffer();
		sql.append(	SrmAutoProcessEntity_HI_RO.OVERDUE_QUERY);
		List<SrmAutoProcessEntity_HI_RO> list = srmAutoProcessDAO_HI_RO.findList(sql);
		int i = 0;
		for (  i = 0; i < list.size(); i++) {
			SrmPoHeadersEntity_HI row = SrmPoHeadersDAO_HI.findByProperty("po_header_id", list.get(i).getPoHeaderId())
					.get(0);
			row.setOperatorUserId(row.getLastUpdatedBy());
			SrmPoHeadersDAO_HI.saveOrUpdate(row);
		}

		return "已有"+i+"条数据审批过期";
	};
	// 订单自然关闭关闭
	public String updateAutoClosePo()throws Exception{
		StringBuffer sql =new StringBuffer();
		sql.append(SrmAutoProcessEntity_HI_RO.PO_NATURAL_CLOSED_QUERY);
		List<SrmAutoProcessEntity_HI_RO> list = srmAutoProcessDAO_HI_RO.findList(sql);
		int i = 0;
		for (  i = 0; i < list.size(); i++) {
			SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.findByProperty("po_line_id", list.get(i).getPoLineId())
					.get(0);
			row.setStatus("NATURAL_CLOSED");
			row.setOperatorUserId(row.getLastUpdatedBy());
			SrmPoLinesDAO_HI.saveOrUpdate(row);
		}
		return "已自然关闭"+i+"条订单行";
	}
	//送货通知自然关闭
	public String updateAutoCloseNotice()throws Exception{
		StringBuffer sql =new StringBuffer();
		sql.append(SrmAutoProcessEntity_HI_RO.NOTICE_NATURAL_CLOSED_QUERY);
		List<SrmAutoProcessEntity_HI_RO> list = srmAutoProcessDAO_HI_RO.findList(sql);
		int i = 0;
		for (  i = 0; i < list.size(); i++) {
			SrmPoNoticeEntity_HI row = noticeDAO_HI.findByProperty("po_notice_id", list.get(i).getPoNoticeId())
					.get(0);
			row.setStatus("NATURAL_CLOSED");
			row.setOperatorUserId(row.getLastUpdatedBy());
			noticeDAO_HI.saveOrUpdate(row);
		}
		return "已自然关闭"+i+"条送货通知";
	}
	

}
