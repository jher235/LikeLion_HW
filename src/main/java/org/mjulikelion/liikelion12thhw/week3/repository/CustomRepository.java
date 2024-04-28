package org.mjulikelion.liikelion12thhw.week3.repository;


import org.mjulikelion.liikelion12thhw.week3.Memo;

import java.util.LinkedList;
import java.util.List;

public interface CustomRepository<T> {
    void create(T entity);
}
