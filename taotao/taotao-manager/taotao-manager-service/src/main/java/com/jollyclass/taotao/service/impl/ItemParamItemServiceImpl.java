package com.jollyclass.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.mapper.TbItemParamItemMapper;
import com.jollyclass.taotao.pojo.TbItemParamItem;
import com.jollyclass.taotao.pojo.TbItemParamItemExample;
import com.jollyclass.taotao.service.ItemParamItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月20日 下午7:03:17
 * 
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getItemParamItemByItemId(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		// 将json转换成list
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map m1 : jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
			sb.append("        </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
				sb.append("            <td>" + m2.get("v") + "</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();

	}

}
