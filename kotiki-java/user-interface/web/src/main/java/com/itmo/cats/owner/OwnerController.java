package com.itmo.cats.owner;

import com.itmo.cats.coremodels.owner.Owner;
import com.itmo.cats.coremodels.owner.OwnerCreationModel;
import com.itmo.cats.dtomodels.owner.OwnerCreationRequest;
import com.itmo.cats.dtomodels.owner.OwnerResponse;
import com.itmo.cats.dtomodels.owner.OwnerUpdateRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {

    private final RabbitTemplate _rabbitTemplate;

    @Autowired
    public OwnerController(RabbitTemplate rabbitTemplate) {
        _rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "create")
    public OwnerResponse Create(@RequestBody OwnerCreationRequest request) {
        var owner = _rabbitTemplate.convertSendAndReceive("ownersAddQueue", castOwnerCreationRequestToOwnerCreationModel(request));

        return castOwnerToOwnerResponse((Owner) owner);
    }

    @GetMapping(value = "getById")
    public OwnerResponse getOwnerById(@RequestParam(value = "id") int id) {
        var model = _rabbitTemplate.convertSendAndReceive("ownersGetByIdQueue", id);

        return castOwnerToOwnerResponse((Owner) model);
    }

    @GetMapping(value = "getAll")
    public List<OwnerResponse> getAll() {
        var owners = (List<Owner>) _rabbitTemplate.convertSendAndReceive("ownersGetAllQueue", 1);

        var result = new ArrayList<OwnerResponse>();

        for (Owner owner : owners) {
            result.add(castOwnerToOwnerResponse(owner));
        }

        return result;
    }

    @PutMapping(value = "update")
    public void update(@RequestBody OwnerUpdateRequest request) {
        _rabbitTemplate.convertSendAndReceive("ownersUpdateQueue", castOwnerUpdateRequestToOwner(request));
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam(value = "id") int id) {
        _rabbitTemplate.convertSendAndReceive("ownersDeleteByIdQueue", id);
    }

    private OwnerCreationModel castOwnerCreationRequestToOwnerCreationModel(OwnerCreationRequest request) {
        var model = new OwnerCreationModel(request.getName(), request.getBirthDate());

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

