package model.TestToolUser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserDB implements Serializable {
	ArrayList<Teacher> teacherList;
	ArrayList<Student> studentList;
	
	public UserDB() {
		teacherList = new ArrayList<Teacher>();
		studentList = new ArrayList<Student>();
	}
	
	public void addTeacher(Teacher t) {
		teacherList.add(t);
	}
	
	public void addStudent(Student s) {
		studentList.add(s);
	}
	
	public boolean checkExisting(String email) {
		for(int i = 0; i <teacherList.size();i++) {
			Teacher teach = teacherList.get(i);
			if(teach.getEmail().equals(email)) {
				return true;
			}
		}
		for(int i = 0; i <studentList.size();i++) {
			Student stud = studentList.get(i);
			if(stud.getEmail().equals(email)) {
				return true;
				
			}
		}
		return false;
	}
	
	public ArrayList<Student> getStudents() {
		return studentList;
	}
	
	public User login(String email, String password) {
		System.out.println("attempting login"+teacherList.size());
		for(int i = 0; i <teacherList.size();i++) {
			Teacher teach = teacherList.get(i);
			if(teach.getEmail().equals(email)) {
				if(teach.getPassword().equals(password)) {
					teach.restoreObservable();
					return teach;
				}
				
					
			}
		}
		
		for(int i = 0; i <studentList.size();i++) {
			Student stud = studentList.get(i);
			if(stud.getEmail().equals(email)) {
				if(stud.getPassword().equals(password)) {
					stud.restoreObservable();
					return stud;
				}
				
			}
		}
		return null;
	}
	public void save() {
		System.out.println("saving...");
    	try
        {
           FileOutputStream fileOut =
           new FileOutputStream("testtooldb");
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(this);
           out.close();
           fileOut.close();

        }catch(IOException i)
        {
            i.printStackTrace();
        }
	}
	
 }
