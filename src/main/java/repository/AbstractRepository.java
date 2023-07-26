package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import mapper.AbstractRepositoryMapper;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

abstract class AbstractRepository<E extends BaseEntity<? extends Serializable>, D> implements PanacheRepository<E> {

    abstract AbstractRepositoryMapper<E, D> getMapper();

    private Class<E> getEntityType() {
        return getMapper().getEntityType();
    }

    public List<D> getAll() {
        return getMapper().toDTO(findAll().list());
    }

    public List<D> findByQuery(String query, Map<String, Object> params) {
        var list = list(query, params);
        return getMapper().toDTO(list);
    }

    @Transactional
    public D save(D dto) {
        E entity = getMapper().toEntity(dto);
        if (entity.getId() == null) {
            persist(entity);
        } else {
            merge(entity, entity.getId());
        }
        return dto;
    }

    @Transactional
    public D saveIfNotExists(D dto) {
        E entity = getMapper().toEntity(dto);
        E persisted = getEntityManager().find(getEntityType(), entity.getId());

        if (persisted == null)
            persist(entity);

        return dto;
    }

    @Transactional
    public void merge(E newEntity, Serializable id) {
        try {
            E persisted = getEntityManager().find(getEntityType(), id);

            if (persisted != null) {
                BeanUtils.copyProperties(persisted, newEntity);
                BeanUtils.setProperty(persisted, "id", id);
            } else {
                persisted = newEntity;
            }
            persist(persisted);
        } catch (Exception e) {
            throw new RuntimeException("Error merging entity", e);
        }
    }

    public D findById(Serializable id) {
        return getMapper().toDTO(getEntityManager().find(getEntityType(), id));
    }
}
