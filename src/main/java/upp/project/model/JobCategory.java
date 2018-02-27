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
public class JobCategory {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.ALL)
    private List<Company> companies;
}
