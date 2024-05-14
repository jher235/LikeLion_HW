package org.mjulikelion.likelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.like.GetMemoLikesResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.memo.GetMemoListData;
import org.mjulikelion.likelion12thhw.week3.dto.response.memo.MemoResponse;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.ForbiddenException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.Memo;
import org.mjulikelion.likelion12thhw.week3.model.MemoLike;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.repository.MemoLikeRepository;
import org.mjulikelion.likelion12thhw.week3.repository.MemoRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
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

    public void registerMemo(MemoCreateDto memoCreateDTO, User user) {

        Memo memo = Memo.builder()
                .title(memoCreateDTO.getTitle())
                .content(memoCreateDTO.getContent())
                .user(user)
                .build();
        memoRepository.save(memo);
        log.info(String.valueOf(memo.getId()));
    }

    public MemoResponse get(UUID memoId, User user) {
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, user);
        MemoResponse memoResponse = new MemoResponse(memo);
        return memoResponse;
    }


    public GetMemoListData getList(User user) {
        GetMemoListData getMemoListData = new GetMemoListData(memoRepository.findAllByUser(user));
        memoRepository.findAllByUser(user).stream().forEach(i -> log.info(String.valueOf(i)));
        return getMemoListData;
    }


    public void delete(UUID memoId, User user) {
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, user);
        memoRepository.delete(memo);
    }


    public void modify(UUID memoId, User user, MemoModifyDto memoModifyDTO) {
        Memo memo = this.findMemoByMemoId(memoId);
        checkAuth(memo, user);
        if (!memoModifyDTO.getTitle().isEmpty()) memo.setTitle(memoModifyDTO.getTitle());
        if (!memoModifyDTO.getContent().isEmpty()) memo.setContent(memoModifyDTO.getContent());
        memoRepository.save(memo);//세이브 하나로 add, update가 모두 가능 - 기존 ID 존재할 경우 거기에 업데이트한다고 함.
    }


    //권한 확인
    private void checkAuth(Memo memo, User user) {
        if (!memo.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException(ErrorCode.MEMO_FORBIDDEN, "해당 메모에 대한 권한이 없는 아이디입니다.");
        }
    }

    public void like(User user, UUID memoId) {

        Memo memo = this.findMemoByMemoId(memoId);
        if (likeRepository.existsMemoLikeByUserAndMemo(user, memo)) {
            throw new ConflictException(ErrorCode.LIKE_CONFLICT);
        }
        MemoLike memoLike = MemoLike.builder()
                .user(user)
                .memo(memo)
                .build();
        likeRepository.save(memoLike);
    }

    public void unLike(User user, UUID memoId) {

        Memo memo = this.findMemoByMemoId(memoId);
        MemoLike memoLike = likeRepository.findMemoLikeByUserAndMemo(user, memo)
                .orElseThrow(() -> new ConflictException(ErrorCode.UNLIKE_CONFLICT));
        likeRepository.delete(memoLike);
    }


    public GetMemoLikesResponseData getLikeListById(UUID memoId) {
        Memo memo = this.findMemoByMemoId(memoId);
        List<MemoLike> likeList = likeRepository.findAllByMemo(memo);
        int likeCount = likeList.size();
        GetMemoLikesResponseData getMemoLikesResponseData = new GetMemoLikesResponseData(likeCount, likeList);
        log.info("111");
        return getMemoLikesResponseData;
    }

    private Memo findMemoByMemoId(UUID uuid) {
        return memoRepository.findById(uuid).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEM0_NOT_FOUND));
    }


}
