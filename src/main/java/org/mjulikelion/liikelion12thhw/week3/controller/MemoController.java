package org.mjulikelion.liikelion12thhw.week3.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.mjulikelion.liikelion12thhw.week3.dto.MemoDTO;
import org.mjulikelion.liikelion12thhw.week3.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//궁금증 -> RestAPI의 정점이 GET,POST,DELETE 같은 메서드를 지정할 수 있는 것 아닌가?
//그럼 URI를 각 목적별로 분류할 필요없이. "/memo"로 GET,POST,DELETE를 보내서 한번에 처리하는게 좋은건가???
@RestController
@RequestMapping("/memo")
@AllArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //오류 확인용
    @GetMapping("/list")
    public List<Memo> printLists(){
        return memoService.print();
    }

    //등록
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMemo(@RequestBody MemoDTO memoDto){
        memoService.registerMemo(memoDto);
    }

    //단일 조회
    @PostMapping("/read/{mid}")
    public Memo readMemo(@PathVariable int mid, @RequestBody String uid){
        System.out.println(memoService.readMemo(mid, uid));
        return memoService.readMemo(mid, uid);
    }

//    @GetMapping ("/read/{mid}")   //GET으로 조회 시 url에 "?uid=user1"이런 방식으로 uid를 작성해줘야해서 좀 이상한거 같다.
//    public Memo readMemo(@PathVariable int mid, @RequestParam String uid){
//        System.out.println(memoService.readMemo(mid, uid));
//        return  memoService.readMemo(mid, uid);
//    }


    //모든 메모 조회
    @PostMapping("/list/{writerId}")
    public List<Memo> listMemo(@PathVariable String writerId, @RequestBody String uid){
        return memoService.listMemo(writerId, uid);
    }

    //특정 메모 삭제
    @DeleteMapping("/delete/{mid}")
    public void deleteMemo(@PathVariable int mid, @RequestBody String uid){
        memoService.deleteMemo(mid, uid);
    }

    //특정 메모 수정
    @PatchMapping("/modify/{mid}")
    public void modifyMemo(@PathVariable int mid, @RequestBody MemoDTO memoDTO){
        memoService.modifyMemo(mid,memoDTO);
    }


}
