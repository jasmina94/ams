package upp.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
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

    @ManyToOne
    private JobCategory jobCategory;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CustomUser> agents = new ArrayList<>();
}
