package upp.project.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import upp.project.model.JobCategory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 22/02/2018.
 */
@Data
@NoArgsConstructor
public class JobCategoryDTO {

    private long id;
    private String name;
    private List<CompanyDTO> companies;

    public JobCategoryDTO(JobCategory jobCategory){
        this(jobCategory, true);
    }

    public JobCategoryDTO(JobCategory jobCategory, boolean cascade){
        this.id = jobCategory.getId();
        this.name = jobCategory.getName();
        if(cascade){
            this.companies = jobCategory.getCompanies().stream().map(company -> new CompanyDTO(company, false)).collect(Collectors.toList());
        }
    }
}
