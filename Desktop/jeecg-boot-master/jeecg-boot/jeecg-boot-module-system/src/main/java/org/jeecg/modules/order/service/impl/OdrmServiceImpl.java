package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.Odrm;
import org.jeecg.modules.order.entity.Odrd;
import org.jeecg.modules.order.mapper.OdrdMapper;
import org.jeecg.modules.order.mapper.OdrmMapper;
import org.jeecg.modules.order.service.IOdrmService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2019-07-29 14:47:47
 * @Version: V1.0
 */
@Service
public class OdrmServiceImpl extends ServiceImpl<OdrmMapper, Odrm> implements IOdrmService {

	@Autowired
	private OdrmMapper odrmMapper;
	@Autowired
	private OdrdMapper odrdMapper;
	
	@Override
	@Transactional
	public void saveMain(Odrm odrm, List<Odrd> odrdList) {
		odrmMapper.insert(odrm);
		for(Odrd entity:odrdList) {
			//外键设置
			entity.setOrderId(odrm.getId());
			odrdMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Odrm odrm,List<Odrd> odrdList) {
		odrmMapper.updateById(odrm);
		
		//1.先删除子表数据
		odrdMapper.deleteByMainId(odrm.getId());
		
		//2.子表数据重新插入
		for(Odrd entity:odrdList) {
			//外键设置
			entity.setOrderId(odrm.getId());
			odrdMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		odrdMapper.deleteByMainId(id);
		odrmMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			odrdMapper.deleteByMainId(id.toString());
			odrmMapper.deleteById(id);
		}
	}
	
}
