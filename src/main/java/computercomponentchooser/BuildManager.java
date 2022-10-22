package computercomponentchooser;

import java.util.HashMap;
import java.util.Map;

import computercomponentchooser.exceptions.BlankStringException;
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

    public void findBuilds(String searchTerm) throws BlankStringException {
        int i = 0;
        if (searchTerm.isBlank()) {
            throw new BlankStringException();
        } // Guard Clause, check if searchTerm is blank or not

        for (String name : builds.keySet()) {
            if (i == 0) {
                System.out.println("Found Builds:");
            }
            if (name.contains(searchTerm)) {
                System.out.println(name);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("No builds that meet specifications found.");
        }
    }

    public void filterBuilds(String filterType, String lowestNumber, String highestNumber) {
        int i = 0;
        try {
            switch (filterType) {
            case "price":
                i = filterPrice(lowestNumber, highestNumber, i);
                break;
            case "power":
                i = filterPower(lowestNumber, highestNumber, i);
                break;
            case "compatibility":
                i = filterCompatibility(i);
                break;
            default:
                // add throw exception/assert if this works
                break;
            }
            if (i == 0) {
                System.out.println("No builds that meet specifications found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static int filterPrice(String lowestNumber, String highestNumber, int i) throws NumberFormatException {
        for (String name : builds.keySet()) {
            float lowestNum = Float.parseFloat(lowestNumber);
            float highestNum = Float.parseFloat(highestNumber);
            if (highestNum < 0 || lowestNum < 0) {
                System.out.println("Please enter number(s) greater than 0.");
                break;
            }
            Build build = builds.get(name);
            if (lowestNum > highestNum) {
                System.out.println("Please enter a valid range.");
                break;
            }
            if (lowestNum <= build.getTotalCost() && build.getTotalCost() <= highestNum) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    private static int filterPower(String lowestNumber, String highestNumber, int i) throws NumberFormatException {
        for (String name : builds.keySet()) {
            int lowestNum = Integer.parseInt(lowestNumber);
            int highestNum = Integer.parseInt(highestNumber);
            if (highestNum < 0 || lowestNum < 0) {
                System.out.println("Please enter number(s) greater than 0.");
                break;
            }
            if (lowestNum > highestNum) {
                System.out.println("Please enter a valid range.");
                break;
            } // different placement compared to price filter to avoid the similar lines warning
            Build build = builds.get(name);
            if (lowestNum <= build.getTotalPower() && build.getTotalPower() <= highestNum) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    private static int filterCompatibility(int i) {
        for (String name : builds.keySet()) {
            Build build = builds.get(name);
            if (build.getCompatibility().equals("Compatible")) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    private static int printFilteredList(int i, String name) {
        if (i == 0) {
            System.out.println("Filtered Builds:");
        }
        System.out.println((i + 1) + ". " + name);
        i++;
        return i;
    }
}