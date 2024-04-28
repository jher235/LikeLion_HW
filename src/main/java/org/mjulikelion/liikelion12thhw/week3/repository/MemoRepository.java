package org.mjulikelion.liikelion12thhw.week3.repository;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemoRepository implements CustomRepository<Memo> {

    private final List<Memo> memoList = new LinkedList<>();
    @Getter
    private int memoId = 0;

    public List<Memo> printList() {
        memoList.forEach(i -> System.out.println(i));
        return memoList;

    }

    public void create(Memo memo) {
        memoList.add(memo);
        System.out.println("memoList = " + memoList.size());
        memoId++;
    }

    public Optional<Memo> get(int memoId) {
        Optional<Memo> memo = memoList.stream().filter(i -> i.getMemoId() == memoId).findAny();
        return memo;
    }

    public List<Memo> getList(String userId) {
        return memoList.stream().filter(i -> i.getWriterId().equals(userId)).collect(Collectors.toList());
    }

    public void remove(Memo memo) {
        memoList.remove(memo);
    }


    public void modify(Memo modMemo, Memo preMemo) {
        memoList.remove(preMemo);
        memoList.add(modMemo);
    }

    //메모 생성 전 메모가 존재하는지 확인
    public boolean isExist(int memoId) {
        return memoList.stream().anyMatch(m -> m.getMemoId() == memoId);
    }

    //단순히 메모의 작성자Id와 유저 Id가 일치하는지 확인 후 boolean 반환
    public boolean checkAuth(Memo memo, String userId) {
        return memo.getWriterId().equals(userId);
    }

}
