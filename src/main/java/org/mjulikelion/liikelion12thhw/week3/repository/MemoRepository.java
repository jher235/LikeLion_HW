package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MemoRepository extends EntityRepository<Memo> {

    public MemoRepository() {
        super(new LinkedList<>(), Memo.class);
    }

    public List<Memo> getListById(UUID userId) {
        return super.list.stream().filter(i -> i.getWriterId().equals(userId)).collect(Collectors.toList());
    }


}
