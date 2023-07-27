package mapper;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface AbstractRepositoryMapper<E, D>{

    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTO(List<E> entity);

    List<E> toEntity(List<D> dto);

    @SuppressWarnings("unchecked")
    default Class<E> getEntityType() {
        var typeParameters = AbstractRepositoryMapper.class.getTypeParameters();
        var implTypes = TypeUtils.getTypeArguments(getClass(), AbstractRepositoryMapper.class);

        return implTypes.entrySet().stream()
                .filter(e -> e.getKey().equals(typeParameters[0]))
                .map(e -> (Class<E>) e.getValue())
                .findFirst()
                .orElseThrow();
    }
}
