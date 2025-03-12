package com.example.security.mapper;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.NoticeDto;

import java.util.List;

public interface NoticeMapper {
    NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params);

    int pagedListCount(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> pagedList(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params);
}
