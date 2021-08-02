package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.StoreMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.StoreDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.masters.Store;
import com.cleviro.ErpManagerApp.repository.masters.StoreRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StoreDto findStoreById(int id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent())
            return StoreMapper.toStoreDto(store.get());
        else throw ExceptionUtil.exception(EntityType.STORE, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<StoreDto> findAllStores() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .map(StoreMapper::toStoreDto)
                .collect(Collectors.toSet());
    }

    @Override
    public StoreDto addStore(StoreDto storeDto) {
        Optional<Store> store = Optional.ofNullable(storeRepository.findByName(storeDto.getName()));
        if (!store.isPresent()){
            Store storeModel = new Store()
                    .setName(storeDto.getName())
                    .setStatus(storeDto.getStatus())
                    .setLocation(modelMapper.map(storeDto.getLocation(), Location.class));
            return StoreMapper.toStoreDto(storeRepository.save(storeModel));
        }
        throw ExceptionUtil.exception(EntityType.STORE, ExceptionType.DUPLICATE_ENTITY,storeDto.getName());
    }

    @Override
    public StoreDto updateStore(StoreDto storeDto) {
        Optional<Store> store = storeRepository.findById(storeDto.getId());
        if (store.isPresent()){
            Optional<Store> store1 = Optional.ofNullable(storeRepository.findByName(storeDto.getName()));
            if (!store1.isPresent()){
                Store storeModel = new Store()
                        .setName(storeDto.getName())
                        .setStatus(storeDto.getStatus())
                        .setLocation(modelMapper.map(storeDto.getLocation(), Location.class));
                return StoreMapper.toStoreDto(storeRepository.save(storeModel));
            }
            throw ExceptionUtil.exception(EntityType.STORE, ExceptionType.DUPLICATE_ENTITY,storeDto.getName());
        }
        throw ExceptionUtil.exception(EntityType.STORE, ExceptionType.ENTITY_NOT_FOUND,storeDto.getName());
    }

    @Override
    public void removeStore(int id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent())storeRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.STORE, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
