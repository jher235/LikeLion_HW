package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoRepository implements CustomRepository<Memo>{

    private int memoId=0;
    private final List<Memo> memoList = new LinkedList<>();

    public List<Memo> printList(){
        memoList.forEach(i->System.out.println(i));
        return memoList;

    }

    //여기서 create에 필요한 정보들을 모아 Memo객체를 생성한 후  create로 보내줍니다.
    public void beforeCreate(String content, String writerId){
        Memo memo = new Memo(memoId,content,writerId);
        create(memo);
    }

    @Override
    public void create(Memo memo) {
        checkDupMemoId(memo.getMemoId());
        memoList.add(memo);
        System.out.println("memoList = "+ memoList.size());
        memoId++;
    }

    public Memo get(int memoId, String userId){
        Memo memo = findMemo(memoId);
        checkAuth(findMemo(memoId),userId);
        return memo;
    }

    public List<Memo> getList(String writerId, String userId){
        if(!writerId.equals(userId)){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return memoList.stream().filter(i->i.getWriterId().equals(writerId)).collect(Collectors.toList());
    }

    public void remove(int memoId, String userId){
        checkAuth(findMemo(memoId),userId);
        memoList.remove(findMemo(memoId));
    }

    public void modify(int memoId, String userId, String content){
        Memo memo = findMemo(memoId);
        checkAuth(memo, userId);
        memoList.remove(memo);
        Memo modMemo = new Memo(memoId,content,userId);
        memoList.add(memoId,modMemo);
    }

    //메모 생성 시 중복확인 메서드
    private void checkDupMemoId(int memoId){
        if(memoList.stream().anyMatch(memo -> memo.getMemoId()==memoId)) {
            throw new IllegalArgumentException("메모 ID "+memoId+"는 이미 존재합니다.");
        }
    }

    //단순히 메모의 작성자Id와 유저 Id가 일치하는지 확인하는 메서드. 일치하지 않으면 예외 처리
    private void checkAuth(Memo memo, String userId){
        if(!memo.getWriterId().equals(userId)) throw new IllegalArgumentException("권한이 없습니다.");
    }

    //메모가 존재하는지 확인후 반환하는 메서드
    private Memo findMemo(int memoId) {
        Optional<Memo> memo = memoList.stream().filter(i->i.getMemoId()==memoId).findAny();
        return memo.orElseThrow(() -> new IllegalArgumentException("메모 ID "+memoId+"는 존재하지 않습니다."));
    }
}
