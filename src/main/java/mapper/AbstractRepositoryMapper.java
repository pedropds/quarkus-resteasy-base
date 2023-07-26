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
                .filter(entry -> typeParameters[0].equals(entry.getKey()))
                .map(entry -> (Class<E>) entry.getValue())
                .findFirst()
                .orElseThrow();
    }
}
