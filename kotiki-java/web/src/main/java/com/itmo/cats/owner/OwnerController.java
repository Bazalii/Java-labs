package com.itmo.cats.owner;

import com.itmo.cats.domain.owner.Owner;
import com.itmo.cats.domain.owner.OwnerCreationModel;
import com.itmo.cats.domain.owner.service.OwnerService;
import com.itmo.cats.owner.dto.OwnerCreationRequest;
import com.itmo.cats.owner.dto.OwnerResponse;
import com.itmo.cats.owner.dto.OwnerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final OwnerService _ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        _ownerService = ownerService;
    }

    @PostMapping(value = "create")
    public OwnerResponse Create(@RequestBody OwnerCreationRequest request) {
        var owner = _ownerService.add(castOwnerCreationRequestToOwnerCreationModel(request));
        return castOwnerToOwnerResponse(owner);
    }

    @GetMapping(value = "getById")
    public OwnerResponse getOwnerById(@RequestParam(value = "id") int id) {
        var model = _ownerService.getById(id);
        return castOwnerToOwnerResponse(model);
    }

    @GetMapping(value = "getAll")
    public List<OwnerResponse> getAll() {
        var owners = _ownerService.getAll();
        var result = new ArrayList<OwnerResponse>();
        for (Owner owner : owners) {
            result.add(castOwnerToOwnerResponse(owner));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody OwnerUpdateRequest request) {
        _ownerService.update(castOwnerUpdateRequestToOwner(request));
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _ownerService.deleteById(id);
    }

    private OwnerCreationModel castOwnerCreationRequestToOwnerCreationModel(OwnerCreationRequest request) {
        var model = new OwnerCreationModel();
        model.setName(request.getName());
        model.setBirthDate(request.getBirthDate());
        return model;
    }

    private OwnerResponse castOwnerToOwnerResponse(Owner owner) {
        return new OwnerResponse(owner.getId(), owner.getName(), owner.getBirthDate());
    }

    private Owner castOwnerUpdateRequestToOwner(OwnerUpdateRequest request) {
        var model = new Owner();
        model.setId(request.getId());
        model.setName(request.getName());
        model.setBirthDate(request.getBirthDate());
        return model;
    }
}

