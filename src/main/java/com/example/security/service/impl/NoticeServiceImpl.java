package com.example.security.service.impl;

import com.example.security.domain.Notice;
import com.example.security.dto.DefaultDto;
import com.example.security.dto.NoticeDto;
import com.example.security.mapper.NoticeMapper;
import com.example.security.repository.NoticeRepository;
import com.example.security.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    public NoticeServiceImpl(NoticeRepository noticeRepository, NoticeMapper noticeMapper){
        this.noticeRepository = noticeRepository;
        this.noticeMapper = noticeMapper;
    }

    @Override
    public NoticeDto.CreateResDto create(NoticeDto.CreateReqDto params) {
        return noticeRepository.save(params.toEntity()).toCreateResDto();
    }

    @Override
    public void update(NoticeDto.UpdateReqDto params) {
        Notice notice = noticeRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ notice.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ notice.setProcess(params.getProcess()); }
        if(params.getTitle() != null){ notice.setTitle(params.getTitle()); }
        if(params.getContent() != null){ notice.setContent(params.getContent()); }
        noticeRepository.save(notice);
    }

    @Override
    public void delete(NoticeDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public NoticeDto.DetailResDto get(DefaultDto.DetailReqDto params) {
        return noticeMapper.detail(params);
    }

    @Override
    public NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto params) {
        return get(params);
    }

    public List<NoticeDto.DetailResDto> addlist(List<NoticeDto.DetailResDto> list) {
        List<NoticeDto.DetailResDto> finalList = new ArrayList<>();
        for(NoticeDto.DetailResDto each : list){
            finalList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params) {
        params.init();
        return addlist(noticeMapper.list(params));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto params) {
        DefaultDto.PagedListResDto returnVal = params.init(noticeMapper.pagedListCount(params));
        returnVal.setList(addlist(noticeMapper.pagedList(params)));
        return returnVal;
    }
    @Override
    public List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params) {
        params.init();
        return addlist(noticeMapper.scrollList(params));
    }
}
