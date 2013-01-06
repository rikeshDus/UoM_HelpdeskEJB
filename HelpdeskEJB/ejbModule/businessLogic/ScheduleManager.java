package businessLogic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ScheduleManager
 */
@Stateless
@LocalBean
public class ScheduleManager {

    /**
     * Default constructor. 
     */
    public ScheduleManager() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    /*Generate schedule if not sport.
     * retrieve all free slots of event participant
     *  return to the user
     */
   public ArrayList<Timetable> generateNonSportSchedule(int faculties[], String course[] ,Date startDate, Date endDate){
    	
    	
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	ArrayList<CourseStructure > tempCourseStructure = new ArrayList<CourseStructure>();
    	ArrayList<Timetable> tempFreeSlot = new ArrayList<Timetable>();
    	ArrayList<Timetable> allFreeSlot = new ArrayList<Timetable>();
    	
    	
    	// get all structureCourse based on faculty
    	if(faculties.length>0){
    		FacultyCourseManager facultyCourseManager = new FacultyCourseManager();
    		allCourseStructure = facultyCourseManager.getCourseByFaculty(faculties);
    	}//end if(faculties.length>0)
    	
    	//get all structureCourse based on course
    	if(course.length>0){
    		CourseStructureManager coursestructure = new CourseStructureManager();
    		tempCourseStructure = coursestructure.getAllCourseStructureByCourse(course);
    	}//end if(course.length>0)
    	
    	//merge both CourseStructure Arraylist
    	for(int i =0 ; i<tempCourseStructure.size();i++){
    		if(!allCourseStructure.contains(tempCourseStructure.get(i))){
    			allCourseStructure.add(tempCourseStructure.get(i));
    		}//end if(!allCourseStructure.contains(tempCourseStructure.get(i))){
    	}//for(int i =0 ; i<tempCourseStructure.size();i++)
    	
    	
    	
    	 /* implement a genetic algorithm
    	 * to find the best fit date
    	 */
    	
    	//temporarily
    	
    	
    	//initially
    	TimetableManager timetableManager = new TimetableManager();
    	allFreeSlot = timetableManager.getAllTimetableByStructureId(allCourseStructure.get(0).getCourse_structure_id(), startDate, endDate);
    	
    	
    	//Loop in all course
    	for(int i=1;i<allCourseStructure.size();i++){
    		
    		tempFreeSlot = timetableManager.getAllTimetableByStructureId(allCourseStructure.get(i).getCourse_structure_id(), startDate, endDate);
    		
    		for(int j=0;j<tempFreeSlot.size();j++){
    			
    			if(!(allFreeSlot.contains(tempFreeSlot.get(j)))){
    				allFreeSlot.add(tempFreeSlot.get(j));
    			}//end if(allFreeSlot.contains(tempFreeSlot.get(j))){
    			
    		}//end for(int j=0;j<tempFreeSlot.size();j++){
    		
    	}//end for(int i=0;i<allCourseStructure.size();i++){
    	
    	
    	return allFreeSlot;    	
    }//end of 
    
    
}
