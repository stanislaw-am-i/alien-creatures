package lt.ehu.student.aliencreatures.dao;

import lt.ehu.student.aliencreatures.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t);
    public abstract boolean delete(T t);
    public abstract List<T> findAll();
    public abstract T update(T t);
}
