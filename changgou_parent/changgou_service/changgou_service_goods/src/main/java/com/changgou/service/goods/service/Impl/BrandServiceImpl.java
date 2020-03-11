package com.changgou.service.goods.service.Impl;

import com.changgou.common.utils.PinYinUtils;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.dao.BrandMapper;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findList() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }



/**
 * 品牌的新增
 */
@Override
@Transactional
public void add(Brand brand) {
    if(StringUtils.isEmpty(brand.getLetter())){
        String letter = PinYinUtils.getFirstLetter(brand.getName());
        brand.setLetter(letter);
    }
    brandMapper.insertSelective(brand);
}

    /**
     * 品牌的删除
     * @param id
     */
    @Override
    @Transactional
    public void delById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 品牌修改
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 品牌列表条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> list(Map<String, Object> searchMap) {
        //设置查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            //设置品牌名称模糊查询
            if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //设置品牌首字母的精确查询
            if (searchMap.get("letter")!=null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        return brands;
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> page1 = (Page<Brand>) brandMapper.selectAll();
        return page1;
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页
        PageHelper.startPage(page,size);

        //设置查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            //设置品牌名称模糊查询
            if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //设置品牌首字母的精确查询
            if (searchMap.get("letter")!=null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        return pageInfo;

    }
}
