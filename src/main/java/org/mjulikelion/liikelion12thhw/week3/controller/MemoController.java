package org.mjulikelion.liikelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mjulikelion.liikelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.like.GetLikeListDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoDto;
import org.mjulikelion.liikelion12thhw.week3.dto.response.memo.GetMemoListDto;
import org.mjulikelion.liikelion12thhw.week3.service.MemoService;
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
    public ResponseEntity<ResponseDto<Void>> registerMemo(@RequestBody @Valid MemoCreateDto memoCreateDTO, @RequestHeader("userId") UUID userId) {
        memoService.registerMemo(memoCreateDTO, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "메모 등록 성공"), HttpStatus.CREATED);
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<ResponseDto<GetMemoDto>> getMemo(@PathVariable UUID memoId, @RequestHeader("userId") UUID userId) {
        GetMemoDto getMemoDto = memoService.get(memoId, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단일 메모", getMemoDto), HttpStatus.OK);
    }

    //모든 메모 조회
    @GetMapping
    public ResponseEntity<ResponseDto<GetMemoListDto>> getUserMemoList(@RequestHeader("userId") UUID userId) {
        GetMemoListDto getMemoListDto = memoService.getList(userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 리스트", getMemoListDto), HttpStatus.OK);
    }

    //특정 메모 삭제
    @DeleteMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> deleteMemo(@PathVariable UUID memoId, @RequestHeader("userId") UUID userId) {
        memoService.delete(memoId, userId);

        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "삭제완료"), HttpStatus.OK);
    }

    //특정 메모 수정
    @PatchMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> modifyMemo(@PathVariable UUID memoId, @RequestHeader("userId") UUID userId, @RequestBody @Valid MemoModifyDto memoModifyDTO) {
        memoService.modify(memoId, userId, memoModifyDTO);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 수정 완료"), HttpStatus.OK);
    }

    @PostMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<Void>> registerLike(
            @PathVariable UUID memoId, @RequestHeader("userId") UUID userId) {
        memoService.like(userId, memoId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "정상적으로 처리되었습니다"), HttpStatus.OK);
    }

    @GetMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<GetLikeListDto>> getLikeList(@PathVariable UUID memoId) {
        GetLikeListDto getLikeListDto = memoService.getLikeListById(memoId);
        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK, "해당 메모의 좋아요 리스트 반환 성공", getLikeListDto), HttpStatus.OK);
    }


}
