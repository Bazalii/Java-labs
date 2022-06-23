package com.itmo.cats.service.implementation;

import com.itmo.cats.coremodels.cat.Cat;
import com.itmo.cats.coremodels.cat.CatCreationModel;
import com.itmo.cats.coremodels.cat.FriendModel;
import com.itmo.cats.coremodels.cat.GetAllCatsByIdMessage;
import com.itmo.cats.coremodels.cat.GetCatByIdMessage;
import com.itmo.cats.repository.CatRepository;
import com.itmo.cats.service.CatService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableRabbit
@Service
public class CatServiceImpl implements CatService {
    private final CatRepository _catRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository) {
        _catRepository = catRepository;
    }

    @Override
    @RabbitListener(queues = "catsGetByIdQueue")
    public Cat getById(GetCatByIdMessage getCatByIdMessage) {
        return _catRepository.getById(getCatByIdMessage);
    }

    @Override
    @RabbitListener(queues = "catsAddQueue")
    public Cat add(CatCreationModel model) {
        var cat = new Cat();
        cat.setName(model.getName());
        cat.setBirthDate(model.getBirthDate());
        cat.setBreed(model.getBreed());
        cat.setColor(model.getColor());
        cat.setOwnerId(model.getOwnerId());
        return _catRepository.add(cat);
    }

    @Override
    @RabbitListener(queues = "catsUpdateQueue")
    public void update(Cat cat) {
        _catRepository.update(cat);
    }

    @Override
    @RabbitListener(queues = "catsDeleteByIdQueue")
    public void deleteById(int id) {
        _catRepository.deleteById(id);
    }

    @Override
    @RabbitListener(queues = "catsGetAllQueue")
    public List<Cat> getAll(GetAllCatsByIdMessage getAllCatsByIdMessage) {
        return _catRepository.getAll(getAllCatsByIdMessage);
    }

    @Override
    @RabbitListener(queues = "catsAddFriendByIdQueue")
    public void addFriendById(FriendModel friendModel) {
        _catRepository.addFriendById(friendModel.getCatId(), friendModel.getFriendId());
    }

    @Override
    @RabbitListener(queues = "catsRemoveFriendByIdQueue")
    public void removeFriendById(FriendModel friendModel) {
        _catRepository.removeFriendById(friendModel.getCatId(), friendModel.getFriendId());
    }
}
