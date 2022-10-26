package seedu.duke.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class SelectedModuleTest {

    @Test
    public void testEquals_instanceWithItself_true(){
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        int semester = 1;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());

        // Use assert true over assert equals to for test coverage of equals method
        assertTrue(selectedModule.equals(selectedModule));
    }

    @Test
    public void testNotEquals_instanceWithAnotherInstanceWithDifferentSemesterAndSameModule_false(){
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        int semester = 1;
        int semesterDifferent = 2;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());

        SelectedModule selectedModuleWithDifferentSemester = new SelectedModule(module,semesterDifferent);
        // Use assert true over assert equals to for test coverage of equals method
        assertFalse(selectedModule.equals(selectedModuleWithDifferentSemester));
    }

    @Test
    public void testNotEquals_instanceWithAnotherInstanceWithSameSemesterAndDifferentModule_false(){
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        // validate correct module details
        Module moduleDifferent = Module.get("CS1231");
        assertNotNull(moduleDifferent);
        assertEquals("CS1231", moduleDifferent.moduleCode);
        assertEquals("Discrete Structures", moduleDifferent.title);
        assertEquals(List.of(3, 1, 0, 3, 3), moduleDifferent.workload);
        assertEquals(2, moduleDifferent.semesterData.size());
        assertEquals("Computer Science", moduleDifferent.department);
        assertEquals("Computing", moduleDifferent.faculty);

        int semester = 1;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());

        SelectedModule selectedModuleWithDifferentModule = new SelectedModule(moduleDifferent,semester);
        // Use assert false over assert equals to for test coverage of equals method
        assertFalse(selectedModule.equals(selectedModuleWithDifferentModule));
    }

    @Test
    public void testEquals_notInstanceOfSelectedModule_false(){
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        int semester = 1;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());

        // Use assert false over assert equals to for test coverage of equals method
        assertFalse(selectedModule.equals(module));
    }

    @Test
    public void testEquals_instanceWithSameSemesterSameModuleDifferentSlots_true(){
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        int semester = 1;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());
        selectedModule.selectSlot(LessonType.TUTORIAL,"02A");

        SelectedModule selectedModuleDifferentSlot = new SelectedModule(module, semester);
        selectedModuleDifferentSlot.selectSlot(LessonType.TUTORIAL, "02B");

        Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
        assertEquals("02A",selectedSlots.get(LessonType.TUTORIAL));

        Map<LessonType, String> selectedSlotsDifferent = selectedModuleDifferentSlot.getSelectedSlots();
        assertEquals("02B",selectedSlotsDifferent.get(LessonType.TUTORIAL));

        // Use assert true over assert equals to for test coverage of equals method
        assertTrue(selectedModule.equals(selectedModuleDifferentSlot));

    }

    @Test
    public void validateDataAfterSelectingModuleSlot_cs1010s_tutorialSlot_02B_correctDetails() {
        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        int semester = 1;

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertNotNull(selectedModule.getModule());

        Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
        assertEquals("02A",selectedSlots.get(LessonType.TUTORIAL));
        selectedModule.selectSlot(LessonType.TUTORIAL, "02B");
        assertEquals("02B",selectedSlots.get(LessonType.TUTORIAL));
    }

}