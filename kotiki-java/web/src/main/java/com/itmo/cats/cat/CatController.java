package com.itmo.cats.cat;


import com.itmo.cats.cat.Dto.CatCreationRequest;
import com.itmo.cats.cat.Dto.CatResponse;
import com.itmo.cats.cat.Dto.CatUpdateRequest;
import com.itmo.cats.cat.Dto.FriendRequest;
import com.itmo.cats.domain.cat.Cat;
import com.itmo.cats.domain.cat.CatCreationModel;
import com.itmo.cats.domain.cat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    private final CatService _catService;

    @Autowired
    public CatController(CatService catService) {
        _catService = catService;
    }

    @PostMapping(value = "create")
    public void Create(@RequestBody CatCreationRequest request) {
        _catService.add(castCatCreationRequestToCatCreationModel(request));
    }

    @GetMapping(value = "getById")
    public CatResponse getCatById(@RequestParam(value = "id") int id) {
        var model = _catService.getById(id);
        return castCatToCatResponse(model);
    }

    @GetMapping(value = "getAll")
    public List<CatResponse> getAll() {
        var cats = _catService.getAll();
        var result = new ArrayList<CatResponse>();
        for (Cat cat : cats) {
            result.add(castCatToCatResponse(cat));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody CatUpdateRequest request) {
        _catService.update(castCatUpdateRequestToCat(request));
    }

    @PutMapping(value = "addFriend")
    public void addFriend(@RequestBody FriendRequest request) {
        _catService.addFriendById(request.getCatId(), request.getFriendId());
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _catService.deleteById(id);
    }

    @DeleteMapping(value = "removeFriend")
    public void delete(@RequestBody FriendRequest request) {
        _catService.removeFriendById(request.getCatId(), request.getFriendId());
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
        var response = new CatResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBirthDate(model.getBirthDate());
        response.setBreed(model.getBreed());
        response.setColor(model.getColor());
        response.setOwnerId(model.getOwnerId());
        return response;
    }
}
