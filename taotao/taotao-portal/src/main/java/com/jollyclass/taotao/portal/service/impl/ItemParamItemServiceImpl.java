package com.jollyclass.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbItemParamItem;
import com.jollyclass.taotao.portal.service.ItemParamItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午6:05:17
 * 
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_BASE_PARAM}")
	private String ITEM_BASE_PARAM;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getItemParamItemByItemId(long itemId) {
		try {
			String json = HttpClientUtils.doGet(REST_BASE_URL+ITEM_BASE_PARAM+itemId);
			if (!StringUtils.isBlank(json)) {
				TaoTaoResult taoResult = TaoTaoResult.formatToPojo(json, TbItemParamItem.class);
				if (taoResult.getStatus()==200) {
					TbItemParamItem itemParamItem=(TbItemParamItem) taoResult.getData();
					String paramData = itemParamItem.getParamData();
					if (paramData==null) {
						return null;
					}
					//生成html
					// 把规格参数json数据转换成java对象
					List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
					StringBuffer sb = new StringBuffer();
					sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
					sb.append("    <tbody>\n");
					for(Map m1:jsonList) {
						sb.append("        <tr>\n");
						sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
						sb.append("        </tr>\n");
						List<Map> list2 = (List<Map>) m1.get("params");
						for(Map m2:list2) {
							sb.append("        <tr>\n");
							sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
							sb.append("            <td>"+m2.get("v")+"</td>\n");
							sb.append("        </tr>\n");
						}
					}
					sb.append("    </tbody>\n");
					sb.append("</table>");
					//返回html片段
					return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
