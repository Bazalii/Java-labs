package com.itmo.cats.owners;

import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.OwnerCreationModel;
import com.itmo.cats.domains.owners.services.OwnerService;
import com.itmo.cats.owners.Dto.OwnerCreationRequest;
import com.itmo.cats.owners.Dto.OwnerResponse;
import com.itmo.cats.owners.Dto.OwnerUpdateRequest;
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
    public void Create(@RequestBody OwnerCreationRequest request) {
        _ownerService.add(CastOwnerCreationRequestToOwnerCreationModel(request));
    }

    @GetMapping(value = "getById")
    public OwnerResponse getOwnerById(@RequestParam(value = "id") int id) {
        var model = _ownerService.getById(id);
        return CastOwnerToOwnerResponse(model);
    }

    @GetMapping(value = "getAll")
    public List<OwnerResponse> getAll() {
        var owners = _ownerService.getAll();
        var result = new ArrayList<OwnerResponse>();
        for (Owner owner : owners) {
            result.add(CastOwnerToOwnerResponse(owner));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody OwnerUpdateRequest request) {
        _ownerService.update(CastOwnerUpdateRequestToOwner(request));
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _ownerService.deleteById(id);
    }

    private OwnerCreationModel CastOwnerCreationRequestToOwnerCreationModel(OwnerCreationRequest request) {
        var model = new OwnerCreationModel();
        model.name = request.name;
        model.birthDate = request.birthDate;
        return model;
    }

    private OwnerResponse CastOwnerToOwnerResponse(Owner owner) {
        var response = new OwnerResponse();
        response.id = owner.id;
        response.name = owner.name;
        response.birthDate = owner.birthDate;
        return response;
    }

    private Owner CastOwnerUpdateRequestToOwner(OwnerUpdateRequest request) {
        var model = new Owner();
        model.id = request.id;
        model.name = request.name;
        model.birthDate = request.birthDate;
        return model;
    }
}

