package org.mjulikelion.liikelion12thhw.week3.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.mjulikelion.liikelion12thhw.week3.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //오류 확인용
    @GetMapping("")
    public List<Memo> printLists(){
        return memoService.print();
    }

    //등록
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMemo(@RequestBody String content, @RequestHeader String userId){
        memoService.registerMemo(content,userId);
    }

    @GetMapping ("/{memoId}")
    public Memo getMemo(@PathVariable int memoId, @RequestHeader String userId){
//        System.out.println(memoService.get(memoId, userId));
        return  memoService.get(memoId, userId);
    }

    //모든 메모 조회
    @GetMapping("")
    public List<Memo> getUserMemoList(@RequestHeader String userId){
        return memoService.getList(userId);
    }

    //특정 메모 삭제
    @DeleteMapping("/{memoId}")
    public void deleteMemo(@PathVariable int memoId, @RequestHeader String userId){
        memoService.delete(memoId, userId);
    }

    //특정 메모 수정
    @PatchMapping("/{memoId}")
    public void modifyMemo(@PathVariable int memoId, @RequestHeader String userId, @RequestBody String content){
        memoService.modify(memoId, userId, content);
    }

}
