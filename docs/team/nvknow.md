## Nvknow - Project Portfolio Page

### Overview

Hi, I am Cao Yikai, a year 3 student majoring in Industrial Engineering and minoring in Statistics.

Pet Clinic Management System(PCMS) is a project with Command Line Interface (CLI) developed by a team of five students following the course requirements of CS2113 within six weeks. PCMS is a desktop application that helps a clinic reception to record appointments, assign tasks, and manage data of pets/services/tasks/employees.

### Summary of Contributions

**Code contributed**

[RepoSense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=nvknow&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=nvknow&tabRepo=AY2223S1-CS2113-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- Implemented the attributes and functions of Employee and EmployeeList
  - What it does: add an employee into the employee list, remove an employee in the employee list, view the employees in the employee list, etc.
  - Justification: an employee is the one who operate each task, which is crucial in the management system.

- Implemented the attributes and functions of Service and ServiceList
  - What it does: add a service into the service list, remove a service in the service list, view the services in the service list, etc.
  - Justification: a service is attached to each appointment and used to improve the status of the pet, which is the service goal of the clinic.

- Implemented the attributes and functions of Ui
  - What it does: show logo and welcome messages to the user, read the user's command and show error messages when receiving a wrong input.
  - Justification: the Ui is essential for the Command Line Interface requirement.

- Implemented the tests of Employee and EmployeeList

**Enhancements implemented**

- Added the function of Finish Task
  - What it does: set the status of a task as done.
  - Justification: when all the tasks related to an appointment are set as done, the appointment is set as processed. So the completion of task is highly related to the completion of appointment.

- Added more diverse status to Pet
  - What it does: set built-in integer index of the pet. The higher the index, the better the condition of a pet. Each integer represents a specific status.
  - Justification: can be explained together by the justification of the next feature.

- Added the function of improving the status of a pet when an appointment is processed
  - What it does: when an appointment is processed, the built-in index of the related pet is increased, indicating a better status.
  - Justification: achieved the process of improving the condition of the pets by completing appointments, which is the core goal for the pet clinic.

- Added exception handling to EmployeeParser
- Added exception handling to ServiceParser
- Improved the index of Pet
- Improved the interface of Duke to be more OOP
- Improved the overall output information
- Fixed all the format errors in v1.0 checked by Gradle

**Contributions to the UG**

- Added the instructions of Finish Task
- Added Setting Up
- Updated Sample Workflow
- Fixed some formatting issues

**Contributions to the DG**

- Added Employee management feature and its Design considerations
- Added the UML diagram of Employee Add
- Added the UML diagram of Appointment Add
- Added the UML diagram of Service Add
- Added User Stories of v1.0 and v2.0
- Added Setting Up and Getting Started
- Added Non-Functional Requirements
- Reorganized the structure and catalog

**Contributions to team-based tasks**

- Served as a team leader
- Initiated and held weekly meetings
- Assigned weekly tasks to each group member
- Maintained the issue tracker
- Maintained the milestones
- Release management
- Opened pull request from team repo to module repo
- Carried some smoke-test on the deliverable
- Set up category tags of issues and bugs received in the Practical Exam Dry Run
- Fixed bugs related to employee received in the Practical Exam Dry Run

**Mentoring contributions**

- Helped team members to initiate the check of Gradle
- Helped team members to fix format errors checked by Gradle (which could be proven by any team member)

**Contributions beyond the project team**

- Reviewed the pull requests of another team: [CS2113-T17-3](https://github.com/nus-cs2113-AY2223S1/tp/pull/10)
- Reported more than 10 bugs in the Practical Exam Dry Run: [CS2113T-W11-1](https://github.com/nvknow/ped/issues)

  

