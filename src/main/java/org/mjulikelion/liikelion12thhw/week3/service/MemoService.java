package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.Memo;
import org.mjulikelion.liikelion12thhw.week3.dto.MemoDTO;
import org.mjulikelion.liikelion12thhw.week3.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public void registerMemo(MemoDTO memoDto){
        memoRepository.create(memoDto.getContent(),memoDto.getUid());
    }


    public Memo readMemo(int mid, String uid){
        return memoRepository.read(mid,uid);
    }


    public List<Memo> listMemo(String writerId, String uid){
        return memoRepository.getList(writerId, uid);
    }


    public void deleteMemo(int mid, String uid){
        memoRepository.remove(mid,uid);
    }


    public void modifyMemo(int mid, MemoDTO memoDTO){
        memoRepository.modify(mid, memoDTO.getUid(), memoDTO.getContent());
    }

    public List<Memo> print(){
        return memoRepository.printList();
    }

}
