package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mjulikelion.likelion12thhw.week3.annotation.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.like.GetMemoLikesResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.memo.GetMemoListData;
import org.mjulikelion.likelion12thhw.week3.dto.response.memo.MemoResponse;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
@Log4j2
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Void>> registerMemo(@RequestBody @Valid MemoCreateDto memoCreateDTO, @AuthenticatedUser User user) {
        memoService.registerMemo(memoCreateDTO, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "메모 등록 성공"), HttpStatus.CREATED);
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<ResponseDto<MemoResponse>> getMemo(@PathVariable UUID memoId, @AuthenticatedUser User user) {
        MemoResponse memoResponse = memoService.get(memoId, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단일 메모", memoResponse), HttpStatus.OK);
    }

    //모든 메모 조회
    @GetMapping
    public ResponseEntity<ResponseDto<GetMemoListData>> getUserMemoList(@AuthenticatedUser User user) {
        GetMemoListData getMemoListData = memoService.getList(user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 리스트", getMemoListData), HttpStatus.OK);
    }

    //특정 메모 삭제
    @DeleteMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> deleteMemo(@PathVariable UUID memoId, @AuthenticatedUser User user) {
        memoService.delete(memoId, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "삭제완료"), HttpStatus.OK);
    }

    //특정 메모 수정
    @PatchMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> modifyMemo(@PathVariable UUID memoId, @AuthenticatedUser User user, @RequestBody @Valid MemoModifyDto memoModifyDTO) {
        memoService.modify(memoId, user, memoModifyDTO);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 수정 완료"), HttpStatus.OK);
    }

    @PostMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<Void>> registerLike(
            @PathVariable UUID memoId, @AuthenticatedUser User user) {
        memoService.like(user, memoId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "정상적으로 좋아요가 추가 되었습니다"), HttpStatus.OK);
    }

    @DeleteMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<Void>> deleteLike(
            @PathVariable UUID memoId, @AuthenticatedUser User user) {
        memoService.unLike(user, memoId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "정상적으로 좋아요가 삭제 되었습니다"), HttpStatus.OK);
    }

    @GetMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<GetMemoLikesResponseData>> getLikeList(@PathVariable UUID memoId) {
        GetMemoLikesResponseData getMemoLikesResponseData = memoService.getLikeListById(memoId);
        log.info("222");
        log.info(getMemoLikesResponseData.getLikeList());
        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK, "해당 메모의 좋아요 리스트 반환 성공", getMemoLikesResponseData), HttpStatus.OK);
    }


}
