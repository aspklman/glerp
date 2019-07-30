package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.Odrd;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单明细表
 * @Author: jeecg-boot
 * @Date:   2019-07-29 14:47:47
 * @Version: V1.0
 */
public interface IOdrdService extends IService<Odrd> {

	public List<Odrd> selectByMainId(String mainId);
}
