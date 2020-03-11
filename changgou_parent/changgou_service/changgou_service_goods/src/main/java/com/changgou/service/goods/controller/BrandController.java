package com.changgou.service.goods.controller;

import com.changgou.common.pojo.PageResult;
import com.changgou.common.pojo.Result;
import com.changgou.common.pojo.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findList(){
        List<Brand> list = brandService.findList();
        return new Result<>(true, StatusCode.OK,"查询成功",list);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id){
        Brand brand = brandService.findById(id);
        return  new Result<>(true,StatusCode.OK,"查询成功",brand);
    }
    /**
     * 添加 restful风格 post添加
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return  new Result(true,StatusCode.OK,"添加成功");
    }


    /**
     * 删除品牌
     *
     */
    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Integer id){
        brandService.delById(id);
        return  new Result(true,StatusCode.OK,"删除成功");
    }
    /**
     * 品牌修改
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 品牌列表条件查询
     */
    @GetMapping("/search")
    public Result<List<Brand>> search(@RequestParam Map searchMap){
        List<Brand> list = brandService.list(searchMap);
        return new Result<>(true,StatusCode.OK,"查询成功",list);

    }

    /**
     * 分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Brand> pageInfo = brandService.findPage(page, size);
        PageResult pageResult = new PageResult(pageInfo.getTotal(),pageInfo.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
    /**
     * 分页+条件查询
     */
@GetMapping("/searchPage/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable("page") int page,@PathVariable("size") int size){
    Page pageInfo = brandService.findPage(searchMap, page, size);
    PageResult pageResult = new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    return new Result(true,StatusCode.OK,"查询成功",pageResult);
}
}
