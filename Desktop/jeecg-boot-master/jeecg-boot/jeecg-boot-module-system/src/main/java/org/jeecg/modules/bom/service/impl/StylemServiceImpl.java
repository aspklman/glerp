package org.jeecg.modules.bom.service.impl;

import org.jeecg.modules.bom.entity.Stylem;
import org.jeecg.modules.bom.entity.Styles;
import org.jeecg.modules.bom.mapper.StylesMapper;
import org.jeecg.modules.bom.mapper.StylemMapper;
import org.jeecg.modules.bom.service.IStylemService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Service
public class StylemServiceImpl extends ServiceImpl<StylemMapper, Stylem> implements IStylemService {

	@Autowired
	private StylemMapper stylemMapper;
	@Autowired
	private StylesMapper stylesMapper;
	
	@Override
	@Transactional
	public void saveMain(Stylem stylem, List<Styles> stylesList) {
		stylemMapper.insert(stylem);
		for(Styles entity:stylesList) {
			//外键设置
			entity.setStyleId(stylem.getId());
			stylesMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Stylem stylem,List<Styles> stylesList) {
		stylemMapper.updateById(stylem);
		
		//1.先删除子表数据
		stylesMapper.deleteByMainId(stylem.getId());
		
		//2.子表数据重新插入
		for(Styles entity:stylesList) {
			//外键设置
			entity.setStyleId(stylem.getId());
			stylesMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		stylesMapper.deleteByMainId(id);
		stylemMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			stylesMapper.deleteByMainId(id.toString());
			stylemMapper.deleteById(id);
		}
	}
	
}
