package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /**
     * 品牌列表查询
     */
    List<Brand> findList();
public Brand findById(Integer id);
    void add(Brand brand);
    void delById(Integer id);
    void update(Brand brand);
    List<Brand> list(Map<String,Object> searchMap);
    /**
     * 品牌列表分页查询
     * page:当前的页码
     * size:每页显示多少条
     */
    Page<Brand> findPage(int page, int size);


   Page<Brand> findPage(Map<String,Object> searchMap,int page,int size);
}
