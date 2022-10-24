package computercomponentchooser;

import computercomponentchooser.components.Component;
import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.Cooler;
import computercomponentchooser.components.Case;

import java.util.ArrayList;

/**
 * Build is a class that represents a build of computer components and contains methods associated with the build.
 */
public class Build {
    private String name;
    private final LinkedHashMap2D<Component> components = new LinkedHashMap2D<>();


    /**
     * Initializes a new Build object.
     *
     * @param name The name of the build.
     */
    public Build(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the build.
     *
     * @return The name of the build.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the build.
     *
     * @param name The name of the build.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a component to the build.
     *
     * @param type The type of component to be added.
     * @param component The component to be added.
     */
    public void addComponent(String type, Component component) {
        switch (type) {
        case "cpu":
            components.addElement("cpu", component.getName(), component);
            break;
        case "memory":
            components.addElement("memory", component.getName(), component);
            break;
        case "motherboard":
            components.addElement("motherboard", component.getName(), component);
            break;
        case "powersupply":
            components.addElement("powersupply", component.getName(), component);
            break;
        case "gpu":
            components.addElement("gpu", component.getName(), component);
            break;
        case "drive":
            components.addElement("drive", component.getName(), component);
            break;
        case "monitor":
            components.addElement("monitor", component.getName(), component);
            break;
        case "cooler":
            components.addElement("cooler", component.getName(), component);
            break;
        case "case":
            components.addElement("case", component.getName(), component);
            break;
        case "other":
            components.addElement("other", component.getName(), component);
            break;
        default:
            // throw exception
            break;
        }
    }

    /**
     * Deletes a component from the build.
     *
     * @param type The type of component to be removed.
     * @param name The name of the component to be removed.
     */
    public void deleteComponent(String type, String name) {
        components.removeElement(type, name);
    }

    /**
     * Gets the component of the specified type and name from the build.
     *
     * @param type The type of component to be retrieved.
     * @param name The name of the component to be retrieved.
     * @return The component of the specified type and name from the build.
     */
    public Component getComponent(String type, String name) {
        return components.getElement(type, name);
    }

    /**
     * Formats the components of the build into a string.
     *
     * @return The components of the build formatted into a string.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Component component : getAllComponents()) {
            sb.append(i).append(". ").append(component.toString()).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }

    /**
     * Formats the components of the build into a string appropriate for CSV files.
     *
     * @return The components of the build formatted into a string appropriate for CSV files.
     */
    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        for (Component component : getAllComponents()) {
            sb.append(this.name + ",").append(component.toCsv()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Gets all the components in the build in an ArrayList.
     *
     * @return The components of the build.
     */
    public ArrayList<Component> getAllComponents() {
        ArrayList<Component> allComponents = new ArrayList<>();
        for (String type : components.getTypeList()) {
            for (String name : components.getNamesOfTypeList(type)) {
                allComponents.add(components.getElement(type, name));
            }
        }
        return allComponents;
    }

    /**
     * Gets the total cost of the build by adding the cost of all the components in the build.
     *
     * @return The total cost of the build.
     */
    public float getTotalCost() {
        float totalCost = 0;
        for (Component component : getAllComponents()) {
            totalCost += Float.parseFloat(component.getPrice());
        }
        return totalCost;
    }

    /**
     * Gets the total power of the build by adding the wattage of all the components in the build.
     *
     * @return The total power of the build.
     */
    public int getTotalPower() {
        int totalPower = 0;
        for (Component component : getAllComponents()) {
            if (!component.getType().equals("powersupply")) {
                totalPower += Integer.parseInt(component.getPower());
            }
        }
        return totalPower;
    }

    /**
     * Checks the power compatibility of the build by checking if the total power of the build is less than the power
     * of the power supply.
     *
     * @return True if the build is power compatible, false otherwise.
     */
    public boolean checkPowerSupply() {
        int totalPower = getTotalPower();
        for (Component component : getAllComponents()) {
            if (component.getType().equals("powersupply")) {
                if (totalPower <= Integer.parseInt(component.getPower())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks the socket compatibility of the build by checking if the socket of the CPU is the same as the socket of
     * the motherboard and cooler.
     *
     * @return True if the build is socket compatible, false otherwise.
     */
    public boolean checkSocket() {
        String socket = "";
        boolean socketCompatible = false;
        for (Component component : getAllComponents()) {
            if (component.getType().equals("motherboard")) {
                socketCompatible = true;
                socket = ((Motherboard) component).getSocket();
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("cpu")) {
                if (!((Cpu) component).getSocket().equals(socket)) {
                    socketCompatible = false;
                }
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("cooler")) {
                if (!((Cooler) component).getSocket().equals(socket)) {
                    socketCompatible = false;
                }
            }
        }
        return socketCompatible;
    }

    /**
     * Checks the form factor compatibility of the build by checking if the form factor of the motherboard is the same
     * as the form factor of the case.
     *
     * @return True if the build is form factor compatible, false otherwise.
     */
    public boolean checkFormFactor() {
        String formFactor = "";
        for (Component component : getAllComponents()) {
            if (component.getType().equals("motherboard")) {
                formFactor = ((Motherboard) component).getFormFactor();
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("case")) {
                if (((Case) component).getFormFactor().equals(formFactor)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks the expansion slot compatibility of the build by checking if the expansion slots of the case are more
     * than the number of drives added to the build.
     *
     * @return True if the build is expansion slot compatible, false otherwise.
     */
    public boolean checkExpansionSlots() {
        int slot = 0;
        for (Component component : getAllComponents()) {
            if (component.getType().equals("drive")) {
                slot += 1;
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("case")) {
                if (slot <= Integer.parseInt(((Case) component).getExpansionSlots())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks the GPU slot compatibility of the build by checking if the GPU slot of the motherboard is the same as the
     * number of GPUs added to the build.
     *
     * @return True if the build is GPU slot compatible, false otherwise.
     */
    public boolean checkGpuSlot() {
        int slot = 0;
        for (Component component : getAllComponents()) {
            if (component.getType().equals("gpu")) {
                slot += 1;
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("motherboard")) {
                if (slot <= Integer.parseInt(((Motherboard) component).getGpuSlots())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks the memory slot compatibility of the build by checking if the memory slots of the motherboard are more
     * than the number of memory modules added to the build.
     *
     * @return True if the build is memory slot compatible, false otherwise.
     */
    public boolean checkMemorySlot() {
        int slot = 0;
        for (Component component : getAllComponents()) {
            if (component.getType().equals("memory")) {
                slot += 1;
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("motherboard")) {
                if (slot <= Integer.parseInt(((Motherboard) component).getMemorySlots())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the compatibility of the build by checking if the build is power, socket, form factor, expansion slot
     * and GPU slot compatible.
     *
     * @return Compatible if the build is compatible, otherwise Not compatible if the build is not compatible.
     */
    public String getCompatibility() {
        if (checkPowerSupply() && checkSocket() && checkGpuSlot() && checkMemorySlot() && checkFormFactor()
                && checkExpansionSlots()) {
            return "Compatible";
        } else {
            return "Not compatible";
        }
    }

    /**
     * Get the aggregated specifications of the build.
     *
     * @return The aggregated specifications of the build in a string.
     */
    public String getBuildInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Build name: ").append(name).append(System.lineSeparator());
        sb.append("Total cost: ").append(getTotalCost()).append(System.lineSeparator());
        sb.append("Total power: ").append(getTotalPower()).append(System.lineSeparator());
        sb.append("Compatibility: ").append(getCompatibility()).append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Get the all the compatibility info of the build.
     *
     * @return The compatibility info of the build in a string.
     */
    public String getCompatibilityInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Power supply: ").append(checkPowerSupply()).append(System.lineSeparator());
        sb.append("Socket: ").append(checkSocket()).append(System.lineSeparator());
        sb.append("GPU slot: ").append(checkGpuSlot()).append(System.lineSeparator());
        sb.append("Memory slot: ").append(checkMemorySlot()).append(System.lineSeparator());
        sb.append("Expansion slots: ").append(checkExpansionSlots()).append(System.lineSeparator());
        sb.append("Form factor: ").append(checkFormFactor()).append(System.lineSeparator());
        return sb.toString();
    }
}
