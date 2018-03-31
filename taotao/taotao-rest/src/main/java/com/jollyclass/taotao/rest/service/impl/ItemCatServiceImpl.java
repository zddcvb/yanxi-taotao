package com.jollyclass.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.mapper.TbItemCatMapper;
import com.jollyclass.taotao.pojo.TbItemCat;
import com.jollyclass.taotao.pojo.TbItemCatExample;
import com.jollyclass.taotao.rest.pojo.CatNod;
import com.jollyclass.taotao.rest.pojo.CatResult;
import com.jollyclass.taotao.rest.service.ItemCatService;

/**
 * @author 邹丹丹
 * @date 2017年7月21日 下午4:39:47
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	public CatResult getItemCatList(){
		CatResult catResult=new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}
	/**
	 * 递归根据规则进行生成数据
	 * @param parentId 父级id，0代表为父级
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<?> getCatList(long parentId) {
		List data=new ArrayList<>();
		TbItemCatExample example=new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		int count=0;
		for (TbItemCat tbItemCat : list) {
			
			if (tbItemCat.getIsParent()) {
				CatNod catNod=new CatNod();
				//如果是父节点
				if (parentId==0) {
					//为0代表为父级目录,需要添加链接
					catNod.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else{
					catNod.setName(tbItemCat.getName());
				}
				catNod.setUrl("/products/"+tbItemCat.getId()+".html");
				catNod.setItems(getCatList(tbItemCat.getId()));
				data.add(catNod);
				count++;
				if (count==14) {
					break;
				}
			}else{
				//如果是子节点
				data.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return data;
	}
}
