package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.like.GetLikeListDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoListDto;
import org.mjulikelion.liikelion12thhw.week3.exception.ForbiddenException;
import org.mjulikelion.liikelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.liikelion12thhw.week3.model.Memo;
import org.mjulikelion.liikelion12thhw.week3.model.MemoLike;
import org.mjulikelion.liikelion12thhw.week3.model.User;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoLikeRepository;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoRepository;
import org.mjulikelion.liikelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MemoService {
    //한 서비스에서 여러 리포지토리를 의존해도 된다.
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final MemoLikeRepository likeRepository;

    public void registerMemo(MemoCreateDto memoCreateDTO, UUID userId) {

        validateUser(userId);
        UUID memoId = UUID.randomUUID();
        Memo memo = Memo.builder()
                .title(memoCreateDTO.getTitle())
                .content(memoCreateDTO.getContent())
                .user(findUserByUserId(userId))
                .build();

        memoRepository.save(memo);
    }

    public GetMemoDto get(UUID memoId, UUID userId) {

        validateUser(userId);
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, userId);
        GetMemoDto getMemoDto = new GetMemoDto(memo);
        return getMemoDto;
    }


    public GetMemoListDto getList(UUID userId) {
        validateUser(userId);
        GetMemoListDto getMemoListDto = new GetMemoListDto(memoRepository.findAllById(userId));
        return getMemoListDto;
    }


    public void delete(UUID memoId, UUID userId) {
        validateUser(userId);
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, userId);
        memoRepository.delete(memo);
    }


    public void modify(UUID memoId, UUID userId, MemoModifyDto memoModifyDTO) {
        validateUser(userId);
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, userId);
        if (!memoModifyDTO.getTitle().isEmpty()) memo.setTitle(memoModifyDTO.getTitle());
        if (!memoModifyDTO.getContent().isEmpty()) memo.setContent(memoModifyDTO.getContent());
        memoRepository.save(memo);//세이브 하나로 add, update가 모두 가능 - 기존 ID 존재할 경우 거기에 업데이트한다고 함.
    }


    private void validateUser(UUID userId) {
        if (!userRepository.isExist(userId)) {
            throw new NotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }
    }

    //권한 확인
    private void checkAuth(Memo memo, UUID userId) {
        if (!memo.getUser().getId().equals(userId)) {
            throw new ForbiddenException("해당 메모에 대한 권한이 없는 아이디입니다.");
        }
    }


    public void pushLike(UUID userId, UUID memoId) {
        validateUser(userId);
        Memo memo = this.findMemoByMemoId(memoId);
        User user = this.findUserByUserId(userId);
        MemoLike like = MemoLike.builder()
                .user(user)
                .memo(memo)
                .build();
        likeRepository.save(like);
    }

    public GetLikeListDto getLikeListById(UUID memoId) {
        Memo memo = this.findMemoByMemoId(memoId);
        List<MemoLike> likeList = likeRepository.findAllByMemo(memo);
        int likeCount = likeList.size();
        GetLikeListDto getLikeListDto = new GetLikeListDto(likeCount, likeList);
        return getLikeListDto;
    }

    private Memo findMemoByMemoId(UUID uuid) {
        return memoRepository.findById(uuid).orElseThrow(() ->
                new NotFoundException("메모 " + uuid + "를 찾을 수 없습니다."));
    }

    private User findUserByUserId(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() ->
                new NotFoundException("유저 " + uuid + "를 찾을 수 없습니다."));
    }


}
