package shop.app.Services.users;

import org.springframework.stereotype.Service;
import shop.app.ConstantsMessage;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.users.Customer;
import shop.app.repositories.users.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getById(long id) {
        Optional<Customer> data = customerRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Customer add(Customer data) {
        return customerRepository.save(data);
    }

    public Customer update(Customer data) throws DataNotFoundException {
        Customer oldData = getById(data.getId());//getId with db
        if (oldData == null) {
            throw new DataNotFoundException("data with id" + data.getId() + "not found");
        }
        oldData.setAddress(data.getAddress());
        oldData.setEmail(data.getEmail());
        oldData.setFirstname(data.getFirstname());
        oldData.setLastname(data.getLastname());
        oldData.setMobile(data.getMobile());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());
        return customerRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Customer oldData = getById(id);//getId with db
        if (oldData == null) {
            throw new DataNotFoundException(String.format(String.format(ConstantsMessage.MSG, id)));
        }
        customerRepository.deleteById(id);
        return true;
    }

}
