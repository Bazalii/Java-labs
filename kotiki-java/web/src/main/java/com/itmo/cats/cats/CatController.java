package com.itmo.cats.cats;


import com.itmo.cats.cats.Dto.FriendRequest;
import com.itmo.cats.cats.Dto.CatCreationRequest;
import com.itmo.cats.cats.Dto.CatResponse;
import com.itmo.cats.cats.Dto.CatUpdateRequest;
import com.itmo.cats.domains.cats.Cat;
import com.itmo.cats.domains.cats.CatCreationModel;
import com.itmo.cats.domains.cats.services.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    private final CatsService _catsService;

    @Autowired
    public CatController(CatsService catsService) {
        _catsService = catsService;
    }

    @PostMapping(value = "create")
    public void Create(@RequestBody CatCreationRequest request) {
        _catsService.add(CastCatCreationRequestToCatCreationModel(request));
    }

    @GetMapping(value = "getById")
    public CatResponse getCatById(@RequestParam(value = "id") int id) {
        var model = _catsService.getById(id);
        return CastCatToCatResponse(model);
    }

    @GetMapping(value = "getAll")
    public List<CatResponse> getAll() {
        var cats = _catsService.getAll();
        var result = new ArrayList<CatResponse>();
        for (Cat cat : cats) {
            result.add(CastCatToCatResponse(cat));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody CatUpdateRequest request) {
        _catsService.update(CastCatUpdateRequestToCat(request));
    }

    @PutMapping(value = "addFriend")
    public void addFriend(@RequestBody FriendRequest request) {
        _catsService.addFriendById(request.catId, request.friendId);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _catsService.deleteById(id);
    }

    @DeleteMapping(value = "removeFriend")
    public void delete(@RequestBody FriendRequest request) {
        _catsService.removeFriendById(request.catId, request.friendId);
    }

    private CatCreationModel CastCatCreationRequestToCatCreationModel(CatCreationRequest request) {
        var model = new CatCreationModel();
        model.name = request.name;
        model.birthDate = request.birthDate;
        model.breed = request.breed;
        model.color = request.color;
        model.ownerId = request.ownerId;
        return model;
    }

    private Cat CastCatUpdateRequestToCat(CatUpdateRequest request) {
        var model = new Cat();
        model.id = request.id;
        model.name = request.name;
        model.birthDate = request.birthDate;
        model.breed = request.breed;
        model.color = request.color;
        model.ownerId = request.ownerId;
        return model;
    }

    private CatResponse CastCatToCatResponse(Cat model) {
        var response = new CatResponse();
        response.id = model.id;
        response.name = model.name;
        response.birthDate = model.birthDate;
        response.breed = model.breed;
        response.color = model.color;
        response.ownerId = model.ownerId;
        return response;
    }
}
