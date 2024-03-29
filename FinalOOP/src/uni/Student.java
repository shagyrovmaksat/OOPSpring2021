package uni;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Represents Student information
 *
 */
public class Student extends User {
    
	private static final long serialVersionUID = 1L;
	
	private int yearOfStudy = 1;
    /**
     * Speciality of the student
     */
    private Speciality speciality;
    /**
     * Faculty of the student
     */
    private Faculty faculty;

    /**
     * Courses that student registered
     */
    private HashSet<Course> courses = new HashSet<Course>();
  
    /**
     * Studying degree
     */
    private Degree degree;
    /**
     * Schedule of the student
     */
    private Schedule schedule;
    /**
     * GPA of the student
     */
    private double gpa;
    /**
     * Limit of credits that student has
     */
    private int limitOfCredits;
    /**
     * Transcript of the student
     */
    private Trancsript transcript = new Trancsript();
    /**
     * Books that student gets
     */
    private Vector<Book> books;
    /**
     * Number of FX tries of the student 
     */
    private HashMap<Course, Integer> numOfTriesFX= new HashMap<Course, Integer>();
    /**
     * Number of F tries of the student 
     */
    private HashMap<Course, Integer> numOfTriesF= new HashMap<Course, Integer>();

    public Student() {
    	super();
    }
    
    public Student(String name, String surname, String password) {
    	super(name, surname, password);
    }
    
    public Student(String name, String surname, String password, Speciality speciality, Faculty faculty, Degree degree, int limitOfCredits) {
    	this(name, surname, password);
    	this.setFaculty(faculty);
    	this.setDegree(degree);
    	this.setLimitOfCredits(limitOfCredits);
    }

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	/**
	 * Increases study year
	 */
	public void increaseYearOfStudy() {
		yearOfStudy += 1;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public HashSet<Course> getCourses() {
		return courses;
	}

	public void setCourses(HashSet<Course> courses) {
		this.courses = courses;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Double getGpa() {
		return gpa;
	}

	/**Calculates GPA by elective courses
	 * @return technical GPA
	 */
	public Double viewTechnicalGpa() {
		double res = 0;
		int cnt = 0;
		for(Course c : this.courses) {
			if(c.getCourseType().equals(CourseType.ELECTIVE)) {
				res += this.transcript.getCourseMark(c).getGpaMark();
				cnt++;
			}
		}
		this.gpa = res/cnt;
		return gpa;
	}

	/**
	 * Calculates GPA of the student
	 */
	public void calculateGpa() {
		double res = 0;
		int cnt = 0;
		for(Course c : this.courses) {
			res += this.transcript.getCourseMark(c).getGpaMark();
			cnt++;
		}
		this.gpa = res/cnt;
	}

	public int getLimitOfCredits() {
		return limitOfCredits;
	}
	
	
	public void setLimitOfCredits(int limitOfCredits) {
		this.limitOfCredits = limitOfCredits;
	}

	public Trancsript getTranscript() {
		return transcript;
	}

	public Vector<Book> getBooks() {
		return books;
	}
  
	public void getBook(Book book) {
	}
	
	public void returnBook(Book book) {
		
	}
	
	public Mark getCourseMark(Course course) {
		return this.transcript.getCourseMark(course);
	}
	
	
	/**Rates the teacher
	 * @param teacher
	 * @param rate
	 */
	public void rateTeacher(Teacher teacher, int rate) {
		teacher.updateRate(rate);
	}
	
	/**Registers student for course if registration is open
	 * @param course new course
	 * @return true if successfully added, otherwise false
	 */
	public boolean registerForCourse(Course course) {
		if (Database.registrationIsOpen && Database.courses.contains(course) && !courses.contains(course) && course.getCountOfStudents() <= course.getLimitOfStudents()) {
			course.increaseCountOfStudents();
			courses.add(course);
			return Database.getTeacherByCourse(course).getCourseStudents(course).add(this);
		}
		return false;
	}
	
	
	/**Drops course if registration is open
	 * @param course
	 * @return true if successfully dropped, otherwise false
	 */
	public boolean dropCourse(Course course) {
		if (Database.registrationIsOpen && Database.courses.contains(course) && courses.contains(course)) {
			course.decreaseCountOfStudents();
			courses.remove(course);
			return Database.getTeacherByCourse(course).getCourseStudents(course).remove(this);
		}
		return false;
	}

	public void addNumOfTriesExam(Course course, int num){
    	this.numOfTriesFX.put(course, (Integer) num);
	}

	public void updNumOfTriesExam(Course course, int num){
		this.numOfTriesFX.put(course, (Integer) num);
	}

	public int getNumOfTriesExam(Course course){
    	return this.numOfTriesFX.get(course);
	}

	public void addNumOfTriesCourse(Course course, int num){
    	this.numOfTriesF.put(course, (Integer) num);
	}

	public void updNumOfTriesCourse(Course course, int num){
		this.numOfTriesF.put(course, (Integer) num);
	}

	public int getNumOfTriesCourse(Course course){
    	return this.numOfTriesF.get(course);

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + limitOfCredits;
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
		result = prime * result + yearOfStudy;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (degree != other.degree)
			return false;
		if (faculty != other.faculty)
			return false;
		if (Double.doubleToLongBits(gpa) != Double.doubleToLongBits(other.gpa))
			return false;
		if (limitOfCredits != other.limitOfCredits)
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (speciality != other.speciality)
			return false;
		if (transcript == null) {
			if (other.transcript != null)
				return false;
		} else if (!transcript.equals(other.transcript))
			return false;
		if (yearOfStudy != other.yearOfStudy)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [yearOfStudy=" + yearOfStudy + ", speciality=" + speciality + ", faculty=" + faculty
				+ ", courses=" + courses + ", degree=" + degree + ", schedule=" + schedule + ", gpa=" + gpa
				+ ", limitOfCredits=" + limitOfCredits + ", transcript=" + transcript + ", books=" + books + "]";
	}
}
