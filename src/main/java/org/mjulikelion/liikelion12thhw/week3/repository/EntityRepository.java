package org.mjulikelion.liikelion12thhw.week3.repository;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public abstract class EntityRepository<T extends Entity> {

    protected final List<T> list;
    protected final Class<T> entityClass;

    public boolean isExist(UUID id) {
        return list.stream().anyMatch(i -> i.getId().equals(id));
    }

    public T findById(UUID id) {
        return list.stream().filter(i -> i.getId().equals(id)).findAny().orElseThrow(()
                -> new NotFoundException(this.entityClass.getSimpleName() + "을 찾을 수 없습니다."));
    }

    public void create(T entity) {
        list.add(entity);
        list.stream().forEach(i -> System.out.println(i.getId()));
    }

    public void remove(T entity) {
        list.remove(entity);
    }

    //객체의 해시 코드를 기반으로 삭제하기 때문에 매게 변수 하나로 수정이 가능
    public void modify(T entity) {
        list.remove(entity);
        list.add(entity);
    }


}
