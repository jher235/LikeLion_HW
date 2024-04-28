package org.mjulikelion.liikelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mjulikelion.liikelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.GetMemoDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.GetMemoListDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.liikelion12thhw.week3.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
@Log4j2
public class MemoController {

    private final MemoService memoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Void>> registerMemo(@RequestBody @Valid MemoCreateDto memoCreateDTO, @RequestHeader String userId) {
        //memoCreateDTO의 content가 null일 수도 있음
        memoService.registerMemo(memoCreateDTO, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "게시글 등록 성공"), HttpStatus.CREATED);
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<ResponseDto<GetMemoDto>> getMemo(@PathVariable int memoId, @RequestHeader String userId) {
//        System.out.println(memoService.get(memoId, userId));
        GetMemoDto getMemoDto = new GetMemoDto(memoService.get(memoId, userId));
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단일 메모", getMemoDto), HttpStatus.OK);
    }

    //모든 메모 조회

    //    @GetMapping("")
//    public List<Memo> getUserMemoList(@RequestHeader String userId){
//        return memoService.getList(userId);
//    }
    @GetMapping("")
    public ResponseEntity<ResponseDto<GetMemoListDto>> getUserMemoList(@RequestHeader String userId) {
        GetMemoListDto getMemoListDto = new GetMemoListDto(memoService.getList(userId));
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 리스트", getMemoListDto), HttpStatus.OK);
    }

    //특정 메모 삭제
//    @DeleteMapping("/{memoId}")
//    public void deleteMemo(@PathVariable int memoId, @RequestHeader String userId){
//        memoService.delete(memoId, userId);
//    }

    @DeleteMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> deleteMemo(@PathVariable int memoId, @RequestHeader String userId) {
        memoService.delete(memoId, userId);

        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "삭제완료"), HttpStatus.OK);
    }

    //특정 메모 수정
//    @PatchMapping("/{memoId}")
//    public void modifyMemo(@PathVariable int memoId, @RequestHeader String userId, @RequestBody MemoModifyDto memoModifyDTO){
//        memoService.modify(memoId, userId, memoModifyDTO);
//    }
    @PatchMapping("/{memoId}")
    public ResponseEntity<ResponseDto<Void>> modifyMemo(@PathVariable int memoId, @RequestHeader String userId, @RequestBody @Valid MemoModifyDto memoModifyDTO) {
        memoService.modify(memoId, userId, memoModifyDTO);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "메모 수정 완료"), HttpStatus.OK);
    }

    //메모 좋아요
    @PatchMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<Void>> likeMemo(@RequestHeader String userId) {
//        memoService.like()
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "좋아요 반영 완료"), HttpStatus.OK);
    }

}
