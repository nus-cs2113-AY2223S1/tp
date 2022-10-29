package seedu.duke.records;

import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.biometrics.WeightAndFatList;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;

import java.util.ArrayList;

import static seedu.duke.command.DateCommand.sortDateForAll;

public class RecordList {
    ArrayList<Record> recordList;

    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    public void addData(Record data) {
        recordList.add(data);
    }

    public Record getRecord(int index) throws IllegalValueException {
        if (index < 0 || index >= getRecordListSize()) {
            throw new IllegalValueException("Please provide a valid index for the record");
        }
        return recordList.get(index);
    }

    public void removeRecord(int index) throws IllegalValueException {
        if (index < 0 || index >= getRecordListSize()) {
            throw new IllegalValueException("Please provide a valid index for the record");
        }
        recordList.remove(index);
    }

    public ArrayList<Record> getRecordList(ArrayList<WeightAndFat> weightAndFatList,
                                           ArrayList<Food> foodArrayList,
                                           ArrayList<Exercise> exerciseArrayList) {
        recordList = new ArrayList<Record>();
        for (WeightAndFat w: weightAndFatList) {
            recordList.add(w);
        }
        for (Food f: foodArrayList) {
            recordList.add(f);
        }
        for (Exercise ex: exerciseArrayList) {
            recordList.add(ex);
        }
        sortDateForAll(recordList);
        return recordList;
    }

    public int getRecordListSize() {
        return recordList.size();
    }
}
