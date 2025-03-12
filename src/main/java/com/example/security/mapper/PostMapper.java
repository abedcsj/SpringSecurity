package com.example.security.mapper;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.PostDto;

import java.util.List;

public interface PostMapper {
    PostDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostDto.DetailResDto> list(PostDto.ListReqDto params);

    int pagedListCount(PostDto.PagedListReqDto params);
    List<PostDto.DetailResDto> pagedList(PostDto.PagedListReqDto params);
    List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto params);
}
