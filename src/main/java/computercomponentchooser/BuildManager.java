package computercomponentchooser;

import computercomponentchooser.exceptions.BlankStringException;
import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.exceptions.UnknownCommandException;
import computercomponentchooser.exceptions.UnlistedBuildException;
import computercomponentchooser.exceptions.NegativeNumberException;

import java.util.HashMap;
import java.util.Map;

/**
 * BuildManager is the class that manages the builds and contains methods for the management of builds. E.g.
 * adding, deleting, filtering builds.
 */
public class BuildManager {
    private static Map<String, Build> builds;

    /**
     * Initializes a new BuildManager object.
     */
    public BuildManager() {
        builds = new HashMap<>();
    }

    /**
     * Adds a build to the buildManager.
     *
     * @param build The build to be added.
     * @throws DuplicateBuildException If the build already exists.
     */
    public void addBuild(Build build) throws DuplicateBuildException {
        if (builds.containsKey(build.getName())) {
            throw new DuplicateBuildException();
        }
        builds.put(build.getName(), build);
    }

    /**
     * Deletes a build from the buildManager.
     *
     * @param name The name of the build to be deleted.
     * @throws UnlistedBuildException If the build does not exist.
     */
    public void deleteBuild(String name) throws UnlistedBuildException {
        if (!builds.containsKey(name)) {
            throw new UnlistedBuildException();
        }
        builds.remove(name);
    }

    /**
     * Returns the build with the specified name.
     *
     * @param name The name of the build to be retrieved.
     * @return The build with the specified name.
     */
    public Build getBuild(String name) {
        return builds.get(name);
    }

    /**
     * Returns the builds.
     *
      * @return The builds.
     */
    public Map<String, Build> getBuilds() {
        return builds;
    }

    /**
     * Returns the number of builds stored by the program.
     *
     * @return The number of builds stored by the program.
     */
    public int size() {
        return builds.size();
    }

    /**
     * Returns a StringBuilder object containing the list of builds.
     *
     * @return The list of builds.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String name : builds.keySet()) {
            sb.append(i).append(". ").append(name).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }

    /**
     * Finds builds with names that contains the specified word.
     *
     * @param searchTerm The word to be searched for.
     * @throws BlankStringException If the search term is blank.
     */
    public void findBuilds(String searchTerm) throws BlankStringException {
        int i = 0;
        if (searchTerm.isBlank()) {
            throw new BlankStringException();
        } // Guard Clause, check if searchTerm is blank or not

        for (String name : builds.keySet()) {
            if (i == 0) {
                System.out.println("Found Builds:");
                i++;
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

    /**
     * Filters builds accordingly to user specified requirements.
     * This method can filter builds by compatibility and price or power within user specified range.
     *
     * @param filterType The type of filter to be applied.
     * @param lowestNumber The lowest number in the range.
     * @param highestNumber The highest number in the range.
     * @throws NumberFormatException If the user input is not a number.
     * @throws UnknownCommandException If the user input is not a valid type.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void filterBuilds(String filterType, String lowestNumber, String highestNumber) throws NumberFormatException,
            UnknownCommandException, NegativeNumberException {
        int i = 0;

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
            throw new UnknownCommandException();
        }
        if (i == 0) {
            System.out.println("No builds that meet specifications found.");
        }
    }

    /**
     * Filters builds by price. This method will lead to the printing of builds that meet the user specified price
     * range.
     * Swaps lowestNumber and highestNumber automatically if lowestNumber is greater than highestNumber.
     *
     * @param lowestNumber The lowest number in the range.
     * @param highestNumber The highest number in the range.
     * @param i The number of builds that meet the user specified requirements.
     * @return The number of builds that meet the user specified requirements.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    private int filterPrice(String lowestNumber, String highestNumber, int i) throws NumberFormatException,
            NegativeNumberException {
        assert i >= 0;
        for (String name : builds.keySet()) {
            float lowestNum = Float.parseFloat(lowestNumber);
            float highestNum = Float.parseFloat(highestNumber);
            if (highestNum < 0 || lowestNum < 0) {
                throw new NegativeNumberException();
            }
            Build build = builds.get(name);
            if (lowestNum > highestNum) {
                float temp = lowestNum;
                lowestNum = highestNum;
                highestNum = temp;
            }
            if (lowestNum <= build.getTotalCost() && build.getTotalCost() <= highestNum) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    /**
     * Filters builds by total power consumption. This method will lead to the printing of builds that meet the user
     * specified power consumption range.
     * range.
     * Swaps lowestNumber and highestNumber automatically if lowestNumber is greater than highestNumber.
     *
     * @param lowestNumber The lowest number in the range.
     * @param highestNumber The highest number in the range.
     * @param i The number of builds that meet the user specified requirements.
     * @return The number of builds that meet the user specified requirements.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    private int filterPower(String lowestNumber, String highestNumber, int i) throws NumberFormatException,
            NegativeNumberException {
        assert i >= 0;
        for (String name : builds.keySet()) {
            int lowestNum = Integer.parseInt(lowestNumber);
            int highestNum = Integer.parseInt(highestNumber);
            if (highestNum < 0 || lowestNum < 0) {
                throw new NegativeNumberException();
            }
            if (lowestNum > highestNum) {
                int temp = lowestNum;
                lowestNum = highestNum;
                highestNum = temp;
            }
            Build build = builds.get(name);
            if (lowestNum <= build.getTotalPower() && build.getTotalPower() <= highestNum) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    /**
     * Filters builds by compatibility. This method will lead to the printing of builds which parts are compatible with
     * each other.
     *
     * @param i The number of builds that meet the user specified requirements.
     * @return The number of builds that meet the user specified requirements.
     */
    private int filterCompatibility(int i) {
        assert i >=0;
        for (String name : builds.keySet()) {
            Build build = builds.get(name);
            if (build.getCompatibility().equals("Compatible")) {
                i = printFilteredList(i, name);
            }
        }
        return i;
    }

    /**
     * This method prints out a build that meets the user specified requirements.
     *
     * @param i The number of builds that meet the user specified requirements.
     * @param name The name of the build.
     * @return The number of builds that meet the user specified requirements.
     */
    private int printFilteredList(int i, String name) {
        if (i == 0) {
            System.out.println("Filtered Builds:");
        }
        System.out.println((i + 1) + ". " + name);
        i++;
        return i;
    }

    /**
     * Finds the build with the specified name and returns a boolean value to indicate if the build is found.
     *
     * @param buildName The name of the build to be deleted.
     * @return True if the build is deleted, false if the build is not found.
     */
    public boolean doesBuildExist(String buildName) {
        return builds.containsKey(buildName);
    }
}