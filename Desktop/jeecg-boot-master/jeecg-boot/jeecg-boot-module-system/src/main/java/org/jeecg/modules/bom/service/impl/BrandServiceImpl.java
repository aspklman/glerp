package org.jeecg.modules.bom.service.impl;

import org.jeecg.modules.bom.entity.Brand;
import org.jeecg.modules.bom.mapper.BrandMapper;
import org.jeecg.modules.bom.service.IBrandService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 品牌表
 * @Author: jeecg-boot
 * @Date:   2019-07-26
 * @Version: V1.0
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
