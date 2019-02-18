package fdmcv2.web.mbeans;

import fdmcv2.Service.CatService;
import fdmcv2.domain.models.binding.CatCreateBindingModel;
import fdmcv2.domain.models.service.CatServiceModel;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;

    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean() {
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return this.catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void create() throws IOException {
        this.catService.saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));

        FacesContext.getCurrentInstance().getExternalContext().redirect("all-cats.xhtml");
    }
}
