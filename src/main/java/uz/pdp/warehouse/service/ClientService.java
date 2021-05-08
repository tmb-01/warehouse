package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        boolean existsById = clientRepository.existsById(id);
        if (existsById) {
            return clientRepository.findById(id).get();
        }
        return new Client();
    }

    public Result add(Client client) {
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("client with this phone number is exist", false);
        }
        clientRepository.save(client);
        return new Result("client saved", true);
    }

    public Result delete(Long id) {
        boolean existsById = clientRepository.existsById(id);
        if (existsById) {
            clientRepository.deleteById(id);
            return new Result("client deleted", true);
        }
        return new Result("not client exist with this id", false);
    }

    public Result update(Client client) {
        Optional<Client> byId = clientRepository.findById(client.getId());
        if (byId.isPresent()) {
            Client supplier1 = byId.get();
            supplier1.setName(client.getName());
            supplier1.setActive(client.isActive());
            clientRepository.save(supplier1);
            return new Result("updated", true);
        }
        return new Result("not client exist with this id", false);
    }
}