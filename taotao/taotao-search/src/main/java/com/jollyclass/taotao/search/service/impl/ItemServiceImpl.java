package com.jollyclass.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.search.mapper.ItemResultMapper;
import com.jollyclass.taotao.search.pojo.ItemResult;
import com.jollyclass.taotao.search.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午2:32:21
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemResultMapper itemResultMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public TaoTaoResult importAllIndex() {
		try {
			List<ItemResult> itemList = itemResultMapper.getItemList();
			if (itemList != null && itemList.size() > 0) {
				for (ItemResult item : itemList) {
					SolrInputDocument document = new SolrInputDocument();
					document.addField("id", item.getId());
					document.addField("item_title", item.getTitle());
					document.addField("item_sell_point", item.getSell_point());
					document.addField("item_price", item.getPrice());
					document.addField("item_image", item.getImage());
					document.addField("item_category_name", item.getCategory_name());
					document.addField("item_desc", item.getItem_desc());
					solrServer.add(document);
				}
				solrServer.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaoTaoResult.ok();
	}

}
