package computercomponentchooser;

import java.util.HashMap;
import java.util.Map;

import computercomponentchooser.exceptions.DuplicateBuildException;

public class BuildManager {
    private final Map<String, Build> builds;

    public BuildManager() {
        builds = new HashMap<>();
    }

    public void addBuild(Build build) throws DuplicateBuildException {
        if (builds.containsKey(build.getName())) {
            throw new DuplicateBuildException();
        }
        builds.put(build.getName(), build);
    }

    public void deleteBuild(String name) {
        builds.remove(name);
    }

    public Build getBuild(String name) {
        return builds.get(name);
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

    public BuildManager findBuilds(String searchTerm) {
        BuildManager foundBuilds = new BuildManager();
        for (String name : builds.keySet()) {
            if (name.contains(searchTerm)) {
                try {
                    foundBuilds.addBuild(builds.get(name));
                } catch (DuplicateBuildException e) {
                    // this should never happen, our build map has no duplicate keys
                }
            }
        }
        return foundBuilds;
    }
}