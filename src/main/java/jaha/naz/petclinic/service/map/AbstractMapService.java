package jaha.naz.petclinic.service.map;

import jaha.naz.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map= new HashMap<>();

    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    public T save(T object){
        if(object!=null){
            if(object.getId()==null){
               object.setId( this.getNextId());
            }
            map.put(object.getId(), object);
        }
        else {
            throw new RuntimeException("Null object can not be saved!!!");
        }
        return object;
    }
    public T findById(ID id){
        return map.get(id);
    }

    public void deleteById(ID id){
        map.remove(id);
    }
    public void delete(T object){
        map.entrySet().removeIf(entity ->entity.equals(object));
    }

    private Long getNextId(){
        if(!map.isEmpty()){
            return Collections.max(map.keySet())+1;
        }
        else
            return 1L;
    }
}
