package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.Branch;
import br.com.wesleyeduardo.stockcontrol.repository.BranchRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BranchForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    @NotNull @NotEmpty @Length(min = 3)
    private String street;

    @NotNull @NotEmpty @Length(min = 3)
    private String city;

    @NotNull @NotEmpty @Length(min = 3)
    private String state;

    @NotNull @NotEmpty @Length(min = 3)
    private String telephone;

    @NotNull @NotEmpty @Length(min = 3)
    private String manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Branch toConvert() {
        return new Branch(name,street,city,state,telephone,manager);
    }

    public Branch update(Long id, BranchRepository branchRepository) {

        Branch branch = branchRepository.getOne(id);

        branch.setName(this.name);
        branch.setStreet(this.street);
        branch.setCity(this.city);
        branch.setState(this.state);
        branch.setTelephone(this.telephone);
        branch.setManager(this.manager);

        return  branch;

    }

}
