package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.liikelion12thhw.week3.domain.Like;
import org.mjulikelion.liikelion12thhw.week3.domain.Memo;
import org.mjulikelion.liikelion12thhw.week3.domain.User;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.like.GetLikeListDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoListDto;
import org.mjulikelion.liikelion12thhw.week3.exception.ForbiddenException;
import org.mjulikelion.liikelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoRepository;
import org.mjulikelion.liikelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public void registerMemo(MemoCreateDto memoCreateDTO, UUID userId) {

        validateUser(userId);
        UUID memoId = UUID.randomUUID();
        Memo memo = Memo.builder()
                .id(memoId)
                .title(memoCreateDTO.getTitle())
                .content(memoCreateDTO.getContent())
                .writerId(userId)
                .build();

        memoRepository.create(memo);
    }

    public GetMemoDto get(UUID memoId, UUID userId) {

        validateUser(userId);
        Memo memo = memoRepository.findById(memoId);
        checkAuth(memo, userId);
        GetMemoDto getMemoDto = new GetMemoDto(memo);
        return getMemoDto;
    }


    public GetMemoListDto getList(UUID userId) {
        validateUser(userId);
        GetMemoListDto getMemoListDto = new GetMemoListDto(memoRepository.getListById(userId));
        return getMemoListDto;
    }


    public void delete(UUID memoId, UUID userId) {
        validateUser(userId);
        Memo memo = memoRepository.findById(memoId);
        checkAuth(memo, userId);
        memoRepository.remove(memo);
    }


    public void modify(UUID memoId, UUID userId, MemoModifyDto memoModifyDTO) {
        validateUser(userId);
        Memo memo = memoRepository.findById(memoId);
        checkAuth(memo, userId);
        if (!memoModifyDTO.getTitle().isEmpty()) memo.setTitle(memoModifyDTO.getTitle());
        if (!memoModifyDTO.getContent().isEmpty()) memo.setContent(memoModifyDTO.getContent());
        memoRepository.modify(memo);
    }


    private void validateUser(UUID userId) {
        if (!userRepository.isExist(userId)) {
            throw new NotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }
    }

    //권한 확인
    private void checkAuth(Memo memo, UUID userId) {
        if (!memo.getWriterId().equals(userId)) {
            throw new ForbiddenException("해당 메모에 대한 권한이 없는 아이디입니다.");
        }
    }


    public void like(UUID userId, UUID memoId) {
        validateUser(userId);
        Memo memo = memoRepository.findById(memoId);
        User user = userRepository.findById(userId);
        memo.like(user);
    }

    public GetLikeListDto getLikeListById(UUID memoId) {
        Memo memo = memoRepository.findById(memoId);
        List<Like> likeList = memo.getLikeList();
        int likeCount = likeList.size();
        GetLikeListDto getLikeListDto = new GetLikeListDto(likeCount, likeList);
        return getLikeListDto;
    }


}
