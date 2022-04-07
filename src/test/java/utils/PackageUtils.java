package utils;

import domain.Package;
import domain.constant.PackageName;

import java.util.HashMap;
import java.util.Map;

import static domain.constant.PackageName.*;

public class PackageUtils {

    private PackageUtils() {
    }

    public static Map<PackageName, Package> getPackages() {
        Map<PackageName, Package> packages = new HashMap<>();
        packages.put(PACKAGE_S, new Package(PACKAGE_S, 10L, 50L));
        packages.put(PACKAGE_M, new Package(PACKAGE_M, 50L, 100L));
        packages.put(PACKAGE_L, new Package(PACKAGE_L, 500L, 500L));

        return packages;
    }
}

