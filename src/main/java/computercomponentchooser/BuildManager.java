package computercomponentchooser;

import java.util.HashMap;
import java.util.Map;

import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.exceptions.UnlistedBuildException;

public class BuildManager {
    private static Map<String, Build> builds;

    public BuildManager() {
        builds = new HashMap<>();
    }

    public void addBuild(Build build) throws DuplicateBuildException {
        if (builds.containsKey(build.getName())) {
            throw new DuplicateBuildException();
        }
        builds.put(build.getName(), build);
    }

    public void deleteBuild(String name, Build build) throws UnlistedBuildException {
        if (!builds.containsKey(build.getName())) {
            throw new UnlistedBuildException();
        }
        builds.remove(name);
    }

    public Build getBuild(String name) {
        return builds.get(name);
    }

    public Map<String, Build> getBuilds() {
        return builds;
    }

    public int size() {
        return builds.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String name : builds.keySet()) {
            sb.append(i).append(". ").append(name).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }

    public void findBuilds(String searchTerm) {
        for (String name : builds.keySet()) {
            if (name.contains(searchTerm)) {
                System.out.println(name);
            }
        }
    }

    public void filterBuilds(String filterType, String lowestNumber, String highestNumber) {
        // exception to ensure/handle the possible input error where lowestNumber > highestNumber
        // ensure that the user input is a number
        // ensure that numbers are >= 0
        int i = 0;
        switch (filterType) {
        case "price":
            for (String name : builds.keySet()) {
                float lowestNum = Float.parseFloat(lowestNumber);
                float highestNum = Float.parseFloat(highestNumber);
                Build build = builds.get(name);
                if (lowestNum <= build.getTotalCost() && build.getTotalCost() <= highestNum) {
                    if (i == 0) {
                        System.out.println("Filtered Builds:");
                    }
                    System.out.println((i + 1) + ". " + name);
                    i++;
                }
            }
            break;
        case "power":
            for (String name : builds.keySet()) {
                int lowestNum = Integer.parseInt(lowestNumber);
                int highestNum = Integer.parseInt(highestNumber);
                Build build = builds.get(name);
                if (lowestNum <= build.getTotalPower() && build.getTotalPower() <= highestNum) {
                    if (i == 0) {
                        System.out.println("Filtered Builds:");
                    }
                    System.out.println((i + 1) + ". " + name);
                    i++;
                }
            }
            break;
        case "compatibility":
            for (String name : builds.keySet()) {
                Build build = builds.get(name);
                if (build.getCompatibility().equals("Compatible")) {
                    if (i == 0) {
                        System.out.println("Filtered Builds:");
                    }
                    System.out.println((i + 1) + ". " + name);
                    i++;
                }
            }
            break;
        default:
            // add throw exception/assert if this works
            break;
        }
        if (i == 0) {
            System.out.println("No builds that meet specifications found.");
        }
    }
}