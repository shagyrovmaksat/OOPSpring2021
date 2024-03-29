package uni;

import java.io.Serializable;
import java.util.HashMap;

public class Trancsript implements Serializable {

	private static final long serialVersionUID = 1L;
	
	HashMap<Course, Mark> coursesAndMarks = new HashMap<Course, Mark>();
	
	public HashMap<Course, Mark> getCoursesAndMarks() {
		return this.coursesAndMarks;
	}
	
	public void addCourseAndMark(Course course, Mark mark) {
		coursesAndMarks.put(course, mark);
	}
	
	public void updateCourseMark(Course course, Mark mark) {
		coursesAndMarks.put(course, mark);
	}

	public Mark getCourseMark(Course course) {
		return coursesAndMarks.get(course);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesAndMarks == null) ? 0 : coursesAndMarks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trancsript other = (Trancsript) obj;
		if (coursesAndMarks == null) {
			if (other.coursesAndMarks != null)
				return false;
		} else if (!coursesAndMarks.equals(other.coursesAndMarks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transript [coursesAndMarks=" + coursesAndMarks + "]";
	}


	

}
