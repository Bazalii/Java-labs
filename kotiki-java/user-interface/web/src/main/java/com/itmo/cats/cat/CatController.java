package com.itmo.cats.cat;


import com.itmo.cats.coremodels.cat.*;
import com.itmo.cats.dtomodels.cat.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {

    private final RabbitTemplate _rabbitTemplate;

    @Autowired
    public CatController(RabbitTemplate rabbitTemplate) {
        _rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "create")
    public CatResponse Create(@RequestBody CatCreationRequest request) {
        var cat = _rabbitTemplate.convertSendAndReceive("catsAddQueue", castCatCreationRequestToCatCreationModel(request));
        return castCatToCatResponse((Cat) cat);
    }

    @GetMapping(value = "getById")
    public CatResponse getCatById(@RequestParam(value = "id") int id) {
        var model = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue",
                new GetCatByIdMessage(id, SecurityContextHolder.getContext().getAuthentication().getName()));
        return castCatToCatResponse((Cat) model);
    }

    @GetMapping(value = "getAll")
    public List<CatResponse> getAll() {
        var cats = (List<Cat>) _rabbitTemplate.convertSendAndReceive("catsGetAllQueue",
                new GetAllCatsByIdMessage(SecurityContextHolder.getContext().getAuthentication().getName()));
        var result = new ArrayList<CatResponse>();
        for (Cat cat : cats) {
            result.add(castCatToCatResponse(cat));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody CatUpdateRequest request) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateQueue", castCatUpdateRequestToCat(request));
    }

    @PutMapping(value = "addFriend")
    public void addFriend(@RequestBody FriendRequest request) {
        _rabbitTemplate.convertAndSend("catsAddFriendByIdQueue", new FriendModel(request.getCatId(), request.getFriendId()));
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _rabbitTemplate.convertSendAndReceive("catsDeleteByIdQueue", id);
    }

    @DeleteMapping(value = "removeFriend")
    public void delete(@RequestBody FriendRequest request) {
        _rabbitTemplate.convertAndSend("catsRemoveFriendByIdQueue", new FriendModel(request.getCatId(), request.getFriendId()));
    }

    private CatCreationModel castCatCreationRequestToCatCreationModel(CatCreationRequest request) {
        var model = new CatCreationModel();
        model.setName(request.getName());
        model.setBirthDate(request.getBirthDate());
        model.setBreed(request.getBreed());
        model.setColor(request.getColor());
        model.setOwnerId(request.getOwnerId());
        return model;
    }

    private Cat castCatUpdateRequestToCat(CatUpdateRequest request) {
        var model = new Cat();
        model.setId(request.getId());
        model.setName(request.getName());
        model.setBirthDate(request.getBirthDate());
        model.setBreed(request.getBreed());
        model.setColor(request.getColor());
        model.setOwnerId(request.getOwnerId());
        return model;
    }

    private CatResponse castCatToCatResponse(Cat model) {
        if (model == null) {
            return null;
        }

        return new CatResponse(model.getId(), model.getName(), model.getBirthDate(), model.getBreed(), model.getColor(), model.getOwnerId());
    }
}
