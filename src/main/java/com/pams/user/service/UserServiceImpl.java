package com.pams.user.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pams.common.protocol.CommonResponseVO;
import com.pams.common.protocol.CommonResultCode;
import com.pams.common.util.CommonUtils;
import com.pams.user.dto.User;
import com.pams.user.predicate.UserPredicate;
import com.pams.user.repo.UserRepository;

@JsonSerialize
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public CommonResponseVO signUpUser(User user) {
		
		//need validation check
		User signUpUser = userRepo.save(user);
		
		CommonResponseVO response = new CommonResponseVO();
		response.setResponseCode(CommonResultCode.SUCCESS_NORMAL.getCode());
		response.setResponseMessage(CommonResultCode.SUCCESS_NORMAL.getMessage());
		response.setResponseData(signUpUser);
		
		return response;
	}

	@Override
	public CommonResponseVO getUserList(User user) {
		List<User> userList = (List<User>) userRepo.findAll(UserPredicate.searchCondition(user));
		
		CommonResponseVO response = new CommonResponseVO();
		response.setResponseCode(CommonResultCode.SUCCESS_NORMAL.getCode());
		response.setResponseMessage(CommonResultCode.SUCCESS_NORMAL.getMessage());
		response.setResponseDataList(userList);
	
		return response;
	}

	@Override
	public CommonResponseVO updateUserInfo(User user) {
		
		User updateUser = userRepo.findById(user.getId()).get();
		
		userModification(updateUser, user);
		
		userRepo.save(updateUser);
				
		CommonResponseVO response = new CommonResponseVO();
		response.setResponseCode(CommonResultCode.SUCCESS_NORMAL.getCode());
		response.setResponseMessage(CommonResultCode.SUCCESS_NORMAL.getMessage());
		response.setResponseData(updateUser);
		
		return response;
	}

	private void userModification(User updateUser, User user) {
		if(CommonUtils.isNotNull(user.getName()))
			updateUser.setName(user.getName());
		
		if(CommonUtils.isNotNull(user.getEmail()))
			updateUser.setEmail(user.getEmail());
		
		if(CommonUtils.isNotNull(user.getGrade()))
			updateUser.setGrade(user.getGrade());
		
		if(CommonUtils.isNotNull(user.getIsActive()))
			updateUser.setIsActive(user.getIsActive());
		
		if(CommonUtils.isNotNull(user.getRoleCode()))
			updateUser.setRoleCode(user.getRoleCode());
				
		if(CommonUtils.isNotNull(user.getClubCode()))
			updateUser.setClubCode(user.getClubCode());
		
		updateUser.setUpdate_date(Timestamp.valueOf(LocalDateTime.now()));
	}

}
