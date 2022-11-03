package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandSetLessonTest {

    @Test
    void setLesson_emptyList_returnsNoModulesToSet() {
        assertEquals("No modules available for lessons to be set.", CommandSetLesson.setLesson());
    }

}