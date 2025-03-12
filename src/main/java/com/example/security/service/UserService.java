package com.example.security.service;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto.LoginResDto login(UserDto.LoginReqDto params);
    /**/
    UserDto.CreateResDto create(UserDto.CreateReqDto params);
    void update(UserDto.UpdateReqDto params);
    void delete(UserDto.UpdateReqDto params);
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto params);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto params);

    DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params);
}
