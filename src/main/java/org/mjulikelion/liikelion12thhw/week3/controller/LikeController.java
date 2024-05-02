package org.mjulikelion.liikelion12thhw.week3.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.like.GetLikeListDto;
import org.mjulikelion.liikelion12thhw.week3.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/memos")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<Void>> registerLike(
            @PathVariable int memoId, @RequestHeader String userName) {
        likeService.register(userName, memoId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "정상적으로 처리되었습니다"), HttpStatus.OK);
    }

    @GetMapping("/{memoId}/like")
    public ResponseEntity<ResponseDto<GetLikeListDto>> getLikeList(@PathVariable int memoId) {
        GetLikeListDto getLikeListDto = likeService.getList(memoId);
        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK, "해당 메모의 좋아요 리스트 반환 성공", getLikeListDto), HttpStatus.OK);
    }


}
