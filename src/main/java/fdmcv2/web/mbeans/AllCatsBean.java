package fdmcv2.web.mbeans;

import fdmcv2.Service.CatService;
import fdmcv2.domain.models.service.CatServiceModel;
import fdmcv2.domain.view.AllCatsViewModel;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllCatsBean {

    private List<AllCatsViewModel> model;

    private CatService catService;
    private ModelMapper modelMapper;

    public AllCatsBean() {
    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
       this.model = this.catService
                .findAllCats()
                .stream()
                .map(c -> this.modelMapper.map(c, AllCatsViewModel.class))
                .collect(Collectors.toList());

        System.out.println();
    }

    public List<AllCatsViewModel> getModel() {
        return this.model;
    }

    public void setModel(List<AllCatsViewModel> model) {
        this.model = model;
    }
}
