package upp.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jasmina on 21/02/2018.
 */
@Entity
@Data
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String postalCode;

    @Column
    private String place;

    @Column
    private double maxDistance;

    @ManyToMany(mappedBy = "companies", cascade = CascadeType.ALL)
    private List<JobCategory> jobCategories;
}
