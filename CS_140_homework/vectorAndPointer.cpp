// bin/bash/author Jianlyu Mao

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <map>

using namespace std;

class StudentEnrollment{

public:
    struct Student;
    struct Course;

    void print_student(Student *sptr);

    void print_course(Course *cptr);

    void enroll(Student *sptr,Course *cptr);
};


struct StudentEnrollment::Student{

    string name;
    vector<Course *> courses;

};

struct StudentEnrollment::Course{
    string name;
    vector<Student *> students;
};


int main(){
    StudentEnrollment enrollment;

    StudentEnrollment::Student studs[5]={{"Nancy"},{"Bob"},{"Gordon"},{"Olivia"},{"Dean"}};

    StudentEnrollment::Course cous[5]={{"Biology"},{"Physics"},{"English"},{"CS140"},{"CS104"}};

    // This segment of codes simply enroll some students to some classes manually.
    enrollment.enroll(&studs[0],&cous[0]);
    enrollment.enroll(&studs[1],&cous[0]);
    enrollment.enroll(&studs[2],&cous[0]);
    enrollment.enroll(&studs[3],&cous[0]);
    enrollment.enroll(&studs[4],&cous[0]);
    enrollment.enroll(&studs[0],&cous[1]);
    enrollment.enroll(&studs[1],&cous[4]);
    enrollment.enroll(&studs[3],&cous[2]);
    enrollment.enroll(&studs[2],&cous[1]);
    enrollment.enroll(&studs[3],&cous[3]);


    for (size_t i=0; i<5;i++){
        enrollment.print_student(&studs[i]);
        cout << "\n";
    }

    for (size_t i=0; i<5;i++){
        enrollment.print_course(&cous[i]);
        cout << "\n";
    }


    return 0;
}

void StudentEnrollment::print_student(Student *sptr){
    cout << "The name of student: " << sptr->name << endl;
    cout << "The courses taken: " << endl;
    for (size_t i=0;i<sptr->courses.size();i++){
        Course* take=sptr->courses.at(i);
        cout << take->name << endl;
    }
}

void StudentEnrollment::print_course(Course *cptr){
    cout << "The name of course: " << cptr->name << endl;
    cout << "The names of students taking the course: " << endl;
    for (size_t i=0; i<cptr->students.size();i++){
        Student* student_name=cptr->students.at(i);
        cout << student_name->name << endl;
    }
}

void StudentEnrollment::enroll(Student *sptr,Course *cptr){
    // for student, we add the given course to his/her curriculum.
    sptr->courses.push_back(cptr);
    // for course, we add the student to the class list.
    cptr->students.push_back(sptr);
}
