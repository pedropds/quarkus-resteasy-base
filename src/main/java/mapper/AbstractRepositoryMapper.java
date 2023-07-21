package mapper;

import java.util.List;

public interface AbstractRepositoryMapper<E, D>{

    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTO(List<E> entity);

    List<E> toEntity(List<D> dto);

}
