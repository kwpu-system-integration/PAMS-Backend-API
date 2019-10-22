package com.pams.user.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pams.common.protocol.CommonResponseVO;
import com.pams.user.dto.User;
import com.pams.user.service.UserService;

@RestController
@RequestMapping("/pams")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public @ResponseBody CommonResponseVO signUpUser(@RequestBody User user) {
		
		CommonResponseVO response = userService.signUpUser(user);
	
        return response;
    }
	
	@RequestMapping(value ="/getUserList", method = RequestMethod.GET)
	public @ResponseBody CommonResponseVO getUserList(@ModelAttribute User user) {
		
		CommonResponseVO response = userService.getUserList(user);
		
		return response;
	}
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
	public @ResponseBody CommonResponseVO updateUserInfo(@RequestBody User user) {
		
		CommonResponseVO response = userService.updateUserInfo(user);
		
		return response;
	}
}
