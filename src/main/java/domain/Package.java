package domain;

import domain.constant.PackageName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Package {

    private PackageName name;
    private Long availableMinutes;
    private Long availableSms;

}
