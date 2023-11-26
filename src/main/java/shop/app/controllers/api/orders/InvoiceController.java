package shop.app.controllers.api.orders;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.*;
import shop.app.Services.orders.InvoiceService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.orders.Invoice;

import java.util.List;


@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoicceService) {
        this.invoiceService = invoicceService;
    }

    @GetMapping("/find")
    public ServiceResponse<Invoice> find(@RequestParam long cid){
        try {
            List<Invoice> result = invoiceService.findByCustomer(cid);
            return new  ServiceResponse<Invoice>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Invoice>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceResponse<Invoice> search(@PathVariable long id){
        try {
            Invoice result = invoiceService.getById(id);
            return new  ServiceResponse<Invoice>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Invoice>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Invoice> add(@RequestBody Invoice data){
        try {
            Invoice result = invoiceService.add(data);
            return new  ServiceResponse<Invoice>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Invoice>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Invoice> update(@RequestBody Invoice data){
        try {
            Invoice result = invoiceService.update(data);
            return new  ServiceResponse<Invoice>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Invoice>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = invoiceService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
