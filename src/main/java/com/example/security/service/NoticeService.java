package com.example.security.service;

import com.example.security.dto.DefaultDto;
import com.example.security.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    NoticeDto.CreateResDto create(NoticeDto.CreateReqDto params);
    void update(NoticeDto.UpdateReqDto params);
    void delete(NoticeDto.UpdateReqDto params);
    NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto params);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params);

    DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params);
}
