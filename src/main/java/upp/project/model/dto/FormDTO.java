package upp.project.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.form.FormProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jasmina on 23/02/2018.
 */
@Data
@NoArgsConstructor
public class FormDTO {

    private String message;

    private String formKey;

    private List<FormProperty> formProperties = new ArrayList<>();
    
    private LinkedHashMap<String, String> enumMap = new LinkedHashMap<>();
}
