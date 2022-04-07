package domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageName {

    PACKAGE_S("Package S"),
    PACKAGE_M("Package M"),
    PACKAGE_L("Package L");

    private final String name;

}
