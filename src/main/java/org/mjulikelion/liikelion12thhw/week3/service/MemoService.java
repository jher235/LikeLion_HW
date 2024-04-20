package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public void registerMemo(String content, String userId){
        memoRepository.create(content, userId);
    }


    public Memo get(int memoId, String userId){
        return memoRepository.get(memoId,userId);
    }


    public List<Memo> getList(String writerId, String userId){
        return memoRepository.getList(writerId, userId);
    }


    public void delete(int memoId, String userId){
        memoRepository.remove(memoId,userId);
    }


    public void modify(int memoId, String userId, String content){
        memoRepository.modify(memoId, userId, content);
    }

    public List<Memo> print(){
        return memoRepository.printList();
    }

}
