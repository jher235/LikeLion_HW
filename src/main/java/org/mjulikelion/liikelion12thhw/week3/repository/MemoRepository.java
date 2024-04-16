package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemoRepository {

    private int memoId=0;
    private final List<Memo> memoList = new ArrayList<>();

    public List<Memo> printList(){
        memoList.forEach(i->System.out.println(i));
        return memoList;
    }

    public void create(String content, String writerId){
        Memo memo = new Memo(memoId++,content,writerId);

        checkDupMid(memo.getMid());

        memoList.add(memo);

        System.out.println("memoList = "+memoList.size());

    }

    public Memo read(int mid, String uid){
        return checkAuth(mid,uid);
    }

    public List<Memo> getList(String writerId, String uid){
        if(!uid.equals(writerId)) throw new IllegalArgumentException("권한이 없습니다.");
        return memoList.stream().filter(i->i.getWriterId().equals(writerId)).collect(Collectors.toList());
    }

    public void remove(int mid, String uid){
        checkAuth(mid,uid);
        memoList.remove(mid);
    }

    public void modify(int mid, String uid, String content){
        Memo memo = checkAuth(mid,uid);
        memoList.remove(memo);
        Memo modMemo = new Memo(mid,content,uid);
        memoList.add(modMemo);
    }



    //메모 생성 시 중복확인 메서드 -> 중복X->true, 중복O->false
    private void checkDupMid(int mid){
        Optional<Memo> dupMemo = filterId(mid);
        if(dupMemo.isPresent()) throw new IllegalArgumentException("메모 ID "+mid+"는 이미 존재합니다.");
    }

    //메모의 존재를 확인한 후 메모에 대한 권한이 없으면 예외 처리, 권한 있으면 메모 리턴
    private Memo checkAuth(int mid, String uid){
        Memo memo = checkExistMemo(mid);
//        System.out.println(uid+" : "+memo.getWriterId()+ " : " +memo.getMid());
        if(!memo.getWriterId().equals(uid)) throw new IllegalArgumentException("권한이 없습니다.");
        return memo;
    }


    //메모가 존재하는지 확인후 반환하는 메서드
    private Memo checkExistMemo(int mid){
        Optional<Memo> memo = filterId(mid);
        return memo.orElseThrow(() -> new IllegalArgumentException("메모 ID "+mid+"는 존재하지 않습니다."));
    }

    //메모 리스트에서 메모를 찾는 메서드
    private Optional<Memo> filterId(int mid){
        Optional<Memo> memo = memoList.stream().filter(i->i.getMid()==mid).findAny();
        return memo;
    }


}
