package br.com.wesleyeduardo.stockcontrol.dto;
import br.com.wesleyeduardo.stockcontrol.model.Branch;
import org.springframework.data.domain.Page;

public class BranchDto {

    private Long id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String telephone;
    private String manager;

    public BranchDto(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
        this.street = branch.getStreet();
        this.city = branch.getCity();
        this.state = branch.getState();
        this.telephone = branch.getTelephone();
        this.manager = branch.getManager();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getManager() {
        return manager;
    }

    public static Page<BranchDto> converter(Page<Branch> branchs) {
        return branchs.map(BranchDto::new);
    }
}
