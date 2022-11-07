## ChengYoghurt - Project Portfolio Page

### Overview
CS2113 Team project - A Pet Clinic Management System.  
Designed for head nurse who is skilled in using CLI.
---

### Summary of Contributions
Basically responsible for functions related to appointment.

##### Code contributed  

[RepoSense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=ChengYoghurt&tabRepo=AY2223S1-CS2113-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)  


###Enhancements implemented

####addAppointment
What it does:
- Add an appointment to the list.
- It relates to a clinic service and a pet, with a valid date.

Justification:
- Will check every input params, i.e. `service`, `pet` and `date` before adding. This is to make sure it only relates to existing objects.

Highlights:
- This enhancement was the basis for further task management, as every task is created according to an existing appointment.
- It also interacts with class `service` and `pet` to provide user, i.e. the head nurse, with more detailed information on an appointment. 

####removeAppointment
What it does:
- Remove an existing appointment from the list.

Justification:
- Since appointment is one of the *composition* of task, after removing one appointment leads all related tasks been removed automatically.

####listAppointment
What it does:
- Show the detailed info of all appointments from the list.

####findAppointment
What it does:
- Find the appointment with given id from the list.

Justification:
- It's not an API open for user, just serving as a helping function.

####updateAppointmentStatus
What it does:
- Update the appointment status at specific time. 

Justification:
- Use status defined in `AppointmentStatus` class.
- When an appointment is first created, its status is `AppointmentStatus::PENDING`.
- When at least one of the tasks is derived from the appointment, while not all tasks are marked as done, it updates the status to `AppointmentStatus::PROCESSING`.
- When all tasks from the appointment are marked as done, it updates the status to `AppointmentStatus::PROCESSED`.

Highlights:
- This enhancement helps the appointment status to be updated without manually settings, as it is automatically called every time after executing `addTask` or finishing one `Task`.

####checkFormattedDate
What it does:
- Check the given string before setting it as the `date` of one appointment.
- Given string must have format of `yyyy-MM-dd`.

Justification:
- When an appointment is first created, its status is `AppointmentStatus::PENDING`
- When at least one of the tasks is derived from the appointment, while not all tasks are marked as done, it updates the status to `AppointmentStatus::PROCESSING`
- When all tasks from the appointment are marked as done, it updates the status to `AppointmentStatus::PROCESSED`

Highlights:
- It uses the `SimpleDateFormat` from java library to check format and perform validation.
- It examines not only format, but also the logic, i.e. doesn't accept passed date, of the input date string.

**others**
- Added status of appointment as `AppointmentStauts` enum class
- Added exception handler to functions related to appointment
- Improved parser for removing appointment
- Fixed nearly half of the format errors in v1.0 checked by Gradle
- 
**Contributions to the UG**
- First version of functions related to appointment

**Contributions to the DG**  
- Added Appointment manage feature
- Added Appointment status feature
- Added Appointment date feature
- Added Service manage feature
- Added the UML diagram of appointment remove
- Added the UML diagram of employee remove
- Added the UML diagram of service remove

**Mentoring contributions**

- Helped team members to use `git` command line interface when `source tree` somehow failed
- Helped team members to use `coverage` when testing

**Contributions to team-based tasks**

- Engaged in discussion on design and enhancements of the product
- Fixed issues of projects after PE dry-run

**Contributions beyond the project team**
- Reviewed the pull requests and DG of another [team](https://github.com/nus-cs2113-AY2223S1/tp/pull/3)
- Tested the product of other two teams during PE [dry-run](https://github.com/ChengYoghurt/ped/tree/main/files)