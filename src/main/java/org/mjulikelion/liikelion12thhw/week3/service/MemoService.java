package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoCreateDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.memo.MemoModifyDto;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public void registerMemo(MemoCreateDto memoCreateDTO, String userId) {
        int memoId = memoRepository.getMemoId();
        if (memoRepository.isExist(memoId)) {
            throw new IllegalArgumentException("메모 ID " + memoId + "는 이미 존재합니다.");
        }
        Memo memo = new Memo(memoId, memoCreateDTO.getContent(), userId);
        memoRepository.create(memo);
    }

    public Memo get(int memoId, String userId) {

        Optional<Memo> optionalMemo = memoRepository.get(memoId);
        Memo memo = optionalMemo.orElseThrow(() -> new IllegalArgumentException("메모 ID " + memoId + "는 존재하지 않습니다."));
        memoRepository.checkAuth(memo, userId);
        return memo;
    }


    public List<Memo> getList(String userId) {
        return memoRepository.getList(userId);
    }


    public void delete(int memoId, String userId) {
        Memo memo = get(memoId, userId);
        memoRepository.remove(memo);
    }


    public void modify(int memoId, String userId, MemoModifyDto memoModifyDTO) {
        Memo preMemo = get(memoId, userId);
        Memo newMemo = new Memo(memoId, memoModifyDTO.getContent(), userId);
        memoRepository.modify(newMemo, preMemo);
    }

    public List<Memo> print() {
        return memoRepository.printList();
    }

}
