package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        return clientService.update(client);
    }
}
