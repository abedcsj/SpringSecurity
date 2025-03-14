package com.example.security.service.impl;

import com.example.security.domain.User;
import com.example.security.dto.DefaultDto;
import com.example.security.dto.UserDto;
import com.example.security.mapper.UserMapper;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;
import com.example.security.util.TokenFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto.LoginResDto login(UserDto.LoginReqDto params) {
        User user = userRepository.findByUsernameAndPassword(params.getUsername(), params.getPassword());
        String refreshToken = null;
        UserDto.LoginResDto resDto = null;
        if(user == null){
        } else {
            refreshToken = new TokenFactory().generateToken(user.getId());
            String afterValue = new TokenFactory().verifyToken(refreshToken);
            System.out.println("afterValue: " + afterValue);
        }
        return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
    }

    /**/

    @Override
    public UserDto.CreateResDto create(UserDto.CreateReqDto params) {

        User user = userRepository.findByUsername(params.getUsername());
        if(user != null){
            //아이디가 중복이라는 뜻!
            //throw new RuntimeException("id duplicated");
            return UserDto.CreateResDto.builder().id((long)-100).build();
        }
        return userRepository.save(params.toEntity()).toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto params) {
        User user = userRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ user.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ user.setProcess(params.getProcess()); }

        if(params.getPassword() != null){ user.setPassword(params.getPassword()); }
        if(params.getName() != null){ user.setName(params.getName()); }
        if(params.getNick() != null){ user.setNick(params.getNick()); }
        if(params.getPhone() != null){ user.setPhone(params.getPhone()); }
        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public UserDto.DetailResDto get(DefaultDto.DetailReqDto params) {
        return userMapper.detail(params);
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto params) {
        return get(params);
    }

    public List<UserDto.DetailResDto> addlist(List<UserDto.DetailResDto> list) {
        List<UserDto.DetailResDto> finalList = new ArrayList<>();
        for(UserDto.DetailResDto each : list){
            finalList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto params) {
        params.init();
        return addlist(userMapper.list(params));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto params) {
        DefaultDto.PagedListResDto returnVal = params.init(userMapper.pagedListCount(params));
        returnVal.setList(addlist(userMapper.pagedList(params)));
        return returnVal;
    }
    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params) {
        params.init();
        return addlist(userMapper.scrollList(params));
    }
}
