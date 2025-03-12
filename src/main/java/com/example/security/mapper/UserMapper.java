package com.example.security.mapper;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.UserDto;

import java.util.List;

public interface UserMapper {
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto params);

    int pagedListCount(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> pagedList(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params);
}
