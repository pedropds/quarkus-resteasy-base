package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import mapper.AbstractRepositoryMapper;

import java.util.List;
import java.util.Map;

abstract class AbstractRepository<E,D> implements PanacheRepository<E> {

    abstract AbstractRepositoryMapper<E,D> getMapper();

    public List<D> getAll() {
        return getMapper().toDTO(findAll().list());
    }

    public List<D> findByQuery(String query, Map<String, Object> params) {
        var list = list(query, params);
        return getMapper().toDTO(list);
    }
}
