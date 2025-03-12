package com.example.security.mapper;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.PostimgDto;

import java.util.List;

public interface PostimgMapper {
    PostimgDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto params);

    int pagedListCount(PostimgDto.PagedListReqDto params);
    List<PostimgDto.DetailResDto> pagedList(PostimgDto.PagedListReqDto params);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto params);
}
