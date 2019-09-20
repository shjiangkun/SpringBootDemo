package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start, size, "id asc");
        List<Category> cs = categoryMapper.findAll();
        PageInfo<Category> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "listCategory";
    }

    @PostMapping("/categories")
    public String listCategory(Category c) throws Exception {
        categoryMapper.save(c);
        return "redirect:/categories";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryMapper.delete(c.getId());
        return "redirect:/categories";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryMapper.update(c);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable(name = "id") int id, Model m) throws Exception {
        Category c = categoryMapper.get(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @PutMapping("/category")
    @ResponseBody
    public void submitCategory(Category category) throws Exception {
        System.out.println("springboot接受到浏览器以JSON格式提交的数据:" + category);
    }

    @GetMapping("/category")
    @ResponseBody
    public List<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start, size, "id asc");
        List<Category> cs = categoryMapper.findAll();
        PageInfo<Category> page = new PageInfo<>(cs);
        return page.getList();
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public Category getCategory(@PathVariable("id") int id) throws Exception {
        Category c = categoryMapper.get(id);
        System.out.println(c);
        return c;
    }

}
