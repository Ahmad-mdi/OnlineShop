package shop.app.Services.orders;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.orders.Invoice;
import shop.app.repositories.orders.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> findByCustomer(long customerId){
        return invoiceRepository.findAllByCustomer(customerId);
    }
    public Invoice getById(long id) {
        Optional<Invoice> data = invoiceRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Invoice add(Invoice data) {
        return invoiceRepository.save(data);
    }

    public Invoice update(Invoice data) throws DataNotFoundException {
        Invoice oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setPayedDate(data.getPayedDate());
        return invoiceRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Invoice oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        invoiceRepository.deleteById(id);
        return true;
    }

}
