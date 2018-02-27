package upp.project.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import upp.project.model.Company;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 22/02/2018.
 */
@Data
@NoArgsConstructor
public class CompanyDTO {

    private long id;
    private String name;
    private String address;
    private String postalCode;
    private String place;
    private double maxDistance;
    private JobCategoryDTO jobCategory;

    public CompanyDTO(Company company){
        this(company, true);
    }

    public CompanyDTO(Company company, boolean cascade){
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.postalCode = company.getPostalCode();
        this.place = company.getPlace();
        this.maxDistance = company.getMaxDistance();
        if(cascade){
            this.jobCategory = new JobCategoryDTO(company.getJobCategory(), false);
        }
    }
}
