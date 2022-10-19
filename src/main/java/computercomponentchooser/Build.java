package computercomponentchooser;

import computercomponentchooser.components.Component;
import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Motherboard;

import java.util.ArrayList;

public class Build {
    private String name;
    private final LinkedHashMap2D<Component> components = new LinkedHashMap2D<>();



    public Build(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        default:
            // throw exception
            break;
        }
    }

    public void deleteComponent(String type, String name) {
        components.removeElement(type, name);
    }

    public Component getComponent(String type, String name) {
        return components.getElement(type, name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Component component : getAllComponents()) {
            sb.append(i).append(". ").append(component.toString()).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }

    public ArrayList<Component> getAllComponents() {
        ArrayList<Component> allComponents = new ArrayList<>();
        for (String type : components.getTypeList()) {
            for (String name : components.getNamesOfTypeList(type)) {
                allComponents.add(components.getElement(type, name));
            }
        }
        return allComponents;
    }

    public float getTotalCost() {
        float totalCost = 0;
        for (Component component : getAllComponents()) {
            totalCost += Float.parseFloat(component.getPrice());
        }
        return totalCost;
    }

    public int getTotalPower() {
        int totalPower = 0;
        for (Component component : getAllComponents()) {
            if (!component.getType().equals("powersupply")) {
                totalPower += Integer.parseInt(component.getPower());
            }
        }
        return totalPower;
    }

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

    public boolean checkSocket() {
        String socket = "";
        for (Component component : getAllComponents()) {
            if (component.getType().equals("cpu")) {
                socket = ((Cpu) component).getSocket();
            }
        }
        for (Component component : getAllComponents()) {
            if (component.getType().equals("motherboard")) {
                if (socket.equals(((Motherboard) component).getSocket())) {
                    return true;
                }
            }
        }
        return false;
    }

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

    public String getCompability() {
        if (checkPowerSupply() && checkSocket() && checkGpuSlot() && checkMemorySlot()) {
            return "Compatible";
        } else {
            return "Not compatible";
        }
    }

    public String getBuildInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Build name: ").append(name).append(System.lineSeparator());
        sb.append("Total cost: ").append(getTotalCost()).append(System.lineSeparator());
        sb.append("Total power: ").append(getTotalPower()).append(System.lineSeparator());
        sb.append("Compatibility: ").append(getCompability()).append(System.lineSeparator());
        return sb.toString();
    }

    public String getCompatibilityInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Power supply: ").append(checkPowerSupply()).append(System.lineSeparator());
        sb.append("Socket: ").append(checkSocket()).append(System.lineSeparator());
        sb.append("GPU slot: ").append(checkGpuSlot()).append(System.lineSeparator());
        sb.append("Memory slot: ").append(checkMemorySlot()).append(System.lineSeparator());
        return sb.toString();
    }
}
