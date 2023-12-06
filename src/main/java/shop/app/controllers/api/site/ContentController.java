package shop.app.controllers.api.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.ContentService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Content;
import shop.app.models.site.Nav;

import java.util.List;


@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ServiceResponse<Content> getByKey(@RequestParam String key) {
        try {
            Content result = contentService.findByKey(key);
            return new ServiceResponse<Content>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Content> search(@PathVariable long id) {
        try {
            Content result = contentService.getById(id);
            return new ServiceResponse<Content>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Content> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Content> result = contentService.getAll(pageSize, pageNumber);
            long totalCount = contentService.getAllCount();
            return new ServiceResponse<>(result,totalCount, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Content> add(@RequestBody Content data) {
        try {
            Content result = contentService.add(data);
            return new ServiceResponse<Content>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @PutMapping("/update")
    public ServiceResponse<Content> update(@RequestBody Content data) {
        try {
            Content result = contentService.update(data);
            return new ServiceResponse<Content>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = contentService.deleteById(id);
            return new ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }
}
