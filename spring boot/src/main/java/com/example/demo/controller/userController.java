package com.example.demo.controller;

import com.example.demo.model.user;
import com.example.demo.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class userController {

    @Autowired
    private IUserService userService;

   @RequestMapping("/add")
   @ResponseBody
   public void testAdd(@RequestBody Map map){
       String add_id = map.get("add_id").toString();
       String add_username = map.get("add_username").toString();
       String add_password = map.get("add_password").toString();
       userService.addNewUser(add_id,add_username,add_password);
   }

   @RequestMapping("/search")
   @ResponseBody
   public List<user> testFindUserList(@RequestBody Map map){
       String search_id = map.get("search_id").toString();
       String search_username = map.get("search_username").toString();
       if (search_id == ""){
           search_id = null;
       }
       if (search_username == ""){
           search_username = null;
       }
       List<user> userList = userService.findUsersByCondition(search_id, search_username);
       return  userList;
   }

   @RequestMapping("/edit")
   @ResponseBody
   public user testFindUser(@RequestBody Map map){
       String currentId = map.get("currentId").toString();
       user user = userService.findUserById(currentId);
       return user;
   }

   @RequestMapping("/update")
   @ResponseBody
   public void testUpdate(@RequestBody Map map){
       String id = map.get("upd_id").toString();
       String username = map.get("upd_username").toString();
       String password = map.get("upd_password").toString();
       userService.update(id,username,password);
   }

   @RequestMapping("/delete")
   @ResponseBody
   public void testDelete(@RequestBody Map map){
       String id = map.get("deleteId").toString();
       userService.delete(id);
   }
}
